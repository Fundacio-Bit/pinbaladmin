
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TramitAPersAutFilterForm extends PinbalAdminBaseFilterForm implements TramitAPersAutFields {

  private java.lang.Long persautidDesde;

  public java.lang.Long getPersautidDesde() {
    return this.persautidDesde;
  }

  public void setPersautidDesde(java.lang.Long persautidDesde) {
    this.persautidDesde = persautidDesde;
  }


  private java.lang.Long persautidFins;

  public java.lang.Long getPersautidFins() {
    return this.persautidFins;
  }

  public void setPersautidFins(java.lang.Long persautidFins) {
    this.persautidFins = persautidFins;
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


  private java.sql.Timestamp datatramitDesde;

  public java.sql.Timestamp getDatatramitDesde() {
    return this.datatramitDesde;
  }

  public void setDatatramitDesde(java.sql.Timestamp datatramitDesde) {
    this.datatramitDesde = datatramitDesde;
  }


  private java.sql.Timestamp datatramitFins;

  public java.sql.Timestamp getDatatramitFins() {
    return this.datatramitFins;
  }

  public void setDatatramitFins(java.sql.Timestamp datatramitFins) {
    this.datatramitFins = datatramitFins;
  }


  private java.lang.String nif;

  public java.lang.String getNif() {
    return this.nif;
  }

  public void setNif(java.lang.String nif) {
    this.nif = nif;
  }


  private java.lang.String mail;

  public java.lang.String getMail() {
    return this.mail;
  }

  public void setMail(java.lang.String mail) {
    this.mail = mail;
  }


  private java.lang.String telefon;

  public java.lang.String getTelefon() {
    return this.telefon;
  }

  public void setTelefon(java.lang.String telefon) {
    this.telefon = telefon;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String llinatge1;

  public java.lang.String getLlinatge1() {
    return this.llinatge1;
  }

  public void setLlinatge1(java.lang.String llinatge1) {
    this.llinatge1 = llinatge1;
  }


  private java.lang.String llinatge2;

  public java.lang.String getLlinatge2() {
    return this.llinatge2;
  }

  public void setLlinatge2(java.lang.String llinatge2) {
    this.llinatge2 = llinatge2;
  }


  private java.lang.String urlsistra;

  public java.lang.String getUrlsistra() {
    return this.urlsistra;
  }

  public void setUrlsistra(java.lang.String urlsistra) {
    this.urlsistra = urlsistra;
  }


  private java.lang.String idsesionformulario;

  public java.lang.String getIdsesionformulario() {
    return this.idsesionformulario;
  }

  public void setIdsesionformulario(java.lang.String idsesionformulario) {
    this.idsesionformulario = idsesionformulario;
  }


  public TramitAPersAutFilterForm() {
  }
  
  public TramitAPersAutFilterForm(TramitAPersAutFilterForm __toClone) {
    super(__toClone);
    this.persautidDesde = __toClone.persautidDesde;
    this.persautidFins = __toClone.persautidFins;
    this.tramitidDesde = __toClone.tramitidDesde;
    this.tramitidFins = __toClone.tramitidFins;
    this.datatramitDesde = __toClone.datatramitDesde;
    this.datatramitFins = __toClone.datatramitFins;
    this.nif = __toClone.nif;
    this.mail = __toClone.mail;
    this.telefon = __toClone.telefon;
    this.nom = __toClone.nom;
    this.llinatge1 = __toClone.llinatge1;
    this.llinatge2 = __toClone.llinatge2;
    this.urlsistra = __toClone.urlsistra;
    this.idsesionformulario = __toClone.idsesionformulario;
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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
