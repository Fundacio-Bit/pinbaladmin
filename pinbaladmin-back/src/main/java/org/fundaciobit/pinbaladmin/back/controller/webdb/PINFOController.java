package org.fundaciobit.pinbaladmin.back.controller.webdb;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
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
import org.fundaciobit.pinbaladmin.back.form.webdb.PINFOForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.PINFOWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.persistence.PINFOJPA;
import org.fundaciobit.pinbaladmin.model.entity.PINFO;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un PINFO
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/pINFO")
@SessionAttributes(types = { PINFOForm.class, PINFOFilterForm.class })
public class PINFOController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<PINFO, java.lang.Long, PINFOForm> implements PINFOFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.PINFOService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.PINFOService pINFOEjb;

  @Autowired
  private PINFOWebValidator pINFOWebValidator;

  @Autowired
  protected PINFORefList pINFORefList;

  // References 
  @Autowired
  protected IncidenciaTecnicaRefList incidenciaTecnicaRefList;

  /**
   * Llistat de totes PINFO
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PINFOFilterForm ff;
    ff = (PINFOFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PINFO de forma paginada
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
    llistat(mav, request, getPINFOFilterForm(pagina, mav, request));
    return mav;
  }

  public PINFOFilterForm getPINFOFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PINFOFilterForm pINFOFilterForm;
    pINFOFilterForm = (PINFOFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(pINFOFilterForm == null) {
      pINFOFilterForm = new PINFOFilterForm();
      pINFOFilterForm.setContexte(getContextWeb());
      pINFOFilterForm.setEntityNameCode(getEntityNameCode());
      pINFOFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      pINFOFilterForm.setNou(true);
    } else {
      pINFOFilterForm.setNou(false);
    }
    pINFOFilterForm.setPage(pagina == null ? 1 : pagina);
    return pINFOFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PINFO de forma paginada
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
      @ModelAttribute PINFOFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPINFOFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PINFO de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PINFO> llistat(ModelAndView mav, HttpServletRequest request,
     PINFOFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PINFO> pINFO = processarLlistat(pINFOEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("pINFOItems", pINFO);

    mav.addObject("pINFOFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, pINFO, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, pINFO);

    return pINFO;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PINFOFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PINFO> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field IncidenciaID
    {
      _listSKV = getReferenceListForIncidenciaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfIncidenciaTecnicaForIncidenciaID(_tmp);
      if (filterForm.getGroupByFields().contains(INCIDENCIAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, INCIDENCIAID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PINFOFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PINFO> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PINFO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(INCIDENCIAID, filterForm.getMapOfIncidenciaTecnicaForIncidenciaID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PINFO
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPINFOGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PINFOForm pINFOForm = getPINFOForm(null, false, request, mav);
    mav.addObject("pINFOForm" ,pINFOForm);
    fillReferencesForForm(pINFOForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PINFOForm getPINFOForm(PINFOJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PINFOForm pINFOForm;
    if(_jpa == null) {
      pINFOForm = new PINFOForm(new PINFOJPA(), true);
    } else {
      pINFOForm = new PINFOForm(_jpa, false);
      pINFOForm.setView(__isView);
    }
    pINFOForm.setContexte(getContextWeb());
    pINFOForm.setEntityNameCode(getEntityNameCode());
    pINFOForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return pINFOForm;
  }

  public void fillReferencesForForm(PINFOForm pINFOForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (pINFOForm.getListOfIncidenciaTecnicaForIncidenciaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForIncidenciaID(request, mav, pINFOForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pINFOForm.setListOfIncidenciaTecnicaForIncidenciaID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PINFO
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPINFOPost(@ModelAttribute PINFOForm pINFOForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PINFOJPA pINFO = pINFOForm.getPINFO();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, pINFO, pINFOForm); // FILE
      preValidate(request, pINFOForm, result);
      getWebValidator().validate(pINFOForm, result);
      postValidate(request,pINFOForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        pINFO = create(request, pINFO);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", pINFO.getPinfoID());
        pINFOForm.setPINFO(pINFO);
        return getRedirectWhenCreated(request, pINFOForm);
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

  @RequestMapping(value = "/view/{pinfoID}", method = RequestMethod.GET)
  public ModelAndView veurePINFOGet(@PathVariable("pinfoID") java.lang.Long pinfoID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPINFOGet(pinfoID,
        request, response, true);
  }


  protected ModelAndView editAndViewPINFOGet(@PathVariable("pinfoID") java.lang.Long pinfoID,
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
    PINFOJPA pINFO = findByPrimaryKey(request, pinfoID);

    if (pINFO == null) {
      createMessageWarning(request, "error.notfound", pinfoID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, pinfoID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PINFOForm pINFOForm = getPINFOForm(pINFO, __isView, request, mav);
      pINFOForm.setView(__isView);
      if(__isView) {
        pINFOForm.setAllFieldsReadOnly(ALL_PINFO_FIELDS);
        pINFOForm.setSaveButtonVisible(false);
        pINFOForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(pINFOForm, request, mav);
      mav.addObject("pINFOForm", pINFOForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PINFO existent
   */
  @RequestMapping(value = "/{pinfoID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPINFOGet(@PathVariable("pinfoID") java.lang.Long pinfoID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPINFOGet(pinfoID,
        request, response, false);
  }



  /**
   * Editar un PINFO existent
   */
  @RequestMapping(value = "/{pinfoID}/edit", method = RequestMethod.POST)
  public String editarPINFOPost(@ModelAttribute PINFOForm pINFOForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PINFOJPA pINFO = pINFOForm.getPINFO();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, pINFO, pINFOForm); // FILE
      preValidate(request, pINFOForm, result);
      getWebValidator().validate(pINFOForm, result);
      postValidate(request, pINFOForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        pINFO = update(request, pINFO);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", pINFO.getPinfoID());
        status.setComplete();
        return getRedirectWhenModified(request, pINFOForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          pINFO.getPinfoID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, pINFOForm, __e);
    }

  }


  /**
   * Eliminar un PINFO existent
   */
  @RequestMapping(value = "/{pinfoID}/delete")
  public String eliminarPINFO(@PathVariable("pinfoID") java.lang.Long pinfoID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PINFO pINFO = this.findByPrimaryKey(request, pinfoID);
      if (pINFO == null) {
        String __msg = createMessageError(request, "error.notfound", pinfoID);
        return getRedirectWhenDelete(request, pinfoID, new Exception(__msg));
      } else {
        delete(request, pINFO);
        createMessageSuccess(request, "success.deleted", pinfoID);
        return getRedirectWhenDelete(request, pinfoID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", pinfoID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, pinfoID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PINFOFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPINFO(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __pinfoID, Throwable e) {
    java.lang.Long pinfoID = (java.lang.Long)__pinfoID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (pinfoID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(pinfoID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "pINFO.pINFO";
  }

  public String getEntityNameCodePlural() {
    return "pINFO.pINFO.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("pINFO.PinfoID");
  }

  @InitBinder("pINFOFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("pINFOForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "pINFO.PinfoID");
  }

  public PINFOWebValidator getWebValidator() {
    return pINFOWebValidator;
  }


  public void setWebValidator(PINFOWebValidator __val) {
    if (__val != null) {
      this.pINFOWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PINFO
   */
  @RequestMapping(value = "/{pinfoID}/cancel")
  public String cancelPINFO(@PathVariable("pinfoID") java.lang.Long pinfoID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, pinfoID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de PINFO
   */
  @RequestMapping(value = "/cancel")
  public String cancelPINFO(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, PINFO pINFO,
      PINFOForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerID(), form.isFitxerIDDelete(),
        form.isNou()? null : pINFO.getFitxer());
    ((PINFOJPA)pINFO).setFitxer(f);
    if (f != null) { 
      pINFO.setFitxerID(f.getFitxerID());
    } else {
      pINFO.setFitxerID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getFitxerfirmatID(), form.isFitxerfirmatIDDelete(),
        form.isNou()? null : pINFO.getFitxerfirmat());
    ((PINFOJPA)pINFO).setFitxerfirmat(f);
    if (f != null) { 
      pINFO.setFitxerfirmatID(f.getFitxerID());
    } else {
      pINFO.setFitxerfirmatID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(PINFO pINFO) {
    deleteFile(pINFO.getFitxerID());
    deleteFile(pINFO.getFitxerfirmatID());
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


  public List<StringKeyValue> getReferenceListForIncidenciaID(HttpServletRequest request,
       ModelAndView mav, PINFOForm pINFOForm, Where where)  throws I18NException {
    if (pINFOForm.isHiddenField(INCIDENCIAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pINFOForm.isReadOnlyField(INCIDENCIAID)) {
      _where = IncidenciaTecnicaFields.INCIDENCIATECNICAID.equal(pINFOForm.getPINFO().getIncidenciaID());
    }
    return getReferenceListForIncidenciaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForIncidenciaID(HttpServletRequest request,
       ModelAndView mav, PINFOFilterForm pINFOFilterForm,
       List<PINFO> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pINFOFilterForm.isHiddenField(INCIDENCIAID)
       && !pINFOFilterForm.isGroupByField(INCIDENCIAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(INCIDENCIAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PINFO _item : list) {
        if(_item.getIncidenciaID() == null) { continue; };
        _pkList.add(_item.getIncidenciaID());
        }
        _w = IncidenciaTecnicaFields.INCIDENCIATECNICAID.in(_pkList);
      }
    return getReferenceListForIncidenciaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForIncidenciaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return incidenciaTecnicaRefList.getReferenceList(IncidenciaTecnicaFields.INCIDENCIATECNICAID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,PINFOForm pINFOForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PINFOForm pINFOForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PINFOFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PINFOFilterForm filterForm,  List<PINFO> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PINFOForm pINFOForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PINFOForm pINFOForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long pinfoID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long pinfoID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "pINFOFormWebDB";
  }

  public String getTileList() {
    return "pINFOListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "PINFO_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PINFOJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pinfoID) throws I18NException {
    return (PINFOJPA) pINFOEjb.findByPrimaryKey(pinfoID);
  }


  public PINFOJPA create(HttpServletRequest request, PINFOJPA pINFO)
    throws I18NException, I18NValidationException {
    return (PINFOJPA) pINFOEjb.create(pINFO);
  }


  public PINFOJPA update(HttpServletRequest request, PINFOJPA pINFO)
    throws I18NException, I18NValidationException {
    return (PINFOJPA) pINFOEjb.update(pINFO);
  }


  public void delete(HttpServletRequest request, PINFO pINFO) throws I18NException {
    pINFOEjb.delete(pINFO);
  }

} // Final de Classe

