//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.22 a las 03:49:21 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence maxOccurs="unbounded"&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Servicio"/&gt;
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
    "servicio"
})
@XmlRootElement(name = "Servicios")
public class Servicios {

    @XmlElement(name = "Servicio", required = true)
    protected List<Servicio> servicio;

    /**
     * Gets the value of the servicio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servicio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServicio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Servicio }
     * 
     * 
     */
    public List<Servicio> getServicio() {
        if (servicio == null) {
            servicio = new ArrayList<Servicio>();
        }
        return this.servicio;
    }

}