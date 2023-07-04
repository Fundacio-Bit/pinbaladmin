
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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Asunto"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Consulta"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Contactos"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Procedimiento"/&gt;
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
@XmlRootElement(name = "Solicitud")
public class Solicitud {

    @XmlElement(name = "Asunto", required = true)
    protected String asunto;
    @XmlElement(name = "Consulta", required = true)
    protected String consulta;
    @XmlElement(name = "Contactos", required = true)
    protected Contactos contactos;
    @XmlElement(name = "Procedimiento", required = true)
    protected Procedimiento procedimiento;

    /**
     * Obtiene el valor de la propiedad asunto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Define el valor de la propiedad asunto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsunto(String value) {
        this.asunto = value;
    }

    /**
     * Obtiene el valor de la propiedad consulta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsulta() {
        return consulta;
    }

    /**
     * Define el valor de la propiedad consulta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsulta(String value) {
        this.consulta = value;
    }

    /**
     * Obtiene el valor de la propiedad contactos.
     * 
     * @return
     *     possible object is
     *     {@link Contactos }
     *     
     */
    public Contactos getContactos() {
        return contactos;
    }

    /**
     * Define el valor de la propiedad contactos.
     * 
     * @param value
     *     allowed object is
     *     {@link Contactos }
     *     
     */
    public void setContactos(Contactos value) {
        this.contactos = value;
    }

    /**
     * Obtiene el valor de la propiedad procedimiento.
     * 
     * @return
     *     possible object is
     *     {@link Procedimiento }
     *     
     */
    public Procedimiento getProcedimiento() {
        return procedimiento;
    }

    /**
     * Define el valor de la propiedad procedimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Procedimiento }
     *     
     */
    public void setProcedimiento(Procedimiento value) {
        this.procedimiento = value;
    }

}
