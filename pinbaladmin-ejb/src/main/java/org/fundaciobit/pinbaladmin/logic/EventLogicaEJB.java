package org.fundaciobit.pinbaladmin.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.EventEJB;
import org.fundaciobit.pinbaladmin.logic.utils.EmailUtil;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;

/**
 * 
 * @author anadal
 */
@Stateless(name = "EventLogicaEJB")
public class EventLogicaEJB extends EventEJB implements EventLogicaService {

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;
    
    @EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
    protected IncidenciaTecnicaLogicaService incidenciaLogicaEjb;
    
    @Override
    @PermitAll
	public Event create(Event bean) throws I18NException {
		Event ev = super.create(bean);

		log.info("EventLogicaEJB.create: tipus: " + ev.getTipus());
		if (ev.getTipus() == Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC || ev.getTipus() == Constants.EVENT_TIPUS_COMENTARI_SUPORT || ev.getTipus() == Constants.EVENT_TIPUS_CONSULTA_A_CEDENT ) {
			int idx = ev.getComentari().indexOf("|");

			String subject = ev.getComentari().substring(0, idx);
			String message = ev.getComentari().substring(idx + 1);

			final String from = Configuracio.getAppEmail();
			final boolean isHtml = true;
			String email = ev.getDestinatarimail();
			String[] emails = email.split(";");
			
			FitxerJPA adjunt = ev.getFitxer();

			try {
				log.info("CORREU PER ENVIAR");
				EmailUtil.postMail(subject, message, isHtml, from, adjunt, emails);
				log.info("CORREU ENVIAT");

				ev.setComentari(message);
				this.update(ev);
			} catch (Throwable th) {
				log.error("Error enviant correu: " + th.getMessage());
				throw new I18NException("Error enviant correu: " + th.getMessage());
			}
		}
		return ev;
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

    @Override
    @PermitAll
    public Event update(Event instance) throws I18NException {
        log.info("Update de EventLogicaEJB");
        log.info(instance.getDestinatari() + " - " + instance.getDestinatarimail());
        return super.update(instance);
    }
}