
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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}NormaLegal"/&gt;
 *         &lt;element name="Documento"&gt;
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
 *                   &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Enlace" minOccurs="0"/&gt;
 *                 &lt;/all&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Articulos"/&gt;
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
@XmlRootElement(name = "Norma")
public class Norma {

    @XmlElement(name = "NormaLegal", required = true)
    protected String normaLegal;
    @XmlElement(name = "Documento", required = true)
    protected Norma.Documento documento;
    @XmlElement(name = "Articulos", required = true)
    protected Articulos articulos;

    /**
     * Obtiene el valor de la propiedad normaLegal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNormaLegal() {
        return normaLegal;
    }

    /**
     * Define el valor de la propiedad normaLegal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNormaLegal(String value) {
        this.normaLegal = value;
    }

    /**
     * Obtiene el valor de la propiedad documento.
     * 
     * @return
     *     possible object is
     *     {@link Norma.Documento }
     *     
     */
    public Norma.Documento getDocumento() {
        return documento;
    }

    /**
     * Define el valor de la propiedad documento.
     * 
     * @param value
     *     allowed object is
     *     {@link Norma.Documento }
     *     
     */
    public void setDocumento(Norma.Documento value) {
        this.documento = value;
    }

    /**
     * Obtiene el valor de la propiedad articulos.
     * 
     * @return
     *     possible object is
     *     {@link Articulos }
     *     
     */
    public Articulos getArticulos() {
        return articulos;
    }

    /**
     * Define el valor de la propiedad articulos.
     * 
     * @param value
     *     allowed object is
     *     {@link Articulos }
     *     
     */
    public void setArticulos(Articulos value) {
        this.articulos = value;
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
     *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}Enlace" minOccurs="0"/&gt;
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
        @XmlElement(name = "Enlace")
        protected String enlace;

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

        /**
         * Enlace de la norma legal que regula el procedimiento
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

    }

}
