package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.EstadoProcedimiento;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;

@Singleton
@Startup
public class SchedulerConsultaEstatSolicitudPID {
	final long TRANSACTION_EXIT_IN_MILI = 4 * 60 * 1000; // 4 minuts

	public final Logger log = Logger.getLogger(this.getClass());

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;


    @EJB(mappedName = EventLogicaService.JNDI_NAME)
    protected EventLogicaService eventLogicaEjb;

	@Resource
	private TimerService timerService;

	@PostConstruct
	public void init() {
		// Configurar la tarea con valores dinámicos

//		String horaStr = Configuracio.getHoraTancamentExpedientsScheduler(); // 14
//		String nHoresStr = Configuracio.getNhoresTancamentExpedientsScheduler(); // 2
//
//		int nHores = Integer.parseInt(nHoresStr);
//		if (nHores > 1) {
//			int hores = Integer.parseInt(horaStr);
//			horaStr += "-" + (hores + nHores - 1);
//		}
//
//		log.info("initScheduler:: Tancar expedients a les " + horaStr + " hores");
		String horaStr = "14";
		String minuteStr = "00";

		scheduleTask(horaStr, minuteStr);
	}

	public void scheduleTask(String horaStr, String minuteStr) {

		// Limpiar timers anteriores
		for (Timer timer : timerService.getTimers()) {
			timer.cancel();
		}
		ScheduleExpression schedule = new ScheduleExpression();
		schedule.hour(horaStr);
		schedule.minute(minuteStr);

		Timer newTimer = timerService.createCalendarTimer(schedule);
		System.out.println("CREAT Schedule obtenirEstatsSolicitudsPinbal: " + newTimer.getNextTimeout());
	}

	@Timeout
	public void onTimeout(Timer timer) {
		log.info("No executam el cron per canviar estats encara.");
//		obtenirEstatsSolicitudsPinbal();
	}

	protected void obtenirEstatsSolicitudsPinbal() {
		log.info("Comença obtenirEstatsSolicitudsPinbal()");

		long startTime = System.currentTimeMillis();
		try {
			ScspFuncionario funcionario = new ScspFuncionario();
			funcionario.setNifFuncionario("45186147W");
			funcionario.setNombreCompletoFuncionario("Juan Pablo Trias Segura");

			ScspTitular titular = new ScspTitular();
			titular.setTipoDocumentacion(ScspTipoDocumentacion.NIF);
			titular.setDocumentacion("45186147W");
			titular.setNombre("Juan Pablo");
			titular.setApellido1("Trias");
			titular.setApellido2("Segura");
			titular.setNombreCompleto("Juan Pablo Trias Segura");

			// Solicituds locals: només les que tenen Organ Gestor
			Where wSolicitudLocals = SolicitudFields.ORGANID.isNotNull();

			/*
			 * Les solicituds que s'han de consultar:
			 * 
			 * Son les que s'han enviat a Madrid i espren resposta. Si ja tenim la resposta no s'han de consultar.
			 * 
			 * Esperam resposta de les solicitud amb estat SOLI_ESTAT_PENDENT_AUTORITZAR (alta) i SOLI_ESTAT_PENDENT_AUTORITZAR_MODIFICACIO (modificacio).
			 * Si está autoritzat i no es fa cap modificació, no s'ha de tornar a consultar, ja tenim la resposta.
			 * Si donen resposta de Madrid, la solicitud pot pasar a diversos estats, pero no torna a pendent autoritzar, perque ja tenim resposta, ok o ko.
			 * 
			 * 
			 * Error Pinbal fent la consulta:
			 * 
			 * Si dona error fent PRE-ALTAS, l'estat de la solicitud es SOLI_ESTAT_ERROR_ENVIANT_MADRID (_MODIFICACIO). Aquestes s'enviaran una altra vegada amb cron nocturn.
			 * També tenim ESTAT_PINBAL_ERROR. Aquests son solicituds que han donat error fent la consulta. Aquestes s'han de tornar a consultar, s'havien de consultar, han donat error, pero no sabem el nou estat.
			 * 
			 * 
			 * Solicituds pendents d'autoritzar manualment. Hi ha solicituds que s'han enviat manualent, aquestes tenen ESTAT_PINBAL_NO_SOLICITAT, que es el que totes tenen per defecte i que es canvia quan es fa l'alta.
			 * Aquestes no les hem de processar.
			 * 
			 */
			
			// Excloure les que no s'han sol·licitat (estat per defecte)
//			Where wEstatPinbalValid = SolicitudFields.ESTATPINBAL.notEqual(Constants.ESTAT_PINBAL_NO_SOLICITAT);

			Where wEstatSolicitudValid = SolicitudFields.ESTATSOLICITUD.equal(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);

			// Filtres finals: locals + (estat solicitud vàlid o estat pinbal vàlid)
			Where whereFinal = Where.AND(wSolicitudLocals, wEstatSolicitudValid);

			// Ordenació
			OrderBy order = new OrderBy(SolicitudFields.ESTATSOLICITUD, OrderType.DESC);

			// Execució
			List<Solicitud> solicituds = solicitudLogicaEjb.select(whereFinal, order);
			log.info("Solicituds a processar: " + solicituds.size());

			List<String> solicitudConsultades = new ArrayList<String>();
			
			for (Solicitud solicitud : solicituds) {
				
				String codi = solicitud.getProcedimentCodi();
				
				if (codi.length() > 20 ) {
					log.error("Solicitud amb codi llarg: " + codi);
					continue;
				}
				
				if (solicitudConsultades.contains(codi)) {
					log.info("Solicitud " + codi + " ja consultada.");
					continue;
				} else {
					solicitudConsultades.add(codi);
				}
				
				try {
					Long estatPinbalOld = solicitud.getEstatpinbal();
					Retorno retorno = solicitudLogicaEjb.consultaEstatApiPinbal(titular, funcionario, solicitud.getSolicitudID());

					final String SOLICITUD_TROBADA = "0";
					if (retorno.getEstado().getCodigoEstado().equals(SOLICITUD_TROBADA)) {

						EstadoProcedimiento estadoActual = retorno.getProcedimiento().getEstadoProcedimiento();
						Long estatPinbalNou = Long.valueOf(estadoActual.getEstado());
						
						if (estatPinbalOld != estatPinbalNou) {
							if (estatPinbalOld != Constants.ESTAT_PINBAL_ERROR && estatPinbalNou != Constants.ESTAT_PINBAL_ERROR) {
								crearMissatgeCanviEstat(solicitud.getSolicitudID(), estatPinbalOld, estadoActual);
							}
						}
						solicitud.setEstatpinbal(estatPinbalNou);
					} else {
						log.error("No s'ha trobat la solicitud " + codi + " a Pinbal. Estat: " + retorno.getEstado().getCodigoEstado() + " - " + retorno.getEstado().getLiteralError() );
					}
					
				} catch (I18NException e) {
					log.error("Error creant event de canvi de solicitud " + solicitud.getProcedimentCodi() + ": " + e.getMessage());
				} catch (Exception e) {
					log.error("Error al consultar l'estat de la solicitud " + codi + ": " + e.getMessage(), e);
					solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_ERROR);
				}
				
				solicitudLogicaEjb.update(solicitud);
				
				// Si el CRON s'executa durant 2 min, surt del for i acaba la funció.
				if ((System.currentTimeMillis() - startTime) > TRANSACTION_EXIT_IN_MILI) {
					log.warn("Timeout.");
					break;
				}
			}

		} catch (I18NException e) {
			final String msg = "Error al cron obtenirEstatsSolicitudsPinbal():: " + e.getMessage();
			log.error(msg, e);
		}

		long endTime = System.currentTimeMillis();
		log.info("Total time: " + (endTime - startTime));
		log.info("Acaba obtenirEstatsSolicitudsPinbal()");
	}
	
	private void crearMissatgeCanviEstat(Long solicitudID, Long estadoAnterior, EstadoProcedimiento estadoActual) throws I18NException {
		String estadoAnteriorStr = getEstatString(estadoAnterior);
		Long estatActual = Long.valueOf(estadoActual.getEstado());
		String estadoActualStr = getEstatString(estatActual);

    	String msgPinbal = estadoActual.getDescripcion();
    	if (estadoActual.getObservaciones() != null && !estadoActual.getObservaciones().isEmpty()) {
    	    msgPinbal += "<br><br><b>Observacions:</b> " + estadoActual.getObservaciones();
    	}

    	String descripcio = "<div style=\"margin: 0.5rem; font-size: 15px;\">"
    	        + "<b>Actualització de l'estat de la sol·licitud a Pinbal</b><br>"
    	        + "<br>"
    	        + "<b>Estat anterior:</b> " + estadoAnteriorStr + "<br>"
    	        + "<b>Estat actual:</b> " + estadoActualStr + "<br>"
    	        		+ "<br>"
    	        + msgPinbal
    	        + "</div>";

    	String asumpte = "Actualització de l'estat de la solicitud a Pinbal";

		
		// afegir event a la solicitud indicant el canvi d'estat
		Long _incidenciaTecnicaID_ = null;
		
		Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());
		int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
		String _persona_ = "PinbalAdmin";
		boolean _noLlegit_ = true;

		String _caidIdentificadorConsulta_ = null;
		String _caidNumeroSeguiment_ = null;
		String _destinatari_ = null;
		String _destinatariEmail_ = null;

		log.info("Afegint event a la solicitud. Descripció: " + descripcio);
		eventLogicaEjb.create(solicitudID, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_,
				_destinatari_, _destinatariEmail_, asumpte, descripcio, null, _noLlegit_,
				_caidIdentificadorConsulta_, _caidNumeroSeguiment_);
	}
    

	private String getEstatString(Long estado) {
		String estadoActualStr;
		if (estado == Constants.ESTAT_PINBAL_NO_SOLICITAT)
			estadoActualStr = "NO_SOLICITAT";
		else if (estado == Constants.ESTAT_PINBAL_PENDENT_TRAMITAR)
			estadoActualStr = "PENDENT_TRAMITAR";
		else if (estado == Constants.ESTAT_PINBAL_DESISTIT)
			estadoActualStr = "DESISTIT";
		else if (estado == Constants.ESTAT_PINBAL_APROVAT)
			estadoActualStr = "APROVAT";
		else if (estado == Constants.ESTAT_PINBAL_NO_APROVAT)
			estadoActualStr = "NO_APROVAT";
		else if (estado == Constants.ESTAT_PINBAL_PENDENT_SUBSANACIO)
			estadoActualStr = "PENDENT_SUBSANACIO";
		else if (estado == Constants.ESTAT_PINBAL_SUBSANAT)
			estadoActualStr = "SUBSANAT";
		else if (estado == Constants.ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT)
			estadoActualStr = "PENDENT_AUTORITZACIO_CEDENT";
		else if (estado == Constants.ESTAT_PINBAL_AUTORITZAT)
			estadoActualStr = "AUTORITZAT";
		else if (estado == Constants.ESTAT_PINBAL_DESESTIMAT)
			estadoActualStr = "DESESTIMAT";
		else if (estado == Constants.ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO)
			estadoActualStr = "AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO";
		else
			estadoActualStr = "ERROR";
		return estadoActualStr;
	}

}
