package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.SolicitudServeiValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class SolicitudServeiWebValidator  implements Validator, SolicitudServeiFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected SolicitudServeiValidator<Object> validator = new SolicitudServeiValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/EstatSolicitudServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EstatSolicitudServeiLocal estatSolicitudServeiEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/ServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.ServeiLocal serveiEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/SolicitudEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudLocal solicitudEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/SolicitudServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudServeiLocal solicitudServeiEjb;



  public SolicitudServeiWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return SolicitudServeiForm.class.isAssignableFrom(clazz);
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
      isNou, estatSolicitudServeiEjb, serveiEjb, solicitudEjb, solicitudServeiEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public SolicitudServeiValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(SolicitudServeiValidator<Object> validator) {
    this.validator = validator;
  }

}