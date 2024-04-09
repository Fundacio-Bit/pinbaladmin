package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.TramitHProcFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class TramitHProcValidator<I extends TramitHProc>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements TramitHProcFields {

    protected final Logger log = Logger.getLogger(getClass());


  public TramitHProcValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager
    ,org.fundaciobit.pinbaladmin.model.dao.ITramitHProcManager __tramitHProcManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,TRAMITID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TRAMITID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)));

    __vr.rejectIfEmptyOrWhitespace(__target__,URLSEU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(URLSEU)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CADUCITAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CADUCITAT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DESCRIPCIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PETICIONSALDIA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PETICIONSALDIA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PETICIONSALMES, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PETICIONSALMES)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PERIODICO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERIODICO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,AUTOMATIZADO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(AUTOMATIZADO)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 240) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(CODI) == 0) {
      java.lang.String __codi = __target__.getCodi();
      if (__codi!= null && __codi.length() > 30) {
        __vr.rejectValue(CODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(30)));
      }
    }

    if (__vr.getFieldErrorCount(TIPUS) == 0) {
      java.lang.String __tipus = __target__.getTipus();
      if (__tipus!= null && __tipus.length() > 240) {
        __vr.rejectValue(TIPUS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(URLSEU) == 0) {
      java.lang.String __urlseu = __target__.getUrlseu();
      if (__urlseu!= null && __urlseu.length() > 240) {
        __vr.rejectValue(URLSEU, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(URLSEU)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = __target__.getDescripcio();
      if (__descripcio!= null && __descripcio.length() > 240) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
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