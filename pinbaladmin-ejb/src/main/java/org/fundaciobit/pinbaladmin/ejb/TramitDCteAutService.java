
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TramitDCteAutJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitDCteAutIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitDCteAutManager;

import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TramitDCteAutService extends TramitDCteAutIJPAManager,ITramitDCteAutManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitDCteAutEJB!org.fundaciobit.pinbaladmin.ejb.TramitDCteAutService";

    public TramitDCteAutJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TramitDCteAut instance, FitxerService fitxerEjb) throws I18NException;
}
