package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.all.TramitAPublicController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitJConsentController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitJConsentLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;
import org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitIServJPA;
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

import com.itextpdf.text.pdf.PdfReader;

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
    
    public boolean isPublic() {
        return false;
    }
    public long actual() {
        return 9;
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
            request.getSession().setAttribute("tramitid", tramitID);
        }else {
            tramitID = tramitForm.getTramitJConsent().getTramitid();
        }

        String uuid = HibernateFileUtil.encryptFileID(tramitID);
        tramitForm.setCancelButtonVisible(false);
        tramitForm.setDeleteButtonVisible(false);
        tramitForm.setSaveButtonVisible(false);

        tramitForm.addAdditionalButton(new AdditionalButton("", "genapp.pagination.anterior",
                getContextWebPrev() + "/back/" + uuid, AdditionalButtonStyle.INFO));

        tramitForm.addAdditionalButton(
        		new AdditionalButton("", "tramit.sistra.finalitzar", "javascript:submitForm();", AdditionalButtonStyle.PRIMARY));
        
        tramitForm.addAdditionalButton(
                new AdditionalButton("", "genapp.delete", getContextWeb() + "/delete/" + uuid, AdditionalButtonStyle.DANGER));
        
        {
            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb);
            tramitForm.setAttachedAdditionalJspCode(true);
        }

        
        return tramitForm;
    }

	@Override
	public void preValidate(HttpServletRequest request, TramitJConsentForm tramitJConsentForm, BindingResult result)
			throws I18NException {
		super.preValidate(request, tramitJConsentForm, result);

		TramitJConsentJPA J = tramitJConsentForm.getTramitJConsent();

	}    
    
    @Override
    public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("", "..."));
        __tmp.add(new StringKeyValue(Constants.CONSENTIMENT_TIPUS_NOOP, "No oposició"));
        __tmp.add(new StringKeyValue(Constants.CONSENTIMENT_TIPUS_LLEI, "Llei"));
        __tmp.add(new StringKeyValue(Constants.CONSENTIMENT_TIPUS_SI, "Si"));
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForConsentimentadjunt(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("", "..."));
        __tmp.add(new StringKeyValue(Constants.CONSENTIMENT_PUBLICAT, "Publicat"));
        __tmp.add(new StringKeyValue(Constants.CONSENTIMENT_ADJUNT, "Adjunt"));
        return __tmp;
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
        String ret = super.editarTramitJConsentPost(tramitForm, result, status, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitForm.getTramitJConsent().getTramitid();

            log.info("tramitID: " + tramitID);
            log.info("actual: " + actual());
            log.info("isPublic: " + isPublic());

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb);
            tramitForm.setAttachedAdditionalJspCode(true);
        }
        return ret;

    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitJConsentForm TramitJConsentForm) {
        TramitJConsentJPA tramitJ = TramitJConsentForm.getTramitJConsent();
        request.getSession().setAttribute("tramitJ", tramitJ);

        Long tramitId = tramitJ.getTramitid();
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
    
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public String deleteFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
//        return TramitAOperadorController.getRedirectWhenDeleted(request, uuid, tramitAPersAutLogicEjb);
    	return "redirect:" + TramitAPublicController.CONTEXT_WEB + "/cancelarTramit/" + uuid;

    }

    @Override
    public String crearTramitJConsentPost(TramitJConsentForm tramitJConsentForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ret = super.crearTramitJConsentPost(tramitJConsentForm, result, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitJConsentForm.getTramitJConsent().getTramitid();

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb);
            tramitJConsentForm.setAttachedAdditionalJspCode(true);
        }
        return ret;
    }
    
    @Override
    public void postValidate(HttpServletRequest request, TramitJConsentForm tramitJConsentForm, BindingResult result)
			throws I18NException {
		super.postValidate(request, tramitJConsentForm, result);

		// Comprovació de si el consentiment es correcte.
		TramitJConsent tramitJ = tramitJConsentForm.getTramitJConsent();

		String consentiment = tramitJ.getConsentiment();
		switch (consentiment) {
		case Constants.CONSENTIMENT_TIPUS_LLEI:
			// Continuar sense fer res
			break;
		case Constants.CONSENTIMENT_TIPUS_NOOP:
		case Constants.CONSENTIMENT_TIPUS_SI:
			String consentimentadjunt = tramitJ.getConsentimentadjunt();
			if (consentimentadjunt == null) {
				rejectValue(result, CONSENTIMENTADJUNT);
			} else {
				if (consentimentadjunt.equals(Constants.CONSENTIMENT_PUBLICAT)) {
					if (tramitJ.getUrlconsentiment() == null) {
						rejectValue(result, URLCONSENTIMENT);
//					} else { //XXX XYZ: Asegurar que la URL es de un PDF
//						// Validació de que la url es d'un PDF
//						String url = tramitJ.getUrlconsentiment();
//						if (!url.toLowerCase().endsWith(".pdf")) {
//							rejectValue(result, URLCONSENTIMENT);
//						}
					}
				} else if (consentimentadjunt.equals(Constants.CONSENTIMENT_ADJUNT)) {
					if (tramitJ.getAdjuntID() == null) {
						rejectValue(result, ADJUNTID);
					}
				}
			}
			break;
		default:
			rejectValue(result, CONSENTIMENT);
			break;
		}
	}
    
	private void rejectValue(BindingResult result, Field<?> field) {
		result.rejectValue(get(field), "genapp.validation.required",
				new String[] { I18NUtils.tradueix(field.fullName) }, null);
	}
}
