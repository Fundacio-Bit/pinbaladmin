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
import org.fundaciobit.pinbaladmin.back.form.webdb.DepartamentForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.DepartamentWebValidator;

import org.fundaciobit.pinbaladmin.persistence.DepartamentJPA;
import org.fundaciobit.pinbaladmin.model.entity.Departament;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Departament
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/departament")
@SessionAttributes(types = { DepartamentForm.class, DepartamentFilterForm.class })
public class DepartamentController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<Departament, java.lang.Long> implements DepartamentFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DepartamentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DepartamentService departamentEjb;

  @Autowired
  private DepartamentWebValidator departamentWebValidator;

  @Autowired
  protected DepartamentRefList departamentRefList;

  // References 
  @Autowired
  protected AreaRefList areaRefList;

  /**
   * Llistat de totes Departament
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    DepartamentFilterForm ff;
    ff = (DepartamentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Departament de forma paginada
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
    llistat(mav, request, getDepartamentFilterForm(pagina, mav, request));
    return mav;
  }

  public DepartamentFilterForm getDepartamentFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    DepartamentFilterForm departamentFilterForm;
    departamentFilterForm = (DepartamentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(departamentFilterForm == null) {
      departamentFilterForm = new DepartamentFilterForm();
      departamentFilterForm.setContexte(getContextWeb());
      departamentFilterForm.setEntityNameCode(getEntityNameCode());
      departamentFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      departamentFilterForm.setNou(true);
    } else {
      departamentFilterForm.setNou(false);
    }
    departamentFilterForm.setPage(pagina == null ? 1 : pagina);
    return departamentFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Departament de forma paginada
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
      @ModelAttribute DepartamentFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getDepartamentFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Departament de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Departament> llistat(ModelAndView mav, HttpServletRequest request,
     DepartamentFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Departament> departament = processarLlistat(departamentEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("departamentItems", departament);

    mav.addObject("departamentFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, departament, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, departament);

    return departament;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(DepartamentFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Departament> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field areaID
    {
      _listSKV = getReferenceListForAreaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfAreaForAreaID(_tmp);
      if (filterForm.getGroupByFields().contains(AREAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, AREAID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    DepartamentFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Departament> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_DEPARTAMENT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(AREAID, filterForm.getMapOfAreaForAreaID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Departament
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearDepartamentGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    DepartamentForm departamentForm = getDepartamentForm(null, false, request, mav);
    mav.addObject("departamentForm" ,departamentForm);
    fillReferencesForForm(departamentForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public DepartamentForm getDepartamentForm(DepartamentJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    DepartamentForm departamentForm;
    if(_jpa == null) {
      departamentForm = new DepartamentForm(new DepartamentJPA(), true);
    } else {
      departamentForm = new DepartamentForm(_jpa, false);
      departamentForm.setView(__isView);
    }
    departamentForm.setContexte(getContextWeb());
    departamentForm.setEntityNameCode(getEntityNameCode());
    departamentForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return departamentForm;
  }

  public void fillReferencesForForm(DepartamentForm departamentForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (departamentForm.getListOfAreaForAreaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForAreaID(request, mav, departamentForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      departamentForm.setListOfAreaForAreaID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Departament
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearDepartamentPost(@ModelAttribute DepartamentForm departamentForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    DepartamentJPA departament = departamentForm.getDepartament();

    try {
      preValidate(request, departamentForm, result);
      getWebValidator().validate(departamentForm, result);
      postValidate(request,departamentForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        departament = create(request, departament);
        createMessageSuccess(request, "success.creation", departament.getDepartamentID());
        departamentForm.setDepartament(departament);
        return getRedirectWhenCreated(request, departamentForm);
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

  @RequestMapping(value = "/view/{departamentID}", method = RequestMethod.GET)
  public ModelAndView veureDepartamentGet(@PathVariable("departamentID") java.lang.Long departamentID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewDepartamentGet(departamentID,
        request, response, true);
  }


  protected ModelAndView editAndViewDepartamentGet(@PathVariable("departamentID") java.lang.Long departamentID,
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
    DepartamentJPA departament = findByPrimaryKey(request, departamentID);

    if (departament == null) {
      createMessageWarning(request, "error.notfound", departamentID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, departamentID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      DepartamentForm departamentForm = getDepartamentForm(departament, __isView, request, mav);
      departamentForm.setView(__isView);
      if(__isView) {
        departamentForm.setAllFieldsReadOnly(ALL_DEPARTAMENT_FIELDS);
        departamentForm.setSaveButtonVisible(false);
        departamentForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(departamentForm, request, mav);
      mav.addObject("departamentForm", departamentForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Departament existent
   */
  @RequestMapping(value = "/{departamentID}/edit", method = RequestMethod.GET)
  public ModelAndView editarDepartamentGet(@PathVariable("departamentID") java.lang.Long departamentID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewDepartamentGet(departamentID,
        request, response, false);
  }



  /**
   * Editar un Departament existent
   */
  @RequestMapping(value = "/{departamentID}/edit", method = RequestMethod.POST)
  public String editarDepartamentPost(@ModelAttribute DepartamentForm departamentForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    DepartamentJPA departament = departamentForm.getDepartament();

    try {
      preValidate(request, departamentForm, result);
      getWebValidator().validate(departamentForm, result);
      postValidate(request, departamentForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        departament = update(request, departament);
        createMessageSuccess(request, "success.modification", departament.getDepartamentID());
        status.setComplete();
        return getRedirectWhenModified(request, departamentForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          departament.getDepartamentID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, departamentForm, __e);
    }

  }


  /**
   * Eliminar un Departament existent
   */
  @RequestMapping(value = "/{departamentID}/delete")
  public String eliminarDepartament(@PathVariable("departamentID") java.lang.Long departamentID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Departament departament = this.findByPrimaryKey(request, departamentID);
      if (departament == null) {
        String __msg = createMessageError(request, "error.notfound", departamentID);
        return getRedirectWhenDelete(request, departamentID, new Exception(__msg));
      } else {
        delete(request, departament);
        createMessageSuccess(request, "success.deleted", departamentID);
        return getRedirectWhenDelete(request, departamentID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", departamentID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, departamentID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute DepartamentFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarDepartament(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __departamentID, Throwable e) {
    java.lang.Long departamentID = (java.lang.Long)__departamentID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (departamentID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(departamentID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "departament.departament";
  }

  public String getEntityNameCodePlural() {
    return "departament.departament.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("departament.departamentID");
  }

  @InitBinder("departamentFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("departamentForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "departament.departamentID");
  }

  public DepartamentWebValidator getWebValidator() {
    return departamentWebValidator;
  }


  public void setWebValidator(DepartamentWebValidator __val) {
    if (__val != null) {
      this.departamentWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Departament
   */
  @RequestMapping(value = "/{departamentID}/cancel")
  public String cancelDepartament(@PathVariable("departamentID") java.lang.Long departamentID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, departamentID);
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


  public List<StringKeyValue> getReferenceListForAreaID(HttpServletRequest request,
       ModelAndView mav, DepartamentForm departamentForm, Where where)  throws I18NException {
    if (departamentForm.isHiddenField(AREAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (departamentForm.isReadOnlyField(AREAID)) {
      _where = AreaFields.AREAID.equal(departamentForm.getDepartament().getAreaID());
    }
    return getReferenceListForAreaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForAreaID(HttpServletRequest request,
       ModelAndView mav, DepartamentFilterForm departamentFilterForm,
       List<Departament> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (departamentFilterForm.isHiddenField(AREAID)
       && !departamentFilterForm.isGroupByField(AREAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(AREAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Departament _item : list) {
        _pkList.add(_item.getAreaID());
        }
        _w = AreaFields.AREAID.in(_pkList);
      }
    return getReferenceListForAreaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForAreaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return areaRefList.getReferenceList(AreaFields.AREAID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,DepartamentForm departamentForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,DepartamentForm departamentForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, DepartamentFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, DepartamentFilterForm filterForm,  List<Departament> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, DepartamentForm departamentForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, DepartamentForm departamentForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long departamentID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long departamentID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "departamentFormWebDB";
  }

  public String getTileList() {
    return "departamentListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Departament_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public DepartamentJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long departamentID) throws I18NException {
    return (DepartamentJPA) departamentEjb.findByPrimaryKey(departamentID);
  }


  public DepartamentJPA create(HttpServletRequest request, DepartamentJPA departament)
    throws I18NException, I18NValidationException {
    return (DepartamentJPA) departamentEjb.create(departament);
  }


  public DepartamentJPA update(HttpServletRequest request, DepartamentJPA departament)
    throws I18NException, I18NValidationException {
    return (DepartamentJPA) departamentEjb.update(departament);
  }


  public void delete(HttpServletRequest request, Departament departament) throws I18NException {
    departamentEjb.delete(departament);
  }

} // Final de Classe

