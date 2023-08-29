
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.TramitGDadesTitFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TramitGDadesTitFilterForm extends PinbalAdminBaseFilterForm implements TramitGDadesTitFields {

  private java.lang.Long dadestitidDesde;

  public java.lang.Long getDadestitidDesde() {
    return this.dadestitidDesde;
  }

  public void setDadestitidDesde(java.lang.Long dadestitidDesde) {
    this.dadestitidDesde = dadestitidDesde;
  }


  private java.lang.Long dadestitidFins;

  public java.lang.Long getDadestitidFins() {
    return this.dadestitidFins;
  }

  public void setDadestitidFins(java.lang.Long dadestitidFins) {
    this.dadestitidFins = dadestitidFins;
  }


  private java.lang.Long tramitidDesde;

  public java.lang.Long getTramitidDesde() {
    return this.tramitidDesde;
  }

  public void setTramitidDesde(java.lang.Long tramitidDesde) {
    this.tramitidDesde = tramitidDesde;
  }


  private java.lang.Long tramitidFins;

  public java.lang.Long getTramitidFins() {
    return this.tramitidFins;
  }

  public void setTramitidFins(java.lang.Long tramitidFins) {
    this.tramitidFins = tramitidFins;
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


  public TramitGDadesTitFilterForm() {
  }
  
  public TramitGDadesTitFilterForm(TramitGDadesTitFilterForm __toClone) {
    super(__toClone);
    this.dadestitidDesde = __toClone.dadestitidDesde;
    this.dadestitidFins = __toClone.dadestitidFins;
    this.tramitidDesde = __toClone.tramitidDesde;
    this.tramitidFins = __toClone.tramitidFins;
    this.nif = __toClone.nif;
    this.nom = __toClone.nom;
    this.llinatge1 = __toClone.llinatge1;
    this.llinatge2 = __toClone.llinatge2;
    this.carrec = __toClone.carrec;
    this.mapOfTramitAPersAutForTramitid = __toClone.mapOfTramitAPersAutForTramitid;
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
  private Map<String, String> mapOfTramitAPersAutForTramitid;

  public Map<String, String> getMapOfTramitAPersAutForTramitid() {
    return this.mapOfTramitAPersAutForTramitid;
  }

  public void setMapOfTramitAPersAutForTramitid(Map<String, String> mapOfTramitAPersAutForTramitid) {
    this.mapOfTramitAPersAutForTramitid = mapOfTramitAPersAutForTramitid;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
