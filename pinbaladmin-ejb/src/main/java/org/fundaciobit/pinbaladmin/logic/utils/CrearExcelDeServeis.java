package org.fundaciobit.pinbaladmin.logic.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.xml.parsers.ParserConfigurationException;

import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
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

  @EJB(mappedName = org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService.JNDI_NAME)
  protected static org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService documentSolicitudLogicEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.logic.DocumentLogicaService.JNDI_NAME)
  protected static org.fundaciobit.pinbaladmin.logic.DocumentLogicaService documentLogicEjb;

  
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

  protected static Map<Long, String[]> getDadesExcelBySoliServeiID(SolicitudJPA soli, String tipusExcel, Fitxer docConsentiment)
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

	String origen = soli.getConsentiment();
	String consentiment = "";
	if (origen.equals("noop")) consentiment = "NO_OPOSICION";
	if (origen.equals("si")) consentiment = "Si";
	if (origen.equals("llei")) consentiment = "Ley";
	
	log.info("Consentiment. Abans: " + origen + " Despres: " + consentiment);
	
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

      System.out.println(" LLEGING SERVEI => " + ss.getId());

      String[] dades = new String[CAMPS_EXCEL.length];

      // A 0 FORMULARIO.DATOS_SOLICITUD.CODIPROC
      dades[0] = codiProc;
      // B 1 FORMULARIO.DATOS_SOLICITUD.NOMBREPROC
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
      dades[7] = consentiment;

      {
		List<String> strNormes = new ArrayList<>();
		List<String> strArticles = new ArrayList<>();
		List<String> strUrls = new ArrayList<>();

		// Array de métodos para obtener las normas y los ficheros
		String[] articles = { ss.getArticles(), ss.getArticles2(), ss.getArticles3() };
		String[] normes = { ss.getNormaLegal(), ss.getNorma2(), ss.getNorma3() };
		FitxerJPA[] fitxersNormes = { ss.getFitxernorma(), ss.getFitxernorma2(), ss.getFitxernorma3() };

		// Validamos si existe cada fichero antes de agregarlo
		for (int i = 0; i < fitxersNormes.length; i++) {
			if (fitxersNormes[i] != null && normes[i] != null && articles[i] != null) {
				strNormes.add(normes[i]);
				strArticles.add(articles[i]);
				strUrls.add(generarURLDownload(fitxersNormes[i]));
			}
		}

		// Verificación y asignación a dades
		if (!strNormes.isEmpty()) {
			dades[8] = String.join("\n", strNormes);
			dades[9] = String.join("\n", strArticles);
			dades[10] = String.join("\n", strUrls);
		} else {
			dades[8] = "";
			dades[9] = "";
			dades[10] = "";
		}
      }
      
      // L 11 L'Enllaç de Consentiment
      {
    	if (soli.getConsentiment().equals("llei")) {
            dades[11] = "Ley";
		} else {
			String urlConsentiment = soli.getUrlconsentiment();
			if (urlConsentiment != null) {
				dades[11] = urlConsentiment;
			} else {
				// Coger todos los documentos de la solicitud y buscar el de consentimiento.
				if (docConsentiment != null) {
					String url = generarURLDownload(docConsentiment);
					dades[11] = url;
				}else {
					dades[11] = "";
				}
			}
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

  public static byte[] crearExcelDeServeis(File plantillaXLSX, SolicitudJPA soli, String tipusExcel, Fitxer docConsentiment)
      throws I18NException {

    XSSFWorkbook my_xlsx_workbook = null;
    FileInputStream input_document = null;
    try {
      Long soliID = soli.getSolicitudID();
      Map<Long, String[]> dadesByServeiSolicitudID = getDadesExcelBySoliServeiID(soli, tipusExcel, docConsentiment);

      // Read Excel document first
      input_document = new FileInputStream(plantillaXLSX);
      // convert it into a POI object
      my_xlsx_workbook = new XSSFWorkbook(input_document);
      // Read excel sheet that needs to be updated
      XSSFSheet my_worksheet = my_xlsx_workbook.getSheetAt(1); // Segona Fulla

      // Primera FILA és 2
      String  primeraFila = "1";
      String[][] capzalera = new String[][] {
          { soli.getDenominacio(), "'Entitat Nom'", primeraFila, "0" },
          { soli.getNif(), "'Entitat NIF'", primeraFila, "1" },
          { soli.getDir3(), "'Entitat DIR3'", primeraFila, "2" },          
          { "52e7bcd3aaf2d7d8ff0ece2c50a601ed", "'Numero Serie'", primeraFila, "3" },
          { "SCSP", "Tecnologia", primeraFila, "4" },
          { "3.5.0", "Versio", primeraFila, "5" }
          };

      for (String[] valors : capzalera) {

        String valor = valors[0];

        if (valor == null || valor.trim().length() == 0) {
          throw new Exception(
              "El camp '" + valors[1] + "' de la sol·licitud amb ID " + soliID + " val null ");
        }
        log.info("Capçalera[" + valors + "]");
        XSSFRow row = my_worksheet.getRow(Integer.parseInt(valors[2]));
        log.info("ROW[" + row + "]");
        Cell cell = row.getCell(Integer.parseInt(valors[3]), MissingCellPolicy.CREATE_NULL_AS_BLANK);
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

      



      return outputBA.toByteArray();

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

	public static String generarURLDownload(Fitxer arxiu) {
		String CONTEXTWEB = "/public/arxiu/";

		String url = Configuracio.getAppBackUrl();
		if (arxiu == null) {
			// TODO Llançar error
			url += "/img/blank.gif";
		} else {
			// {arxiuId}/{filename}/{contentType}
			String idfile = HibernateFileUtil.encryptFileID(arxiu.getFitxerID());

			String base = CONTEXTWEB + idfile;
			String nombre = arxiu.getNom();
			if (nombre == null) {
				url += base;
			}
			try {
				base = base + "?nom=" + URLEncoder.encode(nombre, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				base = base + "?nom=" + nombre;
			} //
			String mime = arxiu.getMime();
			if (mime == null) {
				url += base;
			}
			try {
				base = base + "&mime=" + URLEncoder.encode(mime, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				base = base + "&mime=" + mime;
			}
			url += base;
		}

		return url;
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
