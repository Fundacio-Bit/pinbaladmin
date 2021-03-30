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
import org.fundaciobit.pinbaladmin.back.form.webdb.EstatServeiForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.EstatServeiWebValidator;

import org.fundaciobit.pinbaladmin.jpa.EstatServeiJPA;
import org.fundaciobit.pinbaladmin.model.entity.EstatServei;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un EstatServei
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/estatServei")
@SessionAttributes(types = { EstatServeiForm.class, EstatServeiFilterForm.class })
public class EstatServeiController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<EstatServei, java.lang.Long> implements EstatServeiFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatServeiLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatServeiLocal estatServeiEjb;

  @Autowired
  private EstatServeiWebValidator estatServeiWebValidator;

  @Autowired
  protected EstatServeiRefList estatServeiRefList;

  /**
   * Llistat de totes EstatServei
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EstatServeiFilterForm ff;
    ff = (EstatServeiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar EstatServei de forma paginada
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
    llistat(mav, request, getEstatServeiFilterForm(pagina, mav, request));
    return mav;
  }

  public EstatServeiFilterForm getEstatServeiFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EstatServeiFilterForm estatServeiFilterForm;
    estatServeiFilterForm = (EstatServeiFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(estatServeiFilterForm == null) {
      estatServeiFilterForm = new EstatServeiFilterForm();
      estatServeiFilterForm.setContexte(getContextWeb());
      estatServeiFilterForm.setEntityNameCode(getEntityNameCode());
      estatServeiFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      estatServeiFilterForm.setNou(true);
    } else {
      estatServeiFilterForm.setNou(false);
    }
    estatServeiFilterForm.setPage(pagina == null ? 1 : pagina);
    return estatServeiFilterForm;
  }

  /**
   * Segona i següent peticions per llistar EstatServei de forma paginada
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
      @ModelAttribute EstatServeiFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEstatServeiFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de EstatServei de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<EstatServei> llistat(ModelAndView mav, HttpServletRequest request,
     EstatServeiFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<EstatServei> estatServei = processarLlistat(estatServeiEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("estatServeiItems", estatServei);

    mav.addObject("estatServeiFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, estatServei, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, estatServei);

    return estatServei;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EstatServeiFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<EstatServei> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EstatServeiFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<EstatServei> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ESTATSERVEI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou EstatServei
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEstatServeiGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EstatServeiForm estatServeiForm = getEstatServeiForm(null, false, request, mav);
    mav.addObject("estatServeiForm" ,estatServeiForm);
    fillReferencesForForm(estatServeiForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EstatServeiForm getEstatServeiForm(EstatServeiJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EstatServeiForm estatServeiForm;
    if(_jpa == null) {
      estatServeiForm = new EstatServeiForm(new EstatServeiJPA(), true);
    } else {
      estatServeiForm = new EstatServeiForm(_jpa, false);
      estatServeiForm.setView(__isView);
    }
    estatServeiForm.setContexte(getContextWeb());
    estatServeiForm.setEntityNameCode(getEntityNameCode());
    estatServeiForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return estatServeiForm;
  }

  public void fillReferencesForForm(EstatServeiForm estatServeiForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou EstatServei
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEstatServeiPost(@ModelAttribute EstatServeiForm estatServeiForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EstatServeiJPA estatServei = estatServeiForm.getEstatServei();

    try {
      preValidate(request, estatServeiForm, result);
      getWebValidator().validate(estatServeiForm, result);
      postValidate(request,estatServeiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        estatServei = create(request, estatServei);
        createMessageSuccess(request, "success.creation", estatServei.getEstatServeiID());
        estatServeiForm.setEstatServei(estatServei);
        return getRedirectWhenCreated(request, estatServeiForm);
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

  @RequestMapping(value = "/view/{estatServeiID}", method = RequestMethod.GET)
  public ModelAndView veureEstatServeiGet(@PathVariable("estatServeiID") java.lang.Long estatServeiID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstatServeiGet(estatServeiID,
        request, response, true);
  }


  protected ModelAndView editAndViewEstatServeiGet(@PathVariable("estatServeiID") java.lang.Long estatServeiID,
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
    EstatServeiJPA estatServei = findByPrimaryKey(request, estatServeiID);

    if (estatServei == null) {
      createMessageWarning(request, "error.notfound", estatServeiID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, estatServeiID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EstatServeiForm estatServeiForm = getEstatServeiForm(estatServei, __isView, request, mav);
      estatServeiForm.setView(__isView);
      if(__isView) {
        estatServeiForm.setAllFieldsReadOnly(ALL_ESTATSERVEI_FIELDS);
        estatServeiForm.setSaveButtonVisible(false);
        estatServeiForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(estatServeiForm, request, mav);
      mav.addObject("estatServeiForm", estatServeiForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un EstatServei existent
   */
  @RequestMapping(value = "/{estatServeiID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEstatServeiGet(@PathVariable("estatServeiID") java.lang.Long estatServeiID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstatServeiGet(estatServeiID,
        request, response, false);
  }



  /**
   * Editar un EstatServei existent
   */
  @RequestMapping(value = "/{estatServeiID}/edit", method = RequestMethod.POST)
  public String editarEstatServeiPost(@ModelAttribute @Valid EstatServeiForm estatServeiForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EstatServeiJPA estatServei = estatServeiForm.getEstatServei();

    try {
      preValidate(request, estatServeiForm, result);
      getWebValidator().validate(estatServei, result);
      postValidate(request, estatServeiForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        estatServei = update(request, estatServei);
        createMessageSuccess(request, "success.modification", estatServei.getEstatServeiID());
        status.setComplete();
        return getRedirectWhenModified(request, estatServeiForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          estatServei.getEstatServeiID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, estatServeiForm, __e);
    }

  }


  /**
   * Eliminar un EstatServei existent
   */
  @RequestMapping(value = "/{estatServeiID}/delete")
  public String eliminarEstatServei(@PathVariable("estatServeiID") java.lang.Long estatServeiID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      EstatServei estatServei = estatServeiEjb.findByPrimaryKey(estatServeiID);
      if (estatServei == null) {
        String __msg =createMessageError(request, "error.notfound", estatServeiID);
        return getRedirectWhenDelete(request, estatServeiID, new Exception(__msg));
      } else {
        delete(request, estatServei);
        createMessageSuccess(request, "success.deleted", estatServeiID);
        return getRedirectWhenDelete(request, estatServeiID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", estatServeiID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, estatServeiID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EstatServeiFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEstatServei(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __estatServeiID, Throwable e) {
    java.lang.Long estatServeiID = (java.lang.Long)__estatServeiID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (estatServeiID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(estatServeiID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "estatServei.estatServei";
  }

  public String getEntityNameCodePlural() {
    return "estatServei.estatServei.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("estatServei.estatServeiID");
  }

  @InitBinder("estatServeiFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("estatServeiForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public EstatServeiWebValidator getWebValidator() {
    return estatServeiWebValidator;
  }


  public void setWebValidator(EstatServeiWebValidator __val) {
    if (__val != null) {
      this.estatServeiWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de EstatServei
   */
  @RequestMapping(value = "/{estatServeiID}/cancel")
  public String cancelEstatServei(@PathVariable("estatServeiID") java.lang.Long estatServeiID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, estatServeiID);
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


  public void preValidate(HttpServletRequest request,EstatServeiForm estatServeiForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EstatServeiForm estatServeiForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EstatServeiFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EstatServeiFilterForm filterForm,  List<EstatServei> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EstatServeiForm estatServeiForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EstatServeiForm estatServeiForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long estatServeiID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long estatServeiID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "estatServeiFormWebDB";
  }

  public String getTileList() {
    return "estatServeiListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "EstatServeiWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EstatServeiJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long estatServeiID) throws I18NException {
    return (EstatServeiJPA) estatServeiEjb.findByPrimaryKey(estatServeiID);
  }


  public EstatServeiJPA create(HttpServletRequest request, EstatServeiJPA estatServei)
    throws Exception,I18NException, I18NValidationException {
    return (EstatServeiJPA) estatServeiEjb.create(estatServei);
  }


  public EstatServeiJPA update(HttpServletRequest request, EstatServeiJPA estatServei)
    throws Exception,I18NException, I18NValidationException {
    return (EstatServeiJPA) estatServeiEjb.update(estatServei);
  }


  public void delete(HttpServletRequest request, EstatServei estatServei) throws Exception,I18NException {
    estatServeiEjb.delete(estatServei);
  }

} // Final de Classe

