
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.ContacteFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ContacteFilterForm extends PinbalAdminBaseFilterForm implements ContacteFields {

  private java.lang.Long ContacteIDDesde;

  public java.lang.Long getContacteIDDesde() {
    return this.ContacteIDDesde;
  }

  public void setContacteIDDesde(java.lang.Long ContacteIDDesde) {
    this.ContacteIDDesde = ContacteIDDesde;
  }


  private java.lang.Long ContacteIDFins;

  public java.lang.Long getContacteIDFins() {
    return this.ContacteIDFins;
  }

  public void setContacteIDFins(java.lang.Long ContacteIDFins) {
    this.ContacteIDFins = ContacteIDFins;
  }


  private java.lang.String nif;

  public java.lang.String getNif() {
    return this.nif;
  }

  public void setNif(java.lang.String nif) {
    this.nif = nif;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String llinatge1;

  public java.lang.String getLlinatge1() {
    return this.llinatge1;
  }

  public void setLlinatge1(java.lang.String llinatge1) {
    this.llinatge1 = llinatge1;
  }


  private java.lang.String llinatge2;

  public java.lang.String getLlinatge2() {
    return this.llinatge2;
  }

  public void setLlinatge2(java.lang.String llinatge2) {
    this.llinatge2 = llinatge2;
  }


  private java.lang.String carrec;

  public java.lang.String getCarrec() {
    return this.carrec;
  }

  public void setCarrec(java.lang.String carrec) {
    this.carrec = carrec;
  }


  private java.lang.String telefon;

  public java.lang.String getTelefon() {
    return this.telefon;
  }

  public void setTelefon(java.lang.String telefon) {
    this.telefon = telefon;
  }


  private java.lang.String mail;

  public java.lang.String getMail() {
    return this.mail;
  }

  public void setMail(java.lang.String mail) {
    this.mail = mail;
  }


  private java.lang.String username;

  public java.lang.String getUsername() {
    return this.username;
  }

  public void setUsername(java.lang.String username) {
    this.username = username;
  }


  private java.lang.String nombrecompleto;

  public java.lang.String getNombrecompleto() {
    return this.nombrecompleto;
  }

  public void setNombrecompleto(java.lang.String nombrecompleto) {
    this.nombrecompleto = nombrecompleto;
  }


  public ContacteFilterForm() {
  }
  
  public ContacteFilterForm(ContacteFilterForm __toClone) {
    super(__toClone);
    this.ContacteIDDesde = __toClone.ContacteIDDesde;
    this.ContacteIDFins = __toClone.ContacteIDFins;
    this.nif = __toClone.nif;
    this.nom = __toClone.nom;
    this.llinatge1 = __toClone.llinatge1;
    this.llinatge2 = __toClone.llinatge2;
    this.carrec = __toClone.carrec;
    this.telefon = __toClone.telefon;
    this.mail = __toClone.mail;
    this.username = __toClone.username;
    this.nombrecompleto = __toClone.nombrecompleto;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }


  protected OrderBy[] defaultOrderBy = null;


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
