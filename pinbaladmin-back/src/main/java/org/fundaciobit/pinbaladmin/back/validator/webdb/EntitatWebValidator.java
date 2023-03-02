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
import org.fundaciobit.pinbaladmin.persistence.validator.EntitatValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EntitatWebValidator extends AbstractWebValidator<EntitatForm, Entitat>
     implements Validator, EntitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EntitatValidator<Entitat> validator = new EntitatValidator<Entitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.GrupEntitatService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.GrupEntitatService grupEntitatEjb;



  public EntitatWebValidator() {
    super();    
  }
  
  @Override
  public Entitat getBeanOfForm(EntitatForm form) {
    return  form.getEntitat();
  }

  @Override
  public Class<EntitatForm> getClassOfForm() {
    return EntitatForm.class;
  }

  @Override
  public void validate(EntitatForm __form, Entitat __bean, Errors errors) {

    WebValidationResult<EntitatForm> wvr;
    wvr = new WebValidationResult<EntitatForm>(errors);

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


  public void validate(EntitatForm __form, Entitat __bean, Errors errors,
    WebValidationResult<EntitatForm> wvr, boolean isNou) {

    BeanValidatorResult<Entitat> __vr = new BeanValidatorResult<Entitat>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, grupEntitatEjb);

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

  public EntitatValidator<Entitat> getValidator() {
    return validator;
  }

  public void setValidator(EntitatValidator<Entitat> validator) {
    this.validator = validator;
  }

}