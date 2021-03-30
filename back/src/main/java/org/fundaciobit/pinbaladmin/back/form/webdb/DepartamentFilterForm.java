
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.DepartamentFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class DepartamentFilterForm extends PinbalAdminBaseFilterForm implements DepartamentFields {

  private java.lang.Long departamentIDDesde;

  public java.lang.Long getDepartamentIDDesde() {
    return this.departamentIDDesde;
  }

  public void setDepartamentIDDesde(java.lang.Long departamentIDDesde) {
    this.departamentIDDesde = departamentIDDesde;
  }


  private java.lang.Long departamentIDFins;

  public java.lang.Long getDepartamentIDFins() {
    return this.departamentIDFins;
  }

  public void setDepartamentIDFins(java.lang.Long departamentIDFins) {
    this.departamentIDFins = departamentIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.Long areaIDDesde;

  public java.lang.Long getAreaIDDesde() {
    return this.areaIDDesde;
  }

  public void setAreaIDDesde(java.lang.Long areaIDDesde) {
    this.areaIDDesde = areaIDDesde;
  }


  private java.lang.Long areaIDFins;

  public java.lang.Long getAreaIDFins() {
    return this.areaIDFins;
  }

  public void setAreaIDFins(java.lang.Long areaIDFins) {
    this.areaIDFins = areaIDFins;
  }


  public DepartamentFilterForm() {
  }
  
  public DepartamentFilterForm(DepartamentFilterForm __toClone) {
    super(__toClone);
    this.departamentIDDesde = __toClone.departamentIDDesde;
    this.departamentIDFins = __toClone.departamentIDFins;
    this.nom = __toClone.nom;
    this.areaIDDesde = __toClone.areaIDDesde;
    this.areaIDFins = __toClone.areaIDFins;
    this.mapOfAreaForAreaID = __toClone.mapOfAreaForAreaID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { AREAID }));
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
  private Map<String, String> mapOfAreaForAreaID;

  public Map<String, String> getMapOfAreaForAreaID() {
    return this.mapOfAreaForAreaID;
  }

  public void setMapOfAreaForAreaID(Map<String, String> mapOfAreaForAreaID) {
    this.mapOfAreaForAreaID = mapOfAreaForAreaID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
