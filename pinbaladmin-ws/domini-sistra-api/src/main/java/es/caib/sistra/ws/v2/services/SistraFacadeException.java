
package es.caib.sistra.ws.v2.services;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "fault", targetNamespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade")
public class SistraFacadeException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private es.caib.sistra.ws.v2.model.sistrafacade.SistraFacadeException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public SistraFacadeException(String message, es.caib.sistra.ws.v2.model.sistrafacade.SistraFacadeException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public SistraFacadeException(String message, es.caib.sistra.ws.v2.model.sistrafacade.SistraFacadeException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: es.caib.sistra.ws.v2.model.sistrafacade.SistraFacadeException
     */
    public es.caib.sistra.ws.v2.model.sistrafacade.SistraFacadeException getFaultInfo() {
        return faultInfo;
    }

}