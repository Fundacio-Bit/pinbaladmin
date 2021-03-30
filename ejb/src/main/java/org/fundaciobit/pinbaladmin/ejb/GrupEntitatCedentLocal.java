
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.GrupEntitatCedentJPA;
import org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatCedentManager;

@Local
public interface GrupEntitatCedentLocal extends IGrupEntitatCedentManager {

 public static final String JNDI_NAME = "pinbaladmin/GrupEntitatCedentEJB/local";
  public GrupEntitatCedentJPA findByPrimaryKey(Long _ID_);
}
