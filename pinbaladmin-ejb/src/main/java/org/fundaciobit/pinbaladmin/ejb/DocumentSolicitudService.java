
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IDocumentSolicitudManager;

import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface DocumentSolicitudService extends DocumentSolicitudIJPAManager,IDocumentSolicitudManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/DocumentSolicitudEJB!org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService";

    public DocumentSolicitudJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(DocumentSolicitud instance, FitxerService fitxerEjb) throws I18NException;
}
