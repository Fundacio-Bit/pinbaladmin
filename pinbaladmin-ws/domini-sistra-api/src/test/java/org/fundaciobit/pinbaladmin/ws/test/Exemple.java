package org.fundaciobit.pinbaladmin.ws.test;

import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import es.caib.sistra.ws.v2.model.sistrafacade.ParametrosDominio;
import es.caib.sistra.ws.v2.model.valoresdominio.Columna;
import es.caib.sistra.ws.v2.model.valoresdominio.Fila;
import es.caib.sistra.ws.v2.model.valoresdominio.Filas;
import es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio;
import es.caib.sistra.ws.v2.services.SistraConsentimentFacade;
import es.caib.sistra.ws.v2.services.SistraConsentimentFacadeService;
import es.caib.sistra.ws.v2.services.SistraEntitatsFacade;
import es.caib.sistra.ws.v2.services.SistraEntitatsFacadeService;
import es.caib.sistra.ws.v2.services.SistraFacadeException;
import es.caib.sistra.ws.v2.services.SistraTipusProcedimentFacade;
import es.caib.sistra.ws.v2.services.SistraTipusProcedimentFacadeService;

/**
 * 
 * @author anadal
 *
 */
public class Exemple {

    public static final Logger log = Logger.getLogger(Exemple.class);

    private static final Properties testProperties = new Properties();

    static {

        // Propietats del Servidor i del test
        try {
            testProperties.load(new FileInputStream("test.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println("Entra dins l'exemple.");
        try {
            
            
            SistraEntitatsFacade apiEntitats;
            {
                // Adreça servidor
                String endpoint = getEndPoint() + "/entitats";
                SistraEntitatsFacadeService service;
                System.out.println("endpoint " + endpoint);

                URL wsdl = new URL(endpoint + "?wsdl");
                service = new SistraEntitatsFacadeService(wsdl);
                apiEntitats = service.getSistraEntitatsFacade();

                Map<String, Object> reqContext;
                reqContext = ((BindingProvider) apiEntitats).getRequestContext();
                reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

                //    reqContext.put(BindingProvider.USERNAME_PROPERTY, usr_app);
                //    reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd_app);

            }

            testEntitats(apiEntitats);

            /*
            SistraTipusProcedimentFacade apiTipusProcediment;
            {
                // Adreça servidor
                String endpoint = getEndPoint() + "/tipusprocediment";
            
            
            
                SistraTipusProcedimentFacadeService service;
                System.out.println("endpoint " + endpoint);
            
                URL wsdl = new URL(endpoint + "?wsdl");
                service = new SistraTipusProcedimentFacadeService(wsdl);
                apiTipusProcediment = service.getSistraTipusProcedimentFacade();
            
                Map<String, Object> reqContext;
                reqContext = ((BindingProvider) apiTipusProcediment).getRequestContext();
                reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
               
             //   reqContext.put(BindingProvider.USERNAME_PROPERTY, usr_app);
             //   reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd_app);
             
            
            }
            

            testTipusProcediment(apiTipusProcediment);

            SistraConsentimentFacade apiConsentiment;
            {
                // Adreça servidor
                String endpoint = getEndPoint() + "/consentiment";
                SistraConsentimentFacadeService service;
                System.out.println("endpoint " + endpoint);

                URL wsdl = new URL(endpoint + "?wsdl");
                service = new SistraConsentimentFacadeService(wsdl);
                apiConsentiment = service.getSistraConsentimentFacade();

                Map<String, Object> reqContext;
                reqContext = ((BindingProvider) apiConsentiment).getRequestContext();
                reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

                //    reqContext.put(BindingProvider.USERNAME_PROPERTY, usr_app);
                //    reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd_app);

            }

            testConsentiment(apiConsentiment);
*/
        } catch (Exception e) {
            System.err.println("Error desconegut: " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected static void testConsentiment(SistraConsentimentFacade api) throws SistraFacadeException {
        String id = "12341234124312";

        ParametrosDominio parametros = new ParametrosDominio();
        parametros.getParametro().add("AEATIAE"); // Codi Servei
        parametros.getParametro().add("ca");

        ValoresDominio valores = api.obtenerDominio(id, parametros);

        if (valores.getDescripcionError() != null && valores.getDescripcionError().getValue() != null) {
            System.err.println("ERROR: " + valores.getDescripcionError().getValue());
        } else {
            Filas filas = valores.getFilas().getValue();

            List<Fila> list = filas.getFila();

            for (Fila fila : list) {
                System.out.println("=======================");
                List<Columna> columns = fila.getColumna();
                for (Columna col : columns) {
                    System.out.println("\t" + col.getCodigo() + ": " + col.getValor());
                }

            }

        }
        log.info("Versio " + valores);
    }

    protected static void testTipusProcediment(SistraTipusProcedimentFacade api) throws SistraFacadeException {
        String id = "12341234124312";

        ParametrosDominio parametros = new ParametrosDominio();
        parametros.getParametro().add("ca");

        ValoresDominio valores = api.obtenerDominio(id, parametros);

        if (valores.getDescripcionError() != null && valores.getDescripcionError().getValue() != null) {
            System.err.println("ERROR: " + valores.getDescripcionError().getValue());
        } else {
            Filas filas = valores.getFilas().getValue();

            List<Fila> list = filas.getFila();

            for (Fila fila : list) {
                System.out.println("=======================");
                List<Columna> columns = fila.getColumna();
                for (Columna col : columns) {
                    System.out.println("\t" + col.getCodigo() + ": " + col.getValor());
                }

            }

        }
        log.info("Versio " + valores);
    }
    
    

    protected static void testEntitats(SistraEntitatsFacade api) throws SistraFacadeException {
        String id = "12341234124312";

        ParametrosDominio parametros = new ParametrosDominio();
        parametros.getParametro().add("ca");

        ValoresDominio valores = api.obtenerDominio(id, parametros);

        if (valores.getDescripcionError() != null && valores.getDescripcionError().getValue() != null) {
            System.err.println("ERROR: " + valores.getDescripcionError().getValue());
        } else {
            Filas filas = valores.getFilas().getValue();

            List<Fila> list = filas.getFila();

            for (Fila fila : list) {
                System.out.println("=======================");
                List<Columna> columns = fila.getColumna();
                for (Columna col : columns) {
                    System.out.println("\t" + col.getCodigo() + ": " + col.getValor());
                }

            }

        }
        log.info("Versio " + valores);
    }

    public static String getEndPoint() {
        return testProperties.getProperty("test_host_base");
    }
    /*
      public static String getTestAppUserName() {
    return testProperties.getProperty("test_usr");
      }
      
    
    
      public static String getTestAppPassword() {
    return testProperties.getProperty("test_pwd");
      }
    
    */
}
