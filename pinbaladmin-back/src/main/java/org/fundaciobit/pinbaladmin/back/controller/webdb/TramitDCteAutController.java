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
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TramitDCteAutWebValidator;

import org.fundaciobit.pinbaladmin.persistence.TramitDCteAutJPA;
import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un TramitDCteAut
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tramitDCteAut")
@SessionAttributes(types = { TramitDCteAutForm.class, TramitDCteAutFilterForm.class })
public class TramitDCteAutController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<TramitDCteAut, java.lang.Long> implements TramitDCteAutFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitDCteAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitDCteAutService tramitDCteAutEjb;

  @Autowired
  private TramitDCteAutWebValidator tramitDCteAutWebValidator;

  @Autowired
  protected TramitDCteAutRefList tramitDCteAutRefList;

  // References 
  @Autowired
  protected TramitAPersAutRefList tramitAPersAutRefList;

  /**
   * Llistat de totes TramitDCteAut
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TramitDCteAutFilterForm ff;
    ff = (TramitDCteAutFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TramitDCteAut de forma paginada
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
    llistat(mav, request, getTramitDCteAutFilterForm(pagina, mav, request));
    return mav;
  }

  public TramitDCteAutFilterForm getTramitDCteAutFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TramitDCteAutFilterForm tramitDCteAutFilterForm;
    tramitDCteAutFilterForm = (TramitDCteAutFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tramitDCteAutFilterForm == null) {
      tramitDCteAutFilterForm = new TramitDCteAutFilterForm();
      tramitDCteAutFilterForm.setContexte(getContextWeb());
      tramitDCteAutFilterForm.setEntityNameCode(getEntityNameCode());
      tramitDCteAutFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tramitDCteAutFilterForm.setNou(true);
    } else {
      tramitDCteAutFilterForm.setNou(false);
    }
    tramitDCteAutFilterForm.setPage(pagina == null ? 1 : pagina);
    return tramitDCteAutFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TramitDCteAut de forma paginada
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
      @ModelAttribute TramitDCteAutFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTramitDCteAutFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TramitDCteAut de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TramitDCteAut> llistat(ModelAndView mav, HttpServletRequest request,
     TramitDCteAutFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TramitDCteAut> tramitDCteAut = processarLlistat(tramitDCteAutEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tramitDCteAutItems", tramitDCteAut);

    mav.addObject("tramitDCteAutFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tramitDCteAut, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tramitDCteAut);

    return tramitDCteAut;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TramitDCteAutFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TramitDCteAut> list, List<GroupByItem> groupItems) throws I18NException {
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
    TramitDCteAutFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TramitDCteAut> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TRAMITDCTEAUT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TRAMITID, filterForm.getMapOfTramitAPersAutForTramitid());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TramitDCteAut
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTramitDCteAutGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TramitDCteAutForm tramitDCteAutForm = getTramitDCteAutForm(null, false, request, mav);
    mav.addObject("tramitDCteAutForm" ,tramitDCteAutForm);
    fillReferencesForForm(tramitDCteAutForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TramitDCteAutForm getTramitDCteAutForm(TramitDCteAutJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TramitDCteAutForm tramitDCteAutForm;
    if(_jpa == null) {
      tramitDCteAutForm = new TramitDCteAutForm(new TramitDCteAutJPA(), true);
    } else {
      tramitDCteAutForm = new TramitDCteAutForm(_jpa, false);
      tramitDCteAutForm.setView(__isView);
    }
    tramitDCteAutForm.setContexte(getContextWeb());
    tramitDCteAutForm.setEntityNameCode(getEntityNameCode());
    tramitDCteAutForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tramitDCteAutForm;
  }

  public void fillReferencesForForm(TramitDCteAutForm tramitDCteAutForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tramitDCteAutForm.getListOfTramitAPersAutForTramitid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTramitid(request, mav, tramitDCteAutForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitDCteAutForm.setListOfTramitAPersAutForTramitid(_listSKV);
    }
    
  }

  /**
   * Guardar un nou TramitDCteAut
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTramitDCteAutPost(@ModelAttribute TramitDCteAutForm tramitDCteAutForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TramitDCteAutJPA tramitDCteAut = tramitDCteAutForm.getTramitDCteAut();

    try {
      preValidate(request, tramitDCteAutForm, result);
      getWebValidator().validate(tramitDCteAutForm, result);
      postValidate(request,tramitDCteAutForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitDCteAut = create(request, tramitDCteAut);
        createMessageSuccess(request, "success.creation", tramitDCteAut.getCteautid());
        tramitDCteAutForm.setTramitDCteAut(tramitDCteAut);
        return getRedirectWhenCreated(request, tramitDCteAutForm);
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

  @RequestMapping(value = "/view/{cteautid}", method = RequestMethod.GET)
  public ModelAndView veureTramitDCteAutGet(@PathVariable("cteautid") java.lang.Long cteautid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitDCteAutGet(cteautid,
        request, response, true);
  }


  protected ModelAndView editAndViewTramitDCteAutGet(@PathVariable("cteautid") java.lang.Long cteautid,
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
    TramitDCteAutJPA tramitDCteAut = findByPrimaryKey(request, cteautid);

    if (tramitDCteAut == null) {
      createMessageWarning(request, "error.notfound", cteautid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, cteautid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TramitDCteAutForm tramitDCteAutForm = getTramitDCteAutForm(tramitDCteAut, __isView, request, mav);
      tramitDCteAutForm.setView(__isView);
      if(__isView) {
        tramitDCteAutForm.setAllFieldsReadOnly(ALL_TRAMITDCTEAUT_FIELDS);
        tramitDCteAutForm.setSaveButtonVisible(false);
        tramitDCteAutForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tramitDCteAutForm, request, mav);
      mav.addObject("tramitDCteAutForm", tramitDCteAutForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TramitDCteAut existent
   */
  @RequestMapping(value = "/{cteautid}/edit", method = RequestMethod.GET)
  public ModelAndView editarTramitDCteAutGet(@PathVariable("cteautid") java.lang.Long cteautid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitDCteAutGet(cteautid,
        request, response, false);
  }



  /**
   * Editar un TramitDCteAut existent
   */
  @RequestMapping(value = "/{cteautid}/edit", method = RequestMethod.POST)
  public String editarTramitDCteAutPost(@ModelAttribute TramitDCteAutForm tramitDCteAutForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TramitDCteAutJPA tramitDCteAut = tramitDCteAutForm.getTramitDCteAut();

    try {
      preValidate(request, tramitDCteAutForm, result);
      getWebValidator().validate(tramitDCteAutForm, result);
      postValidate(request, tramitDCteAutForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitDCteAut = update(request, tramitDCteAut);
        createMessageSuccess(request, "success.modification", tramitDCteAut.getCteautid());
        status.setComplete();
        return getRedirectWhenModified(request, tramitDCteAutForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tramitDCteAut.getCteautid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tramitDCteAutForm, __e);
    }

  }


  /**
   * Eliminar un TramitDCteAut existent
   */
  @RequestMapping(value = "/{cteautid}/delete")
  public String eliminarTramitDCteAut(@PathVariable("cteautid") java.lang.Long cteautid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TramitDCteAut tramitDCteAut = this.findByPrimaryKey(request, cteautid);
      if (tramitDCteAut == null) {
        String __msg = createMessageError(request, "error.notfound", cteautid);
        return getRedirectWhenDelete(request, cteautid, new Exception(__msg));
      } else {
        delete(request, tramitDCteAut);
        createMessageSuccess(request, "success.deleted", cteautid);
        return getRedirectWhenDelete(request, cteautid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", cteautid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, cteautid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TramitDCteAutFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTramitDCteAut(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __cteautid, Throwable e) {
    java.lang.Long cteautid = (java.lang.Long)__cteautid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (cteautid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(cteautid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tramitDCteAut.tramitDCteAut";
  }

  public String getEntityNameCodePlural() {
    return "tramitDCteAut.tramitDCteAut.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tramitDCteAut.cteautid");
  }

  @InitBinder("tramitDCteAutFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tramitDCteAutForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "tramitDCteAut.cteautid");
  }

  public TramitDCteAutWebValidator getWebValidator() {
    return tramitDCteAutWebValidator;
  }


  public void setWebValidator(TramitDCteAutWebValidator __val) {
    if (__val != null) {
      this.tramitDCteAutWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TramitDCteAut
   */
  @RequestMapping(value = "/{cteautid}/cancel")
  public String cancelTramitDCteAut(@PathVariable("cteautid") java.lang.Long cteautid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, cteautid);
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
       ModelAndView mav, TramitDCteAutForm tramitDCteAutForm, Where where)  throws I18NException {
    if (tramitDCteAutForm.isHiddenField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tramitDCteAutForm.isReadOnlyField(TRAMITID)) {
      _where = TramitAPersAutFields.TRAMITID.equal(tramitDCteAutForm.getTramitDCteAut().getTramitid());
    }
    return getReferenceListForTramitid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTramitid(HttpServletRequest request,
       ModelAndView mav, TramitDCteAutFilterForm tramitDCteAutFilterForm,
       List<TramitDCteAut> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitDCteAutFilterForm.isHiddenField(TRAMITID)
       && !tramitDCteAutFilterForm.isGroupByField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TRAMITID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TramitDCteAut _item : list) {
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

  public void preValidate(HttpServletRequest request,TramitDCteAutForm tramitDCteAutForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TramitDCteAutForm tramitDCteAutForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TramitDCteAutFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TramitDCteAutFilterForm filterForm,  List<TramitDCteAut> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TramitDCteAutForm tramitDCteAutForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TramitDCteAutForm tramitDCteAutForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long cteautid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long cteautid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tramitDCteAutFormWebDB";
  }

  public String getTileList() {
    return "tramitDCteAutListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TramitDCteAut_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TramitDCteAutJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long cteautid) throws I18NException {
    return (TramitDCteAutJPA) tramitDCteAutEjb.findByPrimaryKey(cteautid);
  }


  public TramitDCteAutJPA create(HttpServletRequest request, TramitDCteAutJPA tramitDCteAut)
    throws I18NException, I18NValidationException {
    return (TramitDCteAutJPA) tramitDCteAutEjb.create(tramitDCteAut);
  }


  public TramitDCteAutJPA update(HttpServletRequest request, TramitDCteAutJPA tramitDCteAut)
    throws I18NException, I18NValidationException {
    return (TramitDCteAutJPA) tramitDCteAutEjb.update(tramitDCteAut);
  }


  public void delete(HttpServletRequest request, TramitDCteAut tramitDCteAut) throws I18NException {
    tramitDCteAutEjb.delete(tramitDCteAut);
  }

} // Final de Classe

