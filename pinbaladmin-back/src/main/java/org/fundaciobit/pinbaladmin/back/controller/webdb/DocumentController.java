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
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.DocumentWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un Document
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/document")
@SessionAttributes(types = { DocumentForm.class, DocumentFilterForm.class })
public class DocumentController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<Document, java.lang.Long, DocumentForm> implements DocumentFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentService documentEjb;

  @Autowired
  private DocumentWebValidator documentWebValidator;

  @Autowired
  protected DocumentRefList documentRefList;

  /**
   * Llistat de totes Document
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    DocumentFilterForm ff;
    ff = (DocumentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Document de forma paginada
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
    llistat(mav, request, getDocumentFilterForm(pagina, mav, request));
    return mav;
  }

  public DocumentFilterForm getDocumentFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    DocumentFilterForm documentFilterForm;
    documentFilterForm = (DocumentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(documentFilterForm == null) {
      documentFilterForm = new DocumentFilterForm();
      documentFilterForm.setContexte(getContextWeb());
      documentFilterForm.setEntityNameCode(getEntityNameCode());
      documentFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      documentFilterForm.setNou(true);
    } else {
      documentFilterForm.setNou(false);
    }
    documentFilterForm.setPage(pagina == null ? 1 : pagina);
    return documentFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Document de forma paginada
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
      @ModelAttribute DocumentFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getDocumentFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Document de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Document> llistat(ModelAndView mav, HttpServletRequest request,
     DocumentFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Document> document = processarLlistat(documentEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("documentItems", document);

    mav.addObject("documentFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, document, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, document);

    return document;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(DocumentFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Document> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field tipus
    {
      _listSKV = getReferenceListForTipus(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipus(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUS, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    DocumentFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Document> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_DOCUMENT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Document
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearDocumentGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    DocumentForm documentForm = getDocumentForm(null, false, request, mav);
    mav.addObject("documentForm" ,documentForm);
    fillReferencesForForm(documentForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public DocumentForm getDocumentForm(DocumentJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    DocumentForm documentForm;
    if(_jpa == null) {
      documentForm = new DocumentForm(new DocumentJPA(), true);
    } else {
      documentForm = new DocumentForm(_jpa, false);
      documentForm.setView(__isView);
    }
    documentForm.setContexte(getContextWeb());
    documentForm.setEntityNameCode(getEntityNameCode());
    documentForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return documentForm;
  }

  public void fillReferencesForForm(DocumentForm documentForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (documentForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, documentForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      documentForm.setListOfValuesForTipus(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Document
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearDocumentPost(@ModelAttribute DocumentForm documentForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    DocumentJPA document = documentForm.getDocument();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, document, documentForm); // FILE
      preValidate(request, documentForm, result);
      getWebValidator().validate(documentForm, result);
      postValidate(request,documentForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        document = create(request, document);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", document.getDocumentID());
        documentForm.setDocument(document);
        return getRedirectWhenCreated(request, documentForm);
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

  @RequestMapping(value = "/view/{documentID}", method = RequestMethod.GET)
  public ModelAndView veureDocumentGet(@PathVariable("documentID") java.lang.Long documentID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewDocumentGet(documentID,
        request, response, true);
  }


  protected ModelAndView editAndViewDocumentGet(@PathVariable("documentID") java.lang.Long documentID,
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
    DocumentJPA document = findByPrimaryKey(request, documentID);

    if (document == null) {
      createMessageWarning(request, "error.notfound", documentID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, documentID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      DocumentForm documentForm = getDocumentForm(document, __isView, request, mav);
      documentForm.setView(__isView);
      if(__isView) {
        documentForm.setAllFieldsReadOnly(ALL_DOCUMENT_FIELDS);
        documentForm.setSaveButtonVisible(false);
        documentForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(documentForm, request, mav);
      mav.addObject("documentForm", documentForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Document existent
   */
  @RequestMapping(value = "/{documentID}/edit", method = RequestMethod.GET)
  public ModelAndView editarDocumentGet(@PathVariable("documentID") java.lang.Long documentID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewDocumentGet(documentID,
        request, response, false);
  }



  /**
   * Editar un Document existent
   */
  @RequestMapping(value = "/{documentID}/edit", method = RequestMethod.POST)
  public String editarDocumentPost(@ModelAttribute DocumentForm documentForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    DocumentJPA document = documentForm.getDocument();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, document, documentForm); // FILE
      preValidate(request, documentForm, result);
      getWebValidator().validate(documentForm, result);
      postValidate(request, documentForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        document = update(request, document);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", document.getDocumentID());
        status.setComplete();
        return getRedirectWhenModified(request, documentForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          document.getDocumentID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, documentForm, __e);
    }

  }


  /**
   * Eliminar un Document existent
   */
  @RequestMapping(value = "/{documentID}/delete")
  public String eliminarDocument(@PathVariable("documentID") java.lang.Long documentID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Document document = this.findByPrimaryKey(request, documentID);
      if (document == null) {
        String __msg = createMessageError(request, "error.notfound", documentID);
        return getRedirectWhenDelete(request, documentID, new Exception(__msg));
      } else {
        delete(request, document);
        createMessageSuccess(request, "success.deleted", documentID);
        return getRedirectWhenDelete(request, documentID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", documentID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, documentID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute DocumentFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarDocument(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __documentID, Throwable e) {
    java.lang.Long documentID = (java.lang.Long)__documentID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (documentID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(documentID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "document.document";
  }

  public String getEntityNameCodePlural() {
    return "document.document.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("document.documentID");
  }

  @InitBinder("documentFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("documentForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "document.documentID");
  }

  public DocumentWebValidator getWebValidator() {
    return documentWebValidator;
  }


  public void setWebValidator(DocumentWebValidator __val) {
    if (__val != null) {
      this.documentWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Document
   */
  @RequestMapping(value = "/{documentID}/cancel")
  public String cancelDocument(@PathVariable("documentID") java.lang.Long documentID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, documentID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Document document,
      DocumentForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerOriginalID(), form.isFitxerOriginalIDDelete(),
        form.isNou()? null : document.getFitxerOriginal());
    ((DocumentJPA)document).setFitxerOriginal(f);
    if (f != null) { 
      document.setFitxerOriginalID(f.getFitxerID());
    } else {
      document.setFitxerOriginalID(0);
    }

    f = (FitxerJPA)afm.preProcessFile(form.getFitxerFirmatID(), form.isFitxerFirmatIDDelete(),
        form.isNou()? null : document.getFitxerFirmat());
    ((DocumentJPA)document).setFitxerFirmat(f);
    if (f != null) { 
      document.setFitxerFirmatID(f.getFitxerID());
    } else {
      document.setFitxerFirmatID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(Document document) {
    deleteFile(document.getFitxerOriginalID());
    deleteFile(document.getFitxerFirmatID());
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


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, DocumentForm documentForm, Where where)  throws I18NException {
    if (documentForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, DocumentFilterForm documentFilterForm,
       List<Document> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (documentFilterForm.isHiddenField(TIPUS)
       && !documentFilterForm.isGroupByField(TIPUS)
       && !documentFilterForm.isFilterByField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipus(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
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

  public void preValidate(HttpServletRequest request,DocumentForm documentForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,DocumentForm documentForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, DocumentFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, DocumentFilterForm filterForm,  List<Document> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, DocumentForm documentForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, DocumentForm documentForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long documentID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long documentID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "documentFormWebDB";
  }

  public String getTileList() {
    return "documentListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Document_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public DocumentJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long documentID) throws I18NException {
    return (DocumentJPA) documentEjb.findByPrimaryKey(documentID);
  }


  public DocumentJPA create(HttpServletRequest request, DocumentJPA document)
    throws I18NException, I18NValidationException {
    return (DocumentJPA) documentEjb.create(document);
  }


  public DocumentJPA update(HttpServletRequest request, DocumentJPA document)
    throws I18NException, I18NValidationException {
    return (DocumentJPA) documentEjb.update(document);
  }


  public void delete(HttpServletRequest request, Document document) throws I18NException {
    documentEjb.delete(document);
  }

} // Final de Classe

