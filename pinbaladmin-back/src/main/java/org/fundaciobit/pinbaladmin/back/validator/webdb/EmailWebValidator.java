package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.persistence.validator.EmailValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EmailForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Email;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EmailWebValidator extends AbstractWebValidator<EmailForm, Email>
     implements Validator, EmailFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EmailValidator<Email> validator = new EmailValidator<Email>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EmailService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EmailService emailEjb;



  public EmailWebValidator() {
    super();    
  }
  
  @Override
  public Email getBeanOfForm(EmailForm form) {
    return  form.getEmail();
  }

  @Override
  public Class<EmailForm> getClassOfForm() {
    return EmailForm.class;
  }

  @Override
  public void validate(EmailForm __form, Email __bean, Errors errors) {

    WebValidationResult<EmailForm> wvr;
    wvr = new WebValidationResult<EmailForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean(String.valueOf(objNou));
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(EmailForm __form, Email __bean, Errors errors,
    WebValidationResult<EmailForm> wvr, boolean isNou) {

    BeanValidatorResult<Email> __vr = new BeanValidatorResult<Email>();
    validator.validate(__vr, __bean,
      isNou, emailEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EmailValidator<Email> getValidator() {
    return validator;
  }

  public void setValidator(EmailValidator<Email> validator) {
    this.validator = validator;
  }

}