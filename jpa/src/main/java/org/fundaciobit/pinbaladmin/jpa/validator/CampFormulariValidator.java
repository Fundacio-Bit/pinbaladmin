package org.fundaciobit.pinbaladmin.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.CampFormulariFields;
import org.fundaciobit.pinbaladmin.model.fields.FormulariFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class CampFormulariValidator<T> implements CampFormulariFields {

  protected final Logger log = Logger.getLogger(getClass());


  public CampFormulariValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.ICampFormulariManager __campFormulariManager
    ,org.fundaciobit.pinbaladmin.model.dao.IFormulariManager __formulariManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CAMPPDF, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CAMPPDF)));

    __vr.rejectIfEmptyOrWhitespace(__target__,FORMULARIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FORMULARIID)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = (java.lang.String)__vr.getFieldValue(__target__,NOM);
      if (__nom!= null && __nom.length() > 100) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__vr.getFieldErrorCount(CAMPPDF) == 0) {
      java.lang.String __camppdf = (java.lang.String)__vr.getFieldValue(__target__,CAMPPDF);
      if (__camppdf!= null && __camppdf.length() > 100) {
        __vr.rejectValue(CAMPPDF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CAMPPDF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(FORMULARIID) == 0) {
      java.lang.Long __formulariid = (java.lang.Long)__vr.getFieldValue(__target__,FORMULARIID);
      Long __count_ = null;
      try { __count_ = __formulariManager.count(FormulariFields.FORMULARIID.equal(__formulariid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(FORMULARIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("formulari.formulari"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("formulari.formulariid"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__formulariid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}