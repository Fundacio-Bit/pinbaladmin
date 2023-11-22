//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.22 a las 02:37:00 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.peticion;

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

    @XmlElement(name = "Contactos", required = true)
    protected Contactos contactos;
    @XmlElement(name = "Procedimiento", required = true)
    protected Procedimiento2 procedimiento;

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
     *     {@link Procedimiento2 }
     *     
     */
    public Procedimiento2 getProcedimiento() {
        return procedimiento;
    }

    /**
     * Define el valor de la propiedad procedimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Procedimiento2 }
     *     
     */
    public void setProcedimiento(Procedimiento2 value) {
        this.procedimiento = value;
    }

}
