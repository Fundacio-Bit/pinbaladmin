package org.fundaciobit.pinbaladmin.back.controller.webdb;

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
import org.fundaciobit.pinbaladmin.back.form.webdb.ContacteForm;

import org.fundaciobit.pinbaladmin.back.validator.webdb.ContacteWebValidator;

import org.fundaciobit.pinbaladmin.persistence.ContacteJPA;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pinbaladmin.back.utils.Tab;

/**
 * Controller per gestionar un Contacte
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="contacte.contacte.plural", order=30, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/contacte")
@SessionAttributes(types = { ContacteForm.class, ContacteFilterForm.class })
@Tile(name="contacteFormWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe
    contentJsp="/WEB-INF/jsp/webdb/contacteForm.jsp", type=TileType.WEBDB_FORM,
    attributes={ @TileAttribute(name="titol", value="contacte.contacte")})
@Tile(name="contacteListWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe 
    contentJsp="/WEB-INF/jsp/webdb/contacteList.jsp", type=TileType.WEBDB_LIST,
    attributes={ @TileAttribute(name="titol", value="contacte.contacte")})
public class ContacteController
    extends org.fundaciobit.pinbaladmin.back.controller.PinbalAdminBaseController<Contacte, java.lang.Long> implements ContacteFields {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ContacteService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ContacteService contacteEjb;

  @Autowired
  private ContacteWebValidator contacteWebValidator;

  @Autowired
  protected ContacteRefList contacteRefList;

  /**
   * Llistat de totes Contacte
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    ContacteFilterForm ff;
    ff = (ContacteFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Contacte de forma paginada
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
    llistat(mav, request, getContacteFilterForm(pagina, mav, request));
    return mav;
  }

  public ContacteFilterForm getContacteFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    ContacteFilterForm contacteFilterForm;
    contacteFilterForm = (ContacteFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(contacteFilterForm == null) {
      contacteFilterForm = new ContacteFilterForm();
      contacteFilterForm.setContexte(getContextWeb());
      contacteFilterForm.setEntityNameCode(getEntityNameCode());
      contacteFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      contacteFilterForm.setNou(true);
    } else {
      contacteFilterForm.setNou(false);
    }
    contacteFilterForm.setPage(pagina == null ? 1 : pagina);
    return contacteFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Contacte de forma paginada
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
      @ModelAttribute ContacteFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getContacteFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Contacte de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Contacte> llistat(ModelAndView mav, HttpServletRequest request,
     ContacteFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Contacte> contacte = processarLlistat(contacteEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("contacteItems", contacte);

    mav.addObject("contacteFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, contacte, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, contacte);

    return contacte;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(ContacteFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Contacte> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    ContacteFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Contacte> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_CONTACTE_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Contacte
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearContacteGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    ContacteForm contacteForm = getContacteForm(null, false, request, mav);
    mav.addObject("contacteForm" ,contacteForm);
    fillReferencesForForm(contacteForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public ContacteForm getContacteForm(ContacteJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    ContacteForm contacteForm;
    if(_jpa == null) {
      contacteForm = new ContacteForm(new ContacteJPA(), true);
    } else {
      contacteForm = new ContacteForm(_jpa, false);
      contacteForm.setView(__isView);
    }
    contacteForm.setContexte(getContextWeb());
    contacteForm.setEntityNameCode(getEntityNameCode());
    contacteForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return contacteForm;
  }

  public void fillReferencesForForm(ContacteForm contacteForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Contacte
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearContactePost(@ModelAttribute ContacteForm contacteForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ContacteJPA contacte = contacteForm.getContacte();

    try {
      preValidate(request, contacteForm, result);
      getWebValidator().validate(contacteForm, result);
      postValidate(request,contacteForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        contacte = create(request, contacte);
        createMessageSuccess(request, "success.creation", contacte.getContacteID());
        contacteForm.setContacte(contacte);
        return getRedirectWhenCreated(request, contacteForm);
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

  @RequestMapping(value = "/view/{contacteID}", method = RequestMethod.GET)
  public ModelAndView veureContacteGet(@PathVariable("contacteID") java.lang.Long contacteID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewContacteGet(contacteID,
        request, response, true);
  }


  protected ModelAndView editAndViewContacteGet(@PathVariable("contacteID") java.lang.Long contacteID,
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
    ContacteJPA contacte = findByPrimaryKey(request, contacteID);

    if (contacte == null) {
      createMessageWarning(request, "error.notfound", contacteID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      ContacteForm contacteForm = getContacteForm(contacte, __isView, request, mav);
      contacteForm.setView(__isView);
      if(__isView) {
        contacteForm.setAllFieldsReadOnly(ALL_CONTACTE_FIELDS);
        contacteForm.setSaveButtonVisible(false);
        contacteForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(contacteForm, request, mav);
      mav.addObject("contacteForm", contacteForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Contacte existent
   */
  @RequestMapping(value = "/{contacteID}/edit", method = RequestMethod.GET)
  public ModelAndView editarContacteGet(@PathVariable("contacteID") java.lang.Long contacteID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewContacteGet(contacteID,
        request, response, false);
  }



  /**
   * Editar un Contacte existent
   */
  @RequestMapping(value = "/{contacteID}/edit", method = RequestMethod.POST)
  public String editarContactePost(@ModelAttribute ContacteForm contacteForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ContacteJPA contacte = contacteForm.getContacte();

    try {
      preValidate(request, contacteForm, result);
      getWebValidator().validate(contacteForm, result);
      postValidate(request, contacteForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        contacte = update(request, contacte);
        createMessageSuccess(request, "success.modification", contacte.getContacteID());
        status.setComplete();
        return getRedirectWhenModified(request, contacteForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          contacte.getContacteID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, contacteForm, __e);
    }

  }


  /**
   * Eliminar un Contacte existent
   */
  @RequestMapping(value = "/{contacteID}/delete")
  public String eliminarContacte(@PathVariable("contacteID") java.lang.Long contacteID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Contacte contacte = this.findByPrimaryKey(request, contacteID);
      if (contacte == null) {
        String __msg = createMessageError(request, "error.notfound", contacteID);
        return getRedirectWhenDelete(request, contacteID, new Exception(__msg));
      } else {
        delete(request, contacte);
        createMessageSuccess(request, "success.deleted", contacteID);
        return getRedirectWhenDelete(request, contacteID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", contacteID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, contacteID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute ContacteFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarContacte(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __contacteID, Throwable e) {
    java.lang.Long contacteID = (java.lang.Long)__contacteID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (contacteID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(contacteID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "contacte.contacte";
  }

  public String getEntityNameCodePlural() {
    return "contacte.contacte.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("contacte.ContacteID");
  }

  @InitBinder("contacteFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("contacteForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "contacte.ContacteID");
  }

  public ContacteWebValidator getWebValidator() {
    return contacteWebValidator;
  }


  public void setWebValidator(ContacteWebValidator __val) {
    if (__val != null) {
      this.contacteWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Contacte
   */
  @RequestMapping(value = "/{contacteID}/cancel")
  public String cancelContacte(@PathVariable("contacteID") java.lang.Long contacteID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, contacteID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Contacte
   */
  @RequestMapping(value = "/cancel")
  public String cancelContacte(HttpServletRequest request,HttpServletResponse response) {
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

  public void preValidate(HttpServletRequest request,ContacteForm contacteForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,ContacteForm contacteForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, ContacteFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, ContacteFilterForm filterForm,  List<Contacte> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, ContacteForm contacteForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, ContacteForm contacteForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long contacteID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long contacteID) {
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
    return "contacteFormWebDB";
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
        return "contacteListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "Contacte_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public ContacteJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long contacteID) throws I18NException {
    return (ContacteJPA) contacteEjb.findByPrimaryKey(contacteID);
  }


  public ContacteJPA create(HttpServletRequest request, ContacteJPA contacte)
    throws I18NException, I18NValidationException {
    return (ContacteJPA) contacteEjb.create(contacte);
  }


  public ContacteJPA update(HttpServletRequest request, ContacteJPA contacte)
    throws I18NException, I18NValidationException {
    return (ContacteJPA) contacteEjb.update(contacte);
  }


  public void delete(HttpServletRequest request, Contacte contacte) throws I18NException {
    contacteEjb.delete(contacte);
  }

} // Final de Classe

