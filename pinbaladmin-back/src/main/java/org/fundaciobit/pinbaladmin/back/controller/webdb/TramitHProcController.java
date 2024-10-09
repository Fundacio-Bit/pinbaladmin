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
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TramitHProcWebValidator;

import org.fundaciobit.pinbaladmin.persistence.TramitHProcJPA;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un TramitHProc
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tramitHProc")
@SessionAttributes(types = { TramitHProcForm.class, TramitHProcFilterForm.class })
public class TramitHProcController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<TramitHProc, java.lang.Long> implements TramitHProcFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitHProcService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitHProcService tramitHProcEjb;

  @Autowired
  private TramitHProcWebValidator tramitHProcWebValidator;

  @Autowired
  protected TramitHProcRefList tramitHProcRefList;

  // References 
  @Autowired
  protected TramitAPersAutRefList tramitAPersAutRefList;

  /**
   * Llistat de totes TramitHProc
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TramitHProcFilterForm ff;
    ff = (TramitHProcFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TramitHProc de forma paginada
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
    llistat(mav, request, getTramitHProcFilterForm(pagina, mav, request));
    return mav;
  }

  public TramitHProcFilterForm getTramitHProcFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TramitHProcFilterForm tramitHProcFilterForm;
    tramitHProcFilterForm = (TramitHProcFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tramitHProcFilterForm == null) {
      tramitHProcFilterForm = new TramitHProcFilterForm();
      tramitHProcFilterForm.setContexte(getContextWeb());
      tramitHProcFilterForm.setEntityNameCode(getEntityNameCode());
      tramitHProcFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tramitHProcFilterForm.setNou(true);
    } else {
      tramitHProcFilterForm.setNou(false);
    }
    tramitHProcFilterForm.setPage(pagina == null ? 1 : pagina);
    return tramitHProcFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TramitHProc de forma paginada
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
      @ModelAttribute TramitHProcFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTramitHProcFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TramitHProc de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TramitHProc> llistat(ModelAndView mav, HttpServletRequest request,
     TramitHProcFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TramitHProc> tramitHProc = processarLlistat(tramitHProcEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tramitHProcItems", tramitHProc);

    mav.addObject("tramitHProcFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tramitHProc, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tramitHProc);

    return tramitHProc;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TramitHProcFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TramitHProc> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field tramitid
    {
      _listSKV = getReferenceListForTramitid(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTramitAPersAutForTramitid(_tmp);
      if (filterForm.getGroupByFields().contains(TRAMITID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TRAMITID, false);
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


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, CADUCITAT);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, PERIODICO);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, AUTOMATIZADO);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TramitHProcFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TramitHProc> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TRAMITHPROC_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TRAMITID, filterForm.getMapOfTramitAPersAutForTramitid());
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TramitHProc
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTramitHProcGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TramitHProcForm tramitHProcForm = getTramitHProcForm(null, false, request, mav);
    mav.addObject("tramitHProcForm" ,tramitHProcForm);
    fillReferencesForForm(tramitHProcForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TramitHProcForm getTramitHProcForm(TramitHProcJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TramitHProcForm tramitHProcForm;
    if(_jpa == null) {
      tramitHProcForm = new TramitHProcForm(new TramitHProcJPA(), true);
    } else {
      tramitHProcForm = new TramitHProcForm(_jpa, false);
      tramitHProcForm.setView(__isView);
    }
    tramitHProcForm.setContexte(getContextWeb());
    tramitHProcForm.setEntityNameCode(getEntityNameCode());
    tramitHProcForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tramitHProcForm;
  }

  public void fillReferencesForForm(TramitHProcForm tramitHProcForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tramitHProcForm.getListOfTramitAPersAutForTramitid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTramitid(request, mav, tramitHProcForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitHProcForm.setListOfTramitAPersAutForTramitid(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitHProcForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, tramitHProcForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitHProcForm.setListOfValuesForTipus(_listSKV);
    }
    
  }

  /**
   * Guardar un nou TramitHProc
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTramitHProcPost(@ModelAttribute TramitHProcForm tramitHProcForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TramitHProcJPA tramitHProc = tramitHProcForm.getTramitHProc();

    try {
      preValidate(request, tramitHProcForm, result);
      getWebValidator().validate(tramitHProcForm, result);
      postValidate(request,tramitHProcForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitHProc = create(request, tramitHProc);
        createMessageSuccess(request, "success.creation", tramitHProc.getProcid());
        tramitHProcForm.setTramitHProc(tramitHProc);
        return getRedirectWhenCreated(request, tramitHProcForm);
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

  @RequestMapping(value = "/view/{procid}", method = RequestMethod.GET)
  public ModelAndView veureTramitHProcGet(@PathVariable("procid") java.lang.Long procid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitHProcGet(procid,
        request, response, true);
  }


  protected ModelAndView editAndViewTramitHProcGet(@PathVariable("procid") java.lang.Long procid,
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
    TramitHProcJPA tramitHProc = findByPrimaryKey(request, procid);

    if (tramitHProc == null) {
      createMessageWarning(request, "error.notfound", procid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, procid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TramitHProcForm tramitHProcForm = getTramitHProcForm(tramitHProc, __isView, request, mav);
      tramitHProcForm.setView(__isView);
      if(__isView) {
        tramitHProcForm.setAllFieldsReadOnly(ALL_TRAMITHPROC_FIELDS);
        tramitHProcForm.setSaveButtonVisible(false);
        tramitHProcForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tramitHProcForm, request, mav);
      mav.addObject("tramitHProcForm", tramitHProcForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TramitHProc existent
   */
  @RequestMapping(value = "/{procid}/edit", method = RequestMethod.GET)
  public ModelAndView editarTramitHProcGet(@PathVariable("procid") java.lang.Long procid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitHProcGet(procid,
        request, response, false);
  }



  /**
   * Editar un TramitHProc existent
   */
  @RequestMapping(value = "/{procid}/edit", method = RequestMethod.POST)
  public String editarTramitHProcPost(@ModelAttribute TramitHProcForm tramitHProcForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TramitHProcJPA tramitHProc = tramitHProcForm.getTramitHProc();

    try {
      preValidate(request, tramitHProcForm, result);
      getWebValidator().validate(tramitHProcForm, result);
      postValidate(request, tramitHProcForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitHProc = update(request, tramitHProc);
        createMessageSuccess(request, "success.modification", tramitHProc.getProcid());
        status.setComplete();
        return getRedirectWhenModified(request, tramitHProcForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tramitHProc.getProcid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tramitHProcForm, __e);
    }

  }


  /**
   * Eliminar un TramitHProc existent
   */
  @RequestMapping(value = "/{procid}/delete")
  public String eliminarTramitHProc(@PathVariable("procid") java.lang.Long procid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TramitHProc tramitHProc = this.findByPrimaryKey(request, procid);
      if (tramitHProc == null) {
        String __msg = createMessageError(request, "error.notfound", procid);
        return getRedirectWhenDelete(request, procid, new Exception(__msg));
      } else {
        delete(request, tramitHProc);
        createMessageSuccess(request, "success.deleted", procid);
        return getRedirectWhenDelete(request, procid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", procid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, procid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TramitHProcFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTramitHProc(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __procid, Throwable e) {
    java.lang.Long procid = (java.lang.Long)__procid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (procid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(procid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tramitHProc.tramitHProc";
  }

  public String getEntityNameCodePlural() {
    return "tramitHProc.tramitHProc.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tramitHProc.procid");
  }

  @InitBinder("tramitHProcFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tramitHProcForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "tramitHProc.procid");
  }

  public TramitHProcWebValidator getWebValidator() {
    return tramitHProcWebValidator;
  }


  public void setWebValidator(TramitHProcWebValidator __val) {
    if (__val != null) {
      this.tramitHProcWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TramitHProc
   */
  @RequestMapping(value = "/{procid}/cancel")
  public String cancelTramitHProc(@PathVariable("procid") java.lang.Long procid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, procid);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de TramitHProc
   */
  @RequestMapping(value = "/cancel")
  public String cancelTramitHProc(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
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


  public List<StringKeyValue> getReferenceListForTramitid(HttpServletRequest request,
       ModelAndView mav, TramitHProcForm tramitHProcForm, Where where)  throws I18NException {
    if (tramitHProcForm.isHiddenField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tramitHProcForm.isReadOnlyField(TRAMITID)) {
      _where = TramitAPersAutFields.TRAMITID.equal(tramitHProcForm.getTramitHProc().getTramitid());
    }
    return getReferenceListForTramitid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTramitid(HttpServletRequest request,
       ModelAndView mav, TramitHProcFilterForm tramitHProcFilterForm,
       List<TramitHProc> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitHProcFilterForm.isHiddenField(TRAMITID)
       && !tramitHProcFilterForm.isGroupByField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TRAMITID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TramitHProc _item : list) {
        _pkList.add(_item.getTramitid());
        }
        _w = TramitAPersAutFields.TRAMITID.in(_pkList);
      }
    return getReferenceListForTramitid(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTramitid(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return tramitAPersAutRefList.getReferenceList(TramitAPersAutFields.TRAMITID, where );
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, TramitHProcForm tramitHProcForm, Where where)  throws I18NException {
    if (tramitHProcForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, TramitHProcFilterForm tramitHProcFilterForm,
       List<TramitHProc> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitHProcFilterForm.isHiddenField(TIPUS)
       && !tramitHProcFilterForm.isGroupByField(TIPUS)
       && !tramitHProcFilterForm.isFilterByField(TIPUS)) {
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
    __tmp.add(new StringKeyValue("4" , "4"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,TramitHProcForm tramitHProcForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TramitHProcForm tramitHProcForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TramitHProcFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TramitHProcFilterForm filterForm,  List<TramitHProc> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TramitHProcForm tramitHProcForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TramitHProcForm tramitHProcForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long procid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long procid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tramitHProcFormWebDB";
  }

  public String getTileList() {
    return "tramitHProcListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TramitHProc_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TramitHProcJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long procid) throws I18NException {
    return (TramitHProcJPA) tramitHProcEjb.findByPrimaryKey(procid);
  }


  public TramitHProcJPA create(HttpServletRequest request, TramitHProcJPA tramitHProc)
    throws I18NException, I18NValidationException {
    return (TramitHProcJPA) tramitHProcEjb.create(tramitHProc);
  }


  public TramitHProcJPA update(HttpServletRequest request, TramitHProcJPA tramitHProc)
    throws I18NException, I18NValidationException {
    return (TramitHProcJPA) tramitHProcEjb.update(tramitHProc);
  }


  public void delete(HttpServletRequest request, TramitHProc tramitHProc) throws I18NException {
    tramitHProcEjb.delete(tramitHProc);
  }

} // Final de Classe

