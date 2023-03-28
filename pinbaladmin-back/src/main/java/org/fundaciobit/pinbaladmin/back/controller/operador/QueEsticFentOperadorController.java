package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.model.bean.EventBean;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/queesticfent")
@SessionAttributes(types = { EventForm.class, EventFilterForm.class })
public class QueEsticFentOperadorController {

    protected static final Logger log = Logger.getLogger(QueEsticFentOperadorController.class);

    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EventService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.EventService eventEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;

    @EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
    protected IncidenciaTecnicaLogicaService incidenciaTecnicaLogicaEjb;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listGet(HttpServletRequest request, HttpServletResponse response) {

        return listPost(request, response);
    }

    /**
     * Guardar un nou Event
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView listPost(HttpServletRequest request, HttpServletResponse response) {

        log.info("Entra a queesticfent");

        ModelAndView mav = new ModelAndView("queesticfent");

        String dateStr = request.getParameter("data");

        Date date;
        if (dateStr == null || dateStr.trim().length() == 0) {

            date = new Date();

            dateStr = SDF.format(date);

        } else {

            try {
                date = SDF.parse(dateStr);
            } catch (Throwable e) {
                HtmlUtils.saveMessageError(request, "Error en el format de la data: " + dateStr);

                mav.addObject("data", SDF.format(new Date()));

                return mav;
            }
        }

        Timestamp from = new Timestamp(atStartOfDay(date).getTime());
        log.info("from: " + from);

        Timestamp to = new Timestamp(atEndOfDay(date).getTime());
        log.info("to: " + to);

        final String username = request.getRemoteUser();
        log.info("remote user: " + username);

        mav.addObject("data", dateStr);

        try {
            
            Map<Long, Event> fullevents = new TreeMap<Long, Event>();
            
            // (1) Consultam sol·licituds
            {
                final Where w1Cre= SolicitudFields.CREADOR.equal(username);
                final Where w1Ope= SolicitudFields.OPERADOR.equal(username);
                
                final Where w1 = Where.OR(w1Cre, w1Ope);

                final Where w2 = SolicitudFields.DATAINICI.between(from, to);
                final Where w3 = SolicitudFields.DATAFI.between(from, to);
                List<Solicitud> solis = solicitudEjb.select(Where.AND(w1, Where.OR(w2, w3)));

                // Convertin a Event
                for (Solicitud solicitud : solis) {
                    Event ev = new EventBean();
                    ev.setSolicitudID(solicitud.getSolicitudID());
                    fullevents.put(solicitud.getSolicitudID(), ev);
                }
            }

            // (2) Consultam incidencies
            {
                final Where w1Cre= IncidenciaTecnicaFields.CREADOR.equal(username);
                final Where w1Ope= IncidenciaTecnicaFields.OPERADOR.equal(username);
                
                final Where w1 = Where.OR(w1Cre, w1Ope);

                final Where w2 = IncidenciaTecnicaFields.DATAINICI.between(from, to);
                final Where w3 = IncidenciaTecnicaFields.DATAFI.between(from, to);
                List<IncidenciaTecnica> its = incidenciaTecnicaLogicaEjb.select(Where.AND(w1, Where.OR(w2, w3)));

                // Convertin a Event
                for (IncidenciaTecnica ic : its) {
                    Event ev = new EventBean();
                    ev.setIncidenciaTecnicaID(ic.getIncidenciaTecnicaID());
                    fullevents.put(ic.getIncidenciaTecnicaID(), ev);
                }
            }

            // (3) Consultam events
            {
                final Where wOp1 = EventFields.PERSONA.equal(username);
                final Where wOp2 = EventFields.PERSONA.like("% " + username + " %");
                final Where w1 = Where.OR(wOp1, wOp2);
                
                final Where w2 = EventFields.DATAEVENT.between(from, to);
                List<Event> events = eventEjb.select(Where.AND(w1, w2));
                for (Event event : events) {
                    if (event.getSolicitudID() == null) {
                        fullevents.put(event.getIncidenciaTecnicaID(), event);
                    }else {
                        fullevents.put(event.getSolicitudID(), event);
                    }
                }
            }
            log.info("Queesticfent: #events");

            List<String> items = new ArrayList<String>();

            for (Event event : fullevents.values()) {

                String tipus;
                String tipusTitle;
                long id;
                
                boolean esSoli = (event.getSolicitudID() != null);

                //String text;
                if (esSoli) {
                    id = event.getSolicitudID();

                    Solicitud soli = solicitudEjb.findByPrimaryKey(id);
                    tipusTitle = soli.getProcedimentNom();

                    final String cai = soli.getTicketAssociat();
                    if (cai == null) {
                        tipus = "Solicitud[" + id + "]";
                    } else {
                        tipus = "CAI-" + cai;
                    }
                } else {

                    id = event.getIncidenciaTecnicaID();
                    tipus = "Inc.Tec.[" + id + "]";
                    tipusTitle = incidenciaTecnicaLogicaEjb.executeQueryOne(IncidenciaTecnicaFields.TITOL,
                            IncidenciaTecnicaFields.INCIDENCIATECNICAID.equal(id));
                }

                //text = event.getComentari();

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                String dia = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
                String mes = String.format("%02d", cal.get(Calendar.MONTH) + 1);
                int any = cal.get(Calendar.YEAR);

                String msg = "INTEROP: " + tipus + ": " + tipusTitle;

                int MAX_LENGTH = 230;
                if (msg.length() > MAX_LENGTH) {
                    msg = msg.substring(0, MAX_LENGTH);
                }

                String msgEnc;
                try {
                    msgEnc = URLEncoder.encode(msg, StandardCharsets.ISO_8859_1.toString());
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    msgEnc = msg;
                }

                String url = "https://queesticfent.fundaciobit.org/queesticfent/NovaModificacio.jsp?usuariID="
                        + username + "&data=" + dia + "%2F" + mes + "%2F" + any
                        + "+00%3A00&projecteID=28&redirectUrl=LlistatEntrades.jsp%3Fmes%3D0%26any%3D" + any
                        + "%26usuariID%3D" + username + "%26projecteID%3D28&accioID=-3&dada1=" + msgEnc;
                
                StringBuffer str = new StringBuffer();

                str.append(msg + "  <a target=\"_blank\" href=\"" + url
                        + "\" ><span class=\"label label-success\"><b>+</b></span> </a>");

                items.add(str.toString());
            }
            mav.addObject("items", items);
            
        } catch (I18NException e) {
            HtmlUtils.saveMessageError(request, "Error llegint events: " + I18NUtils.getMessage(e));
        }

        return mav;

    }

    public static Date atEndOfDay(Date date) {
        return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
    }

    public static Date atStartOfDay(Date date) {
        return DateUtils.truncate(date, Calendar.DATE);
    }

}
