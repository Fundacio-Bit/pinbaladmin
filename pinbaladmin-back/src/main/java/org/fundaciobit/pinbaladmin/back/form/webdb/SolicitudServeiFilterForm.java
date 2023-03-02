
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class SolicitudServeiFilterForm extends PinbalAdminBaseFilterForm implements SolicitudServeiFields {

  private java.lang.Long idDesde;

  public java.lang.Long getIdDesde() {
    return this.idDesde;
  }

  public void setIdDesde(java.lang.Long idDesde) {
    this.idDesde = idDesde;
  }


  private java.lang.Long idFins;

  public java.lang.Long getIdFins() {
    return this.idFins;
  }

  public void setIdFins(java.lang.Long idFins) {
    this.idFins = idFins;
  }


  private java.lang.Long solicitudIDDesde;

  public java.lang.Long getSolicitudIDDesde() {
    return this.solicitudIDDesde;
  }

  public void setSolicitudIDDesde(java.lang.Long solicitudIDDesde) {
    this.solicitudIDDesde = solicitudIDDesde;
  }


  private java.lang.Long solicitudIDFins;

  public java.lang.Long getSolicitudIDFins() {
    return this.solicitudIDFins;
  }

  public void setSolicitudIDFins(java.lang.Long solicitudIDFins) {
    this.solicitudIDFins = solicitudIDFins;
  }


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


  private java.lang.Long estatSolicitudServeiIDDesde;

  public java.lang.Long getEstatSolicitudServeiIDDesde() {
    return this.estatSolicitudServeiIDDesde;
  }

  public void setEstatSolicitudServeiIDDesde(java.lang.Long estatSolicitudServeiIDDesde) {
    this.estatSolicitudServeiIDDesde = estatSolicitudServeiIDDesde;
  }


  private java.lang.Long estatSolicitudServeiIDFins;

  public java.lang.Long getEstatSolicitudServeiIDFins() {
    return this.estatSolicitudServeiIDFins;
  }

  public void setEstatSolicitudServeiIDFins(java.lang.Long estatSolicitudServeiIDFins) {
    this.estatSolicitudServeiIDFins = estatSolicitudServeiIDFins;
  }


  private java.lang.String normaLegal;

  public java.lang.String getNormaLegal() {
    return this.normaLegal;
  }

  public void setNormaLegal(java.lang.String normaLegal) {
    this.normaLegal = normaLegal;
  }


  private java.lang.String enllazNormaLegal;

  public java.lang.String getEnllazNormaLegal() {
    return this.enllazNormaLegal;
  }

  public void setEnllazNormaLegal(java.lang.String enllazNormaLegal) {
    this.enllazNormaLegal = enllazNormaLegal;
  }


  private java.lang.String articles;

  public java.lang.String getArticles() {
    return this.articles;
  }

  public void setArticles(java.lang.String articles) {
    this.articles = articles;
  }


  private java.lang.String tipusConsentiment;

  public java.lang.String getTipusConsentiment() {
    return this.tipusConsentiment;
  }

  public void setTipusConsentiment(java.lang.String tipusConsentiment) {
    this.tipusConsentiment = tipusConsentiment;
  }


  private java.lang.String consentiment;

  public java.lang.String getConsentiment() {
    return this.consentiment;
  }

  public void setConsentiment(java.lang.String consentiment) {
    this.consentiment = consentiment;
  }


  private java.lang.String enllazConsentiment;

  public java.lang.String getEnllazConsentiment() {
    return this.enllazConsentiment;
  }

  public void setEnllazConsentiment(java.lang.String enllazConsentiment) {
    this.enllazConsentiment = enllazConsentiment;
  }


  private java.lang.String notes;

  public java.lang.String getNotes() {
    return this.notes;
  }

  public void setNotes(java.lang.String notes) {
    this.notes = notes;
  }


  private java.lang.String caduca;

  public java.lang.String getCaduca() {
    return this.caduca;
  }

  public void setCaduca(java.lang.String caduca) {
    this.caduca = caduca;
  }


  private java.lang.String fechaCaduca;

  public java.lang.String getFechaCaduca() {
    return this.fechaCaduca;
  }

  public void setFechaCaduca(java.lang.String fechaCaduca) {
    this.fechaCaduca = fechaCaduca;
  }


  public SolicitudServeiFilterForm() {
  }
  
  public SolicitudServeiFilterForm(SolicitudServeiFilterForm __toClone) {
    super(__toClone);
    this.idDesde = __toClone.idDesde;
    this.idFins = __toClone.idFins;
    this.solicitudIDDesde = __toClone.solicitudIDDesde;
    this.solicitudIDFins = __toClone.solicitudIDFins;
    this.serveiIDDesde = __toClone.serveiIDDesde;
    this.serveiIDFins = __toClone.serveiIDFins;
    this.estatSolicitudServeiIDDesde = __toClone.estatSolicitudServeiIDDesde;
    this.estatSolicitudServeiIDFins = __toClone.estatSolicitudServeiIDFins;
    this.normaLegal = __toClone.normaLegal;
    this.enllazNormaLegal = __toClone.enllazNormaLegal;
    this.articles = __toClone.articles;
    this.tipusConsentiment = __toClone.tipusConsentiment;
    this.consentiment = __toClone.consentiment;
    this.enllazConsentiment = __toClone.enllazConsentiment;
    this.notes = __toClone.notes;
    this.caduca = __toClone.caduca;
    this.fechaCaduca = __toClone.fechaCaduca;
    this.mapOfSolicitudForSolicitudID = __toClone.mapOfSolicitudForSolicitudID;
    this.mapOfServeiForServeiID = __toClone.mapOfServeiForServeiID;
    this.mapOfEstatSolicitudServeiForEstatSolicitudServeiID = __toClone.mapOfEstatSolicitudServeiForEstatSolicitudServeiID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NORMALEGAL ,NOTES }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ESTATSOLICITUDSERVEIID }));
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
  private Map<String, String> mapOfSolicitudForSolicitudID;

  public Map<String, String> getMapOfSolicitudForSolicitudID() {
    return this.mapOfSolicitudForSolicitudID;
  }

  public void setMapOfSolicitudForSolicitudID(Map<String, String> mapOfSolicitudForSolicitudID) {
    this.mapOfSolicitudForSolicitudID = mapOfSolicitudForSolicitudID;
  }



  private Map<String, String> mapOfServeiForServeiID;

  public Map<String, String> getMapOfServeiForServeiID() {
    return this.mapOfServeiForServeiID;
  }

  public void setMapOfServeiForServeiID(Map<String, String> mapOfServeiForServeiID) {
    this.mapOfServeiForServeiID = mapOfServeiForServeiID;
  }



  private Map<String, String> mapOfEstatSolicitudServeiForEstatSolicitudServeiID;

  public Map<String, String> getMapOfEstatSolicitudServeiForEstatSolicitudServeiID() {
    return this.mapOfEstatSolicitudServeiForEstatSolicitudServeiID;
  }

  public void setMapOfEstatSolicitudServeiForEstatSolicitudServeiID(Map<String, String> mapOfEstatSolicitudServeiForEstatSolicitudServeiID) {
    this.mapOfEstatSolicitudServeiForEstatSolicitudServeiID = mapOfEstatSolicitudServeiForEstatSolicitudServeiID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
