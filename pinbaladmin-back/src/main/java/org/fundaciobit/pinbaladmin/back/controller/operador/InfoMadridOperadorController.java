package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.InfoMadridController;
import org.fundaciobit.pinbaladmin.back.form.webdb.InfoMadridFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.InfoMadridForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = "/operador/infoMadrid")
@SessionAttributes(types = { InfoMadridForm.class, InfoMadridFilterForm.class })
public class InfoMadridOperadorController extends InfoMadridController {

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;

	@Override
	public String getTileForm() {
		return "infoMadridFormOperador";
	}

	@Override
	public String getTileList() {
		return "infoMadridListOperador";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "infoMadridOperador_FilterForm";
	}

	@Override
	public boolean isActiveDelete() {
		return false;
	}

//    @Override
//    public InfoMadridFilterForm getInfoMadridFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
//            throws I18NException {
//        InfoMadridFilterForm infoMadridFilterForm = super.getInfoMadridFilterForm(pagina, mav, request);
//
//        if (infoMadridFilterForm.isNou()) {
//            infoMadridFilterForm.addHiddenField(ORGANID);
//            infoMadridFilterForm.addHiddenField(ENTITATID);
//            
//            infoMadridFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_RELOAD, "actualitzar.dir3",
//                    getContextWeb() + "/updateDir3", AdditionalButtonStyle.PRIMARY));
//
//            infoMadridFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-code-branch fa-rotate-90",
//                    "mostrar.jerarquia", "javascript:mostrarJerarquia({0})", AdditionalButtonStyle.INFO));
//
//            infoMadridFilterForm.setAttachedAdditionalJspCode(true);
//            infoMadridFilterForm.setVisibleFilterBy(true);
//
//        }
//        return infoMadridFilterForm;
//    }

	@Override
	public InfoMadridForm getInfoMadridForm(InfoMadridJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {

		InfoMadridForm form = super.getInfoMadridForm(_jpa, __isView, request, mav);

		if (__isView) {

			log.info("Info Madrid Form");
			form.addHiddenField(CONSULTA);
			
		}
		
		String nom = form.getInfoMadrid().getTitularNom();
		
		if (nom != null) {
			nom = nom.replace("|", " ");
		}
		
		form.getInfoMadrid().setTitularNom(nom);

		return form;

	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, Long infoMadridID) {
		try {
			Long soliID = solicitudLogicaEjb.executeQueryOne(SolicitudFields.SOLICITUDID,
					SolicitudFields.INFOMADRIDID.equal(infoMadridID));
			return "redirect:" + SolicitudFullViewOperadorController.CONTEXTWEB + "/view/" + soliID;
		} catch (I18NException e) {
			return "redirect:" + getContextWeb() + "/list";

		}
	}
	
	@Override
	public List<StringKeyValue> getReferenceListForEstatProcediment(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {
		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

		for (long estat : Constants.ESTATS_SOLI) {
			String key = String.valueOf(estat);
			String value = I18NUtils.tradueix("solicitud.estat." + key);
			
//			log.info("Estat PROC: " + key + " - " + value);
			
			__tmp.add(new StringKeyValue(key, value));
		}
		return __tmp;
	}

	@Override
	public List<StringKeyValue> getReferenceListForEstatAutoritzacio(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {
		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

		for (Long estat : Constants.ESTATS_PINBAL) {
			String key = String.valueOf(estat);
			String value = I18NUtils.tradueix("estat.pinbal." + key);
			
//			log.info("Estat AUTH: " + key + " - " + value);
			
			__tmp.add(new StringKeyValue(key, value));
		}
		return __tmp;
	}
	
	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, InfoMadridFilterForm filterForm,
			List<InfoMadrid> list) throws I18NException {
		// TODO Auto-generated method stub
		super.postList(request, mav, filterForm, list);
		
		
		for (InfoMadrid infoMadrid : list) {
			if (infoMadrid.getTitularNom() != null) {

				String nom = infoMadrid.getTitularNom();
				
				if (nom != null) {
					nom = nom.replace("|", " ");
				}
				
				infoMadrid.setTitularNom(nom);
			}

		}
		
	}
}
