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
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaLocal;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
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

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EventLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EventLocal eventEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudLocal solicitudEjb;

  @EJB(mappedName = IncidenciaTecnicaLogicaLocal.JNDI_NAME)
  protected IncidenciaTecnicaLogicaLocal incidenciaTecnicaLogicaEjb;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ModelAndView crearEventGet(HttpServletRequest request, HttpServletResponse response) {

    return crearEventPost(request, response);
  }

  /**
   * Guardar un nou Event
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public ModelAndView crearEventPost(HttpServletRequest request,
      HttpServletResponse response) {

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
    Timestamp to = new Timestamp(atEndOfDay(date).getTime());

    log.info("from: " + from);
    log.info("to: " + to);

    final String username = request.getRemoteUser();

    log.info("remote user: " + username);

    Where w = Where.AND(EventFields.PERSONA.equal(username),

        EventFields.DATAEVENT.between(from, to)

    );

    mav.addObject("data", dateStr);

    try {
      List<Event> events = eventEjb.select(w);

      log.info("Queesticfent: #events");

      List<String> items = new ArrayList<String>();

      for (Event event : events) {

        boolean esSoli = (event.getSolicitudID() != null);

        StringBuffer str = new StringBuffer("PINBAL: ");

        String tipus;
        String tipusTitle;
        long id;
        String text;
        if (esSoli) {
          tipus = "Soli";
          id = event.getSolicitudID();
          Solicitud soli = solicitudEjb.findByPrimaryKey(id);
          tipusTitle = soli.getProcedimentNom();
        } else {
          tipus = "Inc.Tec.";
          id = event.getIncidenciaTecnicaID();

          IncidenciaTecnica it = incidenciaTecnicaLogicaEjb.findByPrimaryKey(id);
          tipusTitle = it.getTitol();
        }

        text = event.getComentari();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String dia = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
        String mes = String.format("%02d", cal.get(Calendar.MONTH) + 1);
        int any = cal.get(Calendar.YEAR);

        String msg = "PINBAL: (" + tipus + "[" + id + "] => " + tipusTitle + " ) " + text;

        if (msg.length() > 230) {
          msg = msg.substring(0, 230);
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
            + "%26usuariID%3D" + username + "%26projecteID%3D28&accioID=-3&dada1="
            + msgEnc;

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
