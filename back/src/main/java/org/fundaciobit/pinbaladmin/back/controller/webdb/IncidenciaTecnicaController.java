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
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.fundaciobit.pinbaladmin.back.form.webdb.*;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.IncidenciaTecnicaWebValidator;

import org.fundaciobit.pinbaladmin.jpa.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un IncidenciaTecnica
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/incidenciaTecnica")
@SessionAttributes(types = { IncidenciaTecnicaForm.class, IncidenciaTecnicaFilterForm.class })
public class IncidenciaTecnicaController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<IncidenciaTecnica, java.lang.Long> implements IncidenciaTecnicaFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaLocal incidenciaTecnicaEjb;

  @Autowired
  private IncidenciaTecnicaWebValidator incidenciaTecnicaWebValidator;

  @Autowired
  protected IncidenciaTecnicaRefList incidenciaTecnicaRefList;

  /**
   * Llistat de totes IncidenciaTecnica
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    IncidenciaTecnicaFilterForm ff;
    ff = (IncidenciaTecnicaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar IncidenciaTecnica de forma paginada
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
    llistat(mav, request, getIncidenciaTecnicaFilterForm(pagina, mav, request));
    return mav;
  }

  public IncidenciaTecnicaFilterForm getIncidenciaTecnicaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    IncidenciaTecnicaFilterForm incidenciaTecnicaFilterForm;
    incidenciaTecnicaFilterForm = (IncidenciaTecnicaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(incidenciaTecnicaFilterForm == null) {
      incidenciaTecnicaFilterForm = new IncidenciaTecnicaFilterForm();
      incidenciaTecnicaFilterForm.setContexte(getContextWeb());
      incidenciaTecnicaFilterForm.setEntityNameCode(getEntityNameCode());
      incidenciaTecnicaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      incidenciaTecnicaFilterForm.setNou(true);
    } else {
      incidenciaTecnicaFilterForm.setNou(false);
    }
    incidenciaTecnicaFilterForm.setPage(pagina == null ? 1 : pagina);
    return incidenciaTecnicaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar IncidenciaTecnica de forma paginada
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
      @ModelAttribute IncidenciaTecnicaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getIncidenciaTecnicaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de IncidenciaTecnica de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<IncidenciaTecnica> llistat(ModelAndView mav, HttpServletRequest request,
     IncidenciaTecnicaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<IncidenciaTecnica> incidenciaTecnica = processarLlistat(incidenciaTecnicaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("incidenciaTecnicaItems", incidenciaTecnica);

    mav.addObject("incidenciaTecnicaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, incidenciaTecnica, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, incidenciaTecnica);

    return incidenciaTecnica;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(IncidenciaTecnicaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<IncidenciaTecnica> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field estat
    {
      _listSKV = getReferenceListForEstat(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEstat(_tmp);
      if (filterForm.getGroupByFields().contains(ESTAT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTAT, false);
      };
    }

    // Field creador
    {
      _listSKV = getReferenceListForCreador(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForCreador(_tmp);
      if (filterForm.getGroupByFields().contains(CREADOR)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CREADOR, false);
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


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    IncidenciaTecnicaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<IncidenciaTecnica> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_INCIDENCIATECNICA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ESTAT, filterForm.getMapOfValuesForEstat());
    __mapping.put(CREADOR, filterForm.getMapOfValuesForCreador());
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou IncidenciaTecnica
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearIncidenciaTecnicaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    IncidenciaTecnicaForm incidenciaTecnicaForm = getIncidenciaTecnicaForm(null, false, request, mav);
    mav.addObject("incidenciaTecnicaForm" ,incidenciaTecnicaForm);
    fillReferencesForForm(incidenciaTecnicaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public IncidenciaTecnicaForm getIncidenciaTecnicaForm(IncidenciaTecnicaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    IncidenciaTecnicaForm incidenciaTecnicaForm;
    if(_jpa == null) {
      incidenciaTecnicaForm = new IncidenciaTecnicaForm(new IncidenciaTecnicaJPA(), true);
    } else {
      incidenciaTecnicaForm = new IncidenciaTecnicaForm(_jpa, false);
      incidenciaTecnicaForm.setView(__isView);
    }
    incidenciaTecnicaForm.setContexte(getContextWeb());
    incidenciaTecnicaForm.setEntityNameCode(getEntityNameCode());
    incidenciaTecnicaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return incidenciaTecnicaForm;
  }

  public void fillReferencesForForm(IncidenciaTecnicaForm incidenciaTecnicaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (incidenciaTecnicaForm.getListOfValuesForEstat() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstat(request, mav, incidenciaTecnicaForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      incidenciaTecnicaForm.setListOfValuesForEstat(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (incidenciaTecnicaForm.getListOfValuesForCreador() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForCreador(request, mav, incidenciaTecnicaForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      incidenciaTecnicaForm.setListOfValuesForCreador(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (incidenciaTecnicaForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, incidenciaTecnicaForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      incidenciaTecnicaForm.setListOfValuesForTipus(_listSKV);
    }
    
  }

  /**
   * Guardar un nou IncidenciaTecnica
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearIncidenciaTecnicaPost(@ModelAttribute IncidenciaTecnicaForm incidenciaTecnicaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    IncidenciaTecnicaJPA incidenciaTecnica = incidenciaTecnicaForm.getIncidenciaTecnica();

    try {
      preValidate(request, incidenciaTecnicaForm, result);
      getWebValidator().validate(incidenciaTecnicaForm, result);
      postValidate(request,incidenciaTecnicaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        incidenciaTecnica = create(request, incidenciaTecnica);
        createMessageSuccess(request, "success.creation", incidenciaTecnica.getIncidenciaTecnicaID());
        incidenciaTecnicaForm.setIncidenciaTecnica(incidenciaTecnica);
        return getRedirectWhenCreated(request, incidenciaTecnicaForm);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{incidenciaTecnicaID}", method = RequestMethod.GET)
  public ModelAndView veureIncidenciaTecnicaGet(@PathVariable("incidenciaTecnicaID") java.lang.Long incidenciaTecnicaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewIncidenciaTecnicaGet(incidenciaTecnicaID,
        request, response, true);
  }


  protected ModelAndView editAndViewIncidenciaTecnicaGet(@PathVariable("incidenciaTecnicaID") java.lang.Long incidenciaTecnicaID,
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
    IncidenciaTecnicaJPA incidenciaTecnica = findByPrimaryKey(request, incidenciaTecnicaID);

    if (incidenciaTecnica == null) {
      createMessageWarning(request, "error.notfound", incidenciaTecnicaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, incidenciaTecnicaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      IncidenciaTecnicaForm incidenciaTecnicaForm = getIncidenciaTecnicaForm(incidenciaTecnica, __isView, request, mav);
      incidenciaTecnicaForm.setView(__isView);
      if(__isView) {
        incidenciaTecnicaForm.setAllFieldsReadOnly(ALL_INCIDENCIATECNICA_FIELDS);
        incidenciaTecnicaForm.setSaveButtonVisible(false);
        incidenciaTecnicaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(incidenciaTecnicaForm, request, mav);
      mav.addObject("incidenciaTecnicaForm", incidenciaTecnicaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un IncidenciaTecnica existent
   */
  @RequestMapping(value = "/{incidenciaTecnicaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarIncidenciaTecnicaGet(@PathVariable("incidenciaTecnicaID") java.lang.Long incidenciaTecnicaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewIncidenciaTecnicaGet(incidenciaTecnicaID,
        request, response, false);
  }



  /**
   * Editar un IncidenciaTecnica existent
   */
  @RequestMapping(value = "/{incidenciaTecnicaID}/edit", method = RequestMethod.POST)
  public String editarIncidenciaTecnicaPost(@ModelAttribute @Valid IncidenciaTecnicaForm incidenciaTecnicaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    IncidenciaTecnicaJPA incidenciaTecnica = incidenciaTecnicaForm.getIncidenciaTecnica();

    try {
      preValidate(request, incidenciaTecnicaForm, result);
      getWebValidator().validate(incidenciaTecnica, result);
      postValidate(request, incidenciaTecnicaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        incidenciaTecnica = update(request, incidenciaTecnica);
        createMessageSuccess(request, "success.modification", incidenciaTecnica.getIncidenciaTecnicaID());
        status.setComplete();
        return getRedirectWhenModified(request, incidenciaTecnicaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          incidenciaTecnica.getIncidenciaTecnicaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, incidenciaTecnicaForm, __e);
    }

  }


  /**
   * Eliminar un IncidenciaTecnica existent
   */
  @RequestMapping(value = "/{incidenciaTecnicaID}/delete")
  public String eliminarIncidenciaTecnica(@PathVariable("incidenciaTecnicaID") java.lang.Long incidenciaTecnicaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      IncidenciaTecnica incidenciaTecnica = findByPrimaryKey(request, incidenciaTecnicaID);
      if (incidenciaTecnica == null) {
        String __msg =createMessageError(request, "error.notfound", incidenciaTecnicaID);
        return getRedirectWhenDelete(request, incidenciaTecnicaID, new Exception(__msg));
      } else {
        delete(request, incidenciaTecnica);
        createMessageSuccess(request, "success.deleted", incidenciaTecnicaID);
        return getRedirectWhenDelete(request, incidenciaTecnicaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", incidenciaTecnicaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, incidenciaTecnicaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute IncidenciaTecnicaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarIncidenciaTecnica(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return new java.lang.Long(value);
}

  @Override
  public String[] getArgumentsMissatge(Object __incidenciaTecnicaID, Throwable e) {
    java.lang.Long incidenciaTecnicaID = (java.lang.Long)__incidenciaTecnicaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (incidenciaTecnicaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(incidenciaTecnicaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "incidenciaTecnica.incidenciaTecnica";
  }

  public String getEntityNameCodePlural() {
    return "incidenciaTecnica.incidenciaTecnica.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("incidenciaTecnica.incidenciaTecnicaID");
  }

  @InitBinder("incidenciaTecnicaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("incidenciaTecnicaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    initDisallowedFields(binder, "incidenciaTecnica.incidenciaTecnicaID");
  }

  public IncidenciaTecnicaWebValidator getWebValidator() {
    return incidenciaTecnicaWebValidator;
  }


  public void setWebValidator(IncidenciaTecnicaWebValidator __val) {
    if (__val != null) {
      this.incidenciaTecnicaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de IncidenciaTecnica
   */
  @RequestMapping(value = "/{incidenciaTecnicaID}/cancel")
  public String cancelIncidenciaTecnica(@PathVariable("incidenciaTecnicaID") java.lang.Long incidenciaTecnicaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, incidenciaTecnicaID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
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


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, IncidenciaTecnicaForm incidenciaTecnicaForm, Where where)  throws I18NException {
    if (incidenciaTecnicaForm.isHiddenField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    return getReferenceListForEstat(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, IncidenciaTecnicaFilterForm incidenciaTecnicaFilterForm,
       List<IncidenciaTecnica> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (incidenciaTecnicaFilterForm.isHiddenField(ESTAT)
      && !incidenciaTecnicaFilterForm.isGroupByField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    return getReferenceListForEstat(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForCreador(HttpServletRequest request,
       ModelAndView mav, IncidenciaTecnicaForm incidenciaTecnicaForm, Where where)  throws I18NException {
    if (incidenciaTecnicaForm.isHiddenField(CREADOR)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    return getReferenceListForCreador(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForCreador(HttpServletRequest request,
       ModelAndView mav, IncidenciaTecnicaFilterForm incidenciaTecnicaFilterForm,
       List<IncidenciaTecnica> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (incidenciaTecnicaFilterForm.isHiddenField(CREADOR)
      && !incidenciaTecnicaFilterForm.isGroupByField(CREADOR)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    return getReferenceListForCreador(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForCreador(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("anadal" , "anadal"));
    __tmp.add(new StringKeyValue("pvico" , "pvico"));
    __tmp.add(new StringKeyValue("atrobat" , "atrobat"));
    __tmp.add(new StringKeyValue("gdeignacio" , "gdeignacio"));
    __tmp.add(new StringKeyValue("mgonzalez" , "mgonzalez"));
    __tmp.add(new StringKeyValue("mcapo" , "mcapo"));
    __tmp.add(new StringKeyValue("fsalas" , "fsalas"));
    __tmp.add(new StringKeyValue("ptrias" , "ptrias"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, IncidenciaTecnicaForm incidenciaTecnicaForm, Where where)  throws I18NException {
    if (incidenciaTecnicaForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, IncidenciaTecnicaFilterForm incidenciaTecnicaFilterForm,
       List<IncidenciaTecnica> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (incidenciaTecnicaFilterForm.isHiddenField(TIPUS)
      && !incidenciaTecnicaFilterForm.isGroupByField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
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
    __tmp.add(new StringKeyValue("4" , "4"));
    return __tmp;
  }


  public void preValidate(HttpServletRequest request,IncidenciaTecnicaForm incidenciaTecnicaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,IncidenciaTecnicaForm incidenciaTecnicaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, IncidenciaTecnicaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, IncidenciaTecnicaFilterForm filterForm,  List<IncidenciaTecnica> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, IncidenciaTecnicaForm incidenciaTecnicaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, IncidenciaTecnicaForm incidenciaTecnicaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long incidenciaTecnicaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long incidenciaTecnicaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "incidenciaTecnicaFormWebDB";
  }

  public String getTileList() {
    return "incidenciaTecnicaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "IncidenciaTecnicaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public IncidenciaTecnicaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long incidenciaTecnicaID) throws I18NException {
    return (IncidenciaTecnicaJPA) incidenciaTecnicaEjb.findByPrimaryKey(incidenciaTecnicaID);
  }


  public IncidenciaTecnicaJPA create(HttpServletRequest request, IncidenciaTecnicaJPA incidenciaTecnica)
    throws Exception,I18NException, I18NValidationException {
    return (IncidenciaTecnicaJPA) incidenciaTecnicaEjb.create(incidenciaTecnica);
  }


  public IncidenciaTecnicaJPA update(HttpServletRequest request, IncidenciaTecnicaJPA incidenciaTecnica)
    throws Exception,I18NException, I18NValidationException {
    return (IncidenciaTecnicaJPA) incidenciaTecnicaEjb.update(incidenciaTecnica);
  }


  public void delete(HttpServletRequest request, IncidenciaTecnica incidenciaTecnica) throws Exception,I18NException {
    incidenciaTecnicaEjb.delete(incidenciaTecnica);
  }

} // Final de Classe

