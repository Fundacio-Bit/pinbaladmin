package es.caib.sistra.ws.v2.services;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.URL;

/**
 * @author areus
 */
@WebServiceClient(name = "SistraConsentimentFacadeService",
                  wsdlLocation = "SistraFacade.wsdl",
                  targetNamespace = "urn:es:caib:sistra:ws:v2:services") 
public class SistraConsentimentFacadeService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("urn:es:caib:sistra:ws:v2:services", "SistraConsentimentFacadeService");
    public final static QName SistraConsentimentFacade = new QName("urn:es:caib:sistra:ws:v2:services", "SistraConsentimentFacade");
    static {
        URL url = SistraConsentimentFacadeService.class.getResource("SistraFacade.wsdl");
        if (url == null) {
            url = SistraConsentimentFacadeService.class.getClassLoader().getResource("SistraFacade.wsdl");
        }
        if (url == null) {
            java.util.logging.Logger.getLogger(SistraConsentimentFacadeService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "SistraFacade.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SistraConsentimentFacadeService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SistraConsentimentFacadeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SistraConsentimentFacadeService() {
        super(WSDL_LOCATION, SERVICE);
    }


    /**
     *
     * @return
     *     returns SistraConsentimentFacade
     */
    @WebEndpoint(name = "SistraConsentimentFacade")
    public SistraConsentimentFacade getSistraConsentimentFacade() {
        return super.getPort(SistraConsentimentFacade, SistraConsentimentFacade.class);
    }

    /**
     *
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SistraConsentimentFacade
     */
    @WebEndpoint(name = "SistraConsentimentFacade")
    public SistraConsentimentFacade getSistraConsentimentFacade(WebServiceFeature... features) {
        return super.getPort(SistraConsentimentFacade, SistraConsentimentFacade.class, features);
    }

}