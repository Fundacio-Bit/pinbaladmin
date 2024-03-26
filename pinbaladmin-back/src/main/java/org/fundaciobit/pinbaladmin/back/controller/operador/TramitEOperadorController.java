package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitECteAudController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitDCteAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitECteAudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
import org.fundaciobit.pinbaladmin.model.fields.TramitDCteAutFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitECteAudFields;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitECteAudJPA;
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
@RequestMapping(value = TramitEOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitECteAudForm.class, TramitECteAudFilterForm.class })
public class TramitEOperadorController extends TramitECteAudController {

    public static final String CONTEXT_WEB_PREV = TramitDOperadorController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/operador/tramite";
    public static final String CONTEXT_WEB_NEXT = TramitFOperadorController.CONTEXT_WEB;

    @EJB(mappedName = TramitECteAudLogicaService.JNDI_NAME)
    protected TramitECteAudLogicaService tramitECteAudLogicEjb;

    @EJB(mappedName = TramitDCteAutLogicaService.JNDI_NAME)
    protected TramitDCteAutLogicaService tramitDcteAutLogicEjb;
    
    
    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    public boolean isPublic() {
        return false;
    }
    public long actual() {
        return 4;
    }
    

    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }

    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }
    
    @Override
    public String getTileForm() {
        return "tramitEFormOperador";
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
    public TramitECteAudForm getTramitECteAudForm(TramitECteAudJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitECteAudForm tramitForm = super.getTramitECteAudForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.e");

        Long tramitID; 

        if (tramitForm.isNou()) {
            tramitID = TramitAOperadorController.getTramitIDFromRequest(request);
            TramitECteAudJPA tramitE = tramitForm.getTramitECteAud();

            tramitE.setTramitid(tramitID);
            request.getSession().setAttribute("tramitid", tramitID);
        }else {
            tramitID = tramitForm.getTramitECteAud().getTramitid();
        }
        
        String uuid = HibernateFileUtil.encryptFileID(tramitID);
        
        tramitForm.setCancelButtonVisible(false);
        tramitForm.setDeleteButtonVisible(false);
        tramitForm.setSaveButtonVisible(false);

		tramitForm.addAdditionalButton(
				new AdditionalButton("", "genapp.continue", "javascript: $('form').submit();", "btn-primary"));

        tramitForm.addAdditionalButton(new AdditionalButton("", "genapp.pagination.anterior",
                getContextWebPrev() + "/back/" + uuid, "btn-info"));

        tramitForm.addAdditionalButton(
                new AdditionalButton("", "genapp.delete", getContextWeb() + "/delete/" + uuid, "btn-danger"));
        
        {
        	
        	List<TramitDCteAut> llistatTramitD = tramitDcteAutLogicEjb.select(TramitDCteAutFields.TRAMITID.equal(tramitID));
        	if (llistatTramitD.size() == 1) {
        		request.setAttribute("tramitD", llistatTramitD.get(0));
        	}
        	
        	String anotacions = "Per aquest tramit, s'ha de tenir en compte que ara s'ha de fer una auditoria de seguretat de la informació";

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
            tramitForm.setAttachedAdditionalJspCode(true);
        }

        return tramitForm;
    }

    @Override
    public TramitECteAudJPA create(HttpServletRequest request, TramitECteAudJPA tramitECteAud)
            throws I18NException, I18NValidationException {
        return (TramitECteAudJPA) tramitECteAudLogicEjb.create(tramitECteAud);
    }

    @Override
    public TramitECteAudJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
        return (TramitECteAudJPA) tramitECteAudLogicEjb.findByPrimaryKey(id);
    }

    @Override
    public TramitECteAudJPA update(HttpServletRequest request, TramitECteAudJPA tramitJPA)
            throws I18NException, I18NValidationException {
        return (TramitECteAudJPA) tramitECteAudLogicEjb.update(tramitJPA);
    }



  //Si estamos en D, cuando le damos a /next, E comprueba si existe o no, y le saca el new o el edit.
    @RequestMapping(value = "/next/{uuid}", method = RequestMethod.GET)
    public String getNextTramitFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);

        Long creats = tramitECteAudLogicEjb.count(TramitECteAudFields.TRAMITID.equal(tramitID));

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
        Long id = tramitECteAudLogicEjb.executeQueryOne(TramitECteAudFields.CTEAUDID,
                TramitECteAudFields.TRAMITID.equal(tramitID));
    
        return super.editarTramitECteAudGet(id, request, response);
    }
    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.POST)
    public String editarTramitECteAudPost(@ModelAttribute TramitECteAudForm tramitForm,
            BindingResult result, SessionStatus status, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {

        String ret =  super.editarTramitECteAudPost(tramitForm, result, status, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitForm.getTramitECteAud().getTramitid();

            log.info("tramitID: " + tramitID);
            log.info("actual: " + actual());
            log.info("isPublic: " + isPublic());

            String anotacions = "Per aquest tramit, s'ha de tenir en compte que ara s'ha de fer una auditoria de seguretat de la informació";
            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
            tramitForm.setAttachedAdditionalJspCode(true);
        }
        return ret;

    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitECteAudForm TramitECteAudForm) {

        TramitECteAudJPA tramitE = TramitECteAudForm.getTramitECteAud();
        request.getSession().setAttribute("tramitE", tramitE);

        Long tramitId = tramitE.getTramitid();
        String uuid =  HibernateFileUtil.encryptFileID(tramitId);

        return "redirect:" + getContextWebNext() + "/next/" + uuid;
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitECteAudForm tramitForm,
            Throwable __e) {
        log.info("Esteim a getRedirectWhenModified de TramitE");

        if (__e == null) {
            return getRedirectWhenCreated(request, tramitForm);
        } else {
            return getTileForm();
        }
    }

//    @Override
//    public ModelAndView editarTramitECteAudGet(@PathVariable("dadescesiid") java.lang.Long dadescesiid,
//            HttpServletRequest request, HttpServletResponse response) throws I18NException {
//    
//        return editAndViewTramitECteAudGet(dadescesiid, request, response, false);
//    }
    
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public String deleteFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return TramitAOperadorController.getRedirectWhenDeleted(request, uuid, tramitAPersAutLogicEjb);
    }

     @Override
    public String crearTramitECteAudPost(TramitECteAudForm tramitECteAudForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ret = super.crearTramitECteAudPost(tramitECteAudForm, result, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitECteAudForm.getTramitECteAud().getTramitid();

            log.info("tramitID: " + tramitID);
            log.info("actual: " + actual());
            log.info("isPublic: " + isPublic());
            
            String anotacions = "Per aquest tramit, s'ha de tenir en compte que ara s'ha de fer una auditoria de seguretat de la informació";

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
            tramitECteAudForm.setAttachedAdditionalJspCode(true);
        }
        return ret;
    }
}
