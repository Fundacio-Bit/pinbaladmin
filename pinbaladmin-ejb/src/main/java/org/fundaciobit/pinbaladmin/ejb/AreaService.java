
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.AreaJPA;
import org.fundaciobit.pinbaladmin.persistence.AreaIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IAreaManager;

import org.fundaciobit.pinbaladmin.model.entity.Area;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface AreaService extends AreaIJPAManager,IAreaManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/AreaEJB!org.fundaciobit.pinbaladmin.ejb.AreaService";

    public AreaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Area instance, FitxerService fitxerEjb) throws I18NException;
}
