
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TramitHProcJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitHProcIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitHProcManager;

import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TramitHProcService extends TramitHProcIJPAManager,ITramitHProcManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitHProcEJB!org.fundaciobit.pinbaladmin.ejb.TramitHProcService";

    public TramitHProcJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TramitHProc instance, FitxerService fitxerEjb) throws I18NException;
}
