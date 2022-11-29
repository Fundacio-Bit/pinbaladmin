package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.OperadorValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.OperadorForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class OperadorWebValidator  implements Validator, OperadorFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected OperadorValidator<Object> validator = new OperadorValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/OperadorEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.OperadorLocal operadorEjb;



  public OperadorWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return OperadorForm.class.isAssignableFrom(clazz);
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
      isNou, operadorEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public OperadorValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(OperadorValidator<Object> validator) {
    this.validator = validator;
  }

}