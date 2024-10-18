package org.fundaciobit.pinbaladmin.back.controller.all;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.back.controller.webdb.PinfoDataController;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataForm;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.PINFOLogicaService;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.PINFO;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.pinbaladmin.model.fields.PINFOFields;
import org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields;
import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias 17 oct 2024 15:38:55
 */

@Controller
@RequestMapping(value = PinfoDataPublicController.CONTEXT_WEB)
@SessionAttributes(types = { PinfoDataForm.class, PinfoDataFilterForm.class })
public class PinfoDataPublicController extends PinfoDataController {

	public static final String CONTEXT_WEB = "/public/pinfodata";

	@EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
	protected IncidenciaTecnicaLogicaService incidenciaTecnicaLogicaEjb;

	@EJB(mappedName = PINFOLogicaService.JNDI_NAME)
	protected PINFOLogicaService pinfoLogicEjb;

	@EJB(mappedName = PinfoDataLogicaService.JNDI_NAME)
	protected PinfoDataLogicaService pinfoDataLogicaEjb;

	@Override
	public String getTileForm() {
		return "pinfoDataFormPublic";
	}

	@Override
	public String getTileList() {
		return "pinfoDataListPublic";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return this.getClass().getName() + "_FilterForm";
	}

	@Override
	public PinfoDataFilterForm getPinfoDataFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		PinfoDataFilterForm filterForm = super.getPinfoDataFilterForm(pagina, mav, request);

		Long id = (Long) request.getSession().getAttribute("incidenciaId");

		if (id == null) {
			id = 50111L;
		}
		IncidenciaTecnica inc = incidenciaTecnicaLogicaEjb.findByPrimaryKey(id);
		mav.addObject("incidencia", inc);

		if (filterForm.isNou()) {
			filterForm.addHiddenField(PinfoDataFields.PINFODATAID);
			filterForm.addHiddenField(PinfoDataFields.PINFOID);
			filterForm.addHiddenField(PinfoDataFields.ESTAT);

			filterForm.setVisibleExportList(false);

			filterForm.setItemsPerPage(-1);
			filterForm.setTitleCode("tramit.pinfo.solicitar");
			filterForm.setAttachedAdditionalJspCode(true);

		}

		return filterForm;
	}

	@Override
	public PinfoDataForm getPinfoDataForm(PinfoDataJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {
		PinfoDataForm form = super.getPinfoDataForm(_jpa, __isView, request, mav);

		PinfoData pinfoData = form.getPinfoData();

		form.addHiddenField(PinfoDataFields.PINFOID);

		Long pinfoID = (Long) request.getSession().getAttribute("pinfoID");
		pinfoData.setPinfoID(pinfoID);

		form.addHiddenField(PinfoDataFields.ESTAT);
		pinfoData.setEstat(0L);

		pinfoData.setAlta(1L);

		return form;
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		Where where = super.getAdditionalCondition(request);

		Long incidenciaID = (Long) request.getSession().getAttribute("incidenciaId");

		log.info("incidenciaID 1: " + incidenciaID);
		if (incidenciaID == null) {
			incidenciaID = 50111L;
		}

		if (incidenciaID != null) {

			List<PINFO> pinfos = pinfoLogicEjb.select(PINFOFields.INCIDENCIAID.equal(incidenciaID));
			log.info("pinfos: " + pinfos.size());
			if (pinfos.size() == 1) {
				Long pinfoID = pinfos.get(0).getPinfoID();
				log.info("pinfoID: " + pinfoID);
				request.getSession().setAttribute("pinfoID", pinfoID);
				where = Where.AND(where, PinfoDataFields.PINFOID.equal(pinfoID));
			}
		}

		return where;
	}

	@Override
	public PinfoDataJPA create(HttpServletRequest request, PinfoDataJPA pinfoData)
			throws I18NException, I18NValidationException {

		PinfoDataJPA pinfoDataJPA;
		pinfoDataJPA = (PinfoDataJPA) pinfoDataLogicaEjb.create(pinfoData);

		return pinfoDataJPA;
	}

	@Override
	public void delete(HttpServletRequest request, PinfoData pinfoData) throws I18NException {
		pinfoDataLogicaEjb.delete(pinfoData);
	}

	@Override
	public PinfoDataJPA update(HttpServletRequest request, PinfoDataJPA pinfoData)
			throws I18NException, I18NValidationException {
		return (PinfoDataJPA) pinfoDataLogicaEjb.update(pinfoData);
	}

	@Override
	  public PinfoDataJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pinfodataID) throws I18NException {
	    return (PinfoDataJPA) pinfoDataLogicaEjb.findByPrimaryKey(pinfodataID);
	  }
	
	@Override
	public List<StringKeyValue> getReferenceListForAlta(HttpServletRequest request, ModelAndView mav, Where where)
			throws I18NException {
		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
		__tmp.add(new StringKeyValue("1", "Alta"));
		__tmp.add(new StringKeyValue("0", "Baixa"));

		return __tmp;
	}

	@Override
	public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request, ModelAndView mav, Where where)
			throws I18NException {
		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
		__tmp.add(new StringKeyValue("2", "Solicitat"));
		__tmp.add(new StringKeyValue("1", "Pendent"));
		__tmp.add(new StringKeyValue("0", "Creant"));

		return __tmp;
	}

}
