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
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TramitBDadesSoliWebValidator;

import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPA;
import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un TramitBDadesSoli
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tramitBDadesSoli")
@SessionAttributes(types = { TramitBDadesSoliForm.class, TramitBDadesSoliFilterForm.class })
public class TramitBDadesSoliController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<TramitBDadesSoli, java.lang.Long> implements TramitBDadesSoliFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitBDadesSoliService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitBDadesSoliService tramitBDadesSoliEjb;

  @Autowired
  private TramitBDadesSoliWebValidator tramitBDadesSoliWebValidator;

  @Autowired
  protected TramitBDadesSoliRefList tramitBDadesSoliRefList;

  // References 
  @Autowired
  protected TramitAPersAutRefList tramitAPersAutRefList;

  /**
   * Llistat de totes TramitBDadesSoli
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TramitBDadesSoliFilterForm ff;
    ff = (TramitBDadesSoliFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TramitBDadesSoli de forma paginada
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
    llistat(mav, request, getTramitBDadesSoliFilterForm(pagina, mav, request));
    return mav;
  }

  public TramitBDadesSoliFilterForm getTramitBDadesSoliFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TramitBDadesSoliFilterForm tramitBDadesSoliFilterForm;
    tramitBDadesSoliFilterForm = (TramitBDadesSoliFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tramitBDadesSoliFilterForm == null) {
      tramitBDadesSoliFilterForm = new TramitBDadesSoliFilterForm();
      tramitBDadesSoliFilterForm.setContexte(getContextWeb());
      tramitBDadesSoliFilterForm.setEntityNameCode(getEntityNameCode());
      tramitBDadesSoliFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tramitBDadesSoliFilterForm.setNou(true);
    } else {
      tramitBDadesSoliFilterForm.setNou(false);
    }
    tramitBDadesSoliFilterForm.setPage(pagina == null ? 1 : pagina);
    return tramitBDadesSoliFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TramitBDadesSoli de forma paginada
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
      @ModelAttribute TramitBDadesSoliFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTramitBDadesSoliFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TramitBDadesSoli de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TramitBDadesSoli> llistat(ModelAndView mav, HttpServletRequest request,
     TramitBDadesSoliFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TramitBDadesSoli> tramitBDadesSoli = processarLlistat(tramitBDadesSoliEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tramitBDadesSoliItems", tramitBDadesSoli);

    mav.addObject("tramitBDadesSoliFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tramitBDadesSoli, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tramitBDadesSoli);

    return tramitBDadesSoli;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TramitBDadesSoliFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TramitBDadesSoli> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field tipussolicitud
    {
      _listSKV = getReferenceListForTipussolicitud(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipussolicitud(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSSOLICITUD)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSSOLICITUD, false);
      };
    }

    // Field entorn
    {
      _listSKV = getReferenceListForEntorn(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEntorn(_tmp);
      if (filterForm.getGroupByFields().contains(ENTORN)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTORN, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TramitBDadesSoliFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TramitBDadesSoli> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TRAMITBDADESSOLI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TRAMITID, filterForm.getMapOfTramitAPersAutForTramitid());
    __mapping.put(TIPUSSOLICITUD, filterForm.getMapOfValuesForTipussolicitud());
    __mapping.put(ENTORN, filterForm.getMapOfValuesForEntorn());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TramitBDadesSoli
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTramitBDadesSoliGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TramitBDadesSoliForm tramitBDadesSoliForm = getTramitBDadesSoliForm(null, false, request, mav);
    mav.addObject("tramitBDadesSoliForm" ,tramitBDadesSoliForm);
    fillReferencesForForm(tramitBDadesSoliForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TramitBDadesSoliForm getTramitBDadesSoliForm(TramitBDadesSoliJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TramitBDadesSoliForm tramitBDadesSoliForm;
    if(_jpa == null) {
      tramitBDadesSoliForm = new TramitBDadesSoliForm(new TramitBDadesSoliJPA(), true);
    } else {
      tramitBDadesSoliForm = new TramitBDadesSoliForm(_jpa, false);
      tramitBDadesSoliForm.setView(__isView);
    }
    tramitBDadesSoliForm.setContexte(getContextWeb());
    tramitBDadesSoliForm.setEntityNameCode(getEntityNameCode());
    tramitBDadesSoliForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tramitBDadesSoliForm;
  }

  public void fillReferencesForForm(TramitBDadesSoliForm tramitBDadesSoliForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tramitBDadesSoliForm.getListOfTramitAPersAutForTramitid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTramitid(request, mav, tramitBDadesSoliForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitBDadesSoliForm.setListOfTramitAPersAutForTramitid(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitBDadesSoliForm.getListOfValuesForTipussolicitud() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipussolicitud(request, mav, tramitBDadesSoliForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitBDadesSoliForm.setListOfValuesForTipussolicitud(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitBDadesSoliForm.getListOfValuesForEntorn() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntorn(request, mav, tramitBDadesSoliForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitBDadesSoliForm.setListOfValuesForEntorn(_listSKV);
    }
    
  }

  /**
   * Guardar un nou TramitBDadesSoli
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTramitBDadesSoliPost(@ModelAttribute TramitBDadesSoliForm tramitBDadesSoliForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TramitBDadesSoliJPA tramitBDadesSoli = tramitBDadesSoliForm.getTramitBDadesSoli();

    try {
      preValidate(request, tramitBDadesSoliForm, result);
      getWebValidator().validate(tramitBDadesSoliForm, result);
      postValidate(request,tramitBDadesSoliForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitBDadesSoli = create(request, tramitBDadesSoli);
        createMessageSuccess(request, "success.creation", tramitBDadesSoli.getDadessoliid());
        tramitBDadesSoliForm.setTramitBDadesSoli(tramitBDadesSoli);
        return getRedirectWhenCreated(request, tramitBDadesSoliForm);
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

  @RequestMapping(value = "/view/{dadessoliid}", method = RequestMethod.GET)
  public ModelAndView veureTramitBDadesSoliGet(@PathVariable("dadessoliid") java.lang.Long dadessoliid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitBDadesSoliGet(dadessoliid,
        request, response, true);
  }


  protected ModelAndView editAndViewTramitBDadesSoliGet(@PathVariable("dadessoliid") java.lang.Long dadessoliid,
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
    TramitBDadesSoliJPA tramitBDadesSoli = findByPrimaryKey(request, dadessoliid);

    if (tramitBDadesSoli == null) {
      createMessageWarning(request, "error.notfound", dadessoliid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, dadessoliid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TramitBDadesSoliForm tramitBDadesSoliForm = getTramitBDadesSoliForm(tramitBDadesSoli, __isView, request, mav);
      tramitBDadesSoliForm.setView(__isView);
      if(__isView) {
        tramitBDadesSoliForm.setAllFieldsReadOnly(ALL_TRAMITBDADESSOLI_FIELDS);
        tramitBDadesSoliForm.setSaveButtonVisible(false);
        tramitBDadesSoliForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tramitBDadesSoliForm, request, mav);
      mav.addObject("tramitBDadesSoliForm", tramitBDadesSoliForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TramitBDadesSoli existent
   */
  @RequestMapping(value = "/{dadessoliid}/edit", method = RequestMethod.GET)
  public ModelAndView editarTramitBDadesSoliGet(@PathVariable("dadessoliid") java.lang.Long dadessoliid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitBDadesSoliGet(dadessoliid,
        request, response, false);
  }



  /**
   * Editar un TramitBDadesSoli existent
   */
  @RequestMapping(value = "/{dadessoliid}/edit", method = RequestMethod.POST)
  public String editarTramitBDadesSoliPost(@ModelAttribute TramitBDadesSoliForm tramitBDadesSoliForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TramitBDadesSoliJPA tramitBDadesSoli = tramitBDadesSoliForm.getTramitBDadesSoli();

    try {
      preValidate(request, tramitBDadesSoliForm, result);
      getWebValidator().validate(tramitBDadesSoliForm, result);
      postValidate(request, tramitBDadesSoliForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitBDadesSoli = update(request, tramitBDadesSoli);
        createMessageSuccess(request, "success.modification", tramitBDadesSoli.getDadessoliid());
        status.setComplete();
        return getRedirectWhenModified(request, tramitBDadesSoliForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tramitBDadesSoli.getDadessoliid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tramitBDadesSoliForm, __e);
    }

  }


  /**
   * Eliminar un TramitBDadesSoli existent
   */
  @RequestMapping(value = "/{dadessoliid}/delete")
  public String eliminarTramitBDadesSoli(@PathVariable("dadessoliid") java.lang.Long dadessoliid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TramitBDadesSoli tramitBDadesSoli = this.findByPrimaryKey(request, dadessoliid);
      if (tramitBDadesSoli == null) {
        String __msg = createMessageError(request, "error.notfound", dadessoliid);
        return getRedirectWhenDelete(request, dadessoliid, new Exception(__msg));
      } else {
        delete(request, tramitBDadesSoli);
        createMessageSuccess(request, "success.deleted", dadessoliid);
        return getRedirectWhenDelete(request, dadessoliid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", dadessoliid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, dadessoliid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TramitBDadesSoliFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTramitBDadesSoli(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __dadessoliid, Throwable e) {
    java.lang.Long dadessoliid = (java.lang.Long)__dadessoliid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (dadessoliid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(dadessoliid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tramitBDadesSoli.tramitBDadesSoli";
  }

  public String getEntityNameCodePlural() {
    return "tramitBDadesSoli.tramitBDadesSoli.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tramitBDadesSoli.dadessoliid");
  }

  @InitBinder("tramitBDadesSoliFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tramitBDadesSoliForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "tramitBDadesSoli.dadessoliid");
  }

  public TramitBDadesSoliWebValidator getWebValidator() {
    return tramitBDadesSoliWebValidator;
  }


  public void setWebValidator(TramitBDadesSoliWebValidator __val) {
    if (__val != null) {
      this.tramitBDadesSoliWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TramitBDadesSoli
   */
  @RequestMapping(value = "/{dadessoliid}/cancel")
  public String cancelTramitBDadesSoli(@PathVariable("dadessoliid") java.lang.Long dadessoliid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, dadessoliid);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de TramitBDadesSoli
   */
  @RequestMapping(value = "/cancel")
  public String cancelTramitBDadesSoli(HttpServletRequest request,HttpServletResponse response) {
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
       ModelAndView mav, TramitBDadesSoliForm tramitBDadesSoliForm, Where where)  throws I18NException {
    if (tramitBDadesSoliForm.isHiddenField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tramitBDadesSoliForm.isReadOnlyField(TRAMITID)) {
      _where = TramitAPersAutFields.TRAMITID.equal(tramitBDadesSoliForm.getTramitBDadesSoli().getTramitid());
    }
    return getReferenceListForTramitid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTramitid(HttpServletRequest request,
       ModelAndView mav, TramitBDadesSoliFilterForm tramitBDadesSoliFilterForm,
       List<TramitBDadesSoli> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitBDadesSoliFilterForm.isHiddenField(TRAMITID)
       && !tramitBDadesSoliFilterForm.isGroupByField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TRAMITID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TramitBDadesSoli _item : list) {
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


  public List<StringKeyValue> getReferenceListForTipussolicitud(HttpServletRequest request,
       ModelAndView mav, TramitBDadesSoliForm tramitBDadesSoliForm, Where where)  throws I18NException {
    if (tramitBDadesSoliForm.isHiddenField(TIPUSSOLICITUD)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipussolicitud(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipussolicitud(HttpServletRequest request,
       ModelAndView mav, TramitBDadesSoliFilterForm tramitBDadesSoliFilterForm,
       List<TramitBDadesSoli> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitBDadesSoliFilterForm.isHiddenField(TIPUSSOLICITUD)
       && !tramitBDadesSoliFilterForm.isGroupByField(TIPUSSOLICITUD)
       && !tramitBDadesSoliFilterForm.isFilterByField(TIPUSSOLICITUD)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipussolicitud(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipussolicitud(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForEntorn(HttpServletRequest request,
       ModelAndView mav, TramitBDadesSoliForm tramitBDadesSoliForm, Where where)  throws I18NException {
    if (tramitBDadesSoliForm.isHiddenField(ENTORN)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEntorn(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEntorn(HttpServletRequest request,
       ModelAndView mav, TramitBDadesSoliFilterForm tramitBDadesSoliFilterForm,
       List<TramitBDadesSoli> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitBDadesSoliFilterForm.isHiddenField(ENTORN)
       && !tramitBDadesSoliFilterForm.isGroupByField(ENTORN)
       && !tramitBDadesSoliFilterForm.isFilterByField(ENTORN)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEntorn(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntorn(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("pre" , "pre"));
    __tmp.add(new StringKeyValue("pro" , "pro"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,TramitBDadesSoliForm tramitBDadesSoliForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TramitBDadesSoliForm tramitBDadesSoliForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TramitBDadesSoliFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TramitBDadesSoliFilterForm filterForm,  List<TramitBDadesSoli> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TramitBDadesSoliForm tramitBDadesSoliForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TramitBDadesSoliForm tramitBDadesSoliForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long dadessoliid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long dadessoliid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tramitBDadesSoliFormWebDB";
  }

  public String getTileList() {
    return "tramitBDadesSoliListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TramitBDadesSoli_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TramitBDadesSoliJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long dadessoliid) throws I18NException {
    return (TramitBDadesSoliJPA) tramitBDadesSoliEjb.findByPrimaryKey(dadessoliid);
  }


  public TramitBDadesSoliJPA create(HttpServletRequest request, TramitBDadesSoliJPA tramitBDadesSoli)
    throws I18NException, I18NValidationException {
    return (TramitBDadesSoliJPA) tramitBDadesSoliEjb.create(tramitBDadesSoli);
  }


  public TramitBDadesSoliJPA update(HttpServletRequest request, TramitBDadesSoliJPA tramitBDadesSoli)
    throws I18NException, I18NValidationException {
    return (TramitBDadesSoliJPA) tramitBDadesSoliEjb.update(tramitBDadesSoli);
  }


  public void delete(HttpServletRequest request, TramitBDadesSoli tramitBDadesSoli) throws I18NException {
    tramitBDadesSoliEjb.delete(tramitBDadesSoli);
  }

} // Final de Classe

