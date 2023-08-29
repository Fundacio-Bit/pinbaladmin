package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitDCteAutService;
import org.fundaciobit.pinbaladmin.persistence.TramitDCteAutJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface TramitDCteAutLogicaService extends TramitDCteAutService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitDCteAutLogicaEJB!org.fundaciobit.pinbaladmin.logic.TramitDCteAutLogicaService";

    @Override
    @PermitAll
    public TramitDCteAutJPA findByPrimaryKey(Long _ID_);

    public void deleteFull(Long _ID) throws I18NException;

}
