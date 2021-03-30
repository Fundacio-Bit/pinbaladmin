package org.fundaciobit.pinbaladmin.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.DocumentCedentFields;
import org.fundaciobit.pinbaladmin.model.fields.EntitatServeiFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class DocumentCedentValidator<T> implements DocumentCedentFields {

  protected final Logger log = Logger.getLogger(getClass());


  public DocumentCedentValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IDocumentCedentManager __documentCedentManager
    ,org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,TITOL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TITOL)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATSERVEIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATSERVEIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    // Check size
    if (__vr.getFieldErrorCount(TITOL) == 0) {
      java.lang.String __titol = (java.lang.String)__vr.getFieldValue(__target__,TITOL);
      if (__titol!= null && __titol.length() > 255) {
        __vr.rejectValue(TITOL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TITOL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = (java.lang.String)__vr.getFieldValue(__target__,DESCRIPCIO);
      if (__descripcio!= null && __descripcio.length() > 2147483647) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
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
    if (__vr.getFieldErrorCount(ENTITATSERVEIID) == 0) {
      java.lang.Long __entitatserveiid = (java.lang.Long)__vr.getFieldValue(__target__,ENTITATSERVEIID);
      Long __count_ = null;
      try { __count_ = __entitatServeiManager.count(EntitatServeiFields.ENTITATSERVEIID.equal(__entitatserveiid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ENTITATSERVEIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitatServei.entitatServei"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitatServei.entitatServeiID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatserveiid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}