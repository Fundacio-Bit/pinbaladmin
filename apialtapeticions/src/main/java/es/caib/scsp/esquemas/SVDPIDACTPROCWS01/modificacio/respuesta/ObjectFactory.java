//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.22 a las 02:37:02 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.respuesta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.respuesta package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IdPeticion_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "IdPeticion");
    private final static QName _NumElementos_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "NumElementos");
    private final static QName _TimeStamp_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "TimeStamp");
    private final static QName _CodigoEstadoSecundario_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "CodigoEstadoSecundario");
    private final static QName _TiempoEstimadoRespuesta_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "TiempoEstimadoRespuesta");
    private final static QName _Consentimiento_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "Consentimiento");
    private final static QName _NifEmisor_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "NifEmisor");
    private final static QName _IdentificadorSolicitante_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "IdentificadorSolicitante");
    private final static QName _NombreSolicitante_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "NombreSolicitante");
    private final static QName _UnidadTramitadora_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "UnidadTramitadora");
    private final static QName _CodigoUnidadTramitadora_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "CodigoUnidadTramitadora");
    private final static QName _CodProcedimiento_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "CodProcedimiento");
    private final static QName _NombreProcedimiento_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "NombreProcedimiento");
    private final static QName _Finalidad_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "Finalidad");
    private final static QName _NombreCompletoFuncionario_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "NombreCompletoFuncionario");
    private final static QName _NifFuncionario_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "NifFuncionario");
    private final static QName _IdExpediente_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "IdExpediente");
    private final static QName _TipoDocumentacion_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "TipoDocumentacion");
    private final static QName _Documentacion_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "Documentacion");
    private final static QName _NombreCompleto_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "NombreCompleto");
    private final static QName _IdSolicitud_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "IdSolicitud");
    private final static QName _IdTransmision_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "IdTransmision");
    private final static QName _FechaGeneracion_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "FechaGeneracion");
    private final static QName _DatosEspecificos_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosEspecificos");
    private final static QName _Nombre_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Nombre");
    private final static QName _Apellido1_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Apellido1");
    private final static QName _Apellido2_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Apellido2");
    private final static QName _Email_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Email");
    private final static QName _Telefono_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Telefono");
    private final static QName _Fax_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Fax");
    private final static QName _PeticionesEstimadas_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "PeticionesEstimadas");
    private final static QName _Codigo_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Codigo");
    private final static QName _ClaseTramite_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "ClaseTramite");
    private final static QName _Periodico_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Periodico");
    private final static QName _FechaCaducidad_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaCaducidad");
    private final static QName _Automatizado_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Automatizado");
    private final static QName _Enlace_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Enlace");
    private final static QName _Observaciones_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Observaciones");
    private final static QName _NormaLegal_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NormaLegal");
    private final static QName _Articulo_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Articulo");
    private final static QName _CodigoCertificado_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoCertificado");
    private final static QName _Descripcion_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Descripcion");
    private final static QName _Contenido_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Contenido");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.respuesta
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Consentimiento }
     * 
     */
    public Consentimiento createConsentimiento() {
        return new Consentimiento();
    }

    /**
     * Create an instance of {@link Norma }
     * 
     */
    public Norma createNorma() {
        return new Norma();
    }

    /**
     * Create an instance of {@link Atributos }
     * 
     */
    public Atributos createAtributos() {
        return new Atributos();
    }

    /**
     * Create an instance of {@link Estado }
     * 
     */
    public Estado createEstado() {
        return new Estado();
    }

    /**
     * Create an instance of {@link DatosGenericos }
     * 
     */
    public DatosGenericos createDatosGenericos() {
        return new DatosGenericos();
    }

    /**
     * Create an instance of {@link Emisor }
     * 
     */
    public Emisor createEmisor() {
        return new Emisor();
    }

    /**
     * Create an instance of {@link Solicitante }
     * 
     */
    public Solicitante createSolicitante() {
        return new Solicitante();
    }

    /**
     * Create an instance of {@link Procedimiento }
     * 
     */
    public Procedimiento createProcedimiento() {
        return new Procedimiento();
    }

    /**
     * Create an instance of {@link Funcionario }
     * 
     */
    public Funcionario createFuncionario() {
        return new Funcionario();
    }

    /**
     * Create an instance of {@link Titular }
     * 
     */
    public Titular createTitular() {
        return new Titular();
    }

    /**
     * Create an instance of {@link Transmision }
     * 
     */
    public Transmision createTransmision() {
        return new Transmision();
    }

    /**
     * Create an instance of {@link Respuesta }
     * 
     */
    public Respuesta createRespuesta() {
        return new Respuesta();
    }

    /**
     * Create an instance of {@link Transmisiones }
     * 
     */
    public Transmisiones createTransmisiones() {
        return new Transmisiones();
    }

    /**
     * Create an instance of {@link TransmisionDatos }
     * 
     */
    public TransmisionDatos createTransmisionDatos() {
        return new TransmisionDatos();
    }

    /**
     * Create an instance of {@link DatosEspecificos }
     * 
     */
    public DatosEspecificos createDatosEspecificos() {
        return new DatosEspecificos();
    }

    /**
     * Create an instance of {@link Respuesta2 }
     * 
     */
    public Respuesta2 createRespuesta2() {
        return new Respuesta2();
    }

    /**
     * Create an instance of {@link Estado2 }
     * 
     */
    public Estado2 createEstado2() {
        return new Estado2();
    }

    /**
     * Create an instance of {@link Errores }
     * 
     */
    public Errores createErrores() {
        return new Errores();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link Solicitud }
     * 
     */
    public Solicitud createSolicitud() {
        return new Solicitud();
    }

    /**
     * Create an instance of {@link Contactos }
     * 
     */
    public Contactos createContactos() {
        return new Contactos();
    }

    /**
     * Create an instance of {@link Contacto }
     * 
     */
    public Contacto createContacto() {
        return new Contacto();
    }

    /**
     * Create an instance of {@link Procedimiento2 }
     * 
     */
    public Procedimiento2 createProcedimiento2() {
        return new Procedimiento2();
    }

    /**
     * Create an instance of {@link Consentimiento.Documento }
     * 
     */
    public Consentimiento.Documento createConsentimientoDocumento() {
        return new Consentimiento.Documento();
    }

    /**
     * Create an instance of {@link Servicios }
     * 
     */
    public Servicios createServicios() {
        return new Servicios();
    }

    /**
     * Create an instance of {@link Servicio }
     * 
     */
    public Servicio createServicio() {
        return new Servicio();
    }

    /**
     * Create an instance of {@link Normas }
     * 
     */
    public Normas createNormas() {
        return new Normas();
    }

    /**
     * Create an instance of {@link Norma.Documento }
     * 
     */
    public Norma.Documento createNormaDocumento() {
        return new Norma.Documento();
    }

    /**
     * Create an instance of {@link Articulos }
     * 
     */
    public Articulos createArticulos() {
        return new Articulos();
    }

    /**
     * Create an instance of {@link DocumentosAutorizacion }
     * 
     */
    public DocumentosAutorizacion createDocumentosAutorizacion() {
        return new DocumentosAutorizacion();
    }

    /**
     * Create an instance of {@link DocumentoAutorizacion }
     * 
     */
    public DocumentoAutorizacion createDocumentoAutorizacion() {
        return new DocumentoAutorizacion();
    }

    /**
     * Create an instance of {@link Apellido1RES }
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link Apellido1RES }
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "Apellido1")
    public Apellido1RES createApellido1RES(String value) {
        return new Apellido1RES(value);
    }

    /**
     * Create an instance of {@link Apellido2RES }
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link Apellido2RES }
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "Apellido2")
    public Apellido2RES createApellido2RES(String value) {
        return new Apellido2RES(value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "IdPeticion")
    public JAXBElement<String> createIdPeticion(String value) {
        return new JAXBElement<String>(_IdPeticion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "NumElementos")
    public JAXBElement<Integer> createNumElementos(Integer value) {
        return new JAXBElement<Integer>(_NumElementos_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "TimeStamp")
    public JAXBElement<String> createTimeStamp(String value) {
        return new JAXBElement<String>(_TimeStamp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link CodigoEstadoRES }
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link CodigoEstadoRES }
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "CodigoEstado")
    public CodigoEstadoRES createCodigoEstadoRES(String value) {
        return new CodigoEstadoRES(value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "CodigoEstadoSecundario")
    public JAXBElement<String> createCodigoEstadoSecundario(String value) {
        return new JAXBElement<String>(_CodigoEstadoSecundario_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link LiteralErrorRES }
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link LiteralErrorRES }
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "LiteralError")
    public LiteralErrorRES createLiteralErrorRES(String value) {
        return new LiteralErrorRES(value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "TiempoEstimadoRespuesta")
    public JAXBElement<Integer> createTiempoEstimadoRespuesta(Integer value) {
        return new JAXBElement<Integer>(_TiempoEstimadoRespuesta_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link CodigoCertificadoRES }
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link CodigoCertificadoRES }
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "CodigoCertificado")
    public CodigoCertificadoRES createCodigoCertificadoRES(String value) {
        return new CodigoCertificadoRES(value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "Consentimiento")
    public JAXBElement<String> createConsentimiento(String value) {
        return new JAXBElement<String>(_Consentimiento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "NifEmisor")
    public JAXBElement<String> createNifEmisor(String value) {
        return new JAXBElement<String>(_NifEmisor_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link NombreEmisorRES }
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link NombreEmisorRES }
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "NombreEmisor")
    public NombreEmisorRES createNombreEmisorRES(String value) {
        return new NombreEmisorRES(value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "IdentificadorSolicitante")
    public JAXBElement<String> createIdentificadorSolicitante(String value) {
        return new JAXBElement<String>(_IdentificadorSolicitante_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "NombreSolicitante")
    public JAXBElement<String> createNombreSolicitante(String value) {
        return new JAXBElement<String>(_NombreSolicitante_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "UnidadTramitadora")
    public JAXBElement<String> createUnidadTramitadora(String value) {
        return new JAXBElement<String>(_UnidadTramitadora_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "CodigoUnidadTramitadora")
    public JAXBElement<String> createCodigoUnidadTramitadora(String value) {
        return new JAXBElement<String>(_CodigoUnidadTramitadora_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "CodProcedimiento")
    public JAXBElement<String> createCodProcedimiento(String value) {
        return new JAXBElement<String>(_CodProcedimiento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "NombreProcedimiento")
    public JAXBElement<String> createNombreProcedimiento(String value) {
        return new JAXBElement<String>(_NombreProcedimiento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link AutomatizadoRES }
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link AutomatizadoRES }
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "Automatizado")
    public AutomatizadoRES createAutomatizadoRES(String value) {
        return new AutomatizadoRES(value);
    }

    /**
     * Create an instance of {@link ClaseTramiteRES }
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link ClaseTramiteRES }
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "ClaseTramite")
    public ClaseTramiteRES createClaseTramiteRES(Integer value) {
        return new ClaseTramiteRES(value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "Finalidad")
    public JAXBElement<String> createFinalidad(String value) {
        return new JAXBElement<String>(_Finalidad_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "NombreCompletoFuncionario")
    public JAXBElement<String> createNombreCompletoFuncionario(String value) {
        return new JAXBElement<String>(_NombreCompletoFuncionario_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "NifFuncionario")
    public JAXBElement<String> createNifFuncionario(String value) {
        return new JAXBElement<String>(_NifFuncionario_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "IdExpediente")
    public JAXBElement<String> createIdExpediente(String value) {
        return new JAXBElement<String>(_IdExpediente_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "TipoDocumentacion")
    public JAXBElement<String> createTipoDocumentacion(String value) {
        return new JAXBElement<String>(_TipoDocumentacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "Documentacion")
    public JAXBElement<String> createDocumentacion(String value) {
        return new JAXBElement<String>(_Documentacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "NombreCompleto")
    public JAXBElement<String> createNombreCompleto(String value) {
        return new JAXBElement<String>(_NombreCompleto_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link NombreRES }
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link NombreRES }
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "Nombre")
    public NombreRES createNombreRES(String value) {
        return new NombreRES(value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "IdSolicitud")
    public JAXBElement<String> createIdSolicitud(String value) {
        return new JAXBElement<String>(_IdSolicitud_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "IdTransmision")
    public JAXBElement<String> createIdTransmision(String value) {
        return new JAXBElement<String>(_IdTransmision_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", name = "FechaGeneracion")
    public JAXBElement<String> createFechaGeneracion(String value) {
        return new JAXBElement<String>(_FechaGeneracion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatosEspecificos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DatosEspecificos }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "DatosEspecificos")
    public JAXBElement<DatosEspecificos> createDatosEspecificos(DatosEspecificos value) {
        return new JAXBElement<DatosEspecificos>(_DatosEspecificos_QNAME, DatosEspecificos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Nombre")
    public JAXBElement<String> createNombre(String value) {
        return new JAXBElement<String>(_Nombre_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Apellido1")
    public JAXBElement<String> createApellido1(String value) {
        return new JAXBElement<String>(_Apellido1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Apellido2")
    public JAXBElement<String> createApellido2(String value) {
        return new JAXBElement<String>(_Apellido2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Email")
    public JAXBElement<String> createEmail(String value) {
        return new JAXBElement<String>(_Email_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Telefono")
    public JAXBElement<String> createTelefono(String value) {
        return new JAXBElement<String>(_Telefono_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Fax")
    public JAXBElement<String> createFax(String value) {
        return new JAXBElement<String>(_Fax_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "PeticionesEstimadas")
    public JAXBElement<Integer> createPeticionesEstimadas(Integer value) {
        return new JAXBElement<Integer>(_PeticionesEstimadas_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Codigo")
    public JAXBElement<String> createCodigo(String value) {
        return new JAXBElement<String>(_Codigo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "ClaseTramite")
    public JAXBElement<Integer> createClaseTramite(Integer value) {
        return new JAXBElement<Integer>(_ClaseTramite_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Periodico")
    public JAXBElement<String> createPeriodico(String value) {
        return new JAXBElement<String>(_Periodico_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "FechaCaducidad")
    public JAXBElement<XMLGregorianCalendar> createFechaCaducidad(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_FechaCaducidad_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Automatizado")
    public JAXBElement<String> createAutomatizado(String value) {
        return new JAXBElement<String>(_Automatizado_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Enlace")
    public JAXBElement<String> createEnlace(String value) {
        return new JAXBElement<String>(_Enlace_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Observaciones")
    public JAXBElement<String> createObservaciones(String value) {
        return new JAXBElement<String>(_Observaciones_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "NormaLegal")
    public JAXBElement<String> createNormaLegal(String value) {
        return new JAXBElement<String>(_NormaLegal_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Articulo")
    public JAXBElement<String> createArticulo(String value) {
        return new JAXBElement<String>(_Articulo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "CodigoCertificado")
    public JAXBElement<String> createCodigoCertificado(String value) {
        return new JAXBElement<String>(_CodigoCertificado_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Descripcion")
    public JAXBElement<String> createDescripcion(String value) {
        return new JAXBElement<String>(_Descripcion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Contenido")
    public JAXBElement<byte[]> createContenido(byte[] value) {
        return new JAXBElement<byte[]>(_Contenido_QNAME, byte[].class, null, ((byte[]) value));
    }

}
