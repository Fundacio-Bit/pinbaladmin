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
import org.fundaciobit.pinbaladmin.persistence.validator.ModificacioSoliServValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSoliServForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.ModificacioSoliServ;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ModificacioSoliServWebValidator extends AbstractWebValidator<ModificacioSoliServForm, ModificacioSoliServ>
     implements Validator, ModificacioSoliServFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected ModificacioSoliServValidator<ModificacioSoliServ> validator = new ModificacioSoliServValidator<ModificacioSoliServ>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ModificacioSoliServService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ModificacioSoliServService modificacioSoliServEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ModificacioSolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ModificacioSolicitudService modificacioSolicitudEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService solicitudServeiEjb;



  public ModificacioSoliServWebValidator() {
    super();    
  }
  
  @Override
  public ModificacioSoliServ getBeanOfForm(ModificacioSoliServForm form) {
    return  form.getModificacioSoliServ();
  }

  @Override
  public Class<ModificacioSoliServForm> getClassOfForm() {
    return ModificacioSoliServForm.class;
  }

  @Override
  public void validate(ModificacioSoliServForm __form, ModificacioSoliServ __bean, Errors errors) {

    WebValidationResult<ModificacioSoliServForm> wvr;
    wvr = new WebValidationResult<ModificacioSoliServForm>(errors);

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


  public void validate(ModificacioSoliServForm __form, ModificacioSoliServ __bean, Errors errors,
    WebValidationResult<ModificacioSoliServForm> wvr, boolean isNou) {

    BeanValidatorResult<ModificacioSoliServ> __vr = new BeanValidatorResult<ModificacioSoliServ>();
    validator.validate(__vr, __bean,
      isNou, modificacioSoliServEjb, modificacioSolicitudEjb, solicitudServeiEjb);

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

  public ModificacioSoliServValidator<ModificacioSoliServ> getValidator() {
    return validator;
  }

  public void setValidator(ModificacioSoliServValidator<ModificacioSoliServ> validator) {
    this.validator = validator;
  }

}