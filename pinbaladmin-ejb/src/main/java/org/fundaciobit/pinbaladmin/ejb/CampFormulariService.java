
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.CampFormulariJPA;
import org.fundaciobit.pinbaladmin.persistence.CampFormulariIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ICampFormulariManager;

import org.fundaciobit.pinbaladmin.model.entity.CampFormulari;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface CampFormulariService extends CampFormulariIJPAManager,ICampFormulariManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/CampFormulariEJB!org.fundaciobit.pinbaladmin.ejb.CampFormulariService";

    public CampFormulariJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(CampFormulari instance, FitxerService fitxerEjb) throws I18NException;
}
