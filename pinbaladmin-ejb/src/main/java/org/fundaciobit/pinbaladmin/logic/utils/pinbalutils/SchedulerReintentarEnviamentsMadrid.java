package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Schedules;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.jboss.ejb3.annotation.TransactionTimeout;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;

@Singleton
@Startup
public class SchedulerReintentarEnviamentsMadrid {
	final long TRANSACTION_EXIT_IN_MILI = 4 * 60 * 1000; // 4 minuts

	public final Logger log = Logger.getLogger(this.getClass());

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;

    @EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
    protected InfoMadridLogicaService infoMadridLogicaEjb;
    
    @EJB(mappedName = PinbalUtilsModificacioLogicaService.JNDI_NAME)
    protected PinbalUtilsModificacioLogicaService pinbalModificacioLogicaEjb;

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
		String horaStr = "12";
		String minuteStr = "20";
		
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
		System.out.println("CREAT Schedule enviarSolicitudsAmbErrorMadrid: " + newTimer.getNextTimeout());
	}

	@Timeout
	public void onTimeout(Timer timer) {
		enviarSolicitudsAmbErrorMadrid();
	}

	private void enviarSolicitudsAmbErrorMadrid() {
		log.info("Comença enviarSolicitudsAmbErrorMadrid()");
		long startTime = System.currentTimeMillis();

		try {
			// Funcionario i titular de sistema
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

			// Filtres
			Where wSolicitudLocals = SolicitudFields.ORGANID.isNotNull();
			
			Where wEstatSolicitudError = SolicitudFields.ESTATSOLICITUD.equal(Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID);
			Where whereFinal = Where.AND(wSolicitudLocals, wEstatSolicitudError);

			List<Solicitud> solicituds = solicitudLogicaEjb.select(whereFinal,
					new OrderBy(SolicitudFields.ESTATSOLICITUD, OrderType.DESC));
			log.info("Solicituds a processar: " + solicituds.size());

			List<String> codisConsultats = new ArrayList<>();

			PinbalUtilsAltaLogicaEJB alta = new PinbalUtilsAltaLogicaEJB();

			for (Solicitud solicitud : solicituds) {
				String codi = solicitud.getProcedimentCodi();

				if (codisConsultats.contains(codi)) {
					log.info("Solicitud " + codi + " ja consultada.");
					continue;
				}
				codisConsultats.add(codi);

				try {
					InfoMadridJPA infoMad = infoMadridLogicaEjb.findByPrimaryKey(solicitud.getInfomadridid());

					// Si no ha sido autorizado, enviamos una ALTA. Si ya ha sido autorizado,
					// enviamos una MODIFICACION.
					if (infoMad.getDataAutoritzacio() == null) {
						// Construir el texto consulta personalizado
//						String consultaTexto = "Buenos días,\n"
//								+ "Enviamos solicitud para dar servicios de alta en el procedimiento "
//								+ solicitud.getProcedimentCodi() + "\n\n" + "Quedamos a la espera de su respuesta.\n"
//								+ "Un saludo.";

						// Obtener la solicitud para alta con la consulta configurada
						es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud solicitudAlta = solicitudLogicaEjb
								.getDadesSolicitudApiPinbalAlta((SolicitudJPA) solicitud);

//						solicitudAlta.setConsulta(consultaTexto);

						// Enviar solicitud
						es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta resposta = alta
								.altaSolicitudApiPinbal(titular, funcionario, solicitudAlta);

						// 3. Procesar respuesta: ACTUALIZAR SOLI + CREAR INFO MADRID
						solicitudLogicaEjb.processarRespostaPinbalAlta(solicitud, resposta, titular, funcionario,
								infoMad);

					} else {
						es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud solicitudMod = solicitudLogicaEjb
								.getDadesModificarSolicitudApiPinbal(solicitud.getSolicitudID());

						es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta resposta = pinbalModificacioLogicaEjb
								.modificacioSolicitudApiPinbal(titular, funcionario, solicitudMod);

						solicitudLogicaEjb.processarRespostaPinbalModificacio(solicitud, resposta, titular,
								funcionario);

					}

					solicitudLogicaEjb.update(solicitud);

				} catch (Exception e) {
					log.error("Error processant la solicitud " + codi + ": " + e.getMessage(), e);
					solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_ERROR);
					solicitudLogicaEjb.update(solicitud);
				}

				// Timeout de seguretat
				if ((System.currentTimeMillis() - startTime) > TRANSACTION_EXIT_IN_MILI) {
					log.warn("Timeout. Aturam cron.");
					break;
				}
			}

		} catch (Exception e) {
			log.error("Error al cron enviarSolicitudsAmbErrorMadrid(): " + e.getMessage(), e);
		}

		long endTime = System.currentTimeMillis();
		log.info("Total time: " + (endTime - startTime));
		log.info("Acaba enviarSolicitudsAmbErrorMadrid()");
	}
}
