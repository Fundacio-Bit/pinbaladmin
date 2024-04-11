package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class TramitIServValidator<I extends TramitIServ>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements TramitIServFields {

    protected final Logger log = Logger.getLogger(getClass());


  public TramitIServValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager
    ,org.fundaciobit.pinbaladmin.model.dao.ITramitIServManager __tramitIServManager) {

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

    __vr.rejectIfEmptyOrWhitespace(__target__,NORMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NORMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,URLNORMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(URLNORMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ARTICLES, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARTICLES)));

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
      if (__codi!= null && __codi.length() > 100) {
        __vr.rejectValue(CODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(NORMA) == 0) {
      java.lang.String __norma = __target__.getNorma();
      if (__norma!= null && __norma.length() > 240) {
        __vr.rejectValue(NORMA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NORMA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(URLNORMA) == 0) {
      java.lang.String __urlnorma = __target__.getUrlnorma();
      if (__urlnorma!= null && __urlnorma.length() > 240) {
        __vr.rejectValue(URLNORMA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(URLNORMA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(ARTICLES) == 0) {
      java.lang.String __articles = __target__.getArticles();
      if (__articles!= null && __articles.length() > 60) {
        __vr.rejectValue(ARTICLES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARTICLES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(60)));
      }
    }

    if (__vr.getFieldErrorCount(NORMA2) == 0) {
      java.lang.String __norma2 = __target__.getNorma2();
      if (__norma2!= null && __norma2.length() > 240) {
        __vr.rejectValue(NORMA2, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NORMA2)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(URLNORMA2) == 0) {
      java.lang.String __urlnorma2 = __target__.getUrlnorma2();
      if (__urlnorma2!= null && __urlnorma2.length() > 240) {
        __vr.rejectValue(URLNORMA2, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(URLNORMA2)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(ARTICLES2) == 0) {
      java.lang.String __articles2 = __target__.getArticles2();
      if (__articles2!= null && __articles2.length() > 60) {
        __vr.rejectValue(ARTICLES2, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARTICLES2)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(60)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
    if (__vr.getFieldErrorCount(FITXERNORMAID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,FITXERNORMAID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(FITXERNORMAID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FITXERNORMAID)));
      }
    }

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