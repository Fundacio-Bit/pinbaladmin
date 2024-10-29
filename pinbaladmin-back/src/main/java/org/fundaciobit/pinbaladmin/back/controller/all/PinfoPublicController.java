package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.pinbaladmin.back.controller.webdb.PinfoController;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.PinfoLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias 28 oct 2024 15:27:35
 */

@Controller
@RequestMapping(value = PinfoPublicController.CONTEXT_WEB)
@SessionAttributes(types = { PinfoForm.class, PinfoFilterForm.class })
public class PinfoPublicController extends PinfoController {

	public static final String CONTEXT_WEB = "/public/pinfo";

	@EJB(mappedName = PinfoLogicaService.JNDI_NAME)
	protected PinfoLogicaService pinfoLogicaEjb;

	@Override
	public String getTileForm() {
		return "pinfoFormPublic";
	}

	@Override
	public String getTileList() {
		return "pinfoListPublic";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return this.getClass().getName() + "_FilterForm";
	}

	@Override
	public PinfoJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pinfoID) throws I18NException {
		return (PinfoJPA) pinfoLogicaEjb.findByPrimaryKey(pinfoID);
	}

	@Override
	public PinfoForm getPinfoForm(PinfoJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		PinfoForm pinfoForm = super.getPinfoForm(_jpa, __isView, request, mav);

		PinfoJPA pinfo = pinfoForm.getPinfo();

		if (pinfo.getEstat() == Constants.ESTAT_PINFO_CREANT) {
			pinfoForm.addHiddenField(FITXERFIRMATID);
			pinfoForm.addHiddenField(PORTAFIBID);
//			pinfoForm.addHiddenField(PINFOID);
			pinfoForm.addHiddenField(ESTAT);

			pinfoForm.addReadOnlyField(FITXERID);
			pinfoForm.addReadOnlyField(SOLICITANTNIF);

			pinfoForm.setDeleteButtonVisible(false);
			pinfoForm.setSaveButtonVisible(false);
			pinfoForm.setCancelButtonVisible(false);

			Long pinfoID = pinfo.getPinfoID();
			log.info("pinfoID: " + pinfoID);
			pinfoForm.addAdditionalButton(new AdditionalButton("fas fa-signed-file", "enviar.portafib",
					CONTEXT_WEB + "/enviarPortafib/" + pinfoID  , AdditionalButtonStyle.INFO));

		}

		mav.addObject("pinfo", pinfo);
		pinfoForm.setAttachedAdditionalJspCode(true);

		return pinfoForm;
	}
	
	@RequestMapping(value = "/enviarPortafib/{pinfoID}")
	public String enviarPinfoPortaFIB(HttpServletRequest request,  @PathVariable("pinfoID") java.lang.Long pinfoID) throws I18NException {
		
		log.info("enviarPinfoPortaFIB: " + pinfoID);
		
		PinfoJPA pinfo = pinfoLogicaEjb.findByPrimaryKey(pinfoID);
		log.info("pinfo: " + pinfo);
		
		pinfo.setEstat(Constants.ESTAT_PINFO_PENDENT_FIRMA);
		Pinfo p  = pinfoLogicaEjb.update(pinfo);
		
		HtmlUtils.saveMessageInfo(request, "Enviar Pinfo a firmar. " + p.getPinfoID());
		
		
		return "redirect:" + CONTEXT_WEB + "/view/" + p.getPinfoID();
		
		//Mas adelante, se irá a List para ver sus pinfos (añadir campo solicitante al pinfo, y cambiar getAdditionalCondition())
	}
	


}
