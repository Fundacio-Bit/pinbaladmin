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
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TramitFCteTecWebValidator;

import org.fundaciobit.pinbaladmin.persistence.TramitFCteTecJPA;
import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un TramitFCteTec
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tramitFCteTec")
@SessionAttributes(types = { TramitFCteTecForm.class, TramitFCteTecFilterForm.class })
public class TramitFCteTecController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<TramitFCteTec, java.lang.Long> implements TramitFCteTecFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitFCteTecService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitFCteTecService tramitFCteTecEjb;

  @Autowired
  private TramitFCteTecWebValidator tramitFCteTecWebValidator;

  @Autowired
  protected TramitFCteTecRefList tramitFCteTecRefList;

  // References 
  @Autowired
  protected TramitAPersAutRefList tramitAPersAutRefList;

  /**
   * Llistat de totes TramitFCteTec
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TramitFCteTecFilterForm ff;
    ff = (TramitFCteTecFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TramitFCteTec de forma paginada
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
    llistat(mav, request, getTramitFCteTecFilterForm(pagina, mav, request));
    return mav;
  }

  public TramitFCteTecFilterForm getTramitFCteTecFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TramitFCteTecFilterForm tramitFCteTecFilterForm;
    tramitFCteTecFilterForm = (TramitFCteTecFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tramitFCteTecFilterForm == null) {
      tramitFCteTecFilterForm = new TramitFCteTecFilterForm();
      tramitFCteTecFilterForm.setContexte(getContextWeb());
      tramitFCteTecFilterForm.setEntityNameCode(getEntityNameCode());
      tramitFCteTecFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tramitFCteTecFilterForm.setNou(true);
    } else {
      tramitFCteTecFilterForm.setNou(false);
    }
    tramitFCteTecFilterForm.setPage(pagina == null ? 1 : pagina);
    return tramitFCteTecFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TramitFCteTec de forma paginada
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
      @ModelAttribute TramitFCteTecFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTramitFCteTecFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TramitFCteTec de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TramitFCteTec> llistat(ModelAndView mav, HttpServletRequest request,
     TramitFCteTecFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TramitFCteTec> tramitFCteTec = processarLlistat(tramitFCteTecEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tramitFCteTecItems", tramitFCteTec);

    mav.addObject("tramitFCteTecFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tramitFCteTec, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tramitFCteTec);

    return tramitFCteTec;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TramitFCteTecFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TramitFCteTec> list, List<GroupByItem> groupItems) throws I18NException {
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


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TramitFCteTecFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TramitFCteTec> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TRAMITFCTETEC_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TRAMITID, filterForm.getMapOfTramitAPersAutForTramitid());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TramitFCteTec
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTramitFCteTecGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TramitFCteTecForm tramitFCteTecForm = getTramitFCteTecForm(null, false, request, mav);
    mav.addObject("tramitFCteTecForm" ,tramitFCteTecForm);
    fillReferencesForForm(tramitFCteTecForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TramitFCteTecForm getTramitFCteTecForm(TramitFCteTecJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TramitFCteTecForm tramitFCteTecForm;
    if(_jpa == null) {
      tramitFCteTecForm = new TramitFCteTecForm(new TramitFCteTecJPA(), true);
    } else {
      tramitFCteTecForm = new TramitFCteTecForm(_jpa, false);
      tramitFCteTecForm.setView(__isView);
    }
    tramitFCteTecForm.setContexte(getContextWeb());
    tramitFCteTecForm.setEntityNameCode(getEntityNameCode());
    tramitFCteTecForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tramitFCteTecForm;
  }

  public void fillReferencesForForm(TramitFCteTecForm tramitFCteTecForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tramitFCteTecForm.getListOfTramitAPersAutForTramitid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTramitid(request, mav, tramitFCteTecForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitFCteTecForm.setListOfTramitAPersAutForTramitid(_listSKV);
    }
    
  }

  /**
   * Guardar un nou TramitFCteTec
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTramitFCteTecPost(@ModelAttribute TramitFCteTecForm tramitFCteTecForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TramitFCteTecJPA tramitFCteTec = tramitFCteTecForm.getTramitFCteTec();

    try {
      preValidate(request, tramitFCteTecForm, result);
      getWebValidator().validate(tramitFCteTecForm, result);
      postValidate(request,tramitFCteTecForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitFCteTec = create(request, tramitFCteTec);
        createMessageSuccess(request, "success.creation", tramitFCteTec.getCtetecid());
        tramitFCteTecForm.setTramitFCteTec(tramitFCteTec);
        return getRedirectWhenCreated(request, tramitFCteTecForm);
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

  @RequestMapping(value = "/view/{ctetecid}", method = RequestMethod.GET)
  public ModelAndView veureTramitFCteTecGet(@PathVariable("ctetecid") java.lang.Long ctetecid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitFCteTecGet(ctetecid,
        request, response, true);
  }


  protected ModelAndView editAndViewTramitFCteTecGet(@PathVariable("ctetecid") java.lang.Long ctetecid,
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
    TramitFCteTecJPA tramitFCteTec = findByPrimaryKey(request, ctetecid);

    if (tramitFCteTec == null) {
      createMessageWarning(request, "error.notfound", ctetecid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, ctetecid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TramitFCteTecForm tramitFCteTecForm = getTramitFCteTecForm(tramitFCteTec, __isView, request, mav);
      tramitFCteTecForm.setView(__isView);
      if(__isView) {
        tramitFCteTecForm.setAllFieldsReadOnly(ALL_TRAMITFCTETEC_FIELDS);
        tramitFCteTecForm.setSaveButtonVisible(false);
        tramitFCteTecForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tramitFCteTecForm, request, mav);
      mav.addObject("tramitFCteTecForm", tramitFCteTecForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TramitFCteTec existent
   */
  @RequestMapping(value = "/{ctetecid}/edit", method = RequestMethod.GET)
  public ModelAndView editarTramitFCteTecGet(@PathVariable("ctetecid") java.lang.Long ctetecid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitFCteTecGet(ctetecid,
        request, response, false);
  }



  /**
   * Editar un TramitFCteTec existent
   */
  @RequestMapping(value = "/{ctetecid}/edit", method = RequestMethod.POST)
  public String editarTramitFCteTecPost(@ModelAttribute TramitFCteTecForm tramitFCteTecForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TramitFCteTecJPA tramitFCteTec = tramitFCteTecForm.getTramitFCteTec();

    try {
      preValidate(request, tramitFCteTecForm, result);
      getWebValidator().validate(tramitFCteTecForm, result);
      postValidate(request, tramitFCteTecForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitFCteTec = update(request, tramitFCteTec);
        createMessageSuccess(request, "success.modification", tramitFCteTec.getCtetecid());
        status.setComplete();
        return getRedirectWhenModified(request, tramitFCteTecForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tramitFCteTec.getCtetecid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tramitFCteTecForm, __e);
    }

  }


  /**
   * Eliminar un TramitFCteTec existent
   */
  @RequestMapping(value = "/{ctetecid}/delete")
  public String eliminarTramitFCteTec(@PathVariable("ctetecid") java.lang.Long ctetecid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TramitFCteTec tramitFCteTec = this.findByPrimaryKey(request, ctetecid);
      if (tramitFCteTec == null) {
        String __msg = createMessageError(request, "error.notfound", ctetecid);
        return getRedirectWhenDelete(request, ctetecid, new Exception(__msg));
      } else {
        delete(request, tramitFCteTec);
        createMessageSuccess(request, "success.deleted", ctetecid);
        return getRedirectWhenDelete(request, ctetecid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", ctetecid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, ctetecid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TramitFCteTecFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTramitFCteTec(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __ctetecid, Throwable e) {
    java.lang.Long ctetecid = (java.lang.Long)__ctetecid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (ctetecid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(ctetecid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tramitFCteTec.tramitFCteTec";
  }

  public String getEntityNameCodePlural() {
    return "tramitFCteTec.tramitFCteTec.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tramitFCteTec.ctetecid");
  }

  @InitBinder("tramitFCteTecFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tramitFCteTecForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "tramitFCteTec.ctetecid");
  }

  public TramitFCteTecWebValidator getWebValidator() {
    return tramitFCteTecWebValidator;
  }


  public void setWebValidator(TramitFCteTecWebValidator __val) {
    if (__val != null) {
      this.tramitFCteTecWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TramitFCteTec
   */
  @RequestMapping(value = "/{ctetecid}/cancel")
  public String cancelTramitFCteTec(@PathVariable("ctetecid") java.lang.Long ctetecid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, ctetecid);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de TramitFCteTec
   */
  @RequestMapping(value = "/cancel")
  public String cancelTramitFCteTec(HttpServletRequest request,HttpServletResponse response) {
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
       ModelAndView mav, TramitFCteTecForm tramitFCteTecForm, Where where)  throws I18NException {
    if (tramitFCteTecForm.isHiddenField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tramitFCteTecForm.isReadOnlyField(TRAMITID)) {
      _where = TramitAPersAutFields.TRAMITID.equal(tramitFCteTecForm.getTramitFCteTec().getTramitid());
    }
    return getReferenceListForTramitid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTramitid(HttpServletRequest request,
       ModelAndView mav, TramitFCteTecFilterForm tramitFCteTecFilterForm,
       List<TramitFCteTec> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitFCteTecFilterForm.isHiddenField(TRAMITID)
       && !tramitFCteTecFilterForm.isGroupByField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TRAMITID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TramitFCteTec _item : list) {
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,TramitFCteTecForm tramitFCteTecForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TramitFCteTecForm tramitFCteTecForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TramitFCteTecFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TramitFCteTecFilterForm filterForm,  List<TramitFCteTec> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TramitFCteTecForm tramitFCteTecForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TramitFCteTecForm tramitFCteTecForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long ctetecid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long ctetecid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tramitFCteTecFormWebDB";
  }

  public String getTileList() {
    return "tramitFCteTecListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TramitFCteTec_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TramitFCteTecJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long ctetecid) throws I18NException {
    return (TramitFCteTecJPA) tramitFCteTecEjb.findByPrimaryKey(ctetecid);
  }


  public TramitFCteTecJPA create(HttpServletRequest request, TramitFCteTecJPA tramitFCteTec)
    throws I18NException, I18NValidationException {
    return (TramitFCteTecJPA) tramitFCteTecEjb.create(tramitFCteTec);
  }


  public TramitFCteTecJPA update(HttpServletRequest request, TramitFCteTecJPA tramitFCteTec)
    throws I18NException, I18NValidationException {
    return (TramitFCteTecJPA) tramitFCteTecEjb.update(tramitFCteTec);
  }


  public void delete(HttpServletRequest request, TramitFCteTec tramitFCteTec) throws I18NException {
    tramitFCteTecEjb.delete(tramitFCteTec);
  }

} // Final de Classe

