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
import org.fundaciobit.pinbaladmin.back.form.webdb.EmailForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.EmailWebValidator;

import org.fundaciobit.pinbaladmin.persistence.EmailJPA;
import org.fundaciobit.pinbaladmin.model.entity.Email;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Email
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/email")
@SessionAttributes(types = { EmailForm.class, EmailFilterForm.class })
public class EmailController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<Email, java.lang.Long> implements EmailFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EmailService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EmailService emailEjb;

  @Autowired
  private EmailWebValidator emailWebValidator;

  @Autowired
  protected EmailRefList emailRefList;

  /**
   * Llistat de totes Email
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EmailFilterForm ff;
    ff = (EmailFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Email de forma paginada
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
    llistat(mav, request, getEmailFilterForm(pagina, mav, request));
    return mav;
  }

  public EmailFilterForm getEmailFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EmailFilterForm emailFilterForm;
    emailFilterForm = (EmailFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(emailFilterForm == null) {
      emailFilterForm = new EmailFilterForm();
      emailFilterForm.setContexte(getContextWeb());
      emailFilterForm.setEntityNameCode(getEntityNameCode());
      emailFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      emailFilterForm.setNou(true);
    } else {
      emailFilterForm.setNou(false);
    }
    emailFilterForm.setPage(pagina == null ? 1 : pagina);
    return emailFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Email de forma paginada
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
      @ModelAttribute EmailFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEmailFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Email de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Email> llistat(ModelAndView mav, HttpServletRequest request,
     EmailFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Email> email = processarLlistat(emailEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("emailItems", email);

    mav.addObject("emailFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, email, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, email);

    return email;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EmailFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Email> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EmailFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Email> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_EMAIL_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Email
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEmailGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EmailForm emailForm = getEmailForm(null, false, request, mav);
    mav.addObject("emailForm" ,emailForm);
    fillReferencesForForm(emailForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EmailForm getEmailForm(EmailJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EmailForm emailForm;
    if(_jpa == null) {
      emailForm = new EmailForm(new EmailJPA(), true);
    } else {
      emailForm = new EmailForm(_jpa, false);
      emailForm.setView(__isView);
    }
    emailForm.setContexte(getContextWeb());
    emailForm.setEntityNameCode(getEntityNameCode());
    emailForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return emailForm;
  }

  public void fillReferencesForForm(EmailForm emailForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Email
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEmailPost(@ModelAttribute EmailForm emailForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EmailJPA email = emailForm.getEmail();

    try {
      preValidate(request, emailForm, result);
      getWebValidator().validate(emailForm, result);
      postValidate(request,emailForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        email = create(request, email);
        createMessageSuccess(request, "success.creation", email.getEmailID());
        emailForm.setEmail(email);
        return getRedirectWhenCreated(request, emailForm);
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

  @RequestMapping(value = "/view/{emailID}", method = RequestMethod.GET)
  public ModelAndView veureEmailGet(@PathVariable("emailID") java.lang.Long emailID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEmailGet(emailID,
        request, response, true);
  }


  protected ModelAndView editAndViewEmailGet(@PathVariable("emailID") java.lang.Long emailID,
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
    EmailJPA email = findByPrimaryKey(request, emailID);

    if (email == null) {
      createMessageWarning(request, "error.notfound", emailID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, emailID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EmailForm emailForm = getEmailForm(email, __isView, request, mav);
      emailForm.setView(__isView);
      if(__isView) {
        emailForm.setAllFieldsReadOnly(ALL_EMAIL_FIELDS);
        emailForm.setSaveButtonVisible(false);
        emailForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(emailForm, request, mav);
      mav.addObject("emailForm", emailForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Email existent
   */
  @RequestMapping(value = "/{emailID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEmailGet(@PathVariable("emailID") java.lang.Long emailID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEmailGet(emailID,
        request, response, false);
  }



  /**
   * Editar un Email existent
   */
  @RequestMapping(value = "/{emailID}/edit", method = RequestMethod.POST)
  public String editarEmailPost(@ModelAttribute EmailForm emailForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EmailJPA email = emailForm.getEmail();

    try {
      preValidate(request, emailForm, result);
      getWebValidator().validate(emailForm, result);
      postValidate(request, emailForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        email = update(request, email);
        createMessageSuccess(request, "success.modification", email.getEmailID());
        status.setComplete();
        return getRedirectWhenModified(request, emailForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          email.getEmailID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, emailForm, __e);
    }

  }


  /**
   * Eliminar un Email existent
   */
  @RequestMapping(value = "/{emailID}/delete")
  public String eliminarEmail(@PathVariable("emailID") java.lang.Long emailID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Email email = this.findByPrimaryKey(request, emailID);
      if (email == null) {
        String __msg = createMessageError(request, "error.notfound", emailID);
        return getRedirectWhenDelete(request, emailID, new Exception(__msg));
      } else {
        delete(request, email);
        createMessageSuccess(request, "success.deleted", emailID);
        return getRedirectWhenDelete(request, emailID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", emailID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, emailID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EmailFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEmail(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __emailID, Throwable e) {
    java.lang.Long emailID = (java.lang.Long)__emailID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (emailID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(emailID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "email.email";
  }

  public String getEntityNameCodePlural() {
    return "email.email.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("email.emailID");
  }

  @InitBinder("emailFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("emailForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "email.emailID");
  }

  public EmailWebValidator getWebValidator() {
    return emailWebValidator;
  }


  public void setWebValidator(EmailWebValidator __val) {
    if (__val != null) {
      this.emailWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Email
   */
  @RequestMapping(value = "/{emailID}/cancel")
  public String cancelEmail(@PathVariable("emailID") java.lang.Long emailID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, emailID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Email
   */
  @RequestMapping(value = "/cancel")
  public String cancelEmail(HttpServletRequest request,HttpServletResponse response) {
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,EmailForm emailForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EmailForm emailForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EmailFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EmailFilterForm filterForm,  List<Email> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EmailForm emailForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EmailForm emailForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long emailID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long emailID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "emailFormWebDB";
  }

  public String getTileList() {
    return "emailListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Email_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EmailJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long emailID) throws I18NException {
    return (EmailJPA) emailEjb.findByPrimaryKey(emailID);
  }


  public EmailJPA create(HttpServletRequest request, EmailJPA email)
    throws I18NException, I18NValidationException {
    return (EmailJPA) emailEjb.create(email);
  }


  public EmailJPA update(HttpServletRequest request, EmailJPA email)
    throws I18NException, I18NValidationException {
    return (EmailJPA) emailEjb.update(email);
  }


  public void delete(HttpServletRequest request, Email email) throws I18NException {
    emailEjb.delete(email);
  }

} // Final de Classe

