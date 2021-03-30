
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.model.dao.IDocumentSolicitudManager;

@Local
public interface DocumentSolicitudLocal extends IDocumentSolicitudManager {

 public static final String JNDI_NAME = "pinbaladmin/DocumentSolicitudEJB/local";
  public DocumentSolicitudJPA findByPrimaryKey(Long _ID_);
}
