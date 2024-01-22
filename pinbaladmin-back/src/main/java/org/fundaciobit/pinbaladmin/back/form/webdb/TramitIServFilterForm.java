
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TramitIServFilterForm extends PinbalAdminBaseFilterForm implements TramitIServFields {

  private java.lang.Long servidDesde;

  public java.lang.Long getServidDesde() {
    return this.servidDesde;
  }

  public void setServidDesde(java.lang.Long servidDesde) {
    this.servidDesde = servidDesde;
  }


  private java.lang.Long servidFins;

  public java.lang.Long getServidFins() {
    return this.servidFins;
  }

  public void setServidFins(java.lang.Long servidFins) {
    this.servidFins = servidFins;
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


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String codi;

  public java.lang.String getCodi() {
    return this.codi;
  }

  public void setCodi(java.lang.String codi) {
    this.codi = codi;
  }


  private java.lang.String norma;

  public java.lang.String getNorma() {
    return this.norma;
  }

  public void setNorma(java.lang.String norma) {
    this.norma = norma;
  }


  private java.lang.String urlnorma;

  public java.lang.String getUrlnorma() {
    return this.urlnorma;
  }

  public void setUrlnorma(java.lang.String urlnorma) {
    this.urlnorma = urlnorma;
  }


  private java.lang.String articles;

  public java.lang.String getArticles() {
    return this.articles;
  }

  public void setArticles(java.lang.String articles) {
    this.articles = articles;
  }


  private java.lang.String consentiment;

  public java.lang.String getConsentiment() {
    return this.consentiment;
  }

  public void setConsentiment(java.lang.String consentiment) {
    this.consentiment = consentiment;
  }


  private java.lang.String consentimentpublicat;

  public java.lang.String getConsentimentpublicat() {
    return this.consentimentpublicat;
  }

  public void setConsentimentpublicat(java.lang.String consentimentpublicat) {
    this.consentimentpublicat = consentimentpublicat;
  }


  private java.lang.String urlconsentiment;

  public java.lang.String getUrlconsentiment() {
    return this.urlconsentiment;
  }

  public void setUrlconsentiment(java.lang.String urlconsentiment) {
    this.urlconsentiment = urlconsentiment;
  }


  public TramitIServFilterForm() {
  }
  
  public TramitIServFilterForm(TramitIServFilterForm __toClone) {
    super(__toClone);
    this.servidDesde = __toClone.servidDesde;
    this.servidFins = __toClone.servidFins;
    this.tramitidDesde = __toClone.tramitidDesde;
    this.tramitidFins = __toClone.tramitidFins;
    this.nom = __toClone.nom;
    this.codi = __toClone.codi;
    this.norma = __toClone.norma;
    this.urlnorma = __toClone.urlnorma;
    this.articles = __toClone.articles;
    this.consentiment = __toClone.consentiment;
    this.consentimentpublicat = __toClone.consentimentpublicat;
    this.urlconsentiment = __toClone.urlconsentiment;
    this.mapOfTramitAPersAutForTramitid = __toClone.mapOfTramitAPersAutForTramitid;
    this.mapOfValuesForNom = __toClone.mapOfValuesForNom;
    this.mapOfValuesForConsentiment = __toClone.mapOfValuesForConsentiment;
    this.mapOfValuesForConsentimentpublicat = __toClone.mapOfValuesForConsentimentpublicat;
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



  private Map<String, String> mapOfValuesForNom;

  public Map<String, String> getMapOfValuesForNom() {
    return this.mapOfValuesForNom;
  }

  public void setMapOfValuesForNom(Map<String, String> mapOfValuesForNom) {
    this.mapOfValuesForNom = mapOfValuesForNom;
  }



  private Map<String, String> mapOfValuesForConsentiment;

  public Map<String, String> getMapOfValuesForConsentiment() {
    return this.mapOfValuesForConsentiment;
  }

  public void setMapOfValuesForConsentiment(Map<String, String> mapOfValuesForConsentiment) {
    this.mapOfValuesForConsentiment = mapOfValuesForConsentiment;
  }



  private Map<String, String> mapOfValuesForConsentimentpublicat;

  public Map<String, String> getMapOfValuesForConsentimentpublicat() {
    return this.mapOfValuesForConsentimentpublicat;
  }

  public void setMapOfValuesForConsentimentpublicat(Map<String, String> mapOfValuesForConsentimentpublicat) {
    this.mapOfValuesForConsentimentpublicat = mapOfValuesForConsentimentpublicat;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
