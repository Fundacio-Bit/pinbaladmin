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
import org.fundaciobit.pinbaladmin.persistence.validator.GrupEntitatCedentValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.GrupEntitatCedentForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.GrupEntitatCedent;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class GrupEntitatCedentWebValidator extends AbstractWebValidator<GrupEntitatCedentForm, GrupEntitatCedent>
     implements Validator, GrupEntitatCedentFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected GrupEntitatCedentValidator<GrupEntitatCedent> validator = new GrupEntitatCedentValidator<GrupEntitatCedent>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatServeiService entitatServeiEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.GrupEntitatService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.GrupEntitatService grupEntitatEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.GrupEntitatCedentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.GrupEntitatCedentService grupEntitatCedentEjb;



  public GrupEntitatCedentWebValidator() {
    super();    
  }
  
  @Override
  public GrupEntitatCedent getBeanOfForm(GrupEntitatCedentForm form) {
    return  form.getGrupEntitatCedent();
  }

  @Override
  public Class<GrupEntitatCedentForm> getClassOfForm() {
    return GrupEntitatCedentForm.class;
  }

  @Override
  public void validate(GrupEntitatCedentForm __form, GrupEntitatCedent __bean, Errors errors) {

    WebValidationResult<GrupEntitatCedentForm> wvr;
    wvr = new WebValidationResult<GrupEntitatCedentForm>(errors);

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


  public void validate(GrupEntitatCedentForm __form, GrupEntitatCedent __bean, Errors errors,
    WebValidationResult<GrupEntitatCedentForm> wvr, boolean isNou) {

    BeanValidatorResult<GrupEntitatCedent> __vr = new BeanValidatorResult<GrupEntitatCedent>();
    validator.validate(__vr, __bean,
      isNou, entitatServeiEjb, grupEntitatEjb, grupEntitatCedentEjb);

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

  public GrupEntitatCedentValidator<GrupEntitatCedent> getValidator() {
    return validator;
  }

  public void setValidator(GrupEntitatCedentValidator<GrupEntitatCedent> validator) {
    this.validator = validator;
  }

}