
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ModificacioSolicitudFilterForm extends PinbalAdminBaseFilterForm implements ModificacioSolicitudFields {

  private java.lang.Long modsoliIDDesde;

  public java.lang.Long getModsoliIDDesde() {
    return this.modsoliIDDesde;
  }

  public void setModsoliIDDesde(java.lang.Long modsoliIDDesde) {
    this.modsoliIDDesde = modsoliIDDesde;
  }


  private java.lang.Long modsoliIDFins;

  public java.lang.Long getModsoliIDFins() {
    return this.modsoliIDFins;
  }

  public void setModsoliIDFins(java.lang.Long modsoliIDFins) {
    this.modsoliIDFins = modsoliIDFins;
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


  private java.lang.String procedimentCodi;

  public java.lang.String getProcedimentCodi() {
    return this.procedimentCodi;
  }

  public void setProcedimentCodi(java.lang.String procedimentCodi) {
    this.procedimentCodi = procedimentCodi;
  }


  private java.lang.String procedimentNom;

  public java.lang.String getProcedimentNom() {
    return this.procedimentNom;
  }

  public void setProcedimentNom(java.lang.String procedimentNom) {
    this.procedimentNom = procedimentNom;
  }


  private java.lang.String codiSiaNou;

  public java.lang.String getCodiSiaNou() {
    return this.codiSiaNou;
  }

  public void setCodiSiaNou(java.lang.String codiSiaNou) {
    this.codiSiaNou = codiSiaNou;
  }


  private java.util.List<java.lang.Long> estatIDSelect;

  public java.util.List<java.lang.Long> getEstatIDSelect() {
    return this.estatIDSelect;
  }

  public void setEstatIDSelect(java.util.List<java.lang.Long> estatIDSelect) {
    this.estatIDSelect = estatIDSelect;
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


  private java.sql.Timestamp dataFiDesde;

  public java.sql.Timestamp getDataFiDesde() {
    return this.dataFiDesde;
  }

  public void setDataFiDesde(java.sql.Timestamp dataFiDesde) {
    this.dataFiDesde = dataFiDesde;
  }


  private java.sql.Timestamp dataFiFins;

  public java.sql.Timestamp getDataFiFins() {
    return this.dataFiFins;
  }

  public void setDataFiFins(java.sql.Timestamp dataFiFins) {
    this.dataFiFins = dataFiFins;
  }


  private java.lang.String notes;

  public java.lang.String getNotes() {
    return this.notes;
  }

  public void setNotes(java.lang.String notes) {
    this.notes = notes;
  }


  private java.lang.Long organIDDesde;

  public java.lang.Long getOrganIDDesde() {
    return this.organIDDesde;
  }

  public void setOrganIDDesde(java.lang.Long organIDDesde) {
    this.organIDDesde = organIDDesde;
  }


  private java.lang.Long organIDFins;

  public java.lang.Long getOrganIDFins() {
    return this.organIDFins;
  }

  public void setOrganIDFins(java.lang.Long organIDFins) {
    this.organIDFins = organIDFins;
  }


  private java.lang.String responsableProcNom;

  public java.lang.String getResponsableProcNom() {
    return this.responsableProcNom;
  }

  public void setResponsableProcNom(java.lang.String responsableProcNom) {
    this.responsableProcNom = responsableProcNom;
  }


  private java.lang.String responsableProceMail;

  public java.lang.String getResponsableProceMail() {
    return this.responsableProceMail;
  }

  public void setResponsableProceMail(java.lang.String responsableProceMail) {
    this.responsableProceMail = responsableProceMail;
  }


  private java.lang.String consentiment;

  public java.lang.String getConsentiment() {
    return this.consentiment;
  }

  public void setConsentiment(java.lang.String consentiment) {
    this.consentiment = consentiment;
  }


  private java.lang.String solicitantNom;

  public java.lang.String getSolicitantNom() {
    return this.solicitantNom;
  }

  public void setSolicitantNom(java.lang.String solicitantNom) {
    this.solicitantNom = solicitantNom;
  }


  private java.lang.String solicitantNif;

  public java.lang.String getSolicitantNif() {
    return this.solicitantNif;
  }

  public void setSolicitantNif(java.lang.String solicitantNif) {
    this.solicitantNif = solicitantNif;
  }


  private java.lang.String solicitantMail;

  public java.lang.String getSolicitantMail() {
    return this.solicitantMail;
  }

  public void setSolicitantMail(java.lang.String solicitantMail) {
    this.solicitantMail = solicitantMail;
  }


  private java.lang.String solicitantUsername;

  public java.lang.String getSolicitantUsername() {
    return this.solicitantUsername;
  }

  public void setSolicitantUsername(java.lang.String solicitantUsername) {
    this.solicitantUsername = solicitantUsername;
  }


  private java.util.List<java.lang.Long> estatModificacioSelect;

  public java.util.List<java.lang.Long> getEstatModificacioSelect() {
    return this.estatModificacioSelect;
  }

  public void setEstatModificacioSelect(java.util.List<java.lang.Long> estatModificacioSelect) {
    this.estatModificacioSelect = estatModificacioSelect;
  }


  public ModificacioSolicitudFilterForm() {
  }
  
  public ModificacioSolicitudFilterForm(ModificacioSolicitudFilterForm __toClone) {
    super(__toClone);
    this.modsoliIDDesde = __toClone.modsoliIDDesde;
    this.modsoliIDFins = __toClone.modsoliIDFins;
    this.solicitudIDDesde = __toClone.solicitudIDDesde;
    this.solicitudIDFins = __toClone.solicitudIDFins;
    this.procedimentCodi = __toClone.procedimentCodi;
    this.procedimentNom = __toClone.procedimentNom;
    this.codiSiaNou = __toClone.codiSiaNou;
    this.estatIDSelect = __toClone.estatIDSelect;
    this.dataIniciDesde = __toClone.dataIniciDesde;
    this.dataIniciFins = __toClone.dataIniciFins;
    this.dataFiDesde = __toClone.dataFiDesde;
    this.dataFiFins = __toClone.dataFiFins;
    this.notes = __toClone.notes;
    this.organIDDesde = __toClone.organIDDesde;
    this.organIDFins = __toClone.organIDFins;
    this.responsableProcNom = __toClone.responsableProcNom;
    this.responsableProceMail = __toClone.responsableProceMail;
    this.consentiment = __toClone.consentiment;
    this.solicitantNom = __toClone.solicitantNom;
    this.solicitantNif = __toClone.solicitantNif;
    this.solicitantMail = __toClone.solicitantMail;
    this.solicitantUsername = __toClone.solicitantUsername;
    this.estatModificacioSelect = __toClone.estatModificacioSelect;
    this.mapOfSolicitudForSolicitudID = __toClone.mapOfSolicitudForSolicitudID;
    this.mapOfValuesForEstatID = __toClone.mapOfValuesForEstatID;
    this.mapOfOrganForOrganID = __toClone.mapOfOrganForOrganID;
    this.mapOfValuesForConsentiment = __toClone.mapOfValuesForConsentiment;
    this.mapOfValuesForEstatModificacio = __toClone.mapOfValuesForEstatModificacio;
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
  private Map<String, String> mapOfSolicitudForSolicitudID;

  public Map<String, String> getMapOfSolicitudForSolicitudID() {
    return this.mapOfSolicitudForSolicitudID;
  }

  public void setMapOfSolicitudForSolicitudID(Map<String, String> mapOfSolicitudForSolicitudID) {
    this.mapOfSolicitudForSolicitudID = mapOfSolicitudForSolicitudID;
  }



  private Map<String, String> mapOfValuesForEstatID;

  public Map<String, String> getMapOfValuesForEstatID() {
    return this.mapOfValuesForEstatID;
  }

  public void setMapOfValuesForEstatID(Map<String, String> mapOfValuesForEstatID) {
    this.mapOfValuesForEstatID = mapOfValuesForEstatID;
  }



  private Map<String, String> mapOfOrganForOrganID;

  public Map<String, String> getMapOfOrganForOrganID() {
    return this.mapOfOrganForOrganID;
  }

  public void setMapOfOrganForOrganID(Map<String, String> mapOfOrganForOrganID) {
    this.mapOfOrganForOrganID = mapOfOrganForOrganID;
  }



  private Map<String, String> mapOfValuesForConsentiment;

  public Map<String, String> getMapOfValuesForConsentiment() {
    return this.mapOfValuesForConsentiment;
  }

  public void setMapOfValuesForConsentiment(Map<String, String> mapOfValuesForConsentiment) {
    this.mapOfValuesForConsentiment = mapOfValuesForConsentiment;
  }



  private Map<String, String> mapOfValuesForEstatModificacio;

  public Map<String, String> getMapOfValuesForEstatModificacio() {
    return this.mapOfValuesForEstatModificacio;
  }

  public void setMapOfValuesForEstatModificacio(Map<String, String> mapOfValuesForEstatModificacio) {
    this.mapOfValuesForEstatModificacio = mapOfValuesForEstatModificacio;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
