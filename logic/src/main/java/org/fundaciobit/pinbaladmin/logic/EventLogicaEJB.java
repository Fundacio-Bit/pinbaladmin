package org.fundaciobit.pinbaladmin.logic;


import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.EventEJB;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 */
@Stateless(name = "EventLogicaEJB")
@SecurityDomain("seycon")
public class EventLogicaEJB extends EventEJB implements EventLogicaLocal {
  
  
  @Override
  @PermitAll
  public Event create(Event bean) throws I18NException {
    return super.create(bean);
  }

}