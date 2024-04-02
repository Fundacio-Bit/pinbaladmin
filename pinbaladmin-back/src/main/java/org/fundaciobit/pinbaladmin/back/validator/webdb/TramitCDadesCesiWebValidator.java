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
import org.fundaciobit.pinbaladmin.persistence.validator.TramitCDadesCesiValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TramitCDadesCesiWebValidator extends AbstractWebValidator<TramitCDadesCesiForm, TramitCDadesCesi>
     implements Validator, TramitCDadesCesiFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TramitCDadesCesiValidator<TramitCDadesCesi> validator = new TramitCDadesCesiValidator<TramitCDadesCesi>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService tramitAPersAutEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitCDadesCesiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitCDadesCesiService tramitCDadesCesiEjb;



  public TramitCDadesCesiWebValidator() {
    super();    
  }
  
  @Override
  public TramitCDadesCesi getBeanOfForm(TramitCDadesCesiForm form) {
    return  form.getTramitCDadesCesi();
  }

  @Override
  public Class<TramitCDadesCesiForm> getClassOfForm() {
    return TramitCDadesCesiForm.class;
  }

  @Override
  public void validate(TramitCDadesCesiForm __form, TramitCDadesCesi __bean, Errors errors) {

    WebValidationResult<TramitCDadesCesiForm> wvr;
    wvr = new WebValidationResult<TramitCDadesCesiForm>(errors);

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


  public void validate(TramitCDadesCesiForm __form, TramitCDadesCesi __bean, Errors errors,
    WebValidationResult<TramitCDadesCesiForm> wvr, boolean isNou) {

    BeanValidatorResult<TramitCDadesCesi> __vr = new BeanValidatorResult<TramitCDadesCesi>();
    validator.validate(__vr, __bean,
      isNou, tramitAPersAutEjb, tramitCDadesCesiEjb);

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

  public TramitCDadesCesiValidator<TramitCDadesCesi> getValidator() {
    return validator;
  }

  public void setValidator(TramitCDadesCesiValidator<TramitCDadesCesi> validator) {
    this.validator = validator;
  }

}