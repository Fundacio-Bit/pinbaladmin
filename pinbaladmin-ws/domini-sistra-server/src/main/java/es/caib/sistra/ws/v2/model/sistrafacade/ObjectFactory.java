
package es.caib.sistra.ws.v2.model.sistrafacade;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.caib.sistra.ws.v2.model.sistrafacade package. 
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

    private final static QName _Fault_QNAME = new QName("urn:es:caib:sistra:ws:v2:model:SistraFacade", "fault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.caib.sistra.ws.v2.model.sistrafacade
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RealizarConsultaResponse }
     * 
     */
    public RealizarConsultaResponse createRealizarConsultaResponse() {
        return new RealizarConsultaResponse();
    }

    /**
     * Create an instance of {@link ObtenerDominioResponse }
     * 
     */
    public ObtenerDominioResponse createObtenerDominioResponse() {
        return new ObtenerDominioResponse();
    }

    /**
     * Create an instance of {@link SistraFacadeException }
     * 
     */
    public SistraFacadeException createSistraFacadeException() {
        return new SistraFacadeException();
    }

    /**
     * Create an instance of {@link RealizarConsulta }
     * 
     */
    public RealizarConsulta createRealizarConsulta() {
        return new RealizarConsulta();
    }

    /**
     * Create an instance of {@link ObtenerDominio }
     * 
     */
    public ObtenerDominio createObtenerDominio() {
        return new ObtenerDominio();
    }

    /**
     * Create an instance of {@link ParametrosDominio }
     * 
     */
    public ParametrosDominio createParametrosDominio() {
        return new ParametrosDominio();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SistraFacadeException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade", name = "fault")
    public JAXBElement<SistraFacadeException> createFault(SistraFacadeException value) {
        return new JAXBElement<SistraFacadeException>(_Fault_QNAME, SistraFacadeException.class, null, value);
    }

}
