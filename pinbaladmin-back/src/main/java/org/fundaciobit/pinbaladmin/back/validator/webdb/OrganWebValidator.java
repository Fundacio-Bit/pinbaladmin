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
import org.fundaciobit.pinbaladmin.persistence.validator.OrganValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.OrganForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Organ;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class OrganWebValidator extends AbstractWebValidator<OrganForm, Organ>
     implements Validator, OrganFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected OrganValidator<Organ> validator = new OrganValidator<Organ>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OrganService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.OrganService organEjb;



  public OrganWebValidator() {
    super();    
  }
  
  @Override
  public Organ getBeanOfForm(OrganForm form) {
    return  form.getOrgan();
  }

  @Override
  public Class<OrganForm> getClassOfForm() {
    return OrganForm.class;
  }

  @Override
  public void validate(OrganForm __form, Organ __bean, Errors errors) {

    WebValidationResult<OrganForm> wvr;
    wvr = new WebValidationResult<OrganForm>(errors);

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


  public void validate(OrganForm __form, Organ __bean, Errors errors,
    WebValidationResult<OrganForm> wvr, boolean isNou) {

    BeanValidatorResult<Organ> __vr = new BeanValidatorResult<Organ>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, organEjb);

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

  public OrganValidator<Organ> getValidator() {
    return validator;
  }

  public void setValidator(OrganValidator<Organ> validator) {
    this.validator = validator;
  }

}