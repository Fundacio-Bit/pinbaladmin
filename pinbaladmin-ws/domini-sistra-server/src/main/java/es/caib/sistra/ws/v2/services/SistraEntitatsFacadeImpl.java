package es.caib.sistra.ws.v2.services;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */


import java.util.List;
import java.util.Locale;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;
import javax.annotation.Resource;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.ws.api.annotation.TransportGuarantee;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.logic.utils.I18NLogicUtils;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.pinbaladmin.model.fields.EntitatFields;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.jboss.ws.api.annotation.WebContext;

import es.caib.sistra.ws.v2.model.valoresdominio.Columna;
import es.caib.sistra.ws.v2.model.valoresdominio.Fila;
import es.caib.sistra.ws.v2.model.valoresdominio.Filas;


/**
 * This class was generated by Apache CXF 2.7.4 2018-05-08T09:26:09.543+02:00
 * Generated source version: 2.7.4
 * 
 */

/*
 * 
 * @SuppressWarnings("restriction")
 * 
 * @DeclareRoles({ "PFI_USER" })
 * 
 * @RunAs("PFI_USER")
 * 
 * @Stateless(name="Cws")
 * 
 * @SOAPBinding(style = SOAPBinding.Style.RPC)
 * 
 * @WebService ( name="Cws", portName = "CWS", serviceName = "CwsService",
 * targetNamespace = PortafirmasIndraImpl.QNAME, endpointInterface =
 * "es.indra.portafirmasws.cws.Cws" )
 * 
 * @WebContext ( contextRoot="/portafib/portafirmasws/web/services",
 * urlPattern="/CWS", transportGuarantee= TransportGuarantee.NONE,
 * secureWSDLAccess = false )
 */

// @SecurityDomain(Constants.SECURITY_DOMAIN)
// @RolesAllowed({ Constants.PAD_ADMIN })
// @SOAPBinding(style = SOAPBinding.Style.RPC)
// @org.apache.cxf.interceptor.InInterceptors(interceptors = {
// "es.caib.portafib.ws.utils.PortaFIBInInterceptor" })
// @org.apache.cxf.interceptor.InFaultInterceptors(interceptors = {
// "es.caib.portafib.ws.utils.PortaFIBInInterceptor" })

// @SuppressWarnings("restriction")
@DeclareRoles({ Constants.PAD_ADMIN })
@RunAs(Constants.PAD_ADMIN)
@Stateless(name = "SistraDominiEntitatsEJB")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebContext(contextRoot = "/pinbaladmin/ws", urlPattern = "/dominisistra/entitats", transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false)
@javax.jws.WebService(serviceName = "SistraEntitatsFacadeService", portName = "SistraEntitatsFacade", targetNamespace = "urn:es:caib:sistra:ws:v2:services",
// wsdlLocation = "SistraFacade.wsdl",
endpointInterface = "es.caib.sistra.ws.v2.services.SistraEntitatsFacade")
public class SistraEntitatsFacadeImpl implements SistraEntitatsFacade {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatService entitatLocalEjb;

  @Resource
  WebServiceContext wsContext;

  protected final Logger log = Logger.getLogger(getClass());

  /*
   * (non-Javadoc)
   * 
   * @see
   * es.caib.sistra.ws.v2.services.SistraFacade#obtenerDominio(java.lang.String
   * id ,)es.caib.sistra.ws.v2.model.sistrafacade.ParametrosDominio parametros
   * )*
   */
  @PermitAll
  public es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio obtenerDominio(
      java.lang.String id, es.caib.sistra.ws.v2.model.sistrafacade.ParametrosDominio parametros)
      throws es.caib.sistra.ws.v2.services.SistraFacadeException {
    log.info("Executing operation obtenerDominio ENTITATS");
    System.out.println(id);
    System.out.println(parametros);

    try {
      
      List<Entitat> list = entitatLocalEjb.select(new OrderBy(EntitatFields.NOM));

      Filas filas = new Filas();

      List<Fila> filasList = filas.getFila();

      for (Entitat entitat : list) {
         Fila fila = new Fila();
         
         Columna col1 = new Columna();         
         col1.setCodigo("codi");
         col1.setValor(entitat.getCIF());

         Columna col2 = new Columna();         
         col2.setCodigo("nom");
         col2.setValor(entitat.getNom());

         fila.getColumna().add(col1);
         fila.getColumna().add(col2);

         filasList.add(fila);
      }
      

      es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio _return;
      _return = new es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio();
      _return.setFilas(filas);

      return _return;
   
    } catch (I18NException ex) {
      
      String msg = I18NLogicUtils.getMessage(ex, new Locale("ca"));
      
      log.error(msg, ex);
      
      es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio _return;
      _return = new es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio();
      
      _return.setError(true);
      _return.setDescripcionError(msg);
      
      return _return;
      
    } catch (java.lang.Exception ex) {

      log.error(ex.getMessage(), ex);
      
      es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio _return;
      _return = new es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio();
      
      _return.setError(true);
      _return.setDescripcionError(ex.getMessage());
      
      return _return;
    }
    // throw new SistraFacadeException("SistraFacadeException...");
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * es.caib.sistra.ws.v2.services.SistraFacade#realizarConsulta(java.lang.String
   * identificadorTramite
   * ,)es.caib.sistra.ws.v2.model.formularioconsulta.FormulariosConsulta forms
   * )*
   */
  @PermitAll
  public es.caib.sistra.ws.v2.model.documentoconsulta.DocumentosConsulta realizarConsulta(
      java.lang.String identificadorTramite,
      es.caib.sistra.ws.v2.model.formularioconsulta.FormulariosConsulta forms)
      throws SistraFacadeException {
    log.info("Executing operation realizarConsulta");
    System.out.println(identificadorTramite);
    System.out.println(forms);
    try {
      es.caib.sistra.ws.v2.model.documentoconsulta.DocumentosConsulta _return = null;     
      return _return;
    } catch (java.lang.Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException(ex);
    }
    // throw new SistraFacadeException("SistraFacadeException...");
  }

  /*
   * protected UsuariAplicacioJPA checkUserPassword(Application application)
   * throws SoapFault {
   * 
   * 
   * 
   * // Autenticam directament sobre seycon: Aquest usuari ha d'apareixer //
   * dins la BBDD de seycon, però ha d'estar donat d'alta dins la // taula
   * d'usuaris aplicacio Set<String> roles = new HashSet<String>(); try {
   * LoginContext lc = new LoginContext(Constants.SECURITY_DOMAIN, new
   * PassiveCallbackHandler(user, password)); lc.login();
   * 
   * Set<Principal> principalsCred = lc.getSubject().getPrincipals(); if
   * (principalsCred == null ||principalsCred.isEmpty()) {
   * log.warn(" getPrincipals() == BUIT"); } else { for (Principal object :
   * principalsCred) { log.debug(" getPrincipals() == " + object.getName() + "("
   * + object.getClass() + ")"); if ("Roles".equals(object.getName()) && object
   * instanceof org.jboss.security.SimpleGroup) { org.jboss.security.SimpleGroup
   * sg = (org.jboss.security.SimpleGroup)object; //iterable
   * Enumeration<Principal> enumPrinc = sg.members();
   * while(enumPrinc.hasMoreElements()) { Principal rol =
   * enumPrinc.nextElement(); log.debug("           ROL: " + rol.getName());
   * roles.add(rol.getName()); } } } } isOK = true; } catch (LoginException le)
   * { // Authentication failed. log.error("CAIB3 Login ERROR" +
   * le.getMessage()); isOK = false; }
   * 
   * if (isOK) { if (log.isDebugEnabled()) {
   * log.debug(" Autenticat usuari aplicacio " + user); } } else {
   * MessageContext mctx = wsContext.getMessageContext(); HttpServletRequest
   * request = (HttpServletRequest)mctx.get(mctx.SERVLET_REQUEST); final String
   * msg = "Contrasenya no vàlida per l'usuari ]" + user +
   * "[ o no té rols assignats per les operacions requerides." +
   * "Remote address: " + request.getRemoteHost(); log.error(msg);
   * 
   * throw createFaultNoAutenticat(); }
   * 
   * if (roles.contains(PFI_ADMIN) || roles.contains(PFI_USER)) { // OK } else {
   * final String msg = "L'usuari ]" + user +
   * "[ no té ni el rol PFI_ADMIN ni el rol PFI_USER"; log.error(msg); throw
   * createFaultNoAutenticat(); }
   * 
   * return usuariAplicacio; }
   * 
   * public static SoapFault createFaultNoAutenticat() { return createFault(1,
   * "Aplicación no autorizada"); }
   * 
   * public static SoapFault createFaultErrorGeneral(Throwable th) { return
   * createFault(99, "Error general de los servicios web de portafirmas: " +
   * th.getClass().getName()); }
   * 
   * public static SoapFault createFaultErrorGeneral(String msg) { return
   * createFault(99, "Error general de los servicios web de portafirmas: " +
   * msg); }
   * 
   * public static SoapFault createFaultNullPointer() { return createFault(99,
   * "Error general de los servicios" +
   * " web de portafirmas: java.lang.NullPointerException"); }
   * 
   * private static SoapFault createFault(int code, String msg) { SoapFault sf;
   * sf = new SoapFault(code + ": " + msg, new
   * javax.xml.namespace.QName(String.valueOf(code))); return sf; };
   */
}
