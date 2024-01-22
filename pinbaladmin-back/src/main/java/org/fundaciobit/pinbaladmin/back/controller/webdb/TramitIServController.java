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
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TramitIServWebValidator;

import org.fundaciobit.pinbaladmin.persistence.TramitIServJPA;
import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un TramitIServ
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tramitIServ")
@SessionAttributes(types = { TramitIServForm.class, TramitIServFilterForm.class })
public class TramitIServController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<TramitIServ, java.lang.Long> implements TramitIServFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitIServService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitIServService tramitIServEjb;

  @Autowired
  private TramitIServWebValidator tramitIServWebValidator;

  @Autowired
  protected TramitIServRefList tramitIServRefList;

  // References 
  @Autowired
  protected TramitAPersAutRefList tramitAPersAutRefList;

  /**
   * Llistat de totes TramitIServ
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TramitIServFilterForm ff;
    ff = (TramitIServFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TramitIServ de forma paginada
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
    llistat(mav, request, getTramitIServFilterForm(pagina, mav, request));
    return mav;
  }

  public TramitIServFilterForm getTramitIServFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TramitIServFilterForm tramitIServFilterForm;
    tramitIServFilterForm = (TramitIServFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tramitIServFilterForm == null) {
      tramitIServFilterForm = new TramitIServFilterForm();
      tramitIServFilterForm.setContexte(getContextWeb());
      tramitIServFilterForm.setEntityNameCode(getEntityNameCode());
      tramitIServFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tramitIServFilterForm.setNou(true);
    } else {
      tramitIServFilterForm.setNou(false);
    }
    tramitIServFilterForm.setPage(pagina == null ? 1 : pagina);
    return tramitIServFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TramitIServ de forma paginada
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
      @ModelAttribute TramitIServFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTramitIServFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TramitIServ de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TramitIServ> llistat(ModelAndView mav, HttpServletRequest request,
     TramitIServFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TramitIServ> tramitIServ = processarLlistat(tramitIServEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tramitIServItems", tramitIServ);

    mav.addObject("tramitIServFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tramitIServ, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tramitIServ);

    return tramitIServ;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TramitIServFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TramitIServ> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field nom
    {
      _listSKV = getReferenceListForNom(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForNom(_tmp);
      if (filterForm.getGroupByFields().contains(NOM)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, NOM, false);
      };
    }

    // Field consentiment
    {
      _listSKV = getReferenceListForConsentiment(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForConsentiment(_tmp);
      if (filterForm.getGroupByFields().contains(CONSENTIMENT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONSENTIMENT, false);
      };
    }

    // Field consentimentpublicat
    {
      _listSKV = getReferenceListForConsentimentpublicat(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForConsentimentpublicat(_tmp);
      if (filterForm.getGroupByFields().contains(CONSENTIMENTPUBLICAT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONSENTIMENTPUBLICAT, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TramitIServFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TramitIServ> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TRAMITISERV_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TRAMITID, filterForm.getMapOfTramitAPersAutForTramitid());
    __mapping.put(NOM, filterForm.getMapOfValuesForNom());
    __mapping.put(CONSENTIMENT, filterForm.getMapOfValuesForConsentiment());
    __mapping.put(CONSENTIMENTPUBLICAT, filterForm.getMapOfValuesForConsentimentpublicat());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TramitIServ
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTramitIServGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TramitIServForm tramitIServForm = getTramitIServForm(null, false, request, mav);
    mav.addObject("tramitIServForm" ,tramitIServForm);
    fillReferencesForForm(tramitIServForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TramitIServForm getTramitIServForm(TramitIServJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TramitIServForm tramitIServForm;
    if(_jpa == null) {
      tramitIServForm = new TramitIServForm(new TramitIServJPA(), true);
    } else {
      tramitIServForm = new TramitIServForm(_jpa, false);
      tramitIServForm.setView(__isView);
    }
    tramitIServForm.setContexte(getContextWeb());
    tramitIServForm.setEntityNameCode(getEntityNameCode());
    tramitIServForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tramitIServForm;
  }

  public void fillReferencesForForm(TramitIServForm tramitIServForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tramitIServForm.getListOfTramitAPersAutForTramitid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTramitid(request, mav, tramitIServForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitIServForm.setListOfTramitAPersAutForTramitid(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitIServForm.getListOfValuesForNom() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForNom(request, mav, tramitIServForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitIServForm.setListOfValuesForNom(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitIServForm.getListOfValuesForConsentiment() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConsentiment(request, mav, tramitIServForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitIServForm.setListOfValuesForConsentiment(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitIServForm.getListOfValuesForConsentimentpublicat() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConsentimentpublicat(request, mav, tramitIServForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitIServForm.setListOfValuesForConsentimentpublicat(_listSKV);
    }
    
  }

  /**
   * Guardar un nou TramitIServ
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTramitIServPost(@ModelAttribute TramitIServForm tramitIServForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TramitIServJPA tramitIServ = tramitIServForm.getTramitIServ();

    try {
      preValidate(request, tramitIServForm, result);
      getWebValidator().validate(tramitIServForm, result);
      postValidate(request,tramitIServForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitIServ = create(request, tramitIServ);
        createMessageSuccess(request, "success.creation", tramitIServ.getServid());
        tramitIServForm.setTramitIServ(tramitIServ);
        return getRedirectWhenCreated(request, tramitIServForm);
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

  @RequestMapping(value = "/view/{servid}", method = RequestMethod.GET)
  public ModelAndView veureTramitIServGet(@PathVariable("servid") java.lang.Long servid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitIServGet(servid,
        request, response, true);
  }


  protected ModelAndView editAndViewTramitIServGet(@PathVariable("servid") java.lang.Long servid,
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
    TramitIServJPA tramitIServ = findByPrimaryKey(request, servid);

    if (tramitIServ == null) {
      createMessageWarning(request, "error.notfound", servid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, servid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TramitIServForm tramitIServForm = getTramitIServForm(tramitIServ, __isView, request, mav);
      tramitIServForm.setView(__isView);
      if(__isView) {
        tramitIServForm.setAllFieldsReadOnly(ALL_TRAMITISERV_FIELDS);
        tramitIServForm.setSaveButtonVisible(false);
        tramitIServForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tramitIServForm, request, mav);
      mav.addObject("tramitIServForm", tramitIServForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TramitIServ existent
   */
  @RequestMapping(value = "/{servid}/edit", method = RequestMethod.GET)
  public ModelAndView editarTramitIServGet(@PathVariable("servid") java.lang.Long servid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitIServGet(servid,
        request, response, false);
  }



  /**
   * Editar un TramitIServ existent
   */
  @RequestMapping(value = "/{servid}/edit", method = RequestMethod.POST)
  public String editarTramitIServPost(@ModelAttribute TramitIServForm tramitIServForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TramitIServJPA tramitIServ = tramitIServForm.getTramitIServ();

    try {
      preValidate(request, tramitIServForm, result);
      getWebValidator().validate(tramitIServForm, result);
      postValidate(request, tramitIServForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitIServ = update(request, tramitIServ);
        createMessageSuccess(request, "success.modification", tramitIServ.getServid());
        status.setComplete();
        return getRedirectWhenModified(request, tramitIServForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tramitIServ.getServid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tramitIServForm, __e);
    }

  }


  /**
   * Eliminar un TramitIServ existent
   */
  @RequestMapping(value = "/{servid}/delete")
  public String eliminarTramitIServ(@PathVariable("servid") java.lang.Long servid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TramitIServ tramitIServ = this.findByPrimaryKey(request, servid);
      if (tramitIServ == null) {
        String __msg = createMessageError(request, "error.notfound", servid);
        return getRedirectWhenDelete(request, servid, new Exception(__msg));
      } else {
        delete(request, tramitIServ);
        createMessageSuccess(request, "success.deleted", servid);
        return getRedirectWhenDelete(request, servid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", servid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, servid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TramitIServFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTramitIServ(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __servid, Throwable e) {
    java.lang.Long servid = (java.lang.Long)__servid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (servid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(servid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tramitIServ.tramitIServ";
  }

  public String getEntityNameCodePlural() {
    return "tramitIServ.tramitIServ.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tramitIServ.servid");
  }

  @InitBinder("tramitIServFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tramitIServForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "tramitIServ.servid");
  }

  public TramitIServWebValidator getWebValidator() {
    return tramitIServWebValidator;
  }


  public void setWebValidator(TramitIServWebValidator __val) {
    if (__val != null) {
      this.tramitIServWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TramitIServ
   */
  @RequestMapping(value = "/{servid}/cancel")
  public String cancelTramitIServ(@PathVariable("servid") java.lang.Long servid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, servid);
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
       ModelAndView mav, TramitIServForm tramitIServForm, Where where)  throws I18NException {
    if (tramitIServForm.isHiddenField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tramitIServForm.isReadOnlyField(TRAMITID)) {
      _where = TramitAPersAutFields.TRAMITID.equal(tramitIServForm.getTramitIServ().getTramitid());
    }
    return getReferenceListForTramitid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTramitid(HttpServletRequest request,
       ModelAndView mav, TramitIServFilterForm tramitIServFilterForm,
       List<TramitIServ> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitIServFilterForm.isHiddenField(TRAMITID)
       && !tramitIServFilterForm.isGroupByField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TRAMITID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TramitIServ _item : list) {
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


  public List<StringKeyValue> getReferenceListForNom(HttpServletRequest request,
       ModelAndView mav, TramitIServForm tramitIServForm, Where where)  throws I18NException {
    if (tramitIServForm.isHiddenField(NOM)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForNom(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForNom(HttpServletRequest request,
       ModelAndView mav, TramitIServFilterForm tramitIServFilterForm,
       List<TramitIServ> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitIServFilterForm.isHiddenField(NOM)
       && !tramitIServFilterForm.isGroupByField(NOM)
       && !tramitIServFilterForm.isFilterByField(NOM)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForNom(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForNom(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("Procediment1" , "Procediment1"));
    __tmp.add(new StringKeyValue("Procediment2" , "Procediment2"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request,
       ModelAndView mav, TramitIServForm tramitIServForm, Where where)  throws I18NException {
    if (tramitIServForm.isHiddenField(CONSENTIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForConsentiment(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request,
       ModelAndView mav, TramitIServFilterForm tramitIServFilterForm,
       List<TramitIServ> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitIServFilterForm.isHiddenField(CONSENTIMENT)
       && !tramitIServFilterForm.isGroupByField(CONSENTIMENT)
       && !tramitIServFilterForm.isFilterByField(CONSENTIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForConsentiment(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("NoOposició" , "NoOposició"));
    __tmp.add(new StringKeyValue("Llei" , "Llei"));
    __tmp.add(new StringKeyValue("Si" , "Si"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForConsentimentpublicat(HttpServletRequest request,
       ModelAndView mav, TramitIServForm tramitIServForm, Where where)  throws I18NException {
    if (tramitIServForm.isHiddenField(CONSENTIMENTPUBLICAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForConsentimentpublicat(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForConsentimentpublicat(HttpServletRequest request,
       ModelAndView mav, TramitIServFilterForm tramitIServFilterForm,
       List<TramitIServ> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitIServFilterForm.isHiddenField(CONSENTIMENTPUBLICAT)
       && !tramitIServFilterForm.isGroupByField(CONSENTIMENTPUBLICAT)
       && !tramitIServFilterForm.isFilterByField(CONSENTIMENTPUBLICAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForConsentimentpublicat(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForConsentimentpublicat(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("Publicat" , "Publicat"));
    __tmp.add(new StringKeyValue("Adjunt" , "Adjunt"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,TramitIServForm tramitIServForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TramitIServForm tramitIServForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TramitIServFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TramitIServFilterForm filterForm,  List<TramitIServ> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TramitIServForm tramitIServForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TramitIServForm tramitIServForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long servid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long servid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tramitIServFormWebDB";
  }

  public String getTileList() {
    return "tramitIServListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TramitIServ_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TramitIServJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long servid) throws I18NException {
    return (TramitIServJPA) tramitIServEjb.findByPrimaryKey(servid);
  }


  public TramitIServJPA create(HttpServletRequest request, TramitIServJPA tramitIServ)
    throws I18NException, I18NValidationException {
    return (TramitIServJPA) tramitIServEjb.create(tramitIServ);
  }


  public TramitIServJPA update(HttpServletRequest request, TramitIServJPA tramitIServ)
    throws I18NException, I18NValidationException {
    return (TramitIServJPA) tramitIServEjb.update(tramitIServ);
  }


  public void delete(HttpServletRequest request, TramitIServ tramitIServ) throws I18NException {
    tramitIServEjb.delete(tramitIServ);
  }

} // Final de Classe

