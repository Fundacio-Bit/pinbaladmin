package org.fundaciobit.pinbaladmin.logic;


import javax.ejb.Local;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.PinfoService;
import org.fundaciobit.pinbaladmin.logic.utils.Responsable;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PinfoLogicaService extends PinfoService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinfoLogicaEJB!org.fundaciobit.pinbaladmin.logic.PinfoLogicaService";

	public Long generarPinfoPDF(Long pinfoID, Responsable responsable) throws Exception, I18NException;

	public PinfoJPA arrancarPeticioFlux(long pinfoID, String languageUI, FlowTemplateSimpleFlowTemplate flux)
			throws I18NException;

	public Long cosesAFerPinfoFirmat(Long portafibID) throws I18NException;
	
	
}
