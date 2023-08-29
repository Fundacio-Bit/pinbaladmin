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
import org.fundaciobit.pinbaladmin.persistence.validator.SolicitudValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class SolicitudWebValidator extends AbstractWebValidator<SolicitudForm, Solicitud>
     implements Validator, SolicitudFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected SolicitudValidator<Solicitud> validator = new SolicitudValidator<Solicitud>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DepartamentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DepartamentService departamentEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OrganService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.OrganService organEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;



  public SolicitudWebValidator() {
    super();    
  }
  
  @Override
  public Solicitud getBeanOfForm(SolicitudForm form) {
    return  form.getSolicitud();
  }

  @Override
  public Class<SolicitudForm> getClassOfForm() {
    return SolicitudForm.class;
  }

  @Override
  public void validate(SolicitudForm __form, Solicitud __bean, Errors errors) {

    WebValidationResult<SolicitudForm> wvr;
    wvr = new WebValidationResult<SolicitudForm>(errors);

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


  public void validate(SolicitudForm __form, Solicitud __bean, Errors errors,
    WebValidationResult<SolicitudForm> wvr, boolean isNou) {

    BeanValidatorResult<Solicitud> __vr = new BeanValidatorResult<Solicitud>();
    validator.validate(__vr, __bean,
      isNou, departamentEjb, organEjb, solicitudEjb);

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

  public SolicitudValidator<Solicitud> getValidator() {
    return validator;
  }

  public void setValidator(SolicitudValidator<Solicitud> validator) {
    this.validator = validator;
  }

}