package org.fundaciobit.pinbaladmin.back.controller.all;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitAOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutForm;
import org.fundaciobit.pinbaladmin.back.utils.CrearExcelDeServeis;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitAPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitAPersAutForm.class, TramitAPersAutFilterForm.class })
public class TramitAPublicController extends TramitAOperadorController {

    public static final String CONTEXT_WEB = "/public/tramita";
    public static final String RETURN_URL = "/public/tramita/list";
    public static final String RETURN_TO_SISTRA = RETURN_URL;
    public static final String CONTEXT_WEB_NEXT = TramitBPublicController.CONTEXT_WEB;

    @Override
    public boolean isPublic() {
        return true;
    }
    
    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }

    @Override
    public String getTileForm() {
        return "tramitAFormPublic";
    }

    @Override
    public String getTileList() {
        return "tramitSistraListPublic";
    }

    @Override
    public TramitAPersAutForm getTramitAPersAutForm(TramitAPersAutJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitAPersAutForm tramitForm = super.getTramitAPersAutForm(_jpa, __isView, request, mav);

        tramitForm.addHiddenField(TRAMITID);
        tramitForm.addHiddenField(URLSISTRA);
        tramitForm.addHiddenField(IDSESIONFORMULARIO);
        tramitForm.addHiddenField(DATATRAMIT);

        TramitAPersAutJPA tramitA = tramitForm.getTramitAPersAut();
        
        if (tramitForm.isNou()) {
            tramitA.setNif("45186147W");
            tramitA.setNom("Pau");
            tramitA.setTelefon("971123132");
            tramitA.setMail("mail@fbit.org");
            tramitA.setLlinatge1("Trias");
            tramitA.setLlinatge2("Segura");
            tramitA.setDatatramit(new Timestamp(System.currentTimeMillis()));
        }else {
            tramitA.setTelefon("telf");
            tramitA.setMail("mail@fbit.org");
        }
        
        return tramitForm;
    }

    @RequestMapping(value = "/finalitzarTramit/{uuid}", method = RequestMethod.GET)
    public ModelAndView getRedirectToFinish(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("uuid") java.lang.String uuid) {

        ModelAndView mav = new ModelAndView("returnToSistraPost");
        try {
            Long tramitID = HibernateFileUtil.decryptFileID(uuid);
            crearSolicitudAmbTramitID(tramitID);

            TramitAPersAut tramitA = tramitAPersAutLogicEjb.findByPrimaryKey(tramitID);

            String urlCallbackSistra = tramitA.getUrlsistra(); // "https://se.caib.es/sistramitfront/asistente/retornoGestorFormularioExterno.html";
            String idSesionFormulario = tramitA.getIdsesionformulario();
            String ticketGFE = uuid;
            String ticket = idSesionFormulario + ":" + ticketGFE;

            mav.addObject("urlCallbackSistra", urlCallbackSistra);
            mav.addObject("ticket", ticket);

            log.info("URL Callback: " + urlCallbackSistra);
            log.info("ticket:" + ticket);

        } catch (Exception e) {
            log.error(e);
            HtmlUtils.saveMessageError(request, "Error creant incidencia a Sistra: " + e.getMessage());
        }
        return mav;
    }

    public void crearSolicitudAmbTramitID(Long tramitID) throws Exception {
        log.info("Generador del fitxer XML amb tramitID=" + tramitID);

        SolicitudJPA soli = tramitAPersAutLogicEjb.crearSolicitudAmbTramit(tramitID);
        log.info("Solicitud Creada a BBDD: " + soli.getSolicitudID());

//        log.info("Serveis de la solicitud: " + soli.getSolicitudServeis().size());
        
//        Long fitxerID = soli.getSolicitudXmlID();
//        Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
//
//        generarDocumentSolicitudAmbXML(soli, prop);
//        generarDocumentsSolicitud(soli, prop);
//        generarExcelDeServeis(soli);

//        log.info("Generat");
    }

    public void generarDocumentsSolicitud(SolicitudJPA soli, Properties prop) throws Exception, I18NException {

        Long solicitudID = soli.getSolicitudID();

        Organ organGestor = organLogicaEjb.findByPrimaryKey(soli.getOrganid());
        while (organGestor.getDir3pare() != null) {
            List<Organ> organ = organLogicaEjb.select(OrganFields.DIR3.equal(organGestor.getDir3pare()));
            if (organ.size() == 1) {
                organGestor = organ.get(0);
            }
        }

        if (organGestor.getCif().equals("S0711001H")) {
            Organ dgtic = organLogicaEjb.findByPrimaryKey(70012);
            prop.setProperty("FORMULARIO.DATOS_SOLICITUD.UNIDAD", dgtic.getNom());
            prop.setProperty("FORMULARIO.DATOS_SOLICITUD.CODIUR", dgtic.getDir3());
        }

        File outputPDF = File.createTempFile("pinbaladmin_formulari", ".pdf");
        File outputODT = File.createTempFile("pinbaladmin_formulari", ".odt");

        File plantilla = new File(Configuracio.getTemplateFormulari());

        ParserFormulariXML.creaDocFormulari(prop, plantilla, outputPDF, outputODT);

        {
            FitxerJPA fitxer = new FitxerJPA("Formulario_Director_General.pdf", outputPDF.length(), "application/pdf",
                    "");

            fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

            FileSystemManager.sobreescriureFitxer(outputPDF, fitxer.getFitxerID());

            Long tipus = Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF;
            Document doc = documentLogicaEjb.create("Formulario_Director_General (PDF)", fitxer.getFitxerID(), null,
                    null, tipus);

            DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), solicitudID);
            documentSolicitudLogicaEjb.create(ds);
        }

        {
            FitxerJPA fitxer = new FitxerJPA("Formulario_Director_General.odt", outputODT.length(),
                    "application/vnd.oasis.opendocument.text", "");

            fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

            FileSystemManager.sobreescriureFitxer(outputODT, fitxer.getFitxerID());

            Long tipus = Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_ODT;
            Document doc = documentLogicaEjb.create("Formulario_Director_General (ODT)", fitxer.getFitxerID(), null,
                    null, tipus);

            DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), solicitudID);
            documentSolicitudLogicaEjb.create(ds);
        }
    }

    public void generarExcelDeServeis(SolicitudJPA soli) throws Exception, I18NException {

        Long solicitudID = soli.getSolicitudID();

        log.info("generaPlantillaExcelDeServeis(); => SOLI = " + solicitudID);

        File plantillaXLSX = new File(Configuracio.getTemplateServeisExcel());

        byte[] data = CrearExcelDeServeis.crearExcelDeServeis(plantillaXLSX, soli);

        String nom = SDF.format(new Date()) + plantillaXLSX.getName();
        Fitxer f = fitxerPublicLogicaEjb.create(nom, data.length,
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", null);

        FileSystemManager.crearFitxer(new ByteArrayInputStream(data), f.getFitxerID());

        Long tipus = Constants.DOCUMENT_SOLICITUD_EXCEL_SERVEIS;
        Document doc = documentLogicaEjb.create(nom, f.getFitxerID(), null, null, tipus);

        DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), solicitudID);

        documentSolicitudLogicaEjb.create(ds);

        log.info("Generat el fitxer de serveis. Revisar un document titulat " + nom
                + " dins de l'apartat de 'Llistat de Documents-Sol·licitud'");
    }

    public void generarDocumentSolicitudAmbXML(SolicitudJPA soli, Properties prop) throws Exception {

        String fileName = "Datos_de_la_solicitud_" + soli.getProcedimentCodi() + ".pdf";

        com.lowagie.text.Document documento = new com.lowagie.text.Document();
        FileOutputStream ficheroPdf = new FileOutputStream(fileName);

        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);

        documento.open();
        Set<Entry<Object, Object>> set = prop.entrySet();
        for (Entry<Object, Object> entry : set) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            documento.add(new Paragraph(key + ": " + value));
        }
        documento.add(new Paragraph("Esto es el primer párrafo, normalito"));

        documento.close();

        File file = new File(fileName);
        long size = file.length();
        String mime = "application/pdf";
        String desc = "";

        FitxerJPA fitxer = new FitxerJPA(fileName, size, mime, desc);
        fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

        FileSystemManager.crearFitxer(file, fitxer.getFitxerID());

        soli.setDocumentSolicitudID(fitxer.getFitxerID());
        solicitudLogicaEjb.update(soli);
    }

}
