
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.DepartamentJPA;
import org.fundaciobit.pinbaladmin.model.dao.IDepartamentManager;

@Local
public interface DepartamentLocal extends IDepartamentManager {

 public static final String JNDI_NAME = "pinbaladmin/DepartamentEJB/local";
  public DepartamentJPA findByPrimaryKey(Long _ID_);
}
