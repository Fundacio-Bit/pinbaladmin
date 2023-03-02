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
import org.fundaciobit.pinbaladmin.persistence.validator.TipusTiquetValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TipusTiquetForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.TipusTiquet;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusTiquetWebValidator extends AbstractWebValidator<TipusTiquetForm, TipusTiquet>
     implements Validator, TipusTiquetFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TipusTiquetValidator<TipusTiquet> validator = new TipusTiquetValidator<TipusTiquet>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TipusTiquetService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TipusTiquetService tipusTiquetEjb;



  public TipusTiquetWebValidator() {
    super();    
  }
  
  @Override
  public TipusTiquet getBeanOfForm(TipusTiquetForm form) {
    return  form.getTipusTiquet();
  }

  @Override
  public Class<TipusTiquetForm> getClassOfForm() {
    return TipusTiquetForm.class;
  }

  @Override
  public void validate(TipusTiquetForm __form, TipusTiquet __bean, Errors errors) {

    WebValidationResult<TipusTiquetForm> wvr;
    wvr = new WebValidationResult<TipusTiquetForm>(errors);

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


  public void validate(TipusTiquetForm __form, TipusTiquet __bean, Errors errors,
    WebValidationResult<TipusTiquetForm> wvr, boolean isNou) {

    BeanValidatorResult<TipusTiquet> __vr = new BeanValidatorResult<TipusTiquet>();
    validator.validate(__vr, __bean,
      isNou, tipusTiquetEjb);

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

  public TipusTiquetValidator<TipusTiquet> getValidator() {
    return validator;
  }

  public void setValidator(TipusTiquetValidator<TipusTiquet> validator) {
    this.validator = validator;
  }

}