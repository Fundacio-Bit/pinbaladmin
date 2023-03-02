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
import org.fundaciobit.pinbaladmin.persistence.validator.EntitatServeiValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatServeiForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.EntitatServei;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EntitatServeiWebValidator extends AbstractWebValidator<EntitatServeiForm, EntitatServei>
     implements Validator, EntitatServeiFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EntitatServeiValidator<EntitatServei> validator = new EntitatServeiValidator<EntitatServei>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatServeiService entitatServeiEjb;



  public EntitatServeiWebValidator() {
    super();    
  }
  
  @Override
  public EntitatServei getBeanOfForm(EntitatServeiForm form) {
    return  form.getEntitatServei();
  }

  @Override
  public Class<EntitatServeiForm> getClassOfForm() {
    return EntitatServeiForm.class;
  }

  @Override
  public void validate(EntitatServeiForm __form, EntitatServei __bean, Errors errors) {

    WebValidationResult<EntitatServeiForm> wvr;
    wvr = new WebValidationResult<EntitatServeiForm>(errors);

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


  public void validate(EntitatServeiForm __form, EntitatServei __bean, Errors errors,
    WebValidationResult<EntitatServeiForm> wvr, boolean isNou) {

    BeanValidatorResult<EntitatServei> __vr = new BeanValidatorResult<EntitatServei>();
    validator.validate(__vr, __bean,
      isNou, entitatServeiEjb);

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

  public EntitatServeiValidator<EntitatServei> getValidator() {
    return validator;
  }

  public void setValidator(EntitatServeiValidator<EntitatServei> validator) {
    this.validator = validator;
  }

}