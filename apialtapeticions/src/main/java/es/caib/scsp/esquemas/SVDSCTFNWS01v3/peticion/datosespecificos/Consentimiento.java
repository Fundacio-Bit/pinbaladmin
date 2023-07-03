
package es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos;

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
 *         &lt;element name="Tipo"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Si"/&gt;
 *               &lt;enumeration value="NoOpo"/&gt;
 *               &lt;enumeration value="Ley"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Enlace" minOccurs="0"/&gt;
 *         &lt;element name="Documento" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;all&gt;
 *                   &lt;element name="Nombre"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;whiteSpace value="collapse"/&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="512"/&gt;
 *                         &lt;pattern value=".+\.(pdf|PDF)"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Descripcion"/&gt;
 *                   &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Contenido"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
@XmlRootElement(name = "Consentimiento")
public class Consentimiento {

    @XmlElement(name = "Tipo", required = true)
    protected String tipo;
    @XmlElement(name = "Enlace")
    protected String enlace;
    @XmlElement(name = "Documento")
    protected Consentimiento.Documento documento;

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Enlace del consentimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnlace() {
        return enlace;
    }

    /**
     * Define el valor de la propiedad enlace.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnlace(String value) {
        this.enlace = value;
    }

    /**
     * Obtiene el valor de la propiedad documento.
     * 
     * @return
     *     possible object is
     *     {@link Consentimiento.Documento }
     *     
     */
    public Consentimiento.Documento getDocumento() {
        return documento;
    }

    /**
     * Define el valor de la propiedad documento.
     * 
     * @param value
     *     allowed object is
     *     {@link Consentimiento.Documento }
     *     
     */
    public void setDocumento(Consentimiento.Documento value) {
        this.documento = value;
    }


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
     *         &lt;element name="Nombre"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;whiteSpace value="collapse"/&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="512"/&gt;
     *               &lt;pattern value=".+\.(pdf|PDF)"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Descripcion"/&gt;
     *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Contenido"/&gt;
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
    public static class Documento {

        @XmlElement(name = "Nombre", required = true)
        protected String nombre;
        @XmlElement(name = "Descripcion", required = true)
        protected String descripcion;
        @XmlElement(name = "Contenido", required = true)
        protected byte[] contenido;

        /**
         * Obtiene el valor de la propiedad nombre.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Define el valor de la propiedad nombre.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombre(String value) {
            this.nombre = value;
        }

        /**
         * Obtiene el valor de la propiedad descripcion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcion() {
            return descripcion;
        }

        /**
         * Define el valor de la propiedad descripcion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcion(String value) {
            this.descripcion = value;
        }

        /**
         * Obtiene el valor de la propiedad contenido.
         * 
         * @return
         *     possible object is
         *     byte[]
         */
        public byte[] getContenido() {
            return contenido;
        }

        /**
         * Define el valor de la propiedad contenido.
         * 
         * @param value
         *     allowed object is
         *     byte[]
         */
        public void setContenido(byte[] value) {
            this.contenido = value;
        }

    }

}
