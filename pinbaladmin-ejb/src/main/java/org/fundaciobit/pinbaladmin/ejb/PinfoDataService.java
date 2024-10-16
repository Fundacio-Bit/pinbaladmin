
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;
import org.fundaciobit.pinbaladmin.persistence.PinfoDataIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IPinfoDataManager;

import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PinfoDataService extends PinfoDataIJPAManager,IPinfoDataManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinfoDataEJB!org.fundaciobit.pinbaladmin.ejb.PinfoDataService";

    public PinfoDataJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PinfoData instance, FitxerService fitxerEjb) throws I18NException;
}
