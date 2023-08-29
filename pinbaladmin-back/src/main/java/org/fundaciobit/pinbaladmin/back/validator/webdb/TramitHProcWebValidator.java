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
import org.fundaciobit.pinbaladmin.persistence.validator.TramitHProcValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TramitHProcWebValidator extends AbstractWebValidator<TramitHProcForm, TramitHProc>
     implements Validator, TramitHProcFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TramitHProcValidator<TramitHProc> validator = new TramitHProcValidator<TramitHProc>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService tramitAPersAutEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitHProcService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitHProcService tramitHProcEjb;



  public TramitHProcWebValidator() {
    super();    
  }
  
  @Override
  public TramitHProc getBeanOfForm(TramitHProcForm form) {
    return  form.getTramitHProc();
  }

  @Override
  public Class<TramitHProcForm> getClassOfForm() {
    return TramitHProcForm.class;
  }

  @Override
  public void validate(TramitHProcForm __form, TramitHProc __bean, Errors errors) {

    WebValidationResult<TramitHProcForm> wvr;
    wvr = new WebValidationResult<TramitHProcForm>(errors);

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


  public void validate(TramitHProcForm __form, TramitHProc __bean, Errors errors,
    WebValidationResult<TramitHProcForm> wvr, boolean isNou) {

    BeanValidatorResult<TramitHProc> __vr = new BeanValidatorResult<TramitHProc>();
    validator.validate(__vr, __bean,
      isNou, tramitAPersAutEjb, tramitHProcEjb);

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

  public TramitHProcValidator<TramitHProc> getValidator() {
    return validator;
  }

  public void setValidator(TramitHProcValidator<TramitHProc> validator) {
    this.validator = validator;
  }

}