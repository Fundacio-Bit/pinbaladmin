
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


  private java.lang.String notes;

  public java.lang.String getNotes() {
    return this.notes;
  }

  public void setNotes(java.lang.String notes) {
    this.notes = notes;
  }


  private java.lang.String codiSiaConv;

  public java.lang.String getCodiSiaConv() {
    return this.codiSiaConv;
  }

  public void setCodiSiaConv(java.lang.String codiSiaConv) {
    this.codiSiaConv = codiSiaConv;
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


  private java.lang.Long organidDesde;

  public java.lang.Long getOrganidDesde() {
    return this.organidDesde;
  }

  public void setOrganidDesde(java.lang.Long organidDesde) {
    this.organidDesde = organidDesde;
  }


  private java.lang.Long organidFins;

  public java.lang.Long getOrganidFins() {
    return this.organidFins;
  }

  public void setOrganidFins(java.lang.Long organidFins) {
    this.organidFins = organidFins;
  }


  private java.util.List<java.lang.Long> estatSolicitudSelect;

  public java.util.List<java.lang.Long> getEstatSolicitudSelect() {
    return this.estatSolicitudSelect;
  }

  public void setEstatSolicitudSelect(java.util.List<java.lang.Long> estatSolicitudSelect) {
    this.estatSolicitudSelect = estatSolicitudSelect;
  }


  private java.lang.String expedientPid;

  public java.lang.String getExpedientPid() {
    return this.expedientPid;
  }

  public void setExpedientPid(java.lang.String expedientPid) {
    this.expedientPid = expedientPid;
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


  private java.lang.String creador;

  public java.lang.String getCreador() {
    return this.creador;
  }

  public void setCreador(java.lang.String creador) {
    this.creador = creador;
  }


  private java.lang.String operador;

  public java.lang.String getOperador() {
    return this.operador;
  }

  public void setOperador(java.lang.String operador) {
    this.operador = operador;
  }


  private java.util.List<java.lang.Long> estatpinbalSelect;

  public java.util.List<java.lang.Long> getEstatpinbalSelect() {
    return this.estatpinbalSelect;
  }

  public void setEstatpinbalSelect(java.util.List<java.lang.Long> estatpinbalSelect) {
    this.estatpinbalSelect = estatpinbalSelect;
  }


  private java.lang.String consentiment;

  public java.lang.String getConsentiment() {
    return this.consentiment;
  }

  public void setConsentiment(java.lang.String consentiment) {
    this.consentiment = consentiment;
  }


  private java.lang.String urlconsentiment;

  public java.lang.String getUrlconsentiment() {
    return this.urlconsentiment;
  }

  public void setUrlconsentiment(java.lang.String urlconsentiment) {
    this.urlconsentiment = urlconsentiment;
  }


  private java.lang.String consentimentadjunt;

  public java.lang.String getConsentimentadjunt() {
    return this.consentimentadjunt;
  }

  public void setConsentimentadjunt(java.lang.String consentimentadjunt) {
    this.consentimentadjunt = consentimentadjunt;
  }


  private java.lang.Long portafibIDDesde;

  public java.lang.Long getPortafibIDDesde() {
    return this.portafibIDDesde;
  }

  public void setPortafibIDDesde(java.lang.Long portafibIDDesde) {
    this.portafibIDDesde = portafibIDDesde;
  }


  private java.lang.Long portafibIDFins;

  public java.lang.Long getPortafibIDFins() {
    return this.portafibIDFins;
  }

  public void setPortafibIDFins(java.lang.Long portafibIDFins) {
    this.portafibIDFins = portafibIDFins;
  }


  private java.lang.Long infomadrididDesde;

  public java.lang.Long getInfomadrididDesde() {
    return this.infomadrididDesde;
  }

  public void setInfomadrididDesde(java.lang.Long infomadrididDesde) {
    this.infomadrididDesde = infomadrididDesde;
  }


  private java.lang.Long infomadrididFins;

  public java.lang.Long getInfomadrididFins() {
    return this.infomadrididFins;
  }

  public void setInfomadrididFins(java.lang.Long infomadrididFins) {
    this.infomadrididFins = infomadrididFins;
  }


  private java.sql.Timestamp dataCaducitatDesde;

  public java.sql.Timestamp getDataCaducitatDesde() {
    return this.dataCaducitatDesde;
  }

  public void setDataCaducitatDesde(java.sql.Timestamp dataCaducitatDesde) {
    this.dataCaducitatDesde = dataCaducitatDesde;
  }


  private java.sql.Timestamp dataCaducitatFins;

  public java.sql.Timestamp getDataCaducitatFins() {
    return this.dataCaducitatFins;
  }

  public void setDataCaducitatFins(java.sql.Timestamp dataCaducitatFins) {
    this.dataCaducitatFins = dataCaducitatFins;
  }


  private java.lang.Long contacteTitularIDDesde;

  public java.lang.Long getContacteTitularIDDesde() {
    return this.contacteTitularIDDesde;
  }

  public void setContacteTitularIDDesde(java.lang.Long contacteTitularIDDesde) {
    this.contacteTitularIDDesde = contacteTitularIDDesde;
  }


  private java.lang.Long contacteTitularIDFins;

  public java.lang.Long getContacteTitularIDFins() {
    return this.contacteTitularIDFins;
  }

  public void setContacteTitularIDFins(java.lang.Long contacteTitularIDFins) {
    this.contacteTitularIDFins = contacteTitularIDFins;
  }


  private java.lang.Long solicitudFusionadaIDDesde;

  public java.lang.Long getSolicitudFusionadaIDDesde() {
    return this.solicitudFusionadaIDDesde;
  }

  public void setSolicitudFusionadaIDDesde(java.lang.Long solicitudFusionadaIDDesde) {
    this.solicitudFusionadaIDDesde = solicitudFusionadaIDDesde;
  }


  private java.lang.Long solicitudFusionadaIDFins;

  public java.lang.Long getSolicitudFusionadaIDFins() {
    return this.solicitudFusionadaIDFins;
  }

  public void setSolicitudFusionadaIDFins(java.lang.Long solicitudFusionadaIDFins) {
    this.solicitudFusionadaIDFins = solicitudFusionadaIDFins;
  }


  private java.lang.Long contactePersonaIDDesde;

  public java.lang.Long getContactePersonaIDDesde() {
    return this.contactePersonaIDDesde;
  }

  public void setContactePersonaIDDesde(java.lang.Long contactePersonaIDDesde) {
    this.contactePersonaIDDesde = contactePersonaIDDesde;
  }


  private java.lang.Long contactePersonaIDFins;

  public java.lang.Long getContactePersonaIDFins() {
    return this.contactePersonaIDFins;
  }

  public void setContactePersonaIDFins(java.lang.Long contactePersonaIDFins) {
    this.contactePersonaIDFins = contactePersonaIDFins;
  }


  private java.lang.Long contacteResponsableIDDesde;

  public java.lang.Long getContacteResponsableIDDesde() {
    return this.contacteResponsableIDDesde;
  }

  public void setContacteResponsableIDDesde(java.lang.Long contacteResponsableIDDesde) {
    this.contacteResponsableIDDesde = contacteResponsableIDDesde;
  }


  private java.lang.Long contacteResponsableIDFins;

  public java.lang.Long getContacteResponsableIDFins() {
    return this.contacteResponsableIDFins;
  }

  public void setContacteResponsableIDFins(java.lang.Long contacteResponsableIDFins) {
    this.contacteResponsableIDFins = contacteResponsableIDFins;
  }


  private java.lang.Long contacteSolicitantIDDesde;

  public java.lang.Long getContacteSolicitantIDDesde() {
    return this.contacteSolicitantIDDesde;
  }

  public void setContacteSolicitantIDDesde(java.lang.Long contacteSolicitantIDDesde) {
    this.contacteSolicitantIDDesde = contacteSolicitantIDDesde;
  }


  private java.lang.Long contacteSolicitantIDFins;

  public java.lang.Long getContacteSolicitantIDFins() {
    return this.contacteSolicitantIDFins;
  }

  public void setContacteSolicitantIDFins(java.lang.Long contacteSolicitantIDFins) {
    this.contacteSolicitantIDFins = contacteSolicitantIDFins;
  }


  private java.lang.Long contacteGestAutIDDesde;

  public java.lang.Long getContacteGestAutIDDesde() {
    return this.contacteGestAutIDDesde;
  }

  public void setContacteGestAutIDDesde(java.lang.Long contacteGestAutIDDesde) {
    this.contacteGestAutIDDesde = contacteGestAutIDDesde;
  }


  private java.lang.Long contacteGestAutIDFins;

  public java.lang.Long getContacteGestAutIDFins() {
    return this.contacteGestAutIDFins;
  }

  public void setContacteGestAutIDFins(java.lang.Long contacteGestAutIDFins) {
    this.contacteGestAutIDFins = contacteGestAutIDFins;
  }


  private java.lang.Long contacteAuditoriaIDDesde;

  public java.lang.Long getContacteAuditoriaIDDesde() {
    return this.contacteAuditoriaIDDesde;
  }

  public void setContacteAuditoriaIDDesde(java.lang.Long contacteAuditoriaIDDesde) {
    this.contacteAuditoriaIDDesde = contacteAuditoriaIDDesde;
  }


  private java.lang.Long contacteAuditoriaIDFins;

  public java.lang.Long getContacteAuditoriaIDFins() {
    return this.contacteAuditoriaIDFins;
  }

  public void setContacteAuditoriaIDFins(java.lang.Long contacteAuditoriaIDFins) {
    this.contacteAuditoriaIDFins = contacteAuditoriaIDFins;
  }


  private java.lang.Long contacteTecnicIDDesde;

  public java.lang.Long getContacteTecnicIDDesde() {
    return this.contacteTecnicIDDesde;
  }

  public void setContacteTecnicIDDesde(java.lang.Long contacteTecnicIDDesde) {
    this.contacteTecnicIDDesde = contacteTecnicIDDesde;
  }


  private java.lang.Long contacteTecnicIDFins;

  public java.lang.Long getContacteTecnicIDFins() {
    return this.contacteTecnicIDFins;
  }

  public void setContacteTecnicIDFins(java.lang.Long contacteTecnicIDFins) {
    this.contacteTecnicIDFins = contacteTecnicIDFins;
  }


  private java.lang.String titularFirmaNifOld;

  public java.lang.String getTitularFirmaNifOld() {
    return this.titularFirmaNifOld;
  }

  public void setTitularFirmaNifOld(java.lang.String titularFirmaNifOld) {
    this.titularFirmaNifOld = titularFirmaNifOld;
  }


  private java.lang.String personacontacteold;

  public java.lang.String getPersonacontacteold() {
    return this.personacontacteold;
  }

  public void setPersonacontacteold(java.lang.String personacontacteold) {
    this.personacontacteold = personacontacteold;
  }


  private java.lang.String personacontacteemailold;

  public java.lang.String getPersonacontacteemailold() {
    return this.personacontacteemailold;
  }

  public void setPersonacontacteemailold(java.lang.String personacontacteemailold) {
    this.personacontacteemailold = personacontacteemailold;
  }


  private java.lang.String responsableprocnomold;

  public java.lang.String getResponsableprocnomold() {
    return this.responsableprocnomold;
  }

  public void setResponsableprocnomold(java.lang.String responsableprocnomold) {
    this.responsableprocnomold = responsableprocnomold;
  }


  private java.lang.String responsableprocemailold;

  public java.lang.String getResponsableprocemailold() {
    return this.responsableprocemailold;
  }

  public void setResponsableprocemailold(java.lang.String responsableprocemailold) {
    this.responsableprocemailold = responsableprocemailold;
  }


  private java.lang.String titularfirmanomold;

  public java.lang.String getTitularfirmanomold() {
    return this.titularfirmanomold;
  }

  public void setTitularfirmanomold(java.lang.String titularfirmanomold) {
    this.titularfirmanomold = titularfirmanomold;
  }


  private java.lang.String titularfirmaemailold;

  public java.lang.String getTitularfirmaemailold() {
    return this.titularfirmaemailold;
  }

  public void setTitularfirmaemailold(java.lang.String titularfirmaemailold) {
    this.titularfirmaemailold = titularfirmaemailold;
  }


  public SolicitudFilterForm() {
  }
  
  public SolicitudFilterForm(SolicitudFilterForm __toClone) {
    super(__toClone);
    this.solicitudIDDesde = __toClone.solicitudIDDesde;
    this.solicitudIDFins = __toClone.solicitudIDFins;
    this.procedimentCodi = __toClone.procedimentCodi;
    this.codiDescriptiu = __toClone.codiDescriptiu;
    this.notes = __toClone.notes;
    this.codiSiaConv = __toClone.codiSiaConv;
    this.procedimentNom = __toClone.procedimentNom;
    this.procedimentTipus = __toClone.procedimentTipus;
    this.organidDesde = __toClone.organidDesde;
    this.organidFins = __toClone.organidFins;
    this.estatSolicitudSelect = __toClone.estatSolicitudSelect;
    this.expedientPid = __toClone.expedientPid;
    this.entitatEstatal = __toClone.entitatEstatal;
    this.pinfo = __toClone.pinfo;
    this.dataIniciDesde = __toClone.dataIniciDesde;
    this.dataIniciFins = __toClone.dataIniciFins;
    this.dataFiDesde = __toClone.dataFiDesde;
    this.dataFiFins = __toClone.dataFiFins;
    this.denominacio = __toClone.denominacio;
    this.dir3 = __toClone.dir3;
    this.nif = __toClone.nif;
    this.creador = __toClone.creador;
    this.operador = __toClone.operador;
    this.estatpinbalSelect = __toClone.estatpinbalSelect;
    this.consentiment = __toClone.consentiment;
    this.urlconsentiment = __toClone.urlconsentiment;
    this.consentimentadjunt = __toClone.consentimentadjunt;
    this.portafibIDDesde = __toClone.portafibIDDesde;
    this.portafibIDFins = __toClone.portafibIDFins;
    this.infomadrididDesde = __toClone.infomadrididDesde;
    this.infomadrididFins = __toClone.infomadrididFins;
    this.dataCaducitatDesde = __toClone.dataCaducitatDesde;
    this.dataCaducitatFins = __toClone.dataCaducitatFins;
    this.contacteTitularIDDesde = __toClone.contacteTitularIDDesde;
    this.contacteTitularIDFins = __toClone.contacteTitularIDFins;
    this.solicitudFusionadaIDDesde = __toClone.solicitudFusionadaIDDesde;
    this.solicitudFusionadaIDFins = __toClone.solicitudFusionadaIDFins;
    this.contactePersonaIDDesde = __toClone.contactePersonaIDDesde;
    this.contactePersonaIDFins = __toClone.contactePersonaIDFins;
    this.contacteResponsableIDDesde = __toClone.contacteResponsableIDDesde;
    this.contacteResponsableIDFins = __toClone.contacteResponsableIDFins;
    this.contacteSolicitantIDDesde = __toClone.contacteSolicitantIDDesde;
    this.contacteSolicitantIDFins = __toClone.contacteSolicitantIDFins;
    this.contacteGestAutIDDesde = __toClone.contacteGestAutIDDesde;
    this.contacteGestAutIDFins = __toClone.contacteGestAutIDFins;
    this.contacteAuditoriaIDDesde = __toClone.contacteAuditoriaIDDesde;
    this.contacteAuditoriaIDFins = __toClone.contacteAuditoriaIDFins;
    this.contacteTecnicIDDesde = __toClone.contacteTecnicIDDesde;
    this.contacteTecnicIDFins = __toClone.contacteTecnicIDFins;
    this.titularFirmaNifOld = __toClone.titularFirmaNifOld;
    this.personacontacteold = __toClone.personacontacteold;
    this.personacontacteemailold = __toClone.personacontacteemailold;
    this.responsableprocnomold = __toClone.responsableprocnomold;
    this.responsableprocemailold = __toClone.responsableprocemailold;
    this.titularfirmanomold = __toClone.titularfirmanomold;
    this.titularfirmaemailold = __toClone.titularfirmaemailold;
    this.mapOfValuesForProcedimentTipus = __toClone.mapOfValuesForProcedimentTipus;
    this.mapOfOrganForOrganid = __toClone.mapOfOrganForOrganid;
    this.mapOfValuesForEstatSolicitud = __toClone.mapOfValuesForEstatSolicitud;
    this.mapOfValuesForCreador = __toClone.mapOfValuesForCreador;
    this.mapOfValuesForOperador = __toClone.mapOfValuesForOperador;
    this.mapOfValuesForEstatpinbal = __toClone.mapOfValuesForEstatpinbal;
    this.mapOfValuesForConsentiment = __toClone.mapOfValuesForConsentiment;
    this.mapOfValuesForConsentimentadjunt = __toClone.mapOfValuesForConsentimentadjunt;
    this.mapOfInfoMadridForInfomadridid = __toClone.mapOfInfoMadridForInfomadridid;
    this.mapOfContacteForContacteTitularID = __toClone.mapOfContacteForContacteTitularID;
    this.mapOfContacteForContactePersonaID = __toClone.mapOfContacteForContactePersonaID;
    this.mapOfContacteForContacteResponsableID = __toClone.mapOfContacteForContacteResponsableID;
    this.mapOfContacteForContacteSolicitantID = __toClone.mapOfContacteForContacteSolicitantID;
    this.mapOfContacteForContacteGestAutID = __toClone.mapOfContacteForContacteGestAutID;
    this.mapOfContacteForContacteAuditoriaID = __toClone.mapOfContacteForContacteAuditoriaID;
    this.mapOfContacteForContacteTecnicID = __toClone.mapOfContacteForContacteTecnicID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { PROCEDIMENTCODI ,PROCEDIMENTNOM ,ESTATSOLICITUD ,EXPEDIENTPID ,ENTITATESTATAL ,PINFO ,DATAINICI ,DATAFI }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ESTATSOLICITUD ,ENTITATESTATAL ,FIRMATDOCSOLICITUD ,PRODUCCIO ,OPERADOR }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(ESTATSOLICITUD ) , new OrderBy(DATAINICI, org.fundaciobit.genapp.common.query.OrderType.DESC )};


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



  private Map<String, String> mapOfOrganForOrganid;

  public Map<String, String> getMapOfOrganForOrganid() {
    return this.mapOfOrganForOrganid;
  }

  public void setMapOfOrganForOrganid(Map<String, String> mapOfOrganForOrganid) {
    this.mapOfOrganForOrganid = mapOfOrganForOrganid;
  }



  private Map<String, String> mapOfValuesForEstatSolicitud;

  public Map<String, String> getMapOfValuesForEstatSolicitud() {
    return this.mapOfValuesForEstatSolicitud;
  }

  public void setMapOfValuesForEstatSolicitud(Map<String, String> mapOfValuesForEstatSolicitud) {
    this.mapOfValuesForEstatSolicitud = mapOfValuesForEstatSolicitud;
  }



  private Map<String, String> mapOfValuesForCreador;

  public Map<String, String> getMapOfValuesForCreador() {
    return this.mapOfValuesForCreador;
  }

  public void setMapOfValuesForCreador(Map<String, String> mapOfValuesForCreador) {
    this.mapOfValuesForCreador = mapOfValuesForCreador;
  }



  private Map<String, String> mapOfValuesForOperador;

  public Map<String, String> getMapOfValuesForOperador() {
    return this.mapOfValuesForOperador;
  }

  public void setMapOfValuesForOperador(Map<String, String> mapOfValuesForOperador) {
    this.mapOfValuesForOperador = mapOfValuesForOperador;
  }



  private Map<String, String> mapOfValuesForEstatpinbal;

  public Map<String, String> getMapOfValuesForEstatpinbal() {
    return this.mapOfValuesForEstatpinbal;
  }

  public void setMapOfValuesForEstatpinbal(Map<String, String> mapOfValuesForEstatpinbal) {
    this.mapOfValuesForEstatpinbal = mapOfValuesForEstatpinbal;
  }



  private Map<String, String> mapOfValuesForConsentiment;

  public Map<String, String> getMapOfValuesForConsentiment() {
    return this.mapOfValuesForConsentiment;
  }

  public void setMapOfValuesForConsentiment(Map<String, String> mapOfValuesForConsentiment) {
    this.mapOfValuesForConsentiment = mapOfValuesForConsentiment;
  }



  private Map<String, String> mapOfValuesForConsentimentadjunt;

  public Map<String, String> getMapOfValuesForConsentimentadjunt() {
    return this.mapOfValuesForConsentimentadjunt;
  }

  public void setMapOfValuesForConsentimentadjunt(Map<String, String> mapOfValuesForConsentimentadjunt) {
    this.mapOfValuesForConsentimentadjunt = mapOfValuesForConsentimentadjunt;
  }



  private Map<String, String> mapOfInfoMadridForInfomadridid;

  public Map<String, String> getMapOfInfoMadridForInfomadridid() {
    return this.mapOfInfoMadridForInfomadridid;
  }

  public void setMapOfInfoMadridForInfomadridid(Map<String, String> mapOfInfoMadridForInfomadridid) {
    this.mapOfInfoMadridForInfomadridid = mapOfInfoMadridForInfomadridid;
  }



  private Map<String, String> mapOfContacteForContacteTitularID;

  public Map<String, String> getMapOfContacteForContacteTitularID() {
    return this.mapOfContacteForContacteTitularID;
  }

  public void setMapOfContacteForContacteTitularID(Map<String, String> mapOfContacteForContacteTitularID) {
    this.mapOfContacteForContacteTitularID = mapOfContacteForContacteTitularID;
  }



  private Map<String, String> mapOfContacteForContactePersonaID;

  public Map<String, String> getMapOfContacteForContactePersonaID() {
    return this.mapOfContacteForContactePersonaID;
  }

  public void setMapOfContacteForContactePersonaID(Map<String, String> mapOfContacteForContactePersonaID) {
    this.mapOfContacteForContactePersonaID = mapOfContacteForContactePersonaID;
  }



  private Map<String, String> mapOfContacteForContacteResponsableID;

  public Map<String, String> getMapOfContacteForContacteResponsableID() {
    return this.mapOfContacteForContacteResponsableID;
  }

  public void setMapOfContacteForContacteResponsableID(Map<String, String> mapOfContacteForContacteResponsableID) {
    this.mapOfContacteForContacteResponsableID = mapOfContacteForContacteResponsableID;
  }



  private Map<String, String> mapOfContacteForContacteSolicitantID;

  public Map<String, String> getMapOfContacteForContacteSolicitantID() {
    return this.mapOfContacteForContacteSolicitantID;
  }

  public void setMapOfContacteForContacteSolicitantID(Map<String, String> mapOfContacteForContacteSolicitantID) {
    this.mapOfContacteForContacteSolicitantID = mapOfContacteForContacteSolicitantID;
  }



  private Map<String, String> mapOfContacteForContacteGestAutID;

  public Map<String, String> getMapOfContacteForContacteGestAutID() {
    return this.mapOfContacteForContacteGestAutID;
  }

  public void setMapOfContacteForContacteGestAutID(Map<String, String> mapOfContacteForContacteGestAutID) {
    this.mapOfContacteForContacteGestAutID = mapOfContacteForContacteGestAutID;
  }



  private Map<String, String> mapOfContacteForContacteAuditoriaID;

  public Map<String, String> getMapOfContacteForContacteAuditoriaID() {
    return this.mapOfContacteForContacteAuditoriaID;
  }

  public void setMapOfContacteForContacteAuditoriaID(Map<String, String> mapOfContacteForContacteAuditoriaID) {
    this.mapOfContacteForContacteAuditoriaID = mapOfContacteForContacteAuditoriaID;
  }



  private Map<String, String> mapOfContacteForContacteTecnicID;

  public Map<String, String> getMapOfContacteForContacteTecnicID() {
    return this.mapOfContacteForContacteTecnicID;
  }

  public void setMapOfContacteForContacteTecnicID(Map<String, String> mapOfContacteForContacteTecnicID) {
    this.mapOfContacteForContacteTecnicID = mapOfContacteForContacteTecnicID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
