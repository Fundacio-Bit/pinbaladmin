package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class SolicitudValidator<I extends Solicitud>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements SolicitudFields {

    protected final Logger log = Logger.getLogger(getClass());


  public SolicitudValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager
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

    __vr.rejectIfEmptyOrWhitespace(__target__,CREADOR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CREADOR)));

    __vr.rejectIfEmptyOrWhitespace(__target__,OPERADOR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(OPERADOR)));

    // Check size
    if (__vr.getFieldErrorCount(PROCEDIMENTCODI) == 0) {
      java.lang.String __procedimentcodi = __target__.getProcedimentCodi();
      if (__procedimentcodi!= null && __procedimentcodi.length() > 255) {
        __vr.rejectValue(PROCEDIMENTCODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTCODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(CODIDESCRIPTIU) == 0) {
      java.lang.String __codidescriptiu = __target__.getCodiDescriptiu();
      if (__codidescriptiu!= null && __codidescriptiu.length() > 256) {
        __vr.rejectValue(CODIDESCRIPTIU, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIDESCRIPTIU)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }

    if (__vr.getFieldErrorCount(PROCEDIMENTNOM) == 0) {
      java.lang.String __procedimentnom = __target__.getProcedimentNom();
      if (__procedimentnom!= null && __procedimentnom.length() > 2000) {
        __vr.rejectValue(PROCEDIMENTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2000)));
      }
    }

    if (__vr.getFieldErrorCount(PROCEDIMENTTIPUS) == 0) {
      java.lang.String __procedimenttipus = __target__.getProcedimentTipus();
      if (__procedimenttipus!= null && __procedimenttipus.length() > 255) {
        __vr.rejectValue(PROCEDIMENTTIPUS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTTIPUS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(EXPEDIENTPID) == 0) {
      java.lang.String __expedientpid = __target__.getExpedientPid();
      if (__expedientpid!= null && __expedientpid.length() > 2147483647) {
        __vr.rejectValue(EXPEDIENTPID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EXPEDIENTPID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(ENTITATESTATAL) == 0) {
      java.lang.String __entitatestatal = __target__.getEntitatEstatal();
      if (__entitatestatal!= null && __entitatestatal.length() > 255) {
        __vr.rejectValue(ENTITATESTATAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATESTATAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(PINFO) == 0) {
      java.lang.String __pinfo = __target__.getPinfo();
      if (__pinfo!= null && __pinfo.length() > 255) {
        __vr.rejectValue(PINFO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PINFO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(PERSONACONTACTE) == 0) {
      java.lang.String __personacontacte = __target__.getPersonaContacte();
      if (__personacontacte!= null && __personacontacte.length() > 255) {
        __vr.rejectValue(PERSONACONTACTE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERSONACONTACTE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(PERSONACONTACTEEMAIL) == 0) {
      java.lang.String __personacontacteemail = __target__.getPersonaContacteEmail();
      if (__personacontacteemail!= null && __personacontacteemail.length() > 100) {
        __vr.rejectValue(PERSONACONTACTEEMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERSONACONTACTEEMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(RESPONSABLEPROCNOM) == 0) {
      java.lang.String __responsableprocnom = __target__.getResponsableProcNom();
      if (__responsableprocnom!= null && __responsableprocnom.length() > 255) {
        __vr.rejectValue(RESPONSABLEPROCNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPONSABLEPROCNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(RESPONSABLEPROCEMAIL) == 0) {
      java.lang.String __responsableprocemail = __target__.getResponsableProcEmail();
      if (__responsableprocemail!= null && __responsableprocemail.length() > 255) {
        __vr.rejectValue(RESPONSABLEPROCEMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPONSABLEPROCEMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(NOTES) == 0) {
      java.lang.String __notes = __target__.getNotes();
      if (__notes!= null && __notes.length() > 2550) {
        __vr.rejectValue(NOTES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOTES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2550)));
      }
    }

    if (__vr.getFieldErrorCount(DENOMINACIO) == 0) {
      java.lang.String __denominacio = __target__.getDenominacio();
      if (__denominacio!= null && __denominacio.length() > 255) {
        __vr.rejectValue(DENOMINACIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DENOMINACIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(DIR3) == 0) {
      java.lang.String __dir3 = __target__.getDir3();
      if (__dir3!= null && __dir3.length() > 50) {
        __vr.rejectValue(DIR3, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DIR3)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(NIF) == 0) {
      java.lang.String __nif = __target__.getNif();
      if (__nif!= null && __nif.length() > 40) {
        __vr.rejectValue(NIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(40)));
      }
    }

    if (__vr.getFieldErrorCount(CREADOR) == 0) {
      java.lang.String __creador = __target__.getCreador();
      if (__creador!= null && __creador.length() > 100) {
        __vr.rejectValue(CREADOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CREADOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(OPERADOR) == 0) {
      java.lang.String __operador = __target__.getOperador();
      if (__operador!= null && __operador.length() > 100) {
        __vr.rejectValue(OPERADOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(OPERADOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(CONSENTIMENT) == 0) {
      java.lang.String __consentiment = __target__.getConsentiment();
      if (__consentiment!= null && __consentiment.length() > 80) {
        __vr.rejectValue(CONSENTIMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONSENTIMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(80)));
      }
    }

    if (__vr.getFieldErrorCount(URLCONSENTIMENT) == 0) {
      java.lang.String __urlconsentiment = __target__.getUrlconsentiment();
      if (__urlconsentiment!= null && __urlconsentiment.length() > 255) {
        __vr.rejectValue(URLCONSENTIMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(URLCONSENTIMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(CONSENTIMENTADJUNT) == 0) {
      java.lang.String __consentimentadjunt = __target__.getConsentimentadjunt();
      if (__consentimentadjunt!= null && __consentimentadjunt.length() > 200) {
        __vr.rejectValue(CONSENTIMENTADJUNT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONSENTIMENTADJUNT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(200)));
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
    if (__vr.getFieldErrorCount(ORGANID) == 0) {
      java.lang.Long __organid = __target__.getOrganid();
      if (__organid != null ) {
        Long __count_ = null;
        try { __count_ = __organManager.count(OrganFields.ORGANID.equal(__organid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(ORGANID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("organ.organ"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("organ.organid"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__organid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}