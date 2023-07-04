
package es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence maxOccurs="unbounded"&gt;
 *         &lt;element ref="{http://intermediacion.redsara.es/scsp/esquemas/datosespecificos}DocumentoAutorizacion"/&gt;
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
    "documentoAutorizacion"
})
@XmlRootElement(name = "DocumentosAutorizacion")
public class DocumentosAutorizacion {

    @XmlElement(name = "DocumentoAutorizacion", required = true)
    protected List<DocumentoAutorizacion> documentoAutorizacion;

    /**
     * Gets the value of the documentoAutorizacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoAutorizacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoAutorizacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoAutorizacion }
     * 
     * 
     */
    public List<DocumentoAutorizacion> getDocumentoAutorizacion() {
        if (documentoAutorizacion == null) {
            documentoAutorizacion = new ArrayList<DocumentoAutorizacion>();
        }
        return this.documentoAutorizacion;
    }

}
