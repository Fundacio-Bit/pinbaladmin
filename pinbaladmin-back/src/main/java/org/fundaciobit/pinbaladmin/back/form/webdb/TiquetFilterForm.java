
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.TiquetFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TiquetFilterForm extends PinbalAdminBaseFilterForm implements TiquetFields {

  private java.lang.Long tiquetIDDesde;

  public java.lang.Long getTiquetIDDesde() {
    return this.tiquetIDDesde;
  }

  public void setTiquetIDDesde(java.lang.Long tiquetIDDesde) {
    this.tiquetIDDesde = tiquetIDDesde;
  }


  private java.lang.Long tiquetIDFins;

  public java.lang.Long getTiquetIDFins() {
    return this.tiquetIDFins;
  }

  public void setTiquetIDFins(java.lang.Long tiquetIDFins) {
    this.tiquetIDFins = tiquetIDFins;
  }


  private java.lang.String titol;

  public java.lang.String getTitol() {
    return this.titol;
  }

  public void setTitol(java.lang.String titol) {
    this.titol = titol;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.String informador;

  public java.lang.String getInformador() {
    return this.informador;
  }

  public void setInformador(java.lang.String informador) {
    this.informador = informador;
  }


  private java.sql.Timestamp dataAltaDesde;

  public java.sql.Timestamp getDataAltaDesde() {
    return this.dataAltaDesde;
  }

  public void setDataAltaDesde(java.sql.Timestamp dataAltaDesde) {
    this.dataAltaDesde = dataAltaDesde;
  }


  private java.sql.Timestamp dataAltaFins;

  public java.sql.Timestamp getDataAltaFins() {
    return this.dataAltaFins;
  }

  public void setDataAltaFins(java.sql.Timestamp dataAltaFins) {
    this.dataAltaFins = dataAltaFins;
  }


  private java.lang.Long estatTiquetIDDesde;

  public java.lang.Long getEstatTiquetIDDesde() {
    return this.estatTiquetIDDesde;
  }

  public void setEstatTiquetIDDesde(java.lang.Long estatTiquetIDDesde) {
    this.estatTiquetIDDesde = estatTiquetIDDesde;
  }


  private java.lang.Long estatTiquetIDFins;

  public java.lang.Long getEstatTiquetIDFins() {
    return this.estatTiquetIDFins;
  }

  public void setEstatTiquetIDFins(java.lang.Long estatTiquetIDFins) {
    this.estatTiquetIDFins = estatTiquetIDFins;
  }


  private java.lang.Long tipusTiquetIDDesde;

  public java.lang.Long getTipusTiquetIDDesde() {
    return this.tipusTiquetIDDesde;
  }

  public void setTipusTiquetIDDesde(java.lang.Long tipusTiquetIDDesde) {
    this.tipusTiquetIDDesde = tipusTiquetIDDesde;
  }


  private java.lang.Long tipusTiquetIDFins;

  public java.lang.Long getTipusTiquetIDFins() {
    return this.tipusTiquetIDFins;
  }

  public void setTipusTiquetIDFins(java.lang.Long tipusTiquetIDFins) {
    this.tipusTiquetIDFins = tipusTiquetIDFins;
  }


  private java.lang.String versioPinbal;

  public java.lang.String getVersioPinbal() {
    return this.versioPinbal;
  }

  public void setVersioPinbal(java.lang.String versioPinbal) {
    this.versioPinbal = versioPinbal;
  }


  private java.sql.Timestamp dataIniciDesde;

  public java.sql.Timestamp getDataIniciDesde() {
    return this.dataIniciDesde;
  }

  public void setDataIniciDesde(java.sql.Timestamp dataIniciDesde) {
    this.dataIniciDesde = dataIniciDesde;
  }


  private java.sql.Timestamp dataIniciFins;

  public java.sql.Timestamp getDataIniciFins() {
    return this.dataIniciFins;
  }

  public void setDataIniciFins(java.sql.Timestamp dataIniciFins) {
    this.dataIniciFins = dataIniciFins;
  }


  private java.sql.Timestamp dataIncidenciaDesde;

  public java.sql.Timestamp getDataIncidenciaDesde() {
    return this.dataIncidenciaDesde;
  }

  public void setDataIncidenciaDesde(java.sql.Timestamp dataIncidenciaDesde) {
    this.dataIncidenciaDesde = dataIncidenciaDesde;
  }


  private java.sql.Timestamp dataIncidenciaFins;

  public java.sql.Timestamp getDataIncidenciaFins() {
    return this.dataIncidenciaFins;
  }

  public void setDataIncidenciaFins(java.sql.Timestamp dataIncidenciaFins) {
    this.dataIncidenciaFins = dataIncidenciaFins;
  }


  private java.lang.String solucionatPer;

  public java.lang.String getSolucionatPer() {
    return this.solucionatPer;
  }

  public void setSolucionatPer(java.lang.String solucionatPer) {
    this.solucionatPer = solucionatPer;
  }


  private java.sql.Timestamp datafiDesde;

  public java.sql.Timestamp getDatafiDesde() {
    return this.datafiDesde;
  }

  public void setDatafiDesde(java.sql.Timestamp datafiDesde) {
    this.datafiDesde = datafiDesde;
  }


  private java.sql.Timestamp datafiFins;

  public java.sql.Timestamp getDatafiFins() {
    return this.datafiFins;
  }

  public void setDatafiFins(java.sql.Timestamp datafiFins) {
    this.datafiFins = datafiFins;
  }


  private java.lang.String notes;

  public java.lang.String getNotes() {
    return this.notes;
  }

  public void setNotes(java.lang.String notes) {
    this.notes = notes;
  }


  private java.lang.Integer entornDesde;

  public java.lang.Integer getEntornDesde() {
    return this.entornDesde;
  }

  public void setEntornDesde(java.lang.Integer entornDesde) {
    this.entornDesde = entornDesde;
  }


  private java.lang.Integer entornFins;

  public java.lang.Integer getEntornFins() {
    return this.entornFins;
  }

  public void setEntornFins(java.lang.Integer entornFins) {
    this.entornFins = entornFins;
  }


  public TiquetFilterForm() {
  }
  
  public TiquetFilterForm(TiquetFilterForm __toClone) {
    super(__toClone);
    this.tiquetIDDesde = __toClone.tiquetIDDesde;
    this.tiquetIDFins = __toClone.tiquetIDFins;
    this.titol = __toClone.titol;
    this.descripcio = __toClone.descripcio;
    this.informador = __toClone.informador;
    this.dataAltaDesde = __toClone.dataAltaDesde;
    this.dataAltaFins = __toClone.dataAltaFins;
    this.estatTiquetIDDesde = __toClone.estatTiquetIDDesde;
    this.estatTiquetIDFins = __toClone.estatTiquetIDFins;
    this.tipusTiquetIDDesde = __toClone.tipusTiquetIDDesde;
    this.tipusTiquetIDFins = __toClone.tipusTiquetIDFins;
    this.versioPinbal = __toClone.versioPinbal;
    this.dataIniciDesde = __toClone.dataIniciDesde;
    this.dataIniciFins = __toClone.dataIniciFins;
    this.dataIncidenciaDesde = __toClone.dataIncidenciaDesde;
    this.dataIncidenciaFins = __toClone.dataIncidenciaFins;
    this.solucionatPer = __toClone.solucionatPer;
    this.datafiDesde = __toClone.datafiDesde;
    this.datafiFins = __toClone.datafiFins;
    this.notes = __toClone.notes;
    this.entornDesde = __toClone.entornDesde;
    this.entornFins = __toClone.entornFins;
    this.mapOfEstatTiquetForEstatTiquetID = __toClone.mapOfEstatTiquetForEstatTiquetID;
    this.mapOfTipusTiquetForTipusTiquetID = __toClone.mapOfTipusTiquetForTipusTiquetID;
    this.mapOfValuesForEntorn = __toClone.mapOfValuesForEntorn;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TITOL ,INFORMADOR ,VERSIOPINBAL ,DATAINCIDENCIA ,SOLUCIONATPER }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DATAALTA ,ESTATTIQUETID ,TIPUSTIQUETID ,VERSIOPINBAL ,DATAFI ,ENTORN }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(TITOL ) , new OrderBy(DATAALTA, org.fundaciobit.genapp.common.query.OrderType.DESC ) , new OrderBy(ESTATTIQUETID )};


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
  private Map<String, String> mapOfEstatTiquetForEstatTiquetID;

  public Map<String, String> getMapOfEstatTiquetForEstatTiquetID() {
    return this.mapOfEstatTiquetForEstatTiquetID;
  }

  public void setMapOfEstatTiquetForEstatTiquetID(Map<String, String> mapOfEstatTiquetForEstatTiquetID) {
    this.mapOfEstatTiquetForEstatTiquetID = mapOfEstatTiquetForEstatTiquetID;
  }



  private Map<String, String> mapOfTipusTiquetForTipusTiquetID;

  public Map<String, String> getMapOfTipusTiquetForTipusTiquetID() {
    return this.mapOfTipusTiquetForTipusTiquetID;
  }

  public void setMapOfTipusTiquetForTipusTiquetID(Map<String, String> mapOfTipusTiquetForTipusTiquetID) {
    this.mapOfTipusTiquetForTipusTiquetID = mapOfTipusTiquetForTipusTiquetID;
  }



  private Map<String, String> mapOfValuesForEntorn;

  public Map<String, String> getMapOfValuesForEntorn() {
    return this.mapOfValuesForEntorn;
  }

  public void setMapOfValuesForEntorn(Map<String, String> mapOfValuesForEntorn) {
    this.mapOfValuesForEntorn = mapOfValuesForEntorn;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
