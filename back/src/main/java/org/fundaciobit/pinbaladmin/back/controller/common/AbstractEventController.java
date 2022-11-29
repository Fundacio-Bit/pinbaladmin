package org.fundaciobit.pinbaladmin.back.controller.common;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.operador.EnviarCorreuContacteOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.EventController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.jpa.EventJPA;
import org.fundaciobit.pinbaladmin.logic.EventLogicaLocal;
import org.fundaciobit.pinbaladmin.logic.utils.EmailUtil;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.utils.Configuracio;
import org.fundaciobit.pinbaladmin.utils.Constants;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractEventController<T> extends EventController implements Constants {

  public static final String SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID = "SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID";

  public static final String SESSION_EVENT_IS_ESTATAL = "SESSION_EVENT_IS_ESTATAL";

  @EJB(mappedName = EventLogicaLocal.JNDI_NAME)
  protected EventLogicaLocal eventLogicaEjb;

  public abstract boolean isPublic();

  public abstract boolean isSolicitud();

  @Override
  public boolean isActiveFormEdit() {
    return !isPublic();
  }

  @Override
  public boolean isActiveDelete() {
    return !isPublic();
  }

  @Override
  public boolean isActiveFormView() {
    return !isPublic();
  }

  @Override
  public String getTileForm() {
    return isPublic() ? "eventFormOperadorPublic" : "eventFormOperador";
  }

  @Override
  public String getTileList() {
    return isPublic() ? "eventListOperadorIframePublic" : "eventListOperadorIframe"; // "eventListOperador";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "EventOperador_FilterForm_" + isPublic() + "_" + isSolicitud();
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEventGet(HttpServletRequest request, HttpServletResponse response)
      throws I18NException {

    log.info("\n\n ENTRA A NEW");

    Long itemID = (Long) request.getSession()
        .getAttribute(SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);
    if (itemID == null) {

      String itemNom = isSolicitud() ? "solicituID" : "incidenciaTecnicaID";

      HtmlUtils.saveMessageError(request,
          "XXXXXXXXXX S'ha intentat editar o crear un Event però no s'ha definit el " + itemNom
              + " a través de la sessió " + SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);

      return new ModelAndView(new RedirectView(
          redirectWhenSessionItemIDNotDefined().replace("redirect:", ""), true));

    }

    T item = findItemByPrimaryKey(itemID);

    String email = getPersonaContacteEmail(item);
    if (email == null || email.trim().length() == 0) {
      String itemNom = isSolicitud() ? "solicitud" : "incidència";

      Boolean isEstatal = (Boolean) request.getSession()
          .getAttribute(SESSION_EVENT_IS_ESTATAL);
      if (!Boolean.TRUE.equals(isEstatal)) {

        log.info("\n\n Passa per NEW AMB ERROR");

        HtmlUtils.saveMessageError(request,
            "XXXXXXXX No s'ha definit el email de la persona de contacte dins de la "
                + itemNom);
        return new ModelAndView(new RedirectView(
            getRedirectWhenCancel(request, itemID).replace("redirect:", ""), true));
      }
    }

    ModelAndView mav = super.crearEventGet(request, response);

    return mav;
  }

  @Override
  public EventForm getEventForm(EventJPA _jpa, boolean __isView, HttpServletRequest request,
      ModelAndView mav) throws I18NException {

    EventForm eventForm = super.getEventForm(_jpa, __isView, request, mav);

    Long itemID = (Long) request.getSession()
        .getAttribute(SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);
    if (itemID == null) {

      String itemNom = isSolicitud() ? "solicituID" : "incidenciaTecnicaID";

      HtmlUtils.saveMessageError(request,
          "S'ha intentat editar o crear un Event però no s'ha definit el " + itemNom
              + " a traves de la sessió " + SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);

      mav.setView(new RedirectView(
          redirectWhenSessionItemIDNotDefined().replace("redirect:", ""), true));
      return eventForm;
    }

    T item = findItemByPrimaryKey(itemID);

    if (item == null) {
      String itemNom = isSolicitud() ? "solicitud" : "incidenciaTecnica";
      HtmlUtils.saveMessageError(request,
          "S'ha intentat editar o crear un Event però el ID de " + itemNom + " ( " + itemID
              + ") retorna un element null.");

      mav.setView(new RedirectView(
          redirectWhenSessionItemIDNotDefined().replace("redirect:", ""), true));
      return eventForm;
    }

    if (isSolicitud()) {
      eventForm.addHiddenField(INCIDENCIATECNICAID);
      eventForm.addReadOnlyField(SOLICITUDID);
    } else {
      eventForm.addHiddenField(SOLICITUDID);
      eventForm.addReadOnlyField(INCIDENCIATECNICAID);
    }

    if (eventForm.isNou()) {
      
      
      eventForm.setCancelButtonVisible(false);
      
      eventForm.setTitleCode("=Nova Entrada");
      
      /*
       * if (isPublic()) { mav.setViewName("eventFormOperadorPublic"); } else {
       * mav.setViewName("eventFormOperador"); }
       */
      eventForm.getEvent().setDataEvent(new Timestamp(System.currentTimeMillis()));
      if (isSolicitud()) {
        eventForm.getEvent().setSolicitudID(itemID);
      } else {
        eventForm.getEvent().setIncidenciaTecnicaID(itemID);
      }

      if (isPublic()) {
        eventForm.getEvent().setPersona(getPersonaContacteNom(item));
        eventForm.getEvent().setTipus(EVENT_TIPUS_COMENTARI_CONTACTE);
        eventForm.addHiddenField(TIPUS);
        eventForm.addHiddenField(NOLLEGIT);

        eventForm.addReadOnlyField(DATAEVENT);
        eventForm.addReadOnlyField(PERSONA);

        eventForm.getEvent().setNoLlegit(true);
      } else {
        eventForm.getEvent().setPersona(request.getUserPrincipal().getName());
        eventForm.getEvent().setTipus(EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT);
      }
    }

    if (!isPublic()) {
      mav.addObject("persona_tramitador", request.getUserPrincipal().getName());
      request.getSession().setAttribute("persona_tramitador",
          request.getUserPrincipal().getName());
    }

    String email = getPersonaContacteEmail(item);
    if (email == null || email.trim().length() == 0) {

      Boolean isEstatal = (Boolean) request.getSession()
          .getAttribute(SESSION_EVENT_IS_ESTATAL);
      if (!Boolean.TRUE.equals(isEstatal)) {

        String itemNom = isSolicitud() ? "solicitud" : "incidència";
        HtmlUtils.saveMessageError(request,
            "No s'ha definit el email de la persona de contacte dins de la " + itemNom);
        mav.setView(new RedirectView(
            getRedirectWhenCancel(request, itemID).replace("redirect:", ""), true));
        return eventForm;
      }
    }

    mav.addObject("persona_contacte", email);
    request.getSession().setAttribute("persona_contacte", email);

    eventForm.setAttachedAdditionalJspCode(true);

    return eventForm;
  }

  public abstract T findItemByPrimaryKey(Long itemID);

  public abstract String redirectWhenSessionItemIDNotDefined();

  public abstract String getPersonaContacteEmail(T item);

  public abstract String getPersonaContacteNom(T item);

  public abstract String getUrlToEditItem(T item);

  public abstract String getUrlToCloseItem(T item);

  public abstract String getTitol(T item);

  public abstract boolean isClosed(T item);

  @RequestMapping(value = "/veureevents/{itemStrID}", method = RequestMethod.GET)
  public String veureEvents(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String itemStrID) throws I18NException {

    Boolean isEstatal = Boolean.FALSE;

    return veureEvents(request, response, itemStrID, isEstatal);

  }

  @RequestMapping(value = "/veureevents/{itemStrID}/{isEstatal}", method = RequestMethod.GET)
  public String veureEvents(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("itemStrID") String itemStrID,
      @PathVariable("isEstatal") Boolean isEstatal) throws I18NException {

    Long itemID;
    if (isPublic()) {
      itemID = HibernateFileUtil.decryptFileID(itemStrID);
    } else {
      itemID = Long.parseLong(itemStrID);
    }

    request.getSession().setAttribute(SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID, itemID);
    request.getSession().setAttribute(SESSION_EVENT_IS_ESTATAL, isEstatal);

    return "redirect:" + getContextWeb() + "/list";

  }

  @Override
  public String getRedirectWhenCreated(HttpServletRequest request, EventForm eventForm) {

    log.info("Entra a getRedirectWhenCreated ... Princial => " + request.getUserPrincipal());

    if (request.getUserPrincipal() != null) {
      // Accés loguejat
      Event ev = eventForm.getEvent();

      if (ev.getTipus() == Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC) {

        Long itemID;
        String tipus;
        if (isSolicitud()) {

          itemID = ev.getSolicitudID();
          tipus = "sol·licitud";
        } else {

          itemID = ev.getIncidenciaTecnicaID();
          tipus = "incidència";
        }
        try {
          final String subject = "PINBAL [" + itemID + "] - ACTUALITZACIÓ "
              + tipus.toUpperCase() + " - " + getTitolItem(itemID);
          final String from = Configuracio.getAppEmail();
          final String message = getCapCorreu(tipus, itemID)
              + "<div style=\"background-color:#f6f6f6;\">"
              + eventForm.getEvent().getComentari().replace("\n", "<br/>")
              + (eventForm.getEvent().getFitxerID() == null ? ""
                  : "<br/><br/><b>S'han adjuntat fitxers.</b>")
              + "</div><br/>"
              + "Podrà reobrir aquesta incidència o aportar més informació utilitzant el següent enllaç: <a href=\""
              + getLinkPublic(itemID) + "\" > Accedir a " + tipus + "</a><br/><br/>"
              + getPeuCorreu();

          final boolean isHtml = true;

          EmailUtil.postMail(subject, message, isHtml, from,
              getPersonaContacteEmailByItemID(itemID));
        } catch (Throwable th) {

          String msg;
          if (th instanceof I18NException) {
            msg = I18NUtils.getMessage((I18NException) th);
          } else {
            msg = th.getMessage();
          }

          HtmlUtils.saveMessageError(request, "Error enviant correu: " + msg);

        }

      }

    }

    return getRedirectWhenCancel(request, eventForm.getEvent().getEventID());
  }

  @Override
  public String getRedirectWhenModified(HttpServletRequest request, EventForm eventForm,
      Throwable __e) {
    if (__e == null) {
      return getRedirectWhenCancel(request, eventForm.getEvent().getEventID());
    } else {
      return getTileForm();
    }
  }

  @Override
  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long eventID,
      Throwable __e) {
    return getRedirectWhenCancel(request, eventID);
  }

  @Override
  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long eventID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public static final String ENVIAR_ENLLAZ = "/enviarenllaz/";

  @RequestMapping(value = ENVIAR_ENLLAZ + "{itemID}", method = RequestMethod.GET)
  public String enviarEnllaz(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long itemID) {

    try {
      String email = getPersonaContacteEmailByItemID(itemID);

      String itemNom = isSolicitud() ? "sol·licitud" : "incidència";
      if (email == null) {

        HtmlUtils.saveMessageError(request,
            "El contacte de la " + itemNom + "  " + itemID + " és buit.");
      } else {

        String[] emails = { email };
        log.error("Dest: " + Arrays.toString(emails));

        final String titol = getTitolItem(itemID);

        final String tipus = isSolicitud() ? "Sol·licitud" : "Incidència";

        final boolean isHtml = true;
        for (String address : emails) {
          try {

            String url = getLinkPublic(itemID);

            final String subject = "PINBAL [" + itemID + "] - ALTA " + tipus.toUpperCase()
                + " - " + titol;

            String msg = getCapCorreu(tipus, itemID)
                + "    Des de la Fundació Bit l'informam que la seva " + tipus + " titulada '"
                + titol + "' ha estat rebuda correctament i es troba en estudi.<br/><br/>"
                + "    Per fer el seguiment de la " + tipus
                + " ho podrà fer utilitzant el següent enllaç: " + "<a href=\""
                + getLinkPublic(itemID) + "\" > Accedir a " + tipus + "</a>" + "<br/><br/>"
                + getPeuCorreu();
            /*
             * 
             * "Enllaç a la " + itemNom + " titulada '" + titol + "'",
             * "Bones:\n\n" +
             * "En el següent enllaç trobarà les accions que s'estan duent a terme en la seva petició titulada: '"
             * + titol + "'." +
             * "\n\nTambé podrà afegir informació addicional a la seva " +
             * itemNom + " a través d'aquest enllaç: " + url +
             * "\n\n      Atentament,"
             * 
             */

            EmailUtil.postMail(subject, msg, isHtml, Configuracio.getAppEmail(), address);

            HtmlUtils.saveMessageSuccess(request,
                "S'ha enviat un correu a " + address + " amb l'enllaç " + url);

          } catch (Exception e) {
            String msg = "No s'ha pogut enviar el correu a " + address + ": " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
          }
        }
      }
    } catch (I18NException i18ne) {

      String msg = "No s'ha pogut enviar el correu: " + I18NUtils.getMessage(i18ne);
      log.error(msg, i18ne);
      HtmlUtils.saveMessageError(request, msg);

    }

    return "redirect:" + getContextWeb() + "/veureevents/" + itemID;
  }

  protected String getCapCorreu(String tipus, Long itemID) {
    return "Bon dia;<br/><br/><b>Número " + tipus + ": " + itemID + "</b><br/><br/>";
  }

  protected String getPeuCorreu() {
    return "        Salutacions<br/><br/>"
        + "        <i>Àrea de Govern Digital - Fundació BIT</i>"
        + "<div style=\"color:#868686\">"
        + "=================================================================<br/>"
        + "Per favor, no contesteu directament aquest correu, per fer qualsevol consulta<br/>"
        + "sobre la incidència accediu a l'enllaç aportat en aquest correu.<br/>"
        + "=================================================================" + "</div>";
  }

  private String getTitolItem(Long itemID) throws I18NException {
    T item = findItemByPrimaryKey(itemID);
    String titol;
    log.info("item getClass" + item.getClass());
    if (item instanceof Solicitud) {
      Solicitud soli = ((Solicitud) item);
      titol = soli.getProcedimentCodi() + " " + soli.getProcedimentNom();
    } else if (item instanceof IncidenciaTecnica) {
      IncidenciaTecnica inci = ((IncidenciaTecnica) item);
      titol = inci.getTitol();
    } else {
      throw new I18NException("genapp.comodi", "No puc processar tipus " + item.getClass());
    }
    return titol;
  }

  public static final String SESSION_ENVIARCORREU_DEST = "__SESSION_ENVIARCORREU_DEST__";

  public static final String SESSION_ENVIARCORREU_MISSATGE = "__SESSION_ENVIARCORREU_MISSATGE__";

  public static final String SESSION_ENVIARCORREU_CALLBACK = "__SESSION_ENVIARCORREU_CALLBACK__";

  @RequestMapping(value = "/enviarcorreu/{itemID}", method = RequestMethod.GET)
  public String enviarCorreu(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long itemID) {

    try {
      {

        String itemNom = isSolicitud() ? "sol·licitud" : "incidència";

        String titol = getTitolItem(itemID);

        String url = getLinkPublic(itemID);

        String msg = "Bon dia:\n"
            + "En el següent enllaç trobarà les accions que s'estan duent a terme en la seva petició titulada: '"
            + titol + "'." + "\n També podrà afegir informació addicional a la seva " + itemNom
            + " a través d'aquest enllaç: " + url + "\n\n" 
            + getPeuCorreu().replace("<br/>", "\n").replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");

        request.getSession().setAttribute(SESSION_ENVIARCORREU_MISSATGE, msg);

      }

      {
        String email = getPersonaContacteEmailByItemID(itemID);
        request.getSession().setAttribute(SESSION_ENVIARCORREU_DEST, email);
      }

      {
        request.getSession().setAttribute(SESSION_ENVIARCORREU_CALLBACK,
            "redirect:" + getContextWeb() + "/veureevents/" + itemID);
      }

      return "redirect:" + EnviarCorreuContacteOperadorController.CONTEXT_WEB + "/new";

    } catch (I18NException i18ne) {

      String msg = "No s'ha pogut enviar el correu: " + I18NUtils.getMessage(i18ne);
      log.error(msg, i18ne);
      HtmlUtils.saveMessageError(request, msg);

      return "redirect:" + getContextWeb() + "/veureevents/" + itemID;

    }

  }

  private String getLinkPublic(Long itemID) {
    String url = Configuracio.getAppUrl() + getPublicContextPath() + "/veureevents/"
        + HibernateFileUtil.encryptFileID(itemID);
    return url;
  }

  public abstract String getPublicContextPath();

  public abstract String getPersonaContacteEmailByItemID(Long itemID) throws I18NException;

  @Override
  public EventFilterForm getEventFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    EventFilterForm eventFilterForm = super.getEventFilterForm(pagina, mav, request);

    Long itemID = (Long) request.getSession()
        .getAttribute(SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);
    if (itemID == null) {
      String itemNom = isSolicitud() ? "solicitud" : "incidència";
      HtmlUtils.saveMessageError(request,
          "S'ha intentat editar o crear un Event però no s'ha definit el " + itemNom
              + " a traves de la sessio " + SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);
      mav.setView(new RedirectView(
          redirectWhenSessionItemIDNotDefined().replace("redirect:", ""), true));
      return eventFilterForm;
    }

    T item = findItemByPrimaryKey(itemID);

    mav.addObject("personaContacte", getPersonaContacteNom(item));
    mav.addObject("personaContacteEmail", getPersonaContacteEmail(item));
    mav.addObject("isEstatal", request.getSession().getAttribute(SESSION_EVENT_IS_ESTATAL));

    mav.addObject("urlToEditItem", getUrlToEditItem(item));

    if (!isClosed(item)) {
      mav.addObject("urlToCloseItem", getUrlToCloseItem(item));
    }
    
    
    mav.addObject("urlMarcarComLlegides", request.getContextPath() + getContextWeb() + "/marcarcomllegida/" + itemID);

    mav.addObject("ID", itemID);
    mav.addObject("tipus", isSolicitud() ? "Sol·licitud" : "Incidència");
    mav.addObject("titol", getTitol(item));
    mav.addObject("iframe", request.getContextPath() + getContextWeb() + "/list");

    mav.addObject("isPublic", isPublic());

    mav.addObject("isSolicitud", isSolicitud());

    mav.addObject("contextweb", getContextWeb());

    String estat = getEstat(item);
    mav.addObject("estat", estat);

    mav.addObject("showOnlyPublic", isPublic());

    if (eventFilterForm.isNou()) {
      eventFilterForm.setOrderBy(DATAEVENT.fullName);
      eventFilterForm.setOrderAsc(true);

      eventFilterForm.addHiddenField(INCIDENCIATECNICAID);
      eventFilterForm.addHiddenField(EVENTID);
      eventFilterForm.addHiddenField(FITXERID);
      eventFilterForm.addHiddenField(NOLLEGIT);

      eventFilterForm.setItemsPerPage(null);
    }

    return eventFilterForm;
  }

  public abstract String getEstat(T item) throws I18NException;

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

    Long itemID = (Long) request.getSession()
        .getAttribute(SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);

    Where w;
    if (itemID == null) {
      w = EventFields.DATAEVENT.isNull();
      String itemNom = isSolicitud() ? "solicitud" : "incidència";
      HtmlUtils.saveMessageError(request,
          "S'ha cridat a veure event d'una sol·licitud però no s'ha registrat en la sessio el "
              + itemNom + " emprant la sessio "
              + SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);

    } else {
      w = isSolicitud() ? EventFields.SOLICITUDID.equal(itemID)
          : EventFields.INCIDENCIATECNICAID.equal(itemID);
    }

    return w;
  }

  @Override
  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

    // Privat - Tramitador
    __tmp.add(new StringKeyValue(String.valueOf(EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT),
        "Comentari Tramitador Privat"));

    // Public - Tramitador
    __tmp.add(new StringKeyValue(String.valueOf(EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC),
        "Comentari Tramitador Públic"));

    // Public - Contacte
    __tmp.add(new StringKeyValue(String.valueOf(EVENT_TIPUS_COMENTARI_CONTACTE),
        "Comentari Contacte (Públic)"));

    // Privat - Tramitador
    __tmp.add(new StringKeyValue(String.valueOf(EVENT_TIPUS_TIQUET_MINHAP),
        "Tiquet MinHAP (Privat)"));
    return __tmp;
  }

  @Override
  public void postValidate(HttpServletRequest request, EventForm eventForm,
      BindingResult result) throws I18NException {

    if (eventForm.getEvent().getTipus() == EVENT_TIPUS_TIQUET_MINHAP) {
      org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace(result,
          get(CAIDIDENTIFICADORCONSULTA), "genapp.validation.required",
          new Object[] { I18NUtils.tradueix(CAIDIDENTIFICADORCONSULTA.fullName) });
      org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace(result,
          get(CAIDNUMEROSEGUIMENT), "genapp.validation.required",
          new Object[] { I18NUtils.tradueix(CAIDNUMEROSEGUIMENT.fullName) });
    }

    boolean inclouFitxer = false;
    if (eventForm.getFitxerID() == null) {
      log.info("eventForm.getFitxerID() == null");
    } else {
      CommonsMultipartFile cmf = eventForm.getFitxerID();
      log.info("cmf.getContentType() == " + cmf.getContentType());
      log.info("cmf.getOriginalFilename() == ]" + cmf.getOriginalFilename() + "[");
      log.info("cmf.getSize() == ]" + cmf.getSize() + "[");
      log.info("cmf.getFileItem() == ]" + cmf.getFileItem() + "[");

    }
    if (inclouFitxer == false) {
      org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace(result,
          get(PERSONA), "genapp.validation.required",
          new Object[] { I18NUtils.tradueix(PERSONA.fullName) });

    }
  }

  @Override
  public EventJPA create(HttpServletRequest request, EventJPA event)
      throws Exception, I18NException, I18NValidationException {
    return (EventJPA) eventLogicaEjb.create(event);
  }
  
  
  
  @RequestMapping(value = "/marcarcomllegida/{itemID}", method = RequestMethod.GET)
  public String marcarEntradesComLlegides(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long itemID) {

    Where wComu;
    //StringField creador;
    if (isSolicitud()) {
       //creador = new EventQueryPath().SOLICITUD().CREADOR();
       wComu = EventFields.SOLICITUDID.equal(itemID);
    } else {
       //creador = new EventQueryPath().INCIDENCIATECNICA().CREADOR();
       wComu = EventFields.INCIDENCIATECNICAID.equal(itemID);
    }
    
    
    List<Event> eventsNoLlegits;
    try {
      eventsNoLlegits = eventLogicaEjb.select(Where.AND(wComu, EventFields.NOLLEGIT.equal(Boolean.TRUE)));
    
    
    for (Event event : eventsNoLlegits) {
      event.setNoLlegit(false);
      eventLogicaEjb.update(event);
    }
    
    } catch (I18NException e) {
      // TODO Auto-generated catch block
     String msg = "Error marcant com a llegits les entrades de " + itemID + ": " + I18NUtils.getMessage(e);
     
     log.error(msg, e);
     
     HtmlUtils.saveMessageError(request, msg);
     
    }
    
    
    return "redirect:" + getContextWeb() + "/veureevents/" + itemID;
  
  }
  

}
