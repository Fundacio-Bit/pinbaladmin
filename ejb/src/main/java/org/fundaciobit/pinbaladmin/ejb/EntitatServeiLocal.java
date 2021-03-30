
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.EntitatServeiJPA;
import org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager;

@Local
public interface EntitatServeiLocal extends IEntitatServeiManager {

 public static final String JNDI_NAME = "pinbaladmin/EntitatServeiEJB/local";
  public EntitatServeiJPA findByPrimaryKey(Long _ID_);
}
