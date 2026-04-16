package org.fundaciobit.pinbaladmin.logic;

import javax.ejb.Local;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

/**
 * Servicio para la creación centralizada de notificaciones y eventos.
 * 
 * @author PinbalAdmin Team
 */
@Local
public interface NotificacionLogicaService {

	String JNDI_NAME = "java:app/pinbaladmin-ejb/NotificacionLogicaEJB!"
			+ "org.fundaciobit.pinbaladmin.logic.NotificacionLogicaService";

	/**
	 * Notifica a los tramitadores sobre una solicitud desestimada por Madrid. NO
	 * envía email al contacto (mensaje de Madrid suele ser ilegible).
	 * 
	 * @param solicitud    La solicitud desestimada
	 * @param motivoMadrid El mensaje técnico de Madrid
	 * @param tipoProceso  "AUTORITZACIÓ" o "MODIFICACIÓ"
	 */
	void notificarDesestimacionATramitadores(SolicitudJPA solicitud, String motivoMadrid, String tipoProceso)
			throws I18NException;

	/**
	 * Notifica al contacto que su solicitud ha sido autorizada. ENVÍA email al
	 * contacto (buena noticia).
	 * 
	 * @param solicitud La solicitud autorizada
	 */
	void notificarAutorizacionAContacto(SolicitudJPA solicitud) throws I18NException;

	/**
	 * Registra que Madrid ha autorizado la solicitud. Este evento es PÚBLICO 
	 * (visible para tramitadores y contacto) pero NO envía email automáticamente.
	 * El tramitador decidirá cuándo enviar el email de notificación.
	 * 
	 * @param solicitud La solicitud autorizada
	 */
	void registrarAutorizacionDesdeMadrid(SolicitudJPA solicitud) throws I18NException;

	/**
	 * Registra un evento interno de envío a Madrid. Solo visible para tramitadores,
	 * NO envía email.
	 * 
	 * @param solicitud   La solicitud
	 * @param operador    El operador que realiza la acción
	 * @param tipoProceso "Solicitud" o "Modificació"
	 * @param mensaje     Descripción del resultado
	 */
	void registrarEnvioAMadrid(SolicitudJPA solicitud, String operador, String tipoProceso, String mensaje)
			throws I18NException;

	/**
	 * Registra la recepción de un documento firmado desde PortaFIB. Solo visible
	 * para tramitadores, NO envía email. Marca como NO LEÍDO porque requiere que el
	 * tramitador continúe el proceso.
	 * 
	 * @param solicitud La solicitud
	 * @param fitxerID  El ID del archivo firmado
	 */
	void registrarRecepcionFirma(SolicitudJPA solicitud, Long fitxerID) throws I18NException;

	/**
	 * Registra cambio de estado detectado por el scheduler automático. Solo visible
	 * para tramitadores.
	 * 
	 * @param solicitud         La solicitud
	 * @param estadoAnterior    Estado anterior
	 * @param estadoActual      Estado actual
	 * @param descripcionMadrid Descripción de Madrid
	 * @param observaciones     Observaciones adicionales (puede ser null)
	 */
	void registrarCambioEstadoScheduler(SolicitudJPA solicitud, Long estadoAnterior, Long estadoActual,
			String descripcionMadrid, String observaciones) throws I18NException;

	/**
	 * Crea un evento para consulta a cedente. ENVÍA email al cedente con adjunto.
	 * 
	 * @param solicitud     La solicitud
	 * @param operador      Operador que hace la consulta
	 * @param cedente       Nombre del cedente
	 * @param emailsCedente Emails del cedente (separados por ;)
	 * @param asunto        Asunto del mensaje
	 * @param mensaje       Contenido del mensaje
	 * @param adjuntoID     ID del archivo adjunto (Excel)
	 */
	void crearConsultaACedente(SolicitudJPA solicitud, String operador, String cedente, String emailsCedente,
			String asunto, String mensaje, Long adjuntoID) throws I18NException;
}
