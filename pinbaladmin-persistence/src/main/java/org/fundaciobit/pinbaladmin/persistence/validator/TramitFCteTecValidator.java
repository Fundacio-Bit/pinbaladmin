package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.TramitFCteTecFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class TramitFCteTecValidator<I extends TramitFCteTec>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements TramitFCteTecFields {

    protected final Logger log = Logger.getLogger(getClass());


  public TramitFCteTecValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager
    ,org.fundaciobit.pinbaladmin.model.dao.ITramitFCteTecManager __tramitFCteTecManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,TRAMITID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TRAMITID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NIF, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,LLINATGE1, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLINATGE1)));

    // Check size
    if (__vr.getFieldErrorCount(NIF) == 0) {
      java.lang.String __nif = __target__.getNif();
      if (__nif!= null && __nif.length() > 30) {
        __vr.rejectValue(NIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(30)));
      }
    }

    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 40) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(40)));
      }
    }

    if (__vr.getFieldErrorCount(LLINATGE1) == 0) {
      java.lang.String __llinatge1 = __target__.getLlinatge1();
      if (__llinatge1!= null && __llinatge1.length() > 40) {
        __vr.rejectValue(LLINATGE1, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLINATGE1)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(40)));
      }
    }

    if (__vr.getFieldErrorCount(LLINATGE2) == 0) {
      java.lang.String __llinatge2 = __target__.getLlinatge2();
      if (__llinatge2!= null && __llinatge2.length() > 40) {
        __vr.rejectValue(LLINATGE2, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLINATGE2)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(40)));
      }
    }

    if (__vr.getFieldErrorCount(CARREC) == 0) {
      java.lang.String __carrec = __target__.getCarrec();
      if (__carrec!= null && __carrec.length() > 100) {
        __vr.rejectValue(CARREC, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CARREC)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(TELEFON) == 0) {
      java.lang.String __telefon = __target__.getTelefon();
      if (__telefon!= null && __telefon.length() > 10) {
        __vr.rejectValue(TELEFON, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TELEFON)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(10)));
      }
    }

    if (__vr.getFieldErrorCount(MAIL) == 0) {
      java.lang.String __mail = __target__.getMail();
      if (__mail!= null && __mail.length() > 100) {
        __vr.rejectValue(MAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
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