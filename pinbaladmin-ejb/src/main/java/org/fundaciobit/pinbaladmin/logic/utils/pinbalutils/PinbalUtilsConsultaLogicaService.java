package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;


@Local
public interface PinbalUtilsConsultaLogicaService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinbalUtilsConsultaLogicaEJB!org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsConsultaLogicaService";
    
    
	public void procesarRetornoPinbal(Retorno retorno, ScspTitular titular, SolicitudJPA solicitud)
			throws I18NException;

	public Retorno consultaEstatApiPinbal(ScspTitular titular, ScspFuncionario funcionario, String codiProcediment)
			throws Exception;    
    

}
