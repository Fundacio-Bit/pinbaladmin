package org.fundaciobit.pinbaladmin.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.EntitatServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.EstatServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.FormulariFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class ServeiValidator<T> implements ServeiFields {

  protected final Logger log = Logger.getLogger(getClass());


  public ServeiValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager
    ,org.fundaciobit.pinbaladmin.model.dao.IEstatServeiManager __estatServeiManager
    ,org.fundaciobit.pinbaladmin.model.dao.IFormulariManager __formulariManager
    ,org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,CODI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATSERVEIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATSERVEIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ESTATSERVEIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTATSERVEIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSCONSENTIMENT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSCONSENTIMENT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,OCULT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(OCULT)));

    // Check size
    if (__vr.getFieldErrorCount(CODI) == 0) {
      java.lang.String __codi = (java.lang.String)__vr.getFieldValue(__target__,CODI);
      if (__codi!= null && __codi.length() > 255) {
        __vr.rejectValue(CODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = (java.lang.String)__vr.getFieldValue(__target__,NOM);
      if (__nom!= null && __nom.length() > 1000) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(1000)));
      }
    }
    
    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = (java.lang.String)__vr.getFieldValue(__target__,DESCRIPCIO);
      if (__descripcio!= null && __descripcio.length() > 1000) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(1000)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(CODI) == 0) {
        java.lang.String __codi = (java.lang.String)__vr.getFieldValue(__target__,CODI);
        Long __count_ = null;
        try { __count_ = __serveiManager.count(org.fundaciobit.genapp.common.query.Where.AND(CODI.equal(__codi))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(CODI, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codi)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(CODI) == 0 && __vr.getFieldErrorCount(SERVEIID) == 0) {
        java.lang.String __codi = (java.lang.String)__vr.getFieldValue(__target__,CODI);
        java.lang.Long __serveiid = (java.lang.Long)__vr.getFieldValue(__target__,SERVEIID);
        Long __count_ = null;
        try { __count_ = __serveiManager.count(org.fundaciobit.genapp.common.query.Where.AND(CODI.equal(__codi), SERVEIID.notEqual(__serveiid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(CODI, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codi)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));
        }
      }

    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(FORMULARIID) == 0) {
      java.lang.Long __formulariid = (java.lang.Long)__vr.getFieldValue(__target__,FORMULARIID);
      if (__formulariid != null ) {
        Long __count_ = null;
        try { __count_ = __formulariManager.count(FormulariFields.FORMULARIID.equal(__formulariid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(FORMULARIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("formulari.formulari"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("formulari.formulariid"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__formulariid)));
        }
      }
    }

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

    if (__vr.getFieldErrorCount(ESTATSERVEIID) == 0) {
      java.lang.Long __estatserveiid = (java.lang.Long)__vr.getFieldValue(__target__,ESTATSERVEIID);
      Long __count_ = null;
      try { __count_ = __estatServeiManager.count(EstatServeiFields.ESTATSERVEIID.equal(__estatserveiid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ESTATSERVEIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("estatServei.estatServei"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("estatServei.estatServeiID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__estatserveiid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}