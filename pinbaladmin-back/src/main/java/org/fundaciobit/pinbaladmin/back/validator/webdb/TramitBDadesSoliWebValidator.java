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
import org.fundaciobit.pinbaladmin.persistence.validator.TramitBDadesSoliValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TramitBDadesSoliWebValidator extends AbstractWebValidator<TramitBDadesSoliForm, TramitBDadesSoli>
     implements Validator, TramitBDadesSoliFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TramitBDadesSoliValidator<TramitBDadesSoli> validator = new TramitBDadesSoliValidator<TramitBDadesSoli>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService tramitAPersAutEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitBDadesSoliService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitBDadesSoliService tramitBDadesSoliEjb;



  public TramitBDadesSoliWebValidator() {
    super();    
  }
  
  @Override
  public TramitBDadesSoli getBeanOfForm(TramitBDadesSoliForm form) {
    return  form.getTramitBDadesSoli();
  }

  @Override
  public Class<TramitBDadesSoliForm> getClassOfForm() {
    return TramitBDadesSoliForm.class;
  }

  @Override
  public void validate(TramitBDadesSoliForm __form, TramitBDadesSoli __bean, Errors errors) {

    WebValidationResult<TramitBDadesSoliForm> wvr;
    wvr = new WebValidationResult<TramitBDadesSoliForm>(errors);

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


  public void validate(TramitBDadesSoliForm __form, TramitBDadesSoli __bean, Errors errors,
    WebValidationResult<TramitBDadesSoliForm> wvr, boolean isNou) {

    BeanValidatorResult<TramitBDadesSoli> __vr = new BeanValidatorResult<TramitBDadesSoli>();
    validator.validate(__vr, __bean,
      isNou, tramitAPersAutEjb, tramitBDadesSoliEjb);

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

  public TramitBDadesSoliValidator<TramitBDadesSoli> getValidator() {
    return validator;
  }

  public void setValidator(TramitBDadesSoliValidator<TramitBDadesSoli> validator) {
    this.validator = validator;
  }

}