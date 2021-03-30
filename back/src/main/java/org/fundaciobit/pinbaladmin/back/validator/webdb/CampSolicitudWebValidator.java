package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.CampSolicitudValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.CampSolicitudForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class CampSolicitudWebValidator  implements Validator, CampSolicitudFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected CampSolicitudValidator<Object> validator = new CampSolicitudValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/CampFormulariEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.CampFormulariLocal campFormulariEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/CampSolicitudEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.CampSolicitudLocal campSolicitudEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/SolicitudServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudServeiLocal solicitudServeiEjb;



  public CampSolicitudWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return CampSolicitudForm.class.isAssignableFrom(clazz);
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
      isNou, campFormulariEjb, campSolicitudEjb, solicitudServeiEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public CampSolicitudValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(CampSolicitudValidator<Object> validator) {
    this.validator = validator;
  }

}