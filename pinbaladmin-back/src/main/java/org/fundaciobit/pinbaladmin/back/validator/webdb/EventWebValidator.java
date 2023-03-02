package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.persistence.validator.EventValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Event;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EventWebValidator extends AbstractWebValidator<EventForm, Event>
     implements Validator, EventFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EventValidator<Event> validator = new EventValidator<Event>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EventService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EventService eventEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaService incidenciaTecnicaEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;



  public EventWebValidator() {
    super();    
  }
  
  @Override
  public Event getBeanOfForm(EventForm form) {
    return  form.getEvent();
  }

  @Override
  public Class<EventForm> getClassOfForm() {
    return EventForm.class;
  }

  @Override
  public void validate(EventForm __form, Event __bean, Errors errors) {

    WebValidationResult<EventForm> wvr;
    wvr = new WebValidationResult<EventForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean(String.valueOf(objNou));
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(EventForm __form, Event __bean, Errors errors,
    WebValidationResult<EventForm> wvr, boolean isNou) {

    BeanValidatorResult<Event> __vr = new BeanValidatorResult<Event>();
    validator.validate(__vr, __bean,
      isNou, eventEjb, incidenciaTecnicaEjb, solicitudEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EventValidator<Event> getValidator() {
    return validator;
  }

  public void setValidator(EventValidator<Event> validator) {
    this.validator = validator;
  }

}