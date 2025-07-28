package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import java.sql.Timestamp;
import java.util.Properties;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaEJB.TipusCridada;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.EstadoProcedimiento;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;

public class PinbalUtilsConsulta extends PinbalUtilsCommon {
	
	final String SOLICITUD_TROBADA = "0";
//	final String TICKET_NO_TROBAT = "1";
	final String PROCEDIMENT_NO_TROBAT = "2";
//	final String SOLICITANT_SENSE_SOLICITUTS = "3";

	@EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
	protected InfoMadridLogicaService infoMadridLogicaEjb;
	   
	public PinbalUtilsConsulta() {
	}

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

	public InfoMadridJPA actualitzarSolicitud(Retorno retorno, ScspTitular titular, SolicitudJPA solicitud) throws I18NException {

		String codigoEstado = retorno.getEstado().getCodigoEstado();
		
		switch (codigoEstado) {
		case SOLICITUD_TROBADA:

			EstadoProcedimiento estado = retorno.getProcedimiento().getEstadoProcedimiento();
			log.info("estado procedimiento: " + estado.getEstado() + " - " + estado.getDescripcion());

			actualitzarEstatSolicitud(estado, solicitud);
			InfoMadridJPA infoMadJpa= actualizarInfoMadrid(estado, solicitud,  titular);
			return infoMadJpa;
		case PROCEDIMENT_NO_TROBAT:
			//Aixó te sentit si la solicitut está pendent d'autoritzar, sino es no solicitada.
			
			//Si está pendent d'autoritzar i no ha trobat la solicitud. S'ha enviat manualment.
			
			//Si ja està autoritzada o algun estat posterior, s'ha enviat manualment.
			
			procesarSolicitudNoTrobada(solicitud);
			
			// Aquest es el cas de solicituds canviades d'estat manualment. Enviades a
			// Madrid "pendents de tramitar", pero sense utilitzar api PINBAL.
			return null;
		default:
			String errorMsg1 = "Error al consultar l'estat de la solicitud " + solicitud.getProcedimentCodi() + ": "
					+ retorno.getEstado().getLiteralError();
			log.error(errorMsg1);
			return null;
		}
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

	private void actualitzarEstatSolicitud(EstadoProcedimiento estado, Solicitud solicitud) {
	    Long estatPinbalAnterior = solicitud.getEstatpinbal();
	    Long estatPinbalNou = Long.valueOf(estado.getEstado());

	    long estatSoli = solicitud.getEstatSolicitud();
	    boolean jaAutoritzada = estatSoli == Constants.SOLI_ESTAT_AUTORITZAT
	                         || estatSoli == Constants.SOLI_ESTAT_AUTORITZAT_ESMENES
	                         || estatSoli == Constants.SOLI_ESTAT_AUTORITZAT_ERROR_ENVIANT_MADRID;

	    String estatNom;
	    if (estatSoli == Constants.SOLI_ESTAT_PENDENT_AUTORITZAR) estatNom = "Pendent autoritzar";
	    else if (estatSoli == Constants.SOLI_ESTAT_AUTORITZAT) estatNom = "Autoritzat";
	    else if (estatSoli == Constants.SOLI_ESTAT_ESMENES) estatNom = "Esmenes";
	    else estatNom = "Desconegut";

	    log.info("Solicitud " + solicitud.getProcedimentCodi() + "\t[" + estatNom + "]:\t(" 
	             + estatPinbalAnterior + " -> " + estatPinbalNou + ") " + estado.getDescripcion());

	    solicitud.setEstatpinbal(estatPinbalNou);

	    if (estatPinbalNou.equals(Constants.ESTAT_PINBAL_ERROR)) {
	        // caso ESTAT_PINBAL_ERROR
	        solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID);
	    }
	    // else if (estatPinbalNou == Constants.ESTAT_PINBAL_NO_SOLICITAT) {
	    //     // estaba comentado en el switch original
	    // }
	    else if (estatPinbalNou.equals(Constants.ESTAT_PINBAL_PENDENT_TRAMITAR)
	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_DESISTIT)
	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_APROVAT)
	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_SUBSANAT)
	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT)
	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO)) {
	        solicitud.setEstatSolicitud(jaAutoritzada 
	            ? Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_MODIFICACIO 
	            : Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
	    }
	    else if (estatPinbalNou.equals(Constants.ESTAT_PINBAL_NO_APROVAT)
	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_PENDENT_SUBSANACIO)
	          || estatPinbalNou.equals(Constants.ESTAT_PINBAL_DESESTIMAT)) {
	        solicitud.setEstatSolicitud(jaAutoritzada 
	            ? Constants.SOLI_ESTAT_AUTORITZAT_ESMENES 
	            : Constants.SOLI_ESTAT_ESMENA_ENVIAR_CONTACTE);
	    }
	    else if (estatPinbalNou.equals(Constants.ESTAT_PINBAL_AUTORITZAT)) {
	        solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_AUTORITZAT);
	    }
	}

	private void print(String camp, Object o) {
		log.info(camp + ": " + o);

	}
	
	private InfoMadridJPA actualizarInfoMadrid(EstadoProcedimiento estadoProc, SolicitudJPA soli, ScspTitular titular) throws I18NException {
		
		Long infoMadID = soli.getInfomadridid();
		
		if (infoMadID == null) {
			String codi = soli.getProcedimentCodi();
			Long estatProc = soli.getEstatSolicitud();


			int estatAut = estadoProc.getEstado();
			String missatge = estadoProc.getObservaciones();

			String consultaTexto = "Buenos días,\n"
					+ "Enviamos solicitud para dar servicios de alta en el procedimiento " + codi + "\n\n"
					+ "Quedamos a la espera de su respuesta.\n" + "Un saludo.";

			String titularNom = titular.getNombreCompleto();
			String titularNif = titular.getDocumentacion();

			Timestamp now = new Timestamp(System.currentTimeMillis());

			Timestamp dataAuth = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_AUTORITZAT ? now : null;
			Timestamp dataEnviament = now;

			long reintents = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR ? 1 : 0;

			InfoMadridJPA infoMadJPA = new InfoMadridJPA(codi, estatProc, estatAut, missatge, consultaTexto, titularNom,
					titularNif, dataAuth, dataEnviament, reintents);

			print("codi", codi);
			print("estatProc", estatProc);
			print("estatAut", estatAut);
			print("missatge", missatge);
			print("consultaTexto", consultaTexto);
			print("titularNom", titularNom);
			print("titularNif", titularNif);
			print("dataAuth", dataAuth);
			print("dataEnviament", dataEnviament);
			print("reintents", reintents);
		
			return infoMadJPA;
	//		print("infoMadridLogicaEjb", infoMadridLogicaEjb);
			
//			log.info("Crearem InfoMad");

//			InfoMadrid infoMad =  infoMadridLogicaEjb.create(infoMadJPA);
//			log.info("InfoMad creado: " + infoMad.getInfoMadridID());

//			soli.setInfomadridid(infoMad.getInfoMadridID());

		}else {
			InfoMadridJPA infoMad = infoMadridLogicaEjb.findByPrimaryKey(infoMadID);	
			
			if (infoMad != null) {
				//Si l'acaven d'autoritzar, actualitzar data.

				if (estadoProc.getEstado() == Constants.ESTAT_PINBAL_AUTORITZAT
						&& infoMad.getEstatProcediment() != Constants.ESTAT_AUTORITZACIO_AUTORITZAT) {
					infoMad.setDataAutoritzacio(new Timestamp(System.currentTimeMillis()));
				}
				
				infoMad.setEstatProcediment(soli.getEstatSolicitud());
				infoMad.setEstatAutoritzacio(estadoProc.getEstado());
				infoMad.setMissatge(estadoProc.getObservaciones());
			}
			return infoMad;
		}
	};
}
