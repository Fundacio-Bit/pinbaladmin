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
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.fundaciobit.pinbaladmin.back.form.webdb.*;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.SolicitudWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.jpa.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.jpa.SolicitudJPA;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Solicitud
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/solicitud")
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<Solicitud, java.lang.Long, SolicitudForm> implements SolicitudFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudLocal solicitudEjb;

  @Autowired
  private SolicitudWebValidator solicitudWebValidator;

  @Autowired
  protected SolicitudRefList solicitudRefList;

  // References 
  @Autowired
  protected EstatSolicitudRefList estatSolicitudRefList;

  // References 
  @Autowired
  protected DepartamentRefList departamentRefList;

  /**
   * Llistat de totes Solicitud
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    SolicitudFilterForm ff;
    ff = (SolicitudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Solicitud de forma paginada
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
    llistat(mav, request, getSolicitudFilterForm(pagina, mav, request));
    return mav;
  }

  public SolicitudFilterForm getSolicitudFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    SolicitudFilterForm solicitudFilterForm;
    solicitudFilterForm = (SolicitudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(solicitudFilterForm == null) {
      solicitudFilterForm = new SolicitudFilterForm();
      solicitudFilterForm.setContexte(getContextWeb());
      solicitudFilterForm.setEntityNameCode(getEntityNameCode());
      solicitudFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      solicitudFilterForm.setNou(true);
    } else {
      solicitudFilterForm.setNou(false);
    }
    solicitudFilterForm.setPage(pagina == null ? 1 : pagina);
    return solicitudFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Solicitud de forma paginada
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
      @ModelAttribute SolicitudFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getSolicitudFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Solicitud de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Solicitud> llistat(ModelAndView mav, HttpServletRequest request,
     SolicitudFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Solicitud> solicitud = processarLlistat(solicitudEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("solicitudItems", solicitud);

    mav.addObject("solicitudFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, solicitud, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, solicitud);

    return solicitud;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(SolicitudFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Solicitud> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field procedimentTipus
    {
      _listSKV = getReferenceListForProcedimentTipus(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForProcedimentTipus(_tmp);
      if (filterForm.getGroupByFields().contains(PROCEDIMENTTIPUS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PROCEDIMENTTIPUS, false);
      };
    }

    // Field estatID
    {
      _listSKV = getReferenceListForEstatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEstatSolicitudForEstatID(_tmp);
      if (filterForm.getGroupByFields().contains(ESTATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTATID, false);
      };
    }

    // Field departamentID
    {
      _listSKV = getReferenceListForDepartamentID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfDepartamentForDepartamentID(_tmp);
      if (filterForm.getGroupByFields().contains(DEPARTAMENTID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, DEPARTAMENTID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("solicitud.firmatdocsolicitud", groupByItemsMap, FIRMATDOCSOLICITUD);


      fillValuesToGroupByItemsBoolean("solicitud.produccio", groupByItemsMap, PRODUCCIO);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    SolicitudFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Solicitud> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_SOLICITUD_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(PROCEDIMENTTIPUS, filterForm.getMapOfValuesForProcedimentTipus());
    __mapping.put(ESTATID, filterForm.getMapOfEstatSolicitudForEstatID());
    __mapping.put(DEPARTAMENTID, filterForm.getMapOfDepartamentForDepartamentID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Solicitud
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearSolicitudGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    SolicitudForm solicitudForm = getSolicitudForm(null, false, request, mav);
    mav.addObject("solicitudForm" ,solicitudForm);
    fillReferencesForForm(solicitudForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public SolicitudForm getSolicitudForm(SolicitudJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    SolicitudForm solicitudForm;
    if(_jpa == null) {
      solicitudForm = new SolicitudForm(new SolicitudJPA(), true);
    } else {
      solicitudForm = new SolicitudForm(_jpa, false);
      solicitudForm.setView(__isView);
    }
    solicitudForm.setContexte(getContextWeb());
    solicitudForm.setEntityNameCode(getEntityNameCode());
    solicitudForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return solicitudForm;
  }

  public void fillReferencesForForm(SolicitudForm solicitudForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (solicitudForm.getListOfValuesForProcedimentTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForProcedimentTipus(request, mav, solicitudForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      solicitudForm.setListOfValuesForProcedimentTipus(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (solicitudForm.getListOfEstatSolicitudForEstatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstatID(request, mav, solicitudForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      solicitudForm.setListOfEstatSolicitudForEstatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (solicitudForm.getListOfDepartamentForDepartamentID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForDepartamentID(request, mav, solicitudForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      solicitudForm.setListOfDepartamentForDepartamentID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Solicitud
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearSolicitudPost(@ModelAttribute SolicitudForm solicitudForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    SolicitudJPA solicitud = solicitudForm.getSolicitud();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, solicitud, solicitudForm); // FILE
      preValidate(request, solicitudForm, result);
      getWebValidator().validate(solicitudForm, result);
      postValidate(request,solicitudForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        solicitud = create(request, solicitud);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", solicitud.getSolicitudID());
        solicitudForm.setSolicitud(solicitud);
        return getRedirectWhenCreated(request, solicitudForm);
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

  @RequestMapping(value = "/view/{solicitudID}", method = RequestMethod.GET)
  public ModelAndView veureSolicitudGet(@PathVariable("solicitudID") java.lang.Long solicitudID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewSolicitudGet(solicitudID,
        request, response, true);
  }


  protected ModelAndView editAndViewSolicitudGet(@PathVariable("solicitudID") java.lang.Long solicitudID,
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
    SolicitudJPA solicitud = findByPrimaryKey(request, solicitudID);

    if (solicitud == null) {
      createMessageWarning(request, "error.notfound", solicitudID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, solicitudID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      SolicitudForm solicitudForm = getSolicitudForm(solicitud, __isView, request, mav);
      solicitudForm.setView(__isView);
      if(__isView) {
        solicitudForm.setAllFieldsReadOnly(ALL_SOLICITUD_FIELDS);
        solicitudForm.setSaveButtonVisible(false);
        solicitudForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(solicitudForm, request, mav);
      mav.addObject("solicitudForm", solicitudForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Solicitud existent
   */
  @RequestMapping(value = "/{solicitudID}/edit", method = RequestMethod.GET)
  public ModelAndView editarSolicitudGet(@PathVariable("solicitudID") java.lang.Long solicitudID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewSolicitudGet(solicitudID,
        request, response, false);
  }



  /**
   * Editar un Solicitud existent
   */
  @RequestMapping(value = "/{solicitudID}/edit", method = RequestMethod.POST)
  public String editarSolicitudPost(@ModelAttribute @Valid SolicitudForm solicitudForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    SolicitudJPA solicitud = solicitudForm.getSolicitud();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, solicitud, solicitudForm); // FILE
      preValidate(request, solicitudForm, result);
      getWebValidator().validate(solicitud, result);
      postValidate(request, solicitudForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        solicitud = update(request, solicitud);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", solicitud.getSolicitudID());
        status.setComplete();
        return getRedirectWhenModified(request, solicitudForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          solicitud.getSolicitudID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, solicitudForm, __e);
    }

  }


  /**
   * Eliminar un Solicitud existent
   */
  @RequestMapping(value = "/{solicitudID}/delete")
  public String eliminarSolicitud(@PathVariable("solicitudID") java.lang.Long solicitudID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Solicitud solicitud = findByPrimaryKey(request, solicitudID);
      if (solicitud == null) {
        String __msg =createMessageError(request, "error.notfound", solicitudID);
        return getRedirectWhenDelete(request, solicitudID, new Exception(__msg));
      } else {
        delete(request, solicitud);
        createMessageSuccess(request, "success.deleted", solicitudID);
        return getRedirectWhenDelete(request, solicitudID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", solicitudID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, solicitudID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute SolicitudFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarSolicitud(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __solicitudID, Throwable e) {
    java.lang.Long solicitudID = (java.lang.Long)__solicitudID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (solicitudID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(solicitudID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "solicitud.solicitud";
  }

  public String getEntityNameCodePlural() {
    return "solicitud.solicitud.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("solicitud.solicitudID");
  }

  @InitBinder("solicitudFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("solicitudForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    initDisallowedFields(binder, "solicitud.solicitudID");
  }

  public SolicitudWebValidator getWebValidator() {
    return solicitudWebValidator;
  }


  public void setWebValidator(SolicitudWebValidator __val) {
    if (__val != null) {
      this.solicitudWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Solicitud
   */
  @RequestMapping(value = "/{solicitudID}/cancel")
  public String cancelSolicitud(@PathVariable("solicitudID") java.lang.Long solicitudID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, solicitudID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Solicitud solicitud,
      SolicitudForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getDocumentSolicitudID(), form.isDocumentSolicitudIDDelete(),
        form.isNou()? null : solicitud.getDocumentSolicitud());
    ((SolicitudJPA)solicitud).setDocumentSolicitud(f);
    if (f != null) { 
      solicitud.setDocumentSolicitudID(f.getFitxerID());
    } else {
      solicitud.setDocumentSolicitudID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getSolicitudXmlID(), form.isSolicitudXmlIDDelete(),
        form.isNou()? null : solicitud.getSolicitudXml());
    ((SolicitudJPA)solicitud).setSolicitudXml(f);
    if (f != null) { 
      solicitud.setSolicitudXmlID(f.getFitxerID());
    } else {
      solicitud.setSolicitudXmlID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(Solicitud solicitud) {
    deleteFile(solicitud.getDocumentSolicitudID());
    deleteFile(solicitud.getSolicitudXmlID());
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


  public List<StringKeyValue> getReferenceListForProcedimentTipus(HttpServletRequest request,
       ModelAndView mav, SolicitudForm solicitudForm, Where where)  throws I18NException {
    if (solicitudForm.isHiddenField(PROCEDIMENTTIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    return getReferenceListForProcedimentTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForProcedimentTipus(HttpServletRequest request,
       ModelAndView mav, SolicitudFilterForm solicitudFilterForm,
       List<Solicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (solicitudFilterForm.isHiddenField(PROCEDIMENTTIPUS)
      && !solicitudFilterForm.isGroupByField(PROCEDIMENTTIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    return getReferenceListForProcedimentTipus(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForProcedimentTipus(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    __tmp.add(new StringKeyValue("5" , "5"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForEstatID(HttpServletRequest request,
       ModelAndView mav, SolicitudForm solicitudForm, Where where)  throws I18NException {
    if (solicitudForm.isHiddenField(ESTATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (solicitudForm.isReadOnlyField(ESTATID)) {
      _where = EstatSolicitudFields.ESTATSOLICITUDID.equal(solicitudForm.getSolicitud().getEstatID());
    }
    return getReferenceListForEstatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEstatID(HttpServletRequest request,
       ModelAndView mav, SolicitudFilterForm solicitudFilterForm,
       List<Solicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (solicitudFilterForm.isHiddenField(ESTATID)
      && !solicitudFilterForm.isGroupByField(ESTATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ESTATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Solicitud _item : list) {
        _pkList.add(_item.getEstatID());
        }
        _w = EstatSolicitudFields.ESTATSOLICITUDID.in(_pkList);
      }
    return getReferenceListForEstatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return estatSolicitudRefList.getReferenceList(EstatSolicitudFields.ESTATSOLICITUDID, where );
  }


  public List<StringKeyValue> getReferenceListForDepartamentID(HttpServletRequest request,
       ModelAndView mav, SolicitudForm solicitudForm, Where where)  throws I18NException {
    if (solicitudForm.isHiddenField(DEPARTAMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (solicitudForm.isReadOnlyField(DEPARTAMENTID)) {
      _where = DepartamentFields.DEPARTAMENTID.equal(solicitudForm.getSolicitud().getDepartamentID());
    }
    return getReferenceListForDepartamentID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForDepartamentID(HttpServletRequest request,
       ModelAndView mav, SolicitudFilterForm solicitudFilterForm,
       List<Solicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (solicitudFilterForm.isHiddenField(DEPARTAMENTID)
      && !solicitudFilterForm.isGroupByField(DEPARTAMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(DEPARTAMENTID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Solicitud _item : list) {
        if(_item.getDepartamentID() == null) { continue; };
        _pkList.add(_item.getDepartamentID());
        }
        _w = DepartamentFields.DEPARTAMENTID.in(_pkList);
      }
    return getReferenceListForDepartamentID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForDepartamentID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return departamentRefList.getReferenceList(DepartamentFields.DEPARTAMENTID, where );
  }


  public void preValidate(HttpServletRequest request,SolicitudForm solicitudForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,SolicitudForm solicitudForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, SolicitudFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, SolicitudFilterForm filterForm,  List<Solicitud> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, SolicitudForm solicitudForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, SolicitudForm solicitudForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long solicitudID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long solicitudID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "solicitudFormWebDB";
  }

  public String getTileList() {
    return "solicitudListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "SolicitudWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public SolicitudJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long solicitudID) throws I18NException {
    return (SolicitudJPA) solicitudEjb.findByPrimaryKey(solicitudID);
  }


  public SolicitudJPA create(HttpServletRequest request, SolicitudJPA solicitud)
    throws Exception,I18NException, I18NValidationException {
    return (SolicitudJPA) solicitudEjb.create(solicitud);
  }


  public SolicitudJPA update(HttpServletRequest request, SolicitudJPA solicitud)
    throws Exception,I18NException, I18NValidationException {
    return (SolicitudJPA) solicitudEjb.update(solicitud);
  }


  public void delete(HttpServletRequest request, Solicitud solicitud) throws Exception,I18NException {
    solicitudEjb.delete(solicitud);
  }

} // Final de Classe

