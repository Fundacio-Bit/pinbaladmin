package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.all.ModificarSolicitudPublicController.Item;
import org.fundaciobit.pinbaladmin.back.controller.operador.LlistaCorreusOperadorController.SolicitudDTO;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments.TipusProcediment;
import org.fundaciobit.pinbaladmin.logic.DocumentLogicaService;
import org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.EntitatServeiLogicService;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.EntitatJPA;
import org.fundaciobit.pinbaladmin.persistence.EntitatServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.OrganJPA;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = FusionarProcedimentsOperadorController.CONTEXTWEB)
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class FusionarProcedimentsOperadorController {

	public static final String CONTEXTWEB = "/operador/fusionarprocediments";

	protected static final Logger log = Logger.getLogger(FusionarProcedimentsOperadorController.class);

	@EJB(mappedName = org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService solicitudLogicaEjb;

	@EJB(mappedName = OrganLogicaService.JNDI_NAME)
	protected OrganLogicaService organLogicaEjb;

	@EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
	protected SolicitudServeiLogicaService solicitudServeiLogicaEjb;

	@EJB(mappedName = ServeiLogicaService.JNDI_NAME)
	protected ServeiLogicaService serveiLogicaEjb;

	@EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
	protected DocumentSolicitudLogicaService documentSolicitudLogicaEjb;

	@EJB(mappedName = DocumentLogicaService.JNDI_NAME)
	protected DocumentLogicaService documentLogicaEjb;

	@EJB(mappedName = EntitatServeiLogicService.JNDI_NAME)
	protected EntitatServeiLogicService entitatLogicaEjb;

	@EJB(mappedName = EventLogicaService.JNDI_NAME)
	protected EventLogicaService eventLogicaEjb;

	
	@RequestMapping(value = "/elegirProcediments", method = RequestMethod.GET)
	public ModelAndView elegirProcediments(HttpServletRequest request, HttpServletResponse response) {
		log.info("Entra a elegirProcediments");

		ModelAndView mav = new ModelAndView("fusionarprocediments");

		mav.addObject("contexte", CONTEXTWEB);

		return mav;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public void buscar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String param = request.getParameter("param");

			log.info("Buscando procediments: " + param);

			List<Solicitud> results = new ArrayList<>();

			// 1. Buscar por ID si es número
			try {
				Long id = Long.parseLong(param);
				results.addAll(solicitudLogicaEjb.select(SolicitudFields.SOLICITUDID.equal(id)));
			} catch (NumberFormatException ignored) {
			}

			// 2. Buscar por Codi
			results.addAll(solicitudLogicaEjb.select(SolicitudFields.PROCEDIMENTCODI.like("%" + param + "%")));

			// 3. Buscar por Nom
			results.addAll(solicitudLogicaEjb.select(SolicitudFields.PROCEDIMENTNOM.like("%" + param + "%")));

			// Quitar duplicados
			List<Solicitud> distinctResults = results.stream().distinct().collect(Collectors.toList());

			// Convertir a DTOs
			List<SolicitudDTO> dtoList = distinctResults.stream().map(SolicitudDTO::new).collect(Collectors.toList());

			// Responder en JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			Gson g = new Gson();
			response.getWriter().print(g.toJson(dtoList));
		} catch (Throwable e) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			String msg;
			if (e instanceof I18NException) {
				msg = I18NUtils.getMessage((I18NException) e);
			} else {
				msg = "Error assignant a item: " + e.getMessage();
			}

			log.error(msg, e);

			Gson g = new Gson();
			response.getWriter().print(g.toJson(Collections.singletonMap("error", msg)));

		}
	}

//	@RequestMapping(value = "/fusionar", method = RequestMethod.POST)
//	@ResponseBody
//	public String fusionar(@RequestBody List<Long> ids) {
//		log.info("Fusionando procediments: " + ids);
//		solicitudLogicaEjb.fusionarSolicitudos(ids);
//		return "OK";
//	}

	public class SolicitudDTO {
		private Long id;
		private String codi;
		private String nom;
		// lo que quieras mostrar en la lista

		public SolicitudDTO(Solicitud s) {
			this.id = s.getSolicitudID();
			this.codi = s.getProcedimentCodi();
			this.nom = s.getProcedimentNom();
		}
	}

	public class ServeiDTO {

		private Long id;
		private String codi;
		private String nom;
		private String cedent;

		public ServeiDTO(Long serveiID) {
			ServeiJPA servei = serveiLogicaEjb.findByPrimaryKey(serveiID);

			this.id = servei.getServeiID();
			this.codi = servei.getCodi();
			this.nom = servei.getNom();

			EntitatServeiJPA entitat = entitatLogicaEjb.findByPrimaryKey(servei.getEntitatServeiID());
			this.cedent = entitat.getNom();

		}
	}

	public class DocumentDTO {
		private Long id;
		private String nom;
		private Long tipus;
		private Long solicitudID;

		public DocumentDTO(Long documentID, Long solicitudID) {
			DocumentLogicaService docEjb = documentLogicaEjb;
			DocumentJPA doc = docEjb.findByPrimaryKey(documentID);

			this.id = doc.getDocumentID();
			this.nom = doc.getNom();
			this.tipus = doc.getTipus();
			this.solicitudID = solicitudID;
		}
	}

	public class SolicitudFullDTO {
		private Long solicitudID;
		private String procedimentCodi;
		private String codiDescriptiu;
		private String codiSiaConv;
		private String procedimentNom;
		private String procedimentTipus;
		private Timestamp dataInici;
		private Timestamp dataFi;
		private String personaContacte;
		private String personaContacteEmail;
		private String responsableProcNom;
		private String responsableProcEmail;
		private String notes;
		private String consentiment;
		private String urlconsentiment;
		private String consentimentadjunt;

		private String organ;
		private String estatSolicitud;
		private String estatpinbal;

		private List<ServeiDTO> servicios;
		private List<DocumentDTO> documentos;

		public SolicitudFullDTO(Long solicitudID) throws I18NException {
			SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(solicitudID);

			this.solicitudID = soli.getSolicitudID();
			this.procedimentCodi = soli.getProcedimentCodi();
			this.codiDescriptiu = soli.getCodiDescriptiu();
			this.codiSiaConv = soli.getCodiSiaConv();
			this.procedimentNom = soli.getProcedimentNom();
			this.dataInici = soli.getDataInici();
			this.dataFi = soli.getDataFi();
			this.personaContacte = soli.getPersonaContacte();
			this.personaContacteEmail = soli.getPersonaContacteEmail();
			this.responsableProcNom = soli.getResponsableProcNom();
			this.responsableProcEmail = soli.getResponsableProcEmail();
			this.notes = soli.getNotes();
			this.consentiment = soli.getConsentiment();
			this.urlconsentiment = soli.getUrlconsentiment();
			this.consentimentadjunt = soli.getConsentimentadjunt();

			OrganJPA organ = organLogicaEjb.findByPrimaryKey(soli.getOrganid());

			this.organ = organ != null ? "(" + organ.getDir3() + ") " + organ.getNom() : "";
			this.estatSolicitud = I18NUtils.tradueix("solicitud.estat." + soli.getEstatSolicitud());
			this.estatpinbal = I18NUtils.tradueix("estat.pinbal." + soli.getEstatpinbal());
//			this.procedimentTipus = soli.getProcedimentTipus();
			this.procedimentTipus = getTipusDocFromID(soli.getProcedimentTipus());
			
//			List<String> serveisList = solicitudServeiLogicaEjb
//					.select(SolicitudServeiFields.SOLICITUDID.equal(solicitudID))
//					.stream().map(ss -> serveiLogicaEjb.findByPrimaryKey(ss.getServeiID())).filter(s -> s != null)
//					.map(s -> s.getCodi() + " - " + s.getNom()).collect(Collectors.toList());
//			
//			this.servicios = serveisList.toArray(new String[0]);

			List<ServeiDTO> serveisList = solicitudServeiLogicaEjb
					.select(SolicitudServeiFields.SOLICITUDID.equal(solicitudID)).stream()
					.map(ss -> new ServeiDTO(ss.getServeiID())).collect(Collectors.toList());

			this.servicios = serveisList;

//			List<String> docsList = documentSolicitudLogicaEjb
//					.select(DocumentSolicitudFields.SOLICITUDID.equal(solicitudID))
//					.stream().map(ds -> documentLogicaEjb.findByPrimaryKey(ds.getDocumentID())).filter(d -> d != null)
//					.map(d -> d.getNom()).collect(Collectors.toList());
//			
//			this.documentos = docsList.toArray(new String[0]);

			List<DocumentDTO> docsList = documentSolicitudLogicaEjb
					.select(DocumentSolicitudFields.SOLICITUDID.equal(solicitudID)).stream()
					.map(ds -> new DocumentDTO(ds.getDocumentID(), solicitudID)).collect(Collectors.toList());

			this.documentos = docsList;

		}
	}

	@RequestMapping(value = "/detallesFusion", method = RequestMethod.POST)
	public void detallesFusion(HttpServletRequest request, HttpServletResponse response)
			throws IOException, I18NException {

		log.info("Entra a detallesFusion");

		String idsParam = request.getParameter("ids"); // "3333,4444"

		log.info("IDs recibidos: " + idsParam);

		if (idsParam == null || idsParam.isEmpty()) {
			response.setContentType("application/json");
			response.getWriter().print("[]");
			return;
		}

		String[] idStrings = idsParam.split(",");
		List<Long> ids = new ArrayList<>();
		for (String s : idStrings) {
			try {
				ids.add(Long.parseLong(s.trim()));
			} catch (NumberFormatException e) {
			}
		}

		List<SolicitudFullDTO> solicitudes = new ArrayList<>();

		// luego buscar las solicitudes y devolver JSON
		for (Long id : ids) {
			log.info("Procesando ID: " + id);
			SolicitudJPA solicitud = solicitudLogicaEjb.findByPrimaryKey(id);

			if (solicitud != null) {
				SolicitudFullDTO solicitudFull = new SolicitudFullDTO(solicitud.getSolicitudID());
				solicitudes.add(solicitudFull);
				log.info("Solicitud encontrada: " + solicitud.getSolicitudID());
			} else {
				log.warn("No se encontró solicitud con ID: " + id);
			}
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Gson g = new Gson();
		response.getWriter().print(g.toJson(solicitudes));
	}

	@RequestMapping(value = "/fusionar", method = RequestMethod.POST)
	public String procesarFusion(HttpServletRequest request, HttpServletResponse response) throws IOException, I18NException {

		log.info("Entra a fusionar");

		// ================================
		// Servicios y documentos (listas)
		// ================================
		String fusionadosParam = request.getParameter("fusionados"); // "5555,6666"
		List<Long> fusionados = fusionadosParam != null && !fusionadosParam.isEmpty()
				? Arrays.stream(fusionadosParam.split(",")).map(Long::parseLong).collect(Collectors.toList())
				: Collections.emptyList();
		log.info("Identificadores: " + fusionados);

		String serviciosParam = request.getParameter("servicios"); // "5555,6666"
		List<Long> servicios = serviciosParam != null && !serviciosParam.isEmpty()
				? Arrays.stream(serviciosParam.split(",")).map(Long::parseLong).collect(Collectors.toList())
				: Collections.emptyList();
		log.info("Servicios seleccionados: " + servicios);

		String documentosParam = request.getParameter("documentos"); // "7777,8888"
		List<Long> documentos = documentosParam != null && !documentosParam.isEmpty()
				? Arrays.stream(documentosParam.split(",")).map(Long::parseLong).collect(Collectors.toList())
				: Collections.emptyList();
		log.info("Documentos seleccionados: " + documentos);

		// ================================
		// Campos simples
		// ================================
		SolicitudJPA solicitudNueva = crearSolicicitudCampos(request);
		String codiSiaConvocatoria = procesarCodisProcediment(fusionados);
		solicitudNueva.setCodiSiaConv(codiSiaConvocatoria);
		
		List<SolicitudServeiJPA> serviciosNuevos = getServiciosSolicitud(fusionados, servicios);
		
	//	fusionar(solicitudNueva, fusionados, serviciosNuevos, documentos);

		return "redirect:/operador/fusionarprocediments/elegirProcediments";
	}

	private String procesarCodisProcediment(List<Long> fusionados) {
		// Coger todos los codigos SIA de los procedimientos y concater los distintos
		
		List<String> codis = new ArrayList<>();
		
		for (Long soliID : fusionados) {
			SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);
			if (soli != null && soli.getCodiSiaConv() != null && !soli.getCodiSiaConv().isEmpty()) {
				String[] parts = soli.getCodiSiaConv().split(",");
				for (String part : parts) {
					String trimmed = part.trim();
					if (!trimmed.isEmpty() && !codis.contains(trimmed) && trimmed.length() <= 20) {
						codis.add(trimmed);
					}
				}
			}
		}
		
		int maxLength = 255;
		String result = String.join(", ", codis);
		String maxLengthStr = result.length() > maxLength ? result.substring(0, maxLength) : result; 
		
		log.info("Códigos SIA concatenados: " + maxLengthStr);
		
		return maxLengthStr;
	}

	private void fusionar(SolicitudJPA solicitudNueva, List<Long> fusionados,
			List<SolicitudServeiJPA> serviciosNuevos, List<Long> documentos) throws I18NException {

		solicitudLogicaEjb.create(solicitudNueva);

		Long nuevaSolicitudID = solicitudNueva.getSolicitudID();
		
		List<Event> eventos = eventLogicaEjb.select(EventFields.SOLICITUDID.in(fusionados));

		for (Event ev : eventos) {
			ev.setSolicitudID(nuevaSolicitudID);
			eventLogicaEjb.update(ev);
		}

		for (SolicitudServeiJPA ss : serviciosNuevos) {
			ss.setSolicitudID(nuevaSolicitudID);
			solicitudServeiLogicaEjb.create(ss);
		}

		List<DocumentSolicitud> docs = documentSolicitudLogicaEjb.select(Where.AND(
				DocumentSolicitudFields.SOLICITUDID.in(fusionados), DocumentSolicitudFields.DOCUMENTID.in(documentos)));
		
		for (DocumentSolicitud ds : docs) {
			ds.setSolicitudID(nuevaSolicitudID);
			documentSolicitudLogicaEjb.update(ds);
		}
		
		generarNousDocumentsSolicitud();
		
		log.info("Fusión completada: nueva solicitud ID " + solicitudNueva.getSolicitudID());
		

	}

	private void generarNousDocumentsSolicitud() {
		/*
		 * Lista de documentos que hay que regenerar:
		 * 
		 * Documento de la solicitud PDF
		 * XML de la solicitud
		 * 
		 * Formulario para el DG (PDF)
		 * Formulario para el DG (ODT)
		 * 
		 * Excel de procedimientos Locales
		 * Excel de procedimientos Estatales
		 * 
		 * 
		 */

		
		
	}

	private SolicitudJPA crearSolicicitudCampos(HttpServletRequest request) throws I18NException {

		String procedimentCodi = request.getParameter("procedimentCodi");
		String codiDescriptiu = request.getParameter("codiDescriptiu");
		String codiSiaConv = request.getParameter("codiSiaConv");
		String procedimentNom = request.getParameter("procedimentNom");
		Timestamp dataInici = parseTimestamp(request.getParameter("dataInici"));
		Timestamp dataFi = parseTimestamp(request.getParameter("dataFi"));
		String personaContacte = request.getParameter("personaContacte");
		String personaContacteEmail = request.getParameter("personaContacteEmail");
		String responsableProcNom = request.getParameter("responsableProcNom");
		String responsableProcEmail = request.getParameter("responsableProcEmail");
		String notes = request.getParameter("notes");
		String consentiment = request.getParameter("consentiment");
		String urlconsentiment = request.getParameter("urlconsentiment");
		String consentimentadjunt = request.getParameter("consentimentadjunt");

		String organParam = request.getParameter("organ");
		log.info("Organ recibido: " + organParam);

		String dir3 = extractDir3(organParam);
		Long organId = organLogicaEjb.executeQueryOne(OrganFields.ORGANID, OrganFields.DIR3.equal(dir3));
		log.info("Organ extraído: dir3=" + dir3 + ", organId=" + organId);

		String estatSolicitud = request.getParameter("estatSolicitud");
		Long estatSolicitudId = extratEstat("solicitud.estat.", Constants.ESTATS_SOLI, estatSolicitud);

		String estatpinbal = request.getParameter("estatpinbal");
		Long estatpinbalId = extratEstat("estat.pinbal.", Constants.ESTATS_PINBAL, estatpinbal);

//		String procedimentTipus = request.getParameter("procedimentTipus");
		
        String procedimentTipusText = request.getParameter("procedimentTipus");
        String procedimentTipus = String.valueOf(getTipusDocIDFromText(procedimentTipusText));
        

		// ================================
		// Log para depuración
		// ================================
		log.info("procedimentCodi: " + procedimentCodi);
		log.info("codiDescriptiu: " + codiDescriptiu);
		log.info("codiSiaConv: " + codiSiaConv);
		log.info("procedimentNom: " + procedimentNom);
		log.info("dataInici: " + dataInici);
		log.info("dataFi: " + dataFi);
		log.info("personaContacte: " + personaContacte);
		log.info("personaContacteEmail: " + personaContacteEmail);
		log.info("responsableProcNom: " + responsableProcNom);
		log.info("responsableProcEmail: " + responsableProcEmail);
		log.info("notes: " + notes);
		log.info("consentiment: " + consentiment);
		log.info("urlconsentiment: " + urlconsentiment);
		log.info("consentimentadjunt: " + consentimentadjunt);

		log.info("organId: " + organId);
		log.info("estatSolicitud: " + estatSolicitudId);
		log.info("estatpinbal: " + estatpinbalId);
		log.info("procedimentTipus: " + procedimentTipus);

		SolicitudJPA solicitudNueva = new SolicitudJPA();

		solicitudNueva.setProcedimentCodi(procedimentCodi);
		solicitudNueva.setCodiDescriptiu(codiDescriptiu);
		solicitudNueva.setCodiSiaConv(codiSiaConv);
		solicitudNueva.setProcedimentNom(procedimentNom);
		solicitudNueva.setProcedimentTipus(procedimentTipus);
		solicitudNueva.setDataInici(dataInici);
		solicitudNueva.setDataFi(dataFi);
		solicitudNueva.setPersonaContacte(personaContacte);
		solicitudNueva.setPersonaContacteEmail(personaContacteEmail);
		solicitudNueva.setResponsableProcNom(responsableProcNom);
		solicitudNueva.setResponsableProcEmail(responsableProcEmail);
		solicitudNueva.setNotes(notes);
		solicitudNueva.setConsentiment(consentiment);
		solicitudNueva.setUrlconsentiment(urlconsentiment);
		solicitudNueva.setConsentimentadjunt(consentimentadjunt);
		solicitudNueva.setOrganid(organId);
		solicitudNueva.setEstatSolicitud(estatSolicitudId);
		solicitudNueva.setEstatpinbal(estatpinbalId);

		return solicitudNueva;
	}

	private List<SolicitudServeiJPA> getServiciosSolicitud(List<Long> fusionados, List<Long> servicios)
			throws I18NException {
		// Coger todos los soliServ de un servicio de estas solicitudes, y recuperar
		// todas las normas.

		// Crear un nuevo soliServ por cada servicio seleccionado, y asignarle las
		// normas recuperadas.

		List<SolicitudServeiJPA> definitius = new ArrayList<>();

		class NormaInfo {
			String norma;
			Long fitxerId;
			String articles;
			Long solicitudId; // para logging
			Long serveiId; // para logging

			NormaInfo(String norma, Long fitxerId, String articles, Long solicitudId, Long serveiId) {
				this.norma = norma;
				this.fitxerId = fitxerId;
				this.articles = articles;
				this.solicitudId = solicitudId;
				this.serveiId = serveiId;
			}
		}

		for (Long serveiID : servicios) {
			List<SolicitudServei> soliServsBySoli = solicitudServeiLogicaEjb.select(Where.AND(
					SolicitudServeiFields.SOLICITUDID.in(fusionados), SolicitudServeiFields.SERVEIID.equal(serveiID)));

			List<NormaInfo> normas = new ArrayList<>();

			for (SolicitudServei ss : soliServsBySoli) {
				log.info("Procesando SoliServ: solicitudId=" + ss.getSolicitudID() + ", serveiId=" + ss.getServeiID());

				if (ss.getNormaLegal() != null && !ss.getNormaLegal().isEmpty()) {
					log.info("  Añadiendo norma1='" + ss.getNormaLegal() + "' (solicitud=" + ss.getSolicitudID()
							+ ", servei=" + ss.getServeiID() + ")");
					normas.add(new NormaInfo(ss.getNormaLegal(), ss.getFitxernormaID(), ss.getArticles(),
							ss.getSolicitudID(), ss.getServeiID()));
				}
				if (ss.getNorma2() != null && !ss.getNorma2().isEmpty()) {
					log.info("  Añadiendo norma2='" + ss.getNorma2() + "' (solicitud=" + ss.getSolicitudID()
							+ ", servei=" + ss.getServeiID() + ")");
					normas.add(new NormaInfo(ss.getNorma2(), ss.getFitxernorma2ID(), ss.getArticles2(),
							ss.getSolicitudID(), ss.getServeiID()));
				}
				if (ss.getNorma3() != null && !ss.getNorma3().isEmpty()) {
					log.info("  Añadiendo norma3='" + ss.getNorma3() + "' (solicitud=" + ss.getSolicitudID()
							+ ", servei=" + ss.getServeiID() + ")");
					normas.add(new NormaInfo(ss.getNorma3(), ss.getFitxernorma3ID(), ss.getArticles3(),
							ss.getSolicitudID(), ss.getServeiID()));
				}
			}

			// 🔑 Eliminar duplicados por el nombre de la norma
			
			log.info("Total normas encontradas para serveiId=" + serveiID + ": " + normas.size());
			
			Set<String> seenNormas = new HashSet<>();
			List<NormaInfo> normasUnicas = normas.stream().filter(n -> seenNormas.add(n.norma)) 
					.collect(Collectors.toList());
			// solo deja pasar la primera vez que aparece la norma

			log.info("Consolidando servicio " + serveiID + ": " + normasUnicas.size() + " normas únicas encontradas");

			// Crear el nuevo único SoliServ fusionado
			SolicitudServeiJPA nuevoSoliServ = new SolicitudServeiJPA();
			nuevoSoliServ.setServeiID(serveiID);

			// Asignar hasta 3 normas
			if (normasUnicas.size() > 0) {
				nuevoSoliServ.setNormaLegal(normasUnicas.get(0).norma);
				nuevoSoliServ.setFitxernormaID(normasUnicas.get(0).fitxerId);
				nuevoSoliServ.setArticles(normasUnicas.get(0).articles);
				
				log.info("  Norma asignada: '" + normasUnicas.get(0).norma + "' (solicitud="
						+ normasUnicas.get(0).solicitudId + ", servei=" + normasUnicas.get(0).serveiId + ")");
			}
			if (normasUnicas.size() > 1) {
				nuevoSoliServ.setNorma2(normasUnicas.get(1).norma);
				nuevoSoliServ.setFitxernorma2ID(normasUnicas.get(1).fitxerId);
				nuevoSoliServ.setArticles2(normasUnicas.get(1).articles);
				
				log.info("  Norma asignada: '" + normasUnicas.get(1).norma + "' (solicitud="
						+ normasUnicas.get(1).solicitudId + ", servei=" + normasUnicas.get(1).serveiId + ")");
			}
			if (normasUnicas.size() > 2) {
				nuevoSoliServ.setNorma3(normasUnicas.get(2).norma);
				nuevoSoliServ.setFitxernorma3ID(normasUnicas.get(2).fitxerId);
				nuevoSoliServ.setArticles3(normasUnicas.get(2).articles);
				
				log.info("  Norma asignada: '" + normasUnicas.get(2).norma + "' (solicitud="
						+ normasUnicas.get(2).solicitudId + ", servei=" + normasUnicas.get(2).serveiId + ")");
			}

			definitius.add(nuevoSoliServ);
		}
		
		return definitius;

	}
	
	
	
	
	
	
	
	
	
	
	//===========================================================
	//===================    Auxiliares     =====================
	//===========================================================
		
	public static Timestamp parseTimestamp(String dateStr) {
		if (dateStr == null || dateStr.isEmpty())
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy, hh:mm:ss a", Locale.ENGLISH);
			Date parsedDate = sdf.parse(dateStr.trim());
			return new Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			System.err.println("No se pudo parsear la fecha: " + dateStr + ": " + e.getMessage());
			return null;
		}
	}

	private String extractDir3(String organString) {
		if (organString == null)
			return null;
		int start = organString.indexOf("(");
		int end = organString.indexOf(")");
		if (start != -1 && end != -1 && end > start) {
			return organString.substring(start + 1, end).trim();
		}
		return null;
	}

	private Long extratEstat(String prefix, Long[] estats, String estatStr) {
		for (Long estat : estats) {
			if (I18NUtils.tradueix(prefix + estat).equals(estatStr)) {
				return estat;
			}
		}
		return null;
	}

	private Long extratEstat(String prefix, long[] estats, String estatStr) {
		for (long estat : estats) {
			if (I18NUtils.tradueix(prefix + estat).equals(estatStr)) {
				return estat; // autoboxing a Long
			}
		}
		return null;
	}

	private String getTipusDocFromID(String id) {
		log.info("getTipusDocFromID: " + id);
		
		if (id == null) {
			return null;
		}
		
		String lang = "ca";        
        List<TipusProcediment> tipus = TipusProcediments.getAllTipusProcediments();
		for (TipusProcediment tp : tipus) {
			if (tp.id == Long.valueOf(id)) {
				String text;
				if (lang.equals("es")) {
					text = tp.castella;
				} else {
					text = tp.catala;
				}
				return text;
			}
		}
        return null;
        
	}
	
	private Long getTipusDocIDFromText(String text) {
		log.info("getTipusDocIDFromText: " + text);

		if (text == null) {
			return null;
		}

		String lang = "ca";
		List<TipusProcediment> tipus = TipusProcediments.getAllTipusProcediments();
		for (TipusProcediment tp : tipus) {
			String cmp;
			if (lang.equals("es")) {
				cmp = tp.castella;
			} else {
				cmp = tp.catala;
			}
			if (cmp.equals(text)) {
				return tp.id;
			}
		}
		return null;

	}
	
}
