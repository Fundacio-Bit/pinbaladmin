package es.caib.sistra.ws.v2.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author areus
 */
@WebService(targetNamespace = "urn:es:caib:sistra:ws:v2:services", name = "SistraConsentimentFacade")
@XmlSeeAlso({es.caib.sistra.ws.v2.model.formularioconsulta.ObjectFactory.class, es.caib.sistra.ws.v2.model.sistrafacade.ObjectFactory.class, es.caib.sistra.ws.v2.model.valoresdominio.ObjectFactory.class, es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory.class})
public interface SistraConsentimentFacade {

    @WebResult(name = "obtenerDominioReturn", targetNamespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade")
    @RequestWrapper(localName = "obtenerDominio", targetNamespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade", className = "es.caib.sistra.ws.v2.model.sistrafacade.ObtenerDominio")
    @WebMethod
    @ResponseWrapper(localName = "obtenerDominioResponse", targetNamespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade", className = "es.caib.sistra.ws.v2.model.sistrafacade.ObtenerDominioResponse")
    public es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio obtenerDominio(
          @WebParam(name = "id", targetNamespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade")
                String id,
          @WebParam(name = "parametros", targetNamespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade")
                es.caib.sistra.ws.v2.model.sistrafacade.ParametrosDominio parametros
    ) throws SistraFacadeException;

    @WebResult(name = "realizarConsultaReturn", targetNamespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade")
    @RequestWrapper(localName = "realizarConsulta", targetNamespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade", className = "es.caib.sistra.ws.v2.model.sistrafacade.RealizarConsulta")
    @WebMethod
    @ResponseWrapper(localName = "realizarConsultaResponse", targetNamespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade", className = "es.caib.sistra.ws.v2.model.sistrafacade.RealizarConsultaResponse")
    public es.caib.sistra.ws.v2.model.documentoconsulta.DocumentosConsulta realizarConsulta(
          @WebParam(name = "identificadorTramite", targetNamespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade")
                String identificadorTramite,
          @WebParam(name = "forms", targetNamespace = "urn:es:caib:sistra:ws:v2:model:SistraFacade")
                es.caib.sistra.ws.v2.model.formularioconsulta.FormulariosConsulta forms
    ) throws SistraFacadeException;
}
