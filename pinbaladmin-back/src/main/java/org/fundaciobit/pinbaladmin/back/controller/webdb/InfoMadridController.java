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
import org.fundaciobit.pinbaladmin.back.form.webdb.InfoMadridForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.InfoMadridWebValidator;

import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pinbaladmin.back.utils.Tab;

/**
 * Controller per gestionar un InfoMadrid
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="infoMadrid.infoMadrid.plural", order=200, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/infoMadrid")
@SessionAttributes(types = { InfoMadridForm.class, InfoMadridFilterForm.class })
@Tile(name="infoMadridFormWebDB", contentJsp="/WEB-INF/jsp/webdb/infoMadridForm.jsp", extendsTile=Tab.MENU_WEBDB,
      type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="infoMadrid.infoMadrid")})
@Tile(name="infoMadridListWebDB", contentJsp="/WEB-INF/jsp/webdb/infoMadridList.jsp", extendsTile=Tab.MENU_WEBDB,
       type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="infoMadrid.infoMadrid") })
public class InfoMadridController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<InfoMadrid, java.lang.Long> implements InfoMadridFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.InfoMadridService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.InfoMadridService infoMadridEjb;

  @Autowired
  private InfoMadridWebValidator infoMadridWebValidator;

  @Autowired
  protected InfoMadridRefList infoMadridRefList;

  /**
   * Llistat de totes InfoMadrid
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    InfoMadridFilterForm ff;
    ff = (InfoMadridFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar InfoMadrid de forma paginada
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
    llistat(mav, request, getInfoMadridFilterForm(pagina, mav, request));
    return mav;
  }

  public InfoMadridFilterForm getInfoMadridFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    InfoMadridFilterForm infoMadridFilterForm;
    infoMadridFilterForm = (InfoMadridFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(infoMadridFilterForm == null) {
      infoMadridFilterForm = new InfoMadridFilterForm();
      infoMadridFilterForm.setContexte(getContextWeb());
      infoMadridFilterForm.setEntityNameCode(getEntityNameCode());
      infoMadridFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      infoMadridFilterForm.setNou(true);
    } else {
      infoMadridFilterForm.setNou(false);
    }
    infoMadridFilterForm.setPage(pagina == null ? 1 : pagina);
    return infoMadridFilterForm;
  }

  /**
   * Segona i següent peticions per llistar InfoMadrid de forma paginada
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
      @ModelAttribute InfoMadridFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getInfoMadridFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de InfoMadrid de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<InfoMadrid> llistat(ModelAndView mav, HttpServletRequest request,
     InfoMadridFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<InfoMadrid> infoMadrid = processarLlistat(infoMadridEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("infoMadridItems", infoMadrid);

    mav.addObject("infoMadridFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, infoMadrid, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, infoMadrid);

    return infoMadrid;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(InfoMadridFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<InfoMadrid> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field estatProcediment
    {
      _listSKV = getReferenceListForEstatProcediment(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEstatProcediment(_tmp);
      if (filterForm.getGroupByFields().contains(ESTATPROCEDIMENT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTATPROCEDIMENT, false);
      };
    }

    // Field estatAutoritzacio
    {
      _listSKV = getReferenceListForEstatAutoritzacio(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEstatAutoritzacio(_tmp);
      if (filterForm.getGroupByFields().contains(ESTATAUTORITZACIO)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTATAUTORITZACIO, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    InfoMadridFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<InfoMadrid> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_INFOMADRID_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ESTATPROCEDIMENT, filterForm.getMapOfValuesForEstatProcediment());
    __mapping.put(ESTATAUTORITZACIO, filterForm.getMapOfValuesForEstatAutoritzacio());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou InfoMadrid
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearInfoMadridGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    InfoMadridForm infoMadridForm = getInfoMadridForm(null, false, request, mav);
    mav.addObject("infoMadridForm" ,infoMadridForm);
    fillReferencesForForm(infoMadridForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public InfoMadridForm getInfoMadridForm(InfoMadridJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    InfoMadridForm infoMadridForm;
    if(_jpa == null) {
      infoMadridForm = new InfoMadridForm(new InfoMadridJPA(), true);
    } else {
      infoMadridForm = new InfoMadridForm(_jpa, false);
      infoMadridForm.setView(__isView);
    }
    infoMadridForm.setContexte(getContextWeb());
    infoMadridForm.setEntityNameCode(getEntityNameCode());
    infoMadridForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return infoMadridForm;
  }

  public void fillReferencesForForm(InfoMadridForm infoMadridForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (infoMadridForm.getListOfValuesForEstatProcediment() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstatProcediment(request, mav, infoMadridForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      infoMadridForm.setListOfValuesForEstatProcediment(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (infoMadridForm.getListOfValuesForEstatAutoritzacio() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstatAutoritzacio(request, mav, infoMadridForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      infoMadridForm.setListOfValuesForEstatAutoritzacio(_listSKV);
    }
    
  }

  /**
   * Guardar un nou InfoMadrid
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearInfoMadridPost(@ModelAttribute InfoMadridForm infoMadridForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    InfoMadridJPA infoMadrid = infoMadridForm.getInfoMadrid();

    try {
      preValidate(request, infoMadridForm, result);
      getWebValidator().validate(infoMadridForm, result);
      postValidate(request,infoMadridForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        infoMadrid = create(request, infoMadrid);
        createMessageSuccess(request, "success.creation", infoMadrid.getInfoMadridID());
        infoMadridForm.setInfoMadrid(infoMadrid);
        return getRedirectWhenCreated(request, infoMadridForm);
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

  @RequestMapping(value = "/view/{infoMadridID}", method = RequestMethod.GET)
  public ModelAndView veureInfoMadridGet(@PathVariable("infoMadridID") java.lang.Long infoMadridID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewInfoMadridGet(infoMadridID,
        request, response, true);
  }


  protected ModelAndView editAndViewInfoMadridGet(@PathVariable("infoMadridID") java.lang.Long infoMadridID,
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
    InfoMadridJPA infoMadrid = findByPrimaryKey(request, infoMadridID);

    if (infoMadrid == null) {
      createMessageWarning(request, "error.notfound", infoMadridID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      InfoMadridForm infoMadridForm = getInfoMadridForm(infoMadrid, __isView, request, mav);
      infoMadridForm.setView(__isView);
      if(__isView) {
        infoMadridForm.setAllFieldsReadOnly(ALL_INFOMADRID_FIELDS);
        infoMadridForm.setSaveButtonVisible(false);
        infoMadridForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(infoMadridForm, request, mav);
      mav.addObject("infoMadridForm", infoMadridForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un InfoMadrid existent
   */
  @RequestMapping(value = "/{infoMadridID}/edit", method = RequestMethod.GET)
  public ModelAndView editarInfoMadridGet(@PathVariable("infoMadridID") java.lang.Long infoMadridID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewInfoMadridGet(infoMadridID,
        request, response, false);
  }



  /**
   * Editar un InfoMadrid existent
   */
  @RequestMapping(value = "/{infoMadridID}/edit", method = RequestMethod.POST)
  public String editarInfoMadridPost(@ModelAttribute InfoMadridForm infoMadridForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    InfoMadridJPA infoMadrid = infoMadridForm.getInfoMadrid();

    try {
      preValidate(request, infoMadridForm, result);
      getWebValidator().validate(infoMadridForm, result);
      postValidate(request, infoMadridForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        infoMadrid = update(request, infoMadrid);
        createMessageSuccess(request, "success.modification", infoMadrid.getInfoMadridID());
        status.setComplete();
        return getRedirectWhenModified(request, infoMadridForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          infoMadrid.getInfoMadridID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, infoMadridForm, __e);
    }

  }


  /**
   * Eliminar un InfoMadrid existent
   */
  @RequestMapping(value = "/{infoMadridID}/delete")
  public String eliminarInfoMadrid(@PathVariable("infoMadridID") java.lang.Long infoMadridID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      InfoMadrid infoMadrid = this.findByPrimaryKey(request, infoMadridID);
      if (infoMadrid == null) {
        String __msg = createMessageError(request, "error.notfound", infoMadridID);
        return getRedirectWhenDelete(request, infoMadridID, new Exception(__msg));
      } else {
        delete(request, infoMadrid);
        createMessageSuccess(request, "success.deleted", infoMadridID);
        return getRedirectWhenDelete(request, infoMadridID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", infoMadridID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, infoMadridID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute InfoMadridFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarInfoMadrid(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __infoMadridID, Throwable e) {
    java.lang.Long infoMadridID = (java.lang.Long)__infoMadridID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (infoMadridID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(infoMadridID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "infoMadrid.infoMadrid";
  }

  public String getEntityNameCodePlural() {
    return "infoMadrid.infoMadrid.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("infoMadrid.infoMadridID");
  }

  @InitBinder("infoMadridFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("infoMadridForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "infoMadrid.infoMadridID");
  }

  public InfoMadridWebValidator getWebValidator() {
    return infoMadridWebValidator;
  }


  public void setWebValidator(InfoMadridWebValidator __val) {
    if (__val != null) {
      this.infoMadridWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de InfoMadrid
   */
  @RequestMapping(value = "/{infoMadridID}/cancel")
  public String cancelInfoMadrid(@PathVariable("infoMadridID") java.lang.Long infoMadridID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, infoMadridID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de InfoMadrid
   */
  @RequestMapping(value = "/cancel")
  public String cancelInfoMadrid(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForEstatProcediment(HttpServletRequest request,
       ModelAndView mav, InfoMadridForm infoMadridForm, Where where)  throws I18NException {
    if (infoMadridForm.isHiddenField(ESTATPROCEDIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEstatProcediment(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstatProcediment(HttpServletRequest request,
       ModelAndView mav, InfoMadridFilterForm infoMadridFilterForm,
       List<InfoMadrid> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (infoMadridFilterForm.isHiddenField(ESTATPROCEDIMENT)
       && !infoMadridFilterForm.isGroupByField(ESTATPROCEDIMENT)
       && !infoMadridFilterForm.isFilterByField(ESTATPROCEDIMENT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEstatProcediment(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstatProcediment(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForEstatAutoritzacio(HttpServletRequest request,
       ModelAndView mav, InfoMadridForm infoMadridForm, Where where)  throws I18NException {
    if (infoMadridForm.isHiddenField(ESTATAUTORITZACIO)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEstatAutoritzacio(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstatAutoritzacio(HttpServletRequest request,
       ModelAndView mav, InfoMadridFilterForm infoMadridFilterForm,
       List<InfoMadrid> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (infoMadridFilterForm.isHiddenField(ESTATAUTORITZACIO)
       && !infoMadridFilterForm.isGroupByField(ESTATAUTORITZACIO)
       && !infoMadridFilterForm.isFilterByField(ESTATAUTORITZACIO)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEstatAutoritzacio(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstatAutoritzacio(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
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

  public void preValidate(HttpServletRequest request,InfoMadridForm infoMadridForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,InfoMadridForm infoMadridForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, InfoMadridFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, InfoMadridFilterForm filterForm,  List<InfoMadrid> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, InfoMadridForm infoMadridForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, InfoMadridForm infoMadridForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long infoMadridID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long infoMadridID) {
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
    return "infoMadridFormWebDB";
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
        return "infoMadridListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "InfoMadrid_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public InfoMadridJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long infoMadridID) throws I18NException {
    return (InfoMadridJPA) infoMadridEjb.findByPrimaryKey(infoMadridID);
  }


  public InfoMadridJPA create(HttpServletRequest request, InfoMadridJPA infoMadrid)
    throws I18NException, I18NValidationException {
    return (InfoMadridJPA) infoMadridEjb.create(infoMadrid);
  }


  public InfoMadridJPA update(HttpServletRequest request, InfoMadridJPA infoMadrid)
    throws I18NException, I18NValidationException {
    return (InfoMadridJPA) infoMadridEjb.update(infoMadrid);
  }


  public void delete(HttpServletRequest request, InfoMadrid infoMadrid) throws I18NException {
    infoMadridEjb.delete(infoMadrid);
  }

} // Final de Classe

