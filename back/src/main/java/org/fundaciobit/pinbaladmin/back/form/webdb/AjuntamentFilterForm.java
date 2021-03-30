
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.AjuntamentFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AjuntamentFilterForm extends PinbalAdminBaseFilterForm implements AjuntamentFields {

  private java.lang.String ajuntamentID;

  public java.lang.String getAjuntamentID() {
    return this.ajuntamentID;
  }

  public void setAjuntamentID(java.lang.String ajuntamentID) {
    this.ajuntamentID = ajuntamentID;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String solicitud;

  public java.lang.String getSolicitud() {
    return this.solicitud;
  }

  public void setSolicitud(java.lang.String solicitud) {
    this.solicitud = solicitud;
  }


  private java.lang.String personaContacte;

  public java.lang.String getPersonaContacte() {
    return this.personaContacte;
  }

  public void setPersonaContacte(java.lang.String personaContacte) {
    this.personaContacte = personaContacte;
  }


  private java.lang.String estat;

  public java.lang.String getEstat() {
    return this.estat;
  }

  public void setEstat(java.lang.String estat) {
    this.estat = estat;
  }


  public AjuntamentFilterForm() {
  }
  
  public AjuntamentFilterForm(AjuntamentFilterForm __toClone) {
    super(__toClone);
    this.ajuntamentID = __toClone.ajuntamentID;
    this.nom = __toClone.nom;
    this.solicitud = __toClone.solicitud;
    this.personaContacte = __toClone.personaContacte;
    this.estat = __toClone.estat;
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


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(NOM )};


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
