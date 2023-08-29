
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TramitIServJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitIServIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitIServManager;

import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TramitIServService extends TramitIServIJPAManager,ITramitIServManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitIServEJB!org.fundaciobit.pinbaladmin.ejb.TramitIServService";

    public TramitIServJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TramitIServ instance, FitxerService fitxerEjb) throws I18NException;
}
