
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager;

import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface SolicitudServeiService extends SolicitudServeiIJPAManager,ISolicitudServeiManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/SolicitudServeiEJB!org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService";

    public SolicitudServeiJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(SolicitudServei instance, FitxerService fitxerEjb) throws I18NException;
}
