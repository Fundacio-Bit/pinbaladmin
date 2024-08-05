package org.fundaciobit.pinbaladmin.logic;


import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.ejb.OrganService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface OrganLogicaService extends OrganService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/OrganLogicaEJB!org.fundaciobit.pinbaladmin.logic.OrganLogicaService";
}
