
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.EstatServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.EstatServeiIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IEstatServeiManager;

import org.fundaciobit.pinbaladmin.model.entity.EstatServei;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EstatServeiService extends EstatServeiIJPAManager,IEstatServeiManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/EstatServeiEJB!org.fundaciobit.pinbaladmin.ejb.EstatServeiService";

    public EstatServeiJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(EstatServei instance, FitxerService fitxerEjb) throws I18NException;
}
