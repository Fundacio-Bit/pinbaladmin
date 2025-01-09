package org.fundaciobit.pinbaladmin.logic;


import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.PinfoDataService;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaEJB.PinfoDataFull;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PinfoDataLogicaService extends PinfoDataService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinfoDataLogicaEJB!org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaService";

    public PinfoDataFull getEstructuraUsuarisProcedimentServeis(Long pinfoID)  throws I18NException ;

    public IEstructuraOrganitzativaPlugin getPluginEstructuraOrganitzativa() throws I18NException;

    public List<String> getResponsablesProcedimentsPinfos(Long pinfoID) throws I18NException;
}
