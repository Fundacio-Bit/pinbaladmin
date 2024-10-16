package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.PINFO;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.PINFOFields;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PINFOValidator<I extends PINFO>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements PINFOFields {

    protected final Logger log = Logger.getLogger(getClass());


  public PINFOValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager
    ,org.fundaciobit.pinbaladmin.model.dao.IPINFOManager __pINFOManager) {

    // Valors Not Null
    // Check size
    if (__vr.getFieldErrorCount(PORTAFIBID) == 0) {
      java.lang.String __portafibid = __target__.getPortafibid();
      if (__portafibid!= null && __portafibid.length() > 50) {
        __vr.rejectValue(PORTAFIBID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PORTAFIBID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
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