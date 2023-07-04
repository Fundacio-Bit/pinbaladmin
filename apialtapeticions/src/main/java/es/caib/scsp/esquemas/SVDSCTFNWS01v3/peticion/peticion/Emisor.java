
package es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.peticion;

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
@XmlRootElement(name = "Emisor")
public class Emisor {

    @XmlElement(name = "NifEmisor", required = true)
    protected String nifEmisor;
    @XmlElement(name = "NombreEmisor", required = true)
    protected String nombreEmisor;

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
     *     {@link String }
     *     
     */
    public String getNombreEmisor() {
        return nombreEmisor;
    }

    /**
     * Define el valor de la propiedad nombreEmisor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEmisor(String value) {
        this.nombreEmisor = value;
    }

}
