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
import org.fundaciobit.pinbaladmin.persistence.validator.ModificacioSolicitudValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSolicitudForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.ModificacioSolicitud;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ModificacioSolicitudWebValidator extends AbstractWebValidator<ModificacioSolicitudForm, ModificacioSolicitud>
     implements Validator, ModificacioSolicitudFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected ModificacioSolicitudValidator<ModificacioSolicitud> validator = new ModificacioSolicitudValidator<ModificacioSolicitud>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ModificacioSolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ModificacioSolicitudService modificacioSolicitudEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OrganService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.OrganService organEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;



  public ModificacioSolicitudWebValidator() {
    super();    
  }
  
  @Override
  public ModificacioSolicitud getBeanOfForm(ModificacioSolicitudForm form) {
    return  form.getModificacioSolicitud();
  }

  @Override
  public Class<ModificacioSolicitudForm> getClassOfForm() {
    return ModificacioSolicitudForm.class;
  }

  @Override
  public void validate(ModificacioSolicitudForm __form, ModificacioSolicitud __bean, Errors errors) {

    WebValidationResult<ModificacioSolicitudForm> wvr;
    wvr = new WebValidationResult<ModificacioSolicitudForm>(errors);

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


  public void validate(ModificacioSolicitudForm __form, ModificacioSolicitud __bean, Errors errors,
    WebValidationResult<ModificacioSolicitudForm> wvr, boolean isNou) {

    BeanValidatorResult<ModificacioSolicitud> __vr = new BeanValidatorResult<ModificacioSolicitud>();
    validator.validate(__vr, __bean,
      isNou, modificacioSolicitudEjb, organEjb, solicitudEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public ModificacioSolicitudValidator<ModificacioSolicitud> getValidator() {
    return validator;
  }

  public void setValidator(ModificacioSolicitudValidator<ModificacioSolicitud> validator) {
    this.validator = validator;
  }

}