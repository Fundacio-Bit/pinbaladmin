
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.DocumentEntitatJPA;
import org.fundaciobit.pinbaladmin.model.dao.IDocumentEntitatManager;

@Local
public interface DocumentEntitatLocal extends IDocumentEntitatManager {

 public static final String JNDI_NAME = "pinbaladmin/DocumentEntitatEJB/local";
  public DocumentEntitatJPA findByPrimaryKey(Long _ID_);
}
