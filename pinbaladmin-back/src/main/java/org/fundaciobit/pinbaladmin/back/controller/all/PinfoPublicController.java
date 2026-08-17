package org.fundaciobit.pinbaladmin.back.controller.all;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.FileDownloadController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.PinfoController;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoForm;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.PinfoLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.EmailUtil;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.model.fields.PinfoFields;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias 28 oct 2024 15:27:35
 */

@Controller
@RequestMapping(value = PinfoPublicController.CONTEXT_WEB)
@SessionAttributes(types = { PinfoForm.class, PinfoFilterForm.class })
public class PinfoPublicController extends PinfoController {

	public static final String CONTEXT_WEB = "/public/pinfo";

	// Posición para el campo adicional de destinatario (NIF + Nom)
	private static final int DESTINATARI_INFO = 100;
	// Posición para el campo adicional de fecha de inicio
	private static final int DATA_INICI_INFO = 101;

	public static SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	@EJB(mappedName = PinfoLogicaService.JNDI_NAME)
	protected PinfoLogicaService pinfoLogicaEjb;

	@EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
	protected IncidenciaTecnicaLogicaService incidenciaTecnicaLogicaEjb;

	@EJB(mappedName = EventLogicaService.JNDI_NAME)
	protected EventLogicaService eventLogicaEjb;

	@Override
	public String getTileForm() {
		return "pinfoFormPublic";
	}

	@Override
	public String getTileList() {
		return "pinfoListPublic";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return this.getClass().getName() + "_FilterForm";
	}

	@Override
	public PinfoJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pinfoID) throws I18NException {
		return (PinfoJPA) pinfoLogicaEjb.findByPrimaryKey(pinfoID);
	}

	@Override
	public PinfoForm getPinfoForm(PinfoJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		PinfoForm pinfoForm = super.getPinfoForm(_jpa, __isView, request, mav);

		PinfoJPA pinfo = pinfoForm.getPinfo();

		if (pinfo.getEstat() == Constants.ESTAT_PINFO_CREANT) {
			pinfoForm.addHiddenField(FITXERFIRMATID);
			pinfoForm.addHiddenField(PORTAFIBID);
			pinfoForm.addHiddenField(ESTAT);
			pinfoForm.addHiddenField(SOLICITANTNIF);
			pinfoForm.addHiddenField(FITXERID);
			pinfoForm.addHiddenField(DESTINATARINIF);

			pinfoForm.addReadOnlyField(FITXERID);
			pinfoForm.addReadOnlyField(SOLICITANTNIF);
			pinfoForm.addReadOnlyField(INCIDENCIAID);

			pinfoForm.setDeleteButtonVisible(false);
			pinfoForm.setSaveButtonVisible(false);
			pinfoForm.setCancelButtonVisible(false);

			Long pinfoID = pinfo.getPinfoID();
			log.info("pinfoID: " + pinfoID);

			pinfoForm.addAdditionalButton(new AdditionalButton("fas fa-arrow-left", "tornar",
					PinfoDataPublicController.CONTEXT_WEB + "/list/1", AdditionalButtonStyle.SECONDARY));

			pinfoForm.addAdditionalButton(new AdditionalButton("fas fa-sign", "Enviar a firmar",
					CONTEXT_WEB + "/enviarPinfoPortaFIB/" + pinfoID, AdditionalButtonStyle.PRIMARY));
		} else if (pinfo.getEstat() == Constants.ESTAT_PINFO_INICIANT) {
			// Primera vista del pinfo.

		}

		String urlPinfoPDF = "/pinbaladmin" + FileDownloadController.fileUrl(pinfo.getFitxer());

		mav.addObject("urlPinfoPDF", urlPinfoPDF);
		mav.addObject("pinfo", pinfo);
		pinfoForm.setAttachedAdditionalJspCode(true);
		return pinfoForm;
	}

	@RequestMapping(value = "/enviarPinfoPortaFIB/{pinfoID}")
	public String enviarPinfoPortaFIB(HttpServletRequest request, @PathVariable("pinfoID") java.lang.Long pinfoID)
			throws I18NException {

		log.info("Paso 1: /enviarPinfoPortaFIB/" + pinfoID);
		pinfoLogicaEjb.enviarPinfoPortaFIB(pinfoID); // , responsable);

		// Enviar email informativo al solicitante
		try {
			enviarEmailConfirmacion(pinfoID);
		} catch (Exception e) {
			log.error("Error enviant email de confirmació al solicitant: " + e.getMessage(), e);
			// No bloqueamos el flujo si falla el email
		}

		// Redirect to página de confirmación
		return "redirect:" + CONTEXT_WEB + "/confirmacionEnviado/" + pinfoID;
	}

	@RequestMapping(value = "/confirmacionEnviado/{pinfoID}")
	public ModelAndView confirmacionEnviado(HttpServletRequest request, @PathVariable("pinfoID") java.lang.Long pinfoID)
			throws I18NException {

		log.info("confirmacionEnviado: pinfoID=" + pinfoID);

		ModelAndView mav = new ModelAndView("pinfoEnviadoConfirmacion");

		PinfoJPA pinfo = pinfoLogicaEjb.findByPrimaryKey(pinfoID);
		Long incidenciaID = pinfo.getIncidenciaID();
		IncidenciaTecnicaJPA incidencia = incidenciaTecnicaLogicaEjb.findByPrimaryKey(incidenciaID);

		// Información para mostrar en la página
		mav.addObject("incidenciaID", incidenciaID);
		mav.addObject("pinfoID", pinfoID);
		mav.addObject("titolIncidencia", incidencia.getTitol());
		mav.addObject("destinatariNom", pinfo.getDestinatariNom());
		mav.addObject("destinatariNIF", pinfo.getDestinatariNIF());
		mav.addObject("emailSolicitant", incidencia.getContacteEmail());

		// URLs para los botones
		String destinatari = "CONTACTE|" + incidencia.getContacteNom();
		String urlEvents = "/pinbaladmin" + redirectToEventsPinfo(incidenciaID, destinatari).replace("redirect:", "");
		String urlMisPinfos = "/pinbaladmin" + CONTEXT_WEB + "/list/1";

		mav.addObject("urlEvents", urlEvents);
		mav.addObject("urlMisPinfos", urlMisPinfos);

		return mav;
	}

	private void enviarEmailConfirmacion(Long pinfoID) throws Exception {
		log.info("Enviando email de confirmación para PINFO: " + pinfoID);

		PinfoJPA pinfo = pinfoLogicaEjb.findByPrimaryKey(pinfoID);
		Long incidenciaID = pinfo.getIncidenciaID();
		IncidenciaTecnicaJPA incidencia = incidenciaTecnicaLogicaEjb.findByPrimaryKey(incidenciaID);

		String destinatariEmail = incidencia.getContacteEmail();
		String solicitantNom = incidencia.getContacteNom();
		String responsableNom = pinfo.getDestinatariNom();
		String responsableNIF = pinfo.getDestinatariNIF();

		String subject = "PINFO [" + incidenciaID + "] - Sol·licitud enviada a firmar";
		String from = Configuracio.getAppEmail();

		// Generar URL para seguimiento
		String destinatari = "CONTACTE|" + incidencia.getContacteNom();
		String encryptedIncidenciaID = HibernateFileUtil.encryptFileID(incidenciaID);
		String encryptedDestinatari = HibernateFileUtil.encryptString(destinatari);
		String urlSeguiment = Configuracio.getAppBackUrl() + "/public/eventincidenciatecnica/veureevents/"
				+ encryptedIncidenciaID + "/" + encryptedDestinatari;

		String message = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto;'>"
				+ "<div style='background-color: #4DBA79; color: white; padding: 20px; text-align: center;'>"
				+ "<h2 style='margin: 0;'>Sol·licitud de Permisos PINBAL</h2>"
				+ "</div>"
				+ "<div style='padding: 20px; background-color: #f8f9fa;'>"
				+ "<p>Bon dia <strong>" + solicitantNom + "</strong>,</p>"
				+ "<p>La seva sol·licitud de permisos <strong>PINFO " + incidenciaID
				+ "</strong> s'ha enviat correctament a firmar.</p>"
				+ "<div style='background-color: white; padding: 15px; border-left: 4px solid #4DBA79; margin: 20px 0;'>"
				+ "<p style='margin: 5px 0;'><strong>Títol:</strong> " + incidencia.getTitol() + "</p>"
				+ "<p style='margin: 5px 0;'><strong>Número de sol·licitud:</strong> " + incidenciaID + "</p>"
				+ "<p style='margin: 5px 0;'><strong>Responsable que firmarà:</strong> " + responsableNom + " ("
				+ responsableNIF + ")</p>"
				+ "</div>"
				+ "<p>El responsable seleccionat rebrà una notificació per procedir amb la signatura electrònica del document.</p>"
				+ "<p><strong>Pot seguir l'estat de la seva sol·licitud accedint al següent enllaç:</strong></p>"
				+ "<div style='text-align: center; margin: 30px 0;'>"
				+ "<a href='" + urlSeguiment + "' style='background-color: #4DBA79; color: white; padding: 12px 30px; "
				+ "text-decoration: none; border-radius: 5px; display: inline-block;'>Veure estat de la sol·licitud</a>"
				+ "</div>"
				+ "<p style='color: #666; font-size: 14px; margin-top: 20px;'>També rebrà notificacions per correu electrònic quan hi hagi actualitzacions en la seva sol·licitud.</p>"
				+ "</div>"
				+ "<div style='background-color: #e9ecef; padding: 15px; text-align: center; font-size: 12px; color: #666;'>"
				+ "<p style='margin: 5px 0;'>Salutacions,</p>"
				+ "<p style='margin: 5px 0;'><em>Àrea de Govern Digital - Fundació BIT</em></p>"
				+ "<p style='margin: 10px 0; padding-top: 10px; border-top: 1px solid #ccc;'>"
				+ "Si us plau, NO CONTESTEU directament a aquest correu. Per a qualsevol consulta, accediu a l'enllaç proporcionat."
				+ "</p>"
				+ "</div>"
				+ "</div>";

		boolean isHtml = true;
		FitxerJPA adjunt = null;

		EmailUtil.postMail(subject, message, isHtml, from, adjunt, destinatariEmail);

		log.info("Email de confirmació enviat a: " + destinatariEmail);
	}

	@Override
	public PinfoFilterForm getPinfoFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		PinfoFilterForm pinfoFilterForm = super.getPinfoFilterForm(pagina, mav, request);

		if (pinfoFilterForm.isNou()) {
			pinfoFilterForm.setTitleCode("tramitpinfo.meus");

			pinfoFilterForm.setVisibleMultipleSelection(false);
			pinfoFilterForm.setDeleteButtonVisible(false);
			pinfoFilterForm.setDeleteSelectedButtonVisible(false);
			pinfoFilterForm.setEditButtonVisible(false);
			pinfoFilterForm.setViewButtonVisible(false);
			pinfoFilterForm.setAddButtonVisible(false);

			// Ocultar campos del solicitante (es el usuario logueado)
			pinfoFilterForm.addHiddenField(SOLICITANTNIF);
			pinfoFilterForm.addHiddenField(SOLICITANTNOM);

			// Ocultar campos individuales de destinatario (los combinaremos en un
			// AdditionalField)
			pinfoFilterForm.addHiddenField(DESTINATARINIF);
			pinfoFilterForm.addHiddenField(DESTINATARINOM);

			// Ocultar campos de ficheros (se añadirán como botones en postList)
			pinfoFilterForm.addHiddenField(FITXERID);
			pinfoFilterForm.addHiddenField(FITXERFIRMATID);

			// Ocultar campos técnicos
			pinfoFilterForm.addHiddenField(PORTAFIBID);
			pinfoFilterForm.addHiddenField(ENTITAT);
			pinfoFilterForm.addHiddenField(MISSATGEPINBAL);
			pinfoFilterForm.addHiddenField(MISSATGESOLICITANT);
			pinfoFilterForm.addHiddenField(LOGPPNBAL);

			// Crear campo adicional para mostrar NIF + Nombre del destinatario combinados
			AdditionalField<Long, String> destinatariField = new AdditionalField<Long, String>();
			destinatariField.setCodeName("pinfo.destinatari");
			destinatariField.setPosition(DESTINATARI_INFO);
			destinatariField.setValueMap(new HashMap<Long, String>());
			destinatariField.setEscapeXml(false); // Para permitir HTML
			pinfoFilterForm.addAdditionalField(destinatariField);

			// Crear campo adicional para la fecha de inicio de la incidencia (calculado en postList)
			AdditionalField<Long, Timestamp> dataIniciField = new AdditionalField<Long, Timestamp>();
			dataIniciField.setCodeName("pinfo.dataInici");
			dataIniciField.setPosition(DATA_INICI_INFO);
			dataIniciField.setValueMap(new HashMap<Long, String>());
			pinfoFilterForm.addAdditionalField(dataIniciField);

			// Añadir botón para ver histórico de eventos
			pinfoFilterForm.addAdditionalButtonForEachItem(
					new AdditionalButton("fas fa-bullhorn", "veure.events",
							CONTEXT_WEB + "/viewevents/{0}", AdditionalButtonStyle.SUCCESS));

			pinfoFilterForm.setItemsPerPage(-1);
			pinfoFilterForm.setFooterListVisible(false);

			OrderBy order = new OrderBy(PinfoFields.PINFOID);
			pinfoFilterForm.setDefaultOrderBy(new OrderBy[] { order });
			pinfoFilterForm.setOrderBy(PinfoFields.PINFOID.fullName);
			pinfoFilterForm.setOrderAsc(false);

			pinfoFilterForm.setAttachedAdditionalJspCode(true);
		}

		return pinfoFilterForm;
	}

	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, PinfoFilterForm filterForm,
			List<Pinfo> list) throws I18NException {

		super.postList(request, mav, filterForm, list);

		// Limpiar botones adicionales previos
		filterForm.getAdditionalButtonsByPK().clear();

		// Obtener el mapa del campo adicional para destinatario
		Map<Long, String> destinatariMap = (Map<Long, String>) filterForm.getAdditionalField(DESTINATARI_INFO)
				.getValueMap();
		destinatariMap.clear();
		
		// Obtener el mapa del campo adicional para fecha de inicio
		Map<Long, String> dataIniciMap = (Map<Long, String>) filterForm.getAdditionalField(DATA_INICI_INFO)
				.getValueMap();
		dataIniciMap.clear();
		
		// OPTIMIZACIÓN: Recopilar todos los IDs de incidencias de la lista actual
		Set<Long> incidenciaIDs = new HashSet<>();
		for (Pinfo pinfo : list) {
			if (pinfo.getIncidenciaID() != null) {
				incidenciaIDs.add(pinfo.getIncidenciaID());
			}
		}
		
		// Cargar TODAS las incidencias en una sola query y crear un mapa para acceso rápido
		Map<Long, IncidenciaTecnica> incidenciaCache = new HashMap<>();
		if (!incidenciaIDs.isEmpty()) {
			Where whereIncidencias = IncidenciaTecnicaFields.INCIDENCIATECNICAID.in(incidenciaIDs.toArray(new Long[0]));
			List<IncidenciaTecnica> incidencias = incidenciaTecnicaLogicaEjb.select(whereIncidencias);
			for (IncidenciaTecnica incidencia : incidencias) {
				incidenciaCache.put(incidencia.getIncidenciaTecnicaID(), incidencia);
			}
		}

		// Procesar cada PINFO
		for (Pinfo pinfo : list) {
			Long pinfoID = pinfo.getPinfoID();

			// Combinar NIF + Nombre del destinatario en una sola celda
			String destinatariNIF = pinfo.getDestinatariNIF();
			String destinatariNom = pinfo.getDestinatariNom();

			StringBuilder destinatariHtml = new StringBuilder();
			if (destinatariNIF != null && !destinatariNIF.isEmpty()) {
				destinatariHtml.append("<div style='line-height: 1.6;'>");
				destinatariHtml.append("<span style='font-weight: 600; color: #2c3e50; display: block;'>");
				destinatariHtml.append(destinatariNIF);
				destinatariHtml.append("</span>");

				if (destinatariNom != null && !destinatariNom.isEmpty()) {
					destinatariHtml.append("<span style='color: #666; font-size: 0.95em;'>");
					destinatariHtml.append(destinatariNom);
					destinatariHtml.append("</span>");
				}
				destinatariHtml.append("</div>");
			}
			destinatariMap.put(pinfoID, destinatariHtml.toString());
			
			// Obtener fecha de inicio de la incidencia desde el cache (sin queries adicionales)
			Long incidenciaID = pinfo.getIncidenciaID();
			if (incidenciaID != null) {
				IncidenciaTecnica incidencia = incidenciaCache.get(incidenciaID);
				if (incidencia != null && incidencia.getDataInici() != null) {
					dataIniciMap.put(pinfoID, SDF.format(incidencia.getDataInici()));
				}
			}

			// Añadir botones de descarga según disponibilidad de ficheros
			if (pinfo.getFitxerfirmat() != null) {
				// Si hay fichero firmado, añadir botón verde para descargarlo
				String downloadUrl = FileDownloadController.fileUrl(pinfo.getFitxerfirmat());
				filterForm.addAdditionalButtonByPK(pinfoID,
						new AdditionalButton("fas fa-file-signature", "descarregar.pdf.firmat",
								downloadUrl, AdditionalButtonStyle.PRIMARY));
			} else if (pinfo.getFitxer() != null) {
				// Si solo hay fichero sin firmar, añadir botón amarillo
				String downloadUrl = FileDownloadController.fileUrl(pinfo.getFitxer());
				filterForm.addAdditionalButtonByPK(pinfoID,
						new AdditionalButton("fas fa-file-pdf", "descarregar.pdf",
								downloadUrl, AdditionalButtonStyle.WARNING));
			}
		}
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
		// Este es el listado de "Els Meus Pinfos", por lo que filtramos por el NIF del
		// solicitante (usuario logueado)
		Where w = super.getAdditionalCondition(request);

		String usuariNIF = (String) request.getSession().getAttribute("usuariNIF");
		Where wPinfosSolicitant = PinfoFields.SOLICITANTNIF.equal(usuariNIF);
		Where wNoCreant = PinfoFields.ESTAT.notEqual(Constants.ESTAT_PINFO_CREANT); // Excluir PINFOs en estado "CREANT"
																					// (en creación)
		Where wNoError = PinfoFields.ESTAT.notEqual(Constants.ESTAT_PINFO_ERROR); // Excluir PINFOs en estado "ERROR"
		return Where.AND(w, wPinfosSolicitant, wNoCreant, wNoError);
	}

	public Event afegirEventPinfoEnviatPortaFIB(Long pinfoID) throws I18NException {

		PinfoJPA pinfo = pinfoLogicaEjb.findByPrimaryKey(pinfoID);
		String destinatariNIF = pinfo.getDestinatariNIF();
		Long fitxerPinfoID = pinfo.getFitxerID();
		Long incidenciaID = pinfo.getIncidenciaID();

		IncidenciaTecnicaJPA it = incidenciaTecnicaLogicaEjb.findByPrimaryKey(incidenciaID);

		EventJPA ev = new EventJPA();
		ev.setIncidenciaTecnicaID(it.getIncidenciaTecnicaID());
		ev.setDataEvent(new Timestamp(System.currentTimeMillis()));
		ev.setNoLlegit(false);
		ev.setPersona(it.getOperador());
		ev.setTipus(Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC);
		ev.setDestinatari(it.getContacteNom());
		ev.setDestinatarimail(it.getContacteEmail());
		ev.setFitxerID(fitxerPinfoID);

		ev.setAsumpte("Pinfo creat i enviat a firmar");
		String msg = "<div style='margin: 0.5rem;'>Bon dia, <br> <br> Des de la Fundació BIT l'informam que el seu Pinfo:\r\n"
				+ "  <div style='margin: 0.7rem;font-weight: bold;'>" + it.getTitol() + "</div>"
				+ "  s'ha donat d'alta correctament i s'ha enviat al seu responsable (" + destinatariNIF
				+ ") per a que ho firmi.</div>";
		ev.setComentari(msg);

		Event evt = eventLogicaEjb.create(ev);
		return evt;
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, PinfoForm pinfoForm) {
		Long itemID = pinfoForm.getPinfo().getIncidenciaID();
		IncidenciaTecnicaJPA it = incidenciaTecnicaLogicaEjb.findByPrimaryKey(itemID);
		String destinatari = it.getContacteNom();
		return redirectToEventsPinfo(itemID, destinatari);
	}

	public String redirectToEventsPinfo(Long incidenciaID, String destinatari) {
		// Configuracio.getAppBackUrl()
		String id = HibernateFileUtil.encryptFileID(incidenciaID);
		String dest = destinatari == null ? "" : ("/" + HibernateFileUtil.encryptString(destinatari));

		String url = "/public/eventincidenciatecnica/veureevents/" + id + dest;

		log.info("redirectToEventsPinfo: " + url);
		return "redirect:" + url;
	}

	@RequestMapping(value = "/viewevents/{pinfoID}")
	public String viewEventsPinfo(@PathVariable("pinfoID") Long pinfoID) {
		PinfoJPA pinfo = pinfoLogicaEjb.findByPrimaryKey(pinfoID);
		Long incidenciaID = pinfo.getIncidenciaID();
		IncidenciaTecnicaJPA it = incidenciaTecnicaLogicaEjb.findByPrimaryKey(incidenciaID);
		String destinatari = "CONTACTE|" + it.getContacteNom();
		log.info("viewEventsPinfo: pinfoID=" + pinfoID + ", incidenciaID=" + incidenciaID + ", destinatari=" + destinatari);
		return redirectToEventsPinfo(incidenciaID, destinatari);
	}

	@Override
	public PinfoJPA update(HttpServletRequest request, PinfoJPA pinfo) throws I18NException, I18NValidationException {
		return (PinfoJPA) pinfoLogicaEjb.update(pinfo);
	}

	public String getRedirectToList() {
		return "redirect:" + getContextWeb() + "/list/1";
	}

	@Override
	public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {

		List<StringKeyValue> _tmp = new java.util.ArrayList<StringKeyValue>();

		Long[] estatsPinfo = Constants.ESTATS_PINFO;
		for (Long estat : estatsPinfo) {
			StringKeyValue skv = new StringKeyValue(estat.toString(), I18NUtils.tradueix("estat.pinfo." + estat));
			_tmp.add(skv);
		}

		return _tmp;
	}
}
