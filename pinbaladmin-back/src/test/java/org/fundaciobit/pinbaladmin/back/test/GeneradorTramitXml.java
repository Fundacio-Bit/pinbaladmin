package org.fundaciobit.pinbaladmin.back.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.model.PinbalAdminDaoManager;
import org.fundaciobit.pinbaladmin.model.dao.IFitxerManager;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class GeneradorTramitXml {

    public static void main(String[] args) {

        try {
            String xml;
            {
                byte[] xmlData = FileUtils.readFileToByteArray(new File("formulario.xml"));
                xml = new String(xmlData, "UTF-8");
            }

            Properties prop = ParserFormulariXML.getPropertiesFromFormulario(xml);

            String documentacion = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NIFSECG");

            
            
            
            
            String fileName = "Datos_de_la_solicitud_" + 3015170 + ".pdf";

            Document documento = new Document();
            FileOutputStream ficheroPdf = new FileOutputStream(fileName);

            PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);

            documento.open();
            
            documento.add(new Paragraph("Esto es el primer párrafo, normalito"));

            Set<Entry<Object,Object>> set = prop.entrySet();
            for (Entry<Object, Object> entry : set) {
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();

                documento.add(new Paragraph(key + ": " + value));
            }
            


            documento.close();

            File file = new File(fileName);
            long size = file.length();
            String mime = "application/pdf";
            String desc = "";

            FitxerJPA fitxer = new FitxerJPA(fileName, size, mime, desc);

            System.out.println(fitxer.getFitxerID());
            
            IFitxerManager fitxerDao = PinbalAdminDaoManager.getDaoManagers().getFitxerManager();
            fitxer = (FitxerJPA) fitxerDao.create(fitxer);

            //            FileSystemManager.crearFitxer(file, fitxer.getFitxerID());

            //            soli.setDocumentSolicitudID(fitxer.getFitxerID());
            //            solicitudLogicaEjb.update(soli);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
