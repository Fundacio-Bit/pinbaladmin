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
import org.fundaciobit.pinbaladmin.persistence.validator.CampSolicitudValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.CampSolicitudForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.CampSolicitud;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class CampSolicitudWebValidator extends AbstractWebValidator<CampSolicitudForm, CampSolicitud>
     implements Validator, CampSolicitudFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected CampSolicitudValidator<CampSolicitud> validator = new CampSolicitudValidator<CampSolicitud>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.CampFormulariService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.CampFormulariService campFormulariEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.CampSolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.CampSolicitudService campSolicitudEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService solicitudServeiEjb;



  public CampSolicitudWebValidator() {
    super();    
  }
  
  @Override
  public CampSolicitud getBeanOfForm(CampSolicitudForm form) {
    return  form.getCampSolicitud();
  }

  @Override
  public Class<CampSolicitudForm> getClassOfForm() {
    return CampSolicitudForm.class;
  }

  @Override
  public void validate(CampSolicitudForm __form, CampSolicitud __bean, Errors errors) {

    WebValidationResult<CampSolicitudForm> wvr;
    wvr = new WebValidationResult<CampSolicitudForm>(errors);

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


  public void validate(CampSolicitudForm __form, CampSolicitud __bean, Errors errors,
    WebValidationResult<CampSolicitudForm> wvr, boolean isNou) {

    BeanValidatorResult<CampSolicitud> __vr = new BeanValidatorResult<CampSolicitud>();
    validator.validate(__vr, __bean,
      isNou, campFormulariEjb, campSolicitudEjb, solicitudServeiEjb);

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

  public CampSolicitudValidator<CampSolicitud> getValidator() {
    return validator;
  }

  public void setValidator(CampSolicitudValidator<CampSolicitud> validator) {
    this.validator = validator;
  }

}