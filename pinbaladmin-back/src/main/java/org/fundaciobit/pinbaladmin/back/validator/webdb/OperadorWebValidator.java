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
import org.fundaciobit.pinbaladmin.persistence.validator.OperadorValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.OperadorForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Operador;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class OperadorWebValidator extends AbstractWebValidator<OperadorForm, Operador>
     implements Validator, OperadorFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected OperadorValidator<Operador> validator = new OperadorValidator<Operador>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OperadorService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.OperadorService operadorEjb;



  public OperadorWebValidator() {
    super();    
  }
  
  @Override
  public Operador getBeanOfForm(OperadorForm form) {
    return  form.getOperador();
  }

  @Override
  public Class<OperadorForm> getClassOfForm() {
    return OperadorForm.class;
  }

  @Override
  public void validate(OperadorForm __form, Operador __bean, Errors errors) {

    WebValidationResult<OperadorForm> wvr;
    wvr = new WebValidationResult<OperadorForm>(errors);

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


  public void validate(OperadorForm __form, Operador __bean, Errors errors,
    WebValidationResult<OperadorForm> wvr, boolean isNou) {

    BeanValidatorResult<Operador> __vr = new BeanValidatorResult<Operador>();
    validator.validate(__vr, __bean,
      isNou, operadorEjb);

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

  public OperadorValidator<Operador> getValidator() {
    return validator;
  }

  public void setValidator(OperadorValidator<Operador> validator) {
    this.validator = validator;
  }

}