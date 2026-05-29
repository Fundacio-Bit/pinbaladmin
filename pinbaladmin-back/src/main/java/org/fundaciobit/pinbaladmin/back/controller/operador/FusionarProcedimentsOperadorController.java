package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments.TipusProcediment;
import org.fundaciobit.pinbaladmin.logic.ContacteLogicaService;
import org.fundaciobit.pinbaladmin.logic.DocumentLogicaService;
import org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.EntitatServeiLogicService;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.CrearExcelDeServeis;
import org.fundaciobit.pinbaladmin.logic.utils.GenerarDocumentsDGLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.EntitatServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
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

	@EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
	protected InfoMadridLogicaService infoMadridLogicaEjb;

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerPublicLogicaEjb;
	
	@EJB(mappedName = ContacteLogicaService.JNDI_NAME)
	protected ContacteLogicaService contacteLogicaEjb;
	
	@EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
	protected TramitAPersAutLogicaService tramitALogicEjb;
    
    @EJB(mappedName = GenerarDocumentsDGLogicaService.JNDI_NAME)
    protected GenerarDocumentsDGLogicaService generarDocumentsDGLogicaEjb;


	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

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

			Where wLocals = SolicitudFields.ORGANID.isNotNull();
			Where wNoFusionadas = SolicitudFields.ESTATSOLICITUD.notEqual(Constants.SOLI_ESTAT_FUSIONADA);

			Where wID = null;
			Where wCodi = SolicitudFields.PROCEDIMENTCODI.like("%" + param + "%");
			Where wNom = SolicitudFields.PROCEDIMENTNOM.like("%" + param + "%");

			// 1. Buscar por ID si es número
			try {
				Long id = Long.parseLong(param);
				wID = SolicitudFields.SOLICITUDID.equal(id);
//				results.addAll(solicitudLogicaEjb.select(SolicitudFields.SOLICITUDID.equal(id)));
			} catch (NumberFormatException ignored) {
			}

			results.addAll(solicitudLogicaEjb.select(Where.AND(wLocals, wNoFusionadas, Where.OR(wID, wCodi, wNom))));

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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCodi() {
            return codi;
        }

        public void setCodi(String codi) {
            this.codi = codi;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCodi() {
            return codi;
        }

        public void setCodi(String codi) {
            this.codi = codi;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getCedent() {
            return cedent;
        }

        public void setCedent(String cedent) {
            this.cedent = cedent;
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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public Long getTipus() {
            return tipus;
        }

        public void setTipus(Long tipus) {
            this.tipus = tipus;
        }

        public Long getSolicitudID() {
            return solicitudID;
        }

        public void setSolicitudID(Long solicitudID) {
            this.solicitudID = solicitudID;
        }
		
		
		
	}

	public class ConsentimentDTO {
		private Long fitxerID;
		private String nomFitxer;
		private String tipus;
		private Long solicitudID;
		private String url;

		public ConsentimentDTO(SolicitudJPA soli) {
			this.fitxerID = soli.getFitxerConsentimentID();

			if (this.fitxerID != null) {
				FitxerJPA fitxer = fitxerPublicLogicaEjb.findByPrimaryKey(this.fitxerID);
				if (fitxer != null) {
					this.nomFitxer = fitxer.getNom();
				}
			}

			this.tipus = I18NUtils.tradueix("consentiment.tipus." + soli.getConsentiment());

			this.solicitudID = soli.getSolicitudID();
			this.url = soli.getUrlconsentiment();
		}

        public Long getFitxerID() {
            return fitxerID;
        }

        public void setFitxerID(Long fitxerID) {
            this.fitxerID = fitxerID;
        }

        public String getNomFitxer() {
            return nomFitxer;
        }

        public void setNomFitxer(String nomFitxer) {
            this.nomFitxer = nomFitxer;
        }

        public String getTipus() {
            return tipus;
        }

        public void setTipus(String tipus) {
            this.tipus = tipus;
        }

        public Long getSolicitudID() {
            return solicitudID;
        }

        public void setSolicitudID(Long solicitudID) {
            this.solicitudID = solicitudID;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
		
		
		
		
	}

	public class SolicitudFullDTO {
		private Long solicitudID;
		private String procedimentCodi;
		private String codiDescriptiu;
//		private String codiSiaConv;
		private String procedimentNom;
		private String procedimentTipus;
		private Timestamp dataInici;
		private Timestamp dataCaducitat;
		private String creador;
//		private String personaContacte;
//		private String personaContacteEmail;
//		private String responsableProcNom;
//		private String responsableProcEmail;
//		private String titularFirmaNIF;
//		private String titularFirmaNom;
//		private String titularFirmaLlinatges;
//		private String titularFirmaEmail;

		private String entitatNom;
		private String entitatCif;
		private String entitatDir3;

//		private String notes;
//		private String consentiment;
//		private String urlconsentiment;
//		private String consentimentadjunt;

		private String organid;
		private String estatSolicitud;
		private String estatpinbal;

		private List<ServeiDTO> servicios;
		private List<DocumentDTO> documentos;

		private ConsentimentDTO consentiment;

		public SolicitudFullDTO(Long solicitudID) throws I18NException {
			SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(solicitudID);

			this.solicitudID = soli.getSolicitudID();
			this.procedimentCodi = soli.getProcedimentCodi();
			this.codiDescriptiu = soli.getCodiDescriptiu();
//			this.codiSiaConv = soli.getCodiSiaConv();
			this.procedimentNom = soli.getProcedimentNom();
			this.dataInici = soli.getDataInici();
			this.dataCaducitat = soli.getDataCaducitat();
			this.creador = soli.getCreador();
//			this.personaContacte = soli.getPersonaContacte();
//			this.personaContacteEmail = soli.getPersonaContacteEmail();
//			this.responsableProcNom = soli.getResponsableProcNom();
//			this.responsableProcEmail = soli.getResponsableProcEmail();

//			this.titularFirmaNIF = soli.getTitularFirmaNif();
//			this.titularFirmaNom = soli.getTitularFirmaNom();
//			this.titularFirmaEmail = soli.getTitularFirmaEmail();
//			this.titularFirmaLlinatges = soli.getTitularFirmaLlinatges();

			this.entitatNom = soli.getDenominacio();
			this.entitatCif = soli.getNif();
			this.entitatDir3 = soli.getDir3();

//			this.notes = soli.getNotes();
//			this.consentiment = soli.getConsentiment();
//			this.urlconsentiment = soli.getUrlconsentiment();
//			this.consentimentadjunt = soli.getConsentimentadjunt();

			OrganJPA organ = organLogicaEjb.findByPrimaryKey(soli.getOrganid());

			this.organid = organ != null ? "(" + organ.getDir3() + ") " + organ.getNom() : "";
			this.estatSolicitud = I18NUtils.tradueix("solicitud.estat." + soli.getEstatSolicitud());
			this.estatpinbal = I18NUtils.tradueix("estat.pinbal." + soli.getEstatpinbal());
//			this.procedimentTipus = soli.getProcedimentTipus();

			log.info("ProcedimentTipus ID: " + soli.getProcedimentTipus());
			String tipusText = getTipusDocFromID(soli.getProcedimentTipus());
			log.info("ProcedimentTipus Text: " + tipusText);
			this.procedimentTipus = tipusText;

			List<ServeiDTO> serveisList = solicitudServeiLogicaEjb
					.select(SolicitudServeiFields.SOLICITUDID.equal(solicitudID)).stream()
					.map(ss -> new ServeiDTO(ss.getServeiID())).collect(Collectors.toList());

			this.servicios = serveisList;

			List<DocumentDTO> docsList = documentSolicitudLogicaEjb
					.select(DocumentSolicitudFields.SOLICITUDID.equal(solicitudID),
							new OrderBy(DocumentSolicitudFields.DOCUMENTID))
					.stream().map(ds -> new DocumentDTO(ds.getDocumentID(), solicitudID)).collect(Collectors.toList());

			this.documentos = docsList;

			this.consentiment = new ConsentimentDTO(soli);

		}

        public Long getSolicitudID() {
            return solicitudID;
        }

        public void setSolicitudID(Long solicitudID) {
            this.solicitudID = solicitudID;
        }

        public String getProcedimentCodi() {
            return procedimentCodi;
        }

        public void setProcedimentCodi(String procedimentCodi) {
            this.procedimentCodi = procedimentCodi;
        }

        public String getCodiDescriptiu() {
            return codiDescriptiu;
        }

        public void setCodiDescriptiu(String codiDescriptiu) {
            this.codiDescriptiu = codiDescriptiu;
        }

        public String getProcedimentNom() {
            return procedimentNom;
        }

        public void setProcedimentNom(String procedimentNom) {
            this.procedimentNom = procedimentNom;
        }

        public String getProcedimentTipus() {
            return procedimentTipus;
        }

        public void setProcedimentTipus(String procedimentTipus) {
            this.procedimentTipus = procedimentTipus;
        }

        public Timestamp getDataInici() {
            return dataInici;
        }

        public void setDataInici(Timestamp dataInici) {
            this.dataInici = dataInici;
        }

        public Timestamp getDataCaducitat() {
            return dataCaducitat;
        }

        public void setDataCaducitat(Timestamp dataCaducitat) {
            this.dataCaducitat = dataCaducitat;
        }

        public String getCreador() {
            return creador;
        }

        public void setCreador(String creador) {
            this.creador = creador;
        }

//        public String getPersonaContacte() {
//            return personaContacte;
//        }
//
//        public void setPersonaContacte(String personaContacte) {
//            this.personaContacte = personaContacte;
//        }
//
//        public String getPersonaContacteEmail() {
//            return personaContacteEmail;
//        }
//
//        public void setPersonaContacteEmail(String personaContacteEmail) {
//            this.personaContacteEmail = personaContacteEmail;
//        }
//
//        public String getResponsableProcNom() {
//            return responsableProcNom;
//        }
//
//        public void setResponsableProcNom(String responsableProcNom) {
//            this.responsableProcNom = responsableProcNom;
//        }
//
//        public String getResponsableProcEmail() {
//            return responsableProcEmail;
//        }
//
//        public void setResponsableProcEmail(String responsableProcEmail) {
//            this.responsableProcEmail = responsableProcEmail;
//        }

//        public String getTitularFirmaNIF() {
//            return titularFirmaNIF;
//        }
//
//        public void setTitularFirmaNIF(String titularFirmaNIF) {
//            this.titularFirmaNIF = titularFirmaNIF;
//        }

//        public String getTitularFirmaNom() {
//            return titularFirmaNom;
//        }
//
//        public void setTitularFirmaNom(String titularFirmaNom) {
//            this.titularFirmaNom = titularFirmaNom;
//        }

//        public String getTitularFirmaLlinatges() {
//            return titularFirmaLlinatges;
//        }
//
//        public void setTitularFirmaLlinatges(String titularFirmaLlinatges) {
//            this.titularFirmaLlinatges = titularFirmaLlinatges;
//        }

//        public String getTitularFirmaEmail() {
//            return titularFirmaEmail;
//        }
//
//        public void setTitularFirmaEmail(String titularFirmaEmail) {
//            this.titularFirmaEmail = titularFirmaEmail;
//        }

        public String getEntitatNom() {
            return entitatNom;
        }

        public void setEntitatNom(String entitatNom) {
            this.entitatNom = entitatNom;
        }

        public String getEntitatCif() {
            return entitatCif;
        }

        public void setEntitatCif(String entitatCif) {
            this.entitatCif = entitatCif;
        }

        public String getEntitatDir3() {
            return entitatDir3;
        }

        public void setEntitatDir3(String entitatDir3) {
            this.entitatDir3 = entitatDir3;
        }

        public String getOrganid() {
            return organid;
        }

        public void setOrganid(String organid) {
            this.organid = organid;
        }

        public String getEstatSolicitud() {
            return estatSolicitud;
        }

        public void setEstatSolicitud(String estatSolicitud) {
            this.estatSolicitud = estatSolicitud;
        }

        public String getEstatpinbal() {
            return estatpinbal;
        }

        public void setEstatpinbal(String estatpinbal) {
            this.estatpinbal = estatpinbal;
        }

        public List<ServeiDTO> getServicios() {
            return servicios;
        }

        public void setServicios(List<ServeiDTO> servicios) {
            this.servicios = servicios;
        }

        public List<DocumentDTO> getDocumentos() {
            return documentos;
        }

        public void setDocumentos(List<DocumentDTO> documentos) {
            this.documentos = documentos;
        }

        public ConsentimentDTO getConsentiment() {
            return consentiment;
        }

        public void setConsentiment(ConsentimentDTO consentiment) {
            this.consentiment = consentiment;
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
	public String procesarFusion(HttpServletRequest request, HttpServletResponse response)
			throws IOException, I18NException {

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

		String consentimientoParam = request.getParameter("consentiment"); // El ID del procedimiento que aporta el
																			// consentimiento

		// ================================
		// Campos simples
		// ================================
		SolicitudJPA solicitudNueva = crearSolicicitudCampos(request);
		solicitudNueva.setOperador(request.getRemoteUser());

		afegirConsentimentInfo(solicitudNueva, consentimientoParam);

		List<SolicitudJPA> solicitudes = procesarMultiples(fusionados, solicitudNueva);

		InfoMadridJPA infoMadrid = crearNouInfoMadrid(solicitudes, solicitudNueva);

		List<SolicitudServeiJPA> serviciosNuevos = getServiciosSolicitud(fusionados, servicios);

		fusionar(solicitudNueva, fusionados, serviciosNuevos, documentos, infoMadrid);

		marcarFusionadas(solicitudes, solicitudNueva.getSolicitudID());

//		return "redirect:/operador/fusionarprocediments/elegirProcediments";
		return "redirect:/operador/solicitudfullview/view/" + solicitudNueva.getSolicitudID();
	}

	private void afegirConsentimentInfo(SolicitudJPA solicitudNueva, String consentimientoParam) {

		log.info("Procesando consentimiento de la solicitud ID: " + consentimientoParam);

		if (consentimientoParam != null && !consentimientoParam.isEmpty()) {
			try {
				Long soliID = Long.parseLong(consentimientoParam);
				SolicitudJPA soliConsentiment = solicitudLogicaEjb.findByPrimaryKey(soliID);
				if (soliConsentiment != null) {
					solicitudNueva.setConsentiment(soliConsentiment.getConsentiment());

					solicitudNueva.setFitxerConsentimentID(soliConsentiment.getFitxerConsentimentID());
					solicitudNueva.setUrlconsentiment(soliConsentiment.getUrlconsentiment());

					if (soliConsentiment.getFitxerConsentimentID() != null) {
						log.info("Consentimiento añadido de la solicitud ID " + soliID + ": fitxerID="
								+ soliConsentiment.getFitxerConsentimentID());
						solicitudNueva.setConsentimentadjunt(Constants.CONSENTIMENT_ADJUNT);
					} else {
						log.info("Consentimiento añadido de la solicitud ID " + soliID + ": URL="
								+ soliConsentiment.getUrlconsentiment());

						solicitudNueva.setConsentimentadjunt(Constants.CONSENTIMENT_PUBLICAT);
					}

				}
			} catch (NumberFormatException e) {
				log.warn("ID de consentimiento no válido: " + consentimientoParam);
			}
		}

		log.info("Consentimiento procesado: " + solicitudNueva.getConsentiment() + ", fitxerID="
				+ solicitudNueva.getFitxerConsentimentID() + ", url=" + solicitudNueva.getUrlconsentiment());
	}

	private List<SolicitudJPA> procesarMultiples(List<Long> fusionados, SolicitudJPA solicitudNueva) {
		boolean firmatDocSolicitud = true;
		boolean produccio = true;

		List<SolicitudJPA> fusionadas = new ArrayList<>();

		List<String> IDs = new ArrayList<>();
		List<String> codis = new ArrayList<>();
		List<String> notas = new ArrayList<>();
		Timestamp dataFi = null;

		for (Long soliID : fusionados) {
			SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);
			if (soli != null) {
				fusionadas.add(soli);
				IDs.add(soliID.toString());

				if (soli.getCodiSiaConv() != null && !soli.getCodiSiaConv().isEmpty()) {
					String[] parts = soli.getCodiSiaConv().split(",");
					for (String part : parts) {
						String trimmed = part.trim();
						if (!trimmed.isEmpty() && !codis.contains(trimmed) && trimmed.length() <= 20) {
							codis.add(trimmed);
						}
					}
				}

				if (soli.getNotes() != null && !soli.getNotes().isEmpty()) {
					notas.add(soli.getNotes().trim());
				}

				if (soli.getDataFi() != null) {
					if (dataFi == null || soli.getDataFi().after(dataFi)) {
						dataFi = soli.getDataFi();
					}
				}

				// Si tots estan firmats, el nou també ho està
				firmatDocSolicitud &= soli.isFirmatDocSolicitud();

				// Si tots estan en producció, el nou també ho està
				produccio &= soli.isProduccio();
			}
		}

		int maxLength = 255;

		String codiSiaConv = String.join(", ", codis);
		codiSiaConv = codiSiaConv.length() > maxLength ? codiSiaConv.substring(0, maxLength) : codiSiaConv;
		log.info("Codigos SIA concatenados: " + codiSiaConv);

		String notasFinal = "Procediment fusionat de : " + String.join(", ", IDs) + "\n----------------------\n"
				+ String.join("\n----------------------\n", notas);

		log.info("Notas concatenadas: " + notas.size() + " notas. Tamaño total: " + notasFinal.length());
		log.info(notasFinal);
		
		if (notasFinal.length() > 4000) {
			notasFinal = notasFinal.substring(0, 4000);
		}

		solicitudNueva.setCodiSiaConv(codiSiaConv);
		solicitudNueva.setNotes(notasFinal);
		solicitudNueva.setDataFi(dataFi);

		solicitudNueva.setFirmatDocSolicitud(firmatDocSolicitud);
		solicitudNueva.setProduccio(produccio);

		return fusionadas;
	}

	private InfoMadridJPA crearNouInfoMadrid(List<SolicitudJPA> solicitudes, SolicitudJPA solicitudNueva) {

		// Revisar el estado de los infoMadrid y asignar uno nuevo con informacion
		// correcta.

		InfoMadridJPA infoMadNou = new InfoMadridJPA();

		infoMadNou.setCodi(solicitudNueva.getProcedimentCodi());
		String consultaTexto = "Buenos días,\n" + "Enviamos solicitud para dar servicios de alta en el procedimiento "
				+ solicitudNueva.getProcedimentCodi() + "\n\n" + "Quedamos a la espera de su respuesta.\n"
				+ "Un saludo.";

		infoMadNou.setConsulta(consultaTexto);

//		int autorizados = 0;
//		int enviadosPreAltas = 0;
//		int consultados = 0;

		// Llistat d'intents per agafar el maxim

//		List<String[]> titularsList = new ArrayList<>();
		Long maxIntents = 0L;

		Timestamp dataAutFinal = null;
		Timestamp dataEnviFinal = null;
		Timestamp dataConsFinal = null;

		String titularNom = null;
		String titularNif = null;

		List<String> mensajes = new ArrayList<>();
		String mensajeFinal = "";

		List<Long> estadosPinbal = new ArrayList<>();

		//boolean todasEnviadas = true;
		//boolean todasAutorizadas = true;

		for (SolicitudJPA soli : solicitudes) {
			if (soli != null && soli.getInfomadridid() != null) {
				InfoMadridJPA infoMad = infoMadridLogicaEjb.findByPrimaryKey(soli.getInfomadridid());

				if (infoMad == null) {
					log.warn("La solicitud ID " + soli.getSolicitudID() + " no tiene InfoMadrid asociado (ID "
							+ soli.getInfomadridid() + ").");
					continue;
				}

				Long estatPinbal = soli.getEstatpinbal();
				if (estatPinbal != null && !estadosPinbal.contains(estatPinbal)) {
					estadosPinbal.add(estatPinbal);
				}

				Timestamp dataAut = infoMad.getDataAutoritzacio();

				if (dataAut != null) {
					// Se queda la fecha más reciente
//					autorizados++;

					if (dataAutFinal == null || dataAut.after(dataAutFinal)) {
						dataAutFinal = dataAut;
					}
				} else {
					//todasAutorizadas = false;
				}

				Timestamp dataEnvi = infoMad.getDataEnviament();
				if (dataEnvi != null) {
					// Se queda la fecha más reciente
					if (dataEnviFinal == null || dataEnvi.after(dataEnviFinal)) {
						dataEnviFinal = dataEnvi;
					}
				} else {
					//todasEnviadas = false;
				}

				Timestamp dataCons = infoMad.getDataConsulta();
				if (dataCons != null) {
					// Se queda la fecha más reciente
					if (dataConsFinal == null || dataCons.after(dataConsFinal)) {
						dataConsFinal = dataCons;
					}
				}

				Long intents = infoMad.getIntents();
				if (intents != null && intents > maxIntents) {
					maxIntents = intents;
				}

				Long nouTitularInfoMadContacteID = solicitudNueva.getContacteSolicitantID();
				Contacte solicitant = contacteLogicaEjb.findByPrimaryKey(nouTitularInfoMadContacteID);
				
				titularNom = solicitant.getNombrecompleto();
				titularNif = solicitant.getNif();

				// Si el mensaje ya lo tenemos, no lo añadimos
				if (infoMad.getMissatge() != null && !mensajes.contains(infoMad.getMissatge())) {
					mensajes.add(infoMad.getMissatge());
					mensajeFinal += "Procediment " + infoMad.getCodi() + " - " + infoMad.getDataConsulta() + ":\n";
					mensajeFinal += infoMad.getMissatge() + "\n----------------------\n";
				}

			}
		}
		
		log.info("Mensajes concatenados: " + mensajes.size() + " mensajes. Tamaño total: " + mensajeFinal.length());
		log.info(mensajeFinal);
		
		if (mensajeFinal.length() > 1024) {
			mensajeFinal = mensajeFinal.substring(0, 1024);
		}

		infoMadNou.setDataAutoritzacio(dataAutFinal);
		infoMadNou.setDataEnviament(dataEnviFinal);
		infoMadNou.setDataConsulta(dataConsFinal);

		infoMadNou.setIntents(maxIntents);

		infoMadNou.setTitularNom(titularNom);
		infoMadNou.setTitularNif(titularNif);

		infoMadNou.setEstatProcediment(solicitudNueva.getEstatSolicitud());
		infoMadNou.setEstatProcediment(solicitudNueva.getEstatpinbal());

//		Long estatPinbal = null;
//
//		if (estadosPinbal.size() == 1) {
//			estatPinbal = estadosPinbal.get(0);
//		} else if (estadosPinbal.size() == 0) {
//			estatPinbal = Constants.ESTAT_PINBAL_NO_SOLICITAT;
//		} else {
//			// Solicitudes con estados Madrid diferentes.
//
//			// Si hay alguna no enviada, el estado puede ser o NO_SOLICITAT (si no habia
//			// ninguna autorizada) o AUTORITZA
//			if (!todasEnviadas) {
//				estatPinbal = Constants.ESTAT_PINBAL_NO_SOLICITAT;
//			} else {
//				// Si todas están enviadas
//			}
//
//			// Si la fecha de autorización es nula, y la de envio no, está pendiente de
//			// tramitar.
//			if (dataAutFinal == null && dataEnviFinal != null) {
//				estatPinbal = Constants.ESTAT_PINBAL_PENDENT_TRAMITAR;
//			} else if (dataAutFinal != null) {
//				estatPinbal = Constants.ESTAT_PINBAL_AUTORITZAT;
//			} else {
//				estatPinbal = Constants.ESTAT_PINBAL_NO_SOLICITAT;
//			}
//
//		}
//
//		infoMadNou.setEstatAutoritzacio(estatPinbal);

		infoMadNou.setMissatge(mensajeFinal);

		return infoMadNou;

	}


	private void fusionar(SolicitudJPA solicitudNueva, List<Long> fusionados, List<SolicitudServeiJPA> serviciosNuevos,
			List<Long> documentos, InfoMadridJPA infoMad) throws I18NException {

		InfoMadrid im = infoMadridLogicaEjb.create(infoMad);
		Long infoMadridID = im.getInfoMadridID();
		log.info("InfoMadrid creado ID: " + infoMadridID);
		solicitudNueva.setInfomadridid(infoMadridID);
//		solicitudNueva.setEstatpinbal(im.getEstatAutoritzacio());

		Solicitud soli = solicitudLogicaEjb.create(solicitudNueva);
		Long nuevaSolicitudID = soli.getSolicitudID();

		List<Event> eventos = eventLogicaEjb.select(EventFields.SOLICITUDID.in(fusionados));

		for (Event ev : eventos) {
			ev.setSolicitudID(nuevaSolicitudID);
			eventLogicaEjb.update(ev);
		}

//		Set<SolicitudServeiJPA> set = new HashSet<>();
		for (SolicitudServeiJPA ss : serviciosNuevos) {
			ss.setSolicitudID(nuevaSolicitudID);
			solicitudServeiLogicaEjb.create(ss);
//			SolicitudServei solSer = solicitudServeiLogicaEjb.create(ss);
//			set.add((SolicitudServeiJPA) solSer);
		}
//		solicitudNueva.setSolicitudServeis(set);

//		List<DocumentSolicitud> docs = documentSolicitudLogicaEjb.select(Where.AND(
//				DocumentSolicitudFields.SOLICITUDID.in(fusionados), DocumentSolicitudFields.DOCUMENTID.in(documentos)));
//		
//		for (DocumentSolicitud ds : docs) {
//			ds.setSolicitudID(nuevaSolicitudID);
//			documentSolicitudLogicaEjb.update(ds);
//		}

		try {
			// Gestión de documentos.
			generarNousDocumentsSolicitud(solicitudNueva, fusionados, documentos);
		} catch (I18NException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Falta borrar todas las solicitudes originales.
		log.info("Fusión completada: nueva solicitud ID " + solicitudNueva.getSolicitudID());

	}

	private void generarNousDocumentsSolicitud(SolicitudJPA solicitudNueva, List<Long> fusionados,
			List<Long> documentos) throws I18NException, Exception {

		Long nuevaSolicitudID = solicitudNueva.getSolicitudID();
		solicitudNueva = solicitudLogicaEjb.findByPrimaryKeyFull(nuevaSolicitudID);

		// Todo lo que tenga un documento firmado, o se quiera guardar manualmente, se
		// mantiene.
		// Excels y plantillas fuera y se generan nuevas.

		List<DocumentSolicitud> docs = documentSolicitudLogicaEjb
				.select(DocumentSolicitudFields.SOLICITUDID.in(fusionados));

		for (DocumentSolicitud ds : docs) {
			Document doc = documentLogicaEjb.findByPrimaryKey(ds.getDocumentID());
			// Si está firmado, esta marcado para guardar, o es de tipo ALTRTES, se mantiene
			if (doc.getFitxerFirmatID() != null || documentos.contains(doc.getDocumentID())
					|| doc.getTipus() == Constants.DOCUMENT_SOLICITUD_ALTRES) {
				ds.setSolicitudID(nuevaSolicitudID);
				documentSolicitudLogicaEjb.update(ds);
			} else {
				// Borrar documento y su asociación
				documentSolicitudLogicaEjb.delete(ds);
				documentLogicaEjb.delete(doc);
			}
		}

		// Ahora guardamos los ficherosXML y documentos de Solicitud de las solicitudes.
		List<Solicitud> solicitudesOriginals = solicitudLogicaEjb.select(SolicitudFields.SOLICITUDID.in(fusionados));
		for (Solicitud soli : solicitudesOriginals) {
			Long fitxerXMLID = soli.getSolicitudXmlID();
			FitxerJPA fitxerJpa = fitxerPublicLogicaEjb.findByPrimaryKey(fitxerXMLID);
			// Crear Document i DocumentSolicitud.
			if (fitxerJpa == null) {
				log.warn("La solicitud ID " + soli.getSolicitudID() + " no tiene fichero XML asociado (ID "
						+ fitxerXMLID + "). No se podrá copiar el formulario.");
			} else {

				afegirDocumentSolicitudAmbFitxer(fitxerJpa,
						"formulari_" + soli.getSolicitudID() + "_" + soli.getDataInici() + ".xml",
						Constants.DOCUMENT_SOLICITUD_XML_SOLI, nuevaSolicitudID);
			}

			Long docSoliID = soli.getDocumentSolicitudID();
			if (docSoliID == null) {
				log.warn("La solicitud ID " + soli.getSolicitudID() + " no tiene documento de solicitud asociado (ID "
						+ docSoliID + "). No se podrá copiar el documento de solicitud.");

			} else {

				FitxerJPA fitxerDocSoli = fitxerPublicLogicaEjb.findByPrimaryKey(docSoliID);
				afegirDocumentSolicitudAmbFitxer(fitxerDocSoli,
						"document_solicitud_" + soli.getSolicitudID() + "_" + soli.getDataInici() + ".pdf",
						Constants.DOCUMENT_SOLICITUD_ALTRES, nuevaSolicitudID);
			}
		}

		// Ahora añadimos los nuevos documentos que se tienen que generar:
		// Excel de locales y estatales, plantilla ODT y PDF.

		//Long organid = solicitudNueva.getOrganid();

		//Properties prop;
		
        Fitxer docConsentiment = null;
        Long docConsentID = solicitudNueva.getFitxerConsentimentID();
        if (docConsentID != null) {
			docConsentiment = fitxerPublicLogicaEjb.findByPrimaryKey(docConsentID);
        }
        
		generarDocumentsDGLogicaEjb.generarFormulariDirectorGeneralPDFODT(nuevaSolicitudID);


//		generarDocumentsSolicitud(nuevaSolicitudID, organid, prop);
		generarExcelDeServeis(solicitudNueva, docConsentiment);

		/*
		 * Lista de documentos que hay que regenerar:
		 * 
		 * Documento de la solicitud PDF XML de la solicitud
		 * 
		 * Formulario para el DG (PDF) Formulario para el DG (ODT)
		 * 
		 * Excel de procedimientos Locales Excel de procedimientos Estatales
		 * 
		 */
	}

	public void generarExcelDeServeis(SolicitudJPA soli, Fitxer docConsentiment) throws Exception, I18NException {

		Long solicitudID = soli.getSolicitudID();
		log.info("generaPlantillaExcelDeServeis(); => SOLI = " + solicitudID);

		File plantillaXLSX = new File(Configuracio.getTemplateServeisExcel());

		String[] excels = { "locals", "estatals" };

		for (String excel : excels) {
			log.info("Generant Excel de Serveis: " + excel);
			byte[] data = CrearExcelDeServeis.crearExcelDeServeis(plantillaXLSX, soli, excel, docConsentiment);

			if (data == null) {
				String msg = "No hi ha serveis " + excel + " per a la sol·licitud";
				log.warn(msg);
				continue;
			}
			
			// locals_2019-12-31_12:26_Plantilla-Procedimientos.xlsx
			String nom = excel + "_" + SDF.format(new Date()) + "_" + plantillaXLSX.getName();

			FitxerJPA fitxer = new FitxerJPA(nom, data.length,
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", null);
			fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

			FileSystemManager.crearFitxer(new ByteArrayInputStream(data), fitxer.getFitxerID());

			Long tipus = Constants.DOCUMENT_SOLICITUD_EXCEL_SERVEIS;
			afegirDocumentSolicitudAmbFitxer(fitxer, nom, tipus, solicitudID);
		}
	}

	private void afegirDocumentSolicitudAmbFitxer(FitxerJPA fitxer, String nom, Long tipus, Long soliID)
			throws I18NException {

		Document doc = documentLogicaEjb.create(nom, fitxer.getFitxerID(), null, null, tipus);

		DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), soliID);

		documentSolicitudLogicaEjb.create(ds);
		log.info("Afegit document: " + nom + " a la solicitud: " + soliID);

	}

	private SolicitudJPA crearSolicicitudCampos(HttpServletRequest request) throws I18NException {

		String procedimentCodi = request.getParameter("procedimentCodi");
		String codiDescriptiu = request.getParameter("codiDescriptiu");
//		String codiSiaConv = request.getParameter("codiSiaConv");
		String procedimentNom = request.getParameter("procedimentNom");
		Timestamp dataInici = parseTimestamp(request.getParameter("dataInici"));
		Timestamp dataCaducitat = parseTimestamp(request.getParameter("dataCaducitat"));

		String creador = request.getParameter("creador");

//		String personaContacte = request.getParameter("personaContacte");
//		String personaContacteEmail = request.getParameter("personaContacteEmail");
//		String responsableProcNom = request.getParameter("responsableProcNom");
//		String responsableProcEmail = request.getParameter("responsableProcEmail");

//		String titularFirmaNIF = request.getParameter("titularFirmaNIF");
//		String titularFirmaNom = request.getParameter("titularFirmaNom");
//		String titularFirmaEmail = request.getParameter("titularFirmaEmail");
//		String titularFirmaLlinatges = request.getParameter("titularFirmaLlinatges");

		String entitatNom = request.getParameter("entitatNom");
		String entitatCif = request.getParameter("entitatCif");
		String entitatDir3 = request.getParameter("entitatDir3");

		String organParam = request.getParameter("organid");
		log.info("Organ recibido: " + organParam);

		String dir3 = extractDir3(organParam);
		Long organId = organLogicaEjb.executeQueryOne(OrganFields.ORGANID, OrganFields.DIR3.equal(dir3));
		log.info("Organ extraído: dir3=" + dir3 + ", organId=" + organId);

		String estatSolicitud = request.getParameter("estatSolicitud");
		Long estatSolicitudId = extratEstat("solicitud.estat.", Constants.ESTATS_SOLI, estatSolicitud);

		String estatpinbal = request.getParameter("estatpinbal");
		log.info("estatpinbal recibido: " + estatpinbal);
		Long estatpinbalId = extratEstat("estat.pinbal.", Constants.ESTATS_PINBAL, estatpinbal);
		log.info("estatpinbalId extraído: " + estatpinbalId);

//		String procedimentTipus = request.getParameter("procedimentTipus");

		String procedimentTipusText = request.getParameter("procedimentTipus");
		String procedimentTipus = String.valueOf(getTipusDocIDFromText(procedimentTipusText));

		// ================================
		// Log para depuración
		// ================================
		log.info("procedimentCodi: " + procedimentCodi);
		log.info("codiDescriptiu: " + codiDescriptiu);
//		log.info("codiSiaConv: " + codiSiaConv);
		log.info("procedimentNom: " + procedimentNom);
		log.info("dataInici: " + dataInici);
		log.info("dataCaducitat: " + dataCaducitat);

		log.info("creador: " + creador);

//		log.info("personaContacte: " + personaContacte);
//		log.info("personaContacteEmail: " + personaContacteEmail);
//		log.info("responsableProcNom: " + responsableProcNom);
//		log.info("responsableProcEmail: " + responsableProcEmail);

//		log.info("titularFirmaNif: " + titularFirmaNIF);
//		log.info("titularFirmaNom: " + titularFirmaNom);
//		log.info("titularFirmaEmail: " + titularFirmaEmail);
//		log.info("titularFirmaLlinatges: " + titularFirmaLlinatges);

		log.info("entitatNom: " + entitatNom);
		log.info("entitatCif: " + entitatCif);
		log.info("entitatDir3: " + entitatDir3);

		log.info("organId: " + organId);
		log.info("estatSolicitud: " + estatSolicitudId);
		log.info("estatpinbal: " + estatpinbalId);
		log.info("procedimentTipus: " + procedimentTipus);

//		SolicitudJPA solicitudNueva = new SolicitudJPA();
//
//		solicitudNueva.setProcedimentCodi(procedimentCodi);
//		solicitudNueva.setCodiDescriptiu(codiDescriptiu);
////		solicitudNueva.setCodiSiaConv(codiSiaConv);
//		solicitudNueva.setProcedimentNom(procedimentNom);
//		solicitudNueva.setProcedimentTipus(procedimentTipus);
//		solicitudNueva.setDataInici(dataInici);
//		solicitudNueva.setDataCaducitat(dataCaducitat);
//		solicitudNueva.setDataFi(null);
//		
//		solicitudNueva.setCreador(creador);
//		solicitudNueva.setPersonaContacte(personaContacte);
//		solicitudNueva.setPersonaContacteEmail(personaContacteEmail);
//		solicitudNueva.setResponsableProcNom(responsableProcNom);
//		solicitudNueva.setResponsableProcEmail(responsableProcEmail);
//		
//		solicitudNueva.setTitularFirmaNif(firmaTitularNif);
//		solicitudNueva.setTitularFirmaNom(titularFirmaNom);
//		solicitudNueva.setDenominacio(entitatNom);
//		solicitudNueva.setNif(entitatCif);
//		solicitudNueva.setDir3(entitatDir3);
//		
//		
//		solicitudNueva.setOrganid(organId);
//		solicitudNueva.setEstatSolicitud(estatSolicitudId);
//		solicitudNueva.setEstatpinbal(estatpinbalId);

		// Se rellenan estos campos despues en la fusión
		String operador = null;

		String codiSiaConv = null;
		String notesSoli = null;
		Timestamp dataFi = null;
		boolean firmatDocSolicitud = false;
		boolean produccio = false;

		String consentiment = null;
		String urlconsentiment = null;
		String consentimentadjunt = null;
		Long fitxerConsentimentID = null;

		Long infoMadridID = null;
//		Long estatpinbal = null;

		// Estos campos son nulos por ser locales, o porque siempre lo son:
		String expedientPid = null;
		String entitatEstatal = null;
		String pinfo = null;
		
		//Datos de contactos pendientes de arreglar.
		String titularFirmaNIFOld = null;
		String titularFirmaMailOld= null;
		String titularFirmaNomOld = null;
		String respoProcNomOld = null;
		String respoProcEmailOld = null;
		String personaContacteOld = null;
		String personaContacteEmailOld = null;
		
		
		
		
		Long contacteTitularID = null;
		Long contacteResponsableID = null;
		Long contactePersonaID = null;
		Long contacteSolicitantID = null;
		Long contacteGestAutID = null;
		Long contacteAuditoriaID = null; 
		Long contacteTecnicID = null;

		// Y estos son nulos por ser una fusión nueva:
		Long docSoliID = null;
		Long solicitudXmlID = null;
		Long portafibID = null;
		
		//Este campo es para saber que solicitud es la fusionada, es para las antiguas. Es null para las nuevas fusiones.
		Long solicitudFusionadaID = null;

		SolicitudJPA solicitudNueva = new SolicitudJPA(procedimentCodi, codiDescriptiu, codiSiaConv, procedimentNom,
				procedimentTipus, organId, estatSolicitudId, expedientPid, entitatEstatal, pinfo, dataInici, dataFi,
				    notesSoli, docSoliID,
				solicitudXmlID, firmatDocSolicitud, produccio, entitatNom, entitatDir3, entitatCif, creador, operador,
				estatpinbalId, consentiment, urlconsentiment, consentimentadjunt, portafibID, infoMadridID, dataCaducitat,
				fitxerConsentimentID, contacteTitularID,  
				 solicitudFusionadaID, contacteResponsableID, contactePersonaID, contacteSolicitantID,
				contacteGestAutID, contacteAuditoriaID, contacteTecnicID, titularFirmaNIFOld, personaContacteOld, personaContacteEmailOld, respoProcNomOld,
				respoProcEmailOld, titularFirmaNomOld, titularFirmaMailOld);

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

			String notes = "";

			String caduca = null;
			String dataCad = null;

			List<SolicitudServei> soliServsBySoli = solicitudServeiLogicaEjb.select(Where.AND(
					SolicitudServeiFields.SOLICITUDID.in(fusionados), SolicitudServeiFields.SERVEIID.equal(serveiID)));

			List<NormaInfo> normas = new ArrayList<>();
			List<Long> estatsSoliSer = new ArrayList<>();
			for (SolicitudServei ss : soliServsBySoli) {
				Long serveiId = ss.getServeiID();
				Long solicitudId = ss.getSolicitudID();

				log.info("Procesando SoliServ: solicitudId=" + solicitudId + ", serveiId=" + serveiId);

				if (ss.getNormaLegal() != null && !ss.getNormaLegal().isEmpty()) {
					log.info("  Añadiendo norma1='" + ss.getNormaLegal() + "' (solicitud=" + solicitudId + ", servei="
							+ serveiId + ")");
					normas.add(new NormaInfo(ss.getNormaLegal(), ss.getFitxernormaID(), ss.getArticles(), solicitudId,
							serveiId));
				}
				if (ss.getNorma2() != null && !ss.getNorma2().isEmpty()) {
					log.info("  Añadiendo norma2='" + ss.getNorma2() + "' (solicitud=" + solicitudId + ", servei="
							+ serveiId + ")");
					normas.add(new NormaInfo(ss.getNorma2(), ss.getFitxernorma2ID(), ss.getArticles2(), solicitudId,
							serveiId));
				}
				if (ss.getNorma3() != null && !ss.getNorma3().isEmpty()) {
					log.info("  Añadiendo norma3='" + ss.getNorma3() + "' (solicitud=" + solicitudId + ", servei="
							+ serveiId + ")");
					normas.add(new NormaInfo(ss.getNorma3(), ss.getFitxernorma3ID(), ss.getArticles3(), solicitudId,
							serveiId));
				}

				notes += ss.getNotes() != null ? ss.getNotes() + "\n" : "";
				Long estatSoliSerID = ss.getEstatSolicitudServeiID();

				if (estatSoliSerID == null) {
					estatSoliSerID = Constants.ESTAT_SOLICITUD_SERVEI_PENDENT_AUTORITZAR;
				}

				if (!estatsSoliSer.contains(estatSoliSerID)) {
					log.info("  Añadiendo estatSolicitudServeiID=" + estatSoliSerID + " (solicitud=" + solicitudId
							+ ", servei=" + serveiId + ")");
					estatsSoliSer.add(estatSoliSerID);
				} else {
					log.info("  EstatSolicitudServeiID=" + estatSoliSerID + " ya añadido anteriormente.");
				}

				if (ss.getFechaCaduca() != null) {
					dataCad = ss.getFechaCaduca();
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

			Long estatSoliSerID = estatsSoliSer.size() > 0 ? estatsSoliSer.get(0)
					: Constants.ESTAT_SOLICITUD_SERVEI_PENDENT_AUTORITZAR;
			log.info("Asignando estatSolicitudServeiID=" + estatSoliSerID + " al nuevo SoliServ");
			nuevoSoliServ.setEstatSolicitudServeiID(estatsSoliSer.get(0));

			nuevoSoliServ.setConsentiment(null);
			nuevoSoliServ.setTipusConsentiment(null);
			nuevoSoliServ.setEnllazConsentiment(null);

			if (dataCad == null || dataCad.isEmpty()) {
				caduca = "No Caduca";
			} else {
				caduca = "Caduca";
			}

			nuevoSoliServ.setCaduca(caduca);
			nuevoSoliServ.setFechaCaduca(dataCad);

			nuevoSoliServ.setNotes(notes);

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

	private void marcarFusionadas(List<SolicitudJPA> fusionados, Long solicitudNuevaID) throws I18NException {
		// Marcar las solicitudes fusionadas como "fusionadas" en su estado
		// y guardar el ID de la solicitud nueva en la que se han fusionado
		for (SolicitudJPA soli : fusionados) {
			soli.setEstatSolicitud(Constants.SOLI_ESTAT_FUSIONADA);
			soli.setSolicitudFusionadaID(solicitudNuevaID);
			solicitudLogicaEjb.update(soli);
			log.info("Solicitud ID " + soli.getSolicitudID() + " marcada como fusionada en solicitud " + solicitudNuevaID);
		}
	}

	// ===========================================================
	// =================== Auxiliares =====================
	// ===========================================================

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
