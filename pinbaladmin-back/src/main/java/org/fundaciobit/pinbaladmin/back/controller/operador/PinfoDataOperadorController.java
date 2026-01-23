package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.back.controller.webdb.PinfoDataController;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataForm;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager.TipusPluginUserInfo;
import org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

/**
 * 
 * @author ptrias 17 dic 2024 13:28:40
 */

@Controller
@RequestMapping(value = PinfoDataOperadorController.WEBCONTEXT)
@SessionAttributes(types = { PinfoDataForm.class, PinfoDataFilterForm.class })
public class PinfoDataOperadorController extends PinfoDataController {

	public static final String WEBCONTEXT = "/operador/pinfoData";

	@Override
	public String getTileForm() {
		return "pinfoDataFormOperador";
	}

	@Override
	public String getTileList() {
		return "pinfoDataListOperador";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return this.getClass().getName() + "_FilterForm";
	}

	@Override
	public PinfoDataFilterForm getPinfoDataFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		PinfoDataFilterForm filterForm = super.getPinfoDataFilterForm(pagina, mav, request);

		if (filterForm.isNou()) {
			filterForm.addHiddenField(PinfoDataFields.PINFODATAID);
			filterForm.addHiddenField(PinfoDataFields.PINFOID);
			filterForm.addHiddenField(PinfoDataFields.ESTAT);

			filterForm.setVisibleExportList(false);
			filterForm.setDeleteSelectedButtonVisible(false);
			filterForm.setAddButtonVisible(false);

			filterForm.setItemsPerPage(-1);
			filterForm.setTitleCode("tramit.pinfo.solicitar");
			filterForm.setAttachedAdditionalJspCode(true);
		}

		return filterForm;
	}
	
	
	@RequestMapping(value = { "/validateUser" }, method = RequestMethod.GET)
	public void validateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String username = request.getParameter("username");
	    log.info("username: ]" + username + "[");

	    boolean debug = false;
    	boolean caib = true;

		IUserInformationPlugin plugin =  PinbalAdminPluginsManager.getUserInformationPluginInstance(debug, TipusPluginUserInfo.LDAP);
	    UserInfo info = plugin.getUserInfoByUserName(username);

	    String usuariTrobat = null;
	    if (info != null) {
	        String nom = info.getFullName() + " - " + info.getAdministrationID();
	        /*
	        String nif = info.getAddress() + " - " + 
	                     info.getCompany() + " - " + 
	                     info.getCompanyArea() + " - " + 
	                     info.getCompanyDepartment() + " - " + 
	                     info.getDir3() + " - " + 
	                     info.getId() + " - " + 
	                     info.getNotes() + " - " + 
	                     info.getName() + " - " + 
	                     info.getPhoneNumber() + " - " + 
	                     info.getBirthDate() + " - " + 
	                     info.getCreationDate() + " - " + 
	                     info.getGender() + " - " + 
	                     info.getEmail();*/
	        usuariTrobat = nom; // + " - " + nif;
	    }

	    Map<String, Object> result = new HashMap<String, Object>();
	    result.put("exists", info != null);
	    result.put("data", usuariTrobat);

	    Gson g = new Gson();
	    String jsonResult = g.toJson(result);

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(jsonResult);
	    out.flush();
	}

	@RequestMapping(value = { "/permisos" }, method = RequestMethod.POST)
	public String permisos(HttpServletRequest request) {
		// Esta funcion recoge el parametro usr del request y redirige al listado de
		// PinfoDatas de ese usuario.

		String user = request.getParameter("username");
		log.info("user: ]" + user + "[");

		request.getSession().setAttribute("user", user);

		return "redirect:/operador/pinfoData/list";
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		String user = (String) request.getSession().getAttribute("user");
		Where wUsuari = PinfoDataFields.USUARIID.equal(user);

		return Where.AND(wUsuari, super.getAdditionalCondition(request));
	}
}
