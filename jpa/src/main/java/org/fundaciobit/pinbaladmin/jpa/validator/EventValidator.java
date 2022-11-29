package org.fundaciobit.pinbaladmin.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EventValidator<T> implements EventFields {

  protected final Logger log = Logger.getLogger(getClass());


  public EventValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IEventManager __eventManager
    ,org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager
    ,org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,DATAEVENT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATAEVENT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PERSONA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERSONA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOLLEGIT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOLLEGIT)));

    // Check size
    if (__vr.getFieldErrorCount(PERSONA) == 0) {
      java.lang.String __persona = (java.lang.String)__vr.getFieldValue(__target__,PERSONA);
      if (__persona!= null && __persona.length() > 255) {
        __vr.rejectValue(PERSONA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERSONA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(COMENTARI) == 0) {
      java.lang.String __comentari = (java.lang.String)__vr.getFieldValue(__target__,COMENTARI);
      if (__comentari!= null && __comentari.length() > 2147483647) {
        __vr.rejectValue(COMENTARI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(COMENTARI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }
    
    if (__vr.getFieldErrorCount(CAIDIDENTIFICADORCONSULTA) == 0) {
      java.lang.String __caididentificadorconsulta = (java.lang.String)__vr.getFieldValue(__target__,CAIDIDENTIFICADORCONSULTA);
      if (__caididentificadorconsulta!= null && __caididentificadorconsulta.length() > 100) {
        __vr.rejectValue(CAIDIDENTIFICADORCONSULTA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CAIDIDENTIFICADORCONSULTA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__vr.getFieldErrorCount(CAIDNUMEROSEGUIMENT) == 0) {
      java.lang.String __caidnumeroseguiment = (java.lang.String)__vr.getFieldValue(__target__,CAIDNUMEROSEGUIMENT);
      if (__caidnumeroseguiment!= null && __caidnumeroseguiment.length() > 100) {
        __vr.rejectValue(CAIDNUMEROSEGUIMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CAIDNUMEROSEGUIMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
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
    if (__vr.getFieldErrorCount(SOLICITUDID) == 0) {
      java.lang.Long __solicitudid = (java.lang.Long)__vr.getFieldValue(__target__,SOLICITUDID);
      if (__solicitudid != null ) {
        Long __count_ = null;
        try { __count_ = __solicitudManager.count(SolicitudFields.SOLICITUDID.equal(__solicitudid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(SOLICITUDID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitud.solicitud"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("solicitud.solicitudID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__solicitudid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(INCIDENCIATECNICAID) == 0) {
      java.lang.Long __incidenciatecnicaid = (java.lang.Long)__vr.getFieldValue(__target__,INCIDENCIATECNICAID);
      if (__incidenciatecnicaid != null ) {
        Long __count_ = null;
        try { __count_ = __incidenciaTecnicaManager.count(IncidenciaTecnicaFields.INCIDENCIATECNICAID.equal(__incidenciatecnicaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(INCIDENCIATECNICAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("incidenciaTecnica.incidenciaTecnica"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("incidenciaTecnica.incidenciaTecnicaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__incidenciatecnicaid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}