
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.EventJPA;
import org.fundaciobit.pinbaladmin.model.dao.IEventManager;

@Local
public interface EventLocal extends IEventManager {

 public static final String JNDI_NAME = "pinbaladmin/EventEJB/local";
  public EventJPA findByPrimaryKey(Long _ID_);
}
