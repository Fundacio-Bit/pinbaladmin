
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.AreaJPA;
import org.fundaciobit.pinbaladmin.model.dao.IAreaManager;

@Local
public interface AreaLocal extends IAreaManager {

 public static final String JNDI_NAME = "pinbaladmin/AreaEJB/local";
  public AreaJPA findByPrimaryKey(Long _ID_);
}
