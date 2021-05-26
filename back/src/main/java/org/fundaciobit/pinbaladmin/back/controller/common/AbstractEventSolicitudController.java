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
import org.fundaciobit.pinbaladmin.back.controller.all.EventSolicitudPublicController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.EventController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.jpa.EventJPA;
import org.fundaciobit.pinbaladmin.logic.EventLogicaLocal;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaLocal;
import org.fundaciobit.pinbaladmin.logic.utils.EmailUtil;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
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
public abstract class AbstractEventSolicitudController extends EventController
    implements Constants {

  public static final String SESSION_EVENT_SOLICITUD_ID = "SESSION_EVENT_SOLICITUD_ID";

  @EJB(mappedName = SolicitudLogicaLocal.JNDI_NAME)
  protected SolicitudLogicaLocal solicitudLogicaEjb;
  
  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatSolicitudLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatSolicitudLocal estatSolicitudEjb;
  
  @EJB(mappedName = EventLogicaLocal.JNDI_NAME)
  protected EventLogicaLocal eventLogicaEjb;

  public abstract boolean isPublic();


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
    return isPublic()?"eventFormOperadorPublic":"eventFormOperador";
  }

  @Override
  public String getTileList() {
    return "eventListOperador";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "EventOperador_FilterForm";
  }

  @Override
  public EventForm getEventForm(EventJPA _jpa, boolean __isView, HttpServletRequest request,
      ModelAndView mav) throws I18NException {

    EventForm eventForm = super.getEventForm(_jpa, __isView, request, mav);

    Long soliID = (Long) request.getSession().getAttribute(SESSION_EVENT_SOLICITUD_ID);
    if (soliID == null) {
      HtmlUtils.saveMessageError(request,
          "S'ha intentat editar o crear un Event però no s'ha definit el solicituID a traves de la sessio "
              + SESSION_EVENT_SOLICITUD_ID);
      mav.setView(new RedirectView("/operador/solicitudlocal/list", true));
      return eventForm;
    }

    Solicitud soli = solicitudLogicaEjb.findByPrimaryKey(soliID);

    eventForm.addHiddenField(TASCATECNICAID);
    eventForm.addReadOnlyField(SOLICITUDID);

    if (eventForm.isNou()) {
      
      if (isPublic()) {
        mav.setViewName("eventFormOperadorPublic");
      } else {
        mav.setViewName("eventFormOperador");
      }
     
      
      eventForm.getEvent().setDataEvent(new Timestamp(System.currentTimeMillis()));
      eventForm.getEvent().setSolicitudID(soliID);
      
      if (isPublic()) {
        eventForm.getEvent().setPersona(soli.getPersonaContacteEmail());      
        eventForm.getEvent().setTipus(EVENT_TIPUS_COMENTARI_CONTACTE);
        eventForm.addHiddenField(TIPUS);
        eventForm.addHiddenField(NOLLEGIT);
        eventForm.getEvent().setNoLlegit(true);
      } else {
         eventForm.getEvent().setPersona(request.getUserPrincipal().getName());      
         eventForm.getEvent().setTipus(EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT);
      }
    } else {
      mav.setViewName("eventFormOperadorPublic");
    }

    if (!isPublic()) {
      mav.addObject("persona_tramitador", request.getUserPrincipal().getName());  
    }

    mav.addObject("persona_contacte", soli.getPersonaContacteEmail());

    eventForm.setAttachedAdditionalJspCode(true);

    return eventForm;
  }

  @RequestMapping(value = "/veureevents/{solicitudStrID}", method = RequestMethod.GET)
  public ModelAndView veureEvents(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String solicitudStrID) throws I18NException {

    Long solicitudID;
    if (isPublic()) {
      solicitudID = HibernateFileUtil.decryptFileID(solicitudStrID);
    } else {
      solicitudID = Long.parseLong(solicitudStrID);
    }

    request.getSession().setAttribute(SESSION_EVENT_SOLICITUD_ID, solicitudID);
    
    
    String tile = isPublic()? "eventListOperadorIframePublic":"eventListOperadorIframe";
    
    ModelAndView mav = new ModelAndView(tile);
    Solicitud soli = solicitudLogicaEjb.findByPrimaryKey(solicitudID);
    mav.addObject("soli", soli);
    
    
    mav.addObject("ID", solicitudID);
    mav.addObject("titol", soli.getProcedimentNom());
    mav.addObject("iframe", request.getContextPath() + getContextWeb() + "/list");
    
    mav.addObject("isPublic", isPublic());
    
    mav.addObject("contextweb", getContextWeb());
    
    return mav;
    
    //if () {

    //   return this.llistat(request, response); //"redirect:" + getContextWeb() + "/list";
    
  }
  
  
  @Override
  public String getRedirectWhenCreated(HttpServletRequest request, EventForm eventForm) {
    Long soliID = (Long) request.getSession().getAttribute(SESSION_EVENT_SOLICITUD_ID);
    
    String soli = isPublic()? HibernateFileUtil.encryptFileID(soliID): String.valueOf(soliID);
    
    
    return "redirect:" + getContextWeb() + "/veureevents/" + soli;   
  }

  @Override
  public String getRedirectWhenModified(HttpServletRequest request, EventForm eventForm, Throwable __e) {
    if (__e == null) {
      return  getRedirectWhenCancel(request, eventForm.getEvent().getEventID());
    } else {
      return  getTileForm();
    }
  }

  @Override
  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long eventID, Throwable __e) {
    return getRedirectWhenCancel(request, eventID);
  }

  @Override
  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long eventID) {
    //Long soliID = (Long) request.getSession().getAttribute(SESSION_EVENT_SOLICITUD_ID);
    
    //String soli = isPublic()? HibernateFileUtil.encryptFileID(soliID): String.valueOf(soliID);
    
    
    return "redirect:" + getContextWeb() + "/list";
  }
  
  
  
  
  @RequestMapping(value = "/enviarcorreu/{solicitudID}", method = RequestMethod.GET)
  public String enviarCorreu(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long solicitudID) throws I18NException {

    String email = solicitudLogicaEjb.executeQueryOne(SolicitudFields.PERSONACONTACTEEMAIL,
        SolicitudFields.SOLICITUDID.equal(solicitudID));
    
    if (email == null) {
      HtmlUtils.saveMessageError(request,
          "El contacte de la solicitud  " + solicitudID + " és buit.");
    } else {

   
    final boolean isHtml = false;

    String[] emails = { email };
    log.error("Dest: " + Arrays.toString(emails));

    for (String address : emails) {
      try {

        String url = Configuracio.getAppUrl() + EventSolicitudPublicController.CONTEXT_PATH +  "/veureevents/"
            + HibernateFileUtil.encryptFileID(solicitudID);

        EmailUtil.postMail("Enllaç la gestió de la seva petició de permisos", "Bones:\n"
            + "En el següent enllaç trobarà les accions que s'estan duent a terme en la seva petició, així com afegir informació addicional a la seva sol·licitud: "
            + url, isHtml,

            Configuracio.getAppEmail(), address);

        HtmlUtils.saveMessageSuccess(request,
            "S'ha enviat un correu a " + address + " amb l'enllaç " + url);

      } catch (Exception e) {
        HtmlUtils.saveMessageError(request,
            "No s'ha pogut enviar el correu a " + address + ": " + e.getMessage());
      }
    }
    }

    return "redirect:" + getContextWeb() + "/veureevents/" + solicitudID;
  }

  @Override
  public EventFilterForm getEventFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    EventFilterForm eventFilterForm = super.getEventFilterForm(pagina, mav, request);

    Long soliID = (Long) request.getSession().getAttribute(SESSION_EVENT_SOLICITUD_ID);
    if (soliID == null) {
      HtmlUtils.saveMessageError(request,
          "S'ha intentat editar o crear un Event però no s'ha definit el solicituID a traves de la sessio "
              + SESSION_EVENT_SOLICITUD_ID);
      mav.setView(new RedirectView("/operador/solicitudlocal/list", true));
      return eventFilterForm;
    }

    Solicitud soli = solicitudLogicaEjb.findByPrimaryKey(soliID);
    mav.addObject("soli", soli);
    
    
    mav.addObject("ID", soliID);
    mav.addObject("titol", soli.getProcedimentNom());
    
    

    String estat = estatSolicitudEjb.executeQueryOne(EstatSolicitudFields.NOM, EstatSolicitudFields.ESTATSOLICITUDID.equal(soli.getEstatID()));
    mav.addObject("estat", estat );
    
    mav.addObject("showOnlyPublic", isPublic());
    

    if (eventFilterForm.isNou()) {
      eventFilterForm.setOrderBy(DATAEVENT.fullName);
      eventFilterForm.setOrderAsc(true);

      eventFilterForm.addHiddenField(TASCATECNICAID);
      eventFilterForm.addHiddenField(EVENTID);
      eventFilterForm.addHiddenField(FITXERID);
      eventFilterForm.addHiddenField(NOLLEGIT);

      eventFilterForm.setItemsPerPage(null);
    }
    
    
    return eventFilterForm;
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

    Long soliID = (Long) request.getSession().getAttribute(SESSION_EVENT_SOLICITUD_ID);

    Where w;
    if (soliID == null) {
      w = EventFields.DATAEVENT.isNull();
      HtmlUtils.saveMessageError(request,
          "S'ha cridat a veur event d'una sol·licitud però no s'ha registrat en la sessio el solicitudID emprant la sessio "
              + SESSION_EVENT_SOLICITUD_ID);

    } else {
      w = EventFields.SOLICITUDID.equal(soliID);
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
      throws Exception,I18NException, I18NValidationException {
      return (EventJPA) eventLogicaEjb.create(event);
    }

}
