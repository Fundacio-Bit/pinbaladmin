package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.DepartamentValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.DepartamentForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class DepartamentWebValidator  implements Validator, DepartamentFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected DepartamentValidator<Object> validator = new DepartamentValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/AreaEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.AreaLocal areaEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/DepartamentEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.DepartamentLocal departamentEjb;



  public DepartamentWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return DepartamentForm.class.isAssignableFrom(clazz);
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
      isNou, areaEjb, departamentEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public DepartamentValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(DepartamentValidator<Object> validator) {
    this.validator = validator;
  }

}