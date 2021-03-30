package org.fundaciobit.pinbaladmin.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.CampSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.CampFormulariFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class CampSolicitudValidator<T> implements CampSolicitudFields {

  protected final Logger log = Logger.getLogger(getClass());


  public CampSolicitudValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.ICampFormulariManager __campFormulariManager
    ,org.fundaciobit.pinbaladmin.model.dao.ICampSolicitudManager __campSolicitudManager
    ,org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,CAMPFORMULARIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CAMPFORMULARIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,SOLICITUDSERVEIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITUDSERVEIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,VALOR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VALOR)));

    // Check size
    if (__vr.getFieldErrorCount(VALOR) == 0) {
      java.lang.String __valor = (java.lang.String)__vr.getFieldValue(__target__,VALOR);
      if (__valor!= null && __valor.length() > 2000) {
        __vr.rejectValue(VALOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VALOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2000)));
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
    if (__vr.getFieldErrorCount(CAMPFORMULARIID) == 0) {
      java.lang.Long __campformulariid = (java.lang.Long)__vr.getFieldValue(__target__,CAMPFORMULARIID);
      Long __count_ = null;
      try { __count_ = __campFormulariManager.count(CampFormulariFields.CAMPFORMULARIID.equal(__campformulariid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(CAMPFORMULARIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("campFormulari.campFormulari"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("campFormulari.campFormulariID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__campformulariid)));
      }
    }

    if (__vr.getFieldErrorCount(SOLICITUDSERVEIID) == 0) {
      java.lang.Long __solicitudserveiid = (java.lang.Long)__vr.getFieldValue(__target__,SOLICITUDSERVEIID);
      Long __count_ = null;
      try { __count_ = __solicitudServeiManager.count(SolicitudServeiFields.ID.equal(__solicitudserveiid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(SOLICITUDSERVEIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitudServei.solicitudServei"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitudServei.id"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__solicitudserveiid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}