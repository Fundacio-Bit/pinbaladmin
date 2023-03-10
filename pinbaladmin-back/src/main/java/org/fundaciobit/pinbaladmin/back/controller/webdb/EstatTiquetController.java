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
import org.fundaciobit.pinbaladmin.back.form.webdb.EstatTiquetForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.EstatTiquetWebValidator;

import org.fundaciobit.pinbaladmin.persistence.EstatTiquetJPA;
import org.fundaciobit.pinbaladmin.model.entity.EstatTiquet;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un EstatTiquet
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/estatTiquet")
@SessionAttributes(types = { EstatTiquetForm.class, EstatTiquetFilterForm.class })
public class EstatTiquetController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<EstatTiquet, java.lang.Long> implements EstatTiquetFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatTiquetService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatTiquetService estatTiquetEjb;

  @Autowired
  private EstatTiquetWebValidator estatTiquetWebValidator;

  @Autowired
  protected EstatTiquetRefList estatTiquetRefList;

  /**
   * Llistat de totes EstatTiquet
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EstatTiquetFilterForm ff;
    ff = (EstatTiquetFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar EstatTiquet de forma paginada
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
    llistat(mav, request, getEstatTiquetFilterForm(pagina, mav, request));
    return mav;
  }

  public EstatTiquetFilterForm getEstatTiquetFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EstatTiquetFilterForm estatTiquetFilterForm;
    estatTiquetFilterForm = (EstatTiquetFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(estatTiquetFilterForm == null) {
      estatTiquetFilterForm = new EstatTiquetFilterForm();
      estatTiquetFilterForm.setContexte(getContextWeb());
      estatTiquetFilterForm.setEntityNameCode(getEntityNameCode());
      estatTiquetFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      estatTiquetFilterForm.setNou(true);
    } else {
      estatTiquetFilterForm.setNou(false);
    }
    estatTiquetFilterForm.setPage(pagina == null ? 1 : pagina);
    return estatTiquetFilterForm;
  }

  /**
   * Segona i següent peticions per llistar EstatTiquet de forma paginada
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
      @ModelAttribute EstatTiquetFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEstatTiquetFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de EstatTiquet de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<EstatTiquet> llistat(ModelAndView mav, HttpServletRequest request,
     EstatTiquetFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<EstatTiquet> estatTiquet = processarLlistat(estatTiquetEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("estatTiquetItems", estatTiquet);

    mav.addObject("estatTiquetFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, estatTiquet, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, estatTiquet);

    return estatTiquet;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EstatTiquetFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<EstatTiquet> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EstatTiquetFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<EstatTiquet> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ESTATTIQUET_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou EstatTiquet
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEstatTiquetGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EstatTiquetForm estatTiquetForm = getEstatTiquetForm(null, false, request, mav);
    mav.addObject("estatTiquetForm" ,estatTiquetForm);
    fillReferencesForForm(estatTiquetForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EstatTiquetForm getEstatTiquetForm(EstatTiquetJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EstatTiquetForm estatTiquetForm;
    if(_jpa == null) {
      estatTiquetForm = new EstatTiquetForm(new EstatTiquetJPA(), true);
    } else {
      estatTiquetForm = new EstatTiquetForm(_jpa, false);
      estatTiquetForm.setView(__isView);
    }
    estatTiquetForm.setContexte(getContextWeb());
    estatTiquetForm.setEntityNameCode(getEntityNameCode());
    estatTiquetForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return estatTiquetForm;
  }

  public void fillReferencesForForm(EstatTiquetForm estatTiquetForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou EstatTiquet
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEstatTiquetPost(@ModelAttribute EstatTiquetForm estatTiquetForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EstatTiquetJPA estatTiquet = estatTiquetForm.getEstatTiquet();

    try {
      preValidate(request, estatTiquetForm, result);
      getWebValidator().validate(estatTiquetForm, result);
      postValidate(request,estatTiquetForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        estatTiquet = create(request, estatTiquet);
        createMessageSuccess(request, "success.creation", estatTiquet.getEstatTiquetID());
        estatTiquetForm.setEstatTiquet(estatTiquet);
        return getRedirectWhenCreated(request, estatTiquetForm);
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

  @RequestMapping(value = "/view/{estatTiquetID}", method = RequestMethod.GET)
  public ModelAndView veureEstatTiquetGet(@PathVariable("estatTiquetID") java.lang.Long estatTiquetID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstatTiquetGet(estatTiquetID,
        request, response, true);
  }


  protected ModelAndView editAndViewEstatTiquetGet(@PathVariable("estatTiquetID") java.lang.Long estatTiquetID,
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
    EstatTiquetJPA estatTiquet = findByPrimaryKey(request, estatTiquetID);

    if (estatTiquet == null) {
      createMessageWarning(request, "error.notfound", estatTiquetID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, estatTiquetID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EstatTiquetForm estatTiquetForm = getEstatTiquetForm(estatTiquet, __isView, request, mav);
      estatTiquetForm.setView(__isView);
      if(__isView) {
        estatTiquetForm.setAllFieldsReadOnly(ALL_ESTATTIQUET_FIELDS);
        estatTiquetForm.setSaveButtonVisible(false);
        estatTiquetForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(estatTiquetForm, request, mav);
      mav.addObject("estatTiquetForm", estatTiquetForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un EstatTiquet existent
   */
  @RequestMapping(value = "/{estatTiquetID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEstatTiquetGet(@PathVariable("estatTiquetID") java.lang.Long estatTiquetID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstatTiquetGet(estatTiquetID,
        request, response, false);
  }



  /**
   * Editar un EstatTiquet existent
   */
  @RequestMapping(value = "/{estatTiquetID}/edit", method = RequestMethod.POST)
  public String editarEstatTiquetPost(@ModelAttribute EstatTiquetForm estatTiquetForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EstatTiquetJPA estatTiquet = estatTiquetForm.getEstatTiquet();

    try {
      preValidate(request, estatTiquetForm, result);
      getWebValidator().validate(estatTiquetForm, result);
      postValidate(request, estatTiquetForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        estatTiquet = update(request, estatTiquet);
        createMessageSuccess(request, "success.modification", estatTiquet.getEstatTiquetID());
        status.setComplete();
        return getRedirectWhenModified(request, estatTiquetForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          estatTiquet.getEstatTiquetID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, estatTiquetForm, __e);
    }

  }


  /**
   * Eliminar un EstatTiquet existent
   */
  @RequestMapping(value = "/{estatTiquetID}/delete")
  public String eliminarEstatTiquet(@PathVariable("estatTiquetID") java.lang.Long estatTiquetID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      EstatTiquet estatTiquet = this.findByPrimaryKey(request, estatTiquetID);
      if (estatTiquet == null) {
        String __msg = createMessageError(request, "error.notfound", estatTiquetID);
        return getRedirectWhenDelete(request, estatTiquetID, new Exception(__msg));
      } else {
        delete(request, estatTiquet);
        createMessageSuccess(request, "success.deleted", estatTiquetID);
        return getRedirectWhenDelete(request, estatTiquetID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", estatTiquetID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, estatTiquetID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EstatTiquetFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEstatTiquet(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __estatTiquetID, Throwable e) {
    java.lang.Long estatTiquetID = (java.lang.Long)__estatTiquetID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (estatTiquetID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(estatTiquetID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "estatTiquet.estatTiquet";
  }

  public String getEntityNameCodePlural() {
    return "estatTiquet.estatTiquet.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("estatTiquet.estatTiquetID");
  }

  @InitBinder("estatTiquetFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("estatTiquetForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder);
  }

  public EstatTiquetWebValidator getWebValidator() {
    return estatTiquetWebValidator;
  }


  public void setWebValidator(EstatTiquetWebValidator __val) {
    if (__val != null) {
      this.estatTiquetWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de EstatTiquet
   */
  @RequestMapping(value = "/{estatTiquetID}/cancel")
  public String cancelEstatTiquet(@PathVariable("estatTiquetID") java.lang.Long estatTiquetID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, estatTiquetID);
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

  public void preValidate(HttpServletRequest request,EstatTiquetForm estatTiquetForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EstatTiquetForm estatTiquetForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EstatTiquetFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EstatTiquetFilterForm filterForm,  List<EstatTiquet> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EstatTiquetForm estatTiquetForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EstatTiquetForm estatTiquetForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long estatTiquetID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long estatTiquetID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "estatTiquetFormWebDB";
  }

  public String getTileList() {
    return "estatTiquetListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "EstatTiquetWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EstatTiquetJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long estatTiquetID) throws I18NException {
    return (EstatTiquetJPA) estatTiquetEjb.findByPrimaryKey(estatTiquetID);
  }


  public EstatTiquetJPA create(HttpServletRequest request, EstatTiquetJPA estatTiquet)
    throws I18NException, I18NValidationException {
    return (EstatTiquetJPA) estatTiquetEjb.create(estatTiquet);
  }


  public EstatTiquetJPA update(HttpServletRequest request, EstatTiquetJPA estatTiquet)
    throws I18NException, I18NValidationException {
    return (EstatTiquetJPA) estatTiquetEjb.update(estatTiquet);
  }


  public void delete(HttpServletRequest request, EstatTiquet estatTiquet) throws I18NException {
    estatTiquetEjb.delete(estatTiquet);
  }

} // Final de Classe

