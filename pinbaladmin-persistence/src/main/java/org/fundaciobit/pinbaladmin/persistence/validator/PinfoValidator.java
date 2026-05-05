package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.PinfoFields;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PinfoValidator<I extends Pinfo>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements PinfoFields {

    protected final Logger log = Logger.getLogger(getClass());


  public PinfoValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager
    ,org.fundaciobit.pinbaladmin.model.dao.IPinfoManager __pinfoManager) {

    // Valors Not Null
    // Check size
    if (__vr.getFieldErrorCount(ENTITAT) == 0) {
      java.lang.String __entitat = __target__.getEntitat();
      if (__entitat!= null && __entitat.length() > 50) {
        __vr.rejectValue(ENTITAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(SOLICITANTNIF) == 0) {
      java.lang.String __solicitantnif = __target__.getSolicitantNIF();
      if (__solicitantnif!= null && __solicitantnif.length() > 100) {
        __vr.rejectValue(SOLICITANTNIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTNIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(SOLICITANTNOM) == 0) {
      java.lang.String __solicitantnom = __target__.getSolicitantNom();
      if (__solicitantnom!= null && __solicitantnom.length() > 255) {
        __vr.rejectValue(SOLICITANTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(PORTAFIBID) == 0) {
      java.lang.String __portafibid = __target__.getPortafibid();
      if (__portafibid!= null && __portafibid.length() > 50) {
        __vr.rejectValue(PORTAFIBID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PORTAFIBID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(DESTINATARINIF) == 0) {
      java.lang.String __destinatarinif = __target__.getDestinatariNIF();
      if (__destinatarinif!= null && __destinatarinif.length() > 100) {
        __vr.rejectValue(DESTINATARINIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARINIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(DESTINATARINOM) == 0) {
      java.lang.String __destinatarinom = __target__.getDestinatariNom();
      if (__destinatarinom!= null && __destinatarinom.length() > 255) {
        __vr.rejectValue(DESTINATARINOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARINOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(MISSATGEPINBAL) == 0) {
      java.lang.String __missatgepinbal = __target__.getMissatgePinbal();
      if (__missatgepinbal!= null && __missatgepinbal.length() > 2147483647) {
        __vr.rejectValue(MISSATGEPINBAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MISSATGEPINBAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(LOGPPNBAL) == 0) {
      java.lang.String __logppnbal = __target__.getLogpPnbal();
      if (__logppnbal!= null && __logppnbal.length() > 2147483647) {
        __vr.rejectValue(LOGPPNBAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LOGPPNBAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(MISSATGESOLICITANT) == 0) {
      java.lang.String __missatgesolicitant = __target__.getMissatgeSolicitant();
      if (__missatgesolicitant!= null && __missatgesolicitant.length() > 2147483647) {
        __vr.rejectValue(MISSATGESOLICITANT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MISSATGESOLICITANT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
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
    if (__vr.getFieldErrorCount(INCIDENCIAID) == 0) {
      java.lang.Long __incidenciaid = __target__.getIncidenciaID();
      if (__incidenciaid != null ) {
        Long __count_ = null;
        try { __count_ = __incidenciaTecnicaManager.count(IncidenciaTecnicaFields.INCIDENCIATECNICAID.equal(__incidenciaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(INCIDENCIAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("incidenciaTecnica.incidenciaTecnica"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("incidenciaTecnica.incidenciaTecnicaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__incidenciaid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}