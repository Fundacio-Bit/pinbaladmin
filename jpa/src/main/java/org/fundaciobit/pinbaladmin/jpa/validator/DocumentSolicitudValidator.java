package org.fundaciobit.pinbaladmin.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class DocumentSolicitudValidator<T> implements DocumentSolicitudFields {

  protected final Logger log = Logger.getLogger(getClass());


  public DocumentSolicitudValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IDocumentManager __documentManager
    ,org.fundaciobit.pinbaladmin.model.dao.IDocumentSolicitudManager __documentSolicitudManager
    ,org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,DOCUMENTID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DOCUMENTID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,SOLICITUDID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITUDID)));

    // Check size
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
    if (__vr.getFieldErrorCount(DOCUMENTID) == 0) {
      java.lang.Long __documentid = (java.lang.Long)__vr.getFieldValue(__target__,DOCUMENTID);
      Long __count_ = null;
      try { __count_ = __documentManager.count(DocumentFields.DOCUMENTID.equal(__documentid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(DOCUMENTID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("document.document"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("document.documentID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__documentid)));
      }
    }

    if (__vr.getFieldErrorCount(SOLICITUDID) == 0) {
      java.lang.Long __solicitudid = (java.lang.Long)__vr.getFieldValue(__target__,SOLICITUDID);
      Long __count_ = null;
      try { __count_ = __solicitudManager.count(SolicitudFields.SOLICITUDID.equal(__solicitudid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(SOLICITUDID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitud.solicitud"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitud.solicitudID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__solicitudid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}