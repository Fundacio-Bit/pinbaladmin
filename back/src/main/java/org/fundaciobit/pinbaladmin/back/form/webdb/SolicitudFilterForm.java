
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class SolicitudFilterForm extends PinbalAdminBaseFilterForm implements SolicitudFields {

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


  private java.lang.String codiDescriptiu;

  public java.lang.String getCodiDescriptiu() {
    return this.codiDescriptiu;
  }

  public void setCodiDescriptiu(java.lang.String codiDescriptiu) {
    this.codiDescriptiu = codiDescriptiu;
  }


  private java.lang.String procedimentNom;

  public java.lang.String getProcedimentNom() {
    return this.procedimentNom;
  }

  public void setProcedimentNom(java.lang.String procedimentNom) {
    this.procedimentNom = procedimentNom;
  }


  private java.lang.String procedimentTipus;

  public java.lang.String getProcedimentTipus() {
    return this.procedimentTipus;
  }

  public void setProcedimentTipus(java.lang.String procedimentTipus) {
    this.procedimentTipus = procedimentTipus;
  }


  private java.lang.Long estatIDDesde;

  public java.lang.Long getEstatIDDesde() {
    return this.estatIDDesde;
  }

  public void setEstatIDDesde(java.lang.Long estatIDDesde) {
    this.estatIDDesde = estatIDDesde;
  }


  private java.lang.Long estatIDFins;

  public java.lang.Long getEstatIDFins() {
    return this.estatIDFins;
  }

  public void setEstatIDFins(java.lang.Long estatIDFins) {
    this.estatIDFins = estatIDFins;
  }


  private java.lang.String ticketAssociat;

  public java.lang.String getTicketAssociat() {
    return this.ticketAssociat;
  }

  public void setTicketAssociat(java.lang.String ticketAssociat) {
    this.ticketAssociat = ticketAssociat;
  }


  private java.lang.String ticketNumeroSeguiment;

  public java.lang.String getTicketNumeroSeguiment() {
    return this.ticketNumeroSeguiment;
  }

  public void setTicketNumeroSeguiment(java.lang.String ticketNumeroSeguiment) {
    this.ticketNumeroSeguiment = ticketNumeroSeguiment;
  }


  private java.lang.Long departamentIDDesde;

  public java.lang.Long getDepartamentIDDesde() {
    return this.departamentIDDesde;
  }

  public void setDepartamentIDDesde(java.lang.Long departamentIDDesde) {
    this.departamentIDDesde = departamentIDDesde;
  }


  private java.lang.Long departamentIDFins;

  public java.lang.Long getDepartamentIDFins() {
    return this.departamentIDFins;
  }

  public void setDepartamentIDFins(java.lang.Long departamentIDFins) {
    this.departamentIDFins = departamentIDFins;
  }


  private java.lang.String entitatEstatal;

  public java.lang.String getEntitatEstatal() {
    return this.entitatEstatal;
  }

  public void setEntitatEstatal(java.lang.String entitatEstatal) {
    this.entitatEstatal = entitatEstatal;
  }


  private java.lang.String pinfo;

  public java.lang.String getPinfo() {
    return this.pinfo;
  }

  public void setPinfo(java.lang.String pinfo) {
    this.pinfo = pinfo;
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


  private java.lang.String personaContacte;

  public java.lang.String getPersonaContacte() {
    return this.personaContacte;
  }

  public void setPersonaContacte(java.lang.String personaContacte) {
    this.personaContacte = personaContacte;
  }


  private java.lang.String personaContacteEmail;

  public java.lang.String getPersonaContacteEmail() {
    return this.personaContacteEmail;
  }

  public void setPersonaContacteEmail(java.lang.String personaContacteEmail) {
    this.personaContacteEmail = personaContacteEmail;
  }


  private java.lang.String responsableProcNom;

  public java.lang.String getResponsableProcNom() {
    return this.responsableProcNom;
  }

  public void setResponsableProcNom(java.lang.String responsableProcNom) {
    this.responsableProcNom = responsableProcNom;
  }


  private java.lang.String responsableProcEmail;

  public java.lang.String getResponsableProcEmail() {
    return this.responsableProcEmail;
  }

  public void setResponsableProcEmail(java.lang.String responsableProcEmail) {
    this.responsableProcEmail = responsableProcEmail;
  }


  private java.lang.String notes;

  public java.lang.String getNotes() {
    return this.notes;
  }

  public void setNotes(java.lang.String notes) {
    this.notes = notes;
  }


  private java.lang.String creador;

  public java.lang.String getCreador() {
    return this.creador;
  }

  public void setCreador(java.lang.String creador) {
    this.creador = creador;
  }


  private java.lang.String denominacio;

  public java.lang.String getDenominacio() {
    return this.denominacio;
  }

  public void setDenominacio(java.lang.String denominacio) {
    this.denominacio = denominacio;
  }


  private java.lang.String dir3;

  public java.lang.String getDir3() {
    return this.dir3;
  }

  public void setDir3(java.lang.String dir3) {
    this.dir3 = dir3;
  }


  private java.lang.String nif;

  public java.lang.String getNif() {
    return this.nif;
  }

  public void setNif(java.lang.String nif) {
    this.nif = nif;
  }


  public SolicitudFilterForm() {
  }
  
  public SolicitudFilterForm(SolicitudFilterForm __toClone) {
    super(__toClone);
    this.solicitudIDDesde = __toClone.solicitudIDDesde;
    this.solicitudIDFins = __toClone.solicitudIDFins;
    this.procedimentCodi = __toClone.procedimentCodi;
    this.codiDescriptiu = __toClone.codiDescriptiu;
    this.procedimentNom = __toClone.procedimentNom;
    this.procedimentTipus = __toClone.procedimentTipus;
    this.estatIDDesde = __toClone.estatIDDesde;
    this.estatIDFins = __toClone.estatIDFins;
    this.ticketAssociat = __toClone.ticketAssociat;
    this.ticketNumeroSeguiment = __toClone.ticketNumeroSeguiment;
    this.departamentIDDesde = __toClone.departamentIDDesde;
    this.departamentIDFins = __toClone.departamentIDFins;
    this.entitatEstatal = __toClone.entitatEstatal;
    this.pinfo = __toClone.pinfo;
    this.dataIniciDesde = __toClone.dataIniciDesde;
    this.dataIniciFins = __toClone.dataIniciFins;
    this.dataFiDesde = __toClone.dataFiDesde;
    this.dataFiFins = __toClone.dataFiFins;
    this.personaContacte = __toClone.personaContacte;
    this.personaContacteEmail = __toClone.personaContacteEmail;
    this.responsableProcNom = __toClone.responsableProcNom;
    this.responsableProcEmail = __toClone.responsableProcEmail;
    this.notes = __toClone.notes;
    this.creador = __toClone.creador;
    this.denominacio = __toClone.denominacio;
    this.dir3 = __toClone.dir3;
    this.nif = __toClone.nif;
    this.mapOfValuesForProcedimentTipus = __toClone.mapOfValuesForProcedimentTipus;
    this.mapOfEstatSolicitudForEstatID = __toClone.mapOfEstatSolicitudForEstatID;
    this.mapOfDepartamentForDepartamentID = __toClone.mapOfDepartamentForDepartamentID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { PROCEDIMENTCODI ,PROCEDIMENTNOM ,ESTATID ,TICKETASSOCIAT ,ENTITATESTATAL ,PINFO }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ESTATID ,DEPARTAMENTID ,ENTITATESTATAL ,FIRMATDOCSOLICITUD ,PRODUCCIO ,CREADOR }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(ESTATID ) , new OrderBy(DATAINICI, org.fundaciobit.genapp.common.query.OrderType.DESC )};


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
  private Map<String, String> mapOfValuesForProcedimentTipus;

  public Map<String, String> getMapOfValuesForProcedimentTipus() {
    return this.mapOfValuesForProcedimentTipus;
  }

  public void setMapOfValuesForProcedimentTipus(Map<String, String> mapOfValuesForProcedimentTipus) {
    this.mapOfValuesForProcedimentTipus = mapOfValuesForProcedimentTipus;
  }



  private Map<String, String> mapOfEstatSolicitudForEstatID;

  public Map<String, String> getMapOfEstatSolicitudForEstatID() {
    return this.mapOfEstatSolicitudForEstatID;
  }

  public void setMapOfEstatSolicitudForEstatID(Map<String, String> mapOfEstatSolicitudForEstatID) {
    this.mapOfEstatSolicitudForEstatID = mapOfEstatSolicitudForEstatID;
  }



  private Map<String, String> mapOfDepartamentForDepartamentID;

  public Map<String, String> getMapOfDepartamentForDepartamentID() {
    return this.mapOfDepartamentForDepartamentID;
  }

  public void setMapOfDepartamentForDepartamentID(Map<String, String> mapOfDepartamentForDepartamentID) {
    this.mapOfDepartamentForDepartamentID = mapOfDepartamentForDepartamentID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
