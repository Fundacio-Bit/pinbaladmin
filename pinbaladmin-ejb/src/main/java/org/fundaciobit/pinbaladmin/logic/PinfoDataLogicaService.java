package org.fundaciobit.pinbaladmin.logic;


import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.PinfoDataService;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaEJB.PinfoDataFull;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PinfoDataLogicaService extends PinfoDataService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinfoDataLogicaEJB!org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaService";

    public PinfoDataFull getEstructuraUsuarisProcedimentServeis(Long pinfoID)  throws I18NException ;
}
