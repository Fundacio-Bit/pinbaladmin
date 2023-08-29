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
import org.fundaciobit.pinbaladmin.persistence.validator.TramitJConsentValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TramitJConsentWebValidator extends AbstractWebValidator<TramitJConsentForm, TramitJConsent>
     implements Validator, TramitJConsentFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TramitJConsentValidator<TramitJConsent> validator = new TramitJConsentValidator<TramitJConsent>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService tramitAPersAutEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitJConsentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitJConsentService tramitJConsentEjb;



  public TramitJConsentWebValidator() {
    super();    
  }
  
  @Override
  public TramitJConsent getBeanOfForm(TramitJConsentForm form) {
    return  form.getTramitJConsent();
  }

  @Override
  public Class<TramitJConsentForm> getClassOfForm() {
    return TramitJConsentForm.class;
  }

  @Override
  public void validate(TramitJConsentForm __form, TramitJConsent __bean, Errors errors) {

    WebValidationResult<TramitJConsentForm> wvr;
    wvr = new WebValidationResult<TramitJConsentForm>(errors);

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


  public void validate(TramitJConsentForm __form, TramitJConsent __bean, Errors errors,
    WebValidationResult<TramitJConsentForm> wvr, boolean isNou) {

    BeanValidatorResult<TramitJConsent> __vr = new BeanValidatorResult<TramitJConsent>();
    validator.validate(__vr, __bean,
      isNou, tramitAPersAutEjb, tramitJConsentEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
        if (!errors.hasFieldErrors(get(ADJUNTID))){
            CommonsMultipartFile adjuntID = ((TramitJConsentForm)__form).getAdjuntID();
            if (adjuntID == null || adjuntID.isEmpty()) {
                errors.rejectValue(get(ADJUNTID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(ADJUNTID)) },
                null);
            }
        }

    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public TramitJConsentValidator<TramitJConsent> getValidator() {
    return validator;
  }

  public void setValidator(TramitJConsentValidator<TramitJConsent> validator) {
    this.validator = validator;
  }

}