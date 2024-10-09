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
import org.fundaciobit.pinbaladmin.back.form.webdb.OrganForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.OrganWebValidator;

import org.fundaciobit.pinbaladmin.persistence.OrganJPA;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Organ
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/organ")
@SessionAttributes(types = { OrganForm.class, OrganFilterForm.class })
public class OrganController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<Organ, java.lang.Long> implements OrganFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OrganService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.OrganService organEjb;

  @Autowired
  private OrganWebValidator organWebValidator;

  @Autowired
  protected OrganRefList organRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes Organ
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    OrganFilterForm ff;
    ff = (OrganFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Organ de forma paginada
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
    llistat(mav, request, getOrganFilterForm(pagina, mav, request));
    return mav;
  }

  public OrganFilterForm getOrganFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    OrganFilterForm organFilterForm;
    organFilterForm = (OrganFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(organFilterForm == null) {
      organFilterForm = new OrganFilterForm();
      organFilterForm.setContexte(getContextWeb());
      organFilterForm.setEntityNameCode(getEntityNameCode());
      organFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      organFilterForm.setNou(true);
    } else {
      organFilterForm.setNou(false);
    }
    organFilterForm.setPage(pagina == null ? 1 : pagina);
    return organFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Organ de forma paginada
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
      @ModelAttribute OrganFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getOrganFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Organ de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Organ> llistat(ModelAndView mav, HttpServletRequest request,
     OrganFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Organ> organ = processarLlistat(organEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("organItems", organ);

    mav.addObject("organFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, organ, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, organ);

    return organ;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(OrganFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Organ> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field entitatid
    {
      _listSKV = getReferenceListForEntitatid(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatid(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    OrganFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Organ> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ORGAN_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatid());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Organ
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearOrganGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    OrganForm organForm = getOrganForm(null, false, request, mav);
    mav.addObject("organForm" ,organForm);
    fillReferencesForForm(organForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public OrganForm getOrganForm(OrganJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    OrganForm organForm;
    if(_jpa == null) {
      organForm = new OrganForm(new OrganJPA(), true);
    } else {
      organForm = new OrganForm(_jpa, false);
      organForm.setView(__isView);
    }
    organForm.setContexte(getContextWeb());
    organForm.setEntityNameCode(getEntityNameCode());
    organForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return organForm;
  }

  public void fillReferencesForForm(OrganForm organForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (organForm.getListOfEntitatForEntitatid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatid(request, mav, organForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      organForm.setListOfEntitatForEntitatid(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Organ
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearOrganPost(@ModelAttribute OrganForm organForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    OrganJPA organ = organForm.getOrgan();

    try {
      preValidate(request, organForm, result);
      getWebValidator().validate(organForm, result);
      postValidate(request,organForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        organ = create(request, organ);
        createMessageSuccess(request, "success.creation", organ.getOrganid());
        organForm.setOrgan(organ);
        return getRedirectWhenCreated(request, organForm);
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

  @RequestMapping(value = "/view/{organid}", method = RequestMethod.GET)
  public ModelAndView veureOrganGet(@PathVariable("organid") java.lang.Long organid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewOrganGet(organid,
        request, response, true);
  }


  protected ModelAndView editAndViewOrganGet(@PathVariable("organid") java.lang.Long organid,
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
    OrganJPA organ = findByPrimaryKey(request, organid);

    if (organ == null) {
      createMessageWarning(request, "error.notfound", organid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, organid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      OrganForm organForm = getOrganForm(organ, __isView, request, mav);
      organForm.setView(__isView);
      if(__isView) {
        organForm.setAllFieldsReadOnly(ALL_ORGAN_FIELDS);
        organForm.setSaveButtonVisible(false);
        organForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(organForm, request, mav);
      mav.addObject("organForm", organForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Organ existent
   */
  @RequestMapping(value = "/{organid}/edit", method = RequestMethod.GET)
  public ModelAndView editarOrganGet(@PathVariable("organid") java.lang.Long organid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewOrganGet(organid,
        request, response, false);
  }



  /**
   * Editar un Organ existent
   */
  @RequestMapping(value = "/{organid}/edit", method = RequestMethod.POST)
  public String editarOrganPost(@ModelAttribute OrganForm organForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    OrganJPA organ = organForm.getOrgan();

    try {
      preValidate(request, organForm, result);
      getWebValidator().validate(organForm, result);
      postValidate(request, organForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        organ = update(request, organ);
        createMessageSuccess(request, "success.modification", organ.getOrganid());
        status.setComplete();
        return getRedirectWhenModified(request, organForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          organ.getOrganid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, organForm, __e);
    }

  }


  /**
   * Eliminar un Organ existent
   */
  @RequestMapping(value = "/{organid}/delete")
  public String eliminarOrgan(@PathVariable("organid") java.lang.Long organid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Organ organ = this.findByPrimaryKey(request, organid);
      if (organ == null) {
        String __msg = createMessageError(request, "error.notfound", organid);
        return getRedirectWhenDelete(request, organid, new Exception(__msg));
      } else {
        delete(request, organ);
        createMessageSuccess(request, "success.deleted", organid);
        return getRedirectWhenDelete(request, organid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", organid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, organid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute OrganFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarOrgan(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __organid, Throwable e) {
    java.lang.Long organid = (java.lang.Long)__organid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (organid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(organid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "organ.organ";
  }

  public String getEntityNameCodePlural() {
    return "organ.organ.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("organ.organid");
  }

  @InitBinder("organFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("organForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "organ.organid");
  }

  public OrganWebValidator getWebValidator() {
    return organWebValidator;
  }


  public void setWebValidator(OrganWebValidator __val) {
    if (__val != null) {
      this.organWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Organ
   */
  @RequestMapping(value = "/{organid}/cancel")
  public String cancelOrgan(@PathVariable("organid") java.lang.Long organid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, organid);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Organ
   */
  @RequestMapping(value = "/cancel")
  public String cancelOrgan(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForEntitatid(HttpServletRequest request,
       ModelAndView mav, OrganForm organForm, Where where)  throws I18NException {
    if (organForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (organForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(organForm.getOrgan().getEntitatid());
    }
    return getReferenceListForEntitatid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatid(HttpServletRequest request,
       ModelAndView mav, OrganFilterForm organFilterForm,
       List<Organ> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (organFilterForm.isHiddenField(ENTITATID)
       && !organFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Organ _item : list) {
        if(_item.getEntitatid() == null) { continue; };
        _pkList.add(_item.getEntitatid());
        }
        _w = EntitatFields.ENTITATID.in(_pkList);
      }
    return getReferenceListForEntitatid(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatid(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,OrganForm organForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,OrganForm organForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, OrganFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, OrganFilterForm filterForm,  List<Organ> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, OrganForm organForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, OrganForm organForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long organid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long organid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "organFormWebDB";
  }

  public String getTileList() {
    return "organListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Organ_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public OrganJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long organid) throws I18NException {
    return (OrganJPA) organEjb.findByPrimaryKey(organid);
  }


  public OrganJPA create(HttpServletRequest request, OrganJPA organ)
    throws I18NException, I18NValidationException {
    return (OrganJPA) organEjb.create(organ);
  }


  public OrganJPA update(HttpServletRequest request, OrganJPA organ)
    throws I18NException, I18NValidationException {
    return (OrganJPA) organEjb.update(organ);
  }


  public void delete(HttpServletRequest request, Organ organ) throws I18NException {
    organEjb.delete(organ);
  }

} // Final de Classe

