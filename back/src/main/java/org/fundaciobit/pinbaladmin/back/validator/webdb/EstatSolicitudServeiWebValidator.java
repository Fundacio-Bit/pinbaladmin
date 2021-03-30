package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.EstatSolicitudServeiValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EstatSolicitudServeiForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EstatSolicitudServeiWebValidator  implements Validator, EstatSolicitudServeiFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected EstatSolicitudServeiValidator<Object> validator = new EstatSolicitudServeiValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/EstatSolicitudServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EstatSolicitudServeiLocal estatSolicitudServeiEjb;



  public EstatSolicitudServeiWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return EstatSolicitudServeiForm.class.isAssignableFrom(clazz);
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
      isNou, estatSolicitudServeiEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EstatSolicitudServeiValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(EstatSolicitudServeiValidator<Object> validator) {
    this.validator = validator;
  }

}