package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitHProcController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcForm;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments.TipusProcediment;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitHProcLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.model.fields.TramitHProcFields;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitHProcJPA;
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
@RequestMapping(value = TramitHOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitHProcForm.class, TramitHProcFilterForm.class })
public class TramitHOperadorController extends TramitHProcController {

    public static final String CONTEXT_WEB_PREV = TramitGOperadorController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/operador/tramith";
    public static final String CONTEXT_WEB_NEXT = TramitIOperadorController.CONTEXT_WEB;

    @EJB(mappedName = TramitHProcLogicaService.JNDI_NAME)
    protected TramitHProcLogicaService tramitHProcLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    public boolean isPublic() {
        return false;
    }
    public long actual() {
        return 7;
    }
    

    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }

    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }
    
    @Override
    public String getTileForm() {
        return "tramitHFormOperador";
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
    public TramitHProcForm getTramitHProcForm(TramitHProcJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitHProcForm tramitForm = super.getTramitHProcForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.h");

        Long tramitID; 

        if (tramitForm.isNou()) {
            tramitID = TramitAOperadorController.getTramitIDFromRequest(request);
            TramitHProcJPA tramitH = tramitForm.getTramitHProc();

            tramitH.setTramitid(tramitID);
            request.getSession().setAttribute("tramitid", tramitID);

        }else {
            tramitID = tramitForm.getTramitHProc().getTramitid();
        }
        
        String uuid = HibernateFileUtil.encryptFileID(tramitID);
        
        tramitForm.setCancelButtonVisible(false);
        tramitForm.setDeleteButtonVisible(false);
        tramitForm.setSaveButtonVisible(false);

        tramitForm.addAdditionalButton(new AdditionalButton("", "genapp.pagination.anterior",
                getContextWebPrev() + "/back/" + uuid, "btn-info"));

        tramitForm.addAdditionalButton(
        		new AdditionalButton("", "genapp.continue", "javascript: $('form').submit();", "btn-primary"));
        
        tramitForm.addAdditionalButton(
                new AdditionalButton("", "genapp.delete", getContextWeb() + "/delete/" + uuid, "btn-danger"));

        {
        	String anotacions = null;

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
            tramitForm.setAttachedAdditionalJspCode(true);
        }

        return tramitForm;
    }

    @Override
    public TramitHProcJPA create(HttpServletRequest request, TramitHProcJPA tramitHProc)
            throws I18NException, I18NValidationException {
        return (TramitHProcJPA) tramitHProcLogicEjb.create(tramitHProc);
    }

    @Override
    public TramitHProcJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
        return (TramitHProcJPA) tramitHProcLogicEjb.findByPrimaryKey(id);
    }

    @Override
    public TramitHProcJPA update(HttpServletRequest request, TramitHProcJPA tramitJPA)
            throws I18NException, I18NValidationException {
        return (TramitHProcJPA) tramitHProcLogicEjb.update(tramitJPA);
    }


    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        String lang = "ca";
        log.info("lang = " + lang);
        
        List<TipusProcediment> tipus = TipusProcediments.getAllTipusProcediments();
        for (TipusProcediment tipusProcediment : tipus) {
            String key = String.valueOf(tipusProcediment.id);
            String val;
            if (lang.equals("es")) {
                val = tipusProcediment.castella;
            } else {
                val = tipusProcediment.catala;
            }
            __tmp.add(new StringKeyValue(key, val));

        }
        return __tmp;
    }

    public String getTipusProcediment(Long key) {
        String tp = null;
        String lang = "es";

        List<TipusProcediment> tipus = TipusProcediments.getAllTipusProcediments();
        for (TipusProcediment tipusProcediment : tipus) {
            if (tipusProcediment.id == key) {
                if (lang.equals("es")) {
                    tp = tipusProcediment.castella;
                } else {
                    tp = tipusProcediment.catala;
                }

                break;
            }
        }

        return tp;
    }

    public void postValidate(HttpServletRequest request, TramitHProcForm tramitHProcForm, BindingResult result)
            throws I18NException {

        //Comprovació de la caducitat. Si caduca i no te data, error.
        {
            TramitHProc tramitH = tramitHProcForm.getTramitHProc();

            if (tramitH.isCaducitat()) {
                if (tramitH.getCaducitatdata() == null) {
                    result.rejectValue(get(CADUCITATDATA), "genapp.validation.required",
                            new String[] { I18NUtils.tradueix(CADUCITATDATA.fullName) }, null);

                }
                if (tramitH.getCaducitatdata().compareTo(new Date(System.currentTimeMillis())) < 0) {
                    result.rejectValue(get(CADUCITATDATA), "genapp.comodi",
                            new String[] { I18NUtils.tradueix(CADUCITATDATA.fullName) + " ha de ser posterior a la actual"}, null);
                }

            }
        }
    }
    
    //===========================================================================================================================
    //Si estamos en D, cuando le damos a /next, E comprueba si existe o no, y le saca el new o el edit.
    @RequestMapping(value = "/next/{uuid}", method = RequestMethod.GET)
    public String getNextTramitFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);

        Long creats = tramitHProcLogicEjb.count(TramitHProcFields.TRAMITID.equal(tramitID));

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
        Long id = tramitHProcLogicEjb.executeQueryOne(TramitHProcFields.PROCID,
                TramitHProcFields.TRAMITID.equal(tramitID));
    
        return super.editarTramitHProcGet(id, request, response);
    }
    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.POST)
    public String editarTramitHProcPost(@ModelAttribute TramitHProcForm tramitForm,
            BindingResult result, SessionStatus status, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {

        String ret =  super.editarTramitHProcPost(tramitForm, result, status, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitForm.getTramitHProc().getTramitid();

            log.info("tramitID: " + tramitID);
            log.info("actual: " + actual());
            log.info("isPublic: " + isPublic());

        	String anotacions = null;

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
            tramitForm.setAttachedAdditionalJspCode(true);
        }
        return ret;

    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitHProcForm tramitHProcForm) {

        TramitHProcJPA tramitH = tramitHProcForm.getTramitHProc();
        request.getSession().setAttribute("tramitH", tramitH);

        Long tramitId = tramitH.getTramitid();
        String uuid =  HibernateFileUtil.encryptFileID(tramitId);

        return "redirect:" + getContextWebNext() + "/next/" + uuid;
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitHProcForm tramitForm,
            Throwable __e) {
        log.info("Esteim a getRedirectWhenModified de TramitH");

        if (__e == null) {
            return getRedirectWhenCreated(request, tramitForm);
        } else {
            return getTileForm();
        }
    }

//    @Override
//    public ModelAndView editarTramitHProcGet(@PathVariable("dadescesiid") java.lang.Long dadescesiid,
//            HttpServletRequest request, HttpServletResponse response) throws I18NException {
//    
//        return editAndViewTramitHProcGet(dadescesiid, request, response, false);
//    }
    
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public String deleteFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return TramitAOperadorController.getRedirectWhenDeleted(request, uuid, tramitAPersAutLogicEjb);
    }
    
 

    @Override
    public String crearTramitHProcPost(TramitHProcForm tramitHProcForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ret = super.crearTramitHProcPost(tramitHProcForm, result, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitHProcForm.getTramitHProc().getTramitid();

        	String anotacions = null;

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
            tramitHProcForm.setAttachedAdditionalJspCode(true);
        }
        return ret;
    }
}
