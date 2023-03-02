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
import org.fundaciobit.pinbaladmin.back.form.webdb.FormulariForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.FormulariWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.persistence.FormulariJPA;
import org.fundaciobit.pinbaladmin.model.entity.Formulari;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Formulari
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/formulari")
@SessionAttributes(types = { FormulariForm.class, FormulariFilterForm.class })
public class FormulariController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<Formulari, java.lang.Long, FormulariForm> implements FormulariFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FormulariService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.FormulariService formulariEjb;

  @Autowired
  private FormulariWebValidator formulariWebValidator;

  @Autowired
  protected FormulariRefList formulariRefList;

  /**
   * Llistat de totes Formulari
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    FormulariFilterForm ff;
    ff = (FormulariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Formulari de forma paginada
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
    llistat(mav, request, getFormulariFilterForm(pagina, mav, request));
    return mav;
  }

  public FormulariFilterForm getFormulariFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    FormulariFilterForm formulariFilterForm;
    formulariFilterForm = (FormulariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(formulariFilterForm == null) {
      formulariFilterForm = new FormulariFilterForm();
      formulariFilterForm.setContexte(getContextWeb());
      formulariFilterForm.setEntityNameCode(getEntityNameCode());
      formulariFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      formulariFilterForm.setNou(true);
    } else {
      formulariFilterForm.setNou(false);
    }
    formulariFilterForm.setPage(pagina == null ? 1 : pagina);
    return formulariFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Formulari de forma paginada
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
      @ModelAttribute FormulariFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getFormulariFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Formulari de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Formulari> llistat(ModelAndView mav, HttpServletRequest request,
     FormulariFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Formulari> formulari = processarLlistat(formulariEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("formulariItems", formulari);

    mav.addObject("formulariFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, formulari, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, formulari);

    return formulari;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(FormulariFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Formulari> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    FormulariFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Formulari> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_FORMULARI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Formulari
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearFormulariGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    FormulariForm formulariForm = getFormulariForm(null, false, request, mav);
    mav.addObject("formulariForm" ,formulariForm);
    fillReferencesForForm(formulariForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public FormulariForm getFormulariForm(FormulariJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    FormulariForm formulariForm;
    if(_jpa == null) {
      formulariForm = new FormulariForm(new FormulariJPA(), true);
    } else {
      formulariForm = new FormulariForm(_jpa, false);
      formulariForm.setView(__isView);
    }
    formulariForm.setContexte(getContextWeb());
    formulariForm.setEntityNameCode(getEntityNameCode());
    formulariForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return formulariForm;
  }

  public void fillReferencesForForm(FormulariForm formulariForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Formulari
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearFormulariPost(@ModelAttribute FormulariForm formulariForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    FormulariJPA formulari = formulariForm.getFormulari();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, formulari, formulariForm); // FILE
      preValidate(request, formulariForm, result);
      getWebValidator().validate(formulariForm, result);
      postValidate(request,formulariForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        formulari = create(request, formulari);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", formulari.getFormulariid());
        formulariForm.setFormulari(formulari);
        return getRedirectWhenCreated(request, formulariForm);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{formulariid}", method = RequestMethod.GET)
  public ModelAndView veureFormulariGet(@PathVariable("formulariid") java.lang.Long formulariid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFormulariGet(formulariid,
        request, response, true);
  }


  protected ModelAndView editAndViewFormulariGet(@PathVariable("formulariid") java.lang.Long formulariid,
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
    FormulariJPA formulari = findByPrimaryKey(request, formulariid);

    if (formulari == null) {
      createMessageWarning(request, "error.notfound", formulariid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, formulariid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      FormulariForm formulariForm = getFormulariForm(formulari, __isView, request, mav);
      formulariForm.setView(__isView);
      if(__isView) {
        formulariForm.setAllFieldsReadOnly(ALL_FORMULARI_FIELDS);
        formulariForm.setSaveButtonVisible(false);
        formulariForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(formulariForm, request, mav);
      mav.addObject("formulariForm", formulariForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Formulari existent
   */
  @RequestMapping(value = "/{formulariid}/edit", method = RequestMethod.GET)
  public ModelAndView editarFormulariGet(@PathVariable("formulariid") java.lang.Long formulariid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFormulariGet(formulariid,
        request, response, false);
  }



  /**
   * Editar un Formulari existent
   */
  @RequestMapping(value = "/{formulariid}/edit", method = RequestMethod.POST)
  public String editarFormulariPost(@ModelAttribute FormulariForm formulariForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    FormulariJPA formulari = formulariForm.getFormulari();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, formulari, formulariForm); // FILE
      preValidate(request, formulariForm, result);
      getWebValidator().validate(formulariForm, result);
      postValidate(request, formulariForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        formulari = update(request, formulari);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", formulari.getFormulariid());
        status.setComplete();
        return getRedirectWhenModified(request, formulariForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          formulari.getFormulariid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, formulariForm, __e);
    }

  }


  /**
   * Eliminar un Formulari existent
   */
  @RequestMapping(value = "/{formulariid}/delete")
  public String eliminarFormulari(@PathVariable("formulariid") java.lang.Long formulariid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Formulari formulari = formulariEjb.findByPrimaryKey(formulariid);
      if (formulari == null) {
        String __msg =createMessageError(request, "error.notfound", formulariid);
        return getRedirectWhenDelete(request, formulariid, new Exception(__msg));
      } else {
        delete(request, formulari);
        createMessageSuccess(request, "success.deleted", formulariid);
        return getRedirectWhenDelete(request, formulariid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", formulariid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, formulariid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute FormulariFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarFormulari(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __formulariid, Throwable e) {
    java.lang.Long formulariid = (java.lang.Long)__formulariid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (formulariid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(formulariid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "formulari.formulari";
  }

  public String getEntityNameCodePlural() {
    return "formulari.formulari.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("formulari.formulariid");
  }

  @InitBinder("formulariFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("formulariForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "formulari.formulariid");
  }

  public FormulariWebValidator getWebValidator() {
    return formulariWebValidator;
  }


  public void setWebValidator(FormulariWebValidator __val) {
    if (__val != null) {
      this.formulariWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Formulari
   */
  @RequestMapping(value = "/{formulariid}/cancel")
  public String cancelFormulari(@PathVariable("formulariid") java.lang.Long formulariid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, formulariid);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Formulari formulari,
      FormulariForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerID(), form.isFitxerIDDelete(),
        form.isNou()? null : formulari.getFitxer());
    ((FormulariJPA)formulari).setFitxer(f);
    if (f != null) { 
      formulari.setFitxerID(f.getFitxerID());
    } else {
      formulari.setFitxerID(null);
    }

  }

  // FILE
  @Override
  public void deleteFiles(Formulari formulari) {
    deleteFile(formulari.getFitxerID());
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

  public void preValidate(HttpServletRequest request,FormulariForm formulariForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,FormulariForm formulariForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, FormulariFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, FormulariFilterForm filterForm,  List<Formulari> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, FormulariForm formulariForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, FormulariForm formulariForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long formulariid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long formulariid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "formulariFormWebDB";
  }

  public String getTileList() {
    return "formulariListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "FormulariWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public FormulariJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long formulariid) throws I18NException {
    return (FormulariJPA) formulariEjb.findByPrimaryKey(formulariid);
  }


  public FormulariJPA create(HttpServletRequest request, FormulariJPA formulari)
    throws I18NException, I18NValidationException {
    return (FormulariJPA) formulariEjb.create(formulari);
  }


  public FormulariJPA update(HttpServletRequest request, FormulariJPA formulari)
    throws I18NException, I18NValidationException {
    return (FormulariJPA) formulariEjb.update(formulari);
  }


  public void delete(HttpServletRequest request, Formulari formulari) throws I18NException {
    formulariEjb.delete(formulari);
  }

} // Final de Classe

