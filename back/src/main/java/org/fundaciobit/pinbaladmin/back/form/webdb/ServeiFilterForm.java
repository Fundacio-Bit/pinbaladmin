
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ServeiFilterForm extends PinbalAdminBaseFilterForm implements ServeiFields {

  private java.lang.Long serveiIDDesde;

  public java.lang.Long getServeiIDDesde() {
    return this.serveiIDDesde;
  }

  public void setServeiIDDesde(java.lang.Long serveiIDDesde) {
    this.serveiIDDesde = serveiIDDesde;
  }


  private java.lang.Long serveiIDFins;

  public java.lang.Long getServeiIDFins() {
    return this.serveiIDFins;
  }

  public void setServeiIDFins(java.lang.Long serveiIDFins) {
    this.serveiIDFins = serveiIDFins;
  }


  private java.lang.String codi;

  public java.lang.String getCodi() {
    return this.codi;
  }

  public void setCodi(java.lang.String codi) {
    this.codi = codi;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.Long formulariIDDesde;

  public java.lang.Long getFormulariIDDesde() {
    return this.formulariIDDesde;
  }

  public void setFormulariIDDesde(java.lang.Long formulariIDDesde) {
    this.formulariIDDesde = formulariIDDesde;
  }


  private java.lang.Long formulariIDFins;

  public java.lang.Long getFormulariIDFins() {
    return this.formulariIDFins;
  }

  public void setFormulariIDFins(java.lang.Long formulariIDFins) {
    this.formulariIDFins = formulariIDFins;
  }


  private java.lang.Long entitatServeiIDDesde;

  public java.lang.Long getEntitatServeiIDDesde() {
    return this.entitatServeiIDDesde;
  }

  public void setEntitatServeiIDDesde(java.lang.Long entitatServeiIDDesde) {
    this.entitatServeiIDDesde = entitatServeiIDDesde;
  }


  private java.lang.Long entitatServeiIDFins;

  public java.lang.Long getEntitatServeiIDFins() {
    return this.entitatServeiIDFins;
  }

  public void setEntitatServeiIDFins(java.lang.Long entitatServeiIDFins) {
    this.entitatServeiIDFins = entitatServeiIDFins;
  }


  private java.lang.Long estatServeiIDDesde;

  public java.lang.Long getEstatServeiIDDesde() {
    return this.estatServeiIDDesde;
  }

  public void setEstatServeiIDDesde(java.lang.Long estatServeiIDDesde) {
    this.estatServeiIDDesde = estatServeiIDDesde;
  }


  private java.lang.Long estatServeiIDFins;

  public java.lang.Long getEstatServeiIDFins() {
    return this.estatServeiIDFins;
  }

  public void setEstatServeiIDFins(java.lang.Long estatServeiIDFins) {
    this.estatServeiIDFins = estatServeiIDFins;
  }


  private java.lang.Integer tipusConsentimentDesde;

  public java.lang.Integer getTipusConsentimentDesde() {
    return this.tipusConsentimentDesde;
  }

  public void setTipusConsentimentDesde(java.lang.Integer tipusConsentimentDesde) {
    this.tipusConsentimentDesde = tipusConsentimentDesde;
  }


  private java.lang.Integer tipusConsentimentFins;

  public java.lang.Integer getTipusConsentimentFins() {
    return this.tipusConsentimentFins;
  }

  public void setTipusConsentimentFins(java.lang.Integer tipusConsentimentFins) {
    this.tipusConsentimentFins = tipusConsentimentFins;
  }


  public ServeiFilterForm() {
  }
  
  public ServeiFilterForm(ServeiFilterForm __toClone) {
    super(__toClone);
    this.serveiIDDesde = __toClone.serveiIDDesde;
    this.serveiIDFins = __toClone.serveiIDFins;
    this.codi = __toClone.codi;
    this.nom = __toClone.nom;
    this.descripcio = __toClone.descripcio;
    this.formulariIDDesde = __toClone.formulariIDDesde;
    this.formulariIDFins = __toClone.formulariIDFins;
    this.entitatServeiIDDesde = __toClone.entitatServeiIDDesde;
    this.entitatServeiIDFins = __toClone.entitatServeiIDFins;
    this.estatServeiIDDesde = __toClone.estatServeiIDDesde;
    this.estatServeiIDFins = __toClone.estatServeiIDFins;
    this.tipusConsentimentDesde = __toClone.tipusConsentimentDesde;
    this.tipusConsentimentFins = __toClone.tipusConsentimentFins;
    this.mapOfFormulariForFormulariID = __toClone.mapOfFormulariForFormulariID;
    this.mapOfEntitatServeiForEntitatServeiID = __toClone.mapOfEntitatServeiForEntitatServeiID;
    this.mapOfEstatServeiForEstatServeiID = __toClone.mapOfEstatServeiForEstatServeiID;
    this.mapOfValuesForTipusConsentiment = __toClone.mapOfValuesForTipusConsentiment;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { CODI ,NOM }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ENTITATSERVEIID ,ESTATSERVEIID ,TIPUSCONSENTIMENT }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(CODI ) , new OrderBy(NOM ) , new OrderBy(ENTITATSERVEIID ) , new OrderBy(ESTATSERVEIID )};


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
  private Map<String, String> mapOfFormulariForFormulariID;

  public Map<String, String> getMapOfFormulariForFormulariID() {
    return this.mapOfFormulariForFormulariID;
  }

  public void setMapOfFormulariForFormulariID(Map<String, String> mapOfFormulariForFormulariID) {
    this.mapOfFormulariForFormulariID = mapOfFormulariForFormulariID;
  }



  private Map<String, String> mapOfEntitatServeiForEntitatServeiID;

  public Map<String, String> getMapOfEntitatServeiForEntitatServeiID() {
    return this.mapOfEntitatServeiForEntitatServeiID;
  }

  public void setMapOfEntitatServeiForEntitatServeiID(Map<String, String> mapOfEntitatServeiForEntitatServeiID) {
    this.mapOfEntitatServeiForEntitatServeiID = mapOfEntitatServeiForEntitatServeiID;
  }



  private Map<String, String> mapOfEstatServeiForEstatServeiID;

  public Map<String, String> getMapOfEstatServeiForEstatServeiID() {
    return this.mapOfEstatServeiForEstatServeiID;
  }

  public void setMapOfEstatServeiForEstatServeiID(Map<String, String> mapOfEstatServeiForEstatServeiID) {
    this.mapOfEstatServeiForEstatServeiID = mapOfEstatServeiForEstatServeiID;
  }



  private Map<String, String> mapOfValuesForTipusConsentiment;

  public Map<String, String> getMapOfValuesForTipusConsentiment() {
    return this.mapOfValuesForTipusConsentiment;
  }

  public void setMapOfValuesForTipusConsentiment(Map<String, String> mapOfValuesForTipusConsentiment) {
    this.mapOfValuesForTipusConsentiment = mapOfValuesForTipusConsentiment;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
