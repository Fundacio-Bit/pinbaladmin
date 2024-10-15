package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = SolicitudActivaOperadorController.CONTEXTWEB)
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudActivaOperadorController extends SolicitudOperadorController {

	public static final String CONTEXTWEB = "/operador/solicitudactiva";

	@Override
	public Where getAdditionalConditionFine(HttpServletRequest request) throws I18NException {
		return SolicitudFields.ESTATID.lessThan(60L); // 60 == ESTAT TANCAT
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "SolicitudWebDB_Activa_FilterForm_Operador";
	}

	@Override
	public String getEntityNameCode() {
		return "solicitud.solicitudactiva";
	}

	@Override
	public String getEntityNameCodePlural() {
		return "solicitud.solicitudactiva.plural";
	}

	@Override
	public Boolean isEstatal() {
		return null; // Significa que gestiona els dos tipus
	}

	@Override
	public boolean showAdvancedFilter() {
		return true;
	}

	@Override
	public SolicitudFilterForm getSolicitudFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		// TODO Auto-generated method stub
		SolicitudFilterForm solicitudFilterForm = super.getSolicitudFilterForm(pagina, mav, request);

		if (solicitudFilterForm.isNou()) {
			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_BELL, "solicitud.actualitzarestats",
					getContextWeb() + "/actualitzarEstats", AdditionalButtonStyle.WARNING));
		}

		return solicitudFilterForm;
	}

//  /actualitzarEstats
	@RequestMapping(value = "/actualitzarEstats", method = RequestMethod.GET)
	public String actualitzarEstats(HttpServletRequest request, HttpServletResponse response) throws I18NException {

		// Actualitzar estat de les sol·licituds
		log.info("Actualizaremos el estado de las solicitudes pendientes");

		long anticEstatPendent = 10;

		Long[] estatsAProcesar = { Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Director,
				Constants.SOLICITUD_ESTAT_PENDENT_ENVIAR_MADRID, Constants.SOLICITUD_ESTAT_PENDENT_Firma_Director,
				Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR, Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Cedents,
				Constants.SOLICITUD_ESTAT_PENDENT_Firma_Cedent, anticEstatPendent };

		List<Solicitud> list = solicitudLogicaEjb.select(SolicitudFields.ESTATID.in(estatsAProcesar));

		log.info("Sol·licituds a actualitzar: " + list.size());
		int updates = 0;
		for (Solicitud soli : list) {
			Long nouEstat;
			Long soliID = soli.getSolicitudID();
			if (soli.getOrganid() != null) {
				// Si es local, veure si está pendent d'enviar a DG, pendent de DG, pendent
				// d'enviar a Madrid, o pendent d'autoritzar

				if (isFirmatPelDirector(soli.getSolicitudID())) {
					// Vuere si ja s'ha enviat a Madrid. Utilitzar l'estat Pinbal
					Integer estatPinbal = soli.getEstatpinbal();
					if (estatPinbal == null) {
						soli.setEstatpinbal(Constants.ESTAT_PINBAL_NO_SOLICITAT);
					}

					if (soli.getEstatpinbal() == Constants.ESTAT_PINBAL_NO_SOLICITAT) {
						log.info("LOCAL - SoliID :" + soliID + " firmada director i no enviada a Madrid");
						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_ENVIAR_MADRID;
					} else if (soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR) {
						log.info("LOCAL - SoliID :" + soliID + " enviada a Madrid amb ERROR");
						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR;
					} else {
						log.info("LOCAL - SoliID :" + soliID + " firmada director i enviada a Madrid");
						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR;
					}
				} else {
					// No te el document firmat. Comprovar si s'ha enviat o no.
					if (isEnviatAFirmar(soliID)) {
						log.info("LOCAL - SoliID :" + soliID + " no firmada director, pero enviada a firmar");
						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Firma_Director;
					} else {
						log.info("LOCAL - SoliID :" + soliID + " no enviada a firmar al director");
						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Director;
					}
				}

			} else {
				// Solicituts estatals
				// S'ha de veure si s'han enviat correus de consulta a cedents. Si no n'hi ha,
				// pendent d'enviar a cedents. Si n'hi ha, pendent firma_cedents. Si hi ha
				// tantes consultes a cedents com respostes, penent autoritzar

				List<Event> eventsSoliEstatal = eventLogicaEjb.select(EventFields.SOLICITUDID.equal(soliID));
				int numConsultes = 0;
				int numRespostes = 0;

				for (Event event : eventsSoliEstatal) {
					if (event.getTipus() == Constants.EVENT_TIPUS_CONSULTA_A_CEDENT) {
						numConsultes++;
					} else if (event.getTipus() == Constants.EVENT_TIPUS_CEDENT_RESPOSTA) {
						numRespostes++;
					}
				}

				if (numConsultes == 0) {
					log.info("ESTATAL - SoliID :" + soliID + " no hi ha consultes a cedents");
					nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Cedents;
				} else if (numConsultes == numRespostes) {
					log.info("ESTATAL - SoliID :" + soliID + " Totes les consultes a cedents respostes (" + numRespostes
							+ "/" + numConsultes + ")");
					nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR;
				} else {
					log.info("ESTATAL - SoliID :" + soliID + " Consultes a cedents pendents de resposta ("
							+ numRespostes + "/" + numConsultes + ")");
					nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Firma_Cedent;
				}
			}

			if (nouEstat != soli.getEstatID()) {
				updates ++;
                soli.setEstatID(nouEstat);
                solicitudLogicaEjb.update(soli);
			}
		}

		HtmlUtils.saveMessageSuccess(request, "Estat de les " + updates + " sol·licituds actualitzat correctament.");
		return "redirect:" + getContextWeb() + "/list";

	}

	@EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService documentSolicitudEjb;

	@EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.ejb.DocumentService documentEjb;

	public boolean isFirmatPelDirector(Long soliID) throws I18NException {

		List<Long> listDocumentsSolicitud = documentSolicitudEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(soliID));

		List<Document> documentsPDF = documentEjb.select(Where.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud),
				DocumentFields.TIPUS.equal(Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF)));

		for (Document document : documentsPDF) {
			if (document.getFitxerFirmatID() != null) {
				return true;
			}
		}

		return false;
	}

	public boolean isEnviatAFirmar(Long soliID) throws I18NException {

		List<Long> listDocumentsSolicitud = documentSolicitudEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(soliID));

		List<Document> documentsPDF = documentEjb.select(Where.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud),
				DocumentFields.TIPUS.equal(Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF)));

		for (Document document : documentsPDF) {
			if (document.getNotes() != null && document.getNotes().trim().length() > 0) {
				return true;
			}
		}

		return false;
	}
}
