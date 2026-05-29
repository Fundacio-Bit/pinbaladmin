package org.fundaciobit.pinbaladmin.logic;

import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servicio centralizado para la creación de notificaciones y eventos.
 * 
 * TIPOS DE COMENTARIOS: - PRIVADOS (valores negativos): Solo visibles para
 * tramitadores - PÚBLICOS (valores positivos): Visibles para tramitadores Y
 * contacto/destinatario
 * 
 * REGLA DE ENVÍO DE EMAIL: Solo se envía email cuando el mensaje SALE de
 * tramitadores hacia otra persona. Las respuestas que recibimos (de contacto,
 * cedente, etc.) NO envían email.
 * 
 * @author PinbalAdmin Team
 */
@Stateless(name = "NotificacionLogicaEJB")
public class NotificacionLogicaEJB implements NotificacionLogicaService {

	private static final Logger log = LoggerFactory.getLogger(NotificacionLogicaEJB.class);

	@EJB(mappedName = EventLogicaService.JNDI_NAME)
	protected EventLogicaService eventLogicaEjb;

	@EJB(mappedName = ContacteLogicaService.JNDI_NAME)
	protected ContacteLogicaService contacteLogicaEjb;

	// ========================================================================
	// MÉTODOS PÚBLICOS - Notificaciones específicas del flujo PREALTAS
	// ========================================================================

	@Override
	public void notificarDesestimacionATramitadores(SolicitudJPA solicitud, String motivoMadrid, String tipoProceso)
			throws I18NException {

		String asunto = String.format("DESESTIMACIÓ des de Madrid: PROCÉS %s PROCEDIMENT %s", tipoProceso,
				solicitud.getProcedimentCodi());

		String mensaje = generarMensajeDesestimacionParaTramitadores(solicitud, motivoMadrid, tipoProceso);

		log.info("Notificando desestimación a tramitadores (PRIVADO): solicitud={}, tipo={}", 
				solicitud.getSolicitudID(), tipoProceso);

		// Tipo TRAMITADOR_PRIVAT = mensaje PRIVADO (solo tramitadores)
		// Es una notificación de Madrid hacia nosotros, NO del contacto
		crearEventoPrivado(solicitud, 
				Constants.SISTEMA_PINBALADMIN + " - Madrid", // Claramente viene del sistema
				asunto, mensaje, 
				true // Marcar como no leído para llamar atención
		);
	}

	@Override
	public void notificarAutorizacionAContacto(SolicitudJPA solicitud) throws I18NException {

		String asunto = String.format("PROCÉS AUTORITZACIÓ PROCEDIMENT %s. Procediment Autoritzat.",
				solicitud.getProcedimentCodi());

		String mensaje = String.format("La seva sol·licitud amb codi %s ha estat autoritzada. "
				+ "Ja pot procedir a realitzar els tràmits que desitgi.", solicitud.getProcedimentCodi());

		Long contacteID = solicitud.getContacteSolicitantID();
		Contacte solicitant = contacteLogicaEjb.findByPrimaryKey(contacteID);
		
		log.info("Notificando autorización a contacto: solicitud={}, email={}", solicitud.getSolicitudID(),
				solicitant.getMail());

		// Tipo TRAMITADOR_PUBLIC = mensaje público que SALE de tramitadores
		// Envía email al contacto
		crearEventoPublicoConEmail(solicitud, Constants.SISTEMA_PINBALADMIN, solicitant.getNom(),
				solicitant.getMail(), asunto, mensaje);
	}

	@Override
	public void registrarAutorizacionDesdeMadrid(SolicitudJPA solicitud) throws I18NException {

		String asunto = String.format("PROCÉS AUTORITZACIÓ PROCEDIMENT %s. Procediment Autoritzat.",
				solicitud.getProcedimentCodi());

		String mensaje = String.format(
				"<div style=\"margin: .5rem;\">"
				+ "La Plataforma Estatal d'Interoperabilitat (PID) ha AUTORITZAT la sol·licitud "
				+ "del procediment amb codi <b>%s</b>.<br><br>"
				+ "<i>Nota: El tramitador enviarà el correu de notificació al contacte quan correspongui.</i>"
				+ "</div>",
				solicitud.getProcedimentCodi());

		log.info("Registrando autorización desde Madrid: solicitud={}", solicitud.getSolicitudID());

		// Tipo CONTACTE = mensaje PÚBLICO (tramitadores y contacto lo ven) 
		// pero NO envía email (representa información recibida de Madrid)
		crearEventoPublicoSinEmail(solicitud, 
				Constants.SISTEMA_PINBALADMIN + " - Madrid", 
				asunto, 
				mensaje, 
				true // Marcar como NO LEÍDO para que tramitador lo vea
		);
	}

	@Override
	public void registrarEnvioAMadrid(SolicitudJPA solicitud, String operador, String tipoProceso, String mensaje)
			throws I18NException {

		String asunto = String.format("%s enviada a MADRID. %s", tipoProceso, solicitud.getProcedimentCodi());

		String mensajeCompleto = String.format("S'ha enviat la %s a MADRID. %s", tipoProceso.toLowerCase(), mensaje);

		log.info("Registrando envío a Madrid: solicitud={}, tipo={}", solicitud.getSolicitudID(), tipoProceso);

		// Tipo TRAMITADOR_PRIVAT = solo para tramitadores, NO envía email
		crearEventoPrivado(solicitud, "pinbaladmin - " + operador, asunto, mensajeCompleto, false // No requiere
																									// atención
																									// inmediata
		);
	}

	@Override
	public void registrarRecepcionFirma(SolicitudJPA solicitud, Long fitxerID) throws I18NException {

		log.info("Registrando recepción de firma: solicitud={}, archivo={}", solicitud.getSolicitudID(), fitxerID);

		// Tipo TRAMITADOR_PRIVAT = solo tramitadores
		// NO LEÍDO = requiere acción del tramitador (enviar a Madrid)
		crearEventoPrivadoConAdjunto(solicitud, Constants.SISTEMA_PORTAFIB + " - " + Constants.SISTEMA_PINBALADMIN,
				"Guardat Fitxer Firmat", "Solicitud Firmada rebuda de Portafib", fitxerID, true // NO LEÍDO - requiere
																								// que tramitador envíe
																								// a Madrid
		);
	}

	@Override
	public void registrarCambioEstadoScheduler(SolicitudJPA solicitud, Long estadoAnterior, Long estadoActual,
			String descripcionMadrid, String observaciones) throws I18NException {

		String estadoAntStr = obtenerNombreEstado(estadoAnterior);
		String estadoActStr = obtenerNombreEstado(estadoActual);

		StringBuilder mensaje = new StringBuilder();
		mensaje.append("<div style=\"margin: 0.5rem; font-size: 15px;\">")
				.append("<b>Actualització de l'estat de la sol·licitud a Pinbal</b><br><br>")
				.append("<b>Estat anterior:</b> ").append(estadoAntStr).append("<br>").append("<b>Estat actual:</b> ")
				.append(estadoActStr).append("<br><br>").append(descripcionMadrid);

		if (observaciones != null && !observaciones.isEmpty()) {
			mensaje.append("<br><br><b>Observacions:</b> ").append(observaciones);
		}

		mensaje.append("</div>");

		log.info("Registrando cambio de estado: solicitud={}, {} → {}", solicitud.getSolicitudID(), estadoAntStr,
				estadoActStr);

		crearEventoPrivado(solicitud, Constants.SISTEMA_PINBALADMIN,
				"Actualització de l'estat de la solicitud a Pinbal", mensaje.toString(), true // NO LEÍDO - cambio
																								// automático requiere
																								// revisión
		);
	}

	@Override
	public void crearConsultaACedente(SolicitudJPA solicitud, String operador, String cedente, String emailsCedente,
			String asunto, String mensaje, Long adjuntoID) throws I18NException {

		log.info("Creando consulta a cedente: solicitud={}, cedente={}, emails={}", solicitud.getSolicitudID(), cedente,
				emailsCedente);

		EventJPA event = new EventJPA();
		event.setSolicitudID(solicitud.getSolicitudID());
		event.setDataEvent(new Timestamp(System.currentTimeMillis()));
		event.setTipus(Constants.EVENT_TIPUS_CONSULTA_A_CEDENT); // ENVÍA EMAIL
		event.setPersona(operador);
		event.setDestinatari(cedente);
		event.setDestinatarimail(emailsCedente);
		event.setAsumpte(asunto);
		event.setComentari(mensaje);
		event.setFitxerID(adjuntoID);
		event.setNoLlegit(false);
		event.setCaidIdentificadorConsulta(null);
		event.setCaidNumeroSeguiment(null);

		eventLogicaEjb.create(event);
	}

	// ========================================================================
	// MÉTODOS PRIVADOS - Creación de eventos base
	// ========================================================================

	/**
	 * Crea un evento PRIVADO (solo tramitadores, NO envía email). Tipo:
	 * EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT (-1)
	 */
	private void crearEventoPrivado(SolicitudJPA solicitud, String persona, String asunto, String mensaje,
			boolean noLeido) throws I18NException {

		EventJPA event = new EventJPA();
		event.setSolicitudID(solicitud.getSolicitudID());
		event.setIncidenciaTecnicaID(null);
		event.setDataEvent(new Timestamp(System.currentTimeMillis()));
		event.setTipus(Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT);
		event.setPersona(persona);
		event.setDestinatari(null);
		event.setDestinatarimail(null);
		event.setAsumpte(asunto);
		event.setComentari(mensaje);
		event.setFitxerID(null);
		event.setNoLlegit(noLeido);
		event.setCaidIdentificadorConsulta(null);
		event.setCaidNumeroSeguiment(null);

		eventLogicaEjb.create(event);
	}

	/**
	 * Crea un evento PRIVADO con adjunto.
	 */
	private void crearEventoPrivadoConAdjunto(SolicitudJPA solicitud, String persona, String asunto, String mensaje,
			Long fitxerID, boolean noLeido) throws I18NException {

		EventJPA event = new EventJPA();
		event.setSolicitudID(solicitud.getSolicitudID());
		event.setIncidenciaTecnicaID(null);
		event.setDataEvent(new Timestamp(System.currentTimeMillis()));
		event.setTipus(Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT);
		event.setPersona(persona);
		event.setDestinatari(null);
		event.setDestinatarimail(null);
		event.setAsumpte(asunto);
		event.setComentari(mensaje);
		event.setFitxerID(fitxerID);
		event.setNoLlegit(noLeido);
		event.setCaidIdentificadorConsulta(null);
		event.setCaidNumeroSeguiment(null);

		eventLogicaEjb.create(event);
	}

	/**
	 * Crea un evento PÚBLICO sin email (mensaje que "recibimos"). Tipo:
	 * EVENT_TIPUS_COMENTARI_CONTACTE (2)
	 * 
	 * Aunque es público (tramitadores y contacto lo ven), NO envía email porque
	 * representa un mensaje que viene "del contacto" hacia nosotros.
	 */
	private void crearEventoPublicoSinEmail(SolicitudJPA solicitud, String persona, String asunto, String mensaje,
			boolean noLeido) throws I18NException {

		EventJPA event = new EventJPA();
		event.setSolicitudID(solicitud.getSolicitudID());
		event.setIncidenciaTecnicaID(null);
		event.setDataEvent(new Timestamp(System.currentTimeMillis()));
		event.setTipus(Constants.EVENT_TIPUS_COMENTARI_CONTACTE);
		event.setPersona(persona);
		event.setDestinatari(null);
		event.setDestinatarimail(null);
		event.setAsumpte(asunto);
		event.setComentari(mensaje);
		event.setFitxerID(null);
		event.setNoLlegit(noLeido);
		event.setCaidIdentificadorConsulta(null);
		event.setCaidNumeroSeguiment(null);

		eventLogicaEjb.create(event);
	}

	/**
	 * Crea un evento PÚBLICO con email (mensaje que "enviamos"). Tipo:
	 * EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC (1)
	 * 
	 * Envía email al destinatario porque es un mensaje que SALE de tramitadores.
	 */
	private void crearEventoPublicoConEmail(SolicitudJPA solicitud, String persona, String destinatario,
			String emailDestinatario, String asunto, String mensaje) throws I18NException {

		EventJPA event = new EventJPA();
		event.setSolicitudID(solicitud.getSolicitudID());
		event.setIncidenciaTecnicaID(null);
		event.setDataEvent(new Timestamp(System.currentTimeMillis()));
		event.setTipus(Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC);
		event.setPersona(persona);
		event.setDestinatari(destinatario);
		event.setDestinatarimail(emailDestinatario);
		event.setAsumpte(asunto);
		event.setComentari(mensaje);
		event.setFitxerID(null);
		event.setNoLlegit(false);
		event.setCaidIdentificadorConsulta(null);
		event.setCaidNumeroSeguiment(null);

		eventLogicaEjb.create(event);
	}

	// ========================================================================
	// UTILIDADES
	// ========================================================================

	/**
	 * Genera el mensaje HTML para una desestimación.
	 */
	private String generarMensajeDesestimacion(SolicitudJPA solicitud, String motivoMadrid) {
		String respostaMadrid = (motivoMadrid != null) ? motivoMadrid : "";
		respostaMadrid = respostaMadrid.replace("\n", "<br>");

		String urlEsmena = generarURLEsmena(solicitud);

		String missatge = "<div style=\"margin: .5rem;\">" + "Bon dia,<br>" + "<br>"
				+ "Des de la Plataforma Estatal d'Interoperabilitat (PID) han DESESTIMAT la sol·liciud d'autorització del procediment amb codi "
				+ solicitud.getProcedimentCodi() + " . <br>" + "<br>" + "Ens indiquen el següent motiu:"
				+ "<div style=\"margin: 1rem 2rem;font-style: italic;\">" + respostaMadrid + "</div>"
				+ "Per poder tramitar aquesta esmena, podeu accedir al segënt enllaç:<br>"
				+ "<button style=\"background-color: #4CAF50; border: none; color: white; padding: 12px 24px; "
				+ "font-size: 16px; margin: 1rem 2rem; border-radius: 4px; cursor: pointer;\">\n" + "<a href='"
				+ urlEsmena + "' style=\"color: white; text-decoration: none;\">Esmenar solicitud</a>\n" + "</button>"
				+ "</div>";

		return missatge;
	}

	/**
	 * Genera el mensaje HTML para una desestimación dirigido a TRAMITADORES.
	 * Incluye información técnica y deja claro que es una notificación interna.
	 */
	private String generarMensajeDesestimacionParaTramitadores(SolicitudJPA solicitud, String motivoMadrid, String tipoProceso) {
		String respostaMadrid = (motivoMadrid != null) ? motivoMadrid : "(Sense motiu especificat)";
		respostaMadrid = respostaMadrid.replace("\n", "<br>");
		
		String urlEsmena = generarURLEsmena(solicitud);

		Long contacteID = solicitud.getContacteSolicitantID();
		Contacte solicitant = contacteLogicaEjb.findByPrimaryKey(contacteID);
		
		
		
		String missatge = "<div style=\"margin: .5rem;\">" 
				+ "<strong>NOTIFICACIÓ INTERNA - DESESTIMACIÓ DES DE MADRID</strong><br><br>"
				+ "La Plataforma Estatal d'Interoperabilitat (PID) ha DESESTIMAT la sol·licitud:<br><br>"
				+ "<div style=\"margin: 0.5rem 1rem;\">"
				+ "• <strong>Tipus de procés:</strong> " + tipoProceso + "<br>"
				+ "• <strong>Procediment:</strong> " + solicitud.getProcedimentCodi() + "<br>"
				+ "• <strong>ID Sol·licitud:</strong> " + solicitud.getSolicitudID() + "<br>"
				+ "• <strong>Contacte:</strong> " + (solicitant.getNombrecompleto() != null ? solicitant.getNombrecompleto() : "N/D") + "<br>"
				+ "</div><br>"
				+ "<strong>Motiu de la desestimació:</strong><br>"
				+ "<div style=\"margin: 0.5rem 1rem; padding: 0.5rem; background-color: #f8f9fa; border-left: 3px solid #dc3545; font-style: italic;\">"
				+ respostaMadrid 
				+ "</div><br>"
				+ "<strong>Acció requerida:</strong><br>"
				+ "<div style=\"margin: 0.5rem 1rem;\">"
				+ "Si us plau, contactau amb el sol·licitant per informar-lo de la desestimació "
				+ "i coordinar les esmenes necessàries.<br><br>"
				+ "Podeu accedir directament a l'esmena mitjançant el següent enllaç:<br>"
				+ "<button style=\"background-color: #4CAF50; border: none; color: white; padding: 12px 24px; "
				+ "font-size: 16px; margin: 1rem 0; border-radius: 4px; cursor: pointer;\">\n" 
				+ "<a href='" + urlEsmena + "' style=\"color: white; text-decoration: none;\">Esmenar sol·licitud</a>\n" 
				+ "</button><br>"
				+ "o amb aquesta url: <a href='" + urlEsmena + "'>" + urlEsmena + "</a><br><br>"
				+ "</div>"
				+ "</div>";

		return missatge;
	}

	/**
	 * Genera la URL para esmenar una solicitud.
	 */
	private String generarURLEsmena(SolicitudJPA solicitud) {
		String url = Configuracio.getAppBackUrl() + "/public/esmenarSolicitud/tramitEsmena/"
				+ solicitud.getSolicitudID();
		return url;
	}

	/**
	 * Obtiene el nombre descriptivo de un estado.
	 */
	private String obtenerNombreEstado(Long estado) {
		if (estado == null)
			return "DESCONOCIDO";

		if (estado.equals(Constants.ESTAT_PINBAL_NO_SOLICITAT))
			return "NO_SOLICITAT";
		else if (estado.equals(Constants.ESTAT_PINBAL_PENDENT_TRAMITAR))
			return "PENDENT_TRAMITAR";
		else if (estado.equals(Constants.ESTAT_PINBAL_DESISTIT))
			return "DESISTIT";
		else if (estado.equals(Constants.ESTAT_PINBAL_APROVAT))
			return "APROVAT";
		else if (estado.equals(Constants.ESTAT_PINBAL_NO_APROVAT))
			return "NO_APROVAT";
		else if (estado.equals(Constants.ESTAT_PINBAL_PENDENT_SUBSANACIO))
			return "PENDENT_SUBSANACIO";
		else if (estado.equals(Constants.ESTAT_PINBAL_SUBSANAT))
			return "SUBSANAT";
		else if (estado.equals(Constants.ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT))
			return "PENDENT_AUTORITZACIO_CEDENT";
		else if (estado.equals(Constants.ESTAT_PINBAL_AUTORITZAT))
			return "AUTORITZAT";
		else if (estado.equals(Constants.ESTAT_PINBAL_DESESTIMAT))
			return "DESESTIMAT";
		else if (estado.equals(Constants.ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO))
			return "AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO";
		else if (estado.equals(Constants.ESTAT_PINBAL_ERROR))
			return "ERROR";
		else
			return "ESTADO_" + estado;
	}
}
