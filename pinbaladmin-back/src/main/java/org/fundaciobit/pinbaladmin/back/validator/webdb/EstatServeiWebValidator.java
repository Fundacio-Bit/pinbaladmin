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
import org.fundaciobit.pinbaladmin.persistence.validator.EstatServeiValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EstatServeiForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.EstatServei;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EstatServeiWebValidator extends AbstractWebValidator<EstatServeiForm, EstatServei>
     implements Validator, EstatServeiFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EstatServeiValidator<EstatServei> validator = new EstatServeiValidator<EstatServei>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatServeiService estatServeiEjb;



  public EstatServeiWebValidator() {
    super();    
  }
  
  @Override
  public EstatServei getBeanOfForm(EstatServeiForm form) {
    return  form.getEstatServei();
  }

  @Override
  public Class<EstatServeiForm> getClassOfForm() {
    return EstatServeiForm.class;
  }

  @Override
  public void validate(EstatServeiForm __form, EstatServei __bean, Errors errors) {

    WebValidationResult<EstatServeiForm> wvr;
    wvr = new WebValidationResult<EstatServeiForm>(errors);

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


  public void validate(EstatServeiForm __form, EstatServei __bean, Errors errors,
    WebValidationResult<EstatServeiForm> wvr, boolean isNou) {

    BeanValidatorResult<EstatServei> __vr = new BeanValidatorResult<EstatServei>();
    validator.validate(__vr, __bean,
      isNou, estatServeiEjb);

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

  public EstatServeiValidator<EstatServei> getValidator() {
    return validator;
  }

  public void setValidator(EstatServeiValidator<EstatServei> validator) {
    this.validator = validator;
  }

}