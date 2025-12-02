//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.11.26 a las 12:50:37 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.respuesta;

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
 *         &lt;element name="CertificadoX509" type="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Contenido"/&gt;
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
@XmlRootElement(name = "AutorizacionDelegada")
public class AutorizacionDelegada {

    @XmlElement(name = "CertificadoX509", required = true)
    protected byte[] certificadoX509;

    /**
     * Obtiene el valor de la propiedad certificadoX509.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCertificadoX509() {
        return certificadoX509;
    }

    /**
     * Define el valor de la propiedad certificadoX509.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCertificadoX509(byte[] value) {
        this.certificadoX509 = value;
    }

}
