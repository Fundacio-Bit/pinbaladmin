package org.fundaciobit.pinbaladmin.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAnnex;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleMetadata;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleReviser;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignature;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestBase;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSigner;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.jersey.ApiFirmaAsyncSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.DocumentEJB;
import org.fundaciobit.pinbaladmin.ejb.OperadorService;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.OperadorFields;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "DocumentLogicaEJB")
public class DocumentLogicaEJB extends DocumentEJB implements DocumentLogicaService {

	@EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
	protected DocumentSolicitudLogicaService documentSolicitudLogicaEjb;

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;

	@EJB(mappedName = OperadorService.JNDI_NAME)
	protected OperadorService operadorEjb;

	@EJB(mappedName = EventLogicaService.JNDI_NAME)
	protected EventLogicaService eventLogicaEjb;

	@EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FitxerService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.ejb.FitxerService fitxerEjb;

	@Override
	@PermitAll
	public Document create(Document instance) throws I18NException {
		return super.create(instance);
	}

	@PermitAll
	@Override
	public Document update(Document instance) throws I18NException {
		return super.update(instance);
	}

	protected ApiFirmaAsyncSimple getApiFirmaAsyncSimple() throws I18NException {

		String host = Configuracio.getPortafibGatewayV2();
		String username = Configuracio.getPortafibUsername();
		String password = Configuracio.getPortafibPassword();

		ApiFirmaAsyncSimpleJersey api;

		try {
			new URL(host);
			api = new ApiFirmaAsyncSimpleJersey(host, username, password);

		} catch (MalformedURLException urle) {
			String errorMsg = "Error a la URL de conexió amb PortaFIB. Revisar la URL de la propietat "
					+ Constants.PINBALADMIN_PROPERTY_BASE + "portafib.apifirmaasync.url" + " de l'arxiu: "
					+ Constants.PINBALADMIN_PROPERTY_BASE + "system.properties.";

			throw new I18NException(errorMsg + "   -   " + urle.getMessage());
		} catch (Exception e) {
			throw new I18NException("error.portafib.conexio.api",
					Constants.PINBALADMIN_PROPERTY_BASE + "system.properties.", e.getMessage());
		}

		// api.setConnectionTimeoutMs(20000); // 20 segons
		// api.setReadTimeoutMs(20000); // 20 segons

		return api;
	}

	@Override
	public void enviarDocumentDGPortaFIB(Long docID, String destinatariNif, String remitent) throws I18NException {

		Long soliID = documentSolicitudLogicaEjb.executeQueryOne(DocumentSolicitudFields.SOLICITUDID,
				DocumentSolicitudFields.DOCUMENTID.equal(docID));
		
		Document doc = this.findByPrimaryKey(docID);
		SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);
		
		String tipusPeticio;
		if (doc.getTipus() == Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF) {
			tipusPeticio = "Solicitud";
		} else {
			tipusPeticio = "Formulari AEAT";
		}
		
		String titolPeticio = tipusPeticio + " Autorització PINBAL. Procediment: " + soli.getProcedimentCodi();
		String description = soli.getProcedimentCodi() + " - " + soli.getProcedimentNom();
		String reason = tipusPeticio + " d'autorització als Serveis de la Plataforma d'Intermediació: SVD";

		Long idPortafib = crearIEnviarPeticioDeFirma(doc, destinatariNif, titolPeticio, description, reason,
				remitent);

		log.info("Peticio de firma creada: " + idPortafib);
		// S'ha d'afegir un camp a la taula documentSolicitud per saber l'estat a PortaFIB

		doc.setNotes(idPortafib.toString());
		this.update(doc);

		if (doc.getTipus() == Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF) {
			soli.setEstatID(Constants.SOLICITUD_ESTAT_PENDENT_Firma_Director);
			soli.setPortafibID(idPortafib);
			solicitudLogicaEjb.update(soli);
		}

		String msg = "Peticio de firma enviada a Portafib.\n" + "Remitent: " + remitent + "\n" + "Destinatari: "
				+ destinatariNif + "\n" + "Fitxer: " + doc.getNom();

		afegirEventSolicitudEnviada(soliID, remitent, msg);
	}

	public Long crearIEnviarPeticioDeFirma(Document doc, String destinatariNif, String titolPeticio,
			String description, String reason, String remitent) throws I18NException {

		String languageUI = "ca";
		String languageDoc = "ca";

		// Fitxer a Firmar
		FirmaAsyncSimpleFile fitxerAFirmar = getFitxerPortafibFromDoc(doc);
		if (fitxerAFirmar == null) {
			throw new I18NException("genapp.comodi", "No s'ha definit fitxer a firmar");
		}

		Long tipusDocumentalID = 14L; // Elegir un tipus documental: Autorització. 14 - Sol·licitud

		String senderUsername = remitent;
		String senderFullName = operadorEjb.executeQueryOne(OperadorFields.NOM,
				OperadorFields.USERNAME.equal(remitent));

		FirmaAsyncSimpleSignatureBlock[] signatureBlocks = convertNifToSignatureBlocks(destinatariNif);

		String profileCode = Configuracio.getPortafibProfile();
		int priority = FirmaAsyncSimpleSignatureRequestWithSignBlockList.PRIORITY_NORMAL_NORMAL;

		// Annexes
		List<FirmaAsyncSimpleAnnex> annexs = new ArrayList<FirmaAsyncSimpleAnnex>();

		String title = titolPeticio.length() > 250 ? titolPeticio.substring(0, 250) : titolPeticio;

		FirmaAsyncSimpleFile originalDetachedSignature = null;

		String descripcioTipusDocumental = null;

		String expedientCode = null;
		String expedientName = null;
		String expedientUrl = null;
		String procedureCode = null;
		String procedureName = null;
		String additionalInformation = null;
		Double additionalInformationEvaluable = null;

		List<FirmaAsyncSimpleMetadata> metadadaList = null;

		FirmaAsyncSimpleSignatureRequestBase signatureRequestBase;
		signatureRequestBase = new FirmaAsyncSimpleSignatureRequestBase(profileCode, title, description, reason,
				fitxerAFirmar, originalDetachedSignature, tipusDocumentalID, descripcioTipusDocumental, languageDoc,
				languageUI, priority, senderUsername, senderFullName, expedientCode, expedientName, expedientUrl,
				procedureCode, procedureName, additionalInformation, additionalInformationEvaluable, annexs,
				metadadaList);

		Long peticioDeFirmaID;

		FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest;
		signatureRequest = new FirmaAsyncSimpleSignatureRequestWithSignBlockList(signatureRequestBase, signatureBlocks);

		ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();
		
		try {
			peticioDeFirmaID = api.createAndStartSignatureRequestWithSignBlockList(signatureRequest);
			return peticioDeFirmaID;
		} catch (AbstractApisIBException e) {
//			String msg = I18NLogicUtils.tradueix(new Locale(languageUI), "error.portafib.generic", e.getMessage());
			throw new I18NException("error.portafib.generic", new I18NArgumentString(e.getMessage()));
		}
	}

	protected FirmaAsyncSimpleFile getFitxerPortafibFromDoc(Document doc) throws I18NException {

//		log.info("getFitxerPortafibFromDocID: " + docID);
//
//		Document doc = this.findByPrimaryKey(docID);
		Long fitxerID = doc.getFitxerOriginalID();

		log.info("fitxerID: " + fitxerID);

		File file = FileSystemManager.getFile(fitxerID);
		Fitxer fitxer = fitxerEjb.findByPrimaryKey(fitxerID);

		if (!file.exists()) {
			throw new I18NException("error.fitxer.noexist", file.getAbsolutePath());
		}

		byte[] data;
		try {
			data = FileUtils.readFromFile(file);
		} catch (Throwable t) {
			throw new I18NException("error.fitxer.cantread", file.getAbsolutePath(), t.getMessage());
		}

		FirmaAsyncSimpleFile portafibFile = new FirmaAsyncSimpleFile(fitxer.getNom(), fitxer.getMime(), data);
		return portafibFile;
	}

	protected FirmaAsyncSimpleSignatureBlock[] convertNifToSignatureBlocks(String nifDestinatari) throws I18NException {
		FirmaAsyncSimpleSignatureBlock[] signatureBlocks = null;

		String[][] destinataris = new String[][] { { nifDestinatari } };

		if (destinataris == null || destinataris.length == 0) {
			throw new I18NException("error.nifdestinatari.undefined.property", "nifsDestinataris", "test.properties");
		}

		signatureBlocks = new FirmaAsyncSimpleSignatureBlock[destinataris.length];

		for (int i = 0; i < destinataris.length; i++) {
			String[] destinatarisBloc = destinataris[i];
			if (destinatarisBloc == null || destinatarisBloc.length == 0) {
				throw new I18NException("error.nifdestinatari.destinatarios", String.valueOf(i));
			}
			log.info("BLOC[" + i + "] => Destinataris = " + Arrays.toString(destinatarisBloc));
			List<FirmaAsyncSimpleSignature> signers = new ArrayList<FirmaAsyncSimpleSignature>();
			for (int j = 0; j < destinatarisBloc.length; j++) {

				String nif = destinatarisBloc[j].trim();

				if (nif.trim().length() == 0) {
					throw new I18NException("error.nifdestinatari.destinatario", String.valueOf(i), String.valueOf(j));
				}

				FirmaAsyncSimpleSigner personToSign;

				personToSign = new FirmaAsyncSimpleSigner();
				personToSign.setAdministrationID(nif);

				boolean required = true;
				String reason = null; // Usar la de la Petició

				// Revisors
				int minimumNumberOfRevisers;
				List<FirmaAsyncSimpleReviser> revisers;

				minimumNumberOfRevisers = 0;
				revisers = null;

				signers.add(new FirmaAsyncSimpleSignature(personToSign, required, reason, minimumNumberOfRevisers,
						revisers));

			}

			int minimumNumberOfSignaturesRequired = signers.size();
			signatureBlocks[i] = new FirmaAsyncSimpleSignatureBlock(minimumNumberOfSignaturesRequired, signers);

		}
		return signatureBlocks;
	}

	protected void afegirEventSolicitudEnviada(Long soliID, String remitent, String missatge) throws I18NException {
		log.info("Afegir event de peticio enviada a portafib");
		{
			Long _solicitudID_ = soliID;
			Long _incidenciaTecnicaID_ = null;

			Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());

			int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
			boolean _noLlegit_ = false;
			Long _fitxerID_ = null;
			String _missatge_ = missatge;
			String _asumpte_ = "Peticio de firma enviada a Portafib";
			String _persona_ = remitent;
			String _destinatari_ = null;
			String _destinatariEmail_ = null;
			String _caidIdentificadorConsulta_ = null;
			String _caidNumeroSeguiment_ = null;

			eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_, _destinatari_,
					_destinatariEmail_, _asumpte_, _missatge_, _fitxerID_, _noLlegit_, _caidIdentificadorConsulta_,
					_caidNumeroSeguiment_);
		}
	}
	// ------------ Rebre Document Firmat -------------------{
	@Override
	public void cosesAFerDocumentFirmat(Long portafibID) throws I18NException {
		Long docID = getDocIDFromPortafibID(portafibID);

		if (docID == null) {
			log.error("No hi ha cap document amb portafibID=" + portafibID + ". ", new Exception());
		} else {
			FirmaAsyncSimpleSignedFile firma = getFitxerSignat(portafibID);
			Long fitxerFirmatID = guardarFitxer(firma);

			Long soliID = documentSolicitudLogicaEjb.executeQueryOne(DocumentSolicitudFields.SOLICITUDID,
					DocumentSolicitudFields.DOCUMENTID.equal(docID));
			if (soliID != null) {
				afegirFitxerADocSolicitud(docID, soliID, fitxerFirmatID);
				crearEventSolcitudFirmada(soliID, fitxerFirmatID);
			} else {
				log.error("No hi ha cap sol·licitud amb documentID=" + docID + ". ", new Exception());
			}
		}
	}

	protected Long getDocIDFromPortafibID(Long portafibID) throws I18NException {
		return this.executeQueryOne(DocumentFields.DOCUMENTID, DocumentFields.NOTES.like("%" + portafibID + "%"));
	}

	protected FirmaAsyncSimpleSignedFile getFitxerSignat(long portafibID) throws I18NException {
		String languageUI = "ca";

		FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
		rinfo = new FirmaAsyncSimpleSignatureRequestInfo(portafibID, languageUI);

		ApiFirmaAsyncSimple api;
		FirmaAsyncSimpleSignedFile fitxerSignat = null;
		try {
			api = getApiFirmaAsyncSimple();
			fitxerSignat = api.getSignedFileOfSignatureRequest(rinfo);
		} catch (Throwable t) {
			throw new I18NException("error.portafib.fitxersignat", String.valueOf(portafibID), t.getMessage());
		}

		return fitxerSignat;
	}

	protected long guardarFitxer(FirmaAsyncSimpleSignedFile firma) throws I18NException {
		// Guarda fitxer signat a FileSystemManager i a la BD. Retorna el ID del fitxer
		String nom = firma.getSignedFile().getNom();
		String mime = firma.getSignedFile().getMime();
		byte[] data = firma.getSignedFile().getData();

		Fitxer fdb = fitxerEjb.create(nom, data.length, mime, null);

		Long fitxerID = fdb.getFitxerID();

		try {
			File fitxersignat = FileSystemManager.getFile(fitxerID);
			FileOutputStream fos = new FileOutputStream(fitxersignat);
			fos.write(data);
			fos.flush();
			fos.close();

		} catch (Throwable t) {
			throw new I18NException("error.fitxer.guardar.fsm", String.valueOf(fitxerID), t.getMessage());
		}

		return fitxerID;
	}

	protected void afegirFitxerADocSolicitud(Long docID, Long soliID, Long fitxerID) throws I18NException {
		Document doc = this.findByPrimaryKey(docID);
		doc.setFitxerFirmatID(fitxerID);

		if (doc.getTipus() == Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF) {
			// Si el document es un formulari de director, canviar l'estat de la sol·licitud
			// a PENDENT_AUTORITZAR

			SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);
			soli.setEstatID(Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR);
			solicitudLogicaEjb.update(soli);
		}

		this.update(doc);
	}

	protected void crearEventSolcitudFirmada(Long soliID, Long fitxerFirmatID) throws I18NException {

		log.info("Afegir event de peticio rebuda de portafib");
		{
			Long _solicitudID_ = soliID;
			Long _incidenciaTecnicaID_ = null;

			Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());

			int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
			boolean _noLlegit_ = true;
			Long _fitxerID_ = fitxerFirmatID;
			String _missatge_ = "Solicitud Firmada rebuda de Portafib";
			String _asumpte_ = "Guardat Fitxer Firmat";
			String _persona_ = "PortaFIB - PinbalAdmin";

			String _destinatari_ = null;
			String _destinatariEmail_ = null;
			String _caidIdentificadorConsulta_ = null;
			String _caidNumeroSeguiment_ = null;

			eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_, _destinatari_,
					_destinatariEmail_, _asumpte_, _missatge_, _fitxerID_, _noLlegit_, _caidIdentificadorConsulta_,
					_caidNumeroSeguiment_);
		}
	}
}