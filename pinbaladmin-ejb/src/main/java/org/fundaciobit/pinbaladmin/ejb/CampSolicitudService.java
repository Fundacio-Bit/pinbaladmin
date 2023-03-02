
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.CampSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.CampSolicitudIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ICampSolicitudManager;

import org.fundaciobit.pinbaladmin.model.entity.CampSolicitud;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface CampSolicitudService extends CampSolicitudIJPAManager,ICampSolicitudManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/CampSolicitudEJB!org.fundaciobit.pinbaladmin.ejb.CampSolicitudService";

    public CampSolicitudJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(CampSolicitud instance, FitxerService fitxerEjb) throws I18NException;
}
