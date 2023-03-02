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
import org.fundaciobit.pinbaladmin.back.form.webdb.TiquetForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.TiquetWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.persistence.TiquetJPA;
import org.fundaciobit.pinbaladmin.model.entity.Tiquet;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Tiquet
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tiquet")
@SessionAttributes(types = { TiquetForm.class, TiquetFilterForm.class })
public class TiquetController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<Tiquet, java.lang.Long, TiquetForm> implements TiquetFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TiquetService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TiquetService tiquetEjb;

  @Autowired
  private TiquetWebValidator tiquetWebValidator;

  @Autowired
  protected TiquetRefList tiquetRefList;

  // References 
  @Autowired
  protected EstatTiquetRefList estatTiquetRefList;

  // References 
  @Autowired
  protected TipusTiquetRefList tipusTiquetRefList;

  /**
   * Llistat de totes Tiquet
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TiquetFilterForm ff;
    ff = (TiquetFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Tiquet de forma paginada
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
    llistat(mav, request, getTiquetFilterForm(pagina, mav, request));
    return mav;
  }

  public TiquetFilterForm getTiquetFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TiquetFilterForm tiquetFilterForm;
    tiquetFilterForm = (TiquetFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tiquetFilterForm == null) {
      tiquetFilterForm = new TiquetFilterForm();
      tiquetFilterForm.setContexte(getContextWeb());
      tiquetFilterForm.setEntityNameCode(getEntityNameCode());
      tiquetFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tiquetFilterForm.setNou(true);
    } else {
      tiquetFilterForm.setNou(false);
    }
    tiquetFilterForm.setPage(pagina == null ? 1 : pagina);
    return tiquetFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Tiquet de forma paginada
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
      @ModelAttribute TiquetFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTiquetFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Tiquet de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Tiquet> llistat(ModelAndView mav, HttpServletRequest request,
     TiquetFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Tiquet> tiquet = processarLlistat(tiquetEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tiquetItems", tiquet);

    mav.addObject("tiquetFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tiquet, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tiquet);

    return tiquet;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TiquetFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Tiquet> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field estatTiquetID
    {
      _listSKV = getReferenceListForEstatTiquetID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEstatTiquetForEstatTiquetID(_tmp);
      if (filterForm.getGroupByFields().contains(ESTATTIQUETID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTATTIQUETID, false);
      };
    }

    // Field tipusTiquetID
    {
      _listSKV = getReferenceListForTipusTiquetID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTipusTiquetForTipusTiquetID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSTIQUETID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSTIQUETID, false);
      };
    }

    // Field entorn
    {
      _listSKV = getReferenceListForEntorn(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEntorn(_tmp);
      if (filterForm.getGroupByFields().contains(ENTORN)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTORN, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TiquetFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Tiquet> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIQUET_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ESTATTIQUETID, filterForm.getMapOfEstatTiquetForEstatTiquetID());
    __mapping.put(TIPUSTIQUETID, filterForm.getMapOfTipusTiquetForTipusTiquetID());
    __mapping.put(ENTORN, filterForm.getMapOfValuesForEntorn());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Tiquet
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTiquetGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TiquetForm tiquetForm = getTiquetForm(null, false, request, mav);
    mav.addObject("tiquetForm" ,tiquetForm);
    fillReferencesForForm(tiquetForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TiquetForm getTiquetForm(TiquetJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TiquetForm tiquetForm;
    if(_jpa == null) {
      tiquetForm = new TiquetForm(new TiquetJPA(), true);
    } else {
      tiquetForm = new TiquetForm(_jpa, false);
      tiquetForm.setView(__isView);
    }
    tiquetForm.setContexte(getContextWeb());
    tiquetForm.setEntityNameCode(getEntityNameCode());
    tiquetForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tiquetForm;
  }

  public void fillReferencesForForm(TiquetForm tiquetForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tiquetForm.getListOfEstatTiquetForEstatTiquetID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstatTiquetID(request, mav, tiquetForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tiquetForm.setListOfEstatTiquetForEstatTiquetID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tiquetForm.getListOfTipusTiquetForTipusTiquetID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusTiquetID(request, mav, tiquetForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tiquetForm.setListOfTipusTiquetForTipusTiquetID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tiquetForm.getListOfValuesForEntorn() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntorn(request, mav, tiquetForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tiquetForm.setListOfValuesForEntorn(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Tiquet
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTiquetPost(@ModelAttribute TiquetForm tiquetForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TiquetJPA tiquet = tiquetForm.getTiquet();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, tiquet, tiquetForm); // FILE
      preValidate(request, tiquetForm, result);
      getWebValidator().validate(tiquetForm, result);
      postValidate(request,tiquetForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        tiquet = create(request, tiquet);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", tiquet.getTiquetID());
        tiquetForm.setTiquet(tiquet);
        return getRedirectWhenCreated(request, tiquetForm);
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

  @RequestMapping(value = "/view/{tiquetID}", method = RequestMethod.GET)
  public ModelAndView veureTiquetGet(@PathVariable("tiquetID") java.lang.Long tiquetID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTiquetGet(tiquetID,
        request, response, true);
  }


  protected ModelAndView editAndViewTiquetGet(@PathVariable("tiquetID") java.lang.Long tiquetID,
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
    TiquetJPA tiquet = findByPrimaryKey(request, tiquetID);

    if (tiquet == null) {
      createMessageWarning(request, "error.notfound", tiquetID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, tiquetID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TiquetForm tiquetForm = getTiquetForm(tiquet, __isView, request, mav);
      tiquetForm.setView(__isView);
      if(__isView) {
        tiquetForm.setAllFieldsReadOnly(ALL_TIQUET_FIELDS);
        tiquetForm.setSaveButtonVisible(false);
        tiquetForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tiquetForm, request, mav);
      mav.addObject("tiquetForm", tiquetForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Tiquet existent
   */
  @RequestMapping(value = "/{tiquetID}/edit", method = RequestMethod.GET)
  public ModelAndView editarTiquetGet(@PathVariable("tiquetID") java.lang.Long tiquetID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTiquetGet(tiquetID,
        request, response, false);
  }



  /**
   * Editar un Tiquet existent
   */
  @RequestMapping(value = "/{tiquetID}/edit", method = RequestMethod.POST)
  public String editarTiquetPost(@ModelAttribute TiquetForm tiquetForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TiquetJPA tiquet = tiquetForm.getTiquet();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, tiquet, tiquetForm); // FILE
      preValidate(request, tiquetForm, result);
      getWebValidator().validate(tiquetForm, result);
      postValidate(request, tiquetForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        tiquet = update(request, tiquet);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", tiquet.getTiquetID());
        status.setComplete();
        return getRedirectWhenModified(request, tiquetForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tiquet.getTiquetID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tiquetForm, __e);
    }

  }


  /**
   * Eliminar un Tiquet existent
   */
  @RequestMapping(value = "/{tiquetID}/delete")
  public String eliminarTiquet(@PathVariable("tiquetID") java.lang.Long tiquetID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Tiquet tiquet = tiquetEjb.findByPrimaryKey(tiquetID);
      if (tiquet == null) {
        String __msg =createMessageError(request, "error.notfound", tiquetID);
        return getRedirectWhenDelete(request, tiquetID, new Exception(__msg));
      } else {
        delete(request, tiquet);
        createMessageSuccess(request, "success.deleted", tiquetID);
        return getRedirectWhenDelete(request, tiquetID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", tiquetID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, tiquetID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TiquetFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTiquet(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __tiquetID, Throwable e) {
    java.lang.Long tiquetID = (java.lang.Long)__tiquetID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (tiquetID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(tiquetID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tiquet.tiquet";
  }

  public String getEntityNameCodePlural() {
    return "tiquet.tiquet.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tiquet.tiquetID");
  }

  @InitBinder("tiquetFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tiquetForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "tiquet.tiquetID");
  }

  public TiquetWebValidator getWebValidator() {
    return tiquetWebValidator;
  }


  public void setWebValidator(TiquetWebValidator __val) {
    if (__val != null) {
      this.tiquetWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Tiquet
   */
  @RequestMapping(value = "/{tiquetID}/cancel")
  public String cancelTiquet(@PathVariable("tiquetID") java.lang.Long tiquetID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, tiquetID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Tiquet tiquet,
      TiquetForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getAdjunt1ID(), form.isAdjunt1IDDelete(),
        form.isNou()? null : tiquet.getAdjunt1());
    ((TiquetJPA)tiquet).setAdjunt1(f);
    if (f != null) { 
      tiquet.setAdjunt1ID(f.getFitxerID());
    } else {
      tiquet.setAdjunt1ID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getAdjunt2ID(), form.isAdjunt2IDDelete(),
        form.isNou()? null : tiquet.getAdjunt2());
    ((TiquetJPA)tiquet).setAdjunt2(f);
    if (f != null) { 
      tiquet.setAdjunt2ID(f.getFitxerID());
    } else {
      tiquet.setAdjunt2ID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(Tiquet tiquet) {
    deleteFile(tiquet.getAdjunt1ID());
    deleteFile(tiquet.getAdjunt2ID());
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


  public List<StringKeyValue> getReferenceListForEstatTiquetID(HttpServletRequest request,
       ModelAndView mav, TiquetForm tiquetForm, Where where)  throws I18NException {
    if (tiquetForm.isHiddenField(ESTATTIQUETID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tiquetForm.isReadOnlyField(ESTATTIQUETID)) {
      _where = EstatTiquetFields.ESTATTIQUETID.equal(tiquetForm.getTiquet().getEstatTiquetID());
    }
    return getReferenceListForEstatTiquetID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEstatTiquetID(HttpServletRequest request,
       ModelAndView mav, TiquetFilterForm tiquetFilterForm,
       List<Tiquet> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tiquetFilterForm.isHiddenField(ESTATTIQUETID)
      && !tiquetFilterForm.isGroupByField(ESTATTIQUETID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ESTATTIQUETID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Tiquet _item : list) {
        _pkList.add(_item.getEstatTiquetID());
        }
        _w = EstatTiquetFields.ESTATTIQUETID.in(_pkList);
      }
    return getReferenceListForEstatTiquetID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstatTiquetID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return estatTiquetRefList.getReferenceList(EstatTiquetFields.ESTATTIQUETID, where );
  }


  public List<StringKeyValue> getReferenceListForTipusTiquetID(HttpServletRequest request,
       ModelAndView mav, TiquetForm tiquetForm, Where where)  throws I18NException {
    if (tiquetForm.isHiddenField(TIPUSTIQUETID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tiquetForm.isReadOnlyField(TIPUSTIQUETID)) {
      _where = TipusTiquetFields.TIPUSTIQUETID.equal(tiquetForm.getTiquet().getTipusTiquetID());
    }
    return getReferenceListForTipusTiquetID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTipusTiquetID(HttpServletRequest request,
       ModelAndView mav, TiquetFilterForm tiquetFilterForm,
       List<Tiquet> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tiquetFilterForm.isHiddenField(TIPUSTIQUETID)
      && !tiquetFilterForm.isGroupByField(TIPUSTIQUETID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TIPUSTIQUETID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Tiquet _item : list) {
        _pkList.add(_item.getTipusTiquetID());
        }
        _w = TipusTiquetFields.TIPUSTIQUETID.in(_pkList);
      }
    return getReferenceListForTipusTiquetID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusTiquetID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return tipusTiquetRefList.getReferenceList(TipusTiquetFields.TIPUSTIQUETID, where );
  }


  public List<StringKeyValue> getReferenceListForEntorn(HttpServletRequest request,
       ModelAndView mav, TiquetForm tiquetForm, Where where)  throws I18NException {
    if (tiquetForm.isHiddenField(ENTORN)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEntorn(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEntorn(HttpServletRequest request,
       ModelAndView mav, TiquetFilterForm tiquetFilterForm,
       List<Tiquet> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tiquetFilterForm.isHiddenField(ENTORN)
      && !tiquetFilterForm.isGroupByField(ENTORN)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEntorn(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntorn(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,TiquetForm tiquetForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TiquetForm tiquetForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TiquetFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TiquetFilterForm filterForm,  List<Tiquet> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TiquetForm tiquetForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TiquetForm tiquetForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long tiquetID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long tiquetID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tiquetFormWebDB";
  }

  public String getTileList() {
    return "tiquetListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TiquetWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TiquetJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long tiquetID) throws I18NException {
    return (TiquetJPA) tiquetEjb.findByPrimaryKey(tiquetID);
  }


  public TiquetJPA create(HttpServletRequest request, TiquetJPA tiquet)
    throws I18NException, I18NValidationException {
    return (TiquetJPA) tiquetEjb.create(tiquet);
  }


  public TiquetJPA update(HttpServletRequest request, TiquetJPA tiquet)
    throws I18NException, I18NValidationException {
    return (TiquetJPA) tiquetEjb.update(tiquet);
  }


  public void delete(HttpServletRequest request, Tiquet tiquet) throws I18NException {
    tiquetEjb.delete(tiquet);
  }

} // Final de Classe

