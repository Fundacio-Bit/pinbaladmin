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
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.fundaciobit.pinbaladmin.back.form.webdb.*;
import org.fundaciobit.pinbaladmin.back.form.webdb.GrupEntitatCedentForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.GrupEntitatCedentWebValidator;

import org.fundaciobit.pinbaladmin.jpa.GrupEntitatCedentJPA;
import org.fundaciobit.pinbaladmin.model.entity.GrupEntitatCedent;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un GrupEntitatCedent
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/grupEntitatCedent")
@SessionAttributes(types = { GrupEntitatCedentForm.class, GrupEntitatCedentFilterForm.class })
public class GrupEntitatCedentController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<GrupEntitatCedent, java.lang.Long> implements GrupEntitatCedentFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.GrupEntitatCedentLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.GrupEntitatCedentLocal grupEntitatCedentEjb;

  @Autowired
  private GrupEntitatCedentWebValidator grupEntitatCedentWebValidator;

  @Autowired
  protected GrupEntitatCedentRefList grupEntitatCedentRefList;

  // References 
  @Autowired
  protected GrupEntitatRefList grupEntitatRefList;

  // References 
  @Autowired
  protected EntitatServeiRefList entitatServeiRefList;

  /**
   * Llistat de totes GrupEntitatCedent
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    GrupEntitatCedentFilterForm ff;
    ff = (GrupEntitatCedentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar GrupEntitatCedent de forma paginada
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
    llistat(mav, request, getGrupEntitatCedentFilterForm(pagina, mav, request));
    return mav;
  }

  public GrupEntitatCedentFilterForm getGrupEntitatCedentFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    GrupEntitatCedentFilterForm grupEntitatCedentFilterForm;
    grupEntitatCedentFilterForm = (GrupEntitatCedentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(grupEntitatCedentFilterForm == null) {
      grupEntitatCedentFilterForm = new GrupEntitatCedentFilterForm();
      grupEntitatCedentFilterForm.setContexte(getContextWeb());
      grupEntitatCedentFilterForm.setEntityNameCode(getEntityNameCode());
      grupEntitatCedentFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      grupEntitatCedentFilterForm.setNou(true);
    } else {
      grupEntitatCedentFilterForm.setNou(false);
    }
    grupEntitatCedentFilterForm.setPage(pagina == null ? 1 : pagina);
    return grupEntitatCedentFilterForm;
  }

  /**
   * Segona i següent peticions per llistar GrupEntitatCedent de forma paginada
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
      @ModelAttribute GrupEntitatCedentFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getGrupEntitatCedentFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de GrupEntitatCedent de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<GrupEntitatCedent> llistat(ModelAndView mav, HttpServletRequest request,
     GrupEntitatCedentFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<GrupEntitatCedent> grupEntitatCedent = processarLlistat(grupEntitatCedentEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("grupEntitatCedentItems", grupEntitatCedent);

    mav.addObject("grupEntitatCedentFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, grupEntitatCedent, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, grupEntitatCedent);

    return grupEntitatCedent;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(GrupEntitatCedentFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<GrupEntitatCedent> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field grupEntitatID
    {
      _listSKV = getReferenceListForGrupEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfGrupEntitatForGrupEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(GRUPENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, GRUPENTITATID, false);
      };
    }

    // Field cedentID
    {
      _listSKV = getReferenceListForCedentID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatServeiForCedentID(_tmp);
      if (filterForm.getGroupByFields().contains(CEDENTID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CEDENTID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    GrupEntitatCedentFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<GrupEntitatCedent> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_GRUPENTITATCEDENT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(GRUPENTITATID, filterForm.getMapOfGrupEntitatForGrupEntitatID());
    __mapping.put(CEDENTID, filterForm.getMapOfEntitatServeiForCedentID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou GrupEntitatCedent
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearGrupEntitatCedentGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    GrupEntitatCedentForm grupEntitatCedentForm = getGrupEntitatCedentForm(null, false, request, mav);
    mav.addObject("grupEntitatCedentForm" ,grupEntitatCedentForm);
    fillReferencesForForm(grupEntitatCedentForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public GrupEntitatCedentForm getGrupEntitatCedentForm(GrupEntitatCedentJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    GrupEntitatCedentForm grupEntitatCedentForm;
    if(_jpa == null) {
      grupEntitatCedentForm = new GrupEntitatCedentForm(new GrupEntitatCedentJPA(), true);
    } else {
      grupEntitatCedentForm = new GrupEntitatCedentForm(_jpa, false);
      grupEntitatCedentForm.setView(__isView);
    }
    grupEntitatCedentForm.setContexte(getContextWeb());
    grupEntitatCedentForm.setEntityNameCode(getEntityNameCode());
    grupEntitatCedentForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return grupEntitatCedentForm;
  }

  public void fillReferencesForForm(GrupEntitatCedentForm grupEntitatCedentForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (grupEntitatCedentForm.getListOfGrupEntitatForGrupEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForGrupEntitatID(request, mav, grupEntitatCedentForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      grupEntitatCedentForm.setListOfGrupEntitatForGrupEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (grupEntitatCedentForm.getListOfEntitatServeiForCedentID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForCedentID(request, mav, grupEntitatCedentForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      grupEntitatCedentForm.setListOfEntitatServeiForCedentID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou GrupEntitatCedent
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearGrupEntitatCedentPost(@ModelAttribute GrupEntitatCedentForm grupEntitatCedentForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    GrupEntitatCedentJPA grupEntitatCedent = grupEntitatCedentForm.getGrupEntitatCedent();

    try {
      preValidate(request, grupEntitatCedentForm, result);
      getWebValidator().validate(grupEntitatCedentForm, result);
      postValidate(request,grupEntitatCedentForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        grupEntitatCedent = create(request, grupEntitatCedent);
        createMessageSuccess(request, "success.creation", grupEntitatCedent.getGrupEntitatCedentID());
        grupEntitatCedentForm.setGrupEntitatCedent(grupEntitatCedent);
        return getRedirectWhenCreated(request, grupEntitatCedentForm);
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

  @RequestMapping(value = "/view/{grupEntitatCedentID}", method = RequestMethod.GET)
  public ModelAndView veureGrupEntitatCedentGet(@PathVariable("grupEntitatCedentID") java.lang.Long grupEntitatCedentID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGrupEntitatCedentGet(grupEntitatCedentID,
        request, response, true);
  }


  protected ModelAndView editAndViewGrupEntitatCedentGet(@PathVariable("grupEntitatCedentID") java.lang.Long grupEntitatCedentID,
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
    GrupEntitatCedentJPA grupEntitatCedent = findByPrimaryKey(request, grupEntitatCedentID);

    if (grupEntitatCedent == null) {
      createMessageWarning(request, "error.notfound", grupEntitatCedentID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, grupEntitatCedentID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      GrupEntitatCedentForm grupEntitatCedentForm = getGrupEntitatCedentForm(grupEntitatCedent, __isView, request, mav);
      grupEntitatCedentForm.setView(__isView);
      if(__isView) {
        grupEntitatCedentForm.setAllFieldsReadOnly(ALL_GRUPENTITATCEDENT_FIELDS);
        grupEntitatCedentForm.setSaveButtonVisible(false);
        grupEntitatCedentForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(grupEntitatCedentForm, request, mav);
      mav.addObject("grupEntitatCedentForm", grupEntitatCedentForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un GrupEntitatCedent existent
   */
  @RequestMapping(value = "/{grupEntitatCedentID}/edit", method = RequestMethod.GET)
  public ModelAndView editarGrupEntitatCedentGet(@PathVariable("grupEntitatCedentID") java.lang.Long grupEntitatCedentID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGrupEntitatCedentGet(grupEntitatCedentID,
        request, response, false);
  }



  /**
   * Editar un GrupEntitatCedent existent
   */
  @RequestMapping(value = "/{grupEntitatCedentID}/edit", method = RequestMethod.POST)
  public String editarGrupEntitatCedentPost(@ModelAttribute @Valid GrupEntitatCedentForm grupEntitatCedentForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    GrupEntitatCedentJPA grupEntitatCedent = grupEntitatCedentForm.getGrupEntitatCedent();

    try {
      preValidate(request, grupEntitatCedentForm, result);
      getWebValidator().validate(grupEntitatCedent, result);
      postValidate(request, grupEntitatCedentForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        grupEntitatCedent = update(request, grupEntitatCedent);
        createMessageSuccess(request, "success.modification", grupEntitatCedent.getGrupEntitatCedentID());
        status.setComplete();
        return getRedirectWhenModified(request, grupEntitatCedentForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          grupEntitatCedent.getGrupEntitatCedentID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, grupEntitatCedentForm, __e);
    }

  }


  /**
   * Eliminar un GrupEntitatCedent existent
   */
  @RequestMapping(value = "/{grupEntitatCedentID}/delete")
  public String eliminarGrupEntitatCedent(@PathVariable("grupEntitatCedentID") java.lang.Long grupEntitatCedentID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      GrupEntitatCedent grupEntitatCedent = grupEntitatCedentEjb.findByPrimaryKey(grupEntitatCedentID);
      if (grupEntitatCedent == null) {
        String __msg =createMessageError(request, "error.notfound", grupEntitatCedentID);
        return getRedirectWhenDelete(request, grupEntitatCedentID, new Exception(__msg));
      } else {
        delete(request, grupEntitatCedent);
        createMessageSuccess(request, "success.deleted", grupEntitatCedentID);
        return getRedirectWhenDelete(request, grupEntitatCedentID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", grupEntitatCedentID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, grupEntitatCedentID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute GrupEntitatCedentFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarGrupEntitatCedent(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return new java.lang.Long(value);
}

  @Override
  public String[] getArgumentsMissatge(Object __grupEntitatCedentID, Throwable e) {
    java.lang.Long grupEntitatCedentID = (java.lang.Long)__grupEntitatCedentID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (grupEntitatCedentID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(grupEntitatCedentID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "grupEntitatCedent.grupEntitatCedent";
  }

  public String getEntityNameCodePlural() {
    return "grupEntitatCedent.grupEntitatCedent.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("grupEntitatCedent.grupEntitatCedentID");
  }

  @InitBinder("grupEntitatCedentFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("grupEntitatCedentForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("grupEntitatCedentID");

  }

  public GrupEntitatCedentWebValidator getWebValidator() {
    return grupEntitatCedentWebValidator;
  }


  public void setWebValidator(GrupEntitatCedentWebValidator __val) {
    if (__val != null) {
      this.grupEntitatCedentWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de GrupEntitatCedent
   */
  @RequestMapping(value = "/{grupEntitatCedentID}/cancel")
  public String cancelGrupEntitatCedent(@PathVariable("grupEntitatCedentID") java.lang.Long grupEntitatCedentID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, grupEntitatCedentID);
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


  public List<StringKeyValue> getReferenceListForGrupEntitatID(HttpServletRequest request,
       ModelAndView mav, GrupEntitatCedentForm grupEntitatCedentForm, Where where)  throws I18NException {
    if (grupEntitatCedentForm.isHiddenField(GRUPENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (grupEntitatCedentForm.isReadOnlyField(GRUPENTITATID)) {
      _where = GrupEntitatFields.GRUPENTITATID.equal(grupEntitatCedentForm.getGrupEntitatCedent().getGrupEntitatID());
    }
    return getReferenceListForGrupEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForGrupEntitatID(HttpServletRequest request,
       ModelAndView mav, GrupEntitatCedentFilterForm grupEntitatCedentFilterForm,
       List<GrupEntitatCedent> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (grupEntitatCedentFilterForm.isHiddenField(GRUPENTITATID)
      && !grupEntitatCedentFilterForm.isGroupByField(GRUPENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(GRUPENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (GrupEntitatCedent _item : list) {
        _pkList.add(_item.getGrupEntitatID());
        }
        _w = GrupEntitatFields.GRUPENTITATID.in(_pkList);
      }
    return getReferenceListForGrupEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForGrupEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return grupEntitatRefList.getReferenceList(GrupEntitatFields.GRUPENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForCedentID(HttpServletRequest request,
       ModelAndView mav, GrupEntitatCedentForm grupEntitatCedentForm, Where where)  throws I18NException {
    if (grupEntitatCedentForm.isHiddenField(CEDENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (grupEntitatCedentForm.isReadOnlyField(CEDENTID)) {
      _where = EntitatServeiFields.ENTITATSERVEIID.equal(grupEntitatCedentForm.getGrupEntitatCedent().getCedentID());
    }
    return getReferenceListForCedentID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForCedentID(HttpServletRequest request,
       ModelAndView mav, GrupEntitatCedentFilterForm grupEntitatCedentFilterForm,
       List<GrupEntitatCedent> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (grupEntitatCedentFilterForm.isHiddenField(CEDENTID)
      && !grupEntitatCedentFilterForm.isGroupByField(CEDENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(CEDENTID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (GrupEntitatCedent _item : list) {
        _pkList.add(_item.getCedentID());
        }
        _w = EntitatServeiFields.ENTITATSERVEIID.in(_pkList);
      }
    return getReferenceListForCedentID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForCedentID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatServeiRefList.getReferenceList(EntitatServeiFields.ENTITATSERVEIID, where );
  }


  public void preValidate(HttpServletRequest request,GrupEntitatCedentForm grupEntitatCedentForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,GrupEntitatCedentForm grupEntitatCedentForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, GrupEntitatCedentFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, GrupEntitatCedentFilterForm filterForm,  List<GrupEntitatCedent> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, GrupEntitatCedentForm grupEntitatCedentForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, GrupEntitatCedentForm grupEntitatCedentForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long grupEntitatCedentID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long grupEntitatCedentID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "grupEntitatCedentFormWebDB";
  }

  public String getTileList() {
    return "grupEntitatCedentListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "GrupEntitatCedentWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public GrupEntitatCedentJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long grupEntitatCedentID) throws I18NException {
    return (GrupEntitatCedentJPA) grupEntitatCedentEjb.findByPrimaryKey(grupEntitatCedentID);
  }


  public GrupEntitatCedentJPA create(HttpServletRequest request, GrupEntitatCedentJPA grupEntitatCedent)
    throws Exception,I18NException, I18NValidationException {
    return (GrupEntitatCedentJPA) grupEntitatCedentEjb.create(grupEntitatCedent);
  }


  public GrupEntitatCedentJPA update(HttpServletRequest request, GrupEntitatCedentJPA grupEntitatCedent)
    throws Exception,I18NException, I18NValidationException {
    return (GrupEntitatCedentJPA) grupEntitatCedentEjb.update(grupEntitatCedent);
  }


  public void delete(HttpServletRequest request, GrupEntitatCedent grupEntitatCedent) throws Exception,I18NException {
    grupEntitatCedentEjb.delete(grupEntitatCedent);
  }

} // Final de Classe

