
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.EventFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EventFilterForm extends PinbalAdminBaseFilterForm implements EventFields {

  private java.lang.Long eventIDDesde;

  public java.lang.Long getEventIDDesde() {
    return this.eventIDDesde;
  }

  public void setEventIDDesde(java.lang.Long eventIDDesde) {
    this.eventIDDesde = eventIDDesde;
  }


  private java.lang.Long eventIDFins;

  public java.lang.Long getEventIDFins() {
    return this.eventIDFins;
  }

  public void setEventIDFins(java.lang.Long eventIDFins) {
    this.eventIDFins = eventIDFins;
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


  private java.lang.Long incidenciaTecnicaIDDesde;

  public java.lang.Long getIncidenciaTecnicaIDDesde() {
    return this.incidenciaTecnicaIDDesde;
  }

  public void setIncidenciaTecnicaIDDesde(java.lang.Long incidenciaTecnicaIDDesde) {
    this.incidenciaTecnicaIDDesde = incidenciaTecnicaIDDesde;
  }


  private java.lang.Long incidenciaTecnicaIDFins;

  public java.lang.Long getIncidenciaTecnicaIDFins() {
    return this.incidenciaTecnicaIDFins;
  }

  public void setIncidenciaTecnicaIDFins(java.lang.Long incidenciaTecnicaIDFins) {
    this.incidenciaTecnicaIDFins = incidenciaTecnicaIDFins;
  }


  private java.sql.Timestamp dataEventDesde;

  public java.sql.Timestamp getDataEventDesde() {
    return this.dataEventDesde;
  }

  public void setDataEventDesde(java.sql.Timestamp dataEventDesde) {
    this.dataEventDesde = dataEventDesde;
  }


  private java.sql.Timestamp dataEventFins;

  public java.sql.Timestamp getDataEventFins() {
    return this.dataEventFins;
  }

  public void setDataEventFins(java.sql.Timestamp dataEventFins) {
    this.dataEventFins = dataEventFins;
  }


  private java.lang.Integer tipusDesde;

  public java.lang.Integer getTipusDesde() {
    return this.tipusDesde;
  }

  public void setTipusDesde(java.lang.Integer tipusDesde) {
    this.tipusDesde = tipusDesde;
  }


  private java.lang.Integer tipusFins;

  public java.lang.Integer getTipusFins() {
    return this.tipusFins;
  }

  public void setTipusFins(java.lang.Integer tipusFins) {
    this.tipusFins = tipusFins;
  }


  private java.lang.String persona;

  public java.lang.String getPersona() {
    return this.persona;
  }

  public void setPersona(java.lang.String persona) {
    this.persona = persona;
  }


  private java.lang.String comentari;

  public java.lang.String getComentari() {
    return this.comentari;
  }

  public void setComentari(java.lang.String comentari) {
    this.comentari = comentari;
  }


  private java.lang.String caidIdentificadorConsulta;

  public java.lang.String getCaidIdentificadorConsulta() {
    return this.caidIdentificadorConsulta;
  }

  public void setCaidIdentificadorConsulta(java.lang.String caidIdentificadorConsulta) {
    this.caidIdentificadorConsulta = caidIdentificadorConsulta;
  }


  private java.lang.String caidNumeroSeguiment;

  public java.lang.String getCaidNumeroSeguiment() {
    return this.caidNumeroSeguiment;
  }

  public void setCaidNumeroSeguiment(java.lang.String caidNumeroSeguiment) {
    this.caidNumeroSeguiment = caidNumeroSeguiment;
  }


  public EventFilterForm() {
  }
  
  public EventFilterForm(EventFilterForm __toClone) {
    super(__toClone);
    this.eventIDDesde = __toClone.eventIDDesde;
    this.eventIDFins = __toClone.eventIDFins;
    this.solicitudIDDesde = __toClone.solicitudIDDesde;
    this.solicitudIDFins = __toClone.solicitudIDFins;
    this.incidenciaTecnicaIDDesde = __toClone.incidenciaTecnicaIDDesde;
    this.incidenciaTecnicaIDFins = __toClone.incidenciaTecnicaIDFins;
    this.dataEventDesde = __toClone.dataEventDesde;
    this.dataEventFins = __toClone.dataEventFins;
    this.tipusDesde = __toClone.tipusDesde;
    this.tipusFins = __toClone.tipusFins;
    this.persona = __toClone.persona;
    this.comentari = __toClone.comentari;
    this.caidIdentificadorConsulta = __toClone.caidIdentificadorConsulta;
    this.caidNumeroSeguiment = __toClone.caidNumeroSeguiment;
    this.mapOfSolicitudForSolicitudID = __toClone.mapOfSolicitudForSolicitudID;
    this.mapOfIncidenciaTecnicaForIncidenciaTecnicaID = __toClone.mapOfIncidenciaTecnicaForIncidenciaTecnicaID;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
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



  private Map<String, String> mapOfIncidenciaTecnicaForIncidenciaTecnicaID;

  public Map<String, String> getMapOfIncidenciaTecnicaForIncidenciaTecnicaID() {
    return this.mapOfIncidenciaTecnicaForIncidenciaTecnicaID;
  }

  public void setMapOfIncidenciaTecnicaForIncidenciaTecnicaID(Map<String, String> mapOfIncidenciaTecnicaForIncidenciaTecnicaID) {
    this.mapOfIncidenciaTecnicaForIncidenciaTecnicaID = mapOfIncidenciaTecnicaForIncidenciaTecnicaID;
  }



  private Map<String, String> mapOfValuesForTipus;

  public Map<String, String> getMapOfValuesForTipus() {
    return this.mapOfValuesForTipus;
  }

  public void setMapOfValuesForTipus(Map<String, String> mapOfValuesForTipus) {
    this.mapOfValuesForTipus = mapOfValuesForTipus;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
