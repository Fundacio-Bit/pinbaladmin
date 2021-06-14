
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class IncidenciaTecnicaFilterForm extends PinbalAdminBaseFilterForm implements IncidenciaTecnicaFields {

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


  private java.lang.Integer estatDesde;

  public java.lang.Integer getEstatDesde() {
    return this.estatDesde;
  }

  public void setEstatDesde(java.lang.Integer estatDesde) {
    this.estatDesde = estatDesde;
  }


  private java.lang.Integer estatFins;

  public java.lang.Integer getEstatFins() {
    return this.estatFins;
  }

  public void setEstatFins(java.lang.Integer estatFins) {
    this.estatFins = estatFins;
  }


  private java.lang.String nomEntitat;

  public java.lang.String getNomEntitat() {
    return this.nomEntitat;
  }

  public void setNomEntitat(java.lang.String nomEntitat) {
    this.nomEntitat = nomEntitat;
  }


  private java.lang.String contacteNom;

  public java.lang.String getContacteNom() {
    return this.contacteNom;
  }

  public void setContacteNom(java.lang.String contacteNom) {
    this.contacteNom = contacteNom;
  }


  private java.lang.String contacteEmail;

  public java.lang.String getContacteEmail() {
    return this.contacteEmail;
  }

  public void setContacteEmail(java.lang.String contacteEmail) {
    this.contacteEmail = contacteEmail;
  }


  private java.lang.String contacteTelefon;

  public java.lang.String getContacteTelefon() {
    return this.contacteTelefon;
  }

  public void setContacteTelefon(java.lang.String contacteTelefon) {
    this.contacteTelefon = contacteTelefon;
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


  public IncidenciaTecnicaFilterForm() {
  }
  
  public IncidenciaTecnicaFilterForm(IncidenciaTecnicaFilterForm __toClone) {
    super(__toClone);
    this.incidenciaTecnicaIDDesde = __toClone.incidenciaTecnicaIDDesde;
    this.incidenciaTecnicaIDFins = __toClone.incidenciaTecnicaIDFins;
    this.titol = __toClone.titol;
    this.descripcio = __toClone.descripcio;
    this.dataIniciDesde = __toClone.dataIniciDesde;
    this.dataIniciFins = __toClone.dataIniciFins;
    this.estatDesde = __toClone.estatDesde;
    this.estatFins = __toClone.estatFins;
    this.nomEntitat = __toClone.nomEntitat;
    this.contacteNom = __toClone.contacteNom;
    this.contacteEmail = __toClone.contacteEmail;
    this.contacteTelefon = __toClone.contacteTelefon;
    this.caidIdentificadorConsulta = __toClone.caidIdentificadorConsulta;
    this.caidNumeroSeguiment = __toClone.caidNumeroSeguiment;
    this.mapOfValuesForEstat = __toClone.mapOfValuesForEstat;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TITOL ,DESCRIPCIO ,NOMENTITAT ,CONTACTEEMAIL }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ESTAT }));
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
