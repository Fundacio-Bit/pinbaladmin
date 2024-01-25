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
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.SolicitudServeiWebValidator;

import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un SolicitudServei
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/solicitudServei")
@SessionAttributes(types = { SolicitudServeiForm.class, SolicitudServeiFilterForm.class })
public class SolicitudServeiController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<SolicitudServei, java.lang.Long> implements SolicitudServeiFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService solicitudServeiEjb;

  @Autowired
  private SolicitudServeiWebValidator solicitudServeiWebValidator;

  @Autowired
  protected SolicitudServeiRefList solicitudServeiRefList;

  // References 
  @Autowired
  protected SolicitudRefList solicitudRefList;

  // References 
  @Autowired
  protected ServeiRefList serveiRefList;

  /**
   * Llistat de totes SolicitudServei
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    SolicitudServeiFilterForm ff;
    ff = (SolicitudServeiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar SolicitudServei de forma paginada
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
    llistat(mav, request, getSolicitudServeiFilterForm(pagina, mav, request));
    return mav;
  }

  public SolicitudServeiFilterForm getSolicitudServeiFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    SolicitudServeiFilterForm solicitudServeiFilterForm;
    solicitudServeiFilterForm = (SolicitudServeiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(solicitudServeiFilterForm == null) {
      solicitudServeiFilterForm = new SolicitudServeiFilterForm();
      solicitudServeiFilterForm.setContexte(getContextWeb());
      solicitudServeiFilterForm.setEntityNameCode(getEntityNameCode());
      solicitudServeiFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      solicitudServeiFilterForm.setNou(true);
    } else {
      solicitudServeiFilterForm.setNou(false);
    }
    solicitudServeiFilterForm.setPage(pagina == null ? 1 : pagina);
    return solicitudServeiFilterForm;
  }

  /**
   * Segona i següent peticions per llistar SolicitudServei de forma paginada
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
      @ModelAttribute SolicitudServeiFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getSolicitudServeiFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de SolicitudServei de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<SolicitudServei> llistat(ModelAndView mav, HttpServletRequest request,
     SolicitudServeiFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<SolicitudServei> solicitudServei = processarLlistat(solicitudServeiEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("solicitudServeiItems", solicitudServei);

    mav.addObject("solicitudServeiFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, solicitudServei, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, solicitudServei);

    return solicitudServei;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(SolicitudServeiFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<SolicitudServei> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field serveiID
    {
      _listSKV = getReferenceListForServeiID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfServeiForServeiID(_tmp);
      if (filterForm.getGroupByFields().contains(SERVEIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SERVEIID, false);
      };
    }

    // Field estatSolicitudServeiID
    {
      _listSKV = getReferenceListForEstatSolicitudServeiID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEstatSolicitudServeiID(_tmp);
      if (filterForm.getGroupByFields().contains(ESTATSOLICITUDSERVEIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTATSOLICITUDSERVEIID, false);
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

    // Field consentiment
    {
      _listSKV = getReferenceListForConsentiment(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForConsentiment(_tmp);
      if (filterForm.getGroupByFields().contains(CONSENTIMENT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONSENTIMENT, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    SolicitudServeiFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<SolicitudServei> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_SOLICITUDSERVEI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(SOLICITUDID, filterForm.getMapOfSolicitudForSolicitudID());
    __mapping.put(SERVEIID, filterForm.getMapOfServeiForServeiID());
    __mapping.put(ESTATSOLICITUDSERVEIID, filterForm.getMapOfValuesForEstatSolicitudServeiID());
    __mapping.put(TIPUSCONSENTIMENT, filterForm.getMapOfValuesForTipusConsentiment());
    __mapping.put(CONSENTIMENT, filterForm.getMapOfValuesForConsentiment());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou SolicitudServei
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearSolicitudServeiGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    SolicitudServeiForm solicitudServeiForm = getSolicitudServeiForm(null, false, request, mav);
    mav.addObject("solicitudServeiForm" ,solicitudServeiForm);
    fillReferencesForForm(solicitudServeiForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public SolicitudServeiForm getSolicitudServeiForm(SolicitudServeiJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    SolicitudServeiForm solicitudServeiForm;
    if(_jpa == null) {
      solicitudServeiForm = new SolicitudServeiForm(new SolicitudServeiJPA(), true);
    } else {
      solicitudServeiForm = new SolicitudServeiForm(_jpa, false);
      solicitudServeiForm.setView(__isView);
    }
    solicitudServeiForm.setContexte(getContextWeb());
    solicitudServeiForm.setEntityNameCode(getEntityNameCode());
    solicitudServeiForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return solicitudServeiForm;
  }

  public void fillReferencesForForm(SolicitudServeiForm solicitudServeiForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (solicitudServeiForm.getListOfSolicitudForSolicitudID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSolicitudID(request, mav, solicitudServeiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      solicitudServeiForm.setListOfSolicitudForSolicitudID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (solicitudServeiForm.getListOfServeiForServeiID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForServeiID(request, mav, solicitudServeiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      solicitudServeiForm.setListOfServeiForServeiID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (solicitudServeiForm.getListOfValuesForEstatSolicitudServeiID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstatSolicitudServeiID(request, mav, solicitudServeiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      solicitudServeiForm.setListOfValuesForEstatSolicitudServeiID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (solicitudServeiForm.getListOfValuesForTipusConsentiment() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusConsentiment(request, mav, solicitudServeiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      solicitudServeiForm.setListOfValuesForTipusConsentiment(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (solicitudServeiForm.getListOfValuesForConsentiment() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConsentiment(request, mav, solicitudServeiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      solicitudServeiForm.setListOfValuesForConsentiment(_listSKV);
    }
    
  }

  /**
   * Guardar un nou SolicitudServei
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearSolicitudServeiPost(@ModelAttribute SolicitudServeiForm solicitudServeiForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    SolicitudServeiJPA solicitudServei = solicitudServeiForm.getSolicitudServei();

    try {
      preValidate(request, solicitudServeiForm, result);
      getWebValidator().validate(solicitudServeiForm, result);
      postValidate(request,solicitudServeiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        solicitudServei = create(request, solicitudServei);
        createMessageSuccess(request, "success.creation", solicitudServei.getId());
        solicitudServeiForm.setSolicitudServei(solicitudServei);
        return getRedirectWhenCreated(request, solicitudServeiForm);
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

  @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
  public ModelAndView veureSolicitudServeiGet(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewSolicitudServeiGet(id,
        request, response, true);
  }


  protected ModelAndView editAndViewSolicitudServeiGet(@PathVariable("id") java.lang.Long id,
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
    SolicitudServeiJPA solicitudServei = findByPrimaryKey(request, id);

    if (solicitudServei == null) {
      createMessageWarning(request, "error.notfound", id);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, id), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      SolicitudServeiForm solicitudServeiForm = getSolicitudServeiForm(solicitudServei, __isView, request, mav);
      solicitudServeiForm.setView(__isView);
      if(__isView) {
        solicitudServeiForm.setAllFieldsReadOnly(ALL_SOLICITUDSERVEI_FIELDS);
        solicitudServeiForm.setSaveButtonVisible(false);
        solicitudServeiForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(solicitudServeiForm, request, mav);
      mav.addObject("solicitudServeiForm", solicitudServeiForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un SolicitudServei existent
   */
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public ModelAndView editarSolicitudServeiGet(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewSolicitudServeiGet(id,
        request, response, false);
  }



  /**
   * Editar un SolicitudServei existent
   */
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public String editarSolicitudServeiPost(@ModelAttribute SolicitudServeiForm solicitudServeiForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    SolicitudServeiJPA solicitudServei = solicitudServeiForm.getSolicitudServei();

    try {
      preValidate(request, solicitudServeiForm, result);
      getWebValidator().validate(solicitudServeiForm, result);
      postValidate(request, solicitudServeiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        solicitudServei = update(request, solicitudServei);
        createMessageSuccess(request, "success.modification", solicitudServei.getId());
        status.setComplete();
        return getRedirectWhenModified(request, solicitudServeiForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          solicitudServei.getId(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, solicitudServeiForm, __e);
    }

  }


  /**
   * Eliminar un SolicitudServei existent
   */
  @RequestMapping(value = "/{id}/delete")
  public String eliminarSolicitudServei(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      SolicitudServei solicitudServei = this.findByPrimaryKey(request, id);
      if (solicitudServei == null) {
        String __msg = createMessageError(request, "error.notfound", id);
        return getRedirectWhenDelete(request, id, new Exception(__msg));
      } else {
        delete(request, solicitudServei);
        createMessageSuccess(request, "success.deleted", id);
        return getRedirectWhenDelete(request, id,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", id, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, id, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute SolicitudServeiFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarSolicitudServei(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __id, Throwable e) {
    java.lang.Long id = (java.lang.Long)__id;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (id == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(id),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "solicitudServei.solicitudServei";
  }

  public String getEntityNameCodePlural() {
    return "solicitudServei.solicitudServei.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("solicitudServei.id");
  }

  @InitBinder("solicitudServeiFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("solicitudServeiForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "solicitudServei.id");
  }

  public SolicitudServeiWebValidator getWebValidator() {
    return solicitudServeiWebValidator;
  }


  public void setWebValidator(SolicitudServeiWebValidator __val) {
    if (__val != null) {
      this.solicitudServeiWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de SolicitudServei
   */
  @RequestMapping(value = "/{id}/cancel")
  public String cancelSolicitudServei(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, id);
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


  public List<StringKeyValue> getReferenceListForSolicitudID(HttpServletRequest request,
       ModelAndView mav, SolicitudServeiForm solicitudServeiForm, Where where)  throws I18NException {
    if (solicitudServeiForm.isHiddenField(SOLICITUDID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (solicitudServeiForm.isReadOnlyField(SOLICITUDID)) {
      _where = SolicitudFields.SOLICITUDID.equal(solicitudServeiForm.getSolicitudServei().getSolicitudID());
    }
    return getReferenceListForSolicitudID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSolicitudID(HttpServletRequest request,
       ModelAndView mav, SolicitudServeiFilterForm solicitudServeiFilterForm,
       List<SolicitudServei> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (solicitudServeiFilterForm.isHiddenField(SOLICITUDID)
       && !solicitudServeiFilterForm.isGroupByField(SOLICITUDID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SOLICITUDID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (SolicitudServei _item : list) {
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


  public List<StringKeyValue> getReferenceListForServeiID(HttpServletRequest request,
       ModelAndView mav, SolicitudServeiForm solicitudServeiForm, Where where)  throws I18NException {
    if (solicitudServeiForm.isHiddenField(SERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (solicitudServeiForm.isReadOnlyField(SERVEIID)) {
      _where = ServeiFields.SERVEIID.equal(solicitudServeiForm.getSolicitudServei().getServeiID());
    }
    return getReferenceListForServeiID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForServeiID(HttpServletRequest request,
       ModelAndView mav, SolicitudServeiFilterForm solicitudServeiFilterForm,
       List<SolicitudServei> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (solicitudServeiFilterForm.isHiddenField(SERVEIID)
       && !solicitudServeiFilterForm.isGroupByField(SERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SERVEIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (SolicitudServei _item : list) {
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


  public List<StringKeyValue> getReferenceListForEstatSolicitudServeiID(HttpServletRequest request,
       ModelAndView mav, SolicitudServeiForm solicitudServeiForm, Where where)  throws I18NException {
    if (solicitudServeiForm.isHiddenField(ESTATSOLICITUDSERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEstatSolicitudServeiID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstatSolicitudServeiID(HttpServletRequest request,
       ModelAndView mav, SolicitudServeiFilterForm solicitudServeiFilterForm,
       List<SolicitudServei> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (solicitudServeiFilterForm.isHiddenField(ESTATSOLICITUDSERVEIID)
       && !solicitudServeiFilterForm.isGroupByField(ESTATSOLICITUDSERVEIID)
       && !solicitudServeiFilterForm.isFilterByField(ESTATSOLICITUDSERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEstatSolicitudServeiID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstatSolicitudServeiID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("-1" , "-1"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("10" , "10"));
    __tmp.add(new StringKeyValue("20" , "20"));
    __tmp.add(new StringKeyValue("30" , "30"));
    __tmp.add(new StringKeyValue("40" , "40"));
    __tmp.add(new StringKeyValue("50" , "50"));
    __tmp.add(new StringKeyValue("60" , "60"));
    __tmp.add(new StringKeyValue("80" , "80"));
    __tmp.add(new StringKeyValue("90" , "90"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForTipusConsentiment(HttpServletRequest request,
       ModelAndView mav, SolicitudServeiForm solicitudServeiForm, Where where)  throws I18NException {
    if (solicitudServeiForm.isHiddenField(TIPUSCONSENTIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipusConsentiment(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipusConsentiment(HttpServletRequest request,
       ModelAndView mav, SolicitudServeiFilterForm solicitudServeiFilterForm,
       List<SolicitudServei> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (solicitudServeiFilterForm.isHiddenField(TIPUSCONSENTIMENT)
       && !solicitudServeiFilterForm.isGroupByField(TIPUSCONSENTIMENT)
       && !solicitudServeiFilterForm.isFilterByField(TIPUSCONSENTIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipusConsentiment(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusConsentiment(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("adjunt" , "adjunt"));
    __tmp.add(new StringKeyValue("publicat" , "publicat"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request,
       ModelAndView mav, SolicitudServeiForm solicitudServeiForm, Where where)  throws I18NException {
    if (solicitudServeiForm.isHiddenField(CONSENTIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForConsentiment(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request,
       ModelAndView mav, SolicitudServeiFilterForm solicitudServeiFilterForm,
       List<SolicitudServei> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (solicitudServeiFilterForm.isHiddenField(CONSENTIMENT)
       && !solicitudServeiFilterForm.isGroupByField(CONSENTIMENT)
       && !solicitudServeiFilterForm.isFilterByField(CONSENTIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForConsentiment(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("si" , "si"));
    __tmp.add(new StringKeyValue("llei" , "llei"));
    __tmp.add(new StringKeyValue("noop" , "noop"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,SolicitudServeiForm solicitudServeiForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,SolicitudServeiForm solicitudServeiForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, SolicitudServeiFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, SolicitudServeiFilterForm filterForm,  List<SolicitudServei> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, SolicitudServeiForm solicitudServeiForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, SolicitudServeiForm solicitudServeiForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long id, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long id) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "solicitudServeiFormWebDB";
  }

  public String getTileList() {
    return "solicitudServeiListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "SolicitudServei_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public SolicitudServeiJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
    return (SolicitudServeiJPA) solicitudServeiEjb.findByPrimaryKey(id);
  }


  public SolicitudServeiJPA create(HttpServletRequest request, SolicitudServeiJPA solicitudServei)
    throws I18NException, I18NValidationException {
    return (SolicitudServeiJPA) solicitudServeiEjb.create(solicitudServei);
  }


  public SolicitudServeiJPA update(HttpServletRequest request, SolicitudServeiJPA solicitudServei)
    throws I18NException, I18NValidationException {
    return (SolicitudServeiJPA) solicitudServeiEjb.update(solicitudServei);
  }


  public void delete(HttpServletRequest request, SolicitudServei solicitudServei) throws I18NException {
    solicitudServeiEjb.delete(solicitudServei);
  }

} // Final de Classe

