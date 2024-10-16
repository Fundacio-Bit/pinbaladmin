
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.PINFOFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PINFOFilterForm extends PinbalAdminBaseFilterForm implements PINFOFields {

  private java.lang.Long PinfoIDDesde;

  public java.lang.Long getPinfoIDDesde() {
    return this.PinfoIDDesde;
  }

  public void setPinfoIDDesde(java.lang.Long PinfoIDDesde) {
    this.PinfoIDDesde = PinfoIDDesde;
  }


  private java.lang.Long PinfoIDFins;

  public java.lang.Long getPinfoIDFins() {
    return this.PinfoIDFins;
  }

  public void setPinfoIDFins(java.lang.Long PinfoIDFins) {
    this.PinfoIDFins = PinfoIDFins;
  }


  private java.lang.Long IncidenciaIDDesde;

  public java.lang.Long getIncidenciaIDDesde() {
    return this.IncidenciaIDDesde;
  }

  public void setIncidenciaIDDesde(java.lang.Long IncidenciaIDDesde) {
    this.IncidenciaIDDesde = IncidenciaIDDesde;
  }


  private java.lang.Long IncidenciaIDFins;

  public java.lang.Long getIncidenciaIDFins() {
    return this.IncidenciaIDFins;
  }

  public void setIncidenciaIDFins(java.lang.Long IncidenciaIDFins) {
    this.IncidenciaIDFins = IncidenciaIDFins;
  }


  private java.lang.Long estatDesde;

  public java.lang.Long getEstatDesde() {
    return this.estatDesde;
  }

  public void setEstatDesde(java.lang.Long estatDesde) {
    this.estatDesde = estatDesde;
  }


  private java.lang.Long estatFins;

  public java.lang.Long getEstatFins() {
    return this.estatFins;
  }

  public void setEstatFins(java.lang.Long estatFins) {
    this.estatFins = estatFins;
  }


  private java.lang.String portafibid;

  public java.lang.String getPortafibid() {
    return this.portafibid;
  }

  public void setPortafibid(java.lang.String portafibid) {
    this.portafibid = portafibid;
  }


  public PINFOFilterForm() {
  }
  
  public PINFOFilterForm(PINFOFilterForm __toClone) {
    super(__toClone);
    this.PinfoIDDesde = __toClone.PinfoIDDesde;
    this.PinfoIDFins = __toClone.PinfoIDFins;
    this.IncidenciaIDDesde = __toClone.IncidenciaIDDesde;
    this.IncidenciaIDFins = __toClone.IncidenciaIDFins;
    this.estatDesde = __toClone.estatDesde;
    this.estatFins = __toClone.estatFins;
    this.portafibid = __toClone.portafibid;
    this.mapOfIncidenciaTecnicaForIncidenciaID = __toClone.mapOfIncidenciaTecnicaForIncidenciaID;
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
  private Map<String, String> mapOfIncidenciaTecnicaForIncidenciaID;

  public Map<String, String> getMapOfIncidenciaTecnicaForIncidenciaID() {
    return this.mapOfIncidenciaTecnicaForIncidenciaID;
  }

  public void setMapOfIncidenciaTecnicaForIncidenciaID(Map<String, String> mapOfIncidenciaTecnicaForIncidenciaID) {
    this.mapOfIncidenciaTecnicaForIncidenciaID = mapOfIncidenciaTecnicaForIncidenciaID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
