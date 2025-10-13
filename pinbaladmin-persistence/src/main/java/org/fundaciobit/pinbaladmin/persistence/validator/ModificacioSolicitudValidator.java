package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.ModificacioSolicitud;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class ModificacioSolicitudValidator<I extends ModificacioSolicitud>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements ModificacioSolicitudFields {

    protected final Logger log = Logger.getLogger(getClass());


  public ModificacioSolicitudValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IModificacioSolicitudManager __modificacioSolicitudManager
    ,org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager
    ,org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,SOLICITUDID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITUDID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ESMENA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESMENA)));

    // Check size
    if (__vr.getFieldErrorCount(PROCEDIMENTCODI) == 0) {
      java.lang.String __procedimentcodi = __target__.getProcedimentCodi();
      if (__procedimentcodi!= null && __procedimentcodi.length() > 255) {
        __vr.rejectValue(PROCEDIMENTCODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTCODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(PROCEDIMENTNOM) == 0) {
      java.lang.String __procedimentnom = __target__.getProcedimentNom();
      if (__procedimentnom!= null && __procedimentnom.length() > 2000) {
        __vr.rejectValue(PROCEDIMENTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2000)));
      }
    }

    if (__vr.getFieldErrorCount(CODISIANOU) == 0) {
      java.lang.String __codisianou = __target__.getCodiSiaNou();
      if (__codisianou!= null && __codisianou.length() > 255) {
        __vr.rejectValue(CODISIANOU, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODISIANOU)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(PROCEDIMENTTIPUS) == 0) {
      java.lang.String __procedimenttipus = __target__.getProcedimentTipus();
      if (__procedimenttipus!= null && __procedimenttipus.length() > 255) {
        __vr.rejectValue(PROCEDIMENTTIPUS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTTIPUS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(NOTES) == 0) {
      java.lang.String __notes = __target__.getNotes();
      if (__notes!= null && __notes.length() > 2000) {
        __vr.rejectValue(NOTES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOTES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2000)));
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
      java.lang.String __responsableprocemail = __target__.getResponsableProceMail();
      if (__responsableprocemail!= null && __responsableprocemail.length() > 255) {
        __vr.rejectValue(RESPONSABLEPROCEMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPONSABLEPROCEMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(CONSENTIMENT) == 0) {
      java.lang.String __consentiment = __target__.getConsentiment();
      if (__consentiment!= null && __consentiment.length() > 80) {
        __vr.rejectValue(CONSENTIMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONSENTIMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(80)));
      }
    }

    if (__vr.getFieldErrorCount(SOLICITANTNOM) == 0) {
      java.lang.String __solicitantnom = __target__.getSolicitantNom();
      if (__solicitantnom!= null && __solicitantnom.length() > 255) {
        __vr.rejectValue(SOLICITANTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(SOLICITANTNIF) == 0) {
      java.lang.String __solicitantnif = __target__.getSolicitantNif();
      if (__solicitantnif!= null && __solicitantnif.length() > 255) {
        __vr.rejectValue(SOLICITANTNIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTNIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(SOLICITANTMAIL) == 0) {
      java.lang.String __solicitantmail = __target__.getSolicitantMail();
      if (__solicitantmail!= null && __solicitantmail.length() > 255) {
        __vr.rejectValue(SOLICITANTMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(SOLICITANTUSERNAME) == 0) {
      java.lang.String __solicitantusername = __target__.getSolicitantUsername();
      if (__solicitantusername!= null && __solicitantusername.length() > 255) {
        __vr.rejectValue(SOLICITANTUSERNAME, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTUSERNAME)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(CONTACTENOM) == 0) {
      java.lang.String __contactenom = __target__.getContactenom();
      if (__contactenom!= null && __contactenom.length() > 255) {
        __vr.rejectValue(CONTACTENOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTACTENOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(CONTACTEMAIL) == 0) {
      java.lang.String __contactemail = __target__.getContactemail();
      if (__contactemail!= null && __contactemail.length() > 255) {
        __vr.rejectValue(CONTACTEMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTACTEMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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

    if (__vr.getFieldErrorCount(ORGANID) == 0) {
      java.lang.Long __organid = __target__.getOrganID();
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