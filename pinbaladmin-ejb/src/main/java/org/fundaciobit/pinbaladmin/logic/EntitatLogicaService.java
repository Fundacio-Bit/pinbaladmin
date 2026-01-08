package org.fundaciobit.pinbaladmin.logic;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.ejb.EntitatService;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface EntitatLogicaService extends EntitatService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/EntitatLogicaEJB!org.fundaciobit.pinbaladmin.logic.EntitatLogicaService";

	public Entitat findByCif(String cif);
	
}
