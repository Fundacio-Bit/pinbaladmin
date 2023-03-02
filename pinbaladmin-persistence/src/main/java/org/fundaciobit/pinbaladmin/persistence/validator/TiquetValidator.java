package org.fundaciobit.pinbaladmin.persistence.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.model.entity.Tiquet;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.pinbaladmin.model.fields.TiquetFields;
import org.fundaciobit.pinbaladmin.model.fields.EstatTiquetFields;
import org.fundaciobit.pinbaladmin.model.fields.TipusTiquetFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class TiquetValidator<I extends Tiquet>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements TiquetFields {

    protected final Logger log = Logger.getLogger(getClass());


  public TiquetValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,org.fundaciobit.pinbaladmin.model.dao.IEstatTiquetManager __estatTiquetManager
    ,org.fundaciobit.pinbaladmin.model.dao.ITipusTiquetManager __tipusTiquetManager
    ,org.fundaciobit.pinbaladmin.model.dao.ITiquetManager __tiquetManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,TITOL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TITOL)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DESCRIPCIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,INFORMADOR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INFORMADOR)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATAALTA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATAALTA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ESTATTIQUETID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTATTIQUETID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSTIQUETID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSTIQUETID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,VERSIOPINBAL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VERSIOPINBAL)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTORN, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTORN)));

    // Check size
    if (__vr.getFieldErrorCount(TITOL) == 0) {
      java.lang.String __titol = __target__.getTitol();
      if (__titol!= null && __titol.length() > 256) {
        __vr.rejectValue(TITOL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TITOL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }

    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = __target__.getDescripcio();
      if (__descripcio!= null && __descripcio.length() > 3000) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(3000)));
      }
    }

    if (__vr.getFieldErrorCount(INFORMADOR) == 0) {
      java.lang.String __informador = __target__.getInformador();
      if (__informador!= null && __informador.length() > 100) {
        __vr.rejectValue(INFORMADOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INFORMADOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(VERSIOPINBAL) == 0) {
      java.lang.String __versiopinbal = __target__.getVersioPinbal();
      if (__versiopinbal!= null && __versiopinbal.length() > 100) {
        __vr.rejectValue(VERSIOPINBAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VERSIOPINBAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(SOLUCIONATPER) == 0) {
      java.lang.String __solucionatper = __target__.getSolucionatPer();
      if (__solucionatper!= null && __solucionatper.length() > 100) {
        __vr.rejectValue(SOLUCIONATPER, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLUCIONATPER)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(NOTES) == 0) {
      java.lang.String __notes = __target__.getNotes();
      if (__notes!= null && __notes.length() > 1000) {
        __vr.rejectValue(NOTES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOTES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(1000)));
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
    if (__vr.getFieldErrorCount(ESTATTIQUETID) == 0) {
      java.lang.Long __estattiquetid = __target__.getEstatTiquetID();
      Long __count_ = null;
      try { __count_ = __estatTiquetManager.count(EstatTiquetFields.ESTATTIQUETID.equal(__estattiquetid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ESTATTIQUETID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("estatTiquet.estatTiquet"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("estatTiquet.estatTiquetID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__estattiquetid)));
      }
    }

    if (__vr.getFieldErrorCount(TIPUSTIQUETID) == 0) {
      java.lang.Long __tipustiquetid = __target__.getTipusTiquetID();
      Long __count_ = null;
      try { __count_ = __tipusTiquetManager.count(TipusTiquetFields.TIPUSTIQUETID.equal(__tipustiquetid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(TIPUSTIQUETID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusTiquet.tipusTiquet"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusTiquet.tipusTiquetID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipustiquetid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}