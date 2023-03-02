
package es.caib.sistra.ws.v2.model.valoresdominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValoresDominio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValoresDominio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descripcionError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="filas" type="{urn:es:caib:sistra:ws:v2:model:ValoresDominio}filas" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValoresDominio", propOrder = {
    "descripcionError",
    "error",
    "filas"
})
public class ValoresDominio {

    @XmlElement(nillable = true)
    protected String descripcionError;
    @XmlElement(defaultValue = "false")
    protected boolean error;
    @XmlElement(nillable = true)
    protected Filas filas;

    /**
     * Gets the value of the descripcionError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionError() {
        return descripcionError;
    }

    /**
     * Sets the value of the descripcionError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionError(String value) {
        this.descripcionError = value;
    }

    /**
     * Gets the value of the error property.
     * 
     */
    public boolean isError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     */
    public void setError(boolean value) {
        this.error = value;
    }

    /**
     * Gets the value of the filas property.
     * 
     * @return
     *     possible object is
     *     {@link Filas }
     *     
     */
    public Filas getFilas() {
        return filas;
    }

    /**
     * Sets the value of the filas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Filas }
     *     
     */
    public void setFilas(Filas value) {
        this.filas = value;
    }

}
