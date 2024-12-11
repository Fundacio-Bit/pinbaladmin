package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaEJB.TipusCridada;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;

public class PinbalUtilsConsulta extends PinbalUtilsCommon {

	public PinbalUtilsConsulta() {
	}

	public es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno consultaEstatApiPinbal(
			ScspTitular titular, ScspFuncionario funcionario,
			es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta consulta) throws Exception {

		PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(
				getPinbalAdminSolicitudsConfiguration(TipusCridada.CONSULTA));
		return api.consultaEstatPinbalApi(consulta, titular, funcionario);

	}

}
