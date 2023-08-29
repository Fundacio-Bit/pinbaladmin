
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TramitCDadesCesiJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitCDadesCesiIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitCDadesCesiManager;

import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TramitCDadesCesiService extends TramitCDadesCesiIJPAManager,ITramitCDadesCesiManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitCDadesCesiEJB!org.fundaciobit.pinbaladmin.ejb.TramitCDadesCesiService";

    public TramitCDadesCesiJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TramitCDadesCesi instance, FitxerService fitxerEjb) throws I18NException;
}
