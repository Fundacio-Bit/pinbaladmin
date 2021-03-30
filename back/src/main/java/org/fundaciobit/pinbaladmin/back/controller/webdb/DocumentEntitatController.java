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
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentEntitatForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.DocumentEntitatWebValidator;

import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.jpa.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.jpa.DocumentEntitatJPA;
import org.fundaciobit.pinbaladmin.model.entity.DocumentEntitat;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un DocumentEntitat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/documentEntitat")
@SessionAttributes(types = { DocumentEntitatForm.class, DocumentEntitatFilterForm.class })
public class DocumentEntitatController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesBaseController<DocumentEntitat, java.lang.Long, DocumentEntitatForm> implements DocumentEntitatFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentEntitatLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentEntitatLocal documentEntitatEjb;

  @Autowired
  private DocumentEntitatWebValidator documentEntitatWebValidator;

  @Autowired
  protected DocumentEntitatRefList documentEntitatRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes DocumentEntitat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    DocumentEntitatFilterForm ff;
    ff = (DocumentEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar DocumentEntitat de forma paginada
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
    llistat(mav, request, getDocumentEntitatFilterForm(pagina, mav, request));
    return mav;
  }

  public DocumentEntitatFilterForm getDocumentEntitatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    DocumentEntitatFilterForm documentEntitatFilterForm;
    documentEntitatFilterForm = (DocumentEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(documentEntitatFilterForm == null) {
      documentEntitatFilterForm = new DocumentEntitatFilterForm();
      documentEntitatFilterForm.setContexte(getContextWeb());
      documentEntitatFilterForm.setEntityNameCode(getEntityNameCode());
      documentEntitatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      documentEntitatFilterForm.setNou(true);
    } else {
      documentEntitatFilterForm.setNou(false);
    }
    documentEntitatFilterForm.setPage(pagina == null ? 1 : pagina);
    return documentEntitatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar DocumentEntitat de forma paginada
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
      @ModelAttribute DocumentEntitatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getDocumentEntitatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de DocumentEntitat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<DocumentEntitat> llistat(ModelAndView mav, HttpServletRequest request,
     DocumentEntitatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<DocumentEntitat> documentEntitat = processarLlistat(documentEntitatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("documentEntitatItems", documentEntitat);

    mav.addObject("documentEntitatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, documentEntitat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, documentEntitat);

    return documentEntitat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(DocumentEntitatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<DocumentEntitat> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    DocumentEntitatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<DocumentEntitat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_DOCUMENTENTITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou DocumentEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearDocumentEntitatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    DocumentEntitatForm documentEntitatForm = getDocumentEntitatForm(null, false, request, mav);
    mav.addObject("documentEntitatForm" ,documentEntitatForm);
    fillReferencesForForm(documentEntitatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public DocumentEntitatForm getDocumentEntitatForm(DocumentEntitatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    DocumentEntitatForm documentEntitatForm;
    if(_jpa == null) {
      documentEntitatForm = new DocumentEntitatForm(new DocumentEntitatJPA(), true);
    } else {
      documentEntitatForm = new DocumentEntitatForm(_jpa, false);
      documentEntitatForm.setView(__isView);
    }
    documentEntitatForm.setContexte(getContextWeb());
    documentEntitatForm.setEntityNameCode(getEntityNameCode());
    documentEntitatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return documentEntitatForm;
  }

  public void fillReferencesForForm(DocumentEntitatForm documentEntitatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (documentEntitatForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, documentEntitatForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      documentEntitatForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou DocumentEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearDocumentEntitatPost(@ModelAttribute DocumentEntitatForm documentEntitatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    DocumentEntitatJPA documentEntitat = documentEntitatForm.getDocumentEntitat();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, documentEntitat, documentEntitatForm); // FILE
      preValidate(request, documentEntitatForm, result);
      getWebValidator().validate(documentEntitatForm, result);
      postValidate(request,documentEntitatForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        documentEntitat = create(request, documentEntitat);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", documentEntitat.getDocumentEntitatID());
        documentEntitatForm.setDocumentEntitat(documentEntitat);
        return getRedirectWhenCreated(request, documentEntitatForm);
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

  @RequestMapping(value = "/view/{documentEntitatID}", method = RequestMethod.GET)
  public ModelAndView veureDocumentEntitatGet(@PathVariable("documentEntitatID") java.lang.Long documentEntitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewDocumentEntitatGet(documentEntitatID,
        request, response, true);
  }


  protected ModelAndView editAndViewDocumentEntitatGet(@PathVariable("documentEntitatID") java.lang.Long documentEntitatID,
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
    DocumentEntitatJPA documentEntitat = findByPrimaryKey(request, documentEntitatID);

    if (documentEntitat == null) {
      createMessageWarning(request, "error.notfound", documentEntitatID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, documentEntitatID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      DocumentEntitatForm documentEntitatForm = getDocumentEntitatForm(documentEntitat, __isView, request, mav);
      documentEntitatForm.setView(__isView);
      if(__isView) {
        documentEntitatForm.setAllFieldsReadOnly(ALL_DOCUMENTENTITAT_FIELDS);
        documentEntitatForm.setSaveButtonVisible(false);
        documentEntitatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(documentEntitatForm, request, mav);
      mav.addObject("documentEntitatForm", documentEntitatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un DocumentEntitat existent
   */
  @RequestMapping(value = "/{documentEntitatID}/edit", method = RequestMethod.GET)
  public ModelAndView editarDocumentEntitatGet(@PathVariable("documentEntitatID") java.lang.Long documentEntitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewDocumentEntitatGet(documentEntitatID,
        request, response, false);
  }



  /**
   * Editar un DocumentEntitat existent
   */
  @RequestMapping(value = "/{documentEntitatID}/edit", method = RequestMethod.POST)
  public String editarDocumentEntitatPost(@ModelAttribute @Valid DocumentEntitatForm documentEntitatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    DocumentEntitatJPA documentEntitat = documentEntitatForm.getDocumentEntitat();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, documentEntitat, documentEntitatForm); // FILE
      preValidate(request, documentEntitatForm, result);
      getWebValidator().validate(documentEntitat, result);
      postValidate(request, documentEntitatForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        documentEntitat = update(request, documentEntitat);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", documentEntitat.getDocumentEntitatID());
        status.setComplete();
        return getRedirectWhenModified(request, documentEntitatForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          documentEntitat.getDocumentEntitatID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, documentEntitatForm, __e);
    }

  }


  /**
   * Eliminar un DocumentEntitat existent
   */
  @RequestMapping(value = "/{documentEntitatID}/delete")
  public String eliminarDocumentEntitat(@PathVariable("documentEntitatID") java.lang.Long documentEntitatID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      DocumentEntitat documentEntitat = documentEntitatEjb.findByPrimaryKey(documentEntitatID);
      if (documentEntitat == null) {
        String __msg =createMessageError(request, "error.notfound", documentEntitatID);
        return getRedirectWhenDelete(request, documentEntitatID, new Exception(__msg));
      } else {
        delete(request, documentEntitat);
        createMessageSuccess(request, "success.deleted", documentEntitatID);
        return getRedirectWhenDelete(request, documentEntitatID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", documentEntitatID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, documentEntitatID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute DocumentEntitatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarDocumentEntitat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __documentEntitatID, Throwable e) {
    java.lang.Long documentEntitatID = (java.lang.Long)__documentEntitatID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (documentEntitatID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(documentEntitatID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "documentEntitat.documentEntitat";
  }

  public String getEntityNameCodePlural() {
    return "documentEntitat.documentEntitat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("documentEntitat.documentEntitatID");
  }

  @InitBinder("documentEntitatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("documentEntitatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("documentEntitatID");

  }

  public DocumentEntitatWebValidator getWebValidator() {
    return documentEntitatWebValidator;
  }


  public void setWebValidator(DocumentEntitatWebValidator __val) {
    if (__val != null) {
      this.documentEntitatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de DocumentEntitat
   */
  @RequestMapping(value = "/{documentEntitatID}/cancel")
  public String cancelDocumentEntitat(@PathVariable("documentEntitatID") java.lang.Long documentEntitatID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, documentEntitatID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, DocumentEntitat documentEntitat,
      DocumentEntitatForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerID(), form.isFitxerIDDelete(),
        form.isNou()? null : documentEntitat.getFitxer());
    ((DocumentEntitatJPA)documentEntitat).setFitxer(f);
    if (f != null) { 
      documentEntitat.setFitxerID(f.getFitxerID());
    } else {
      documentEntitat.setFitxerID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(DocumentEntitat documentEntitat) {
    deleteFile(documentEntitat.getFitxerID());
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


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, DocumentEntitatForm documentEntitatForm, Where where)  throws I18NException {
    if (documentEntitatForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (documentEntitatForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(documentEntitatForm.getDocumentEntitat().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, DocumentEntitatFilterForm documentEntitatFilterForm,
       List<DocumentEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (documentEntitatFilterForm.isHiddenField(ENTITATID)
      && !documentEntitatFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (DocumentEntitat _item : list) {
        _pkList.add(_item.getEntitatID());
        }
        _w = EntitatFields.ENTITATID.in(_pkList);
      }
    return getReferenceListForEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where );
  }


  public void preValidate(HttpServletRequest request,DocumentEntitatForm documentEntitatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,DocumentEntitatForm documentEntitatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, DocumentEntitatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, DocumentEntitatFilterForm filterForm,  List<DocumentEntitat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, DocumentEntitatForm documentEntitatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, DocumentEntitatForm documentEntitatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long documentEntitatID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long documentEntitatID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "documentEntitatFormWebDB";
  }

  public String getTileList() {
    return "documentEntitatListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "DocumentEntitatWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public DocumentEntitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long documentEntitatID) throws I18NException {
    return (DocumentEntitatJPA) documentEntitatEjb.findByPrimaryKey(documentEntitatID);
  }


  public DocumentEntitatJPA create(HttpServletRequest request, DocumentEntitatJPA documentEntitat)
    throws Exception,I18NException, I18NValidationException {
    return (DocumentEntitatJPA) documentEntitatEjb.create(documentEntitat);
  }


  public DocumentEntitatJPA update(HttpServletRequest request, DocumentEntitatJPA documentEntitat)
    throws Exception,I18NException, I18NValidationException {
    return (DocumentEntitatJPA) documentEntitatEjb.update(documentEntitat);
  }


  public void delete(HttpServletRequest request, DocumentEntitat documentEntitat) throws Exception,I18NException {
    documentEntitatEjb.delete(documentEntitat);
  }

} // Final de Classe

