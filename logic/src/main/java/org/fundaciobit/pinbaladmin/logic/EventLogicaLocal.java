package org.fundaciobit.pinbaladmin.logic;

import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.EventLocal;
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

  public Set<Long> deleteFull(Long eventID) throws I18NException;
  
  public Set<Long> deleteFullBySolicitantID(Long soliID) throws I18NException;
  
}
