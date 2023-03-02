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
import org.fundaciobit.pinbaladmin.back.form.webdb.AreaForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.AreaWebValidator;

import org.fundaciobit.pinbaladmin.persistence.AreaJPA;
import org.fundaciobit.pinbaladmin.model.entity.Area;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Area
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/area")
@SessionAttributes(types = { AreaForm.class, AreaFilterForm.class })
public class AreaController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<Area, java.lang.Long> implements AreaFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.AreaService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.AreaService areaEjb;

  @Autowired
  private AreaWebValidator areaWebValidator;

  @Autowired
  protected AreaRefList areaRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes Area
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    AreaFilterForm ff;
    ff = (AreaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Area de forma paginada
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
    llistat(mav, request, getAreaFilterForm(pagina, mav, request));
    return mav;
  }

  public AreaFilterForm getAreaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    AreaFilterForm areaFilterForm;
    areaFilterForm = (AreaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(areaFilterForm == null) {
      areaFilterForm = new AreaFilterForm();
      areaFilterForm.setContexte(getContextWeb());
      areaFilterForm.setEntityNameCode(getEntityNameCode());
      areaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      areaFilterForm.setNou(true);
    } else {
      areaFilterForm.setNou(false);
    }
    areaFilterForm.setPage(pagina == null ? 1 : pagina);
    return areaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Area de forma paginada
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
      @ModelAttribute AreaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getAreaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Area de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Area> llistat(ModelAndView mav, HttpServletRequest request,
     AreaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Area> area = processarLlistat(areaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("areaItems", area);

    mav.addObject("areaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, area, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, area);

    return area;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(AreaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Area> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    AreaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Area> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_AREA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Area
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearAreaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    AreaForm areaForm = getAreaForm(null, false, request, mav);
    mav.addObject("areaForm" ,areaForm);
    fillReferencesForForm(areaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public AreaForm getAreaForm(AreaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    AreaForm areaForm;
    if(_jpa == null) {
      areaForm = new AreaForm(new AreaJPA(), true);
    } else {
      areaForm = new AreaForm(_jpa, false);
      areaForm.setView(__isView);
    }
    areaForm.setContexte(getContextWeb());
    areaForm.setEntityNameCode(getEntityNameCode());
    areaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return areaForm;
  }

  public void fillReferencesForForm(AreaForm areaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (areaForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, areaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      areaForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Area
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearAreaPost(@ModelAttribute AreaForm areaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    AreaJPA area = areaForm.getArea();

    try {
      preValidate(request, areaForm, result);
      getWebValidator().validate(areaForm, result);
      postValidate(request,areaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        area = create(request, area);
        createMessageSuccess(request, "success.creation", area.getAreaID());
        areaForm.setArea(area);
        return getRedirectWhenCreated(request, areaForm);
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

  @RequestMapping(value = "/view/{areaID}", method = RequestMethod.GET)
  public ModelAndView veureAreaGet(@PathVariable("areaID") java.lang.Long areaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAreaGet(areaID,
        request, response, true);
  }


  protected ModelAndView editAndViewAreaGet(@PathVariable("areaID") java.lang.Long areaID,
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
    AreaJPA area = findByPrimaryKey(request, areaID);

    if (area == null) {
      createMessageWarning(request, "error.notfound", areaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, areaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      AreaForm areaForm = getAreaForm(area, __isView, request, mav);
      areaForm.setView(__isView);
      if(__isView) {
        areaForm.setAllFieldsReadOnly(ALL_AREA_FIELDS);
        areaForm.setSaveButtonVisible(false);
        areaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(areaForm, request, mav);
      mav.addObject("areaForm", areaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Area existent
   */
  @RequestMapping(value = "/{areaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarAreaGet(@PathVariable("areaID") java.lang.Long areaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAreaGet(areaID,
        request, response, false);
  }



  /**
   * Editar un Area existent
   */
  @RequestMapping(value = "/{areaID}/edit", method = RequestMethod.POST)
  public String editarAreaPost(@ModelAttribute AreaForm areaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    AreaJPA area = areaForm.getArea();

    try {
      preValidate(request, areaForm, result);
      getWebValidator().validate(areaForm, result);
      postValidate(request, areaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        area = update(request, area);
        createMessageSuccess(request, "success.modification", area.getAreaID());
        status.setComplete();
        return getRedirectWhenModified(request, areaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          area.getAreaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, areaForm, __e);
    }

  }


  /**
   * Eliminar un Area existent
   */
  @RequestMapping(value = "/{areaID}/delete")
  public String eliminarArea(@PathVariable("areaID") java.lang.Long areaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Area area = areaEjb.findByPrimaryKey(areaID);
      if (area == null) {
        String __msg =createMessageError(request, "error.notfound", areaID);
        return getRedirectWhenDelete(request, areaID, new Exception(__msg));
      } else {
        delete(request, area);
        createMessageSuccess(request, "success.deleted", areaID);
        return getRedirectWhenDelete(request, areaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", areaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, areaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute AreaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarArea(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __areaID, Throwable e) {
    java.lang.Long areaID = (java.lang.Long)__areaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (areaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(areaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "area.area";
  }

  public String getEntityNameCodePlural() {
    return "area.area.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("area.areaID");
  }

  @InitBinder("areaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("areaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "area.areaID");
  }

  public AreaWebValidator getWebValidator() {
    return areaWebValidator;
  }


  public void setWebValidator(AreaWebValidator __val) {
    if (__val != null) {
      this.areaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Area
   */
  @RequestMapping(value = "/{areaID}/cancel")
  public String cancelArea(@PathVariable("areaID") java.lang.Long areaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, areaID);
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


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, AreaForm areaForm, Where where)  throws I18NException {
    if (areaForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (areaForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(areaForm.getArea().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, AreaFilterForm areaFilterForm,
       List<Area> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (areaFilterForm.isHiddenField(ENTITATID)
      && !areaFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Area _item : list) {
        _pkList.add(_item.getEntitatID());
        }
        _w = EntitatFields.ENTITATID.in(_pkList);
      }
    return getReferenceListForEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,AreaForm areaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,AreaForm areaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, AreaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, AreaFilterForm filterForm,  List<Area> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, AreaForm areaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, AreaForm areaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long areaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long areaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "areaFormWebDB";
  }

  public String getTileList() {
    return "areaListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "AreaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public AreaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long areaID) throws I18NException {
    return (AreaJPA) areaEjb.findByPrimaryKey(areaID);
  }


  public AreaJPA create(HttpServletRequest request, AreaJPA area)
    throws I18NException, I18NValidationException {
    return (AreaJPA) areaEjb.create(area);
  }


  public AreaJPA update(HttpServletRequest request, AreaJPA area)
    throws I18NException, I18NValidationException {
    return (AreaJPA) areaEjb.update(area);
  }


  public void delete(HttpServletRequest request, Area area) throws I18NException {
    areaEjb.delete(area);
  }

} // Final de Classe

