package org.fundaciobit.pinbaladmin.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class IncidenciaTecnicaValidator<T> implements IncidenciaTecnicaFields {

  protected final Logger log = Logger.getLogger(getClass());


  public IncidenciaTecnicaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,TITOL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TITOL)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DESCRIPCIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATAINICI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATAINICI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ESTAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTAT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CREADOR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CREADOR)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CONTACTENOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTACTENOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CONTACTEEMAIL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTACTEEMAIL)));

    // Check size
    if (__vr.getFieldErrorCount(TITOL) == 0) {
      java.lang.String __titol = (java.lang.String)__vr.getFieldValue(__target__,TITOL);
      if (__titol!= null && __titol.length() > 255) {
        __vr.rejectValue(TITOL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TITOL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = (java.lang.String)__vr.getFieldValue(__target__,DESCRIPCIO);
      if (__descripcio!= null && __descripcio.length() > 2147483647) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }
    
    if (__vr.getFieldErrorCount(CREADOR) == 0) {
      java.lang.String __creador = (java.lang.String)__vr.getFieldValue(__target__,CREADOR);
      if (__creador!= null && __creador.length() > 255) {
        __vr.rejectValue(CREADOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CREADOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    

    if (__vr.getFieldErrorCount(NOMENTITAT) == 0) {
      java.lang.String __nomentitat = (java.lang.String)__vr.getFieldValue(__target__,NOMENTITAT);
      if (__nomentitat!= null && __nomentitat.length() > 255) {
        __vr.rejectValue(NOMENTITAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOMENTITAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(CONTACTENOM) == 0) {
      java.lang.String __contactenom = (java.lang.String)__vr.getFieldValue(__target__,CONTACTENOM);
      if (__contactenom!= null && __contactenom.length() > 255) {
        __vr.rejectValue(CONTACTENOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTACTENOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(CONTACTEEMAIL) == 0) {
      java.lang.String __contacteemail = (java.lang.String)__vr.getFieldValue(__target__,CONTACTEEMAIL);
      if (__contacteemail!= null && __contacteemail.length() > 100) {
        __vr.rejectValue(CONTACTEEMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTACTEEMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__vr.getFieldErrorCount(CONTACTETELEFON) == 0) {
      java.lang.String __contactetelefon = (java.lang.String)__vr.getFieldValue(__target__,CONTACTETELEFON);
      if (__contactetelefon!= null && __contactetelefon.length() > 100) {
        __vr.rejectValue(CONTACTETELEFON, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTACTETELEFON)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
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
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}