package org.fundaciobit.pinbaladmin.logic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAnnex;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleMetadata;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestBase;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.DocumentEJB;
import org.fundaciobit.pinbaladmin.ejb.OperadorService;
import org.fundaciobit.pinbaladmin.logic.utils.PortafibUtils;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.OperadorFields;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

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

	@Override
	public void enviarDocumentDGPortaFIB(Long docID, Contacte destinatari, String remitent) throws I18NException {

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

		Long idPortafib = crearIEnviarPeticioDeFirma(doc, destinatari, titolPeticio, description, reason,
				remitent);

		log.info("Peticio de firma creada: " + idPortafib);
		// S'ha d'afegir un camp a la taula documentSolicitud per saber l'estat a PortaFIB

		doc.setNotes(idPortafib.toString());
		this.update(doc);

		if (doc.getTipus() == Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF) {
			soli.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_Firma_Director);
			soli.setPortafibID(idPortafib);
			solicitudLogicaEjb.update(soli);
		}

		String msg = "Peticio de firma enviada a Portafib.\n" + "Remitent: " + remitent + "\n" + "Destinatari: "
				+ destinatari.getNif() + " - " + destinatari.getNom() + "\n" + "Fitxer: " + doc.getNom();

		afegirEventSolicitudEnviada(soliID, remitent, msg);
	}

	public Long crearIEnviarPeticioDeFirma(Document doc, Contacte destinatari, String titolPeticio,
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

		FirmaAsyncSimpleSignatureBlock[] signatureBlocks = PortafibUtils.convertContacteToSignatureBlock(destinatari);
//		FirmaAsyncSimpleSignatureBlock[] signatureBlocks = PortafibUtils.convertNifToSignatureBlocks(destinatariNif);

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

		ApiFirmaAsyncSimple api = PortafibUtils.getApiFirmaAsyncSimple();
		
		try {
			peticioDeFirmaID = api.createAndStartSignatureRequestWithSignBlockList(signatureRequest);
			return peticioDeFirmaID;
		} catch (AbstractApisIBException e) {
//			String msg = I18NLogicUtils.tradueix(new Locale(languageUI), "error.portafib.generic", e.getMessage());
			log.error("Error creant peticio de firma a Portafib: " + e.getMessage(), e);
			throw new I18NException("error.portafib.generic", new I18NArgumentString(e.getMessage()));
		}
	}

	protected FirmaAsyncSimpleFile getFitxerPortafibFromDoc(Document doc) throws I18NException {
		Long fitxerID = doc.getFitxerOriginalID();

		log.info("fitxerID: " + fitxerID);
		return PortafibUtils.getPortaFIBFileFromFitxerID(fitxerID, fitxerEjb);
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
	public Long cosesAFerDocumentFirmat(Long portafibID) throws I18NException {
		Long docID = getDocIDFromPortafibID(portafibID);

		if (docID == null) {
			log.error("No hi ha cap document amb portafibID=" + portafibID);
		} else {
			FirmaAsyncSimpleSignedFile firma = PortafibUtils.getFitxerSignat(portafibID);
			Long fitxerFirmatID = PortafibUtils.guardarFitxer(firma, fitxerEjb);
			
			//El document de l'event ha de ser una copia del document original.
			Long fitxerFirmatIDCopia = PortafibUtils.guardarFitxer(firma, fitxerEjb);
			
			Long soliID = documentSolicitudLogicaEjb.executeQueryOne(DocumentSolicitudFields.SOLICITUDID,
					DocumentSolicitudFields.DOCUMENTID.equal(docID));
			if (soliID != null) {
				afegirFitxerADocSolicitud(docID, soliID, fitxerFirmatID);
				crearEventSolcitudFirmada(soliID, fitxerFirmatIDCopia );
			} else {
				log.error("No hi ha cap sol·licitud amb documentID=" + docID);
			}
		}
		return docID;
	}

	protected Long getDocIDFromPortafibID(Long portafibID) throws I18NException {
		return this.executeQueryOne(DocumentFields.DOCUMENTID, DocumentFields.NOTES.like("%" + portafibID + "%"));
	}
	
	protected void afegirFitxerADocSolicitud(Long docID, Long soliID, Long fitxerID) throws I18NException {
		Document doc = this.findByPrimaryKey(docID);
		doc.setFitxerFirmatID(fitxerID);

		if (doc.getTipus() == Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF) {
			// Si el document es un formulari de director, canviar l'estat de la sol·licitud
			// a PENDENT_Enviar a Madrid

			SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);
			soli.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_ENVIAR_MADRID);
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