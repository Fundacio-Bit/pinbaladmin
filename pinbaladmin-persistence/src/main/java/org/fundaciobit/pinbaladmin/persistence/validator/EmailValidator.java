package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.Email;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.EmailFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EmailValidator<I extends Email>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements EmailFields {

    protected final Logger log = Logger.getLogger(getClass());


  public EmailValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IEmailManager __emailManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,DATAENVIAMENT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATAENVIAMENT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENVIADOR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENVIADOR)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DESTINATARIS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARIS)));

    __vr.rejectIfEmptyOrWhitespace(__target__,SUBJECT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUBJECT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MESSAGE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MESSAGE)));

    // Check size
    if (__vr.getFieldErrorCount(ENVIADOR) == 0) {
      java.lang.String __enviador = __target__.getEnviador();
      if (__enviador!= null && __enviador.length() > 255) {
        __vr.rejectValue(ENVIADOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENVIADOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(DESTINATARIS) == 0) {
      java.lang.String __destinataris = __target__.getDestinataris();
      if (__destinataris!= null && __destinataris.length() > 6000) {
        __vr.rejectValue(DESTINATARIS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARIS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(6000)));
      }
    }

    if (__vr.getFieldErrorCount(SUBJECT) == 0) {
      java.lang.String __subject = __target__.getSubject();
      if (__subject!= null && __subject.length() > 255) {
        __vr.rejectValue(SUBJECT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUBJECT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(MESSAGE) == 0) {
      java.lang.String __message = __target__.getMessage();
      if (__message!= null && __message.length() > 6000) {
        __vr.rejectValue(MESSAGE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MESSAGE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(6000)));
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