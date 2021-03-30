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
import org.fundaciobit.pinbaladmin.back.form.webdb.EstatSolicitudServeiForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.EstatSolicitudServeiWebValidator;

import org.fundaciobit.pinbaladmin.jpa.EstatSolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.model.entity.EstatSolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un EstatSolicitudServei
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/estatSolicitudServei")
@SessionAttributes(types = { EstatSolicitudServeiForm.class, EstatSolicitudServeiFilterForm.class })
public class EstatSolicitudServeiController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<EstatSolicitudServei, java.lang.Long> implements EstatSolicitudServeiFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatSolicitudServeiLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatSolicitudServeiLocal estatSolicitudServeiEjb;

  @Autowired
  private EstatSolicitudServeiWebValidator estatSolicitudServeiWebValidator;

  @Autowired
  protected EstatSolicitudServeiRefList estatSolicitudServeiRefList;

  /**
   * Llistat de totes EstatSolicitudServei
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EstatSolicitudServeiFilterForm ff;
    ff = (EstatSolicitudServeiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar EstatSolicitudServei de forma paginada
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
    llistat(mav, request, getEstatSolicitudServeiFilterForm(pagina, mav, request));
    return mav;
  }

  public EstatSolicitudServeiFilterForm getEstatSolicitudServeiFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EstatSolicitudServeiFilterForm estatSolicitudServeiFilterForm;
    estatSolicitudServeiFilterForm = (EstatSolicitudServeiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(estatSolicitudServeiFilterForm == null) {
      estatSolicitudServeiFilterForm = new EstatSolicitudServeiFilterForm();
      estatSolicitudServeiFilterForm.setContexte(getContextWeb());
      estatSolicitudServeiFilterForm.setEntityNameCode(getEntityNameCode());
      estatSolicitudServeiFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      estatSolicitudServeiFilterForm.setNou(true);
    } else {
      estatSolicitudServeiFilterForm.setNou(false);
    }
    estatSolicitudServeiFilterForm.setPage(pagina == null ? 1 : pagina);
    return estatSolicitudServeiFilterForm;
  }

  /**
   * Segona i següent peticions per llistar EstatSolicitudServei de forma paginada
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
      @ModelAttribute EstatSolicitudServeiFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEstatSolicitudServeiFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de EstatSolicitudServei de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<EstatSolicitudServei> llistat(ModelAndView mav, HttpServletRequest request,
     EstatSolicitudServeiFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<EstatSolicitudServei> estatSolicitudServei = processarLlistat(estatSolicitudServeiEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("estatSolicitudServeiItems", estatSolicitudServei);

    mav.addObject("estatSolicitudServeiFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, estatSolicitudServei, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, estatSolicitudServei);

    return estatSolicitudServei;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EstatSolicitudServeiFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<EstatSolicitudServei> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EstatSolicitudServeiFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<EstatSolicitudServei> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ESTATSOLICITUDSERVEI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou EstatSolicitudServei
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEstatSolicitudServeiGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EstatSolicitudServeiForm estatSolicitudServeiForm = getEstatSolicitudServeiForm(null, false, request, mav);
    mav.addObject("estatSolicitudServeiForm" ,estatSolicitudServeiForm);
    fillReferencesForForm(estatSolicitudServeiForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EstatSolicitudServeiForm getEstatSolicitudServeiForm(EstatSolicitudServeiJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EstatSolicitudServeiForm estatSolicitudServeiForm;
    if(_jpa == null) {
      estatSolicitudServeiForm = new EstatSolicitudServeiForm(new EstatSolicitudServeiJPA(), true);
    } else {
      estatSolicitudServeiForm = new EstatSolicitudServeiForm(_jpa, false);
      estatSolicitudServeiForm.setView(__isView);
    }
    estatSolicitudServeiForm.setContexte(getContextWeb());
    estatSolicitudServeiForm.setEntityNameCode(getEntityNameCode());
    estatSolicitudServeiForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return estatSolicitudServeiForm;
  }

  public void fillReferencesForForm(EstatSolicitudServeiForm estatSolicitudServeiForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou EstatSolicitudServei
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEstatSolicitudServeiPost(@ModelAttribute EstatSolicitudServeiForm estatSolicitudServeiForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EstatSolicitudServeiJPA estatSolicitudServei = estatSolicitudServeiForm.getEstatSolicitudServei();

    try {
      preValidate(request, estatSolicitudServeiForm, result);
      getWebValidator().validate(estatSolicitudServeiForm, result);
      postValidate(request,estatSolicitudServeiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        estatSolicitudServei = create(request, estatSolicitudServei);
        createMessageSuccess(request, "success.creation", estatSolicitudServei.getEstatSolicitudServeiID());
        estatSolicitudServeiForm.setEstatSolicitudServei(estatSolicitudServei);
        return getRedirectWhenCreated(request, estatSolicitudServeiForm);
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

  @RequestMapping(value = "/view/{estatSolicitudServeiID}", method = RequestMethod.GET)
  public ModelAndView veureEstatSolicitudServeiGet(@PathVariable("estatSolicitudServeiID") java.lang.Long estatSolicitudServeiID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstatSolicitudServeiGet(estatSolicitudServeiID,
        request, response, true);
  }


  protected ModelAndView editAndViewEstatSolicitudServeiGet(@PathVariable("estatSolicitudServeiID") java.lang.Long estatSolicitudServeiID,
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
    EstatSolicitudServeiJPA estatSolicitudServei = findByPrimaryKey(request, estatSolicitudServeiID);

    if (estatSolicitudServei == null) {
      createMessageWarning(request, "error.notfound", estatSolicitudServeiID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, estatSolicitudServeiID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EstatSolicitudServeiForm estatSolicitudServeiForm = getEstatSolicitudServeiForm(estatSolicitudServei, __isView, request, mav);
      estatSolicitudServeiForm.setView(__isView);
      if(__isView) {
        estatSolicitudServeiForm.setAllFieldsReadOnly(ALL_ESTATSOLICITUDSERVEI_FIELDS);
        estatSolicitudServeiForm.setSaveButtonVisible(false);
        estatSolicitudServeiForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(estatSolicitudServeiForm, request, mav);
      mav.addObject("estatSolicitudServeiForm", estatSolicitudServeiForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un EstatSolicitudServei existent
   */
  @RequestMapping(value = "/{estatSolicitudServeiID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEstatSolicitudServeiGet(@PathVariable("estatSolicitudServeiID") java.lang.Long estatSolicitudServeiID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstatSolicitudServeiGet(estatSolicitudServeiID,
        request, response, false);
  }



  /**
   * Editar un EstatSolicitudServei existent
   */
  @RequestMapping(value = "/{estatSolicitudServeiID}/edit", method = RequestMethod.POST)
  public String editarEstatSolicitudServeiPost(@ModelAttribute @Valid EstatSolicitudServeiForm estatSolicitudServeiForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EstatSolicitudServeiJPA estatSolicitudServei = estatSolicitudServeiForm.getEstatSolicitudServei();

    try {
      preValidate(request, estatSolicitudServeiForm, result);
      getWebValidator().validate(estatSolicitudServei, result);
      postValidate(request, estatSolicitudServeiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        estatSolicitudServei = update(request, estatSolicitudServei);
        createMessageSuccess(request, "success.modification", estatSolicitudServei.getEstatSolicitudServeiID());
        status.setComplete();
        return getRedirectWhenModified(request, estatSolicitudServeiForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          estatSolicitudServei.getEstatSolicitudServeiID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, estatSolicitudServeiForm, __e);
    }

  }


  /**
   * Eliminar un EstatSolicitudServei existent
   */
  @RequestMapping(value = "/{estatSolicitudServeiID}/delete")
  public String eliminarEstatSolicitudServei(@PathVariable("estatSolicitudServeiID") java.lang.Long estatSolicitudServeiID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      EstatSolicitudServei estatSolicitudServei = estatSolicitudServeiEjb.findByPrimaryKey(estatSolicitudServeiID);
      if (estatSolicitudServei == null) {
        String __msg =createMessageError(request, "error.notfound", estatSolicitudServeiID);
        return getRedirectWhenDelete(request, estatSolicitudServeiID, new Exception(__msg));
      } else {
        delete(request, estatSolicitudServei);
        createMessageSuccess(request, "success.deleted", estatSolicitudServeiID);
        return getRedirectWhenDelete(request, estatSolicitudServeiID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", estatSolicitudServeiID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, estatSolicitudServeiID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EstatSolicitudServeiFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEstatSolicitudServei(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __estatSolicitudServeiID, Throwable e) {
    java.lang.Long estatSolicitudServeiID = (java.lang.Long)__estatSolicitudServeiID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (estatSolicitudServeiID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(estatSolicitudServeiID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "estatSolicitudServei.estatSolicitudServei";
  }

  public String getEntityNameCodePlural() {
    return "estatSolicitudServei.estatSolicitudServei.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("estatSolicitudServei.estatSolicitudServeiID");
  }

  @InitBinder("estatSolicitudServeiFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("estatSolicitudServeiForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public EstatSolicitudServeiWebValidator getWebValidator() {
    return estatSolicitudServeiWebValidator;
  }


  public void setWebValidator(EstatSolicitudServeiWebValidator __val) {
    if (__val != null) {
      this.estatSolicitudServeiWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de EstatSolicitudServei
   */
  @RequestMapping(value = "/{estatSolicitudServeiID}/cancel")
  public String cancelEstatSolicitudServei(@PathVariable("estatSolicitudServeiID") java.lang.Long estatSolicitudServeiID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, estatSolicitudServeiID);
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


  public void preValidate(HttpServletRequest request,EstatSolicitudServeiForm estatSolicitudServeiForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EstatSolicitudServeiForm estatSolicitudServeiForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EstatSolicitudServeiFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EstatSolicitudServeiFilterForm filterForm,  List<EstatSolicitudServei> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EstatSolicitudServeiForm estatSolicitudServeiForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EstatSolicitudServeiForm estatSolicitudServeiForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long estatSolicitudServeiID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long estatSolicitudServeiID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "estatSolicitudServeiFormWebDB";
  }

  public String getTileList() {
    return "estatSolicitudServeiListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "EstatSolicitudServeiWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EstatSolicitudServeiJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long estatSolicitudServeiID) throws I18NException {
    return (EstatSolicitudServeiJPA) estatSolicitudServeiEjb.findByPrimaryKey(estatSolicitudServeiID);
  }


  public EstatSolicitudServeiJPA create(HttpServletRequest request, EstatSolicitudServeiJPA estatSolicitudServei)
    throws Exception,I18NException, I18NValidationException {
    return (EstatSolicitudServeiJPA) estatSolicitudServeiEjb.create(estatSolicitudServei);
  }


  public EstatSolicitudServeiJPA update(HttpServletRequest request, EstatSolicitudServeiJPA estatSolicitudServei)
    throws Exception,I18NException, I18NValidationException {
    return (EstatSolicitudServeiJPA) estatSolicitudServeiEjb.update(estatSolicitudServei);
  }


  public void delete(HttpServletRequest request, EstatSolicitudServei estatSolicitudServei) throws Exception,I18NException {
    estatSolicitudServeiEjb.delete(estatSolicitudServei);
  }

} // Final de Classe

