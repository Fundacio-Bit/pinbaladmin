//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.15 a las 12:17:54 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos package. 
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

    private final static QName _DatosEspecificos_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosEspecificos");
    private final static QName _CodigoEstado_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoEstado");
    private final static QName _LiteralError_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "LiteralError");
    private final static QName _ClaseTramite_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "ClaseTramite");
    private final static QName _Nombre_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Nombre");
    private final static QName _Periodico_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Periodico");
    private final static QName _FechaCaducidad_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaCaducidad");
    private final static QName _Automatizado_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Automatizado");
    private final static QName _Tipo_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Tipo");
    private final static QName _Enlace_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Enlace");
    private final static QName _Descripcion_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Descripcion");
    private final static QName _NormaLegal_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NormaLegal");
    private final static QName _Articulo_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Articulo");
    private final static QName _CodigoCertificado_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoCertificado");
    private final static QName _Cedente_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Cedente");
    private final static QName _NombreServicio_QNAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NombreServicio");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DatosEspecificos }
     * 
     */
    public DatosEspecificos createDatosEspecificos() {
        return new DatosEspecificos();
    }

    /**
     * Create an instance of {@link Retorno }
     * 
     */
    public Retorno createRetorno() {
        return new Retorno();
    }

    /**
     * Create an instance of {@link Estado }
     * 
     */
    public Estado createEstado() {
        return new Estado();
    }

    /**
     * Create an instance of {@link Procedimiento }
     * 
     */
    public Procedimiento createProcedimiento() {
        return new Procedimiento();
    }

    /**
     * Create an instance of {@link Consentimiento }
     * 
     */
    public Consentimiento createConsentimiento() {
        return new Consentimiento();
    }

    /**
     * Create an instance of {@link EstadoProcedimiento }
     * 
     */
    public EstadoProcedimiento createEstadoProcedimiento() {
        return new EstadoProcedimiento();
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
     * Create an instance of {@link Norma }
     * 
     */
    public Norma createNorma() {
        return new Norma();
    }

    /**
     * Create an instance of {@link Articulos }
     * 
     */
    public Articulos createArticulos() {
        return new Articulos();
    }

    /**
     * Create an instance of {@link EstadoAutorizacion }
     * 
     */
    public EstadoAutorizacion createEstadoAutorizacion() {
        return new EstadoAutorizacion();
    }

    /**
     * Create an instance of {@link EstadoAutorizacionCedentes }
     * 
     */
    public EstadoAutorizacionCedentes createEstadoAutorizacionCedentes() {
        return new EstadoAutorizacionCedentes();
    }

    /**
     * Create an instance of {@link EstadoAutorizacionCedente }
     * 
     */
    public EstadoAutorizacionCedente createEstadoAutorizacionCedente() {
        return new EstadoAutorizacionCedente();
    }

    /**
     * Create an instance of {@link Consulta }
     * 
     */
    public Consulta createConsulta() {
        return new Consulta();
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
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "CodigoEstado")
    public JAXBElement<String> createCodigoEstado(String value) {
        return new JAXBElement<String>(_CodigoEstado_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "LiteralError")
    public JAXBElement<String> createLiteralError(String value) {
        return new JAXBElement<String>(_LiteralError_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Tipo")
    public JAXBElement<String> createTipo(String value) {
        return new JAXBElement<String>(_Tipo_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Descripcion")
    public JAXBElement<String> createDescripcion(String value) {
        return new JAXBElement<String>(_Descripcion_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "Cedente")
    public JAXBElement<String> createCedente(String value) {
        return new JAXBElement<String>(_Cedente_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", name = "NombreServicio")
    public JAXBElement<String> createNombreServicio(String value) {
        return new JAXBElement<String>(_NombreServicio_QNAME, String.class, null, value);
    }

}
