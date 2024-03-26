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
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TramitJConsentWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.persistence.TramitJConsentJPA;
import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un TramitJConsent
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tramitJConsent")
@SessionAttributes(types = { TramitJConsentForm.class, TramitJConsentFilterForm.class })
public class TramitJConsentController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<TramitJConsent, java.lang.Long, TramitJConsentForm> implements TramitJConsentFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitJConsentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitJConsentService tramitJConsentEjb;

  @Autowired
  private TramitJConsentWebValidator tramitJConsentWebValidator;

  @Autowired
  protected TramitJConsentRefList tramitJConsentRefList;

  // References 
  @Autowired
  protected TramitAPersAutRefList tramitAPersAutRefList;

  /**
   * Llistat de totes TramitJConsent
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TramitJConsentFilterForm ff;
    ff = (TramitJConsentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TramitJConsent de forma paginada
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
    llistat(mav, request, getTramitJConsentFilterForm(pagina, mav, request));
    return mav;
  }

  public TramitJConsentFilterForm getTramitJConsentFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TramitJConsentFilterForm tramitJConsentFilterForm;
    tramitJConsentFilterForm = (TramitJConsentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tramitJConsentFilterForm == null) {
      tramitJConsentFilterForm = new TramitJConsentFilterForm();
      tramitJConsentFilterForm.setContexte(getContextWeb());
      tramitJConsentFilterForm.setEntityNameCode(getEntityNameCode());
      tramitJConsentFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tramitJConsentFilterForm.setNou(true);
    } else {
      tramitJConsentFilterForm.setNou(false);
    }
    tramitJConsentFilterForm.setPage(pagina == null ? 1 : pagina);
    return tramitJConsentFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TramitJConsent de forma paginada
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
      @ModelAttribute TramitJConsentFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTramitJConsentFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TramitJConsent de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TramitJConsent> llistat(ModelAndView mav, HttpServletRequest request,
     TramitJConsentFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TramitJConsent> tramitJConsent = processarLlistat(tramitJConsentEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tramitJConsentItems", tramitJConsent);

    mav.addObject("tramitJConsentFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tramitJConsent, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tramitJConsent);

    return tramitJConsent;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TramitJConsentFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TramitJConsent> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field consentiment
    {
      _listSKV = getReferenceListForConsentiment(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForConsentiment(_tmp);
      if (filterForm.getGroupByFields().contains(CONSENTIMENT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONSENTIMENT, false);
      };
    }

    // Field consentimentadjunt
    {
      _listSKV = getReferenceListForConsentimentadjunt(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForConsentimentadjunt(_tmp);
      if (filterForm.getGroupByFields().contains(CONSENTIMENTADJUNT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONSENTIMENTADJUNT, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TramitJConsentFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TramitJConsent> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TRAMITJCONSENT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TRAMITID, filterForm.getMapOfTramitAPersAutForTramitid());
    __mapping.put(CONSENTIMENT, filterForm.getMapOfValuesForConsentiment());
    __mapping.put(CONSENTIMENTADJUNT, filterForm.getMapOfValuesForConsentimentadjunt());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TramitJConsent
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTramitJConsentGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TramitJConsentForm tramitJConsentForm = getTramitJConsentForm(null, false, request, mav);
    mav.addObject("tramitJConsentForm" ,tramitJConsentForm);
    fillReferencesForForm(tramitJConsentForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TramitJConsentForm getTramitJConsentForm(TramitJConsentJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TramitJConsentForm tramitJConsentForm;
    if(_jpa == null) {
      tramitJConsentForm = new TramitJConsentForm(new TramitJConsentJPA(), true);
    } else {
      tramitJConsentForm = new TramitJConsentForm(_jpa, false);
      tramitJConsentForm.setView(__isView);
    }
    tramitJConsentForm.setContexte(getContextWeb());
    tramitJConsentForm.setEntityNameCode(getEntityNameCode());
    tramitJConsentForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tramitJConsentForm;
  }

  public void fillReferencesForForm(TramitJConsentForm tramitJConsentForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tramitJConsentForm.getListOfTramitAPersAutForTramitid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTramitid(request, mav, tramitJConsentForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitJConsentForm.setListOfTramitAPersAutForTramitid(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitJConsentForm.getListOfValuesForConsentiment() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConsentiment(request, mav, tramitJConsentForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitJConsentForm.setListOfValuesForConsentiment(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tramitJConsentForm.getListOfValuesForConsentimentadjunt() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConsentimentadjunt(request, mav, tramitJConsentForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tramitJConsentForm.setListOfValuesForConsentimentadjunt(_listSKV);
    }
    
  }

  /**
   * Guardar un nou TramitJConsent
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTramitJConsentPost(@ModelAttribute TramitJConsentForm tramitJConsentForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TramitJConsentJPA tramitJConsent = tramitJConsentForm.getTramitJConsent();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, tramitJConsent, tramitJConsentForm); // FILE
      preValidate(request, tramitJConsentForm, result);
      getWebValidator().validate(tramitJConsentForm, result);
      postValidate(request,tramitJConsentForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitJConsent = create(request, tramitJConsent);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", tramitJConsent.getConsentid());
        tramitJConsentForm.setTramitJConsent(tramitJConsent);
        return getRedirectWhenCreated(request, tramitJConsentForm);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{consentid}", method = RequestMethod.GET)
  public ModelAndView veureTramitJConsentGet(@PathVariable("consentid") java.lang.Long consentid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitJConsentGet(consentid,
        request, response, true);
  }


  protected ModelAndView editAndViewTramitJConsentGet(@PathVariable("consentid") java.lang.Long consentid,
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
    TramitJConsentJPA tramitJConsent = findByPrimaryKey(request, consentid);

    if (tramitJConsent == null) {
      createMessageWarning(request, "error.notfound", consentid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, consentid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TramitJConsentForm tramitJConsentForm = getTramitJConsentForm(tramitJConsent, __isView, request, mav);
      tramitJConsentForm.setView(__isView);
      if(__isView) {
        tramitJConsentForm.setAllFieldsReadOnly(ALL_TRAMITJCONSENT_FIELDS);
        tramitJConsentForm.setSaveButtonVisible(false);
        tramitJConsentForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tramitJConsentForm, request, mav);
      mav.addObject("tramitJConsentForm", tramitJConsentForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TramitJConsent existent
   */
  @RequestMapping(value = "/{consentid}/edit", method = RequestMethod.GET)
  public ModelAndView editarTramitJConsentGet(@PathVariable("consentid") java.lang.Long consentid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitJConsentGet(consentid,
        request, response, false);
  }



  /**
   * Editar un TramitJConsent existent
   */
  @RequestMapping(value = "/{consentid}/edit", method = RequestMethod.POST)
  public String editarTramitJConsentPost(@ModelAttribute TramitJConsentForm tramitJConsentForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TramitJConsentJPA tramitJConsent = tramitJConsentForm.getTramitJConsent();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, tramitJConsent, tramitJConsentForm); // FILE
      preValidate(request, tramitJConsentForm, result);
      getWebValidator().validate(tramitJConsentForm, result);
      postValidate(request, tramitJConsentForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitJConsent = update(request, tramitJConsent);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", tramitJConsent.getConsentid());
        status.setComplete();
        return getRedirectWhenModified(request, tramitJConsentForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tramitJConsent.getConsentid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tramitJConsentForm, __e);
    }

  }


  /**
   * Eliminar un TramitJConsent existent
   */
  @RequestMapping(value = "/{consentid}/delete")
  public String eliminarTramitJConsent(@PathVariable("consentid") java.lang.Long consentid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TramitJConsent tramitJConsent = this.findByPrimaryKey(request, consentid);
      if (tramitJConsent == null) {
        String __msg = createMessageError(request, "error.notfound", consentid);
        return getRedirectWhenDelete(request, consentid, new Exception(__msg));
      } else {
        delete(request, tramitJConsent);
        createMessageSuccess(request, "success.deleted", consentid);
        return getRedirectWhenDelete(request, consentid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", consentid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, consentid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TramitJConsentFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTramitJConsent(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __consentid, Throwable e) {
    java.lang.Long consentid = (java.lang.Long)__consentid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (consentid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(consentid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tramitJConsent.tramitJConsent";
  }

  public String getEntityNameCodePlural() {
    return "tramitJConsent.tramitJConsent.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tramitJConsent.consentid");
  }

  @InitBinder("tramitJConsentFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tramitJConsentForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "tramitJConsent.consentid");
  }

  public TramitJConsentWebValidator getWebValidator() {
    return tramitJConsentWebValidator;
  }


  public void setWebValidator(TramitJConsentWebValidator __val) {
    if (__val != null) {
      this.tramitJConsentWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TramitJConsent
   */
  @RequestMapping(value = "/{consentid}/cancel")
  public String cancelTramitJConsent(@PathVariable("consentid") java.lang.Long consentid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, consentid);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, TramitJConsent tramitJConsent,
      TramitJConsentForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getAdjuntID(), form.isAdjuntIDDelete(),
        form.isNou()? null : tramitJConsent.getAdjunt());
    ((TramitJConsentJPA)tramitJConsent).setAdjunt(f);
    if (f != null) { 
      tramitJConsent.setAdjuntID(f.getFitxerID());
    } else {
      tramitJConsent.setAdjuntID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(TramitJConsent tramitJConsent) {
    deleteFile(tramitJConsent.getAdjuntID());
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
       ModelAndView mav, TramitJConsentForm tramitJConsentForm, Where where)  throws I18NException {
    if (tramitJConsentForm.isHiddenField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tramitJConsentForm.isReadOnlyField(TRAMITID)) {
      _where = TramitAPersAutFields.TRAMITID.equal(tramitJConsentForm.getTramitJConsent().getTramitid());
    }
    return getReferenceListForTramitid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTramitid(HttpServletRequest request,
       ModelAndView mav, TramitJConsentFilterForm tramitJConsentFilterForm,
       List<TramitJConsent> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitJConsentFilterForm.isHiddenField(TRAMITID)
       && !tramitJConsentFilterForm.isGroupByField(TRAMITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TRAMITID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TramitJConsent _item : list) {
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


  public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request,
       ModelAndView mav, TramitJConsentForm tramitJConsentForm, Where where)  throws I18NException {
    if (tramitJConsentForm.isHiddenField(CONSENTIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForConsentiment(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request,
       ModelAndView mav, TramitJConsentFilterForm tramitJConsentFilterForm,
       List<TramitJConsent> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitJConsentFilterForm.isHiddenField(CONSENTIMENT)
       && !tramitJConsentFilterForm.isGroupByField(CONSENTIMENT)
       && !tramitJConsentFilterForm.isFilterByField(CONSENTIMENT)) {
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


  public List<StringKeyValue> getReferenceListForConsentimentadjunt(HttpServletRequest request,
       ModelAndView mav, TramitJConsentForm tramitJConsentForm, Where where)  throws I18NException {
    if (tramitJConsentForm.isHiddenField(CONSENTIMENTADJUNT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForConsentimentadjunt(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForConsentimentadjunt(HttpServletRequest request,
       ModelAndView mav, TramitJConsentFilterForm tramitJConsentFilterForm,
       List<TramitJConsent> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tramitJConsentFilterForm.isHiddenField(CONSENTIMENTADJUNT)
       && !tramitJConsentFilterForm.isGroupByField(CONSENTIMENTADJUNT)
       && !tramitJConsentFilterForm.isFilterByField(CONSENTIMENTADJUNT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForConsentimentadjunt(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForConsentimentadjunt(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("adjunt" , "adjunt"));
    __tmp.add(new StringKeyValue("publicat" , "publicat"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,TramitJConsentForm tramitJConsentForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TramitJConsentForm tramitJConsentForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TramitJConsentFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TramitJConsentFilterForm filterForm,  List<TramitJConsent> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TramitJConsentForm tramitJConsentForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TramitJConsentForm tramitJConsentForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long consentid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long consentid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tramitJConsentFormWebDB";
  }

  public String getTileList() {
    return "tramitJConsentListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TramitJConsent_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TramitJConsentJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long consentid) throws I18NException {
    return (TramitJConsentJPA) tramitJConsentEjb.findByPrimaryKey(consentid);
  }


  public TramitJConsentJPA create(HttpServletRequest request, TramitJConsentJPA tramitJConsent)
    throws I18NException, I18NValidationException {
    return (TramitJConsentJPA) tramitJConsentEjb.create(tramitJConsent);
  }


  public TramitJConsentJPA update(HttpServletRequest request, TramitJConsentJPA tramitJConsent)
    throws I18NException, I18NValidationException {
    return (TramitJConsentJPA) tramitJConsentEjb.update(tramitJConsent);
  }


  public void delete(HttpServletRequest request, TramitJConsent tramitJConsent) throws I18NException {
    tramitJConsentEjb.delete(tramitJConsent);
  }

} // Final de Classe

