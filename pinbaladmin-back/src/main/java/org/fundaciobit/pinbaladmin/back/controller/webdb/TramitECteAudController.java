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
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TramitECteAudWebValidator;

import org.fundaciobit.pinbaladmin.persistence.TramitECteAudJPA;
import org.fundaciobit.pinbaladmin.model.entity.TramitECteAud;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un TramitECteAud
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tramitECteAud")
@SessionAttributes(types = { TramitECteAudForm.class, TramitECteAudFilterForm.class })
public class TramitECteAudController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<TramitECteAud, java.lang.Long> implements TramitECteAudFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitECteAudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitECteAudService tramitECteAudEjb;

  @Autowired
  private TramitECteAudWebValidator tramitECteAudWebValidator;

  @Autowired
  protected TramitECteAudRefList tramitECteAudRefList;

  // References 
  @Autowired
  protected TramitAPersAutRefList tramitAPersAutRefList;

  /**
   * Llistat de totes TramitECteAud
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TramitECteAudFilterForm ff;
    ff = (TramitECteAudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TramitECteAud de forma paginada
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
    llistat(mav, request, getTramitECteAudFilterForm(pagina, mav, request));
    return mav;
  }

  public TramitECteAudFilterForm getTramitECteAudFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TramitECteAudFilterForm tramitECteAudFilterForm;
    tramitECteAudFilterForm = (TramitECteAudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tramitECteAudFilterForm == null) {
      tramitECteAudFilterForm = new TramitECteAudFilterForm();
      tramitECteAudFilterForm.setContexte(getContextWeb());
      tramitECteAudFilterForm.setEntityNameCode(getEntityNameCode());
      tramitECteAudFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tramitECteAudFilterForm.setNou(true);
    } else {
      tramitECteAudFilterForm.setNou(false);
    }
    tramitECteAudFilterForm.setPage(pagina == null ? 1 : pagina);
    return tramitECteAudFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TramitECteAud de forma paginada
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
      @ModelAttribute TramitECteAudFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTramitECteAudFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TramitECteAud de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TramitECteAud> llistat(ModelAndView mav, HttpServletRequest request,
     TramitECteAudFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TramitECteAud> tramitECteAud = processarLlistat(tramitECteAudEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tramitECteAudItems", tramitECteAud);

    mav.addObject("tramitECteAudFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tramitECteAud, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tramitECteAud);

    return tramitECteAud;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TramitECteAudFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TramitECteAud> list, List<GroupByItem> groupItems) throws I18NException {
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
    TramitECteAudFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TramitECteAud> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TRAMITECTEAUD_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TRAMITID, filterForm.getMapOfTramitAPersAutForTramitid());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TramitECteAud
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTramitECteAudGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TramitECteAudForm tramitECteAudForm = getTramitECteAudForm(null, false, request, mav);
    mav.addObject("tramitECteAudForm" ,tramitECteAudForm);
    fillReferencesForForm(tramitECteAudForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TramitECteAudForm getTramitECteAudForm(TramitECteAudJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TramitECteAudForm tramitECteAudForm;
    if(_jpa == null) {
      tramitECteAudForm = new TramitECteAudForm(new TramitECteAudJPA(), true);
    } else {
      tramitECteAudForm = new TramitECteAudForm(_jpa, false);
      tramitECteAudForm.setView(__isView);
    }
    tramitECteAudForm.setContexte(getContextWeb());
    tramitECteAudForm.setEntityNameCode(getEntityNameCode());
    tramitECteAudForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tramitECteAudForm;
  }

  public void fillReferencesForForm(TramitECteAudForm tramitECteAudForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tramitECteAudForm.getListOfTramitAPersAutForTramitid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTramitid(request, mav, tramitECteAudForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitECteAudForm.setListOfTramitAPersAutForTramitid(_listSKV);
    }
    
  }

  /**
   * Guardar un nou TramitECteAud
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTramitECteAudPost(@ModelAttribute TramitECteAudForm tramitECteAudForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TramitECteAudJPA tramitECteAud = tramitECteAudForm.getTramitECteAud();

    try {
      preValidate(request, tramitECteAudForm, result);
      getWebValidator().validate(tramitECteAudForm, result);
      postValidate(request,tramitECteAudForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitECteAud = create(request, tramitECteAud);
        createMessageSuccess(request, "success.creation", tramitECteAud.getCteaudid());
        tramitECteAudForm.setTramitECteAud(tramitECteAud);
        return getRedirectWhenCreated(request, tramitECteAudForm);
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

  @RequestMapping(value = "/view/{cteaudid}", method = RequestMethod.GET)
  public ModelAndView veureTramitECteAudGet(@PathVariable("cteaudid") java.lang.Long cteaudid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitECteAudGet(cteaudid,
        request, response, true);
  }


  protected ModelAndView editAndViewTramitECteAudGet(@PathVariable("cteaudid") java.lang.Long cteaudid,
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
    TramitECteAudJPA tramitECteAud = findByPrimaryKey(request, cteaudid);

    if (tramitECteAud == null) {
      createMessageWarning(request, "error.notfound", cteaudid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, cteaudid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TramitECteAudForm tramitECteAudForm = getTramitECteAudForm(tramitECteAud, __isView, request, mav);
      tramitECteAudForm.setView(__isView);
      if(__isView) {
        tramitECteAudForm.setAllFieldsReadOnly(ALL_TRAMITECTEAUD_FIELDS);
        tramitECteAudForm.setSaveButtonVisible(false);
        tramitECteAudForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tramitECteAudForm, request, mav);
      mav.addObject("tramitECteAudForm", tramitECteAudForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TramitECteAud existent
   */
  @RequestMapping(value = "/{cteaudid}/edit", method = RequestMethod.GET)
  public ModelAndView editarTramitECteAudGet(@PathVariable("cteaudid") java.lang.Long cteaudid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitECteAudGet(cteaudid,
        request, response, false);
  }



  /**
   * Editar un TramitECteAud existent
   */
  @RequestMapping(value = "/{cteaudid}/edit", method = RequestMethod.POST)
  public String editarTramitECteAudPost(@ModelAttribute TramitECteAudForm tramitECteAudForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TramitECteAudJPA tramitECteAud = tramitECteAudForm.getTramitECteAud();

    try {
      preValidate(request, tramitECteAudForm, result);
      getWebValidator().validate(tramitECteAudForm, result);
      postValidate(request, tramitECteAudForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitECteAud = update(request, tramitECteAud);
        createMessageSuccess(request, "success.modification", tramitECteAud.getCteaudid());
        status.setComplete();
        return getRedirectWhenModified(request, tramitECteAudForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tramitECteAud.getCteaudid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tramitECteAudForm, __e);
    }

  }


  /**
   * Eliminar un TramitECteAud existent
   */
  @RequestMapping(value = "/{cteaudid}/delete")
  public String eliminarTramitECteAud(@PathVariable("cteaudid") java.lang.Long cteaudid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TramitECteAud tramitECteAud = this.findByPrimaryKey(request, cteaudid);
      if (tramitECteAud == null) {
        String __msg = createMessageError(request, "error.notfound", cteaudid);
        return getRedirectWhenDelete(request, cteaudid, new Exception(__msg));
      } else {
        delete(request, tramitECteAud);
        createMessageSuccess(request, "success.deleted", cteaudid);
        return getRedirectWhenDelete(request, cteaudid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", cteaudid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, cteaudid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TramitECteAudFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTramitECteAud(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __cteaudid, Throwable e) {
    java.lang.Long cteaudid = (java.lang.Long)__cteaudid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (cteaudid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(cteaudid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tramitECteAud.tramitECteAud";
  }

  public String getEntityNameCodePlural() {
    return "tramitECteAud.tramitECteAud.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tramitECteAud.cteaudid");
  }

  @InitBinder("tramitECteAudFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tramitECteAudForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "tramitECteAud.cteaudid");
  }

  public TramitECteAudWebValidator getWebValidator() {
    return tramitECteAudWebValidator;
  }


  public void setWebValidator(TramitECteAudWebValidator __val) {
    if (__val != null) {
      this.tramitECteAudWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TramitECteAud
   */
  @RequestMapping(value = "/{cteaudid}/cancel")
  public String cancelTramitECteAud(@PathVariable("cteaudid") java.lang.Long cteaudid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, cteaudid);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de TramitECteAud
   */
  @RequestMapping(value = "/cancel")
  public String cancelTramitECteAud(HttpServletRequest request,HttpServletResponse response) {
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
       ModelAndView mav, TramitECteAudForm tramitECteAudForm, Where where)  throws I18NException {
    if (tramitECteAudForm.isHiddenField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tramitECteAudForm.isReadOnlyField(TRAMITID)) {
      _where = TramitAPersAutFields.TRAMITID.equal(tramitECteAudForm.getTramitECteAud().getTramitid());
    }
    return getReferenceListForTramitid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTramitid(HttpServletRequest request,
       ModelAndView mav, TramitECteAudFilterForm tramitECteAudFilterForm,
       List<TramitECteAud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitECteAudFilterForm.isHiddenField(TRAMITID)
       && !tramitECteAudFilterForm.isGroupByField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TRAMITID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TramitECteAud _item : list) {
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

  public void preValidate(HttpServletRequest request,TramitECteAudForm tramitECteAudForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TramitECteAudForm tramitECteAudForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TramitECteAudFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TramitECteAudFilterForm filterForm,  List<TramitECteAud> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TramitECteAudForm tramitECteAudForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TramitECteAudForm tramitECteAudForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long cteaudid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long cteaudid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tramitECteAudFormWebDB";
  }

  public String getTileList() {
    return "tramitECteAudListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TramitECteAud_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TramitECteAudJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long cteaudid) throws I18NException {
    return (TramitECteAudJPA) tramitECteAudEjb.findByPrimaryKey(cteaudid);
  }


  public TramitECteAudJPA create(HttpServletRequest request, TramitECteAudJPA tramitECteAud)
    throws I18NException, I18NValidationException {
    return (TramitECteAudJPA) tramitECteAudEjb.create(tramitECteAud);
  }


  public TramitECteAudJPA update(HttpServletRequest request, TramitECteAudJPA tramitECteAud)
    throws I18NException, I18NValidationException {
    return (TramitECteAudJPA) tramitECteAudEjb.update(tramitECteAud);
  }


  public void delete(HttpServletRequest request, TramitECteAud tramitECteAud) throws I18NException {
    tramitECteAudEjb.delete(tramitECteAud);
  }

} // Final de Classe

