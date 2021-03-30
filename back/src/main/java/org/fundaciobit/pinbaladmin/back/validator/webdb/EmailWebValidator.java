package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.EmailValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EmailForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EmailWebValidator  implements Validator, EmailFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected EmailValidator<Object> validator = new EmailValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/EmailEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EmailLocal emailEjb;



  public EmailWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return EmailForm.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    WebValidationResult<Object> wvr;
    wvr = new WebValidationResult<Object>(errors);

    Boolean nou = (Boolean)errors.getFieldValue("nou");
    boolean isNou =  nou != null && nou.booleanValue();

    validate(target, errors, wvr, isNou);
  }


  public void validate(Object target, Errors errors,
    WebValidationResult<Object> wvr, boolean isNou) {

    validator.validate(wvr, target,
      isNou, emailEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EmailValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(EmailValidator<Object> validator) {
    this.validator = validator;
  }

}