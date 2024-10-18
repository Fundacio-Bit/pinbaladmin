package org.fundaciobit.pinbaladmin.logic;


import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.ejb.PINFOService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PINFOLogicaService extends PINFOService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PINFOLogicaEJB!org.fundaciobit.pinbaladmin.logic.PINFOLogicaService";
}
