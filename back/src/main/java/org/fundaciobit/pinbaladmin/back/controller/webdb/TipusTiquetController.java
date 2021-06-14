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
import org.fundaciobit.pinbaladmin.back.form.webdb.TipusTiquetForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TipusTiquetWebValidator;

import org.fundaciobit.pinbaladmin.jpa.TipusTiquetJPA;
import org.fundaciobit.pinbaladmin.model.entity.TipusTiquet;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un TipusTiquet
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tipusTiquet")
@SessionAttributes(types = { TipusTiquetForm.class, TipusTiquetFilterForm.class })
public class TipusTiquetController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<TipusTiquet, java.lang.Long> implements TipusTiquetFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TipusTiquetLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TipusTiquetLocal tipusTiquetEjb;

  @Autowired
  private TipusTiquetWebValidator tipusTiquetWebValidator;

  @Autowired
  protected TipusTiquetRefList tipusTiquetRefList;

  /**
   * Llistat de totes TipusTiquet
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TipusTiquetFilterForm ff;
    ff = (TipusTiquetFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TipusTiquet de forma paginada
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
    llistat(mav, request, getTipusTiquetFilterForm(pagina, mav, request));
    return mav;
  }

  public TipusTiquetFilterForm getTipusTiquetFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TipusTiquetFilterForm tipusTiquetFilterForm;
    tipusTiquetFilterForm = (TipusTiquetFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tipusTiquetFilterForm == null) {
      tipusTiquetFilterForm = new TipusTiquetFilterForm();
      tipusTiquetFilterForm.setContexte(getContextWeb());
      tipusTiquetFilterForm.setEntityNameCode(getEntityNameCode());
      tipusTiquetFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tipusTiquetFilterForm.setNou(true);
    } else {
      tipusTiquetFilterForm.setNou(false);
    }
    tipusTiquetFilterForm.setPage(pagina == null ? 1 : pagina);
    return tipusTiquetFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TipusTiquet de forma paginada
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
      @ModelAttribute TipusTiquetFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTipusTiquetFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TipusTiquet de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TipusTiquet> llistat(ModelAndView mav, HttpServletRequest request,
     TipusTiquetFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TipusTiquet> tipusTiquet = processarLlistat(tipusTiquetEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tipusTiquetItems", tipusTiquet);

    mav.addObject("tipusTiquetFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tipusTiquet, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tipusTiquet);

    return tipusTiquet;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TipusTiquetFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TipusTiquet> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TipusTiquetFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusTiquet> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSTIQUET_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TipusTiquet
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTipusTiquetGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TipusTiquetForm tipusTiquetForm = getTipusTiquetForm(null, false, request, mav);
    mav.addObject("tipusTiquetForm" ,tipusTiquetForm);
    fillReferencesForForm(tipusTiquetForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TipusTiquetForm getTipusTiquetForm(TipusTiquetJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TipusTiquetForm tipusTiquetForm;
    if(_jpa == null) {
      tipusTiquetForm = new TipusTiquetForm(new TipusTiquetJPA(), true);
    } else {
      tipusTiquetForm = new TipusTiquetForm(_jpa, false);
      tipusTiquetForm.setView(__isView);
    }
    tipusTiquetForm.setContexte(getContextWeb());
    tipusTiquetForm.setEntityNameCode(getEntityNameCode());
    tipusTiquetForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tipusTiquetForm;
  }

  public void fillReferencesForForm(TipusTiquetForm tipusTiquetForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou TipusTiquet
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTipusTiquetPost(@ModelAttribute TipusTiquetForm tipusTiquetForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TipusTiquetJPA tipusTiquet = tipusTiquetForm.getTipusTiquet();

    try {
      preValidate(request, tipusTiquetForm, result);
      getWebValidator().validate(tipusTiquetForm, result);
      postValidate(request,tipusTiquetForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tipusTiquet = create(request, tipusTiquet);
        createMessageSuccess(request, "success.creation", tipusTiquet.getTipusTiquetID());
        tipusTiquetForm.setTipusTiquet(tipusTiquet);
        return getRedirectWhenCreated(request, tipusTiquetForm);
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

  @RequestMapping(value = "/view/{tipusTiquetID}", method = RequestMethod.GET)
  public ModelAndView veureTipusTiquetGet(@PathVariable("tipusTiquetID") java.lang.Long tipusTiquetID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusTiquetGet(tipusTiquetID,
        request, response, true);
  }


  protected ModelAndView editAndViewTipusTiquetGet(@PathVariable("tipusTiquetID") java.lang.Long tipusTiquetID,
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
    TipusTiquetJPA tipusTiquet = findByPrimaryKey(request, tipusTiquetID);

    if (tipusTiquet == null) {
      createMessageWarning(request, "error.notfound", tipusTiquetID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, tipusTiquetID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TipusTiquetForm tipusTiquetForm = getTipusTiquetForm(tipusTiquet, __isView, request, mav);
      tipusTiquetForm.setView(__isView);
      if(__isView) {
        tipusTiquetForm.setAllFieldsReadOnly(ALL_TIPUSTIQUET_FIELDS);
        tipusTiquetForm.setSaveButtonVisible(false);
        tipusTiquetForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tipusTiquetForm, request, mav);
      mav.addObject("tipusTiquetForm", tipusTiquetForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TipusTiquet existent
   */
  @RequestMapping(value = "/{tipusTiquetID}/edit", method = RequestMethod.GET)
  public ModelAndView editarTipusTiquetGet(@PathVariable("tipusTiquetID") java.lang.Long tipusTiquetID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusTiquetGet(tipusTiquetID,
        request, response, false);
  }



  /**
   * Editar un TipusTiquet existent
   */
  @RequestMapping(value = "/{tipusTiquetID}/edit", method = RequestMethod.POST)
  public String editarTipusTiquetPost(@ModelAttribute @Valid TipusTiquetForm tipusTiquetForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TipusTiquetJPA tipusTiquet = tipusTiquetForm.getTipusTiquet();

    try {
      preValidate(request, tipusTiquetForm, result);
      getWebValidator().validate(tipusTiquet, result);
      postValidate(request, tipusTiquetForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tipusTiquet = update(request, tipusTiquet);
        createMessageSuccess(request, "success.modification", tipusTiquet.getTipusTiquetID());
        status.setComplete();
        return getRedirectWhenModified(request, tipusTiquetForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tipusTiquet.getTipusTiquetID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tipusTiquetForm, __e);
    }

  }


  /**
   * Eliminar un TipusTiquet existent
   */
  @RequestMapping(value = "/{tipusTiquetID}/delete")
  public String eliminarTipusTiquet(@PathVariable("tipusTiquetID") java.lang.Long tipusTiquetID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TipusTiquet tipusTiquet = findByPrimaryKey(request, tipusTiquetID);
      if (tipusTiquet == null) {
        String __msg =createMessageError(request, "error.notfound", tipusTiquetID);
        return getRedirectWhenDelete(request, tipusTiquetID, new Exception(__msg));
      } else {
        delete(request, tipusTiquet);
        createMessageSuccess(request, "success.deleted", tipusTiquetID);
        return getRedirectWhenDelete(request, tipusTiquetID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", tipusTiquetID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, tipusTiquetID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TipusTiquetFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTipusTiquet(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __tipusTiquetID, Throwable e) {
    java.lang.Long tipusTiquetID = (java.lang.Long)__tipusTiquetID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (tipusTiquetID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(tipusTiquetID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tipusTiquet.tipusTiquet";
  }

  public String getEntityNameCodePlural() {
    return "tipusTiquet.tipusTiquet.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tipusTiquet.tipusTiquetID");
  }

  @InitBinder("tipusTiquetFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tipusTiquetForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    initDisallowedFields(binder);
  }

  public TipusTiquetWebValidator getWebValidator() {
    return tipusTiquetWebValidator;
  }


  public void setWebValidator(TipusTiquetWebValidator __val) {
    if (__val != null) {
      this.tipusTiquetWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TipusTiquet
   */
  @RequestMapping(value = "/{tipusTiquetID}/cancel")
  public String cancelTipusTiquet(@PathVariable("tipusTiquetID") java.lang.Long tipusTiquetID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, tipusTiquetID);
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


  public void preValidate(HttpServletRequest request,TipusTiquetForm tipusTiquetForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TipusTiquetForm tipusTiquetForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TipusTiquetFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TipusTiquetFilterForm filterForm,  List<TipusTiquet> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TipusTiquetForm tipusTiquetForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TipusTiquetForm tipusTiquetForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long tipusTiquetID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long tipusTiquetID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tipusTiquetFormWebDB";
  }

  public String getTileList() {
    return "tipusTiquetListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "TipusTiquetWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TipusTiquetJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long tipusTiquetID) throws I18NException {
    return (TipusTiquetJPA) tipusTiquetEjb.findByPrimaryKey(tipusTiquetID);
  }


  public TipusTiquetJPA create(HttpServletRequest request, TipusTiquetJPA tipusTiquet)
    throws Exception,I18NException, I18NValidationException {
    return (TipusTiquetJPA) tipusTiquetEjb.create(tipusTiquet);
  }


  public TipusTiquetJPA update(HttpServletRequest request, TipusTiquetJPA tipusTiquet)
    throws Exception,I18NException, I18NValidationException {
    return (TipusTiquetJPA) tipusTiquetEjb.update(tipusTiquet);
  }


  public void delete(HttpServletRequest request, TipusTiquet tipusTiquet) throws Exception,I18NException {
    tipusTiquetEjb.delete(tipusTiquet);
  }

} // Final de Classe

