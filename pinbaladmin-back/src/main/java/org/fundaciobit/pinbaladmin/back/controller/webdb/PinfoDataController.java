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
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.PinfoDataWebValidator;

import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un PinfoData
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/pinfoData")
@SessionAttributes(types = { PinfoDataForm.class, PinfoDataFilterForm.class })
public class PinfoDataController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<PinfoData, java.lang.Long> implements PinfoDataFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.PinfoDataService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.PinfoDataService pinfoDataEjb;

  @Autowired
  private PinfoDataWebValidator pinfoDataWebValidator;

  @Autowired
  protected PinfoDataRefList pinfoDataRefList;

  // References 
  @Autowired
  protected PINFORefList pINFORefList;

  // References 
  @Autowired
  protected SolicitudRefList solicitudRefList;

  // References 
  @Autowired
  protected ServeiRefList serveiRefList;

  /**
   * Llistat de totes PinfoData
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PinfoDataFilterForm ff;
    ff = (PinfoDataFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PinfoData de forma paginada
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
    llistat(mav, request, getPinfoDataFilterForm(pagina, mav, request));
    return mav;
  }

  public PinfoDataFilterForm getPinfoDataFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PinfoDataFilterForm pinfoDataFilterForm;
    pinfoDataFilterForm = (PinfoDataFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(pinfoDataFilterForm == null) {
      pinfoDataFilterForm = new PinfoDataFilterForm();
      pinfoDataFilterForm.setContexte(getContextWeb());
      pinfoDataFilterForm.setEntityNameCode(getEntityNameCode());
      pinfoDataFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      pinfoDataFilterForm.setNou(true);
    } else {
      pinfoDataFilterForm.setNou(false);
    }
    pinfoDataFilterForm.setPage(pagina == null ? 1 : pagina);
    return pinfoDataFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PinfoData de forma paginada
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
      @ModelAttribute PinfoDataFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPinfoDataFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PinfoData de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PinfoData> llistat(ModelAndView mav, HttpServletRequest request,
     PinfoDataFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PinfoData> pinfoData = processarLlistat(pinfoDataEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("pinfoDataItems", pinfoData);

    mav.addObject("pinfoDataFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, pinfoData, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, pinfoData);

    return pinfoData;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PinfoDataFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PinfoData> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field pinfoID
    {
      _listSKV = getReferenceListForPinfoID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPINFOForPinfoID(_tmp);
      if (filterForm.getGroupByFields().contains(PINFOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PINFOID, false);
      };
    }

    // Field estat
    {
      _listSKV = getReferenceListForEstat(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEstat(_tmp);
      if (filterForm.getGroupByFields().contains(ESTAT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTAT, false);
      };
    }

    // Field procedimentID
    {
      _listSKV = getReferenceListForProcedimentID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfSolicitudForProcedimentID(_tmp);
      if (filterForm.getGroupByFields().contains(PROCEDIMENTID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PROCEDIMENTID, false);
      };
    }

    // Field serveiID
    {
      _listSKV = getReferenceListForServeiID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfServeiForServeiID(_tmp);
      if (filterForm.getGroupByFields().contains(SERVEIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SERVEIID, false);
      };
    }

    // Field alta
    {
      _listSKV = getReferenceListForAlta(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForAlta(_tmp);
      if (filterForm.getGroupByFields().contains(ALTA)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ALTA, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PinfoDataFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PinfoData> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PINFODATA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(PINFOID, filterForm.getMapOfPINFOForPinfoID());
    __mapping.put(ESTAT, filterForm.getMapOfValuesForEstat());
    __mapping.put(PROCEDIMENTID, filterForm.getMapOfSolicitudForProcedimentID());
    __mapping.put(SERVEIID, filterForm.getMapOfServeiForServeiID());
    __mapping.put(ALTA, filterForm.getMapOfValuesForAlta());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PinfoData
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPinfoDataGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PinfoDataForm pinfoDataForm = getPinfoDataForm(null, false, request, mav);
    mav.addObject("pinfoDataForm" ,pinfoDataForm);
    fillReferencesForForm(pinfoDataForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PinfoDataForm getPinfoDataForm(PinfoDataJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PinfoDataForm pinfoDataForm;
    if(_jpa == null) {
      pinfoDataForm = new PinfoDataForm(new PinfoDataJPA(), true);
    } else {
      pinfoDataForm = new PinfoDataForm(_jpa, false);
      pinfoDataForm.setView(__isView);
    }
    pinfoDataForm.setContexte(getContextWeb());
    pinfoDataForm.setEntityNameCode(getEntityNameCode());
    pinfoDataForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return pinfoDataForm;
  }

  public void fillReferencesForForm(PinfoDataForm pinfoDataForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (pinfoDataForm.getListOfPINFOForPinfoID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPinfoID(request, mav, pinfoDataForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pinfoDataForm.setListOfPINFOForPinfoID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pinfoDataForm.getListOfValuesForEstat() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstat(request, mav, pinfoDataForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pinfoDataForm.setListOfValuesForEstat(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pinfoDataForm.getListOfSolicitudForProcedimentID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForProcedimentID(request, mav, pinfoDataForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pinfoDataForm.setListOfSolicitudForProcedimentID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pinfoDataForm.getListOfServeiForServeiID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForServeiID(request, mav, pinfoDataForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pinfoDataForm.setListOfServeiForServeiID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pinfoDataForm.getListOfValuesForAlta() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForAlta(request, mav, pinfoDataForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pinfoDataForm.setListOfValuesForAlta(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PinfoData
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPinfoDataPost(@ModelAttribute PinfoDataForm pinfoDataForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PinfoDataJPA pinfoData = pinfoDataForm.getPinfoData();

    try {
      preValidate(request, pinfoDataForm, result);
      getWebValidator().validate(pinfoDataForm, result);
      postValidate(request,pinfoDataForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        pinfoData = create(request, pinfoData);
        createMessageSuccess(request, "success.creation", pinfoData.getPinfodataID());
        pinfoDataForm.setPinfoData(pinfoData);
        return getRedirectWhenCreated(request, pinfoDataForm);
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

  @RequestMapping(value = "/view/{pinfodataID}", method = RequestMethod.GET)
  public ModelAndView veurePinfoDataGet(@PathVariable("pinfodataID") java.lang.Long pinfodataID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPinfoDataGet(pinfodataID,
        request, response, true);
  }


  protected ModelAndView editAndViewPinfoDataGet(@PathVariable("pinfodataID") java.lang.Long pinfodataID,
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
    PinfoDataJPA pinfoData = findByPrimaryKey(request, pinfodataID);

    if (pinfoData == null) {
      createMessageWarning(request, "error.notfound", pinfodataID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, pinfodataID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PinfoDataForm pinfoDataForm = getPinfoDataForm(pinfoData, __isView, request, mav);
      pinfoDataForm.setView(__isView);
      if(__isView) {
        pinfoDataForm.setAllFieldsReadOnly(ALL_PINFODATA_FIELDS);
        pinfoDataForm.setSaveButtonVisible(false);
        pinfoDataForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(pinfoDataForm, request, mav);
      mav.addObject("pinfoDataForm", pinfoDataForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PinfoData existent
   */
  @RequestMapping(value = "/{pinfodataID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPinfoDataGet(@PathVariable("pinfodataID") java.lang.Long pinfodataID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPinfoDataGet(pinfodataID,
        request, response, false);
  }



  /**
   * Editar un PinfoData existent
   */
  @RequestMapping(value = "/{pinfodataID}/edit", method = RequestMethod.POST)
  public String editarPinfoDataPost(@ModelAttribute PinfoDataForm pinfoDataForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PinfoDataJPA pinfoData = pinfoDataForm.getPinfoData();

    try {
      preValidate(request, pinfoDataForm, result);
      getWebValidator().validate(pinfoDataForm, result);
      postValidate(request, pinfoDataForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        pinfoData = update(request, pinfoData);
        createMessageSuccess(request, "success.modification", pinfoData.getPinfodataID());
        status.setComplete();
        return getRedirectWhenModified(request, pinfoDataForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          pinfoData.getPinfodataID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, pinfoDataForm, __e);
    }

  }


  /**
   * Eliminar un PinfoData existent
   */
  @RequestMapping(value = "/{pinfodataID}/delete")
  public String eliminarPinfoData(@PathVariable("pinfodataID") java.lang.Long pinfodataID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PinfoData pinfoData = this.findByPrimaryKey(request, pinfodataID);
      if (pinfoData == null) {
        String __msg = createMessageError(request, "error.notfound", pinfodataID);
        return getRedirectWhenDelete(request, pinfodataID, new Exception(__msg));
      } else {
        delete(request, pinfoData);
        createMessageSuccess(request, "success.deleted", pinfodataID);
        return getRedirectWhenDelete(request, pinfodataID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", pinfodataID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, pinfodataID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PinfoDataFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPinfoData(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __pinfodataID, Throwable e) {
    java.lang.Long pinfodataID = (java.lang.Long)__pinfodataID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (pinfodataID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(pinfodataID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "pinfoData.pinfoData";
  }

  public String getEntityNameCodePlural() {
    return "pinfoData.pinfoData.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("pinfoData.pinfodataID");
  }

  @InitBinder("pinfoDataFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("pinfoDataForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "pinfoData.pinfodataID");
  }

  public PinfoDataWebValidator getWebValidator() {
    return pinfoDataWebValidator;
  }


  public void setWebValidator(PinfoDataWebValidator __val) {
    if (__val != null) {
      this.pinfoDataWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PinfoData
   */
  @RequestMapping(value = "/{pinfodataID}/cancel")
  public String cancelPinfoData(@PathVariable("pinfodataID") java.lang.Long pinfodataID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, pinfodataID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de PinfoData
   */
  @RequestMapping(value = "/cancel")
  public String cancelPinfoData(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForPinfoID(HttpServletRequest request,
       ModelAndView mav, PinfoDataForm pinfoDataForm, Where where)  throws I18NException {
    if (pinfoDataForm.isHiddenField(PINFOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pinfoDataForm.isReadOnlyField(PINFOID)) {
      _where = PINFOFields.PINFOID.equal(pinfoDataForm.getPinfoData().getPinfoID());
    }
    return getReferenceListForPinfoID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPinfoID(HttpServletRequest request,
       ModelAndView mav, PinfoDataFilterForm pinfoDataFilterForm,
       List<PinfoData> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pinfoDataFilterForm.isHiddenField(PINFOID)
       && !pinfoDataFilterForm.isGroupByField(PINFOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PINFOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PinfoData _item : list) {
        if(_item.getPinfoID() == null) { continue; };
        _pkList.add(_item.getPinfoID());
        }
        _w = PINFOFields.PINFOID.in(_pkList);
      }
    return getReferenceListForPinfoID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPinfoID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return pINFORefList.getReferenceList(PINFOFields.PINFOID, where );
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, PinfoDataForm pinfoDataForm, Where where)  throws I18NException {
    if (pinfoDataForm.isHiddenField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEstat(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, PinfoDataFilterForm pinfoDataFilterForm,
       List<PinfoData> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pinfoDataFilterForm.isHiddenField(ESTAT)
       && !pinfoDataFilterForm.isGroupByField(ESTAT)
       && !pinfoDataFilterForm.isFilterByField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEstat(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("obert" , "obert"));
    __tmp.add(new StringKeyValue(" tancat" , " tancat"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForProcedimentID(HttpServletRequest request,
       ModelAndView mav, PinfoDataForm pinfoDataForm, Where where)  throws I18NException {
    if (pinfoDataForm.isHiddenField(PROCEDIMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pinfoDataForm.isReadOnlyField(PROCEDIMENTID)) {
      _where = SolicitudFields.SOLICITUDID.equal(pinfoDataForm.getPinfoData().getProcedimentID());
    }
    return getReferenceListForProcedimentID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForProcedimentID(HttpServletRequest request,
       ModelAndView mav, PinfoDataFilterForm pinfoDataFilterForm,
       List<PinfoData> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pinfoDataFilterForm.isHiddenField(PROCEDIMENTID)
       && !pinfoDataFilterForm.isGroupByField(PROCEDIMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PROCEDIMENTID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PinfoData _item : list) {
        if(_item.getProcedimentID() == null) { continue; };
        _pkList.add(_item.getProcedimentID());
        }
        _w = SolicitudFields.SOLICITUDID.in(_pkList);
      }
    return getReferenceListForProcedimentID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForProcedimentID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return solicitudRefList.getReferenceList(SolicitudFields.SOLICITUDID, where );
  }


  public List<StringKeyValue> getReferenceListForServeiID(HttpServletRequest request,
       ModelAndView mav, PinfoDataForm pinfoDataForm, Where where)  throws I18NException {
    if (pinfoDataForm.isHiddenField(SERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pinfoDataForm.isReadOnlyField(SERVEIID)) {
      _where = ServeiFields.SERVEIID.equal(pinfoDataForm.getPinfoData().getServeiID());
    }
    return getReferenceListForServeiID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForServeiID(HttpServletRequest request,
       ModelAndView mav, PinfoDataFilterForm pinfoDataFilterForm,
       List<PinfoData> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pinfoDataFilterForm.isHiddenField(SERVEIID)
       && !pinfoDataFilterForm.isGroupByField(SERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SERVEIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PinfoData _item : list) {
        if(_item.getServeiID() == null) { continue; };
        _pkList.add(_item.getServeiID());
        }
        _w = ServeiFields.SERVEIID.in(_pkList);
      }
    return getReferenceListForServeiID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForServeiID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return serveiRefList.getReferenceList(ServeiFields.SERVEIID, where );
  }


  public List<StringKeyValue> getReferenceListForAlta(HttpServletRequest request,
       ModelAndView mav, PinfoDataForm pinfoDataForm, Where where)  throws I18NException {
    if (pinfoDataForm.isHiddenField(ALTA)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForAlta(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForAlta(HttpServletRequest request,
       ModelAndView mav, PinfoDataFilterForm pinfoDataFilterForm,
       List<PinfoData> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pinfoDataFilterForm.isHiddenField(ALTA)
       && !pinfoDataFilterForm.isGroupByField(ALTA)
       && !pinfoDataFilterForm.isFilterByField(ALTA)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForAlta(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForAlta(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("alta" , "alta"));
    __tmp.add(new StringKeyValue(" baixa" , " baixa"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,PinfoDataForm pinfoDataForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PinfoDataForm pinfoDataForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PinfoDataFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PinfoDataFilterForm filterForm,  List<PinfoData> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PinfoDataForm pinfoDataForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PinfoDataForm pinfoDataForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long pinfodataID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long pinfodataID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "pinfoDataFormWebDB";
  }

  public String getTileList() {
    return "pinfoDataListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "PinfoData_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PinfoDataJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pinfodataID) throws I18NException {
    return (PinfoDataJPA) pinfoDataEjb.findByPrimaryKey(pinfodataID);
  }


  public PinfoDataJPA create(HttpServletRequest request, PinfoDataJPA pinfoData)
    throws I18NException, I18NValidationException {
    return (PinfoDataJPA) pinfoDataEjb.create(pinfoData);
  }


  public PinfoDataJPA update(HttpServletRequest request, PinfoDataJPA pinfoData)
    throws I18NException, I18NValidationException {
    return (PinfoDataJPA) pinfoDataEjb.update(pinfoData);
  }


  public void delete(HttpServletRequest request, PinfoData pinfoData) throws I18NException {
    pinfoDataEjb.delete(pinfoData);
  }

} // Final de Classe

