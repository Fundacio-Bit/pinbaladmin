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

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import org.fundaciobit.pinbaladmin.back.form.webdb.*;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSolicitudForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.ModificacioSolicitudWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSolicitudJPA;
import org.fundaciobit.pinbaladmin.model.entity.ModificacioSolicitud;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pinbaladmin.back.utils.Tab;

/**
 * Controller per gestionar un ModificacioSolicitud
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="modificacioSolicitud.modificacioSolicitud.plural", order=200, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/modificacioSolicitud")
@SessionAttributes(types = { ModificacioSolicitudForm.class, ModificacioSolicitudFilterForm.class })
@Tile(name="modificacioSolicitudFormWebDB", contentJsp="/WEB-INF/jsp/webdb/modificacioSolicitudForm.jsp", extendsTile=Tab.MENU_WEBDB,
      type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="modificacioSolicitud.modificacioSolicitud")})
@Tile(name="modificacioSolicitudListWebDB", contentJsp="/WEB-INF/jsp/webdb/modificacioSolicitudList.jsp", extendsTile=Tab.MENU_WEBDB,
       type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="modificacioSolicitud.modificacioSolicitud") })
public class ModificacioSolicitudController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<ModificacioSolicitud, java.lang.Long, ModificacioSolicitudForm> implements ModificacioSolicitudFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ModificacioSolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ModificacioSolicitudService modificacioSolicitudEjb;

  @Autowired
  private ModificacioSolicitudWebValidator modificacioSolicitudWebValidator;

  @Autowired
  protected ModificacioSolicitudRefList modificacioSolicitudRefList;

  // References 
  @Autowired
  protected SolicitudRefList solicitudRefList;

  // References 
  @Autowired
  protected OrganRefList organRefList;

  /**
   * Llistat de totes ModificacioSolicitud
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    ModificacioSolicitudFilterForm ff;
    ff = (ModificacioSolicitudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar ModificacioSolicitud de forma paginada
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
    llistat(mav, request, getModificacioSolicitudFilterForm(pagina, mav, request));
    return mav;
  }

  public ModificacioSolicitudFilterForm getModificacioSolicitudFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    ModificacioSolicitudFilterForm modificacioSolicitudFilterForm;
    modificacioSolicitudFilterForm = (ModificacioSolicitudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(modificacioSolicitudFilterForm == null) {
      modificacioSolicitudFilterForm = new ModificacioSolicitudFilterForm();
      modificacioSolicitudFilterForm.setContexte(getContextWeb());
      modificacioSolicitudFilterForm.setEntityNameCode(getEntityNameCode());
      modificacioSolicitudFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      modificacioSolicitudFilterForm.setNou(true);
    } else {
      modificacioSolicitudFilterForm.setNou(false);
    }
    modificacioSolicitudFilterForm.setPage(pagina == null ? 1 : pagina);
    return modificacioSolicitudFilterForm;
  }

  /**
   * Segona i següent peticions per llistar ModificacioSolicitud de forma paginada
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
      @ModelAttribute ModificacioSolicitudFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getModificacioSolicitudFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de ModificacioSolicitud de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<ModificacioSolicitud> llistat(ModelAndView mav, HttpServletRequest request,
     ModificacioSolicitudFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<ModificacioSolicitud> modificacioSolicitud = processarLlistat(modificacioSolicitudEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("modificacioSolicitudItems", modificacioSolicitud);

    mav.addObject("modificacioSolicitudFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, modificacioSolicitud, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, modificacioSolicitud);

    return modificacioSolicitud;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(ModificacioSolicitudFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<ModificacioSolicitud> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field solicitudID
    {
      _listSKV = getReferenceListForSolicitudID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfSolicitudForSolicitudID(_tmp);
      if (filterForm.getGroupByFields().contains(SOLICITUDID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SOLICITUDID, false);
      };
    }

    // Field estatID
    {
      _listSKV = getReferenceListForEstatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEstatID(_tmp);
      if (filterForm.getGroupByFields().contains(ESTATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTATID, false);
      };
    }

    // Field organID
    {
      _listSKV = getReferenceListForOrganID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfOrganForOrganID(_tmp);
      if (filterForm.getGroupByFields().contains(ORGANID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ORGANID, false);
      };
    }

    // Field consentiment
    {
      _listSKV = getReferenceListForConsentiment(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForConsentiment(_tmp);
      if (filterForm.getGroupByFields().contains(CONSENTIMENT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONSENTIMENT, false);
      };
    }

    // Field estatModificacio
    {
      _listSKV = getReferenceListForEstatModificacio(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEstatModificacio(_tmp);
      if (filterForm.getGroupByFields().contains(ESTATMODIFICACIO)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTATMODIFICACIO, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    ModificacioSolicitudFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<ModificacioSolicitud> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_MODIFICACIOSOLICITUD_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(SOLICITUDID, filterForm.getMapOfSolicitudForSolicitudID());
    __mapping.put(ESTATID, filterForm.getMapOfValuesForEstatID());
    __mapping.put(ORGANID, filterForm.getMapOfOrganForOrganID());
    __mapping.put(CONSENTIMENT, filterForm.getMapOfValuesForConsentiment());
    __mapping.put(ESTATMODIFICACIO, filterForm.getMapOfValuesForEstatModificacio());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou ModificacioSolicitud
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearModificacioSolicitudGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    ModificacioSolicitudForm modificacioSolicitudForm = getModificacioSolicitudForm(null, false, request, mav);
    mav.addObject("modificacioSolicitudForm" ,modificacioSolicitudForm);
    fillReferencesForForm(modificacioSolicitudForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public ModificacioSolicitudForm getModificacioSolicitudForm(ModificacioSolicitudJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    ModificacioSolicitudForm modificacioSolicitudForm;
    if(_jpa == null) {
      modificacioSolicitudForm = new ModificacioSolicitudForm(new ModificacioSolicitudJPA(), true);
    } else {
      modificacioSolicitudForm = new ModificacioSolicitudForm(_jpa, false);
      modificacioSolicitudForm.setView(__isView);
    }
    modificacioSolicitudForm.setContexte(getContextWeb());
    modificacioSolicitudForm.setEntityNameCode(getEntityNameCode());
    modificacioSolicitudForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return modificacioSolicitudForm;
  }

  public void fillReferencesForForm(ModificacioSolicitudForm modificacioSolicitudForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (modificacioSolicitudForm.getListOfSolicitudForSolicitudID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSolicitudID(request, mav, modificacioSolicitudForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      modificacioSolicitudForm.setListOfSolicitudForSolicitudID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (modificacioSolicitudForm.getListOfValuesForEstatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstatID(request, mav, modificacioSolicitudForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      modificacioSolicitudForm.setListOfValuesForEstatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (modificacioSolicitudForm.getListOfOrganForOrganID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForOrganID(request, mav, modificacioSolicitudForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      modificacioSolicitudForm.setListOfOrganForOrganID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (modificacioSolicitudForm.getListOfValuesForConsentiment() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConsentiment(request, mav, modificacioSolicitudForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      modificacioSolicitudForm.setListOfValuesForConsentiment(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (modificacioSolicitudForm.getListOfValuesForEstatModificacio() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstatModificacio(request, mav, modificacioSolicitudForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      modificacioSolicitudForm.setListOfValuesForEstatModificacio(_listSKV);
    }
    
  }

  /**
   * Guardar un nou ModificacioSolicitud
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearModificacioSolicitudPost(@ModelAttribute ModificacioSolicitudForm modificacioSolicitudForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModificacioSolicitudJPA modificacioSolicitud = modificacioSolicitudForm.getModificacioSolicitud();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, modificacioSolicitud, modificacioSolicitudForm); // FILE
      preValidate(request, modificacioSolicitudForm, result);
      getWebValidator().validate(modificacioSolicitudForm, result);
      postValidate(request,modificacioSolicitudForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        modificacioSolicitud = create(request, modificacioSolicitud);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", modificacioSolicitud.getModsoliID());
        modificacioSolicitudForm.setModificacioSolicitud(modificacioSolicitud);
        return getRedirectWhenCreated(request, modificacioSolicitudForm);
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

  @RequestMapping(value = "/view/{modsoliID}", method = RequestMethod.GET)
  public ModelAndView veureModificacioSolicitudGet(@PathVariable("modsoliID") java.lang.Long modsoliID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewModificacioSolicitudGet(modsoliID,
        request, response, true);
  }


  protected ModelAndView editAndViewModificacioSolicitudGet(@PathVariable("modsoliID") java.lang.Long modsoliID,
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
    ModificacioSolicitudJPA modificacioSolicitud = findByPrimaryKey(request, modsoliID);

    if (modificacioSolicitud == null) {
      createMessageWarning(request, "error.notfound", modsoliID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      ModificacioSolicitudForm modificacioSolicitudForm = getModificacioSolicitudForm(modificacioSolicitud, __isView, request, mav);
      modificacioSolicitudForm.setView(__isView);
      if(__isView) {
        modificacioSolicitudForm.setAllFieldsReadOnly(ALL_MODIFICACIOSOLICITUD_FIELDS);
        modificacioSolicitudForm.setSaveButtonVisible(false);
        modificacioSolicitudForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(modificacioSolicitudForm, request, mav);
      mav.addObject("modificacioSolicitudForm", modificacioSolicitudForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un ModificacioSolicitud existent
   */
  @RequestMapping(value = "/{modsoliID}/edit", method = RequestMethod.GET)
  public ModelAndView editarModificacioSolicitudGet(@PathVariable("modsoliID") java.lang.Long modsoliID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewModificacioSolicitudGet(modsoliID,
        request, response, false);
  }



  /**
   * Editar un ModificacioSolicitud existent
   */
  @RequestMapping(value = "/{modsoliID}/edit", method = RequestMethod.POST)
  public String editarModificacioSolicitudPost(@ModelAttribute ModificacioSolicitudForm modificacioSolicitudForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModificacioSolicitudJPA modificacioSolicitud = modificacioSolicitudForm.getModificacioSolicitud();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, modificacioSolicitud, modificacioSolicitudForm); // FILE
      preValidate(request, modificacioSolicitudForm, result);
      getWebValidator().validate(modificacioSolicitudForm, result);
      postValidate(request, modificacioSolicitudForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        modificacioSolicitud = update(request, modificacioSolicitud);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", modificacioSolicitud.getModsoliID());
        status.setComplete();
        return getRedirectWhenModified(request, modificacioSolicitudForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          modificacioSolicitud.getModsoliID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, modificacioSolicitudForm, __e);
    }

  }


  /**
   * Eliminar un ModificacioSolicitud existent
   */
  @RequestMapping(value = "/{modsoliID}/delete")
  public String eliminarModificacioSolicitud(@PathVariable("modsoliID") java.lang.Long modsoliID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      ModificacioSolicitud modificacioSolicitud = this.findByPrimaryKey(request, modsoliID);
      if (modificacioSolicitud == null) {
        String __msg = createMessageError(request, "error.notfound", modsoliID);
        return getRedirectWhenDelete(request, modsoliID, new Exception(__msg));
      } else {
        delete(request, modificacioSolicitud);
        createMessageSuccess(request, "success.deleted", modsoliID);
        return getRedirectWhenDelete(request, modsoliID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", modsoliID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, modsoliID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute ModificacioSolicitudFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarModificacioSolicitud(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __modsoliID, Throwable e) {
    java.lang.Long modsoliID = (java.lang.Long)__modsoliID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (modsoliID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(modsoliID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "modificacioSolicitud.modificacioSolicitud";
  }

  public String getEntityNameCodePlural() {
    return "modificacioSolicitud.modificacioSolicitud.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("modificacioSolicitud.modsoliID");
  }

  @InitBinder("modificacioSolicitudFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("modificacioSolicitudForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "modificacioSolicitud.modsoliID");
  }

  public ModificacioSolicitudWebValidator getWebValidator() {
    return modificacioSolicitudWebValidator;
  }


  public void setWebValidator(ModificacioSolicitudWebValidator __val) {
    if (__val != null) {
      this.modificacioSolicitudWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de ModificacioSolicitud
   */
  @RequestMapping(value = "/{modsoliID}/cancel")
  public String cancelModificacioSolicitud(@PathVariable("modsoliID") java.lang.Long modsoliID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, modsoliID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de ModificacioSolicitud
   */
  @RequestMapping(value = "/cancel")
  public String cancelModificacioSolicitud(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, ModificacioSolicitud modificacioSolicitud,
      ModificacioSolicitudForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getDoCconsentimentID(), form.isDoCconsentimentIDDelete(),
        form.isNou()? null : modificacioSolicitud.getDoCconsentiment());
    ((ModificacioSolicitudJPA)modificacioSolicitud).setDoCconsentiment(f);
    if (f != null) { 
      modificacioSolicitud.setDoCconsentimentID(f.getFitxerID());
    } else {
      modificacioSolicitud.setDoCconsentimentID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(ModificacioSolicitud modificacioSolicitud) {
    deleteFile(modificacioSolicitud.getDoCconsentimentID());
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


  public List<StringKeyValue> getReferenceListForSolicitudID(HttpServletRequest request,
       ModelAndView mav, ModificacioSolicitudForm modificacioSolicitudForm, Where where)  throws I18NException {
    if (modificacioSolicitudForm.isHiddenField(SOLICITUDID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (modificacioSolicitudForm.isReadOnlyField(SOLICITUDID)) {
      _where = SolicitudFields.SOLICITUDID.equal(modificacioSolicitudForm.getModificacioSolicitud().getSolicitudID());
    }
    return getReferenceListForSolicitudID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSolicitudID(HttpServletRequest request,
       ModelAndView mav, ModificacioSolicitudFilterForm modificacioSolicitudFilterForm,
       List<ModificacioSolicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modificacioSolicitudFilterForm.isHiddenField(SOLICITUDID)
       && !modificacioSolicitudFilterForm.isGroupByField(SOLICITUDID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SOLICITUDID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (ModificacioSolicitud _item : list) {
        _pkList.add(_item.getSolicitudID());
        }
        _w = SolicitudFields.SOLICITUDID.in(_pkList);
      }
    return getReferenceListForSolicitudID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSolicitudID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return solicitudRefList.getReferenceList(SolicitudFields.SOLICITUDID, where );
  }


  public List<StringKeyValue> getReferenceListForEstatID(HttpServletRequest request,
       ModelAndView mav, ModificacioSolicitudForm modificacioSolicitudForm, Where where)  throws I18NException {
    if (modificacioSolicitudForm.isHiddenField(ESTATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEstatID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstatID(HttpServletRequest request,
       ModelAndView mav, ModificacioSolicitudFilterForm modificacioSolicitudFilterForm,
       List<ModificacioSolicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modificacioSolicitudFilterForm.isHiddenField(ESTATID)
       && !modificacioSolicitudFilterForm.isGroupByField(ESTATID)
       && !modificacioSolicitudFilterForm.isFilterByField(ESTATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEstatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("30" , "30"));
    __tmp.add(new StringKeyValue("20" , "20"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForOrganID(HttpServletRequest request,
       ModelAndView mav, ModificacioSolicitudForm modificacioSolicitudForm, Where where)  throws I18NException {
    if (modificacioSolicitudForm.isHiddenField(ORGANID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (modificacioSolicitudForm.isReadOnlyField(ORGANID)) {
      _where = OrganFields.ORGANID.equal(modificacioSolicitudForm.getModificacioSolicitud().getOrganID());
    }
    return getReferenceListForOrganID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForOrganID(HttpServletRequest request,
       ModelAndView mav, ModificacioSolicitudFilterForm modificacioSolicitudFilterForm,
       List<ModificacioSolicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modificacioSolicitudFilterForm.isHiddenField(ORGANID)
       && !modificacioSolicitudFilterForm.isGroupByField(ORGANID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ORGANID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (ModificacioSolicitud _item : list) {
        if(_item.getOrganID() == null) { continue; };
        _pkList.add(_item.getOrganID());
        }
        _w = OrganFields.ORGANID.in(_pkList);
      }
    return getReferenceListForOrganID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForOrganID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return organRefList.getReferenceList(OrganFields.ORGANID, where );
  }


  public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request,
       ModelAndView mav, ModificacioSolicitudForm modificacioSolicitudForm, Where where)  throws I18NException {
    if (modificacioSolicitudForm.isHiddenField(CONSENTIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForConsentiment(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request,
       ModelAndView mav, ModificacioSolicitudFilterForm modificacioSolicitudFilterForm,
       List<ModificacioSolicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modificacioSolicitudFilterForm.isHiddenField(CONSENTIMENT)
       && !modificacioSolicitudFilterForm.isGroupByField(CONSENTIMENT)
       && !modificacioSolicitudFilterForm.isFilterByField(CONSENTIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForConsentiment(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForConsentiment(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("noop" , "noop"));
    __tmp.add(new StringKeyValue("si" , "si"));
    __tmp.add(new StringKeyValue("llei" , "llei"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForEstatModificacio(HttpServletRequest request,
       ModelAndView mav, ModificacioSolicitudForm modificacioSolicitudForm, Where where)  throws I18NException {
    if (modificacioSolicitudForm.isHiddenField(ESTATMODIFICACIO)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEstatModificacio(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstatModificacio(HttpServletRequest request,
       ModelAndView mav, ModificacioSolicitudFilterForm modificacioSolicitudFilterForm,
       List<ModificacioSolicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modificacioSolicitudFilterForm.isHiddenField(ESTATMODIFICACIO)
       && !modificacioSolicitudFilterForm.isGroupByField(ESTATMODIFICACIO)
       && !modificacioSolicitudFilterForm.isFilterByField(ESTATMODIFICACIO)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEstatModificacio(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstatModificacio(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("creacion" , "creacion"));
    __tmp.add(new StringKeyValue("enviada" , "enviada"));
    __tmp.add(new StringKeyValue("aceptada" , "aceptada"));
    __tmp.add(new StringKeyValue("rechazada" , "rechazada"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,ModificacioSolicitudForm modificacioSolicitudForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,ModificacioSolicitudForm modificacioSolicitudForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, ModificacioSolicitudFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, ModificacioSolicitudFilterForm filterForm,  List<ModificacioSolicitud> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, ModificacioSolicitudForm modificacioSolicitudForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, ModificacioSolicitudForm modificacioSolicitudForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long modsoliID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long modsoliID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
        try {
            Set<Tile> rm;
            rm=AnnotationUtils.getDeclaredRepeatableAnnotations(this.getClass(), Tile.class);
            if (rm != null && !rm.isEmpty()) {
                String trobada = null;
                for (Tile tile : rm) {
                    if (tile.type() == TileType.WEBDB_FORM) {
                        trobada = tile.name();
                    }
                }
                if (trobada != null) {
                    return trobada;
                }
            }
        } catch (Exception e) {
            log.error("Error en el getTileForm: " + e.getMessage(), e);
        }
    return "modificacioSolicitudFormWebDB";
  }

    public String getTileList() {
        try {
            Set<Tile> rm;
            rm=AnnotationUtils.getDeclaredRepeatableAnnotations(this.getClass(), Tile.class);
            if (rm != null && !rm.isEmpty()) {
                String trobada = null;
                for (Tile tile : rm) {
                    if (tile.type() == TileType.WEBDB_LIST) {
                        trobada = tile.name();
                    }
                }
                if (trobada != null) {
                    return trobada;
                }
            }
        } catch (Exception e) {
            log.error("Error en el getTileList: " + e.getMessage(), e);
        }
        return "modificacioSolicitudListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "ModificacioSolicitud_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public ModificacioSolicitudJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long modsoliID) throws I18NException {
    return (ModificacioSolicitudJPA) modificacioSolicitudEjb.findByPrimaryKey(modsoliID);
  }


  public ModificacioSolicitudJPA create(HttpServletRequest request, ModificacioSolicitudJPA modificacioSolicitud)
    throws I18NException, I18NValidationException {
    return (ModificacioSolicitudJPA) modificacioSolicitudEjb.create(modificacioSolicitud);
  }


  public ModificacioSolicitudJPA update(HttpServletRequest request, ModificacioSolicitudJPA modificacioSolicitud)
    throws I18NException, I18NValidationException {
    return (ModificacioSolicitudJPA) modificacioSolicitudEjb.update(modificacioSolicitud);
  }


  public void delete(HttpServletRequest request, ModificacioSolicitud modificacioSolicitud) throws I18NException {
    modificacioSolicitudEjb.delete(modificacioSolicitud);
  }

} // Final de Classe

