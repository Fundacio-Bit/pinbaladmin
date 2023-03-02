
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager;

import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface IncidenciaTecnicaService extends IncidenciaTecnicaIJPAManager,IIncidenciaTecnicaManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/IncidenciaTecnicaEJB!org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaService";

    public IncidenciaTecnicaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(IncidenciaTecnica instance, FitxerService fitxerEjb) throws I18NException;
}
