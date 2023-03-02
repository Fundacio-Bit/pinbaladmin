
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.EstatSolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.EstatSolicitudServeiIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudServeiManager;

import org.fundaciobit.pinbaladmin.model.entity.EstatSolicitudServei;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EstatSolicitudServeiService extends EstatSolicitudServeiIJPAManager,IEstatSolicitudServeiManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/EstatSolicitudServeiEJB!org.fundaciobit.pinbaladmin.ejb.EstatSolicitudServeiService";

    public EstatSolicitudServeiJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(EstatSolicitudServei instance, FitxerService fitxerEjb) throws I18NException;
}
