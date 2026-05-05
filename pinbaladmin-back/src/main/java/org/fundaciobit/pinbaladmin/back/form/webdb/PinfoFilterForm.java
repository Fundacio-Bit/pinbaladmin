
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


  private java.lang.String entitat;

  public java.lang.String getEntitat() {
    return this.entitat;
  }

  public void setEntitat(java.lang.String entitat) {
    this.entitat = entitat;
  }


  private java.lang.String solicitantNIF;

  public java.lang.String getSolicitantNIF() {
    return this.solicitantNIF;
  }

  public void setSolicitantNIF(java.lang.String solicitantNIF) {
    this.solicitantNIF = solicitantNIF;
  }


  private java.lang.String solicitantNom;

  public java.lang.String getSolicitantNom() {
    return this.solicitantNom;
  }

  public void setSolicitantNom(java.lang.String solicitantNom) {
    this.solicitantNom = solicitantNom;
  }


  private java.util.List<java.lang.Long> estatSelect;

  public java.util.List<java.lang.Long> getEstatSelect() {
    return this.estatSelect;
  }

  public void setEstatSelect(java.util.List<java.lang.Long> estatSelect) {
    this.estatSelect = estatSelect;
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


  private java.lang.String destinatariNom;

  public java.lang.String getDestinatariNom() {
    return this.destinatariNom;
  }

  public void setDestinatariNom(java.lang.String destinatariNom) {
    this.destinatariNom = destinatariNom;
  }


  private java.lang.String missatgePinbal;

  public java.lang.String getMissatgePinbal() {
    return this.missatgePinbal;
  }

  public void setMissatgePinbal(java.lang.String missatgePinbal) {
    this.missatgePinbal = missatgePinbal;
  }


  private java.lang.String logpPnbal;

  public java.lang.String getLogpPnbal() {
    return this.logpPnbal;
  }

  public void setLogpPnbal(java.lang.String logpPnbal) {
    this.logpPnbal = logpPnbal;
  }


  private java.lang.String missatgeSolicitant;

  public java.lang.String getMissatgeSolicitant() {
    return this.missatgeSolicitant;
  }

  public void setMissatgeSolicitant(java.lang.String missatgeSolicitant) {
    this.missatgeSolicitant = missatgeSolicitant;
  }


  public PinfoFilterForm() {
  }
  
  public PinfoFilterForm(PinfoFilterForm __toClone) {
    super(__toClone);
    this.pinfoIDDesde = __toClone.pinfoIDDesde;
    this.pinfoIDFins = __toClone.pinfoIDFins;
    this.incidenciaIDDesde = __toClone.incidenciaIDDesde;
    this.incidenciaIDFins = __toClone.incidenciaIDFins;
    this.entitat = __toClone.entitat;
    this.solicitantNIF = __toClone.solicitantNIF;
    this.solicitantNom = __toClone.solicitantNom;
    this.estatSelect = __toClone.estatSelect;
    this.portafibid = __toClone.portafibid;
    this.destinatariNIF = __toClone.destinatariNIF;
    this.destinatariNom = __toClone.destinatariNom;
    this.missatgePinbal = __toClone.missatgePinbal;
    this.logpPnbal = __toClone.logpPnbal;
    this.missatgeSolicitant = __toClone.missatgeSolicitant;
    this.mapOfIncidenciaTecnicaForIncidenciaID = __toClone.mapOfIncidenciaTecnicaForIncidenciaID;
    this.mapOfValuesForEntitat = __toClone.mapOfValuesForEntitat;
    this.mapOfValuesForEstat = __toClone.mapOfValuesForEstat;
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



  private Map<String, String> mapOfValuesForEntitat;

  public Map<String, String> getMapOfValuesForEntitat() {
    return this.mapOfValuesForEntitat;
  }

  public void setMapOfValuesForEntitat(Map<String, String> mapOfValuesForEntitat) {
    this.mapOfValuesForEntitat = mapOfValuesForEntitat;
  }



  private Map<String, String> mapOfValuesForEstat;

  public Map<String, String> getMapOfValuesForEstat() {
    return this.mapOfValuesForEstat;
  }

  public void setMapOfValuesForEstat(Map<String, String> mapOfValuesForEstat) {
    this.mapOfValuesForEstat = mapOfValuesForEstat;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
