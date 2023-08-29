package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitFCteTecService;
import org.fundaciobit.pinbaladmin.persistence.TramitFCteTecJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface TramitFCteTecLogicaService extends TramitFCteTecService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitFCteTecLogicaEJB!org.fundaciobit.pinbaladmin.logic.TramitFCteTecLogicaService";

    @Override
    @PermitAll
    public TramitFCteTecJPA findByPrimaryKey(Long _ID_);

    public void deleteFull(Long _ID) throws I18NException;

}
