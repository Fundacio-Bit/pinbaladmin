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
import org.fundaciobit.pinbaladmin.persistence.validator.DocumentCedentValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentCedentForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.DocumentCedent;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class DocumentCedentWebValidator extends AbstractWebValidator<DocumentCedentForm, DocumentCedent>
     implements Validator, DocumentCedentFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected DocumentCedentValidator<DocumentCedent> validator = new DocumentCedentValidator<DocumentCedent>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentCedentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentCedentService documentCedentEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatServeiService entitatServeiEjb;



  public DocumentCedentWebValidator() {
    super();    
  }
  
  @Override
  public DocumentCedent getBeanOfForm(DocumentCedentForm form) {
    return  form.getDocumentCedent();
  }

  @Override
  public Class<DocumentCedentForm> getClassOfForm() {
    return DocumentCedentForm.class;
  }

  @Override
  public void validate(DocumentCedentForm __form, DocumentCedent __bean, Errors errors) {

    WebValidationResult<DocumentCedentForm> wvr;
    wvr = new WebValidationResult<DocumentCedentForm>(errors);

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


  public void validate(DocumentCedentForm __form, DocumentCedent __bean, Errors errors,
    WebValidationResult<DocumentCedentForm> wvr, boolean isNou) {

    BeanValidatorResult<DocumentCedent> __vr = new BeanValidatorResult<DocumentCedent>();
    validator.validate(__vr, __bean,
      isNou, documentCedentEjb, entitatServeiEjb);

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

  public DocumentCedentValidator<DocumentCedent> getValidator() {
    return validator;
  }

  public void setValidator(DocumentCedentValidator<DocumentCedent> validator) {
    this.validator = validator;
  }

}