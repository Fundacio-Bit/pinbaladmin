package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.TiquetValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TiquetForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TiquetWebValidator  implements Validator, TiquetFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected TiquetValidator<Object> validator = new TiquetValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/EstatTiquetEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EstatTiquetLocal estatTiquetEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/TipusTiquetEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.TipusTiquetLocal tipusTiquetEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/TiquetEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.TiquetLocal tiquetEjb;



  public TiquetWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return TiquetForm.class.isAssignableFrom(clazz);
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

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }
    validator.validate(wvr, target,
      isNou, estatTiquetEjb, tipusTiquetEjb, tiquetEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public TiquetValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(TiquetValidator<Object> validator) {
    this.validator = validator;
  }

}