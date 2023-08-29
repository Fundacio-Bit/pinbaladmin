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
import org.fundaciobit.pinbaladmin.persistence.validator.TramitECteAudValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.TramitECteAud;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TramitECteAudWebValidator extends AbstractWebValidator<TramitECteAudForm, TramitECteAud>
     implements Validator, TramitECteAudFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TramitECteAudValidator<TramitECteAud> validator = new TramitECteAudValidator<TramitECteAud>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService tramitAPersAutEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitECteAudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitECteAudService tramitECteAudEjb;



  public TramitECteAudWebValidator() {
    super();    
  }
  
  @Override
  public TramitECteAud getBeanOfForm(TramitECteAudForm form) {
    return  form.getTramitECteAud();
  }

  @Override
  public Class<TramitECteAudForm> getClassOfForm() {
    return TramitECteAudForm.class;
  }

  @Override
  public void validate(TramitECteAudForm __form, TramitECteAud __bean, Errors errors) {

    WebValidationResult<TramitECteAudForm> wvr;
    wvr = new WebValidationResult<TramitECteAudForm>(errors);

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


  public void validate(TramitECteAudForm __form, TramitECteAud __bean, Errors errors,
    WebValidationResult<TramitECteAudForm> wvr, boolean isNou) {

    BeanValidatorResult<TramitECteAud> __vr = new BeanValidatorResult<TramitECteAud>();
    validator.validate(__vr, __bean,
      isNou, tramitAPersAutEjb, tramitECteAudEjb);

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

  public TramitECteAudValidator<TramitECteAud> getValidator() {
    return validator;
  }

  public void setValidator(TramitECteAudValidator<TramitECteAud> validator) {
    this.validator = validator;
  }

}