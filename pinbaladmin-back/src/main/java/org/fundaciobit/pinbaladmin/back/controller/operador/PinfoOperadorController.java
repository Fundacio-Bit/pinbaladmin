package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.PinfoController;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.pinbaladmin.model.fields.PinfoFields;
import org.fundaciobit.pinbaladmin.model.fields.PinfoQueryPath;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
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
	protected PinfoDataLogicaService pinfoDataLogicEjb;

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

//		String str = "";
//		Where i = new PinfoQueryPath().FITXER().NOM().like("%" + str + "%");
		
		if (pinfoFilterForm.isNou()) {
			pinfoFilterForm.setVisibleMultipleSelection(false);
			pinfoFilterForm.setDeleteButtonVisible(false);
			pinfoFilterForm.setDeleteSelectedButtonVisible(false);
			pinfoFilterForm.setEditButtonVisible(false);
			pinfoFilterForm.setViewButtonVisible(true);

			pinfoFilterForm.addHiddenField(PORTAFIBID);
			pinfoFilterForm.addHiddenField(FITXERID);
			pinfoFilterForm.addHiddenField(FITXERFIRMATID);
			
			//Afegir filtre per NifSolicitant, NifDestinatari, idpinfo,
			List<Field<?>> filterBy = pinfoFilterForm.getDefaultFilterByFields();
			filterBy.add(PinfoFields.DESTINATARINIF);
			filterBy.add(PinfoFields.SOLICITANTNIF);
			filterBy.add(PinfoFields.PINFOID);
			
			pinfoFilterForm.setFilterByFields(filterBy);
		}

		return pinfoFilterForm;
	}
	
	
	@Override
	public PinfoForm getPinfoForm(PinfoJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		PinfoForm pinfoForm = super.getPinfoForm(_jpa, __isView, request, mav);
		
		if (__isView) {
			PinfoJPA pinfo = pinfoForm.getPinfo();
			Long estat = pinfo.getEstat();
			
			if (estat == Constants.ESTAT_PINFO_PENDENT_TRAMITAR) {
				pinfoForm.addAdditionalButton(new AdditionalButton("fas fa-cogs", "procesar.pinfo",
						WEBCONTEXT + "/procesarPinfo/{0}", AdditionalButtonStyle.PRIMARY));
			}
			
		}

		return pinfoForm;
	}
	
	
	@RequestMapping(value = "/procesarPinfo/{pinfoID}")
	public String procesarPinfo(HttpServletRequest request, ModelAndView mav, @PathVariable("pinfoID") java.lang.Long pinfoID) throws I18NException {
		
		Where wPinfoID = PinfoFields.PINFOID.equal(pinfoID);
		List<PinfoData> pinfoDatas =  pinfoDataLogicEjb.select(wPinfoID);
		
		log.info("Procesant PinfoData " + pinfoDatas.size());
		
		for (PinfoData pinfoData : pinfoDatas) {	
			log.info("Procesant PinfoData " + pinfoData.getPinfodataID() + ": usr[" + pinfoData.getUsuariid() + "] - ["
					+ pinfoData.getProcedimentID() + "] - [" + pinfoData.getServeiID() + "] - [" + pinfoData.getAlta() + "]");
		}
		
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
		super.postList(request, mav, filterForm, list);
		//afegir botó per veure events d'un Pinfo
	}
	
	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		Long[] estats = { Constants.ESTAT_PINFO_PENDENT_FIRMA, Constants.ESTAT_PINFO_PENDENT_TRAMITAR,
				Constants.ESTAT_PINFO_TRAMITAT };
		
		Where wEstats = PinfoFields.ESTAT.in(estats);

		return Where.AND(super.getAdditionalCondition(request), wEstats);
	}
	
	
}
