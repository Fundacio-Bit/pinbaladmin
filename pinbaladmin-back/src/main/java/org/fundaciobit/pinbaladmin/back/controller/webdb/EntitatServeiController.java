package org.fundaciobit.pinbaladmin.back.controller.webdb;

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
import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatServeiForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.EntitatServeiWebValidator;

import org.fundaciobit.pinbaladmin.persistence.EntitatServeiJPA;
import org.fundaciobit.pinbaladmin.model.entity.EntitatServei;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un EntitatServei
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/entitatServei")
@SessionAttributes(types = { EntitatServeiForm.class, EntitatServeiFilterForm.class })
public class EntitatServeiController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<EntitatServei, java.lang.Long> implements EntitatServeiFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatServeiService entitatServeiEjb;

  @Autowired
  private EntitatServeiWebValidator entitatServeiWebValidator;

  @Autowired
  protected EntitatServeiRefList entitatServeiRefList;

  /**
   * Llistat de totes EntitatServei
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EntitatServeiFilterForm ff;
    ff = (EntitatServeiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar EntitatServei de forma paginada
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
    llistat(mav, request, getEntitatServeiFilterForm(pagina, mav, request));
    return mav;
  }

  public EntitatServeiFilterForm getEntitatServeiFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EntitatServeiFilterForm entitatServeiFilterForm;
    entitatServeiFilterForm = (EntitatServeiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(entitatServeiFilterForm == null) {
      entitatServeiFilterForm = new EntitatServeiFilterForm();
      entitatServeiFilterForm.setContexte(getContextWeb());
      entitatServeiFilterForm.setEntityNameCode(getEntityNameCode());
      entitatServeiFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      entitatServeiFilterForm.setNou(true);
    } else {
      entitatServeiFilterForm.setNou(false);
    }
    entitatServeiFilterForm.setPage(pagina == null ? 1 : pagina);
    return entitatServeiFilterForm;
  }

  /**
   * Segona i següent peticions per llistar EntitatServei de forma paginada
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
      @ModelAttribute EntitatServeiFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEntitatServeiFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de EntitatServei de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<EntitatServei> llistat(ModelAndView mav, HttpServletRequest request,
     EntitatServeiFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<EntitatServei> entitatServei = processarLlistat(entitatServeiEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("entitatServeiItems", entitatServei);

    mav.addObject("entitatServeiFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, entitatServei, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, entitatServei);

    return entitatServei;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EntitatServeiFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<EntitatServei> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, BALEARS);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EntitatServeiFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<EntitatServei> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ENTITATSERVEI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou EntitatServei
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEntitatServeiGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EntitatServeiForm entitatServeiForm = getEntitatServeiForm(null, false, request, mav);
    mav.addObject("entitatServeiForm" ,entitatServeiForm);
    fillReferencesForForm(entitatServeiForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EntitatServeiForm getEntitatServeiForm(EntitatServeiJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EntitatServeiForm entitatServeiForm;
    if(_jpa == null) {
      entitatServeiForm = new EntitatServeiForm(new EntitatServeiJPA(), true);
    } else {
      entitatServeiForm = new EntitatServeiForm(_jpa, false);
      entitatServeiForm.setView(__isView);
    }
    entitatServeiForm.setContexte(getContextWeb());
    entitatServeiForm.setEntityNameCode(getEntityNameCode());
    entitatServeiForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return entitatServeiForm;
  }

  public void fillReferencesForForm(EntitatServeiForm entitatServeiForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou EntitatServei
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEntitatServeiPost(@ModelAttribute EntitatServeiForm entitatServeiForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EntitatServeiJPA entitatServei = entitatServeiForm.getEntitatServei();

    try {
      preValidate(request, entitatServeiForm, result);
      getWebValidator().validate(entitatServeiForm, result);
      postValidate(request,entitatServeiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        entitatServei = create(request, entitatServei);
        createMessageSuccess(request, "success.creation", entitatServei.getEntitatServeiID());
        entitatServeiForm.setEntitatServei(entitatServei);
        return getRedirectWhenCreated(request, entitatServeiForm);
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

  @RequestMapping(value = "/view/{entitatServeiID}", method = RequestMethod.GET)
  public ModelAndView veureEntitatServeiGet(@PathVariable("entitatServeiID") java.lang.Long entitatServeiID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEntitatServeiGet(entitatServeiID,
        request, response, true);
  }


  protected ModelAndView editAndViewEntitatServeiGet(@PathVariable("entitatServeiID") java.lang.Long entitatServeiID,
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
    EntitatServeiJPA entitatServei = findByPrimaryKey(request, entitatServeiID);

    if (entitatServei == null) {
      createMessageWarning(request, "error.notfound", entitatServeiID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, entitatServeiID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EntitatServeiForm entitatServeiForm = getEntitatServeiForm(entitatServei, __isView, request, mav);
      entitatServeiForm.setView(__isView);
      if(__isView) {
        entitatServeiForm.setAllFieldsReadOnly(ALL_ENTITATSERVEI_FIELDS);
        entitatServeiForm.setSaveButtonVisible(false);
        entitatServeiForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(entitatServeiForm, request, mav);
      mav.addObject("entitatServeiForm", entitatServeiForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un EntitatServei existent
   */
  @RequestMapping(value = "/{entitatServeiID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEntitatServeiGet(@PathVariable("entitatServeiID") java.lang.Long entitatServeiID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEntitatServeiGet(entitatServeiID,
        request, response, false);
  }



  /**
   * Editar un EntitatServei existent
   */
  @RequestMapping(value = "/{entitatServeiID}/edit", method = RequestMethod.POST)
  public String editarEntitatServeiPost(@ModelAttribute EntitatServeiForm entitatServeiForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EntitatServeiJPA entitatServei = entitatServeiForm.getEntitatServei();

    try {
      preValidate(request, entitatServeiForm, result);
      getWebValidator().validate(entitatServeiForm, result);
      postValidate(request, entitatServeiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        entitatServei = update(request, entitatServei);
        createMessageSuccess(request, "success.modification", entitatServei.getEntitatServeiID());
        status.setComplete();
        return getRedirectWhenModified(request, entitatServeiForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          entitatServei.getEntitatServeiID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, entitatServeiForm, __e);
    }

  }


  /**
   * Eliminar un EntitatServei existent
   */
  @RequestMapping(value = "/{entitatServeiID}/delete")
  public String eliminarEntitatServei(@PathVariable("entitatServeiID") java.lang.Long entitatServeiID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      EntitatServei entitatServei = this.findByPrimaryKey(request, entitatServeiID);
      if (entitatServei == null) {
        String __msg = createMessageError(request, "error.notfound", entitatServeiID);
        return getRedirectWhenDelete(request, entitatServeiID, new Exception(__msg));
      } else {
        delete(request, entitatServei);
        createMessageSuccess(request, "success.deleted", entitatServeiID);
        return getRedirectWhenDelete(request, entitatServeiID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", entitatServeiID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, entitatServeiID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EntitatServeiFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEntitatServei(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __entitatServeiID, Throwable e) {
    java.lang.Long entitatServeiID = (java.lang.Long)__entitatServeiID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (entitatServeiID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(entitatServeiID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "entitatServei.entitatServei";
  }

  public String getEntityNameCodePlural() {
    return "entitatServei.entitatServei.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("entitatServei.entitatServeiID");
  }

  @InitBinder("entitatServeiFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("entitatServeiForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "entitatServei.entitatServeiID");
  }

  public EntitatServeiWebValidator getWebValidator() {
    return entitatServeiWebValidator;
  }


  public void setWebValidator(EntitatServeiWebValidator __val) {
    if (__val != null) {
      this.entitatServeiWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de EntitatServei
   */
  @RequestMapping(value = "/{entitatServeiID}/cancel")
  public String cancelEntitatServei(@PathVariable("entitatServeiID") java.lang.Long entitatServeiID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, entitatServeiID);
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,EntitatServeiForm entitatServeiForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EntitatServeiForm entitatServeiForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EntitatServeiFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EntitatServeiFilterForm filterForm,  List<EntitatServei> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EntitatServeiForm entitatServeiForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EntitatServeiForm entitatServeiForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long entitatServeiID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long entitatServeiID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "entitatServeiFormWebDB";
  }

  public String getTileList() {
    return "entitatServeiListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "EntitatServei_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EntitatServeiJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long entitatServeiID) throws I18NException {
    return (EntitatServeiJPA) entitatServeiEjb.findByPrimaryKey(entitatServeiID);
  }


  public EntitatServeiJPA create(HttpServletRequest request, EntitatServeiJPA entitatServei)
    throws I18NException, I18NValidationException {
    return (EntitatServeiJPA) entitatServeiEjb.create(entitatServei);
  }


  public EntitatServeiJPA update(HttpServletRequest request, EntitatServeiJPA entitatServei)
    throws I18NException, I18NValidationException {
    return (EntitatServeiJPA) entitatServeiEjb.update(entitatServei);
  }


  public void delete(HttpServletRequest request, EntitatServei entitatServei) throws I18NException {
    entitatServeiEjb.delete(entitatServei);
  }

} // Final de Classe

