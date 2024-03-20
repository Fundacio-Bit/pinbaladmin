package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.Section;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.all.TramitAPublicController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitIServController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitIServLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitJConsentLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;
import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;
import org.fundaciobit.pinbaladmin.persistence.TramitIServJPA;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
@RequestMapping(value = TramitIOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitIServForm.class, TramitIServFilterForm.class })
public class TramitIOperadorController extends TramitIServController {

    public static final String CONTEXT_WEB_PREV = TramitHOperadorController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/operador/tramiti";
    public static final String CONTEXT_WEB_NEXT = TramitJOperadorController.CONTEXT_WEB;

    @EJB(mappedName = TramitIServLogicaService.JNDI_NAME)
    protected TramitIServLogicaService tramitIServLogicEjb;

    @EJB(mappedName = TramitJConsentLogicaService.JNDI_NAME)
    protected TramitJConsentLogicaService tramitJConsentLogicaEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    public boolean isPublic() {
        return false;
    }
    public long actual() {
        return 8;
    }
    

    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }

    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }
    
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
        Long tramitID = TramitAOperadorController.getTramitIDFromRequest(request);

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
        
        Long tramitId = (Long) request.getSession().getAttribute("tramitid");
        String uuid =  HibernateFileUtil.encryptFileID(tramitId);
        log.info("-> uuid: " + uuid);

        return "redirect:" + getContextWeb() + "/list/1?tramitid=" + uuid;
    }

    @Override
    public TramitIServForm getTramitIServForm(TramitIServJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitIServForm tramitForm = super.getTramitIServForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.i.form");

        TramitIServJPA tramitI = tramitForm.getTramitIServ();
        Long tramitID;
        if (tramitForm.isNou()) {
            tramitID = TramitAOperadorController.getTramitIDFromRequest(request);
            tramitI.setTramitid(tramitID);

        } else {
            tramitID = tramitI.getTramitid();
        }
        
         request.getSession().setAttribute("tramitid", tramitID);
         
        {
            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb);
            tramitForm.setAttachedAdditionalJspCode(true);
        }

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
            
            tramitIServFilterForm.setItemsPerPage(-1);
            
        }

        {
            Long tramitID = TramitAOperadorController.getTramitIDFromRequest(request);

            String uuid = HibernateFileUtil.encryptFileID(tramitID);

            log.info("Estamos en I, servicios del tramite " + tramitID);

            tramitIServFilterForm.getAdditionalButtons().clear();

            tramitIServFilterForm.addAdditionalButton(
                    new AdditionalButton("", "tramit.sistra.cancelar.tramit", getContextWeb() + "/delete/" + uuid, "btn-secondary"));

            tramitIServFilterForm.addAdditionalButton(new AdditionalButton("fas fa-plus", "tramit.i.afegir.servei",
                    getContextWeb() + "/new?tramitid=" + uuid, "btn-info"));

            tramitIServFilterForm.addAdditionalButton(new AdditionalButton("fas fa-arrow-left",
                    "genapp.pagination.anterior", getContextWebPrev() + "/back/" + uuid, "btn-info"));

            Long serveisAfegits = tramitIServLogicEjb.count(TRAMITID.equal(tramitID));
            log.info("serveisAfegits: " + serveisAfegits);

			if (serveisAfegits > 0) {
				tramitIServFilterForm.addAdditionalButton(new AdditionalButton("fas fa-check-circle",
						"tramitJConsent.tramitJConsent", getContextWebNext() + "/next/" + uuid, "btn-primary"));
			}
            {
                TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb);
                tramitIServFilterForm.setAttachedAdditionalJspCode(true);
            }
        }
        

        return tramitIServFilterForm;
    }

    @Override
    public TramitIServJPA create(HttpServletRequest request, TramitIServJPA tramitIServ)
            throws I18NException, I18NValidationException {
        return (TramitIServJPA) tramitIServLogicEjb.create(tramitIServ);
    }

    @Override
    public TramitIServJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
        return (TramitIServJPA) tramitIServLogicEjb.findByPrimaryKey(id);
    }

    @Override
    public TramitIServJPA update(HttpServletRequest request, TramitIServJPA tramitJPA)
            throws I18NException, I18NValidationException {
        return (TramitIServJPA) tramitIServLogicEjb.update(tramitJPA);
    }

    @Override
    public void delete(HttpServletRequest request, TramitIServ tramitIServ) throws I18NException {
        tramitIServLogicEjb.delete(tramitIServ);
    }

    public List<StringKeyValue> getReferenceListForNom(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        return tramitIServLogicEjb.getReferenceListForServei();
    }

    //===========================================================================================================================

    //En este caso, cuando H llama a next, siempre llevará al listado, esté vacío o lleno.
    @RequestMapping(value = "/next/{uuid}", method = RequestMethod.GET)
    public String getNextTramitFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return "redirect:" + getContextWeb() + "/list/1?tramitid=" + uuid;
    }

    //Si estamos en D, miramos el back de C, y que nos de su /edit
    @RequestMapping(value = "/back/{uuid}", method = RequestMethod.GET)
    public String getEditUrlFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return "redirect:" + getContextWeb() + "/list/1?tramitid=" + uuid;
    }
    
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public String deleteFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return TramitAOperadorController.getRedirectWhenDeleted(request, uuid, tramitAPersAutLogicEjb);
    }
    
	// Per al Wizard
	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, TramitIServFilterForm filterForm,
			List<TramitIServ> list) throws I18NException {

		super.postList(request, mav, filterForm, list);
		request.getSession().setAttribute("tramitI", list);
	}

    @Override
    public String editarTramitIServPost(TramitIServForm tramitIServForm, BindingResult result, SessionStatus status,
            HttpServletRequest request, HttpServletResponse response) throws I18NException {

        String ret =  super.editarTramitIServPost(tramitIServForm, result, status, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitIServForm.getTramitIServ().getTramitid();

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb);
            tramitIServForm.setAttachedAdditionalJspCode(true);
        }
        return ret;
    }
    
	@Override
	public String crearTramitIServPost(TramitIServForm tramitIServForm, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		TramitIServJPA I = tramitIServForm.getTramitIServ();
		String codi = I.getCodi();
		Long tramitid = I.getTramitid();

		Long serveisRepetits = tramitIServLogicEjb.count(Where.AND(TRAMITID.equal(tramitid), CODI.equal(codi)));
		if (serveisRepetits > 0) {
			result.rejectValue(get(CODI), "genapp.validation.unique",
					new String[] { codi, I18NUtils.tradueix(CODI.fullName) }, null);
		}

		String ret = super.crearTramitIServPost(tramitIServForm, result, request, response);

		if (result.hasErrors()) {
			TramitAOperadorController.dadesWizard(request, tramitid, actual(), isPublic(), tramitAPersAutLogicEjb);
			tramitIServForm.setAttachedAdditionalJspCode(true);
		}
		return ret;
	}
}
