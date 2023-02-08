package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.common.AbstractEventController;
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
import org.fundaciobit.pinbaladmin.model.fields.OperadorFields;
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

  public static final String CACHE_SIZE_DE_EMAILS_LLEGITS = "__CACHE_SIZE_DE_EMAILS_LLEGITS__";
  
  
  public static final String  MOSTRAR_MISSATGE_ARXIU = "__mostrarMissatgeArxiu__";

  public static final int MISSATGE22 = 1;

  public static final int ATTACHMENTS = 2;

  @EJB(mappedName = IncidenciaTecnicaLogicaLocal.JNDI_NAME)
  protected IncidenciaTecnicaLogicaLocal incidenciaTecnicaLogicaEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ServeiLocal serveiEjb;

  @EJB(mappedName = SolicitudLogicaLocal.JNDI_NAME)
  protected SolicitudLogicaLocal solicitudLogicaEjb;
  
  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OperadorLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.OperadorLocal operadorEjb;

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

      emailFilterForm.setOrderBy(DATAENVIAMENT.javaName);
      emailFilterForm.setOrderAsc(false);

      emailFilterForm.setGroupByFields(new ArrayList<Field<?>>());
      emailFilterForm.setFilterByFields(new ArrayList<Field<?>>());

      emailFilterForm.addHiddenField(DESTINATARIS);
      emailFilterForm.addHiddenField(MESSAGE);
      emailFilterForm.addHiddenField(EMAILID);

      emailFilterForm.setItemsPerPage(5);
      //emailFilterForm.setAllItemsPerPage(new int[] { 5 });
      
      emailFilterForm.setVisibleExportList(false);
      
      emailFilterForm.setAttachedAdditionalJspCode(true);

      emailFilterForm.setActionsRenderer(EmailFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);

      emailFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-warning-sign",
          "incidencia.convertir", "javascript:crearIncidencia({0})", "btn-warning")); // getContextWeb() + "/incidencia/{0}"

      // Afegir boto per Solicitud
      emailFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-list-alt",
          "solicitud.convertir", "javascript:crearSolicitud({0})" , "btn-success")); // getContextWeb() + \"/solicitud/{0}\"

      {
        AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
        adfield4.setCodeName(EmailFields.MESSAGE.fullName);
        adfield4.setPosition(MISSATGE22);
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
    
    // Tramitadors
    {
      

      SelectMultipleStringKeyValue smskv;
      smskv = new SelectMultipleStringKeyValue(OperadorFields.USERNAME.select,
          OperadorFields.NOM.select);

      List<StringKeyValue> tramitadors = operadorEjb.executeQuery(smskv, null);
      
     

      mav.addObject("tramitadors", tramitadors);
    }
    
    
    
    
    Boolean mostrarMissatgeArxiu = (Boolean)request.getSession().getAttribute(MOSTRAR_MISSATGE_ARXIU);
    
    if (mostrarMissatgeArxiu == null) {
      mostrarMissatgeArxiu = false;
      request.getSession().setAttribute(MOSTRAR_MISSATGE_ARXIU, false);
    }
    
    
    emailFilterForm.getAdditionalButtons().clear();
    if (mostrarMissatgeArxiu) {
      emailFilterForm.addAdditionalButton(new AdditionalButton(" icon-eye-close", "amagarmissatgearxiu",
         getContextWeb() + "/amagarmissatgearxiu", "btn-info"));
    } else {
      emailFilterForm.addAdditionalButton(new AdditionalButton("icon-eye-open", "mostrarmissatgearxiu",
          getContextWeb() + "/mostrarmissatgearxiu", "btn-info"));
    }
    

    return emailFilterForm;

  }
  
  
  
  @RequestMapping(value = "/amagarmissatgearxiu", method = RequestMethod.GET)
  public String amagarmissatgearxiu(HttpServletRequest request, HttpServletResponse response) {

    request.getSession().setAttribute(MOSTRAR_MISSATGE_ARXIU, false);

    return "redirect:" + getContextWeb() + "/list";

  }

  @RequestMapping(value = "/mostrarmissatgearxiu", method = RequestMethod.GET)
  public String mostrarmissatgearxiu(HttpServletRequest request,
      HttpServletResponse response) {

    request.getSession().setAttribute(MOSTRAR_MISSATGE_ARXIU, true);

    return "redirect:" + getContextWeb() + "/list";

  }
  
  

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      EmailFilterForm filterForm, List<Email> list) throws I18NException {

    Map<Long, String> map;
    map = (Map<Long, String>) filterForm.getAdditionalField(MISSATGE22).getValueMap();
    map.clear();

    Map<Long, String> mapAttach;
    mapAttach = (Map<Long, String>) filterForm.getAdditionalField(ATTACHMENTS).getValueMap();
    mapAttach.clear();

    Map<Long, EmailMessageInfo> cache = (Map<Long, EmailMessageInfo>) request.getSession()
        .getAttribute(CACHE_DE_EMAILS_LLEGITS);

    for (Email email : list) {

      if (email.getMessage() != null) {
        String msg = email.getMessage().trim();

        if ((msg.startsWith("<") && msg.endsWith(">")) || msg.endsWith("</html>")) {
          // map.put(email.getEmailID(), "<div
          // style=\"max-width:500px;max-height:400px;overflow:scroll;\">" + msg
          // + "</div>");
          map.put(email.getEmailID(), "<iframe border=\"2\" width=\"500px\" height=\"350px\" src=\"" + request.getContextPath()
              + getContextWeb() + "/message/" + email.getEmailID() + "\"></iframe>");
        } else {
          map.put(email.getEmailID(),
              "<textarea readonly  style=\"width:auto;max-width:500px;max-height:350px;\""
                  + computeRowsCols(msg) + ">" + msg + "</textarea>");
        }
      }

      EmailMessageInfo emi = cache.get(email.getEmailID());

      if (emi.getAttachments() != null) {
        StringBuffer str = new StringBuffer();

        for (EmailAttachmentInfo ads : emi.getAttachments()) {

          str.append("<div style=\"border-style: solid;border-width:1px;\">");
          str.append("<small>-Nom: " + ads.getFileName() + "<br/>" + "-Mida: "
              + ads.getData().length + " bytes<br/>" + "-Tipus: " + ads.getContentType());
          str.append("</small></div><br/>");
        }

        mapAttach.put(email.getEmailID(), str.toString());

      }

    }

    filterForm.setSubTitleCode("=Tens " + cachesize(request)
        + " correus sense processar ..."  );

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

    return " rows=\"" + Math.min(rows, 70) + "\" cols=\"" + cols + "\" ";

  }

  @RequestMapping(value = "/incidencia/{emailID}/{tramitador}", method = RequestMethod.GET)
  public String incidencia(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("emailID") Long emailID, @PathVariable("tramitador")String tramitador ) {

    try {

      //Map<Long, EmailMessageInfo> cache
      // cache= (Map<Long, EmailMessageInfo>) request.getSession().getAttribute(CACHE_DE_EMAILS_LLEGITS);

      final boolean enableCertificationCheck = false;
      EmailReader er = new EmailReader(System.getProperties(), enableCertificationCheck);

      if (er.getCountMessages() == cachesize(request)) {

        //EmailMessageInfo emi = cache.get(emailID);
        EmailMessageInfo emi = er.getMessage((int)(long)emailID);

        IncidenciaTecnica it = incidenciaTecnicaLogicaEjb.createFromEmail(emi,
            tramitador);

        er.deleteMessage((int) (long) emailID);

        // Redireccionam a Enviar Correu al Contacte
        return "redirect:" + EventIncidenciaTecnicaOperadorController.CONTEXT_PATH
            + AbstractEventController.ENVIAR_ENLLAZ + it.getIncidenciaTecnicaID();

        // return "redirect:" + IncidenciaTecnicaOperadorController.WEBCONTEXT +
        // "/"
        // + it.getIncidenciaTecnicaID() + "/edit";

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

  public int cachesize(HttpServletRequest request) {
    Integer i = (Integer) request.getSession().getAttribute(CACHE_SIZE_DE_EMAILS_LLEGITS);

    if (i == null) {
      return 0;
    } else {
      return i;
    }

  }

  @RequestMapping(value = "/solicitud/{emailID}", method = RequestMethod.GET)
  public String solicitud(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long emailID) {

    try {

      long start = System.currentTimeMillis();
      //Map<Long, EmailMessageInfo> cache;
      //cache = (Map<Long, EmailMessageInfo>) request.getSession().getAttribute(CACHE_DE_EMAILS_LLEGITS);

      final boolean enableCertificationCheck = false;
      EmailReader er = new EmailReader(System.getProperties(), enableCertificationCheck);

      if (er.getCountMessages() == cachesize(request)) {

        //EmailMessageInfo emi = cache.get(emailID);
        
        EmailMessageInfo emi =  er.getMessage((int) (long) emailID);

        // IncidenciaTecnica it =
        // incidenciaTecnicaLogicaEjb.createFromEmail(emi,
        // request.getRemoteUser());

        String fileName = emi.getSubject();

        SolicitudEstatalDesDeFitxersMultiplesOperadorController.crearSolicitudsDesDeEmail(
            request, emi, fileName, log, serveiEjb, solicitudLogicaEjb);

        er.deleteMessage((int) (long) emailID);

        long end = System.currentTimeMillis();

        // XYZ ZZZ
        // S'ha de reenviar a ENVIAR CORREU A CONTACTE. Revisar mètode
        // incidencia ????

        return "redirect:/operador/solicitudestatal/list/" + start + "/" + end;

      } else {
        HtmlUtils.saveMessageWarning(request, "Ha rebut altres correus. Torni a intentar-ho.");
      }
      /*
       * } catch (I18NException e) { String msg = I18NUtils.getMessage(e);
       * log.error(msg, e); HtmlUtils.saveMessageError(request, msg);
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
      final OrderBy[] orderBy, Integer itemsPerPage, final int inici)
      throws I18NException {

    List<Email> list = new ArrayList<Email>();

    Map<Long, EmailMessageInfo> cache = new HashMap<Long, EmailMessageInfo>();

    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getRequest();

    int size = 0;

    try {

      final boolean enableCertificationCheck = false;
      EmailReader er = new EmailReader(System.getProperties(), enableCertificationCheck);

      size = er.getCountMessages();
      
      if (itemsPerPage == null || itemsPerPage == -1) {
        itemsPerPage = size;
      }
      

      final int start = 1;
      final int end = Math.min(size, itemsPerPage);
      Boolean includeAttachments = (Boolean)request.getSession().getAttribute(MOSTRAR_MISSATGE_ARXIU);

      List<EmailMessageInfo> emails = er.list(start, end, includeAttachments);

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

    /*
    if (orderBy == null || orderBy.length == 0) {
      log.info("OrderBy NULL o buit");
    } else {
      for (OrderBy o : orderBy) {
        log.info("   - OrderBy[" + o.javaName + "]: " + o.orderType);

        if (o.javaName.equals(DATAENVIAMENT.javaName)) {
          Collections.sort(list, new EmailDateComparator(orderBy[0].orderType));
          break;
        }
      }
    }
    */

    request.getSession().setAttribute(CACHE_DE_EMAILS_LLEGITS, cache);
    request.getSession().setAttribute(CACHE_SIZE_DE_EMAILS_LLEGITS, size);
    return list;

  }

  /**
   * 
   * @author anadal
   *
   */
  public class EmailDateComparator implements Comparator<Email> {

    private final int orderType;

    public EmailDateComparator(OrderType asc) {
      this.orderType = asc.equals(OrderType.ASC) ? 1 : -1;
    }

    @Override
    public int compare(Email o1, Email o2) {
      return orderType * o1.getDataEnviament().compareTo(o2.getDataEnviament());
    }

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

  @RequestMapping(value = "/message/{emailID}")
  public void getMessageEmail(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("emailID") java.lang.Long emailID) {

    EmailJPA email;
    try {
      email = this.findByPrimaryKey(request, emailID);
      
      response.setContentType("text/html"); 
      response.setCharacterEncoding("UTF-8");

      PrintWriter os = response.getWriter();

      os.print("<html><body>");
      os.print(email.getMessage());
      os.print("</body></html>");
      os.flush();
      os.close();

    } catch (Throwable e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

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
