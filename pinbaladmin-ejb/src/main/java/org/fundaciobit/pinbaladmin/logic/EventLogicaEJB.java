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
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.utils.EmailUtil;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
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
 
		log.info("EventLogicaEJB.create: tipus: " + ev.getTipus() );
		if (ev.getTipus() == Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC) {

			Long itemID;
			String tipus;
			String titol;
			
			if (ev.getSolicitudID() != null && ev.getIncidenciaTecnicaID() == null) {
				itemID = ev.getSolicitudID();
				tipus = "solicitud";
				titol = solicitudLogicaEjb.executeQueryOne(SolicitudFields.PROCEDIMENTNOM, SolicitudFields.SOLICITUDID.equal(itemID));
			} else {
				itemID = ev.getIncidenciaTecnicaID();
				tipus = "incidencia";
				titol = incidenciaLogicaEjb.executeQueryOne(IncidenciaTecnicaFields.TITOL, IncidenciaTecnicaFields.INCIDENCIATECNICAID.equal(itemID));
			}
			
			String subject = "PINBAL [" + itemID + "] - ACTUALITZACIÓ " + tipus.toUpperCase() + " - " + titol;
			try {

				final String from = Configuracio.getAppEmail();

				final String message = "Bon dia;<br/><br/><b>Número " + tipus + ": " + itemID + "</b>" + "<br/><br/>"
						+ "<div style=\"background-color:#f6f6f6;\">"
						+ ev.getComentari().replace("\n", "<br/>")
						+ (ev.getFitxerID() == null ? "" : "<br/><br/><b>S'han adjuntat fitxers.</b>")
						+ "</div>"
						+ getPeuCorreu(itemID, tipus);

				final boolean isHtml = true;

				String email = ev.getDestinatarimail();
				FitxerJPA adjunt = ev.getFitxer();

				this.update(ev);

				log.info("CORREU PER ENVIAR");
				EmailUtil.postMail(subject, message, isHtml, from, adjunt, email);
				log.info("CORREU ENVIAT");

			} catch (Throwable th) {
				log.error("Error enviant correu: " + th.getMessage());
				throw new I18NException("Error enviant correu: " + th.getMessage());
			}
		}
		return ev;
	}

    private String getPeuCorreu(Long soliID, String tipus) {
		//tipus pot ser "solicitud" o "incidencia"
		String url = Configuracio.getAppUrl() + "/public/event" + tipus + "/veureevents/"
				+ HibernateFileUtil.encryptFileID(soliID);
		
		String msg = "<div id=\"peu_correu\">"
				
				+ "  <div id=\"reObrir\">"
				+ "     Per respondre, contesteu, per favor, utilitzant el següent enllaç: <a"
				+ "     href=\"" + url + "\"> Accedir a solicitud</a>"
				+ "  </div>"

				+ "  <div id=\"firma\">"
				+ "		Salutacions<br /> <i>Àrea de Govern Digital - Fundació BIT</i>"
				+ "  </div>"

				+ "  <div id=\"noContestar\">"
				+ "		Per favor, NO CONTESTEU directament aquest correu, per fer qualsevol consulta sobre la " + tipus
				+ " accediu a l'enllaç aportat en aquest correu."
				+ "  </div>" 
				
				+ "  <style>" 
				+ "     #peu_correu {margin: .5rem;}"
				+ "     #peu_correu div {padding: .5rem 0;}"
				+ "     #noContestar {color: #868686; border: 4px double #868686; border-left: none; border-right: none;}" 
				+ "  </style>"
				
				+ "</div>";

		return msg;
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