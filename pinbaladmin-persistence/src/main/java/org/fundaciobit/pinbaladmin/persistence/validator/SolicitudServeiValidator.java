package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class SolicitudServeiValidator<I extends SolicitudServei>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements SolicitudServeiFields {

    protected final Logger log = Logger.getLogger(getClass());


  public SolicitudServeiValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager
    ,org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager
    ,org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,SOLICITUDID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITUDID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,SERVEIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SERVEIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ESTATSOLICITUDSERVEIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTATSOLICITUDSERVEIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CONSENTIMENT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONSENTIMENT)));

    // Check size
    if (__vr.getFieldErrorCount(ENLLAZNORMALEGAL) == 0) {
      java.lang.String __enllaznormalegal = __target__.getEnllazNormaLegal();
      if (__enllaznormalegal!= null && __enllaznormalegal.length() > 255) {
        __vr.rejectValue(ENLLAZNORMALEGAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENLLAZNORMALEGAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(TIPUSCONSENTIMENT) == 0) {
      java.lang.String __tipusconsentiment = __target__.getTipusConsentiment();
      if (__tipusconsentiment!= null && __tipusconsentiment.length() > 255) {
        __vr.rejectValue(TIPUSCONSENTIMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSCONSENTIMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(CONSENTIMENT) == 0) {
      java.lang.String __consentiment = __target__.getConsentiment();
      if (__consentiment!= null && __consentiment.length() > 255) {
        __vr.rejectValue(CONSENTIMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONSENTIMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ENLLAZCONSENTIMENT) == 0) {
      java.lang.String __enllazconsentiment = __target__.getEnllazConsentiment();
      if (__enllazconsentiment!= null && __enllazconsentiment.length() > 255) {
        __vr.rejectValue(ENLLAZCONSENTIMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENLLAZCONSENTIMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(NOTES) == 0) {
      java.lang.String __notes = __target__.getNotes();
      if (__notes!= null && __notes.length() > 2000) {
        __vr.rejectValue(NOTES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOTES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2000)));
      }
    }

    if (__vr.getFieldErrorCount(CADUCA) == 0) {
      java.lang.String __caduca = __target__.getCaduca();
      if (__caduca!= null && __caduca.length() > 255) {
        __vr.rejectValue(CADUCA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CADUCA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(FECHACADUCA) == 0) {
      java.lang.String __fechacaduca = __target__.getFechaCaduca();
      if (__fechacaduca!= null && __fechacaduca.length() > 255) {
        __vr.rejectValue(FECHACADUCA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FECHACADUCA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(NORMALEGAL) == 0) {
      java.lang.String __normalegal = __target__.getNormaLegal();
      if (__normalegal!= null && __normalegal.length() > 3000) {
        __vr.rejectValue(NORMALEGAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NORMALEGAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(3000)));
      }
    }

    if (__vr.getFieldErrorCount(ARTICLES) == 0) {
      java.lang.String __articles = __target__.getArticles();
      if (__articles!= null && __articles.length() > 255) {
        __vr.rejectValue(ARTICLES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARTICLES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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

      // Check Unique MULTIPLE for (serveiid, solicitudid)
      if (__vr.getFieldErrorCount(SERVEIID) == 0 && __vr.getFieldErrorCount(SOLICITUDID) == 0) {
        java.lang.Long __serveiid = __target__.getServeiID();
        java.lang.Long __solicitudid = __target__.getSolicitudID();
        Long __count_ = null;
        try { __count_ = __solicitudServeiManager.count(org.fundaciobit.genapp.common.query.Where.AND(SERVEIID.equal(__serveiid), SOLICITUDID.equal(__solicitudid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(SERVEIID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__serveiid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SERVEIID)));
            __vr.rejectValue(SOLICITUDID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__solicitudid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITUDID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (serveiid, solicitudid)
      if (__vr.getFieldErrorCount(SERVEIID) == 0 && __vr.getFieldErrorCount(SOLICITUDID) == 0 && __vr.getFieldErrorCount(ID) == 0) {
        java.lang.Long __serveiid = __target__.getServeiID();
        java.lang.Long __solicitudid = __target__.getSolicitudID();
        java.lang.Long __id = __target__.getId();
        Long __count_ = null;
        try { __count_ = __solicitudServeiManager.count(org.fundaciobit.genapp.common.query.Where.AND(SERVEIID.equal(__serveiid), SOLICITUDID.equal(__solicitudid), ID.notEqual(__id))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(SERVEIID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__serveiid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SERVEIID)));
            __vr.rejectValue(SOLICITUDID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__solicitudid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITUDID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(SOLICITUDID) == 0) {
      java.lang.Long __solicitudid = __target__.getSolicitudID();
      Long __count_ = null;
      try { __count_ = __solicitudManager.count(SolicitudFields.SOLICITUDID.equal(__solicitudid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(SOLICITUDID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitud.solicitud"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitud.solicitudID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__solicitudid)));
      }
    }

    if (__vr.getFieldErrorCount(SERVEIID) == 0) {
      java.lang.Long __serveiid = __target__.getServeiID();
      Long __count_ = null;
      try { __count_ = __serveiManager.count(ServeiFields.SERVEIID.equal(__serveiid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(SERVEIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("servei.servei"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("servei.serveiID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__serveiid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}