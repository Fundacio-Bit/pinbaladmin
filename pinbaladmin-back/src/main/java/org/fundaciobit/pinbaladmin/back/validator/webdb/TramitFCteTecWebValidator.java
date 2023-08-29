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
import org.fundaciobit.pinbaladmin.persistence.validator.TramitFCteTecValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TramitFCteTecWebValidator extends AbstractWebValidator<TramitFCteTecForm, TramitFCteTec>
     implements Validator, TramitFCteTecFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TramitFCteTecValidator<TramitFCteTec> validator = new TramitFCteTecValidator<TramitFCteTec>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService tramitAPersAutEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitFCteTecService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitFCteTecService tramitFCteTecEjb;



  public TramitFCteTecWebValidator() {
    super();    
  }
  
  @Override
  public TramitFCteTec getBeanOfForm(TramitFCteTecForm form) {
    return  form.getTramitFCteTec();
  }

  @Override
  public Class<TramitFCteTecForm> getClassOfForm() {
    return TramitFCteTecForm.class;
  }

  @Override
  public void validate(TramitFCteTecForm __form, TramitFCteTec __bean, Errors errors) {

    WebValidationResult<TramitFCteTecForm> wvr;
    wvr = new WebValidationResult<TramitFCteTecForm>(errors);

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


  public void validate(TramitFCteTecForm __form, TramitFCteTec __bean, Errors errors,
    WebValidationResult<TramitFCteTecForm> wvr, boolean isNou) {

    BeanValidatorResult<TramitFCteTec> __vr = new BeanValidatorResult<TramitFCteTec>();
    validator.validate(__vr, __bean,
      isNou, tramitAPersAutEjb, tramitFCteTecEjb);

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

  public TramitFCteTecValidator<TramitFCteTec> getValidator() {
    return validator;
  }

  public void setValidator(TramitFCteTecValidator<TramitFCteTec> validator) {
    this.validator = validator;
  }

}