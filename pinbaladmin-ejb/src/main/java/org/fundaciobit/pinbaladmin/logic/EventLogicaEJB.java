package org.fundaciobit.pinbaladmin.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.EventEJB;
import org.fundaciobit.pinbaladmin.model.entity.Event;

/**
 * 
 * @author anadal
 */
@Stateless(name = "EventLogicaEJB")
public class EventLogicaEJB extends EventEJB implements EventLogicaService {

    @Override
    @PermitAll
    public Event create(Event bean) throws I18NException {
        return super.create(bean);
    }

    @Override
    public Set<Long> deleteFull(Long eventID) throws I18NException {

        Set<Long> files = new HashSet<Long>();
        Long fileID = executeQueryOne(FITXERID, EVENTID.equal(eventID));
        if (fileID != null) {
            files.add(fileID);
        }

        this.delete(eventID);

        return files;

    }

    @Override
    public Set<Long> deleteFullBySolicitantID(Long soliID) throws I18NException {

        Set<Long> files = new HashSet<Long>();

        List<Event> events = select(SOLICITUDID.equal(soliID));

        for (Event event : events) {
            files.addAll(deleteFull(event.getEventID()));
        }

        return files;

    }

}