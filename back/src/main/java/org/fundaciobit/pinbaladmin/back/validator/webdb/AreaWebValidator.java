package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.AreaValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.AreaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class AreaWebValidator  implements Validator, AreaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected AreaValidator<Object> validator = new AreaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/AreaEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.AreaLocal areaEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/EntitatEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EntitatLocal entitatEjb;



  public AreaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return AreaForm.class.isAssignableFrom(clazz);
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
      isNou, areaEjb, entitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public AreaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(AreaValidator<Object> validator) {
    this.validator = validator;
  }

}