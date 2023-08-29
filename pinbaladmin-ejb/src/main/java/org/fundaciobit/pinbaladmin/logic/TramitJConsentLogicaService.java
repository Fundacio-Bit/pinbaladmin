package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitJConsentService;
import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;
import org.fundaciobit.pinbaladmin.persistence.TramitJConsentJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface TramitJConsentLogicaService extends TramitJConsentService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitJConsentLogicaEJB!org.fundaciobit.pinbaladmin.logic.TramitJConsentLogicaService";

    @Override
    @PermitAll
    public TramitJConsentJPA findByPrimaryKey(Long _ID_);

    public void deleteFull(Long _ID_) throws I18NException;

}
