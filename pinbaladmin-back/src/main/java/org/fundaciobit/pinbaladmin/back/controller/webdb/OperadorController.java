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
import org.fundaciobit.pinbaladmin.back.form.webdb.OperadorForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.OperadorWebValidator;

import org.fundaciobit.pinbaladmin.persistence.OperadorJPA;
import org.fundaciobit.pinbaladmin.model.entity.Operador;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Operador
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/operador")
@SessionAttributes(types = { OperadorForm.class, OperadorFilterForm.class })
public class OperadorController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<Operador, java.lang.Long> implements OperadorFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OperadorService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.OperadorService operadorEjb;

  @Autowired
  private OperadorWebValidator operadorWebValidator;

  @Autowired
  protected OperadorRefList operadorRefList;

  /**
   * Llistat de totes Operador
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    OperadorFilterForm ff;
    ff = (OperadorFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Operador de forma paginada
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
    llistat(mav, request, getOperadorFilterForm(pagina, mav, request));
    return mav;
  }

  public OperadorFilterForm getOperadorFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    OperadorFilterForm operadorFilterForm;
    operadorFilterForm = (OperadorFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(operadorFilterForm == null) {
      operadorFilterForm = new OperadorFilterForm();
      operadorFilterForm.setContexte(getContextWeb());
      operadorFilterForm.setEntityNameCode(getEntityNameCode());
      operadorFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      operadorFilterForm.setNou(true);
    } else {
      operadorFilterForm.setNou(false);
    }
    operadorFilterForm.setPage(pagina == null ? 1 : pagina);
    return operadorFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Operador de forma paginada
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
      @ModelAttribute OperadorFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getOperadorFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Operador de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Operador> llistat(ModelAndView mav, HttpServletRequest request,
     OperadorFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Operador> operador = processarLlistat(operadorEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("operadorItems", operador);

    mav.addObject("operadorFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, operador, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, operador);

    return operador;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(OperadorFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Operador> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    OperadorFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Operador> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_OPERADOR_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Operador
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearOperadorGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    OperadorForm operadorForm = getOperadorForm(null, false, request, mav);
    mav.addObject("operadorForm" ,operadorForm);
    fillReferencesForForm(operadorForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public OperadorForm getOperadorForm(OperadorJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    OperadorForm operadorForm;
    if(_jpa == null) {
      operadorForm = new OperadorForm(new OperadorJPA(), true);
    } else {
      operadorForm = new OperadorForm(_jpa, false);
      operadorForm.setView(__isView);
    }
    operadorForm.setContexte(getContextWeb());
    operadorForm.setEntityNameCode(getEntityNameCode());
    operadorForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return operadorForm;
  }

  public void fillReferencesForForm(OperadorForm operadorForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Operador
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearOperadorPost(@ModelAttribute OperadorForm operadorForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    OperadorJPA operador = operadorForm.getOperador();

    try {
      preValidate(request, operadorForm, result);
      getWebValidator().validate(operadorForm, result);
      postValidate(request,operadorForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        operador = create(request, operador);
        createMessageSuccess(request, "success.creation", operador.getOperadorID());
        operadorForm.setOperador(operador);
        return getRedirectWhenCreated(request, operadorForm);
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

  @RequestMapping(value = "/view/{operadorID}", method = RequestMethod.GET)
  public ModelAndView veureOperadorGet(@PathVariable("operadorID") java.lang.Long operadorID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewOperadorGet(operadorID,
        request, response, true);
  }


  protected ModelAndView editAndViewOperadorGet(@PathVariable("operadorID") java.lang.Long operadorID,
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
    OperadorJPA operador = findByPrimaryKey(request, operadorID);

    if (operador == null) {
      createMessageWarning(request, "error.notfound", operadorID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, operadorID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      OperadorForm operadorForm = getOperadorForm(operador, __isView, request, mav);
      operadorForm.setView(__isView);
      if(__isView) {
        operadorForm.setAllFieldsReadOnly(ALL_OPERADOR_FIELDS);
        operadorForm.setSaveButtonVisible(false);
        operadorForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(operadorForm, request, mav);
      mav.addObject("operadorForm", operadorForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Operador existent
   */
  @RequestMapping(value = "/{operadorID}/edit", method = RequestMethod.GET)
  public ModelAndView editarOperadorGet(@PathVariable("operadorID") java.lang.Long operadorID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewOperadorGet(operadorID,
        request, response, false);
  }



  /**
   * Editar un Operador existent
   */
  @RequestMapping(value = "/{operadorID}/edit", method = RequestMethod.POST)
  public String editarOperadorPost(@ModelAttribute OperadorForm operadorForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    OperadorJPA operador = operadorForm.getOperador();

    try {
      preValidate(request, operadorForm, result);
      getWebValidator().validate(operadorForm, result);
      postValidate(request, operadorForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        operador = update(request, operador);
        createMessageSuccess(request, "success.modification", operador.getOperadorID());
        status.setComplete();
        return getRedirectWhenModified(request, operadorForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          operador.getOperadorID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, operadorForm, __e);
    }

  }


  /**
   * Eliminar un Operador existent
   */
  @RequestMapping(value = "/{operadorID}/delete")
  public String eliminarOperador(@PathVariable("operadorID") java.lang.Long operadorID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Operador operador = this.findByPrimaryKey(request, operadorID);
      if (operador == null) {
        String __msg = createMessageError(request, "error.notfound", operadorID);
        return getRedirectWhenDelete(request, operadorID, new Exception(__msg));
      } else {
        delete(request, operador);
        createMessageSuccess(request, "success.deleted", operadorID);
        return getRedirectWhenDelete(request, operadorID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", operadorID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, operadorID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute OperadorFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarOperador(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __operadorID, Throwable e) {
    java.lang.Long operadorID = (java.lang.Long)__operadorID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (operadorID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(operadorID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "operador.operador";
  }

  public String getEntityNameCodePlural() {
    return "operador.operador.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("operador.operadorID");
  }

  @InitBinder("operadorFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("operadorForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "operador.operadorID");
  }

  public OperadorWebValidator getWebValidator() {
    return operadorWebValidator;
  }


  public void setWebValidator(OperadorWebValidator __val) {
    if (__val != null) {
      this.operadorWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Operador
   */
  @RequestMapping(value = "/{operadorID}/cancel")
  public String cancelOperador(@PathVariable("operadorID") java.lang.Long operadorID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, operadorID);
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

  public void preValidate(HttpServletRequest request,OperadorForm operadorForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,OperadorForm operadorForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, OperadorFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, OperadorFilterForm filterForm,  List<Operador> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, OperadorForm operadorForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, OperadorForm operadorForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long operadorID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long operadorID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "operadorFormWebDB";
  }

  public String getTileList() {
    return "operadorListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "OperadorWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public OperadorJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long operadorID) throws I18NException {
    return (OperadorJPA) operadorEjb.findByPrimaryKey(operadorID);
  }


  public OperadorJPA create(HttpServletRequest request, OperadorJPA operador)
    throws I18NException, I18NValidationException {
    return (OperadorJPA) operadorEjb.create(operador);
  }


  public OperadorJPA update(HttpServletRequest request, OperadorJPA operador)
    throws I18NException, I18NValidationException {
    return (OperadorJPA) operadorEjb.update(operador);
  }


  public void delete(HttpServletRequest request, Operador operador) throws I18NException {
    operadorEjb.delete(operador);
  }

} // Final de Classe

