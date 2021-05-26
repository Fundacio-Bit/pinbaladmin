package org.fundaciobit.pinbaladmin.logic;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.EventLocal;
import org.fundaciobit.pinbaladmin.jpa.EventJPA;
import org.fundaciobit.pinbaladmin.model.entity.Event;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface EventLogicaLocal extends EventLocal {

  String JNDI_NAME = "pinbaladmin/EventLogicaEJB/local";
  
  @Override
  @PermitAll
  public Event create(Event bean) throws I18NException;

  
  
}
