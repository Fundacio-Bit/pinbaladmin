package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.InfoMadridFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class InfoMadridValidator<I extends InfoMadrid>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements InfoMadridFields {

    protected final Logger log = Logger.getLogger(getClass());


  public InfoMadridValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IInfoMadridManager __infoMadridManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,CODI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,INTENTS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INTENTS)));

    // Check size
    if (__vr.getFieldErrorCount(CODI) == 0) {
      java.lang.String __codi = __target__.getCodi();
      if (__codi!= null && __codi.length() > 20) {
        __vr.rejectValue(CODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(20)));
      }
    }

    if (__vr.getFieldErrorCount(MISSATGE) == 0) {
      java.lang.String __missatge = __target__.getMissatge();
      if (__missatge!= null && __missatge.length() > 2147483647) {
        __vr.rejectValue(MISSATGE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MISSATGE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(CONSULTA) == 0) {
      java.lang.String __consulta = __target__.getConsulta();
      if (__consulta!= null && __consulta.length() > 240) {
        __vr.rejectValue(CONSULTA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONSULTA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(TITULARNOM) == 0) {
      java.lang.String __titularnom = __target__.getTitularNom();
      if (__titularnom!= null && __titularnom.length() > 240) {
        __vr.rejectValue(TITULARNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TITULARNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(240)));
      }
    }

    if (__vr.getFieldErrorCount(TITULARNIF) == 0) {
      java.lang.String __titularnif = __target__.getTitularNif();
      if (__titularnif!= null && __titularnif.length() > 20) {
        __vr.rejectValue(TITULARNIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TITULARNIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(20)));
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
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}