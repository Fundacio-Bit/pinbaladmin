
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TramitECteAudJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitECteAudIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitECteAudManager;

import org.fundaciobit.pinbaladmin.model.entity.TramitECteAud;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TramitECteAudService extends TramitECteAudIJPAManager,ITramitECteAudManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitECteAudEJB!org.fundaciobit.pinbaladmin.ejb.TramitECteAudService";

    public TramitECteAudJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TramitECteAud instance, FitxerService fitxerEjb) throws I18NException;
}
