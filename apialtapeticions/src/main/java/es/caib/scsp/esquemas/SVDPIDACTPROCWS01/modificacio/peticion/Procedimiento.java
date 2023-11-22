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
@XmlRootElement(name = "Procedimiento", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion")
public class Procedimiento {

    @XmlElement(name = "CodProcedimiento", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", required = true)
    protected String codProcedimiento;
    @XmlElement(name = "NombreProcedimiento", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", required = true)
    protected String nombreProcedimiento;
    @XmlElementRef(name = "Automatizado", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", type = AutomatizadoPET.class, required = false)
    protected AutomatizadoPET automatizado;
    @XmlElementRef(name = "ClaseTramite", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", type = ClaseTramitePET.class, required = false)
    protected ClaseTramitePET claseTramite;

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
     *     {@link AutomatizadoPET }
     *     
     */
    public AutomatizadoPET getAutomatizado() {
        return automatizado;
    }

    /**
     * Define el valor de la propiedad automatizado.
     * 
     * @param value
     *     allowed object is
     *     {@link AutomatizadoPET }
     *     
     */
    public void setAutomatizado(AutomatizadoPET value) {
        this.automatizado = value;
    }

    /**
     * Obtiene el valor de la propiedad claseTramite.
     * 
     * @return
     *     possible object is
     *     {@link ClaseTramitePET }
     *     
     */
    public ClaseTramitePET getClaseTramite() {
        return claseTramite;
    }

    /**
     * Define el valor de la propiedad claseTramite.
     * 
     * @param value
     *     allowed object is
     *     {@link ClaseTramitePET }
     *     
     */
    public void setClaseTramite(ClaseTramitePET value) {
        this.claseTramite = value;
    }

}
