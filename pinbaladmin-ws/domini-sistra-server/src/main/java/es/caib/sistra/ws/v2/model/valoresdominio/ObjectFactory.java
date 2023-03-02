
package es.caib.sistra.ws.v2.model.valoresdominio;

import javax.xml.bind.annotation.XmlRegistry;


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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.caib.sistra.ws.v2.model.valoresdominio
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Filas }
     * 
     */
    public Filas createFilas() {
        return new Filas();
    }

    /**
     * Create an instance of {@link Fila }
     * 
     */
    public Fila createFila() {
        return new Fila();
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

}
