//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.22 a las 03:49:22 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.peticion;

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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}CodigoEstado" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}CodigoEstadoSecundario" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}LiteralError" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/peticion}TiempoEstimadoRespuesta" minOccurs="0"/&gt;
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
@XmlRootElement(name = "Estado", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion")
public class Estado {

    @XmlElementRef(name = "CodigoEstado", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", type = CodigoEstadoPET.class, required = false)
    protected CodigoEstadoPET codigoEstado;
    @XmlElement(name = "CodigoEstadoSecundario", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion")
    protected String codigoEstadoSecundario;
    @XmlElementRef(name = "LiteralError", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", type = LiteralErrorPET.class, required = false)
    protected LiteralErrorPET literalError;
    @XmlElement(name = "TiempoEstimadoRespuesta", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/peticion")
    protected Integer tiempoEstimadoRespuesta;

    /**
     * Obtiene el valor de la propiedad codigoEstado.
     * 
     * @return
     *     possible object is
     *     {@link CodigoEstadoPET }
     *     
     */
    public CodigoEstadoPET getCodigoEstado() {
        return codigoEstado;
    }

    /**
     * Define el valor de la propiedad codigoEstado.
     * 
     * @param value
     *     allowed object is
     *     {@link CodigoEstadoPET }
     *     
     */
    public void setCodigoEstado(CodigoEstadoPET value) {
        this.codigoEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoEstadoSecundario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEstadoSecundario() {
        return codigoEstadoSecundario;
    }

    /**
     * Define el valor de la propiedad codigoEstadoSecundario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEstadoSecundario(String value) {
        this.codigoEstadoSecundario = value;
    }

    /**
     * Obtiene el valor de la propiedad literalError.
     * 
     * @return
     *     possible object is
     *     {@link LiteralErrorPET }
     *     
     */
    public LiteralErrorPET getLiteralError() {
        return literalError;
    }

    /**
     * Define el valor de la propiedad literalError.
     * 
     * @param value
     *     allowed object is
     *     {@link LiteralErrorPET }
     *     
     */
    public void setLiteralError(LiteralErrorPET value) {
        this.literalError = value;
    }

    /**
     * Obtiene el valor de la propiedad tiempoEstimadoRespuesta.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTiempoEstimadoRespuesta() {
        return tiempoEstimadoRespuesta;
    }

    /**
     * Define el valor de la propiedad tiempoEstimadoRespuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTiempoEstimadoRespuesta(Integer value) {
        this.tiempoEstimadoRespuesta = value;
    }

}
