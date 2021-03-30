package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.DocumentEntitatValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentEntitatForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class DocumentEntitatWebValidator  implements Validator, DocumentEntitatFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected DocumentEntitatValidator<Object> validator = new DocumentEntitatValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/DocumentEntitatEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.DocumentEntitatLocal documentEntitatEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/EntitatEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EntitatLocal entitatEjb;



  public DocumentEntitatWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return DocumentEntitatForm.class.isAssignableFrom(clazz);
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
    }
    validator.validate(wvr, target,
      isNou, documentEntitatEjb, entitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public DocumentEntitatValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(DocumentEntitatValidator<Object> validator) {
    this.validator = validator;
  }

}