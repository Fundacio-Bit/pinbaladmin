package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.DocumentCedentValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentCedentForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class DocumentCedentWebValidator  implements Validator, DocumentCedentFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected DocumentCedentValidator<Object> validator = new DocumentCedentValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/DocumentCedentEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.DocumentCedentLocal documentCedentEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/EntitatServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EntitatServeiLocal entitatServeiEjb;



  public DocumentCedentWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return DocumentCedentForm.class.isAssignableFrom(clazz);
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
      isNou, documentCedentEjb, entitatServeiEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public DocumentCedentValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(DocumentCedentValidator<Object> validator) {
    this.validator = validator;
  }

}