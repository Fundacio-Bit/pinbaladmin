package org.fundaciobit.pinbaladmin.logic;

import java.util.Set;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.DocumentService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface DocumentLogicaService extends DocumentService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/DocumentLogicaEJB!org.fundaciobit.pinbaladmin.logic.DocumentLogicaService";
}
