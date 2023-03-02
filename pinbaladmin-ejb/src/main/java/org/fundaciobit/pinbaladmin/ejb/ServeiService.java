
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.ServeiIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IServeiManager;

import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface ServeiService extends ServeiIJPAManager,IServeiManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/ServeiEJB!org.fundaciobit.pinbaladmin.ejb.ServeiService";

    public ServeiJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Servei instance, FitxerService fitxerEjb) throws I18NException;
}
