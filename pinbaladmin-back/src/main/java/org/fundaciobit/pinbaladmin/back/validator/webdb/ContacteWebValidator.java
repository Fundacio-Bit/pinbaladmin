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
import org.fundaciobit.pinbaladmin.persistence.validator.ContacteValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.ContacteForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ContacteWebValidator extends AbstractWebValidator<ContacteForm, Contacte>
     implements Validator, ContacteFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected ContacteValidator<Contacte> validator = new ContacteValidator<Contacte>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ContacteService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ContacteService contacteEjb;



  public ContacteWebValidator() {
    super();    
  }
  
  @Override
  public Contacte getBeanOfForm(ContacteForm form) {
    return  form.getContacte();
  }

  @Override
  public Class<ContacteForm> getClassOfForm() {
    return ContacteForm.class;
  }

  @Override
  public void validate(ContacteForm __form, Contacte __bean, Errors errors) {

    WebValidationResult<ContacteForm> wvr;
    wvr = new WebValidationResult<ContacteForm>(errors);

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


  public void validate(ContacteForm __form, Contacte __bean, Errors errors,
    WebValidationResult<ContacteForm> wvr, boolean isNou) {

    BeanValidatorResult<Contacte> __vr = new BeanValidatorResult<Contacte>();
    validator.validate(__vr, __bean,
      isNou, contacteEjb);

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

  public ContacteValidator<Contacte> getValidator() {
    return validator;
  }

  public void setValidator(ContacteValidator<Contacte> validator) {
    this.validator = validator;
  }

}