package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.EventValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EventWebValidator  implements Validator, EventFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected EventValidator<Object> validator = new EventValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/EventEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EventLocal eventEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/IncidenciaTecnicaEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaLocal incidenciaTecnicaEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/SolicitudEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudLocal solicitudEjb;



  public EventWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return EventForm.class.isAssignableFrom(clazz);
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
      isNou, eventEjb, incidenciaTecnicaEjb, solicitudEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EventValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(EventValidator<Object> validator) {
    this.validator = validator;
  }

}