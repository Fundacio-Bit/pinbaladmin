
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager;

import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface SolicitudService extends SolicitudIJPAManager,ISolicitudManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/SolicitudEJB!org.fundaciobit.pinbaladmin.ejb.SolicitudService";

    public SolicitudJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Solicitud instance, FitxerService fitxerEjb) throws I18NException;
}
