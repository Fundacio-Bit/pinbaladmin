
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.DocumentCedentJPA;
import org.fundaciobit.pinbaladmin.model.dao.IDocumentCedentManager;

@Local
public interface DocumentCedentLocal extends IDocumentCedentManager {

 public static final String JNDI_NAME = "pinbaladmin/DocumentCedentEJB/local";
  public DocumentCedentJPA findByPrimaryKey(Long _ID_);
}
