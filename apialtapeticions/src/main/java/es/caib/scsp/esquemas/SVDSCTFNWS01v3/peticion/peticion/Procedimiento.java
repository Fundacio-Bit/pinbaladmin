
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
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}CodProcedimiento"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}NombreProcedimiento"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Automatizado" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}ClaseTramite" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "codProcedimiento",
    "nombreProcedimiento",
    "automatizado",
    "claseTramite"
})
@XmlRootElement(name = "Procedimiento")
public class Procedimiento {

    @XmlElement(name = "CodProcedimiento", required = true)
    protected String codProcedimiento;
    @XmlElement(name = "NombreProcedimiento", required = true)
    protected String nombreProcedimiento;
    @XmlElement(name = "Automatizado")
    protected String automatizado;
    @XmlElement(name = "ClaseTramite")
    protected Integer claseTramite;

    /**
     * Obtiene el valor de la propiedad codProcedimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodProcedimiento() {
        return codProcedimiento;
    }

    /**
     * Define el valor de la propiedad codProcedimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodProcedimiento(String value) {
        this.codProcedimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreProcedimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreProcedimiento() {
        return nombreProcedimiento;
    }

    /**
     * Define el valor de la propiedad nombreProcedimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreProcedimiento(String value) {
        this.nombreProcedimiento = value;
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

}
