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
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.fundaciobit.pinbaladmin.back.form.webdb.*;
import org.fundaciobit.pinbaladmin.back.form.webdb.EstatSolicitudForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.EstatSolicitudWebValidator;

import org.fundaciobit.pinbaladmin.jpa.EstatSolicitudJPA;
import org.fundaciobit.pinbaladmin.model.entity.EstatSolicitud;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un EstatSolicitud
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/estatSolicitud")
@SessionAttributes(types = { EstatSolicitudForm.class, EstatSolicitudFilterForm.class })
public class EstatSolicitudController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<EstatSolicitud, java.lang.Long> implements EstatSolicitudFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatSolicitudLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatSolicitudLocal estatSolicitudEjb;

  @Autowired
  private EstatSolicitudWebValidator estatSolicitudWebValidator;

  @Autowired
  protected EstatSolicitudRefList estatSolicitudRefList;

  /**
   * Llistat de totes EstatSolicitud
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EstatSolicitudFilterForm ff;
    ff = (EstatSolicitudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar EstatSolicitud de forma paginada
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
    llistat(mav, request, getEstatSolicitudFilterForm(pagina, mav, request));
    return mav;
  }

  public EstatSolicitudFilterForm getEstatSolicitudFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EstatSolicitudFilterForm estatSolicitudFilterForm;
    estatSolicitudFilterForm = (EstatSolicitudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(estatSolicitudFilterForm == null) {
      estatSolicitudFilterForm = new EstatSolicitudFilterForm();
      estatSolicitudFilterForm.setContexte(getContextWeb());
      estatSolicitudFilterForm.setEntityNameCode(getEntityNameCode());
      estatSolicitudFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      estatSolicitudFilterForm.setNou(true);
    } else {
      estatSolicitudFilterForm.setNou(false);
    }
    estatSolicitudFilterForm.setPage(pagina == null ? 1 : pagina);
    return estatSolicitudFilterForm;
  }

  /**
   * Segona i següent peticions per llistar EstatSolicitud de forma paginada
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
      @ModelAttribute EstatSolicitudFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEstatSolicitudFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de EstatSolicitud de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<EstatSolicitud> llistat(ModelAndView mav, HttpServletRequest request,
     EstatSolicitudFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<EstatSolicitud> estatSolicitud = processarLlistat(estatSolicitudEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("estatSolicitudItems", estatSolicitud);

    mav.addObject("estatSolicitudFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, estatSolicitud, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, estatSolicitud);

    return estatSolicitud;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EstatSolicitudFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<EstatSolicitud> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EstatSolicitudFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<EstatSolicitud> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ESTATSOLICITUD_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou EstatSolicitud
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEstatSolicitudGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EstatSolicitudForm estatSolicitudForm = getEstatSolicitudForm(null, false, request, mav);
    mav.addObject("estatSolicitudForm" ,estatSolicitudForm);
    fillReferencesForForm(estatSolicitudForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EstatSolicitudForm getEstatSolicitudForm(EstatSolicitudJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EstatSolicitudForm estatSolicitudForm;
    if(_jpa == null) {
      estatSolicitudForm = new EstatSolicitudForm(new EstatSolicitudJPA(), true);
    } else {
      estatSolicitudForm = new EstatSolicitudForm(_jpa, false);
      estatSolicitudForm.setView(__isView);
    }
    estatSolicitudForm.setContexte(getContextWeb());
    estatSolicitudForm.setEntityNameCode(getEntityNameCode());
    estatSolicitudForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return estatSolicitudForm;
  }

  public void fillReferencesForForm(EstatSolicitudForm estatSolicitudForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou EstatSolicitud
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEstatSolicitudPost(@ModelAttribute EstatSolicitudForm estatSolicitudForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EstatSolicitudJPA estatSolicitud = estatSolicitudForm.getEstatSolicitud();

    try {
      preValidate(request, estatSolicitudForm, result);
      getWebValidator().validate(estatSolicitudForm, result);
      postValidate(request,estatSolicitudForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        estatSolicitud = create(request, estatSolicitud);
        createMessageSuccess(request, "success.creation", estatSolicitud.getEstatSolicitudID());
        estatSolicitudForm.setEstatSolicitud(estatSolicitud);
        return getRedirectWhenCreated(request, estatSolicitudForm);
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

  @RequestMapping(value = "/view/{estatSolicitudID}", method = RequestMethod.GET)
  public ModelAndView veureEstatSolicitudGet(@PathVariable("estatSolicitudID") java.lang.Long estatSolicitudID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstatSolicitudGet(estatSolicitudID,
        request, response, true);
  }


  protected ModelAndView editAndViewEstatSolicitudGet(@PathVariable("estatSolicitudID") java.lang.Long estatSolicitudID,
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
    EstatSolicitudJPA estatSolicitud = findByPrimaryKey(request, estatSolicitudID);

    if (estatSolicitud == null) {
      createMessageWarning(request, "error.notfound", estatSolicitudID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, estatSolicitudID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EstatSolicitudForm estatSolicitudForm = getEstatSolicitudForm(estatSolicitud, __isView, request, mav);
      estatSolicitudForm.setView(__isView);
      if(__isView) {
        estatSolicitudForm.setAllFieldsReadOnly(ALL_ESTATSOLICITUD_FIELDS);
        estatSolicitudForm.setSaveButtonVisible(false);
        estatSolicitudForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(estatSolicitudForm, request, mav);
      mav.addObject("estatSolicitudForm", estatSolicitudForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un EstatSolicitud existent
   */
  @RequestMapping(value = "/{estatSolicitudID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEstatSolicitudGet(@PathVariable("estatSolicitudID") java.lang.Long estatSolicitudID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstatSolicitudGet(estatSolicitudID,
        request, response, false);
  }



  /**
   * Editar un EstatSolicitud existent
   */
  @RequestMapping(value = "/{estatSolicitudID}/edit", method = RequestMethod.POST)
  public String editarEstatSolicitudPost(@ModelAttribute @Valid EstatSolicitudForm estatSolicitudForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EstatSolicitudJPA estatSolicitud = estatSolicitudForm.getEstatSolicitud();

    try {
      preValidate(request, estatSolicitudForm, result);
      getWebValidator().validate(estatSolicitud, result);
      postValidate(request, estatSolicitudForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        estatSolicitud = update(request, estatSolicitud);
        createMessageSuccess(request, "success.modification", estatSolicitud.getEstatSolicitudID());
        status.setComplete();
        return getRedirectWhenModified(request, estatSolicitudForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          estatSolicitud.getEstatSolicitudID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, estatSolicitudForm, __e);
    }

  }


  /**
   * Eliminar un EstatSolicitud existent
   */
  @RequestMapping(value = "/{estatSolicitudID}/delete")
  public String eliminarEstatSolicitud(@PathVariable("estatSolicitudID") java.lang.Long estatSolicitudID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      EstatSolicitud estatSolicitud = findByPrimaryKey(request, estatSolicitudID);
      if (estatSolicitud == null) {
        String __msg =createMessageError(request, "error.notfound", estatSolicitudID);
        return getRedirectWhenDelete(request, estatSolicitudID, new Exception(__msg));
      } else {
        delete(request, estatSolicitud);
        createMessageSuccess(request, "success.deleted", estatSolicitudID);
        return getRedirectWhenDelete(request, estatSolicitudID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", estatSolicitudID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, estatSolicitudID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EstatSolicitudFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEstatSolicitud(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __estatSolicitudID, Throwable e) {
    java.lang.Long estatSolicitudID = (java.lang.Long)__estatSolicitudID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (estatSolicitudID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(estatSolicitudID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "estatSolicitud.estatSolicitud";
  }

  public String getEntityNameCodePlural() {
    return "estatSolicitud.estatSolicitud.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("estatSolicitud.estatSolicitudID");
  }

  @InitBinder("estatSolicitudFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("estatSolicitudForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    initDisallowedFields(binder);
  }

  public EstatSolicitudWebValidator getWebValidator() {
    return estatSolicitudWebValidator;
  }


  public void setWebValidator(EstatSolicitudWebValidator __val) {
    if (__val != null) {
      this.estatSolicitudWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de EstatSolicitud
   */
  @RequestMapping(value = "/{estatSolicitudID}/cancel")
  public String cancelEstatSolicitud(@PathVariable("estatSolicitudID") java.lang.Long estatSolicitudID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, estatSolicitudID);
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


  public void preValidate(HttpServletRequest request,EstatSolicitudForm estatSolicitudForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EstatSolicitudForm estatSolicitudForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EstatSolicitudFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EstatSolicitudFilterForm filterForm,  List<EstatSolicitud> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EstatSolicitudForm estatSolicitudForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EstatSolicitudForm estatSolicitudForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long estatSolicitudID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long estatSolicitudID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "estatSolicitudFormWebDB";
  }

  public String getTileList() {
    return "estatSolicitudListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "EstatSolicitudWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EstatSolicitudJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long estatSolicitudID) throws I18NException {
    return (EstatSolicitudJPA) estatSolicitudEjb.findByPrimaryKey(estatSolicitudID);
  }


  public EstatSolicitudJPA create(HttpServletRequest request, EstatSolicitudJPA estatSolicitud)
    throws Exception,I18NException, I18NValidationException {
    return (EstatSolicitudJPA) estatSolicitudEjb.create(estatSolicitud);
  }


  public EstatSolicitudJPA update(HttpServletRequest request, EstatSolicitudJPA estatSolicitud)
    throws Exception,I18NException, I18NValidationException {
    return (EstatSolicitudJPA) estatSolicitudEjb.update(estatSolicitud);
  }


  public void delete(HttpServletRequest request, EstatSolicitud estatSolicitud) throws Exception,I18NException {
    estatSolicitudEjb.delete(estatSolicitud);
  }

} // Final de Classe

