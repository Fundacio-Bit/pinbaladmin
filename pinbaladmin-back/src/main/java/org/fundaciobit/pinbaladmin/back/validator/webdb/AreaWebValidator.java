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
import org.fundaciobit.pinbaladmin.persistence.validator.AreaValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.AreaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Area;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class AreaWebValidator extends AbstractWebValidator<AreaForm, Area>
     implements Validator, AreaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected AreaValidator<Area> validator = new AreaValidator<Area>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.AreaService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.AreaService areaEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatService entitatEjb;



  public AreaWebValidator() {
    super();    
  }
  
  @Override
  public Area getBeanOfForm(AreaForm form) {
    return  form.getArea();
  }

  @Override
  public Class<AreaForm> getClassOfForm() {
    return AreaForm.class;
  }

  @Override
  public void validate(AreaForm __form, Area __bean, Errors errors) {

    WebValidationResult<AreaForm> wvr;
    wvr = new WebValidationResult<AreaForm>(errors);

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


  public void validate(AreaForm __form, Area __bean, Errors errors,
    WebValidationResult<AreaForm> wvr, boolean isNou) {

    BeanValidatorResult<Area> __vr = new BeanValidatorResult<Area>();
    validator.validate(__vr, __bean,
      isNou, areaEjb, entitatEjb);

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

  public AreaValidator<Area> getValidator() {
    return validator;
  }

  public void setValidator(AreaValidator<Area> validator) {
    this.validator = validator;
  }

}