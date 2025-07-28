package org.fundaciobit.pinbaladmin.logic;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.ejb.InfoMadridService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface InfoMadridLogicaService extends InfoMadridService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/InfoMadridLogicaEJB!org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService";
}
