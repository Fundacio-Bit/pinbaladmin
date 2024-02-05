package org.fundaciobit.pinbaladmin.logic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsConfiguration;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.ejb.DocumentService;
import org.fundaciobit.pinbaladmin.ejb.FitxerService;
import org.fundaciobit.pinbaladmin.ejb.SolicitudEJB;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.dto.SolicitudDTO;
import org.fundaciobit.pinbaladmin.logic.utils.FileInfo;
import org.fundaciobit.pinbaladmin.logic.utils.PdfDownloader;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailAttachmentInfo;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.utils.commons.GregorianCalendars;
import org.hibernate.Hibernate;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;

/**
 * 
 * @author anadal
 * @author areus
 */
@Stateless(name = "SolicitudLogicaEJB")
//@SecurityDomain("seycon")
@PermitAll
public class SolicitudLogicaEJB extends SolicitudEJB implements SolicitudLogicaService {

    protected static final long TRANSACTION_TIMEOUT_IN_SEC = 180;
    protected static final long TRANSACTION_EXIT_IN_MILI = (TRANSACTION_TIMEOUT_IN_SEC * 2 / 3) * 1000;

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

//    @Override
//    @PermitAll
//    public void updateCAID(Long soliID, String incidencia, String seguiment) throws I18NException {
//
//        SolicitudJPA soli = this.findByPrimaryKey(soliID);
//
//        soli.setTicketAssociat(incidencia);
//        soli.setTicketNumeroSeguiment(seguiment);
//
//        this.update(soli);
//    }

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

            //Afegim event de Solicitud Creada
            java.lang.Long _incidenciaTecnicaID_ = null;
            java.lang.Long _solicitudID_ = soliID;

            boolean isEstatal = soli.getEntitatEstatal() != null;

            String descripcio = "Solicitud " + (isEstatal ? "estatal" : "local") + " creada correctament";

            java.sql.Timestamp _dataEvent_ = soli.getDataInici();
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_CONTACTE;
            java.lang.String _persona_ = soli.getCreador();
            boolean _noLlegit_ = false;

            java.lang.String _caidIdentificadorConsulta_ = null;
            java.lang.String _caidNumeroSeguiment_ = null;

            java.lang.String _destinatari_ = soli.getPersonaContacte();
            java.lang.String _destinatariEmail_ = soli.getPersonaContacteEmail();

            eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_, _destinatari_,
                    _destinatariEmail_, descripcio, null, _noLlegit_, _caidIdentificadorConsulta_,
                    _caidNumeroSeguiment_);

            // Afegir Documents per cada Solicitud
            for (EmailAttachmentInfo attach : attachs) {

                if (attach != xlsx) {

                    Fitxer attachFile = createFile(fitxerEjb, attach.getFileName(), attach.getContentType(), null,
                            attach.getData());
                    Long tipus = Constants.DOCUMENT_SOLICITUD_ALTRES;

                    DocumentJPA doc = new DocumentJPA(attach.getFileName(), attachFile.getFitxerID(), null, null,
                            tipus);

                    documentEjb.create(doc);

                    DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), soliID);

                    documentSolicitudLogicaEjb.create(ds);

                    java.lang.String _comentari_ = "Afegit fitxer";

                    eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_,
                            _destinatari_, _destinatariEmail_, _comentari_, attachFile.getFitxerID(), _noLlegit_,
                            _caidIdentificadorConsulta_, _caidNumeroSeguiment_);
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

        config.setFinalidad("Solicitar autorización procedimiento");
        config.setIdentificadorSolicitante("S0711001H");
        config.setUnidadTramitadora("Fundacio BIT");

        config.setCodProcedimiento(Configuracio.getApiPinbalCodiProcediment());

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
    public es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta altaSolicitudApiPinbal(ScspTitular titular, ScspFuncionario funcionario, es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud solicitud)
            throws Exception {

        PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(getPinbalAdminSolicitudsConfiguration(TipusCridada.ALTA));
        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta respuesta = api.altaSolicitudPinbalApi(solicitud, titular, funcionario);

        return respuesta;
    }
    
    @Override
    public es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta modificacioSolicitudApiPinbal(
            ScspTitular titular, ScspFuncionario funcionario,
            es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud solicitud) throws Exception {

        PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(getPinbalAdminSolicitudsConfiguration(TipusCridada.MODIFICACIO));
        log.info("Estamos en EJB. Llamamos al metodo de API");
        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta respuesta = api.modificacioSolicitudPinbalApi(solicitud, titular, funcionario);

        return respuesta;
    }

    @Override
    public es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud getDadesAltaSolicitudApiPinbal(Long solicitudID, Properties prop) throws Exception {

        SolicitudJPA soli = this.findByPrimaryKey(solicitudID);
        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud solicitud = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud();

        String asunto = "Alta Servicios. Codigo Solicitud: " + soli.getProcedimentCodi();
        solicitud.setAsunto(asunto);

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contactos contactos = getContactosAlta(prop);
        solicitud.setContactos(contactos);
        
        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Procedimiento proc = getProcedimientoAlta(soli);
        solicitud.setProcedimiento(proc);
        return solicitud;
    }

    @Override
    public es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud getDadesModificarSolicitudApiPinbal(Long solicitudID, Properties prop) throws Exception {

        SolicitudJPA soli = this.findByPrimaryKey(solicitudID);
        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud solicitud = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud();

        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contactos contactos = getContactosModificacio(prop);
        solicitud.setContactos(contactos);
        
        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Procedimiento proc = getProcedimientoModificacio(soli);
        solicitud.setProcedimiento(proc);
        return solicitud;
    }

    
    private es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contactos getContactosAlta(Properties prop) {

        String base = "FORMULARIO.DATOS_SOLICITUD.";

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contactos contactos = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contactos();

        // Contacto Aut
        String contactoAutApe1 = prop.getProperty(base + "APE1SECD");
        String contactoAutApe2 = prop.getProperty(base + "APE2SECD");
        String contactoAutMail = prop.getProperty(base + "MAILSECD");
        String contactoAutNombre = prop.getProperty(base + "NOMBRESECD");
        String contactoAutTelefon = prop.getProperty(base + "TELEFONOSECD");

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contacto contactoAut = createContactoAlta(contactoAutApe1, contactoAutApe2, contactoAutMail, contactoAutNombre,
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

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contacto contactoAud = createContactoAlta(contactoAudApe1, contactoAudApe2, contactoAudMail, contactoAudNombre,
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

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contacto contactoTec = createContactoAlta(contactoTecApe1, contactoTecApe2, contactoTecMail, contactoTecNombre,
                contactoTecTelefon);

        if (contactoTec != null) {
            contactos.getContacto().add(contactoTec);
        }

        return contactos;
    }

    private es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contacto createContactoAlta(String contactoApe1, String contactoApe2, String contactoMail,
            String contactoNombre, String contactoTelefono) {
        
        if (contactoApe1 != null && contactoMail != null && contactoNombre != null && contactoTelefono != null) {
            es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contacto contacto = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contacto();
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
    
    public es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Procedimiento getProcedimientoAlta(SolicitudJPA soli) throws Exception {

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
        XMLGregorianCalendar _FechaCaducidad = GregorianCalendars.timestampToXMLGregorianCalendar(dataCaducitat); //parseTimestampToXMLGregorian(dataCaducitat);

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
//                FitxerJPA fitxer = document.getDocument().getFitxerOriginal();
//                String desc = "Excel de serveis i procediments";
//                String tipo = "EXCEL DE SERVICIOS";
//                docsAuth.add(new DocAuthInfo(fitxer, desc, tipo)); 

            } else if (tipus == Constants.DOCUMENT_SOLICITUD_CONSENTIMENT) {
                consentiment = document.getDocument().getFitxerOriginal(); // Document consentiment
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

        
        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Procedimiento proc = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Procedimiento();
        proc.setAutomatizado(_Automatizado);
        proc.setClaseTramite(_ClaseTramite);
        proc.setCodigo(_Codigo);
        proc.setDescripcion(_Descripcion);
        proc.setNombre(_Nombre);
        proc.setObservaciones(_Observaciones);
        proc.setPeriodico(_Periodico);
        proc.setPeticionesEstimadas(_PeticionesEstimadas);
        proc.setFechaCaducidad(_FechaCaducidad);

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DocumentosAutorizacion _DocumentosAutorizacion = getDocsAutorizacionAlta(docsAuth);
        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Consentimiento _Consentimiento = getConsentimientoAlta(soli.getSolicitudServeis(), consentiment);

        proc.setDocumentosAutorizacion(_DocumentosAutorizacion);
        proc.setConsentimiento(_Consentimiento);
        
        if (_DocumentosAutorizacion == null|| _Consentimiento == null) {
            return proc;
        }
        
        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Servicios _Servicios = getServiciosAlta(soli);
        proc.setServicios(_Servicios);

        return proc;
    }

//    private XMLGregorianCalendar parseTimestampToXMLGregorian(Timestamp caducitatdata) {
//        if (caducitatdata == null) {
//            return null;
//        }
//        XMLGregorianCalendar cal = null;
//        try {
//
//            LocalDateTime ldt = caducitatdata.toLocalDateTime();
//            cal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
//
//            cal.setYear(ldt.getYear());
//            cal.setMonth(ldt.getMonthValue());
//            cal.setDay(ldt.getDayOfMonth());
//            cal.setHour(ldt.getHour());
//            cal.setMinute(ldt.getMinute());
//            cal.setSecond(ldt.getSecond());
//            cal.setFractionalSecond(new BigDecimal("0." + ldt.getNano()));
//
//        } catch (DatatypeConfigurationException e) {
//
//            e.printStackTrace();
//        }
//        return cal;
//    }

    private es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Consentimiento getConsentimientoAlta(Set<SolicitudServeiJPA> set, Fitxer fitxerConsentiment) throws Exception {

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Consentimiento cons = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Consentimiento();

        String consentiment = null;
        String tipo = null;
        String enlace = null;

        final String PINBAL_CONSENTIMENT_LLEI = "Ley";
        final String PINBAL_CONSENTIMENT_SI = "Si";
        final String PINBAL_CONSENTIMENT_NOOP= "NoOpo";
        
        for (SolicitudServeiJPA solSerJPA : set) {
            consentiment = solSerJPA.getConsentiment(); //si, llei, noop
            if (consentiment != null) {
                if (consentiment.equals(Constants.CONSENTIMENT_TIPUS_LLEI)) {
                    cons.setTipo(PINBAL_CONSENTIMENT_LLEI);
                    log.info("CONS: "  + PINBAL_CONSENTIMENT_LLEI);
                    return cons;
                } else {
                    tipo = consentiment.equals(Constants.CONSENTIMENT_TIPUS_SI) ? PINBAL_CONSENTIMENT_SI : PINBAL_CONSENTIMENT_NOOP;
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
                es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Consentimiento.Documento doc = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Consentimiento.Documento();

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
            log.info("CONS: No tenim fitxer de consentiment.");
            return null;
        }
    }

    private es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DocumentosAutorizacion getDocsAutorizacionAlta(Set<DocAuthInfo> documents) throws Exception {

        log.info("Documents de la solicitud: " + documents.size());
        
        if (documents.size() == 0) {
            return null;
        }
        
        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DocumentosAutorizacion docs = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DocumentosAutorizacion();

        for (DocAuthInfo docInfo : documents) {
            FitxerJPA fitxer = docInfo.getFitxer();

            String nom = fitxer.getNom();
            String descripcio = docInfo.getDescripcio();
            String tipo = docInfo.getTipo();

            Long fitxerID = fitxer.getFitxerID();
            File file = FileSystemManager.getFile(fitxerID);
            byte[] contingut = FileUtils.readFromFile(file);

            es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DocumentoAutorizacion docAut = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DocumentoAutorizacion();
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

    private es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Servicios getServiciosAlta(SolicitudJPA soli) throws Exception {

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Servicios servicios = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Servicios();
        Set<SolicitudServeiJPA> serveisDeLaSolicitud = soli.getSolicitudServeis();

        for (SolicitudServeiJPA ss : serveisDeLaSolicitud) {
            boolean balear = ss.getServei().getEntitatServei().isBalears();
            boolean estatPendentMadrid = ss.getEstatSolicitudServeiID() == 40L; //ESTATS_SOLICITUD_SERVEI: 40L -> Pendent d'autoritzar
            
            if (!balear && estatPendentMadrid) {

                es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Servicio servicio = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Servicio();

                es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Norma norma = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Norma();
                {
                    String normaLegal = ss.getNormaLegal();

                    es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Norma.Documento docNorma = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Norma.Documento();
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

                    es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Articulos articulos = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Articulos();
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

                es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Normas normas = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Normas();
                normas.getNorma().add(norma);

                servicio.setCodigoCertificado(codigoCertificado);
                servicio.setNormas(normas);

                servicios.getServicio().add(servicio);
            }
        }

        return servicios;
    }

    
    
    
    
    
    
    private es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contactos getContactosModificacio(Properties prop) {

        String base = "FORMULARIO.DATOS_SOLICITUD.";

        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contactos contactos = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contactos();

        // Contacto Aut
        String contactoAutApe1 = prop.getProperty(base + "APE1SECD");
        String contactoAutApe2 = prop.getProperty(base + "APE2SECD");
        String contactoAutMail = prop.getProperty(base + "MAILSECD");
        String contactoAutNombre = prop.getProperty(base + "NOMBRESECD");
        String contactoAutTelefon = prop.getProperty(base + "TELEFONOSECD");

        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contacto contactoAut = createContactoModificacio(contactoAutApe1, contactoAutApe2, contactoAutMail, contactoAutNombre,
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

        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contacto contactoAud = createContactoModificacio(contactoAudApe1, contactoAudApe2, contactoAudMail, contactoAudNombre,
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

        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contacto contactoTec = createContactoModificacio(contactoTecApe1, contactoTecApe2, contactoTecMail, contactoTecNombre,
                contactoTecTelefon);

        if (contactoTec != null) {
            contactos.getContacto().add(contactoTec);
        }

        return contactos;
    }

    private es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contacto createContactoModificacio(String contactoApe1, String contactoApe2, String contactoMail,
            String contactoNombre, String contactoTelefono) {
        
        if (contactoApe1 != null && contactoMail != null && contactoNombre != null && contactoTelefono != null) {
            es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contacto contacto = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contacto();
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

    public es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Procedimiento getProcedimientoModificacio(SolicitudJPA soli) throws Exception {

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
        
        XMLGregorianCalendar _FechaCaducidad = GregorianCalendars.timestampToXMLGregorianCalendar(dataCaducitat);

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
                consentiment = document.getDocument().getFitxerOriginal(); // Document consentiment
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
        
        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Procedimiento proc = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Procedimiento();
        proc.setAutomatizado(_Automatizado);
        proc.setClaseTramite(_ClaseTramite);
        proc.setCodigo(_Codigo);
        proc.setDescripcion(_Descripcion);
        proc.setNombre(_Nombre);
        proc.setObservaciones(_Observaciones);
        proc.setPeriodico(_Periodico);
        proc.setPeticionesEstimadas(_PeticionesEstimadas);
        proc.setFechaCaducidad(_FechaCaducidad);

        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DocumentosAutorizacion _DocumentosAutorizacion = getDocsAutorizacionModificacio(docsAuth);
        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Consentimiento _Consentimiento = getConsentimientoModificacio(soli.getSolicitudServeis(), consentiment);

        proc.setDocumentosAutorizacion(_DocumentosAutorizacion);
        proc.setConsentimiento(_Consentimiento);
        
        if (_DocumentosAutorizacion == null|| _Consentimiento == null) {
            return proc;
        }
        
        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Servicios _Servicios = getServiciosModificacio(soli);
        proc.setServicios(_Servicios);

        return proc;
    }

    private es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Consentimiento getConsentimientoModificacio(Set<SolicitudServeiJPA> set, Fitxer fitxerConsentiment) throws Exception {
        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Consentimiento cons = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Consentimiento();

        String consentiment = null;
        String tipo = null;
        String enlace = null;

        final String PINBAL_CONSENTIMENT_LLEI = "Ley";
        final String PINBAL_CONSENTIMENT_SI = "Si";
        final String PINBAL_CONSENTIMENT_NOOP= "NoOpo";
        
        for (SolicitudServeiJPA solSerJPA : set) {
            consentiment = solSerJPA.getConsentiment(); //si, llei, noop
            if (consentiment != null) {
                if (consentiment.equals(Constants.CONSENTIMENT_TIPUS_LLEI)) {
                    cons.setTipo(PINBAL_CONSENTIMENT_LLEI);
                    log.info("CONS: "  + PINBAL_CONSENTIMENT_LLEI);
                    return cons;
                } else {
                    tipo = consentiment.equals(Constants.CONSENTIMENT_TIPUS_SI) ? PINBAL_CONSENTIMENT_SI : PINBAL_CONSENTIMENT_NOOP;
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
                es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Consentimiento.Documento doc = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Consentimiento.Documento();

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
            log.info("CONS: No tenim fitxer de consentiment.");
            return null;
        }

    }

    private es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DocumentosAutorizacion getDocsAutorizacionModificacio(Set<DocAuthInfo> documents) throws Exception {

        log.info("Documents de la solicitud: " + documents.size());
        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DocumentosAutorizacion docs = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DocumentosAutorizacion();

        for (DocAuthInfo docInfo : documents) {
            FitxerJPA fitxer = docInfo.getFitxer();

            String nom = fitxer.getNom();
            String descripcio = docInfo.getDescripcio();
            String tipo = docInfo.getTipo();

            Long fitxerID = fitxer.getFitxerID();
            File file = FileSystemManager.getFile(fitxerID);
            byte[] contingut = FileUtils.readFromFile(file);

            es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DocumentoAutorizacion docAut = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DocumentoAutorizacion();
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

    private es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Servicios getServiciosModificacio(SolicitudJPA soli) throws Exception {

        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Servicios servicios = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Servicios();
        Set<SolicitudServeiJPA> serveisDeLaSolicitud = soli.getSolicitudServeis();

        for (SolicitudServeiJPA ss : serveisDeLaSolicitud) {
            boolean balear = ss.getServei().getEntitatServei().isBalears();
            boolean estatPendentMadrid = ss.getEstatSolicitudServeiID() == 40L; //ESTATS_SOLICITUD_SERVEI: 40L -> Pendent d'autoritzar
            
            if (!balear && estatPendentMadrid) {
                es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Servicio servicio = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Servicio();

                es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Norma norma = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Norma();
                {
                    String normaLegal = ss.getNormaLegal();

                    es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Norma.Documento docNorma = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Norma.Documento();
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

                    es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Articulos articulos = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Articulos();
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

                es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Normas normas = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Normas();
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

    @Override
    public Solicitud getSolicitudFromTramitID(String ticketGFE) throws Exception {
        
        Long tramitID = HibernateFileUtil.decryptFileID(ticketGFE);
        
        final String likeStr = "%tramitid[" + tramitID + "]%";

        Where w = SolicitudFields.NOTES.like(likeStr);
        
        log.info("Where: "  + w.toSQL());
        
        List<Solicitud> llistat = this.select(w);
        int size = llistat.size();
        
        if (size == 1) {
            return llistat.get(0);
        }else if (size == 0) {
            throw new Exception("No s'ha trobat la solicitud [" + tramitID + "]a la BBDD");
        } else {
            throw new Exception("Hi ha mes d'una solicitud amb aquest codi tramit");
        }
    }
//    
//    /**
//     * Funció que s'executa cada vespre a les 5:00 i actualitza l'estat de les solicituds a pinbal
//     */
//    @TransactionTimeout(value = TRANSACTION_TIMEOUT_IN_SEC)
//    @Schedule(hour = "14", minute = "56",  persistent = false)
//    protected void eliminarFitxersSignatsDeLocal() {
//        log.info("Comença eliminarFitxersSignatsDeLocal()");
//
//        long startTime = System.currentTimeMillis();
//        try {
//            //Llistat de solicituds: 
//            ScspFuncionario funcionario = new ScspFuncionario();
//
//            funcionario.setNifFuncionario("45186147W");
//            funcionario.setNombreCompletoFuncionario("Juan Pablo Trias Segura");
//            
//            ScspTitular titular = new ScspTitular();
//
//            ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.NIF;
//            titular.setTipoDocumentacion(tipoDocumentacion);
//            titular.setDocumentacion("45186147W");
//            titular.setNombre("Juan Pablo");
//            titular.setApellido1("Trias");
//            titular.setApellido2("Segura");
//            titular.setNombreCompleto("Juan Pablo Trias Segura");
//
//            
//            Calendar cal = Calendar.getInstance();
//            cal.setTimeInMillis(System.currentTimeMillis());
//            cal.add(Calendar.MONTH, -5);
//            
//            List<Solicitud> solicituds = this.select(Where.AND(
//                SolicitudFields.DATAINICI.greaterThan(new Timestamp(cal.getTimeInMillis()))),
//                SolicitudFields.ORGANID.isNotNull()
//            );
//            
//            log.info("Solicituds a procesasr: " + solicituds.size());
//            for (Solicitud solicitud : solicituds) {
//                Consulta consulta = new Consulta();
//                consulta.setCodigoProcedimiento(solicitud.getProcedimentCodi());
//                
//                try {
//                    Retorno retorno = this.consultaEstatApiPinbal(titular, funcionario, consulta);
//                    if (retorno.getEstado().getCodigoEstado().equals("2")) {
//                        log.info("estado procedimiento " + solicitud.getProcedimentCodi() + ": "
//                                + Constants.ESTAT_PINBAL_NO_SOLICITAT + " - " + "no solicitat");
//                        solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_NO_SOLICITAT);
//                    } else if (retorno.getEstado().getCodigoEstado().equals("0")) {
//                        EstadoProcedimiento estado = retorno.getProcedimiento().getEstadoProcedimiento();
//                        log.info("estado procedimiento " + solicitud.getProcedimentCodi() + ": " + estado.getEstado()
//                                + " - " + estado.getDescripcion());
//
//                        solicitud.setEstatpinbal(estado.getEstado());
//                    }
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    log.warn("Error solicitud " + solicitud.getProcedimentCodi() + ": " + e.getMessage());
//                    solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_ERROR);
//                }
//                
//                this.update(solicitud);
//                
//                //El Timeout son 3 minuts. Si el CRON s'executa durant 2 min, surt del for i acaba la funció.
//                if ((System.currentTimeMillis() - startTime) > TRANSACTION_EXIT_IN_MILI) {
//                    log.warn("Timeout.");
//                    break;
//                }
//            }
//
//        } catch (I18NException e) {
//            final String msg = "Error";
//            log.error(msg, e);
//        }
//
//        long endTime = System.currentTimeMillis();
//        log.info("Total time: " + (endTime - startTime));
//        log.info("Acaba eliminarFitxersSignatsDeLocal()");
//    }
}