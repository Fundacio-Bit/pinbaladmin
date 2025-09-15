package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta;

@Local
public interface PinbalUtilsAltaLogicaService {

	public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinbalUtilsAltaLogicaEJB!org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsAltaLogicaService";

	public es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud getDadesSolicitudApiPinbalAlta(
			SolicitudJPA soli) throws Exception;

	public es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta altaSolicitudApiPinbal(
			ScspTitular titular, ScspFuncionario funcionario,
			es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud solicitud) throws Exception;

	public void processarRespostaPinbalAlta(org.fundaciobit.pinbaladmin.model.entity.Solicitud solicitud,
			es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta resposta, ScspTitular titular,
			ScspFuncionario funcionario, InfoMadridJPA infoMadrid) throws Exception;
}
