
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.ServeiJPA;
import org.fundaciobit.pinbaladmin.model.dao.IServeiManager;

@Local
public interface ServeiLocal extends IServeiManager {

 public static final String JNDI_NAME = "pinbaladmin/ServeiEJB/local";
  public ServeiJPA findByPrimaryKey(Long _ID_);
}
