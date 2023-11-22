//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.22 a las 02:37:02 PM CET 
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
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}IdentificadorSolicitante"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}NombreSolicitante"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}UnidadTramitadora"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}CodigoUnidadTramitadora"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}Procedimiento"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}Finalidad"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}Consentimiento"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}Funcionario"/&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta}IdExpediente" minOccurs="0"/&gt;
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
@XmlRootElement(name = "Solicitante", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta")
public class Solicitante {

    @XmlElement(name = "IdentificadorSolicitante", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", required = true)
    protected String identificadorSolicitante;
    @XmlElement(name = "NombreSolicitante", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", required = true)
    protected String nombreSolicitante;
    @XmlElement(name = "UnidadTramitadora", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", required = true)
    protected String unidadTramitadora;
    @XmlElement(name = "CodigoUnidadTramitadora", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", required = true)
    protected String codigoUnidadTramitadora;
    @XmlElement(name = "Procedimiento", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", required = true)
    protected Procedimiento procedimiento;
    @XmlElement(name = "Finalidad", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", required = true)
    protected String finalidad;
    @XmlElement(name = "Consentimiento", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", required = true)
    protected String consentimiento;
    @XmlElement(name = "Funcionario", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", required = true)
    protected Funcionario funcionario;
    @XmlElement(name = "IdExpediente", namespace = "http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta")
    protected String idExpediente;

    /**
     * Obtiene el valor de la propiedad identificadorSolicitante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificadorSolicitante() {
        return identificadorSolicitante;
    }

    /**
     * Define el valor de la propiedad identificadorSolicitante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificadorSolicitante(String value) {
        this.identificadorSolicitante = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreSolicitante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    /**
     * Define el valor de la propiedad nombreSolicitante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreSolicitante(String value) {
        this.nombreSolicitante = value;
    }

    /**
     * Obtiene el valor de la propiedad unidadTramitadora.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadTramitadora() {
        return unidadTramitadora;
    }

    /**
     * Define el valor de la propiedad unidadTramitadora.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadTramitadora(String value) {
        this.unidadTramitadora = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoUnidadTramitadora.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoUnidadTramitadora() {
        return codigoUnidadTramitadora;
    }

    /**
     * Define el valor de la propiedad codigoUnidadTramitadora.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoUnidadTramitadora(String value) {
        this.codigoUnidadTramitadora = value;
    }

    /**
     * Obtiene el valor de la propiedad procedimiento.
     * 
     * @return
     *     possible object is
     *     {@link Procedimiento }
     *     
     */
    public Procedimiento getProcedimiento() {
        return procedimiento;
    }

    /**
     * Define el valor de la propiedad procedimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Procedimiento }
     *     
     */
    public void setProcedimiento(Procedimiento value) {
        this.procedimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad finalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinalidad() {
        return finalidad;
    }

    /**
     * Define el valor de la propiedad finalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinalidad(String value) {
        this.finalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad consentimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentimiento() {
        return consentimiento;
    }

    /**
     * Define el valor de la propiedad consentimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentimiento(String value) {
        this.consentimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad funcionario.
     * 
     * @return
     *     possible object is
     *     {@link Funcionario }
     *     
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Define el valor de la propiedad funcionario.
     * 
     * @param value
     *     allowed object is
     *     {@link Funcionario }
     *     
     */
    public void setFuncionario(Funcionario value) {
        this.funcionario = value;
    }

    /**
     * Obtiene el valor de la propiedad idExpediente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdExpediente() {
        return idExpediente;
    }

    /**
     * Define el valor de la propiedad idExpediente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdExpediente(String value) {
        this.idExpediente = value;
    }

}