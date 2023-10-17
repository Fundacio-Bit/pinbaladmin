package org.fundaciobit.pinbaladmin.back.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.document.DocumentKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

/**
 * 
 * @author anadal
 *
 */
public class ParserFormulariXML {

  public static void main(String[] args) {

    try {

      byte[] xmlData = FileUtils.readFromFile(new File("formulario.xml"));
      String xml = new String(xmlData);

      Properties prop = getPropertiesFromFormulario(xml);

      // prop.store(new FileOutputStream("formulario.properties"),
      // "PINBAL_TRAMIT");

      // String creador = "pvico";
      // long solicitudID;
      // solicitudID = creaSolicitud(creador, prop);
      // creaServeis(prop, solicitudID);

      File plantilla = new File("Formulario.odt");

      File outputPDF = new File("Formulario_Ramon_Roca.pdf");
      File outputOdt = new File("Formulario_Ramon_Roca.odt");

      creaDocFormulari(prop, plantilla, outputPDF, outputOdt);

      
      System.out.println("FINAL");
      
      System.exit(0);
      
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      
      System.exit(1);
    }

    
  }
  
  

  
  

  public static final void creaDocFormulari(Properties props, File plantilla, File outputPDF,
      File outputOdt) throws Exception {

    Map<String, Object> data = new HashMap<String, Object>();
    data.put("props", props);
    data.put("data", new Date());
    
    
    String cp = props.getProperty("FORMULARIO.DATOS_SOLICITUD.CP");
    if (cp == null || cp.trim().length() == 0 ) {
      cp = "";
    }
    data.put("cp", "");
    
    
    String entorn = props.getProperty("FORMULARIO.DATOS_SOLICITUD.LDAENTORNOCULTO");
    if (entorn.indexOf("Producció") == -1) {
      data.put("produccion", false);
    } else { 
      data.put("produccion", true);
    }
    
    System.out.println("entorn.indexOf(Preproducción) => " + entorn.indexOf("Preproducción")); 
    
    if (entorn.indexOf("Preproducció") == -1) {
      data.put("preproduccion", false);
    } else { 
      data.put("preproduccion", true);
    }
    

    FileOutputStream fosPDF = new FileOutputStream(outputPDF);
    FileOutputStream fosODT = new FileOutputStream(outputOdt);
    //FileInputStream fis = new FileInputStream(plantilla);
    
    byte[] template = FileUtils.readFromFile(plantilla);
    
    try {
       createPdf(new ByteArrayInputStream(template), fosPDF, data);
       createOdt(new ByteArrayInputStream(template), fosODT, data);

    } finally {
      try {
       
        fosODT.flush();
        fosODT.close();
        
        fosPDF.flush();
        fosPDF.close();
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  public static void createOdt(InputStream templateOdtInputStream,
      java.io.OutputStream pdfOutputStream, Map<String, Object> map) throws Exception {

      System.out.println("Load ODT file and set the template engine to: "
          + TemplateEngineKind.Freemarker.name());
      IXDocReport xdocGenerator = XDocReportRegistry.getRegistry()
          .loadReport(templateOdtInputStream, TemplateEngineKind.Freemarker);



      System.out.println("Merge Java model with the ODT and convert it to PDF...");


      xdocGenerator.process(map, pdfOutputStream);

      System.out
          .println("PDF conversion process has finished, closing the input/output streams...");
      templateOdtInputStream.close();
      pdfOutputStream.close();

  }


  public static void createPdf(InputStream templateOdtInputStream,
      java.io.OutputStream pdfOutputStream, Map<String, Object> map) throws Exception {

      System.out.println("Load ODT file and set the template engine to: "
          + TemplateEngineKind.Freemarker.name());
      IXDocReport xdocGenerator = XDocReportRegistry.getRegistry()
          .loadReport(templateOdtInputStream, TemplateEngineKind.Freemarker);

      // IContext context = xdocGenerator.createContext();

      // System.out.println("Configuring the XDOCReport Context, put object:
      // "+customer.getClass().getSimpleName());
      // context.put("customer", customer);

      // System.out.println("Including the Fonts provider (prevents missing
      // default xdocreport FontProvider).");
//       PdfOptions subOptions = PdfOptions.create();
//       subOptions.fontProvider(new FontProvider());

      System.out
          .println("Set format converter: " + DocumentKind.ODT + " to " + ConverterTypeTo.PDF);
       Options options =  Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF); //.subOptions(subOptions);


      System.out.println("Merge Java model with the ODT and convert it to PDF...");
      xdocGenerator.convert(map, options, pdfOutputStream);



      System.out
          .println("PDF conversion process has finished, closing the input/output streams...");
      templateOdtInputStream.close();
      pdfOutputStream.close();

      System.out.println("FINAL");


  }


  // XYZ ZZZ TODO
  public static long FINDSERVEIBYCODI(String codi) {

    return 0;

  };

  public static Properties getPropertiesFromFormulario(String xml)
      throws Exception {
    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(xml));

    org.w3c.dom.Document doc = db.parse(is);

    NodeList nodesForm = ((org.w3c.dom.Document) doc).getElementsByTagName("FORMULARIO");

    Properties prop = new Properties();

    Node element = nodesForm.item(0);
    findAttrInChildren("", element, prop);
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

      
//      for (int i = 0; i < nodeMap.getLength(); i++) {
//        System.out.println(nodeMap.item(i).getNodeName());
//    }

      /*System.out.println("Item NAME => " + key);
      System.out.println("Item VALUE => " + value);
      System.out.println(); */
//    XYZ JDK11 afegit if, s'ha de llevar

//      Node node = element.getFirstChild();
//      if (node != null) 
      {
          prop.setProperty(key, value);
//          System.out.println("" + node.getValue());
//          System.out.println("" + node.getStringValue());
          System.out.println(key + " : " + element.getFirstChild().getNodeValue());
      }
    }
  }
}
