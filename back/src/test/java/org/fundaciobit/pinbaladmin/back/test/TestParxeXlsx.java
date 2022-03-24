package org.fundaciobit.pinbaladmin.back.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import org.fundaciobit.pinbaladmin.back.utils.ParserSolicitudXLSX;
import org.fundaciobit.pinbaladmin.back.utils.ProcedimentInfo;
import org.fundaciobit.pinbaladmin.back.utils.SolicitudInfo;

/**
 * 
 * @author anadal
 *
 */
public class TestParxeXlsx {

  public static void main(String[] args) {

    File plantillaXLSX = new File("plantilla.xlsx");

    java.io.InputStream input_document = null;
    try {
      boolean debug = true;

      input_document = new FileInputStream(plantillaXLSX);

      SolicitudInfo info = ParserSolicitudXLSX.extreureInfo(input_document, debug);

      System.out.println("---- ENTITAT: " + info.getEntitat());

      Map<String, ProcedimentInfo> procediments = info.getProcediments();

      for (String procID : procediments.keySet()) {

        ProcedimentInfo proc = procediments.get(procID);

        System.out.println("=========================");
        System.out.println(" Codi: " + proc.getCodi());
        System.out.println(" Nom: " + (proc.getNom().length() < 50 ? proc.getNom()
            : (proc.getNom().substring(0, 50) + "...")));
        System.out.println(" Tipus: " + proc.getTipusProcediment());
        System.out.println(" Serveis: " + proc.getServicios());

      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      if (input_document != null) {
        try {
          input_document.close();
        } catch (Exception e) {
          // TODO: handle exception
          e.printStackTrace();
        }
      }
    }

  }

}
