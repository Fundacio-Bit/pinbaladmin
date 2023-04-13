package es.caib.sistra.ws.v2.services;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.4
 * 2018-05-08T09:26:09.590+02:00
 * Generated source version: 2.7.4
 * 
 */
@WebServiceClient(name = "SistraEntitatsFacadeService", 
                  wsdlLocation = "SistraFacade.wsdl",
                  targetNamespace = "urn:es:caib:sistra:ws:v2:services") 
public class SistraEntitatsFacadeService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("urn:es:caib:sistra:ws:v2:services", "SistraEntitatsFacadeService");
    public final static QName SistraEntitatsFacade = new QName("urn:es:caib:sistra:ws:v2:services", "SistraEntitatsFacade");
    static {
        URL url = SistraEntitatsFacadeService.class.getResource("SistraFacade.wsdl");
        if (url == null) {
            url = SistraEntitatsFacadeService.class.getClassLoader().getResource("SistraFacade.wsdl");
        } 
        if (url == null) {
            java.util.logging.Logger.getLogger(SistraEntitatsFacadeService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "SistraFacade.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public SistraEntitatsFacadeService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SistraEntitatsFacadeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SistraEntitatsFacadeService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns SistraEntitatsFacade
     */
    @WebEndpoint(name = "SistraEntitatsFacade")
    public SistraEntitatsFacade getSistraEntitatsFacade() {
        return super.getPort(SistraEntitatsFacade, SistraEntitatsFacade.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SistraEntitatsFacade
     */
    @WebEndpoint(name = "SistraEntitatsFacade")
    public SistraEntitatsFacade getSistraEntitatsFacade(WebServiceFeature... features) {
        return super.getPort(SistraEntitatsFacade, SistraEntitatsFacade.class, features);
    }

}