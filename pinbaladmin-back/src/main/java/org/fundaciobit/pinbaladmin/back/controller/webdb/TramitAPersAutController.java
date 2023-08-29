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
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TramitAPersAutWebValidator;

import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un TramitAPersAut
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tramitAPersAut")
@SessionAttributes(types = { TramitAPersAutForm.class, TramitAPersAutFilterForm.class })
public class TramitAPersAutController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<TramitAPersAut, java.lang.Long> implements TramitAPersAutFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService tramitAPersAutEjb;

  @Autowired
  private TramitAPersAutWebValidator tramitAPersAutWebValidator;

  @Autowired
  protected TramitAPersAutRefList tramitAPersAutRefList;

  /**
   * Llistat de totes TramitAPersAut
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TramitAPersAutFilterForm ff;
    ff = (TramitAPersAutFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TramitAPersAut de forma paginada
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
    llistat(mav, request, getTramitAPersAutFilterForm(pagina, mav, request));
    return mav;
  }

  public TramitAPersAutFilterForm getTramitAPersAutFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TramitAPersAutFilterForm tramitAPersAutFilterForm;
    tramitAPersAutFilterForm = (TramitAPersAutFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tramitAPersAutFilterForm == null) {
      tramitAPersAutFilterForm = new TramitAPersAutFilterForm();
      tramitAPersAutFilterForm.setContexte(getContextWeb());
      tramitAPersAutFilterForm.setEntityNameCode(getEntityNameCode());
      tramitAPersAutFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tramitAPersAutFilterForm.setNou(true);
    } else {
      tramitAPersAutFilterForm.setNou(false);
    }
    tramitAPersAutFilterForm.setPage(pagina == null ? 1 : pagina);
    return tramitAPersAutFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TramitAPersAut de forma paginada
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
      @ModelAttribute TramitAPersAutFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTramitAPersAutFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TramitAPersAut de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TramitAPersAut> llistat(ModelAndView mav, HttpServletRequest request,
     TramitAPersAutFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TramitAPersAut> tramitAPersAut = processarLlistat(tramitAPersAutEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tramitAPersAutItems", tramitAPersAut);

    mav.addObject("tramitAPersAutFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tramitAPersAut, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tramitAPersAut);

    return tramitAPersAut;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TramitAPersAutFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TramitAPersAut> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TramitAPersAutFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TramitAPersAut> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TRAMITAPERSAUT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TramitAPersAut
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTramitAPersAutGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TramitAPersAutForm tramitAPersAutForm = getTramitAPersAutForm(null, false, request, mav);
    mav.addObject("tramitAPersAutForm" ,tramitAPersAutForm);
    fillReferencesForForm(tramitAPersAutForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TramitAPersAutForm getTramitAPersAutForm(TramitAPersAutJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TramitAPersAutForm tramitAPersAutForm;
    if(_jpa == null) {
      tramitAPersAutForm = new TramitAPersAutForm(new TramitAPersAutJPA(), true);
    } else {
      tramitAPersAutForm = new TramitAPersAutForm(_jpa, false);
      tramitAPersAutForm.setView(__isView);
    }
    tramitAPersAutForm.setContexte(getContextWeb());
    tramitAPersAutForm.setEntityNameCode(getEntityNameCode());
    tramitAPersAutForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tramitAPersAutForm;
  }

  public void fillReferencesForForm(TramitAPersAutForm tramitAPersAutForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou TramitAPersAut
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTramitAPersAutPost(@ModelAttribute TramitAPersAutForm tramitAPersAutForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TramitAPersAutJPA tramitAPersAut = tramitAPersAutForm.getTramitAPersAut();

    try {
      preValidate(request, tramitAPersAutForm, result);
      getWebValidator().validate(tramitAPersAutForm, result);
      postValidate(request,tramitAPersAutForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitAPersAut = create(request, tramitAPersAut);
        createMessageSuccess(request, "success.creation", tramitAPersAut.getPersautid());
        tramitAPersAutForm.setTramitAPersAut(tramitAPersAut);
        return getRedirectWhenCreated(request, tramitAPersAutForm);
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

  @RequestMapping(value = "/view/{persautid}", method = RequestMethod.GET)
  public ModelAndView veureTramitAPersAutGet(@PathVariable("persautid") java.lang.Long persautid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitAPersAutGet(persautid,
        request, response, true);
  }


  protected ModelAndView editAndViewTramitAPersAutGet(@PathVariable("persautid") java.lang.Long persautid,
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
    TramitAPersAutJPA tramitAPersAut = findByPrimaryKey(request, persautid);

    if (tramitAPersAut == null) {
      createMessageWarning(request, "error.notfound", persautid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, persautid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TramitAPersAutForm tramitAPersAutForm = getTramitAPersAutForm(tramitAPersAut, __isView, request, mav);
      tramitAPersAutForm.setView(__isView);
      if(__isView) {
        tramitAPersAutForm.setAllFieldsReadOnly(ALL_TRAMITAPERSAUT_FIELDS);
        tramitAPersAutForm.setSaveButtonVisible(false);
        tramitAPersAutForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tramitAPersAutForm, request, mav);
      mav.addObject("tramitAPersAutForm", tramitAPersAutForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TramitAPersAut existent
   */
  @RequestMapping(value = "/{persautid}/edit", method = RequestMethod.GET)
  public ModelAndView editarTramitAPersAutGet(@PathVariable("persautid") java.lang.Long persautid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTramitAPersAutGet(persautid,
        request, response, false);
  }



  /**
   * Editar un TramitAPersAut existent
   */
  @RequestMapping(value = "/{persautid}/edit", method = RequestMethod.POST)
  public String editarTramitAPersAutPost(@ModelAttribute TramitAPersAutForm tramitAPersAutForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TramitAPersAutJPA tramitAPersAut = tramitAPersAutForm.getTramitAPersAut();

    try {
      preValidate(request, tramitAPersAutForm, result);
      getWebValidator().validate(tramitAPersAutForm, result);
      postValidate(request, tramitAPersAutForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tramitAPersAut = update(request, tramitAPersAut);
        createMessageSuccess(request, "success.modification", tramitAPersAut.getPersautid());
        status.setComplete();
        return getRedirectWhenModified(request, tramitAPersAutForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tramitAPersAut.getPersautid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tramitAPersAutForm, __e);
    }

  }


  /**
   * Eliminar un TramitAPersAut existent
   */
  @RequestMapping(value = "/{persautid}/delete")
  public String eliminarTramitAPersAut(@PathVariable("persautid") java.lang.Long persautid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TramitAPersAut tramitAPersAut = this.findByPrimaryKey(request, persautid);
      if (tramitAPersAut == null) {
        String __msg = createMessageError(request, "error.notfound", persautid);
        return getRedirectWhenDelete(request, persautid, new Exception(__msg));
      } else {
        delete(request, tramitAPersAut);
        createMessageSuccess(request, "success.deleted", persautid);
        return getRedirectWhenDelete(request, persautid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", persautid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, persautid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TramitAPersAutFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTramitAPersAut(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __persautid, Throwable e) {
    java.lang.Long persautid = (java.lang.Long)__persautid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (persautid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(persautid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tramitAPersAut.tramitAPersAut";
  }

  public String getEntityNameCodePlural() {
    return "tramitAPersAut.tramitAPersAut.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tramitAPersAut.persautid");
  }

  @InitBinder("tramitAPersAutFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tramitAPersAutForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "tramitAPersAut.persautid");
  }

  public TramitAPersAutWebValidator getWebValidator() {
    return tramitAPersAutWebValidator;
  }


  public void setWebValidator(TramitAPersAutWebValidator __val) {
    if (__val != null) {
      this.tramitAPersAutWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TramitAPersAut
   */
  @RequestMapping(value = "/{persautid}/cancel")
  public String cancelTramitAPersAut(@PathVariable("persautid") java.lang.Long persautid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, persautid);
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

  public void preValidate(HttpServletRequest request,TramitAPersAutForm tramitAPersAutForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TramitAPersAutForm tramitAPersAutForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TramitAPersAutFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TramitAPersAutFilterForm filterForm,  List<TramitAPersAut> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TramitAPersAutForm tramitAPersAutForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TramitAPersAutForm tramitAPersAutForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long persautid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long persautid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tramitAPersAutFormWebDB";
  }

  public String getTileList() {
    return "tramitAPersAutListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TramitAPersAut_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TramitAPersAutJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long persautid) throws I18NException {
    return (TramitAPersAutJPA) tramitAPersAutEjb.findByPrimaryKey(persautid);
  }


  public TramitAPersAutJPA create(HttpServletRequest request, TramitAPersAutJPA tramitAPersAut)
    throws I18NException, I18NValidationException {
    return (TramitAPersAutJPA) tramitAPersAutEjb.create(tramitAPersAut);
  }


  public TramitAPersAutJPA update(HttpServletRequest request, TramitAPersAutJPA tramitAPersAut)
    throws I18NException, I18NValidationException {
    return (TramitAPersAutJPA) tramitAPersAutEjb.update(tramitAPersAut);
  }


  public void delete(HttpServletRequest request, TramitAPersAut tramitAPersAut) throws I18NException {
    tramitAPersAutEjb.delete(tramitAPersAut);
  }

} // Final de Classe

