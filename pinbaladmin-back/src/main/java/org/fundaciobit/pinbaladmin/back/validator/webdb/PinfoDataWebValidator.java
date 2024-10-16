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
import org.fundaciobit.pinbaladmin.persistence.validator.PinfoDataValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.PinfoDataForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PinfoDataWebValidator extends AbstractWebValidator<PinfoDataForm, PinfoData>
     implements Validator, PinfoDataFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PinfoDataValidator<PinfoData> validator = new PinfoDataValidator<PinfoData>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.PINFOService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.PINFOService pINFOEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.PinfoDataService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.PinfoDataService pinfoDataEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ServeiService serveiEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;



  public PinfoDataWebValidator() {
    super();    
  }
  
  @Override
  public PinfoData getBeanOfForm(PinfoDataForm form) {
    return  form.getPinfoData();
  }

  @Override
  public Class<PinfoDataForm> getClassOfForm() {
    return PinfoDataForm.class;
  }

  @Override
  public void validate(PinfoDataForm __form, PinfoData __bean, Errors errors) {

    WebValidationResult<PinfoDataForm> wvr;
    wvr = new WebValidationResult<PinfoDataForm>(errors);

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


  public void validate(PinfoDataForm __form, PinfoData __bean, Errors errors,
    WebValidationResult<PinfoDataForm> wvr, boolean isNou) {

    BeanValidatorResult<PinfoData> __vr = new BeanValidatorResult<PinfoData>();
    validator.validate(__vr, __bean,
      isNou, pINFOEjb, pinfoDataEjb, serveiEjb, solicitudEjb);

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

  public PinfoDataValidator<PinfoData> getValidator() {
    return validator;
  }

  public void setValidator(PinfoDataValidator<PinfoData> validator) {
    this.validator = validator;
  }

}