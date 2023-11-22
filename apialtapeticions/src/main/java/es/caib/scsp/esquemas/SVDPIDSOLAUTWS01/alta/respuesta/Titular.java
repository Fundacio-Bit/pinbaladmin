//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.22 a las 03:49:24 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.respuesta;

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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}TipoDocumentacion" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}Documentacion" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}NombreCompleto" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}Nombre" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}Apellido1" minOccurs="0"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}Apellido2" minOccurs="0"/&gt;
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
    "tipoDocumentacion",
    "documentacion",
    "nombreCompleto",
    "nombre",
    "apellido1",
    "apellido2"
})
@XmlRootElement(name = "Titular", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta")
public class Titular {

    @XmlElement(name = "TipoDocumentacion", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta")
    protected String tipoDocumentacion;
    @XmlElement(name = "Documentacion", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta")
    protected String documentacion;
    @XmlElement(name = "NombreCompleto", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta")
    protected String nombreCompleto;
    @XmlElementRef(name = "Nombre", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", type = NombreRES.class, required = false)
    protected NombreRES nombre;
    @XmlElementRef(name = "Apellido1", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", type = Apellido1RES.class, required = false)
    protected Apellido1RES apellido1;
    @XmlElementRef(name = "Apellido2", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", type = Apellido2RES.class, required = false)
    protected Apellido2RES apellido2;

    /**
     * Obtiene el valor de la propiedad tipoDocumentacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocumentacion() {
        return tipoDocumentacion;
    }

    /**
     * Define el valor de la propiedad tipoDocumentacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocumentacion(String value) {
        this.tipoDocumentacion = value;
    }

    /**
     * Obtiene el valor de la propiedad documentacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentacion() {
        return documentacion;
    }

    /**
     * Define el valor de la propiedad documentacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentacion(String value) {
        this.documentacion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreCompleto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Define el valor de la propiedad nombreCompleto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCompleto(String value) {
        this.nombreCompleto = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link NombreRES }
     *     
     */
    public NombreRES getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link NombreRES }
     *     
     */
    public void setNombre(NombreRES value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad apellido1.
     * 
     * @return
     *     possible object is
     *     {@link Apellido1RES }
     *     
     */
    public Apellido1RES getApellido1() {
        return apellido1;
    }

    /**
     * Define el valor de la propiedad apellido1.
     * 
     * @param value
     *     allowed object is
     *     {@link Apellido1RES }
     *     
     */
    public void setApellido1(Apellido1RES value) {
        this.apellido1 = value;
    }

    /**
     * Obtiene el valor de la propiedad apellido2.
     * 
     * @return
     *     possible object is
     *     {@link Apellido2RES }
     *     
     */
    public Apellido2RES getApellido2() {
        return apellido2;
    }

    /**
     * Define el valor de la propiedad apellido2.
     * 
     * @param value
     *     allowed object is
     *     {@link Apellido2RES }
     *     
     */
    public void setApellido2(Apellido2RES value) {
        this.apellido2 = value;
    }

}
