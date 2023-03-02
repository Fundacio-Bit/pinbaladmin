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
import org.fundaciobit.pinbaladmin.back.form.webdb.ServeiForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.ServeiWebValidator;

import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Servei
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/servei")
@SessionAttributes(types = { ServeiForm.class, ServeiFilterForm.class })
public class ServeiController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<Servei, java.lang.Long> implements ServeiFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ServeiService serveiEjb;

  @Autowired
  private ServeiWebValidator serveiWebValidator;

  @Autowired
  protected ServeiRefList serveiRefList;

  // References 
  @Autowired
  protected FormulariRefList formulariRefList;

  // References 
  @Autowired
  protected EntitatServeiRefList entitatServeiRefList;

  // References 
  @Autowired
  protected EstatServeiRefList estatServeiRefList;

  /**
   * Llistat de totes Servei
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    ServeiFilterForm ff;
    ff = (ServeiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Servei de forma paginada
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
    llistat(mav, request, getServeiFilterForm(pagina, mav, request));
    return mav;
  }

  public ServeiFilterForm getServeiFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    ServeiFilterForm serveiFilterForm;
    serveiFilterForm = (ServeiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(serveiFilterForm == null) {
      serveiFilterForm = new ServeiFilterForm();
      serveiFilterForm.setContexte(getContextWeb());
      serveiFilterForm.setEntityNameCode(getEntityNameCode());
      serveiFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      serveiFilterForm.setNou(true);
    } else {
      serveiFilterForm.setNou(false);
    }
    serveiFilterForm.setPage(pagina == null ? 1 : pagina);
    return serveiFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Servei de forma paginada
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
      @ModelAttribute ServeiFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getServeiFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Servei de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Servei> llistat(ModelAndView mav, HttpServletRequest request,
     ServeiFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Servei> servei = processarLlistat(serveiEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("serveiItems", servei);

    mav.addObject("serveiFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, servei, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, servei);

    return servei;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(ServeiFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Servei> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field formulariID
    {
      _listSKV = getReferenceListForFormulariID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfFormulariForFormulariID(_tmp);
      if (filterForm.getGroupByFields().contains(FORMULARIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FORMULARIID, false);
      };
    }

    // Field entitatServeiID
    {
      _listSKV = getReferenceListForEntitatServeiID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatServeiForEntitatServeiID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATSERVEIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATSERVEIID, false);
      };
    }

    // Field estatServeiID
    {
      _listSKV = getReferenceListForEstatServeiID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEstatServeiForEstatServeiID(_tmp);
      if (filterForm.getGroupByFields().contains(ESTATSERVEIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTATSERVEIID, false);
      };
    }

    // Field tipusConsentiment
    {
      _listSKV = getReferenceListForTipusConsentiment(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipusConsentiment(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSCONSENTIMENT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSCONSENTIMENT, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, OCULT);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    ServeiFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Servei> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_SERVEI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(FORMULARIID, filterForm.getMapOfFormulariForFormulariID());
    __mapping.put(ENTITATSERVEIID, filterForm.getMapOfEntitatServeiForEntitatServeiID());
    __mapping.put(ESTATSERVEIID, filterForm.getMapOfEstatServeiForEstatServeiID());
    __mapping.put(TIPUSCONSENTIMENT, filterForm.getMapOfValuesForTipusConsentiment());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Servei
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearServeiGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    ServeiForm serveiForm = getServeiForm(null, false, request, mav);
    mav.addObject("serveiForm" ,serveiForm);
    fillReferencesForForm(serveiForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public ServeiForm getServeiForm(ServeiJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    ServeiForm serveiForm;
    if(_jpa == null) {
      serveiForm = new ServeiForm(new ServeiJPA(), true);
    } else {
      serveiForm = new ServeiForm(_jpa, false);
      serveiForm.setView(__isView);
    }
    serveiForm.setContexte(getContextWeb());
    serveiForm.setEntityNameCode(getEntityNameCode());
    serveiForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return serveiForm;
  }

  public void fillReferencesForForm(ServeiForm serveiForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (serveiForm.getListOfFormulariForFormulariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFormulariID(request, mav, serveiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      serveiForm.setListOfFormulariForFormulariID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (serveiForm.getListOfEntitatServeiForEntitatServeiID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatServeiID(request, mav, serveiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      serveiForm.setListOfEntitatServeiForEntitatServeiID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (serveiForm.getListOfEstatServeiForEstatServeiID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstatServeiID(request, mav, serveiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      serveiForm.setListOfEstatServeiForEstatServeiID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (serveiForm.getListOfValuesForTipusConsentiment() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusConsentiment(request, mav, serveiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      serveiForm.setListOfValuesForTipusConsentiment(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Servei
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearServeiPost(@ModelAttribute ServeiForm serveiForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ServeiJPA servei = serveiForm.getServei();

    try {
      preValidate(request, serveiForm, result);
      getWebValidator().validate(serveiForm, result);
      postValidate(request,serveiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        servei = create(request, servei);
        createMessageSuccess(request, "success.creation", servei.getServeiID());
        serveiForm.setServei(servei);
        return getRedirectWhenCreated(request, serveiForm);
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

  @RequestMapping(value = "/view/{serveiID}", method = RequestMethod.GET)
  public ModelAndView veureServeiGet(@PathVariable("serveiID") java.lang.Long serveiID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewServeiGet(serveiID,
        request, response, true);
  }


  protected ModelAndView editAndViewServeiGet(@PathVariable("serveiID") java.lang.Long serveiID,
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
    ServeiJPA servei = findByPrimaryKey(request, serveiID);

    if (servei == null) {
      createMessageWarning(request, "error.notfound", serveiID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, serveiID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      ServeiForm serveiForm = getServeiForm(servei, __isView, request, mav);
      serveiForm.setView(__isView);
      if(__isView) {
        serveiForm.setAllFieldsReadOnly(ALL_SERVEI_FIELDS);
        serveiForm.setSaveButtonVisible(false);
        serveiForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(serveiForm, request, mav);
      mav.addObject("serveiForm", serveiForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Servei existent
   */
  @RequestMapping(value = "/{serveiID}/edit", method = RequestMethod.GET)
  public ModelAndView editarServeiGet(@PathVariable("serveiID") java.lang.Long serveiID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewServeiGet(serveiID,
        request, response, false);
  }



  /**
   * Editar un Servei existent
   */
  @RequestMapping(value = "/{serveiID}/edit", method = RequestMethod.POST)
  public String editarServeiPost(@ModelAttribute ServeiForm serveiForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ServeiJPA servei = serveiForm.getServei();

    try {
      preValidate(request, serveiForm, result);
      getWebValidator().validate(serveiForm, result);
      postValidate(request, serveiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        servei = update(request, servei);
        createMessageSuccess(request, "success.modification", servei.getServeiID());
        status.setComplete();
        return getRedirectWhenModified(request, serveiForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          servei.getServeiID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, serveiForm, __e);
    }

  }


  /**
   * Eliminar un Servei existent
   */
  @RequestMapping(value = "/{serveiID}/delete")
  public String eliminarServei(@PathVariable("serveiID") java.lang.Long serveiID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Servei servei = serveiEjb.findByPrimaryKey(serveiID);
      if (servei == null) {
        String __msg =createMessageError(request, "error.notfound", serveiID);
        return getRedirectWhenDelete(request, serveiID, new Exception(__msg));
      } else {
        delete(request, servei);
        createMessageSuccess(request, "success.deleted", serveiID);
        return getRedirectWhenDelete(request, serveiID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", serveiID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, serveiID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute ServeiFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarServei(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __serveiID, Throwable e) {
    java.lang.Long serveiID = (java.lang.Long)__serveiID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (serveiID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(serveiID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "servei.servei";
  }

  public String getEntityNameCodePlural() {
    return "servei.servei.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("servei.serveiID");
  }

  @InitBinder("serveiFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("serveiForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "servei.serveiID");
  }

  public ServeiWebValidator getWebValidator() {
    return serveiWebValidator;
  }


  public void setWebValidator(ServeiWebValidator __val) {
    if (__val != null) {
      this.serveiWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Servei
   */
  @RequestMapping(value = "/{serveiID}/cancel")
  public String cancelServei(@PathVariable("serveiID") java.lang.Long serveiID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, serveiID);
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


  public List<StringKeyValue> getReferenceListForFormulariID(HttpServletRequest request,
       ModelAndView mav, ServeiForm serveiForm, Where where)  throws I18NException {
    if (serveiForm.isHiddenField(FORMULARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (serveiForm.isReadOnlyField(FORMULARIID)) {
      _where = FormulariFields.FORMULARIID.equal(serveiForm.getServei().getFormulariID());
    }
    return getReferenceListForFormulariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFormulariID(HttpServletRequest request,
       ModelAndView mav, ServeiFilterForm serveiFilterForm,
       List<Servei> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (serveiFilterForm.isHiddenField(FORMULARIID)
      && !serveiFilterForm.isGroupByField(FORMULARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FORMULARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Servei _item : list) {
        if(_item.getFormulariID() == null) { continue; };
        _pkList.add(_item.getFormulariID());
        }
        _w = FormulariFields.FORMULARIID.in(_pkList);
      }
    return getReferenceListForFormulariID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFormulariID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return formulariRefList.getReferenceList(FormulariFields.FORMULARIID, where );
  }


  public List<StringKeyValue> getReferenceListForEntitatServeiID(HttpServletRequest request,
       ModelAndView mav, ServeiForm serveiForm, Where where)  throws I18NException {
    if (serveiForm.isHiddenField(ENTITATSERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (serveiForm.isReadOnlyField(ENTITATSERVEIID)) {
      _where = EntitatServeiFields.ENTITATSERVEIID.equal(serveiForm.getServei().getEntitatServeiID());
    }
    return getReferenceListForEntitatServeiID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatServeiID(HttpServletRequest request,
       ModelAndView mav, ServeiFilterForm serveiFilterForm,
       List<Servei> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (serveiFilterForm.isHiddenField(ENTITATSERVEIID)
      && !serveiFilterForm.isGroupByField(ENTITATSERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATSERVEIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Servei _item : list) {
        _pkList.add(_item.getEntitatServeiID());
        }
        _w = EntitatServeiFields.ENTITATSERVEIID.in(_pkList);
      }
    return getReferenceListForEntitatServeiID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatServeiID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatServeiRefList.getReferenceList(EntitatServeiFields.ENTITATSERVEIID, where );
  }


  public List<StringKeyValue> getReferenceListForEstatServeiID(HttpServletRequest request,
       ModelAndView mav, ServeiForm serveiForm, Where where)  throws I18NException {
    if (serveiForm.isHiddenField(ESTATSERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (serveiForm.isReadOnlyField(ESTATSERVEIID)) {
      _where = EstatServeiFields.ESTATSERVEIID.equal(serveiForm.getServei().getEstatServeiID());
    }
    return getReferenceListForEstatServeiID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEstatServeiID(HttpServletRequest request,
       ModelAndView mav, ServeiFilterForm serveiFilterForm,
       List<Servei> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (serveiFilterForm.isHiddenField(ESTATSERVEIID)
      && !serveiFilterForm.isGroupByField(ESTATSERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ESTATSERVEIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Servei _item : list) {
        _pkList.add(_item.getEstatServeiID());
        }
        _w = EstatServeiFields.ESTATSERVEIID.in(_pkList);
      }
    return getReferenceListForEstatServeiID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstatServeiID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return estatServeiRefList.getReferenceList(EstatServeiFields.ESTATSERVEIID, where );
  }


  public List<StringKeyValue> getReferenceListForTipusConsentiment(HttpServletRequest request,
       ModelAndView mav, ServeiForm serveiForm, Where where)  throws I18NException {
    if (serveiForm.isHiddenField(TIPUSCONSENTIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipusConsentiment(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipusConsentiment(HttpServletRequest request,
       ModelAndView mav, ServeiFilterForm serveiFilterForm,
       List<Servei> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (serveiFilterForm.isHiddenField(TIPUSCONSENTIMENT)
      && !serveiFilterForm.isGroupByField(TIPUSCONSENTIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipusConsentiment(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusConsentiment(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,ServeiForm serveiForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,ServeiForm serveiForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, ServeiFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, ServeiFilterForm filterForm,  List<Servei> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, ServeiForm serveiForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, ServeiForm serveiForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long serveiID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long serveiID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "serveiFormWebDB";
  }

  public String getTileList() {
    return "serveiListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "ServeiWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public ServeiJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long serveiID) throws I18NException {
    return (ServeiJPA) serveiEjb.findByPrimaryKey(serveiID);
  }


  public ServeiJPA create(HttpServletRequest request, ServeiJPA servei)
    throws I18NException, I18NValidationException {
    return (ServeiJPA) serveiEjb.create(servei);
  }


  public ServeiJPA update(HttpServletRequest request, ServeiJPA servei)
    throws I18NException, I18NValidationException {
    return (ServeiJPA) serveiEjb.update(servei);
  }


  public void delete(HttpServletRequest request, Servei servei) throws I18NException {
    serveiEjb.delete(servei);
  }

} // Final de Classe

