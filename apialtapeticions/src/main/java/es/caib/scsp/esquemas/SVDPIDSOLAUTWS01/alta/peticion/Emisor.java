//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.22 a las 03:49:22 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.peticion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}NifEmisor"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}NombreEmisor"/&gt;
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
@XmlRootElement(name = "Emisor", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion")
public class Emisor {

    @XmlElement(name = "NifEmisor", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", required = true)
    protected String nifEmisor;
    @XmlElementRef(name = "NombreEmisor", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", type = NombreEmisorPET.class)
    protected NombreEmisorPET nombreEmisor;

    /**
     * Obtiene el valor de la propiedad nifEmisor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNifEmisor() {
        return nifEmisor;
    }

    /**
     * Define el valor de la propiedad nifEmisor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNifEmisor(String value) {
        this.nifEmisor = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreEmisor.
     * 
     * @return
     *     possible object is
     *     {@link NombreEmisorPET }
     *     
     */
    public NombreEmisorPET getNombreEmisor() {
        return nombreEmisor;
    }

    /**
     * Define el valor de la propiedad nombreEmisor.
     * 
     * @param value
     *     allowed object is
     *     {@link NombreEmisorPET }
     *     
     */
    public void setNombreEmisor(NombreEmisorPET value) {
        this.nombreEmisor = value;
    }

}
