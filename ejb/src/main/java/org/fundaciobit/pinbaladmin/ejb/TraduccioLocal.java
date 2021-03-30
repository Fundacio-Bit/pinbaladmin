
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.TraduccioJPA;
import org.fundaciobit.pinbaladmin.model.dao.ITraduccioManager;

@Local
public interface TraduccioLocal extends ITraduccioManager {

 public static final String JNDI_NAME = "pinbaladmin/TraduccioEJB/local";
  public TraduccioJPA findByPrimaryKey(Long _ID_);
}
