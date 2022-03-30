package org.fundaciobit.pinbaladmin.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.EntitatFields;
import org.fundaciobit.pinbaladmin.model.fields.GrupEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EntitatValidator<T> implements EntitatFields {

  protected final Logger log = Logger.getLogger(getClass());


  public EntitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager
    ,org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager __grupEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CIF, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CIF)));

    __vr.rejectIfEmptyOrWhitespace(__target__,GRUPENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CONVENIPMSBAE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONVENIPMSBAE)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = (java.lang.String)__vr.getFieldValue(__target__,NOM);
      if (__nom!= null && __nom.length() > 500) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(500)));
      }
    }
    
    if (__vr.getFieldErrorCount(PERSONACONTACTE) == 0) {
      java.lang.String __personacontacte = (java.lang.String)__vr.getFieldValue(__target__,PERSONACONTACTE);
      if (__personacontacte!= null && __personacontacte.length() > 255) {
        __vr.rejectValue(PERSONACONTACTE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERSONACONTACTE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(CIF) == 0) {
      java.lang.String __cif = (java.lang.String)__vr.getFieldValue(__target__,CIF);
      if (__cif!= null && __cif.length() > 10) {
        __vr.rejectValue(CIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(10)));
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

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}