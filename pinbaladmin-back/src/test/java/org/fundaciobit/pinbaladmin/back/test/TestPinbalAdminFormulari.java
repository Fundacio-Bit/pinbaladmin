package org.fundaciobit.pinbaladmin.back.test;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsConfiguration;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.model.bean.SolicitudBean;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;
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

    public static void main(String[] args) {
        TestPinbalAdminFormulari t = new TestPinbalAdminFormulari();
        try {
            t.test();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void test() throws Exception {
        FileSystemManager.setFilesPath(new File("D:\\Projectes\\pinbaladmin-files\\files"));
//        cridadaAPICrearSolicitudMadridAmbSolicitudID();
        consultaEstatSolicitudPinbal();

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


    public void consultaEstatSolicitudPinbal () {
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
            
            ScspTitular titular = getTitular(prop);
            ScspFuncionario funcionario = getFuncionari(prop);
            Consulta consulta = getConsultaEstat(prop);
            
            
            PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(getPinbalAdminSolicitudsConfiguration(TipusCridada.CONSULTA));
            Retorno retorno = api.consultaEstatPinbalApi(consulta, titular, funcionario);
            
            String estado = retorno.getProcedimiento().getEstadoProcedimiento().getDescripcion();
            System.out.println(" # Estado: " + estado);
            
        }catch(Exception e) {
            
        }
    }
    
    private Consulta getConsultaEstat(Properties prop) {
        
        Consulta consulta = new Consulta();
        
        consulta.setCodigoProcedimiento("1841768");
        
        return consulta;
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
            String jsonTit = prettyWriter.writeValueAsString(titular);
            String jsonFunc = prettyWriter.writeValueAsString(funcionario);
            String jsonSoli = prettyWriter.writeValueAsString(solicitud);

            //            String jsonProc = prettyWriter.writeValueAsString(proc);

            //            System.out.println(jsonTit);
            //            System.out.println(jsonFunc);
            //            System.out.println(jsonSoli);
            //            System.out.println(jsonProc);

            PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(getPinbalAdminSolicitudsConfiguration(TipusCridada.ALTA));
            Respuesta respuesta = api.altaSolicitudPinbalApi(solicitud, titular, funcionario);

            System.out.println("Estado: " + respuesta.getEstado().getCodigoEstado());
            System.out.println("Descripción: " + respuesta.getEstado().getDescripcion());

        } catch (I18NException e) {
            e.printStackTrace();
        } catch (Exception e) {
 //           String msg = "" + e.getMessage();
            e.printStackTrace();
//            System.out.println(msg);
        } catch (Throwable e) {
            String msg = "" + e.getCause();
            System.out.println(msg);
            //            e.printStackTrace();
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

        ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.NIF;
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
        String asunto = "Alta Servicios";
        String consulta = "Alta procedimiento";

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
        Integer tipusProcediment = 0; //Pruebas
        //        Integer [] tipusAceptats = {0, 2, 3, 14, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 99};
        //
        //        for (Integer element : tipusAceptats ) {
        //            if (element == _ClaseTramite ) {
        //                tipusProcediment = element;
        //                break;
        //            }
        //        }

        String _Codigo = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CODIPROC");
        String _Descripcion = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.DESCRIPCION");
        String _Nombre = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBREPROC");
        String _Observaciones = null;

        String caduca = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CADUCA");
        Timestamp dataCaducitat = null;
        if (caduca.equals("S")) {
            dataCaducitat = soli.getDataFi();
        }
        //Timestamp dataFi =  prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBREPROC");
        XMLGregorianCalendar _FechaCaducidad = parseTimestampToXMLGregorian(dataCaducitat);

        //Hay que modificar el tramite. Hay que pedir el tipo de consentimiento y el pdf/enlace en TramitH. 
        // Y pasar en enlace por el xml
        Fitxer consentiment = soli.getDocumentSolicitud();
        Consentimiento _Consentimiento = getConsentimiento(prop, consentiment);

        /*
         * Aquí son el excel de servicios y el documento PDF del Director General. 
         * Falta también aclarar lo de CONVENIO y FORMULARIO DE AUTORIZACION 
         * 
         * Solicitud -> DocumentSolicitud -> Document -> FitxerID
         * 
         * Fitxer[] docAut = { soli.getDocumentSolicitud(), soli.getDocumentSolicitud() }; 
         * DocumentosAutorizacion _DocumentosAutorizacion = getDocsAutorizacion(docAut); 
         */            
        
        Long[] fitxerIDs = {39106L, 39107L};
        DocumentosAutorizacion _DocumentosAutorizacion = getDocsAutorizacion(fitxerIDs);

        //Ahora está cogiendo los datos del xml. Luego tendrá que coger los datos solicitud-servei.
        //También habrá que modificar el tramite y pedir los PDF de las normas en tramitI
        Servicios _Servicios = getServicios(prop, consentiment);

        Procedimiento proc = new Procedimiento();
        proc.setAutomatizado(_Automatizado);
        proc.setClaseTramite(tipusProcediment);
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

    
    public enum TipusCridada{
        ALTA, CONSULTA, MODIFICACIO,
    }

    protected PinbalAdminSolicitudsConfiguration getPinbalAdminSolicitudsConfiguration(TipusCridada tipus) throws Exception {

        PinbalAdminSolicitudsConfiguration config = new PinbalAdminSolicitudsConfiguration();

        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("test.properties")));

        String url = prop.getProperty("urlbase");

        System.out.println(url);
        config.setUrlBase(url);
        config.setUsername(prop.getProperty("username"));
        config.setPassword(prop.getProperty("password"));

        config.setFinalidad("Solicitar autorización procedimiento"); // getPropertyRequired(propertyBase + "finalitat"); // "Baremacions per el proces d'escolaritzacio";
        config.setIdentificadorSolicitante("S0711001H"); // getPropertyRequired(propertyBase + "identificadorsolicitant"); // "S0711001H";
        config.setUnidadTramitadora("Fundacio BIT"); // getPropertyRequired(propertyBase + "unitattramitadora"); // "Servei d'escolarització";

        config.setCodProcedimiento("PREALTAS");//INCIDENCIES_CAID"); 
        // getPropertyRequired(propertyBase + "codiprocediment"); // "CODSVDR_GBA_20121107";

        // TODO crec que això hauria d'estar dins de PinbalAdminSolicitudsApi
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
       
        //        SVDPIDSOLAUTWS01
        config.setCodigoCertificado(codigoCertificado);

        return config;

    }

    //==============================================================================

    private Consentimiento getConsentimiento(Properties prop, Fitxer fitxerConsentiment) {

        Consentimiento cons = new Consentimiento();
        Consentimiento.Documento doc = new Consentimiento.Documento();

        String tipo = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CONSENTIMIENTO"); //Si, Ley, NoOpo
        String enlace = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CONSENTIMIENTOENLACE");

        tipo = "Ley";

        System.out.println("tipo: " + tipo);
        cons.setTipo(tipo);

        if (tipo.equals("Si") || tipo.equals("NoOpo")) {
            if (enlace == null && fitxerConsentiment != null) {
                try {
                    //   System.out.println(documentConsentiment.getFitxerID());

                    String descripcio = "descripcio de prova: " + fitxerConsentiment.getDescripcio();
                    String nom = fitxerConsentiment.getNom();
                    Long consentimentID = fitxerConsentiment.getFitxerID();
                    File fileConsentiment = FileSystemManager.getFile(consentimentID);
                    byte[] contingut = FileUtils.readFromFile(fileConsentiment);

                    System.out.println("CONS: " + nom + " : " + contingut.length + " bytes");
                    doc.setContenido(contingut);
                    doc.setDescripcion(descripcio);
                    doc.setNombre(nom);

                    cons.setDocumento(doc);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                cons.setEnlace(enlace);
            }
        }
        return cons;

        //      while (tipoConsentiment != null && !necessitaDocument) {
        //
        //          tipo = tipoConsentiment;
        //
        //          if (consPublicat.equals("Adjunt")) {
        //              //Si es adjunto, no necesita enlace
        //              enlace = null;
        //              if (tipoConsentiment.equals("noop")) {
        //                  //Si ademas es noop, necesita el documento
        //                  necessitaDocument = true;
        //              } else {
        //                  //El caso de ley adjunta es muy raro. Se puede dar pero no se dará
        //              }
        //          } else {
        //              //Si está publicado, necesita enlace. Da igual tipo de consentimiento
        //              enlace = prop.getProperty(base + i + ".ENLACECON");
        //          }
        //
        //          i++;
        //          tipoConsentiment = prop.getProperty(base + i + ".CONSENTIMIENTO");
        //          consPublicat = prop.getProperty(base + i + ".LDECONSENTIMIENTO");
        //      }

    }

    private DocumentosAutorizacion getDocsAutorizacion(Fitxer[] documents) {

        DocumentosAutorizacion docs = new DocumentosAutorizacion();
        for (Fitxer document : documents) {
            try {

                String descripcio = "Descripcio autorització: " + document.getDescripcio();
                String nom = document.getNom();
                String tipo = null;
                switch (document.getMime()) {
                    case "application/pdf":
                        tipo = "CONVENIO";
                    break;
                    case "application/msexcel":
                        tipo = "FORMULARIO DE AUTORIZACION";
                }

                Long documentID = document.getFitxerID();
                File file = FileSystemManager.getFile(documentID);
                byte[] contingut = FileUtils.readFromFile(file);

                DocumentoAutorizacion docAut = new DocumentoAutorizacion();
                System.out.println("AUT: " + nom + " : " + contingut.length + " bytes");

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

        private DocumentosAutorizacion getDocsAutorizacion(Long[] fitxersID) {
    
            DocumentosAutorizacion docs = new DocumentosAutorizacion();
            for (Long fitxerID : fitxersID) {
                try {
                    File file = FileSystemManager.getFile(fitxerID);
    
                    String nom = file.getName() + ".pdf";

                    //                    Fitxer document = fitxerEjb.findByPrimaryKey(fitxerID);
                    String descripcio = file.getAbsolutePath();
//                    System.out.println(descripcio);
//                    String tipo = document.getMime();
                    
                    String tipo = null;
                    if (fitxerID == 39106L) {
                        tipo = "CONVENIO";
                    } else {
                        tipo = "FORMULARIO DE AUTORIZACION";
                    }

                    byte[] contingut = FileUtils.readFromFile(file);
    
                    DocumentoAutorizacion docAut = new DocumentoAutorizacion();
                    System.out.println("AUT: " + nom + " : " + contingut.length + " bytes");

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

    private Servicios getServicios(Properties prop, Fitxer fitcheroPrueba) {

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
                    Norma.Documento docNorma = new Norma.Documento();
                    {

                        try {
                            String enlace = prop.getProperty(base + i + ".ENLACENOR");// tramitI.getUrlnorma();
                            System.out.println(enlace);
                            String descripcio = "DocNorma prova " + i + ": " + fitcheroPrueba.getDescripcio();
                            String nom = i  + "_" + fitcheroPrueba.getNom();
                            Long consentimentID = fitcheroPrueba.getFitxerID();

                            File fileConsentiment = FileSystemManager.getFile(consentimentID);
                            byte[] contingut = FileUtils.readFromFile(fileConsentiment);

                            System.out.println("NORMA: " + nom + " : " + contingut.length + " bytes");

                            docNorma.setContenido(contingut);
                            docNorma.setDescripcion(descripcio);
                            docNorma.setEnlace(enlace);
                            docNorma.setNombre(nom);

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    Articulos articulos = new Articulos();
                    String articulosString = prop.getProperty(base + i + ".ARTICULOS");
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