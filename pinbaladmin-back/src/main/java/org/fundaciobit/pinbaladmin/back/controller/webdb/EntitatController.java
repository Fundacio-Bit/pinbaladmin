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
import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.EntitatWebValidator;

import org.fundaciobit.pinbaladmin.persistence.EntitatJPA;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Entitat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/entitat")
@SessionAttributes(types = { EntitatForm.class, EntitatFilterForm.class })
public class EntitatController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<Entitat, java.lang.Long> implements EntitatFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatService entitatEjb;

  @Autowired
  private EntitatWebValidator entitatWebValidator;

  @Autowired
  protected EntitatRefList entitatRefList;

  // References 
  @Autowired
  protected GrupEntitatRefList grupEntitatRefList;

  /**
   * Llistat de totes Entitat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EntitatFilterForm ff;
    ff = (EntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Entitat de forma paginada
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
    llistat(mav, request, getEntitatFilterForm(pagina, mav, request));
    return mav;
  }

  public EntitatFilterForm getEntitatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EntitatFilterForm entitatFilterForm;
    entitatFilterForm = (EntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(entitatFilterForm == null) {
      entitatFilterForm = new EntitatFilterForm();
      entitatFilterForm.setContexte(getContextWeb());
      entitatFilterForm.setEntityNameCode(getEntityNameCode());
      entitatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      entitatFilterForm.setNou(true);
    } else {
      entitatFilterForm.setNou(false);
    }
    entitatFilterForm.setPage(pagina == null ? 1 : pagina);
    return entitatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Entitat de forma paginada
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
      @ModelAttribute EntitatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEntitatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Entitat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Entitat> llistat(ModelAndView mav, HttpServletRequest request,
     EntitatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Entitat> entitat = processarLlistat(entitatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("entitatItems", entitat);

    mav.addObject("entitatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, entitat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, entitat);

    return entitat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EntitatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Entitat> list, List<GroupByItem> groupItems) throws I18NException {
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


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, CONVENIPMSBAE);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EntitatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Entitat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ENTITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(GRUPENTITATID, filterForm.getMapOfGrupEntitatForGrupEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Entitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEntitatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EntitatForm entitatForm = getEntitatForm(null, false, request, mav);
    mav.addObject("entitatForm" ,entitatForm);
    fillReferencesForForm(entitatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EntitatForm getEntitatForm(EntitatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EntitatForm entitatForm;
    if(_jpa == null) {
      entitatForm = new EntitatForm(new EntitatJPA(), true);
    } else {
      entitatForm = new EntitatForm(_jpa, false);
      entitatForm.setView(__isView);
    }
    entitatForm.setContexte(getContextWeb());
    entitatForm.setEntityNameCode(getEntityNameCode());
    entitatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return entitatForm;
  }

  public void fillReferencesForForm(EntitatForm entitatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (entitatForm.getListOfGrupEntitatForGrupEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForGrupEntitatID(request, mav, entitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      entitatForm.setListOfGrupEntitatForGrupEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Entitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEntitatPost(@ModelAttribute EntitatForm entitatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EntitatJPA entitat = entitatForm.getEntitat();

    try {
      preValidate(request, entitatForm, result);
      getWebValidator().validate(entitatForm, result);
      postValidate(request,entitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        entitat = create(request, entitat);
        createMessageSuccess(request, "success.creation", entitat.getEntitatID());
        entitatForm.setEntitat(entitat);
        return getRedirectWhenCreated(request, entitatForm);
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

  @RequestMapping(value = "/view/{entitatID}", method = RequestMethod.GET)
  public ModelAndView veureEntitatGet(@PathVariable("entitatID") java.lang.Long entitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEntitatGet(entitatID,
        request, response, true);
  }


  protected ModelAndView editAndViewEntitatGet(@PathVariable("entitatID") java.lang.Long entitatID,
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
    EntitatJPA entitat = findByPrimaryKey(request, entitatID);

    if (entitat == null) {
      createMessageWarning(request, "error.notfound", entitatID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, entitatID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EntitatForm entitatForm = getEntitatForm(entitat, __isView, request, mav);
      entitatForm.setView(__isView);
      if(__isView) {
        entitatForm.setAllFieldsReadOnly(ALL_ENTITAT_FIELDS);
        entitatForm.setSaveButtonVisible(false);
        entitatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(entitatForm, request, mav);
      mav.addObject("entitatForm", entitatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Entitat existent
   */
  @RequestMapping(value = "/{entitatID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEntitatGet(@PathVariable("entitatID") java.lang.Long entitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEntitatGet(entitatID,
        request, response, false);
  }



  /**
   * Editar un Entitat existent
   */
  @RequestMapping(value = "/{entitatID}/edit", method = RequestMethod.POST)
  public String editarEntitatPost(@ModelAttribute EntitatForm entitatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EntitatJPA entitat = entitatForm.getEntitat();

    try {
      preValidate(request, entitatForm, result);
      getWebValidator().validate(entitatForm, result);
      postValidate(request, entitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        entitat = update(request, entitat);
        createMessageSuccess(request, "success.modification", entitat.getEntitatID());
        status.setComplete();
        return getRedirectWhenModified(request, entitatForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          entitat.getEntitatID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, entitatForm, __e);
    }

  }


  /**
   * Eliminar un Entitat existent
   */
  @RequestMapping(value = "/{entitatID}/delete")
  public String eliminarEntitat(@PathVariable("entitatID") java.lang.Long entitatID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Entitat entitat = this.findByPrimaryKey(request, entitatID);
      if (entitat == null) {
        String __msg = createMessageError(request, "error.notfound", entitatID);
        return getRedirectWhenDelete(request, entitatID, new Exception(__msg));
      } else {
        delete(request, entitat);
        createMessageSuccess(request, "success.deleted", entitatID);
        return getRedirectWhenDelete(request, entitatID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", entitatID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, entitatID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EntitatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEntitat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __entitatID, Throwable e) {
    java.lang.Long entitatID = (java.lang.Long)__entitatID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (entitatID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(entitatID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "entitat.entitat";
  }

  public String getEntityNameCodePlural() {
    return "entitat.entitat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("entitat.entitatID");
  }

  @InitBinder("entitatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("entitatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "entitat.entitatID");
  }

  public EntitatWebValidator getWebValidator() {
    return entitatWebValidator;
  }


  public void setWebValidator(EntitatWebValidator __val) {
    if (__val != null) {
      this.entitatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Entitat
   */
  @RequestMapping(value = "/{entitatID}/cancel")
  public String cancelEntitat(@PathVariable("entitatID") java.lang.Long entitatID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, entitatID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Entitat
   */
  @RequestMapping(value = "/cancel")
  public String cancelEntitat(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForGrupEntitatID(HttpServletRequest request,
       ModelAndView mav, EntitatForm entitatForm, Where where)  throws I18NException {
    if (entitatForm.isHiddenField(GRUPENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (entitatForm.isReadOnlyField(GRUPENTITATID)) {
      _where = GrupEntitatFields.GRUPENTITATID.equal(entitatForm.getEntitat().getGrupEntitatID());
    }
    return getReferenceListForGrupEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForGrupEntitatID(HttpServletRequest request,
       ModelAndView mav, EntitatFilterForm entitatFilterForm,
       List<Entitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (entitatFilterForm.isHiddenField(GRUPENTITATID)
       && !entitatFilterForm.isGroupByField(GRUPENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(GRUPENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Entitat _item : list) {
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,EntitatForm entitatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EntitatForm entitatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EntitatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EntitatFilterForm filterForm,  List<Entitat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EntitatForm entitatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EntitatForm entitatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long entitatID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long entitatID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "entitatFormWebDB";
  }

  public String getTileList() {
    return "entitatListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Entitat_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EntitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long entitatID) throws I18NException {
    return (EntitatJPA) entitatEjb.findByPrimaryKey(entitatID);
  }


  public EntitatJPA create(HttpServletRequest request, EntitatJPA entitat)
    throws I18NException, I18NValidationException {
    return (EntitatJPA) entitatEjb.create(entitat);
  }


  public EntitatJPA update(HttpServletRequest request, EntitatJPA entitat)
    throws I18NException, I18NValidationException {
    return (EntitatJPA) entitatEjb.update(entitat);
  }


  public void delete(HttpServletRequest request, Entitat entitat) throws I18NException {
    entitatEjb.delete(entitat);
  }

} // Final de Classe

