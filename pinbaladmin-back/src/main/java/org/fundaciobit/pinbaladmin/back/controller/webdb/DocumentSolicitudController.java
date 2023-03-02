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
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentSolicitudForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.DocumentSolicitudWebValidator;

import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.pinbaladmin.model.fields.*;

/**
 * Controller per gestionar un DocumentSolicitud
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/documentSolicitud")
@SessionAttributes(types = { DocumentSolicitudForm.class, DocumentSolicitudFilterForm.class })
public class DocumentSolicitudController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<DocumentSolicitud, java.lang.Long> implements DocumentSolicitudFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService documentSolicitudEjb;

  @Autowired
  private DocumentSolicitudWebValidator documentSolicitudWebValidator;

  @Autowired
  protected DocumentSolicitudRefList documentSolicitudRefList;

  // References 
  @Autowired
  protected DocumentRefList documentRefList;

  // References 
  @Autowired
  protected SolicitudRefList solicitudRefList;

  /**
   * Llistat de totes DocumentSolicitud
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    DocumentSolicitudFilterForm ff;
    ff = (DocumentSolicitudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar DocumentSolicitud de forma paginada
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
    llistat(mav, request, getDocumentSolicitudFilterForm(pagina, mav, request));
    return mav;
  }

  public DocumentSolicitudFilterForm getDocumentSolicitudFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    DocumentSolicitudFilterForm documentSolicitudFilterForm;
    documentSolicitudFilterForm = (DocumentSolicitudFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(documentSolicitudFilterForm == null) {
      documentSolicitudFilterForm = new DocumentSolicitudFilterForm();
      documentSolicitudFilterForm.setContexte(getContextWeb());
      documentSolicitudFilterForm.setEntityNameCode(getEntityNameCode());
      documentSolicitudFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      documentSolicitudFilterForm.setNou(true);
    } else {
      documentSolicitudFilterForm.setNou(false);
    }
    documentSolicitudFilterForm.setPage(pagina == null ? 1 : pagina);
    return documentSolicitudFilterForm;
  }

  /**
   * Segona i següent peticions per llistar DocumentSolicitud de forma paginada
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
      @ModelAttribute DocumentSolicitudFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getDocumentSolicitudFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de DocumentSolicitud de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<DocumentSolicitud> llistat(ModelAndView mav, HttpServletRequest request,
     DocumentSolicitudFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<DocumentSolicitud> documentSolicitud = processarLlistat(documentSolicitudEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("documentSolicitudItems", documentSolicitud);

    mav.addObject("documentSolicitudFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, documentSolicitud, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, documentSolicitud);

    return documentSolicitud;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(DocumentSolicitudFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<DocumentSolicitud> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field documentID
    {
      _listSKV = getReferenceListForDocumentID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfDocumentForDocumentID(_tmp);
      if (filterForm.getGroupByFields().contains(DOCUMENTID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, DOCUMENTID, false);
      };
    }

    // Field solicitudID
    {
      _listSKV = getReferenceListForSolicitudID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfSolicitudForSolicitudID(_tmp);
      if (filterForm.getGroupByFields().contains(SOLICITUDID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SOLICITUDID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    DocumentSolicitudFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<DocumentSolicitud> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_DOCUMENTSOLICITUD_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(DOCUMENTID, filterForm.getMapOfDocumentForDocumentID());
    __mapping.put(SOLICITUDID, filterForm.getMapOfSolicitudForSolicitudID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou DocumentSolicitud
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearDocumentSolicitudGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    DocumentSolicitudForm documentSolicitudForm = getDocumentSolicitudForm(null, false, request, mav);
    mav.addObject("documentSolicitudForm" ,documentSolicitudForm);
    fillReferencesForForm(documentSolicitudForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public DocumentSolicitudForm getDocumentSolicitudForm(DocumentSolicitudJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    DocumentSolicitudForm documentSolicitudForm;
    if(_jpa == null) {
      documentSolicitudForm = new DocumentSolicitudForm(new DocumentSolicitudJPA(), true);
    } else {
      documentSolicitudForm = new DocumentSolicitudForm(_jpa, false);
      documentSolicitudForm.setView(__isView);
    }
    documentSolicitudForm.setContexte(getContextWeb());
    documentSolicitudForm.setEntityNameCode(getEntityNameCode());
    documentSolicitudForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return documentSolicitudForm;
  }

  public void fillReferencesForForm(DocumentSolicitudForm documentSolicitudForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (documentSolicitudForm.getListOfDocumentForDocumentID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForDocumentID(request, mav, documentSolicitudForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      documentSolicitudForm.setListOfDocumentForDocumentID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (documentSolicitudForm.getListOfSolicitudForSolicitudID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSolicitudID(request, mav, documentSolicitudForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      documentSolicitudForm.setListOfSolicitudForSolicitudID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou DocumentSolicitud
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearDocumentSolicitudPost(@ModelAttribute DocumentSolicitudForm documentSolicitudForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    DocumentSolicitudJPA documentSolicitud = documentSolicitudForm.getDocumentSolicitud();

    try {
      preValidate(request, documentSolicitudForm, result);
      getWebValidator().validate(documentSolicitudForm, result);
      postValidate(request,documentSolicitudForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        documentSolicitud = create(request, documentSolicitud);
        createMessageSuccess(request, "success.creation", documentSolicitud.getDocumentSolicitudID());
        documentSolicitudForm.setDocumentSolicitud(documentSolicitud);
        return getRedirectWhenCreated(request, documentSolicitudForm);
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

  @RequestMapping(value = "/view/{documentSolicitudID}", method = RequestMethod.GET)
  public ModelAndView veureDocumentSolicitudGet(@PathVariable("documentSolicitudID") java.lang.Long documentSolicitudID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewDocumentSolicitudGet(documentSolicitudID,
        request, response, true);
  }


  protected ModelAndView editAndViewDocumentSolicitudGet(@PathVariable("documentSolicitudID") java.lang.Long documentSolicitudID,
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
    DocumentSolicitudJPA documentSolicitud = findByPrimaryKey(request, documentSolicitudID);

    if (documentSolicitud == null) {
      createMessageWarning(request, "error.notfound", documentSolicitudID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, documentSolicitudID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      DocumentSolicitudForm documentSolicitudForm = getDocumentSolicitudForm(documentSolicitud, __isView, request, mav);
      documentSolicitudForm.setView(__isView);
      if(__isView) {
        documentSolicitudForm.setAllFieldsReadOnly(ALL_DOCUMENTSOLICITUD_FIELDS);
        documentSolicitudForm.setSaveButtonVisible(false);
        documentSolicitudForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(documentSolicitudForm, request, mav);
      mav.addObject("documentSolicitudForm", documentSolicitudForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un DocumentSolicitud existent
   */
  @RequestMapping(value = "/{documentSolicitudID}/edit", method = RequestMethod.GET)
  public ModelAndView editarDocumentSolicitudGet(@PathVariable("documentSolicitudID") java.lang.Long documentSolicitudID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewDocumentSolicitudGet(documentSolicitudID,
        request, response, false);
  }



  /**
   * Editar un DocumentSolicitud existent
   */
  @RequestMapping(value = "/{documentSolicitudID}/edit", method = RequestMethod.POST)
  public String editarDocumentSolicitudPost(@ModelAttribute DocumentSolicitudForm documentSolicitudForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    DocumentSolicitudJPA documentSolicitud = documentSolicitudForm.getDocumentSolicitud();

    try {
      preValidate(request, documentSolicitudForm, result);
      getWebValidator().validate(documentSolicitudForm, result);
      postValidate(request, documentSolicitudForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        documentSolicitud = update(request, documentSolicitud);
        createMessageSuccess(request, "success.modification", documentSolicitud.getDocumentSolicitudID());
        status.setComplete();
        return getRedirectWhenModified(request, documentSolicitudForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          documentSolicitud.getDocumentSolicitudID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, documentSolicitudForm, __e);
    }

  }


  /**
   * Eliminar un DocumentSolicitud existent
   */
  @RequestMapping(value = "/{documentSolicitudID}/delete")
  public String eliminarDocumentSolicitud(@PathVariable("documentSolicitudID") java.lang.Long documentSolicitudID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      DocumentSolicitud documentSolicitud = documentSolicitudEjb.findByPrimaryKey(documentSolicitudID);
      if (documentSolicitud == null) {
        String __msg =createMessageError(request, "error.notfound", documentSolicitudID);
        return getRedirectWhenDelete(request, documentSolicitudID, new Exception(__msg));
      } else {
        delete(request, documentSolicitud);
        createMessageSuccess(request, "success.deleted", documentSolicitudID);
        return getRedirectWhenDelete(request, documentSolicitudID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", documentSolicitudID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, documentSolicitudID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute DocumentSolicitudFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarDocumentSolicitud(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __documentSolicitudID, Throwable e) {
    java.lang.Long documentSolicitudID = (java.lang.Long)__documentSolicitudID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (documentSolicitudID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(documentSolicitudID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "documentSolicitud.documentSolicitud";
  }

  public String getEntityNameCodePlural() {
    return "documentSolicitud.documentSolicitud.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("documentSolicitud.documentSolicitudID");
  }

  @InitBinder("documentSolicitudFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("documentSolicitudForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "documentSolicitud.documentSolicitudID");
  }

  public DocumentSolicitudWebValidator getWebValidator() {
    return documentSolicitudWebValidator;
  }


  public void setWebValidator(DocumentSolicitudWebValidator __val) {
    if (__val != null) {
      this.documentSolicitudWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de DocumentSolicitud
   */
  @RequestMapping(value = "/{documentSolicitudID}/cancel")
  public String cancelDocumentSolicitud(@PathVariable("documentSolicitudID") java.lang.Long documentSolicitudID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, documentSolicitudID);
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


  public List<StringKeyValue> getReferenceListForDocumentID(HttpServletRequest request,
       ModelAndView mav, DocumentSolicitudForm documentSolicitudForm, Where where)  throws I18NException {
    if (documentSolicitudForm.isHiddenField(DOCUMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (documentSolicitudForm.isReadOnlyField(DOCUMENTID)) {
      _where = DocumentFields.DOCUMENTID.equal(documentSolicitudForm.getDocumentSolicitud().getDocumentID());
    }
    return getReferenceListForDocumentID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForDocumentID(HttpServletRequest request,
       ModelAndView mav, DocumentSolicitudFilterForm documentSolicitudFilterForm,
       List<DocumentSolicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (documentSolicitudFilterForm.isHiddenField(DOCUMENTID)
      && !documentSolicitudFilterForm.isGroupByField(DOCUMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(DOCUMENTID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (DocumentSolicitud _item : list) {
        _pkList.add(_item.getDocumentID());
        }
        _w = DocumentFields.DOCUMENTID.in(_pkList);
      }
    return getReferenceListForDocumentID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForDocumentID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return documentRefList.getReferenceList(DocumentFields.DOCUMENTID, where );
  }


  public List<StringKeyValue> getReferenceListForSolicitudID(HttpServletRequest request,
       ModelAndView mav, DocumentSolicitudForm documentSolicitudForm, Where where)  throws I18NException {
    if (documentSolicitudForm.isHiddenField(SOLICITUDID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (documentSolicitudForm.isReadOnlyField(SOLICITUDID)) {
      _where = SolicitudFields.SOLICITUDID.equal(documentSolicitudForm.getDocumentSolicitud().getSolicitudID());
    }
    return getReferenceListForSolicitudID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSolicitudID(HttpServletRequest request,
       ModelAndView mav, DocumentSolicitudFilterForm documentSolicitudFilterForm,
       List<DocumentSolicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (documentSolicitudFilterForm.isHiddenField(SOLICITUDID)
      && !documentSolicitudFilterForm.isGroupByField(SOLICITUDID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SOLICITUDID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (DocumentSolicitud _item : list) {
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,DocumentSolicitudForm documentSolicitudForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,DocumentSolicitudForm documentSolicitudForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, DocumentSolicitudFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, DocumentSolicitudFilterForm filterForm,  List<DocumentSolicitud> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, DocumentSolicitudForm documentSolicitudForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, DocumentSolicitudForm documentSolicitudForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long documentSolicitudID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long documentSolicitudID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "documentSolicitudFormWebDB";
  }

  public String getTileList() {
    return "documentSolicitudListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "DocumentSolicitudWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public DocumentSolicitudJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long documentSolicitudID) throws I18NException {
    return (DocumentSolicitudJPA) documentSolicitudEjb.findByPrimaryKey(documentSolicitudID);
  }


  public DocumentSolicitudJPA create(HttpServletRequest request, DocumentSolicitudJPA documentSolicitud)
    throws I18NException, I18NValidationException {
    return (DocumentSolicitudJPA) documentSolicitudEjb.create(documentSolicitud);
  }


  public DocumentSolicitudJPA update(HttpServletRequest request, DocumentSolicitudJPA documentSolicitud)
    throws I18NException, I18NValidationException {
    return (DocumentSolicitudJPA) documentSolicitudEjb.update(documentSolicitud);
  }


  public void delete(HttpServletRequest request, DocumentSolicitud documentSolicitud) throws I18NException {
    documentSolicitudEjb.delete(documentSolicitud);
  }

} // Final de Classe

