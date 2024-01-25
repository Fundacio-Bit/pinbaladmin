package org.fundaciobit.pinbaladmin.logic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.fundaciobit.pinbaladmin.ejb.EventService;
import org.fundaciobit.pinbaladmin.ejb.FitxerService;
import org.fundaciobit.pinbaladmin.ejb.OrganService;
import org.fundaciobit.pinbaladmin.ejb.ServeiService;
import org.fundaciobit.pinbaladmin.ejb.TramitAPersAutEJB;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
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
    @EJB(mappedName = OrganService.JNDI_NAME)
    protected OrganService organJEjb;
    @EJB(mappedName = FitxerService.JNDI_NAME)
    protected FitxerService fitxerEjb;
    @EJB(mappedName = EventService.JNDI_NAME)
    protected EventService eventEjb;
    @EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
    protected SolicitudServeiLogicaService solicitudServeiLogicaEjb;
    @EJB(mappedName = ServeiService.JNDI_NAME)
    protected ServeiService serveiEjb;
    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService documentSolicitudEjb;
    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.DocumentService documentEjb;
    
    public static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

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

    private Long generaXml(Long tramitID) throws I18NException {

        try {
            String fileXml=Configuracio.getTemplateTramitSistraXml();
            log.info("fileXml: "+fileXml);
            
            String plantilla = FileUtils.readFileToString(new File(fileXml), Charset.defaultCharset());

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
                        break;
                        case "B":
                            TramitBDadesSoli B = (TramitBDadesSoli) obj;
                            String tipussolicitudNom = tramitBEjb.getTipussolicitudValue(B.getTipussolicitud());
                            map.put("tipussolicitudNom", tipussolicitudNom);

                            map.put("pre", "Preproducció");
                            map.put("pro", "Producció");
                        break;
                        case "C":
                            TramitCDadesCesi C = (TramitCDadesCesi) obj;
                            String denominacioNom = tramitCEjb.getDenominacioValue(C.getDenominacio());
                            map.put("denominacioNom", denominacioNom);

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

                            Long tipus = Long.parseLong(H.getTipus());
                            String tipusProcedimentNom = getTipusProcediment(tipus);
                            map.put("tipusProcedimentNom", tipusProcedimentNom);

                            String dataCaducitat;
                            if (H.isCaducitat()) {
                                dataCaducitat = SDF.format(H.getCaducitatdata());
                            } else {
                                dataCaducitat = "";
                            }
                            map.put("dataCaducitat", dataCaducitat);
                        break;
                        case "I":
                            map.put("noop", "No Oposició");
                            map.put("llei", "Llei");

                            map.put("servicios", lista);
                            String[] codis = new String[lista.size()];
                            String[] noms = new String[lista.size()];
                            for (int j = 0; j < lista.size(); j++) {
                                TramitIServ I = (TramitIServ) lista.get(j);
                                codis[j] = I.getCodi();
                                noms[j] =  tramitIEjb.getServeiValue(I.getNom());
                                if (I.getUrlconsentiment() == null) {
                                    I.setUrlconsentiment("");
                                }
                            }

                            /*
                            CONSENTIMIENTO:
                            noop -> No oposició
                            llei -> Llei
                            
                            LDECONSENTIMIENTO:
                            0 -> ...
                            1 -> Publicat
                            2 -> Adjunt
                             */

                            String codisServeisString = String.join(",", codis);
                            map.put("codisServeisString", codisServeisString);
                            map.put("nomServeis", noms);
                        break;
                        case "J":
                            TramitJConsent J = (TramitJConsent) obj;
//                            J.get
                        break;
                        
                    }
                }
            }
            String result = TemplateEngine.processExpressionLanguage(plantilla, map);
            
            String fileName = "D:/Projectes/pinbaladmin-files/formulario.xml";
            
            FileUtils.writeStringToFile(new File(fileName), result,
                    StandardCharsets.UTF_8, false);
            
            byte[] data = FileUtils.readFileToByteArray(new File(fileName));

            Fitxer f = fitxerEjb.create("formulari.xml", data.length, "aplication.xml", null);
            FileSystemManager.crearFitxer(new ByteArrayInputStream(data), f.getFitxerID());

            return f.getFitxerID();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        Long estatID = Constants.SOLICITUD_ESTAT_PENDENT;
        String notesSoli = "Procediment creat amb Formulari";
        Timestamp dataInici = new Timestamp(System.currentTimeMillis());
        Integer estatpinbal = Constants.ESTAT_PINBAL_NO_SOLICITAT;
        String creador = "pvico";
        String operador = "pvico";
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
        String dir3 = null;
        String nif = null;
        boolean produccio = true;

        //Depercats
        Long departamentID = null;

        Map<String, Object> map = new HashMap<String, Object>();

        List<List<?>> listas = Arrays.asList(this.select(TRAMITID.equal(tramitID)),
                tramitBEjb.select(TramitBDadesSoliFields.TRAMITID.equal(tramitID)),
                tramitCEjb.select(TramitCDadesCesiFields.TRAMITID.equal(tramitID)),
                tramitDEjb.select(TramitDCteAutFields.TRAMITID.equal(tramitID)),
                tramitEEjb.select(TramitECteAudFields.TRAMITID.equal(tramitID)),
                tramitFEjb.select(TramitFCteTecFields.TRAMITID.equal(tramitID)),
                tramitGEjb.select(TramitGDadesTitFields.TRAMITID.equal(tramitID)),
                tramitHEjb.select(TramitHProcFields.TRAMITID.equal(tramitID))
                //                    tramitIEjb.select(TramitIServFields.TRAMITID.equal(tramitID)), 
                // tramitJEjb.select(TramitJConsentFields.TRAMITID.equal(tramitID))
                );

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

                        String fullNameA = toFullName(A.getNom(), A.getLlinatge1(), A.getLlinatge2());
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

                        dir3 = C.getDir3responsable();
                        denominacio = tramitCEjb.getDenominacioValue(C.getDenominacio());
                        nif = C.getNif();

                        organid = organJEjb.executeQueryOne(OrganFields.ORGANID, OrganFields.DIR3.equal(dir3));

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

                        dataFi = H.getCaducitatdata();

                    break;
                    case "I":
                        map.put("noop", "No Oposició");
                        map.put("llei", "Llei");

                        map.put("servicios", lista);
                        String[] codis = new String[lista.size()];
                        String[] noms = new String[lista.size()];
                        for (int j = 0; j < lista.size(); j++) {
                            TramitIServ I = (TramitIServ) lista.get(j);
                            codis[j] = I.getCodi();
                            noms[j] = tramitIEjb.getServeiValue(I.getNom());
                        }

                        /*
                        CONSENTIMIENTO:
                        noop -> No oposició
                        llei -> Llei
                        
                        LDECONSENTIMIENTO:
                        0 -> ...
                        1 -> Publicat
                        2 -> Adjunt
                         */

                        String codisServeisString = String.join(",", codis);
                        map.put("codisServeisString", codisServeisString);
                        map.put("nomServeis", noms);
                    break;
                    case "J":
                        TramitJConsent J = (TramitJConsent) obj;
                    break;

                }
            }
        }
        //Documents
        Long solicitudXmlID = generaXml(tramitID);
        Long documentSolicitudID = null; //generaDcoumentSolicitudAmbXML();

        soli.setProcedimentCodi(procedimentCodi);
        soli.setCodiDescriptiu(codiDescriptiu);
        soli.setProcedimentNom(procedimentNom);
        soli.setProcedimentTipus(procedimentTipus);
        soli.setEstatID(estatID);
        soli.setDepartamentID(departamentID);
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
        soli.setDocumentSolicitudID(documentSolicitudID);
        soli.setSolicitudXmlID(solicitudXmlID);
        soli.setFirmatDocSolicitud(firmatDocSolicitud);
        soli.setProduccio(produccio);
        soli.setDenominacio(denominacio);
        soli.setDir3(dir3);
        soli.setNif(nif);
        soli.setCreador(creador);
        soli.setOperador(operador);
        soli.setEstatpinbal(estatpinbal);

        SolicitudJPA solicitud = (SolicitudJPA) solicitudLogicaEjb.create(soli);

        Long soliID = solicitud.getSolicitudID();
        log.info("SolicitudID de la solicitud creada: " + soliID);

        eventSolicitudCreada(creador, soliID);

        afegirServeisSolicitud(tramitID, dataFi, soliID);
        
        afegirDocumentConsentiment(tramitID, soliID);

        /*
         * Afegir documents:
         * 1. Document de solicitud
         * 2. PDF Director General - Ok
         * 3. ODT Director General - Ok
         * 4. Excel de Procs-Servs - Ok
         * 5. Consentiment - Ok
         */
        return solicitud;
    }

    private void afegirDocumentConsentiment(Long tramitID, Long soliID) throws I18NException {

        List<TramitJConsent> queryConsentiment = tramitJEjb.select(TramitJConsentFields.TRAMITID.equal(tramitID));
        if (queryConsentiment.size() == 1) {
            TramitJConsent J = (TramitJConsent) queryConsentiment.get(0);

            Long fitxerID = J.getAdjuntID();

            Long tipus = Constants.DOCUMENT_SOLICITUD_CONSENTIMENT;
            String nom = "Document Consentiment";
            Document doc = documentEjb.create(nom, fitxerID, null, null, tipus);

            DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), soliID);

            documentSolicitudEjb.create(ds);
            log.info("Afegit document de Consentiment");
        }

    }

    private void afegirServeisSolicitud(Long tramitID, Timestamp dataFi, Long soliID) throws I18NException {
        {
            List<TramitIServ> listaTramitsI = tramitIEjb.select(TramitIServFields.TRAMITID.equal(tramitID));

            for (TramitIServ I : listaTramitsI) {
                String codiServei = I.getCodi();
                log.info("Codi: " + codiServei);
                
                List<Servei> llistaSservei = serveiEjb.select(ServeiFields.CODI.equal(codiServei));
                
                if (llistaSservei.size() != 1) {
                    break;
                }

                ServeiJPA servei = (ServeiJPA) llistaSservei.get(0);

                Long serveiID = servei.getServeiID();
                log.info("serveiID: " + serveiID);
                
                Long count = solicitudServeiLogicaEjb.count(Where.AND(SolicitudServeiFields.SOLICITUDID.equal(soliID),
                        SolicitudServeiFields.SERVEIID.equal(serveiID)));

                if (count == 0) {

                    Long estatSolicitudServeiID = 10L; //ESTATS_SOLICITUD_SERVEI

                    String normaLegal = I.getNorma();
                    String enllazNormaLegal = I.getUrlnorma();
                    String articles = I.getArticles();
                    String tipusConsentiment = I.getConsentimentpublicat();  //"publicat"/"adjunt"
                    String consentiment = I.getConsentiment();
                    String enllazConsentiment = I.getUrlconsentiment();
                    
                    String caduca;
                    String caducafecha;
                    if (dataFi == null) {
                        caduca = "No caduca";
                        caducafecha = "";
                    }else {
                        caduca = "Caduca";
                        caducafecha = dataFi.toString();                            
                    }

                    String notes = "";

                    log.info("Norma Legal:" + normaLegal);
                    
                    SolicitudServeiJPA ss = new SolicitudServeiJPA(); 
                    
                    ss.setArticles(articles);
                    ss.setCaduca(caduca);
                    ss.setConsentiment(consentiment);
                    ss.setEnllazConsentiment(enllazConsentiment);
                    ss.setEnllazNormaLegal(enllazNormaLegal);
                    ss.setEstatSolicitudServeiID(estatSolicitudServeiID);
                    ss.setFechaCaduca(caducafecha);
                    ss.setNormaLegal(normaLegal);
                    ss.setNotes(notes);
                    ss.setServeiID(serveiID);
                    ss.setSolicitudID(soliID);
                    ss.setTipusConsentiment(tipusConsentiment);
                    
                    log.info("Norma Legal:" + ss.getNormaLegal());
                    solicitudServeiLogicaEjb.create(ss);
                }
            }
        }
    }

    private void eventSolicitudCreada(String creador, Long solicitudID) {
        // Afegir event de creació de la solicitud.

        try {
            java.lang.Long _solicitudID_ = solicitudID;
            java.lang.Long _incidenciaTecnicaID_ = null;
            java.sql.Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
            java.lang.String _persona_ = creador;
            java.lang.String _comentari_ = "S'ha creat la sol·licitud a partir del formulari Sistra de PinbalAdmin";
            java.lang.Long _fitxerID_ = null;
            boolean _noLlegit_ = false;
            java.lang.String _destinatari_ = null;
            java.lang.String _destinatariMail_ = null;

            eventEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_, _destinatari_,
                    _destinatariMail_, _comentari_, _fitxerID_, _noLlegit_, null, null);
        } catch (Throwable th) {
            log.error("Error creant el primer event de la solicitud: " + th.getMessage(), th);
        }
    }
    
    
    @Override
    public Long[] getPartsTramitIDs(long tramitID) throws I18NException {

        Long[] tramitIDs = {
                this.executeQueryOne(TramitAPersAutFields.PERSAUTID, TramitAPersAutFields.TRAMITID.equal(tramitID)),
                tramitBEjb.executeQueryOne(TramitBDadesSoliFields.DADESSOLIID,
                        TramitBDadesSoliFields.TRAMITID.equal(tramitID)),
                tramitCEjb.executeQueryOne(TramitCDadesCesiFields.DADESCESIID,
                        TramitCDadesCesiFields.TRAMITID.equal(tramitID)),
                tramitDEjb.executeQueryOne(TramitDCteAutFields.CTEAUTID, TramitDCteAutFields.TRAMITID.equal(tramitID)),
                tramitEEjb.executeQueryOne(TramitECteAudFields.CTEAUDID, TramitECteAudFields.TRAMITID.equal(tramitID)),
                tramitFEjb.executeQueryOne(TramitFCteTecFields.CTETECID, TramitFCteTecFields.TRAMITID.equal(tramitID)),
                tramitGEjb.executeQueryOne(TramitGDadesTitFields.DADESTITID,
                        TramitGDadesTitFields.TRAMITID.equal(tramitID)),
                tramitHEjb.executeQueryOne(TramitHProcFields.PROCID, TramitHProcFields.TRAMITID.equal(tramitID)),
                tramitIEjb.count(TRAMITID.equal(tramitID)), 
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