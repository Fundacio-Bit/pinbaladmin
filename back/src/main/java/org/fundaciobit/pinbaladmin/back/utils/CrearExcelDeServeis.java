package org.fundaciobit.pinbaladmin.back.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.jpa.SolicitudJPA;
import org.fundaciobit.pinbaladmin.jpa.SolicitudServeiJPA;
import org.xml.sax.SAXException;

/**
 * 
 * @author anadal
 *
 */
public class CrearExcelDeServeis {

  protected static final Logger log = Logger.getLogger(CrearExcelDeServeis.class);

  public static final String[] CAMPS_EXCEL = { "Código del Procedimiento",
      "Nombre del Procedimiento", "Cedente", "Servicio", "Descripción (Codi. Desc. de Sol·licitud o DESCRIPCION de formulari.xml))",
      "Tipo de Procedimiento", "Consentimiento (Possibles valors: Si, Sí, Llei, Ley, No oposició, No oposición) ", "Norma Legal", "Artículos",
      "Enlace http Norma Legal", "Enlace http Consentimiento ", "ENLACENORCaducidad",
      "Periódico", "Automatizado" };

 

  protected static Map<Long, String[]> getDadesExcelBySoliServeiID(SolicitudJPA soli)
      throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
    /*
     * Properties prop = getPropertiesFromFormulario(xml);
     * 
     * Map<String, String> values = new TreeMap<String, String>();
     * 
     * for (Object key : prop.keySet()) {
     * 
     * String keyStr = (String) key; { values.put(keyStr, (String)
     * prop.get(key)); } }
     */

    Set<SolicitudServeiJPA> serveisDeLaSolicitud = soli.getSolicitudServeis();

    String codiProc = soli.getProcedimentCodi(); // values.get("FORMULARIO.DATOS_SOLICITUD.CODIPROC");
    String nomProc = soli.getProcedimentNom(); // values.get("FORMULARIO.DATOS_SOLICITUD.NOMBREPROC");
    String tipusProcediment = soli.getProcedimentTipus();
    String descripcio = soli.getCodiDescriptiu(); // values.get("FORMULARIO.DATOS_SOLICITUD.DESCRIPCION");

    Map<Long, String[]> dadesByServeiSolicitudID = new HashMap<Long, String[]>();

    for (SolicitudServeiJPA ss : serveisDeLaSolicitud) {

      // String base = "FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".";

      System.out.println(" LLEGING SERVEI => " + ss.getId());

      String[] dades = new String[14];

      // A 0 FORMULARIO.DATOS_SOLICITUD.CODIPROC
      dades[0] = codiProc;
      // B 1 FORMULARIO.DATOS_SOLICITUD.NOMBREPROC => Traduir CATALA ????
      dades[1] = nomProc;

      // C 2 Cercar el cedent de (SVDDGPCIWS02 => DGP)
      dades[2] = ss.getServei().getEntitatServei().getNom(); // values.get(base
                                                             // + "CODISERV");
                                                             // // TODO XYZ ZZZ

      // D 3 FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.NOMSERVEI
      dades[3] = ss.getServei().getNom(); // values.get(base + "NOMSERVEI");

      // E 4 FORMULARIO.DATOS_SOLICITUD.DESCRIPCION
      dades[4] = descripcio;

      // F 5 ???' (Ayudas, Becas y Subvenciones) TIPUS PROCEDIMENT
      dades[5] = tipusProcediment;

      // G 6 FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.CONSENTIMIENTO No
      // oposición => NO_OPOSICION
      {
        String origen = ss.getConsentiment(); // values.get(base +
                                              // "LDECONSENTIMIENTO");
        // Sí => Si
        // No oposición => NO_OPOSICION
        // Ley => Ley
        final String valor;
        if ("No oposición".equals(origen) || "No oposició".equals(origen)) {
          valor = "NO_OPOSICION";
        } else if ("Sí".equals(origen) || "Si".equals(origen)) {
          valor = "Si";
        } else if ("Llei".equals(origen) || "Ley".equals(origen)) {
          valor = "Ley";
        } else {
          valor = null;
        }

        // values.get(base + "CONSENTIMIENTO");
        dades[6] = valor;
      }

      // H 7 FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.NORMALEGAL
      dades[7] = ss.getNormaLegal(); // values.get(base + "NORMALEGAL");
      // I 8 FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.ARTICULOS
      dades[8] = ss.getArticles(); // values.get(base + "ARTICULOS");
      // J 9 FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.ENLACENOR
      dades[9] = ss.getEnllazNormaLegal(); // values.get(base + "ENLACENOR");
      // K 10 ???????????
      dades[10] = "Adjunto";

      // L 11 FORMULARIO.DATOS_SOLICITUD.CADUCA o
      // FORMULARIO.DATOS_SOLICITUD.FECHACAD
      {
        String caduca = ss.getFechaCaduca(); // values.get("FORMULARIO.DATOS_SOLICITUD.FECHACAD");
        if (caduca == null || caduca.trim().length() == 0) {
          caduca = ss.getCaduca(); // values.get("FORMULARIO.DATOS_SOLICITUD.CADUCA");
        }
        dades[11] = caduca;
      }
      // M 12 ???????????? (NO)
      dades[12] = "NO";
      // N 13 ???????????? (NO)
      dades[13] = "NO";

      dadesByServeiSolicitudID.put(ss.getId(), dades);

    }    

    return dadesByServeiSolicitudID;
  }

  public static byte[] crearExcelDeServeis(File plantillaXLSX, SolicitudJPA soli)
      throws I18NException {

    try {
      Long soliID = soli.getSolicitudID();

      Map<Long, String[]> dadesByServeiSolicitudID = getDadesExcelBySoliServeiID(soli);

      // Read Excel document first
      FileInputStream input_document = new FileInputStream(plantillaXLSX);
      // convert it into a POI object
      XSSFWorkbook my_xlsx_workbook = new XSSFWorkbook(input_document);
      // Read excel sheet that needs to be updated
      XSSFSheet my_worksheet = my_xlsx_workbook.getSheetAt(1); // Segona Fulla
      
      
      

      
// Primera FILA és 2
      String[][] capzalera = new String[][] {
        { soli.getDenominacio(), "'Entitat Nom'", "1", "0" },
        { soli.getNif(), "'Entitat NIF'", "1", "1" },
        { soli.getDir3(), "'Entitat DIR3'", "1", "2" },
      };
      
      for (String[] valors : capzalera) {
        
        String valor = valors[0];
        
        if (valor == null || valor.trim().length() == 0) {
          throw new Exception("El camp '" + valors[1] + "' de la sol·licitud amb ID "
            +  soliID + " val null ");
        }
        Cell cell = my_worksheet.getRow(Integer.parseInt(valors[2])).getCell(Integer.parseInt(valors[3]));
        cell.setCellValue(valor);
      }
      
      
      
      
      
      

      int row = 6;

      for (Long serveiSolicitudID : dadesByServeiSolicitudID.keySet()) {

        String[] values = dadesByServeiSolicitudID.get(serveiSolicitudID);

        for (int i = 0; i < values.length; i++) {

          if (values[i] == null) {
            String camp = CAMPS_EXCEL[i];

            throw new Exception("El camp '" + camp + "' del servei-solicitud amb ID "
                + serveiSolicitudID + " o solicitud " + soliID + " val null ");

          } else {
            Cell cell = my_worksheet.getRow(row).getCell(i);
            cell.setCellValue(values[i]);
          }
        }
        row++;
      }

      // Open FileOutputStream to write updates
      ByteArrayOutputStream outputBA = new ByteArrayOutputStream();
      // write changes
      my_xlsx_workbook.write(outputBA);
      // close the stream
      outputBA.close();

      my_xlsx_workbook.close();

      // important to close InputStream
      input_document.close();
      
      return outputBA.toByteArray();

    } catch (Exception e) {

      String msg = "Error generant plantilla excel: " + e.getMessage();

      log.error(msg, e);

      throw new I18NException("genapp.comodi", msg);

    }

  }

  /*
  public static Properties getPropertiesFromFormulario(String xml)
      throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(xml));

    org.w3c.dom.Document doc = db.parse(is);

    NodeList nodesForm = ((org.w3c.dom.Document) doc).getElementsByTagName("FORMULARIO");

    Properties prop = new Properties();

    findAttrInChildren("", nodesForm.item(0), prop);
    return prop;
  }

  private static void findAttrInChildren(String base, Node element, Properties prop) {
    // if (!element.getAttribute(tag).isEmpty()) {
    // return element.getAttribute(tag);
    // }

    String name = element.getNodeName();

    if (name.equals("FORMULARIO") || name.equals("DATOS_SOLICITUD")
        || name.equals("DATOS_REGISTRO") || name.equals("LELSERVICIOS")
        || name.startsWith("ID") || name.equals("LELSERVICIOSOCULEXCEL")) {
      NodeList children = element.getChildNodes();
      for (int i = 0, len = children.getLength(); i < len; i++) {
        if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
          Element childElement = (Element) children.item(i);
          findAttrInChildren(base + name + ".", childElement, prop);
        }
      }
    } else {

      String key = base + element.getNodeName();
      String value = element.getTextContent();
      System.out.println("Item NAME => " + key);
      System.out.println("Item VALUE => " + value);
      System.out.println();

      prop.setProperty(key, value);

    }
  }
*/
}
