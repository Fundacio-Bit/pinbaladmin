package org.fundaciobit.pinbaladmin.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.TipusTiquetFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class TipusTiquetValidator<T> implements TipusTiquetFields {

  protected final Logger log = Logger.getLogger(getClass());


  public TipusTiquetValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.ITipusTiquetManager __tipusTiquetManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSTIQUETID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSTIQUETID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = (java.lang.String)__vr.getFieldValue(__target__,NOM);
      if (__nom!= null && __nom.length() > 100) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
      if (__vr.getFieldErrorCount(TIPUSTIQUETID) == 0) {
        java.lang.Long __tipustiquetid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSTIQUETID);
        Long __count_ = null;
        try { __count_ = __tipusTiquetManager.count(org.fundaciobit.genapp.common.query.Where.AND(TIPUSTIQUETID.equal(__tipustiquetid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(TIPUSTIQUETID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipustiquetid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSTIQUETID)));
        }
      }

    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}