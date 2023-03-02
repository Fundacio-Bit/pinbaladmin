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
import org.fundaciobit.pinbaladmin.persistence.validator.DepartamentValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.DepartamentForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Departament;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class DepartamentWebValidator extends AbstractWebValidator<DepartamentForm, Departament>
     implements Validator, DepartamentFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected DepartamentValidator<Departament> validator = new DepartamentValidator<Departament>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.AreaService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.AreaService areaEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DepartamentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DepartamentService departamentEjb;



  public DepartamentWebValidator() {
    super();    
  }
  
  @Override
  public Departament getBeanOfForm(DepartamentForm form) {
    return  form.getDepartament();
  }

  @Override
  public Class<DepartamentForm> getClassOfForm() {
    return DepartamentForm.class;
  }

  @Override
  public void validate(DepartamentForm __form, Departament __bean, Errors errors) {

    WebValidationResult<DepartamentForm> wvr;
    wvr = new WebValidationResult<DepartamentForm>(errors);

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


  public void validate(DepartamentForm __form, Departament __bean, Errors errors,
    WebValidationResult<DepartamentForm> wvr, boolean isNou) {

    BeanValidatorResult<Departament> __vr = new BeanValidatorResult<Departament>();
    validator.validate(__vr, __bean,
      isNou, areaEjb, departamentEjb);

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

  public DepartamentValidator<Departament> getValidator() {
    return validator;
  }

  public void setValidator(DepartamentValidator<Departament> validator) {
    this.validator = validator;
  }

}