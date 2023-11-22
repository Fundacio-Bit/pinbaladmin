//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.15 a las 12:17:56 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.respuesta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Normas"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}CodigoCertificado"/&gt;
 *         &lt;element name="Nombre" type="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}NombreServicio"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}EstadoAutorizacion"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}EstadoAutorizacionCedentes" minOccurs="0"/&gt;
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
@XmlRootElement(name = "Servicio")
public class Servicio {

    @XmlElement(name = "Normas", required = true)
    protected Normas normas;
    @XmlElement(name = "CodigoCertificado", required = true)
    protected String codigoCertificado;
    @XmlElement(name = "Nombre", required = true)
    protected String nombre;
    @XmlElement(name = "EstadoAutorizacion", required = true)
    protected EstadoAutorizacion estadoAutorizacion;
    @XmlElement(name = "EstadoAutorizacionCedentes")
    protected EstadoAutorizacionCedentes estadoAutorizacionCedentes;

    /**
     * Normas legales que justifican la autorización para el envío de peticiones al servicio
     * 
     * @return
     *     possible object is
     *     {@link Normas }
     *     
     */
    public Normas getNormas() {
        return normas;
    }

    /**
     * Define el valor de la propiedad normas.
     * 
     * @param value
     *     allowed object is
     *     {@link Normas }
     *     
     */
    public void setNormas(Normas value) {
        this.normas = value;
    }

    /**
     * Código de certificado
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCertificado() {
        return codigoCertificado;
    }

    /**
     * Define el valor de la propiedad codigoCertificado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCertificado(String value) {
        this.codigoCertificado = value;
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
     * Estado de la autorización del servicio
     * 
     * @return
     *     possible object is
     *     {@link EstadoAutorizacion }
     *     
     */
    public EstadoAutorizacion getEstadoAutorizacion() {
        return estadoAutorizacion;
    }

    /**
     * Define el valor de la propiedad estadoAutorizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoAutorizacion }
     *     
     */
    public void setEstadoAutorizacion(EstadoAutorizacion value) {
        this.estadoAutorizacion = value;
    }

    /**
     * Estado de la autorización del servicio en cada cedente
     * 
     * @return
     *     possible object is
     *     {@link EstadoAutorizacionCedentes }
     *     
     */
    public EstadoAutorizacionCedentes getEstadoAutorizacionCedentes() {
        return estadoAutorizacionCedentes;
    }

    /**
     * Define el valor de la propiedad estadoAutorizacionCedentes.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoAutorizacionCedentes }
     *     
     */
    public void setEstadoAutorizacionCedentes(EstadoAutorizacionCedentes value) {
        this.estadoAutorizacionCedentes = value;
    }

}
