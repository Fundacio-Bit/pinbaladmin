package org.fundaciobit.pinbaladmin.logic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments.TipusProcediment;
import org.fundaciobit.pinbaladmin.ejb.ServeiService;
import org.fundaciobit.pinbaladmin.ejb.TramitAPersAutEJB;
import org.fundaciobit.pinbaladmin.logic.utils.CrearExcelDeServeis;
import org.fundaciobit.pinbaladmin.logic.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitECteAud;
import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.pinbaladmin.model.entity.TramitGDadesTit;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;
import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitBDadesSoliFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitDCteAutFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitECteAudFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitFCteTecFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitGDadesTitFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitHProcFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.EntitatJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.OrganJPA;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

/**
 * 
 * @author anadal
 */
@Stateless(name = "TramitAPersAutLogicaEJB")
public class TramitAPersAutLogicaEJB extends TramitAPersAutEJB implements TramitAPersAutLogicaService {

    @EJB(mappedName = TramitBDadesSoliLogicaService.JNDI_NAME)
    protected TramitBDadesSoliLogicaService tramitBEjb;
    @EJB(mappedName = TramitCDadesCesiLogicaService.JNDI_NAME)
    protected TramitCDadesCesiLogicaService tramitCEjb;
    @EJB(mappedName = TramitDCteAutLogicaService.JNDI_NAME)
    protected TramitDCteAutLogicaService tramitDEjb;
    @EJB(mappedName = TramitECteAudLogicaService.JNDI_NAME)
    protected TramitECteAudLogicaService tramitEEjb;
    @EJB(mappedName = TramitFCteTecLogicaService.JNDI_NAME)
    protected TramitFCteTecLogicaService tramitFEjb;
    @EJB(mappedName = TramitGDadesTitLogicaService.JNDI_NAME)
    protected TramitGDadesTitLogicaService tramitGEjb;
    @EJB(mappedName = TramitHProcLogicaService.JNDI_NAME)
    protected TramitHProcLogicaService tramitHEjb;
    @EJB(mappedName = TramitIServLogicaService.JNDI_NAME)
    protected TramitIServLogicaService tramitIEjb;
    @EJB(mappedName = TramitJConsentLogicaService.JNDI_NAME)
    protected TramitJConsentLogicaService tramitJEjb;

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;
    @EJB(mappedName = EventLogicaService.JNDI_NAME)
    protected EventLogicaService eventLogicaEjb;
    @EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
    protected SolicitudServeiLogicaService solicitudServeiLogicaEjb;
    @EJB(mappedName = ServeiService.JNDI_NAME)
    protected ServeiService serveiEjb;
    
    @EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
    protected DocumentSolicitudLogicaService documentSolicitudLogicEjb;
    @EJB(mappedName = DocumentLogicaService.JNDI_NAME)
    protected DocumentLogicaService documentLogicaEjb;
    @EJB(mappedName = OrganLogicaService.JNDI_NAME)
    protected OrganLogicaService organLogicaEjb;
    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;
    @EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
    protected FitxerPublicLogicaService fitxerPublicLogicaEjb;

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd_HH:mm");

    @Override
    @PermitAll
    public TramitAPersAutJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public TramitAPersAut create(TramitAPersAut instance) throws I18NException {
        log.info("TramitID: " + instance.getTramitid());
        TramitAPersAut tramitA = super.create(instance);
        tramitA.setTramitid(tramitA.getPersautid());
        this.update(tramitA);
        log.info("TramitID: " + tramitA.getTramitid());
        return tramitA;
    }
    
    @Override
    @PermitAll
    public TramitAPersAut update(TramitAPersAut instance) throws I18NException {
        return super.update(instance);
    }

    @Override
    public void deleteFull(Long tramitID) throws I18NException {
        tramitBEjb.delete(TramitBDadesSoliFields.TRAMITID.equal(tramitID));
        tramitCEjb.delete(TramitCDadesCesiFields.TRAMITID.equal(tramitID));
        tramitDEjb.delete(TramitDCteAutFields.TRAMITID.equal(tramitID));
        tramitEEjb.delete(TramitECteAudFields.TRAMITID.equal(tramitID));
        tramitFEjb.delete(TramitFCteTecFields.TRAMITID.equal(tramitID));
        tramitGEjb.delete(TramitGDadesTitFields.TRAMITID.equal(tramitID));
        tramitHEjb.delete(TramitHProcFields.TRAMITID.equal(tramitID));
        tramitIEjb.delete(TramitIServFields.TRAMITID.equal(tramitID));
        tramitJEjb.delete(TramitJConsentFields.TRAMITID.equal(tramitID));

        super.delete(TramitAPersAutFields.TRAMITID.equal(tramitID));
    }

	private Long generarXMLFromMap(Map<String, Object> map) throws Exception {
		String plantillaFitxerXml = Configuracio.getTemplateTramitSistraXml();
		log.info("fileXml: " + plantillaFitxerXml);

		String plantilla = FileUtils.readFileToString(new File(plantillaFitxerXml), Charset.defaultCharset());

		String result = TemplateEngine.processExpressionLanguage(plantilla, map);

		String fileName = "D:/Projectes/pinbaladmin-files/formulario_nuevo.xml";

		FileUtils.writeStringToFile(new File(fileName), result, StandardCharsets.UTF_8, false);

		byte[] data = FileUtils.readFileToByteArray(new File(fileName));

		Fitxer f = fitxerPublicLogicaEjb.create("formulari.xml", data.length, "application/xml", null);
		FileSystemManager.crearFitxer(new ByteArrayInputStream(data), f.getFitxerID());

		return f.getFitxerID();
	}
    
    @Override
    public SolicitudJPA crearSolicitudAmbTramit(Long tramitID) throws I18NException {
        /*
         * Per crear la Solicitud correctament, s'han de fer diverses pases.
         * 1. Crear l'objecte a BBDD, el formulari XML, i obtenir un SolicitudID
         * 2. Crear un event de Creació de Solicitud (Exemple a SolicitudLocalDesDeFitxerXmlOperador)
         * 3. Crear els serveis de la Solicitud (Exemple a generarServeis de SolicitudFullViewOperadorController
         * 4. Crear els Documents de la Solicitud (Exmple a generarFormulari de SolicitudFullViewOperadorController)
         */

        SolicitudJPA soli = new SolicitudJPA();

        //Constants
        Long estatID = Constants.SOLICITUD_ESTAT_PENDENT_DISTRIBUCIO;
        String notesSoli = "Procediment creat amb Formulari. TramitID[" + tramitID + "]";
        Timestamp dataInici = new Timestamp(System.currentTimeMillis());
        Integer estatpinbal = Constants.ESTAT_PINBAL_NO_SOLICITAT;
        String creador = "pinbaladmin";
        String operador = "pinbaladmin";
        boolean firmatDocSolicitud = false;

        //nulls
        String entitatEstatal = null;
        String pinfo = null;

        //Camps a obtenir
        String procedimentCodi = null;
        String codiDescriptiu = null;
        String procedimentNom = null;
        String procedimentTipus = null;
        Long organid = null;
        Timestamp dataFi = null;
        String responsableProcNom = null;
        String responsableProcEmail = null;
        String personaContacte = null;
        String personaContacteEmail = null;
        String denominacio = null;
        String dir3arrel = null;
        String nifArrel = null;
        boolean produccio = true;
        List<TramitIServ> listaTramitsI = null;
        
        TramitJConsent tramitJ = null;
        String consentiment = null;
        String urlconsentiment = null;
        String consentimentadjunt = null;
        Long fitxerConsentimentID = null;
        String nomFitxerAdjunt = null;

        Map<String, Object> map = new HashMap<String, Object>();

        List<List<?>> listas = Arrays.asList(this.select(TRAMITID.equal(tramitID)),
                tramitBEjb.select(TramitBDadesSoliFields.TRAMITID.equal(tramitID)),
                tramitCEjb.select(TramitCDadesCesiFields.TRAMITID.equal(tramitID)),
                tramitDEjb.select(TramitDCteAutFields.TRAMITID.equal(tramitID)),
                tramitEEjb.select(TramitECteAudFields.TRAMITID.equal(tramitID)),
                tramitFEjb.select(TramitFCteTecFields.TRAMITID.equal(tramitID)),
                tramitGEjb.select(TramitGDadesTitFields.TRAMITID.equal(tramitID)),
                tramitHEjb.select(TramitHProcFields.TRAMITID.equal(tramitID)),
                tramitIEjb.select(TramitIServFields.TRAMITID.equal(tramitID)),
                tramitJEjb.select(TramitJConsentFields.TRAMITID.equal(tramitID)));

        List<String> keys = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

        for (int i = 0; i < listas.size(); i++) {
            List<?> lista = listas.get(i);
            if (!lista.isEmpty()) {
                String letra = keys.get(i);
                Object obj = lista.get(0);
                map.put(letra, obj);

                switch (letra) {
                    case "A":
                        TramitAPersAut A = (TramitAPersAut) obj;
                        if (A.getLlinatge2() == null) {
                            A.setLlinatge2("");
                        }
                        String fullNameA = toFullName(A.getNom(), A.getLlinatge1(), A.getLlinatge2());
                        map.put("fullNameA", fullNameA);
                        personaContacte = fullNameA;
                        personaContacteEmail = A.getMail();
                    break;
                    case "B":
                        TramitBDadesSoli B = (TramitBDadesSoli) obj;
                        produccio = B.getEntorn().equals("pro");
                        String tipussolicitudNom = tramitBEjb.getTipussolicitudValue(B.getTipussolicitud());
                        map.put("tipussolicitudNom", tipussolicitudNom);
                        map.put("pre", "Preproducció");
                        map.put("pro", "Producció");
                    break;
                    case "C":
                        TramitCDadesCesi C = (TramitCDadesCesi) obj;

                        OrganJPA organGestor = organLogicaEjb.findByPrimaryKey(C.getOrganID());
                        organid = organGestor.getOrganid();
                        map.put("dir3Organ", organGestor.getDir3());
                        map.put("nomOrgan", organGestor.getNom());

                        EntitatJPA entitatArrel = entitatLogicaEjb.findByPrimaryKey(organGestor.getEntitatid());
                        dir3arrel = entitatArrel.getDir3();
                        nifArrel = entitatArrel.getCIF();
                        denominacio = entitatArrel.getNom();
                        
                        map.put("nomArrel", denominacio);
                        map.put("dir3arrel", dir3arrel);
                        map.put("nifArrel", nifArrel);
                        
                        
                        String municipiNom = tramitCEjb.getMunicipiValue(C.getMunicipi());
                        map.put("municipiNom", municipiNom);

                    break;
                    case "D":
                        TramitDCteAut D = (TramitDCteAut) obj;
                        String fullNameD = toFullName(D.getNom(), D.getLlinatge1(), D.getLlinatge2());
                        map.put("fullNameD", fullNameD);
                    break;
                    case "E":
                        TramitECteAud E = (TramitECteAud) obj;
                        String fullNameE = toFullName(E.getNom(), E.getLlinatge1(), E.getLlinatge2());
                        map.put("fullNameE", fullNameE);
                        responsableProcNom = fullNameE;
                        responsableProcEmail = E.getMail();
                    break;
                    case "F":
                        TramitFCteTec F = (TramitFCteTec) obj;
                        String fullNameF = toFullName(F.getNom(), F.getLlinatge1(), F.getLlinatge2());
                        map.put("fullNameF", fullNameF);
                    break;
                    case "G":
                        TramitGDadesTit G = (TramitGDadesTit) obj;
                        String fullNameG = toFullName(G.getNom(), G.getLlinatge1(), G.getLlinatge2());
                        map.put("fullNameG", fullNameG);
                    break;
                    case "H":
                        TramitHProc H = (TramitHProc) obj;
                        procedimentCodi = H.getCodi();
                        codiDescriptiu = H.getDescripcio();
                        procedimentNom = H.getNom();
                        Long tipus = Long.parseLong(H.getTipus());
                        procedimentTipus = getTipusProcediment(tipus);
                        map.put("tipusProcedimentNom", procedimentTipus);
                        
                        if(H.getUrlseu() == null || H.getUrlseu().trim().length() == 0) {
                        	H.setUrlseu("---");
                        }

                        String dataFiStr;
                        if (H.isCaducitat()) {
                        	dataFi = H.getCaducitatdata();
                            dataFiStr = SDF.format(dataFi);
                        } else {
                            dataFiStr = "";
                        }
                        map.put("dataCaducitat", dataFiStr);
                    break;
                    case "I":
                        listaTramitsI = (List<TramitIServ>) lista;

                        map.put("noop", "No Oposició");
                        map.put("llei", "Llei");
                        map.put("servicios", listaTramitsI);

                        String[] codis = new String[lista.size()];
                        String[] noms = new String[lista.size()];
                        for (int j = 0; j < lista.size(); j++) {
                            TramitIServ I = (TramitIServ) lista.get(j);
                            codis[j] = I.getCodi();
                            noms[j] = tramitIEjb.getServeiValue(I.getNom());
                            if (I.getNorma2() == null) I.setNorma2("");
                            if (I.getArticles2() == null) I.setArticles2("");
                            if (I.getNorma3() == null) I.setNorma3("");
                            if (I.getArticles3() == null) I.setArticles3("");
                            
                        }
                        String codisServeisString = String.join(",", codis);
                        map.put("codisServeisString", codisServeisString);
                        map.put("nomServeis", noms);
                    break;
                    case "J":
                        TramitJConsent J = (TramitJConsent) obj;
                        tramitJ = J;
                        
                        consentiment = J.getConsentiment();

						if ((J.getAdjuntID() == null) && (J.getUrlconsentiment() == null)) {
							fitxerConsentimentID = null;

							map.put("urlConsentiment", "---");
							map.put("adjConsentiment", "---");
						}
                        
						if (J.getAdjuntID() != null) {
							consentimentadjunt = Constants.CONSENTIMENT_ADJUNT;
							nomFitxerAdjunt = J.getAdjunt().getNom();
							fitxerConsentimentID = J.getAdjuntID();
							urlconsentiment = null;
							
							map.put("urlConsentiment", "---");
							map.put("adjConsentiment", nomFitxerAdjunt);
						}
						
						if (J.getUrlconsentiment() != null) {
							urlconsentiment = J.getConsentiment();
							consentimentadjunt = Constants.CONSENTIMENT_PUBLICAT;
							fitxerConsentimentID = null;
							
							map.put("urlConsentiment", urlconsentiment);
							map.put("adjConsentiment", "---");
						}
						
						log.info("Fitxer Consentiment: " + nomFitxerAdjunt);
                    break;
                }
            }
        }

        String extracte = "Procediment " + procedimentCodi + " - " + procedimentNom;
        map.put("extracte", extracte);
        
        //Documents
        Properties prop = null;
		try {
			Long solicitudXmlID = generarXMLFromMap(map);
			prop = ParserFormulariXML.getPropertiesFromFormulario(solicitudXmlID);
			Long docSoliID = generarDocumentSolicitudAmbXML(procedimentCodi, prop, listaTramitsI);
			
			soli.setSolicitudXmlID(solicitudXmlID);
	        soli.setDocumentSolicitudID(docSoliID);
		} catch (Exception e) {
			String msg = "Error generant XML de la sol·licitud: " + e.getMessage();
			log.error(msg, e);
			throw new I18NException(msg);
		}
		
        soli.setProcedimentCodi(procedimentCodi); 
        soli.setCodiDescriptiu(codiDescriptiu);
        soli.setProcedimentNom(procedimentNom);
        soli.setProcedimentTipus(procedimentTipus);
        soli.setEstatID(estatID);
        soli.setOrganid(organid);
        soli.setEntitatEstatal(entitatEstatal);
        soli.setPinfo(pinfo);
        soli.setDataInici(dataInici);
        soli.setDataFi(dataFi);
        soli.setPersonaContacte(personaContacte);
        soli.setPersonaContacteEmail(personaContacteEmail);
        soli.setResponsableProcNom(responsableProcNom);
        soli.setResponsableProcEmail(responsableProcEmail);
        soli.setNotes(notesSoli);
        soli.setFirmatDocSolicitud(firmatDocSolicitud);
        soli.setProduccio(produccio);
        soli.setDenominacio(denominacio);
        soli.setDir3(dir3arrel);
        soli.setNif(nifArrel);
        soli.setCreador(creador);
        soli.setOperador(operador);
        soli.setEstatpinbal(estatpinbal);
        soli.setConsentiment(consentiment);
        soli.setUrlconsentiment(urlconsentiment);
        soli.setConsentimentadjunt(consentimentadjunt);
        

        SolicitudJPA solicitud = (SolicitudJPA) solicitudLogicaEjb.create(soli);

        Long soliID = solicitud.getSolicitudID();
        log.info("SolicitudID de la solicitud creada: " + soliID);

        try {
			log.info("Enviem mail al sol·licitant. De moment no enviam.");
//			String destinatariMail = solicitud.getPersonaContacteEmail();
			String destinatariMail = null;
			enviarMailSolicitant(solicitud, destinatariMail);

			log.info("Generem documents de la sol·licitud");
			generarDocumentsSolicitud(soliID, organid, prop);
			
			log.info("Afegim document de consentiment");
			Fitxer docConsentiment = afegirDocumentConsentiment(fitxerConsentimentID, consentiment, soliID);
			
			log.info("Afegim serveis a la sol·licitud");
			Set<SolicitudServeiJPA> solicitudServeis = afegirServeisSolicitud(listaTramitsI, dataFi, soliID, tramitJ);
			soli.setSolicitudServeis(solicitudServeis);
			log.info("Generem Excel de Serveis");
			generarExcelDeServeis(solicitud, docConsentiment);
			
			log.info("S'ha creat la sol·licitud: " + soliID);
        } catch (Exception e) {
			String msg = "Error generant documents de la sol·licitud: " + e.getMessage();
			log.error(msg, e);
			throw new I18NException(msg);
		}
        
        /*
         * Afegir documents:
         * 1. Document de solicitud - Ok
         * 2. PDF Director General - Ok
         * 3. ODT Director General - Ok
         * 4. Excel de Procs-Servs - Ok
         * 5. Consentiment - Ok
         */
	        

        
        return solicitud;
    }

    private void afegirDocumentSolicitudAmbFitxer(FitxerJPA fitxer, String nom, Long tipus, Long soliID) throws I18NException  {
    
        Document doc = documentLogicaEjb.create(nom, fitxer.getFitxerID(), null, null, tipus);

        DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), soliID);

        documentSolicitudLogicEjb.create(ds);
        log.info("Afegit document: " + nom + " a la solicitud: " + soliID );

    }
    
    private Fitxer afegirDocumentConsentiment(Long fitxerConsentimentID, String consentiment,  Long soliID) throws I18NException {

        if (fitxerConsentimentID != null) {

        	log.info("Tenim document de consentiment: " + fitxerConsentimentID + " - " + consentiment );
        	
        	FitxerJPA cons = fitxerPublicLogicaEjb.findByPrimaryKey(fitxerConsentimentID);
        	File consFile = FileSystemManager.getFile(cons.getFitxerID());
        	
        	// Copiar fitxer de consentiment
        	FitxerJPA fitxerCopia = new FitxerJPA(cons.getNom(), cons.getTamany(), cons.getMime(), cons.getDescripcio());
        	fitxerCopia = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxerCopia);
        	
        	File consFilePdf = FileSystemManager.getFile(fitxerCopia.getFitxerID());
        	FileSystemManager.copy(consFile, consFilePdf);
        	
        	Long tipus =  consentiment.equals(Constants.CONSENTIMENT_TIPUS_SI) ? Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI : Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_NOOP;
        	String nom = "Document Consentiment";
        	afegirDocumentSolicitudAmbFitxer(fitxerCopia, nom, tipus, soliID);
        	return fitxerCopia;
        }else {
        	log.info("No tenim document de consentiment");
        	return null;
        }

    }

	private Set<SolicitudServeiJPA> afegirServeisSolicitud(List<TramitIServ> listaTramitsI, Timestamp dataFi,
			Long soliID, TramitJConsent J) throws I18NException {

		Set<SolicitudServeiJPA> serveisDeLaSolicitud = new HashSet<SolicitudServeiJPA>();

		for (TramitIServ I : listaTramitsI) {
			String codiServei = I.getCodi();
			log.info("Codi: " + codiServei);

			List<Servei> llistaSservei = serveiEjb.select(ServeiFields.CODI.equal(codiServei));

			if (llistaSservei.size() != 1) {
				continue;
			}

			ServeiJPA servei = (ServeiJPA) llistaSservei.get(0);

			Long serveiID = servei.getServeiID();
			log.info("serveiID: " + serveiID);

			Long count = solicitudServeiLogicaEjb.count(Where.AND(SolicitudServeiFields.SOLICITUDID.equal(soliID),
					SolicitudServeiFields.SERVEIID.equal(serveiID)));

			if (count == 0) {

				Long estatSolicitudServeiID = Constants.ESTAT_SOLICITUD_SERVEI_PENDENT_AUTORITZAR;

				String caduca;
				String caducafecha;
				if (dataFi == null) {
					caduca = "No caduca";
					caducafecha = "";
				} else {
					caduca = "Caduca";
					caducafecha = dataFi.toString();
				}

				String notes = "";

				SolicitudServeiJPA ss = new SolicitudServeiJPA();

				ss.setCaduca(caduca);
				ss.setEstatSolicitudServeiID(estatSolicitudServeiID);
				ss.setFechaCaduca(caducafecha);
				ss.setNotes(notes);
				ss.setServeiID(serveiID);
				ss.setServei(servei);
				
				ss.setSolicitudID(soliID);
				
				//Gestió de normes legals
				{
					String norma1 = I.getNorma();
					Long fitxer1Id = I.getFitxernormaID();
					FitxerJPA fitxer1 = fitxerPublicLogicaEjb.findByPrimaryKey(fitxer1Id);
					String articles = I.getArticles();

					ss.setArticles(articles);
					ss.setFitxernormaID(fitxer1Id);
					ss.setFitxernorma(fitxer1);
					ss.setNormaLegal(norma1);

					String norma2 = I.getNorma2();
					Long fitxer2Id = I.getFitxernorma2ID();
					FitxerJPA fitxer2 = fitxerPublicLogicaEjb.findByPrimaryKey(fitxer2Id);
					String articles2 = I.getArticles2();
					
					ss.setNorma2(norma2);
					ss.setFitxernorma2ID(fitxer2Id);
					ss.setFitxernorma2(fitxer2);
					ss.setArticles2(articles2);
					
					String norma3 = I.getNorma3();
					Long fitxer3Id = I.getFitxernorma3ID();
					FitxerJPA fitxer3 = fitxerPublicLogicaEjb.findByPrimaryKey(fitxer3Id);
					String articles3 = I.getArticles3();
					
					ss.setNorma3(norma3);
					ss.setFitxernorma3ID(fitxer3Id);
					ss.setFitxernorma3(fitxer3);
					ss.setArticles3(articles3);
				}
				
				log.info("Norma Legal:" + ss.getNormaLegal());

				SolicitudServeiJPA solicitudServei = (SolicitudServeiJPA) solicitudServeiLogicaEjb.create(ss);
				serveisDeLaSolicitud.add(solicitudServei);
				log.info("Afegit servei a la solicitud: " + solicitudServei.getId());
			}
		}
		return serveisDeLaSolicitud;
	}

	private void enviarMailSolicitant(SolicitudJPA solicitud, String destinatariMail) {
        // Afegir event de creació de la solicitud.
        try {
            java.lang.Long _solicitudID_ = solicitud.getSolicitudID();
            java.lang.Long _incidenciaTecnicaID_ = null;
            java.sql.Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());
            java.lang.String _persona_ = solicitud.getCreador();
            
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC;

            java.lang.String missatge = getMissatgeCorreu(solicitud);
            java.lang.String asumpte = "PINBAL [" + _solicitudID_ + "] - SOLICITUD REGISTRADA AL SISTEMA ";

            java.lang.String _comentari_ = "<div>" + missatge + "</div>";
            
            java.lang.Long _fitxerID_ = null;
            boolean _noLlegit_ = true;
            
            java.lang.String _destinatari_ = solicitud.getPersonaContacte();
            java.lang.String _destinatariMail_ = destinatariMail;
            java.lang.String _caidConsulta_ = null;
            java.lang.String _caidSeguiment_ = null;
            
			eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_, _destinatari_,
					_destinatariMail_, asumpte, _comentari_, _fitxerID_, _noLlegit_, _caidConsulta_, _caidSeguiment_);            
        } catch (Throwable th) {
			log.error("Error enviant correu al Solicitant: " + th.getMessage(), th);
        }
	}
    
	private String getMissatgeCorreu(SolicitudJPA soli) {
//		Long soliID = soli.getSolicitudID();
		String msg = ""
				+ "<div id=\"missatge\" style=\"margin: .5rem;\">"
				+ "    Bon dia,<br />"
				+ "    Desde la Fundació BIT l'informam que hem rebut la seva sol·licitud d'autorització correctament. <br /><br />"
				+ "    <b>Procediment:</b> " + soli.getProcedimentNom() +  "<br />"
				+ "    <b>Codi:</b> "+ soli.getProcedimentCodi() +"<br />"
				+ "</div>";
		return msg;
	}

	public void generarDocumentsSolicitud(Long solicitudID, Long organID, Properties prop) throws Exception, I18NException {
		
		setOrganGestorProperties(organID, prop);		
		
		File outputPDF = File.createTempFile("pinbaladmin_formulari", ".pdf");
		File outputODT = File.createTempFile("pinbaladmin_formulari", ".odt");

		File plantilla = new File(Configuracio.getTemplateFormulari());

		ParserFormulariXML.creaDocFormulari(prop, plantilla, outputPDF, outputODT);

		{
			FitxerJPA fitxer = new FitxerJPA("Formulario_Director_General.pdf", outputPDF.length(), "application/pdf",
					"");

			fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

			Long tipus = Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF;
			afegirDocumentSolicitudAmbFitxer(fitxer, "Formulario_Director_General (PDF)", tipus, solicitudID);

			FileSystemManager.sobreescriureFitxer(outputPDF, fitxer.getFitxerID());
		}

		{
			FitxerJPA fitxer = new FitxerJPA("Formulario_Director_General.odt", outputODT.length(),
					"application/vnd.oasis.opendocument.text", "");

			fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

			FileSystemManager.sobreescriureFitxer(outputODT, fitxer.getFitxerID());

			Long tipus = Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_ODT;
			afegirDocumentSolicitudAmbFitxer(fitxer, "Formulario_Director_General (ODT)", tipus, solicitudID);
		}
	}

	@Override
	public void setOrganGestorProperties(Long organID, Properties prop) throws I18NException {
		String denomincaion;
		String cif;
		String UR;
		String dir3UR;
		String dir3Raiz;

		/*
		 * Denominació: Organ Gestor
		 * CIF: Primer CIF que trobi cercant als pares.
		 * Unitat Responsable: Si el CIF es el de Govern, posar DGTIC, sino, la del CIF trobat.
		 * DIR3 RESPONSABLE: DIR3 UR
		 * DIR3 RAIZ: Dir3 pare mes alt.
		 */
		
		Organ organGestor = organLogicaEjb.findByPrimaryKey(organID);
		Organ unitatResponsable = null; 
		Organ arrel = null; 

		Organ organTest = organGestor;
		boolean end = false;
		while (!end) {
			if (unitatResponsable == null && organTest.getCif() != null) {
				unitatResponsable = organTest;
				organGestor = organTest;
			}
			if (arrel == null && organTest.getDir3pare() == null) {
				arrel = organTest;
			}
			
			if (organTest.getDir3pare() != null) {
				List<Organ> pares = organLogicaEjb.select(OrganFields.DIR3.equal(organTest.getDir3pare()));
				organTest = pares.get(0);
			}else {
				end = true;
			}
		}
		
		if (unitatResponsable.getCif().equals("S0711001H")) {
			String dir3Dgtic = "A04027005";
			List<Organ> organs = organLogicaEjb.select(OrganFields.DIR3.equal(dir3Dgtic));
			if (organs.size() == 1) {
				Organ dgtic = organs.get(0);
				unitatResponsable = dgtic;
				organGestor = arrel;
			}
		}

		
		denomincaion = organGestor.getNom();
		cif = organGestor.getCif();
		
		UR = unitatResponsable.getNom();
		dir3UR = unitatResponsable.getDir3();
		
		dir3Raiz = arrel.getDir3();

		log.info("denomincaion: " + denomincaion);
		log.info("cif: " + cif);
		log.info("UR: " + UR);
		log.info("dir3UR: " + dir3UR);
		log.info("dir3Raiz: " + dir3Raiz);

		prop.setProperty("FORMULARIO.DATOS_SOLICITUD.DENOMINACION", denomincaion);
		prop.setProperty("FORMULARIO.DATOS_SOLICITUD.CIF", cif);
		prop.setProperty("FORMULARIO.DATOS_SOLICITUD.UNIDAD", UR);
		prop.setProperty("FORMULARIO.DATOS_SOLICITUD.CODIUR", dir3UR);
		prop.setProperty("FORMULARIO.DATOS_SOLICITUD.CODIOA", dir3Raiz);
	}


    public void generarExcelDeServeis(SolicitudJPA soli, Fitxer docConsentiment) throws Exception, I18NException {

        Long solicitudID = soli.getSolicitudID();
        log.info("generaPlantillaExcelDeServeis(); => SOLI = " + solicitudID);

        
        File plantillaXLSX = new File(Configuracio.getTemplateServeisExcel());
        
        String[] excels = { "locals", "estatals" };

		for (String excel : excels) {
			log.info("Generant Excel de Serveis: " + excel);
			byte[] data = CrearExcelDeServeis.crearExcelDeServeis(plantillaXLSX, soli, excel, docConsentiment);

			// locals_2019-12-31_12:26_Plantilla-Procedimientos.xlsx
			String nom = excel + "_" + SDF.format(new Date()) + "_" + plantillaXLSX.getName();

			FitxerJPA fitxer = new FitxerJPA(nom, data.length,
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", null);
			fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

			FileSystemManager.crearFitxer(new ByteArrayInputStream(data), fitxer.getFitxerID());

			Long tipus = Constants.DOCUMENT_SOLICITUD_EXCEL_SERVEIS;
			afegirDocumentSolicitudAmbFitxer(fitxer, nom, tipus, solicitudID);
		}
    }

	private Long generarDocumentSolicitudAmbXML(String codiProc, Properties props,List<TramitIServ> listaTramitsI) throws Exception {

		String fileName = "Datos_de_la_solicitud_" + codiProc + ".pdf";
		File outputPDF = File.createTempFile("pinbaladmin_formulari_tramit", ".pdf");
		FileOutputStream fosPDF = new FileOutputStream(outputPDF);

		File plantilla = new File(Configuracio.getTemplateFormulariTramit());
		byte[] template = FileUtils.readFileToByteArray(plantilla);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("props", props);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataStr = sdf.format(new Date());
		data.put("data", dataStr);
		
		Long tipus = Long.parseLong(props.getProperty("FORMULARIO.DATOS_SOLICITUD.TIPOPROCEDIMIENTO"));
		String procedimentTipus = getTipusProcediment(tipus);
		data.put("tipusProcedimentNom", procedimentTipus);
		
		data.put("servicios", listaTramitsI);
		
		String tipusConsentiment= props.getProperty("FORMULARIO.DATOS_SOLICITUD.CONSENTIMIENTO");
		String tipusConsentimentNom = "";
		switch (tipusConsentiment) {
		case Constants.CONSENTIMENT_TIPUS_NOOP:
			tipusConsentimentNom = "No Oposición";
			break;
		case Constants.CONSENTIMENT_TIPUS_LLEI:
			tipusConsentimentNom = "Ley";
			break;
		case Constants.CONSENTIMENT_TIPUS_SI:
			tipusConsentimentNom = "Sí";
			break;
		}
		data.put("tipusConsentimentNom", tipusConsentimentNom);

		try {
			ParserFormulariXML.createPdf(new ByteArrayInputStream(template), fosPDF, data);
			
			long size = outputPDF.length();
			String mime = "application/pdf";
			String desc = "";

			FitxerJPA fitxer = new FitxerJPA(fileName, size, mime, desc);
			fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

			FileSystemManager.crearFitxer(outputPDF, fitxer.getFitxerID());

			return fitxer.getFitxerID();

		} finally {
			try {
				fosPDF.flush();
				fosPDF.close();
			} catch (Exception e) {
				System.err.println("Error creant Documents de Solicitud" + e.getMessage());
			}
		}
	}

    
    @Override
    public Long[] getPartsTramitIDs(Long tramitID ) throws I18NException {

        String mailA = this.executeQueryOne(TramitAPersAutFields.MAIL, TramitAPersAutFields.TRAMITID.equal(tramitID));
        Long tramitA= mailA != null ? tramitID : null;
        
        Long[] tramitIDs = {
                tramitA,
                tramitBEjb.executeQueryOne(TramitBDadesSoliFields.DADESSOLIID, TramitBDadesSoliFields.TRAMITID.equal(tramitID)),
                tramitCEjb.executeQueryOne(TramitCDadesCesiFields.DADESCESIID, TramitCDadesCesiFields.TRAMITID.equal(tramitID)),
                tramitDEjb.executeQueryOne(TramitDCteAutFields.CTEAUTID, TramitDCteAutFields.TRAMITID.equal(tramitID)),
                tramitEEjb.executeQueryOne(TramitECteAudFields.CTEAUDID, TramitECteAudFields.TRAMITID.equal(tramitID)),
                tramitFEjb.executeQueryOne(TramitFCteTecFields.CTETECID, TramitFCteTecFields.TRAMITID.equal(tramitID)),
                tramitGEjb.executeQueryOne(TramitGDadesTitFields.DADESTITID, TramitGDadesTitFields.TRAMITID.equal(tramitID)),
                tramitHEjb.executeQueryOne(TramitHProcFields.PROCID, TramitHProcFields.TRAMITID.equal(tramitID)),
                tramitIEjb.executeQueryOne(TramitIServFields.SERVID, TramitIServFields.TRAMITID.equal(tramitID)), 
                tramitJEjb.executeQueryOne(TramitJConsentFields.CONSENTID, TramitJConsentFields.TRAMITID.equal(tramitID)),
        };

        return tramitIDs;
    }

    public String toFullName(String nom, String l1, String l2) {
        String fullName = nom + " " + l1 + (l2 == "" ? "" : " " + l2);
        return fullName;
    }

    public String getTipusProcediment(Long key) {
        String tp = null;
        String lang = "es";

        List<TipusProcediment> tipus = TipusProcediments.getAllTipusProcediments();
        for (TipusProcediment tipusProcediment : tipus) {
            if (tipusProcediment.id == key) {
                if (lang.equals("es")) {
                    tp = tipusProcediment.castella;
                } else {
                    tp = tipusProcediment.catala;
                }
                break;
            }
        }
        return tp;
    }
}