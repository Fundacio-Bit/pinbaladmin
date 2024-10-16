
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.PINFOJPA;
import org.fundaciobit.pinbaladmin.persistence.PINFOIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IPINFOManager;

import org.fundaciobit.pinbaladmin.model.entity.PINFO;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PINFOService extends PINFOIJPAManager,IPINFOManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PINFOEJB!org.fundaciobit.pinbaladmin.ejb.PINFOService";

    public PINFOJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PINFO instance, FitxerService fitxerEjb) throws I18NException;
}
