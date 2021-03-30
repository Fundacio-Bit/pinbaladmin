package es.caib.sistra.ws.v2.services;

import es.caib.sistra.ws.v2.model.valoresdominio.Columna;
import es.caib.sistra.ws.v2.model.valoresdominio.Fila;
import es.caib.sistra.ws.v2.model.valoresdominio.Filas;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.logic.utils.I18NLogicUtils;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.utils.Constants;
import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author areus
 */
@DeclareRoles({ Constants.PAD_ADMIN })
@RunAs(Constants.PAD_ADMIN)
@Stateless(name = "SistraDominiConsentimentEJB")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebContext(contextRoot = "/pinbaladmin/ws", urlPattern = "/dominisistra/consentiment", transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false)
@javax.jws.WebService(serviceName = "SistraConsentimentFacadeService", portName = "SistraConsentimentFacade", targetNamespace = "urn:es:caib:sistra:ws:v2:services",
// wsdlLocation = "SistraFacade.wsdl",
endpointInterface = "es.caib.sistra.ws.v2.services.SistraConsentimentFacade")
public class SistraConsentimentFacadeImpl implements SistraConsentimentFacade {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ServeiLocal serveiEjb;

  private final Logger log = Logger.getLogger(getClass());

  /**
   * Necessita un primer paràmetre obligatori amb el codi del Servei, i un segon paràmetre opcional
   * que conté l'idioma (ca o es).
   *
   * Retorna les les opcions possibles de consentiment pel servei, amb un codi (noop, si o llei), i
   * el valor conté el nom de l'opció traduit.
   */
  public es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio obtenerDominio(
      String id, es.caib.sistra.ws.v2.model.sistrafacade.ParametrosDominio parametros)
      throws SistraFacadeException {
    log.info("Executing operation obtenerDominio CONSENTIMENT");
    System.out.println(id);
    System.out.println(parametros);

    es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio _return;
    _return = new es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio();

    try {

      if (parametros== null || parametros.getParametro() == null || parametros.getParametro().size() == 0) {
        _return.setError(true);
        _return.setDescripcionError("Es requereix el codi del Servei com a parametre");
        return _return;
      }

      String codi = parametros.getParametro().get(0);
      log.info(" Rebut codi del servei = ]" + codi + "[");

      String lang = "ca";
      if (parametros.getParametro().size()>1) {
        lang = parametros.getParametro().get(1);
        log.info(" Rebut llenguatge = ]" + lang + "[");

        if (!lang.equals("ca") && !lang.equals("es")) {
          _return.setError(true);
          _return.setDescripcionError("El paràmetre llenguatge no és cap dels soportats " + lang);
          return _return;
        }
      }

      List<Servei> serveis = serveiEjb.select(ServeiFields.CODI.equal(codi));
      if (serveis.isEmpty()) {
        _return.setError(true);
        _return.setDescripcionError("No s'ha trobat cap servei amb el codi " + codi);
        return _return;
      }

      Servei servei = serveis.get(0);
      log.info(" Servei = ]" + servei.getServeiID() + "[");

      int tipusConsentiment = servei.getTipusConsentiment();

      log.info(" tipusConsentiment = ]" + tipusConsentiment + "[");

      if (tipusConsentiment != Constants.TIPUSCONSENTIMENT_NOOP_LLEI
            && tipusConsentiment != Constants.TIPUSCONSENTIMENT_SI_LLEI) {
        _return.setError(true);
        _return.setDescripcionError("El tipusConsentiment no és cap dels valors acceptats " + tipusConsentiment);
        return _return;
      }

      Filas filas = new Filas();
      List<Fila> filasList = filas.getFila();

      Locale loc = new Locale(lang);
      ResourceBundle messages;
      messages = ResourceBundle.getBundle("wsmissatges", loc);

      switch (tipusConsentiment) {
        case Constants.TIPUSCONSENTIMENT_NOOP_LLEI:
          filasList.add(getFila("noop", messages.getString("consentiment.noop")));
          break;
        case Constants.TIPUSCONSENTIMENT_SI_LLEI:
          filasList.add(getFila("si", messages.getString("consentiment.si")));
          break;
      }

      filasList.add(getFila("llei", messages.getString( "consentiment.1lei")));

      _return.setFilas(filas);
      return _return;

    } catch (I18NException ex) {
      String msg = I18NLogicUtils.getMessage(ex, new Locale("ca"));
      log.error(msg, ex);
      _return.setError(true);
      _return.setDescripcionError(msg);
      return _return;

    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
      _return.setError(true);
      _return.setDescripcionError(ex.getMessage());
      return _return;
    }
    // throw new SistraFacadeException("SistraFacadeException...");
  }

  protected Fila getFila(String codi, String nom) {
    Fila fila = new Fila();
    fila.getColumna().add(getColumna("codi", codi));
    fila.getColumna().add(getColumna("nom", nom));
    return fila;
  }

  protected Columna getColumna(String codigo, String valor) {
    Columna columna = new Columna();
    columna.setCodigo(codigo);
    columna.setValor(valor);
    return columna;
  }

  /*
   *
   */
  public es.caib.sistra.ws.v2.model.documentoconsulta.DocumentosConsulta realizarConsulta(
      String identificadorTramite,
      es.caib.sistra.ws.v2.model.formularioconsulta.FormulariosConsulta forms)
      throws SistraFacadeException {
    log.info("Executing operation realizarConsulta");
    System.out.println(identificadorTramite);
    System.out.println(forms);
    try {
      es.caib.sistra.ws.v2.model.documentoconsulta.DocumentosConsulta _return = null;
      return _return;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException(ex);
    }
    // throw new SistraFacadeException("SistraFacadeException...");
  }
}
