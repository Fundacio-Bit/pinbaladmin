package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.DocumentSolicitudValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentSolicitudForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class DocumentSolicitudWebValidator  implements Validator, DocumentSolicitudFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected DocumentSolicitudValidator<Object> validator = new DocumentSolicitudValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/DocumentEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.DocumentLocal documentEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/DocumentSolicitudEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudLocal documentSolicitudEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/SolicitudEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudLocal solicitudEjb;



  public DocumentSolicitudWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return DocumentSolicitudForm.class.isAssignableFrom(clazz);
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

    validator.validate(wvr, target,
      isNou, documentEjb, documentSolicitudEjb, solicitudEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public DocumentSolicitudValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(DocumentSolicitudValidator<Object> validator) {
    this.validator = validator;
  }

}