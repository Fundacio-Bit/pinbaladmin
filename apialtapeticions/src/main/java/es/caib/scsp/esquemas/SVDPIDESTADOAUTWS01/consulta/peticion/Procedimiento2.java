//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.15 a las 12:17:55 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.peticion;

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
 *         &lt;element name="Codigo" type="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}CodigoProcedimiento"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}ClaseTramite"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Nombre"/&gt;
 *         &lt;element name="Descripcion" type="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}DescripcionProcedimiento"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Periodico"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}FechaCaducidad" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Automatizado"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Consentimiento"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}EstadoProcedimiento"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Servicios"/&gt;
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
public class Procedimiento2 {

    @XmlElement(name = "Codigo", required = true)
    protected String codigo;
    @XmlElement(name = "ClaseTramite")
    protected int claseTramite;
    @XmlElement(name = "Nombre", required = true)
    protected String nombre;
    @XmlElement(name = "Descripcion", required = true)
    protected String descripcion;
    @XmlElement(name = "Periodico", required = true)
    protected String periodico;
    @XmlElement(name = "FechaCaducidad")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaCaducidad;
    @XmlElement(name = "Automatizado", required = true)
    protected String automatizado;
    @XmlElement(name = "Consentimiento", required = true)
    protected Consentimiento consentimiento;
    @XmlElement(name = "EstadoProcedimiento", required = true)
    protected EstadoProcedimiento estadoProcedimiento;
    @XmlElement(name = "Servicios", required = true)
    protected Servicios servicios;

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
     */
    public int getClaseTramite() {
        return claseTramite;
    }

    /**
     * Define el valor de la propiedad claseTramite.
     * 
     */
    public void setClaseTramite(int value) {
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
     * Estado de la autorización del procedimiento
     * 
     * @return
     *     possible object is
     *     {@link EstadoProcedimiento }
     *     
     */
    public EstadoProcedimiento getEstadoProcedimiento() {
        return estadoProcedimiento;
    }

    /**
     * Define el valor de la propiedad estadoProcedimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoProcedimiento }
     *     
     */
    public void setEstadoProcedimiento(EstadoProcedimiento value) {
        this.estadoProcedimiento = value;
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

}
