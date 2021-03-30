
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.GrupEntitatCedentFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class GrupEntitatCedentFilterForm extends PinbalAdminBaseFilterForm implements GrupEntitatCedentFields {

  private java.lang.Long grupEntitatCedentIDDesde;

  public java.lang.Long getGrupEntitatCedentIDDesde() {
    return this.grupEntitatCedentIDDesde;
  }

  public void setGrupEntitatCedentIDDesde(java.lang.Long grupEntitatCedentIDDesde) {
    this.grupEntitatCedentIDDesde = grupEntitatCedentIDDesde;
  }


  private java.lang.Long grupEntitatCedentIDFins;

  public java.lang.Long getGrupEntitatCedentIDFins() {
    return this.grupEntitatCedentIDFins;
  }

  public void setGrupEntitatCedentIDFins(java.lang.Long grupEntitatCedentIDFins) {
    this.grupEntitatCedentIDFins = grupEntitatCedentIDFins;
  }


  private java.lang.Long grupEntitatIDDesde;

  public java.lang.Long getGrupEntitatIDDesde() {
    return this.grupEntitatIDDesde;
  }

  public void setGrupEntitatIDDesde(java.lang.Long grupEntitatIDDesde) {
    this.grupEntitatIDDesde = grupEntitatIDDesde;
  }


  private java.lang.Long grupEntitatIDFins;

  public java.lang.Long getGrupEntitatIDFins() {
    return this.grupEntitatIDFins;
  }

  public void setGrupEntitatIDFins(java.lang.Long grupEntitatIDFins) {
    this.grupEntitatIDFins = grupEntitatIDFins;
  }


  private java.lang.Long cedentIDDesde;

  public java.lang.Long getCedentIDDesde() {
    return this.cedentIDDesde;
  }

  public void setCedentIDDesde(java.lang.Long cedentIDDesde) {
    this.cedentIDDesde = cedentIDDesde;
  }


  private java.lang.Long cedentIDFins;

  public java.lang.Long getCedentIDFins() {
    return this.cedentIDFins;
  }

  public void setCedentIDFins(java.lang.Long cedentIDFins) {
    this.cedentIDFins = cedentIDFins;
  }


  public GrupEntitatCedentFilterForm() {
  }
  
  public GrupEntitatCedentFilterForm(GrupEntitatCedentFilterForm __toClone) {
    super(__toClone);
    this.grupEntitatCedentIDDesde = __toClone.grupEntitatCedentIDDesde;
    this.grupEntitatCedentIDFins = __toClone.grupEntitatCedentIDFins;
    this.grupEntitatIDDesde = __toClone.grupEntitatIDDesde;
    this.grupEntitatIDFins = __toClone.grupEntitatIDFins;
    this.cedentIDDesde = __toClone.cedentIDDesde;
    this.cedentIDFins = __toClone.cedentIDFins;
    this.mapOfGrupEntitatForGrupEntitatID = __toClone.mapOfGrupEntitatForGrupEntitatID;
    this.mapOfEntitatServeiForCedentID = __toClone.mapOfEntitatServeiForCedentID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { GRUPENTITATID ,CEDENTID }));
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
  private Map<String, String> mapOfGrupEntitatForGrupEntitatID;

  public Map<String, String> getMapOfGrupEntitatForGrupEntitatID() {
    return this.mapOfGrupEntitatForGrupEntitatID;
  }

  public void setMapOfGrupEntitatForGrupEntitatID(Map<String, String> mapOfGrupEntitatForGrupEntitatID) {
    this.mapOfGrupEntitatForGrupEntitatID = mapOfGrupEntitatForGrupEntitatID;
  }



  private Map<String, String> mapOfEntitatServeiForCedentID;

  public Map<String, String> getMapOfEntitatServeiForCedentID() {
    return this.mapOfEntitatServeiForCedentID;
  }

  public void setMapOfEntitatServeiForCedentID(Map<String, String> mapOfEntitatServeiForCedentID) {
    this.mapOfEntitatServeiForCedentID = mapOfEntitatServeiForCedentID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
