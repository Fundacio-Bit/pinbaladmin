
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.DepartamentJPA;
import org.fundaciobit.pinbaladmin.persistence.DepartamentIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IDepartamentManager;

import org.fundaciobit.pinbaladmin.model.entity.Departament;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface DepartamentService extends DepartamentIJPAManager,IDepartamentManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/DepartamentEJB!org.fundaciobit.pinbaladmin.ejb.DepartamentService";

    public DepartamentJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Departament instance, FitxerService fitxerEjb) throws I18NException;
}
