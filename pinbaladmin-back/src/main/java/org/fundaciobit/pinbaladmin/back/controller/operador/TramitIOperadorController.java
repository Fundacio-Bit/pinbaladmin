package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.Section;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.all.TramitAPublicController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitIServController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.ServeiService;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitIServLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitJConsentLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
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

import com.google.gson.Gson;

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

    @EJB(mappedName = ServeiService .JNDI_NAME)
    protected ServeiService serveiEjb;
    
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
        Long normesAfegides = 1L;
        if (tramitForm.isNou()) {
            tramitID = TramitAOperadorController.getTramitIDFromRequest(request);
            tramitI.setTramitid(tramitID);
        } else {
            tramitID = tramitI.getTramitid();
            
			if (tramitI.getFitxernormaID() != null) {
				if (tramitI.getFitxernorma2ID() != null) {
					normesAfegides++;
					if (tramitI.getFitxernorma3ID() != null) {
						normesAfegides++;
					}
				}
			}
        }
        
        
        tramitForm.setSaveButtonVisible(false);
		tramitForm.addAdditionalButton(new AdditionalButton("", "genapp.save", "javascript:submitForm();", AdditionalButtonStyle.PRIMARY));
        
		request.setAttribute("normesAfegides", normesAfegides);
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

            tramitIServFilterForm.addAdditionalButton(new AdditionalButton("fas fa-plus", "tramit.i.afegir.servei",
            		getContextWeb() + "/new?tramitid=" + uuid, AdditionalButtonStyle.INFO));
            
            tramitIServFilterForm.addAdditionalButton(
                    new AdditionalButton("", "genapp.delete", getContextWeb() + "/delete/" + uuid, AdditionalButtonStyle.DANGER));

            Long serveisAfegits = tramitIServLogicEjb.count(TRAMITID.equal(tramitID));
			if (serveisAfegits > 0) {
				tramitIServFilterForm.addAdditionalButton(new AdditionalButton("", "genapp.continue",
						getContextWebNext() + "/next/" + uuid, AdditionalButtonStyle.PRIMARY));
			}
			
			tramitIServFilterForm.addAdditionalButton(new AdditionalButton("",
					"genapp.pagination.anterior", getContextWebPrev() + "/back/" + uuid, AdditionalButtonStyle.INFO));
			
			
            {
            	request.setAttribute("llistat", true);
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
//        return TramitAOperadorController.getRedirectWhenDeleted(request, uuid, tramitAPersAutLogicEjb);
    	return "redirect:" + TramitAPublicController.CONTEXT_WEB + "/cancelarTramit/" + uuid;
    }
    
	// Per al Wizard
	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, TramitIServFilterForm filterForm,
			List<TramitIServ> list) throws I18NException {

		super.postList(request, mav, filterForm, list);
		request.getSession().setAttribute("tramitI", list);
	}

	
	@Override
	public void postValidate(HttpServletRequest request, TramitIServForm tramitIServForm, BindingResult result)
			throws I18NException {
		super.postValidate(request, tramitIServForm, result);

		TramitIServJPA tramitI = tramitIServForm.getTramitIServ();

		// Si alguno de los campos de una norma es distinto de null, todos los campos de
		// la norma deben tener valor.

		if (tramitI.getFitxernorma() != null || tramitI.getNorma() != null || tramitI.getArticles() != null) {
			if (tramitI.getFitxernorma() == null || tramitI.getNorma() == null || tramitI.getArticles() == null) {
				// Marcamos error y dejamos nulos todos los campos de la norma
				tramitI.setFitxernormaID(null);
				tramitI.setFitxernorma(null);
				tramitI.setNorma(null);
				tramitI.setArticles(null);

				result.rejectValue(get(NORMA), "genapp.validation.malformed",
						new String[] { I18NUtils.tradueix(NORMA.fullName) }, null);
			}
		}

		if (tramitI.getFitxernorma2ID() != null || tramitI.getNorma2() != null || tramitI.getArticles2() != null) {
			if (tramitI.getFitxernorma2ID() == null || tramitI.getNorma2() == null || tramitI.getArticles2() == null) {
				// Marcamos error y dejamos nulos todos los campos de la norma
				tramitI.setFitxernorma2ID(null);
				tramitI.setFitxernorma2(null);
				tramitI.setNorma2(null);
				tramitI.setArticles2(null);

				result.rejectValue(get(NORMA2), "genapp.validation.malformed",
						new String[] { I18NUtils.tradueix(NORMA2.fullName) }, null);
				
			}
		}

		if (tramitI.getFitxernorma3ID() != null || tramitI.getNorma3() != null || tramitI.getArticles3() != null) {
			if (tramitI.getFitxernorma3ID() == null || tramitI.getNorma3() == null || tramitI.getArticles3() == null) {
				// Marcamos error y dejamos nulos todos los campos de la norma
				tramitI.setFitxernorma3ID(null);
				tramitI.setFitxernorma3(null);
				tramitI.setNorma3(null);
				tramitI.setArticles3(null);

				result.rejectValue(get(NORMA3), "genapp.validation.malformed",
						new String[] { I18NUtils.tradueix(NORMA3.fullName) }, null);
			}
		}
	}
	
	@Override
	public void preValidate(HttpServletRequest request, TramitIServForm tramitIServForm, BindingResult result)
			throws I18NException {
		super.preValidate(request, tramitIServForm, result);
		
		TramitIServJPA tramitI = tramitIServForm.getTramitIServ();
		
		// Si el campo norma vale "none", poner sus campos a null y no marcar error
		if (tramitI.getNorma2() != null && tramitI.getNorma2().equals("none")) {
			tramitI.setFitxernorma2ID(null);
			tramitI.setFitxernorma2(null);
			tramitI.setNorma2(null);
			tramitI.setArticles2(null);
		}
		
		if (tramitI.getNorma3() != null && tramitI.getNorma3().equals("none")) {
			tramitI.setFitxernorma3ID(null);
			tramitI.setFitxernorma3(null);
			tramitI.setNorma3(null);
			tramitI.setArticles3(null);
		}
	}
	
    @Override
    public String editarTramitIServPost(TramitIServForm tramitIServForm, BindingResult result, SessionStatus status,
            HttpServletRequest request, HttpServletResponse response) throws I18NException {

        String ret =  super.editarTramitIServPost(tramitIServForm, result, status, request, response);
        if (result.hasErrors()) {
        	
        	TramitIServJPA tramitI = tramitIServForm.getTramitIServ();
            Long tramitID = tramitI.getTramitid();
            
            Long normesAfegides = 1L;
			if (tramitI.getFitxernormaID() != null) {
				if (tramitI.getFitxernorma2ID() != null) {
					normesAfegides++;
					if (tramitI.getFitxernorma3ID() != null) {
						normesAfegides++;
					}
				}
			}
	        request.setAttribute("normesAfegides", normesAfegides);

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
			
            Long normesAfegides = 1L;
			if (I.getFitxernormaID() != null) {
				if (I.getFitxernorma2ID() != null) {
					normesAfegides++;
					if (I.getFitxernorma3ID() != null) {
						normesAfegides++;
					}
				}
			}
	        request.setAttribute("normesAfegides", normesAfegides);
	        
			TramitAOperadorController.dadesWizard(request, tramitid, actual(), isPublic(), tramitAPersAutLogicEjb);
			tramitIServForm.setAttachedAdditionalJspCode(true);
		}
		return ret;
	}
	
//	http://ptrias:8080/pinbaladmin/public/tramiti/jsonServeis?query=s
		
	public class Item {
		private String key;
		private String value;

		public Item(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}
	
	
//	@RequestMapping(value = "/{servid}/jsonServeis", method = RequestMethod.GET)
//	public void obtenirJsonServeis(HttpServletRequest request, HttpServletResponse response,
//			@PathVariable("servid") java.lang.Long servid) throws Exception {
//		obtenirJsonServeis(request, response);
//	}
	
	@RequestMapping(value = {"/jsonServeis", "/{servid}/jsonServeis"}, method = RequestMethod.GET)
	public void obtenirJsonServeis(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String param = (String) request.getParameter("query");
		log.info("param: ]" + param + "[");

//		Long firstResult = 0L;
//		Long maxResults = 10L;
//		List<Servei> serveis = serveiEjb.select(ServeiFields.NOM.like("%" + param + "%"), firstResult, maxResults, null);
		
		List<Servei> serveis = serveiEjb.select(Where.OR(ServeiFields.NOM.like("%" + param + "%"), ServeiFields.CODI.like("%" + param + "%")));
		List<Item> items = new java.util.ArrayList<Item>();

		log.info("serveis: " + serveis.size());

		for (Servei servei : serveis) {
			String key = servei.getCodi();
			String value = servei.getNom();

			Item item = new Item(key, value);
			items.add(item);
		}

		Gson g = new Gson();
		String employeeJsonString = g.toJson(items);

		log.info(employeeJsonString);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(employeeJsonString);
		out.flush();
	}
	
}
