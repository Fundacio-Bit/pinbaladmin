
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.InfoMadridFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class InfoMadridFilterForm extends PinbalAdminBaseFilterForm implements InfoMadridFields {

  private java.lang.Long infoMadridIDDesde;

  public java.lang.Long getInfoMadridIDDesde() {
    return this.infoMadridIDDesde;
  }

  public void setInfoMadridIDDesde(java.lang.Long infoMadridIDDesde) {
    this.infoMadridIDDesde = infoMadridIDDesde;
  }


  private java.lang.Long infoMadridIDFins;

  public java.lang.Long getInfoMadridIDFins() {
    return this.infoMadridIDFins;
  }

  public void setInfoMadridIDFins(java.lang.Long infoMadridIDFins) {
    this.infoMadridIDFins = infoMadridIDFins;
  }


  private java.lang.String codi;

  public java.lang.String getCodi() {
    return this.codi;
  }

  public void setCodi(java.lang.String codi) {
    this.codi = codi;
  }


  private java.util.List<java.lang.Long> estatProcedimentSelect;

  public java.util.List<java.lang.Long> getEstatProcedimentSelect() {
    return this.estatProcedimentSelect;
  }

  public void setEstatProcedimentSelect(java.util.List<java.lang.Long> estatProcedimentSelect) {
    this.estatProcedimentSelect = estatProcedimentSelect;
  }


  private java.util.List<java.lang.Long> estatAutoritzacioSelect;

  public java.util.List<java.lang.Long> getEstatAutoritzacioSelect() {
    return this.estatAutoritzacioSelect;
  }

  public void setEstatAutoritzacioSelect(java.util.List<java.lang.Long> estatAutoritzacioSelect) {
    this.estatAutoritzacioSelect = estatAutoritzacioSelect;
  }


  private java.lang.String missatge;

  public java.lang.String getMissatge() {
    return this.missatge;
  }

  public void setMissatge(java.lang.String missatge) {
    this.missatge = missatge;
  }


  private java.lang.String consulta;

  public java.lang.String getConsulta() {
    return this.consulta;
  }

  public void setConsulta(java.lang.String consulta) {
    this.consulta = consulta;
  }


  private java.lang.String titularNom;

  public java.lang.String getTitularNom() {
    return this.titularNom;
  }

  public void setTitularNom(java.lang.String titularNom) {
    this.titularNom = titularNom;
  }


  private java.lang.String titularNif;

  public java.lang.String getTitularNif() {
    return this.titularNif;
  }

  public void setTitularNif(java.lang.String titularNif) {
    this.titularNif = titularNif;
  }


  private java.sql.Timestamp dataAutoritzacioDesde;

  public java.sql.Timestamp getDataAutoritzacioDesde() {
    return this.dataAutoritzacioDesde;
  }

  public void setDataAutoritzacioDesde(java.sql.Timestamp dataAutoritzacioDesde) {
    this.dataAutoritzacioDesde = dataAutoritzacioDesde;
  }


  private java.sql.Timestamp dataAutoritzacioFins;

  public java.sql.Timestamp getDataAutoritzacioFins() {
    return this.dataAutoritzacioFins;
  }

  public void setDataAutoritzacioFins(java.sql.Timestamp dataAutoritzacioFins) {
    this.dataAutoritzacioFins = dataAutoritzacioFins;
  }


  private java.sql.Timestamp dataEnviamentDesde;

  public java.sql.Timestamp getDataEnviamentDesde() {
    return this.dataEnviamentDesde;
  }

  public void setDataEnviamentDesde(java.sql.Timestamp dataEnviamentDesde) {
    this.dataEnviamentDesde = dataEnviamentDesde;
  }


  private java.sql.Timestamp dataEnviamentFins;

  public java.sql.Timestamp getDataEnviamentFins() {
    return this.dataEnviamentFins;
  }

  public void setDataEnviamentFins(java.sql.Timestamp dataEnviamentFins) {
    this.dataEnviamentFins = dataEnviamentFins;
  }


  private java.lang.Long intentsDesde;

  public java.lang.Long getIntentsDesde() {
    return this.intentsDesde;
  }

  public void setIntentsDesde(java.lang.Long intentsDesde) {
    this.intentsDesde = intentsDesde;
  }


  private java.lang.Long intentsFins;

  public java.lang.Long getIntentsFins() {
    return this.intentsFins;
  }

  public void setIntentsFins(java.lang.Long intentsFins) {
    this.intentsFins = intentsFins;
  }


  private java.sql.Timestamp dataConsultaDesde;

  public java.sql.Timestamp getDataConsultaDesde() {
    return this.dataConsultaDesde;
  }

  public void setDataConsultaDesde(java.sql.Timestamp dataConsultaDesde) {
    this.dataConsultaDesde = dataConsultaDesde;
  }


  private java.sql.Timestamp dataConsultaFins;

  public java.sql.Timestamp getDataConsultaFins() {
    return this.dataConsultaFins;
  }

  public void setDataConsultaFins(java.sql.Timestamp dataConsultaFins) {
    this.dataConsultaFins = dataConsultaFins;
  }


  public InfoMadridFilterForm() {
  }
  
  public InfoMadridFilterForm(InfoMadridFilterForm __toClone) {
    super(__toClone);
    this.infoMadridIDDesde = __toClone.infoMadridIDDesde;
    this.infoMadridIDFins = __toClone.infoMadridIDFins;
    this.codi = __toClone.codi;
    this.estatProcedimentSelect = __toClone.estatProcedimentSelect;
    this.estatAutoritzacioSelect = __toClone.estatAutoritzacioSelect;
    this.missatge = __toClone.missatge;
    this.consulta = __toClone.consulta;
    this.titularNom = __toClone.titularNom;
    this.titularNif = __toClone.titularNif;
    this.dataAutoritzacioDesde = __toClone.dataAutoritzacioDesde;
    this.dataAutoritzacioFins = __toClone.dataAutoritzacioFins;
    this.dataEnviamentDesde = __toClone.dataEnviamentDesde;
    this.dataEnviamentFins = __toClone.dataEnviamentFins;
    this.intentsDesde = __toClone.intentsDesde;
    this.intentsFins = __toClone.intentsFins;
    this.dataConsultaDesde = __toClone.dataConsultaDesde;
    this.dataConsultaFins = __toClone.dataConsultaFins;
    this.mapOfValuesForEstatProcediment = __toClone.mapOfValuesForEstatProcediment;
    this.mapOfValuesForEstatAutoritzacio = __toClone.mapOfValuesForEstatAutoritzacio;
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
  private Map<String, String> mapOfValuesForEstatProcediment;

  public Map<String, String> getMapOfValuesForEstatProcediment() {
    return this.mapOfValuesForEstatProcediment;
  }

  public void setMapOfValuesForEstatProcediment(Map<String, String> mapOfValuesForEstatProcediment) {
    this.mapOfValuesForEstatProcediment = mapOfValuesForEstatProcediment;
  }



  private Map<String, String> mapOfValuesForEstatAutoritzacio;

  public Map<String, String> getMapOfValuesForEstatAutoritzacio() {
    return this.mapOfValuesForEstatAutoritzacio;
  }

  public void setMapOfValuesForEstatAutoritzacio(Map<String, String> mapOfValuesForEstatAutoritzacio) {
    this.mapOfValuesForEstatAutoritzacio = mapOfValuesForEstatAutoritzacio;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
