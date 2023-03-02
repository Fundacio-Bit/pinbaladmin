
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.EstatTiquetJPA;
import org.fundaciobit.pinbaladmin.persistence.EstatTiquetIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IEstatTiquetManager;

import org.fundaciobit.pinbaladmin.model.entity.EstatTiquet;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EstatTiquetService extends EstatTiquetIJPAManager,IEstatTiquetManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/EstatTiquetEJB!org.fundaciobit.pinbaladmin.ejb.EstatTiquetService";

    public EstatTiquetJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(EstatTiquet instance, FitxerService fitxerEjb) throws I18NException;
}
