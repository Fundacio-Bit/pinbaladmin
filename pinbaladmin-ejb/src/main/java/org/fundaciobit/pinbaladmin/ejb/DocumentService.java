
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
import org.fundaciobit.pinbaladmin.persistence.DocumentIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IDocumentManager;

import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface DocumentService extends DocumentIJPAManager,IDocumentManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/DocumentEJB!org.fundaciobit.pinbaladmin.ejb.DocumentService";

    public DocumentJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Document instance, FitxerService fitxerEjb) throws I18NException;
}
