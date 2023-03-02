
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.EstatSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.EstatSolicitudIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudManager;

import org.fundaciobit.pinbaladmin.model.entity.EstatSolicitud;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EstatSolicitudService extends EstatSolicitudIJPAManager,IEstatSolicitudManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/EstatSolicitudEJB!org.fundaciobit.pinbaladmin.ejb.EstatSolicitudService";

    public EstatSolicitudJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(EstatSolicitud instance, FitxerService fitxerEjb) throws I18NException;
}
