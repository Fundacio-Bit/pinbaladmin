
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.EstatServeiJPA;
import org.fundaciobit.pinbaladmin.model.dao.IEstatServeiManager;

@Local
public interface EstatServeiLocal extends IEstatServeiManager {

 public static final String JNDI_NAME = "pinbaladmin/EstatServeiEJB/local";
  public EstatServeiJPA findByPrimaryKey(Long _ID_);
}
