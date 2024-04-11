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
import org.fundaciobit.pinbaladmin.persistence.validator.TramitIServValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TramitIServWebValidator extends AbstractWebValidator<TramitIServForm, TramitIServ>
     implements Validator, TramitIServFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TramitIServValidator<TramitIServ> validator = new TramitIServValidator<TramitIServ>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService tramitAPersAutEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.TramitIServService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.TramitIServService tramitIServEjb;



  public TramitIServWebValidator() {
    super();    
  }
  
  @Override
  public TramitIServ getBeanOfForm(TramitIServForm form) {
    return  form.getTramitIServ();
  }

  @Override
  public Class<TramitIServForm> getClassOfForm() {
    return TramitIServForm.class;
  }

  @Override
  public void validate(TramitIServForm __form, TramitIServ __bean, Errors errors) {

    WebValidationResult<TramitIServForm> wvr;
    wvr = new WebValidationResult<TramitIServForm>(errors);

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


  public void validate(TramitIServForm __form, TramitIServ __bean, Errors errors,
    WebValidationResult<TramitIServForm> wvr, boolean isNou) {

    BeanValidatorResult<TramitIServ> __vr = new BeanValidatorResult<TramitIServ>();
    validator.validate(__vr, __bean,
      isNou, tramitAPersAutEjb, tramitIServEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
        if (!errors.hasFieldErrors(get(FITXERNORMAID))){
            CommonsMultipartFile fitxernormaID = ((TramitIServForm)__form).getFitxernormaID();
            if (fitxernormaID == null || fitxernormaID.isEmpty()) {
                errors.rejectValue(get(FITXERNORMAID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERNORMAID)) },
                null);
            }
        }

    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public TramitIServValidator<TramitIServ> getValidator() {
    return validator;
  }

  public void setValidator(TramitIServValidator<TramitIServ> validator) {
    this.validator = validator;
  }

}