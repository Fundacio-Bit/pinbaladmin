package org.fundaciobit.pinbaladmin.ejb.test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitBDadesSoliLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitCDadesCesiLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitDCteAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitECteAudLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitFCteTecLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitGDadesTitLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitHProcLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitIServLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitECteAud;
import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.pinbaladmin.model.entity.TramitGDadesTit;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitBDadesSoliFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitDCteAutFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitECteAudFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitFCteTecFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitGDadesTitFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitHProcFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;

//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Articulos;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Consentimiento;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Consentimiento.Documento;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Contacto;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Contactos;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.DatosEspecificos;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.DocumentoAutorizacion;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.DocumentosAutorizacion;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Error;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Errores;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Estado;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Incidencia;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Norma;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Normas;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Respuesta;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Servicio;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Servicios;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Solicitud;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.DatosGenericos;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.Emisor;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.Funcionario;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.Solicitante;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.SolicitudTransmision;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.Titular;
//import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.Transmision;

public class MappingSolicituds {

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAEjb;

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

    TramitAPersAut tramitA;
    TramitBDadesSoli tramitB;
    TramitCDadesCesi tramitC;
    TramitDCteAut tramitD;
    TramitECteAud tramitE;
    TramitFCteTec tramitF;
    TramitGDadesTit tramitG;
    TramitHProc tramitH;
    List<TramitIServ> tramitsI;

    public static void main(String[] args) {
//        try {
//
//            Long tramitID = 1111L;
//
//            MappingSolicituds m = new MappingSolicituds();
//            m.getSolicitudTransmision(tramitID);
//
//        } catch (I18NException e) {
//            e.printStackTrace();
//        }
    }

//    public SolicitudTransmision getSolicitudTransmision(Long tramitID) throws I18NException {
//
//        System.out.println(tramitAEjb);
//        Long[] identificadorsTramit = tramitAEjb.getPartsTramitIDs(tramitID);
//
//        tramitA = tramitAEjb.findByPrimaryKey(identificadorsTramit[0]);
//        tramitB = tramitBEjb.findByPrimaryKey(identificadorsTramit[1]);
//        tramitC = tramitCEjb.findByPrimaryKey(identificadorsTramit[2]);
//        tramitD = tramitDEjb.findByPrimaryKey(identificadorsTramit[3]);
//        tramitE = tramitEEjb.findByPrimaryKey(identificadorsTramit[4]);
//        tramitF = tramitFEjb.findByPrimaryKey(identificadorsTramit[5]);
//        tramitG = tramitGEjb.findByPrimaryKey(identificadorsTramit[6]);
//        tramitH = tramitHEjb.findByPrimaryKey(identificadorsTramit[7]);
//
//        DatosEspecificos de = getDatosEspecificos();
//        DatosGenericos dg = getDatosGenericos();
//
//        SolicitudTransmision sol = new SolicitudTransmision();
//        sol.setDatosEspecificos(de);
//        sol.setDatosGenericos(dg);
//        return sol;
//    }
//
//    public DatosEspecificos getDatosEspecificos() throws I18NException {
//
//        Respuesta resp = getRespuesta();
//        Solicitud solicitud = getSolicitud();
//
//        DatosEspecificos de = new DatosEspecificos();
//        de.setRespuesta(resp);
//        de.setSolicitud(solicitud);
//        return de;
//    }
//
//    public Respuesta getRespuesta() {
//        //Estado
//        String codigoEstado = null;
//        String descEstado = null;
//
//        //Incidencia
//        String mailInc = null;
//        Long numInc = null;
//        Long numSeg = null;
//
//        //Errores
//        Errores errores = getErrores();
//        Estado estado = getEstado(codigoEstado, descEstado);
//        Incidencia incidencia = getIncidencia(mailInc, numInc, numSeg);
//
//        Respuesta resp = new Respuesta();
//        resp.setErrores(errores);
//        resp.setEstado(estado);
//        resp.setIncidencia(incidencia);
//        return resp;
//    }
//
//    public Solicitud getSolicitud() throws I18NException {
//        //Asunto y consulta
//        String asunto = null;
//        String consulta = null;
//
//        //Contactos
//        Contactos contactos = getContactos();
//
//        //Procedimiento
//        String automProcEsp = null;
//        Integer claseTramitProcEsp = Integer.parseInt(tramitH.getTipus());
//        String codiProcEsp = tramitH.getCodi();
//        String nomProcEsp = tramitH.getNom();
//        String descProcEsp = tramitH.getDescripcio();
//        String obsProcEsp = null;
//        String periProcEsp = null;
//        Integer petsEstimadasProcEsp = (int) tramitH.getPeticionsaldia();
//        XMLGregorianCalendar fechaCaducidad = parseTimestampToXMLGregorian(tramitH.getCaducitatdata());
//
//        byte[] conenido = null;
//        String docDesc = null;
//        String docName = null;
//        Documento doc = getDocumentoConsentimiento(conenido, docDesc, docName);
//
//        String enlace = null;
//        String tipo = null;
//        Consentimiento cons = getConsentimiento(enlace, tipo, doc);
//
//        DocumentosAutorizacion docs = getDocsAutorizacion();
//        Servicios servicios = getServicios();
//
//        es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Procedimiento proc = getProcedimiento(
//                automProcEsp, claseTramitProcEsp, codiProcEsp, nomProcEsp, descProcEsp, obsProcEsp, periProcEsp,
//                petsEstimadasProcEsp, fechaCaducidad, cons, docs, servicios);
//
//        Solicitud solicitud = new Solicitud();
//        solicitud.setAsunto(asunto);
//        solicitud.setConsulta(consulta);
//        solicitud.setContactos(contactos);
//        solicitud.setProcedimiento(proc);
//        return solicitud;
//    }
//
//    private XMLGregorianCalendar parseTimestampToXMLGregorian(Timestamp caducitatdata) {
//        // TODO Auto-generated method stub
//        //XYZ
//        return null;
//    }
//
//    public DatosGenericos getDatosGenericos() throws I18NException {
//        //Emisor
//        String nifEmisor = tramitA.getNif();
//        String nombreEmisor = toFullName(tramitA.getNom(), tramitA.getLlinatge1(), tramitA.getLlinatge2());
//
//        //Solicitante
//        String NombreSolicitante = tramitCEjb.getDenominacioValue(tramitC.getDenominacio());
//        String IdentificadorSolicitante = tramitC.getNif();
//        String UnidadTramitadora = tramitC.getResponsable();
//        String CodigoUnidadTramitadora = tramitC.getDir3responsable();
//        String Consentimiento = null;
//        String Finalidad = null;
//        String IdExpediente = null;
//
//        //Solicitante.funcionario
//        String nifFunc = null;
//        String nomFunc = null;
//
//        //Solicitante.procedimiento
//        String automProcPeti = null;
//        Integer claseTramitProcPeti = Integer.parseInt(tramitH.getTipus());
//        String codiProcPeti = tramitH.getCodi();
//        String nomProcPeti = tramitH.getNom();
//
//        //Titular
//        String titApe1 = tramitG.getLlinatge1();
//        String titApe2 = tramitG.getLlinatge2();
//        String titDocumentacion = tramitG.getNif();
//        String titNombre = tramitG.getNom();
//        String titFullName = toFullName(tramitG.getNom(), tramitG.getLlinatge1(), tramitG.getLlinatge2());
//        String titTipusDoc = null; //NIF??
//
//        //Transmision
//        String codiCert = null;
//        String fechaGen = null;
//        String idSolicitud = null;
//
//        Emisor emi = getEmisor(nifEmisor, nombreEmisor);
//
//        Funcionario func = getFuncionario(nifFunc, nomFunc);
//        es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.Procedimiento proc = getProcedimientoPeticion(
//                automProcPeti, claseTramitProcPeti, codiProcPeti, nomProcPeti);
//        Solicitante solicitant = getSolicitantDG(CodigoUnidadTramitadora, Consentimiento, Finalidad,
//                IdentificadorSolicitante, IdExpediente, NombreSolicitante, UnidadTramitadora, func, proc);
//
//        Titular tit = getTitular(titApe1, titApe2, titDocumentacion, titNombre, titFullName, titTipusDoc);
//
//        Transmision transm = getTransmision(codiCert, fechaGen, idSolicitud);
//
//        DatosGenericos dg = new DatosGenericos();
//
//        dg.setEmisor(emi);
//        dg.setSolicitante(solicitant);
//        dg.setTitular(tit);
//        dg.setTransmision(transm);
//        return dg;
//    }
//
//    public Errores getErrores() {
//        Errores errores = new Errores();
//        {//Aqui va un bucle
//            String errorCode = null;
//            String errorDesc = null;
//            Error error = getError(errorCode, errorDesc);
//            errores.getError().add(error);
//        }
//        return errores;
//    }
//
//    public Contactos getContactos() {
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
//    private Contacto createContacto(String apellido1, String apellido2, String mail, String fax, String nombre,
//            String telefono) {
//        return getContacto(apellido1, apellido2, mail, fax, nombre, telefono);
//    }
//
//    public DocumentosAutorizacion getDocsAutorizacion() {
//        DocumentosAutorizacion docs = new DocumentosAutorizacion();
//        {//Aqui va un bucle
//            byte[] docAutCont = null;
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
//    public Servicios getServicios() throws I18NException {
//        tramitsI = tramitIEjb.select(TramitIServFields.TRAMITID.equal(tramitA.getTramitid()));
//
//        Servicios servicios = new Servicios();
//        for (TramitIServ tramitI : tramitsI) {
//
//            Servicio servicio = new Servicio();
//
//            String codigoCertificado = tramitI.getCodi();
//            Normas normas = new Normas();
//            {//Aqui va un bucle
//                Norma norma = new Norma();
//
//                String normaLegal = tramitI.getNorma();
//                Articulos articulos = new Articulos();
//                {//Aqui va un bucle
//                    String normaArticulo = tramitI.getArticles();
//                    articulos.getArticulo().add(normaArticulo);
//                }
//                es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Norma.Documento documento = new es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Norma.Documento();
//                {
//                    byte[] docNormaContenido = null;
//                    String docNormaDesc = null;
//                    String docNormaEnlace = null;
//                    String docNormaNombre = null;
//
//                    documento.setContenido(docNormaContenido);
//                    documento.setDescripcion(docNormaDesc);
//                    documento.setEnlace(docNormaEnlace);
//                    documento.setNombre(docNormaNombre);
//                }
//                norma.setNormaLegal(normaLegal);
//                norma.setArticulos(articulos);
//                norma.setDocumento(documento);
//                normas.getNorma().add(norma);
//            }
//            servicio.setCodigoCertificado(codigoCertificado);
//            servicio.setNormas(normas);
//
//            servicios.getServicio().add(servicio);
//        }
//        return servicios;
//    }
//
//    public es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Procedimiento getProcedimiento(
//            String automProcEsp, Integer claseTramitProcEsp, String codiProcEsp, String nomProcEsp, String descProcEsp,
//            String obsProcEsp, String periProcEsp, Integer petsEstimadasProcEsp, XMLGregorianCalendar fechaCaducidad,
//            Consentimiento cons, DocumentosAutorizacion docs, Servicios servicios) {
//        es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Procedimiento proc = new es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Procedimiento();
//        {
//
//            proc.setAutomatizado(automProcEsp);
//            proc.setClaseTramite(claseTramitProcEsp);
//            proc.setCodigo(codiProcEsp);
//            proc.setDescripcion(descProcEsp);
//            proc.setNombre(nomProcEsp);
//            proc.setObservaciones(obsProcEsp);
//            proc.setPeriodico(periProcEsp);
//            proc.setPeticionesEstimadas(petsEstimadasProcEsp);
//            proc.setFechaCaducidad(fechaCaducidad);
//            proc.setConsentimiento(cons);
//            proc.setDocumentosAutorizacion(docs);
//            proc.setServicios(servicios);
//        }
//        return proc;
//    }
//
//    public DocumentoAutorizacion getDocumentoAutorizacion(byte[] docAutCont, String docAutdesc, String docAutName,
//            String docAutType) {
//        DocumentoAutorizacion docAut = new DocumentoAutorizacion();
//        docAut.setContenido(docAutCont);
//        docAut.setDescripcion(docAutdesc);
//        docAut.setNombre(docAutName);
//        docAut.setTipo(docAutType);
//        return docAut;
//    }
//
//    public Contacto getContacto(String contactoApe1, String contactoApe2, String contactoMail, String contactoFax,
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
//    public Error getError(String errorCode, String errorDesc) {
//        Error error = new Error();
//
//        error.setCodigo(errorCode);
//        error.setDescripcion(errorDesc);
//        return error;
//    }
//
//    public Incidencia getIncidencia(String mailInc, Long numInc, Long numSeg) {
//        Incidencia incidencia = new Incidencia();
//        incidencia.setEmail(mailInc);
//        incidencia.setNumeroIncidencia(numInc);
//        incidencia.setNumeroSeguimiento(numSeg);
//        return incidencia;
//    }
//
//    public Estado getEstado(String codigoEstado, String descEstado) {
//        Estado estado = new Estado();
//        estado.setCodigoEstado(codigoEstado);
//        estado.setDescripcion(descEstado);
//        return estado;
//    }
//
//    public Documento getDocumentoConsentimiento(byte[] conenido, String docDesc, String docName) {
//        Documento doc = new Documento();
//        {
//
//            doc.setContenido(conenido);
//            doc.setDescripcion(docDesc);
//            doc.setNombre(docName);
//        }
//        return doc;
//    }
//
//    public Consentimiento getConsentimiento(String enlace, String tipo, Documento doc) {
//        Consentimiento cons = new Consentimiento();
//        cons.setEnlace(enlace);
//        cons.setTipo(tipo);
//        cons.setDocumento(doc);
//        return cons;
//    }
//
//    ////////////////////////A PARTIR DE AQUI YA SON DATOS GENERICOS
//
//    public Solicitante getSolicitantDG(String CodigoUnidadTramitadora, String Consentimiento, String Finalidad,
//            String IdentificadorSolicitante, String IdExpediente, String NombreSolicitante, String UnidadTramitadora,
//            Funcionario funcionario, es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.Procedimiento proc) {
//
//        Solicitante solicitant = new Solicitante();
//        solicitant.setCodigoUnidadTramitadora(CodigoUnidadTramitadora);
//        solicitant.setConsentimiento(Consentimiento);
//        solicitant.setFinalidad(Finalidad);
//        solicitant.setIdentificadorSolicitante(IdentificadorSolicitante);
//        solicitant.setIdExpediente(IdExpediente);
//        solicitant.setNombreSolicitante(NombreSolicitante);
//        solicitant.setUnidadTramitadora(UnidadTramitadora);
//        solicitant.setFuncionario(funcionario);
//        solicitant.setProcedimiento(proc);
//        return solicitant;
//    }
//
//    public es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.Procedimiento getProcedimientoPeticion(String autom,
//            Integer claseTramit, String codiProc, String nomProc) {
//        es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.Procedimiento proc = new es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion.Procedimiento();
//        proc.setAutomatizado(autom);
//        proc.setClaseTramite(claseTramit);
//        proc.setCodProcedimiento(codiProc);
//        proc.setNombreProcedimiento(nomProc);
//        return proc;
//    }
//
//    public Funcionario getFuncionario(String nifFunc, String nomFunc) {
//        Funcionario func = new Funcionario();
//        func.setNifFuncionario(nifFunc);
//        func.setNombreCompletoFuncionario(nomFunc);
//        return func;
//    }
//
//    public Emisor getEmisor(String nifEmisor, String nombreEmisor) {
//        Emisor emi = new Emisor();
//        emi.setNifEmisor(nifEmisor);
//        emi.setNombreEmisor(nombreEmisor);
//        return emi;
//    }
//
//    public Transmision getTransmision(String codiCert, String fechaGen, String idSolicitud) {
//        Transmision transm = new Transmision();
//        transm.setCodigoCertificado(codiCert);
//        transm.setFechaGeneracion(fechaGen);
//        transm.setIdSolicitud(idSolicitud);
//        return transm;
//    }
//
//    public Titular getTitular(String titApe1, String titApe2, String titDocumentacion, String titNombre,
//            String titFullName, String titTipusDoc) {
//        Titular tit = new Titular();
//        tit.setApellido1(titApe1);
//        tit.setApellido2(titApe2);
//        tit.setDocumentacion(titDocumentacion);
//        tit.setNombre(titNombre);
//        tit.setNombreCompleto(titFullName);
//        tit.setTipoDocumentacion(titTipusDoc);
//        return tit;
//    }
//
//    public String toFullName(String nom, String l1, String l2) {
//        String fullName = nom + " " + l1 + (l2 == "" ? "" : " " + l2);
//        return fullName;
//    }
}
