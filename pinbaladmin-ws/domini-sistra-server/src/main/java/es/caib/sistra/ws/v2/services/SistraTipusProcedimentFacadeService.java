package es.caib.sistra.ws.v2.services;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.URL;

/**
 * @author anadal
 */
@WebServiceClient(name = "SistraTipusProcedimentFacadeService",
                  wsdlLocation = "SistraFacade.wsdl",
                  targetNamespace = "urn:es:caib:sistra:ws:v2:services") 
public class SistraTipusProcedimentFacadeService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("urn:es:caib:sistra:ws:v2:services", "SistraTipusProcedimentFacadeService");
    public final static QName SistraTipusProcedimentFacade = new QName("urn:es:caib:sistra:ws:v2:services", "SistraTipusProcedimentFacade");
    static {
        URL url = SistraTipusProcedimentFacadeService.class.getResource("SistraFacade.wsdl");
        if (url == null) {
            url = SistraTipusProcedimentFacadeService.class.getClassLoader().getResource("SistraFacade.wsdl");
        }
        if (url == null) {
            java.util.logging.Logger.getLogger(SistraTipusProcedimentFacadeService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "SistraFacade.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SistraTipusProcedimentFacadeService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SistraTipusProcedimentFacadeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SistraTipusProcedimentFacadeService() {
        super(WSDL_LOCATION, SERVICE);
    }


    /**
     *
     * @return
     *     returns SistraTipusProcedimentFacade
     */
    @WebEndpoint(name = "SistraTipusProcedimentFacade")
    public SistraTipusProcedimentFacade getSistraTipusProcedimentFacade() {
        return super.getPort(SistraTipusProcedimentFacade, SistraTipusProcedimentFacade.class);
    }

    /**
     *
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SistraTipusProcedimentFacade
     */
    @WebEndpoint(name = "SistraTipusProcedimentFacade")
    public SistraTipusProcedimentFacade getSistraTipusProcedimentFacade(WebServiceFeature... features) {
        return super.getPort(SistraTipusProcedimentFacade, SistraTipusProcedimentFacade.class, features);
    }

}
