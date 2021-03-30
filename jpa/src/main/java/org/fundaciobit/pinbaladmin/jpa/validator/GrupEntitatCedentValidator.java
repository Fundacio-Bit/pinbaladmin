package org.fundaciobit.pinbaladmin.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.GrupEntitatCedentFields;
import org.fundaciobit.pinbaladmin.model.fields.EntitatServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.GrupEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class GrupEntitatCedentValidator<T> implements GrupEntitatCedentFields {

  protected final Logger log = Logger.getLogger(getClass());


  public GrupEntitatCedentValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager
    ,org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager __grupEntitatManager
    ,org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatCedentManager __grupEntitatCedentManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,GRUPENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CEDENTID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CEDENTID)));

    // Check size
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (grupentitatid, cedentid)
      if (__vr.getFieldErrorCount(GRUPENTITATID) == 0 && __vr.getFieldErrorCount(CEDENTID) == 0) {
        java.lang.Long __grupentitatid = (java.lang.Long)__vr.getFieldValue(__target__,GRUPENTITATID);
        java.lang.Long __cedentid = (java.lang.Long)__vr.getFieldValue(__target__,CEDENTID);
        Long __count_ = null;
        try { __count_ = __grupEntitatCedentManager.count(org.fundaciobit.genapp.common.query.Where.AND(GRUPENTITATID.equal(__grupentitatid), CEDENTID.equal(__cedentid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(GRUPENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupentitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPENTITATID)));
            __vr.rejectValue(CEDENTID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__cedentid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CEDENTID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (grupentitatid, cedentid)
      if (__vr.getFieldErrorCount(GRUPENTITATID) == 0 && __vr.getFieldErrorCount(CEDENTID) == 0 && __vr.getFieldErrorCount(GRUPENTITATCEDENTID) == 0) {
        java.lang.Long __grupentitatid = (java.lang.Long)__vr.getFieldValue(__target__,GRUPENTITATID);
        java.lang.Long __cedentid = (java.lang.Long)__vr.getFieldValue(__target__,CEDENTID);
        java.lang.Long __grupentitatcedentid = (java.lang.Long)__vr.getFieldValue(__target__,GRUPENTITATCEDENTID);
        Long __count_ = null;
        try { __count_ = __grupEntitatCedentManager.count(org.fundaciobit.genapp.common.query.Where.AND(GRUPENTITATID.equal(__grupentitatid), CEDENTID.equal(__cedentid), GRUPENTITATCEDENTID.notEqual(__grupentitatcedentid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(GRUPENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupentitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPENTITATID)));
            __vr.rejectValue(CEDENTID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__cedentid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CEDENTID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(GRUPENTITATID) == 0) {
      java.lang.Long __grupentitatid = (java.lang.Long)__vr.getFieldValue(__target__,GRUPENTITATID);
      Long __count_ = null;
      try { __count_ = __grupEntitatManager.count(GrupEntitatFields.GRUPENTITATID.equal(__grupentitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(GRUPENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("grupEntitat.grupEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("grupEntitat.grupEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupentitatid)));
      }
    }

    if (__vr.getFieldErrorCount(CEDENTID) == 0) {
      java.lang.Long __cedentid = (java.lang.Long)__vr.getFieldValue(__target__,CEDENTID);
      Long __count_ = null;
      try { __count_ = __entitatServeiManager.count(EntitatServeiFields.ENTITATSERVEIID.equal(__cedentid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(CEDENTID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitatServei.entitatServei"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitatServei.entitatServeiID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__cedentid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}