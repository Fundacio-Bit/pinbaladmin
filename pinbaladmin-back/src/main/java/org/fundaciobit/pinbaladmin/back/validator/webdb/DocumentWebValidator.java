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
import org.fundaciobit.pinbaladmin.persistence.validator.DocumentValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class DocumentWebValidator extends AbstractWebValidator<DocumentForm, Document>
     implements Validator, DocumentFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected DocumentValidator<Document> validator = new DocumentValidator<Document>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentService documentEjb;



  public DocumentWebValidator() {
    super();    
  }
  
  @Override
  public Document getBeanOfForm(DocumentForm form) {
    return  form.getDocument();
  }

  @Override
  public Class<DocumentForm> getClassOfForm() {
    return DocumentForm.class;
  }

  @Override
  public void validate(DocumentForm __form, Document __bean, Errors errors) {

    WebValidationResult<DocumentForm> wvr;
    wvr = new WebValidationResult<DocumentForm>(errors);

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


  public void validate(DocumentForm __form, Document __bean, Errors errors,
    WebValidationResult<DocumentForm> wvr, boolean isNou) {

    BeanValidatorResult<Document> __vr = new BeanValidatorResult<Document>();
    validator.validate(__vr, __bean,
      isNou, documentEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
        if (!errors.hasFieldErrors(get(FITXERORIGINALID))){
            CommonsMultipartFile fitxerOriginalID = ((DocumentForm)__form).getFitxerOriginalID();
            if (fitxerOriginalID == null || fitxerOriginalID.isEmpty()) {
                errors.rejectValue(get(FITXERORIGINALID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERORIGINALID)) },
                null);
            }
        }

    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public DocumentValidator<Document> getValidator() {
    return validator;
  }

  public void setValidator(DocumentValidator<Document> validator) {
    this.validator = validator;
  }

}