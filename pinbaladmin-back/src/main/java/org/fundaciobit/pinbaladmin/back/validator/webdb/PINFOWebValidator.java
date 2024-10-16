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
import org.fundaciobit.pinbaladmin.persistence.validator.PINFOValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.PINFOForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.PINFO;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PINFOWebValidator extends AbstractWebValidator<PINFOForm, PINFO>
     implements Validator, PINFOFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PINFOValidator<PINFO> validator = new PINFOValidator<PINFO>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaService incidenciaTecnicaEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.PINFOService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.PINFOService pINFOEjb;



  public PINFOWebValidator() {
    super();    
  }
  
  @Override
  public PINFO getBeanOfForm(PINFOForm form) {
    return  form.getPINFO();
  }

  @Override
  public Class<PINFOForm> getClassOfForm() {
    return PINFOForm.class;
  }

  @Override
  public void validate(PINFOForm __form, PINFO __bean, Errors errors) {

    WebValidationResult<PINFOForm> wvr;
    wvr = new WebValidationResult<PINFOForm>(errors);

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


  public void validate(PINFOForm __form, PINFO __bean, Errors errors,
    WebValidationResult<PINFOForm> wvr, boolean isNou) {

    BeanValidatorResult<PINFO> __vr = new BeanValidatorResult<PINFO>();
    validator.validate(__vr, __bean,
      isNou, incidenciaTecnicaEjb, pINFOEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PINFOValidator<PINFO> getValidator() {
    return validator;
  }

  public void setValidator(PINFOValidator<PINFO> validator) {
    this.validator = validator;
  }

}