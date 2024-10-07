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
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.controller.FileDownloadController;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.xml.sax.SAXException;

/**
 * 
 * @author anadal
 *
 */
public class CrearExcelDeServeis {

  protected static final Logger log = Logger.getLogger(CrearExcelDeServeis.class);

  public static final String[] CAMPS_EXCEL = {
          "Código del Procedimiento", //0
          "Nombre del Procedimiento", //1
          "Cedente", //2
          "Servicio", //3
          "Periodo", //4
          "Descripción (Codi. Desc. de Sol·licitud o DESCRIPCION de formulari.xml))", //5
          "Tipo de Procedimiento", //6
          "Consentimiento (Possibles valors: Si, Sí, Llei, Ley, No oposició, No oposición) ", //7
          "Norma Legal", //8
          "Artículos", //9
          "Enlace http Norma Legal", //10
          "Enlace http Consentimiento ", //11
          "Caducidad", //12
          "Periódico", //13
          "Automatizado", //14
          "Peticiones al dia" // 15 
  };

  protected static Map<Long, String[]> getDadesExcelBySoliServeiID(SolicitudJPA soli, String tipusExcel)
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

    String periodo = "10 años";
    String automatizado = "NO"; //soli.getAutomatizado(); // values.get("FORMULARIO.DATOS_SOLICITUD.AUTOMATIZADO");
    String periodico = "NO"; //soli.getPeriodico(); // values.get("FORMULARIO.DATOS_SOLICITUD.PERIODICO");
    String peticionsDia = "30"; //soli.getPetsDia(); // values.get("FORMULARIO.DATOS_SOLICITUD.PETICIONESDIA");
    
    Map<Long, String[]> dadesByServeiSolicitudID = new HashMap<Long, String[]>();

    log.info("Serveis de la sol·licitud: " + serveisDeLaSolicitud.size());
    
    for (SolicitudServeiJPA ss : serveisDeLaSolicitud) {

    	boolean esLocal = ss.getServei().getEntitatServei().isBalears();
    	boolean volemLocal = tipusExcel.equals("locals");

    	if((esLocal && !volemLocal) || (!esLocal && volemLocal)) {
    		continue;
    	}


    	
      // String base = "FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".";

    	log.info("\tLLEGING SERVEI => " + ss.getId());

      String[] dades = new String[CAMPS_EXCEL.length];

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

      //E 4 PERIODO 
      if (ss.getServei().getCodi().equals("SVDINESECOPAHISTORICOMUNICIPIOSWS01")) {
          dades[4] = periodo;        
      }else {
          dades[4] = "";        
      }
      // F 5 FORMULARIO.DATOS_SOLICITUD.DESCRIPCION
      dades[5] = descripcio;

      //  G 6 (Ayudas, Becas y Subvenciones) TIPUS PROCEDIMENT
      dades[6] = tipusProcediment;

      // H 7 FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.CONSENTIMIENTO No
      // oposición => NO_OPOSICION
      {
        String origen = ss.getConsentiment(); // values.get(base +
                                              // "LDECONSENTIMIENTO");
        // Sí => Si
        // No oposición => NO_OPOSICION
        // Ley => Ley

        final String consentiment;
        if(origen.equals("noop")) {
            consentiment = "NO_OPOSICION";
        } else if (origen.equals("si")) {
            consentiment = "Si";
        } else if (origen.equals("llei")) {
            consentiment = "Ley";
        } else {
            consentiment = "";
        }

        // values.get(base + "CONSENTIMIENTO");
        dades[7] = consentiment;
      }

      // I 8 FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.NORMALEGAL
      dades[8] = ss.getNormaLegal(); // values.get(base + "NORMALEGAL");
      // J 9 FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.ARTICULOS
      dades[9] = ss.getArticles(); // values.get(base + "ARTICULOS");
      //dades[10] = ss.getEnllazNormaLegal(); // values.get(base + "ENLACENOR");
      
      // K 10 FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.ENLACENOR
      Fitxer fitxerNorma = ss.getFitxernorma();
      if (fitxerNorma == null) {
    	  dades[10] = ss.getEnllazNormaLegal();
      } else {
    	  dades[10] = Configuracio.getAppUrl() + FileDownloadController.fileUrl(fitxerNorma);
      }      
      
      // L 11 L'Enllaç de Consentiment
      {
        String ec = ss.getEnllazConsentiment();
        if (ec == null || ec.trim().length() == 0) {
          dades[11] = "Adjunto";
        } else {
          dades[11] = ec;
        }
      }

      // M 12 FORMULARIO.DATOS_SOLICITUD.CADUCA o
      // FORMULARIO.DATOS_SOLICITUD.FECHACAD
      {
        String caduca = ss.getFechaCaduca(); // values.get("FORMULARIO.DATOS_SOLICITUD.FECHACAD");
        if (caduca == null || caduca.trim().length() == 0) {
          caduca = ss.getCaduca(); // values.get("FORMULARIO.DATOS_SOLICITUD.CADUCA");
        }
        dades[12] = caduca;
      }

      // N 13 Periodico (FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.ENLACENOR)
      dades[13] = periodico;
      // O 14 Automatizado (FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.ENLACENOR)
      dades[14] = automatizado;
      
      // P 15 Peticiones estimadas (día) (FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID2.ENLACENOR)
      dades[15] = peticionsDia;

      dadesByServeiSolicitudID.put(ss.getId(), dades);
    }

    return dadesByServeiSolicitudID;
  }

  public static byte[] crearExcelDeServeis(File plantillaXLSX, SolicitudJPA soli, String tipusExcel)
			throws I18NException {

		XSSFWorkbook my_xlsx_workbook = null;
		FileInputStream input_document = null;
		try {
			Long soliID = soli.getSolicitudID();
			Map<Long, String[]> dadesByServeiSolicitudID = getDadesExcelBySoliServeiID(soli, tipusExcel);

			if (dadesByServeiSolicitudID.isEmpty()) {
				// Si no hi ha serveis, no cal generar l'excel
				return null;
			}

			// Read Excel document first
			input_document = new FileInputStream(plantillaXLSX);
			// convert it into a POI object
			my_xlsx_workbook = new XSSFWorkbook(input_document);
			// Read excel sheet that needs to be updated
			XSSFSheet my_worksheet = my_xlsx_workbook.getSheetAt(1); // Segona Fulla

			// Primera FILA és 2
			int primeraFila = 1;
			String[][] capzalera = new String[][] { { soli.getDenominacio(), "'Entitat Nom'" },
					{ soli.getNif(), "'Entitat NIF'" }, { soli.getDir3(), "'Entitat DIR3'" },
					{ "52e7bcd3aaf2d7d8ff0ece2c50a601ed", "'Numero Serie'" }, { "SCSP", "Tecnologia" },
					{ "3.5.0", "Versio" } };

			XSSFRow firstRow = my_worksheet.getRow(primeraFila);

			for (int col = 0; col < capzalera.length; col++) {
				String[] dataCol = capzalera[col];
				String value = dataCol[0];
				String campName = dataCol[1];

				if (value == null || value.trim().length() == 0) {
					throw new Exception("El camp '" + campName + "' de la sol·licitud amb ID " + soliID + " val null ");
				}

				Cell cell = firstRow.getCell(col, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellValue(value);
			}
			log.info("firstRow[" + firstRow + "]");

			// Afegim les dades dels serveis a partir de la fila 6
			int row = 6;
			for (Long serveiSolicitudID : dadesByServeiSolicitudID.keySet()) {

				String[] values = dadesByServeiSolicitudID.get(serveiSolicitudID);
				for (int col = 0; col < values.length; col++) {

					if (values[col] == null) {
						String camp = CAMPS_EXCEL[col];

						throw new Exception("El camp '" + camp + "' del servei-solicitud amb ID " + serveiSolicitudID
								+ " o solicitud " + soliID + " val null ");
					} else {
						Cell cell = my_worksheet.getRow(row).getCell(col);
						cell.setCellValue(values[col]);
					}
				}
				row++;
			}

			
			// Open OutputStream to write updates
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			// write changes
			my_xlsx_workbook.write(output);
			// close the stream
			output.close();

			return output.toByteArray();

		} catch (Exception e) {
			String msg = "Error generant plantilla excel: " + e.getMessage();
			log.error(msg, e);
			throw new I18NException("genapp.comodi", msg);
		} finally {

			if (my_xlsx_workbook != null) {
				try {
					my_xlsx_workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (input_document != null) {
				// important to close InputStream
				try {
					input_document.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

  /*
   * public static Properties getPropertiesFromFormulario(String xml) throws
   * ParserConfigurationException, FileNotFoundException, SAXException,
   * IOException { DocumentBuilder db =
   * DocumentBuilderFactory.newInstance().newDocumentBuilder(); InputSource is =
   * new InputSource(); is.setCharacterStream(new StringReader(xml));
   * 
   * org.w3c.dom.Document doc = db.parse(is);
   * 
   * NodeList nodesForm = ((org.w3c.dom.Document)
   * doc).getElementsByTagName("FORMULARIO");
   * 
   * Properties prop = new Properties();
   * 
   * findAttrInChildren("", nodesForm.item(0), prop); return prop; }
   * 
   * private static void findAttrInChildren(String base, Node element,
   * Properties prop) { // if (!element.getAttribute(tag).isEmpty()) { // return
   * element.getAttribute(tag); // }
   * 
   * String name = element.getNodeName();
   * 
   * if (name.equals("FORMULARIO") || name.equals("DATOS_SOLICITUD") ||
   * name.equals("DATOS_REGISTRO") || name.equals("LELSERVICIOS") ||
   * name.startsWith("ID") || name.equals("LELSERVICIOSOCULEXCEL")) { NodeList
   * children = element.getChildNodes(); for (int i = 0, len =
   * children.getLength(); i < len; i++) { if (children.item(i).getNodeType() ==
   * Node.ELEMENT_NODE) { Element childElement = (Element) children.item(i);
   * findAttrInChildren(base + name + ".", childElement, prop); } } } else {
   * 
   * String key = base + element.getNodeName(); String value =
   * element.getTextContent(); System.out.println("Item NAME => " + key);
   * System.out.println("Item VALUE => " + value); System.out.println();
   * 
   * prop.setProperty(key, value);
   * 
   * } }
   */
}
