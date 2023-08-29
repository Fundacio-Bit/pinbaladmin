package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class TramitCDadesCesiValidator<I extends TramitCDadesCesi>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements TramitCDadesCesiFields {

    protected final Logger log = Logger.getLogger(getClass());


  public TramitCDadesCesiValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager
    ,org.fundaciobit.pinbaladmin.model.dao.ITramitCDadesCesiManager __tramitCDadesCesiManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,TRAMITID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TRAMITID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DENOMINACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DENOMINACIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NIF, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));

    __vr.rejectIfEmptyOrWhitespace(__target__,RESPONSABLE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPONSABLE)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DIR3RESPONSABLE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DIR3RESPONSABLE)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DIR3ARREL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DIR3ARREL)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DIRECCIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DIRECCIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODIPOSTAL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIPOSTAL)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MUNICIPI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MUNICIPI)));

    // Check size
    if (__vr.getFieldErrorCount(DENOMINACIO) == 0) {
      java.lang.String __denominacio = __target__.getDenominacio();
      if (__denominacio!= null && __denominacio.length() > 240) {
        __vr.rejectValue(DENOMINACIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DENOMINACIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(NIF) == 0) {
      java.lang.String __nif = __target__.getNif();
      if (__nif!= null && __nif.length() > 30) {
        __vr.rejectValue(NIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(30)));
      }
    }

    if (__vr.getFieldErrorCount(RESPONSABLE) == 0) {
      java.lang.String __responsable = __target__.getResponsable();
      if (__responsable!= null && __responsable.length() > 240) {
        __vr.rejectValue(RESPONSABLE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPONSABLE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(DIR3RESPONSABLE) == 0) {
      java.lang.String __dir3responsable = __target__.getDir3responsable();
      if (__dir3responsable!= null && __dir3responsable.length() > 30) {
        __vr.rejectValue(DIR3RESPONSABLE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DIR3RESPONSABLE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(30)));
      }
    }

    if (__vr.getFieldErrorCount(DIR3ARREL) == 0) {
      java.lang.String __dir3arrel = __target__.getDir3arrel();
      if (__dir3arrel!= null && __dir3arrel.length() > 30) {
        __vr.rejectValue(DIR3ARREL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DIR3ARREL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(30)));
      }
    }

    if (__vr.getFieldErrorCount(DIRECCIO) == 0) {
      java.lang.String __direccio = __target__.getDireccio();
      if (__direccio!= null && __direccio.length() > 240) {
        __vr.rejectValue(DIRECCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DIRECCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(CODIPOSTAL) == 0) {
      java.lang.String __codipostal = __target__.getCodipostal();
      if (__codipostal!= null && __codipostal.length() > 10) {
        __vr.rejectValue(CODIPOSTAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIPOSTAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(10)));
      }
    }

    if (__vr.getFieldErrorCount(MUNICIPI) == 0) {
      java.lang.String __municipi = __target__.getMunicipi();
      if (__municipi!= null && __municipi.length() > 40) {
        __vr.rejectValue(MUNICIPI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MUNICIPI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(40)));
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