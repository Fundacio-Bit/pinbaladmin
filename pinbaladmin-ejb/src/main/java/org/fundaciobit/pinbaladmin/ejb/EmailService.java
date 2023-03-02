
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.EmailJPA;
import org.fundaciobit.pinbaladmin.persistence.EmailIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IEmailManager;

import org.fundaciobit.pinbaladmin.model.entity.Email;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EmailService extends EmailIJPAManager,IEmailManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/EmailEJB!org.fundaciobit.pinbaladmin.ejb.EmailService";

    public EmailJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Email instance, FitxerService fitxerEjb) throws I18NException;
}
