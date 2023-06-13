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
import org.fundaciobit.pinbaladmin.back.form.webdb.CampSolicitudForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.CampSolicitudWebValidator;

import org.fundaciobit.pinbaladmin.persistence.CampSolicitudJPA;
import org.fundaciobit.pinbaladmin.model.entity.CampSolicitud;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un CampSolicitud
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/campSolicitud")
@SessionAttributes(types = { CampSolicitudForm.class, CampSolicitudFilterForm.class })
public class CampSolicitudController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<CampSolicitud, java.lang.Long> implements CampSolicitudFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.CampSolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.CampSolicitudService campSolicitudEjb;

  @Autowired
  private CampSolicitudWebValidator campSolicitudWebValidator;

  @Autowired
  protected CampSolicitudRefList campSolicitudRefList;

  // References 
  @Autowired
  protected CampFormulariRefList campFormulariRefList;

  // References 
  @Autowired
  protected SolicitudServeiRefList solicitudServeiRefList;

  /**
   * Llistat de totes CampSolicitud
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    CampSolicitudFilterForm ff;
    ff = (CampSolicitudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar CampSolicitud de forma paginada
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
    llistat(mav, request, getCampSolicitudFilterForm(pagina, mav, request));
    return mav;
  }

  public CampSolicitudFilterForm getCampSolicitudFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    CampSolicitudFilterForm campSolicitudFilterForm;
    campSolicitudFilterForm = (CampSolicitudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(campSolicitudFilterForm == null) {
      campSolicitudFilterForm = new CampSolicitudFilterForm();
      campSolicitudFilterForm.setContexte(getContextWeb());
      campSolicitudFilterForm.setEntityNameCode(getEntityNameCode());
      campSolicitudFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      campSolicitudFilterForm.setNou(true);
    } else {
      campSolicitudFilterForm.setNou(false);
    }
    campSolicitudFilterForm.setPage(pagina == null ? 1 : pagina);
    return campSolicitudFilterForm;
  }

  /**
   * Segona i següent peticions per llistar CampSolicitud de forma paginada
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
      @ModelAttribute CampSolicitudFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getCampSolicitudFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de CampSolicitud de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<CampSolicitud> llistat(ModelAndView mav, HttpServletRequest request,
     CampSolicitudFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<CampSolicitud> campSolicitud = processarLlistat(campSolicitudEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("campSolicitudItems", campSolicitud);

    mav.addObject("campSolicitudFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, campSolicitud, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, campSolicitud);

    return campSolicitud;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(CampSolicitudFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<CampSolicitud> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field campFormulariID
    {
      _listSKV = getReferenceListForCampFormulariID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfCampFormulariForCampFormulariID(_tmp);
      if (filterForm.getGroupByFields().contains(CAMPFORMULARIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CAMPFORMULARIID, false);
      };
    }

    // Field solicitudServeiID
    {
      _listSKV = getReferenceListForSolicitudServeiID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfSolicitudServeiForSolicitudServeiID(_tmp);
      if (filterForm.getGroupByFields().contains(SOLICITUDSERVEIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SOLICITUDSERVEIID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    CampSolicitudFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<CampSolicitud> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_CAMPSOLICITUD_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(CAMPFORMULARIID, filterForm.getMapOfCampFormulariForCampFormulariID());
    __mapping.put(SOLICITUDSERVEIID, filterForm.getMapOfSolicitudServeiForSolicitudServeiID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou CampSolicitud
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearCampSolicitudGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    CampSolicitudForm campSolicitudForm = getCampSolicitudForm(null, false, request, mav);
    mav.addObject("campSolicitudForm" ,campSolicitudForm);
    fillReferencesForForm(campSolicitudForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public CampSolicitudForm getCampSolicitudForm(CampSolicitudJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    CampSolicitudForm campSolicitudForm;
    if(_jpa == null) {
      campSolicitudForm = new CampSolicitudForm(new CampSolicitudJPA(), true);
    } else {
      campSolicitudForm = new CampSolicitudForm(_jpa, false);
      campSolicitudForm.setView(__isView);
    }
    campSolicitudForm.setContexte(getContextWeb());
    campSolicitudForm.setEntityNameCode(getEntityNameCode());
    campSolicitudForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return campSolicitudForm;
  }

  public void fillReferencesForForm(CampSolicitudForm campSolicitudForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (campSolicitudForm.getListOfCampFormulariForCampFormulariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForCampFormulariID(request, mav, campSolicitudForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      campSolicitudForm.setListOfCampFormulariForCampFormulariID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (campSolicitudForm.getListOfSolicitudServeiForSolicitudServeiID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSolicitudServeiID(request, mav, campSolicitudForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      campSolicitudForm.setListOfSolicitudServeiForSolicitudServeiID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou CampSolicitud
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearCampSolicitudPost(@ModelAttribute CampSolicitudForm campSolicitudForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    CampSolicitudJPA campSolicitud = campSolicitudForm.getCampSolicitud();

    try {
      preValidate(request, campSolicitudForm, result);
      getWebValidator().validate(campSolicitudForm, result);
      postValidate(request,campSolicitudForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        campSolicitud = create(request, campSolicitud);
        createMessageSuccess(request, "success.creation", campSolicitud.getCampSolicitudID());
        campSolicitudForm.setCampSolicitud(campSolicitud);
        return getRedirectWhenCreated(request, campSolicitudForm);
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

  @RequestMapping(value = "/view/{campSolicitudID}", method = RequestMethod.GET)
  public ModelAndView veureCampSolicitudGet(@PathVariable("campSolicitudID") java.lang.Long campSolicitudID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCampSolicitudGet(campSolicitudID,
        request, response, true);
  }


  protected ModelAndView editAndViewCampSolicitudGet(@PathVariable("campSolicitudID") java.lang.Long campSolicitudID,
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
    CampSolicitudJPA campSolicitud = findByPrimaryKey(request, campSolicitudID);

    if (campSolicitud == null) {
      createMessageWarning(request, "error.notfound", campSolicitudID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, campSolicitudID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      CampSolicitudForm campSolicitudForm = getCampSolicitudForm(campSolicitud, __isView, request, mav);
      campSolicitudForm.setView(__isView);
      if(__isView) {
        campSolicitudForm.setAllFieldsReadOnly(ALL_CAMPSOLICITUD_FIELDS);
        campSolicitudForm.setSaveButtonVisible(false);
        campSolicitudForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(campSolicitudForm, request, mav);
      mav.addObject("campSolicitudForm", campSolicitudForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un CampSolicitud existent
   */
  @RequestMapping(value = "/{campSolicitudID}/edit", method = RequestMethod.GET)
  public ModelAndView editarCampSolicitudGet(@PathVariable("campSolicitudID") java.lang.Long campSolicitudID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCampSolicitudGet(campSolicitudID,
        request, response, false);
  }



  /**
   * Editar un CampSolicitud existent
   */
  @RequestMapping(value = "/{campSolicitudID}/edit", method = RequestMethod.POST)
  public String editarCampSolicitudPost(@ModelAttribute CampSolicitudForm campSolicitudForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    CampSolicitudJPA campSolicitud = campSolicitudForm.getCampSolicitud();

    try {
      preValidate(request, campSolicitudForm, result);
      getWebValidator().validate(campSolicitudForm, result);
      postValidate(request, campSolicitudForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        campSolicitud = update(request, campSolicitud);
        createMessageSuccess(request, "success.modification", campSolicitud.getCampSolicitudID());
        status.setComplete();
        return getRedirectWhenModified(request, campSolicitudForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          campSolicitud.getCampSolicitudID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, campSolicitudForm, __e);
    }

  }


  /**
   * Eliminar un CampSolicitud existent
   */
  @RequestMapping(value = "/{campSolicitudID}/delete")
  public String eliminarCampSolicitud(@PathVariable("campSolicitudID") java.lang.Long campSolicitudID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      CampSolicitud campSolicitud = this.findByPrimaryKey(request, campSolicitudID);
      if (campSolicitud == null) {
        String __msg = createMessageError(request, "error.notfound", campSolicitudID);
        return getRedirectWhenDelete(request, campSolicitudID, new Exception(__msg));
      } else {
        delete(request, campSolicitud);
        createMessageSuccess(request, "success.deleted", campSolicitudID);
        return getRedirectWhenDelete(request, campSolicitudID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", campSolicitudID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, campSolicitudID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute CampSolicitudFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarCampSolicitud(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __campSolicitudID, Throwable e) {
    java.lang.Long campSolicitudID = (java.lang.Long)__campSolicitudID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (campSolicitudID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(campSolicitudID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "campSolicitud.campSolicitud";
  }

  public String getEntityNameCodePlural() {
    return "campSolicitud.campSolicitud.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("campSolicitud.campSolicitudID");
  }

  @InitBinder("campSolicitudFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("campSolicitudForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "campSolicitud.campSolicitudID");
  }

  public CampSolicitudWebValidator getWebValidator() {
    return campSolicitudWebValidator;
  }


  public void setWebValidator(CampSolicitudWebValidator __val) {
    if (__val != null) {
      this.campSolicitudWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de CampSolicitud
   */
  @RequestMapping(value = "/{campSolicitudID}/cancel")
  public String cancelCampSolicitud(@PathVariable("campSolicitudID") java.lang.Long campSolicitudID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, campSolicitudID);
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


  public List<StringKeyValue> getReferenceListForCampFormulariID(HttpServletRequest request,
       ModelAndView mav, CampSolicitudForm campSolicitudForm, Where where)  throws I18NException {
    if (campSolicitudForm.isHiddenField(CAMPFORMULARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (campSolicitudForm.isReadOnlyField(CAMPFORMULARIID)) {
      _where = CampFormulariFields.CAMPFORMULARIID.equal(campSolicitudForm.getCampSolicitud().getCampFormulariID());
    }
    return getReferenceListForCampFormulariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForCampFormulariID(HttpServletRequest request,
       ModelAndView mav, CampSolicitudFilterForm campSolicitudFilterForm,
       List<CampSolicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (campSolicitudFilterForm.isHiddenField(CAMPFORMULARIID)
       && !campSolicitudFilterForm.isGroupByField(CAMPFORMULARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(CAMPFORMULARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (CampSolicitud _item : list) {
        _pkList.add(_item.getCampFormulariID());
        }
        _w = CampFormulariFields.CAMPFORMULARIID.in(_pkList);
      }
    return getReferenceListForCampFormulariID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForCampFormulariID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return campFormulariRefList.getReferenceList(CampFormulariFields.CAMPFORMULARIID, where );
  }


  public List<StringKeyValue> getReferenceListForSolicitudServeiID(HttpServletRequest request,
       ModelAndView mav, CampSolicitudForm campSolicitudForm, Where where)  throws I18NException {
    if (campSolicitudForm.isHiddenField(SOLICITUDSERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (campSolicitudForm.isReadOnlyField(SOLICITUDSERVEIID)) {
      _where = SolicitudServeiFields.ID.equal(campSolicitudForm.getCampSolicitud().getSolicitudServeiID());
    }
    return getReferenceListForSolicitudServeiID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSolicitudServeiID(HttpServletRequest request,
       ModelAndView mav, CampSolicitudFilterForm campSolicitudFilterForm,
       List<CampSolicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (campSolicitudFilterForm.isHiddenField(SOLICITUDSERVEIID)
       && !campSolicitudFilterForm.isGroupByField(SOLICITUDSERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SOLICITUDSERVEIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (CampSolicitud _item : list) {
        _pkList.add(_item.getSolicitudServeiID());
        }
        _w = SolicitudServeiFields.ID.in(_pkList);
      }
    return getReferenceListForSolicitudServeiID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSolicitudServeiID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return solicitudServeiRefList.getReferenceList(SolicitudServeiFields.ID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,CampSolicitudForm campSolicitudForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,CampSolicitudForm campSolicitudForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, CampSolicitudFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, CampSolicitudFilterForm filterForm,  List<CampSolicitud> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, CampSolicitudForm campSolicitudForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, CampSolicitudForm campSolicitudForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long campSolicitudID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long campSolicitudID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "campSolicitudFormWebDB";
  }

  public String getTileList() {
    return "campSolicitudListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "CampSolicitudWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public CampSolicitudJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long campSolicitudID) throws I18NException {
    return (CampSolicitudJPA) campSolicitudEjb.findByPrimaryKey(campSolicitudID);
  }


  public CampSolicitudJPA create(HttpServletRequest request, CampSolicitudJPA campSolicitud)
    throws I18NException, I18NValidationException {
    return (CampSolicitudJPA) campSolicitudEjb.create(campSolicitud);
  }


  public CampSolicitudJPA update(HttpServletRequest request, CampSolicitudJPA campSolicitud)
    throws I18NException, I18NValidationException {
    return (CampSolicitudJPA) campSolicitudEjb.update(campSolicitud);
  }


  public void delete(HttpServletRequest request, CampSolicitud campSolicitud) throws I18NException {
    campSolicitudEjb.delete(campSolicitud);
  }

} // Final de Classe

