package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaEJB.TipusCridada;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
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

	public String actualitzarSolicitud(
			es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno retorno, SolicitudJPA solicitud)
			throws I18NException {

		String codigoEstado = retorno.getEstado().getCodigoEstado();
		switch (codigoEstado) {
		case SOLICITUD_TROBADA:

			EstadoProcedimiento estado = retorno.getProcedimiento().getEstadoProcedimiento();
			log.info("estado procedimiento: " + estado.getEstado() + " - " + estado.getDescripcion());

			actualitzarEstatSolicitud(estado, solicitud);
			break;
		case PROCEDIMENT_NO_TROBAT:
			String errorMsg = "No s'ha trobat la solicitud " + solicitud.getProcedimentCodi()
					+ " a pinbal. Revisar s'ha enviat manualment a Madrid.";
			log.warn(errorMsg);
			// Aquest es el cas de solicituds canviades d'estat manualment. Enviades a
			// Madrid "pendents de tramitar", pero sense utilitzar api PINBAL.
			break;

		default:
			String errorMsg1 = "Error al consultar l'estat de la solicitud " + solicitud.getProcedimentCodi() + ": "
					+ retorno.getEstado().getLiteralError();
			log.error(errorMsg1);
			break;
		}
		return codigoEstado;
	}

	private void actualitzarEstatSolicitud(EstadoProcedimiento estado, Solicitud solicitud) {
	    int estatPinbalAnterior = solicitud.getEstatpinbal();
	    int estatPinbalNou = estado.getEstado();

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

	    switch (estatPinbalNou) {
	        case Constants.ESTAT_PINBAL_ERROR:
//	        case Constants.ESTAT_PINBAL_NO_SOLICITAT:
	            solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID);
	            break;

	        case Constants.ESTAT_PINBAL_PENDENT_TRAMITAR:
	        case Constants.ESTAT_PINBAL_DESISTIT:
	        case Constants.ESTAT_PINBAL_APROVAT:
	        case Constants.ESTAT_PINBAL_SUBSANAT:
	        case Constants.ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT:
	            solicitud.setEstatSolicitud(jaAutoritzada 
	                ? Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_MODIFICACIO 
	                : Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
	            break;

	        case Constants.ESTAT_PINBAL_NO_APROVAT:
	        case Constants.ESTAT_PINBAL_PENDENT_SUBSANACIO:
	        case Constants.ESTAT_PINBAL_DESESTIMAT:
	            solicitud.setEstatSolicitud(jaAutoritzada 
	                ? Constants.SOLI_ESTAT_AUTORITZAT_ESMENES 
	                : Constants.SOLI_ESTAT_ESMENES);
	            break;

	        case Constants.ESTAT_PINBAL_AUTORITZAT:
	        case Constants.ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO:
	            solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_AUTORITZAT);
	            break;
	    }
	}


}
