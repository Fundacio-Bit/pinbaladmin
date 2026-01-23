package org.fundaciobit.pinbaladmin.logic;


import javax.ejb.Local;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.PinfoService;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
import org.fundaciobit.pluginsib.userinformation.UserInfo;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PinfoLogicaService extends PinfoService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinfoLogicaEJB!org.fundaciobit.pinbaladmin.logic.PinfoLogicaService";

	public Long generarPinfoPDF(Long pinfoID) throws Exception, I18NException;

	public PinfoJPA arrancarPeticioFlux(long pinfoID, String languageUI, FlowTemplateSimpleFlowTemplate flux)
			throws I18NException;

	public Long cosesAFerPinfoFirmat(Long portafibID) throws I18NException;

	public void enviarPinfoPortaFIB(Long pinfoID) throws I18NException ;

//	public List<StringKeyValue> getEntitats() throws I18NException;

	public void enviarMissatgeSolicitant(UserInfo operador, Long pinfoID)  throws I18NException;
	
}
