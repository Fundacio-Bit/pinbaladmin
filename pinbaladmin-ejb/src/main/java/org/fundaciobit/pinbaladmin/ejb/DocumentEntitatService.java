
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.DocumentEntitatJPA;
import org.fundaciobit.pinbaladmin.persistence.DocumentEntitatIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IDocumentEntitatManager;

import org.fundaciobit.pinbaladmin.model.entity.DocumentEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface DocumentEntitatService extends DocumentEntitatIJPAManager,IDocumentEntitatManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/DocumentEntitatEJB!org.fundaciobit.pinbaladmin.ejb.DocumentEntitatService";

    public DocumentEntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(DocumentEntitat instance, FitxerService fitxerEjb) throws I18NException;
}
