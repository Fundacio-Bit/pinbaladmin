
package es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos;

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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}NumeroIncidencia"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}NumeroSeguimiento"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Email"/&gt;
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
@XmlRootElement(name = "Incidencia")
public class Incidencia {

    @XmlElement(name = "NumeroIncidencia")
    protected long numeroIncidencia;
    @XmlElement(name = "NumeroSeguimiento")
    protected long numeroSeguimiento;
    @XmlElement(name = "Email", required = true)
    protected String email;

    /**
     * Obtiene el valor de la propiedad numeroIncidencia.
     * 
     */
    public long getNumeroIncidencia() {
        return numeroIncidencia;
    }

    /**
     * Define el valor de la propiedad numeroIncidencia.
     * 
     */
    public void setNumeroIncidencia(long value) {
        this.numeroIncidencia = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroSeguimiento.
     * 
     */
    public long getNumeroSeguimiento() {
        return numeroSeguimiento;
    }

    /**
     * Define el valor de la propiedad numeroSeguimiento.
     * 
     */
    public void setNumeroSeguimiento(long value) {
        this.numeroSeguimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

}
