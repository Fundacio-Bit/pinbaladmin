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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;

import org.fundaciobit.pinbaladmin.back.form.webdb.*;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSoliServForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.ModificacioSoliServWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSoliServJPA;
import org.fundaciobit.pinbaladmin.model.entity.ModificacioSoliServ;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pinbaladmin.back.utils.Tab;

/**
 * Controller per gestionar un ModificacioSoliServ
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="modificacioSoliServ.modificacioSoliServ.plural", order=210, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/modificacioSoliServ")
@SessionAttributes(types = { ModificacioSoliServForm.class, ModificacioSoliServFilterForm.class })
@Tile(name="modificacioSoliServFormWebDB", contentJsp="/WEB-INF/jsp/webdb/modificacioSoliServForm.jsp", extendsTile=Tab.MENU_WEBDB,
      type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="modificacioSoliServ.modificacioSoliServ")})
@Tile(name="modificacioSoliServListWebDB", contentJsp="/WEB-INF/jsp/webdb/modificacioSoliServList.jsp", extendsTile=Tab.MENU_WEBDB,
       type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="modificacioSoliServ.modificacioSoliServ") })
public class ModificacioSoliServController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<ModificacioSoliServ, java.lang.Long, ModificacioSoliServForm> implements ModificacioSoliServFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ModificacioSoliServService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ModificacioSoliServService modificacioSoliServEjb;

  @Autowired
  private ModificacioSoliServWebValidator modificacioSoliServWebValidator;

  @Autowired
  protected ModificacioSoliServRefList modificacioSoliServRefList;

  // References 
  @Autowired
  protected SolicitudServeiRefList solicitudServeiRefList;

  // References 
  @Autowired
  protected ModificacioSolicitudRefList modificacioSolicitudRefList;

  /**
   * Llistat de totes ModificacioSoliServ
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    ModificacioSoliServFilterForm ff;
    ff = (ModificacioSoliServFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar ModificacioSoliServ de forma paginada
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
    llistat(mav, request, getModificacioSoliServFilterForm(pagina, mav, request));
    return mav;
  }

  public ModificacioSoliServFilterForm getModificacioSoliServFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    ModificacioSoliServFilterForm modificacioSoliServFilterForm;
    modificacioSoliServFilterForm = (ModificacioSoliServFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(modificacioSoliServFilterForm == null) {
      modificacioSoliServFilterForm = new ModificacioSoliServFilterForm();
      modificacioSoliServFilterForm.setContexte(getContextWeb());
      modificacioSoliServFilterForm.setEntityNameCode(getEntityNameCode());
      modificacioSoliServFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      modificacioSoliServFilterForm.setNou(true);
    } else {
      modificacioSoliServFilterForm.setNou(false);
    }
    modificacioSoliServFilterForm.setPage(pagina == null ? 1 : pagina);
    return modificacioSoliServFilterForm;
  }

  /**
   * Segona i següent peticions per llistar ModificacioSoliServ de forma paginada
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
      @ModelAttribute ModificacioSoliServFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getModificacioSoliServFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de ModificacioSoliServ de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<ModificacioSoliServ> llistat(ModelAndView mav, HttpServletRequest request,
     ModificacioSoliServFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<ModificacioSoliServ> modificacioSoliServ = processarLlistat(modificacioSoliServEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("modificacioSoliServItems", modificacioSoliServ);

    mav.addObject("modificacioSoliServFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, modificacioSoliServ, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, modificacioSoliServ);

    return modificacioSoliServ;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(ModificacioSoliServFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<ModificacioSoliServ> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field soliServID
    {
      _listSKV = getReferenceListForSoliServID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfSolicitudServeiForSoliServID(_tmp);
      if (filterForm.getGroupByFields().contains(SOLISERVID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SOLISERVID, false);
      };
    }

    // Field modSoliID
    {
      _listSKV = getReferenceListForModSoliID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfModificacioSolicitudForModSoliID(_tmp);
      if (filterForm.getGroupByFields().contains(MODSOLIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, MODSOLIID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    ModificacioSoliServFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<ModificacioSoliServ> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_MODIFICACIOSOLISERV_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(SOLISERVID, filterForm.getMapOfSolicitudServeiForSoliServID());
    __mapping.put(MODSOLIID, filterForm.getMapOfModificacioSolicitudForModSoliID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou ModificacioSoliServ
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearModificacioSoliServGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    ModificacioSoliServForm modificacioSoliServForm = getModificacioSoliServForm(null, false, request, mav);
    mav.addObject("modificacioSoliServForm" ,modificacioSoliServForm);
    fillReferencesForForm(modificacioSoliServForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public ModificacioSoliServForm getModificacioSoliServForm(ModificacioSoliServJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    ModificacioSoliServForm modificacioSoliServForm;
    if(_jpa == null) {
      modificacioSoliServForm = new ModificacioSoliServForm(new ModificacioSoliServJPA(), true);
    } else {
      modificacioSoliServForm = new ModificacioSoliServForm(_jpa, false);
      modificacioSoliServForm.setView(__isView);
    }
    modificacioSoliServForm.setContexte(getContextWeb());
    modificacioSoliServForm.setEntityNameCode(getEntityNameCode());
    modificacioSoliServForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return modificacioSoliServForm;
  }

  public void fillReferencesForForm(ModificacioSoliServForm modificacioSoliServForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (modificacioSoliServForm.getListOfSolicitudServeiForSoliServID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSoliServID(request, mav, modificacioSoliServForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      modificacioSoliServForm.setListOfSolicitudServeiForSoliServID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (modificacioSoliServForm.getListOfModificacioSolicitudForModSoliID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForModSoliID(request, mav, modificacioSoliServForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      modificacioSoliServForm.setListOfModificacioSolicitudForModSoliID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou ModificacioSoliServ
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearModificacioSoliServPost(@ModelAttribute ModificacioSoliServForm modificacioSoliServForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModificacioSoliServJPA modificacioSoliServ = modificacioSoliServForm.getModificacioSoliServ();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, modificacioSoliServ, modificacioSoliServForm); // FILE
      preValidate(request, modificacioSoliServForm, result);
      getWebValidator().validate(modificacioSoliServForm, result);
      postValidate(request,modificacioSoliServForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        modificacioSoliServ = create(request, modificacioSoliServ);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", modificacioSoliServ.getModsoliservid());
        modificacioSoliServForm.setModificacioSoliServ(modificacioSoliServ);
        return getRedirectWhenCreated(request, modificacioSoliServForm);
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

  @RequestMapping(value = "/view/{modsoliservid}", method = RequestMethod.GET)
  public ModelAndView veureModificacioSoliServGet(@PathVariable("modsoliservid") java.lang.Long modsoliservid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewModificacioSoliServGet(modsoliservid,
        request, response, true);
  }


  protected ModelAndView editAndViewModificacioSoliServGet(@PathVariable("modsoliservid") java.lang.Long modsoliservid,
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
    ModificacioSoliServJPA modificacioSoliServ = findByPrimaryKey(request, modsoliservid);

    if (modificacioSoliServ == null) {
      createMessageWarning(request, "error.notfound", modsoliservid);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      ModificacioSoliServForm modificacioSoliServForm = getModificacioSoliServForm(modificacioSoliServ, __isView, request, mav);
      modificacioSoliServForm.setView(__isView);
      if(__isView) {
        modificacioSoliServForm.setAllFieldsReadOnly(ALL_MODIFICACIOSOLISERV_FIELDS);
        modificacioSoliServForm.setSaveButtonVisible(false);
        modificacioSoliServForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(modificacioSoliServForm, request, mav);
      mav.addObject("modificacioSoliServForm", modificacioSoliServForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un ModificacioSoliServ existent
   */
  @RequestMapping(value = "/{modsoliservid}/edit", method = RequestMethod.GET)
  public ModelAndView editarModificacioSoliServGet(@PathVariable("modsoliservid") java.lang.Long modsoliservid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewModificacioSoliServGet(modsoliservid,
        request, response, false);
  }



  /**
   * Editar un ModificacioSoliServ existent
   */
  @RequestMapping(value = "/{modsoliservid}/edit", method = RequestMethod.POST)
  public String editarModificacioSoliServPost(@ModelAttribute ModificacioSoliServForm modificacioSoliServForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModificacioSoliServJPA modificacioSoliServ = modificacioSoliServForm.getModificacioSoliServ();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, modificacioSoliServ, modificacioSoliServForm); // FILE
      preValidate(request, modificacioSoliServForm, result);
      getWebValidator().validate(modificacioSoliServForm, result);
      postValidate(request, modificacioSoliServForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        modificacioSoliServ = update(request, modificacioSoliServ);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", modificacioSoliServ.getModsoliservid());
        status.setComplete();
        return getRedirectWhenModified(request, modificacioSoliServForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          modificacioSoliServ.getModsoliservid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, modificacioSoliServForm, __e);
    }

  }


  /**
   * Eliminar un ModificacioSoliServ existent
   */
  @RequestMapping(value = "/{modsoliservid}/delete")
  public String eliminarModificacioSoliServ(@PathVariable("modsoliservid") java.lang.Long modsoliservid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      ModificacioSoliServ modificacioSoliServ = this.findByPrimaryKey(request, modsoliservid);
      if (modificacioSoliServ == null) {
        String __msg = createMessageError(request, "error.notfound", modsoliservid);
        return getRedirectWhenDelete(request, modsoliservid, new Exception(__msg));
      } else {
        delete(request, modificacioSoliServ);
        createMessageSuccess(request, "success.deleted", modsoliservid);
        return getRedirectWhenDelete(request, modsoliservid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", modsoliservid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, modsoliservid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute ModificacioSoliServFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarModificacioSoliServ(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __modsoliservid, Throwable e) {
    java.lang.Long modsoliservid = (java.lang.Long)__modsoliservid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (modsoliservid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(modsoliservid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "modificacioSoliServ.modificacioSoliServ";
  }

  public String getEntityNameCodePlural() {
    return "modificacioSoliServ.modificacioSoliServ.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("modificacioSoliServ.modsoliservid");
  }

  @InitBinder("modificacioSoliServFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("modificacioSoliServForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "modificacioSoliServ.modsoliservid");
  }

  public ModificacioSoliServWebValidator getWebValidator() {
    return modificacioSoliServWebValidator;
  }


  public void setWebValidator(ModificacioSoliServWebValidator __val) {
    if (__val != null) {
      this.modificacioSoliServWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de ModificacioSoliServ
   */
  @RequestMapping(value = "/{modsoliservid}/cancel")
  public String cancelModificacioSoliServ(@PathVariable("modsoliservid") java.lang.Long modsoliservid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, modsoliservid);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de ModificacioSoliServ
   */
  @RequestMapping(value = "/cancel")
  public String cancelModificacioSoliServ(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, ModificacioSoliServ modificacioSoliServ,
      ModificacioSoliServForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerNorma1ID(), form.isFitxerNorma1IDDelete(),
        form.isNou()? null : modificacioSoliServ.getFitxerNorma1());
    ((ModificacioSoliServJPA)modificacioSoliServ).setFitxerNorma1(f);
    if (f != null) { 
      modificacioSoliServ.setFitxerNorma1ID(f.getFitxerID());
    } else {
      modificacioSoliServ.setFitxerNorma1ID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getFitxerNorma2ID(), form.isFitxerNorma2IDDelete(),
        form.isNou()? null : modificacioSoliServ.getFitxerNorma2());
    ((ModificacioSoliServJPA)modificacioSoliServ).setFitxerNorma2(f);
    if (f != null) { 
      modificacioSoliServ.setFitxerNorma2ID(f.getFitxerID());
    } else {
      modificacioSoliServ.setFitxerNorma2ID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getFitxerNorma3ID(), form.isFitxerNorma3IDDelete(),
        form.isNou()? null : modificacioSoliServ.getFitxerNorma3());
    ((ModificacioSoliServJPA)modificacioSoliServ).setFitxerNorma3(f);
    if (f != null) { 
      modificacioSoliServ.setFitxerNorma3ID(f.getFitxerID());
    } else {
      modificacioSoliServ.setFitxerNorma3ID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(ModificacioSoliServ modificacioSoliServ) {
    deleteFile(modificacioSoliServ.getFitxerNorma1ID());
    deleteFile(modificacioSoliServ.getFitxerNorma2ID());
    deleteFile(modificacioSoliServ.getFitxerNorma3ID());
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


  public List<StringKeyValue> getReferenceListForSoliServID(HttpServletRequest request,
       ModelAndView mav, ModificacioSoliServForm modificacioSoliServForm, Where where)  throws I18NException {
    if (modificacioSoliServForm.isHiddenField(SOLISERVID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (modificacioSoliServForm.isReadOnlyField(SOLISERVID)) {
      _where = SolicitudServeiFields.ID.equal(modificacioSoliServForm.getModificacioSoliServ().getSoliServID());
    }
    return getReferenceListForSoliServID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSoliServID(HttpServletRequest request,
       ModelAndView mav, ModificacioSoliServFilterForm modificacioSoliServFilterForm,
       List<ModificacioSoliServ> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modificacioSoliServFilterForm.isHiddenField(SOLISERVID)
       && !modificacioSoliServFilterForm.isGroupByField(SOLISERVID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SOLISERVID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (ModificacioSoliServ _item : list) {
        _pkList.add(_item.getSoliServID());
        }
        _w = SolicitudServeiFields.ID.in(_pkList);
      }
    return getReferenceListForSoliServID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSoliServID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return solicitudServeiRefList.getReferenceList(SolicitudServeiFields.ID, where );
  }


  public List<StringKeyValue> getReferenceListForModSoliID(HttpServletRequest request,
       ModelAndView mav, ModificacioSoliServForm modificacioSoliServForm, Where where)  throws I18NException {
    if (modificacioSoliServForm.isHiddenField(MODSOLIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (modificacioSoliServForm.isReadOnlyField(MODSOLIID)) {
      _where = ModificacioSolicitudFields.MODSOLIID.equal(modificacioSoliServForm.getModificacioSoliServ().getModSoliID());
    }
    return getReferenceListForModSoliID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForModSoliID(HttpServletRequest request,
       ModelAndView mav, ModificacioSoliServFilterForm modificacioSoliServFilterForm,
       List<ModificacioSoliServ> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modificacioSoliServFilterForm.isHiddenField(MODSOLIID)
       && !modificacioSoliServFilterForm.isGroupByField(MODSOLIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(MODSOLIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (ModificacioSoliServ _item : list) {
        _pkList.add(_item.getModSoliID());
        }
        _w = ModificacioSolicitudFields.MODSOLIID.in(_pkList);
      }
    return getReferenceListForModSoliID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForModSoliID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return modificacioSolicitudRefList.getReferenceList(ModificacioSolicitudFields.MODSOLIID, where );
  }


    @Override
    /** Ha de ser igual que el RequestMapping de la Classe */
    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        final String[] values = rm.value();
        if (values.length == 1) {
            return values[0];
        } else {
            final HttpServletRequest request;
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            final String servletPath = request.getServletPath();

            for (String webcontext : values) {
                if (servletPath.startsWith(webcontext)) {
                    return webcontext;
                }
            }

            log.warn(" No puc trobar el contextweb associat a la cridada.");
            log.warn(" ==== RequestMapping::value=" + Arrays.toString(values));
            log.warn(" ++++ getContextWeb::Scheme: " + request.getScheme());
            log.warn(" ++++ getContextWeb::PathInfo: " + request.getPathInfo());
            log.warn(" ++++ getContextWeb::PathTrans: " + request.getPathTranslated());
            log.warn(" ++++ getContextWeb::ContextPath: " + request.getContextPath());
            log.warn(" ++++ getContextWeb::ServletPath: " + request.getServletPath());
            log.warn(" ++++ getContextWeb::getRequestURI: " + request.getRequestURI());
            log.warn(" ++++ getContextWeb::getRequestURL: " + request.getRequestURL().toString());
            log.warn(" ++++ getContextWeb::getQueryString: " + request.getQueryString());

            return values[0];
        }  }

  public void preValidate(HttpServletRequest request,ModificacioSoliServForm modificacioSoliServForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,ModificacioSoliServForm modificacioSoliServForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, ModificacioSoliServFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, ModificacioSoliServFilterForm filterForm,  List<ModificacioSoliServ> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, ModificacioSoliServForm modificacioSoliServForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, ModificacioSoliServForm modificacioSoliServForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long modsoliservid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long modsoliservid) {
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
    return "modificacioSoliServFormWebDB";
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
        return "modificacioSoliServListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "ModificacioSoliServ_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public ModificacioSoliServJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long modsoliservid) throws I18NException {
    return (ModificacioSoliServJPA) modificacioSoliServEjb.findByPrimaryKey(modsoliservid);
  }


  public ModificacioSoliServJPA create(HttpServletRequest request, ModificacioSoliServJPA modificacioSoliServ)
    throws I18NException, I18NValidationException {
    return (ModificacioSoliServJPA) modificacioSoliServEjb.create(modificacioSoliServ);
  }


  public ModificacioSoliServJPA update(HttpServletRequest request, ModificacioSoliServJPA modificacioSoliServ)
    throws I18NException, I18NValidationException {
    return (ModificacioSoliServJPA) modificacioSoliServEjb.update(modificacioSoliServ);
  }


  public void delete(HttpServletRequest request, ModificacioSoliServ modificacioSoliServ) throws I18NException {
    modificacioSoliServEjb.delete(modificacioSoliServ);
  }

} // Final de Classe

