
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.OrganFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class OrganFilterForm extends PinbalAdminBaseFilterForm implements OrganFields {

  private java.lang.Long organidDesde;

  public java.lang.Long getOrganidDesde() {
    return this.organidDesde;
  }

  public void setOrganidDesde(java.lang.Long organidDesde) {
    this.organidDesde = organidDesde;
  }


  private java.lang.Long organidFins;

  public java.lang.Long getOrganidFins() {
    return this.organidFins;
  }

  public void setOrganidFins(java.lang.Long organidFins) {
    this.organidFins = organidFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String dir3;

  public java.lang.String getDir3() {
    return this.dir3;
  }

  public void setDir3(java.lang.String dir3) {
    this.dir3 = dir3;
  }


  private java.lang.String dir3pare;

  public java.lang.String getDir3pare() {
    return this.dir3pare;
  }

  public void setDir3pare(java.lang.String dir3pare) {
    this.dir3pare = dir3pare;
  }


  private java.lang.Long entitatidDesde;

  public java.lang.Long getEntitatidDesde() {
    return this.entitatidDesde;
  }

  public void setEntitatidDesde(java.lang.Long entitatidDesde) {
    this.entitatidDesde = entitatidDesde;
  }


  private java.lang.Long entitatidFins;

  public java.lang.Long getEntitatidFins() {
    return this.entitatidFins;
  }

  public void setEntitatidFins(java.lang.Long entitatidFins) {
    this.entitatidFins = entitatidFins;
  }


  public OrganFilterForm() {
  }
  
  public OrganFilterForm(OrganFilterForm __toClone) {
    super(__toClone);
    this.organidDesde = __toClone.organidDesde;
    this.organidFins = __toClone.organidFins;
    this.nom = __toClone.nom;
    this.dir3 = __toClone.dir3;
    this.dir3pare = __toClone.dir3pare;
    this.entitatidDesde = __toClone.entitatidDesde;
    this.entitatidFins = __toClone.entitatidFins;
    this.mapOfEntitatForEntitatid = __toClone.mapOfEntitatForEntitatid;
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
  private Map<String, String> mapOfEntitatForEntitatid;

  public Map<String, String> getMapOfEntitatForEntitatid() {
    return this.mapOfEntitatForEntitatid;
  }

  public void setMapOfEntitatForEntitatid(Map<String, String> mapOfEntitatForEntitatid) {
    this.mapOfEntitatForEntitatid = mapOfEntitatForEntitatid;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
