
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.DocumentCedentJPA;
import org.fundaciobit.pinbaladmin.persistence.DocumentCedentIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IDocumentCedentManager;

import org.fundaciobit.pinbaladmin.model.entity.DocumentCedent;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface DocumentCedentService extends DocumentCedentIJPAManager,IDocumentCedentManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/DocumentCedentEJB!org.fundaciobit.pinbaladmin.ejb.DocumentCedentService";

    public DocumentCedentJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(DocumentCedent instance, FitxerService fitxerEjb) throws I18NException;
}
