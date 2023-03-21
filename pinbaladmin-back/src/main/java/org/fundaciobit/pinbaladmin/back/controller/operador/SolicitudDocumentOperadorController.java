package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.DocumentController;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentForm;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
import org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/solicitud/document")
@SessionAttributes(types = { DocumentForm.class, DocumentFilterForm.class })
public class SolicitudDocumentOperadorController extends DocumentController {

    @EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
    protected DocumentSolicitudLogicaService documentSolicitudLogicaEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;

    public static final String SESSIO_SOLIID_MANAGE_DOCUMENTS = "SESSIO_SOLIID_MANAGE_DOCUMENTS";

    @Override
    public String getTileForm() {
        return "documentFormWebDB_operador";
    }

    @Override
    public String getTileList() {
        return "documentListWebDB_operador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "DocumentWebDB_FilterForm_operador";
    }

    @Override
    public String getEntityNameCode() {
        return "documentSolicitud.documentSolicitud";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "documentSolicitud.documentSolicitud.plural";
    }

    @Override
    public DocumentFilterForm getDocumentFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        DocumentFilterForm documentFilterForm = super.getDocumentFilterForm(pagina, mav, request);

        Long soli = getSolicitudID(request);

        if (soli == null) {

            // TODO traduir
            HtmlUtils.saveMessageError(request, "No puc trobar documents de la solicitud ja que no s'ha passat "
                    + "l´identificador de la solicitud !!!!");
            // XYZ ZZZ envii a local o estatal
            mav.setView(new RedirectView("/operador/solicitudlocal/list", true));
        } else {

            List<String> codi = solicitudEjb.executeQuery(SolicitudFields.PROCEDIMENTCODI,
                    SolicitudFields.SOLICITUDID.equal(soli));
            // TODO Traduir
            documentFilterForm.setSubTitleCode("=Documents de la Solicitud amb Codi de Procediment " + codi.get(0));

            if (documentFilterForm.isNou()) {

                documentFilterForm.setAddButtonVisible(false);
                documentFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_PLUS_SIGN,
                        "solicitudservei.afegirfitxer", "/operador/solicitud/document/new", "btn-warning"));

                documentFilterForm.setVisibleMultipleSelection(false);

                documentFilterForm.setFilterByFields(new ArrayList<Field<?>>());

                documentFilterForm.setGroupByFields(new ArrayList<Field<?>>());

            }
        }

        log.info("Passa per getDocumentFilterForm:" + soli);

        return documentFilterForm;

    }

    public Long getSolicitudID(HttpServletRequest request) {
        Long soli;
        String soliStr = request.getParameter(SolicitudFields.SOLICITUDID.javaName);

        if (soliStr == null) {
            // Hem de tenir la solicitud en SESSIO
            soli = (Long) request.getSession().getAttribute(SESSIO_SOLIID_MANAGE_DOCUMENTS);
        } else {
            try {
                soli = Long.parseLong(soliStr);
                request.getSession().setAttribute(SESSIO_SOLIID_MANAGE_DOCUMENTS, soli);
            } catch (Exception e) {
                soli = null;
            }
        }
        return soli;
    }

    @Override
    public DocumentForm getDocumentForm(DocumentJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        DocumentForm documentForm = super.getDocumentForm(_jpa, __isView, request, mav);

        if (documentForm.isNou()) {

            // Hem de tenir la solicitud en SESSIO
            Long soli = getSolicitudID(request);

            if (soli == null) {

                // TODO traduir
                HtmlUtils.saveMessageError(request, "No puc trobar documents de la solicitud ja que no s'ha passat "
                        + "l´identificador de la solicitud !!!!");

                // XYZ ZZZ envii a local o estatal
                mav.setView(new RedirectView("/operador/solicitudlocal/list", true));
            }
        }

        return documentForm;

    }

    public ThreadLocal<Long> soliID = new ThreadLocal<Long>();

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String crearDocumentPost(@ModelAttribute DocumentForm documentForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        Long soli = getSolicitudID(request);

        if (soli == null) {
            // TODO traduir
            HtmlUtils.saveMessageError(request, "No puc trobar documents de la solicitud ja que no s'ha passat "
                    + "l´identificador de la solicitud !!!!");

            return getTileList();
        } else {
            soliID.set(soli);
            return super.crearDocumentPost(documentForm, result, request, response);
        }

    }

    @Override
    public DocumentJPA create(HttpServletRequest request, DocumentJPA document)
            throws I18NException, I18NValidationException {

        // TODO ficar-ho dins un EJB
        DocumentJPA jpaDoc = (DocumentJPA) documentEjb.create(document);

        // Hem de crear la relacio
        Long _solicitudID_ = soliID.get();
        documentSolicitudLogicaEjb.create(jpaDoc.getDocumentID(), _solicitudID_);

        return jpaDoc;

    }

    @Override
    public void delete(HttpServletRequest request, Document document) throws I18NException {

        Long solicitudId = (Long) request.getSession().getAttribute(SESSIO_SOLIID_MANAGE_DOCUMENTS);
        // Ficada lògica dins EJB !!!!
        documentSolicitudLogicaEjb.deleteFull(document.getDocumentID(), solicitudId, true);

    }

    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        Long soli = getSolicitudID(request);

        if (soli == null) {

            throw new I18NException("error", "El valor per soli val null");

        }

        List<Long> documentIDs = documentSolicitudLogicaEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
                DocumentSolicitudFields.SOLICITUDID.equal(soli));

        return DOCUMENTID.in(documentIDs);

    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, DocumentForm docForm) {
        return "redirect:/operador/solicitudfullview/viewsessio";
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, DocumentForm docForm, Throwable __e) {
        if (__e == null) {
            return getRedirectWhenCreated(request, docForm);
        } else {
            return getTileForm();
        }
    }

    @Override
    public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long id, Throwable __e) {
        return "redirect:/operador/solicitudfullview/viewsessio";
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long id) {
        return "redirect:/operador/solicitudfullview/viewsessio";
    }

}
