package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.all.TramitAPublicController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitJConsentController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitJConsentLogicaService;
import org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields;
import org.fundaciobit.pinbaladmin.persistence.TramitJConsentJPA;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitJOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitJConsentForm.class, TramitJConsentFilterForm.class })
public class TramitJOperadorController extends TramitJConsentController {


    public static final String CONTEXT_WEB_PREV = TramitIOperadorController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/operador/tramitj";

    @EJB(mappedName = TramitJConsentLogicaService.JNDI_NAME)
    protected TramitJConsentLogicaService tramitJConsentLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }
    
    @Override
    public String getTileForm() {
        return "tramitJFormOperador";
    }

    @Override
    public boolean isActiveList() {
        return false;
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
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public TramitJConsentForm getTramitJConsentForm(TramitJConsentJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitJConsentForm tramitForm = super.getTramitJConsentForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.j");

        Long tramitID; 

        if (tramitForm.isNou()) {
            tramitID = TramitAOperadorController.getTramitIDFromRequest(request);
            TramitJConsentJPA tramitJ = tramitForm.getTramitJConsent();

            tramitJ.setTramitid(tramitID);
            tramitForm.addHiddenField(TRAMITID);
            request.getSession().setAttribute("tramitid", tramitID);

        }else {
            tramitID = tramitForm.getTramitJConsent().getTramitid();
        }

        String uuid = HibernateFileUtil.encryptFileID(tramitID);
        tramitForm.setCancelButtonVisible(false);
        tramitForm.setDeleteButtonVisible(false);

        tramitForm.addAdditionalButton(new AdditionalButton("fas fa-arrow-left", "genapp.pagination.anterior",
                getContextWebPrev() + "/back/" + uuid, "btn-info"));

        tramitForm.addAdditionalButton(
                new AdditionalButton("", "genapp.delete", getContextWeb() + "/delete/" + uuid, "btn-danger"));
        
        return tramitForm;
    }

    @Override
    public TramitJConsentJPA create(HttpServletRequest request, TramitJConsentJPA tramitJConsent)
            throws I18NException, I18NValidationException {
        return (TramitJConsentJPA) tramitJConsentLogicEjb.create(tramitJConsent);
    }

    @Override
    public TramitJConsentJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
        return (TramitJConsentJPA) tramitJConsentLogicEjb.findByPrimaryKey(id);
    }

    @Override
    public TramitJConsentJPA update(HttpServletRequest request, TramitJConsentJPA tramitJPA)
            throws I18NException, I18NValidationException {
        return (TramitJConsentJPA) tramitJConsentLogicEjb.update(tramitJPA);
    }


//================================================================================================================
    
    //Si estamos en D, cuando le damos a /next, E comprueba si existe o no, y le saca el new o el edit.
    @RequestMapping(value = "/next/{uuid}", method = RequestMethod.GET)
    public String getNextTramitFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);

        Long creats = tramitJConsentLogicEjb.count(TramitJConsentFields.TRAMITID.equal(tramitID));

        if (creats == 0) {
            return "redirect:" + getContextWeb() + "/new?tramitid=" + uuid;
        }else {
            return "redirect:" + getContextWeb() + "/edit/" + uuid;
        }
    }

    //Si estamos en D, miramos el back de C, y que nos de su /edit
    @RequestMapping(value = "/back/{uuid}", method = RequestMethod.GET)
    public String getEditUrlFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return "redirect:" + getContextWeb() + "/edit/" + uuid;
    }
    
    
    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.GET)
    public ModelAndView editarTramitC2(@PathVariable("uuid") java.lang.String uuid, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {
        
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);
        Long id = tramitJConsentLogicEjb.executeQueryOne(TramitJConsentFields.CONSENTID,
                TramitJConsentFields.TRAMITID.equal(tramitID));
    
        return super.editarTramitJConsentGet(id, request, response);
    }
    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.POST)
    public String editarTramitJConsentPost(@ModelAttribute TramitJConsentForm tramitForm,
            BindingResult result, SessionStatus status, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {
        return super.editarTramitJConsentPost(tramitForm, result, status, request, response);
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitJConsentForm TramitJConsentForm) {
        Long tramitId = TramitJConsentForm.getTramitJConsent().getTramitid();
        
        String uuid =  HibernateFileUtil.encryptFileID(tramitId);
        return "redirect:" + TramitAPublicController.CONTEXT_WEB + "/finalitzarTramit/" + uuid;
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitJConsentForm tramitForm,
            Throwable __e) {
        log.info("Esteim a getRedirectWhenModified de TramitJ");

        if (__e == null) {
            return getRedirectWhenCreated(request, tramitForm);
        } else {
            return getTileForm();
        }
    }

//    @Override
//    public ModelAndView editarTramitJConsentGet(@PathVariable("dadescesiid") java.lang.Long dadescesiid,
//            HttpServletRequest request, HttpServletResponse response) throws I18NException {
//    
//        return editAndViewTramitJConsentGet(dadescesiid, request, response, false);
//    }
    
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public String deleteFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return TramitAOperadorController.getRedirectWhenDeleted(request, uuid, tramitAPersAutLogicEjb);
    }
}
