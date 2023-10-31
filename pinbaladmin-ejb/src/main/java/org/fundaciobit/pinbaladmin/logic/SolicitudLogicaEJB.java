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
import java.util.Iterator;
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
import org.fundaciobit.pinbaladmin.ejb.DocumentService;
import org.fundaciobit.pinbaladmin.ejb.FitxerService;
import org.fundaciobit.pinbaladmin.ejb.SolicitudEJB;
import org.fundaciobit.pinbaladmin.logic.dto.SolicitudDTO;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Articulos;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Consentimiento;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Contacto;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Contactos;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.DocumentoAutorizacion;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.DocumentosAutorizacion;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Incidencia;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Norma;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Normas;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Procedimiento;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Respuesta;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Servicio;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Servicios;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Solicitud;


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

                    DocumentJPA doc = new DocumentJPA(attach.getFileName(), attachFile.getFitxerID(), null, null);

                    documentEjb.create(doc);

                    DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), soliID);

                    documentSolicitudLogicaEjb.create(ds);

                }
            }
        }
    }

    protected PinbalAdminSolicitudsConfiguration getPinbalAdminSolicitudsConfiguration() throws Exception {

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
        config.setCodigoCertificado("SVDPIDSOLAUTWS01");

        return config;
    }

    @Override
    public Respuesta altaSolicitudApiPinbal(ScspTitular titular, ScspFuncionario funcionario, Solicitud solicitud)
            throws Exception {

        log.info("EJB: consulta: " + solicitud.getConsulta());
        
        PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(getPinbalAdminSolicitudsConfiguration());
        Respuesta respuesta = api.crearSolicitud(solicitud, titular, funcionario);

        return respuesta; //"Alta tramitada correctament"; //incidencia.getEmail();

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

        String contactoAutApe1 = prop.getProperty(base + "APE1SECD");
        String contactoAutApe2 = prop.getProperty(base + "APE2SECD");
        String contactoAutMail = prop.getProperty(base + "MAILSECD");
        String contactoAutNombre = prop.getProperty(base + "NOMBRESECD");
        String contactoAutTelefon = prop.getProperty(base + "TELEFONOSECD");

        contactos.getContacto().add(createContacto(contactoAutApe1, contactoAutApe2, contactoAutMail, null,
                contactoAutNombre, contactoAutTelefon));

        String contactoAudApe1 = prop.getProperty(base + "APE1SECE");
        String contactoAudApe2 = prop.getProperty(base + "APE2SECE");
        String contactoAudMail = prop.getProperty(base + "MAILSECE");
        String contactoAudNombre = prop.getProperty(base + "NOMBRESECE");
        String contactoAudTelefon = prop.getProperty(base + "TELEFONOSECE");

        contactos.getContacto().add(createContacto(contactoAudApe1, contactoAudApe2, contactoAudMail, null,
                contactoAudNombre, contactoAudTelefon));

        String contactoTecApe1 = prop.getProperty(base + "APE1SECF");
        String contactoTecApe2 = prop.getProperty(base + "APE2SECF");
        String contactoTecMail = prop.getProperty(base + "MAILSECF");
        String contactoTecNombre = prop.getProperty(base + "NOMBRESECF");
        String contactoTecTelefon = prop.getProperty(base + "TELEFONOSECF");

        contactos.getContacto().add(createContacto(contactoTecApe1, contactoTecApe2, contactoTecMail, null,
                contactoTecNombre, contactoTecTelefon));

        return contactos;
    }

    private Contacto createContacto(String contactoApe1, String contactoApe2, String contactoMail, String contactoFax,
            String contactoNombre, String contactoTelefono) {
        Contacto contacto = new Contacto();
        contacto.setApellido1(contactoApe1);
        contacto.setApellido2(contactoApe2);
        contacto.setEmail(contactoMail);
        contacto.setFax(contactoFax);
        contacto.setNombre(contactoNombre);
        contacto.setTelefono(contactoTelefono);
        return contacto;
    }

    public Procedimiento getProcedimiento(SolicitudJPA soli) throws Exception {

        String _Automatizado = "N";// soli.getAutomatizado();
        String _Periodico = "N"; //soli.getPeriodico();

        String petsDia = "40"; //soli.getPetsDia();
        Integer _PeticionesEstimadas = Integer.parseInt(petsDia);

        //        String tipusProc = soli.getProcedimentTipus();
        //        Integer _ClaseTramite = Integer.parseInt(tipusProc);
        Integer _ClaseTramite = 0; //Pruebas
        //        Integer [] tipusAceptats = {0, 2, 3, 14, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 99};
        //
        //        for (Integer element : tipusAceptats ) {
        //            if (element == _ClaseTramite ) {
        //                tipusProcediment = element;
        //                break;
        //            }
        //        }

        String _Codigo = soli.getProcedimentCodi();
        String _Nombre = soli.getProcedimentNom();
        String _Descripcion = soli.getCodiDescriptiu();
        if (_Nombre.equals(_Descripcion)) {
            _Descripcion = "_" + _Descripcion;
        }
        String _Observaciones = null;//soli.getNotes();

        Timestamp dataCaducitat = soli.getDataFi();
        XMLGregorianCalendar _FechaCaducidad = parseTimestampToXMLGregorian(dataCaducitat);

        //Hay que modificar el tramite. Hay que pedir el tipo de consentimiento y el pdf/enlace en TramitH. 
        // Y pasar en enlace por el xml
        Fitxer consentiment = soli.getDocumentSolicitud();
        
        
        Consentimiento _Consentimiento = getConsentimiento(soli.getSolicitudServeis(), consentiment);

        /*
         * Aquí son el excel de servicios y el documento PDF del Director General. 
         * Falta también aclarar lo de CONVENIO y FORMULARIO DE AUTORIZACION 
         * 
         * Solicitud -> DocumentSolicitud -> Document -> FitxerID
         * 
         * Fitxer[] docAut = { soli.getDocumentSolicitud(), soli.getDocumentSolicitud() }; 
         * DocumentosAutorizacion _DocumentosAutorizacion = getDocsAutorizacion(docAut); 
         */

        Set<FitxerJPA> docsAuth = new HashSet<FitxerJPA>();
        for (DocumentSolicitudJPA document : soli.getDocumentSolicituds()) {
            
            int tipus = 0; // document.getTipus();
            switch (tipus) {
                case Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF:
                    docsAuth.add(document.getDocument().getFitxerFirmat()); // Formulari PDF Firmat
                break;
                
                case Constants.DOCUMENT_SOLICITUD_EXCEL_SERVEIS:
                    docsAuth.add(document.getDocument().getFitxerOriginal()); // Excel de Serveis
                break;
                
                default:
                    FitxerJPA original = document.getDocument().getFitxerOriginal();
                    if (original.getMime().equals("application/pdf")) {
                        docsAuth.add(original);
                    }
                break;
            }
        }
        
        DocumentosAutorizacion _DocumentosAutorizacion = getDocsAutorizacion(docsAuth );

        //Ahora está cogiendo los datos del xml. Luego tendrá que coger los datos solicitud-servei.
        //También habrá que modificar el tramite y pedir los PDF de las normas en tramitI
        Servicios _Servicios = getServicios(soli, consentiment);

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

        //        Procedimiento proc = getProcedimiento(_Automatizado, _ClaseTramite, _Codigo, _Descripcion, _Nombre,
        //                _Observaciones, _Periodico, _PeticionesEstimadas, _FechaCaducidad, _Consentimiento,
        //                _DocumentosAutorizacion, _Servicios);
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
        String tipo = null; //prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CONSENTIMIENTO");* //Si, Ley, NoOpo
        String enlace = null; //prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CONSENTIMIENTOENLACE");*

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

        //Si arriba aqui es que tots els serveis necessiten document
        try {
            Consentimiento.Documento doc = new Consentimiento.Documento();

            Long consentimentID = fitxerConsentiment.getFitxerID();

            String nom = fitxerConsentiment.getNom();
            String descripcio = "Fitxer de consentiment. IDFitxer:" + consentimentID;

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
    }

    private DocumentosAutorizacion getDocsAutorizacion(Set<FitxerJPA> fitxers) {

        log.info("Documents de la solicitud: " + fitxers.size());
        DocumentosAutorizacion docs = new DocumentosAutorizacion();
        
        for (FitxerJPA fitxer : fitxers) {
            try {
                String nom = fitxer.getNom();
                String descripcio = "Formulari d'atorització firmar per el Director General";
//                String descripcio = "Excel de Procediments i serveis";
                String tipo = "FORMULARIO DE AUTORIZACION";
                
                Long fitxerID = fitxer.getFitxerID();
                File file = FileSystemManager.getFile(fitxerID);
                byte[] contingut = FileUtils.readFromFile(file);
                
                DocumentoAutorizacion docAut = new DocumentoAutorizacion();
                log.info("AUT: " + nom + " : " + contingut.length + " bytes");
                
                docAut.setContenido(contingut);
                docAut.setDescripcion(descripcio);
                docAut.setNombre(nom);
                docAut.setTipo(tipo);
                
                docs.getDocumentoAutorizacion().add(docAut);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return docs;
    }

    private Servicios getServicios(SolicitudJPA soli, Fitxer fitcheroPrueba) throws Exception {

        //        SolicitudJPA soli = this.findByPrimaryKey(null);
        //
        //        List<SolicitudServei> serveis = solicitudServeiLogicaEJB
        //                .select(SolicitudServeiFields.SOLICITUDID.equal(soli.getSolicitudID()));

        Servicios servicios = new Servicios();
        Set<SolicitudServeiJPA> serveisDeLaSolicitud = soli.getSolicitudServeis();

        for (SolicitudServeiJPA ss : serveisDeLaSolicitud) {
            Servicio servicio = new Servicio();
            {
                String codigoCertificado = ss.getServei().getCodi();

                Norma norma = new Norma();
                {
                    String normaLegal = ss.getNormaLegal(); //  prop.getProperty(base + i + ".NORMALEGAL"); //tramitI.getNorma();
                    Norma.Documento docNorma = new Norma.Documento();
                    {
                        String enlace = ss.getEnllazNormaLegal(); //  prop.getProperty(base + i + ".ENLACENOR");// tramitI.getUrlnorma();
                        String descripcio = "Descripció Norma " + codigoCertificado;
                        String nom = codigoCertificado + "_" + fitcheroPrueba.getNom();
                        Long consentimentID = fitcheroPrueba.getFitxerID();

                        File fileConsentiment = FileSystemManager.getFile(consentimentID);
                        byte[] contingut = FileUtils.readFromFile(fileConsentiment);

                        log.info("NORMA: " + nom + " : " + contingut.length + " bytes");
                        log.info("url norma: " + enlace);

                        docNorma.setContenido(contingut);
                        docNorma.setDescripcion(descripcio);
                        docNorma.setEnlace(enlace);
                        docNorma.setNombre(nom);
                    }

                    Articulos articulos = new Articulos();
                    String articulosString = ss.getArticles(); //  prop.getProperty(base + i + ".ARTICULOS");
                    String[] articulosArray = articulosString.split(",");

                    for (String articulo : articulosArray) {
                        articulos.getArticulo().add(articulo);
                    }

                    norma.setNormaLegal(normaLegal);
                    norma.setArticulos(articulos);
                    norma.setDocumento(docNorma);
                }

                Normas normas = new Normas();
                normas.getNorma().add(norma);

                servicio.setCodigoCertificado(codigoCertificado);
                servicio.setNormas(normas);
            }
            servicios.getServicio().add(servicio);

        }

        return servicios;
    }

}