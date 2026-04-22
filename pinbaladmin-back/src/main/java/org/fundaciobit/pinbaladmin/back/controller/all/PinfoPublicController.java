package org.fundaciobit.pinbaladmin.back.controller.all;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetFlowResultResponse;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleViewFlowTemplateRequest;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
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
import org.fundaciobit.pinbaladmin.logic.utils.PortafibUtils;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.fields.PinfoFields;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy.MM.dd.HH.mm");
	public static final String TITOL_FLUX_FIRMA = "titolFluxFirma";
	private static final Map<String, FlowTemplateSimpleFlowTemplate> fluxInfoByTransactonID = new HashMap<String, FlowTemplateSimpleFlowTemplate>();

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
//			pinfoForm.addHiddenField(PINFOID);
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
					PinfoDataPublicController.CONTEXT_WEB + "/list/1" , AdditionalButtonStyle.SECONDARY));
			
			pinfoForm.addAdditionalButton(new AdditionalButton("fas fa-sign", "Enviar a firmar",
					CONTEXT_WEB + "/enviarPinfoPortaFIB/" + pinfoID , AdditionalButtonStyle.PRIMARY));
		}else if (pinfo.getEstat() == Constants.ESTAT_PINFO_INICIANT) {
			//Primera vista del pinfo.
			
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

		//Responsable responsable = (Responsable) request.getSession().getAttribute(PinfoDataPublicController.RESPONSABLE);
		

		//EJB Per enviar peticio a firmar
		pinfoLogicaEjb.enviarPinfoPortaFIB(pinfoID); //, responsable);
		
		// Enviar email informativo al solicitante
		try {
			enviarEmailConfirmacion(pinfoID);
		} catch (Exception e) {
			log.error("Error enviant email de confirmació al solicitant: " + e.getMessage(), e);
			// No bloqueamos el flujo si falla el email
		}
		
		//Redirect to página de confirmación
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
				+ "<p>La seva sol·licitud de permisos <strong>PINFO " + incidenciaID + "</strong> s'ha enviat correctament a firmar.</p>"
				+ "<div style='background-color: white; padding: 15px; border-left: 4px solid #4DBA79; margin: 20px 0;'>"
				+ "<p style='margin: 5px 0;'><strong>Títol:</strong> " + incidencia.getTitol() + "</p>"
				+ "<p style='margin: 5px 0;'><strong>Número de sol·licitud:</strong> " + incidenciaID + "</p>"
				+ "<p style='margin: 5px 0;'><strong>Responsable que firmarà:</strong> " + responsableNom + " (" + responsableNIF + ")</p>"
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
			pinfoFilterForm.setVisibleMultipleSelection(false);
			pinfoFilterForm.setDeleteButtonVisible(false);
			pinfoFilterForm.setDeleteSelectedButtonVisible(false);
			pinfoFilterForm.setEditButtonVisible(false);
			pinfoFilterForm.setViewButtonVisible(true);
			
			pinfoFilterForm.addHiddenField(SOLICITANTNIF);
			pinfoFilterForm.addHiddenField(FITXERID);
			pinfoFilterForm.addHiddenField(FITXERFIRMATID);
			pinfoFilterForm.addHiddenField(PORTAFIBID);
//			pinfoFilterForm.addHiddenField(PINFOID);
		
			pinfoFilterForm.addHiddenField(ENTITAT);
			pinfoFilterForm.addHiddenField(DESTINATARINIF);
			pinfoFilterForm.addHiddenField(MISSATGEPINBAL);
			
			pinfoFilterForm.setItemsPerPage(-1);
			
			pinfoFilterForm.setFooterListVisible(false);
			
			pinfoFilterForm.setAttachedAdditionalJspCode(true);
		}

		return pinfoFilterForm;
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		Where w = super.getAdditionalCondition(request);

		String usuariNIF = (String) request.getSession().getAttribute("usuariNIF");
		Where wPinfosSolicitant = PinfoFields.SOLICITANTNIF.equal(usuariNIF);

		return Where.AND(w, wPinfosSolicitant);
	}

//	@RequestMapping(value = "/enviarPortafib/{pinfoID}")
//	public String enviarPinfoPortaFIB(HttpServletRequest request, @PathVariable("pinfoID") java.lang.Long pinfoID)
//			throws I18NException {

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

	@Override
	public PinfoJPA update(HttpServletRequest request, PinfoJPA pinfo) throws I18NException, I18NValidationException {
		return (PinfoJPA) pinfoLogicaEjb.update(pinfo);
	}

	@RequestMapping(value = "/crearflux", method = RequestMethod.GET)
	public ModelAndView crearFlux(HttpServletRequest request, HttpServletResponse response) {

		log.info("Paso 1: /crearflux");

		request.getSession().removeAttribute("transactionID");

		ApiFlowTemplateSimple api = null;
		String transactionID = null;
		try {

			final String languageUI = LocaleContextHolder.getLocale().getLanguage();

			api = PortafibUtils.getApiFlowTemplateSimple();

			// Crear Flux
			String name = "Flux de Firma  - " + System.currentTimeMillis();

			final String username = null;
			String descr = generateDescription(username, false);

			final boolean saveOnServer = true;
			final boolean visibleDescription = false;

			FlowTemplateSimpleGetTransactionIdRequest transactionRequest;
			transactionRequest = new FlowTemplateSimpleGetTransactionIdRequest(languageUI, saveOnServer, name, descr,
					visibleDescription);

			// Enviam informació bàsica
			transactionID = api.getTransactionID(transactionRequest);

			log.info("Language      = |" + languageUI + "|");
			log.info("SaveOnServer  = |" + saveOnServer + "|");
			log.info("TransactionID = |" + transactionID + "|");

			final String callBackUrl = Configuracio.getAppBackUrl() + "/public/pinfo/callbackflux/" + transactionID;
			log.info("Paso 2.0: callBackUrl: " + callBackUrl);

			// Per ara només suportam FULLVIEW
			FlowTemplateSimpleStartTransactionRequest startTransactionInfo;
			startTransactionInfo = new FlowTemplateSimpleStartTransactionRequest(transactionID, callBackUrl);

			String urlFlow = api.startTransaction(startTransactionInfo);
			log.info("Paso 2.1: api.stratTransaction. urlFlow: " + urlFlow);

			log.info("RedirectUrl Flow = " + urlFlow);

			request.getSession().setAttribute("transactionID", transactionID);

			String titol_flux = "Titol del Flux"; // (String)
													// request.getSession().getAttribute(MenuUserController.TITOL_PETICIO);

			ModelAndView mav = new ModelAndView("flowview");
			mav.addObject(TITOL_FLUX_FIRMA, titol_flux);
			mav.addObject("fluxname", name);
			mav.addObject("urlflow", urlFlow);
			mav.addObject("wizardstep", 1);
			return mav;

		} catch (AbstractApisIBException aaie) {
			String msg = I18NUtils.tradueix("error.flux.creacio", aaie.getMessage());
			log.error(msg, aaie);

			final String intermediateID = null;
			cleanFlux(api, transactionID, intermediateID, log);

			return new ModelAndView(getRedirectToList());
		}
	}

	public static String generateDescription(final String username, final boolean isTemplate) {
		final long current = System.currentTimeMillis();
		final String currentStr = SDF.format(new Date(current));

		String descr = (isTemplate ? "{template=true}" : "{temporal=true}\n") + "{creation=" + current + "}\n"
				+ "{creationStr=" + currentStr + "}\n" + getFluxFilterByUserName(username);
		return descr;
	}

	public static String getFluxFilterByUserName(String username) {
		final String usrapp = Configuracio.getPortaFIBApiFlowUsername();
		// Filtre de Flux de Firmes no filtra bé per descripció
		// (https://github.com/GovernIB/portafib/issues/752)
		return "{usrapp=" + usrapp + "}" + (username == null ? "" : "{owner=" + username + "}");
	}

	@RequestMapping(value = "/callbackflux/{transactionID}")
	public ModelAndView finalProcesDeFlux(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("transactionID") String transactionID) {

		log.info("Paso 3: CallBackFlux - transactionID[" + transactionID + "]");

		// public String testCrearFluxDeFirma(ApiFlowTemplateSimple api, String
		// languageUI, boolean saveOnServer) throws I18NException {
		ApiFlowTemplateSimple api = null;
		String error = null;
		try {

			api = PortafibUtils.getApiFlowTemplateSimple();

			FlowTemplateSimpleGetFlowResultResponse fullResult = api.getFlowTemplateResult(transactionID);

			FlowTemplateSimpleStatus transactionStatus = fullResult.getStatus();

			int status = transactionStatus.getStatus();
			log.info("Paso 4: transactionStatus = " + status);

			switch (status) {

			case FlowTemplateSimpleStatus.STATUS_INITIALIZING: // = 0;
				error = I18NUtils.tradueix("procesdeflux.status.initializing");
				break;

			case FlowTemplateSimpleStatus.STATUS_IN_PROGRESS: // = 1;
				error = I18NUtils.tradueix("procesdeflux.status.inprogress");
				break;

			case FlowTemplateSimpleStatus.STATUS_FINAL_ERROR: // = -1;
			{
				error = I18NUtils.tradueix("procesdeflux.status.finalerror", transactionStatus.getErrorMessage());
				String desc = transactionStatus.getErrorStackTrace();
				if (desc != null) {
					log.error(error + "\n" + desc);
				}

			}
				break;

			case FlowTemplateSimpleStatus.STATUS_CANCELLED: // = -2;
				error = I18NUtils.tradueix("procesdeflux.status.canceled");
				break;

			case FlowTemplateSimpleStatus.STATUS_FINAL_OK: // = 2;
			{
				log.info("Paso 5: STATUS_FINAL_OK");

				FlowTemplateSimpleFlowTemplate flux = fullResult.getFlowInfo();

				// XYZ ZZZ TRA Debug a partir de setembre
				log.info(" ======= FLUX ========= ");
				log.info(FlowTemplateSimpleFlowTemplate.toString(flux));
				log.info(" ---------------------- ");

				log.info(" INTERMEDIATE =====>  |" + flux.getIntermediateServerFlowTemplateId() + "|");

				fluxInfoByTransactonID.put(transactionID, flux);

//				flux.getBlocks().get(0).getSignatures().get(0).getSigner().getAdministrationID();
				
				Long pinfoID = (Long) request.getSession().getAttribute("pinfoID");
				String lang = LocaleContextHolder.getLocale().getLanguage();

				try {
					log.info("Paso 6.1. Arrancar Peticio PortaFIB BACK");
					pinfoLogicaEjb.arrancarPeticioFlux(pinfoID, lang, flux);

					log.info("Paso 12. Petició creada i enviada a PortaFIB");
					Long incidenciaID = pinfoLogicaEjb.findByPrimaryKey(pinfoID).getIncidenciaID();

					log.info("Paso 13. Afegir event de notificació a PortaFIB");
					Event evt = afegirEventPinfoEnviatPortaFIB(pinfoID);
					String cadenaDestinatari = "CONTACTE|" + evt.getDestinatari();

					ModelAndView mav = new ModelAndView("finaliframe");

					String urlRedirect = Configuracio.getAppBackUrl()
							+ redirectToEventsPinfo(incidenciaID, cadenaDestinatari).replace("redirect:", "");
					String urlRedirect2 = request.getContextPath() + getContextWeb() + "/finalWebAuth/" + transactionID;

					log.info("Paso 14: Redirecto to: " + urlRedirect);
					log.info("Paso 14: Redirecto to2: " + urlRedirect2);

					mav.addObject("URL_FINAL", urlRedirect);

					return mav;
				} catch (Exception e) {
					log.error("Error al arrancar peticio de flux: " + e.getMessage(), e);
				}

			} // Final Case Firma OK

			default: {
				log.error("Status desconegut: " + status);
				error = I18NUtils.tradueix("procesdeflux.status.default", String.valueOf(status));
			}

			} // Final Switch Firma

		} catch (AbstractApisIBException aaie) {

			error = I18NUtils.tradueix("error.error.flux.creacioflux", aaie.getMessage());
			log.error(error, aaie);
			cleanFlux(api, transactionID, null, log);
		}

		log.info("Nos quedamos en paso 4. Error de transaccion: " + error);
		log.error(error);
		HtmlUtils.saveMessageError(request, error);

		ModelAndView mav = new ModelAndView("finaliframe");

		String urlRedirectError = request.getContextPath() + getRedirectToList().replace("redirect:", "");
		log.info("Error. Redirecto to: " + urlRedirectError);
		mav.addObject("URL_FINAL", urlRedirectError);
		return mav;

	}

	@RequestMapping(value = "/mostrarflux/{transactionID}/{intermediateID}")
	public ModelAndView mostrarflux(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("transactionID") String transactionID,
			@PathVariable("intermediateID") String intermediateID) {

		ApiFlowTemplateSimple api = null;

		try {

			final String languageUI = LocaleContextHolder.getLocale().getLanguage();
			api = PortafibUtils.getApiFlowTemplateSimple();
			

			{
				
			FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
            flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI, intermediateID);
            FlowTemplateSimpleFlowTemplate flowTemplateSimpleFlowTemplate  = api.getFlowInfoByFlowTemplateID(flowTemplateRequest);
			flowTemplateSimpleFlowTemplate.getBlocks().get(0).getSignatures().get(0).getSigner().getAdministrationID();
			log.info("Paso X: MostrarFlux - transactionID[" + transactionID + "] - intermediateID[" + intermediateID + "]");
			}


			FlowTemplateSimpleViewFlowTemplateRequest viewFlowRequest;
			viewFlowRequest = new FlowTemplateSimpleViewFlowTemplateRequest(languageUI, intermediateID);
			viewFlowRequest.getFlowTemplateID();
			
			String url = api.getUrlToViewFlowTemplate(viewFlowRequest);

			log.info("View Flow Template Url = " + url);

//            String titol_flux = (String) request.getSession().getAttribute(MenuUserController.TITOL_PETICIO);
			String titol_flux = "Titol del Flux 2"; // (String)
													// request.getSession().getAttribute(MenuUserController.TITOL_PETICIO);

			ModelAndView mav = new ModelAndView("flowview");
			mav.addObject(TITOL_FLUX_FIRMA, titol_flux);

			mav.addObject("title", I18NUtils.tradueix("vista.flux"));
			mav.addObject("urlflow", url);

			mav.addObject("wizardstep", 2);
			mav.addObject("continueUrl",
					getContextWeb() + "/new?transactionID=" + transactionID + "&intermediateID=" + intermediateID);

			mav.addObject("cancelUrl", getRedirectToList().replace("redirect:", ""));
			return mav;

		} catch (AbstractApisIBException aaie) {

			String error = I18NUtils.tradueix("error.flux.creacio", aaie.getMessage());
			log.error(error, aaie);
			HtmlUtils.saveMessageError(request, error);

			return new ModelAndView(getRedirectToList());
		}
	}

	public static void cleanFlux(ApiFlowTemplateSimple api, String transactionID, String intermediateID, Logger log) {
		try {
			api.closeTransaction(transactionID);

			if (intermediateID != null) {

				FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
				flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(
						LocaleContextHolder.getLocale().getLanguage(), intermediateID);

				boolean esborrat = api.deleteFlowTemplate(flowTemplateRequest);
				log.error("Resultat esborrat de flux de firma: " + esborrat);

			}

		} catch (Throwable th) {
			String error = I18NUtils.tradueix("error.flux.esborrar", th.getMessage());
			log.error(error, th);
		}
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
