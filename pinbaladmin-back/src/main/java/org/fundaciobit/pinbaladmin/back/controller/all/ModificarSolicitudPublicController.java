package org.fundaciobit.pinbaladmin.back.controller.all;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.FileDownloadController;
import org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesFormManager;
import org.fundaciobit.pinbaladmin.back.controller.webdb.ModificacioSolicitudController;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSolicitudForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.DocumentLogicaService;
import org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.ModificacioSolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.ModificacioSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ModificarSolicitudPublicController.CONTEXT_WEB)
@SessionAttributes(types = { ModificacioSolicitudForm.class, ModificacioSolicitudFilterForm.class })
public class ModificarSolicitudPublicController extends ModificacioSolicitudController {

	public static final String CONTEXT_WEB = "/public/modificarsolicitud";
	public static final String MOD_SOLI_ID = "modsoliID";
	public static final String SOLICITUD_ID = "solicitudID";

	public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

	@EJB(mappedName = ModificacioSolicitudLogicaService.JNDI_NAME)
	protected ModificacioSolicitudLogicaService modificacioSolicitudLogicaEjb;

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;

	@EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
	protected SolicitudServeiLogicaService solicitudServeiLogicaEjb;

	@EJB(mappedName = ServeiLogicaService.JNDI_NAME)
	protected ServeiLogicaService serveiLogicaEjb;

	@EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
	protected DocumentSolicitudLogicaService documentSolicitudLogicaEjb;

	@EJB(mappedName = DocumentLogicaService.JNDI_NAME)
	protected DocumentLogicaService documentLogicaEjb;

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerLogicEjb;

	@EJB(mappedName = OrganLogicaService.JNDI_NAME)
	protected OrganLogicaService organLogicEjb;

	@EJB(mappedName = EventLogicaService.JNDI_NAME)
	protected EventLogicaService eventLogicaEjb;

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerPublicLogicaEjb;

	@Override
	protected FilesFormManager<Fitxer> getFilesFormManager() {
		return new PinbalAdminFilesFormManager(fitxerPublicLogicaEjb);
	}

	@RequestMapping(value = "/new/{token}", method = RequestMethod.GET)
	public String obtenirDadesFitxerToken(HttpServletRequest request, HttpServletRequest response,
			@PathVariable("token") java.lang.String token) {

		log.info("obtenirDadesFitxerToken token: " + token);
		File file = new File(FileSystemManager.getFilesPath(), token + ".front");

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			log.error("An error occurred." + e.getMessage(), e);
			e.printStackTrace();
		}

		request.getSession().setAttribute("properties", properties);
		request.getSession().setAttribute("token", token);
		return "redirect:" + CONTEXT_WEB + "/seleccionarProcediment";
	}

	@RequestMapping(value = "/seleccionarProcediment", method = RequestMethod.GET)
	public ModelAndView seleccionarProcedimentGet(HttpServletRequest request, HttpServletResponse response)
			throws I18NException {
		log.info("Entra a seleccionarProcediment GET");
		ModelAndView mav = new ModelAndView("seleccionarProcediment");

		Properties properties = (Properties) request.getSession().getAttribute("properties");
		String nomComplet = properties.getProperty("Nom") + " " + properties.getProperty("Cognom1") + " "
				+ properties.getProperty("Cognom2");

		String usuariNIF = properties.getProperty("NIF");
		String username = properties.getProperty("Username");

		request.getSession().setAttribute("usuariNIF", usuariNIF);
		request.getSession().setAttribute("usuariNom", nomComplet);
		request.getSession().setAttribute("usuariUsername", username);

		return mav;
	}

	@Override
	public String getTileForm() {
		return "modificacioSolicitudFormPublic";
	}

	@Override
	public ModificacioSolicitudForm getModificacioSolicitudForm(ModificacioSolicitudJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {

		ModificacioSolicitudForm form = super.getModificacioSolicitudForm(_jpa, __isView, request, mav);

		log.info("Esteim a ModificacioSolicitudForm");

		if (!form.isNou() && !__isView) {
			form.addHiddenField(ModificacioSolicitudFields.SOLICITUDID);
			form.addHiddenField(ModificacioSolicitudFields.NOTES);
			form.addHiddenField(ModificacioSolicitudFields.SOLICITANTNOM);
			form.addHiddenField(ModificacioSolicitudFields.SOLICITANTNIF);
			form.addHiddenField(ModificacioSolicitudFields.SOLICITANTUSERNAME);
			form.addHiddenField(ModificacioSolicitudFields.SOLICITANTMAIL);
			form.addHiddenField(ModificacioSolicitudFields.ESTATMODIFICACIO);

			form.setTitleCode("=");
			form.setAttachedAdditionalJspCode(true);

			form.setDeleteButtonVisible(false);

			Long solicitudID = form.getModificacioSolicitud().getSolicitudID();

			List<ServeiInfo> serveis = getServeisSolicitud(solicitudID);
			mav.addObject("serveis", serveis);

			Long modsoliID = form.getModificacioSolicitud().getModsoliID();
			log.info("modSoli: " + modsoliID);
			request.getSession().setAttribute(MOD_SOLI_ID, modsoliID);
			request.getSession().setAttribute(SOLICITUD_ID, solicitudID);
		}

		return form;
	}

	private List<ServeiInfo> getServeisSolicitud(Long solicitudID) throws I18NException {

		log.info("Test serveis Solicitud " + solicitudID);

		List<ServeiInfo> serveis = new ArrayList<ModificarSolicitudPublicController.ServeiInfo>();
		Where wSoliID = SolicitudServeiFields.SOLICITUDID.equal(solicitudID);

		Long[] estatsNoAfegir = { Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_NORMA,
				Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_SERVEI };

		Where wEstatNoPerAfegir = SolicitudServeiFields.ESTATSOLICITUDSERVEIID.notIn(estatsNoAfegir);

		List<SolicitudServei> solicitudServeiList = solicitudServeiLogicaEjb
				.select(Where.AND(wSoliID, wEstatNoPerAfegir));

		for (SolicitudServei ss : solicitudServeiList) {
			Servei servei = serveiLogicaEjb.findByPrimaryKey(ss.getServeiID());

			if (servei != null) {

				String estat = I18NUtils.tradueix("estat.solicitudservei." + ss.getEstatSolicitudServeiID());

				List<String> normes = new ArrayList<String>();

				if (ss.getFitxernorma() != null) {
					normes.add(normaToHref(ss.getFitxernorma(), ss.getNormaLegal()));
				} else if (ss.getEnllazNormaLegal() != null && isValidURL(ss.getEnllazNormaLegal())) {
					normes.add("<a href=\"" + ss.getEnllazNormaLegal() + "\">" + ss.getNormaLegal() + "</a>");

				}
				if (ss.getFitxernorma2() != null) {
					normes.add(normaToHref(ss.getFitxernorma2(), ss.getNorma2()));
				}
				if (ss.getFitxernorma3() != null) {
					normes.add(normaToHref(ss.getFitxernorma3(), ss.getNorma3()));
				}

				ServeiInfo sInfo = new ServeiInfo(ss.getId(), servei.getCodi(), servei.getNom(), estat,
						String.join("<br>", normes));

				serveis.add(sInfo);
			}
		}
		return serveis;
	}

	@RequestMapping(value = "/seleccionarProcediment", method = RequestMethod.POST)
	public String seleccionarProcedimentPost(HttpServletRequest request, HttpServletResponse response)
			throws I18NException {
		log.info("Entra a seleccionarProcediment POST");
		ModelAndView mav = new ModelAndView("editarProcedimentAll");

		Long solicitudID = Long.valueOf(request.getParameter("solicitudID"));
		// Aqui creamos el objeto de ModifSoli con los datos de la solicitud, y luego
		// redirigimos a edit.

		ModificacioSolicitudJPA modSolicitud = new ModificacioSolicitudJPA();

		Solicitud solicitud = solicitudLogicaEjb.findByPrimaryKey(solicitudID);
		modSolicitud.setSolicitudID(solicitud.getSolicitudID());
		modSolicitud.setProcedimentCodi(solicitud.getProcedimentCodi());
		modSolicitud.setProcedimentNom(solicitud.getProcedimentNom());
		modSolicitud.setEstatID(solicitud.getEstatSolicitud());
		modSolicitud.setDataInici(solicitud.getDataInici());
		modSolicitud.setDataFi(solicitud.getDataFi());
		modSolicitud.setNotes(solicitud.getPinfo()); // o el campo correcto

		modSolicitud.setEstatModificacio(Constants.ESTAT_MODIFICACIO_SOLICITUD_CREACIO); // o el campo correcto
		
		modSolicitud.setOrganID(solicitud.getOrganid());
		modSolicitud.setResponsableProcNom(solicitud.getResponsableProcNom());
		modSolicitud.setResponsableProceMail(solicitud.getResponsableProcEmail());
		modSolicitud.setConsentiment(solicitud.getConsentiment());

		modSolicitud.setSolicitantNif((String) request.getSession().getAttribute("usuariNIF"));
		modSolicitud.setSolicitantNom((String)request.getSession().getAttribute("usuariNom"));
		modSolicitud.setSolicitantUsername((String)request.getSession().getAttribute("usuariUsername"));
		
		
		
		List<Long> listDocumentsSolicitud = documentSolicitudLogicaEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(solicitudID));

		List<Long> tipusDocuments = new ArrayList<Long>();
		tipusDocuments.add(Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_NOOP);
		tipusDocuments.add(Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI);

		List<Document> documents = documentLogicaEjb.select(Where
				.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud), DocumentFields.TIPUS.in(tipusDocuments)));

		log.info("Tenim " + documents + " posibles documents de consentiment.");
		for (Document document : documents) {
			modSolicitud.setDoCconsentimentID(document.getFitxerOriginalID());
			break;

//			String tipus = document.getTipus().equals(Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI) ? "Si"
//					: "No Oposició";
//			String nom = document.getNom();
//
//			log.info("CONS: " + tipus + " | " + nom);
//
//			Fitxer f = fitxerLogicEjb.findByPrimaryKey(document.getFitxerOriginalID());
//			String nomFitxer = f.getNom();
//
//			String urlDownload = normaToHref(f, nomFitxer);
		}

		ModificacioSolicitud mod = modificacioSolicitudLogicaEjb.create(modSolicitud);

//		List<ServeiInfo> serveis = new ArrayList<ModificarSolicitudPublicController.ServeiInfo>();
//		Where wSoliID = SolicitudServeiFields.SOLICITUDID.equal(solicitudID);
//		List<SolicitudServei> solicitudServeiList = solicitudServeiLogicaEjb.select(wSoliID);
//
//		for (SolicitudServei ss : solicitudServeiList) {
//			Servei servei = serveiLogicaEjb.findByPrimaryKey(ss.getServeiID());
//
//			if (servei != null) {
//
//				String estat = I18NUtils.tradueix("estat.solicitudservei." + ss.getEstatSolicitudServeiID());
//
//				List<String> normes = new ArrayList<String>();
//
//				if (ss.getFitxernorma() != null) {
//					normes.add(normaToHref(ss.getFitxernorma(), ss.getNormaLegal()));
//				} else if (ss.getEnllazNormaLegal() != null && isValidURL(ss.getEnllazNormaLegal())) {
//					normes.add("<a href=\"" + ss.getEnllazNormaLegal() + "\">" + ss.getNormaLegal() + "</a>");
//
//				}
//				if (ss.getFitxernorma2() != null) {
//					normes.add(normaToHref(ss.getFitxernorma2(), ss.getNorma2()));
//				}
//				if (ss.getFitxernorma3() != null) {
//					normes.add(normaToHref(ss.getFitxernorma3(), ss.getNorma3()));
//				}
//
//				ServeiInfo sInfo = new ServeiInfo(servei.getServeiID(), servei.getCodi(), servei.getNom(), estat,
//						String.join("<br>", normes));
//
//				serveis.add(sInfo);
//			}
//		}

		return "redirect:" + getContextWeb() + "/" + mod.getModsoliID() + "/edit";

//		mav.addObject("serveis", serveis);
//		return mav;
	}

	private String normaToHref(Fitxer f, String norma) {
		String url = "/pinbaladmin" + FileDownloadController.fileUrl(f);
		log.info(url);

		return "<a href=\"" + url + "\">" + norma + "</a>";

	}

	boolean isValidURL(String url) {
		try {
			new URL(url).toURI();
			return true;
		} catch (MalformedURLException e) {
			return false;
		} catch (URISyntaxException e) {
			return false;
		}
	}

	public class SolicitudInfo {
		private Long solicitudID;
		private String nom;
		private String codi;
		private String estat;
		private String organGestor;
		private String responsable;
		private String responsableMail;
		private String dataInici;
		private String dataFi;
		private String consentiment;
		private String notes;

		public SolicitudInfo(Long solicitudID, String nom, String codi, String estat, String organGestor,
				String responsable, String responsableMail, String dataInici, String dataFi, String consentiment,
				String notes) {
			super();
			this.solicitudID = solicitudID;
			this.nom = nom;
			this.codi = codi;
			this.estat = estat;
			this.organGestor = organGestor;
			this.responsable = responsable;
			this.responsableMail = responsableMail;
			this.dataInici = dataInici;
			this.dataFi = dataFi;
			this.consentiment = consentiment;
			this.notes = notes;
		}

		public Long getSolicitudID() {
			return solicitudID;
		}

		public String getNom() {
			return nom;
		}

		public String getCodi() {
			return codi;
		}

		public String getEstat() {
			return estat;
		}

		public String getOrganGestor() {
			return organGestor;
		}

		public String getResponsable() {
			return responsable;
		}

		public String getResponsableMail() {
			return responsableMail;
		}

		public String getDataInici() {
			return dataInici;
		}

		public String getDataFi() {
			return dataFi;
		}

		public String getConsentiment() {
			return consentiment;
		}

		public String getNotes() {
			return notes;
		}

	}

	public class ServeiInfo {
		private Long id;
		private String codi;
		private String nom;
		private String estat;
		private String normes;

		public Long getId() {
			return id;
		}

		public String getCodi() {
			return codi;
		}

		public String getNom() {
			return nom;
		}

		public String getEstat() {
			return estat;
		}

		public String getNormes() {
			return normes;
		}

		public ServeiInfo(Long id, String codi, String nom, String estat, String normes) {
			super();
			this.id = id;
			this.codi = codi;
			this.nom = nom;
			this.estat = estat;
			this.normes = normes;
		}

	}

	public class DocumentConsentimentInfo {
		private String tipus;
		private String nom;
		private String nomFitxer;
		private String urlDownload;

		public DocumentConsentimentInfo(String tipus, String nom, String nomFitxer, String urlDownload) {
			super();
			this.tipus = tipus;
			this.nom = nom;
			this.nomFitxer = nomFitxer;
			this.urlDownload = urlDownload;
		}

		public String getTipus() {
			return tipus;
		}

		public String getNom() {
			return nom;
		}

		public String getNomFitxer() {
			return nomFitxer;
		}

		public String getUrlDownload() {
			return urlDownload;
		}

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

//	@RequestMapping(value = "/modificarSolicitud/{solicitudID}", method = RequestMethod.GET)
//	public ModelAndView modificarSolicitud(HttpServletRequest request, HttpServletResponse response,
//	        @PathVariable("solicitudID") String solicitudID) throws I18NException {
//
//	    log.info("Entra a modificarSolicitud GET");
//	    ModelAndView mav = new ModelAndView("modificarSolicitudEdit");
//
//	    mav.addObject("solicitudID", solicitudID);
//	    return mav;
//	}
//
//	@RequestMapping(value = "/modificarServeis/{solicitudID}", method = RequestMethod.GET)
//	public ModelAndView modificarServeis(HttpServletRequest request, HttpServletResponse response,
//	        @PathVariable("solicitudID") String solicitudID) throws I18NException {
//
//	    log.info("Entra a modificarServeis GET");
//	    String serveis = request.getParameter("serveis");
//
//	    ModelAndView mav = new ModelAndView("modificarServeisEdit");
//	    mav.addObject("solicitudID", solicitudID);
//	    mav.addObject("serveis", serveis);
//
//	    return mav;
//	}
//
//	@RequestMapping(value = "/modificarConsentiment/{solicitudID}", method = RequestMethod.GET)
//	public ModelAndView modificarConsentiment(HttpServletRequest request, HttpServletResponse response,
//	        @PathVariable("solicitudID") String solicitudID) throws I18NException {
//
//	    log.info("Entra a modificarConsentiment GET");
//	    String docInfos = request.getParameter("docInfos");
//
//	    ModelAndView mav = new ModelAndView("modificarConsentimentEdit");
//	    mav.addObject("solicitudID", solicitudID);
//	    mav.addObject("docInfos", docInfos);
//
//	    return mav;
//	}

	public ModificacioSolicitudJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long modsoliID)
			throws I18NException {
		return (ModificacioSolicitudJPA) modificacioSolicitudLogicaEjb.findByPrimaryKey(modsoliID);
	}

	public ModificacioSolicitudJPA update(HttpServletRequest request, ModificacioSolicitudJPA modificacio)
			throws I18NException, I18NValidationException {

		
		Long soliID = modificacio.getSolicitudID();
		SolicitudJPA solicitudOriginal = solicitudLogicaEjb.findByPrimaryKey(soliID);

		// Afegir evet amb els canvis.
//		
//		Solicitud de modificació del procediment 2411062666.
//		
//		L'usuari Juan Pablo Trias Segura amb NIF 45186147W (ptrias) ha tramitat la modificació de la solicitud 2411062666:
//		
//		Canvis realitzats:
//			
//		Nom Procediment:
//			Reconocimiento del grado de discapacidad y de dependencia -> Reconocimiento del grado de discapacidad y de dependencia 2025
//		Responsable procediment:
//			Margarita Munar -> Margalita Munar
//		Document de Consentiment:
//			 FitxerProves5.pdf -> FitxerProves10.pdf 
//			
//		Serveis amb normes modificades (3):
//			DGSFP0003
//			DGSFP0001
//			
//		Serveis afegits (1):
//			SVDCTITWS02

		String nomUsuari = modificacio.getSolicitantNom();
		String nifUsuari = modificacio.getSolicitantNif();
		String loginUsuari = modificacio.getSolicitantUsername();

		StringBuilder msg = new StringBuilder();

		String codiProcediment = solicitudOriginal.getProcedimentCodi();
		String asumpte = "Solicitud de modificació del procediment " + codiProcediment;

		msg.append("<div style='font-family: sans-serif;'>");

		msg.append("<p><strong>Solicitud de modificació del procediment ").append(codiProcediment)
				.append(".</strong></p>");

		msg.append("<p>L'usuari <strong>").append(nomUsuari).append("</strong> amb NIF <strong>").append(nifUsuari)
				.append("</strong> (").append(loginUsuari)
				.append(") ha tramitat la modificació de la solicitud <strong>").append(codiProcediment)
				.append("</strong>:</p>");

		msg.append("<p><strong>Canvis realitzats:</strong></p><ul>");

		// Comparaciones
		appendSiModificat(msg, "Nom Procediment", solicitudOriginal.getProcedimentNom(),
				modificacio.getProcedimentNom());
		
		// Afegir CODI SIA NOU si no es null.
		if (modificacio.getCodiSiaNou() != null && modificacio.getCodiSiaNou().trim().length() > 0) {
			// StringBuilder msg, String label, Object original, Object modificado
			msg.append("<li><strong>").append("Nou Codi SIA").append(":</strong><br>")
					.append("&nbsp;&nbsp;&nbsp;&nbsp;").append(modificacio.getCodiSiaNou()).append("</li>");
		}
		
		appendSiModificat(msg, "Responsable procediment", solicitudOriginal.getResponsableProcNom(),
				modificacio.getResponsableProcNom());
		appendSiModificat(msg, "Mail Responsable", solicitudOriginal.getResponsableProcEmail(),
				modificacio.getResponsableProceMail());
		appendSiModificat(msg, "Consentiment", solicitudOriginal.getConsentiment(), modificacio.getConsentiment());

		List<Long> listDocumentsSolicitud = documentSolicitudLogicaEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(soliID));

		List<Long> tipusDocuments = new ArrayList<Long>();
		tipusDocuments.add(Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_NOOP);
		tipusDocuments.add(Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI);

		List<Document> documents = documentLogicaEjb.select(Where
				.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud), DocumentFields.TIPUS.in(tipusDocuments)));

		Long docConsentimentOriginalID = null;
		Document consentimentOriginal = null;

		log.info("Tenim " + documents.size() + " possibles documents de consentiment.");

		// Obtenim el primer document de consentiment original
		for (Document document : documents) {
			docConsentimentOriginalID = document.getFitxerOriginalID();
			consentimentOriginal = document;
			break;
		}

		// Document nou de la modificació
		Long docConsentimentNouID = modificacio.getDoCconsentimentID();
		FitxerJPA docConsentimentNou = modificacio.getDoCconsentiment();

		// Comparació per ID
		if (!Objects.equals(docConsentimentOriginalID, docConsentimentNouID)) {
			msg.append("<li><strong>Document de Consentiment:</strong><br>").append("&nbsp;&nbsp;&nbsp;&nbsp;")
					.append(consentimentOriginal != null ? consentimentOriginal.getNom() : "(buit)").append(" &rarr; ")
					.append(docConsentimentNou != null ? docConsentimentNou.getNom() : "(buit)").append("</li>");
		}

		msg.append("</ul>");

		Where wSoli = SolicitudServeiFields.SOLICITUDID.equal(soliID);
		Long[] estats = { Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_NORMA,
				Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_SERVEI };
		Where wEstats = SolicitudServeiFields.ESTATSOLICITUDSERVEIID.in(estats);

		List<SolicitudServei> serveisPerModificar = solicitudServeiLogicaEjb.select(Where.AND(wSoli, wEstats));

		List<String> serveisModificats = new ArrayList<String>();
		List<String> serveisAfegits = new ArrayList<String>();

		for (SolicitudServei soliServ : serveisPerModificar) {
			Servei s = serveiLogicaEjb.findByPrimaryKey(soliServ.getServeiID());

			if (soliServ.getEstatSolicitudServeiID() == Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_NORMA) {
				serveisModificats.add(s.getCodi());
			} else {
				serveisAfegits.add(s.getCodi());
			}
		}

		// Servicios modificados
		if (!serveisModificats.isEmpty()) {
			msg.append("<p><strong>Serveis amb normes modificades (").append(serveisModificats.size())
					.append("):</strong></p><ul>");
			for (String codi : serveisModificats) {
				msg.append("<li>").append(codi).append("</li>");
			}
			msg.append("</ul>");
		}

		// Servicios añadidos
		if (!serveisAfegits.isEmpty()) {
			msg.append("<p><strong>Serveis afegits (").append(serveisAfegits.size()).append("):</strong></p><ul>");
			for (String codi : serveisAfegits) {
				msg.append("<li>").append(codi).append("</li>");
			}
			msg.append("</ul>");
		}

		msg.append("</div>");

//		log.info(msg.toString());

		crearEventModificacio(modificacio, nomUsuari, asumpte, msg.toString());

		// Actualitzar estat solicitud a PENDENT_REVISIO_MODIFICACIO
		solicitudOriginal.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_REVISAR_MODIFICACIO);
		solicitudLogicaEjb.update(solicitudOriginal);
		
		modificacio.setEstatModificacio(Constants.ESTAT_MODIFICACIO_SOLICITUD_ENVIADA);
		ModificacioSolicitudJPA modificacioSolicitud = (ModificacioSolicitudJPA) modificacioSolicitudLogicaEjb
				.update(modificacio);
		return modificacioSolicitud;
	}

	private void appendSiModificat(StringBuilder msg, String label, Object original, Object modificado) {
		if (!Objects.equals(original, modificado)) {
			msg.append("<li><strong>").append(label).append(":</strong><br>").append("&nbsp;&nbsp;&nbsp;&nbsp;")
					.append(original != null ? original : "(buit)").append(" &rarr; ")
					.append(modificado != null ? modificado : "(buit)").append("</li>");
		}
	}

	// Quien envia el mensaje
	private void crearEventModificacio(ModificacioSolicitudJPA modificacio, String usuari, String asumpte, String msg)
			throws I18NException {
		final Timestamp data = new Timestamp(System.currentTimeMillis());
		final String caidIdentificadorConsulta = null;
		final String caidNumeroSeguiment = null;

		Long _fitxerID_ = null;
		boolean _noLlegit_ = true;

		EventJPA event = new EventJPA();
		event.setSolicitudID(modificacio.getSolicitudID());
		event.setIncidenciaTecnicaID(null);
		event.setDataEvent(data);
		event.setTipus(Constants.EVENT_TIPUS_COMENTARI_CONTACTE);
		event.setFitxerID(_fitxerID_);
		event.setNoLlegit(_noLlegit_);
		event.setCaidIdentificadorConsulta(caidIdentificadorConsulta);
		event.setCaidNumeroSeguiment(caidNumeroSeguiment);

		event.setPersona(usuari);
		event.setAsumpte(asumpte);
		event.setComentari(msg);

		// Es un comentari de contacte, no te destinatari.
		event.setDestinatari(null);
		event.setDestinatarimail(null);

		eventLogicaEjb.create(event);
	}

	@Override
	public List<StringKeyValue> getReferenceListForOrganID(HttpServletRequest request, ModelAndView mav, Where where)
			throws I18NException {

		List<StringKeyValue> _tmp = new ArrayList<StringKeyValue>();

		List<Organ> organs = organLogicEjb.select(where);
		for (Organ organ : organs) {
			_tmp.add(new StringKeyValue(String.valueOf(organ.getOrganid()),
					"(" + organ.getDir3() + ") " + organ.getNom()));
		}

		return _tmp;
	}

	@Override
	public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {
		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

		for (String consentimentTipus : Constants.CONSENTIMENTS_TIPUS) {
			String key = consentimentTipus;
			String value = I18NUtils.tradueix("consentiment.tipus." + key);

			__tmp.add(new StringKeyValue(key, value));
		}

		return __tmp;
	}

	@Override
	public List<StringKeyValue> getReferenceListForEstatID(HttpServletRequest request, ModelAndView mav, Where where)
			throws I18NException {
		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

		for (long estat : Constants.ESTATS_SOLI) {
			String key = String.valueOf(estat);
			__tmp.add(new StringKeyValue(key, I18NUtils.tradueix("solicitud.estat." + key)));
		}

		return __tmp;
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, Long modsoliID) {

		return "redirect:" + ModificarSolicitudPublicController.CONTEXT_WEB + "/seleccionarProcediment";
//		return "redirect:" + ModificarSolicitudPublicController.CONTEXT_WEB + "/" + modsoliID + "/edit";
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, ModificacioSolicitudForm modificacioSolicitudForm,
			Throwable __e) {

		// Redirigir al llistat d'events de la Solicitut que es modifica.

		Long soliID = modificacioSolicitudForm.getModificacioSolicitud().getSolicitudID();
//		String destinatari = "Margarita Munar Florit";
		String destinatari = modificacioSolicitudForm.getModificacioSolicitud().getSolicitantNom();
		String cadenaDestinatari = "CONTACTE|" + destinatari;

		String id = HibernateFileUtil.encryptFileID(soliID);
		String dest = destinatari == null ? "" : ("/" + HibernateFileUtil.encryptString(cadenaDestinatari));

		String url = EventSolicitudPublicController.CONTEXT_PATH + "/veureevents/" + id + dest;

		log.info("redirectToEventsPinfo: " + url);
		return "redirect:" + url;
	}

	@RequestMapping(value = "/afegirNorma", method = RequestMethod.POST)
	public String afegirNorma(HttpServletRequest request, @RequestParam("serveiId") Long ID,
			@RequestParam("norma") String norma, @RequestParam("articles") String articles,
			@RequestParam("fitxer") MultipartFile fitxer) throws I18NException {

		log.info("Buenas");

		if (fitxer != null && !fitxer.isEmpty()) {
			String nombreFichero = fitxer.getOriginalFilename();
			log.info("nombreFichero: " + nombreFichero);

			// TODO: Guardar el fichero
			SolicitudServeiJPA ss = solicitudServeiLogicaEjb.findByPrimaryKey(ID);

			if (ss.getNormaLegal() == null) {
				log.info("Afegim norma 1");
				ss.setNormaLegal(norma);
				ss.setArticles(articles);
				ss.setFitxernormaID(fitxerIDFromMultipartFile(fitxer));
				ss.setEstatSolicitudServeiID(Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_NORMA);
			} else if (ss.getNorma2() == null) {
				log.info("Afegim norma 2");
				ss.setNorma2(norma);
				ss.setArticles2(articles);
				ss.setFitxernorma2ID(fitxerIDFromMultipartFile(fitxer));
				ss.setEstatSolicitudServeiID(Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_NORMA);
			} else if (ss.getNorma3() == null) {
				log.info("Afegim norma 3");
				ss.setNorma3(norma);
				ss.setArticles3(articles);
				ss.setFitxernorma3ID(fitxerIDFromMultipartFile(fitxer));
				ss.setEstatSolicitudServeiID(Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_NORMA);
			} else {
				HtmlUtils.saveMessageError(request, "Aquest servei ja te 3 normes. No es poden afegir mes");
			}

			solicitudServeiLogicaEjb.update(ss);

		} else {
			log.warn("No se ha subido ningún fichero.");
			// TODO: Manejar el error si es obligatorio
		}

		log.info("tardes");

		// Obtener ID de solicitud desde sesión
		Long modSoliID = (Long) request.getSession().getAttribute(ModificarSolicitudPublicController.MOD_SOLI_ID);

		// Redirigir a la edición de la solicitud correspondiente
		return "redirect:" + CONTEXT_WEB + "/" + modSoliID + "/edit";
	}

	private Long fitxerIDFromMultipartFile(MultipartFile fitxer) throws I18NException {
		// Crear fitxer Fisic, logic, i tornar ID.

		byte[] data;
		try {
			data = fitxer.getBytes();
		} catch (IOException e) {
			throw new I18NException("genapp.comodi",
					new I18NArgumentString("Error obtenint dades del fitxer. " + e.getMessage()));

		}
		String mime = fitxer.getContentType();
		String nom = fitxer.getOriginalFilename();

		Fitxer f = fitxerLogicEjb.create(nom, data.length, mime, null);
		FileSystemManager.crearFitxer(new ByteArrayInputStream(data), f.getFitxerID());

		return f.getFitxerID();

	}

}
