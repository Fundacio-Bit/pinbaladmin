package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import javax.ejb.Local;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;


@Local
public interface PinbalUtilsConsultaLogicaService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinbalUtilsConsultaLogicaEJB!org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsConsultaLogicaService";
    
	public Retorno consultaEstatApiPinbal(ScspTitular titular, ScspFuncionario funcionario, Long soliID)
			throws Exception;    
    

}
