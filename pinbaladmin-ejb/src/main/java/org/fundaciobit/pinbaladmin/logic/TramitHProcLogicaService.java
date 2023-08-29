package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitHProcService;
import org.fundaciobit.pinbaladmin.persistence.TramitHProcJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface TramitHProcLogicaService extends TramitHProcService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitHProcLogicaEJB!org.fundaciobit.pinbaladmin.logic.TramitHProcLogicaService";

    @Override
    @PermitAll
    public TramitHProcJPA findByPrimaryKey(Long _ID_);

    public void deleteFull(Long _ID) throws I18NException;

}
