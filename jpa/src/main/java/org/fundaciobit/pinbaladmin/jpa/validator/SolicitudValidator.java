package org.fundaciobit.pinbaladmin.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.DepartamentFields;
import org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class SolicitudValidator<T> implements SolicitudFields {

  protected final Logger log = Logger.getLogger(getClass());


  public SolicitudValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IDepartamentManager __departamentManager
    ,org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudManager __estatSolicitudManager
    ,org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,PROCEDIMENTCODI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTCODI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PROCEDIMENTNOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTNOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ESTATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATAINICI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATAINICI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,FIRMATDOCSOLICITUD, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FIRMATDOCSOLICITUD)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PRODUCCIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PRODUCCIO)));

    // Check size
    if (__vr.getFieldErrorCount(PROCEDIMENTCODI) == 0) {
      java.lang.String __procedimentcodi = (java.lang.String)__vr.getFieldValue(__target__,PROCEDIMENTCODI);
      if (__procedimentcodi!= null && __procedimentcodi.length() > 255) {
        __vr.rejectValue(PROCEDIMENTCODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTCODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(CODIDESCRIPTIU) == 0) {
      java.lang.String __codidescriptiu = (java.lang.String)__vr.getFieldValue(__target__,CODIDESCRIPTIU);
      if (__codidescriptiu!= null && __codidescriptiu.length() > 256) {
        __vr.rejectValue(CODIDESCRIPTIU, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIDESCRIPTIU)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }
    
    if (__vr.getFieldErrorCount(PROCEDIMENTNOM) == 0) {
      java.lang.String __procedimentnom = (java.lang.String)__vr.getFieldValue(__target__,PROCEDIMENTNOM);
      if (__procedimentnom!= null && __procedimentnom.length() > 2000) {
        __vr.rejectValue(PROCEDIMENTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2000)));
      }
    }
    
    if (__vr.getFieldErrorCount(PROCEDIMENTTIPUS) == 0) {
      java.lang.String __procedimenttipus = (java.lang.String)__vr.getFieldValue(__target__,PROCEDIMENTTIPUS);
      if (__procedimenttipus!= null && __procedimenttipus.length() > 255) {
        __vr.rejectValue(PROCEDIMENTTIPUS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTTIPUS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    

    if (__vr.getFieldErrorCount(TICKETASSOCIAT) == 0) {
      java.lang.String __ticketassociat = (java.lang.String)__vr.getFieldValue(__target__,TICKETASSOCIAT);
      if (__ticketassociat!= null && __ticketassociat.length() > 255) {
        __vr.rejectValue(TICKETASSOCIAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TICKETASSOCIAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(TICKETNUMEROSEGUIMENT) == 0) {
      java.lang.String __ticketnumeroseguiment = (java.lang.String)__vr.getFieldValue(__target__,TICKETNUMEROSEGUIMENT);
      if (__ticketnumeroseguiment!= null && __ticketnumeroseguiment.length() > 255) {
        __vr.rejectValue(TICKETNUMEROSEGUIMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TICKETNUMEROSEGUIMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(ENTITATESTATAL) == 0) {
      java.lang.String __entitatestatal = (java.lang.String)__vr.getFieldValue(__target__,ENTITATESTATAL);
      if (__entitatestatal!= null && __entitatestatal.length() > 255) {
        __vr.rejectValue(ENTITATESTATAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATESTATAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(PINFO) == 0) {
      java.lang.String __pinfo = (java.lang.String)__vr.getFieldValue(__target__,PINFO);
      if (__pinfo!= null && __pinfo.length() > 255) {
        __vr.rejectValue(PINFO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PINFO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(PERSONACONTACTE) == 0) {
      java.lang.String __personacontacte = (java.lang.String)__vr.getFieldValue(__target__,PERSONACONTACTE);
      if (__personacontacte!= null && __personacontacte.length() > 255) {
        __vr.rejectValue(PERSONACONTACTE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERSONACONTACTE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(PERSONACONTACTEEMAIL) == 0) {
      java.lang.String __personacontacteemail = (java.lang.String)__vr.getFieldValue(__target__,PERSONACONTACTEEMAIL);
      if (__personacontacteemail!= null && __personacontacteemail.length() > 100) {
        __vr.rejectValue(PERSONACONTACTEEMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERSONACONTACTEEMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__vr.getFieldErrorCount(RESPONSABLEPROCNOM) == 0) {
      java.lang.String __responsableprocnom = (java.lang.String)__vr.getFieldValue(__target__,RESPONSABLEPROCNOM);
      if (__responsableprocnom!= null && __responsableprocnom.length() > 255) {
        __vr.rejectValue(RESPONSABLEPROCNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPONSABLEPROCNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(RESPONSABLEPROCEMAIL) == 0) {
      java.lang.String __responsableprocemail = (java.lang.String)__vr.getFieldValue(__target__,RESPONSABLEPROCEMAIL);
      if (__responsableprocemail!= null && __responsableprocemail.length() > 255) {
        __vr.rejectValue(RESPONSABLEPROCEMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPONSABLEPROCEMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(NOTES) == 0) {
      java.lang.String __notes = (java.lang.String)__vr.getFieldValue(__target__,NOTES);
      if (__notes!= null && __notes.length() > 2550) {
        __vr.rejectValue(NOTES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOTES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2550)));
      }
    }
    
    if (__vr.getFieldErrorCount(CREADOR) == 0) {
      java.lang.String __creador = (java.lang.String)__vr.getFieldValue(__target__,CREADOR);
      if (__creador!= null && __creador.length() > 100) {
        __vr.rejectValue(CREADOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CREADOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__vr.getFieldErrorCount(DENOMINACIO) == 0) {
      java.lang.String __denominacio = (java.lang.String)__vr.getFieldValue(__target__,DENOMINACIO);
      if (__denominacio!= null && __denominacio.length() > 255) {
        __vr.rejectValue(DENOMINACIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DENOMINACIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(DIR3) == 0) {
      java.lang.String __dir3 = (java.lang.String)__vr.getFieldValue(__target__,DIR3);
      if (__dir3!= null && __dir3.length() > 50) {
        __vr.rejectValue(DIR3, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DIR3)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }
    
    if (__vr.getFieldErrorCount(NIF) == 0) {
      java.lang.String __nif = (java.lang.String)__vr.getFieldValue(__target__,NIF);
      if (__nif!= null && __nif.length() > 40) {
        __vr.rejectValue(NIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(40)));
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
    if (__vr.getFieldErrorCount(ESTATID) == 0) {
      java.lang.Long __estatid = (java.lang.Long)__vr.getFieldValue(__target__,ESTATID);
      Long __count_ = null;
      try { __count_ = __estatSolicitudManager.count(EstatSolicitudFields.ESTATSOLICITUDID.equal(__estatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ESTATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("estatSolicitud.estatSolicitud"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("estatSolicitud.estatSolicitudID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__estatid)));
      }
    }

    if (__vr.getFieldErrorCount(DEPARTAMENTID) == 0) {
      java.lang.Long __departamentid = (java.lang.Long)__vr.getFieldValue(__target__,DEPARTAMENTID);
      if (__departamentid != null ) {
        Long __count_ = null;
        try { __count_ = __departamentManager.count(DepartamentFields.DEPARTAMENTID.equal(__departamentid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(DEPARTAMENTID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("departament.departament"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("departament.departamentID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__departamentid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}