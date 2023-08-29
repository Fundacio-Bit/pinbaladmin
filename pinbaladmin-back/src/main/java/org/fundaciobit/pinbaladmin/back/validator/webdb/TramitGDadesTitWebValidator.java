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
import org.fundaciobit.pinbaladmin.persistence.validator.TramitGDadesTitValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.TramitGDadesTit;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TramitGDadesTitWebValidator extends AbstractWebValidator<TramitGDadesTitForm, TramitGDadesTit>
     implements Validator, TramitGDadesTitFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TramitGDadesTitValidator<TramitGDadesTit> validator = new TramitGDadesTitValidator<TramitGDadesTit>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService tramitAPersAutEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitGDadesTitService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitGDadesTitService tramitGDadesTitEjb;



  public TramitGDadesTitWebValidator() {
    super();    
  }
  
  @Override
  public TramitGDadesTit getBeanOfForm(TramitGDadesTitForm form) {
    return  form.getTramitGDadesTit();
  }

  @Override
  public Class<TramitGDadesTitForm> getClassOfForm() {
    return TramitGDadesTitForm.class;
  }

  @Override
  public void validate(TramitGDadesTitForm __form, TramitGDadesTit __bean, Errors errors) {

    WebValidationResult<TramitGDadesTitForm> wvr;
    wvr = new WebValidationResult<TramitGDadesTitForm>(errors);

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


  public void validate(TramitGDadesTitForm __form, TramitGDadesTit __bean, Errors errors,
    WebValidationResult<TramitGDadesTitForm> wvr, boolean isNou) {

    BeanValidatorResult<TramitGDadesTit> __vr = new BeanValidatorResult<TramitGDadesTit>();
    validator.validate(__vr, __bean,
      isNou, tramitAPersAutEjb, tramitGDadesTitEjb);

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

  public TramitGDadesTitValidator<TramitGDadesTit> getValidator() {
    return validator;
  }

  public void setValidator(TramitGDadesTitValidator<TramitGDadesTit> validator) {
    this.validator = validator;
  }

}