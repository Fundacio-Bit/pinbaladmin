
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TipusTiquetJPA;
import org.fundaciobit.pinbaladmin.persistence.TipusTiquetIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITipusTiquetManager;

import org.fundaciobit.pinbaladmin.model.entity.TipusTiquet;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TipusTiquetService extends TipusTiquetIJPAManager,ITipusTiquetManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TipusTiquetEJB!org.fundaciobit.pinbaladmin.ejb.TipusTiquetService";

    public TipusTiquetJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TipusTiquet instance, FitxerService fitxerEjb) throws I18NException;
}
