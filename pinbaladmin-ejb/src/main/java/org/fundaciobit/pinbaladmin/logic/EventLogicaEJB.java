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
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

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

		int tipus = ev.getTipus();
		log.info("EventLogicaEJB.create: tipus: " + tipus);
		if (tipus == Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC
				|| tipus == Constants.EVENT_TIPUS_COMENTARI_SUPORT
				|| tipus == Constants.EVENT_TIPUS_CONSULTA_A_CEDENT) {

			String email = ev.getDestinatarimail();
			String[] destinataris = email.split(";");
			
			final String from = Configuracio.getAppEmail();
			final boolean isHtml = true;
			
			
			String subject = ev.getAsumpte();
			String message = ev.getComentari();
			
			if (message.indexOf("<div>") == -1) {
				message = "<div>" + message + "</div>";
			}
			
			String peuCorreu;
			if (ev.getSolicitudID() != null) {
				SolicitudJPA s = solicitudLogicaEjb.findByPrimaryKey(ev.getSolicitudID());

				String destinatari = null;
				if (tipus == Constants.EVENT_TIPUS_CONSULTA_A_CEDENT) {
					destinatari = "CEDENT|" + ev.getDestinatari();
				} else if (tipus == Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC) {
					destinatari = "CONTACTE|" + ev.getDestinatari();
				}

				log.info("EventLogicaEJB.create: tipus: " + tipus + " - destinatari: " + destinatari);
				peuCorreu = EmailUtil.getPeuCorreu(ev.getSolicitudID(), "solicitud", destinatari);
			} else {
				String destinatari = "CONTACTE|" + ev.getDestinatari();
				peuCorreu = EmailUtil.getPeuCorreu(ev.getIncidenciaTecnicaID(), "incidenciatecnica", destinatari);
			}
			
			message = "<html><body>" + message + "<br><br>" + peuCorreu + "</body></html>";
			
//			
//			if (tipus == Constants.EVENT_TIPUS_COMENTARI_SUPORT && ev.getSolicitudID() != null) {
//				SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(ev.getSolicitudID());
//				if (soli.getExpedientPid() != null) {
//					subject = "PID [" + soli.getExpedientPid() + "] - " + subject;
//				}
//			}
//			
			FitxerJPA adjunt = ev.getFitxer();

			try {
				log.info("CORREU PER ENVIAR");
				EmailUtil.postMail(subject, message, isHtml, from, adjunt, destinataris);
				ev.setComentari(message);
				this.update(ev);
				
				log.info("CORREU ENVIAT");
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