
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.EmailFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EmailFilterForm extends PinbalAdminBaseFilterForm implements EmailFields {

  private java.lang.Long emailIDDesde;

  public java.lang.Long getEmailIDDesde() {
    return this.emailIDDesde;
  }

  public void setEmailIDDesde(java.lang.Long emailIDDesde) {
    this.emailIDDesde = emailIDDesde;
  }


  private java.lang.Long emailIDFins;

  public java.lang.Long getEmailIDFins() {
    return this.emailIDFins;
  }

  public void setEmailIDFins(java.lang.Long emailIDFins) {
    this.emailIDFins = emailIDFins;
  }


  private java.lang.String destinataris;

  public java.lang.String getDestinataris() {
    return this.destinataris;
  }

  public void setDestinataris(java.lang.String destinataris) {
    this.destinataris = destinataris;
  }


  private java.lang.String subject;

  public java.lang.String getSubject() {
    return this.subject;
  }

  public void setSubject(java.lang.String subject) {
    this.subject = subject;
  }


  private java.lang.String message;

  public java.lang.String getMessage() {
    return this.message;
  }

  public void setMessage(java.lang.String message) {
    this.message = message;
  }


  private java.sql.Timestamp dataEnviamentDesde;

  public java.sql.Timestamp getDataEnviamentDesde() {
    return this.dataEnviamentDesde;
  }

  public void setDataEnviamentDesde(java.sql.Timestamp dataEnviamentDesde) {
    this.dataEnviamentDesde = dataEnviamentDesde;
  }


  private java.sql.Timestamp dataEnviamentFins;

  public java.sql.Timestamp getDataEnviamentFins() {
    return this.dataEnviamentFins;
  }

  public void setDataEnviamentFins(java.sql.Timestamp dataEnviamentFins) {
    this.dataEnviamentFins = dataEnviamentFins;
  }


  private java.lang.String enviador;

  public java.lang.String getEnviador() {
    return this.enviador;
  }

  public void setEnviador(java.lang.String enviador) {
    this.enviador = enviador;
  }


  public EmailFilterForm() {
  }
  
  public EmailFilterForm(EmailFilterForm __toClone) {
    super(__toClone);
    this.emailIDDesde = __toClone.emailIDDesde;
    this.emailIDFins = __toClone.emailIDFins;
    this.destinataris = __toClone.destinataris;
    this.subject = __toClone.subject;
    this.message = __toClone.message;
    this.dataEnviamentDesde = __toClone.dataEnviamentDesde;
    this.dataEnviamentFins = __toClone.dataEnviamentFins;
    this.enviador = __toClone.enviador;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DESTINATARIS ,SUBJECT ,MESSAGE }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DATAENVIAMENT ,ENVIADOR }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(DATAENVIAMENT, org.fundaciobit.genapp.common.query.OrderType.DESC )};


  public OrderBy[] getDefaultOrderBy() {
    return this.defaultOrderBy;
  }

  public void setDefaultOrderBy(OrderBy[] defOrderBy) {
    this.defaultOrderBy = defOrderBy;
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

   // -----------------------
   // Maps de referencies.
   // -----------------------

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
