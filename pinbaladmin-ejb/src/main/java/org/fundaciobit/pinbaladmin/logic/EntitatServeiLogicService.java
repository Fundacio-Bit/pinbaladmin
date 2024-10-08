package org.fundaciobit.pinbaladmin.logic;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.ejb.EntitatServeiService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface EntitatServeiLogicService extends EntitatServeiService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/EntitatServeiLogicEJB!org.fundaciobit.pinbaladmin.logic.EntitatServeiLogicService";
}
