
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.EntitatJPA;
import org.fundaciobit.pinbaladmin.model.dao.IEntitatManager;

@Local
public interface EntitatLocal extends IEntitatManager {

 public static final String JNDI_NAME = "pinbaladmin/EntitatEJB/local";
  public EntitatJPA findByPrimaryKey(Long _ID_);
}
