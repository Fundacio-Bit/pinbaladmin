package org.fundaciobit.pinbaladmin.logic;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.ejb.EntitatService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface EntitatLogicaService extends EntitatService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/EntitatLogicaEJB!org.fundaciobit.pinbaladmin.logic.EntitatLogicaService";
}
