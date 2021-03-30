
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.DocumentJPA;
import org.fundaciobit.pinbaladmin.model.dao.IDocumentManager;

@Local
public interface DocumentLocal extends IDocumentManager {

 public static final String JNDI_NAME = "pinbaladmin/DocumentEJB/local";
  public DocumentJPA findByPrimaryKey(Long _ID_);
}
