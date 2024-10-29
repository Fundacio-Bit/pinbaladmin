package org.fundaciobit.pinbaladmin.logic;


import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.PinfoService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PinfoLogicaService extends PinfoService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinfoLogicaEJB!org.fundaciobit.pinbaladmin.logic.PinfoLogicaService";

	public Long generarPinfoPDF(Long pinfoID) throws Exception, I18NException;
	
}
