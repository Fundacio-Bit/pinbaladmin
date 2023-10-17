package org.fundaciobit.pinbaladmin.back.test;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Articulos;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Consentimiento;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Contacto;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Contactos;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.DatosEspecificos;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.DocumentoAutorizacion;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.DocumentosAutorizacion;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Error;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Errores;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Estado;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Incidencia;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Norma;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Norma.Documento;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Normas;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Procedimiento;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Respuesta;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Servicio;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Servicios;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Solicitud;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsConfiguration;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.PinbalAdminDaoManager;
import org.fundaciobit.pinbaladmin.model.bean.SolicitudBean;
import org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitBDadesSoliManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitCDadesCesiManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitDCteAutManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitECteAudManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitFCteTecManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitGDadesTitManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitHProcManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitIServManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitJConsentManager;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
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
import org.fundaciobit.pinbaladmin.persistence.PinbalAdminJPADaoManagers;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;

/**
 * 
 * @author anadal
 *
 */

/*
Al final son dos partes, una que hace el funcionario y otra que hacemos nosotros.

En la que hacemos nosotros, y luego enviamos a Madrid, tenemos que añadir:
Asunto
Consulta
Observaciones del procedimiento
generar los documentos autorización

El resto es cosa del funcionario.

Añadir campos a H.Procedimiento:
Automatizado
Periodico

Añadir incluir un fichero de consentimiento si el servicio lo solicita. 

El documento de la norma tiene que ser solo el enlace, porque todo está publicado
 */

public class TestPinbalAdminFormulari {

    @Test
    public void main() {

        FileSystemManager.setFilesPath(new File("D:\\Projectes\\pinbaladmin-files\\files"));
        cridadaAPICrearSolicitudMadridAmbSolicitudID();
    }

    //    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    //    protected TramitAPersAutLogicaService tramitAEjb;
    //
    //    @EJB(mappedName = TramitBDadesSoliLogicaService.JNDI_NAME)
    //    protected TramitBDadesSoliLogicaService tramitBEjb;
    //
    //    @EJB(mappedName = TramitCDadesCesiLogicaService.JNDI_NAME)
    //    protected TramitCDadesCesiLogicaService tramitCEjb;
    //
    //    @EJB(mappedName = TramitDCteAutLogicaService.JNDI_NAME)
    //    protected TramitDCteAutLogicaService tramitDEjb;
    //
    //    @EJB(mappedName = TramitECteAudLogicaService.JNDI_NAME)
    //    protected TramitECteAudLogicaService tramitEEjb;
    //
    //    @EJB(mappedName = TramitFCteTecLogicaService.JNDI_NAME)
    //    protected TramitFCteTecLogicaService tramitFEjb;
    //
    //    @EJB(mappedName = TramitGDadesTitLogicaService.JNDI_NAME)
    //    protected TramitGDadesTitLogicaService tramitGEjb;
    //
    //    @EJB(mappedName = TramitHProcLogicaService.JNDI_NAME)
    //    protected TramitHProcLogicaService tramitHEjb;
    //
    //    @EJB(mappedName = TramitIServLogicaService.JNDI_NAME)
    //    protected TramitIServLogicaService tramitIEjb;

    public TestPinbalAdminFormulari() {
    }

    private String obtenerContenidoXml(Long fitxerID) throws Exception {
        File f = FileSystemManager.getFile(fitxerID);
        byte[] xmlData = FileUtils.readFromFile(f);
        return new String(xmlData);
    }

    public void cridadaAPICrearSolicitudMadridAmbSolicitudID() {
        try {

            GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
            Gson gson = builder.create();

            byte[] soliJSONBytes = FileUtils.readFromFile(new File("solicitud.json"));
            String soliJSON = new String(soliJSONBytes);
            SolicitudBean soli = gson.fromJson(soliJSON, SolicitudBean.class);

            System.out.println("HOLA OLA " + soli.getPersonaContacte());

            Long fitxerID = soli.getSolicitudXmlID();
            String contenidoXml = obtenerContenidoXml(fitxerID);
            System.out.println(contenidoXml);

            Properties prop = ParserFormulariXML.getPropertiesFromFormulario(contenidoXml);

            String nif = prop.getProperty("FORMULARIO.DATOS_REGISTRO.NIF");
            System.out.println(nif);

            ScspTitular titular = getTitular(prop);
            ScspFuncionario funcionario = getFuncionari(prop);
            Solicitud solicitud = getSolicitud(prop);

            //Procedimiento
            Procedimiento proc = getProcedimiento(prop, soli);
            solicitud.setProcedimiento(proc);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            ObjectWriter prettyWriter = objectMapper.writerWithDefaultPrettyPrinter();
            //            String jsonTit = prettyWriter.writeValueAsString(titular);
            //            String jsonFunc = prettyWriter.writeValueAsString(funcionario);
            //            String jsonSoli = prettyWriter.writeValueAsString(solicitud);
            String jsonProc = prettyWriter.writeValueAsString(proc);

            //            System.out.println(jsonTit);
            //            System.out.println(jsonFunc);
            //            System.out.println(jsonSoli);
            System.out.println(jsonProc);

            //            PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(getPinbalAdminSolicitudsConfiguration());
            //            Incidencia incidencia = api.crearSolicitud(solicitud, titular, funcionario);

        } catch (I18NException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ScspFuncionario getFuncionari(Properties prop) {

        ScspFuncionario funcionario = new ScspFuncionario();

        String nif = prop.getProperty("FORMULARIO.DATOS_REGISTRO.NIF");
        String fullName = prop.getProperty("FORMULARIO.DATOS_REGISTRO.NOMBRECOMPLETO");

        funcionario.setNifFuncionario(nif);
        funcionario.setNombreCompletoFuncionario(fullName);
        return funcionario;
    }

    private ScspTitular getTitular(Properties prop) {

        ScspTitular titular = new ScspTitular();

        ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.DNI;
        String documentacion = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NIFSECG");
        String nombre = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBRESECG");
        String ape1 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE1SECG");
        String ape2 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE2SECG");
        String fullName = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMOCULSECG");

        titular.setTipoDocumentacion(tipoDocumentacion);
        titular.setDocumentacion(documentacion);
        titular.setNombre(nombre);
        titular.setApellido1(ape1);
        titular.setApellido2(ape2);
        titular.setNombreCompleto(fullName);

        return titular;
    }

    private Solicitud getSolicitud(Properties prop) {

        //Asunto y consulta
        String asunto = null;
        String consulta = null;

        //Contactos
        Contactos contactos = getContactos(prop);

        Solicitud solicitud = new Solicitud();
        solicitud.setAsunto(asunto);
        solicitud.setConsulta(consulta);
        solicitud.setContactos(contactos);

        return solicitud;
    }

    public Procedimiento getProcedimiento(Properties prop, SolicitudBean soli) {
        String _Automatizado = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.AUTOMATIZADO");
        String _Periodico = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.PERIODICO");

        String petsDia = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.PETICIONESDIA");
        Integer _PeticionesEstimadas = Integer.parseInt(petsDia);

        String tipusProc = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.TIPOPROCEDIMIENTO");
                Integer _ClaseTramite = Integer.parseInt(tipusProc);
        //        Integer _ClaseTramite = Integer.parseInt(soli.getProcedimentTipus());
//        Integer _ClaseTramite = 13;

        String _Codigo = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CODIPROC");
        String _Descripcion = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.DESCRIPCION");
        String _Nombre = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBREPROC");
        String _Observaciones = null;
        //        String _Codigo = soli.getProcedimentCodi();
        //        String _Descripcion = soli.getCodiDescriptiu();
        //        String _Nombre = soli.getProcedimentNom();
        //        String _Observaciones = soli.getNotes();

        
        
        

        String caduca = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CADUCA");
        Timestamp dataCaducitat = null;
        if (caduca.equals("SI")) {
            dataCaducitat = soli.getDataFi();
        }
        //        Timestamp dataFi =  prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBREPROC");
        XMLGregorianCalendar _FechaCaducidad = parseTimestampToXMLGregorian(dataCaducitat);

        
        
        
        Fitxer consentiment = soli.getDocumentSolicitud();
        Consentimiento _Consentimiento = getConsentimiento(prop, consentiment);

        
        
        
        
        
        Fitxer[] docAut = { soli.getDocumentSolicitud() };
        DocumentosAutorizacion _DocumentosAutorizacion = getDocsAutorizacion(docAut);

        
        
        
        
        Servicios _Servicios = getServicios(prop);
        

        
        
        
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

    protected PinbalAdminSolicitudsConfiguration getPinbalAdminSolicitudsConfiguration() throws Exception {

        PinbalAdminSolicitudsConfiguration config = new PinbalAdminSolicitudsConfiguration();

        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("test.properties")));

        config.setUrlBase(prop.getProperty("urlbase"));
        config.setUsername(prop.getProperty("username"));
        config.setPassword(prop.getProperty("password"));

        config.setFinalidad("Baremacions per el proces d'escolaritzacio"); // getPropertyRequired(propertyBase + "finalitat"); // "Baremacions per el proces d'escolaritzacio";
        config.setIdentificadorSolicitante("S0711001H"); // getPropertyRequired(propertyBase + "identificadorsolicitant"); // "S0711001H";
        config.setUnidadTramitadora("Servei d'escolarització"); // getPropertyRequired(propertyBase + "unitattramitadora"); // "Servei d'escolarització";

        config.setCodProcedimiento("INCIDENCIES_CAID"); // getPropertyRequired(propertyBase + "codiprocediment"); // "CODSVDR_GBA_20121107";

        // TODO crec que això hauria d'estar dins de PinbalAdminSolicitudsApi
        /**
         * SVDPIDSOLAUTWS01 | Solicitud de autorizaciones en PID
        SVDPIDESTADOAUTWS01 | Servicio de estado de las autorizaciones en PID 
        SVDPIDACTPROCWS01 | Servicio de actualización de un procedimiento ya dado de alta en PID
         */
        config.setCodigoCertificado("SVDPIDSOLAUTWS01");

        return config;

    }

    //==============================================================================

    private Consentimiento getConsentimiento(Properties prop, Fitxer fitxerConsentiment) {

        Consentimiento cons = new Consentimiento();
        Consentimiento.Documento doc = new Consentimiento.Documento();

        String enlace = null;
        String tipo = "llei";

        String base = "FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID";
        int i = 1;

        String consentiment = prop.getProperty(base + i + ".CONSENTIMIENTO");
        System.out.println(consentiment);

        while (consentiment != null) {
            if (consentiment.equals("No OposiciÃ³")) {
                tipo = "noop";
                String consPublicat = prop.getProperty(base + i + ".LDECONSENTIMIENTO");
                if (consPublicat.equals("Adjunt")) {
                    enlace = null;
                    break;
                } else {
                    enlace = prop.getProperty(base + i + ".ENLACECON"); //getUrlconsentiment();
                }
            }
            i++;
            consentiment = prop.getProperty(base + i + ".CONSENTIMIENTO");
        }

        if (tipo.equals("noop")) {
            if (enlace == null) {
                if (fitxerConsentiment != null) {
                    try {
                        //   System.out.println(documentConsentiment.getFitxerID());

                        String descripcio = fitxerConsentiment.getDescripcio();
                        String nom = fitxerConsentiment.getNom();
                        Long consentimentID = fitxerConsentiment.getFitxerID();
                        File fileConsentiment = FileSystemManager.getFile(consentimentID);
                        byte[] contingut = FileUtils.readFromFile(fileConsentiment);

                        doc.setContenido(null); //contingut);
                        doc.setDescripcion(descripcio);
                        doc.setNombre(nom);

                        cons.setDocumento(doc);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //                    } catch (FileNotFoundException e) {
                    //                        e.printStackTrace();
                    //                    } catch (IOException e) {
                    //                        e.printStackTrace();
                }
            } else {
                cons.setEnlace(enlace);
            }
        }
        cons.setTipo(tipo);
        return cons;
    }

    private DocumentosAutorizacion getDocsAutorizacion(Fitxer[] documents) {

        DocumentosAutorizacion docs = new DocumentosAutorizacion();
        for (Fitxer document : documents) {
            try {

                String descripcio = document.getDescripcio();
                System.out.println(descripcio);
                String nom = document.getNom();
                String tipo = document.getMime();

                Long documentID = document.getFitxerID();
                File file = FileSystemManager.getFile(documentID);
                byte[] contingut = FileUtils.readFromFile(file);

                DocumentoAutorizacion docAut = new DocumentoAutorizacion();
                docAut.setContenido(null); //contingut);
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
    

    private Servicios getServicios(Properties prop) {
        Servicios servicios = new Servicios();

        String base = "FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID";
        int i = 1;

        String codiServei = prop.getProperty(base + i + ".CODISERV");

        while (codiServei != null) {
            Servicio servicio = new Servicio();
            {
                String codigoCertificado = codiServei;

                Norma norma = new Norma();
                {
                    String normaLegal = prop.getProperty(base + i + ".NORMALEGAL"); //tramitI.getNorma();
                    Norma.Documento documento = new Norma.Documento();
                    {
                        byte[] docNormaContenido = new byte[0];
                        String docNormaDesc = null;
                        String docNormaEnlace = prop.getProperty(base + i + ".ENLACENOR");// tramitI.getUrlnorma();
                        String docNormaNombre = null;

                        documento.setContenido(docNormaContenido);
                        documento.setDescripcion(docNormaDesc);
                        documento.setEnlace(docNormaEnlace);
                        documento.setNombre(docNormaNombre);
                    }

                    Articulos articulos = new Articulos();
                    String articulosString = prop.getProperty(base + i + ".ARTICULOS");
                    String[] articulosArray = articulosString.split(",");

                    for (String articulo : articulosArray) {
                        articulos.getArticulo().add(articulo);
                    }

                    norma.setNormaLegal(normaLegal);
                    norma.setArticulos(articulos);
                    norma.setDocumento(documento);
                }

                Normas normas = new Normas();
                normas.getNorma().add(norma);

                servicio.setCodigoCertificado(codigoCertificado);
                servicio.setNormas(normas);
            }
            servicios.getServicio().add(servicio);

            i++;
            codiServei = prop.getProperty(base + i + ".CODISERV");
        }
        return servicios;
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

    private Contactos getContactos(Properties prop) {

        Contactos contactos = new Contactos();

        String contactoAutApe1 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE1SECD");
        String contactoAutApe2 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE2SECD");
        String contactoAutMail = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.MAILSECD");
        String contactoAutNombre = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBRESECD");
        String contactoAutTelefon = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.TELEFONOSECD");

        contactos.getContacto().add(createContacto(contactoAutApe1, contactoAutApe2, contactoAutMail, null,
                contactoAutNombre, contactoAutTelefon));

        String contactoAudApe1 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE1SECE");
        String contactoAudApe2 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE2SECE");
        String contactoAudMail = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.MAILSECE");
        String contactoAudNombre = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBRESECE");
        String contactoAudTelefon = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.TELEFONOSECE");

        contactos.getContacto().add(createContacto(contactoAudApe1, contactoAudApe2, contactoAudMail, null,
                contactoAudNombre, contactoAudTelefon));

        String contactoTecApe1 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE1SECF");
        String contactoTecApe2 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE2SECF");
        String contactoTecMail = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.MAILSECF");
        String contactoTecNombre = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBRESECF");
        String contactoTecTelefon = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.TELEFONOSECF");

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

    //    private Procedimiento getProcedimiento(String _Automatizado, Integer _ClaseTramite, String _Codigo,
    //            String _Descripcion, String _Nombre, String _Observaciones, String _Periodico, Integer _PeticionesEstimadas,
    //            XMLGregorianCalendar _FechaCaducidad, Consentimiento _Consentimiento,
    //            DocumentosAutorizacion _DocumentosAutorizacion, Servicios _Servicios) {
    //        Procedimiento proc = new Procedimiento();
    //        {
    //            proc.setAutomatizado(_Automatizado);
    //            proc.setClaseTramite(_ClaseTramite);
    //            proc.setCodigo(_Codigo);
    //            proc.setDescripcion(_Descripcion);
    //            proc.setNombre(_Nombre);
    //            proc.setObservaciones(_Observaciones);
    //            proc.setPeriodico(_Periodico);
    //            proc.setPeticionesEstimadas(_PeticionesEstimadas);
    //            proc.setFechaCaducidad(_FechaCaducidad);
    //            proc.setConsentimiento(_Consentimiento);
    //            proc.setDocumentosAutorizacion(_DocumentosAutorizacion);
    //            proc.setServicios(_Servicios);
    //        }
    //        return proc;
    //    }

    //    public void altaSolicitudLocualAmbTramitID(Long tramitID) {
    //
    //        try {
    //
    //            ITramitAPersAutManager tramitADao = PinbalAdminDaoManager.getDaoManagers().getTramitAPersAutManager();
    //            ITramitBDadesSoliManager tramitBDao = PinbalAdminDaoManager.getDaoManagers().getTramitBDadesSoliManager();
    //            ITramitCDadesCesiManager tramitCDao = PinbalAdminDaoManager.getDaoManagers().getTramitCDadesCesiManager();
    //            ITramitDCteAutManager tramitDDao = PinbalAdminDaoManager.getDaoManagers().getTramitDCteAutManager();
    //            ITramitECteAudManager tramitEDao = PinbalAdminDaoManager.getDaoManagers().getTramitECteAudManager();
    //            ITramitFCteTecManager tramitFDao = PinbalAdminDaoManager.getDaoManagers().getTramitFCteTecManager();
    //            ITramitGDadesTitManager tramitGDao = PinbalAdminDaoManager.getDaoManagers().getTramitGDadesTitManager();
    //            ITramitHProcManager tramitHDao = PinbalAdminDaoManager.getDaoManagers().getTramitHProcManager();
    //            ITramitIServManager tramitIDao = PinbalAdminDaoManager.getDaoManagers().getTramitIServManager();
    //            ITramitJConsentManager tramitJDao = PinbalAdminDaoManager.getDaoManagers().getTramitJConsentManager();
    //
    //            List<TramitAPersAut> listA = tramitADao.select(TramitAPersAutFields.TRAMITID.equal(tramitID));
    //            List<TramitBDadesSoli> listB = tramitBDao.select(TramitBDadesSoliFields.TRAMITID.equal(tramitID));
    //            List<TramitCDadesCesi> listC = tramitCDao.select(TramitCDadesCesiFields.TRAMITID.equal(tramitID));
    //            List<TramitDCteAut> listD = tramitDDao.select(TramitDCteAutFields.TRAMITID.equal(tramitID));
    //            List<TramitECteAud> listE = tramitEDao.select(TramitECteAudFields.TRAMITID.equal(tramitID));
    //            List<TramitFCteTec> listF = tramitFDao.select(TramitFCteTecFields.TRAMITID.equal(tramitID));
    //            List<TramitGDadesTit> listG = tramitGDao.select(TramitGDadesTitFields.TRAMITID.equal(tramitID));
    //            List<TramitHProc> listH = tramitHDao.select(TramitHProcFields.TRAMITID.equal(tramitID));
    //            List<TramitJConsent> listJ = tramitJDao.select(TramitJConsentFields.TRAMITID.equal(tramitID));
    //
    //            TramitAPersAut tramitA = listA.get(0);
    //            TramitBDadesSoli tramitB = listB.get(0);
    //            TramitCDadesCesi tramitC = listC.get(0);
    //            TramitDCteAut tramitD = listD.get(0);
    //            TramitECteAud tramitE = listE.get(0);
    //            TramitFCteTec tramitF = listF.get(0);
    //            TramitGDadesTit tramitG = listG.get(0);
    //            TramitHProc tramitH = listH.get(0);
    //            List<TramitIServ> tramitsI = tramitIDao.select(TramitIServFields.TRAMITID.equal(tramitID));
    //
    //            TramitJConsent tramitJ = null;
    //            if (listJ.size() > 0) {
    //                tramitJ = listJ.get(0);
    //
    //            }
    //
    //            // Titular
    //            ScspTitular titular = getTitular(tramitG);
    //
    //            // Funcionari
    //            ScspFuncionario funcionario = getFuncionari(tramitA);
    //
    //            // Solicitud
    //            Solicitud solicitud = getSolicitud(tramitD, tramitE, tramitF, tramitH, tramitsI, tramitJ);
    //
    //            PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(getPinbalAdminSolicitudsConfiguration());
    //            Incidencia incidencia = api.crearSolicitud(solicitud, titular, funcionario);
    //
    //            Respuesta respuesta = getRespuesta(tramitA, tramitB, tramitC, tramitD, tramitE, tramitF, tramitG, tramitH,
    //                    tramitsI, tramitJ);
    //
    //            DatosEspecificos de = new DatosEspecificos();
    //            de.setSolicitud(solicitud);
    //            de.setRespuesta(respuesta);
    //
    //            //            System.out.println("Email: " + in.getEmail());
    //            //            System.out.println("NumeroIncidencia: " + in.getNumeroIncidencia());
    //            //            System.out.println("getNumeroSeguimiento: " + in.getNumeroSeguimiento());
    //
    //        } catch (Throwable e) {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //
    //    }

}