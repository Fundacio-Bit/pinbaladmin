package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.TipusTiquetValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TipusTiquetForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusTiquetWebValidator  implements Validator, TipusTiquetFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected TipusTiquetValidator<Object> validator = new TipusTiquetValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/TipusTiquetEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.TipusTiquetLocal tipusTiquetEjb;



  public TipusTiquetWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return TipusTiquetForm.class.isAssignableFrom(clazz);
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
      isNou, tipusTiquetEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public TipusTiquetValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(TipusTiquetValidator<Object> validator) {
    this.validator = validator;
  }

}