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
import org.fundaciobit.pinbaladmin.persistence.validator.TramitDCteAutValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TramitDCteAutWebValidator extends AbstractWebValidator<TramitDCteAutForm, TramitDCteAut>
     implements Validator, TramitDCteAutFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TramitDCteAutValidator<TramitDCteAut> validator = new TramitDCteAutValidator<TramitDCteAut>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService tramitAPersAutEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitDCteAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitDCteAutService tramitDCteAutEjb;



  public TramitDCteAutWebValidator() {
    super();    
  }
  
  @Override
  public TramitDCteAut getBeanOfForm(TramitDCteAutForm form) {
    return  form.getTramitDCteAut();
  }

  @Override
  public Class<TramitDCteAutForm> getClassOfForm() {
    return TramitDCteAutForm.class;
  }

  @Override
  public void validate(TramitDCteAutForm __form, TramitDCteAut __bean, Errors errors) {

    WebValidationResult<TramitDCteAutForm> wvr;
    wvr = new WebValidationResult<TramitDCteAutForm>(errors);

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


  public void validate(TramitDCteAutForm __form, TramitDCteAut __bean, Errors errors,
    WebValidationResult<TramitDCteAutForm> wvr, boolean isNou) {

    BeanValidatorResult<TramitDCteAut> __vr = new BeanValidatorResult<TramitDCteAut>();
    validator.validate(__vr, __bean,
      isNou, tramitAPersAutEjb, tramitDCteAutEjb);

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

  public TramitDCteAutValidator<TramitDCteAut> getValidator() {
    return validator;
  }

  public void setValidator(TramitDCteAutValidator<TramitDCteAut> validator) {
    this.validator = validator;
  }

}