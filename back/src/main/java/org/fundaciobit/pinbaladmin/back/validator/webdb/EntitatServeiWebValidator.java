package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.EntitatServeiValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatServeiForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EntitatServeiWebValidator  implements Validator, EntitatServeiFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected EntitatServeiValidator<Object> validator = new EntitatServeiValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/EntitatServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EntitatServeiLocal entitatServeiEjb;



  public EntitatServeiWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return EntitatServeiForm.class.isAssignableFrom(clazz);
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
      isNou, entitatServeiEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EntitatServeiValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(EntitatServeiValidator<Object> validator) {
    this.validator = validator;
  }

}