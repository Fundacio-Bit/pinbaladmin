package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaEJB.TipusCridada;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.EstadoProcedimiento;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Servicio;

@Stateless(name = "PinbalUtilsConsultaLogicaEJB")
public class PinbalUtilsConsultaLogicaEJB extends PinbalUtilsCommon implements PinbalUtilsConsultaLogicaService {
	
	final String SOLICITUD_TROBADA = "0";
//	final String TICKET_NO_TROBAT = "1";
	final String PROCEDIMENT_NO_TROBAT = "2";
//	final String SOLICITANT_SENSE_SOLICITUTS = "3";

	public PinbalUtilsConsultaLogicaEJB() {
	}

	@Override
	public Retorno consultaEstatApiPinbal(ScspTitular titular, ScspFuncionario funcionario, Long soliID)
			throws I18NException {
		
		SolicitudJPA solicitud = solicitudLogicaEjb.findByPrimaryKey(soliID);
		Retorno retorno = null;
		
	    try {
	    	log.info("Iniciamos consulta con Solicitud " + soliID);

	    	// 1. Consultar PINBAL (Madrid)
	    	retorno = consultaApi(titular, funcionario, solicitud);

	        // 2. Procesar respuesta
	    	InfoMadridJPA infoMad = this.procesarRetornoPinbal(retorno, titular, solicitud);
	        log.info("Respuesta procesada. InfoMad: " + solicitud.getInfomadridid());
	        
	        // 3. Informar al contacto si hay cambios.
	        this.informarContacteCanvisEstat(solicitud, infoMad);
	        
	        
	    } catch (Throwable e) {
	        solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_ERROR);
	        log.error("Error consultando PINBAL: " + e.getMessage(), e);
	    }

	    // 4. Guardar cambios de la solicitud
	    solicitudLogicaEjb.update(solicitud);
		return retorno;
	}

	private Retorno consultaApi(ScspTitular titular, ScspFuncionario funcionario, SolicitudJPA solicitud)
			throws Exception {
		Retorno retorno;
		String codiProcediment = solicitud.getProcedimentCodi();
		log.info("Consulta estat de la solicitud " + codiProcediment);
		
		Consulta consulta = new Consulta();
		consulta.setCodigoProcedimiento(codiProcediment);

		String CIF = solicitud.getNif();
		
		PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(
				getPinbalAdminSolicitudsConfiguration(TipusCridada.CONSULTA, CIF));

		retorno = api.consultaEstatPinbalApi(consulta, titular, funcionario);
		
		log.info("Consulta PINBAL completada. Estado recibido:" + retorno.getEstado() != null ? retorno.getEstado().getCodigoEstado() : "N/A");
		return retorno;
	}

	public InfoMadridJPA procesarRetornoPinbal(Retorno retorno, ScspTitular titular, SolicitudJPA solicitud)
			throws I18NException {

		String codigoEstado = retorno.getEstado().getCodigoEstado();
		InfoMadridJPA infoMadrid = null;
		
		switch (codigoEstado) {
		case SOLICITUD_TROBADA:
			infoMadrid = procesarSolicitudTrobada(retorno, titular, solicitud);
			break;

		case PROCEDIMENT_NO_TROBAT:
			procesarSolicitudNoTrobada(solicitud);
			break;

		default:
			log.error("Error en consulta de solicitud " + solicitud.getProcedimentCodi() + ": "
					+ retorno.getEstado().getLiteralError());
			break;
		}
		
		return infoMadrid;
	}

	private void procesarSolicitudNoTrobada(SolicitudJPA solicitud) {

		Long estatSoli = solicitud.getEstatSolicitud();
		
		if (estatSoli == Constants.SOLI_ESTAT_PENDENT_AUTORITZAR
				|| estatSoli == Constants.SOLI_ESTAT_TANCAT
				|| estatSoli == Constants.SOLI_ESTAT_AUTORITZAT
				|| estatSoli == Constants.SOLI_ESTAT_AUTORITZAT_Parcial
		) {
			String errorMsg = "No s'ha trobat la solicitud " + solicitud.getProcedimentCodi()
					+ " a pinbal. Revisar s'ha enviat manualment a Madrid.";

			log.warn(errorMsg);
			solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_MANUAL);
		}else {
			log.warn("No enviada Manual. Pasam a no Soicitat");
			//Si no està pendent d'autoritzar i no la troba, es que no s'ha solicitat.
			solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_NO_SOLICITAT);
		}
	}
	
	public InfoMadridJPA procesarSolicitudTrobada(Retorno retorno, ScspTitular titular, SolicitudJPA solicitud)
	        throws I18NException {
		
		/*
		 * Despues de hacer la consulta, el nuevo estado de la solicitud será en función del estado que devuelva Madrid.
		 * 
		 * Hay que suponer, que dependiendo del estado en que se encontraba antes la solicitud, no habría que cambiarlo.
		 * Por ejemplo en "DESESTIMADO" hay varios estados...
		 * 
		 */

	    EstadoProcedimiento estadoMadrid = retorno.getProcedimiento().getEstadoProcedimiento();
	    log.info("PINBAL → Estado procedimiento: " + estadoMadrid.getEstado()
	            + " - " + estadoMadrid.getDescripcion());
	    
	    // 1. Crear/actualizar InfoMadrid (pasamos el retorno completo para incluir info de servicios)
	    InfoMadridJPA infoMadrid = actualizarInfoMadrid(retorno, solicitud, titular);
	    
	    // 2. Actualizar la solicitud con los datos de Madrid, teniendo en cuenta el estado anterior de la solicitud.
	    actualizarEstatSolicitud(infoMadrid, solicitud);


	    // 3. Actualizar servicios asociados
	    actualizarServiciosSolicitud(solicitud, retorno);
	    
	    return infoMadrid;
	}

	
	public InfoMadridJPA actualizarInfoMadrid(Retorno retorno, SolicitudJPA soli, ScspTitular titular)
	        throws I18NException {

	    EstadoProcedimiento estadoMadrid = retorno.getProcedimiento().getEstadoProcedimiento();
	    Long infoMadID = soli.getInfomadridid();
	    
	    log.info("InfoMadrid: " + infoMadID);
	    
	    Timestamp ahora = new Timestamp(System.currentTimeMillis());

	    //Normalmente no debería entrar aquí. InfoMadrid se crea en el Alta, y si encuentra la solicitud, ha ido bien, y hay una alta hecha.
	    if (infoMadID == null) {
	    	log.info("Creamos infoMadrid");
	    	
			// Nuevo registro InfoMadrid. Cuando se crea por primera vez, es por la primera
			// vez que se hace la consulta. Se entiende que la solicitud viene de PENDIENTE
			// AUTORIZAR. Porque se envió a Madrid, y NO SE HA CONSULTADO ESTADO NINGUNA
			// VEZ.
	    	
	    	String procedimentCodi = soli.getProcedimentCodi();
	    	Long estatProcediment = soli.getEstatSolicitud();
	    	Long estatAutoritzacio = Long.valueOf(estadoMadrid.getEstado());
	    	String missatge = construirMissatgeComplet(retorno);
	    	String consulta = generarTextoConsulta(procedimentCodi);
	    	String titularNom = titular.getNombreCompleto();
	    	String titularDoc = titular.getDocumentacion();
	    	Timestamp dataAutoritzacio = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_AUTORITZAT ? ahora : null;
	    	Timestamp dataEnviament = ahora;
	    	Long numErrors = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR ? 1L : 0L;
	    	Timestamp dataConsulta = ahora;
	    	
			InfoMadridJPA infoMadJpa = new InfoMadridJPA(procedimentCodi, estatProcediment, estatAutoritzacio, missatge,
					consulta, titularNom, titularDoc, dataAutoritzacio, dataEnviament, numErrors, dataConsulta);

	    	
	    	
	    	
	    	
//	    	InfoMadridJPA infoMadJpa = new InfoMadridJPA(
//	                soli.getProcedimentCodi(),
//	                soli.getEstatSolicitud(),                // Estado interno de la solicitud
//	                estadoMadrid.getEstado(),                // Estado que devuelve Madrid
//	                estadoMadrid.getObservaciones(),         // Mensaje de Madrid
//	                generarTextoConsulta(soli.getProcedimentCodi()), // Texto enviado
//	                titular.getNombreCompleto(),
//	                titular.getDocumentacion(),
//	                soli.getEstatpinbal() == Constants.ESTAT_PINBAL_AUTORITZAT ? ahora : null,
//	                ahora,                                   // Fecha de envío
//	                soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR ? 1 : 0,
//	                ahora
//	        );
	        
	        InfoMadrid infoMad = infoMadridLogicaEjb.create(infoMadJpa);
	        infoMadJpa.setInfoMadridID(infoMad.getInfoMadridID());
	        
            soli.setInfomadridid(infoMad.getInfoMadridID());
            log.info("InfoMadrid creado con ID " + infoMad.getInfoMadridID());
            return infoMadJpa;
            
	    } else {
	        
	    	// Actualizar InfoMadrid existente
	    	log.info("Haremos el findByPK: " + infoMadID);
	        InfoMadridJPA infoMad = infoMadridLogicaEjb.findByPrimaryKey(infoMadID);
	        log.info("Tenemos InfoMadrid: " + + infoMad.getInfoMadridID());
	        

	        if (infoMad != null) {
	            // Si acaba de ser autorizado → actualizar fecha autorización
	            if (estadoMadrid.getEstado() == Constants.ESTAT_PINBAL_AUTORITZAT
	                    && infoMad.getEstatProcediment() != Constants.ESTAT_AUTORITZACIO_AUTORITZAT) {
	                infoMad.setDataAutoritzacio(ahora);
	            }
	            
	            // Actualizar estado y mensaje con toda la información de la consulta
	            infoMad.setEstatAutoritzacio(Long.valueOf(estadoMadrid.getEstado()));
	            infoMad.setMissatge(construirMissatgeComplet(retorno));
	            infoMad.setDataConsulta(ahora);
	            
	            // Actualizar información del titular (por si ha cambiado)
	            if (titular != null) {
	                infoMad.setTitularNom(titular.getNombreCompleto());
	                infoMad.setTitularNif(titular.getDocumentacion());
	            }
			}
	        return infoMad;
	    }
	}
	
	/**
	 * Construye un mensaje completo con toda la información relevante de la respuesta de Madrid
	 * Incluye: estado del procedimiento, observaciones y detalles de servicios (especialmente desestimados)
	 */
	private String construirMissatgeComplet(Retorno retorno) {
	    StringBuilder missatge = new StringBuilder();
	    
	    EstadoProcedimiento estadoProc = retorno.getProcedimiento().getEstadoProcedimiento();
	    
	    // 1. Estado y descripción del procedimiento
	    missatge.append("Estado: ").append(estadoProc.getEstado())
	            .append(" - ").append(estadoProc.getDescripcion());
	    
	    // 2. Observaciones del procedimiento (si existen)
	    if (estadoProc.getObservaciones() != null && !estadoProc.getObservaciones().trim().isEmpty()) {
	        missatge.append("\n\nObservaciones: ").append(estadoProc.getObservaciones());
	    }
	    
	    // 3. Información detallada de servicios (especialmente si hay problemas)
	    List<Servicio> servicios = retorno.getProcedimiento().getServicios().getServicio();
	    boolean hayServiciosConProblemas = false;
	    StringBuilder serviciosInfo = new StringBuilder();
	    
	    for (Servicio servicio : servicios) {
	        int estadoServicio = servicio.getEstadoAutorizacion().getEstado();
	        String observacionesServicio = servicio.getEstadoAutorizacion().getObservaciones();
	        
	        // Incluir información si el servicio NO está autorizado (estado != 7) o tiene observaciones
	        if (estadoServicio != 7 || (observacionesServicio != null && !observacionesServicio.trim().isEmpty())) {
	            if (!hayServiciosConProblemas) {
	                serviciosInfo.append("\n\nServicios:");
	                hayServiciosConProblemas = true;
	            }
	            
	            serviciosInfo.append("\n  • ").append(servicio.getCodigoCertificado())
	                        .append(" (").append(servicio.getNombre()).append(")")
	                        .append(" - Estado: ").append(servicio.getEstadoAutorizacion().getDescripcion());
	            
	            if (observacionesServicio != null && !observacionesServicio.trim().isEmpty()) {
	                serviciosInfo.append("\n    Observaciones: ").append(observacionesServicio);
	            }
	        }
	    }
	    
	    // Solo añadir información de servicios si hay alguno con problemas u observaciones
	    if (hayServiciosConProblemas) {
	        missatge.append(serviciosInfo);
	    }
	    
	    return missatge.toString();
	}

	public String generarTextoConsulta(String codi) {
	    return "Buenos días,\n"
	         + "Enviamos solicitud para dar servicios de alta en el procedimiento " + codi + "\n\n"
	         + "Quedamos a la espera de su respuesta.\n"
	         + "Un saludo.";
	}
	
	
	public void actualizarEstatSolicitud(InfoMadrid infoMadrid, Solicitud solicitud) {
		Long estadoMadridNuevo = Long.valueOf(infoMadrid.getEstatAutoritzacio());// 👈 directo de Madrid
		
		//boolean yaAutorizada = infoMadrid.getDataAutoritzacio() != null; 
		
		//Ahora, en funcion de cada estado, se asocian los otros.
		
		if (estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_AUTORITZAT)) { //OK
			solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_AUTORITZAT);
			infoMadrid.setEstatProcediment(Constants.SOLI_ESTAT_AUTORITZAT);
			
		} else if (estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_NO_APROVAT) //NO OK
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_PENDENT_SUBSANACIO)
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_DESESTIMAT)) {
			

			// Si está pendiente de Madrid, se tiene que poder hacer la consulta. (PENDIENTE DE AUTORIZAR)
			// Si Madrid ha dado respuesta. Se puede ver, pero no hacer más consultas. (TENEMOS INFO MAD)
			// Si está en ESMENES, que busque infomad, pero no puede hacer más consultas, porque cambiará el estado.

			solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_ESMENES);
			infoMadrid.setEstatProcediment(Constants.SOLI_ESTAT_ESMENA_PENDENT_CONTACTE);

		}else if (estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_PENDENT_TRAMITAR) // NOT YET
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_DESISTIT)
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_APROVAT)
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_SUBSANAT)
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT)
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO)) {

			solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
			infoMadrid.setEstatProcediment(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
		}
	}

	public void actualizarServiciosSolicitud(Solicitud solicitud, Retorno retorno) throws I18NException {
	    List<SolicitudServei> soliServicios = solicitudServeiLogicaEjb
	            .select(SolicitudServeiFields.SOLICITUDID.equal(solicitud.getSolicitudID()));

	    List<Servicio> serviciosMadrid = retorno.getProcedimiento().getServicios().getServicio();

	    for (Servicio servicio : serviciosMadrid) {
	        for (SolicitudServei soliServ : soliServicios) {
	            Servei servei = serveiLogicaEjb.findByPrimaryKey(soliServ.getServeiID());

	            if (servei.getCodi().equals(servicio.getCodigoCertificado())) {
	                Long nuevoEstado = mapEstadoServicio(servicio.getEstadoAutorizacion().getEstado());
	                soliServ.setEstatSolicitudServeiID(nuevoEstado);
	                solicitudServeiLogicaEjb.update(soliServ);
	            }
	        }
	    }
	}

	public Long mapEstadoServicio(int estadoMadrid) {

		final int ESTADO_SOLI_SERV_PENDIENTE = 0;
		final int ESTADO_SOLI_SERV_DESISTIDO = 1;
		final int ESTADO_SOLI_SERV_APROBADO = 2;
		final int ESTADO_SOLI_SERV_NO_APROBADO = 3;
		final int ESTADO_SOLI_SERV_PENDIENTE_AUTORIZACION_CEDENTE = 6;
		final int ESTADO_SOLI_SERV_AUTORIZADO = 7;
		final int ESTADO_SOLI_SERV_DESESTIMADO = 8;

		switch (estadoMadrid) {
		case ESTADO_SOLI_SERV_PENDIENTE:
		case ESTADO_SOLI_SERV_DESISTIDO:
		case ESTADO_SOLI_SERV_APROBADO:
		case ESTADO_SOLI_SERV_PENDIENTE_AUTORIZACION_CEDENTE:
			return Constants.ESTAT_SOLICITUD_SERVEI_PENDENT_AUTORITZAR;

		case ESTADO_SOLI_SERV_NO_APROBADO:
		case ESTADO_SOLI_SERV_DESESTIMADO:
			return Constants.ESTAT_SOLICITUD_SERVEI_DESESTIMAT;

		case ESTADO_SOLI_SERV_AUTORIZADO:
			return Constants.ESTAT_SOLICITUD_SERVEI_AUTORITZAT;

		default:
			return Constants.ESTAT_SOLICITUD_SERVEI_NO_DISPONIBLE;
		}
	}

	private void informarContacteCanvisEstat(SolicitudJPA solicitud, InfoMadridJPA infoMad) {
		// Si estamos aqui, es que el estado anterior no era ni autorizado ni esmenes.
		// Así que si ahora es autorizado, informamos, y si es esmenes, también. Porque
		// antes no lo era.

		Long estatSoli = solicitud.getEstatSolicitud();

		try {
			if (estatSoli == Constants.SOLI_ESTAT_AUTORITZAT) {
				// Madrid ha autorizado - Registrar evento PÚBLICO (visible para tramitador y contacto)
				// pero NO envía email automáticamente. Pilar decidirá cuándo enviarlo.
				log.info("Registrando autorización desde Madrid: solicitud=" + solicitud.getSolicitudID());
				notificacionLogicaEjb.registrarAutorizacionDesdeMadrid(solicitud);
				
			} else if (estatSoli == Constants.SOLI_ESTAT_ESMENES) {
				// Solicitud desestimada - NOTIFICAR a tramitadores (NO email)
				log.info("Notificando desestimación a tramitadores: solicitud=" + solicitud.getSolicitudID());
				log.info("Mensaje de Madrid: \n" + infoMad.getMissatge());
				notificacionLogicaEjb.notificarDesestimacionATramitadores(
					solicitud, 
					infoMad.getMissatge(), 
					"AUTORITZACIÓ"
				);
			}
			// Si no ha cambiado de estado, no informamos (PENDENT AUTORITZAR)

		} catch (I18NException e) {
			log.error("Error notificant canvi d'estat: " + e.getMessage(), e);
		}
	}
}
