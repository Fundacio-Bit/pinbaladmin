
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.EntitatServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.EntitatServeiIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager;

import org.fundaciobit.pinbaladmin.model.entity.EntitatServei;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EntitatServeiService extends EntitatServeiIJPAManager,IEntitatServeiManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/EntitatServeiEJB!org.fundaciobit.pinbaladmin.ejb.EntitatServeiService";

    public EntitatServeiJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(EntitatServei instance, FitxerService fitxerEjb) throws I18NException;
}
