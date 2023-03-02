
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.OperadorJPA;
import org.fundaciobit.pinbaladmin.persistence.OperadorIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IOperadorManager;

import org.fundaciobit.pinbaladmin.model.entity.Operador;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface OperadorService extends OperadorIJPAManager,IOperadorManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/OperadorEJB!org.fundaciobit.pinbaladmin.ejb.OperadorService";

    public OperadorJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Operador instance, FitxerService fitxerEjb) throws I18NException;
}
