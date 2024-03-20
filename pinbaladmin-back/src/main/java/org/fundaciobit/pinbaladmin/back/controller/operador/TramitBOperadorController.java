package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitBDadesSoliController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitBDadesSoliLogicaService;
import org.fundaciobit.pinbaladmin.model.fields.TramitBDadesSoliFields;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPA;
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
@RequestMapping(value = TramitBOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitBDadesSoliForm.class, TramitBDadesSoliFilterForm.class })
public class TramitBOperadorController extends TramitBDadesSoliController {

    public static final String CONTEXT_WEB_PREV = TramitAOperadorController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/operador/tramitb";
    public static final String CONTEXT_WEB_NEXT = TramitCOperadorController.CONTEXT_WEB;

    @EJB(mappedName = TramitBDadesSoliLogicaService.JNDI_NAME)
    protected TramitBDadesSoliLogicaService tramitBDadesSoliLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    public boolean isPublic() {
        return false;
    }
    public long actual() {
        return 1;
    }
    

    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }
    
    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }
    
    @Override
    public String getTileForm() {
        return "tramitBFormOperador";
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
    public TramitBDadesSoliForm getTramitBDadesSoliForm(TramitBDadesSoliJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitBDadesSoliForm tramitForm = super.getTramitBDadesSoliForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.b");

        Long tramitID; 

        if (tramitForm.isNou()) {
            tramitID = TramitAOperadorController.getTramitIDFromRequest(request);
            TramitBDadesSoliJPA tramitB = tramitForm.getTramitBDadesSoli();

            tramitB.setTramitid(tramitID);
            request.getSession().setAttribute("tramitid", tramitID);
        }else {
            tramitID = tramitForm.getTramitBDadesSoli().getTramitid();
        }
        
        String uuid = HibernateFileUtil.encryptFileID(tramitID);

        tramitForm.setCancelButtonVisible(false);
        tramitForm.setDeleteButtonVisible(false);

        tramitForm.addAdditionalButton(new AdditionalButton("fas fa-arrow-left", "genapp.pagination.anterior",
                getContextWebPrev() + "/back/" + uuid, "btn-info"));

        tramitForm.addAdditionalButton(
                new AdditionalButton("", "genapp.delete", getContextWeb() + "/delete/" + uuid, "btn-danger"));

        {
            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb);
            tramitForm.setAttachedAdditionalJspCode(true);
        }

        return tramitForm;
    }

    @Override
    public TramitBDadesSoliJPA create(HttpServletRequest request, TramitBDadesSoliJPA tramitBDadesSoli)
            throws I18NException, I18NValidationException {
        return (TramitBDadesSoliJPA) tramitBDadesSoliLogicEjb.create(tramitBDadesSoli);
    }
    
    @Override
    public TramitBDadesSoliJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
        return (TramitBDadesSoliJPA) tramitBDadesSoliLogicEjb.findByPrimaryKey(id);
    }

    @Override
    public TramitBDadesSoliJPA update(HttpServletRequest request, TramitBDadesSoliJPA tramitJPA)
            throws I18NException, I18NValidationException {
        return (TramitBDadesSoliJPA) tramitBDadesSoliLogicEjb.update(tramitJPA);
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipussolicitud(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        return tramitBDadesSoliLogicEjb.getReferenceListForTipussolicitud();
    }
    
    @Override
    public List<StringKeyValue> getReferenceListForEntorn(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("", "..."));
        __tmp.add(new StringKeyValue("pre", "Preproducció"));
        __tmp.add(new StringKeyValue("pro", "Producció"));
        return __tmp;
    }

    //Si estamos en D, cuando le damos a /next, E comprueba si existe o no, y le saca el new o el edit.
    @RequestMapping(value = "/next/{uuid}", method = RequestMethod.GET)
    public String getNextTramitFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);

        Long creats = tramitBDadesSoliLogicEjb.count(TramitBDadesSoliFields.TRAMITID.equal(tramitID));

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
        Long id = tramitBDadesSoliLogicEjb.executeQueryOne(TramitBDadesSoliFields.DADESSOLIID,
                TramitBDadesSoliFields.TRAMITID.equal(tramitID));
    
        return super.editarTramitBDadesSoliGet(id, request, response);
    }
    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.POST)
    public String editarTramitBDadesSoliPost(@ModelAttribute TramitBDadesSoliForm tramitForm,
            BindingResult result, SessionStatus status, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {
        return super.editarTramitBDadesSoliPost(tramitForm, result, status, request, response);
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitBDadesSoliForm tramitBDadesSoliForm) {

        TramitBDadesSoliJPA tramitB = tramitBDadesSoliForm.getTramitBDadesSoli();
        request.getSession().setAttribute("tramitB", tramitB);

        Long tramitId = tramitB.getTramitid();
        String uuid =  HibernateFileUtil.encryptFileID(tramitId);

        return "redirect:" + getContextWebNext() + "/next/" + uuid;

    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitBDadesSoliForm tramitForm,
            Throwable __e) {
        log.info("Esteim a getRedirectWhenModified de TramitB");

        if (__e == null) {
            return getRedirectWhenCreated(request, tramitForm);
        } else {
            return getTileForm();
        }
    }

    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public String deleteFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return TramitAOperadorController.getRedirectWhenDeleted(request, uuid, tramitAPersAutLogicEjb);
    }

    @Override
    public String crearTramitBDadesSoliPost(TramitBDadesSoliForm tramitBDadesSoliForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ret = super.crearTramitBDadesSoliPost(tramitBDadesSoliForm, result, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitBDadesSoliForm.getTramitBDadesSoli().getTramitid();

            log.info("tramitID: " + tramitID);
            log.info("actual: " + actual());
            log.info("isPublic: " + isPublic());
            
            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb);
            tramitBDadesSoliForm.setAttachedAdditionalJspCode(true);
        }
        return ret;
    }
}
