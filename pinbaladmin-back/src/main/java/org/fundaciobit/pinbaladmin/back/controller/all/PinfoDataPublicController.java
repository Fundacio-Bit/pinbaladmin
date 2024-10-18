package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.controller.webdb.PinfoDataController;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataForm;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.PINFOLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
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

		log.info("Estamos en PinfoDataPublicController.getPinfoDataFilterForm(). incidenciaId: " + id);

		return filterForm;
	}

}
