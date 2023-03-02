
package es.caib.sistra.ws.v2.model.documentoconsulta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocumentoConsulta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentoConsulta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoRDS" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="claveRDS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modelo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="plantilla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contenidoFichero" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="nombreFichero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlAcceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlNuevaVentana" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoConsulta", propOrder = {
    "tipoDocumento",
    "nombreDocumento",
    "codigoRDS",
    "claveRDS",
    "xml",
    "modelo",
    "version",
    "plantilla",
    "contenidoFichero",
    "nombreFichero",
    "urlAcceso",
    "urlNuevaVentana"
})
public class DocumentoConsulta {

    @XmlElement(required = true)
    protected String tipoDocumento;
    @XmlElement(required = true)
    protected String nombreDocumento;
    @XmlElement(nillable = true)
    protected Long codigoRDS;
    @XmlElement(nillable = true)
    protected String claveRDS;
    @XmlElement(nillable = true)
    protected String xml;
    @XmlElement(nillable = true)
    protected String modelo;
    @XmlElement(nillable = true)
    protected Integer version;
    @XmlElement(nillable = true)
    protected String plantilla;
    @XmlElement(nillable = true)
    protected byte[] contenidoFichero;
    @XmlElement(nillable = true)
    protected String nombreFichero;
    @XmlElement(nillable = true)
    protected String urlAcceso;
    @XmlElement(nillable = true)
    protected Boolean urlNuevaVentana;

    /**
     * Gets the value of the tipoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the value of the tipoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocumento(String value) {
        this.tipoDocumento = value;
    }

    /**
     * Gets the value of the nombreDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * Sets the value of the nombreDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreDocumento(String value) {
        this.nombreDocumento = value;
    }

    /**
     * Gets the value of the codigoRDS property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodigoRDS() {
        return codigoRDS;
    }

    /**
     * Sets the value of the codigoRDS property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodigoRDS(Long value) {
        this.codigoRDS = value;
    }

    /**
     * Gets the value of the claveRDS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveRDS() {
        return claveRDS;
    }

    /**
     * Sets the value of the claveRDS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveRDS(String value) {
        this.claveRDS = value;
    }

    /**
     * Gets the value of the xml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXml() {
        return xml;
    }

    /**
     * Sets the value of the xml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXml(String value) {
        this.xml = value;
    }

    /**
     * Gets the value of the modelo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Sets the value of the modelo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelo(String value) {
        this.modelo = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersion(Integer value) {
        this.version = value;
    }

    /**
     * Gets the value of the plantilla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlantilla() {
        return plantilla;
    }

    /**
     * Sets the value of the plantilla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlantilla(String value) {
        this.plantilla = value;
    }

    /**
     * Gets the value of the contenidoFichero property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getContenidoFichero() {
        return contenidoFichero;
    }

    /**
     * Sets the value of the contenidoFichero property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setContenidoFichero(byte[] value) {
        this.contenidoFichero = value;
    }

    /**
     * Gets the value of the nombreFichero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreFichero() {
        return nombreFichero;
    }

    /**
     * Sets the value of the nombreFichero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreFichero(String value) {
        this.nombreFichero = value;
    }

    /**
     * Gets the value of the urlAcceso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlAcceso() {
        return urlAcceso;
    }

    /**
     * Sets the value of the urlAcceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlAcceso(String value) {
        this.urlAcceso = value;
    }

    /**
     * Gets the value of the urlNuevaVentana property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUrlNuevaVentana() {
        return urlNuevaVentana;
    }

    /**
     * Sets the value of the urlNuevaVentana property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUrlNuevaVentana(Boolean value) {
        this.urlNuevaVentana = value;
    }

}
