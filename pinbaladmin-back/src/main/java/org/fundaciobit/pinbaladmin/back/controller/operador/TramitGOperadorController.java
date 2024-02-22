package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitGDadesTitController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitGDadesTitLogicaService;
import org.fundaciobit.pinbaladmin.model.fields.TramitGDadesTitFields;
import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitJPA;
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
@RequestMapping(value = TramitGOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitGDadesTitForm.class, TramitGDadesTitFilterForm.class })
public class TramitGOperadorController extends TramitGDadesTitController {

    public static final String CONTEXT_WEB_PREV = TramitFOperadorController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/operador/tramitg";
    public static final String CONTEXT_WEB_NEXT = TramitHOperadorController.CONTEXT_WEB;

    @EJB(mappedName = TramitGDadesTitLogicaService.JNDI_NAME)
    protected TramitGDadesTitLogicaService tramitGDadesTitLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    public boolean isPublic() {
        return false;
    }

    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }

    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }
    
    @Override
    public String getTileForm() {
        return "tramitGFormOperador";
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
    public TramitGDadesTitForm getTramitGDadesTitForm(TramitGDadesTitJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitGDadesTitForm tramitForm = super.getTramitGDadesTitForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.g");

        Long tramitID; 

        if (tramitForm.isNou()) {
            tramitID = TramitAOperadorController.getTramitIDFromRequest(request);
            TramitGDadesTitJPA tramitG = tramitForm.getTramitGDadesTit();

            tramitG.setTramitid(tramitID);
            request.getSession().setAttribute("tramitid", tramitID);

        }else {
            tramitID = tramitForm.getTramitGDadesTit().getTramitid();
        }
        
        String uuid = HibernateFileUtil.encryptFileID(tramitID);
        
        tramitForm.setCancelButtonVisible(false);
        tramitForm.setDeleteButtonVisible(false);

        tramitForm.addAdditionalButton(new AdditionalButton("fas fa-arrow-left", "genapp.pagination.anterior",
                getContextWebPrev() + "/back/" + uuid, "btn-info"));

        tramitForm.addAdditionalButton(
                new AdditionalButton("", "genapp.delete", getContextWeb() + "/delete/" + uuid, "btn-danger"));
        
        mav.addObject("isPublic", isPublic());
        tramitForm.setAttachedAdditionalJspCode(true);

        return tramitForm;
    }

    @Override
    public TramitGDadesTitJPA create(HttpServletRequest request, TramitGDadesTitJPA tramitGDadesTit)
            throws I18NException, I18NValidationException {
        return (TramitGDadesTitJPA) tramitGDadesTitLogicEjb.create(tramitGDadesTit);
    }

    @Override
    public TramitGDadesTitJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
        return (TramitGDadesTitJPA) tramitGDadesTitLogicEjb.findByPrimaryKey(id);
    }

    @Override
    public TramitGDadesTitJPA update(HttpServletRequest request, TramitGDadesTitJPA tramitJPA)
            throws I18NException, I18NValidationException {
        return (TramitGDadesTitJPA) tramitGDadesTitLogicEjb.update(tramitJPA);
    }


    //Si estamos en D, cuando le damos a /next, E comprueba si existe o no, y le saca el new o el edit.
    @RequestMapping(value = "/next/{uuid}", method = RequestMethod.GET)
    public String getNextTramitFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);

        Long creats = tramitGDadesTitLogicEjb.count(TramitGDadesTitFields.TRAMITID.equal(tramitID));

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
        Long id = tramitGDadesTitLogicEjb.executeQueryOne(TramitGDadesTitFields.DADESTITID,
                TramitGDadesTitFields.TRAMITID.equal(tramitID));
    
        return super.editarTramitGDadesTitGet(id, request, response);
    }
    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.POST)
    public String editarTramitGDadesTitPost(@ModelAttribute TramitGDadesTitForm tramitForm,
            BindingResult result, SessionStatus status, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {
        return super.editarTramitGDadesTitPost(tramitForm, result, status, request, response);
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitGDadesTitForm TramitGDadesTitForm) {
        Long tramitId = TramitGDadesTitForm.getTramitGDadesTit().getTramitid();
        
        String uuid =  HibernateFileUtil.encryptFileID(tramitId);
        return "redirect:" + getContextWebNext() + "/next/" + uuid;
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitGDadesTitForm tramitForm,
            Throwable __e) {
        log.info("Esteim a getRedirectWhenModified de TramitG");

        if (__e == null) {
            return getRedirectWhenCreated(request, tramitForm);
        } else {
            return getTileForm();
        }
    }

//    @Override
//    public ModelAndView editarTramitGDadesTitGet(@PathVariable("dadescesiid") java.lang.Long dadescesiid,
//            HttpServletRequest request, HttpServletResponse response) throws I18NException {
//    
//        return editAndViewTramitGDadesTitGet(dadescesiid, request, response, false);
//    }
    
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public String deleteFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return TramitAOperadorController.getRedirectWhenDeleted(request, uuid, tramitAPersAutLogicEjb);
    }
}
