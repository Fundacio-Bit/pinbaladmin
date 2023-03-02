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
import org.fundaciobit.pinbaladmin.persistence.validator.EstatTiquetValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.EstatTiquetForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.EstatTiquet;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EstatTiquetWebValidator extends AbstractWebValidator<EstatTiquetForm, EstatTiquet>
     implements Validator, EstatTiquetFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EstatTiquetValidator<EstatTiquet> validator = new EstatTiquetValidator<EstatTiquet>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatTiquetService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatTiquetService estatTiquetEjb;



  public EstatTiquetWebValidator() {
    super();    
  }
  
  @Override
  public EstatTiquet getBeanOfForm(EstatTiquetForm form) {
    return  form.getEstatTiquet();
  }

  @Override
  public Class<EstatTiquetForm> getClassOfForm() {
    return EstatTiquetForm.class;
  }

  @Override
  public void validate(EstatTiquetForm __form, EstatTiquet __bean, Errors errors) {

    WebValidationResult<EstatTiquetForm> wvr;
    wvr = new WebValidationResult<EstatTiquetForm>(errors);

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


  public void validate(EstatTiquetForm __form, EstatTiquet __bean, Errors errors,
    WebValidationResult<EstatTiquetForm> wvr, boolean isNou) {

    BeanValidatorResult<EstatTiquet> __vr = new BeanValidatorResult<EstatTiquet>();
    validator.validate(__vr, __bean,
      isNou, estatTiquetEjb);

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

  public EstatTiquetValidator<EstatTiquet> getValidator() {
    return validator;
  }

  public void setValidator(EstatTiquetValidator<EstatTiquet> validator) {
    this.validator = validator;
  }

}