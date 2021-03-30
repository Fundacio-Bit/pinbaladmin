
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.GrupEntitatJPA;
import org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager;

@Local
public interface GrupEntitatLocal extends IGrupEntitatManager {

 public static final String JNDI_NAME = "pinbaladmin/GrupEntitatEJB/local";
  public GrupEntitatJPA findByPrimaryKey(Long _ID_);
}
