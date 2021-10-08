package es.caib.sistra.ws.v2.services;

import es.caib.sistra.ws.v2.model.valoresdominio.Columna;
import es.caib.sistra.ws.v2.model.valoresdominio.Fila;
import es.caib.sistra.ws.v2.model.valoresdominio.Filas;
import org.apache.log4j.Logger;
import org.fundaciobit.pinbaladmin.utils.Constants;
import org.fundaciobit.pinbaladmin.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.utils.TipusProcediments.TipusProcediment;
import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @author anadal
 */
@DeclareRoles({ Constants.PAD_ADMIN })
@RunAs(Constants.PAD_ADMIN)
@Stateless(name = "SistraDominiTipusProcedimentEJB")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebContext(contextRoot = "/pinbaladmin/ws", urlPattern = "/dominisistra/tipusprocediment", transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false)
@javax.jws.WebService(serviceName = "SistraTipusProcedimentFacadeService", portName = "SistraTipusProcedimentFacade", targetNamespace = "urn:es:caib:sistra:ws:v2:services",
    // wsdlLocation = "SistraFacade.wsdl",
    endpointInterface = "es.caib.sistra.ws.v2.services.SistraTipusProcedimentFacade")
public class SistraTipusProcedimentFacadeImpl implements SistraTipusProcedimentFacade {

  private final Logger log = Logger.getLogger(getClass());

  /**
   * Necessita un primer paràmetre obligatori amb el codi del Servei, i un segon
   * paràmetre opcional que conté l'idioma (ca o es).
   *
   * Retorna les les opcions possibles de consentiment pel servei, amb un codi
   * (noop, si o llei), i el valor conté el nom de l'opció traduit.
   */
  public es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio obtenerDominio(String id,
      es.caib.sistra.ws.v2.model.sistrafacade.ParametrosDominio parametros)
      throws SistraFacadeException {
    log.info("Executing operation obtenerDominio TIPUSPROCEDIMENT");
    log.info("Domini TipusProcediment ID: " + id);
    log.info("Domini TipusProcediment Parametres: " + parametros);

    es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio _return;
    _return = new es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio();

    try {

      // No necessitam

      String lang = "ca";
      if (parametros.getParametro().size() > 1) {
        lang = parametros.getParametro().get(1);
        log.info(" Rebut llenguatge = ]" + lang + "[");

        if (!lang.equals("ca") && !lang.equals("es")) {
          _return.setError(true);
          _return
              .setDescripcionError("El paràmetre llenguatge no és cap dels soportats " + lang);
          return _return;
        }
      }

      List<TipusProcediment> tipus = TipusProcediments.getAllTipusProcediments();

      Filas filas = new Filas();
      List<Fila> filasList = filas.getFila();

      for (TipusProcediment tp : tipus) {

        String label = "es".equals(lang) ? tp.castella : tp.catala;

        filasList.add(getFila(String.valueOf(tp.id), label));
      }

      _return.setFilas(filas);
      return _return;

    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
      _return.setError(true);
      _return.setDescripcionError(ex.getMessage());
      return _return;
    }
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
