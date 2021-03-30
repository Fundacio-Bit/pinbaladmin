package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.SolicitudValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class SolicitudWebValidator  implements Validator, SolicitudFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected SolicitudValidator<Object> validator = new SolicitudValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/DepartamentEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.DepartamentLocal departamentEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/EstatSolicitudEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EstatSolicitudLocal estatSolicitudEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/SolicitudEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudLocal solicitudEjb;



  public SolicitudWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return SolicitudForm.class.isAssignableFrom(clazz);
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
      isNou, departamentEjb, estatSolicitudEjb, solicitudEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public SolicitudValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(SolicitudValidator<Object> validator) {
    this.validator = validator;
  }

}