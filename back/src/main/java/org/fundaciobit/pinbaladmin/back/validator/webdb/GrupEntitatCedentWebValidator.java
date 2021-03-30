package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.GrupEntitatCedentValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.GrupEntitatCedentForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class GrupEntitatCedentWebValidator  implements Validator, GrupEntitatCedentFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected GrupEntitatCedentValidator<Object> validator = new GrupEntitatCedentValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/EntitatServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EntitatServeiLocal entitatServeiEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/GrupEntitatEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.GrupEntitatLocal grupEntitatEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/GrupEntitatCedentEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.GrupEntitatCedentLocal grupEntitatCedentEjb;



  public GrupEntitatCedentWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return GrupEntitatCedentForm.class.isAssignableFrom(clazz);
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
      isNou, entitatServeiEjb, grupEntitatEjb, grupEntitatCedentEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public GrupEntitatCedentValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(GrupEntitatCedentValidator<Object> validator) {
    this.validator = validator;
  }

}