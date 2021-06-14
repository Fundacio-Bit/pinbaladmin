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
import org.fundaciobit.pinbaladmin.back.form.webdb.CampFormulariForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.CampFormulariWebValidator;

import org.fundaciobit.pinbaladmin.jpa.CampFormulariJPA;
import org.fundaciobit.pinbaladmin.model.entity.CampFormulari;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un CampFormulari
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/campFormulari")
@SessionAttributes(types = { CampFormulariForm.class, CampFormulariFilterForm.class })
public class CampFormulariController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<CampFormulari, java.lang.Long> implements CampFormulariFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.CampFormulariLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.CampFormulariLocal campFormulariEjb;

  @Autowired
  private CampFormulariWebValidator campFormulariWebValidator;

  @Autowired
  protected CampFormulariRefList campFormulariRefList;

  // References 
  @Autowired
  protected FormulariRefList formulariRefList;

  /**
   * Llistat de totes CampFormulari
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    CampFormulariFilterForm ff;
    ff = (CampFormulariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar CampFormulari de forma paginada
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
    llistat(mav, request, getCampFormulariFilterForm(pagina, mav, request));
    return mav;
  }

  public CampFormulariFilterForm getCampFormulariFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    CampFormulariFilterForm campFormulariFilterForm;
    campFormulariFilterForm = (CampFormulariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(campFormulariFilterForm == null) {
      campFormulariFilterForm = new CampFormulariFilterForm();
      campFormulariFilterForm.setContexte(getContextWeb());
      campFormulariFilterForm.setEntityNameCode(getEntityNameCode());
      campFormulariFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      campFormulariFilterForm.setNou(true);
    } else {
      campFormulariFilterForm.setNou(false);
    }
    campFormulariFilterForm.setPage(pagina == null ? 1 : pagina);
    return campFormulariFilterForm;
  }

  /**
   * Segona i següent peticions per llistar CampFormulari de forma paginada
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
      @ModelAttribute CampFormulariFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getCampFormulariFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de CampFormulari de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<CampFormulari> llistat(ModelAndView mav, HttpServletRequest request,
     CampFormulariFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<CampFormulari> campFormulari = processarLlistat(campFormulariEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("campFormulariItems", campFormulari);

    mav.addObject("campFormulariFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, campFormulari, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, campFormulari);

    return campFormulari;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(CampFormulariFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<CampFormulari> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field formulariID
    {
      _listSKV = getReferenceListForFormulariID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfFormulariForFormulariID(_tmp);
      if (filterForm.getGroupByFields().contains(FORMULARIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FORMULARIID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    CampFormulariFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<CampFormulari> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_CAMPFORMULARI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(FORMULARIID, filterForm.getMapOfFormulariForFormulariID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou CampFormulari
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearCampFormulariGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    CampFormulariForm campFormulariForm = getCampFormulariForm(null, false, request, mav);
    mav.addObject("campFormulariForm" ,campFormulariForm);
    fillReferencesForForm(campFormulariForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public CampFormulariForm getCampFormulariForm(CampFormulariJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    CampFormulariForm campFormulariForm;
    if(_jpa == null) {
      campFormulariForm = new CampFormulariForm(new CampFormulariJPA(), true);
    } else {
      campFormulariForm = new CampFormulariForm(_jpa, false);
      campFormulariForm.setView(__isView);
    }
    campFormulariForm.setContexte(getContextWeb());
    campFormulariForm.setEntityNameCode(getEntityNameCode());
    campFormulariForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return campFormulariForm;
  }

  public void fillReferencesForForm(CampFormulariForm campFormulariForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (campFormulariForm.getListOfFormulariForFormulariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFormulariID(request, mav, campFormulariForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      campFormulariForm.setListOfFormulariForFormulariID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou CampFormulari
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearCampFormulariPost(@ModelAttribute CampFormulariForm campFormulariForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    CampFormulariJPA campFormulari = campFormulariForm.getCampFormulari();

    try {
      preValidate(request, campFormulariForm, result);
      getWebValidator().validate(campFormulariForm, result);
      postValidate(request,campFormulariForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        campFormulari = create(request, campFormulari);
        createMessageSuccess(request, "success.creation", campFormulari.getCampFormulariID());
        campFormulariForm.setCampFormulari(campFormulari);
        return getRedirectWhenCreated(request, campFormulariForm);
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

  @RequestMapping(value = "/view/{campFormulariID}", method = RequestMethod.GET)
  public ModelAndView veureCampFormulariGet(@PathVariable("campFormulariID") java.lang.Long campFormulariID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCampFormulariGet(campFormulariID,
        request, response, true);
  }


  protected ModelAndView editAndViewCampFormulariGet(@PathVariable("campFormulariID") java.lang.Long campFormulariID,
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
    CampFormulariJPA campFormulari = findByPrimaryKey(request, campFormulariID);

    if (campFormulari == null) {
      createMessageWarning(request, "error.notfound", campFormulariID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, campFormulariID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      CampFormulariForm campFormulariForm = getCampFormulariForm(campFormulari, __isView, request, mav);
      campFormulariForm.setView(__isView);
      if(__isView) {
        campFormulariForm.setAllFieldsReadOnly(ALL_CAMPFORMULARI_FIELDS);
        campFormulariForm.setSaveButtonVisible(false);
        campFormulariForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(campFormulariForm, request, mav);
      mav.addObject("campFormulariForm", campFormulariForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un CampFormulari existent
   */
  @RequestMapping(value = "/{campFormulariID}/edit", method = RequestMethod.GET)
  public ModelAndView editarCampFormulariGet(@PathVariable("campFormulariID") java.lang.Long campFormulariID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCampFormulariGet(campFormulariID,
        request, response, false);
  }



  /**
   * Editar un CampFormulari existent
   */
  @RequestMapping(value = "/{campFormulariID}/edit", method = RequestMethod.POST)
  public String editarCampFormulariPost(@ModelAttribute @Valid CampFormulariForm campFormulariForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    CampFormulariJPA campFormulari = campFormulariForm.getCampFormulari();

    try {
      preValidate(request, campFormulariForm, result);
      getWebValidator().validate(campFormulari, result);
      postValidate(request, campFormulariForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        campFormulari = update(request, campFormulari);
        createMessageSuccess(request, "success.modification", campFormulari.getCampFormulariID());
        status.setComplete();
        return getRedirectWhenModified(request, campFormulariForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          campFormulari.getCampFormulariID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, campFormulariForm, __e);
    }

  }


  /**
   * Eliminar un CampFormulari existent
   */
  @RequestMapping(value = "/{campFormulariID}/delete")
  public String eliminarCampFormulari(@PathVariable("campFormulariID") java.lang.Long campFormulariID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      CampFormulari campFormulari = findByPrimaryKey(request, campFormulariID);
      if (campFormulari == null) {
        String __msg =createMessageError(request, "error.notfound", campFormulariID);
        return getRedirectWhenDelete(request, campFormulariID, new Exception(__msg));
      } else {
        delete(request, campFormulari);
        createMessageSuccess(request, "success.deleted", campFormulariID);
        return getRedirectWhenDelete(request, campFormulariID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", campFormulariID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, campFormulariID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute CampFormulariFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarCampFormulari(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __campFormulariID, Throwable e) {
    java.lang.Long campFormulariID = (java.lang.Long)__campFormulariID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (campFormulariID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(campFormulariID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "campFormulari.campFormulari";
  }

  public String getEntityNameCodePlural() {
    return "campFormulari.campFormulari.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("campFormulari.campFormulariID");
  }

  @InitBinder("campFormulariFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("campFormulariForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    initDisallowedFields(binder, "campFormulari.campFormulariID");
  }

  public CampFormulariWebValidator getWebValidator() {
    return campFormulariWebValidator;
  }


  public void setWebValidator(CampFormulariWebValidator __val) {
    if (__val != null) {
      this.campFormulariWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de CampFormulari
   */
  @RequestMapping(value = "/{campFormulariID}/cancel")
  public String cancelCampFormulari(@PathVariable("campFormulariID") java.lang.Long campFormulariID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, campFormulariID);
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


  public List<StringKeyValue> getReferenceListForFormulariID(HttpServletRequest request,
       ModelAndView mav, CampFormulariForm campFormulariForm, Where where)  throws I18NException {
    if (campFormulariForm.isHiddenField(FORMULARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (campFormulariForm.isReadOnlyField(FORMULARIID)) {
      _where = FormulariFields.FORMULARIID.equal(campFormulariForm.getCampFormulari().getFormulariID());
    }
    return getReferenceListForFormulariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFormulariID(HttpServletRequest request,
       ModelAndView mav, CampFormulariFilterForm campFormulariFilterForm,
       List<CampFormulari> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (campFormulariFilterForm.isHiddenField(FORMULARIID)
      && !campFormulariFilterForm.isGroupByField(FORMULARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FORMULARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (CampFormulari _item : list) {
        _pkList.add(_item.getFormulariID());
        }
        _w = FormulariFields.FORMULARIID.in(_pkList);
      }
    return getReferenceListForFormulariID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFormulariID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return formulariRefList.getReferenceList(FormulariFields.FORMULARIID, where );
  }


  public void preValidate(HttpServletRequest request,CampFormulariForm campFormulariForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,CampFormulariForm campFormulariForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, CampFormulariFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, CampFormulariFilterForm filterForm,  List<CampFormulari> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, CampFormulariForm campFormulariForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, CampFormulariForm campFormulariForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long campFormulariID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long campFormulariID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "campFormulariFormWebDB";
  }

  public String getTileList() {
    return "campFormulariListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "CampFormulariWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public CampFormulariJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long campFormulariID) throws I18NException {
    return (CampFormulariJPA) campFormulariEjb.findByPrimaryKey(campFormulariID);
  }


  public CampFormulariJPA create(HttpServletRequest request, CampFormulariJPA campFormulari)
    throws Exception,I18NException, I18NValidationException {
    return (CampFormulariJPA) campFormulariEjb.create(campFormulari);
  }


  public CampFormulariJPA update(HttpServletRequest request, CampFormulariJPA campFormulari)
    throws Exception,I18NException, I18NValidationException {
    return (CampFormulariJPA) campFormulariEjb.update(campFormulari);
  }


  public void delete(HttpServletRequest request, CampFormulari campFormulari) throws Exception,I18NException {
    campFormulariEjb.delete(campFormulari);
  }

} // Final de Classe

