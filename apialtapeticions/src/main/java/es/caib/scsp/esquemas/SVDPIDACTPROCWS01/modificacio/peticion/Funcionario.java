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
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}NombreCompletoFuncionario"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}NifFuncionario"/&gt;
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
    "nombreCompletoFuncionario",
    "nifFuncionario"
})
@XmlRootElement(name = "Funcionario", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion")
public class Funcionario {

    @XmlElement(name = "NombreCompletoFuncionario", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", required = true)
    protected String nombreCompletoFuncionario;
    @XmlElement(name = "NifFuncionario", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", required = true)
    protected String nifFuncionario;

    /**
     * Obtiene el valor de la propiedad nombreCompletoFuncionario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCompletoFuncionario() {
        return nombreCompletoFuncionario;
    }

    /**
     * Define el valor de la propiedad nombreCompletoFuncionario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCompletoFuncionario(String value) {
        this.nombreCompletoFuncionario = value;
    }

    /**
     * Obtiene el valor de la propiedad nifFuncionario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNifFuncionario() {
        return nifFuncionario;
    }

    /**
     * Define el valor de la propiedad nifFuncionario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNifFuncionario(String value) {
        this.nifFuncionario = value;
    }

}