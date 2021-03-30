package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.jpa.validator.FormulariValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.FormulariForm;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class FormulariWebValidator  implements Validator, FormulariFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected FormulariValidator<Object> validator = new FormulariValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "pinbaladmin/FormulariEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.FormulariLocal formulariEjb;



  public FormulariWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return FormulariForm.class.isAssignableFrom(clazz);
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
      CommonsMultipartFile fitxerID = ((FormulariForm)target).getFitxerID();
      if (fitxerID == null || fitxerID.isEmpty()) {
        errors.rejectValue(get(FITXERID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERID)) },
          null);
      }

    }
    validator.validate(wvr, target,
      isNou, formulariEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public FormulariValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(FormulariValidator<Object> validator) {
    this.validator = validator;
  }

}