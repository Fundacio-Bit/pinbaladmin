package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitGDadesTitService;
import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface TramitGDadesTitLogicaService extends TramitGDadesTitService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitGDadesTitLogicaEJB!org.fundaciobit.pinbaladmin.logic.TramitGDadesTitLogicaService";

    @Override
    @PermitAll
    public TramitGDadesTitJPA findByPrimaryKey(Long _ID_);

    public void deleteFull(Long _ID) throws I18NException;

}
