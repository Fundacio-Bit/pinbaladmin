package org.fundaciobit.pinbaladmin.back.controller.all;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.FileDownloadController;
import org.fundaciobit.pinbaladmin.back.controller.all.PinfoDataPublicController.Item;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.DocumentLogicaService;
import org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaEJB;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.QueEsticFentUtils;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ModificarSolicitudPublicController.CONTEXT_WEB)
@SessionAttributes(types = { EventForm.class, EventFilterForm.class })
public class ModificarSolicitudPublicController {

	public static final String CONTEXT_WEB = "/public/modificarsolicitud";

	protected static final Logger log = Logger.getLogger(ModificarSolicitudPublicController.class);

	public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

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

	public String getContextWeb() {
		RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
		return rm.value()[0];
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

	@RequestMapping(value = "/seleccionarProcediment", method = RequestMethod.POST)
	public ModelAndView seleccionarProcedimentPost(HttpServletRequest request, HttpServletResponse response)
			throws I18NException {
		log.info("Entra a seleccionarProcediment POST");
		ModelAndView mav = new ModelAndView("editarProcedimentAll");

		Long solicitudID = Long.valueOf(request.getParameter("solicitudID"));
		
		SolicitudInfo solicitudInfo; 
		{

			Solicitud soli = solicitudLogicaEjb.findByPrimaryKey(solicitudID);

			Organ organ = organLogicEjb.findByPrimaryKey(soli.getOrganid());
			String organGestor = "(" + organ.getDir3() + ") " + organ.getNom();

			String estat = I18NUtils.tradueix("solicitud.estat." + soli.getEstatID());
			
			String dataInici = SDF.format(soli.getDataInici());

			String dataFi = null;
			if (soli.getDataFi() != null) {
				dataFi = SDF.format(soli.getDataFi());
			}
			
			String consentiment = null;
			if (soli.getConsentiment() != null) {
				consentiment = I18NUtils.tradueix("consentiment.tipus." + soli.getConsentiment());
			}
					
			if (soli.getConsentimentadjunt() != null) {
				consentiment += " (" + I18NUtils.tradueix("consentiment." + soli.getConsentimentadjunt()) + ") ";
			}

			solicitudInfo = new SolicitudInfo(solicitudID, soli.getProcedimentNom(), soli.getProcedimentCodi(), estat,
					organGestor, soli.getResponsableProcNom(), soli.getResponsableProcEmail(), dataInici, dataFi,
					consentiment, soli.getNotes());
		}
		

		List<ServeiInfo> serveis = new ArrayList<ModificarSolicitudPublicController.ServeiInfo>();
		Where wSoliID = SolicitudServeiFields.SOLICITUDID.equal(solicitudID);
		List<SolicitudServei> solicitudServeiList = solicitudServeiLogicaEjb.select(wSoliID);

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

				ServeiInfo sInfo = new ServeiInfo(servei.getServeiID(), servei.getCodi(), servei.getNom(), estat,
						String.join("<br>", normes));

				serveis.add(sInfo);
			}
		}

		List<Long> listDocumentsSolicitud = documentSolicitudLogicaEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(solicitudID));

		List<Long> tipusDocuments = new ArrayList<Long>();
		tipusDocuments.add(Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_NOOP);
		tipusDocuments.add(Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI);

		List<Document> documents = documentLogicaEjb.select(Where
				.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud), DocumentFields.TIPUS.in(tipusDocuments)));

		List<DocumentConsentimentInfo> docInfos = new ArrayList<ModificarSolicitudPublicController.DocumentConsentimentInfo>();
		for (Document document : documents) {
			String tipus = document.getTipus().equals(Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI) ? "Si"
					: "No Oposició";
			String nom = document.getNom();

			log.info("CONS: " + tipus + " | " + nom);

			Fitxer f = fitxerLogicEjb.findByPrimaryKey(document.getFitxerOriginalID());
			String nomFitxer = f.getNom();

			String urlDownload = normaToHref(f, nomFitxer);

			DocumentConsentimentInfo docInfo = new DocumentConsentimentInfo(tipus, nom, nomFitxer, urlDownload);
			docInfos.add(docInfo);
		}

		mav.addObject("solicitud", solicitudInfo);
		mav.addObject("serveis", serveis);
		mav.addObject("docInfos", docInfos);
//		http://ptrias:8080/pinbaladmin/public/arxiu/bvVxqDnjDvNCgwe_EV4Kzg==?nom=12239+%281%29.pdf&mime=application%2Fpdf
//		http://ptrias:8080/pinbaladmin/public/arxiu/bvVxqDnjDvNCgwe_EV4Kzg==?nom=12239+%281%29.pdf&mime=application%2Fpdf	

		return mav;
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

	@RequestMapping(value = "/afegirEntrada/{usuari}/{dateStr}/{msgEnc}", method = RequestMethod.GET)
	public void afegeixEntrada(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("usuari") String usuari, @PathVariable("dateStr") String dateStr,
			@PathVariable("msgEnc") String msgEnc) {

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

	@RequestMapping(value = "/modificarSolicitud/{solicitudID}", method = RequestMethod.GET)
	public ModelAndView modificarSolicitud(HttpServletRequest request, HttpServletResponse response,
	        @PathVariable("solicitudID") String solicitudID) throws I18NException {

	    log.info("Entra a modificarSolicitud GET");
	    ModelAndView mav = new ModelAndView("modificarSolicitudEdit");

	    mav.addObject("solicitudID", solicitudID);
	    return mav;
	}

	@RequestMapping(value = "/modificarServeis/{solicitudID}", method = RequestMethod.GET)
	public ModelAndView modificarServeis(HttpServletRequest request, HttpServletResponse response,
	        @PathVariable("solicitudID") String solicitudID) throws I18NException {

	    log.info("Entra a modificarServeis GET");
	    String serveis = request.getParameter("serveis");

	    ModelAndView mav = new ModelAndView("modificarServeisEdit");
	    mav.addObject("solicitudID", solicitudID);
	    mav.addObject("serveis", serveis);

	    return mav;
	}

	@RequestMapping(value = "/modificarConsentiment/{solicitudID}", method = RequestMethod.GET)
	public ModelAndView modificarConsentiment(HttpServletRequest request, HttpServletResponse response,
	        @PathVariable("solicitudID") String solicitudID) throws I18NException {

	    log.info("Entra a modificarConsentiment GET");
	    String docInfos = request.getParameter("docInfos");

	    ModelAndView mav = new ModelAndView("modificarConsentimentEdit");
	    mav.addObject("solicitudID", solicitudID);
	    mav.addObject("docInfos", docInfos);

	    return mav;
	}

}
