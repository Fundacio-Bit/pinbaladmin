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
import org.fundaciobit.pinbaladmin.persistence.validator.IncidenciaTecnicaValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class IncidenciaTecnicaWebValidator extends AbstractWebValidator<IncidenciaTecnicaForm, IncidenciaTecnica>
     implements Validator, IncidenciaTecnicaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected IncidenciaTecnicaValidator<IncidenciaTecnica> validator = new IncidenciaTecnicaValidator<IncidenciaTecnica>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaService incidenciaTecnicaEjb;



  public IncidenciaTecnicaWebValidator() {
    super();    
  }
  
  @Override
  public IncidenciaTecnica getBeanOfForm(IncidenciaTecnicaForm form) {
    return  form.getIncidenciaTecnica();
  }

  @Override
  public Class<IncidenciaTecnicaForm> getClassOfForm() {
    return IncidenciaTecnicaForm.class;
  }

  @Override
  public void validate(IncidenciaTecnicaForm __form, IncidenciaTecnica __bean, Errors errors) {

    WebValidationResult<IncidenciaTecnicaForm> wvr;
    wvr = new WebValidationResult<IncidenciaTecnicaForm>(errors);

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


  public void validate(IncidenciaTecnicaForm __form, IncidenciaTecnica __bean, Errors errors,
    WebValidationResult<IncidenciaTecnicaForm> wvr, boolean isNou) {

    BeanValidatorResult<IncidenciaTecnica> __vr = new BeanValidatorResult<IncidenciaTecnica>();
    validator.validate(__vr, __bean,
      isNou, incidenciaTecnicaEjb);

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

  public IncidenciaTecnicaValidator<IncidenciaTecnica> getValidator() {
    return validator;
  }

  public void setValidator(IncidenciaTecnicaValidator<IncidenciaTecnica> validator) {
    this.validator = validator;
  }

}