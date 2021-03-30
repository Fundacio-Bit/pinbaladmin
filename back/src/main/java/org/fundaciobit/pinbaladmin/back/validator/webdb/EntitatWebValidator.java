package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.EntitatValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EntitatWebValidator  implements Validator, EntitatFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected EntitatValidator<Object> validator = new EntitatValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/EntitatEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/GrupEntitatEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.GrupEntitatLocal grupEntitatEjb;



  public EntitatWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return EntitatForm.class.isAssignableFrom(clazz);
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
      isNou, entitatEjb, grupEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EntitatValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(EntitatValidator<Object> validator) {
    this.validator = validator;
  }

}