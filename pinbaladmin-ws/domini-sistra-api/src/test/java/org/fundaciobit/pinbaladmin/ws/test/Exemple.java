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

      


      SistraTipusProcedimentFacade api;
      {
        // Adreça servidor
        String endpoint = getEndPoint();
        SistraTipusProcedimentFacadeService service;
        System.out.println("endpoint " + endpoint);

        URL wsdl = new URL(endpoint + "?wsdl");
        service = new SistraTipusProcedimentFacadeService(wsdl);
        api = service.getSistraTipusProcedimentFacade();

        Map<String, Object> reqContext;
        reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        /*
        reqContext.put(BindingProvider.USERNAME_PROPERTY, usr_app);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd_app);
        */
        
      }
      
      String id = "12341234124312";
      
      ParametrosDominio parametros = new ParametrosDominio();
      parametros.getParametro().add("ca");
   
      ValoresDominio valores = api.obtenerDominio(id, parametros); 

      
      if (valores.getDescripcionError() != null && valores.getDescripcionError().getValue() != null) {
          System.err.println("ERROR: " + valores.getDescripcionError().getValue());
      } else {
        Filas filas = valores.getFilas().getValue();
        
        List<Fila> list = filas.getFila();
        
        for(Fila fila: list) {
            System.out.println("=======================");
            List<Columna> columns = fila.getColumna();
            for (Columna col : columns) {
                System.out.println("\t" + col.getCodigo() + ": " + col.getValor());
            }
            
        }
        
      }
      log.info("Versio " + valores);



    } catch (Exception e) {
      System.err.println("Error desconegut: " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  
  
  
  
  public static String getEndPoint() {
    return testProperties.getProperty("test_host");
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
