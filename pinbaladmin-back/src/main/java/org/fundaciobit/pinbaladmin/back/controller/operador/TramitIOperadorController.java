package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitIServController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServForm;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitIServLogicaService;
import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;
import org.fundaciobit.pinbaladmin.persistence.TramitIServJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitIOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitIServForm.class, TramitIServFilterForm.class })
public class TramitIOperadorController extends TramitIServController {

    public static final String CONTEXT_WEB = "/operador/tramiti";

    @EJB(mappedName = TramitIServLogicaService.JNDI_NAME)
    protected TramitIServLogicaService tramitIServLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @Override
    public String getTileForm() {
        return "tramitIFormOperador";
    }

    @Override
    public String getTileList() {
        return "tramitIListOperador";
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

    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        String tramitIDStr = request.getParameter("tramitid");
        Long tramitID = Long.parseLong(tramitIDStr);

        return TRAMITID.equal(tramitID);
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitIServForm tramitIServForm) {
        return redirectToLlistatServeis(request);
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long _ID_) {
        return redirectToLlistatServeis(request);
    }

    public String getRedirectWhenModified(HttpServletRequest request, TramitIServForm tramitIServForm, Throwable __e) {
        if (__e == null) {
            return redirectToLlistatServeis(request);
        } else {
            return getTileForm();
        }
    }

    public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long servid, Throwable __e) {
        return redirectToLlistatServeis(request);
    }

    public String redirectToLlistatServeis(HttpServletRequest request) {
        Long tramitID = (Long) request.getSession().getAttribute("tramitid");
        return "redirect:" + getContextWeb() + "/list/1?tramitid=" + tramitID;
    }

    @Override
    public TramitIServForm getTramitIServForm(TramitIServJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitIServForm tramitForm = super.getTramitIServForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.i.form");

        if (__isView) {

        } else if (tramitForm.isNou()) {
            TramitIServJPA tramitI = tramitForm.getTramitIServ();

            String tramitIDStr = request.getParameter("tramitid");
            Long tramitID = Long.parseLong(tramitIDStr);
            tramitI.setTramitid(tramitID);
            tramitForm.addHiddenField(TRAMITID);
            request.getSession().setAttribute("tramitid", tramitID);

            tramitI.setNorma("Norma Legal inventada");
            tramitI.setArticles("Art1, 2 i Art 5");
            tramitI.setUrlnorma("https://www.boe.es/buscar/act.php?id=BOE-A-2021-9347");
            tramitI.setUrlconsentiment("toDelete");
        }

        tramitForm.setAttachedAdditionalJspCode(true);
        return tramitForm;
    }

    @Override
    public TramitIServFilterForm getTramitIServFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        TramitIServFilterForm tramitIServFilterForm = super.getTramitIServFilterForm(pagina, mav, request);
        tramitIServFilterForm.setTitleCode("tramit.sistra.titol.i.list");

        if (tramitIServFilterForm.isNou()) {

            Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(TramitIServFields.ALL_TRAMITISERV_FIELDS));
            hiddens.remove(TramitIServFields.NOM);
            hiddens.remove(TramitIServFields.CODI);
            tramitIServFilterForm.setHiddenFields(hiddens);
            tramitIServFilterForm.setAddButtonVisible(false);
            tramitIServFilterForm.setVisibleExportList(false);
        }

        {
            String tramitIDStr = request.getParameter("tramitid");
            Long tramitID = Long.parseLong(tramitIDStr);
            log.info("Estamos en I, servicios del tramite " + tramitID);

            tramitIServFilterForm.getAdditionalButtons().clear();

            tramitIServFilterForm.addAdditionalButton(new AdditionalButton("", "genapp.cancel",
                    getContextWeb() + "/cancelarTramit/" + tramitID, "btn-secondary"));

            tramitIServFilterForm.addAdditionalButton(new AdditionalButton("fas fa-plus", "tramit.i.afegir.servei",
                    getContextWeb() + "/new?tramitid=" + tramitID, "btn-info"));

            Long serveisAfegits = tramitIServLogicEjb.count(TRAMITID.equal(tramitID));
            log.info("serveisAfegits: " + serveisAfegits);

            if (serveisAfegits > 0) {

                Where w = Where.AND(TRAMITID.equal(tramitID), CONSENTIMENT.equal("noop"),
                        CONSENTIMENTPUBLICAT.equal("2"));
                long consentimentNecessari = tramitIServLogicEjb.count(w);

                if (consentimentNecessari > 0) {
                    tramitIServFilterForm.addAdditionalButton(new AdditionalButton("fas fa-check-circle",
                            "afegir consentiment", TramitJOperadorController.CONTEXT_WEB + "/new?tramitid=" + tramitID,
                            "btn-primary"));
                } else {
                    tramitIServFilterForm.addAdditionalButton(
                            new AdditionalButton("fas fa-check-circle", "tramit.i.finalitzar.tramit",
                                    TramitAOperadorController.CONTEXT_WEB + "/generaxml/" + tramitID, "btn-primary"));
                }
            }
        }

        return tramitIServFilterForm;
    }

    @Override
    public TramitIServJPA create(HttpServletRequest request, TramitIServJPA tramitIServ)
            throws I18NException, I18NValidationException {
        return (TramitIServJPA) tramitIServLogicEjb.create(tramitIServ);
    }

    @RequestMapping(value = "/cancelarTramit/{tramitid}", method = RequestMethod.GET)
    public String cancelarTramit(HttpServletRequest request, @PathVariable("tramitid") Long tramitID) {

        try {
            log.info("Estamos en I, vamos a borrar. TramitID=" + tramitID);

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
    public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("", "..."));
        __tmp.add(new StringKeyValue("noop", "No oposició"));
        __tmp.add(new StringKeyValue("llei", "Llei"));
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForConsentimentpublicat(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("", "..."));
        __tmp.add(new StringKeyValue("1", "Publicat"));
        __tmp.add(new StringKeyValue("2", "Adjunt"));
        return __tmp;
    }

    public List<StringKeyValue> getReferenceListForNom(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        return tramitIServLogicEjb.getReferenceListForServei();
    }

}
