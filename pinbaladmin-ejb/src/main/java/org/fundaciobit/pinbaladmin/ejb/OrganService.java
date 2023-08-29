
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.OrganJPA;
import org.fundaciobit.pinbaladmin.persistence.OrganIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IOrganManager;

import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface OrganService extends OrganIJPAManager,IOrganManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/OrganEJB!org.fundaciobit.pinbaladmin.ejb.OrganService";

    public OrganJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Organ instance, FitxerService fitxerEjb) throws I18NException;
}
