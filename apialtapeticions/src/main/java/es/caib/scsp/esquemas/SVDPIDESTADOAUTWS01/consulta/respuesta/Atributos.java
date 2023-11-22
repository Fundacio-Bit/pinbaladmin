//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.15 a las 12:17:56 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.respuesta;

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
 *       &lt;all&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}IdPeticion"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}NumElementos"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}TimeStamp"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}Estado"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}CodigoCertificado"/&gt;
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
@XmlRootElement(name = "Atributos", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta")
public class Atributos {

    @XmlElement(name = "IdPeticion", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", required = true)
    protected String idPeticion;
    @XmlElement(name = "NumElementos", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta")
    protected int numElementos;
    @XmlElement(name = "TimeStamp", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", required = true)
    protected String timeStamp;
    @XmlElement(name = "Estado", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", required = true)
    protected Estado estado;
    @XmlElementRef(name = "CodigoCertificado", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", type = CodigoCertificadoRES.class)
    protected CodigoCertificadoRES codigoCertificado;

    /**
     * Obtiene el valor de la propiedad idPeticion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPeticion() {
        return idPeticion;
    }

    /**
     * Define el valor de la propiedad idPeticion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPeticion(String value) {
        this.idPeticion = value;
    }

    /**
     * Obtiene el valor de la propiedad numElementos.
     * 
     */
    public int getNumElementos() {
        return numElementos;
    }

    /**
     * Define el valor de la propiedad numElementos.
     * 
     */
    public void setNumElementos(int value) {
        this.numElementos = value;
    }

    /**
     * Obtiene el valor de la propiedad timeStamp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Define el valor de la propiedad timeStamp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStamp(String value) {
        this.timeStamp = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link Estado }
     *     
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link Estado }
     *     
     */
    public void setEstado(Estado value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoCertificado.
     * 
     * @return
     *     possible object is
     *     {@link CodigoCertificadoRES }
     *     
     */
    public CodigoCertificadoRES getCodigoCertificado() {
        return codigoCertificado;
    }

    /**
     * Define el valor de la propiedad codigoCertificado.
     * 
     * @param value
     *     allowed object is
     *     {@link CodigoCertificadoRES }
     *     
     */
    public void setCodigoCertificado(CodigoCertificadoRES value) {
        this.codigoCertificado = value;
    }

}