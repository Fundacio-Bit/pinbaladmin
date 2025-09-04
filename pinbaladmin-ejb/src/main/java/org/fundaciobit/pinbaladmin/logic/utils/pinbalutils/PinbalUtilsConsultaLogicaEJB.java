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
	public Retorno consultaEstatApiPinbal(ScspTitular titular, ScspFuncionario funcionario, String codiProcediment)
			throws Exception {

		log.info("Consulta estat de la solicitud " + codiProcediment);
		
		Consulta consulta = new Consulta();
		consulta.setCodigoProcedimiento(codiProcediment);

		PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(
				getPinbalAdminSolicitudsConfiguration(TipusCridada.CONSULTA));

		Retorno retorno = api.consultaEstatPinbalApi(consulta, titular, funcionario);
		
//		actualitzarSolicitud(retorno, solicitud);
		
		return retorno;
	}

//	public InfoMadridJPA actualitzarSolicitud(Retorno retorno, ScspTitular titular, SolicitudJPA solicitud) throws I18NException {
//
//		String codigoEstado = retorno.getEstado().getCodigoEstado();
//		
//		switch (codigoEstado) {
//		case SOLICITUD_TROBADA:
//
//			EstadoProcedimiento estado = retorno.getProcedimiento().getEstadoProcedimiento();
//			log.info("estado procedimiento: " + estado.getEstado() + " - " + estado.getDescripcion());
//
//			actualitzarEstatSolicitud(estado, solicitud);
//			InfoMadridJPA infoMadJpa= actualizarInfoMadrid(estado, solicitud,  titular);
//			actualitzarDadesServeisSolicitud(solicitud, retorno);
//			
//			return infoMadJpa;
//		case PROCEDIMENT_NO_TROBAT:
//			//Aixó te sentit si la solicitut está pendent d'autoritzar, sino es no solicitada.
//			
//			//Si está pendent d'autoritzar i no ha trobat la solicitud. S'ha enviat manualment.
//			
//			//Si ja està autoritzada o algun estat posterior, s'ha enviat manualment.
//			
//			procesarSolicitudNoTrobada(solicitud);
//			
//			// Aquest es el cas de solicituds canviades d'estat manualment. Enviades a
//			// Madrid "pendents de tramitar", pero sense utilitzar api PINBAL.
//			return null;
//		default:
//			String errorMsg1 = "Error al consultar l'estat de la solicitud " + solicitud.getProcedimentCodi() + ": "
//					+ retorno.getEstado().getLiteralError();
//			log.error(errorMsg1);
//			return null;
//		}
//	}

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

//	private void actualitzarEstatSolicitud(EstadoProcedimiento estado, Solicitud solicitud) {
//	    Long estatPinbalAnterior = solicitud.getEstatpinbal();
//	    Long estatPinbalNou = Long.valueOf(estado.getEstado());
//
//	    long estatSoli = solicitud.getEstatSolicitud();
//	    boolean jaAutoritzada = estatSoli == Constants.SOLI_ESTAT_AUTORITZAT
//	                         || estatSoli == Constants.SOLI_ESTAT_AUTORITZAT_ESMENES
//	                         || estatSoli == Constants.SOLI_ESTAT_AUTORITZAT_ERROR_ENVIANT_MADRID;
//
//	    String estatNom;
//	    if (estatSoli == Constants.SOLI_ESTAT_PENDENT_AUTORITZAR) estatNom = "Pendent autoritzar";
//	    else if (estatSoli == Constants.SOLI_ESTAT_AUTORITZAT) estatNom = "Autoritzat";
//	    else if (estatSoli == Constants.SOLI_ESTAT_ESMENES) estatNom = "Esmenes";
//	    else estatNom = "Desconegut";
//
//	    log.info("Solicitud " + solicitud.getProcedimentCodi() + "\t[" + estatNom + "]:\t(" 
//	             + estatPinbalAnterior + " -> " + estatPinbalNou + ") " + estado.getDescripcion());
//
//	    solicitud.setEstatpinbal(estatPinbalNou);
//
//	    if (estatPinbalNou.equals(Constants.ESTAT_PINBAL_ERROR)) {
//	        // caso ESTAT_PINBAL_ERROR
//	        solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID);
//	    }
//	    // else if (estatPinbalNou == Constants.ESTAT_PINBAL_NO_SOLICITAT) {
//	    //     // estaba comentado en el switch original
//	    // }
//	    else if (estatPinbalNou.equals(Constants.ESTAT_PINBAL_PENDENT_TRAMITAR)
//	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_DESISTIT)
//	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_APROVAT)
//	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_SUBSANAT)
//	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT)
//	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO)) {
//	        solicitud.setEstatSolicitud(jaAutoritzada 
//	            ? Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_MODIFICACIO 
//	            : Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
//	    }
//	    else if (estatPinbalNou.equals(Constants.ESTAT_PINBAL_NO_APROVAT)
//	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_PENDENT_SUBSANACIO)
//	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_DESESTIMAT)) {
//	        solicitud.setEstatSolicitud(jaAutoritzada 
//	            ? Constants.SOLI_ESTAT_AUTORITZAT_ESMENES 
//	            : Constants.SOLI_ESTAT_ESMENA_ENVIAR_CONTACTE);
//	    }
//	    else if (estatPinbalNou.equals(Constants.ESTAT_PINBAL_AUTORITZAT)) {
//	        solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_AUTORITZAT);
//	    }
//	}
//
//	private void print(String camp, Object o) {
//		log.info(camp + ": " + o);
//
//	}
//	
////	private InfoMadridJPA actualizarInfoMadrid(EstadoProcedimiento estadoProc, SolicitudJPA soli, ScspTitular titular) throws I18NException {
////		
////		Long infoMadID = soli.getInfomadridid();
////		
////		if (infoMadID == null) {
////			String codi = soli.getProcedimentCodi();
////			Long estatProc = soli.getEstatSolicitud();
////
////
////			int estatAut = estadoProc.getEstado();
////			String missatge = estadoProc.getObservaciones();
////
////			String consultaTexto = "Buenos días,\n"
////					+ "Enviamos solicitud para dar servicios de alta en el procedimiento " + codi + "\n\n"
////					+ "Quedamos a la espera de su respuesta.\n" + "Un saludo.";
////
////			String titularNom = titular.getNombreCompleto();
////			String titularNif = titular.getDocumentacion();
////
////			Timestamp now = new Timestamp(System.currentTimeMillis());
////
////			Timestamp dataAuth = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_AUTORITZAT ? now : null;
////			Timestamp dataEnviament = now;
////
////			long reintents = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR ? 1 : 0;
////
////			InfoMadridJPA infoMadJPA = new InfoMadridJPA(codi, estatProc, estatAut, missatge, consultaTexto, titularNom,
////					titularNif, dataAuth, dataEnviament, reintents);
////
////			print("codi", codi);
////			print("estatProc", estatProc);
////			print("estatAut", estatAut);
////			print("missatge", missatge);
////			print("consultaTexto", consultaTexto);
////			print("titularNom", titularNom);
////			print("titularNif", titularNif);
////			print("dataAuth", dataAuth);
////			print("dataEnviament", dataEnviament);
////			print("reintents", reintents);
////		
////			return infoMadJPA;
////	//		print("infoMadridLogicaEjb", infoMadridLogicaEjb);
////			
//////			log.info("Crearem InfoMad");
////
//////			InfoMadrid infoMad =  infoMadridLogicaEjb.create(infoMadJPA);
//////			log.info("InfoMad creado: " + infoMad.getInfoMadridID());
////
//////			soli.setInfomadridid(infoMad.getInfoMadridID());
////
////		}else {
////			InfoMadridJPA infoMad = infoMadridLogicaEjb.findByPrimaryKey(infoMadID);	
////			
////			if (infoMad != null) {
////				//Si l'acaven d'autoritzar, actualitzar data.
////
////				if (estadoProc.getEstado() == Constants.ESTAT_PINBAL_AUTORITZAT
////						&& infoMad.getEstatProcediment() != Constants.ESTAT_AUTORITZACIO_AUTORITZAT) {
////					infoMad.setDataAutoritzacio(new Timestamp(System.currentTimeMillis()));
////				}
////				
////				infoMad.setEstatProcediment(soli.getEstatSolicitud());
////				infoMad.setEstatAutoritzacio(estadoProc.getEstado());
////				infoMad.setMissatge(estadoProc.getObservaciones());
////			}
////			return infoMad;
////		}
////	};
////	
//	
	public final int ESTADO_SOLI_SERV_PENDIENTE = 0;
	public final int ESTADO_SOLI_SERV_DESISTIDO = 1;
	public final int ESTADO_SOLI_SERV_APROBADO = 2;
	public final int ESTADO_SOLI_SERV_NO_APROBADO = 3;
	public final int ESTADO_SOLI_SERV_PENDIENTE_AUTORIZACION_CEDENTE = 6;
	public final int ESTADO_SOLI_SERV_AUTORIZADO = 7;
	public final int ESTADO_SOLI_SERV_DESESTIMADO = 8;
//
//	private void actualitzarDadesServeisSolicitud(Solicitud solicitud,
//			es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno retorno) throws I18NException {
//
//		// Agafar els serveis de la solicitud, i assignar a cada un l'estat que hi ha a
//		// retorno.
//
//		List<SolicitudServei> listSoliServ = solicitudServeiLogicaEjb
//				.select(SolicitudServeiFields.SOLICITUDID.equal(solicitud.getSolicitudID()));
//
//		List<Servicio> listServeis = retorno.getProcedimiento().getServicios().getServicio();
//
//		for (Servicio servicio : listServeis) {
//			for (SolicitudServei soliServ : listSoliServ) {
//
//				Long serveiId = soliServ.getServeiID();
//				Servei servei = serveiLogicaEjb.findByPrimaryKey(serveiId);
//
////				log.info("Test servei: " + servei.getCodi() + " -> " + servicio.getCodigoCertificado());
//
//				if (servei.getCodi().equals(servicio.getCodigoCertificado())) {
////					log.info("Servei trobat: " + servei.getCodi());
//					Long nouEstat = null;
//
//					int nouEstatPinbal = servicio.getEstadoAutorizacion().getEstado();
//					switch (nouEstatPinbal) {
//					case ESTADO_SOLI_SERV_PENDIENTE:
//					case ESTADO_SOLI_SERV_DESISTIDO:
//					case ESTADO_SOLI_SERV_APROBADO:
//					case ESTADO_SOLI_SERV_PENDIENTE_AUTORIZACION_CEDENTE:
//						nouEstat = Constants.ESTAT_SOLICITUD_SERVEI_PENDENT_AUTORITZAR;
//						break;
//
//					case ESTADO_SOLI_SERV_NO_APROBADO:
//					case ESTADO_SOLI_SERV_DESESTIMADO:
//						nouEstat = Constants.ESTAT_SOLICITUD_SERVEI_DESESTIMAT;
//						break;
//
//					case ESTADO_SOLI_SERV_AUTORIZADO:
//						nouEstat = Constants.ESTAT_SOLICITUD_SERVEI_AUTORITZAT;
//						break;
//
//					default:
//						nouEstat = Constants.ESTAT_SOLICITUD_SERVEI_NO_DISPONIBLE;
//
//					}
//
//					soliServ.setEstatSolicitudServeiID(nouEstat);
//					solicitudServeiLogicaEjb.update(soliServ);
//				} else {
////					log.info("Servei no trobat: " + servei.getCodi());
//				}
//			}
//		}
//	}
//
//	
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void procesarRetornoPinbal(Retorno retorno, ScspTitular titular, SolicitudJPA solicitud)
			throws I18NException {

		String codigoEstado = retorno.getEstado().getCodigoEstado();

		switch (codigoEstado) {
		case SOLICITUD_TROBADA:
			procesarSolicitudTrobada(retorno, titular, solicitud);

		case PROCEDIMENT_NO_TROBAT:
			procesarSolicitudNoTrobada(solicitud);

		default:
			log.error("Error en consulta de solicitud " + solicitud.getProcedimentCodi() + ": "
					+ retorno.getEstado().getLiteralError());
		}
	}

	public void procesarSolicitudTrobada(Retorno retorno, ScspTitular titular, SolicitudJPA solicitud)
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
	    
	    // 1. Crear/actualizar InfoMadrid
	    InfoMadridJPA infoMadrid = actualizarInfoMadrid(estadoMadrid, solicitud, titular);
	    
	    // 2. Actualizar la solicitud con los datos de Madrid, teniendo en cuenta el estado anterior de la solicitud.
	    actualizarEstatSolicitud(infoMadrid, solicitud);


	    // 3. Actualizar servicios asociados
	    actualizarServiciosSolicitud(solicitud, retorno);
	}

	
	public InfoMadridJPA actualizarInfoMadrid(EstadoProcedimiento estadoMadrid, SolicitudJPA soli, ScspTitular titular)
	        throws I18NException {

	    Long infoMadID = soli.getInfomadridid();
	    
	    log.info("InfoMadrid: " + infoMadID);
	    
	    Timestamp ahora = new Timestamp(System.currentTimeMillis());

	    if (infoMadID == null) {
	    	log.info("Creamos infoMadrid");
	    	
			// Nuevo registro InfoMadrid. Cuando se crea por primera vez, es por la primera
			// vez que se hace la consulta. Se entiende que la solicitud viene de PENDIENTE
			// AUTORIZAR. Porque se envió a Madrid, y NO SE HA CONSULTADO ESTADO NINGUNA
			// VEZ.
	    	InfoMadridJPA infoMadJpa = new InfoMadridJPA(
	                soli.getProcedimentCodi(),
	                soli.getEstatSolicitud(),                // Estado interno de la solicitud
	                estadoMadrid.getEstado(),                // Estado que devuelve Madrid
	                estadoMadrid.getObservaciones(),         // Mensaje de Madrid
	                generarTextoConsulta(soli.getProcedimentCodi()), // Texto enviado
	                titular.getNombreCompleto(),
	                titular.getDocumentacion(),
	                soli.getEstatpinbal() == Constants.ESTAT_PINBAL_AUTORITZAT ? ahora : null,
	                ahora,                                   // Fecha de envío
	                soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR ? 1 : 0
	        );
	        
	        InfoMadrid infoMad = infoMadridLogicaEjb2.create(infoMadJpa);
	        infoMadJpa.setInfoMadridID(infoMad.getInfoMadridID());
	        
            soli.setInfomadridid(infoMad.getInfoMadridID());
            log.info("InfoMadrid creado con ID " + infoMad.getInfoMadridID());
            return infoMadJpa;
            
	    } else {
	        
	    	// Actualizar InfoMadrid existente
	    	log.info("Haremos el findByPK: " + infoMadID);
	        InfoMadridJPA infoMad = infoMadridLogicaEjb2.findByPrimaryKey(infoMadID);
	        log.info("Tenemos InfoMadrid: " + + infoMad.getInfoMadridID());
	        

	        if (infoMad != null) {
	            // Si acaba de ser autorizado → actualizar fecha autorización
	            if (estadoMadrid.getEstado() == Constants.ESTAT_PINBAL_AUTORITZAT
	                    && infoMad.getEstatProcediment() != Constants.ESTAT_AUTORITZACIO_AUTORITZAT) {
	                infoMad.setDataAutoritzacio(ahora);
	            }

//	            infoMad.setEstatProcediment(soli.getEstatSolicitud());
	            infoMad.setEstatAutoritzacio(estadoMadrid.getEstado());
	            infoMad.setMissatge(estadoMadrid.getObservaciones());
	        }
	        return infoMad;
	    }
	}

	public String generarTextoConsulta(String codi) {
	    return "Buenos días,\n"
	         + "Enviamos solicitud para dar servicios de alta en el procedimiento " + codi + "\n\n"
	         + "Quedamos a la espera de su respuesta.\n"
	         + "Un saludo.";
	}
	
	
	public void actualizarEstatSolicitud(InfoMadrid infoMadrid, Solicitud solicitud) {
		Long estadoMadridNuevo = Long.valueOf(infoMadrid.getEstatAutoritzacio());
		boolean yaAutorizada = infoMadrid.getDataAutoritzacio() != null; // 👈 directo de Madrid
		
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
			infoMadrid.setEstatProcediment(Constants.SOLI_ESTAT_ESMENA_ENVIAR_CONTACTE);

		}else if (estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_PENDENT_TRAMITAR) // NOT YET
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_DESISTIT)
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_APROVAT)
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_SUBSANAT)
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT)
				|| estadoMadridNuevo.equals(Constants.ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO)) {

			solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);

			infoMadrid.setEstatProcediment(yaAutorizada ? Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_MODIFICACIO
					: Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
		}
		

//		log.info("yaAutorizada: " + yaAutorizada);
//		log.info("CodiProc: " + solicitud.getProcedimentCodi());
//		log.info("estadoPinbalAnterior: " + estadoPinbalAnterior);
//		log.info("estadoPinbalNuevo: " + estadoPinbalNuevo);
//		log.info("missatge: " + infoMadrid.getMissatge());
//
//		// Guardar nuevo estado PINBAL
//		solicitud.setEstatpinbal(estadoPinbalNuevo);
//
//		// === MAPEO PINBAL → ESTADOS INTERNOS ===
//		if (estadoPinbalNuevo.equals(Constants.ESTAT_PINBAL_PENDENT_TRAMITAR)
//				|| estadoPinbalNuevo.equals(Constants.ESTAT_PINBAL_DESISTIT)
//				|| estadoPinbalNuevo.equals(Constants.ESTAT_PINBAL_APROVAT)
//				|| estadoPinbalNuevo.equals(Constants.ESTAT_PINBAL_SUBSANAT)
//				|| estadoPinbalNuevo.equals(Constants.ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT)
//				|| estadoPinbalNuevo.equals(Constants.ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO)) {
//
//			solicitud.setEstatSolicitud(yaAutorizada ? Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_MODIFICACIO
//					: Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
//		} else if (estadoPinbalNuevo.equals(Constants.ESTAT_PINBAL_NO_APROVAT)
//				|| estadoPinbalNuevo.equals(Constants.ESTAT_PINBAL_PENDENT_SUBSANACIO)
//				|| estadoPinbalNuevo.equals(Constants.ESTAT_PINBAL_DESESTIMAT)) {
//
//			solicitud.setEstatSolicitud(yaAutorizada ? Constants.SOLI_ESTAT_AUTORITZAT_ESMENES
//					: Constants.SOLI_ESTAT_ESMENA_ENVIAR_CONTACTE);
//		} else if (estadoPinbalNuevo.equals(Constants.ESTAT_PINBAL_AUTORITZAT)) {
//			solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_AUTORITZAT);
//		}
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

}
