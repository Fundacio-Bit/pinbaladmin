
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitBDadesSoliManager;

import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TramitBDadesSoliService extends TramitBDadesSoliIJPAManager,ITramitBDadesSoliManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitBDadesSoliEJB!org.fundaciobit.pinbaladmin.ejb.TramitBDadesSoliService";

    public TramitBDadesSoliJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TramitBDadesSoli instance, FitxerService fitxerEjb) throws I18NException;
}
