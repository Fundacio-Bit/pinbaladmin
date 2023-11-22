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
 *       &lt;all&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}IdPeticion"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}NumElementos"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}TimeStamp"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}Estado" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}CodigoCertificado"/&gt;
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
@XmlRootElement(name = "Atributos", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion")
public class Atributos {

    @XmlElement(name = "IdPeticion", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", required = true)
    protected String idPeticion;
    @XmlElement(name = "NumElementos", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion")
    protected int numElementos;
    @XmlElement(name = "TimeStamp", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", required = true)
    protected String timeStamp;
    @XmlElement(name = "Estado", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion")
    protected Estado estado;
    @XmlElementRef(name = "CodigoCertificado", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", type = CodigoCertificadoPET.class)
    protected CodigoCertificadoPET codigoCertificado;

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
     *     {@link CodigoCertificadoPET }
     *     
     */
    public CodigoCertificadoPET getCodigoCertificado() {
        return codigoCertificado;
    }

    /**
     * Define el valor de la propiedad codigoCertificado.
     * 
     * @param value
     *     allowed object is
     *     {@link CodigoCertificadoPET }
     *     
     */
    public void setCodigoCertificado(CodigoCertificadoPET value) {
        this.codigoCertificado = value;
    }

}