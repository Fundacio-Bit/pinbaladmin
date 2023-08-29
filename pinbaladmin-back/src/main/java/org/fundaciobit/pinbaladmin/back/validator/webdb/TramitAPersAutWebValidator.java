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
import org.fundaciobit.pinbaladmin.persistence.validator.TramitAPersAutValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TramitAPersAutWebValidator extends AbstractWebValidator<TramitAPersAutForm, TramitAPersAut>
     implements Validator, TramitAPersAutFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TramitAPersAutValidator<TramitAPersAut> validator = new TramitAPersAutValidator<TramitAPersAut>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService tramitAPersAutEjb;



  public TramitAPersAutWebValidator() {
    super();    
  }
  
  @Override
  public TramitAPersAut getBeanOfForm(TramitAPersAutForm form) {
    return  form.getTramitAPersAut();
  }

  @Override
  public Class<TramitAPersAutForm> getClassOfForm() {
    return TramitAPersAutForm.class;
  }

  @Override
  public void validate(TramitAPersAutForm __form, TramitAPersAut __bean, Errors errors) {

    WebValidationResult<TramitAPersAutForm> wvr;
    wvr = new WebValidationResult<TramitAPersAutForm>(errors);

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


  public void validate(TramitAPersAutForm __form, TramitAPersAut __bean, Errors errors,
    WebValidationResult<TramitAPersAutForm> wvr, boolean isNou) {

    BeanValidatorResult<TramitAPersAut> __vr = new BeanValidatorResult<TramitAPersAut>();
    validator.validate(__vr, __bean,
      isNou, tramitAPersAutEjb);

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

  public TramitAPersAutValidator<TramitAPersAut> getValidator() {
    return validator;
  }

  public void setValidator(TramitAPersAutValidator<TramitAPersAut> validator) {
    this.validator = validator;
  }

}