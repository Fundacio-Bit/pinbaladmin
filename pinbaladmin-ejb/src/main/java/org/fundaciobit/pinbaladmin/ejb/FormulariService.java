
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.FormulariJPA;
import org.fundaciobit.pinbaladmin.persistence.FormulariIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IFormulariManager;

import org.fundaciobit.pinbaladmin.model.entity.Formulari;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface FormulariService extends FormulariIJPAManager,IFormulariManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/FormulariEJB!org.fundaciobit.pinbaladmin.ejb.FormulariService";

    public FormulariJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Formulari instance, FitxerService fitxerEjb) throws I18NException;
}
