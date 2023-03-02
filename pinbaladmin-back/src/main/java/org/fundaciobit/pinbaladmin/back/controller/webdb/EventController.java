package org.fundaciobit.pinbaladmin.back.controller.webdb;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.fundaciobit.pinbaladmin.back.form.webdb.*;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.EventWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Event
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/event")
@SessionAttributes(types = { EventForm.class, EventFilterForm.class })
public class EventController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<Event, java.lang.Long, EventForm> implements EventFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EventService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EventService eventEjb;

  @Autowired
  private EventWebValidator eventWebValidator;

  @Autowired
  protected EventRefList eventRefList;

  // References 
  @Autowired
  protected SolicitudRefList solicitudRefList;

  // References 
  @Autowired
  protected IncidenciaTecnicaRefList incidenciaTecnicaRefList;

  /**
   * Llistat de totes Event
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EventFilterForm ff;
    ff = (EventFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Event de forma paginada
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
  public ModelAndView llistatPaginat(HttpServletRequest request,
    HttpServletResponse response, @PathVariable Integer pagina)
      throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileList());
    llistat(mav, request, getEventFilterForm(pagina, mav, request));
    return mav;
  }

  public EventFilterForm getEventFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EventFilterForm eventFilterForm;
    eventFilterForm = (EventFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(eventFilterForm == null) {
      eventFilterForm = new EventFilterForm();
      eventFilterForm.setContexte(getContextWeb());
      eventFilterForm.setEntityNameCode(getEntityNameCode());
      eventFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      eventFilterForm.setNou(true);
    } else {
      eventFilterForm.setNou(false);
    }
    eventFilterForm.setPage(pagina == null ? 1 : pagina);
    return eventFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Event de forma paginada
   * 
   * @param request
   * @param pagina
   * @param filterForm
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.POST)
  public ModelAndView llistatPaginat(HttpServletRequest request,
      HttpServletResponse response,@PathVariable Integer pagina,
      @ModelAttribute EventFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEventFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Event de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Event> llistat(ModelAndView mav, HttpServletRequest request,
     EventFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Event> event = processarLlistat(eventEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("eventItems", event);

    mav.addObject("eventFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, event, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, event);

    return event;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EventFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Event> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field solicitudID
    {
      _listSKV = getReferenceListForSolicitudID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfSolicitudForSolicitudID(_tmp);
      if (filterForm.getGroupByFields().contains(SOLICITUDID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SOLICITUDID, false);
      };
    }

    // Field incidenciaTecnicaID
    {
      _listSKV = getReferenceListForIncidenciaTecnicaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfIncidenciaTecnicaForIncidenciaTecnicaID(_tmp);
      if (filterForm.getGroupByFields().contains(INCIDENCIATECNICAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, INCIDENCIATECNICAID, false);
      };
    }

    // Field tipus
    {
      _listSKV = getReferenceListForTipus(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipus(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUS, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, NOLLEGIT);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EventFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Event> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_EVENT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(SOLICITUDID, filterForm.getMapOfSolicitudForSolicitudID());
    __mapping.put(INCIDENCIATECNICAID, filterForm.getMapOfIncidenciaTecnicaForIncidenciaTecnicaID());
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Event
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEventGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EventForm eventForm = getEventForm(null, false, request, mav);
    mav.addObject("eventForm" ,eventForm);
    fillReferencesForForm(eventForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EventForm getEventForm(EventJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EventForm eventForm;
    if(_jpa == null) {
      eventForm = new EventForm(new EventJPA(), true);
    } else {
      eventForm = new EventForm(_jpa, false);
      eventForm.setView(__isView);
    }
    eventForm.setContexte(getContextWeb());
    eventForm.setEntityNameCode(getEntityNameCode());
    eventForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return eventForm;
  }

  public void fillReferencesForForm(EventForm eventForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (eventForm.getListOfSolicitudForSolicitudID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSolicitudID(request, mav, eventForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      eventForm.setListOfSolicitudForSolicitudID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (eventForm.getListOfIncidenciaTecnicaForIncidenciaTecnicaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForIncidenciaTecnicaID(request, mav, eventForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      eventForm.setListOfIncidenciaTecnicaForIncidenciaTecnicaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (eventForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, eventForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      eventForm.setListOfValuesForTipus(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Event
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEventPost(@ModelAttribute EventForm eventForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EventJPA event = eventForm.getEvent();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, event, eventForm); // FILE
      preValidate(request, eventForm, result);
      getWebValidator().validate(eventForm, result);
      postValidate(request,eventForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        event = create(request, event);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", event.getEventID());
        eventForm.setEvent(event);
        return getRedirectWhenCreated(request, eventForm);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{eventID}", method = RequestMethod.GET)
  public ModelAndView veureEventGet(@PathVariable("eventID") java.lang.Long eventID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEventGet(eventID,
        request, response, true);
  }


  protected ModelAndView editAndViewEventGet(@PathVariable("eventID") java.lang.Long eventID,
      HttpServletRequest request,
      HttpServletResponse response, boolean __isView) throws I18NException {
    if((!__isView) && !isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    } else {
      if(__isView && !isActiveFormView()) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
      }
    }
    EventJPA event = findByPrimaryKey(request, eventID);

    if (event == null) {
      createMessageWarning(request, "error.notfound", eventID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, eventID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EventForm eventForm = getEventForm(event, __isView, request, mav);
      eventForm.setView(__isView);
      if(__isView) {
        eventForm.setAllFieldsReadOnly(ALL_EVENT_FIELDS);
        eventForm.setSaveButtonVisible(false);
        eventForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(eventForm, request, mav);
      mav.addObject("eventForm", eventForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Event existent
   */
  @RequestMapping(value = "/{eventID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEventGet(@PathVariable("eventID") java.lang.Long eventID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEventGet(eventID,
        request, response, false);
  }



  /**
   * Editar un Event existent
   */
  @RequestMapping(value = "/{eventID}/edit", method = RequestMethod.POST)
  public String editarEventPost(@ModelAttribute EventForm eventForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EventJPA event = eventForm.getEvent();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, event, eventForm); // FILE
      preValidate(request, eventForm, result);
      getWebValidator().validate(eventForm, result);
      postValidate(request, eventForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        event = update(request, event);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", event.getEventID());
        status.setComplete();
        return getRedirectWhenModified(request, eventForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          event.getEventID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, eventForm, __e);
    }

  }


  /**
   * Eliminar un Event existent
   */
  @RequestMapping(value = "/{eventID}/delete")
  public String eliminarEvent(@PathVariable("eventID") java.lang.Long eventID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Event event = eventEjb.findByPrimaryKey(eventID);
      if (event == null) {
        String __msg =createMessageError(request, "error.notfound", eventID);
        return getRedirectWhenDelete(request, eventID, new Exception(__msg));
      } else {
        delete(request, event);
        createMessageSuccess(request, "success.deleted", eventID);
        return getRedirectWhenDelete(request, eventID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", eventID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, eventID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EventFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEvent(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return java.lang.Long.parseLong(value, 10);
}

  @Override
  public String[] getArgumentsMissatge(Object __eventID, Throwable e) {
    java.lang.Long eventID = (java.lang.Long)__eventID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (eventID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(eventID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "event.event";
  }

  public String getEntityNameCodePlural() {
    return "event.event.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("event.eventID");
  }

  @InitBinder("eventFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("eventForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "event.eventID");
  }

  public EventWebValidator getWebValidator() {
    return eventWebValidator;
  }


  public void setWebValidator(EventWebValidator __val) {
    if (__val != null) {
      this.eventWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Event
   */
  @RequestMapping(value = "/{eventID}/cancel")
  public String cancelEvent(@PathVariable("eventID") java.lang.Long eventID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, eventID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Event event,
      EventForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerID(), form.isFitxerIDDelete(),
        form.isNou()? null : event.getFitxer());
    ((EventJPA)event).setFitxer(f);
    if (f != null) { 
      event.setFitxerID(f.getFitxerID());
    } else {
      event.setFitxerID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(Event event) {
    deleteFile(event.getFitxerID());
  }
  // Mètodes a sobreescriure 

  public boolean isActiveList() {
    return true;
  }


  public boolean isActiveFormNew() {
    return true;
  }


  public boolean isActiveFormEdit() {
    return true;
  }


  public boolean isActiveDelete() {
    return true;
  }


  public boolean isActiveFormView() {
    return isActiveFormEdit();
  }


  public List<StringKeyValue> getReferenceListForSolicitudID(HttpServletRequest request,
       ModelAndView mav, EventForm eventForm, Where where)  throws I18NException {
    if (eventForm.isHiddenField(SOLICITUDID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (eventForm.isReadOnlyField(SOLICITUDID)) {
      _where = SolicitudFields.SOLICITUDID.equal(eventForm.getEvent().getSolicitudID());
    }
    return getReferenceListForSolicitudID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSolicitudID(HttpServletRequest request,
       ModelAndView mav, EventFilterForm eventFilterForm,
       List<Event> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (eventFilterForm.isHiddenField(SOLICITUDID)
      && !eventFilterForm.isGroupByField(SOLICITUDID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SOLICITUDID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Event _item : list) {
        if(_item.getSolicitudID() == null) { continue; };
        _pkList.add(_item.getSolicitudID());
        }
        _w = SolicitudFields.SOLICITUDID.in(_pkList);
      }
    return getReferenceListForSolicitudID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSolicitudID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return solicitudRefList.getReferenceList(SolicitudFields.SOLICITUDID, where );
  }


  public List<StringKeyValue> getReferenceListForIncidenciaTecnicaID(HttpServletRequest request,
       ModelAndView mav, EventForm eventForm, Where where)  throws I18NException {
    if (eventForm.isHiddenField(INCIDENCIATECNICAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (eventForm.isReadOnlyField(INCIDENCIATECNICAID)) {
      _where = IncidenciaTecnicaFields.INCIDENCIATECNICAID.equal(eventForm.getEvent().getIncidenciaTecnicaID());
    }
    return getReferenceListForIncidenciaTecnicaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForIncidenciaTecnicaID(HttpServletRequest request,
       ModelAndView mav, EventFilterForm eventFilterForm,
       List<Event> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (eventFilterForm.isHiddenField(INCIDENCIATECNICAID)
      && !eventFilterForm.isGroupByField(INCIDENCIATECNICAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(INCIDENCIATECNICAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Event _item : list) {
        if(_item.getIncidenciaTecnicaID() == null) { continue; };
        _pkList.add(_item.getIncidenciaTecnicaID());
        }
        _w = IncidenciaTecnicaFields.INCIDENCIATECNICAID.in(_pkList);
      }
    return getReferenceListForIncidenciaTecnicaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForIncidenciaTecnicaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return incidenciaTecnicaRefList.getReferenceList(IncidenciaTecnicaFields.INCIDENCIATECNICAID, where );
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, EventForm eventForm, Where where)  throws I18NException {
    if (eventForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, EventFilterForm eventFilterForm,
       List<Event> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (eventFilterForm.isHiddenField(TIPUS)
      && !eventFilterForm.isGroupByField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipus(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,EventForm eventForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EventForm eventForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EventFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EventFilterForm filterForm,  List<Event> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EventForm eventForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EventForm eventForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long eventID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long eventID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "eventFormWebDB";
  }

  public String getTileList() {
    return "eventListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "EventWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EventJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long eventID) throws I18NException {
    return (EventJPA) eventEjb.findByPrimaryKey(eventID);
  }


  public EventJPA create(HttpServletRequest request, EventJPA event)
    throws I18NException, I18NValidationException {
    return (EventJPA) eventEjb.create(event);
  }


  public EventJPA update(HttpServletRequest request, EventJPA event)
    throws I18NException, I18NValidationException {
    return (EventJPA) eventEjb.update(event);
  }


  public void delete(HttpServletRequest request, Event event) throws I18NException {
    eventEjb.delete(event);
  }

} // Final de Classe

