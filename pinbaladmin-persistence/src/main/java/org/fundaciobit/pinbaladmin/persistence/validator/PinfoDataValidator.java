package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields;
import org.fundaciobit.pinbaladmin.model.fields.PINFOFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PinfoDataValidator<I extends PinfoData>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements PinfoDataFields {

    protected final Logger log = Logger.getLogger(getClass());


  public PinfoDataValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IPINFOManager __pINFOManager
    ,org.fundaciobit.pinbaladmin.model.dao.IPinfoDataManager __pinfoDataManager
    ,org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager
    ,org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) {

    // Valors Not Null
    // Check size
    if (__vr.getFieldErrorCount(USUARIID) == 0) {
      java.lang.String __usuariid = __target__.getUsuariid();
      if (__usuariid!= null && __usuariid.length() > 200) {
        __vr.rejectValue(USUARIID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(200)));
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
    if (__vr.getFieldErrorCount(PINFOID) == 0) {
      java.lang.Long __pinfoid = __target__.getPinfoID();
      if (__pinfoid != null ) {
        Long __count_ = null;
        try { __count_ = __pINFOManager.count(PINFOFields.PINFOID.equal(__pinfoid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(PINFOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("pINFO.pINFO"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("pINFO.PinfoID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pinfoid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(PROCEDIMENTID) == 0) {
      java.lang.Long __procedimentid = __target__.getProcedimentID();
      if (__procedimentid != null ) {
        Long __count_ = null;
        try { __count_ = __solicitudManager.count(SolicitudFields.SOLICITUDID.equal(__procedimentid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(PROCEDIMENTID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitud.solicitud"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitud.solicitudID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__procedimentid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(SERVEIID) == 0) {
      java.lang.Long __serveiid = __target__.getServeiID();
      if (__serveiid != null ) {
        Long __count_ = null;
        try { __count_ = __serveiManager.count(ServeiFields.SERVEIID.equal(__serveiid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(SERVEIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("servei.servei"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("servei.serveiID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__serveiid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}