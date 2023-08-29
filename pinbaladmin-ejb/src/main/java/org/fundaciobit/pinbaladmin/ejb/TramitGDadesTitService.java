
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITramitGDadesTitManager;

import org.fundaciobit.pinbaladmin.model.entity.TramitGDadesTit;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TramitGDadesTitService extends TramitGDadesTitIJPAManager,ITramitGDadesTitManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitGDadesTitEJB!org.fundaciobit.pinbaladmin.ejb.TramitGDadesTitService";

    public TramitGDadesTitJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TramitGDadesTit instance, FitxerService fitxerEjb) throws I18NException;
}
