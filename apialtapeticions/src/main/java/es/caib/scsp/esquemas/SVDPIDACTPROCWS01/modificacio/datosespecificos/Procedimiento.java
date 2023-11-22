//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.22 a las 02:36:58 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}PeticionesEstimadas"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Codigo"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}ClaseTramite" minOccurs="0"/&gt;
 *         &lt;element name="Nombre" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="500"/&gt;
 *               &lt;whiteSpace value="collapse"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Descripcion" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;whiteSpace value="collapse"/&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="500"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Periodico" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}FechaCaducidad" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Automatizado" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Consentimiento"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Observaciones" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Servicios"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}DocumentosAutorizacion" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Procedimiento")
public class Procedimiento {

    @XmlElement(name = "PeticionesEstimadas")
    protected int peticionesEstimadas;
    @XmlElement(name = "Codigo", required = true)
    protected String codigo;
    @XmlElement(name = "ClaseTramite")
    protected Integer claseTramite;
    @XmlElement(name = "Nombre")
    protected String nombre;
    @XmlElement(name = "Descripcion")
    protected String descripcion;
    @XmlElement(name = "Periodico")
    protected String periodico;
    @XmlElement(name = "FechaCaducidad")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaCaducidad;
    @XmlElement(name = "Automatizado")
    protected String automatizado;
    @XmlElement(name = "Consentimiento", required = true)
    protected Consentimiento consentimiento;
    @XmlElement(name = "Observaciones")
    protected String observaciones;
    @XmlElement(name = "Servicios", required = true)
    protected Servicios servicios;
    @XmlElement(name = "DocumentosAutorizacion")
    protected DocumentosAutorizacion documentosAutorizacion;

    /**
     * Obtiene el valor de la propiedad peticionesEstimadas.
     * 
     */
    public int getPeticionesEstimadas() {
        return peticionesEstimadas;
    }

    /**
     * Define el valor de la propiedad peticionesEstimadas.
     * 
     */
    public void setPeticionesEstimadas(int value) {
        this.peticionesEstimadas = value;
    }

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad claseTramite.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getClaseTramite() {
        return claseTramite;
    }

    /**
     * Define el valor de la propiedad claseTramite.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setClaseTramite(Integer value) {
        this.claseTramite = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad periodico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodico() {
        return periodico;
    }

    /**
     * Define el valor de la propiedad periodico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodico(String value) {
        this.periodico = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaCaducidad.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * Define el valor de la propiedad fechaCaducidad.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCaducidad(XMLGregorianCalendar value) {
        this.fechaCaducidad = value;
    }

    /**
     * Obtiene el valor de la propiedad automatizado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutomatizado() {
        return automatizado;
    }

    /**
     * Define el valor de la propiedad automatizado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutomatizado(String value) {
        this.automatizado = value;
    }

    /**
     * Obtiene el valor de la propiedad consentimiento.
     * 
     * @return
     *     possible object is
     *     {@link Consentimiento }
     *     
     */
    public Consentimiento getConsentimiento() {
        return consentimiento;
    }

    /**
     * Define el valor de la propiedad consentimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Consentimiento }
     *     
     */
    public void setConsentimiento(Consentimiento value) {
        this.consentimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad observaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Define el valor de la propiedad observaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservaciones(String value) {
        this.observaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad servicios.
     * 
     * @return
     *     possible object is
     *     {@link Servicios }
     *     
     */
    public Servicios getServicios() {
        return servicios;
    }

    /**
     * Define el valor de la propiedad servicios.
     * 
     * @param value
     *     allowed object is
     *     {@link Servicios }
     *     
     */
    public void setServicios(Servicios value) {
        this.servicios = value;
    }

    /**
     * Obtiene el valor de la propiedad documentosAutorizacion.
     * 
     * @return
     *     possible object is
     *     {@link DocumentosAutorizacion }
     *     
     */
    public DocumentosAutorizacion getDocumentosAutorizacion() {
        return documentosAutorizacion;
    }

    /**
     * Define el valor de la propiedad documentosAutorizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentosAutorizacion }
     *     
     */
    public void setDocumentosAutorizacion(DocumentosAutorizacion value) {
        this.documentosAutorizacion = value;
    }

}
