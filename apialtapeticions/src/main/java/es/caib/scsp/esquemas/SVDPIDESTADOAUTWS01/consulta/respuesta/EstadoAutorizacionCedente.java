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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Cedente"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}NombreServicio"/&gt;
 *         &lt;element name="Estado" type="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}EstadoServicioCedente"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Descripcion"/&gt;
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
@XmlRootElement(name = "EstadoAutorizacionCedente")
public class EstadoAutorizacionCedente {

    @XmlElement(name = "Cedente", required = true)
    protected String cedente;
    @XmlElement(name = "NombreServicio", required = true)
    protected String nombreServicio;
    @XmlElement(name = "Estado")
    protected int estado;
    @XmlElement(name = "Descripcion", required = true)
    protected String descripcion;

    /**
     * Obtiene el valor de la propiedad cedente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedente() {
        return cedente;
    }

    /**
     * Define el valor de la propiedad cedente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedente(String value) {
        this.cedente = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreServicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreServicio() {
        return nombreServicio;
    }

    /**
     * Define el valor de la propiedad nombreServicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreServicio(String value) {
        this.nombreServicio = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     */
    public void setEstado(int value) {
        this.estado = value;
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

}
