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
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TramitGDadesTitWebValidator;

import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitJPA;
import org.fundaciobit.pinbaladmin.model.entity.TramitGDadesTit;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un TramitGDadesTit
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tramitGDadesTit")
@SessionAttributes(types = { TramitGDadesTitForm.class, TramitGDadesTitFilterForm.class })
public class TramitGDadesTitController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<TramitGDadesTit, java.lang.Long> implements TramitGDadesTitFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitGDadesTitService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitGDadesTitService tramitGDadesTitEjb;

  @Autowired
  private TramitGDadesTitWebValidator tramitGDadesTitWebValidator;

  @Autowired
  protected TramitGDadesTitRefList tramitGDadesTitRefList;

  // References 
  @Autowired
  protected TramitAPersAutRefList tramitAPersAutRefList;

  /**
   * Llistat de totes TramitGDadesTit
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TramitGDadesTitFilterForm ff;
    ff = (TramitGDadesTitFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TramitGDadesTit de forma paginada
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
    llistat(mav, request, getTramitGDadesTitFilterForm(pagina, mav, request));
    return mav;
  }

  public TramitGDadesTitFilterForm getTramitGDadesTitFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TramitGDadesTitFilterForm tramitGDadesTitFilterForm;
    tramitGDadesTitFilterForm = (TramitGDadesTitFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tramitGDadesTitFilterForm == null) {
      tramitGDadesTitFilterForm = new TramitGDadesTitFilterForm();
      tramitGDadesTitFilterForm.setContexte(getContextWeb());
      tramitGDadesTitFilterForm.setEntityNameCode(getEntityNameCode());
      tramitGDadesTitFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tramitGDadesTitFilterForm.setNou(true);
    } else {
      tramitGDadesTitFilterForm.setNou(false);
    }
    tramitGDadesTitFilterForm.setPage(pagina == null ? 1 : pagina);
    return tramitGDadesTitFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TramitGDadesTit de forma paginada
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
      @ModelAttribute TramitGDadesTitFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTramitGDadesTitFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TramitGDadesTit de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TramitGDadesTit> llistat(ModelAndView mav, HttpServletRequest request,
     TramitGDadesTitFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TramitGDadesTit> tramitGDadesTit = processarLlistat(tramitGDadesTitEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tramitGDadesTitItems", tramitGDadesTit);

    mav.addObject("tramitGDadesTitFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tramitGDadesTit, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tramitGDadesTit);

    return tramitGDadesTit;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TramitGDadesTitFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TramitGDadesTit> list, List<GroupByItem> groupItems) throws I18NException {
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
    TramitGDadesTitFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TramitGDadesTit> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TRAMITGDADESTIT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TRAMITID, filterForm.getMapOfTramitAPersAutForTramitid());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TramitGDadesTit
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTramitGDadesTitGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TramitGDadesTitForm tramitGDadesTitForm = getTramitGDadesTitForm(null, false, request, mav);
    mav.addObject("tramitGDadesTitForm" ,tramitGDadesTitForm);
    fillReferencesForForm(tramitGDadesTitForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TramitGDadesTitForm getTramitGDadesTitForm(TramitGDadesTitJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TramitGDadesTitForm tramitGDadesTitForm;
    if(_jpa == null) {
      tramitGDadesTitForm = new TramitGDadesTitForm(new TramitGDadesTitJPA(), true);
    } else {
      tramitGDadesTitForm = new TramitGDadesTitForm(_jpa, false);
      tramitGDadesTitForm.setView(__isView);
    }
    tramitGDadesTitForm.setContexte(getContextWeb());
    tramitGDadesTitForm.setEntityNameCode(getEntityNameCode());
    tramitGDadesTitForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tramitGDadesTitForm;
  }

  public void fillReferencesForForm(TramitGDadesTitForm tramitGDadesTitForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tramitGDadesTitForm.getListOfTramitAPersAutForTramitid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTramitid(request, mav, tramitGDadesTitForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitGDadesTitForm.setListOfTramitAPersAutForTramitid(_listSKV);
    }
    
  }

  /**
   * Guardar un nou TramitGDadesTit
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTramitGDadesTitPost(@ModelAttribute TramitGDadesTitForm tramitGDadesTitForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TramitGDadesTitJPA tramitGDadesTit = tramitGDadesTitForm.getTramitGDadesTit();

    try {
      preValidate(request, tramitGDadesTitForm, result);
      getWebValidator().validate(tramitGDadesTitForm, result);
      postValidate(request,tramitGDadesTitForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitGDadesTit = create(request, tramitGDadesTit);
        createMessageSuccess(request, "success.creation", tramitGDadesTit.getDadestitid());
        tramitGDadesTitForm.setTramitGDadesTit(tramitGDadesTit);
        return getRedirectWhenCreated(request, tramitGDadesTitForm);
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

  @RequestMapping(value = "/view/{dadestitid}", method = RequestMethod.GET)
  public ModelAndView veureTramitGDadesTitGet(@PathVariable("dadestitid") java.lang.Long dadestitid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitGDadesTitGet(dadestitid,
        request, response, true);
  }


  protected ModelAndView editAndViewTramitGDadesTitGet(@PathVariable("dadestitid") java.lang.Long dadestitid,
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
    TramitGDadesTitJPA tramitGDadesTit = findByPrimaryKey(request, dadestitid);

    if (tramitGDadesTit == null) {
      createMessageWarning(request, "error.notfound", dadestitid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, dadestitid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TramitGDadesTitForm tramitGDadesTitForm = getTramitGDadesTitForm(tramitGDadesTit, __isView, request, mav);
      tramitGDadesTitForm.setView(__isView);
      if(__isView) {
        tramitGDadesTitForm.setAllFieldsReadOnly(ALL_TRAMITGDADESTIT_FIELDS);
        tramitGDadesTitForm.setSaveButtonVisible(false);
        tramitGDadesTitForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tramitGDadesTitForm, request, mav);
      mav.addObject("tramitGDadesTitForm", tramitGDadesTitForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TramitGDadesTit existent
   */
  @RequestMapping(value = "/{dadestitid}/edit", method = RequestMethod.GET)
  public ModelAndView editarTramitGDadesTitGet(@PathVariable("dadestitid") java.lang.Long dadestitid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitGDadesTitGet(dadestitid,
        request, response, false);
  }



  /**
   * Editar un TramitGDadesTit existent
   */
  @RequestMapping(value = "/{dadestitid}/edit", method = RequestMethod.POST)
  public String editarTramitGDadesTitPost(@ModelAttribute TramitGDadesTitForm tramitGDadesTitForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TramitGDadesTitJPA tramitGDadesTit = tramitGDadesTitForm.getTramitGDadesTit();

    try {
      preValidate(request, tramitGDadesTitForm, result);
      getWebValidator().validate(tramitGDadesTitForm, result);
      postValidate(request, tramitGDadesTitForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitGDadesTit = update(request, tramitGDadesTit);
        createMessageSuccess(request, "success.modification", tramitGDadesTit.getDadestitid());
        status.setComplete();
        return getRedirectWhenModified(request, tramitGDadesTitForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tramitGDadesTit.getDadestitid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tramitGDadesTitForm, __e);
    }

  }


  /**
   * Eliminar un TramitGDadesTit existent
   */
  @RequestMapping(value = "/{dadestitid}/delete")
  public String eliminarTramitGDadesTit(@PathVariable("dadestitid") java.lang.Long dadestitid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TramitGDadesTit tramitGDadesTit = this.findByPrimaryKey(request, dadestitid);
      if (tramitGDadesTit == null) {
        String __msg = createMessageError(request, "error.notfound", dadestitid);
        return getRedirectWhenDelete(request, dadestitid, new Exception(__msg));
      } else {
        delete(request, tramitGDadesTit);
        createMessageSuccess(request, "success.deleted", dadestitid);
        return getRedirectWhenDelete(request, dadestitid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", dadestitid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, dadestitid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TramitGDadesTitFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTramitGDadesTit(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __dadestitid, Throwable e) {
    java.lang.Long dadestitid = (java.lang.Long)__dadestitid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (dadestitid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(dadestitid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tramitGDadesTit.tramitGDadesTit";
  }

  public String getEntityNameCodePlural() {
    return "tramitGDadesTit.tramitGDadesTit.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tramitGDadesTit.dadestitid");
  }

  @InitBinder("tramitGDadesTitFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tramitGDadesTitForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "tramitGDadesTit.dadestitid");
  }

  public TramitGDadesTitWebValidator getWebValidator() {
    return tramitGDadesTitWebValidator;
  }


  public void setWebValidator(TramitGDadesTitWebValidator __val) {
    if (__val != null) {
      this.tramitGDadesTitWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TramitGDadesTit
   */
  @RequestMapping(value = "/{dadestitid}/cancel")
  public String cancelTramitGDadesTit(@PathVariable("dadestitid") java.lang.Long dadestitid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, dadestitid);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de TramitGDadesTit
   */
  @RequestMapping(value = "/cancel")
  public String cancelTramitGDadesTit(HttpServletRequest request,HttpServletResponse response) {
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
       ModelAndView mav, TramitGDadesTitForm tramitGDadesTitForm, Where where)  throws I18NException {
    if (tramitGDadesTitForm.isHiddenField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tramitGDadesTitForm.isReadOnlyField(TRAMITID)) {
      _where = TramitAPersAutFields.TRAMITID.equal(tramitGDadesTitForm.getTramitGDadesTit().getTramitid());
    }
    return getReferenceListForTramitid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTramitid(HttpServletRequest request,
       ModelAndView mav, TramitGDadesTitFilterForm tramitGDadesTitFilterForm,
       List<TramitGDadesTit> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitGDadesTitFilterForm.isHiddenField(TRAMITID)
       && !tramitGDadesTitFilterForm.isGroupByField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TRAMITID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TramitGDadesTit _item : list) {
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

  public void preValidate(HttpServletRequest request,TramitGDadesTitForm tramitGDadesTitForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TramitGDadesTitForm tramitGDadesTitForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TramitGDadesTitFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TramitGDadesTitFilterForm filterForm,  List<TramitGDadesTit> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TramitGDadesTitForm tramitGDadesTitForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TramitGDadesTitForm tramitGDadesTitForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long dadestitid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long dadestitid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tramitGDadesTitFormWebDB";
  }

  public String getTileList() {
    return "tramitGDadesTitListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TramitGDadesTit_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TramitGDadesTitJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long dadestitid) throws I18NException {
    return (TramitGDadesTitJPA) tramitGDadesTitEjb.findByPrimaryKey(dadestitid);
  }


  public TramitGDadesTitJPA create(HttpServletRequest request, TramitGDadesTitJPA tramitGDadesTit)
    throws I18NException, I18NValidationException {
    return (TramitGDadesTitJPA) tramitGDadesTitEjb.create(tramitGDadesTit);
  }


  public TramitGDadesTitJPA update(HttpServletRequest request, TramitGDadesTitJPA tramitGDadesTit)
    throws I18NException, I18NValidationException {
    return (TramitGDadesTitJPA) tramitGDadesTitEjb.update(tramitGDadesTit);
  }


  public void delete(HttpServletRequest request, TramitGDadesTit tramitGDadesTit) throws I18NException {
    tramitGDadesTitEjb.delete(tramitGDadesTit);
  }

} // Final de Classe

