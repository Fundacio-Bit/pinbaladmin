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
import org.fundaciobit.pinbaladmin.persistence.validator.EstatSolicitudServeiValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EstatSolicitudServeiForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.EstatSolicitudServei;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EstatSolicitudServeiWebValidator extends AbstractWebValidator<EstatSolicitudServeiForm, EstatSolicitudServei>
     implements Validator, EstatSolicitudServeiFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EstatSolicitudServeiValidator<EstatSolicitudServei> validator = new EstatSolicitudServeiValidator<EstatSolicitudServei>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatSolicitudServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatSolicitudServeiService estatSolicitudServeiEjb;



  public EstatSolicitudServeiWebValidator() {
    super();    
  }
  
  @Override
  public EstatSolicitudServei getBeanOfForm(EstatSolicitudServeiForm form) {
    return  form.getEstatSolicitudServei();
  }

  @Override
  public Class<EstatSolicitudServeiForm> getClassOfForm() {
    return EstatSolicitudServeiForm.class;
  }

  @Override
  public void validate(EstatSolicitudServeiForm __form, EstatSolicitudServei __bean, Errors errors) {

    WebValidationResult<EstatSolicitudServeiForm> wvr;
    wvr = new WebValidationResult<EstatSolicitudServeiForm>(errors);

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


  public void validate(EstatSolicitudServeiForm __form, EstatSolicitudServei __bean, Errors errors,
    WebValidationResult<EstatSolicitudServeiForm> wvr, boolean isNou) {

    BeanValidatorResult<EstatSolicitudServei> __vr = new BeanValidatorResult<EstatSolicitudServei>();
    validator.validate(__vr, __bean,
      isNou, estatSolicitudServeiEjb);

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

  public EstatSolicitudServeiValidator<EstatSolicitudServei> getValidator() {
    return validator;
  }

  public void setValidator(EstatSolicitudServeiValidator<EstatSolicitudServei> validator) {
    this.validator = validator;
  }

}