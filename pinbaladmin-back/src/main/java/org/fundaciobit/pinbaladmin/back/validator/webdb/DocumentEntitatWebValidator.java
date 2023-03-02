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
import org.fundaciobit.pinbaladmin.persistence.validator.DocumentEntitatValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentEntitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.DocumentEntitat;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class DocumentEntitatWebValidator extends AbstractWebValidator<DocumentEntitatForm, DocumentEntitat>
     implements Validator, DocumentEntitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected DocumentEntitatValidator<DocumentEntitat> validator = new DocumentEntitatValidator<DocumentEntitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentEntitatService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentEntitatService documentEntitatEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatService entitatEjb;



  public DocumentEntitatWebValidator() {
    super();    
  }
  
  @Override
  public DocumentEntitat getBeanOfForm(DocumentEntitatForm form) {
    return  form.getDocumentEntitat();
  }

  @Override
  public Class<DocumentEntitatForm> getClassOfForm() {
    return DocumentEntitatForm.class;
  }

  @Override
  public void validate(DocumentEntitatForm __form, DocumentEntitat __bean, Errors errors) {

    WebValidationResult<DocumentEntitatForm> wvr;
    wvr = new WebValidationResult<DocumentEntitatForm>(errors);

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


  public void validate(DocumentEntitatForm __form, DocumentEntitat __bean, Errors errors,
    WebValidationResult<DocumentEntitatForm> wvr, boolean isNou) {

    BeanValidatorResult<DocumentEntitat> __vr = new BeanValidatorResult<DocumentEntitat>();
    validator.validate(__vr, __bean,
      isNou, documentEntitatEjb, entitatEjb);

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

  public DocumentEntitatValidator<DocumentEntitat> getValidator() {
    return validator;
  }

  public void setValidator(DocumentEntitatValidator<DocumentEntitat> validator) {
    this.validator = validator;
  }

}