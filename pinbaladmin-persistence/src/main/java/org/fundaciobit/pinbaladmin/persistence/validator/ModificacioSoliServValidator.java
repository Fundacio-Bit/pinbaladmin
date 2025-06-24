package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.ModificacioSoliServ;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSoliServFields;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class ModificacioSoliServValidator<I extends ModificacioSoliServ>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements ModificacioSoliServFields {

    protected final Logger log = Logger.getLogger(getClass());


  public ModificacioSoliServValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IModificacioSoliServManager __modificacioSoliServManager
    ,org.fundaciobit.pinbaladmin.model.dao.IModificacioSolicitudManager __modificacioSolicitudManager
    ,org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,SOLISERVID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLISERVID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MODSOLIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MODSOLIID)));

    // Check size
    if (__vr.getFieldErrorCount(ESTAT) == 0) {
      java.lang.String __estat = __target__.getEstat();
      if (__estat!= null && __estat.length() > 2550) {
        __vr.rejectValue(ESTAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2550)));
      }
    }

    if (__vr.getFieldErrorCount(NORMA1) == 0) {
      java.lang.String __norma1 = __target__.getNorma1();
      if (__norma1!= null && __norma1.length() > 240) {
        __vr.rejectValue(NORMA1, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NORMA1)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(ARTICLES1) == 0) {
      java.lang.String __articles1 = __target__.getArticles1();
      if (__articles1!= null && __articles1.length() > 255) {
        __vr.rejectValue(ARTICLES1, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARTICLES1)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(NORMA2) == 0) {
      java.lang.String __norma2 = __target__.getNorma2();
      if (__norma2!= null && __norma2.length() > 240) {
        __vr.rejectValue(NORMA2, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NORMA2)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(ARTICLES2) == 0) {
      java.lang.String __articles2 = __target__.getArticles2();
      if (__articles2!= null && __articles2.length() > 60) {
        __vr.rejectValue(ARTICLES2, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARTICLES2)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(60)));
      }
    }

    if (__vr.getFieldErrorCount(NORMA3) == 0) {
      java.lang.String __norma3 = __target__.getNorma3();
      if (__norma3!= null && __norma3.length() > 240) {
        __vr.rejectValue(NORMA3, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NORMA3)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(ARTICLES3) == 0) {
      java.lang.String __articles3 = __target__.getArticles3();
      if (__articles3!= null && __articles3.length() > 60) {
        __vr.rejectValue(ARTICLES3, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARTICLES3)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(60)));
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
    if (__vr.getFieldErrorCount(SOLISERVID) == 0) {
      java.lang.Long __soliservid = __target__.getSoliServID();
      Long __count_ = null;
      try { __count_ = __solicitudServeiManager.count(SolicitudServeiFields.ID.equal(__soliservid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(SOLISERVID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitudServei.solicitudServei"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitudServei.id"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__soliservid)));
      }
    }

    if (__vr.getFieldErrorCount(MODSOLIID) == 0) {
      java.lang.Long __modsoliid = __target__.getModSoliID();
      Long __count_ = null;
      try { __count_ = __modificacioSolicitudManager.count(ModificacioSolicitudFields.MODSOLIID.equal(__modsoliid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(MODSOLIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("modificacioSolicitud.modificacioSolicitud"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("modificacioSolicitud.modsoliID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__modsoliid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}