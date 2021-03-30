package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.webdb.EmailController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EmailFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EmailForm;
import org.fundaciobit.pinbaladmin.jpa.EmailJPA;
import org.fundaciobit.pinbaladmin.logic.utils.EmailUtil;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.utils.Configuracio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/operador/email")
@SessionAttributes(types = { EmailForm.class, EmailFilterForm.class })
public class EmailOperatorController extends EmailController {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudLocal solicitudEjb;

  @Override
  public String getSessionAttributeFilterForm() {
    return "EmailOperator_FilterForm";
  }

  public String getTileForm() {
    return "emailFormOperator";
  }

  public String getTileList() {
    return "emailListOperator";
  }

  @Override
  public EmailJPA create(HttpServletRequest request, EmailJPA email) throws Exception,
      I18NException, I18NValidationException {

    final boolean isHtml = true;

    String[] emails = email.getDestinataris().split(";");
    log.error("Dest: " + Arrays.toString(emails));

    for (String address : emails) {
      try {
        EmailUtil.postMail(email.getSubject(), email.getMessage(), isHtml,
            Configuracio.getAppEmail(), address);
      } catch (Exception e) {
        HtmlUtils.saveMessageError(request, "No s'ha pogut enviar el correu a " + address
            + ": " + e.getMessage());
      }
    }

    return (EmailJPA) emailEjb.create(email);
  }

  @Override
  public boolean isActiveFormNew() {
    return true;
  }

  @Override
  public boolean isActiveFormEdit() {
    return false;
  }

  @Override
  public boolean isActiveDelete() {
    return false;
  }

  @Override
  public boolean isActiveFormView() {
    return true;
  }

  @Override
  public EmailFilterForm getEmailFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    EmailFilterForm emailFilterForm;
    emailFilterForm = super.getEmailFilterForm(pagina, mav, request);
    if (emailFilterForm.isNou()) {

      emailFilterForm.addHiddenField(DESTINATARIS);
      emailFilterForm.addHiddenField(EMAILID);
      emailFilterForm.addHiddenField(MESSAGE);
      ;
      // emailFilterForm.addHiddenField(field);;

      emailFilterForm.setVisibleMultipleSelection(false);
      emailFilterForm.setDeleteSelectedButtonVisible(false);
      emailFilterForm.setDeleteButtonVisible(false);
      emailFilterForm.setEditButtonVisible(false);

      emailFilterForm.setVisibleExportList(false);

      emailFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-eye-open",
          "genapp.viewtitle", getContextWeb() + "/view/{0}", "btn-info"));

    }

    return emailFilterForm;
  }

  @Override
  public EmailForm getEmailForm(EmailJPA _jpa, boolean __isView, HttpServletRequest request,
      ModelAndView mav) throws I18NException {
    EmailForm emailForm = super.getEmailForm(_jpa, __isView, request, mav);

    if (emailForm.isNou()) {
      // creacio

      // TODO XYZ ZZZ separador ;

      // Seleccionam els correus de les solicituds Local
      Where where = Where.AND(SolicitudFields.DEPARTAMENTID.isNotNull(),
          SolicitudFields.PERSONACONTACTEEMAIL.isNotNull());

      List<String> emailsList = solicitudEjb.executeQuery(
          SolicitudFields.PERSONACONTACTEEMAIL, where);

      Set<String> emails = new HashSet<String>(emailsList);

      String emailsStr = emails.toString().replaceAll("\\[|\\]", "").replaceAll(", ", ";");

      emailForm.getEmail().setDestinataris(emailsStr);
      emailForm.getEmail().setDataEnviament(new Timestamp(System.currentTimeMillis()));
      emailForm.getEmail().setEnviador(request.getUserPrincipal().getName());

      emailForm.addReadOnlyField(DESTINATARIS);
      emailForm.addReadOnlyField(DATAENVIAMENT);
      emailForm.addReadOnlyField(ENVIADOR);

    } else {
      // only view
    }

    return emailForm;
  }
}
