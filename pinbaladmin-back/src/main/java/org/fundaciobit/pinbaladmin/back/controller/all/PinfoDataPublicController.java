package org.fundaciobit.pinbaladmin.back.controller.all;

import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.pinbaladmin.back.controller.FileDownloadController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.PinfoDataController;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataForm;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.EntitatServeiLogicService;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaEJB.PinfoDataFull;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaService;
import org.fundaciobit.pinbaladmin.logic.PinfoLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager;
import org.fundaciobit.pinbaladmin.logic.utils.Responsable;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields;
import org.fundaciobit.pinbaladmin.model.fields.PinfoFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.SearchUsersResult;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

import es.caib.pinbal.client.comu.LogLevel;
import es.caib.pinbal.client.comu.Page;
import es.caib.pinbal.client.usuaris.FiltreUsuaris;
import es.caib.pinbal.client.usuaris.UsuariClient;
import es.caib.pinbal.client.usuaris.UsuariEntitat;

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

	@EJB(mappedName = PinfoLogicaService.JNDI_NAME)
	protected PinfoLogicaService pinfoLogicEjb;

	@EJB(mappedName = PinfoDataLogicaService.JNDI_NAME)
	protected PinfoDataLogicaService pinfoDataLogicaEjb;

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;

	@EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
	protected SolicitudServeiLogicaService solicitudServeiLogicaEjb;

	@EJB(mappedName = ServeiLogicaService.JNDI_NAME)
	protected ServeiLogicaService serveiLogicaEjb;
	
	@EJB(mappedName = EntitatServeiLogicService.JNDI_NAME)
	protected EntitatServeiLogicService entitatLogicaEjb;
	
	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerLogicaEjb;

	public static final String ALTA_BAIXA = "alta_baixa";
//	public final Long PINFODATA_ALTA = 1L;
//	public final Long PINFODATA_BAIXA = 0L;
	
	public final Long PINFOID_DEFAULT = 1169l;
	public final Long INCIDENCIAID_DEFAULT = 50275l;
	
	public static final String RESPONSABLE = "responsable";
	public final String LLISTA_RESPONSABLES = "llistaResponsables";
	
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
		Long pinfoID = (Long) request.getSession().getAttribute("pinfoID");
		
		if (id == null) {
			id = INCIDENCIAID_DEFAULT;
		}
		
		if (pinfoID == null) {
			pinfoID = pinfoLogicEjb.executeQueryOne(PinfoFields.PINFOID, PinfoFields.INCIDENCIAID.equal(id));
//			pinfoID = 1005L;
		}
		
		IncidenciaTecnica inc = incidenciaTecnicaLogicaEjb.findByPrimaryKey(id);
		mav.addObject("incidencia", inc);

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

		log.info("getPinfoDataFilterForm():: pinfoID: " + pinfoID);
		if (pinfoID != null) {
			PinfoDataFull pinfoDataFull = pinfoDataLogicaEjb.getEstructuraUsuarisProcedimentServeis(pinfoID);
			mav.addObject("pinfoDataFull", pinfoDataFull);
		}

		return filterForm;
	}

	@RequestMapping(value = "/generaPdf", method = RequestMethod.GET)
	public ModelAndView generaPdf(HttpServletRequest request) throws Exception {
		log.info("generaPdf");

		ModelAndView mav = new ModelAndView("showPinfoPdf");

		Long pinfoID = (Long) request.getSession().getAttribute("pinfoID");
		log.info("pinfoID: " + pinfoID);
		Pinfo pinfo = pinfoLogicEjb.findByPrimaryKey(pinfoID);
		
//		Responsable responsable = (Responsable) request.getSession().getAttribute(RESPONSABLE);
		
		Long fitxerID = pinfoLogicEjb.generarPinfoPDF(pinfoID);
		log.info("fitxerID: " + fitxerID);
		
		Fitxer f = fitxerLogicaEjb.findByPrimaryKey(fitxerID);

		//Afegor el nom del destinatari enviant a PortaFIB per guardar-ho a l'event que es crea.
		//afegirEventPinfoEnviat(incidenciaID, senderUsername, msg);
		
		String urlPinfoPDF = "/pinbaladmin" + FileDownloadController.fileUrl(f);
		String urlFirmarPinfo =  Configuracio.getAppBackUrl() + PinfoPublicController.CONTEXT_WEB  + "/enviarPinfoPortaFIB/" +pinfoID;

		mav.addObject("urlPinfoPDF", urlPinfoPDF);
		mav.addObject("urlFirmarPinfo", urlFirmarPinfo);
		
		mav.addObject("pinfo", pinfo);
		
		return mav;
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

//	@Override
//	public List<PinfoData> executeSelect(ITableManager<PinfoData, Long> ejb, Where where, OrderBy[] orderBy,
//			Integer itemsPerPage, int inici) throws I18NException {
//		log.info("pasa por executeSelect");
//
//		OrderBy orderByServ = new OrderBy(PinfoDataFields.SERVEIID, OrderType.ASC);
//		OrderBy orderByProc = new OrderBy(PinfoDataFields.PROCEDIMENTID, OrderType.ASC);
//		OrderBy orderByUser = new OrderBy(PinfoDataFields.USUARIID, OrderType.ASC);
//
//		OrderBy[] myOrderBy = { orderByUser, orderByProc, orderByServ };
//
//		OrderBy[] newOrderBy;
//		
//		if (orderBy == null) {
//			newOrderBy = myOrderBy;
//		} else {
//			newOrderBy = new OrderBy[orderBy.length + myOrderBy.length];
//
//			for (int i = 0; i < orderBy.length; i++) {
//				newOrderBy[i] = orderBy[i];
//			}
//			for (int i = 0; i < myOrderBy.length; i++) {
//				newOrderBy[orderBy.length + i] = myOrderBy[i];
//			}
//		}
//
//		return super.executeSelect(ejb, where, newOrderBy, itemsPerPage, inici);
//	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		Where where = super.getAdditionalCondition(request);

		Long incidenciaID = (Long) request.getSession().getAttribute("incidenciaId");

		log.info("getAdditionalCondition():: incidenciaID  " + incidenciaID);
		if (incidenciaID == null) {
			incidenciaID = INCIDENCIAID_DEFAULT;
		}

		if (incidenciaID != null) {

			List<Pinfo> pinfos = pinfoLogicEjb.select(PinfoFields.INCIDENCIAID.equal(incidenciaID));
			log.info("getAdditionalCondition():: pinfos: " + pinfos.size());
			if (pinfos.size() == 1) {
				Long pinfoID = pinfos.get(0).getPinfoID();
				log.info("pinfoID: " + pinfoID);
				request.getSession().setAttribute("pinfoID", pinfoID);
				request.getSession().setAttribute("incidenciaId", incidenciaID);

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
		__tmp.add(new StringKeyValue(String.valueOf(Constants.PINFO_ALTA), "Alta"));
		__tmp.add(new StringKeyValue(String.valueOf(Constants.PINFO_BAIXA), "Baixa"));

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
		Long alta_baixa = (Long) request.getSession().getAttribute(ALTA_BAIXA);
		
		for (String u : usuaris) {
			
//	        try {
//	        	log.info("Intentarem obtenir dades d'estructura organitzativa de l'usuari: " + u);
//	        	boolean debug = true;
//	        	IEstructuraOrganitzativaPlugin plugin = pinfoDataLogicaEjb.getPluginEstructuraOrganitzativa();
//
////	        	String president = plugin.getGerentPresidentName();
////	        	log.info("Nom del presidente: " + president);
//	        	String codiDG = plugin.getCodeDepartamentDireccioGeneral(u);
//				String usernameDG = plugin.getCapDepartamentDirectorGeneralUsername(u);
//				String nomDG = plugin.getCapDepartamentDirectorGeneralUsername(u);
//
//				log.info("Director general de " + u + ": " + codiDG + " - "+ nomDG + " (" + usernameDG + ")");
//				
//				
////				String capDepartamentDG = plugin.getCapDepartamentDirectorGeneralName(u);
////				log.info("El capDepartamentDG  es: " + capDepartamentDG  );
//
//	        } catch (Exception e) {
//				log.error("NO HA ANAT BE: " + e.getMessage(), e);
//				HtmlUtils.saveMessageError(request, "NO HA ANAT BE: " + e.getMessage());
//			}
			
			
			for (String solSer : solicitudServeis) {
				SolicitudServei ss = solicitudServeiLogicaEjb.findByPrimaryKey(Long.parseLong(solSer));
				Long procedimentID = ss.getSolicitudID();
				Long serveiID = ss.getServeiID();

				log.info("user: " + u + " procedimentID: " + procedimentID + " serveiID: " + serveiID);
				PinfoDataJPA pinfoDataJPA = new PinfoDataJPA(pinfoID, estat, u, procedimentID, serveiID, alta_baixa);

				PinfoData pinfoData = pinfoDataLogicaEjb.create(pinfoDataJPA);
				log.info("pinfoData: " + pinfoData.getPinfodataID());
			}
		}
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

	final String baseUrl = Configuracio.getApiPinbalClientUrl();
	final String username = Configuracio.getApiPinbalClientUsername();
	final String password = Configuracio.getApiPinbalClientPassword();
	final LogLevel logLevel = LogLevel.INFO;

	UsuariClient usuariClient = new UsuariClient(baseUrl, username, password, logLevel);
	
//	@RequestMapping(value = { "/jsonUsuaris" }, method = RequestMethod.GET)
//	public void obtenirJsonUsuaris(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
////		String param = (String) request.getParameter("query");
////		log.info("param: ]" + param + "[");
//
//		String nom = (String) request.getParameter("nom");
//		log.info("nom: ]" + nom + "[");
//		String nif = (String) request.getParameter("nif");
//		log.info("nif: ]" + nif + "[");
//
////		final String baseUrl = Configuracio.getApiPinbalClientUrl();
////		final String username = Configuracio.getApiPinbalClientUsername();
////		final String password = Configuracio.getApiPinbalClientPassword();
////		final LogLevel logLevel = LogLevel.INFO;
////
////		log.info("Creant Clients");
////		UsuariClient usuariClient = new UsuariClient(baseUrl, username, password, logLevel);
////		log.info("Clients creats");
//
//		Long pinfoID = (Long) request.getSession().getAttribute("pinfoID");
//		final String ENTITAT_CIF = pinfoLogicEjb.executeQueryOne(PinfoFields.ENTITAT, PinfoFields.PINFOID.equal(pinfoID));
//		log.info("ENTITAT_CIF: " + ENTITAT_CIF);
//
//		final int page = 0;
//		final int size = 10;
//		String sort = null;
//		FiltreUsuaris filter = new FiltreUsuaris();
//		filter.setIsDelegat(true);
//		
//		
//		try {
//			
//			filter.setNom(nom);
//			filter.setNif(nif);
//			
//			Page<UsuariEntitat> usuariPage = usuariClient.getUsuaris(ENTITAT_CIF, filter, page, size, sort);
//			log.info("Elems: " + usuariPage.getTotalElements());
//			log.info("Pages: " + usuariPage.getTotalPages());
//			log.info("ContentSize: " + usuariPage.getContent().size());
//			
//			log.info(usuariPage.getContent());
//			
////			usuariPage.getContent().get(0).get
//			
//			
//			Gson g = new Gson();
//			String usuarisJson = g.toJson(usuariPage.getContent());
//
//			
//			 log.info(usuarisJson );
//
//			PrintWriter out = response.getWriter();
//			response.setContentType("application/json");
//			response.setCharacterEncoding("UTF-8");
//			out.print(usuarisJson);
//			out.flush();
//		}catch (ClientHandlerException | UniformInterfaceException e) {
//            log.error("Error obtenirJsonUsuaris: " + e.getMessage());
//            
//            PrintWriter out = response.getWriter();
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            out.print("[]");
//            out.flush();
//        }
//		
//
////		usuariPage = usuariClient.getUsuaris(ENTITAT_CIF, filter, page, usuariPage.getTotalElements()-1, sort);
//		
////		List<UsuariEntitat> llistatFiltrar = new java.util.ArrayList<UsuariEntitat>();
////		for (UsuariEntitat usuari : usuariPage.getContent()) {
////			if (usuari.getNif().contains(param) || usuari.getNom().contains(param)) {
////				llistatFiltrar.add(usuari);
////			}
////		}
//		
//		
//		
//	}

	@RequestMapping(value = { "/jsonUsuaris" }, method = RequestMethod.GET)
	public void obtenirJsonUsuaris(HttpServletRequest request, HttpServletResponse response) throws Exception {

//		String param = (String) request.getParameter("query");
//		log.info("param: ]" + param + "[");

		String nom = (String) request.getParameter("nom");
		log.info("nom: ]" + nom + "[");
		String nif = (String) request.getParameter("nif");
		log.info("nif: ]" + nif + "[");

		Long pinfoID = (Long) request.getSession().getAttribute("pinfoID");
		Pinfo pinfo = pinfoLogicEjb.findByPrimaryKey(pinfoID);

		final boolean debug = false;
    	boolean caib = true;
		IUserInformationPlugin pluginUserInfo =  PinbalAdminPluginsManager.getUserInformationPluginInstance(debug, caib);
		
		UserInfo solicitantInfo = null;
		solicitantInfo = pluginUserInfo.getUserInfoByAdministrationID(pinfo.getSolicitantNIF());
		
		String soliUsername = solicitantInfo.getUsername();

		IEstructuraOrganitzativaPlugin pluginEstrOrg = pinfoDataLogicaEjb.getPluginEstructuraOrganitzativa();
		String codiDepartament = pluginEstrOrg.getCodeDepartamentDireccioGeneral(soliUsername);
		log.info("El codiDepartament de " + soliUsername + " es " + codiDepartament);
		
		SearchUsersResult searchUsuarisDepartament = pluginUserInfo.getUsersByDepartment(codiDepartament);
		List<UserInfo> usuarisDepartament = searchUsuarisDepartament.getUsers();

		final String param = nom != null ? nom.trim().toLowerCase() : "";

//		final String toCompare = u.getFullName()
		
//		List<UserInfo> usuarisFiltrats = usuarisDepartament.stream()
//		    .filter(u -> param.isEmpty()
//		        || u.getFullName().toLowerCase().contains(param)
//		        || u.getAdministrationID().toLowerCase().contains(param)
//		        || u.getUsername().toLowerCase().contains(param)
//		    )
//		    .collect(Collectors.toList());

		List<UserInfo> usuarisFiltrats = usuarisDepartament.stream()
			    .filter(u -> {
			        if (param == null || param.isEmpty()) {
			            return true; // No hay filtro, incluir todos
			        }
			        String paramLower = param.toLowerCase();

			        // Usamos valores vacíos como fallback para evitar NullPointerException
			        String fullName = u.getFullName() != null ? u.getFullName().toLowerCase() : "";
			        String adminId = u.getAdministrationID() != null ? u.getAdministrationID().toLowerCase() : "";
			        String username = u.getUsername() != null ? u.getUsername().toLowerCase() : "";

			        return fullName.contains(paramLower)
			            || adminId.contains(paramLower)
			            || username.contains(paramLower);
			    })
			    .collect(Collectors.toList());

		
		try {
			Gson g = new Gson();
			String usuarisJson = g.toJson(usuarisFiltrats);

			log.info(usuarisJson);

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(usuarisJson);
			out.flush();
		} catch (Exception e) {
			log.error("Error obtenirJsonUsuaris: " + e.getMessage());

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print("[]");
			out.flush();
		}
	}

	@RequestMapping(value = { "/jsonProcediments" }, method = RequestMethod.GET)
	public void obtenirJsonProcediments(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = (String) request.getParameter("query");
		log.info("param: ]" + param + "[");

		Where wProcediment = Where.OR(SolicitudFields.PROCEDIMENTCODI.like("%" + param + "%"),
				SolicitudFields.PROCEDIMENTNOM.like("%" + param + "%"));
		
		Long incidenciaId = (Long) request.getSession().getAttribute("incidenciaId");
		log.info("incidenciaId: " + incidenciaId);
		
		Long organID = incidenciaTecnicaLogicaEjb.executeQueryOne(IncidenciaTecnicaFields.ORGANID,
				IncidenciaTecnicaFields.INCIDENCIATECNICAID.equal(incidenciaId));
		log.info("organID: " + organID);

		Where wLocal = SolicitudFields.ORGANID.equal(organID);

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
	
	
	public List<StringKeyValue> getReferenceListForServeiID(HttpServletRequest request, ModelAndView mav, Where where)
			throws I18NException {
		
		List<StringKeyValue> tmp = new java.util.ArrayList<StringKeyValue>();
		
		List<Servei> serveis = serveiLogicaEjb.select(where);
		
		for (Servei servei : serveis) {
            String key =String.valueOf(servei.getServeiID());
//            EntitatServei entitatServei = entitatLogicaEjb.findByPrimaryKey(servei.getEntitatServeiID());
//            String value = "(" + entitatServei.getNom()+ ") " + servei.getNom();
            String value = servei.getCodi();
            
            tmp.add(new StringKeyValue(key, value));
		}
		
		return tmp;
	}
	
	
	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, PinfoDataFilterForm filterForm,
			List<PinfoData> list) throws I18NException {

		super.postList(request, mav, filterForm, list);
		filterForm.getAdditionalButtons().clear();
		
		if (list.size() > 0) {
			filterForm.addAdditionalButton(new AdditionalButton("fas fa-user-tie", "tramitpinfo.responsable",
			getContextWeb() + "/seleccionarResponsable", AdditionalButtonStyle.PRIMARY));
		}

		//Afegir botó crear alta, i per crear baixa.
		filterForm.addAdditionalButton(new AdditionalButton("fas fa-plus", "tramitpinfo.baixa",
				getContextWeb() + "/crearbaixa", AdditionalButtonStyle.DANGER));
		
		filterForm.addAdditionalButton(new AdditionalButton("fas fa-plus", "tramitpinfo.alta",
				getContextWeb() + "/crearalta", AdditionalButtonStyle.SUCCESS));
		
	}
	
	@RequestMapping(value = "/seleccionarResponsable", method = RequestMethod.GET)
	public ModelAndView seleccionarResponsableGet(HttpServletRequest request) throws I18NException {
		log.info("mostrarResponsables GET");
		
		ModelAndView mav = new ModelAndView("llistaResponsables");
		
		//EJB obtenir els responsables
		Long pinfoID = (Long) request.getSession().getAttribute("pinfoID");
		log.info("pinfoID: " + pinfoID);
		
		//Obtenir els procediments dels PinfoDatas:
		List<Responsable> responsablesList = getLlistaResponsablesProcediments(pinfoID);
		request.getSession().setAttribute(LLISTA_RESPONSABLES, responsablesList);
		mav.addObject("responsables", responsablesList);
		return mav;
	}
	
	private List<Responsable> getLlistaResponsablesProcediments(Long pinfoID) throws I18NException {

		List<Responsable> responsablesList = new java.util.ArrayList<Responsable>();

		Pinfo pinfo = pinfoLogicEjb.findByPrimaryKey(pinfoID);

		UserInfo solicitantInfo = null;
		final boolean debug = false;
    	boolean caib = true;
		IUserInformationPlugin pluginUserInfo =  PinbalAdminPluginsManager.getUserInformationPluginInstance(debug, caib);

		try {
			solicitantInfo = pluginUserInfo.getUserInfoByAdministrationID(pinfo.getSolicitantNIF());
		} catch (Exception e) {
			log.error("No hem trobat informació del solicitant (" + pinfo.getSolicitantNIF()
					+ ") a Plugin de UserInformation: " + e.getMessage());
		}

		IEstructuraOrganitzativaPlugin pluginEstrOrg = pinfoDataLogicaEjb.getPluginEstructuraOrganitzativa();
		String username = solicitantInfo.getUsername();

		try {
			String usernameDG = pluginEstrOrg.getCapDepartamentDirectorGeneralUsername(username);
			//usernameDG = "atrobat";//u81599
			usernameDG = "u81599";//atrobat
			log.info("El Director General de " + username + " es " + usernameDG);

			afegirResponsableAmbUsername(usernameDG, "Director General", responsablesList, pluginUserInfo);

		} catch (Exception e) {
			log.error("No hem trobat el Director General: " + e.getMessage());
		}

		try {
			String usernameSG = pluginEstrOrg.getSecretariUsername(username);
			//usernameSG = "acuevas";//u109105
			usernameSG = "u109105";//acuevas
			
			
			log.info("El Secretari General de " + username + " es " + usernameSG);

			afegirResponsableAmbUsername(usernameSG, "Secretari", responsablesList, pluginUserInfo);
		} catch (Exception e) {
			log.error("No hem trobat el Secretari: " + e.getMessage());
		}
		return responsablesList;
	}
	
	private void afegirResponsableAmbUsername(String username, String carrec, List<Responsable> responsablesList,
			 IUserInformationPlugin plugin) throws Exception {
		if (username == null) {
			return;
		}
		
		UserInfo info = plugin.getUserInfoByUserName(username);

		if (info != null) {
			String nif = info.getAdministrationID();
			String nom = info.getName();
			String ape1 = info.getSurname1();
			String ape2 = info.getSurname2();
			String telefon = info.getPhoneNumber();
			String mail = info.getEmail();
			String nomOcult = info.getFullName();

			log.info(nif + " - " + nom + " " + ape1 + " " + ape2 + " - " + carrec + " - " + telefon + " - " + mail
					+ " - " + nomOcult);

			Responsable responsable = new Responsable(nif, nom, ape1, ape2, carrec, telefon, mail, nomOcult);

			responsablesList.add(responsable);
		} else {
			log.error("No hem trobat Info del " + carrec + " (" + username + ")");
		}
	}

	@RequestMapping(value = "/seleccionarResponsable", method = RequestMethod.POST)
	public String seleccionarResponsablePost(HttpServletRequest request) throws I18NException {
		log.info("seleccionarResponsable POST");
		
		String selecionat = request.getParameter("responsable");
		log.info("selecionat: " + selecionat);
		
		List<Responsable> responsablesList = (List<Responsable>) request.getSession().getAttribute(LLISTA_RESPONSABLES);
		Responsable responsable = null;

		if (selecionat.equals("otro")) {
			String nomComplet = request.getParameter("responsable-otro-nom");
			String dni = request.getParameter("responsable-otro-dni");

			log.info("Otro: nif: " + dni +  " nomComplet: " + nomComplet);
			
			responsable = new Responsable(dni, nomComplet);
			responsable.setNif(dni);
		} else {
			for (Responsable res : responsablesList) {
				if (res.getNif().equals(selecionat)) {
					responsable = res;
					break;
				}
			}
		}		
		String nifResponsable = responsable.getNif().toUpperCase();
		log.info("responsable: " + nifResponsable);
		
		//Guardar responsable a destinatariNIF del Pinfo, i redireccionar a PDF
		Long pinfoID = (Long) request.getSession().getAttribute("pinfoID");
		Pinfo pinfo = pinfoLogicEjb.findByPrimaryKey(pinfoID);
		pinfo.setDestinatariNIF(nifResponsable);
		pinfo.setDestinatariNom(responsable.getNomOcult());
		pinfoLogicEjb.update(pinfo);

//		request.getSession().setAttribute(RESPONSABLE, responsable);
		
		return "redirect:" + CONTEXT_WEB + "/generaPdf";
	}
	
	
	
	@RequestMapping(value = "/crearalta")
	public String crearAlta(HttpServletRequest request, ModelAndView mav) throws I18NException {
		log.info("crearAlta");
		
		//Redirigir a new amb method igual a alta.
		request.getSession().setAttribute(ALTA_BAIXA, Constants.PINFO_ALTA);
		return "redirect:" + CONTEXT_WEB + "/new";
	}
	
	@RequestMapping(value = "/crearbaixa")
	public String crearBaixa(HttpServletRequest request, ModelAndView mav) throws I18NException {
		log.info("crearBaixa");

		// Redirigir a new amb method igual a baixa.
		request.getSession().setAttribute(ALTA_BAIXA, Constants.PINFO_BAIXA);
		return "redirect:" + CONTEXT_WEB + "/new";
	}
	
	public class UsuariData{
		private String username;
		private String nom;
		private String nif;
		
		public UsuariData(String username, String nom, String nif) {
			this.username = username;
			this.nom = nom;
			this.nif = nif;
		}
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
		
		public String getNom() {
			return nom;
		}
		
		public void setNom(String nom) {
			this.nom = nom;
		}
		
		public String getNif() {
			return nif;
		}
		
		public void setNif(String nif) {
			this.nif = nif;
		}
	}
	
	@RequestMapping(value = { "/validarUsuariPluginUserInformation" }, method = RequestMethod.GET)
	public void validarUsuariPluginUserInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user = (String) request.getParameter("user");
		log.info("user: ]" + user + "[");

		//Cridada a plugn de UserInformation(user)
		
//        IEstructuraOrganitzativaPlugin instance = pinfoDataLogicaEjb.getPluginEstructuraOrganitzativa();
//		String cap = instance.getCapAreaConsellerName(user);
//		
//		log.info("El cap de " + user + " es " + cap);
        
		UsuariData usuari;
		
		boolean debug = false;
    	boolean caib = true;

		IUserInformationPlugin plugin =  PinbalAdminPluginsManager.getUserInformationPluginInstance(debug, caib);
        UserInfo info = plugin.getUserInfoByUserName(user);
        
        if (info == null) {
        	usuari = null;
		} else {
			String nom = info.getFullName() + " - " + info.getAdministrationID();
			String nif = ""
//			+ cap + " - "
			+ info.getAddress() + " - " 
			+ info.getCompany() + " - " 
			+ info.getCompanyArea() + " - " 
			+ info.getCompanyDepartment() + " - " 
			+ info.getDir3() + " - " 
			+ info.getId() + " - " 
			+ info.getNotes() + " - " 
			+ info.getName() + " - " 
			+ info.getPhoneNumber() + " - " 
			+ info.getBirthDate() + " - " 
			+ info.getCreationDate() + " - " 
			+ info.getGender() + " - " 
			+ info.getEmail();

			usuari = new UsuariData(user, nom, nif);
		}

		Gson g = new Gson();
		String procedimentsJsonString = g.toJson(usuari);

		// log.info(procedimentsJsonString );

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(procedimentsJsonString);
		out.flush();
	}

    
    
    
//    public static final String LOGIN_PLUGIN_KEY_estructuraOrganitzativa = Constants.PINBALADMIN_PROPERTY_BASE + "pluginsib.estructuraorganitzativa.ldapcaib";
//
//    public static IEstructuraOrganitzativaPlugin estructuraOrganitzativaPlugin = null;
//    
//    public static IEstructuraOrganitzativaPlugin getPluginEstructuraOrganitzativa(boolean debug) throws I18NException{
//    	
//        if (estructuraOrganitzativaPlugin == null) {
//        	 Properties propTmp = Configuracio.getSystemAndFileProperties();
//
// 			if (debug) {
// 				log.info("Propietats de sistema i fitxer de configuració:");
// 				Set<Object> set = propTmp.keySet();
// 				for (Object object : set) {
// 					String key = (String) object;
// 					String value = propTmp.getProperty(key);
// 					log.info(key + ": " + value);
// 				}
// 			}
//             
//             String className = propTmp.getProperty(LOGIN_PLUGIN_KEY_estructuraOrganitzativa + ".class");
//             
//             log.info("className: " + className);
//             Object pluginInstance = PluginsManager.instancePluginByClassName(className,
//                     Constants.PINBALADMIN_PROPERTY_BASE, propTmp);
//
//             if (pluginInstance == null) {
//                 throw new I18NException("plugin.donotinstantiateplugin.userinfo");
//             }
//             estructuraOrganitzativaPlugin = (IEstructuraOrganitzativaPlugin) pluginInstance;
//        	
//        }else {
//			log.info("estructuraOrganitzativaPlugin ja existeix. " + estructuraOrganitzativaPlugin.getClass().getName());
//        }
//        return estructuraOrganitzativaPlugin;
//			
//    }
}
