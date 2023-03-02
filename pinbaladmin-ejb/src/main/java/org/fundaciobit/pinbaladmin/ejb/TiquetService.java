
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TiquetJPA;
import org.fundaciobit.pinbaladmin.persistence.TiquetIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITiquetManager;

import org.fundaciobit.pinbaladmin.model.entity.Tiquet;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TiquetService extends TiquetIJPAManager,ITiquetManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TiquetEJB!org.fundaciobit.pinbaladmin.ejb.TiquetService";

    public TiquetJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Tiquet instance, FitxerService fitxerEjb) throws I18NException;
}
