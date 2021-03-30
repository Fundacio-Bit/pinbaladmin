package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.ServeiValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.ServeiForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ServeiWebValidator  implements Validator, ServeiFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected ServeiValidator<Object> validator = new ServeiValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/EntitatServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EntitatServeiLocal entitatServeiEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/EstatServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EstatServeiLocal estatServeiEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/FormulariEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.FormulariLocal formulariEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/ServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.ServeiLocal serveiEjb;



  public ServeiWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return ServeiForm.class.isAssignableFrom(clazz);
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
      isNou, entitatServeiEjb, estatServeiEjb, formulariEjb, serveiEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public ServeiValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(ServeiValidator<Object> validator) {
    this.validator = validator;
  }

}