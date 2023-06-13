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
import org.fundaciobit.pinbaladmin.persistence.validator.SolicitudServeiValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class SolicitudServeiWebValidator extends AbstractWebValidator<SolicitudServeiForm, SolicitudServei>
     implements Validator, SolicitudServeiFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected SolicitudServeiValidator<SolicitudServei> validator = new SolicitudServeiValidator<SolicitudServei>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ServeiService serveiEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService solicitudServeiEjb;



  public SolicitudServeiWebValidator() {
    super();    
  }
  
  @Override
  public SolicitudServei getBeanOfForm(SolicitudServeiForm form) {
    return  form.getSolicitudServei();
  }

  @Override
  public Class<SolicitudServeiForm> getClassOfForm() {
    return SolicitudServeiForm.class;
  }

  @Override
  public void validate(SolicitudServeiForm __form, SolicitudServei __bean, Errors errors) {

    WebValidationResult<SolicitudServeiForm> wvr;
    wvr = new WebValidationResult<SolicitudServeiForm>(errors);

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


  public void validate(SolicitudServeiForm __form, SolicitudServei __bean, Errors errors,
    WebValidationResult<SolicitudServeiForm> wvr, boolean isNou) {

    BeanValidatorResult<SolicitudServei> __vr = new BeanValidatorResult<SolicitudServei>();
    validator.validate(__vr, __bean,
      isNou, serveiEjb, solicitudEjb, solicitudServeiEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public SolicitudServeiValidator<SolicitudServei> getValidator() {
    return validator;
  }

  public void setValidator(SolicitudServeiValidator<SolicitudServei> validator) {
    this.validator = validator;
  }

}