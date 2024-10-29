
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.PinfoFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PinfoFilterForm extends PinbalAdminBaseFilterForm implements PinfoFields {

  private java.lang.Long pinfoIDDesde;

  public java.lang.Long getPinfoIDDesde() {
    return this.pinfoIDDesde;
  }

  public void setPinfoIDDesde(java.lang.Long pinfoIDDesde) {
    this.pinfoIDDesde = pinfoIDDesde;
  }


  private java.lang.Long pinfoIDFins;

  public java.lang.Long getPinfoIDFins() {
    return this.pinfoIDFins;
  }

  public void setPinfoIDFins(java.lang.Long pinfoIDFins) {
    this.pinfoIDFins = pinfoIDFins;
  }


  private java.lang.Long incidenciaIDDesde;

  public java.lang.Long getIncidenciaIDDesde() {
    return this.incidenciaIDDesde;
  }

  public void setIncidenciaIDDesde(java.lang.Long incidenciaIDDesde) {
    this.incidenciaIDDesde = incidenciaIDDesde;
  }


  private java.lang.Long incidenciaIDFins;

  public java.lang.Long getIncidenciaIDFins() {
    return this.incidenciaIDFins;
  }

  public void setIncidenciaIDFins(java.lang.Long incidenciaIDFins) {
    this.incidenciaIDFins = incidenciaIDFins;
  }


  private java.lang.String solicitantNIF;

  public java.lang.String getSolicitantNIF() {
    return this.solicitantNIF;
  }

  public void setSolicitantNIF(java.lang.String solicitantNIF) {
    this.solicitantNIF = solicitantNIF;
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


  private java.lang.String destinatariNIF;

  public java.lang.String getDestinatariNIF() {
    return this.destinatariNIF;
  }

  public void setDestinatariNIF(java.lang.String destinatariNIF) {
    this.destinatariNIF = destinatariNIF;
  }


  public PinfoFilterForm() {
  }
  
  public PinfoFilterForm(PinfoFilterForm __toClone) {
    super(__toClone);
    this.pinfoIDDesde = __toClone.pinfoIDDesde;
    this.pinfoIDFins = __toClone.pinfoIDFins;
    this.incidenciaIDDesde = __toClone.incidenciaIDDesde;
    this.incidenciaIDFins = __toClone.incidenciaIDFins;
    this.solicitantNIF = __toClone.solicitantNIF;
    this.estatDesde = __toClone.estatDesde;
    this.estatFins = __toClone.estatFins;
    this.portafibid = __toClone.portafibid;
    this.destinatariNIF = __toClone.destinatariNIF;
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
