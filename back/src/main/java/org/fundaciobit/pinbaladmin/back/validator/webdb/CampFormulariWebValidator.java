package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.CampFormulariValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.CampFormulariForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class CampFormulariWebValidator  implements Validator, CampFormulariFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected CampFormulariValidator<Object> validator = new CampFormulariValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/CampFormulariEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.CampFormulariLocal campFormulariEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/FormulariEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.FormulariLocal formulariEjb;



  public CampFormulariWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return CampFormulariForm.class.isAssignableFrom(clazz);
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
      isNou, campFormulariEjb, formulariEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public CampFormulariValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(CampFormulariValidator<Object> validator) {
    this.validator = validator;
  }

}