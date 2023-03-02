
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.GrupEntitatJPA;
import org.fundaciobit.pinbaladmin.persistence.GrupEntitatIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager;

import org.fundaciobit.pinbaladmin.model.entity.GrupEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface GrupEntitatService extends GrupEntitatIJPAManager,IGrupEntitatManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/GrupEntitatEJB!org.fundaciobit.pinbaladmin.ejb.GrupEntitatService";

    public GrupEntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(GrupEntitat instance, FitxerService fitxerEjb) throws I18NException;
}
