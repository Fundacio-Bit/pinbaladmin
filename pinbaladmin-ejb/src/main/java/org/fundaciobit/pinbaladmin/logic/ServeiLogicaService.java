package org.fundaciobit.pinbaladmin.logic;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.ejb.ServeiService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ServeiLogicaService extends ServeiService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/ServeiLogicaEJB!org.fundaciobit.pinbaladmin.logic.ServeiLogicaService";
}
