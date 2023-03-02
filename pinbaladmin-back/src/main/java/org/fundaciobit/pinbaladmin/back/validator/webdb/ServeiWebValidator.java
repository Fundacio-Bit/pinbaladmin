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
import org.fundaciobit.pinbaladmin.persistence.validator.ServeiValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.ServeiForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Servei;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ServeiWebValidator extends AbstractWebValidator<ServeiForm, Servei>
     implements Validator, ServeiFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected ServeiValidator<Servei> validator = new ServeiValidator<Servei>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatServeiService entitatServeiEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatServeiService estatServeiEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FormulariService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.FormulariService formulariEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ServeiService serveiEjb;



  public ServeiWebValidator() {
    super();    
  }
  
  @Override
  public Servei getBeanOfForm(ServeiForm form) {
    return  form.getServei();
  }

  @Override
  public Class<ServeiForm> getClassOfForm() {
    return ServeiForm.class;
  }

  @Override
  public void validate(ServeiForm __form, Servei __bean, Errors errors) {

    WebValidationResult<ServeiForm> wvr;
    wvr = new WebValidationResult<ServeiForm>(errors);

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


  public void validate(ServeiForm __form, Servei __bean, Errors errors,
    WebValidationResult<ServeiForm> wvr, boolean isNou) {

    BeanValidatorResult<Servei> __vr = new BeanValidatorResult<Servei>();
    validator.validate(__vr, __bean,
      isNou, entitatServeiEjb, estatServeiEjb, formulariEjb, serveiEjb);

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

  public ServeiValidator<Servei> getValidator() {
    return validator;
  }

  public void setValidator(ServeiValidator<Servei> validator) {
    this.validator = validator;
  }

}