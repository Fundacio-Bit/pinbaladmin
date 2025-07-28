
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IInfoMadridManager;

import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface InfoMadridService extends InfoMadridIJPAManager,IInfoMadridManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/InfoMadridEJB!org.fundaciobit.pinbaladmin.ejb.InfoMadridService";

    public InfoMadridJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(InfoMadrid instance, FitxerService fitxerEjb) throws I18NException;
}
