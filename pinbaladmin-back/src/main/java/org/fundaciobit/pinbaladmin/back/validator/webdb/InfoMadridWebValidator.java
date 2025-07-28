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
import org.fundaciobit.pinbaladmin.persistence.validator.InfoMadridValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.InfoMadridForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class InfoMadridWebValidator extends AbstractWebValidator<InfoMadridForm, InfoMadrid>
     implements Validator, InfoMadridFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected InfoMadridValidator<InfoMadrid> validator = new InfoMadridValidator<InfoMadrid>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.InfoMadridService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.InfoMadridService infoMadridEjb;



  public InfoMadridWebValidator() {
    super();    
  }
  
  @Override
  public InfoMadrid getBeanOfForm(InfoMadridForm form) {
    return  form.getInfoMadrid();
  }

  @Override
  public Class<InfoMadridForm> getClassOfForm() {
    return InfoMadridForm.class;
  }

  @Override
  public void validate(InfoMadridForm __form, InfoMadrid __bean, Errors errors) {

    WebValidationResult<InfoMadridForm> wvr;
    wvr = new WebValidationResult<InfoMadridForm>(errors);

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


  public void validate(InfoMadridForm __form, InfoMadrid __bean, Errors errors,
    WebValidationResult<InfoMadridForm> wvr, boolean isNou) {

    BeanValidatorResult<InfoMadrid> __vr = new BeanValidatorResult<InfoMadrid>();
    validator.validate(__vr, __bean,
      isNou, infoMadridEjb);

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

  public InfoMadridValidator<InfoMadrid> getValidator() {
    return validator;
  }

  public void setValidator(InfoMadridValidator<InfoMadrid> validator) {
    this.validator = validator;
  }

}