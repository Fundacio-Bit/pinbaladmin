package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.DocumentValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentForm;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class DocumentWebValidator  implements Validator, DocumentFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected DocumentValidator<Object> validator = new DocumentValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/DocumentEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.DocumentLocal documentEjb;



  public DocumentWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return DocumentForm.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    WebValidationResult<Object> wvr;
    wvr = new WebValidationResult<Object>(errors);

    Boolean nou = (Boolean)errors.getFieldValue("nou");
    boolean isNou =  nou != null && nou.booleanValue();

    validate(target, errors, wvr, isNou);
  }


  public void validate(Object target, Errors errors,
    WebValidationResult<Object> wvr, boolean isNou) {

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
      CommonsMultipartFile fitxerOriginalID = ((DocumentForm)target).getFitxerOriginalID();
      if (fitxerOriginalID == null || fitxerOriginalID.isEmpty()) {
        errors.rejectValue(get(FITXERORIGINALID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERORIGINALID)) },
          null);
      }

    }
    validator.validate(wvr, target,
      isNou, documentEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public DocumentValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(DocumentValidator<Object> validator) {
    this.validator = validator;
  }

}