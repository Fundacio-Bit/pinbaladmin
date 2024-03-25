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
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TramitCDadesCesiWebValidator;

import org.fundaciobit.pinbaladmin.persistence.TramitCDadesCesiJPA;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un TramitCDadesCesi
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tramitCDadesCesi")
@SessionAttributes(types = { TramitCDadesCesiForm.class, TramitCDadesCesiFilterForm.class })
public class TramitCDadesCesiController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<TramitCDadesCesi, java.lang.Long> implements TramitCDadesCesiFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitCDadesCesiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitCDadesCesiService tramitCDadesCesiEjb;

  @Autowired
  private TramitCDadesCesiWebValidator tramitCDadesCesiWebValidator;

  @Autowired
  protected TramitCDadesCesiRefList tramitCDadesCesiRefList;

  // References 
  @Autowired
  protected TramitAPersAutRefList tramitAPersAutRefList;

  // References 
  @Autowired
  protected OrganRefList organRefList;

  /**
   * Llistat de totes TramitCDadesCesi
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TramitCDadesCesiFilterForm ff;
    ff = (TramitCDadesCesiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TramitCDadesCesi de forma paginada
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
    llistat(mav, request, getTramitCDadesCesiFilterForm(pagina, mav, request));
    return mav;
  }

  public TramitCDadesCesiFilterForm getTramitCDadesCesiFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TramitCDadesCesiFilterForm tramitCDadesCesiFilterForm;
    tramitCDadesCesiFilterForm = (TramitCDadesCesiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tramitCDadesCesiFilterForm == null) {
      tramitCDadesCesiFilterForm = new TramitCDadesCesiFilterForm();
      tramitCDadesCesiFilterForm.setContexte(getContextWeb());
      tramitCDadesCesiFilterForm.setEntityNameCode(getEntityNameCode());
      tramitCDadesCesiFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tramitCDadesCesiFilterForm.setNou(true);
    } else {
      tramitCDadesCesiFilterForm.setNou(false);
    }
    tramitCDadesCesiFilterForm.setPage(pagina == null ? 1 : pagina);
    return tramitCDadesCesiFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TramitCDadesCesi de forma paginada
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
      @ModelAttribute TramitCDadesCesiFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTramitCDadesCesiFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TramitCDadesCesi de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TramitCDadesCesi> llistat(ModelAndView mav, HttpServletRequest request,
     TramitCDadesCesiFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TramitCDadesCesi> tramitCDadesCesi = processarLlistat(tramitCDadesCesiEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tramitCDadesCesiItems", tramitCDadesCesi);

    mav.addObject("tramitCDadesCesiFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tramitCDadesCesi, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tramitCDadesCesi);

    return tramitCDadesCesi;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TramitCDadesCesiFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TramitCDadesCesi> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field organID
    {
      _listSKV = getReferenceListForOrganID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfOrganForOrganID(_tmp);
      if (filterForm.getGroupByFields().contains(ORGANID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ORGANID, false);
      };
    }

    // Field organArrelID
    {
      _listSKV = getReferenceListForOrganArrelID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfOrganForOrganArrelID(_tmp);
      if (filterForm.getGroupByFields().contains(ORGANARRELID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ORGANARRELID, false);
      };
    }

    // Field denominacio
    {
      _listSKV = getReferenceListForDenominacio(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForDenominacio(_tmp);
      if (filterForm.getGroupByFields().contains(DENOMINACIO)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, DENOMINACIO, false);
      };
    }

    // Field municipi
    {
      _listSKV = getReferenceListForMunicipi(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForMunicipi(_tmp);
      if (filterForm.getGroupByFields().contains(MUNICIPI)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, MUNICIPI, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TramitCDadesCesiFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TramitCDadesCesi> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TRAMITCDADESCESI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TRAMITID, filterForm.getMapOfTramitAPersAutForTramitid());
    __mapping.put(ORGANID, filterForm.getMapOfOrganForOrganID());
    __mapping.put(ORGANARRELID, filterForm.getMapOfOrganForOrganArrelID());
    __mapping.put(DENOMINACIO, filterForm.getMapOfValuesForDenominacio());
    __mapping.put(MUNICIPI, filterForm.getMapOfValuesForMunicipi());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TramitCDadesCesi
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTramitCDadesCesiGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TramitCDadesCesiForm tramitCDadesCesiForm = getTramitCDadesCesiForm(null, false, request, mav);
    mav.addObject("tramitCDadesCesiForm" ,tramitCDadesCesiForm);
    fillReferencesForForm(tramitCDadesCesiForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TramitCDadesCesiForm getTramitCDadesCesiForm(TramitCDadesCesiJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TramitCDadesCesiForm tramitCDadesCesiForm;
    if(_jpa == null) {
      tramitCDadesCesiForm = new TramitCDadesCesiForm(new TramitCDadesCesiJPA(), true);
    } else {
      tramitCDadesCesiForm = new TramitCDadesCesiForm(_jpa, false);
      tramitCDadesCesiForm.setView(__isView);
    }
    tramitCDadesCesiForm.setContexte(getContextWeb());
    tramitCDadesCesiForm.setEntityNameCode(getEntityNameCode());
    tramitCDadesCesiForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tramitCDadesCesiForm;
  }

  public void fillReferencesForForm(TramitCDadesCesiForm tramitCDadesCesiForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tramitCDadesCesiForm.getListOfTramitAPersAutForTramitid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTramitid(request, mav, tramitCDadesCesiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitCDadesCesiForm.setListOfTramitAPersAutForTramitid(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitCDadesCesiForm.getListOfOrganForOrganID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForOrganID(request, mav, tramitCDadesCesiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitCDadesCesiForm.setListOfOrganForOrganID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitCDadesCesiForm.getListOfOrganForOrganArrelID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForOrganArrelID(request, mav, tramitCDadesCesiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitCDadesCesiForm.setListOfOrganForOrganArrelID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitCDadesCesiForm.getListOfValuesForDenominacio() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForDenominacio(request, mav, tramitCDadesCesiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitCDadesCesiForm.setListOfValuesForDenominacio(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitCDadesCesiForm.getListOfValuesForMunicipi() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForMunicipi(request, mav, tramitCDadesCesiForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitCDadesCesiForm.setListOfValuesForMunicipi(_listSKV);
    }
    
  }

  /**
   * Guardar un nou TramitCDadesCesi
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTramitCDadesCesiPost(@ModelAttribute TramitCDadesCesiForm tramitCDadesCesiForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TramitCDadesCesiJPA tramitCDadesCesi = tramitCDadesCesiForm.getTramitCDadesCesi();

    try {
      preValidate(request, tramitCDadesCesiForm, result);
      getWebValidator().validate(tramitCDadesCesiForm, result);
      postValidate(request,tramitCDadesCesiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitCDadesCesi = create(request, tramitCDadesCesi);
        createMessageSuccess(request, "success.creation", tramitCDadesCesi.getDadescesiid());
        tramitCDadesCesiForm.setTramitCDadesCesi(tramitCDadesCesi);
        return getRedirectWhenCreated(request, tramitCDadesCesiForm);
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

  @RequestMapping(value = "/view/{dadescesiid}", method = RequestMethod.GET)
  public ModelAndView veureTramitCDadesCesiGet(@PathVariable("dadescesiid") java.lang.Long dadescesiid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitCDadesCesiGet(dadescesiid,
        request, response, true);
  }


  protected ModelAndView editAndViewTramitCDadesCesiGet(@PathVariable("dadescesiid") java.lang.Long dadescesiid,
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
    TramitCDadesCesiJPA tramitCDadesCesi = findByPrimaryKey(request, dadescesiid);

    if (tramitCDadesCesi == null) {
      createMessageWarning(request, "error.notfound", dadescesiid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, dadescesiid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TramitCDadesCesiForm tramitCDadesCesiForm = getTramitCDadesCesiForm(tramitCDadesCesi, __isView, request, mav);
      tramitCDadesCesiForm.setView(__isView);
      if(__isView) {
        tramitCDadesCesiForm.setAllFieldsReadOnly(ALL_TRAMITCDADESCESI_FIELDS);
        tramitCDadesCesiForm.setSaveButtonVisible(false);
        tramitCDadesCesiForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tramitCDadesCesiForm, request, mav);
      mav.addObject("tramitCDadesCesiForm", tramitCDadesCesiForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TramitCDadesCesi existent
   */
  @RequestMapping(value = "/{dadescesiid}/edit", method = RequestMethod.GET)
  public ModelAndView editarTramitCDadesCesiGet(@PathVariable("dadescesiid") java.lang.Long dadescesiid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitCDadesCesiGet(dadescesiid,
        request, response, false);
  }



  /**
   * Editar un TramitCDadesCesi existent
   */
  @RequestMapping(value = "/{dadescesiid}/edit", method = RequestMethod.POST)
  public String editarTramitCDadesCesiPost(@ModelAttribute TramitCDadesCesiForm tramitCDadesCesiForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TramitCDadesCesiJPA tramitCDadesCesi = tramitCDadesCesiForm.getTramitCDadesCesi();

    try {
      preValidate(request, tramitCDadesCesiForm, result);
      getWebValidator().validate(tramitCDadesCesiForm, result);
      postValidate(request, tramitCDadesCesiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitCDadesCesi = update(request, tramitCDadesCesi);
        createMessageSuccess(request, "success.modification", tramitCDadesCesi.getDadescesiid());
        status.setComplete();
        return getRedirectWhenModified(request, tramitCDadesCesiForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tramitCDadesCesi.getDadescesiid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tramitCDadesCesiForm, __e);
    }

  }


  /**
   * Eliminar un TramitCDadesCesi existent
   */
  @RequestMapping(value = "/{dadescesiid}/delete")
  public String eliminarTramitCDadesCesi(@PathVariable("dadescesiid") java.lang.Long dadescesiid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TramitCDadesCesi tramitCDadesCesi = this.findByPrimaryKey(request, dadescesiid);
      if (tramitCDadesCesi == null) {
        String __msg = createMessageError(request, "error.notfound", dadescesiid);
        return getRedirectWhenDelete(request, dadescesiid, new Exception(__msg));
      } else {
        delete(request, tramitCDadesCesi);
        createMessageSuccess(request, "success.deleted", dadescesiid);
        return getRedirectWhenDelete(request, dadescesiid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", dadescesiid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, dadescesiid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TramitCDadesCesiFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTramitCDadesCesi(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __dadescesiid, Throwable e) {
    java.lang.Long dadescesiid = (java.lang.Long)__dadescesiid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (dadescesiid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(dadescesiid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tramitCDadesCesi.tramitCDadesCesi";
  }

  public String getEntityNameCodePlural() {
    return "tramitCDadesCesi.tramitCDadesCesi.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tramitCDadesCesi.dadescesiid");
  }

  @InitBinder("tramitCDadesCesiFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tramitCDadesCesiForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "tramitCDadesCesi.dadescesiid");
  }

  public TramitCDadesCesiWebValidator getWebValidator() {
    return tramitCDadesCesiWebValidator;
  }


  public void setWebValidator(TramitCDadesCesiWebValidator __val) {
    if (__val != null) {
      this.tramitCDadesCesiWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TramitCDadesCesi
   */
  @RequestMapping(value = "/{dadescesiid}/cancel")
  public String cancelTramitCDadesCesi(@PathVariable("dadescesiid") java.lang.Long dadescesiid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, dadescesiid);
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
       ModelAndView mav, TramitCDadesCesiForm tramitCDadesCesiForm, Where where)  throws I18NException {
    if (tramitCDadesCesiForm.isHiddenField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tramitCDadesCesiForm.isReadOnlyField(TRAMITID)) {
      _where = TramitAPersAutFields.TRAMITID.equal(tramitCDadesCesiForm.getTramitCDadesCesi().getTramitid());
    }
    return getReferenceListForTramitid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTramitid(HttpServletRequest request,
       ModelAndView mav, TramitCDadesCesiFilterForm tramitCDadesCesiFilterForm,
       List<TramitCDadesCesi> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitCDadesCesiFilterForm.isHiddenField(TRAMITID)
       && !tramitCDadesCesiFilterForm.isGroupByField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TRAMITID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TramitCDadesCesi _item : list) {
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


  public List<StringKeyValue> getReferenceListForOrganID(HttpServletRequest request,
       ModelAndView mav, TramitCDadesCesiForm tramitCDadesCesiForm, Where where)  throws I18NException {
    if (tramitCDadesCesiForm.isHiddenField(ORGANID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tramitCDadesCesiForm.isReadOnlyField(ORGANID)) {
      _where = OrganFields.ORGANID.equal(tramitCDadesCesiForm.getTramitCDadesCesi().getOrganID());
    }
    return getReferenceListForOrganID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForOrganID(HttpServletRequest request,
       ModelAndView mav, TramitCDadesCesiFilterForm tramitCDadesCesiFilterForm,
       List<TramitCDadesCesi> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitCDadesCesiFilterForm.isHiddenField(ORGANID)
       && !tramitCDadesCesiFilterForm.isGroupByField(ORGANID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ORGANID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TramitCDadesCesi _item : list) {
        _pkList.add(_item.getOrganID());
        }
        _w = OrganFields.ORGANID.in(_pkList);
      }
    return getReferenceListForOrganID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForOrganID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return organRefList.getReferenceList(OrganFields.ORGANID, where );
  }


  public List<StringKeyValue> getReferenceListForOrganArrelID(HttpServletRequest request,
       ModelAndView mav, TramitCDadesCesiForm tramitCDadesCesiForm, Where where)  throws I18NException {
    if (tramitCDadesCesiForm.isHiddenField(ORGANARRELID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tramitCDadesCesiForm.isReadOnlyField(ORGANARRELID)) {
      _where = OrganFields.ORGANID.equal(tramitCDadesCesiForm.getTramitCDadesCesi().getOrganArrelID());
    }
    return getReferenceListForOrganArrelID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForOrganArrelID(HttpServletRequest request,
       ModelAndView mav, TramitCDadesCesiFilterForm tramitCDadesCesiFilterForm,
       List<TramitCDadesCesi> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitCDadesCesiFilterForm.isHiddenField(ORGANARRELID)
       && !tramitCDadesCesiFilterForm.isGroupByField(ORGANARRELID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ORGANARRELID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TramitCDadesCesi _item : list) {
        _pkList.add(_item.getOrganArrelID());
        }
        _w = OrganFields.ORGANID.in(_pkList);
      }
    return getReferenceListForOrganArrelID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForOrganArrelID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return organRefList.getReferenceList(OrganFields.ORGANID, where );
  }


  public List<StringKeyValue> getReferenceListForDenominacio(HttpServletRequest request,
       ModelAndView mav, TramitCDadesCesiForm tramitCDadesCesiForm, Where where)  throws I18NException {
    if (tramitCDadesCesiForm.isHiddenField(DENOMINACIO)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForDenominacio(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForDenominacio(HttpServletRequest request,
       ModelAndView mav, TramitCDadesCesiFilterForm tramitCDadesCesiFilterForm,
       List<TramitCDadesCesi> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitCDadesCesiFilterForm.isHiddenField(DENOMINACIO)
       && !tramitCDadesCesiFilterForm.isGroupByField(DENOMINACIO)
       && !tramitCDadesCesiFilterForm.isFilterByField(DENOMINACIO)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForDenominacio(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForDenominacio(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("denom1" , "denom1"));
    __tmp.add(new StringKeyValue("denom2" , "denom2"));
    __tmp.add(new StringKeyValue("denom3" , "denom3"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForMunicipi(HttpServletRequest request,
       ModelAndView mav, TramitCDadesCesiForm tramitCDadesCesiForm, Where where)  throws I18NException {
    if (tramitCDadesCesiForm.isHiddenField(MUNICIPI)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForMunicipi(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForMunicipi(HttpServletRequest request,
       ModelAndView mav, TramitCDadesCesiFilterForm tramitCDadesCesiFilterForm,
       List<TramitCDadesCesi> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitCDadesCesiFilterForm.isHiddenField(MUNICIPI)
       && !tramitCDadesCesiFilterForm.isGroupByField(MUNICIPI)
       && !tramitCDadesCesiFilterForm.isFilterByField(MUNICIPI)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForMunicipi(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForMunicipi(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("palma" , "palma"));
    __tmp.add(new StringKeyValue("inca" , "inca"));
    __tmp.add(new StringKeyValue("manacor" , "manacor"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,TramitCDadesCesiForm tramitCDadesCesiForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TramitCDadesCesiForm tramitCDadesCesiForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TramitCDadesCesiFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TramitCDadesCesiFilterForm filterForm,  List<TramitCDadesCesi> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TramitCDadesCesiForm tramitCDadesCesiForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TramitCDadesCesiForm tramitCDadesCesiForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long dadescesiid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long dadescesiid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tramitCDadesCesiFormWebDB";
  }

  public String getTileList() {
    return "tramitCDadesCesiListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TramitCDadesCesi_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TramitCDadesCesiJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long dadescesiid) throws I18NException {
    return (TramitCDadesCesiJPA) tramitCDadesCesiEjb.findByPrimaryKey(dadescesiid);
  }


  public TramitCDadesCesiJPA create(HttpServletRequest request, TramitCDadesCesiJPA tramitCDadesCesi)
    throws I18NException, I18NValidationException {
    return (TramitCDadesCesiJPA) tramitCDadesCesiEjb.create(tramitCDadesCesi);
  }


  public TramitCDadesCesiJPA update(HttpServletRequest request, TramitCDadesCesiJPA tramitCDadesCesi)
    throws I18NException, I18NValidationException {
    return (TramitCDadesCesiJPA) tramitCDadesCesiEjb.update(tramitCDadesCesi);
  }


  public void delete(HttpServletRequest request, TramitCDadesCesi tramitCDadesCesi) throws I18NException {
    tramitCDadesCesiEjb.delete(tramitCDadesCesi);
  }

} // Final de Classe

