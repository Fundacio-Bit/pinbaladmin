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
import org.fundaciobit.pinbaladmin.persistence.validator.CampFormulariValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.CampFormulariForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.CampFormulari;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class CampFormulariWebValidator extends AbstractWebValidator<CampFormulariForm, CampFormulari>
     implements Validator, CampFormulariFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected CampFormulariValidator<CampFormulari> validator = new CampFormulariValidator<CampFormulari>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.CampFormulariService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.CampFormulariService campFormulariEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FormulariService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.FormulariService formulariEjb;



  public CampFormulariWebValidator() {
    super();    
  }
  
  @Override
  public CampFormulari getBeanOfForm(CampFormulariForm form) {
    return  form.getCampFormulari();
  }

  @Override
  public Class<CampFormulariForm> getClassOfForm() {
    return CampFormulariForm.class;
  }

  @Override
  public void validate(CampFormulariForm __form, CampFormulari __bean, Errors errors) {

    WebValidationResult<CampFormulariForm> wvr;
    wvr = new WebValidationResult<CampFormulariForm>(errors);

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


  public void validate(CampFormulariForm __form, CampFormulari __bean, Errors errors,
    WebValidationResult<CampFormulariForm> wvr, boolean isNou) {

    BeanValidatorResult<CampFormulari> __vr = new BeanValidatorResult<CampFormulari>();
    validator.validate(__vr, __bean,
      isNou, campFormulariEjb, formulariEjb);

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

  public CampFormulariValidator<CampFormulari> getValidator() {
    return validator;
  }

  public void setValidator(CampFormulariValidator<CampFormulari> validator) {
    this.validator = validator;
  }

}