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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}CodigoEstado"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}CodigoEstadoSecundario" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}LiteralError"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}TiempoEstimadoRespuesta" minOccurs="0"/&gt;
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
@XmlRootElement(name = "Estado", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta")
public class Estado {

    @XmlElementRef(name = "CodigoEstado", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", type = CodigoEstadoRES.class)
    protected CodigoEstadoRES codigoEstado;
    @XmlElement(name = "CodigoEstadoSecundario", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta")
    protected String codigoEstadoSecundario;
    @XmlElementRef(name = "LiteralError", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", type = LiteralErrorRES.class)
    protected LiteralErrorRES literalError;
    @XmlElement(name = "TiempoEstimadoRespuesta", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta")
    protected Integer tiempoEstimadoRespuesta;

    /**
     * Obtiene el valor de la propiedad codigoEstado.
     * 
     * @return
     *     possible object is
     *     {@link CodigoEstadoRES }
     *     
     */
    public CodigoEstadoRES getCodigoEstado() {
        return codigoEstado;
    }

    /**
     * Define el valor de la propiedad codigoEstado.
     * 
     * @param value
     *     allowed object is
     *     {@link CodigoEstadoRES }
     *     
     */
    public void setCodigoEstado(CodigoEstadoRES value) {
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
     *     {@link LiteralErrorRES }
     *     
     */
    public LiteralErrorRES getLiteralError() {
        return literalError;
    }

    /**
     * Define el valor de la propiedad literalError.
     * 
     * @param value
     *     allowed object is
     *     {@link LiteralErrorRES }
     *     
     */
    public void setLiteralError(LiteralErrorRES value) {
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
