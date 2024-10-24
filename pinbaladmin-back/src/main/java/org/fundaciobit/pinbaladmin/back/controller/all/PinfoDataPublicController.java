package org.fundaciobit.pinbaladmin.back.controller.all;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitIOperadorController.Item;
import org.fundaciobit.pinbaladmin.back.controller.webdb.PinfoDataController;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.PINFOLogicaService;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.PINFO;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.PINFOFields;
import org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

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

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;

	@EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
	protected SolicitudServeiLogicaService solicitudServeiLogicaEjb;

	@EJB(mappedName = ServeiLogicaService.JNDI_NAME)
	protected ServeiLogicaService serveiLogicaEjb;

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

//		Long pinfoID = (Long) request.getSession().getAttribute("pinfoID");
//		pinfoData.setPinfoID(pinfoID);

		form.addHiddenField(PinfoDataFields.ESTAT);
		pinfoData.setEstat(0L);

		pinfoData.setAlta(1L);

		return form;
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		Where where = super.getAdditionalCondition(request);

		Long incidenciaID = (Long) request.getSession().getAttribute("incidenciaId");

//		log.info("incidenciaID 1: " + incidenciaID);
		if (incidenciaID == null) {
			incidenciaID = 50111L;
		}

		if (incidenciaID != null) {

			List<PINFO> pinfos = pinfoLogicEjb.select(PINFOFields.INCIDENCIAID.equal(incidenciaID));
//			log.info("pinfos: " + pinfos.size());
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

	@RequestMapping(value = "/procesarPermisos")
	public String procesarPermisos(HttpServletRequest request, ModelAndView mav) throws I18NException {
		log.info("procesarPermisos");

		String user = request.getParameter("usuaris");
//		String procedimentIDStr = request.getParameter("procediments");
		String solicitudServeisStr = request.getParameter("solicitudServeis");

		String[] usuaris = user.split(",");
//		String[] procediments = procedimentIDStr.split(",");
		String[] solicitudServeis = solicitudServeisStr.split(",");

		Long pinfoID = (Long) request.getSession().getAttribute("pinfoID");
		Long estat = 0L; // Creant
		Long alta = 1L; // Alta

		for (String u : usuaris) {
			for (String solSer : solicitudServeis) {
				SolicitudServei ss = solicitudServeiLogicaEjb.findByPrimaryKey(Long.parseLong(solSer));
				Long procedimentID = ss.getSolicitudID();
				Long serveiID = ss.getServeiID();

				log.info("user: " + u + " procedimentID: " + procedimentID + " serveiID: " + serveiID);
				PinfoDataJPA pinfoDataJPA = new PinfoDataJPA(pinfoID, estat, u, procedimentID, serveiID, alta);

				PinfoData pinfoData = pinfoDataLogicaEjb.create(pinfoDataJPA);
				log.info("pinfoData: " + pinfoData.getPinfodataID());
			}
		}

//		log.info("user: " + user);
//		log.info("procedimentID: " + procedimentIDStr);
//		log.info("serveiID: " + serveiIDStr);

//		Long procedimentID = Long.parseLong(procedimentIDStr);
//		Long serveiID = Long.parseLong(serveiIDStr);
//		Long pinfoID = (Long) request.getSession().getAttribute("pinfoID");
//
//		Long estat = 0L; //Creant
//		Long alta = 1L; //Alta
//		
//		PinfoDataJPA pinfoDataJPA = new PinfoDataJPA(pinfoID, estat, user, procedimentID, serveiID, alta);
//		
//		PinfoData pinfoData = pinfoDataLogicaEjb.create(pinfoDataJPA);
//		log.info("pinfoData: " + pinfoData.getPinfodataID());

		return "redirect:" + CONTEXT_WEB + "/list";
	}

	public class Item {
		private String id;
		private String key;
		private String value;

		public Item(String id, String key, String value) {
			this.setId(id);
			this.setKey(key);
			this.setValue(value);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	@RequestMapping(value = { "/jsonProcediments" }, method = RequestMethod.GET)
	public void obtenirJsonProcediments(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = (String) request.getParameter("query");
		log.info("param: ]" + param + "[");

		Where wProcediment = Where.OR(SolicitudFields.PROCEDIMENTCODI.like("%" + param + "%"),
				SolicitudFields.PROCEDIMENTNOM.like("%" + param + "%"));
		Where wLocal = SolicitudFields.ORGANID.isNotNull();

		List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wProcediment, wLocal));

		List<Item> items = new java.util.ArrayList<Item>();

		// log.info("solicituds: " + solicituds.size());

		for (Solicitud soli : solicituds) {
			String id = String.valueOf(soli.getSolicitudID());
			String key = soli.getProcedimentCodi();
			String value = soli.getProcedimentNom();

			Item item = new Item(id, key, value);
			items.add(item);
		}

		Gson g = new Gson();
		String procedimentsJsonString = g.toJson(items);

		// log.info(procedimentsJsonString );

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(procedimentsJsonString);
		out.flush();
	}

	@RequestMapping(value = { "/jsonServeisProcediment" }, method = RequestMethod.GET)
	public void obtenirServeisDelProcediment(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String procedimentIDStr = (String) request.getParameter("procedimentID");
		Long procedimentID = Long.parseLong(procedimentIDStr);
		log.info("procedimentID: " + procedimentID);

		List<SolicitudServei> solicitudServeis = solicitudServeiLogicaEjb
				.select(SolicitudServeiFields.SOLICITUDID.equal(procedimentID));

		List<Item> items = new java.util.ArrayList<Item>();

		for (SolicitudServei ss : solicitudServeis) {
			Long serveiID = ss.getServeiID();
			Servei servei = serveiLogicaEjb.findByPrimaryKey(serveiID);

//			if (servei.getEstatServeiID() == Constants.ESTAT_SOLICITUD_SERVEI_AUTORITZAT) {
				String id = String.valueOf(ss.getId());
				String key = servei.getCodi();
				String value = servei.getNom();

				Item item = new Item(id, key, value);
				items.add(item);
//			}
		}

		Gson g = new Gson();
		String serveisJsonString = g.toJson(items);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(serveisJsonString);
		out.flush();
	}
}
