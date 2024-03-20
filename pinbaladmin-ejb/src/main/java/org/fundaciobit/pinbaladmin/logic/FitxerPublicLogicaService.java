package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.FitxerService;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface FitxerPublicLogicaService extends FitxerService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/FitxerPublicLogicaEJB!org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService";

    @Override
    @PermitAll
    public Fitxer create(Fitxer bean) throws I18NException;

    @Override
    @PermitAll
    public FitxerJPA findByPrimaryKey(Long _ID_);
    
}
