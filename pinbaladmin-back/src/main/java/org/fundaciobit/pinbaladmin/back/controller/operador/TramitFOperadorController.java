package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitFCteTecController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitDCteAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitFCteTecLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.pinbaladmin.model.fields.TramitDCteAutFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitFCteTecFields;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitFCteTecJPA;
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
@RequestMapping(value = TramitFOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitFCteTecForm.class, TramitFCteTecFilterForm.class })
public class TramitFOperadorController extends TramitFCteTecController {

    public static final String CONTEXT_WEB_PREV = TramitEOperadorController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/operador/tramitf";
    public static final String CONTEXT_WEB_NEXT = TramitGOperadorController.CONTEXT_WEB;
    
    @EJB(mappedName = TramitFCteTecLogicaService.JNDI_NAME)
    protected TramitFCteTecLogicaService tramitFCteTecLogicEjb;

    @EJB(mappedName = TramitDCteAutLogicaService.JNDI_NAME)
    protected TramitDCteAutLogicaService tramitDcteAutLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    public boolean isPublic() {
        return false;
    }
    public long actual() {
        return 5;
    }
    

    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }

    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }

    @Override
    public String getTileForm() {
        return "tramitFFormOperador";
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
    public TramitFCteTecForm getTramitFCteTecForm(TramitFCteTecJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitFCteTecForm tramitForm = super.getTramitFCteTecForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.f");

        Long tramitID; 

        if (tramitForm.isNou()) {
            tramitID = TramitAOperadorController.getTramitIDFromRequest(request);
            TramitFCteTecJPA tramitF = tramitForm.getTramitFCteTec();

            tramitF.setTramitid(tramitID);
            request.getSession().setAttribute("tramitid", tramitID);

        }else {
            tramitID = tramitForm.getTramitFCteTec().getTramitid();
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

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb);
            tramitForm.setAttachedAdditionalJspCode(true);
        }


        return tramitForm;
    }

    @Override
    public TramitFCteTecJPA create(HttpServletRequest request, TramitFCteTecJPA tramitFCteTec)
            throws I18NException, I18NValidationException {
        return (TramitFCteTecJPA) tramitFCteTecLogicEjb.create(tramitFCteTec);
    }

    @Override
    public TramitFCteTecJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
        return (TramitFCteTecJPA) tramitFCteTecLogicEjb.findByPrimaryKey(id);
    }

    @Override
    public TramitFCteTecJPA update(HttpServletRequest request, TramitFCteTecJPA tramitJPA)
            throws I18NException, I18NValidationException {
        return (TramitFCteTecJPA) tramitFCteTecLogicEjb.update(tramitJPA);
    }

  //Si estamos en D, cuando le damos a /next, E comprueba si existe o no, y le saca el new o el edit.
    @RequestMapping(value = "/next/{uuid}", method = RequestMethod.GET)
    public String getNextTramitFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);

        Long creats = tramitFCteTecLogicEjb.count(TramitFCteTecFields.TRAMITID.equal(tramitID));

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
        Long id = tramitFCteTecLogicEjb.executeQueryOne(TramitFCteTecFields.CTETECID,
                TramitFCteTecFields.TRAMITID.equal(tramitID));
    
        return super.editarTramitFCteTecGet(id, request, response);
    }
    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.POST)
    public String editarTramitFCteTecPost(@ModelAttribute TramitFCteTecForm tramitForm,
            BindingResult result, SessionStatus status, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {

        String ret =  super.editarTramitFCteTecPost(tramitForm, result, status, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitForm.getTramitFCteTec().getTramitid();

            log.info("tramitID: " + tramitID);
            log.info("actual: " + actual());
            log.info("isPublic: " + isPublic());

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb);
            tramitForm.setAttachedAdditionalJspCode(true);
        }
        return ret;

    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitFCteTecForm TramitFCteTecForm) {

        TramitFCteTecJPA tramitF = TramitFCteTecForm.getTramitFCteTec();
        request.getSession().setAttribute("tramitF", tramitF);

        Long tramitId = tramitF.getTramitid();
        String uuid =  HibernateFileUtil.encryptFileID(tramitId);

        return "redirect:" + getContextWebNext() + "/next/" + uuid;
   }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitFCteTecForm tramitForm,
            Throwable __e) {
        log.info("Esteim a getRedirectWhenModified de TramitF");

        if (__e == null) {
            return getRedirectWhenCreated(request, tramitForm);
        } else {
            return getTileForm();
        }
    }

//    @Override
//    public ModelAndView editarTramitFCteTecGet(@PathVariable("dadescesiid") java.lang.Long dadescesiid,
//            HttpServletRequest request, HttpServletResponse response) throws I18NException {
//    
//        return editAndViewTramitFCteTecGet(dadescesiid, request, response, false);
//    }
    
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public String deleteFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return TramitAOperadorController.getRedirectWhenDeleted(request, uuid, tramitAPersAutLogicEjb);
    }

    @Override
    public String crearTramitFCteTecPost(TramitFCteTecForm tramitFCteTecForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ret = super.crearTramitFCteTecPost(tramitFCteTecForm, result, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitFCteTecForm.getTramitFCteTec().getTramitid();

            log.info("tramitID: " + tramitID);
            log.info("actual: " + actual());
            log.info("isPublic: " + isPublic());
            

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb);
            tramitFCteTecForm.setAttachedAdditionalJspCode(true);
        }
        return ret;
    }
}
