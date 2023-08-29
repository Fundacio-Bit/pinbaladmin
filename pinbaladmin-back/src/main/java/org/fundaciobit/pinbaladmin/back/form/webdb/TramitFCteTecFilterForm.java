
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.TramitFCteTecFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TramitFCteTecFilterForm extends PinbalAdminBaseFilterForm implements TramitFCteTecFields {

  private java.lang.Long ctetecidDesde;

  public java.lang.Long getCtetecidDesde() {
    return this.ctetecidDesde;
  }

  public void setCtetecidDesde(java.lang.Long ctetecidDesde) {
    this.ctetecidDesde = ctetecidDesde;
  }


  private java.lang.Long ctetecidFins;

  public java.lang.Long getCtetecidFins() {
    return this.ctetecidFins;
  }

  public void setCtetecidFins(java.lang.Long ctetecidFins) {
    this.ctetecidFins = ctetecidFins;
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


  private java.lang.String nif;

  public java.lang.String getNif() {
    return this.nif;
  }

  public void setNif(java.lang.String nif) {
    this.nif = nif;
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


  private java.lang.String carrec;

  public java.lang.String getCarrec() {
    return this.carrec;
  }

  public void setCarrec(java.lang.String carrec) {
    this.carrec = carrec;
  }


  private java.lang.String telefon;

  public java.lang.String getTelefon() {
    return this.telefon;
  }

  public void setTelefon(java.lang.String telefon) {
    this.telefon = telefon;
  }


  private java.lang.String mail;

  public java.lang.String getMail() {
    return this.mail;
  }

  public void setMail(java.lang.String mail) {
    this.mail = mail;
  }


  public TramitFCteTecFilterForm() {
  }
  
  public TramitFCteTecFilterForm(TramitFCteTecFilterForm __toClone) {
    super(__toClone);
    this.ctetecidDesde = __toClone.ctetecidDesde;
    this.ctetecidFins = __toClone.ctetecidFins;
    this.tramitidDesde = __toClone.tramitidDesde;
    this.tramitidFins = __toClone.tramitidFins;
    this.nif = __toClone.nif;
    this.nom = __toClone.nom;
    this.llinatge1 = __toClone.llinatge1;
    this.llinatge2 = __toClone.llinatge2;
    this.carrec = __toClone.carrec;
    this.telefon = __toClone.telefon;
    this.mail = __toClone.mail;
    this.mapOfTramitAPersAutForTramitid = __toClone.mapOfTramitAPersAutForTramitid;
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




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
