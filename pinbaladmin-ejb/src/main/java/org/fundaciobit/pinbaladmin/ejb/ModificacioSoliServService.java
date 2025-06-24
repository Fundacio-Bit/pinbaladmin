
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.ModificacioSoliServJPA;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSoliServIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IModificacioSoliServManager;

import org.fundaciobit.pinbaladmin.model.entity.ModificacioSoliServ;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface ModificacioSoliServService extends ModificacioSoliServIJPAManager,IModificacioSoliServManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/ModificacioSoliServEJB!org.fundaciobit.pinbaladmin.ejb.ModificacioSoliServService";

    public ModificacioSoliServJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(ModificacioSoliServ instance, FitxerService fitxerEjb) throws I18NException;
}
