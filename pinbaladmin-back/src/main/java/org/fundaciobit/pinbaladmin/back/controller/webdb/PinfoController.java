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
import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.PinfoWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pinbaladmin.back.utils.Tab;

/**
 * Controller per gestionar un Pinfo
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="pinfo.pinfo.plural", order=250, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/pinfo")
@SessionAttributes(types = { PinfoForm.class, PinfoFilterForm.class })
@Tile(name="pinfoFormWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe
    contentJsp="/WEB-INF/jsp/webdb/pinfoForm.jsp", type=TileType.WEBDB_FORM,
    attributes={ @TileAttribute(name="titol", value="pinfo.pinfo")})
@Tile(name="pinfoListWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe 
    contentJsp="/WEB-INF/jsp/webdb/pinfoList.jsp", type=TileType.WEBDB_LIST,
    attributes={ @TileAttribute(name="titol", value="pinfo.pinfo")})
public class PinfoController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<Pinfo, java.lang.Long, PinfoForm> implements PinfoFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.PinfoService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.PinfoService pinfoEjb;

  @Autowired
  private PinfoWebValidator pinfoWebValidator;

  @Autowired
  protected PinfoRefList pinfoRefList;

  // References 
  @Autowired
  protected IncidenciaTecnicaRefList incidenciaTecnicaRefList;

  /**
   * Llistat de totes Pinfo
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PinfoFilterForm ff;
    ff = (PinfoFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Pinfo de forma paginada
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
    llistat(mav, request, getPinfoFilterForm(pagina, mav, request));
    return mav;
  }

  public PinfoFilterForm getPinfoFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PinfoFilterForm pinfoFilterForm;
    pinfoFilterForm = (PinfoFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(pinfoFilterForm == null) {
      pinfoFilterForm = new PinfoFilterForm();
      pinfoFilterForm.setContexte(getContextWeb());
      pinfoFilterForm.setEntityNameCode(getEntityNameCode());
      pinfoFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      pinfoFilterForm.setNou(true);
    } else {
      pinfoFilterForm.setNou(false);
    }
    pinfoFilterForm.setPage(pagina == null ? 1 : pagina);
    return pinfoFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Pinfo de forma paginada
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
      @ModelAttribute PinfoFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPinfoFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Pinfo de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Pinfo> llistat(ModelAndView mav, HttpServletRequest request,
     PinfoFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Pinfo> pinfo = processarLlistat(pinfoEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("pinfoItems", pinfo);

    mav.addObject("pinfoFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, pinfo, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, pinfo);

    return pinfo;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PinfoFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Pinfo> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field incidenciaID
    {
      _listSKV = getReferenceListForIncidenciaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfIncidenciaTecnicaForIncidenciaID(_tmp);
      if (filterForm.getGroupByFields().contains(INCIDENCIAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, INCIDENCIAID, false);
      };
    }

    // Field entitat
    {
      _listSKV = getReferenceListForEntitat(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEntitat(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITAT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITAT, false);
      };
    }

    // Field estat
    {
      _listSKV = getReferenceListForEstat(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEstat(_tmp);
      if (filterForm.getGroupByFields().contains(ESTAT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTAT, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PinfoFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Pinfo> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PINFO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(INCIDENCIAID, filterForm.getMapOfIncidenciaTecnicaForIncidenciaID());
    __mapping.put(ENTITAT, filterForm.getMapOfValuesForEntitat());
    __mapping.put(ESTAT, filterForm.getMapOfValuesForEstat());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Pinfo
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPinfoGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PinfoForm pinfoForm = getPinfoForm(null, false, request, mav);
    mav.addObject("pinfoForm" ,pinfoForm);
    fillReferencesForForm(pinfoForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PinfoForm getPinfoForm(PinfoJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PinfoForm pinfoForm;
    if(_jpa == null) {
      pinfoForm = new PinfoForm(new PinfoJPA(), true);
    } else {
      pinfoForm = new PinfoForm(_jpa, false);
      pinfoForm.setView(__isView);
    }
    pinfoForm.setContexte(getContextWeb());
    pinfoForm.setEntityNameCode(getEntityNameCode());
    pinfoForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return pinfoForm;
  }

  public void fillReferencesForForm(PinfoForm pinfoForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (pinfoForm.getListOfIncidenciaTecnicaForIncidenciaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForIncidenciaID(request, mav, pinfoForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pinfoForm.setListOfIncidenciaTecnicaForIncidenciaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pinfoForm.getListOfValuesForEntitat() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitat(request, mav, pinfoForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pinfoForm.setListOfValuesForEntitat(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pinfoForm.getListOfValuesForEstat() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstat(request, mav, pinfoForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pinfoForm.setListOfValuesForEstat(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Pinfo
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPinfoPost(@ModelAttribute PinfoForm pinfoForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PinfoJPA pinfo = pinfoForm.getPinfo();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, pinfo, pinfoForm); // FILE
      preValidate(request, pinfoForm, result);
      getWebValidator().validate(pinfoForm, result);
      postValidate(request,pinfoForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        pinfo = create(request, pinfo);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", pinfo.getPinfoID());
        pinfoForm.setPinfo(pinfo);
        return getRedirectWhenCreated(request, pinfoForm);
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
  public ModelAndView veurePinfoGet(@PathVariable("pinfoID") java.lang.Long pinfoID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPinfoGet(pinfoID,
        request, response, true);
  }


  protected ModelAndView editAndViewPinfoGet(@PathVariable("pinfoID") java.lang.Long pinfoID,
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
    PinfoJPA pinfo = findByPrimaryKey(request, pinfoID);

    if (pinfo == null) {
      createMessageWarning(request, "error.notfound", pinfoID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PinfoForm pinfoForm = getPinfoForm(pinfo, __isView, request, mav);
      pinfoForm.setView(__isView);
      if(__isView) {
        pinfoForm.setAllFieldsReadOnly(ALL_PINFO_FIELDS);
        pinfoForm.setSaveButtonVisible(false);
        pinfoForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(pinfoForm, request, mav);
      mav.addObject("pinfoForm", pinfoForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Pinfo existent
   */
  @RequestMapping(value = "/{pinfoID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPinfoGet(@PathVariable("pinfoID") java.lang.Long pinfoID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPinfoGet(pinfoID,
        request, response, false);
  }



  /**
   * Editar un Pinfo existent
   */
  @RequestMapping(value = "/{pinfoID}/edit", method = RequestMethod.POST)
  public String editarPinfoPost(@ModelAttribute PinfoForm pinfoForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PinfoJPA pinfo = pinfoForm.getPinfo();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, pinfo, pinfoForm); // FILE
      preValidate(request, pinfoForm, result);
      getWebValidator().validate(pinfoForm, result);
      postValidate(request, pinfoForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        pinfo = update(request, pinfo);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", pinfo.getPinfoID());
        status.setComplete();
        return getRedirectWhenModified(request, pinfoForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          pinfo.getPinfoID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, pinfoForm, __e);
    }

  }


  /**
   * Eliminar un Pinfo existent
   */
  @RequestMapping(value = "/{pinfoID}/delete")
  public String eliminarPinfo(@PathVariable("pinfoID") java.lang.Long pinfoID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Pinfo pinfo = this.findByPrimaryKey(request, pinfoID);
      if (pinfo == null) {
        String __msg = createMessageError(request, "error.notfound", pinfoID);
        return getRedirectWhenDelete(request, pinfoID, new Exception(__msg));
      } else {
        delete(request, pinfo);
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
    @ModelAttribute PinfoFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPinfo(stringToPK(seleccionats[i]), request, response);
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
    return "pinfo.pinfo";
  }

  public String getEntityNameCodePlural() {
    return "pinfo.pinfo.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("pinfo.pinfoID");
  }

  @InitBinder("pinfoFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("pinfoForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "pinfo.pinfoID");
  }

  public PinfoWebValidator getWebValidator() {
    return pinfoWebValidator;
  }


  public void setWebValidator(PinfoWebValidator __val) {
    if (__val != null) {
      this.pinfoWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Pinfo
   */
  @RequestMapping(value = "/{pinfoID}/cancel")
  public String cancelPinfo(@PathVariable("pinfoID") java.lang.Long pinfoID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, pinfoID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Pinfo
   */
  @RequestMapping(value = "/cancel")
  public String cancelPinfo(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Pinfo pinfo,
      PinfoForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerID(), form.isFitxerIDDelete(),
        form.isNou()? null : pinfo.getFitxer());
    ((PinfoJPA)pinfo).setFitxer(f);
    if (f != null) { 
      pinfo.setFitxerID(f.getFitxerID());
    } else {
      pinfo.setFitxerID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getFitxerfirmatID(), form.isFitxerfirmatIDDelete(),
        form.isNou()? null : pinfo.getFitxerfirmat());
    ((PinfoJPA)pinfo).setFitxerfirmat(f);
    if (f != null) { 
      pinfo.setFitxerfirmatID(f.getFitxerID());
    } else {
      pinfo.setFitxerfirmatID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(Pinfo pinfo) {
    deleteFile(pinfo.getFitxerID());
    deleteFile(pinfo.getFitxerfirmatID());
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
       ModelAndView mav, PinfoForm pinfoForm, Where where)  throws I18NException {
    if (pinfoForm.isHiddenField(INCIDENCIAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pinfoForm.isReadOnlyField(INCIDENCIAID)) {
      _where = IncidenciaTecnicaFields.INCIDENCIATECNICAID.equal(pinfoForm.getPinfo().getIncidenciaID());
    }
    return getReferenceListForIncidenciaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForIncidenciaID(HttpServletRequest request,
       ModelAndView mav, PinfoFilterForm pinfoFilterForm,
       List<Pinfo> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pinfoFilterForm.isHiddenField(INCIDENCIAID)
       && !pinfoFilterForm.isGroupByField(INCIDENCIAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(INCIDENCIAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Pinfo _item : list) {
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


  public List<StringKeyValue> getReferenceListForEntitat(HttpServletRequest request,
       ModelAndView mav, PinfoForm pinfoForm, Where where)  throws I18NException {
    if (pinfoForm.isHiddenField(ENTITAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEntitat(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEntitat(HttpServletRequest request,
       ModelAndView mav, PinfoFilterForm pinfoFilterForm,
       List<Pinfo> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pinfoFilterForm.isHiddenField(ENTITAT)
       && !pinfoFilterForm.isGroupByField(ENTITAT)
       && !pinfoFilterForm.isFilterByField(ENTITAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEntitat(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitat(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("GOVERN" , "GOVERN"));
    __tmp.add(new StringKeyValue("FOGAIBA" , "FOGAIBA"));
    __tmp.add(new StringKeyValue("IBSALUT" , "IBSALUT"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, PinfoForm pinfoForm, Where where)  throws I18NException {
    if (pinfoForm.isHiddenField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEstat(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, PinfoFilterForm pinfoFilterForm,
       List<Pinfo> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pinfoFilterForm.isHiddenField(ESTAT)
       && !pinfoFilterForm.isGroupByField(ESTAT)
       && !pinfoFilterForm.isFilterByField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEstat(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("-1" , "-1"));
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
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

  public void preValidate(HttpServletRequest request,PinfoForm pinfoForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PinfoForm pinfoForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PinfoFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PinfoFilterForm filterForm,  List<Pinfo> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PinfoForm pinfoForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PinfoForm pinfoForm, Throwable __e) {
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
    return "pinfoFormWebDB";
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
        return "pinfoListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "Pinfo_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PinfoJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pinfoID) throws I18NException {
    return (PinfoJPA) pinfoEjb.findByPrimaryKey(pinfoID);
  }


  public PinfoJPA create(HttpServletRequest request, PinfoJPA pinfo)
    throws I18NException, I18NValidationException {
    return (PinfoJPA) pinfoEjb.create(pinfo);
  }


  public PinfoJPA update(HttpServletRequest request, PinfoJPA pinfo)
    throws I18NException, I18NValidationException {
    return (PinfoJPA) pinfoEjb.update(pinfo);
  }


  public void delete(HttpServletRequest request, Pinfo pinfo) throws I18NException {
    pinfoEjb.delete(pinfo);
  }

} // Final de Classe

