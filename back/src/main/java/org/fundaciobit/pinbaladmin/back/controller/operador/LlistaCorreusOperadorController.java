package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.EmailController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EmailFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EmailForm;
import org.fundaciobit.pinbaladmin.back.utils.email.EmailReader;
import org.fundaciobit.pinbaladmin.jpa.EmailJPA;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaLocal;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaLocal;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailAttachmentInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.model.entity.Email;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.fields.EmailFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/llistacorreus")
@SessionAttributes(types = { EmailForm.class, EmailFilterForm.class })
public class LlistaCorreusOperadorController extends EmailController {

  public static final String CACHE_DE_EMAILS_LLEGITS = "__CACHE_DE_EMAILS_LLEGITS__";

  public static final int MISSATGE2 = 1;

  public static final int ATTACHMENTS = 2;

  @EJB(mappedName = IncidenciaTecnicaLogicaLocal.JNDI_NAME)
  protected IncidenciaTecnicaLogicaLocal incidenciaTecnicaLogicaEjb;
  
  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ServeiLocal serveiEjb;


  @EJB(mappedName = SolicitudLogicaLocal.JNDI_NAME)
  protected SolicitudLogicaLocal solicitudLogicaEjb;

  @Override
  public String getTileForm() {
    return "emailFormOperador";
  }

  @Override
  public String getTileList() {
    return "emailListOperador";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "LlistatCorreus_FilterForm";
  }

  @Override
  public EmailFilterForm getEmailFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    EmailFilterForm emailFilterForm = super.getEmailFilterForm(pagina, mav, request);

    // TODO falten botons laterals
    if (emailFilterForm.isNou()) {

      emailFilterForm.setAddButtonVisible(false);
      emailFilterForm.setEditButtonVisible(false);
      emailFilterForm.setDeleteSelectedButtonVisible(false);
      emailFilterForm.setVisibleMultipleSelection(false);

      emailFilterForm.setGroupByFields(new ArrayList<Field<?>>());
      emailFilterForm.setFilterByFields(new ArrayList<Field<?>>());

      emailFilterForm.addHiddenField(DESTINATARIS);
      emailFilterForm.addHiddenField(MESSAGE);

      emailFilterForm.setItemsPerPage(-1);
      emailFilterForm.setAllItemsPerPage(new int[] { -1 });
      
      emailFilterForm.setActionsRenderer(EmailFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);

      emailFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-warning-sign",
          "incidencia.convertir", getContextWeb() + "/incidencia/{0}", "btn-warning"));

      // Afegir boto per Solicitud
      emailFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-list-alt",
          "solicitud.convertir", getContextWeb() + "/solicitud/{0}", "btn-success"));

      {
        AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
        adfield4.setCodeName(EmailFields.MESSAGE.fullName);
        adfield4.setPosition(MISSATGE2);
        // Els valors s'ompliran al mètode postList()
        adfield4.setValueMap(new HashMap<Long, String>());
        adfield4.setEscapeXml(false);

        emailFilterForm.addAdditionalField(adfield4);
      }

      {
        AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
        adfield4.setCodeName("arxiu");
        adfield4.setPosition(ATTACHMENTS);
        // Els valors s'ompliran al mètode postList()
        adfield4.setValueMap(new HashMap<Long, String>());
        adfield4.setEscapeXml(false);

        emailFilterForm.addAdditionalField(adfield4);
      }

    }

    return emailFilterForm;

  }

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      EmailFilterForm filterForm, List<Email> list) throws I18NException {

    Map<Long, String> map;
    map = (Map<Long, String>) filterForm.getAdditionalField(MISSATGE2).getValueMap();
    map.clear();

    Map<Long, String> mapAttach;
    mapAttach = (Map<Long, String>) filterForm.getAdditionalField(ATTACHMENTS).getValueMap();
    mapAttach.clear();

    Map<Long, EmailMessageInfo> cache = (Map<Long, EmailMessageInfo>) request.getSession()
        .getAttribute(CACHE_DE_EMAILS_LLEGITS);

    for (Email email : list) {

      if (email.getMessage() != null) {
        String msg = email.getMessage().trim();

        if (msg.startsWith("<") && msg.endsWith(">")) {
          map.put(email.getEmailID(), "<div>" + msg + "</div>");
        } else {
          map.put(email.getEmailID(), "<textarea readonly  style=\"width:auto;\""
              + computeRowsCols(msg) + ">" + msg + "</textarea>");
        }
      }

      EmailMessageInfo emi = cache.get(email.getEmailID());

      if (emi.getAttachments() != null) {
        StringBuffer str = new StringBuffer();

        for (EmailAttachmentInfo ads : emi.getAttachments()) {

          str.append(" * " + ads.getFileName() + " (" + ads.getData().length + " bytes" + ")")
              .append("<br/>");

        }

        mapAttach.put(email.getEmailID(), str.toString());

      }

      // if (emi.getSubject().indexOf("[PID]") != -1)
      {

      }

    }

  }

  protected String computeRowsCols(String msg) {
    int rows = 1;
    int cols = 1;
    if (msg != null) {
      String[] lines = msg.split("\n");
      rows = lines.length + 1;
      for (int i = 0; i < lines.length; i++) {
        cols = Math.max(cols, lines[i].length());
        if (cols > 80) {
          cols = 80;
          break;
        }
      }
    }

    return " rows=\"" + rows + "\" cols=\"" + cols + "\" ";

  }

  @RequestMapping(value = "/incidencia/{emailID}", method = RequestMethod.GET)
  public String incidencia(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long emailID) {

    try {

      Map<Long, EmailMessageInfo> cache = (Map<Long, EmailMessageInfo>) request.getSession()
          .getAttribute(CACHE_DE_EMAILS_LLEGITS);

      final boolean enableCertificationCheck = false;
      EmailReader er = new EmailReader(System.getProperties(), enableCertificationCheck);

      if (er.getCountMessages() == cache.size()) {

        EmailMessageInfo emi = cache.get(emailID);

        IncidenciaTecnica it = incidenciaTecnicaLogicaEjb.createFromEmail(emi,
            request.getRemoteUser());

        er.deleteMessage((int) (long) emailID);

        return "redirect:" + IncidenciaTecnicaOperadorController.WEBCONTEXT + "/"
            + it.getIncidenciaTecnicaID() + "/edit";

      } else {
        HtmlUtils.saveMessageWarning(request, "Ha rebut altres correus. Torni a intentar-ho.");
      }
    } catch (I18NException e) {
      String msg = I18NUtils.getMessage(e);
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);

    } catch (Exception e) {
      String msg = "Error convertint a incidència: " + e.getMessage();
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
    }

    return "redirect:" + getContextWeb() + "/list";

  }

  @RequestMapping(value = "/solicitud/{emailID}", method = RequestMethod.GET)
  public String solicitud(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long emailID) {

    try {

      long start = System.currentTimeMillis();
      Map<Long, EmailMessageInfo> cache = (Map<Long, EmailMessageInfo>) request.getSession()
          .getAttribute(CACHE_DE_EMAILS_LLEGITS);

      final boolean enableCertificationCheck = false;
      EmailReader er = new EmailReader(System.getProperties(), enableCertificationCheck);

      if (er.getCountMessages() == cache.size()) {

        EmailMessageInfo emi = cache.get(emailID);

        // IncidenciaTecnica it =
        // incidenciaTecnicaLogicaEjb.createFromEmail(emi,
        // request.getRemoteUser());

        String fileName = emi.getSubject();

        SolicitudEstatalDesDeFitxersMultiplesOperadorController.crearSolicitudsDesDeEmail(
            request, emi, fileName, log, serveiEjb, solicitudLogicaEjb);

        er.deleteMessage((int) (long) emailID);
        
        long end = System.currentTimeMillis();

        return "redirect:/operador/solicitudestatal/list/" + start + "/" + end;

      } else {
        HtmlUtils.saveMessageWarning(request, "Ha rebut altres correus. Torni a intentar-ho.");
      }
/*    } catch (I18NException e) {
      String msg = I18NUtils.getMessage(e);
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
*/
    } catch (Exception e) {
      String msg = "Error convertint a solicitud: " + e.getMessage();
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
    }

    return "redirect:" + getContextWeb() + "/list";

  }

  @Override
  public List<Email> executeSelect(ITableManager<Email, Long> ejb, Where where,
      final OrderBy[] orderBy, final Integer itemsPerPage, final int inici)
      throws I18NException {

    List<Email> list = new ArrayList<Email>();

    Map<Long, EmailMessageInfo> cache = new HashMap<Long, EmailMessageInfo>();

    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getRequest();

    try {

      final boolean enableCertificationCheck = false;
      EmailReader er = new EmailReader(System.getProperties(), enableCertificationCheck);

      List<EmailMessageInfo> emails = er.list();

      for (EmailMessageInfo emi : emails) {

        EmailJPA e = message2email(emi);

        list.add(e);

        cache.put((long) emi.getNumber(), emi);

      }

    } catch (Exception e) {

      String msg = "Error llistant correus: " + e.getMessage();

      log.error(msg, e);

      HtmlUtils.saveMessageError(request, msg);
    }

    request.getSession().setAttribute(CACHE_DE_EMAILS_LLEGITS, cache);
    return list;

  }

  private EmailJPA message2email(EmailMessageInfo emi) {
    EmailJPA e;
    long emailID = emi.getNumber();
    java.sql.Timestamp dataEnviament = new Timestamp(emi.getSentDate().getTime());
    java.lang.String enviador = emi.getDisplayFrom();
    java.lang.String destinataris = emi.getDisplayTo();
    java.lang.String subject = emi.getSubject();
    java.lang.String message = emi.getBody();
    e = new EmailJPA(emailID, dataEnviament, enviador, destinataris, subject, message);
    return e;
  }

  @Override
  public void delete(HttpServletRequest request, Email email) throws Exception, I18NException {
    final boolean enableCertificationCheck = false;
    EmailReader er = new EmailReader(System.getProperties(), enableCertificationCheck);
    // TODO Check si el numero de emails ha canviat
    er.deleteMessage((int) email.getEmailID());
  }

  @Override
  public EmailJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long emailID)
      throws I18NException {

    Map<Long, EmailMessageInfo> cache = (Map<Long, EmailMessageInfo>) request.getSession()
        .getAttribute(CACHE_DE_EMAILS_LLEGITS);

    EmailMessageInfo emi = cache.get(emailID);

    return message2email(emi);
  }

  @Override
  public boolean isActiveFormNew() {
    return false;
  }

  @Override
  public boolean isActiveFormEdit() {
    return false;
  }

  @Override
  public boolean isActiveFormView() {
    return false;
  }

}
