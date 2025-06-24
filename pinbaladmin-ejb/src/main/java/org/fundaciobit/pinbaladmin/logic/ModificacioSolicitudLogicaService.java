package org.fundaciobit.pinbaladmin.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.ModificacioSolicitudService;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSolicitudJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ModificacioSolicitudLogicaService extends ModificacioSolicitudService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/ModificacioSolicitudLogicaEJB!org.fundaciobit.pinbaladmin.logic.ModificacioSolicitudLogicaService";

	public void acceptarModificacio(ModificacioSolicitudJPA modSoli) throws I18NException;
	
}
