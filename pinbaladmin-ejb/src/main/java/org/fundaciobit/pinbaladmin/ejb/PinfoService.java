
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
import org.fundaciobit.pinbaladmin.persistence.PinfoIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IPinfoManager;

import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PinfoService extends PinfoIJPAManager,IPinfoManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/PinfoEJB!org.fundaciobit.pinbaladmin.ejb.PinfoService";

    public PinfoJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Pinfo instance, FitxerService fitxerEjb) throws I18NException;
}
