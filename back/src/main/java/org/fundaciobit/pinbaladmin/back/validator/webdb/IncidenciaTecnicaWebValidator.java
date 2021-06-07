package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.IncidenciaTecnicaValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class IncidenciaTecnicaWebValidator  implements Validator, IncidenciaTecnicaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected IncidenciaTecnicaValidator<Object> validator = new IncidenciaTecnicaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/IncidenciaTecnicaEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaLocal incidenciaTecnicaEjb;



  public IncidenciaTecnicaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return IncidenciaTecnicaForm.class.isAssignableFrom(clazz);
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
      isNou, incidenciaTecnicaEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public IncidenciaTecnicaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(IncidenciaTecnicaValidator<Object> validator) {
    this.validator = validator;
  }

}