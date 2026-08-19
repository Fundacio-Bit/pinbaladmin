package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.PinfoController;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoForm;
import org.fundaciobit.pinbaladmin.back.security.LoginInfo;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaService;
import org.fundaciobit.pinbaladmin.logic.PinfoLogicaService;
import org.fundaciobit.pinbaladmin.logic.EntitatLogicaService;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaEJB.PinfoDataFull;
import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.pinbaladmin.model.fields.EntitatFields;
import org.fundaciobit.pinbaladmin.model.fields.PinfoFields;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias 26 nov 2024 14:13:17
 */
@Controller
@RequestMapping(value = PinfoOperadorController.WEBCONTEXT)
@SessionAttributes(types = { PinfoForm.class, PinfoFilterForm.class })
public class PinfoOperadorController extends PinfoController {

	public static final String WEBCONTEXT = "/operador/pinfo";
	
	@EJB(mappedName = PinfoDataLogicaService.JNDI_NAME)
	protected PinfoDataLogicaService pinfoDataLogicaEjb;
	
	@EJB(mappedName = PinfoLogicaService.JNDI_NAME)
	protected PinfoLogicaService pinfoLogicaEjb;
	

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicEjb;

	@Override
	public String getTileForm() {
		return "pinfoOperadorForm";
	}

	@Override
	public String getTileList() {
		return "pinfoOperadorList";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return this.getClass().getName() + "_FilterForm";
	}

	@Override
	public PinfoFilterForm getPinfoFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		PinfoFilterForm pinfoFilterForm = super.getPinfoFilterForm(pagina, mav, request);

		if (pinfoFilterForm.isNou()) {
			pinfoFilterForm.setVisibleMultipleSelection(false);
			pinfoFilterForm.setDeleteButtonVisible(false);
			pinfoFilterForm.setDeleteSelectedButtonVisible(false);
			pinfoFilterForm.setEditButtonVisible(true);
			pinfoFilterForm.setViewButtonVisible(true);
			
			//pinfoFilterForm.addHiddenField(ENTITAT);
			pinfoFilterForm.addHiddenField(PORTAFIBID);
//			pinfoFilterForm.addHiddenField(FITXERID);
			pinfoFilterForm.addHiddenField(FITXERFIRMATID);
			pinfoFilterForm.addHiddenField(DESTINATARINIF);
			pinfoFilterForm.addHiddenField(DESTINATARINOM);
			pinfoFilterForm.addHiddenField(MISSATGEPINBAL);
			pinfoFilterForm.addHiddenField(LOGPPNBAL);
			pinfoFilterForm.addHiddenField(MISSATGESOLICITANT);
			
			//Afegir filtre per NifSolicitant, NifDestinatari, idpinfo,
			List<Field<?>> filterBy = pinfoFilterForm.getDefaultFilterByFields();
		//	filterBy.add(PinfoFields.DESTINATARINIF);
			filterBy.add(PinfoFields.SOLICITANTNIF);
			filterBy.add(PinfoFields.PINFOID);
			
			pinfoFilterForm.setFilterByFields(filterBy);
			
			pinfoFilterForm.setOrderBy(PinfoFields.PINFOID.fullName);
			pinfoFilterForm.setOrderAsc(false);
		}

		return pinfoFilterForm;
	}
	
	
	
	// Problema d'Entitats dins de Pinfo #414

	@Override
	 public List<StringKeyValue> getReferenceListForEntitat(HttpServletRequest request,
	         ModelAndView mav, Where where)  throws I18NException {
	    

        return getReferenceListForEntitat(where, this.entitatLogicEjb, this.pinfoLogicaEjb);
	    
	    
	}

    public static List<StringKeyValue> getReferenceListForEntitat(Where where, EntitatLogicaService entitatLogicEjb, PinfoLogicaService pinfoLogicEjb) throws I18NException {
        // Seleccionam tots els CodisPinbal de les entitats que hi ha 
	    List<String> codisPinbalEntitats;
	 
	    codisPinbalEntitats = entitatLogicEjb.executeQuery(EntitatFields.CODIPINBAL, EntitatFields.GESTIONATPERGOVERNDIGITAL.equal(true));
	    
	    
	    // Seleccionam el codispinbal de tots els PINFO entitats segons Where
	    List<String> codisPinbalRef  = pinfoLogicEjb.executeQuery(PinfoFields.ENTITAT, where);
	    
	    
	    
	    Set<String> codisPinbal = new HashSet<String>();
	    
	    codisPinbal.addAll(codisPinbalEntitats);
	    codisPinbal.addAll(codisPinbalRef);
	          
	
	    List<StringKeyValue> _tmp = new java.util.ArrayList<StringKeyValue>();
	    for (String nom : codisPinbal) {
	        if (nom == null || nom.trim().isEmpty()) {
                continue;
	        }
	        
            _tmp.add(new StringKeyValue(nom, nom));
        }
	    
	    return _tmp;
    }
	
	
	
	
	
	@Override
	public PinfoForm getPinfoForm(PinfoJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		PinfoForm pinfoForm = super.getPinfoForm(_jpa, __isView, request, mav);
		
		if (__isView) {
			PinfoJPA pinfo = pinfoForm.getPinfo();
			Long estat = pinfo.getEstat();
			Long pinfoID = pinfo.getPinfoID();
			
			// Obtener los datos de usuarios y permisos para mostrar en la vista
			try {
				PinfoDataFull pinfoDataFull = pinfoDataLogicaEjb.getEstructuraUsuarisProcedimentServeis(pinfoID);
				mav.addObject("pinfoDataFull", pinfoDataFull);
			} catch (Exception e) {
				log.error("Error obtenint dades de permisos per PINFO " + pinfoID, e);
			}
			
			if (estat == Constants.ESTAT_PINFO_CREANT) {
				pinfoForm.addHiddenField(FITXERID);
				pinfoForm.addHiddenField(FITXERFIRMATID);
				pinfoForm.addHiddenField(PORTAFIBID);
				pinfoForm.addHiddenField(DESTINATARINIF);
				pinfoForm.addHiddenField(DESTINATARINOM);
				pinfoForm.addHiddenField(MISSATGEPINBAL);
				
			} else if (estat == Constants.ESTAT_PINFO_PENDENT_FIRMA) {
				pinfoForm.addHiddenField(FITXERFIRMATID);
				pinfoForm.addHiddenField(MISSATGEPINBAL);

			} else if (estat == Constants.ESTAT_PINFO_PENDENT_TRAMITAR) {
				pinfoForm.addHiddenField(FITXERID);
				pinfoForm.addHiddenField(PORTAFIBID);
				// pinfoForm.addHiddenField(MISSATGEPINBAL);

				// Si encara no s'ha processat, mostrar botó per processar
				if (pinfo.getMissatgePinbal() == null || pinfo.getMissatgePinbal().trim().isEmpty()) {
					pinfoForm.addAdditionalButton(new AdditionalButton("fas fa-cogs", "procesar.pinfo",
							WEBCONTEXT + "/procesarPinfo/{0}", AdditionalButtonStyle.PRIMARY));
				} else {
					// Si ja s'ha processat, mostrar botó per marcar com tramitat
					pinfoForm.addAdditionalButton(new AdditionalButton("fas fa-check-circle", "marcar.com.tramitat",
							WEBCONTEXT + "/marcarComTramitat/{0}", AdditionalButtonStyle.SUCCESS));
					// També permetre reprocessar per si cal
					pinfoForm.addAdditionalButton(new AdditionalButton("fas fa-redo", "reprocessar.pinfo",
							WEBCONTEXT + "/procesarPinfo/{0}", AdditionalButtonStyle.WARNING));
				}

			} else if (estat == Constants.ESTAT_PINFO_TRAMITAT) {
				pinfoForm.addHiddenField(FITXERID);
				pinfoForm.addHiddenField(PORTAFIBID);

				pinfoForm.addAdditionalButton(
						new AdditionalButton("fas fa-paper-plane", "tramitpinfo.enviarmissatge.solicitant",
								WEBCONTEXT + "/enviarMissatgeSolicitant/{0}", AdditionalButtonStyle.SUCCESS));
			}

			pinfoForm.setAttachedAdditionalJspCode(true);
		}

		return pinfoForm;
	}
	
	@RequestMapping(value = "/procesarPinfo/{pinfoID}")
	public String procesarPinfo(HttpServletRequest request, ModelAndView mav, @PathVariable("pinfoID") java.lang.Long pinfoID) {
		
		try {
			pinfoDataLogicaEjb.procesarPermisosPinfo(pinfoID);
			
			String msg = "PINFO " + pinfoID + " processat correctament. Reviseu el resultat i marqueu com tramitat si tot és correcte.";
			log.info(msg);
			HtmlUtils.saveMessageSuccess(request, msg);
			
		} catch (I18NException e) {
			String msg = "Error Procesant PINFO " + pinfoID + ": " + I18NUtils.getMessage(e);
			log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
		}
		
		return "redirect:" + WEBCONTEXT + "/view/" + pinfoID;
	}
	
	@RequestMapping(value = "/marcarComTramitat/{pinfoID}")
	public String marcarComTramitat(HttpServletRequest request, ModelAndView mav, @PathVariable("pinfoID") java.lang.Long pinfoID) {
		
		try {
			pinfoDataLogicaEjb.marcarPinfoComTramitat(pinfoID);
			
			String msg = "PINFO " + pinfoID + " marcat com a TRAMITAT correctament";
			log.info(msg);
			HtmlUtils.saveMessageSuccess(request, msg);
			
		} catch (I18NException e) {
			String msg = "Error marcant PINFO " + pinfoID + " com tramitat: " + I18NUtils.getMessage(e);
			log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
		}
		
		return "redirect:" + WEBCONTEXT + "/view/" + pinfoID;
	}
	
	@RequestMapping(value = "/enviarMissatgeSolicitant/{pinfoID}")
	public String enviarMissatgeSolicitant(HttpServletRequest request, ModelAndView mav,
			@PathVariable("pinfoID") java.lang.Long pinfoID) {

		try {
			UserInfo operador = LoginInfo.getInstance().getUserInfo();
			pinfoLogicaEjb.enviarMissatgeSolicitant(operador, pinfoID);
			String msg = "Missatge enviat al solicitant del PINFO " + pinfoID;
			HtmlUtils.saveMessageSuccess(request, msg);
			
		} catch (I18NException e) {
			String msgError = I18NUtils.getMessage(e);
			log.error(msgError, e);
			HtmlUtils.saveMessageError(request, msgError);
		}

		return "redirect:" + WEBCONTEXT + "/view/" + pinfoID;
	}
	
	@RequestMapping(value = "/llistatUsuaris/{pinfoID}")
	public String llistatUsuaris(HttpServletRequest request, ModelAndView mav, @PathVariable("pinfoID") java.lang.Long pinfoID) {

		
		pinfoDataLogicaEjb.llistatUsuarisPinbal();
		
		return "redirect:" + WEBCONTEXT + "/view/" + pinfoID;

	}

	@Override
	public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {

		List<StringKeyValue> _tmp = new java.util.ArrayList<StringKeyValue>();

		Long[] estatsPinfo = Constants.ESTATS_PINFO;
		for (Long estat : estatsPinfo) {
			StringKeyValue skv = new StringKeyValue(estat.toString(), I18NUtils.tradueix("estat.pinfo." + estat));
			_tmp.add(skv);
		}

		return _tmp;
	}
	
	
	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, PinfoFilterForm filterForm, List<Pinfo> list)
			throws I18NException {

		filterForm.getAdditionalButtonsByPK().clear();

		super.postList(request, mav, filterForm, list);
		// afegir botó per veure events d'un Pinfo

		for (Pinfo pinfo : list) {
			Long pinfoID = pinfo.getPinfoID();
			Long incidenciaID = pinfo.getIncidenciaID();

			filterForm.addAdditionalButtonByPK(pinfoID,
					new AdditionalButton("fas fa-bullhorn", "veure.events",
							EventIncidenciaTecnicaOperadorController.CONTEXT_PATH + "/veureevents/" + incidenciaID,
							AdditionalButtonStyle.SUCCESS));

			if (pinfo.getEstat() == Constants.ESTAT_PINFO_PENDENT_TRAMITAR) {
				// Si encara no s'ha processat, mostrar botó per processar
				if (pinfo.getMissatgePinbal() == null || pinfo.getMissatgePinbal().trim().isEmpty()) {
					filterForm.addAdditionalButtonByPK(pinfoID, new AdditionalButton("fas fa-cogs", "procesar.pinfo",
							WEBCONTEXT + "/procesarPinfo/{0}", AdditionalButtonStyle.PRIMARY));
				} else {
					// Si ja s'ha processat, mostrar botó per marcar com tramitat
					filterForm.addAdditionalButtonByPK(pinfoID, new AdditionalButton("fas fa-check-circle", "marcar.com.tramitat",
							WEBCONTEXT + "/marcarComTramitat/{0}", AdditionalButtonStyle.SUCCESS));
				}

			} else if (pinfo.getEstat() == Constants.ESTAT_PINFO_TRAMITAT) {
				filterForm.addAdditionalButtonByPK(pinfoID,
						new AdditionalButton("fas fa-paper-plane", "tramitpinfo.enviarmissatge.solicitant",
								WEBCONTEXT + "/enviarMissatgeSolicitant/{0}", AdditionalButtonStyle.LIGHT));
			}
		}
	}
	
	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		Long[] estats = { Constants.ESTAT_PINFO_PENDENT_FIRMA, Constants.ESTAT_PINFO_PENDENT_TRAMITAR,
				Constants.ESTAT_PINFO_TRAMITAT, Constants.ESTAT_PINFO_NOTIFICAT };
		
		Where wEstats = PinfoFields.ESTAT.in(estats);

		return Where.AND(super.getAdditionalCondition(request), wEstats);
	}
	
//	DELETE
//	@Override
//	public List<StringKeyValue> getReferenceListForEntitat(HttpServletRequest request, ModelAndView mav, Where where)
//			throws I18NException {
//		return pinfoLogicEjb.getEntitats();
//	}

}
