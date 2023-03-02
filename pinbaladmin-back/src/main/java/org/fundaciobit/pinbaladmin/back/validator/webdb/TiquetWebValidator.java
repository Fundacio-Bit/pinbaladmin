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
import org.fundaciobit.pinbaladmin.persistence.validator.TiquetValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TiquetForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Tiquet;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TiquetWebValidator extends AbstractWebValidator<TiquetForm, Tiquet>
     implements Validator, TiquetFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TiquetValidator<Tiquet> validator = new TiquetValidator<Tiquet>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatTiquetService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatTiquetService estatTiquetEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TipusTiquetService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TipusTiquetService tipusTiquetEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TiquetService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TiquetService tiquetEjb;



  public TiquetWebValidator() {
    super();    
  }
  
  @Override
  public Tiquet getBeanOfForm(TiquetForm form) {
    return  form.getTiquet();
  }

  @Override
  public Class<TiquetForm> getClassOfForm() {
    return TiquetForm.class;
  }

  @Override
  public void validate(TiquetForm __form, Tiquet __bean, Errors errors) {

    WebValidationResult<TiquetForm> wvr;
    wvr = new WebValidationResult<TiquetForm>(errors);

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


  public void validate(TiquetForm __form, Tiquet __bean, Errors errors,
    WebValidationResult<TiquetForm> wvr, boolean isNou) {

    BeanValidatorResult<Tiquet> __vr = new BeanValidatorResult<Tiquet>();
    validator.validate(__vr, __bean,
      isNou, estatTiquetEjb, tipusTiquetEjb, tiquetEjb);

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

  public TiquetValidator<Tiquet> getValidator() {
    return validator;
  }

  public void setValidator(TiquetValidator<Tiquet> validator) {
    this.validator = validator;
  }

}