
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.IdiomaJPA;
import org.fundaciobit.pinbaladmin.persistence.IdiomaIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IIdiomaManager;

import org.fundaciobit.pinbaladmin.model.entity.Idioma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface IdiomaService extends IdiomaIJPAManager,IIdiomaManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/IdiomaEJB!org.fundaciobit.pinbaladmin.ejb.IdiomaService";

    public IdiomaJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(Idioma instance, FitxerService fitxerEjb) throws I18NException;
}
