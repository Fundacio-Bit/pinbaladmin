package org.fundaciobit.pinbaladmin.back.controller.all;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.Section;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pinbaladmin.back.controller.webdb.ModificacioSoliServController;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSoliServFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSoliServForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSolicitudForm;
import org.fundaciobit.pinbaladmin.model.entity.ModificacioSoliServ;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSoliServFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSoliServFields;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSoliServJPA;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = ModificacioSoliServPublicController.CONTEXT_WEB)
@SessionAttributes(types = { ModificacioSoliServForm.class, ModificacioSoliServFilterForm.class })
public class ModificacioSoliServPublicController extends ModificacioSoliServController {

	public static final String CONTEXT_WEB = "/public/modificarsoliserv";

	@Override
	public String getTileForm() {
		return "modificacioSoliServFormPublic";
	}

	@Override
	public String getTileList() {
		return "modificacioSoliServListPublic";
	}
	
	@Override
	public ModificacioSoliServFilterForm getModificacioSoliServFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {
		
		ModificacioSoliServFilterForm filterForm = super.getModificacioSoliServFilterForm(pagina, mav, request);
		
		if (filterForm.isNou()) {
			log.info("Pasam per aqui, bones");
		}
		
		return filterForm;
	}
	
	@Override
	public ModificacioSoliServForm getModificacioSoliServForm(ModificacioSoliServJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {

		ModificacioSoliServForm form = super.getModificacioSoliServForm(_jpa, __isView, request, mav);
		
		ModificacioSoliServ solSer = form.getModificacioSoliServ();
		
		Section norma1 = new Section("norma1", "tramitIServ.normalegal.1", ModificacioSoliServFields.NORMA1, ModificacioSoliServFields.FITXERNORMA1ID, ModificacioSoliServFields.ARTICLES1);
		Section norma2 = new Section("norma2", "tramitIServ.normalegal.2", ModificacioSoliServFields.NORMA2, ModificacioSoliServFields.FITXERNORMA2ID, ModificacioSoliServFields.ARTICLES2);
		Section norma3 = new Section("norma3", "tramitIServ.normalegal.3", ModificacioSoliServFields.NORMA3, ModificacioSoliServFields.FITXERNORMA3ID, ModificacioSoliServFields.ARTICLES3);
		form.addSection(norma1);
		form.addSection(norma2);
		form.addSection(norma3);
		
		Long normesAfegides = 1L;
		if (solSer.getFitxerNorma1ID() != null) {
			if (solSer.getFitxerNorma2ID() != null) {
				normesAfegides++;
				if (solSer.getFitxerNorma3ID() != null) {
					normesAfegides++;
				}
			}
		}
		request.setAttribute("normesAfegides", normesAfegides);

		if (form.isNou()) {
			
			Long modsoliID = (Long) request.getSession().getAttribute(ModificarSolicitudPublicController.MOD_SOLI_ID);
			log.info("modSoli: "+ modsoliID);
			solSer.setModSoliID(modsoliID);
			
			form.addHiddenField(ModificacioSoliServFields.MODSOLISERVID);
			form.addHiddenField(ModificacioSoliServFields.MODSOLIID);
			
			form.addReadOnlyField(ModificacioSoliServFields.SOLISERVID);
			form.setAttachedAdditionalJspCode(true);
		}
		
		return form;
	}
}
