
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.GrupEntitatCedentJPA;
import org.fundaciobit.pinbaladmin.persistence.GrupEntitatCedentIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatCedentManager;

import org.fundaciobit.pinbaladmin.model.entity.GrupEntitatCedent;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface GrupEntitatCedentService extends GrupEntitatCedentIJPAManager,IGrupEntitatCedentManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/GrupEntitatCedentEJB!org.fundaciobit.pinbaladmin.ejb.GrupEntitatCedentService";

    public GrupEntitatCedentJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(GrupEntitatCedent instance, FitxerService fitxerEjb) throws I18NException;
}
