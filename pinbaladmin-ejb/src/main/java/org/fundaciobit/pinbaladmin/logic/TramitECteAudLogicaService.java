package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitECteAudService;
import org.fundaciobit.pinbaladmin.persistence.TramitECteAudJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface TramitECteAudLogicaService extends TramitECteAudService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitECteAudLogicaEJB!org.fundaciobit.pinbaladmin.logic.TramitECteAudLogicaService";

    @Override
    @PermitAll
    public TramitECteAudJPA findByPrimaryKey(Long _ID_);

    public void deleteFull(Long _ID) throws I18NException;

}
