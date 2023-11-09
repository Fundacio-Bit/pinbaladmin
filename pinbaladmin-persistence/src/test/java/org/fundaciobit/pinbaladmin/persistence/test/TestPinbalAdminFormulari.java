package org.fundaciobit.pinbaladmin.persistence.test;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Properties;
//
//import javax.xml.datatype.DatatypeConfigurationException;
//import javax.xml.datatype.DatatypeFactory;
//import javax.xml.datatype.XMLGregorianCalendar;
//
//import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsConfiguration;
//import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
//import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
//import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;
//import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
//import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
//import org.fundaciobit.pinbaladmin.model.entity.TramitECteAud;
//import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
//import org.fundaciobit.pinbaladmin.model.entity.TramitGDadesTit;
//import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
//import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;
//import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.fasterxml.jackson.databind.SerializationFeature;
//
//import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
//import es.caib.pinbal.client.recobriment.model.ScspTitular;
//import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Articulos;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Consentimiento;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Contacto;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Contactos;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.DocumentoAutorizacion;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.DocumentosAutorizacion;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Error;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Errores;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Estado;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Incidencia;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Norma;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Norma.Documento;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Normas;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Procedimiento;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Respuesta;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Servicio;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Servicios;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Solicitud;

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


//    private String obtenerContenidoXml(Long fitxerID) throws Exception {
//        File f = FileSystemManager.getFile(fitxerID);
//        byte[] xmlData = FileUtils.readFromFile(f);
//        return new String(xmlData);
//    }
//
////    private static String getElementValue(Element parentElement, String tagName) {
////        NodeList nodeList = parentElement.getElementsByTagName(tagName);
////        if (nodeList.getLength() > 0) {
////            return nodeList.item(0).getTextContent();
////        }
////        return null;
////    }
//    
//    @Test
//    public void cridadaAPICrearSolicitudMadridAmbSolicitudID(Long solicitudID) {
//        try {
//            ISolicitudManager solicitudDao = PinbalAdminDaoManager.getDaoManagers().getSolicitudManager();
//            org.fundaciobit.pinbaladmin.model.entity.Solicitud soli = solicitudDao.findByPrimaryKey(solicitudID);
//
//            if (soli != null) {
//                GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
//                Gson gson = builder.create();
//                
//                SolicitudBean soli2 = new SolicitudBean(soli);
//                
//                String json = gson.toJson(soli2);
//                
//                FileOutputStream fos = new FileOutputStream(new File("solicitud.json"));
//                fos.write(json.getBytes());
//                fos.flush();
//                fos.close();
//                
//                if (true) {
//                    return;
//                }
//                
//                System.out.println("HOLA OLA " + soli.getCreador());
//                
//                Fitxer f = soli.getSolicitudXml();
//                Long id = f.getFitxerID();
//                
//                
//                
////              Long fitxerID = soli.getSolicitudXmlID();
//              String contenidoXml = obtenerContenidoXml(id );
////              
//              System.out.println(contenidoXml);
//              
//          //    Properties prop = getPropertiesFromFormulario(contenidoXml);
//
////              DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
////              DocumentBuilder builder = factory.newDocumentBuilder();
////      
////              // Analiza el arreglo de bytes en un objeto Document
////              Document document = builder.parse(new ByteArrayInputStream(contenidoXml));
////      
////              // Obtén el elemento FORMULARIO
////              Element formularioElement = document.getDocumentElement();
////      
////              // Accede a los elementos que deseas
////              String apellido2 = getElementValue(formularioElement, "APE2SECD");
////              String email = getElementValue(formularioElement, "EMAIL");
//
//                
////                ScspTitular titular = getTitular(soli);
////                ScspFuncionario funcionario = getFuncionari(soli);
////                Solicitud solicitud = getSolicitud(soli);
////
////                PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(getPinbalAdminSolicitudsConfiguration());
////                Incidencia incidencia = api.crearSolicitud(solicitud, titular, funcionario);
//            }
//
//        } catch (I18NException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }
//    
//    public static Properties getPropertiesFromFormulario(String xml)
//            throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
//          DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//          InputSource is = new InputSource();
//          is.setCharacterStream(new StringReader(xml));
//
//          org.w3c.dom.Document doc = db.parse(is);
//
//          NodeList nodesForm = ((org.w3c.dom.Document) doc).getElementsByTagName("FORMULARIO");
//
//          Properties prop = new Properties();
//
//          Node element = nodesForm.item(0);
//          findAttrInChildren("", element, prop);
//          return prop;
//        }

//
//    private Solicitud getSolicitud(org.fundaciobit.pinbaladmin.model.entity.Solicitud soli) {
//        
//        //Asunto y consulta
//        String asunto = null;
//        String consulta = null;
//
//        //Contactos
//        Contactos contactos = getContactos(tramitD, tramitE, tramitF);
//
//        //Procedimiento
//        String _Automatizado = tramitH.isAutomatizado() ? "SI" : "NO";
//        Integer _ClaseTramite = Integer.parseInt(tramitH.getTipus());
//        String _Codigo = tramitH.getCodi();
//        String _Descripcion = tramitH.getDescripcio();
//        String _Nombre = tramitH.getNom();
//        String _Observaciones = null;
//        String _Periodico = tramitH.isPeriodico() ? "SI" : "NO";
//        Integer _PeticionesEstimadas = (int) tramitH.getPeticionsaldia();
//
//        XMLGregorianCalendar _FechaCaducidad = parseTimestampToXMLGregorian(tramitH.getCaducitatdata());
//        Consentimiento _Consentimiento = getConsentimiento(tramitsI, tramitJ);
//        DocumentosAutorizacion _DocumentosAutorizacion = getDocsAutorizacion();
//        Servicios _Servicios = getServicios(tramitsI);
//
//        Procedimiento proc = getProcedimiento(_Automatizado, _ClaseTramite, _Codigo, _Descripcion, _Nombre,
//                _Observaciones, _Periodico, _PeticionesEstimadas, _FechaCaducidad, _Consentimiento,
//                _DocumentosAutorizacion, _Servicios);
//
//        Solicitud solicitud = new Solicitud();
//        solicitud.setAsunto(asunto);
//        solicitud.setConsulta(consulta);
//        solicitud.setContactos(contactos);
//        solicitud.setProcedimiento(proc);
//
//        // printSolicitud(solicitud);
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//            ObjectWriter prettyWriter = objectMapper.writerWithDefaultPrettyPrinter();
//
//            String prettyJson = prettyWriter.writeValueAsString(solicitud);
//
//            System.out.println(prettyJson);
//
//        } catch (JsonProcessingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return solicitud;
//    }
//
//    private ScspFuncionario getFuncionari(org.fundaciobit.pinbaladmin.model.entity.Solicitud soli) {
//        ScspFuncionario funcionario = new ScspFuncionario();
//        
//        funcionario.setNifFuncionario(tramitA.getNif());
//        funcionario.setNombreCompletoFuncionario(
//                toFullName(tramitA.getNom(), tramitA.getLlinatge1(), tramitA.getLlinatge2()));
//
//        return funcionario;
//    }
//
//    private ScspTitular getTitular(org.fundaciobit.pinbaladmin.model.entity.Solicitud soli) {
//
//        ScspTitular titular = new ScspTitular();
//        ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.DNI;
//        titular.setTipoDocumentacion(tipoDocumentacion);
//        
//        titular.setDocumentacion(tramitG.getNif());
//        titular.setNombre(tramitG.getNom());
//        titular.setApellido1(tramitG.getLlinatge1());
//        titular.setApellido2(tramitG.getLlinatge2());
//
//        return titular;
//    }

//    @Test
//    public void cridadaAPICrearSolicitutMadrid(Long tramitID) {
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

//    protected PinbalAdminSolicitudsConfiguration getPinbalAdminSolicitudsConfiguration() throws Exception {
//
//        PinbalAdminSolicitudsConfiguration config = new PinbalAdminSolicitudsConfiguration();
//
//        Properties prop = new Properties();
//        prop.load(new FileInputStream(new File("test.properties")));
//
//        config.setUrlBase(prop.getProperty("urlbase"));
//        config.setUsername(prop.getProperty("username"));
//        config.setPassword(prop.getProperty("password"));
//
//        config.setFinalidad("Baremacions per el proces d'escolaritzacio"); // getPropertyRequired(propertyBase + "finalitat"); // "Baremacions per el proces d'escolaritzacio";
//        config.setIdentificadorSolicitante("S0711001H"); // getPropertyRequired(propertyBase + "identificadorsolicitant"); // "S0711001H";
//        config.setUnidadTramitadora("Servei d'escolarització"); // getPropertyRequired(propertyBase + "unitattramitadora"); // "Servei d'escolarització";
//
//        config.setCodProcedimiento("INCIDENCIES_CAID"); // getPropertyRequired(propertyBase + "codiprocediment"); // "CODSVDR_GBA_20121107";
//
//        // TODO crec que això hauria d'estar dins de PinbalAdminSolicitudsApi
//        /**
//         * SVDPIDSOLAUTWS01 | Solicitud de autorizaciones en PID
//        SVDPIDESTADOAUTWS01 | Servicio de estado de las autorizaciones en PID 
//        SVDPIDACTPROCWS01 | Servicio de actualización de un procedimiento ya dado de alta en PID
//         */
//        config.setCodigoCertificado("SVDPIDSOLAUTWS01");
//
//        return config;
//    }
//
//    private ScspFuncionario getFuncionari(TramitAPersAut tramitA) {
//        ScspFuncionario funcionario = new ScspFuncionario();
//        funcionario.setNifFuncionario(tramitA.getNif());
//        funcionario.setNombreCompletoFuncionario(
//                toFullName(tramitA.getNom(), tramitA.getLlinatge1(), tramitA.getLlinatge2()));
//
//        return funcionario;
//    }
//
//    private ScspTitular getTitular(TramitGDadesTit tramitG) {
//
//        ScspTitular titular = new ScspTitular();
//
//        ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.DNI;
//
//        titular.setTipoDocumentacion(tipoDocumentacion);
//        titular.setDocumentacion(tramitG.getNif());
//        titular.setNombre(tramitG.getNom());
//        titular.setApellido1(tramitG.getLlinatge1());
//        titular.setApellido2(tramitG.getLlinatge2());
//
//        return titular;
//    }
//
//    private Solicitud getSolicitud(TramitDCteAut tramitD, TramitECteAud tramitE, TramitFCteTec tramitF,
//            TramitHProc tramitH, List<TramitIServ> tramitsI, TramitJConsent tramitJ) {
//
//        //Asunto y consulta
//        String asunto = null;
//        String consulta = null;
//
//        //Contactos
//        Contactos contactos = getContactos(tramitD, tramitE, tramitF);
//
//        //Procedimiento
//        String _Automatizado = tramitH.isAutomatizado() ? "SI" : "NO";
//        Integer _ClaseTramite = Integer.parseInt(tramitH.getTipus());
//        String _Codigo = tramitH.getCodi();
//        String _Descripcion = tramitH.getDescripcio();
//        String _Nombre = tramitH.getNom();
//        String _Observaciones = null;
//        String _Periodico = tramitH.isPeriodico() ? "SI" : "NO";
//        Integer _PeticionesEstimadas = (int) tramitH.getPeticionsaldia();
//
//        XMLGregorianCalendar _FechaCaducidad = parseTimestampToXMLGregorian(tramitH.getCaducitatdata());
//        Consentimiento _Consentimiento = getConsentimiento(tramitsI, tramitJ);
//        DocumentosAutorizacion _DocumentosAutorizacion = getDocsAutorizacion();
//        Servicios _Servicios = getServicios(tramitsI);
//
//        Procedimiento proc = getProcedimiento(_Automatizado, _ClaseTramite, _Codigo, _Descripcion, _Nombre,
//                _Observaciones, _Periodico, _PeticionesEstimadas, _FechaCaducidad, _Consentimiento,
//                _DocumentosAutorizacion, _Servicios);
//
//        Solicitud solicitud = new Solicitud();
//        solicitud.setAsunto(asunto);
//        solicitud.setConsulta(consulta);
//        solicitud.setContactos(contactos);
//        solicitud.setProcedimiento(proc);
//
//        // printSolicitud(solicitud);
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//            ObjectWriter prettyWriter = objectMapper.writerWithDefaultPrettyPrinter();
//
//            String prettyJson = prettyWriter.writeValueAsString(solicitud);
//
//            System.out.println(prettyJson);
//
//        } catch (JsonProcessingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return solicitud;
//    }
//
//    public Respuesta getRespuesta(TramitAPersAut tramitA, TramitBDadesSoli tramitB, TramitCDadesCesi tramitC,
//            TramitDCteAut tramitD, TramitECteAud tramitE, TramitFCteTec tramitF, TramitGDadesTit tramitG,
//            TramitHProc tramitH, List<TramitIServ> tramitsI, TramitJConsent tramitJ) {
//
//        Errores errores = new Errores();
//        List<Error> listError = errores.getError();
//
//        int nErrors = 0;
//        for (int i = 0; i < nErrors; i++) {
//            String codigoError = null;
//            String descripcionError = null;
//
//            Error e = getError(codigoError, descripcionError);
//            listError.add(e);
//        }
//
//        String codigoEstado = null;
//        String descripcionEstado = null;
//        Estado estado = getEstadoRespuesta(codigoEstado, descripcionEstado);
//
//        String email = null;
//        Long numInc = null;
//        Long numSeg = null;
//        Incidencia incidencia = getIncidencia(email, numInc, numSeg);
//
//        Respuesta respuesta = new Respuesta();
//        respuesta.setErrores(errores);
//        respuesta.setEstado(estado);
//        respuesta.setIncidencia(incidencia);
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//            ObjectWriter prettyWriter = objectMapper.writerWithDefaultPrettyPrinter();
//
//            String prettyJson = prettyWriter.writeValueAsString(respuesta);
//
//            System.out.println(prettyJson);
//
//        } catch (JsonProcessingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return respuesta;
//    }
//
//    public Error getError(String codigoError, String descripcionError) {
//        Error e = new Error();
//        e.setCodigo(codigoError);
//        e.setDescripcion(descripcionError);
//        return e;
//    }
//
//    public Incidencia getIncidencia(String email, Long numInc, Long numSeg) {
//        Incidencia incidencia = new Incidencia();
//        incidencia.setEmail(email);
//        incidencia.setNumeroIncidencia(numInc);
//        incidencia.setNumeroSeguimiento(numSeg);
//        return incidencia;
//    }
//
//    public Estado getEstadoRespuesta(String codigoEstado, String descripcion) {
//        Estado estado = new Estado();
//        estado.setCodigoEstado(codigoEstado);
//        estado.setDescripcion(descripcion);
//        return estado;
//    }
//    //==============================================================================
//
//    private Consentimiento getConsentimiento(List<TramitIServ> tramitsI, TramitJConsent tramitJ) {
//
//        Consentimiento cons = new Consentimiento();
//        Consentimiento.Documento doc = new Consentimiento.Documento();
//
//        String enlace = null;
//        String tipo = "llei";
//
//        for (TramitIServ tramitI : tramitsI) {
//            if (tramitI.getConsentiment().equals("noop")) {
//                tipo = "noop";
//                if (tramitI.getConsentimentpublicat().equals("2")) { //1: publicat 2: adjunt
//                    enlace = null;
//                    break;
//                } else {
//                    enlace = tramitI.getUrlconsentiment();
//                }
//            }
//        }
//
//        if (tipo.equals("noop")) {
//            if (enlace == null) {
//                if (tramitJ != null) {
//                    try {
//                        Fitxer f = tramitJ.getAdjunt();
//                        System.out.println(f.getFitxerID());
//                        //                        byte[] contingut = FileSystemManager.getFileContent(f.getFitxerID());
//                        int size = (int) f.getTamany();
//                        size = 10;
//                        byte[] contingut = new byte[size];
//                        String descripcio = f.getDescripcio();
//                        String nom = f.getNom();
//
//                        doc.setContenido(contingut);
//                        doc.setDescripcion(descripcio);
//                        doc.setNombre(nom);
//
//                        cons.setDocumento(doc);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        //                    } catch (FileNotFoundException e) {
//                        //                        e.printStackTrace();
//                        //                    } catch (IOException e) {
//                        //                        e.printStackTrace();
//                    }
//                }
//            } else {
//                cons.setEnlace(enlace);
//            }
//        }
//        cons.setTipo(tipo);
//        return cons;
//    }
//
//    private Servicios getServicios(List<TramitIServ> tramitsI) {
//        Servicios servicios = new Servicios();
//        for (TramitIServ tramitI : tramitsI) {
//
//            Servicio servicio = new Servicio();
//
//            String codigoCertificado = tramitI.getCodi();
//
//            Norma norma = new Norma();
//            {
//                String normaLegal = tramitI.getNorma();
//
//                Norma.Documento documento = new Norma.Documento();
//                {
//                    byte[] docNormaContenido = new byte[0];
//                    String docNormaDesc = null;
//                    String docNormaEnlace = tramitI.getUrlnorma();
//                    String docNormaNombre = null;
//
//                    documento.setContenido(docNormaContenido);
//                    documento.setDescripcion(docNormaDesc);
//                    documento.setEnlace(docNormaEnlace);
//                    documento.setNombre(docNormaNombre);
//                }
//
//                Articulos articulos = new Articulos();
//                String[] articulosArray = tramitI.getArticles().split(",");
//
//                for (String articulo : articulosArray) {
//                    articulos.getArticulo().add(articulo);
//                }
//
//                norma.setNormaLegal(normaLegal);
//                norma.setArticulos(articulos);
//                norma.setDocumento(documento);
//            }
//
//            Normas normas = new Normas();
//            normas.getNorma().add(norma);
//
//            servicio.setCodigoCertificado(codigoCertificado);
//            servicio.setNormas(normas);
//
//            servicios.getServicio().add(servicio);
//        }
//        return servicios;
//    }
//
//    private Contactos getContactos(TramitDCteAut tramitD, TramitECteAud tramitE, TramitFCteTec tramitF) {
//
//        Contactos contactos = new Contactos();
//
//        contactos.getContacto().add(createContacto(tramitD.getLlinatge1(), tramitD.getLlinatge2(), tramitD.getMail(),
//                null, tramitD.getNom(), tramitD.getTelefon()));
//
//        contactos.getContacto().add(createContacto(tramitE.getLlinatge1(), tramitE.getLlinatge2(), tramitE.getMail(),
//                null, tramitE.getNom(), tramitE.getTelefon()));
//
//        contactos.getContacto().add(createContacto(tramitF.getLlinatge1(), tramitF.getLlinatge2(), tramitF.getMail(),
//                null, tramitF.getNom(), tramitF.getTelefon()));
//
//        return contactos;
//    }
//
//    private DocumentosAutorizacion getDocsAutorizacion() {
//
//        DocumentosAutorizacion docs = new DocumentosAutorizacion();
//        {//Aqui va un bucle
//            byte[] docAutCont = new byte[0];
//            String docAutdesc = null;
//            String docAutName = null;
//            String docAutType = null;
//            DocumentoAutorizacion docAut = getDocumentoAutorizacion(docAutCont, docAutdesc, docAutName, docAutType);
//
//            docs.getDocumentoAutorizacion().add(docAut);
//        }
//        return docs;
//    }
//
//    //===============================================================================
//
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
//
//        return cal;
//    }
//
//    private DocumentoAutorizacion getDocumentoAutorizacion(byte[] docAutCont, String docAutdesc, String docAutName,
//            String docAutType) {
//        DocumentoAutorizacion docAut = new DocumentoAutorizacion();
//        docAut.setContenido(docAutCont);
//        docAut.setDescripcion(docAutdesc);
//        docAut.setNombre(docAutName);
//        docAut.setTipo(docAutType);
//        return docAut;
//    }
//
//    private Contacto createContacto(String contactoApe1, String contactoApe2, String contactoMail, String contactoFax,
//            String contactoNombre, String contactoTelefono) {
//        Contacto contacto = new Contacto();
//        contacto.setApellido1(contactoApe1);
//        contacto.setApellido2(contactoApe2);
//        contacto.setEmail(contactoMail);
//        contacto.setFax(contactoFax);
//        contacto.setNombre(contactoNombre);
//        contacto.setTelefono(contactoTelefono);
//        return contacto;
//    }
//
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
//
//    private String toFullName(String nom, String l1, String l2) {
//        String fullName = nom + " " + l1 + (l2 == "" ? "" : " " + l2);
//        return fullName;
//    }
//
//    //==============================================================================
//
//    public void printSolicitud(Solicitud solicitud) {
//
//        String msg = "\n\nDades de la Solicitud creada\n";
//
//        msg += "Asunto: " + solicitud.getAsunto() + " \n";
//        msg += "Consulta: " + solicitud.getConsulta() + " \n";
//
//        msg += "Contactos:  [\n";
//        {
//            List<Contacto> contactos = solicitud.getContactos().getContacto();
//            for (Contacto contacto : contactos) {
//                msg += "\t{\n";
//
//                msg += "\t " + " Nombre: " + contacto.getNombre() + "\n";
//                msg += "\t " + " Apellido1: " + contacto.getApellido1() + "\n";
//                msg += "\t " + " Apellido2: " + contacto.getApellido2() + "\n";
//                msg += "\t " + " Email: " + contacto.getEmail() + "\n";
//                msg += "\t " + " Telefono: " + contacto.getTelefono() + "\n";
//                msg += "\t " + " Fax: " + contacto.getFax() + "\n";
//
//                msg += "\t},\n";
//            }
//            msg += "]\n";
//        }
//
//        msg += "Procedimiento:  {\n";
//        {
//            Procedimiento proc = solicitud.getProcedimiento();
//            msg += "\t " + " Automatizado: " + proc.getAutomatizado() + "\n";
//            msg += "\t " + " ClaseTramite: " + proc.getClaseTramite() + "\n";
//            msg += "\t " + " Codigo: " + proc.getCodigo() + "\n";
//            msg += "\t " + " Descripcion: " + proc.getDescripcion() + "\n";
//            msg += "\t " + " Nombre: " + proc.getNombre() + "\n";
//            msg += "\t " + " Observaciones: " + proc.getObservaciones() + "\n";
//            msg += "\t " + " Periodico: " + proc.getPeriodico() + "\n";
//            msg += "\t " + " PeticionesEstimadas: " + proc.getPeticionesEstimadas() + "\n";
//
//            msg += "\t " + " FechaCaducidad: " + proc.getFechaCaducidad() + "\n";
//
//            msg += "\t " + " Consentimiento: {\n";
//            {
//                Consentimiento cons = proc.getConsentimiento();
//
//                msg += "\t\t  Enlace: " + cons.getEnlace() + "\n";
//                msg += "\t\t  Tipo: " + cons.getTipo() + "\n";
//                msg += "\t\t  DocName: " + cons.getDocumento().getNombre() + "\n";
//                msg += "\t\t  DocDescr: " + cons.getDocumento().getDescripcion() + "\n";
//                msg += "\t\t  DocSize: " + cons.getDocumento().getContenido().length + "\n";
//            }
//            msg += "\t\t}\n";
//
//            msg += "\t " + " DocumentosAutorizacion: [\n";
//            {
//                List<DocumentoAutorizacion> docs = proc.getDocumentosAutorizacion().getDocumentoAutorizacion();
//                for (DocumentoAutorizacion doc : docs) {
//                    msg += "\t\t{\n";
//
//                    msg += "\t\t  DocType: " + doc.getTipo() + "\n";
//                    msg += "\t\t  DocName: " + doc.getNombre() + "\n";
//                    msg += "\t\t  DocDescr: " + doc.getDescripcion() + "\n";
//                    msg += "\t\t  DocSize: " + doc.getContenido().length + "\n";
//
//                    msg += "\t\t},\n";
//                }
//            }
//            msg += "\t  ]\n";
//
//            msg += "\t " + " Servicios: [\n";
//            {
//                List<Servicio> servicios = proc.getServicios().getServicio();
//                for (Servicio servicio : servicios) {
//                    msg += "\t\t{\n";
//
//                    msg += "\t\t  CodigoCertificado: " + servicio.getCodigoCertificado() + "\n";
//
//                    msg += "\t\t  Normas:  [" + "\n";
//                    {
//                        List<Norma> normas = servicio.getNormas().getNorma();
//                        for (Norma norma : normas) {
//                            msg += "\t\t\t{\n";
//                            msg += "\t\t\t  NormaLegal: " + norma.getNormaLegal() + "\n";
//                            msg += "\t\t\t  Articulo: " + norma.getArticulos().getArticulo().get(0) + "\n";
//
//                            msg += "\t\t\t  Documento: {\n";
//                            {
//                                Documento doc = norma.getDocumento();
//                                msg += "\t\t\t\t  EnlaceDocNorma: " + doc.getEnlace() + "\n";
//                                msg += "\t\t\t\t  NombreDocNorma: " + doc.getNombre() + "\n";
//                                msg += "\t\t\t\t  DescripcionDocNorma: " + doc.getDescripcion() + "\n";
//                                msg += "\t\t\t\t  SizeDocNorma: " + doc.getContenido().length + "\n";
//                            }
//
//                            msg += "\t\t\t }\n";
//                        }
//
//                    }
//                    msg += "\t\t   ]\n";
//
//                    msg += "\t\t},\n";
//                }
//            }
//            msg += "\t  ]\n";
//
//        }
//        msg += "}\n";
//
//        System.out.println(msg);
//
//    }

}