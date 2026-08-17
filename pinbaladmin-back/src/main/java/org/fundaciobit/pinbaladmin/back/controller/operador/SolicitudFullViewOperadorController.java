package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.ByteArrayInputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.ContacteLogicaService;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.ModificacioSolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.dto.ContactePortaFIB;
import org.fundaciobit.pinbaladmin.logic.utils.FileInfo;
import org.fundaciobit.pinbaladmin.logic.utils.GenerarDocumentsDGLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.logic.utils.PdfDownloader;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @author anadal
 * @author areus
 */
@Controller
@RequestMapping(value = SolicitudFullViewOperadorController.CONTEXTWEB)
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudFullViewOperadorController extends SolicitudOperadorController {

	public static final String CONTEXTWEB = "/operador/solicitudfullview";

	@EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService documentSolicitudEjb;

	@EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.ejb.DocumentService documentEjb;

	@EJB(mappedName = OrganLogicaService.JNDI_NAME)
	protected OrganLogicaService organLogicaEjb;

	@EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
	protected TramitAPersAutLogicaService tramitALogicEjb;

	@EJB(mappedName = ModificacioSolicitudLogicaService.JNDI_NAME)
	protected ModificacioSolicitudLogicaService modificacioSolicitudLogicaEjb;

	@EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
	protected InfoMadridLogicaService infoMadridLogicaEjb;
	
	@EJB(mappedName = GenerarDocumentsDGLogicaService.JNDI_NAME)
	protected GenerarDocumentsDGLogicaService generarDocumentsDGLogicaEjb;

	@EJB(mappedName = ContacteLogicaService.JNDI_NAME)
	protected ContacteLogicaService contacteLogicaEjb;

	@Override
	public String getTileForm() {
		return "solicitudListWebDB_FullView_operador";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "SolicitudWebDB_FilterForm_Operador_FullView";
	}

	private static final String SESSIO_SOLICITUD_REFERER = "SESSIO_SOLICITUD_REFERER_";

	private static String getSessioSolicitudRefererWithId(Long id) {
		return SESSIO_SOLICITUD_REFERER + id;
	}

	@RequestMapping(value = "/viewsessio", method = RequestMethod.GET)
	public ModelAndView veureSolicitudGet(HttpServletRequest request,
			HttpServletResponse response) throws I18NException {

		Long solicitudID = (Long) request.getSession()
				.getAttribute(SolicitudDocumentOperadorController.SESSIO_SOLIID_MANAGE_DOCUMENTS);

		if (solicitudID == null) {
			return new ModelAndView(new RedirectView("list", true));
		} else {
			return editAndViewSolicitudGet(solicitudID, request, response, true);
		}
	}

	@Override
	public SolicitudForm getSolicitudForm(SolicitudJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {
		SolicitudForm solicitudForm = super.getSolicitudForm(_jpa, __isView, request, mav);

		SolicitudJPA solicitud = solicitudForm.getSolicitud();

		// Establim el títol amb l'estat de la sol·licitud
		Long estatID = solicitud.getEstatSolicitud();
		String titleCode = "solicitud.fullview.estat." + estatID;
		solicitudForm.setTitleCode(titleCode);

		mav.addObject("isView", __isView);

		if (__isView) {
			final boolean isEstatal = solicitud.getEntitatEstatal() != null
					&& solicitud.getEntitatEstatal().trim().length() > 0;

			Long soliID = solicitud.getSolicitudID();

			afegirBotonsBackEditEvents(solicitudForm, solicitud, isEstatal);

			if (isEstatal) {
				addEstatalButtons(solicitudForm, soliID);
			} else {
				addLocalButtons(solicitudForm, solicitud, soliID);
			}

			// Calcular y añadir información del wizard al modelo
			WizardInfo wizardInfo = calcularWizardInfo(solicitud);
			mav.addObject("wizardInfo", wizardInfo);
			mav.addObject("solicitudID", solicitud.getSolicitudID());
			mav.addObject("procedimentCodi", solicitud.getProcedimentCodi());

			//Cercar si tenim info d'algun error:
			String missatgeError = null;
			if (solicitud.getEstatSolicitud() == Constants.SOLI_ESTAT_ESMENES
					|| solicitud.getEstatSolicitud() == Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID) {
				Long infoMadridID = solicitud.getInfomadridid();
				if (infoMadridID != null) {
					InfoMadrid infoMadrid = infoMadridLogicaEjb.findByPrimaryKey(infoMadridID);
					String missatge = infoMadrid.getMissatge();
					if (missatge != null) {
						missatgeError = missatge.replace("\n", "<br>");
					}
				}

				if (missatgeError == null) {
					missatgeError = "No Info Error";
				}
			}
			mav.addObject("missatgeError", missatgeError);

			// getSeccionsFullView(solicitudForm, isEstatal, request, mav);
			solicitudForm.setAttachedAdditionalJspCode(true);
		}

		HttpSession sessio = request.getSession();
		Long id = solicitud.getSolicitudID();
		sessio.setAttribute(SolicitudDocumentOperadorController.SESSIO_SOLIID_MANAGE_DOCUMENTS, id);
		sessio.setAttribute(SolicitudServeiOperadorController.SESSIO_SOLIID_MANAGE_SERVEIS, id);

		log.info("Set attibute [" + SolicitudServeiOperadorController.SESSIO_SOLIID_MANAGE_SERVEIS + "] = "
				+ solicitud.getSolicitudID());

		return solicitudForm;
	}

	private void afegirBotonsBackEditEvents(SolicitudForm solicitudForm, SolicitudJPA solicitud, boolean isEstatal) {
		// Canviam el cancel·lar per un tornar.....
		solicitudForm.setCancelButtonVisible(false);
		Long soliID = solicitud.getSolicitudID();
		String urlTornar = "/operador/solicitudfullview/" + soliID + " /cancel";

		solicitudForm.addAdditionalButton(
				new AdditionalButton("fas fa-arrow-left", "tornar", urlTornar, AdditionalButtonStyle.INFO));

		solicitudForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_EDIT, "solicitud.edit",
				"/operador/solicitud" + (isEstatal ? "estatal" : "local") + "/" + soliID + "/edit",
				AdditionalButtonStyle.WARNING));

		String urlBackToEvents = EventSolicitudOperadorController.CONTEXTWEB + "/veureevents/" + soliID
				+ (isEstatal() == null ? "" : ("/" + isEstatal));

		solicitudForm.addAdditionalButton(new AdditionalButton("fas fa-bullhorn", "events.titol", urlBackToEvents,
				AdditionalButtonStyle.SUCCESS));

	}

	private void addLocalButtons(SolicitudForm solicitudForm, SolicitudJPA solicitud, Long soliID)
			throws I18NException {

		// Botó per generar el formulari del Director General
		solicitudForm.addAdditionalButton(
				new AdditionalButton(IconUtils.ICON_RELOAD, "solicitud.generarformularidirectorgeneral",
						getContextWeb() + "/generarformularidirectorgeneral/" + soliID, AdditionalButtonStyle.WARNING));

		Long estatID = solicitud.getEstatSolicitud();

		if (estatID == Constants.SOLI_ESTAT_PENDENT_DISTRIBUCIO) {
			solicitudForm.addAdditionalButton(new AdditionalButton("fas fa-cog", "marcar.rebuda.distribucio",
					getContextWeb() + "/rebreSolicitud/" + soliID, AdditionalButtonStyle.PRIMARY));
		}
		
		
		if (estatID == Constants.SOLI_ESTAT_PENDENT_Enviar_Director) {
			solicitudForm.addAdditionalButton(new AdditionalButton("fas fa-file-signature", "firmar.director.portafib",
					getContextWeb() + "/enviarAFirmarTitular/" + soliID, AdditionalButtonStyle.PRIMARY));
		}

		// Si no te el document firmat pel DG, i está pendent d'enviar o de rebre firma,
		// mostrar el botó. (Pot ser que s'envii manual)
		boolean pendentFirmaDirector = estatID == Constants.SOLI_ESTAT_PENDENT_Enviar_Director
				|| estatID == Constants.SOLI_ESTAT_PENDENT_Firma_Director;

		if (!isFirmatPelDirector(solicitud) && pendentFirmaDirector) {
			solicitudForm.addAdditionalButton(new AdditionalButton("fas fa-file-upload", "afegir.formulari.firmat",
					getContextWeb() + "/afegirFormulariFirmat/" + soliID, AdditionalButtonStyle.WARNING));
		}

		// ======================
		// BOTONES DE PRE-ALTAS A MADRID
		// ======================
		Long infoMadID = solicitud.getInfomadridid();

		// S'ha de poder enviar a Madrid quan està pendent, i quan s'està en ESMENES,
		// perque s'ha de poder canviar facil i enviar una altra vegada.
		boolean potEnviarMadrid = estatID == Constants.SOLI_ESTAT_PENDENT_ENVIAR_MADRID
				|| estatID == Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID || estatID == Constants.SOLI_ESTAT_ESMENES;

		if (potEnviarMadrid) {
			addMadridAltaOrModificacion(solicitudForm, soliID, infoMadID);
		}

		// Si està pendent d'autoritzar, s'ha enviat a Madrid i volem resposta.
		// Utilitzam CONSULTA. Si no, tenim resposta, la
		// cercam de InfoMad, si es != null, vol dir que en algun moment hem tengut una
		// interacció amb madrid. Estats anteriors tenen infoMadrid == null

		if (estatID == Constants.SOLI_ESTAT_PENDENT_AUTORITZAR) {
			// CONSULTA permitida
			AdditionalButton consulta = new AdditionalButton("fas fa-eye", "consulta.pinbal.madrid",
					"/operador/altapinbal/consultaestado/" + soliID, AdditionalButtonStyle.SECONDARY);
			solicitudForm.addAdditionalButton(consulta);
		} else if (infoMadID != null) {
			AdditionalButton infoMadBtn = new AdditionalButton("fas fa-eye", "consulta.pinbal.madrid",
					"/operador/infoMadrid/view/" + infoMadID, AdditionalButtonStyle.SECONDARY);
			solicitudForm.addAdditionalButton(infoMadBtn);

		}

		// ======================
		// MODIFICACIONS PENDENTS
		// ======================
		if (estatID == Constants.SOLI_ESTAT_CANVI_PENDENT_REVISAR) {

			Where wSoli = ModificacioSolicitudFields.SOLICITUDID.equal(soliID);

			Where wEstatMod = ModificacioSolicitudFields.ESTATMODIFICACIO
					.equal(Constants.ESTAT_MODIFICACIO_SOLICITUD_ENVIADA);

			Long modSoliID = modificacioSolicitudLogicaEjb.executeQueryOne(ModificacioSolicitudFields.MODSOLIID,
					Where.AND(wSoli, wEstatMod));

			log.info("ModSoli: " + modSoliID);
			if (modSoliID != null) {
				solicitudForm.addAdditionalButton(new AdditionalButton("fas fa-jedi", "solicitud.modificacio.aceptar",
						"/operador/solicitudfullview/acceptarModificacio/" + modSoliID, AdditionalButtonStyle.PRIMARY));
			}
		}

		// ======================
		// CREAR SOLICITUD A PINBAL
		// ======================

		// Si existe informació de Madrid, es pot crear o actualitzar la sol·licitud a
		// PINBAL.
		// El controlador ja decidirà si és ALTA o MODIFICACIÓ.

		if (infoMadID != null) {
			solicitudForm.addAdditionalButton(
					new AdditionalButton(
							"fas fa-share-square",
							"pinbal.exportarsolicitud",
							"/operador/solicitudfullview/crearOActualitzarSolicitud/" + soliID,
							AdditionalButtonStyle.PRIMARY));
		}

	}

	private void addMadridAltaOrModificacion(SolicitudForm solicitudForm, Long soliID, Long infoMadID) {

		// AdditionalButton alta = new AdditionalButton("fas fa-cloud-upload-alt",
		// "alta.pinbal.madrid",
		// "/operador/altapinbal/vistaprevia/alta/" + soliID,
		// AdditionalButtonStyle.PRIMARY);
		// solicitudForm.addAdditionalButton(alta);

		// Peticio no enviada a madrid. Enviar ALTA.
		if (infoMadID == null) {
			addAltaMadrid(solicitudForm, soliID);
			return;
		}

		InfoMadridJPA infoMad = infoMadridLogicaEjb.findByPrimaryKey(infoMadID);

		// Si no s'ha autoritzat. Enviar ALTA. Si s'ha autoritzat, enviar MODIFICACIO
		if (infoMad.getDataAutoritzacio() == null) {
			addAltaMadrid(solicitudForm, soliID);
			return;
		}

		AdditionalButton modificacio = new AdditionalButton("fas fa-tools", "modificacio.pinbal.madrid",
				"/operador/altapinbal/vistaprevia/modificacio/" + soliID, AdditionalButtonStyle.SUCCESS);
		solicitudForm.addAdditionalButton(modificacio);
	}

	private void addAltaMadrid(SolicitudForm solicitudForm, Long soliID) {
		AdditionalButton alta = new AdditionalButton("fas fa-cloud-upload-alt", "alta.pinbal.madrid",
				"/operador/altapinbal/vistaprevia/alta/" + soliID, AdditionalButtonStyle.PRIMARY);
		solicitudForm.addAdditionalButton(alta);
	}

	private void addEstatalButtons(SolicitudForm solicitudForm, Long soliID) {
		// if (solicitud.getEstatSolicitud() ==
		// Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Cedents) {
		// // Boto per enviar correus als cedents
		// solicitudForm.addAdditionalButton(new AdditionalButton("fas fa-envelope",
		// "estatal.enviarcorreucedents",
		// "/operador/solicitudestatal/enviarcorreucedents/" + soliID,
		// AdditionalButtonStyle.WARNING));
		// }

		// Boto per enviar correus als cedents
		solicitudForm.addAdditionalButton(new AdditionalButton("fas fa-envelope", "estatal.enviarcorreucedents",
				"/operador/solicitudestatal/enviarcorreucedents/" + soliID, AdditionalButtonStyle.WARNING));
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param soliID
	 * @return
	 */
	@RequestMapping(value = "/generarserveis/{soliID}", method = RequestMethod.GET)
	public String generarServeisAndFormulari(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long soliID) {

		SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);

		Long fitxerID = soli.getSolicitudXmlID();

		log.info(" FITXER ID => " + fitxerID);

		if (fitxerID == null) {

			HtmlUtils.saveMessageError(request,
					"NO ES PODEN GENERAR ELS SERVEIS JA QUE NO HI HA EL FITXER DE XML !!!!!!");

		} else {

			// Si és local
			if ("application/xml".equals(soli.getSolicitudXml().getMime())) {
				try {
					Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);

					generarServeis(request, soliID, prop);

					generarDocumentsDGLogicaEjb.generarFormulariDirectorGeneralPDFODT(soliID);

				} catch (I18NException ie) {
					String msg = I18NUtils.getMessage(ie);
					log.error(msg, ie);
					HtmlUtils.saveMessageError(request, msg);

				} catch (Exception e) {
					log.error(e.getMessage(), e);
					HtmlUtils.saveMessageError(request, "Error" + e.getMessage());
				}
			}
		}

		return "redirect:" + getContextWeb() + "/view/" + soliID;
	}

	@RequestMapping(value = "/generarformularidirectorgeneral/{soliID}", method = RequestMethod.GET)
	public String generarFormulariDirectorGeneral(HttpServletRequest request,
			@PathVariable Long soliID) throws Exception {
		
		log.info("Generant formulari Director General per la sol·licitud [" + soliID + "]");
		
		generarDocumentsDGLogicaEjb.generarFormulariDirectorGeneralPDFODT(soliID);
		
		log.info("Formulari Director General generat per la sol·licitud [" + soliID + "]");
		
		return "redirect:" + getContextWeb() + "/view/" + soliID;
	}
	
	protected void generarServeis(HttpServletRequest request, Long soliID, Properties prop)
			throws I18NException {
		int x = 1;

		while (true) {
			String codi = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".CODISERV");
			log.info(" CODI PER [" + x + "]  => " + codi);
			if (codi == null || codi.trim().isEmpty()) {
				log.info("No hi ha mes serveis");
				break;
			}

			// TODO XYZ ZZZ
			Long serveiID = serveiEjb.executeQueryOne(ServeiFields.SERVEIID, ServeiFields.CODI.equal(codi.trim()));

			if (serveiID == null) {
				HtmlUtils.saveMessageWarning(request, "El servei [" + codi + "] no existeix. L'ignoram ...");
				x++;
				continue;
			}

			// XYZ ZZZ
			java.lang.Long estatSolicitudServeiID = 10L;
			java.lang.String notes = ""; // str.toString();

			String base = "FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".";

			String normaLegal = prop.getProperty(base + "NORMALEGAL");
			String enllazNormaLegal = prop.getProperty(base + "ENLACENOR");
			String articles = prop.getProperty(base + "ARTICULOS");

			String consAdj = prop.getProperty(base + "LDECONSENTIMIENTO");
			String consUrl = prop.getProperty(base + "ENLACECON");
			String consentiment = prop.getProperty(base + "CONSENTIMIENTO");

			String consentimentAux = normalize(consentiment).replaceAll("\\p{M}", "");
			log.info("consentiment: ]" + consentiment + "[ Normalitzam: ]" + consentimentAux + "[ toLowerCase: ]"
					+ consentimentAux.toLowerCase() + "[");
			switch (consentimentAux.toLowerCase()) {
				case "sí":
				case "si":
					consentiment = Constants.CONSENTIMENT_TIPUS_SI;
					break;

				case "nooposicio":
				case "nooposicion":
				case "noop":
				case "noopo":

				case "no oposicio":
				case "no oposicion":
				case "no op":
				case "no opo":

				case "no_oposicio":
				case "no_oposicion":
				case "no_op":
				case "no_opo":
					consentiment = Constants.CONSENTIMENT_TIPUS_NOOP;
					break;
				case "llei":
				case "ley":
					consentiment = Constants.CONSENTIMENT_TIPUS_LLEI;
					break;
			}
			log.info("Consentiment despues: " + consentiment);

			String caducafecha = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.FECHACAD");
			String caduca = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CADUCA");

			Long count = solicitudServeiEjb.count(Where.AND(SolicitudServeiFields.SOLICITUDID.equal(soliID),
					SolicitudServeiFields.SERVEIID.equal(serveiID)));

			if (count == 0) {

				// XXX YYY ZZZ Tornar a posar obtenir PDF quan estigui en marxa api alta pinbal
				// madrid.
				// Long fitxerIDNorma = crearFitxerNormaFromURL(enllazNormaLegal);
				// if (fitxerIDNorma == null) {
				// HtmlUtils.saveMessageWarning(request, "No s'ha pogut crear el fitxer de la
				// norma [" + normaLegal
				// + "] amb URL [" + enllazNormaLegal + "]");
				// }

				Long fitxerIDNorma = null;

				String norma2 = null;
				String articles2 = null;
				Long fitxerIDNorma2 = null;

				String norma3 = null;
				String articles3 = null;
				Long fitxerIDNorma3 = null;

				SolicitudServeiJPA ss = new SolicitudServeiJPA(soliID, serveiID, estatSolicitudServeiID,
						enllazNormaLegal, consAdj, consentiment, consUrl, notes, caduca, caducafecha, normaLegal,
						fitxerIDNorma, articles, norma2, fitxerIDNorma2, articles2, norma3, fitxerIDNorma3, articles3);

				log.info("ss: " + ss);

				solicitudServeiEjb.create(ss);

			} else {
				HtmlUtils.saveMessageWarning(request, "El servei [" + codi + "] ja existeix. L'ignoram ...");
			}

			log.info("Servei [" + x + "][" + +serveiID + "] => " + codi);
			x++;
		}
	}

	public static String normalize(String input) {
		return input == null ? null : Normalizer.normalize(input, Normalizer.Form.NFKD);
	}

	@Override
	protected ModelAndView editAndViewSolicitudGet(Long solicitudID, HttpServletRequest request,
			HttpServletResponse response, boolean __isView) throws I18NException {
		if (__isView) {
			// Guaram el referer, ja que a la vista de solicitud podem venir de moltes
			// bandes:
			// de les solicituts actives/ estatals / locals, o de la llista de
			// sol·licituds d'un servei
			String referer = request.getHeader("referer");
			request.getSession().setAttribute(getSessioSolicitudRefererWithId(solicitudID), referer);
		}
		return super.editAndViewSolicitudGet(solicitudID, request, response, __isView);
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, Long solicitudID) {
		// Si tenim guardat el referer per aquesta sol·licitud l'empram.
		String refererAttribute = getSessioSolicitudRefererWithId(solicitudID);
		String referer = (String) request.getSession().getAttribute(refererAttribute);
		if (referer != null) {
			request.getSession().removeAttribute(refererAttribute);
			return "redirect:" + referer;
		} else {
			return super.getRedirectWhenCancel(request, solicitudID);
		}
	}

	@Override
	public boolean isActiveList() {
		return false;
	}

	@Override
	public boolean isActiveFormNew() {
		return false;
	}

	@Override
	public boolean isActiveFormEdit() {
		return false;
	}

	@Override
	public boolean isActiveDelete() {
		return false;
	}

	@Override
	public boolean isActiveFormView() {
		return true;
	}

	@Override
	public Boolean isEstatal() {
		// Només gestionam la vista form (no hi ha llistat)
		return null;
	}

	@Override
	public boolean showAdvancedFilter() {
		return false;
	}

	public boolean isFirmatPelDirector(SolicitudJPA soli) throws I18NException {

		List<Long> listDocumentsSolicitud = documentSolicitudEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(soli.getSolicitudID()));

		List<Document> documentsPDF = documentEjb.select(Where.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud),
				DocumentFields.TIPUS.equal(Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF)));

		for (Document document : documentsPDF) {
			if (document.getFitxerFirmatID() != null) {
				return true;
			}
		}

		return false;
	}

	@RequestMapping(value = "/afegirFormulariFirmat/{soliID}", method = RequestMethod.GET)
	public String afegirFormulariFirmat(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long soliID) throws I18NException {

		List<DocumentSolicitud> listDocumentsSolicitud = documentSolicitudEjb
				.select(DocumentSolicitudFields.SOLICITUDID.equal(soliID));
		for (DocumentSolicitud docSol : listDocumentsSolicitud) {
			Document document = documentEjb.findByPrimaryKey(docSol.getDocumentID());

			if (document.getTipus() == Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF) {
				return "redirect:" + "/operador" + "/solicituddocumentonlycontent/" + docSol.getDocumentSolicitudID()
						+ "/edit";
			}
		}
		return null;
	}

    @RequestMapping(value = "/rebreSolicitud/{solicitudID}", method = RequestMethod.GET)
    public String rebreSolicitud(@PathVariable("solicitudID") java.lang.Long solicitudID, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {

        log.info("rebreSolicitud de distribucio:: " + solicitudID);

        // Actualitzar l'estat de la sol·licitud a Pendent enviar director.

        SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(solicitudID);
        soli.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_Enviar_Director);
        soli.setOperador(request.getRemoteUser());

        solicitudLogicaEjb.update(soli);

		return "redirect:" + getContextWeb() + "/view/" + solicitudID;
    }

	@RequestMapping(value = "/enviarAFirmarTitular/{soliID}", method = RequestMethod.GET)
	public String enviarDocumentAFirmar(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long soliID) throws I18NException {

		try {
			log.info("Enviem a firmar la sol·licitud [" + soliID + "]");

			Solicitud soli = solicitudLogicaEjb.findByPrimaryKey(soliID);

			// String nifDestinatari = "45186147W";
			// String nifDestinatari = Configuracio.getNIFDirectorGeneral();

			// String nifDestinatari = soli.getTitularFirmaNif();
			// String nomDestinatari = soli.getTitularFirmaNom();

			String remitent = request.getRemoteUser();
			Contacte titular = contacteLogicaEjb.findByPrimaryKey(soli.getContacteTitularID());

			final boolean ENVIAR_COM_USUARI_EXTERN = true; // Indica que s'ha d'enviar com a usuari extern
			ContactePortaFIB contactePortaFIB = new ContactePortaFIB(titular, ENVIAR_COM_USUARI_EXTERN); // Indica que s'ha d'enviar com a usuari extern );

			solicitudLogicaEjb.enviarFormulariDGPortaFIB(soli, contactePortaFIB, remitent);

			log.info("S'ha enviat a firmar la sol·licitud [" + soliID + "]");
			HtmlUtils.saveMessageInfo(request, "S'ha enviat a firmar la sol·licitud [" + soliID + "]");
		} catch (Exception e) {
			String msg = "Error enviant a firmar la sol·licitud [" + soliID + "]: " + e.getMessage();
			log.error(msg, e);
			HtmlUtils.saveMessageError(request, msg);
		}
		return "redirect:" + getContextWeb() + "/view/" + soliID;
	}

	public Long crearFitxerNormaFromURL(String url) {
		try {
			final boolean debug = false;
			FileInfo fileInfo = PdfDownloader.downloadPDFFromBoeBoibUrl(url, debug);

			String nom = fileInfo.getFileName();
			long tamany = fileInfo.getSize();
			String mime = "application/pdf";
			String descripcio = "Fitxer de norma legal descarregat des de la URL [" + url + "]";

			Fitxer fitxer = fitxerEjb.create(nom, tamany, mime, descripcio);

			Long fitxerID = fitxer.getFitxerID();

			FileSystemManager.crearFitxer(new ByteArrayInputStream(fileInfo.getContent()), fitxerID);

			return fitxerID;
		} catch (Exception e) {
			String errorMsg;
			if (e instanceof I18NException) {
				errorMsg = I18NUtils.getMessage((I18NException) e);
			} else {
				errorMsg = e.getMessage();
			}
			errorMsg = "Error creant fitxer de norma legal des de URL [" + url + "]: " + errorMsg;
			log.warn(errorMsg, e);
			return null;
		}
	}

	@RequestMapping(value = "/acceptarModificacio/{modSoliID}", method = RequestMethod.GET)
	public String acceptarModificacio(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long modSoliID) throws I18NException {

		ModificacioSolicitudJPA modSoli = modificacioSolicitudLogicaEjb.findByPrimaryKey(modSoliID);
		Long soliID = modSoli.getSolicitudID();
		try {
			log.info("Acceptarem la modificació " + modSoliID + " de la solicitud [" + soliID + "]");

			modificacioSolicitudLogicaEjb.acceptarModificacio(modSoli);

			log.info("Canvis de la solicitud [" + soliID + "] aceptats");
			HtmlUtils.saveMessageInfo(request, "S'han modificat les dades de la sol·licitud [" + soliID + "]");
		} catch (Exception e) {
			String msg = "Error acceptant la modificacio de la solicitud: " + e.getMessage();
			log.error(msg, e);
			HtmlUtils.saveMessageError(request, msg);
		}
		return "redirect:" + getContextWeb() + "/view/" + soliID;
	}

	@RequestMapping(value = "/crearOActualitzarSolicitud/{soliID}", method = RequestMethod.GET)
	public String crearOActualitzarSolicitudPinbal(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long soliID) throws I18NException {

		try {
			log.info("Crearem o actualitzarem la sol·licitud a PINBAL per la solicitud [" + soliID + "]");

			solicitudLogicaEjb.crearOActualitzarSolicitudPinbal(soliID);

			log.info("S'ha creat o actualitzat la sol·licitud a PINBAL per la solicitud [" + soliID + "]");
			HtmlUtils.saveMessageInfo(request,
					"S'ha creat o actualitzat la sol·licitud a PINBAL per la solicitud [" + soliID + "]");
		} catch (Exception e) {
			String msg = "Error creant o actualitzant la solicitud a PINBAL: " + e.getMessage();
			log.error(msg, e);
			HtmlUtils.saveMessageError(request, msg);
		}
		return "redirect:" + getContextWeb() + "/view/" + soliID;

	}

	public List<StringKeyValue> getReferenceListForContacte(Where where) throws I18NException {
		List<StringKeyValue> list = new ArrayList<>();
		List<Contacte> contactes = contacteLogicaEjb.select(where);

		for (Contacte contacte : contactes) {
			// log.info("Contacte => " + contacte.getContacteID() + " - " +
			// contacte.getNom());
			String key = contacte.getContacteID() + "";

			String value = contacte.getNom() + " " + contacte.getLlinatge1() + " " + contacte.getLlinatge2() + " ("
					+ contacte.getNif() + " - " + contacte.getUsername() + ") - " + contacte.getMail();

			// String value = contacte.getNom() + " (" + contacte.getNif() + ")";
			list.add(new StringKeyValue(key, value));
		}
		return list;
	}

	@Override
	public List<StringKeyValue> getReferenceListForContacteTitularID(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {
		return getReferenceListForContacte(where);
	}

	@Override
	public List<StringKeyValue> getReferenceListForContacteAuditoriaID(HttpServletRequest request, ModelAndView mav,
			SolicitudForm solicitudForm, Where where) throws I18NException {
		return getReferenceListForContacte(where);
	}

	@Override
	public List<StringKeyValue> getReferenceListForContacteGestAutID(HttpServletRequest request, ModelAndView mav,
			SolicitudForm solicitudForm, Where where) throws I18NException {
		return getReferenceListForContacte(where);
	}

	@Override
	public List<StringKeyValue> getReferenceListForContacteSolicitantID(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {
		return getReferenceListForContacte(where);
	}

	@Override
	public List<StringKeyValue> getReferenceListForContacteTecnicID(HttpServletRequest request, ModelAndView mav,
			SolicitudForm solicitudForm, Where where) throws I18NException {
		return getReferenceListForContacte(where);
	}

	/**
	 * Clase interna para representar un estado en el wizard de tramitación.
	 * Cada estado tiene DOS nombres: uno cuando está pendiente (azul) y otro cuando ya está completado (verde).
	 */
	public static class WizardEstado {
		private Long id;
		private String labelPendiente;   // Texto cuando está en proceso (azul)
		private String labelCompletado;  // Texto cuando ya se completó (verde)
		private String labelActual;      // El que se muestra actualmente (se calcula dinámicamente)
		private String descripcion;
		private String cssClass;
		private String icono;

		public WizardEstado(Long id, String labelPendiente, String labelCompletado, String descripcion) {
			this.id = id;
			this.labelPendiente = labelPendiente;
			this.labelCompletado = labelCompletado;
			this.descripcion = descripcion;
		}

		public Long getId() {
			return id;
		}

		public String getLabelPendiente() {
			return labelPendiente;
		}

		public String getLabelCompletado() {
			return labelCompletado;
		}

		public String getLabel() {
			return labelActual;
		}

		public void setLabelActual(String labelActual) {
			this.labelActual = labelActual;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public String getCssClass() {
			return cssClass;
		}

		public void setCssClass(String cssClass) {
			this.cssClass = cssClass;
		}

		public String getIcono() {
			return icono;
		}

		public void setIcono(String icono) {
			this.icono = icono;
		}
	}

	/**
	 * Clase para encapsular toda la información del wizard de tramitación.
	 * Workflow LOCAL: 6 fases (no 7) - Estados 20 y 40 son LA MISMA FASE
	 * 1. Marcar Recibida (5)
	 * 2. Enviar a Firmar (11)
	 * 3. Esperando Firma (15)
	 * 4. Enviar a Madrid (19)
	 * 5. Proceso Madrid (20→40): azul mientras espera, verde cuando autoriza, rojo si desestima
	 * 6. Cerrar (60)
	 */
	public static class WizardInfo {
		private List<WizardEstado> estados;
		private int faseActual;
		private int totalFases;
		private boolean errorState;
		private String estadoActualNombre;

		public WizardInfo(List<WizardEstado> estados, int faseActual, int totalFases, boolean errorState,
				String estadoActualNombre) {
			this.estados = estados;
			this.faseActual = faseActual;
			this.totalFases = totalFases;
			this.errorState = errorState;
			this.estadoActualNombre = estadoActualNombre;
		}

		public List<WizardEstado> getEstados() {
			return estados;
		}

		public int getFaseActual() {
			return faseActual;
		}

		public int getTotalFases() {
			return totalFases;
		}

		public boolean isErrorState() {
			return errorState;
		}

		public String getEstadoActualNombre() {
			return estadoActualNombre;
		}
	}

	/**
	 * Calcula la información del wizard según el estado de la solicitud.
	 * Solo para solicitudes LOCALES con workflow de 7 fases.
	 */
	private WizardInfo calcularWizardInfo(SolicitudJPA solicitud) throws I18NException {
		Long estatActual = solicitud.getEstatSolicitud();

		// Workflow para solicitudes locales (6 fases, no 7)
		// La fase "Proceso Madrid" agrupa los estados 20 y 40:
		// - Estado 20: azul "Esperando Autorización" (en proceso)
		// - Estado 40: verde "Autorizada" (completado)
		// - Estado 30: rojo "Desestimada" (error)
		List<WizardEstado> estados = new ArrayList<>();

		// ID, PENDIENTE (azul), COMPLETADO (verde), Descripción
		addEstadoWizard(estados, Constants.SOLI_ESTAT_PENDENT_DISTRIBUCIO, "Marcar Recibida", "Recibida Distribución", "Solicitud recibida desde CAIB");
		addEstadoWizard(estados, Constants.SOLI_ESTAT_PENDENT_Enviar_Director, "Enviar a Firmar", "Enviada a Firmar", "Documento enviado al titular para firma");
		addEstadoWizard(estados, Constants.SOLI_ESTAT_PENDENT_Firma_Director, "Esperando Firma", "Documento Firmado", "Titular ha firmado digitalmente");

		if (estatActual == Constants.SOLI_ESTAT_CANVI_PENDENT_REVISAR) {
			addEstadoWizard(estados, Constants.SOLI_ESTAT_CANVI_PENDENT_REVISAR, "Revisar Modificacions", "No veurem aquest missatge", "Solicitud con cambios pendientes de revisión");
		}

		addEstadoWizard(estados, Constants.SOLI_ESTAT_PENDENT_ENVIAR_MADRID, "Enviar a Madrid", "Enviada a Madrid", "Solicitud enviada a la Plataforma de Intermediación");

		// Si es una solicitud denegada, se añade una fase más que sea DENEGADA. No hace
		// falta el resto, y la pondremos en rojo error.

		if (estatActual == Constants.SOLI_ESTAT_DENEGADA) {
			addEstadoWizard(estados, Constants.SOLI_ESTAT_DENEGADA, "Solicitud Denegada", "Solicitud Denegada", "Solicitud denegada por Madrid");
			// El estado actual será el de denegada, con lo que se marcará en rojo error, y
			// las fases anteriores estarán en blanco pendientes (no completadas), porque no
			// se han completado al no llegar a autorizar.
		} else {
			if (estatActual == Constants.SOLI_ESTAT_ESMENES) {
				// A este estado llegamos cuando han desestimado de Madrid. Así que en el paso
				// "Pendeinte Autorizar" pondremos "Desestimado", en rojo,
				// y una fase más que será "solicitar enmienda", que será el nuevo actual.

				// De momento no se solicita la enmienda automaticamente, se solicita
				// manualmente pero sin cambiar de estado, así el estado actual se llamará
				// Pendiente Subsanacion, o Pendiente Enmienda.

				// Cuando el solicitante haga el tramite de enmienda, pasará a Pendiente Revisar
				// Cambio. Ya no nos importará lo que haya pasado antes, porque lo que hay que
				// hacer está claro.
				addEstadoWizard(estados, Constants.SOLI_ESTAT_PENDENT_AUTORITZAR, "Desestimada PRE-ALTAS", "Canvi Solicitat", "Proceso de autorización en Madrid (1-6 semanas)");
				addEstadoWizard(estados, Constants.SOLI_ESTAT_ESMENES, "Pendiente Enmienda", "No veurem aquest missatge", "Solicitud con cambios pendientes de revisión");
			} else {
				addEstadoWizard(estados, Constants.SOLI_ESTAT_PENDENT_AUTORITZAR, "Esperando Autorización", "Autorizada PRE-ALTAS", "Proceso de autorización en Madrid (1-6 semanas)");
			}
			addEstadoWizard(estados, Constants.SOLI_ESTAT_TANCAT, "Cerrar Solicitud", "Solicitud Cerrada", "Solicitud archivada");
		}

		// Determinar fase actual y estados especiales
		int faseActual = 0;
		boolean errorState = false;

		// Manejar estados especiales (errores, esmenas, denegadas)
		if (estatActual == Constants.SOLI_ESTAT_AUTORITZAT || estatActual == Constants.SOLI_ESTAT_AUTORITZAT_Manual
				|| estatActual == Constants.SOLI_ESTAT_AUTORITZAT_Parcial) {
			// ESTADO 40: Madrid AUTORIZÓ → La fase "Proceso Madrid" está COMPLETADA
			// La fase actual pasa a ser "Cerrar" (fase 6)
			faseActual = 6; // Siguiente fase: Cerrar
		} else if (estatActual == Constants.SOLI_ESTAT_TANCAT) {
			// ESTADO 60: Solicitud CERRADA → Todas las fases completadas
			faseActual = 7; // Más allá de la última fase (6) para marcarlas todas como completadas
		} else if (estatActual == Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID) {
			// Error técnico al enviar - error en fase "Enviar a Madrid"
			errorState = true;
			faseActual = 4; // Fase "Enviar a Madrid" con error
		} else if (estatActual == Constants.SOLI_ESTAT_CANVI_PENDENT_REVISAR) {
			// Si hay un cambio pendiente de revisar, despues de revisarlo se envía a
			// Madrid. No hay error, pero es un estado especial que se muestra en el wizard.
			faseActual = 4; 
			// Fase "Enviar a Madrid" pasa a ser el 5, el 4 es pendiente de revisión de cambios
		} else if (estatActual == Constants.SOLI_ESTAT_DENEGADA) {
			// Si ha sido denegada, se muestra la fase de "Proceso Madrid" en rojo error, y
			// el estado actual es "Solicitud Denegada"
			errorState = true;
			faseActual = 5; 
			// Fase "Proceso Madrid" con error, en vez de poner info de Madrid pondremos "Denegada".
		} else if (estatActual == Constants.SOLI_ESTAT_ESMENES) {
			// Caso especial. Aqui el actual es el que ponga Pendiente Enmienda, que es el
			// 6, pero hay que poner el 5 en rojo error. MANUALMENTE
			faseActual = 6; 
			// Fase "Pendiente Enmienda", pero el error se muestra en la fase anterior "Proceso Madrid"
		} else if (estatActual == Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_Manual) {
			// Igual que pendiente de aurizar, pero manulamente.
			faseActual = 5; // Fase "Proceso Madrid", pero el mensaje dirá que es una autorización manual.
		} else {
			// Buscar en qué fase está según el estado
			// IMPORTANTE: Para estado 20 (PENDENT_AUTORITZAR), faseActual será 5 (la fase Madrid)
			for (int i = 0; i < estados.size(); i++) {
				if (estatActual >= estados.get(i).getId()) {
					faseActual = i + 1;
				}
			}
		}

		// Asignar iconos, clases CSS y el LABEL CORRECTO a cada estado
		for (int i = 0; i < estados.size(); i++) {
			WizardEstado estado = estados.get(i);

			if (errorState && i == faseActual - 1) {
				// ESTADO CON ERROR
				estado.setCssClass("error");
				estado.setIcono("fas fa-exclamation-circle");
				estado.setLabelActual(estado.getLabelPendiente()); // Muestra lo que había que hacer
			} else if (i < faseActual - 1) {
				// ESTADOS YA COMPLETADOS → Verde con texto "completado"
				estado.setCssClass("completado");
				estado.setIcono("fas fa-check-circle");
				estado.setLabelActual(estado.getLabelCompletado()); // "Recibida", "Enviada", "Firmado", etc.
			} else if (i == faseActual - 1) {
				// ESTADO ACTUAL → Azul con texto "pendiente"
				estado.setCssClass("actual");
				estado.setIcono("fas fa-circle");
				estado.setLabelActual(estado.getLabelPendiente()); // "Marcar Recibida", "Enviar a Firmar", "Cerrar
																	// Solicitud", etc.
			} else {
				// ESTADOS PENDIENTES (futuros) → Blanco con texto "pendiente"
				estado.setCssClass("pendiente");
				estado.setIcono("far fa-circle");
				estado.setLabelActual(estado.getLabelPendiente()); // Lo que habrá que hacer
			}
		}

		// CASO ESPECIAL. PONER ROJO EL DESESTIMADO EN ESTADO ESMENES:
		if (estatActual == Constants.SOLI_ESTAT_ESMENES) {
			// Cojemos el estado anterior (que es la posicion 4 empezando por 0), y lo
			// ponemos en rojo error, con el icono de error, y el label de pendiente, que es
			// "Desestimada PRE-ALTAS"
			WizardEstado estado = estados.get(4);
			errorState = true;
			estado.setCssClass("error");
			estado.setIcono("fas fa-exclamation-circle");
			estado.setLabelActual(estado.getLabelPendiente()); // Muestra lo que había que hacer
		}

		// Obtener nombre del estado actual
		String estadoActualNombre;
		try {
			estadoActualNombre = I18NUtils.tradueix("solicitud.fullview.estat." + estatActual);
		} catch (Exception e) {
			estadoActualNombre = "Estado " + estatActual;
		}

		return new WizardInfo(estados, faseActual, estados.size(), errorState, estadoActualNombre);
	}

	private void addEstadoWizard(List<WizardEstado> estados, long estatID, String pendiente, String completado, String desc) {
		WizardEstado estado = new WizardEstado(estatID, pendiente, completado, desc);
		estados.add(estado);
	}
}
