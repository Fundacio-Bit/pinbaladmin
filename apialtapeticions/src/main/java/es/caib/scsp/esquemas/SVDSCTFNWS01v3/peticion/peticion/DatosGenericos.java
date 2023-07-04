
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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Emisor"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Solicitante"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Titular" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Transmision"/&gt;
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
@XmlRootElement(name = "DatosGenericos")
public class DatosGenericos {

    @XmlElement(name = "Emisor", required = true)
    protected Emisor emisor;
    @XmlElement(name = "Solicitante", required = true)
    protected Solicitante solicitante;
    @XmlElement(name = "Titular")
    protected Titular titular;
    @XmlElement(name = "Transmision", required = true)
    protected Transmision transmision;

    /**
     * Obtiene el valor de la propiedad emisor.
     * 
     * @return
     *     possible object is
     *     {@link Emisor }
     *     
     */
    public Emisor getEmisor() {
        return emisor;
    }

    /**
     * Define el valor de la propiedad emisor.
     * 
     * @param value
     *     allowed object is
     *     {@link Emisor }
     *     
     */
    public void setEmisor(Emisor value) {
        this.emisor = value;
    }

    /**
     * Obtiene el valor de la propiedad solicitante.
     * 
     * @return
     *     possible object is
     *     {@link Solicitante }
     *     
     */
    public Solicitante getSolicitante() {
        return solicitante;
    }

    /**
     * Define el valor de la propiedad solicitante.
     * 
     * @param value
     *     allowed object is
     *     {@link Solicitante }
     *     
     */
    public void setSolicitante(Solicitante value) {
        this.solicitante = value;
    }

    /**
     * Obtiene el valor de la propiedad titular.
     * 
     * @return
     *     possible object is
     *     {@link Titular }
     *     
     */
    public Titular getTitular() {
        return titular;
    }

    /**
     * Define el valor de la propiedad titular.
     * 
     * @param value
     *     allowed object is
     *     {@link Titular }
     *     
     */
    public void setTitular(Titular value) {
        this.titular = value;
    }

    /**
     * Obtiene el valor de la propiedad transmision.
     * 
     * @return
     *     possible object is
     *     {@link Transmision }
     *     
     */
    public Transmision getTransmision() {
        return transmision;
    }

    /**
     * Define el valor de la propiedad transmision.
     * 
     * @param value
     *     allowed object is
     *     {@link Transmision }
     *     
     */
    public void setTransmision(Transmision value) {
        this.transmision = value;
    }

}
