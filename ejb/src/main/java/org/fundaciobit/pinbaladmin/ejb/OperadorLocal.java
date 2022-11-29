
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.OperadorJPA;
import org.fundaciobit.pinbaladmin.model.dao.IOperadorManager;

@Local
public interface OperadorLocal extends IOperadorManager {

 public static final String JNDI_NAME = "pinbaladmin/OperadorEJB/local";
  public OperadorJPA findByPrimaryKey(Long _ID_);
}
