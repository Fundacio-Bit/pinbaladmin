package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.common.AbstractEventController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EmailFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EmailForm;
import org.fundaciobit.pinbaladmin.jpa.EmailJPA;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = EnviarCorreuContacteOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { EmailForm.class, EmailFilterForm.class })
public class EnviarCorreuContacteOperadorController extends EmailOperatorController {

  public static final String CONTEXT_WEB = "/operador/enviarcorreucontacte";

  @Override
  public boolean isActiveList() {
    return false;
  }

  @Override
  protected String getEmailDestinatari(HttpServletRequest request) throws I18NException {

    return (String) request.getSession()
        .getAttribute(AbstractEventController.SESSION_ENVIARCORREU_DEST);

  }

  @Override
  public boolean isStoredInDDBB() {
    return false;
  }

  @Override
  public EmailForm getEmailForm(EmailJPA _jpa, boolean __isView, HttpServletRequest request,
      ModelAndView mav) throws I18NException {
    EmailForm emailForm = super.getEmailForm(_jpa, __isView, request, mav);

    final String msg = (String) request.getSession().getAttribute(AbstractEventController.SESSION_ENVIARCORREU_MISSATGE);
    emailForm.getEmail().setMessage(msg.replaceAll("\n", "<br/>"));
    
    emailForm.addHiddenField(DATAENVIAMENT);
    emailForm.addHiddenField(ENVIADOR);
    
    
    emailForm.addAdditionalButton(new AdditionalButton("icon-bell", "plantilla",
            "javascript:document.getElementById('email.message').value = 'HOLA CARACOLA'", "btn-info"));

    
    emailForm.getReadOnlyFields().remove(DESTINATARIS);
    

    return emailForm;

  }

  @Override
  public String getRedirectWhenCreated(HttpServletRequest request, EmailForm emailForm) {
    return (String) request.getSession()
        .getAttribute(AbstractEventController.SESSION_ENVIARCORREU_CALLBACK);
  }

  @Override
  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long emailID) {
    return (String) request.getSession()
        .getAttribute(AbstractEventController.SESSION_ENVIARCORREU_CALLBACK);
  }

}
