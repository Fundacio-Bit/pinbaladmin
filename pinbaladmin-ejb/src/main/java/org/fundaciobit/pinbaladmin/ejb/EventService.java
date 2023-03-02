
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.EventIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.IEventManager;

import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EventService extends EventIJPAManager,IEventManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/EventEJB!org.fundaciobit.pinbaladmin.ejb.EventService";

    public EventJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Event instance, FitxerService fitxerEjb) throws I18NException;
}
