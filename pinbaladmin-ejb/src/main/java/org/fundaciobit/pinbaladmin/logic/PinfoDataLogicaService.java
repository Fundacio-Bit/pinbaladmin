package org.fundaciobit.pinbaladmin.logic;


import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.ejb.PinfoDataService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PinfoDataLogicaService extends PinfoDataService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinfoDataLogicaEJB!org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaService";
}
