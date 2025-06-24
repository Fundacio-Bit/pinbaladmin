
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.ModificacioSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSolicitudIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IModificacioSolicitudManager;

import org.fundaciobit.pinbaladmin.model.entity.ModificacioSolicitud;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface ModificacioSolicitudService extends ModificacioSolicitudIJPAManager,IModificacioSolicitudManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/ModificacioSolicitudEJB!org.fundaciobit.pinbaladmin.ejb.ModificacioSolicitudService";

    public ModificacioSolicitudJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(ModificacioSolicitud instance, FitxerService fitxerEjb) throws I18NException;
}
