
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager;

import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TramitAPersAutService extends TramitAPersAutIJPAManager,ITramitAPersAutManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitAPersAutEJB!org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService";

    public TramitAPersAutJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TramitAPersAut instance, FitxerService fitxerEjb) throws I18NException;
}
