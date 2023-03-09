
package es.caib.sistra.ws.v2.model.valoresdominio;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.caib.sistra.ws.v2.model.valoresdominio package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ValoresDominioFilas_QNAME = new QName("", "filas");
    private final static QName _ValoresDominioDescripcionError_QNAME = new QName("", "descripcionError");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.caib.sistra.ws.v2.model.valoresdominio
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Fila }
     * 
     */
    public Fila createFila() {
        return new Fila();
    }

    /**
     * Create an instance of {@link Filas }
     * 
     */
    public Filas createFilas() {
        return new Filas();
    }

    /**
     * Create an instance of {@link ValoresDominio }
     * 
     */
    public ValoresDominio createValoresDominio() {
        return new ValoresDominio();
    }

    /**
     * Create an instance of {@link Columna }
     * 
     */
    public Columna createColumna() {
        return new Columna();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Filas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "filas", scope = ValoresDominio.class)
    public JAXBElement<Filas> createValoresDominioFilas(Filas value) {
        return new JAXBElement<Filas>(_ValoresDominioFilas_QNAME, Filas.class, ValoresDominio.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "descripcionError", scope = ValoresDominio.class)
    public JAXBElement<String> createValoresDominioDescripcionError(String value) {
        return new JAXBElement<String>(_ValoresDominioDescripcionError_QNAME, String.class, ValoresDominio.class, value);
    }

}
