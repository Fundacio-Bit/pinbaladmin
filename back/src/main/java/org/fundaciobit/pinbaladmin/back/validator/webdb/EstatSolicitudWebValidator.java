package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.EstatSolicitudValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EstatSolicitudForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EstatSolicitudWebValidator  implements Validator, EstatSolicitudFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected EstatSolicitudValidator<Object> validator = new EstatSolicitudValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/EstatSolicitudEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EstatSolicitudLocal estatSolicitudEjb;



  public EstatSolicitudWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return EstatSolicitudForm.class.isAssignableFrom(clazz);
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
      isNou, estatSolicitudEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EstatSolicitudValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(EstatSolicitudValidator<Object> validator) {
    this.validator = validator;
  }

}