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
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentCedentForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.DocumentCedentWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.persistence.DocumentCedentJPA;
import org.fundaciobit.pinbaladmin.model.entity.DocumentCedent;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un DocumentCedent
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/documentCedent")
@SessionAttributes(types = { DocumentCedentForm.class, DocumentCedentFilterForm.class })
public class DocumentCedentController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<DocumentCedent, java.lang.Long, DocumentCedentForm> implements DocumentCedentFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentCedentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentCedentService documentCedentEjb;

  @Autowired
  private DocumentCedentWebValidator documentCedentWebValidator;

  @Autowired
  protected DocumentCedentRefList documentCedentRefList;

  // References 
  @Autowired
  protected EntitatServeiRefList entitatServeiRefList;

  /**
   * Llistat de totes DocumentCedent
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    DocumentCedentFilterForm ff;
    ff = (DocumentCedentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar DocumentCedent de forma paginada
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
    llistat(mav, request, getDocumentCedentFilterForm(pagina, mav, request));
    return mav;
  }

  public DocumentCedentFilterForm getDocumentCedentFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    DocumentCedentFilterForm documentCedentFilterForm;
    documentCedentFilterForm = (DocumentCedentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(documentCedentFilterForm == null) {
      documentCedentFilterForm = new DocumentCedentFilterForm();
      documentCedentFilterForm.setContexte(getContextWeb());
      documentCedentFilterForm.setEntityNameCode(getEntityNameCode());
      documentCedentFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      documentCedentFilterForm.setNou(true);
    } else {
      documentCedentFilterForm.setNou(false);
    }
    documentCedentFilterForm.setPage(pagina == null ? 1 : pagina);
    return documentCedentFilterForm;
  }

  /**
   * Segona i següent peticions per llistar DocumentCedent de forma paginada
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
      @ModelAttribute DocumentCedentFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getDocumentCedentFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de DocumentCedent de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<DocumentCedent> llistat(ModelAndView mav, HttpServletRequest request,
     DocumentCedentFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<DocumentCedent> documentCedent = processarLlistat(documentCedentEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("documentCedentItems", documentCedent);

    mav.addObject("documentCedentFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, documentCedent, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, documentCedent);

    return documentCedent;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(DocumentCedentFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<DocumentCedent> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field entitatServeiID
    {
      _listSKV = getReferenceListForEntitatServeiID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatServeiForEntitatServeiID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATSERVEIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATSERVEIID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    DocumentCedentFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<DocumentCedent> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_DOCUMENTCEDENT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENTITATSERVEIID, filterForm.getMapOfEntitatServeiForEntitatServeiID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou DocumentCedent
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearDocumentCedentGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    DocumentCedentForm documentCedentForm = getDocumentCedentForm(null, false, request, mav);
    mav.addObject("documentCedentForm" ,documentCedentForm);
    fillReferencesForForm(documentCedentForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public DocumentCedentForm getDocumentCedentForm(DocumentCedentJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    DocumentCedentForm documentCedentForm;
    if(_jpa == null) {
      documentCedentForm = new DocumentCedentForm(new DocumentCedentJPA(), true);
    } else {
      documentCedentForm = new DocumentCedentForm(_jpa, false);
      documentCedentForm.setView(__isView);
    }
    documentCedentForm.setContexte(getContextWeb());
    documentCedentForm.setEntityNameCode(getEntityNameCode());
    documentCedentForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return documentCedentForm;
  }

  public void fillReferencesForForm(DocumentCedentForm documentCedentForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (documentCedentForm.getListOfEntitatServeiForEntitatServeiID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatServeiID(request, mav, documentCedentForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      documentCedentForm.setListOfEntitatServeiForEntitatServeiID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou DocumentCedent
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearDocumentCedentPost(@ModelAttribute DocumentCedentForm documentCedentForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    DocumentCedentJPA documentCedent = documentCedentForm.getDocumentCedent();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, documentCedent, documentCedentForm); // FILE
      preValidate(request, documentCedentForm, result);
      getWebValidator().validate(documentCedentForm, result);
      postValidate(request,documentCedentForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        documentCedent = create(request, documentCedent);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", documentCedent.getDocumentCedentID());
        documentCedentForm.setDocumentCedent(documentCedent);
        return getRedirectWhenCreated(request, documentCedentForm);
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

  @RequestMapping(value = "/view/{documentCedentID}", method = RequestMethod.GET)
  public ModelAndView veureDocumentCedentGet(@PathVariable("documentCedentID") java.lang.Long documentCedentID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewDocumentCedentGet(documentCedentID,
        request, response, true);
  }


  protected ModelAndView editAndViewDocumentCedentGet(@PathVariable("documentCedentID") java.lang.Long documentCedentID,
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
    DocumentCedentJPA documentCedent = findByPrimaryKey(request, documentCedentID);

    if (documentCedent == null) {
      createMessageWarning(request, "error.notfound", documentCedentID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, documentCedentID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      DocumentCedentForm documentCedentForm = getDocumentCedentForm(documentCedent, __isView, request, mav);
      documentCedentForm.setView(__isView);
      if(__isView) {
        documentCedentForm.setAllFieldsReadOnly(ALL_DOCUMENTCEDENT_FIELDS);
        documentCedentForm.setSaveButtonVisible(false);
        documentCedentForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(documentCedentForm, request, mav);
      mav.addObject("documentCedentForm", documentCedentForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un DocumentCedent existent
   */
  @RequestMapping(value = "/{documentCedentID}/edit", method = RequestMethod.GET)
  public ModelAndView editarDocumentCedentGet(@PathVariable("documentCedentID") java.lang.Long documentCedentID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewDocumentCedentGet(documentCedentID,
        request, response, false);
  }



  /**
   * Editar un DocumentCedent existent
   */
  @RequestMapping(value = "/{documentCedentID}/edit", method = RequestMethod.POST)
  public String editarDocumentCedentPost(@ModelAttribute DocumentCedentForm documentCedentForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    DocumentCedentJPA documentCedent = documentCedentForm.getDocumentCedent();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, documentCedent, documentCedentForm); // FILE
      preValidate(request, documentCedentForm, result);
      getWebValidator().validate(documentCedentForm, result);
      postValidate(request, documentCedentForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        documentCedent = update(request, documentCedent);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", documentCedent.getDocumentCedentID());
        status.setComplete();
        return getRedirectWhenModified(request, documentCedentForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          documentCedent.getDocumentCedentID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, documentCedentForm, __e);
    }

  }


  /**
   * Eliminar un DocumentCedent existent
   */
  @RequestMapping(value = "/{documentCedentID}/delete")
  public String eliminarDocumentCedent(@PathVariable("documentCedentID") java.lang.Long documentCedentID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      DocumentCedent documentCedent = this.findByPrimaryKey(request, documentCedentID);
      if (documentCedent == null) {
        String __msg = createMessageError(request, "error.notfound", documentCedentID);
        return getRedirectWhenDelete(request, documentCedentID, new Exception(__msg));
      } else {
        delete(request, documentCedent);
        createMessageSuccess(request, "success.deleted", documentCedentID);
        return getRedirectWhenDelete(request, documentCedentID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", documentCedentID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, documentCedentID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute DocumentCedentFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarDocumentCedent(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __documentCedentID, Throwable e) {
    java.lang.Long documentCedentID = (java.lang.Long)__documentCedentID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (documentCedentID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(documentCedentID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "documentCedent.documentCedent";
  }

  public String getEntityNameCodePlural() {
    return "documentCedent.documentCedent.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("documentCedent.documentCedentID");
  }

  @InitBinder("documentCedentFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("documentCedentForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "documentCedent.documentCedentID");
  }

  public DocumentCedentWebValidator getWebValidator() {
    return documentCedentWebValidator;
  }


  public void setWebValidator(DocumentCedentWebValidator __val) {
    if (__val != null) {
      this.documentCedentWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de DocumentCedent
   */
  @RequestMapping(value = "/{documentCedentID}/cancel")
  public String cancelDocumentCedent(@PathVariable("documentCedentID") java.lang.Long documentCedentID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, documentCedentID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, DocumentCedent documentCedent,
      DocumentCedentForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerID(), form.isFitxerIDDelete(),
        form.isNou()? null : documentCedent.getFitxer());
    ((DocumentCedentJPA)documentCedent).setFitxer(f);
    if (f != null) { 
      documentCedent.setFitxerID(f.getFitxerID());
    } else {
      documentCedent.setFitxerID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(DocumentCedent documentCedent) {
    deleteFile(documentCedent.getFitxerID());
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


  public List<StringKeyValue> getReferenceListForEntitatServeiID(HttpServletRequest request,
       ModelAndView mav, DocumentCedentForm documentCedentForm, Where where)  throws I18NException {
    if (documentCedentForm.isHiddenField(ENTITATSERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (documentCedentForm.isReadOnlyField(ENTITATSERVEIID)) {
      _where = EntitatServeiFields.ENTITATSERVEIID.equal(documentCedentForm.getDocumentCedent().getEntitatServeiID());
    }
    return getReferenceListForEntitatServeiID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatServeiID(HttpServletRequest request,
       ModelAndView mav, DocumentCedentFilterForm documentCedentFilterForm,
       List<DocumentCedent> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (documentCedentFilterForm.isHiddenField(ENTITATSERVEIID)
       && !documentCedentFilterForm.isGroupByField(ENTITATSERVEIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATSERVEIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (DocumentCedent _item : list) {
        _pkList.add(_item.getEntitatServeiID());
        }
        _w = EntitatServeiFields.ENTITATSERVEIID.in(_pkList);
      }
    return getReferenceListForEntitatServeiID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatServeiID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatServeiRefList.getReferenceList(EntitatServeiFields.ENTITATSERVEIID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,DocumentCedentForm documentCedentForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,DocumentCedentForm documentCedentForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, DocumentCedentFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, DocumentCedentFilterForm filterForm,  List<DocumentCedent> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, DocumentCedentForm documentCedentForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, DocumentCedentForm documentCedentForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long documentCedentID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long documentCedentID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "documentCedentFormWebDB";
  }

  public String getTileList() {
    return "documentCedentListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "DocumentCedent_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public DocumentCedentJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long documentCedentID) throws I18NException {
    return (DocumentCedentJPA) documentCedentEjb.findByPrimaryKey(documentCedentID);
  }


  public DocumentCedentJPA create(HttpServletRequest request, DocumentCedentJPA documentCedent)
    throws I18NException, I18NValidationException {
    return (DocumentCedentJPA) documentCedentEjb.create(documentCedent);
  }


  public DocumentCedentJPA update(HttpServletRequest request, DocumentCedentJPA documentCedent)
    throws I18NException, I18NValidationException {
    return (DocumentCedentJPA) documentCedentEjb.update(documentCedent);
  }


  public void delete(HttpServletRequest request, DocumentCedent documentCedent) throws I18NException {
    documentCedentEjb.delete(documentCedent);
  }

} // Final de Classe

