
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.EntitatJPA;
import org.fundaciobit.pinbaladmin.persistence.EntitatIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IEntitatManager;

import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EntitatService extends EntitatIJPAManager,IEntitatManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/EntitatEJB!org.fundaciobit.pinbaladmin.ejb.EntitatService";

    public EntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Entitat instance, FitxerService fitxerEjb) throws I18NException;
}
