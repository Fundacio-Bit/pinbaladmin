
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.OperadorFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class OperadorFilterForm extends PinbalAdminBaseFilterForm implements OperadorFields {

  private java.lang.Long operadorIDDesde;

  public java.lang.Long getOperadorIDDesde() {
    return this.operadorIDDesde;
  }

  public void setOperadorIDDesde(java.lang.Long operadorIDDesde) {
    this.operadorIDDesde = operadorIDDesde;
  }


  private java.lang.Long operadorIDFins;

  public java.lang.Long getOperadorIDFins() {
    return this.operadorIDFins;
  }

  public void setOperadorIDFins(java.lang.Long operadorIDFins) {
    this.operadorIDFins = operadorIDFins;
  }


  private java.lang.String username;

  public java.lang.String getUsername() {
    return this.username;
  }

  public void setUsername(java.lang.String username) {
    this.username = username;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String email;

  public java.lang.String getEmail() {
    return this.email;
  }

  public void setEmail(java.lang.String email) {
    this.email = email;
  }


  public OperadorFilterForm() {
  }
  
  public OperadorFilterForm(OperadorFilterForm __toClone) {
    super(__toClone);
    this.operadorIDDesde = __toClone.operadorIDDesde;
    this.operadorIDFins = __toClone.operadorIDFins;
    this.username = __toClone.username;
    this.nom = __toClone.nom;
    this.email = __toClone.email;
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
