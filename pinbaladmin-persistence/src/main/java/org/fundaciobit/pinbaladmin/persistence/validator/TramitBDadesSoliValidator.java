package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.TramitBDadesSoliFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class TramitBDadesSoliValidator<I extends TramitBDadesSoli>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements TramitBDadesSoliFields {

    protected final Logger log = Logger.getLogger(getClass());


  public TramitBDadesSoliValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager
    ,org.fundaciobit.pinbaladmin.model.dao.ITramitBDadesSoliManager __tramitBDadesSoliManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,TRAMITID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TRAMITID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSSOLICITUD, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSSOLICITUD)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTORN, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTORN)));

    // Check size
    if (__vr.getFieldErrorCount(ENTORN) == 0) {
      java.lang.String __entorn = __target__.getEntorn();
      if (__entorn!= null && __entorn.length() > 20) {
        __vr.rejectValue(ENTORN, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTORN)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(20)));
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
    if (__vr.getFieldErrorCount(TRAMITID) == 0) {
      java.lang.Long __tramitid = __target__.getTramitid();
      Long __count_ = null;
      try { __count_ = __tramitAPersAutManager.count(TramitAPersAutFields.TRAMITID.equal(__tramitid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(TRAMITID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tramitAPersAut.tramitAPersAut"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tramitAPersAut.tramitid"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tramitid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}