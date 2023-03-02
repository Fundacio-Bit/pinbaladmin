package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.EstatTiquet;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.EstatTiquetFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EstatTiquetValidator<I extends EstatTiquet>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements EstatTiquetFields {

    protected final Logger log = Logger.getLogger(getClass());


  public EstatTiquetValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IEstatTiquetManager __estatTiquetManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,ESTATTIQUETID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTATTIQUETID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
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
      if (__vr.getFieldErrorCount(ESTATTIQUETID) == 0) {
        java.lang.Long __estattiquetid = __target__.getEstatTiquetID();
        Long __count_ = null;
        try { __count_ = __estatTiquetManager.count(org.fundaciobit.genapp.common.query.Where.AND(ESTATTIQUETID.equal(__estattiquetid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(ESTATTIQUETID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__estattiquetid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTATTIQUETID)));
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