package org.fundaciobit.pinbaladmin.logic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAnnex;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleExternalSigner;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleMetadata;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleReviser;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignature;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestBase;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSigner;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleBlock;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleExternalSigner;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleReviser;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleSignature;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleSigner;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.OperadorService;
import org.fundaciobit.pinbaladmin.ejb.PinfoEJB;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaEJB.PinfoDataFull;
import org.fundaciobit.pinbaladmin.logic.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.logic.utils.PortafibUtils;
import org.fundaciobit.pinbaladmin.logic.utils.Responsable;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.pinbaladmin.model.fields.PinfoFields;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PinfoLogicaEJB")
public class PinfoLogicaEJB extends PinfoEJB implements PinfoLogicaService {

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerPublicLogicaEjb;

	@EJB(mappedName = PinfoDataLogicaService.JNDI_NAME)
	protected PinfoDataLogicaService pinfoDataLogicaEjb;

	@EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
	protected IncidenciaTecnicaLogicaService incidenciaLogicaEjb;

	@EJB(mappedName = OperadorService.JNDI_NAME)
	protected OperadorService operadorEjb;

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerPublicEjb;
	
	@EJB(mappedName = EventLogicaService.JNDI_NAME)
	protected EventLogicaService eventLogicaEjb;
	
	@EJB(mappedName = OrganLogicaService.JNDI_NAME)
	protected OrganLogicaService organLogicaEjb;
	
	
	@Override
	@PermitAll
	public Pinfo create(Pinfo instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public PinfoJPA findByPrimaryKey(Long _ID_) {
		return (PinfoJPA) super.findByPrimaryKey(_ID_);
	}

	@Override
	@PermitAll
	public Pinfo update(Pinfo instance) throws I18NException {
		return super.update(instance);
	}

	@Override
	public Long generarPinfoPDF(Long pinfoID, Responsable responsable) throws Exception, I18NException {

		log.info("Generant PDF per PINFO: " + pinfoID);
		Map<String, Object> data = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataStr = sdf.format(new Date());
		data.put("fecha", dataStr);

		PinfoJPA pinfo = findByPrimaryKey(pinfoID);
		data.put("pinfo", pinfo);

		data.put("responsable", responsable);
		
		PinfoDataFull pinfoDataFull = pinfoDataLogicaEjb.getEstructuraUsuarisProcedimentServeis(pinfoID);
		data.put("pinfoDataFull", pinfoDataFull);

		IncidenciaTecnica incidencia = incidenciaLogicaEjb.findByPrimaryKey(pinfo.getIncidenciaID());
		data.put("incidencia", incidencia);
		
		String organGestor = "";
		
		Organ organ = organLogicaEjb.findByPrimaryKey(incidencia.getOrganid());
		if (organ != null) {
			organGestor = organ.getNom() + " (" + organ.getDir3() + ")";
		}
		data.put("organGestor", organGestor);
		
		String fileName = "PINFO_" + pinfoID + ".pdf";
		File outputPDF = File.createTempFile("pinbaladmin_formulari_pinfo", ".pdf");
		FileOutputStream fosPDF = new FileOutputStream(outputPDF);

		File plantilla = new File(Configuracio.getTemplatePinfo());
		byte[] template = FileUtils.readFromFile(plantilla);

		log.info(template.length + " bytes llegits de la plantilla: " + plantilla.getAbsolutePath());

		try {
			ParserFormulariXML.createPdf(new ByteArrayInputStream(template), fosPDF, data);

			long size = outputPDF.length();
			String mime = "application/pdf";
			String desc = "";

			FitxerJPA fitxer = new FitxerJPA(fileName, size, mime, desc);
			fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

			FileSystemManager.crearFitxer(outputPDF, fitxer.getFitxerID());

			pinfo.setFitxerID(fitxer.getFitxerID());
			update(pinfo);

			return fitxer.getFitxerID();

//			return null;

		} finally {
			try {
				fosPDF.flush();
				fosPDF.close();
			} catch (Exception e) {
				System.err.println("Error creant Documents de Solicitud" + e.getMessage());
			}
		}
	}

	// Enviar Pinfo amb PortaFIB.

	@Override
	public PinfoJPA arrancarPeticioFlux(long pinfoID, String languageUI, FlowTemplateSimpleFlowTemplate flux) throws I18NException {

		log.info("Paso 6.2. Arrancar Peticio PortaFIB EJB");
		log.info("\n" + FlowTemplateSimpleFlowTemplate.toString(flux));

		Pinfo pinfo = this.findByPrimaryKey(pinfoID);
		
		log.info("Paso 7.1: Cream signatureBlocks amb flux: " + flux);
		FirmaAsyncSimpleSignatureBlock[] signatureBlocks = convertFluxToSignatureBlocks(flux);
		log.info("Paso 7.2: SignatureBlock: " + signatureBlocks.length + " blocs" + signatureBlocks);
		
		// Arrancar la petició
		log.info("Paso 8: Arrancar la petició");
		arrancarPeticioBySignatureBlocks(pinfo, languageUI, signatureBlocks);

		log.info("Paso 11: Peticio arrancada correctament");
		return (PinfoJPA) pinfo;
	}

	public FirmaAsyncSimpleSignatureBlock[] convertFluxToSignatureBlocks(FlowTemplateSimpleFlowTemplate flux)
			throws I18NException {

		List<FlowTemplateSimpleBlock> blocks = flux.getBlocks();

		FirmaAsyncSimpleSignatureBlock[] signatureBlocks;
		signatureBlocks = new FirmaAsyncSimpleSignatureBlock[blocks.size()];

		int count = 0;
		for (FlowTemplateSimpleBlock blockOrigen : blocks) {

			List<FirmaAsyncSimpleSignature> signers = new ArrayList<FirmaAsyncSimpleSignature>();
			for (FlowTemplateSimpleSignature signOrigen : blockOrigen.getSignatures()) {

				FirmaAsyncSimpleSigner personToSign = new FirmaAsyncSimpleSigner();

				FlowTemplateSimpleSigner signerOrig = signOrigen.getSigner();

				FlowTemplateSimpleExternalSigner externalOrig = signerOrig.getExternalSigner();
				if (externalOrig != null) {
					FirmaAsyncSimpleExternalSigner destExtSigner = new FirmaAsyncSimpleExternalSigner();
					destExtSigner.setAdministrationId(externalOrig.getAdministrationId());
					destExtSigner.setEmail(externalOrig.getEmail());
					destExtSigner.setLanguage(externalOrig.getLanguage());

					destExtSigner.setName(externalOrig.getName());
					destExtSigner.setSecurityLevel(externalOrig.getSecurityLevel());
					destExtSigner.setSurnames(externalOrig.getSurnames());

					personToSign.setExternalSigner(destExtSigner);
				} else {
					personToSign.setAdministrationID(signerOrig.getAdministrationID());
					personToSign.setIntermediateServerUsername(signerOrig.getIntermediateServerUsername());
					personToSign.setPositionInTheCompany(signerOrig.getPositionInTheCompany());
					personToSign.setUsername(signerOrig.getUsername());
				}

				final boolean required = signOrigen.isRequired();
				String reason = signOrigen.getReason(); // Usar la de la Petició

				// Revisors
				int minimumNumberOfRevisers = signOrigen.getMinimumNumberOfRevisers();

				List<FirmaAsyncSimpleReviser> revisersDest;

				List<FlowTemplateSimpleReviser> revisorsOrigen = signOrigen.getRevisers();

				if (revisorsOrigen == null || revisorsOrigen.size() == 0) {
					revisersDest = null;
				} else {
					revisersDest = new ArrayList<FirmaAsyncSimpleReviser>();
					for (FlowTemplateSimpleReviser revOrig : revisorsOrigen) {
						FirmaAsyncSimpleReviser revDest = new FirmaAsyncSimpleReviser();
						revDest.setAdministrationID(revOrig.getAdministrationID());
						revDest.setIntermediateServerUsername(revOrig.getIntermediateServerUsername());
						revDest.setPositionInTheCompany(revOrig.getPositionInTheCompany());
						revDest.setRequired(revOrig.isRequired());
						revDest.setUsername(revOrig.getUsername());
						revisersDest.add(revDest);
					}
				}

				signers.add(new FirmaAsyncSimpleSignature(personToSign, required, reason, minimumNumberOfRevisers,
						revisersDest));

			}

			int minimumNumberOfSignaturesRequired = blockOrigen.getSignatureMinimum();
			signatureBlocks[count] = new FirmaAsyncSimpleSignatureBlock(minimumNumberOfSignaturesRequired, signers);
			count++;

		}
		return signatureBlocks;
	}

	public void arrancarPeticioBySignatureBlocks(Pinfo pinfo, String languageUI,
			FirmaAsyncSimpleSignatureBlock[] signatureBlocks) throws I18NException {

		Long idPortafib;
		try {
			IncidenciaTecnica in = incidenciaLogicaEjb.findByPrimaryKey(pinfo.getIncidenciaID());

			FirmaAsyncSimpleFile fitxerAFirmar = getFitxer(pinfo.getFitxer());
			String titolPeticio = in.getTitol();
			String description = "Firma d'un PINFO";
			
			String remitentNom = in.getContacteNom();
			String solicitantNIF = pinfo.getSolicitantNIF();
			
			String reason = "Autorització de la solicitud de permisos";

			//Hauria d'obtenir el nif del destinatari del fluxe, pero no funciona. Revisar el métode convertFluxToSignatureBlocks
			FirmaAsyncSimpleSigner signer = signatureBlocks[0].getSigners().get(0).getSigner();
			
			String destinatariNIF = signer.getAdministrationID();
			String portafibUsername = signer.getIntermediateServerUsername();

			if (destinatariNIF == null && portafibUsername != null) {
				destinatariNIF = portafibUsername;
			}
			
			FirmaAsyncSimpleExternalSigner externalSigner = signer.getExternalSigner();
			log.info("Paso 9.7: Destinatari ExternalSigner: " + externalSigner);
			
			
			
			log.info("Paso 9.1: Crear la petició de firma amb fitxer: " + fitxerAFirmar.getNom());
			idPortafib = createSignatureRequestAndStart(fitxerAFirmar, signatureBlocks, titolPeticio, description,
					reason, solicitantNIF, remitentNom);

			log.info("Paso 10: PortaFIB ID: " + idPortafib);
			
			pinfo.setPortafibid(String.valueOf(idPortafib));
			pinfo.setDestinatariNIF(destinatariNIF);
			pinfo.setEstat(Constants.ESTAT_PINFO_PENDENT_FIRMA);

		} catch (Throwable e) {
			log.error("Error al crear la petició de firma", e);
			pinfo.setEstat(Constants.ESTAT_PINFO_ERROR);
			throw new I18NException("error.portafib.creacio", e.getMessage());
		}
		this.update(pinfo);
	}

	protected FirmaAsyncSimpleFile getFitxer(Fitxer fitxer) throws I18NException {

		File f = FileSystemManager.getFile(fitxer.getFitxerID());

		if (!f.exists()) {
			throw new I18NException("error.fitxer.noexist", f.getAbsolutePath());
		}

		byte[] data;
		try {
			data = FileUtils.readFromFile(f);
		} catch (Throwable t) {
			throw new I18NException("error.fitxer.cantread", f.getAbsolutePath(), t.getMessage());
		}

		FirmaAsyncSimpleFile file = new FirmaAsyncSimpleFile(fitxer.getNom(), fitxer.getMime(), data);
		return file;
	}

	protected Long createSignatureRequestAndStart(FirmaAsyncSimpleFile fitxerAFirmar,
			FirmaAsyncSimpleSignatureBlock[] signatureBlocks, String titolPeticio, String description, String reason,
			String remitentNIF, String remitentFullName) throws Exception {

		String languageUI = "ca";
		String languageDoc = "ca";

		// Fitxer a Firmar
		if (fitxerAFirmar == null) {
			throw new I18NException("genapp.comodi", "No s'ha definit fitxer a firmar");
		}

		Long tipusDocumentalID = 14L; // Elegir un tipus documental: Autorització. 14 - Sol·licitud

		String senderUsername = remitentNIF;
		String senderFullName = remitentFullName;

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
			throw new I18NException("error.portafib.generic", new I18NArgumentString(e.getMessage()));
		}
	}

	
	@Override
	public Long cosesAFerPinfoFirmat(Long portafibID) throws I18NException {
		Long pinfoID = getPinfoIDFromPortafibID(portafibID);
		
		if (pinfoID == null) {
			log.error("No s'ha trobat Pinfo amb portafibid = " + portafibID);
		}else {
			FirmaAsyncSimpleSignedFile firma = PortafibUtils.getFitxerSignat(portafibID);
			Long fitxerFirmatID = PortafibUtils.guardarFitxer(firma, fitxerPublicEjb);

			PinfoJPA pinfo = findByPrimaryKey(pinfoID);
			pinfo.setFitxerfirmatID(fitxerFirmatID);
			pinfo.setEstat(Constants.ESTAT_PINFO_PENDENT_TRAMITAR);
			update(pinfo);
			
			
			//El document de l'event ha de ser una copia del document original.
			Long fitxerFirmatIDCopia = PortafibUtils.guardarFitxer(firma, fitxerPublicEjb);
			crearEventSolcitudFirmada(pinfo, fitxerFirmatIDCopia);			

		}
		
		return pinfoID;
	}
	
	protected Long getPinfoIDFromPortafibID(Long portafibID) throws I18NException {
		return this.executeQueryOne(PinfoFields.PINFOID, PinfoFields.PORTAFIBID.equal(String.valueOf(portafibID)));
	}
	
	protected void crearEventSolcitudFirmada(Pinfo pinfo, Long fitxerFirmatID) throws I18NException {

		log.info("Afegir event de peticio rebuda de portafib");
		
		IncidenciaTecnica incidencia = incidenciaLogicaEjb.findByPrimaryKey(pinfo.getIncidenciaID());
		
		{
			Long _solicitudID_ = null;
			Long _incidenciaTecnicaID_ = incidencia.getIncidenciaTecnicaID();

			Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());

			int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC;
			boolean _noLlegit_ = true;
			Long _fitxerID_ = fitxerFirmatID;
			String _missatge_ = "S'ha rebut el pinfo firmat de Portafib";
			String _asumpte_ = "Guardat Fitxer Firmat";
			String _persona_ = "Usuari PortaFIB (" + pinfo.getDestinatariNIF() + ")";

			String _destinatari_ = incidencia.getContacteNom();
			String _destinatariEmail_ = incidencia.getContacteEmail();
			String _caidIdentificadorConsulta_ = null;
			String _caidNumeroSeguiment_ = null;

			eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_, _destinatari_,
					_destinatariEmail_, _asumpte_, _missatge_, _fitxerID_, _noLlegit_, _caidIdentificadorConsulta_,
					_caidNumeroSeguiment_);
		}
	}

}