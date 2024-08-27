package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
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
import org.fundaciobit.pinbaladmin.logic.utils.QueEsticFentUtils;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

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

    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        return rm.value()[0];
    }

    /**
     * Guardar un nou Event
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView listPost(HttpServletRequest request, HttpServletResponse response) {

        log.info("Entra a queesticfent");

        ModelAndView mav = new ModelAndView("queesticfent");

        Date date;
        String dateStr = request.getParameter("data");
        if (dateStr == null || dateStr.trim().length() == 0) {
            date = new Date();
            dateStr = SDF.format(date);
        } else {
            try {
                date = SDF.parse(dateStr);
            } catch (ParseException e) {
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
            Map<String, String[]> fullevents = new TreeMap<String, String[]>();

            // (1) Consultam sol·licituds
            {
                //Quien fue el creador
                final Where wCreador = SolicitudFields.CREADOR.equal(username);
                final Where wCreada = SolicitudFields.DATAINICI.between(from, to);
                final Where w1 = Where.AND(wCreador, wCreada);

                //Quien fue el cerrador
                final Where wOperador = SolicitudFields.OPERADOR.equal(username);
                final Where wCerrada = SolicitudFields.DATAFI.between(from, to);
                final Where w2 = Where.AND(wOperador, wCerrada);

                final Where w3 = Where.OR(w1, w2);
                List<Long> solis = solicitudEjb.executeQuery(SolicitudFields.SOLICITUDID, w3);

                for (Long solicitudID : solis) {
                    afegirEventTipus(fullevents, "SOL", solicitudID);
                }
            }

            // (2) Consultam incidencies
            {
                //Quien fue el creador
                final Where wCreador = IncidenciaTecnicaFields.CREADOR.equal(username);
                final Where wCreada = IncidenciaTecnicaFields.DATAINICI.between(from, to);
                final Where w1 = Where.AND(wCreador, wCreada);

                //Quien fue el cerrador
                final Where wOperador = IncidenciaTecnicaFields.OPERADOR.equal(username);
                final Where wCerrada = IncidenciaTecnicaFields.DATAFI.between(from, to);
                final Where w2 = Where.AND(wOperador, wCerrada);

                final Where w3 = Where.OR(w1, w2);
                List<Long> its = incidenciaTecnicaLogicaEjb.executeQuery(IncidenciaTecnicaFields.INCIDENCIATECNICAID,
                        w3);

                for (Long incidenciaID : its) {
                    afegirEventTipus(fullevents, "INC", incidenciaID);
                }
            }

            // (3) Consultam events
            {
                final Where w1 = EventFields.PERSONA.equal(username);
                final Where w2 = EventFields.DATAEVENT.between(from, to);
                List<Event> events = eventEjb.select(Where.AND(w1, w2));

                for (Event event : events) {
                    if (event.getSolicitudID() != null) {
                        afegirEventTipus(fullevents, "SOL", event.getSolicitudID());
                    } else {
                        afegirEventTipus(fullevents, "INC", event.getIncidenciaTecnicaID());
                    }
                }
            }

            log.info("Queesticfent: #events=" + fullevents.size());
            mav.addObject("items", fullevents.values());
            mav.addObject("contexte", getContextWeb());

            
            //Events ja afegits a queesticfent
			List<String> modificaciones = QueEsticFentUtils.getModificacionsQEF(username, dateStr);

			// Cercar cada modificacio al llistat fullEvents, i si existeix, afegir-la al
			// llistat d'events ja afegits
			Map<String, String[]> eventsJaAfegits = new TreeMap<String, String[]>();
			
			log.info("Queesticfent: #modificacions=" + modificaciones.size());
			for (String dada : modificaciones) {
				String hash = encode(dada);

				if (fullevents.containsKey(hash)) {
					String tipus = dada.split(":")[0].trim();
					String titol = dada.split(":")[1].trim();

					String[] missatge = { tipus, titol, hash };

					log.info("Ja afegit: " + dada);
					fullevents.remove(hash);
					eventsJaAfegits.put(hash, missatge);
				}
			}

			log.info("Queesticfent: #eventsAfegits=" + eventsJaAfegits.size());
			request.setAttribute("itemsAfegits", eventsJaAfegits.values());

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

    public void afegirEventTipus(Map<String, String[]> fullevents, String event, Long id) {

        String tipus = null;
        String titol = null;

        switch (event) {
            case "SOL":
                Solicitud soli = solicitudEjb.findByPrimaryKey(id);

                if (soli.getEntitatEstatal() != null) {
                    tipus = "Estatal [" + id + "]";
                } else if (soli.getDepartamentID() != null || soli.getOrganid() != null) {
                    tipus = "Local [" + id + "]";
                }
                titol = soli.getProcedimentNom();
            break;
            case "INC":
                IncidenciaTecnica ic = incidenciaTecnicaLogicaEjb.findByPrimaryKey(id);

                tipus = "Incidencia [" + id + "]";
                titol = ic.getTitol();
            break;
        }

        String hash = encode(tipus + ": " + titol);

        String[] missatge = { tipus, titol, hash };
        fullevents.put(hash, missatge);
    }

    @RequestMapping(value = "/afegirEntrada/{usuari}/{dateStr}/{msgEnc}", method = RequestMethod.GET)
    public void afegeixEntrada(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("usuari") String usuari, @PathVariable("dateStr") String dateStr,
            @PathVariable("msgEnc") String msgEnc) {

        try {
        	
//			final ModificacionsApi api = getApi();
//
//			AddModificacioRequest modificacio = new AddModificacioRequest();
//			
//			String usuariID = usuari;
//			Long projecteID = 1021L; //PINBAL
//			String language = "ca";

//          // 1. Convertir la cadena a LocalDate
//			// 2. Convertir LocalDate a LocalDateTime (a la hora actual)
//			// 3. Convertir LocalDateTime a OffsetDateTime, añadiendo el offset (por ejemplo, UTC+0)
        	
			LocalDate fechaLocal = LocalDate.parse(dateStr);
			LocalTime time = LocalTime.now();
			LocalDateTime fechaLocalDateTime = fechaLocal.atTime(time);
			OffsetDateTime data = fechaLocalDateTime.atOffset(ZoneOffset.UTC);
//
            String dada1 = decode(msgEnc);
            
//            
//			modificacio.setUsuariID(usuariID);
//			modificacio.setProjecteID(projecteID);
//			modificacio.setData(data);
//			modificacio.setDada1(dada1);
//			modificacio.setLanguage(language);
//
//			Long response2 = api.add(modificacio);

        	Long res = QueEsticFentUtils.afegirModificacioQEF(usuari, dada1, data);
        	
			System.out.println("res = " + res);
        	
			if (res != null) {
				log.info("Afegit missatge: " + dada1);
			}
        } catch (Exception e) {
        	log.error("Error al afegir entrada: " + e.getMessage(), e);
        	e.printStackTrace();
		}
    }

    public String encode(String originalInput) {

        int MAX_LENGTH = 230;
        if (originalInput.length() > MAX_LENGTH) {
            originalInput = originalInput.substring(0, MAX_LENGTH);
        }
        String encodedString;
        encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        return encodedString;
    }

    public String decode(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String missatge = new String(decodedBytes);
        return missatge;
    }
}
