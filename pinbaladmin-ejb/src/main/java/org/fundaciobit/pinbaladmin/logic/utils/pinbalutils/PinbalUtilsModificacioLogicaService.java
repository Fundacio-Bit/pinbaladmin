package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud;

@Local
public interface PinbalUtilsModificacioLogicaService {

	public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinbalUtilsModificacioLogicaEJB!org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsModificacioLogicaService";

	public Solicitud getDadesSolicitudApiPinbalMod(SolicitudJPA soli) throws Exception;

	public Respuesta modificacioSolicitudApiPinbal(ScspTitular titular, ScspFuncionario funcionario,
			Solicitud solicitud) throws Exception;

	public void processarRespostaPinbalModificacio(org.fundaciobit.pinbaladmin.model.entity.Solicitud solicitud,
			Respuesta resposta, ScspTitular titular, ScspFuncionario funcionario, InfoMadridJPA infoMadrid)
			throws Exception;
}
