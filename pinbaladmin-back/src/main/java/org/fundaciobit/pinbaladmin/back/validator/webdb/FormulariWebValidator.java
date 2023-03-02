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
import org.fundaciobit.pinbaladmin.persistence.validator.FormulariValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.FormulariForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Formulari;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class FormulariWebValidator extends AbstractWebValidator<FormulariForm, Formulari>
     implements Validator, FormulariFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected FormulariValidator<Formulari> validator = new FormulariValidator<Formulari>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FormulariService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.FormulariService formulariEjb;



  public FormulariWebValidator() {
    super();    
  }
  
  @Override
  public Formulari getBeanOfForm(FormulariForm form) {
    return  form.getFormulari();
  }

  @Override
  public Class<FormulariForm> getClassOfForm() {
    return FormulariForm.class;
  }

  @Override
  public void validate(FormulariForm __form, Formulari __bean, Errors errors) {

    WebValidationResult<FormulariForm> wvr;
    wvr = new WebValidationResult<FormulariForm>(errors);

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


  public void validate(FormulariForm __form, Formulari __bean, Errors errors,
    WebValidationResult<FormulariForm> wvr, boolean isNou) {

    BeanValidatorResult<Formulari> __vr = new BeanValidatorResult<Formulari>();
    validator.validate(__vr, __bean,
      isNou, formulariEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
        if (!errors.hasFieldErrors(get(FITXERID))){
            CommonsMultipartFile fitxerID = ((FormulariForm)__form).getFitxerID();
            if (fitxerID == null || fitxerID.isEmpty()) {
                errors.rejectValue(get(FITXERID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERID)) },
                null);
            }
        }

    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public FormulariValidator<Formulari> getValidator() {
    return validator;
  }

  public void setValidator(FormulariValidator<Formulari> validator) {
    this.validator = validator;
  }

}