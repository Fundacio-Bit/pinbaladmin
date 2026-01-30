
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.ContacteJPA;
import org.fundaciobit.pinbaladmin.persistence.ContacteIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IContacteManager;

import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface ContacteService extends ContacteIJPAManager,IContacteManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/ContacteEJB!org.fundaciobit.pinbaladmin.ejb.ContacteService";

    public ContacteJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Contacte instance, FitxerService fitxerEjb) throws I18NException;
}
