
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TramitJConsentJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitJConsentIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitJConsentManager;

import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TramitJConsentService extends TramitJConsentIJPAManager,ITramitJConsentManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitJConsentEJB!org.fundaciobit.pinbaladmin.ejb.TramitJConsentService";

    public TramitJConsentJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TramitJConsent instance, FitxerService fitxerEjb) throws I18NException;
}
