package org.fundaciobit.pinbaladmin.logic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsConfiguration;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.ejb.DocumentService;
import org.fundaciobit.pinbaladmin.ejb.FitxerService;
import org.fundaciobit.pinbaladmin.ejb.SolicitudEJB;
import org.fundaciobit.pinbaladmin.logic.dto.SolicitudDTO;
import org.fundaciobit.pinbaladmin.logic.utils.FileInfo;
import org.fundaciobit.pinbaladmin.logic.utils.PdfDownloader;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailAttachmentInfo;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.hibernate.Hibernate;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Articulos;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Consentimiento;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contacto;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contactos;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DocumentoAutorizacion;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DocumentosAutorizacion;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Norma;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Normas;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Procedimiento;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Servicio;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Servicios;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud;

/**
 * 
 * @author anadal
 * @author areus
 */
@Stateless(name = "SolicitudLogicaEJB")
//@SecurityDomain("seycon")
@PermitAll
public class SolicitudLogicaEJB extends SolicitudEJB implements SolicitudLogicaService {

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FitxerService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.FitxerService fitxerEjb;

    @EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
    protected DocumentSolicitudLogicaService documentSolicitudLogicaEjb;

    @EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
    protected SolicitudServeiLogicaService solicitudServeiLogicaEJB;

    @EJB(mappedName = EventLogicaService.JNDI_NAME)
    protected EventLogicaService eventLogicaEjb;

    @EJB(mappedName = DocumentService.JNDI_NAME)
    protected DocumentService documentEjb;

    @Override
    public Map<Long, List<SolicitudDTO>> getSolicitudsByServei(Collection<Long> serveiIds) {

        if (serveiIds.isEmpty()) {
            return Collections.emptyMap();
        }

        Query query = __em.createQuery("select " + "s.serveiID, " + "solser.solicitudID, "
                + "solser.solicitud.procedimentCodi,  " + "solser.solicitud.procedimentNom,  "
                + "solser.solicitud.departamentID " + "from ServeiJPA s join s.solicitudServeis solser "
                + "where s.serveiID in (:serveiIds) " + "order by s.serveiID, solser.solicitud.dataInici DESC");
        query.setParameter("serveiIds", serveiIds);
        List<Object[]> resultList = (List<Object[]>) query.getResultList();

        Map<Long, List<SolicitudDTO>> resultMap = new HashMap<Long, List<SolicitudDTO>>();
        for (Long serveiId : serveiIds) {
            resultMap.put(serveiId, new ArrayList<SolicitudDTO>());
        }

        for (Object[] result : resultList) {
            Long serveiId = (Long) result[0];
            SolicitudDTO solicitudDTO = new SolicitudDTO((Long) result[1], (String) result[2], (String) result[3],
                    (Long) result[4]);
            resultMap.get(serveiId).add(solicitudDTO);
        }

        return resultMap;
    }

    @Override
    public Set<Long> deleteFull(Long solicitudId, boolean deleteFiles) throws I18NException {

        Set<Long> files = new HashSet<Long>();

        SolicitudJPA solicitud = this.findByPrimaryKey(solicitudId);

        if (solicitud == null) {
            return files;
        }

        // Borram Solicituds Serveis
        {
            List<Long> list = solicitudServeiLogicaEJB.executeQuery(SolicitudServeiFields.SERVEIID.select,
                    SolicitudServeiFields.SOLICITUDID.equal(solicitudId));

            for (Long ss : list) {
                files.addAll(solicitudServeiLogicaEJB.deleteFull(ss, solicitudId, false));
            }

        }

        // Borram Documents de Solicitud
        {
            List<Long> documentsIds = documentSolicitudLogicaEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID.select,
                    DocumentSolicitudFields.SOLICITUDID.equal(solicitudId));

            for (Long ds : documentsIds) {
                files.addAll(documentSolicitudLogicaEjb.deleteFull(ds, solicitudId, false));
            }

        }

        // Borram solicitud
        if (solicitud.getDocumentSolicitudID() != null) {
            files.add(solicitud.getDocumentSolicitudID());
        }

        // Esborram events
        files.addAll(eventLogicaEjb.deleteFullBySolicitantID(solicitudId));

        this.delete(solicitud);

        /*
          Si tot ha anat be llavors borram els fitxers
         if (deleteFiles) {
           LogicUtils.deleteFiles(files, fitxerEjb);
         }
         */

        return files;
    }

    @Override
    @PermitAll
    public SolicitudJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    public SolicitudJPA findByPrimaryKeyFull(Long _ID_) throws I18NException {

        SolicitudJPA s = this.findByPrimaryKey(_ID_);

        Hibernate.initialize(s.getSolicitudServeis());

        Set<SolicitudServeiJPA> ssList = s.getSolicitudServeis();

        for (SolicitudServeiJPA solicitudServeiJPA : ssList) {
            Hibernate.initialize(solicitudServeiJPA.getServei());
            Hibernate.initialize(solicitudServeiJPA.getServei().getEntitatServei());
        }

        return s;

    }

    @Override
    @PermitAll
    public void updateCAID(Long soliID, String incidencia, String seguiment) throws I18NException {

        SolicitudJPA soli = this.findByPrimaryKey(soliID);

        soli.setTicketAssociat(incidencia);
        soli.setTicketNumeroSeguiment(seguiment);

        this.update(soli);
    }

    protected Fitxer createFile(FitxerService fitxerEJB, String fileName, String mime, String descripcio, byte[] data)
            throws I18NException {

        Fitxer f = fitxerEJB.create(fileName, data.length, mime, descripcio);

        FileSystemManager.crearFitxer(new ByteArrayInputStream(data), f.getFitxerID());

        return f;
    }

    @Override
    public void crearSolicituds(List<SolicitudJPA> solicituds, EmailAttachmentInfo xlsx,
            List<EmailAttachmentInfo> attachs) throws I18NException {

        for (SolicitudJPA soli : solicituds) {

            // Desvincular Serveis

            Set<SolicitudServeiJPA> ssSet = soli.getSolicitudServeis();

            soli.setSolicitudServeis(null);

            // Crear Fitxer XLSX i afegir-ho a solicitud
            Fitxer xlsxFile = createFile(fitxerEjb, xlsx.getFileName(), xlsx.getContentType(), null, xlsx.getData());
            soli.setSolicitudXmlID(xlsxFile.getFitxerID());

            // Crear Solicitud
            this.create(soli);

            Long soliID = soli.getSolicitudID();

            // Afegir Serveis a la Solicitud
            for (SolicitudServeiJPA ss : ssSet) {
                ss.setSolicitudID(soliID);
                solicitudServeiLogicaEJB.create(ss);
            }

            // Afegir Documents per cada Solicitud
            for (EmailAttachmentInfo attach : attachs) {

                if (attach != xlsx) {

                    Fitxer attachFile = createFile(fitxerEjb, attach.getFileName(), attach.getContentType(), null,
                            attach.getData());
                    Long tipus = Constants.DOCUMENT_SOLICITUD_ALTRES;
                    
                    DocumentJPA doc = new DocumentJPA(attach.getFileName(), attachFile.getFitxerID(), null, null, tipus);

                    documentEjb.create(doc);

                    DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), soliID);

                    documentSolicitudLogicaEjb.create(ds);

                }
            }
        }
    }

    public enum TipusCridada{
        ALTA, CONSULTA, MODIFICACIO,
    }
    protected PinbalAdminSolicitudsConfiguration getPinbalAdminSolicitudsConfiguration(TipusCridada tipus) throws Exception {

        PinbalAdminSolicitudsConfiguration config = new PinbalAdminSolicitudsConfiguration();

        config.setUrlBase(Configuracio.getApiPinbalUrl());
        config.setUsername(Configuracio.getApiPinbalUsername());
        config.setPassword(Configuracio.getApiPinbalPassword());

        config.setFinalidad("Solicitar autorización procedimiento"); // getPropertyRequired(propertyBase + "finalitat"); 
        config.setIdentificadorSolicitante("S0711001H"); // getPropertyRequired(propertyBase + "identificadorsolicitant");
        config.setUnidadTramitadora("Fundacio BIT"); // getPropertyRequired(propertyBase + "unitattramitadora");

        config.setCodProcedimiento("PREALTAS");

        /**
        SVDPIDSOLAUTWS01    | Solicitud de autorizaciones en PID
        SVDPIDESTADOAUTWS01 | Servicio de estado de las autorizaciones en PID 
        SVDPIDACTPROCWS01   | Servicio de actualización de un procedimiento ya dado de alta en PID
         */

        String codigoCertificado;
        switch (tipus) {
            case ALTA:
                codigoCertificado = "SVDPIDSOLAUTWS01";
            break;
            case CONSULTA:
                codigoCertificado = "SVDPIDESTADOAUTWS01";
            break;
            case MODIFICACIO:
                codigoCertificado = "SVDPIDACTPROCWS01";
            break;
            default:
                throw new Exception("El tipus de cridada no es conegut: ]" + tipus.toString() + "[");
        }
       
        config.setCodigoCertificado(codigoCertificado);

        return config;
    }

    @Override
    public Retorno consultaEstatApiPinbal(ScspTitular titular, ScspFuncionario funcionario, Consulta consulta) throws Exception {

        PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(getPinbalAdminSolicitudsConfiguration(TipusCridada.CONSULTA));
        Retorno retorno = api.consultaEstatPinbalApi(consulta, titular, funcionario);

        return retorno;
    }
    
    @Override
    public Respuesta altaSolicitudApiPinbal(ScspTitular titular, ScspFuncionario funcionario, Solicitud solicitud)
            throws Exception {

        PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(getPinbalAdminSolicitudsConfiguration(TipusCridada.ALTA));
        Respuesta respuesta = api.altaSolicitudPinbalApi(solicitud, titular, funcionario);

        return respuesta;
    }

    @Override
    public Solicitud getDadesSolicitudApiPinbal(Long solicitudID, Properties prop) throws Exception {

        SolicitudJPA soli = this.findByPrimaryKey(solicitudID);
        Solicitud solicitud = new Solicitud();

        String asunto = "Alta Servicios. Codigo Solicitud: " + soli.getProcedimentCodi();
        solicitud.setAsunto(asunto);

        Contactos contactos = getContactos(prop);
        solicitud.setContactos(contactos);
        
        Procedimiento proc = getProcedimiento(soli);
        solicitud.setProcedimiento(proc);
        return solicitud;
    }


    
    private Contactos getContactos(Properties prop) {

        String base = "FORMULARIO.DATOS_SOLICITUD.";

        Contactos contactos = new Contactos();

        // Contacto Aut
        String contactoAutApe1 = prop.getProperty(base + "APE1SECD");
        String contactoAutApe2 = prop.getProperty(base + "APE2SECD");
        String contactoAutMail = prop.getProperty(base + "MAILSECD");
        String contactoAutNombre = prop.getProperty(base + "NOMBRESECD");
        String contactoAutTelefon = prop.getProperty(base + "TELEFONOSECD");

        Contacto contactoAut = createContacto(contactoAutApe1, contactoAutApe2, contactoAutMail, contactoAutNombre,
                contactoAutTelefon);

        if (contactoAut != null) {
            contactos.getContacto().add(contactoAut);
        }

        // Contacto Aud
        String contactoAudApe1 = prop.getProperty(base + "APE1SECE");
        String contactoAudApe2 = prop.getProperty(base + "APE2SECE");
        String contactoAudMail = prop.getProperty(base + "MAILSECE");
        String contactoAudNombre = prop.getProperty(base + "NOMBRESECE");
        String contactoAudTelefon = prop.getProperty(base + "TELEFONOSECE");

        Contacto contactoAud = createContacto(contactoAudApe1, contactoAudApe2, contactoAudMail, contactoAudNombre,
                contactoAudTelefon);

        if (contactoAud != null) {
            contactos.getContacto().add(contactoAud);
        }

        // Contacto Tec
        String contactoTecApe1 = prop.getProperty(base + "APE1SECF");
        String contactoTecApe2 = prop.getProperty(base + "APE2SECF");
        String contactoTecMail = prop.getProperty(base + "MAILSECF");
        String contactoTecNombre = prop.getProperty(base + "NOMBRESECF");
        String contactoTecTelefon = prop.getProperty(base + "TELEFONOSECF");

        Contacto contactoTec = createContacto(contactoTecApe1, contactoTecApe2, contactoTecMail, contactoTecNombre,
                contactoTecTelefon);

        if (contactoTec != null) {
            contactos.getContacto().add(contactoTec);
        }

        return contactos;
    }

    private Contacto createContacto(String contactoApe1, String contactoApe2, String contactoMail,
            String contactoNombre, String contactoTelefono) {
        
        if (contactoApe1 != null && contactoMail != null && contactoNombre != null && contactoTelefono != null) {
            Contacto contacto = new Contacto();
            contacto.setApellido1(contactoApe1);
            if (contactoApe2 == null) {
             //   contactoApe2 = "Apellido2";
            }
            
            contacto.setApellido2(contactoApe2);
            contacto.setEmail(contactoMail);
            contacto.setFax(null);
            contacto.setNombre(contactoNombre);
            contacto.setTelefono(contactoTelefono);
            return contacto;
        } else {
            return null;
        }
    }

    private class DocAuthInfo {
        
        private FitxerJPA fitxer;
        private String descripcio;
        private String tipo;
        
        public FitxerJPA getFitxer() {
            return fitxer;
        }
        public String getDescripcio() {
            return descripcio;
        }
        public String getTipo() {
            return tipo;
        }
        public DocAuthInfo(FitxerJPA fitxer, String descripcio, String tipo) {
            super();
            this.fitxer = fitxer;
            this.descripcio = descripcio;
            this.tipo = tipo;
        }
    }
    
    public Procedimiento getProcedimiento(SolicitudJPA soli) throws Exception {

        String _Automatizado = "N";// soli.getAutomatizado();
        String _Periodico = "N"; //soli.getPeriodico();

        String petsDia = "40"; //soli.getPetsDia();
        Integer _PeticionesEstimadas = Integer.parseInt(petsDia);

        String tipusProc = soli.getProcedimentTipus();
        Integer _ClaseTramite = getIdentificadorNuevoPorId(tipusProc);
        
        String _Codigo = soli.getProcedimentCodi();
        String _Nombre = soli.getProcedimentNom();
        String _Descripcion = soli.getCodiDescriptiu();
        if (_Nombre.equals(_Descripcion)) {
            _Descripcion = "_" + _Descripcion;
        }
        String _Observaciones = null;//soli.getNotes();

        Timestamp dataCaducitat = soli.getDataFi();
        XMLGregorianCalendar _FechaCaducidad = parseTimestampToXMLGregorian(dataCaducitat);

        Fitxer consentiment = null;
        //Aquí son el excel de servicios y el documento PDF del Director General.
        Set<DocAuthInfo> docsAuth = new HashSet<DocAuthInfo>();
        
        for (DocumentSolicitudJPA document : soli.getDocumentSolicituds()) {

            Long tipus = document.getDocument().getTipus();
            
            if (tipus == Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF) {
                FitxerJPA fitxer = document.getDocument().getFitxerFirmat();
                if (fitxer != null) {
                    String desc = "Formulari PDF firmat per el director";
                    String tipo = "FORMULARIO DE AUTORIZACION";
                    docsAuth.add(new DocAuthInfo(fitxer, desc, tipo)); 
                }else {
                    log.info("Fa falta el formulari firmat per el DG");
                }
                
            } else if (tipus == Constants.DOCUMENT_SOLICITUD_EXCEL_SERVEIS) {
                FitxerJPA fitxer = document.getDocument().getFitxerOriginal();
                String desc = "Excel de serveis i procediments";
                String tipo = "EXCEL DE SERVICIOS";
//                docsAuth.add(new DocAuthInfo(fitxer, desc, tipo)); 

            } else if (tipus == Constants.DOCUMENT_SOLICITUD_CONSENTIMENT) {
                consentiment = document.getDocument().getFitxerFirmat(); // Document consentiment
            } else {
                FitxerJPA original = document.getDocument().getFitxerOriginal();
                if (original.getMime().equals("application/pdf")) {
                    FitxerJPA fitxer = original;
                    String desc = "Fitxer PDF associat al procediment";
                    String tipo = "DOC AUTORITZACÓ";
                    docsAuth.add(new DocAuthInfo(fitxer, desc, tipo)); 
                }
            }
        }

        Consentimiento _Consentimiento = getConsentimiento(soli.getSolicitudServeis(), consentiment);
        DocumentosAutorizacion _DocumentosAutorizacion = getDocsAutorizacion(docsAuth);
        Servicios _Servicios = getServicios(soli);

        Procedimiento proc = new Procedimiento();
        proc.setAutomatizado(_Automatizado);
        proc.setClaseTramite(_ClaseTramite);
        proc.setCodigo(_Codigo);
        proc.setDescripcion(_Descripcion);
        proc.setNombre(_Nombre);
        proc.setObservaciones(_Observaciones);
        proc.setPeriodico(_Periodico);
        proc.setPeticionesEstimadas(_PeticionesEstimadas);
        proc.setFechaCaducidad(_FechaCaducidad);
        proc.setDocumentosAutorizacion(_DocumentosAutorizacion);
        proc.setConsentimiento(_Consentimiento);
        proc.setServicios(_Servicios);
        return proc;
    }

    private XMLGregorianCalendar parseTimestampToXMLGregorian(Timestamp caducitatdata) {
        if (caducitatdata == null) {
            return null;
        }
        XMLGregorianCalendar cal = null;
        try {

            LocalDateTime ldt = caducitatdata.toLocalDateTime();
            cal = DatatypeFactory.newInstance().newXMLGregorianCalendar();

            cal.setYear(ldt.getYear());
            cal.setMonth(ldt.getMonthValue());
            cal.setDay(ldt.getDayOfMonth());
            cal.setHour(ldt.getHour());
            cal.setMinute(ldt.getMinute());
            cal.setSecond(ldt.getSecond());
            cal.setFractionalSecond(new BigDecimal("0." + ldt.getNano()));

        } catch (DatatypeConfigurationException e) {

            e.printStackTrace();
        }
        return cal;
    }

    private Consentimiento getConsentimiento(Set<SolicitudServeiJPA> set, Fitxer fitxerConsentiment) throws Exception {

        Consentimiento cons = new Consentimiento();

        String consentiment = null;
        String tipo = null;
        String enlace = null;

        for (SolicitudServeiJPA solSerJPA : set) {
            consentiment = solSerJPA.getConsentiment(); //Si, Llei, No oposició
            if (consentiment != null) {
                if ("Llei".equals(consentiment) || "Ley".equals(consentiment)) {

                    cons.setTipo("Ley");
                    log.info("CONS: Ley");
                    return cons;

                } else {
                    tipo = consentiment.startsWith("S") ? "Si" : "NoOpo";
                    enlace = solSerJPA.getEnllazConsentiment();

                    if (enlace != null && enlace.trim().length() != 0) {
                        cons.setEnlace(enlace);
                        cons.setTipo(tipo);
                        log.info("CONS: " + tipo + ". Enlace: ]" + enlace + "[");
                        return cons;
                    }
                }
            }
        }

        //Si arriba aqui es que tots els serveis necessiten document. Si no el tenim a DocumentsSolicitud, s'haurà d'adjuntar a la vista previa
        if (fitxerConsentiment != null) {
            try {
                Consentimiento.Documento doc = new Consentimiento.Documento();

                Long consentimentID = fitxerConsentiment.getFitxerID();

                String nom = fitxerConsentiment.getNom();
                String descripcio = "Fitxer de consentiment. IDFitxer: " + consentimentID;

                File fileConsentiment = FileSystemManager.getFile(consentimentID);
                byte[] contingut = FileUtils.readFromFile(fileConsentiment);

                log.info("CONS: " + nom + " : " + contingut.length + " bytes");
                doc.setNombre(nom);
                doc.setDescripcion(descripcio);
                doc.setContenido(contingut);

                cons.setTipo(tipo);
                cons.setDocumento(doc);
                return cons;
            } catch (Exception e) {
                String msg = "CONS: Error llegint amb FileUtils.readFromFile()";
                log.error(msg);
                throw new Exception(msg);
            }
        } else {
            cons.setTipo(tipo);
            cons.setEnlace(null);
            cons.setDocumento(null);
            log.info("CONS: No tenim fitxer de consentiment. Demanar-ho a la vista prèvia");
            return cons;
        }

    }

    private DocumentosAutorizacion getDocsAutorizacion(Set<DocAuthInfo> documents) throws Exception {

        log.info("Documents de la solicitud: " + documents.size());
        DocumentosAutorizacion docs = new DocumentosAutorizacion();

        for (DocAuthInfo docInfo : documents) {
            FitxerJPA fitxer = docInfo.getFitxer();

            String nom = fitxer.getNom();
            String descripcio = docInfo.getDescripcio();
            String tipo = docInfo.getTipo();

            Long fitxerID = fitxer.getFitxerID();
            File file = FileSystemManager.getFile(fitxerID);
            byte[] contingut = FileUtils.readFromFile(file);

            DocumentoAutorizacion docAut = new DocumentoAutorizacion();
            // AUT - FORMULARIO AUTORIZACION: FicherFirmart09.pdf (124562 bytes)
            log.info("AUT - " + tipo + ": " + nom + " (" + contingut.length + " bytes)");

            docAut.setNombre(nom);
            docAut.setDescripcion(descripcio);
            docAut.setTipo(tipo);
            docAut.setContenido(contingut);

            docs.getDocumentoAutorizacion().add(docAut);
        }
        return docs;
    }

    private Servicios getServicios(SolicitudJPA soli) throws Exception {

        Servicios servicios = new Servicios();
        Set<SolicitudServeiJPA> serveisDeLaSolicitud = soli.getSolicitudServeis();

        for (SolicitudServeiJPA ss : serveisDeLaSolicitud) {
            boolean balear = ss.getServei().getEntitatServei().isBalears();
            if (!balear) {

                Servicio servicio = new Servicio();

                Norma norma = new Norma();
                {
                    String normaLegal = ss.getNormaLegal();

                    Norma.Documento docNorma = new Norma.Documento();
                    String enlace = ss.getEnllazNormaLegal();

                    boolean debug = false;
                    FileInfo normaFileInfo = PdfDownloader.downloadPDFFromBoeBoibUrl(enlace, debug);

                    if (normaFileInfo != null) {
                        String nom = normaFileInfo.getFileName();
                        String descripcio = "Norma del servicio: " + ss.getServei().getNom();
                        byte[] contingut = normaFileInfo.getContent();

                        log.info("NORMA - " + normaLegal + ": " + nom + " (" + contingut.length + " bytes)");

                        docNorma.setNombre(nom);
                        docNorma.setDescripcion(descripcio);
                        docNorma.setContenido(contingut);
                        docNorma.setEnlace(enlace);
                    } else {
                        docNorma = null;
                    }

                    Articulos articulos = new Articulos();
                    String articulosString = ss.getArticles();
                    String[] articulosArray = articulosString.split(",");

                    for (String articulo : articulosArray) {
                        articulos.getArticulo().add(articulo);
                    }

                    norma.setNormaLegal(normaLegal);
                    norma.setDocumento(docNorma);
                    norma.setArticulos(articulos);
                }

                String codigoCertificado = ss.getServei().getCodi();

                Normas normas = new Normas();
                normas.getNorma().add(norma);

                servicio.setCodigoCertificado(codigoCertificado);
                servicio.setNormas(normas);

                servicios.getServicio().add(servicio);
            }
        }

        return servicios;
    }

    private int getIdentificadorNuevoPorId(String tipoProcedimiento) {
        int idTipoProcedimiento = TipusProcediments.getIdentificadorTipoProcedimiento(tipoProcedimiento);

        // Mapeo de identificadores en la lista actual a identificadores en la nueva lista
        Map<Integer, Integer> mapeoIdentificadores = new HashMap<>();

        // Mapeo de identificadores en la lista actual a identificadores en la nueva lista
        mapeoIdentificadores.put(1, 34);  // Aduanero
        mapeoIdentificadores.put(2, 19);  // Afiliación y cotización a la Seguridad Social
        mapeoIdentificadores.put(3, 20);  // Autorizaciones, licencias, concesiones y homologaciones
        mapeoIdentificadores.put(4, 21);  // Ayudas, Becas y Subvenciones
        mapeoIdentificadores.put(5, 22);  // Certificados
        mapeoIdentificadores.put(6, 23);  // Contratación pública
        mapeoIdentificadores.put(7, 24);  // Convenios de Colaboración y Comunicaciones administrativas
        mapeoIdentificadores.put(8, 25);  // Gestión Económica y Patrimonial
        mapeoIdentificadores.put(9, 26);  // Declaraciones y comunicaciones de los interesados
        mapeoIdentificadores.put(10, 27); // Inspectora
        mapeoIdentificadores.put(11, 28); // Premios
        mapeoIdentificadores.put(12, 29); // Prestaciones
        mapeoIdentificadores.put(13, 2);  // Recursos Humanos
        mapeoIdentificadores.put(14, 30); // Registros y Censos
        mapeoIdentificadores.put(15, 31); // Responsabilidad patrimonial y otras solicitudes de indemnización
        mapeoIdentificadores.put(16, 32); // Revisión de Actos administrativos y Recursos
        mapeoIdentificadores.put(17, 14); // Sancionador
        mapeoIdentificadores.put(18, 33); // Sugerencias, Quejas, Denuncias e Información a los ciudadanos
        mapeoIdentificadores.put(19, 3);  // Tributario

        // Busca el identificador en el nuevo mapeo
        Integer identificadorNuevo = mapeoIdentificadores.get(idTipoProcedimiento);

        // Si se encuentra, devuelve el identificador en la nueva lista
        if (identificadorNuevo != null) {
            return identificadorNuevo;
        }

        // Si no se encuentra se devolverá 0 (Pruebas) para indicar que no se encontró ningún mapeo correspondiente en la nueva lista.
        return 0;
    }

}