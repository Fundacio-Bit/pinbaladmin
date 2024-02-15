package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesFormManager;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitAPersAutController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutForm;
import org.fundaciobit.pinbaladmin.back.utils.CrearExcelDeServeis;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.FitxerService;
import org.fundaciobit.pinbaladmin.ejb.OrganService;
import org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.DocumentLogicaService;
import org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitIServLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;
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

import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64.InputStream;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitAOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitAPersAutForm.class, TramitAPersAutFilterForm.class })
public class TramitAOperadorController extends TramitAPersAutController {

    public static final String CONTEXT_WEB = "/operador/tramita";
    public static final String RETURN_URL = "/operador/tramita/list";
    public static final String RETURN_TO_SISTRA = RETURN_URL;
    

    public static final int ADDITONAL_FIELD_START = 1;

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd_HH.mm_");

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;
    
    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @EJB(mappedName = TramitIServLogicaService.JNDI_NAME)
    protected TramitIServLogicaService tramitIServLogicEjb;

    @EJB(mappedName = OrganLogicaService.JNDI_NAME)
    protected OrganLogicaService organLogicaEjb;
    
    @EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
    protected FitxerPublicLogicaService fitxerPublicLogicaEjb;
    @EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
    protected DocumentSolicitudLogicaService documentSolicitudLogicaEjb;
    @EJB(mappedName = DocumentLogicaService.JNDI_NAME)
    protected DocumentLogicaService documentLogicaEjb;

 
    @Override
    public String getTileForm() {
        return "tramitAFormOperador";
    }

    @Override
    public String getTileList() {
        return "tramitSistraListOperador";
    }

    @Override
    public boolean isActiveList() {
        return true;
    }

    @Override
    public boolean isActiveFormNew() {
        return true;
    }

    @Override
    public boolean isActiveFormEdit() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return true;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitAPersAutForm tramitAPersAutForm) {
        //Cada vegada que es faci create, anar al tramit B
        Long tramitId = tramitAPersAutForm.getTramitAPersAut().getTramitid();

        //Al form del seguent, getParameter del tramitid, i utilitzar-ho per crear el tramitB
        return "redirect:" + TramitBOperadorController.CONTEXT_WEB + "/new?tramitid=" + tramitId;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long _ID_) {
        try {
            Long tramitID = (Long) request.getSession().getAttribute("tramitid");
            log.info("Estamos en A, vamos a borrar. TramitID=" + tramitID);

            if (tramitID == null) {
                log.info("No se borran tablas porque estás en edit o en view");
            } else {
                tramitAPersAutLogicEjb.deleteFull(tramitID);
                request.getSession().removeAttribute("tramitid");
                HtmlUtils.saveMessageError(request, "Tramit Cancelat (taules borrades)");
            }
        } catch (I18NException e) {
            HtmlUtils.saveMessageError(request, "Error esborrant les taules del tramit sistra");
        }
        return "redirect:" + TramitAOperadorController.RETURN_URL;
    }

    @Override
    public TramitAPersAutForm getTramitAPersAutForm(TramitAPersAutJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitAPersAutForm tramitForm = super.getTramitAPersAutForm(_jpa, __isView, request, mav);

        if (__isView) {

        } else if (tramitForm.isNou()) {
            TramitAPersAutJPA tramitA = tramitForm.getTramitAPersAut();
            tramitA.setTramitid(tramitA.getPersautid());
            tramitForm.addHiddenField(TRAMITID);
            tramitForm.addHiddenField(DATATRAMIT);

            tramitA.setNif("45186147W");
            tramitA.setNom("Pau");
            tramitA.setTelefon("971123132");
            tramitA.setMail("mail@fbit.org");
            tramitA.setLlinatge1("Trias");
            tramitA.setLlinatge2("Segura");
            tramitA.setDatatramit(new Timestamp(System.currentTimeMillis()));
        }
        tramitForm.setTitleCode("tramit.sistra.titol.a");

        return tramitForm;
    }

    @Override
    public TramitAPersAutJPA create(HttpServletRequest request, TramitAPersAutJPA tramitAPersAut)
            throws I18NException, I18NValidationException {
        return (TramitAPersAutJPA) tramitAPersAutLogicEjb.create(tramitAPersAut);
    }

    public TramitAPersAutFilterForm getTramitAPersAutFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        TramitAPersAutFilterForm tramitAPersAutFilterForm = super.getTramitAPersAutFilterForm(pagina, mav, request);
        if (tramitAPersAutFilterForm.isNou()) {

            Set<Field<?>> hiddens = new HashSet<Field<?>>(
                    Arrays.asList(TramitAPersAutFields.ALL_TRAMITAPERSAUT_FIELDS));
            hiddens.remove(TramitAPersAutFields.TRAMITID);
            hiddens.remove(TramitAPersAutFields.DATATRAMIT);
            tramitAPersAutFilterForm.setHiddenFields(hiddens);

            tramitAPersAutFilterForm.setAddButtonVisible(false);
            tramitAPersAutFilterForm.addAdditionalButton(new AdditionalButton("fas fa-plus-circle",
                    "tramir.sistra.start", getContextWeb() + "/new", "btn-primary"));

            OrderBy[] orderBy = { new OrderBy(TramitAPersAutFields.DATATRAMIT) };
            tramitAPersAutFilterForm.setDefaultOrderBy(orderBy);
            tramitAPersAutFilterForm.setOrderBy(TramitAPersAutFields.DATATRAMIT.fullName);
            tramitAPersAutFilterForm.setOrderAsc(false);

            tramitAPersAutFilterForm.setEditButtonVisible(false);

            String[] addFields = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
            for (int i = 0; i < addFields.length; i++) {
                AdditionalField<Long, String> addFieldName = new AdditionalField<Long, String>();
                addFieldName.setCodeName("=Tramit" + addFields[i]);
                addFieldName.setPosition(ADDITONAL_FIELD_START + i);
                // Els valors s'ompliran al mètode postList()
                addFieldName.setValueMap(new HashMap<Long, String>());
                addFieldName.setEscapeXml(false);

                tramitAPersAutFilterForm.addAdditionalField(addFieldName);
            }
        }

        return tramitAPersAutFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, TramitAPersAutFilterForm filterForm,
            List<TramitAPersAut> list) throws I18NException {
        super.postList(request, mav, filterForm, list);
        filterForm.getAdditionalButtonsByPK().clear();

        String[] contextsWeb = { TramitAOperadorController.CONTEXT_WEB, TramitBOperadorController.CONTEXT_WEB,
                TramitCOperadorController.CONTEXT_WEB, TramitDOperadorController.CONTEXT_WEB,
                TramitEOperadorController.CONTEXT_WEB, TramitFOperadorController.CONTEXT_WEB,
                TramitGOperadorController.CONTEXT_WEB, TramitHOperadorController.CONTEXT_WEB,
                TramitIOperadorController.CONTEXT_WEB, TramitJOperadorController.CONTEXT_WEB };
        List<Map<Long, String>> lista = new ArrayList<Map<Long, String>>();

        for (int i = 0; i < contextsWeb.length; i++) {
            Map<Long, String> map;
            map = (Map<Long, String>) filterForm.getAdditionalField(ADDITONAL_FIELD_START + i).getValueMap();
            map.clear();
            lista.add(map);
        }

        String[] letras = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

        for (TramitAPersAut tramitA : list) {
            long tramitID = tramitA.getTramitid();
            String uuid =  HibernateFileUtil.encryptFileID(tramitID);
            
            Long[] identificadorsTramit = tramitAPersAutLogicEjb.getPartsTramitIDs(tramitID);
            boolean permetFinalitzar = true;

            for (int i = 0; i < lista.size(); i++) {
                Map<Long, String> map = lista.get(i);
                
                String url = request.getContextPath() + contextsWeb[i];
                String msg = letras[i];

                switch (letras[i]) {
                    case "I":
                        long serveisEfegits = identificadorsTramit[i];
                        if (serveisEfegits > 0) {
                            msg = "List";
                            permetFinalitzar &= true;
                        } else {
                            permetFinalitzar &= false;
                            msg = "Add";
                        }
                        url += "/list/1?tramitid=" + uuid;
                    break;
                    case "J":

                        if (necessitaConsentiment(tramitID)) {
                            if (identificadorsTramit[i] != null) {
                                url += "/" + identificadorsTramit[i] + "/edit" ;
                                permetFinalitzar &= true;
                            } else {
                                url += "/new?tramitid=" + uuid;
                                msg += "*";
                                permetFinalitzar &= false;
                            }
                        } else {
                            msg = "";
                        }

                    break;
                    default :

                        if (identificadorsTramit[i] != null) {
                            url += "/" + identificadorsTramit[i] + "/edit" ;
                            permetFinalitzar &= true;
                        } else {
                            url += "/new?tramitid=" + uuid;
                            msg += "*";
                            permetFinalitzar &= false;
                        }
                    break;

                }
                String div = "<a href='" + url + "'/> " + msg;
                map.put(tramitID, div);
            }
            if (permetFinalitzar) {
                filterForm.addAdditionalButtonByPK(tramitA.getPersautid(),
                        new AdditionalButton("fas fa-check icon-white", "generar.xml",
                                getContextWeb() + "/generaxml/" + tramitID, "btn-info"));
            }
        }
    }

    private boolean necessitaConsentiment(Long tramitID) throws I18NException {
        Where w = Where.AND(TramitIServFields.TRAMITID.equal(tramitID),
                TramitIServFields.CONSENTIMENT.equal(Constants.CONSENTIMENT_TIPUS_NOOP),
                TramitIServFields.CONSENTIMENTPUBLICAT.equal(Constants.CONSENTIMENT_ADJUNT));

        Long serveisQueNecessitenAdjunts = tramitIServLogicEjb.count(w);
        
        return  serveisQueNecessitenAdjunts > 0;
    }
    
    @Override
    public void delete(HttpServletRequest request, TramitAPersAut tramitAPersAut) throws I18NException {
        tramitAPersAutLogicEjb.deleteFull(tramitAPersAut.getTramitid());
    }

    public void generarDocumentsSolicitud(SolicitudJPA soli, Properties prop) throws Exception, I18NException {

        Long solicitudID = soli.getSolicitudID();

        Organ organGestor = organLogicaEjb.findByPrimaryKey(soli.getOrganid());
        while(organGestor.getDir3pare() != null) {
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
            Document doc = documentLogicaEjb.create("Formulario_Director_General (PDF)", fitxer.getFitxerID(), null, null,
                    tipus);

            DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), solicitudID);
            documentSolicitudLogicaEjb.create(ds);
        }

        {
            FitxerJPA fitxer = new FitxerJPA("Formulario_Director_General.odt", outputODT.length(),
                    "application/vnd.oasis.opendocument.text", "");

            fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

            FileSystemManager.sobreescriureFitxer(outputODT, fitxer.getFitxerID());

            Long tipus = Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_ODT;
            Document doc = documentLogicaEjb.create("Formulario_Director_General (ODT)", fitxer.getFitxerID(), null, null,
                    tipus);

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
        Set<Entry<Object,Object>> set = prop.entrySet();
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

    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.GET)
    public String getEditUrlFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);

        Long id = tramitAPersAutLogicEjb.executeQueryOne(TramitAPersAutFields.PERSAUTID,
                TramitAPersAutFields.TRAMITID.equal(tramitID));

        return "redirect:" + getContextWeb() + "/" + id + "/edit";
    }

//--------------------------------------------------------------------------------------------------------------------------------------------
    
    public static Long getTramitIDFromRequest(HttpServletRequest request) {

        String param = request.getParameter("tramitid");
        try {
            Long tramitID = Long.parseLong(param);
            return tramitID;
        } catch (Throwable th) {
            return HibernateFileUtil.decryptFileID(param);
        }
    }

    public static String getRedirectWhenDeleted(HttpServletRequest request, String uuid,
            TramitAPersAutLogicaService tramitAEjb) {
        try {
            Long tramitID = HibernateFileUtil.decryptFileID(uuid);
            tramitAEjb.deleteFull(tramitID);

            request.getSession().removeAttribute("tramitid");
            HtmlUtils.saveMessageError(request, "Tramit Cancelat (taules borrades)");
        } catch (I18NException e) {
            HtmlUtils.saveMessageError(request, "Error esborrant les taules del tramit sistra");
        }
        return "redirect:" + TramitAOperadorController.RETURN_URL;

    }
}
