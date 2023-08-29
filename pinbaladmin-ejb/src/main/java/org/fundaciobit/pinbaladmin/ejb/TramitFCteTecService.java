
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TramitFCteTecJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitFCteTecIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitFCteTecManager;

import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TramitFCteTecService extends TramitFCteTecIJPAManager,ITramitFCteTecManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitFCteTecEJB!org.fundaciobit.pinbaladmin.ejb.TramitFCteTecService";

    public TramitFCteTecJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TramitFCteTec instance, FitxerService fitxerEjb) throws I18NException;
}
