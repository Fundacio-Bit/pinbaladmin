
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.TramitHProcFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TramitHProcFilterForm extends PinbalAdminBaseFilterForm implements TramitHProcFields {

  private java.lang.Long procidDesde;

  public java.lang.Long getProcidDesde() {
    return this.procidDesde;
  }

  public void setProcidDesde(java.lang.Long procidDesde) {
    this.procidDesde = procidDesde;
  }


  private java.lang.Long procidFins;

  public java.lang.Long getProcidFins() {
    return this.procidFins;
  }

  public void setProcidFins(java.lang.Long procidFins) {
    this.procidFins = procidFins;
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


  private java.lang.String tipus;

  public java.lang.String getTipus() {
    return this.tipus;
  }

  public void setTipus(java.lang.String tipus) {
    this.tipus = tipus;
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


  private java.lang.String urlseu;

  public java.lang.String getUrlseu() {
    return this.urlseu;
  }

  public void setUrlseu(java.lang.String urlseu) {
    this.urlseu = urlseu;
  }


  private java.sql.Timestamp caducitatdataDesde;

  public java.sql.Timestamp getCaducitatdataDesde() {
    return this.caducitatdataDesde;
  }

  public void setCaducitatdataDesde(java.sql.Timestamp caducitatdataDesde) {
    this.caducitatdataDesde = caducitatdataDesde;
  }


  private java.sql.Timestamp caducitatdataFins;

  public java.sql.Timestamp getCaducitatdataFins() {
    return this.caducitatdataFins;
  }

  public void setCaducitatdataFins(java.sql.Timestamp caducitatdataFins) {
    this.caducitatdataFins = caducitatdataFins;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.Long peticionsaldiaDesde;

  public java.lang.Long getPeticionsaldiaDesde() {
    return this.peticionsaldiaDesde;
  }

  public void setPeticionsaldiaDesde(java.lang.Long peticionsaldiaDesde) {
    this.peticionsaldiaDesde = peticionsaldiaDesde;
  }


  private java.lang.Long peticionsaldiaFins;

  public java.lang.Long getPeticionsaldiaFins() {
    return this.peticionsaldiaFins;
  }

  public void setPeticionsaldiaFins(java.lang.Long peticionsaldiaFins) {
    this.peticionsaldiaFins = peticionsaldiaFins;
  }


  private java.lang.Long peticionsalmesDesde;

  public java.lang.Long getPeticionsalmesDesde() {
    return this.peticionsalmesDesde;
  }

  public void setPeticionsalmesDesde(java.lang.Long peticionsalmesDesde) {
    this.peticionsalmesDesde = peticionsalmesDesde;
  }


  private java.lang.Long peticionsalmesFins;

  public java.lang.Long getPeticionsalmesFins() {
    return this.peticionsalmesFins;
  }

  public void setPeticionsalmesFins(java.lang.Long peticionsalmesFins) {
    this.peticionsalmesFins = peticionsalmesFins;
  }


  public TramitHProcFilterForm() {
  }
  
  public TramitHProcFilterForm(TramitHProcFilterForm __toClone) {
    super(__toClone);
    this.procidDesde = __toClone.procidDesde;
    this.procidFins = __toClone.procidFins;
    this.tramitidDesde = __toClone.tramitidDesde;
    this.tramitidFins = __toClone.tramitidFins;
    this.tipus = __toClone.tipus;
    this.nom = __toClone.nom;
    this.codi = __toClone.codi;
    this.urlseu = __toClone.urlseu;
    this.caducitatdataDesde = __toClone.caducitatdataDesde;
    this.caducitatdataFins = __toClone.caducitatdataFins;
    this.descripcio = __toClone.descripcio;
    this.peticionsaldiaDesde = __toClone.peticionsaldiaDesde;
    this.peticionsaldiaFins = __toClone.peticionsaldiaFins;
    this.peticionsalmesDesde = __toClone.peticionsalmesDesde;
    this.peticionsalmesFins = __toClone.peticionsalmesFins;
    this.mapOfTramitAPersAutForTramitid = __toClone.mapOfTramitAPersAutForTramitid;
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
  private Map<String, String> mapOfTramitAPersAutForTramitid;

  public Map<String, String> getMapOfTramitAPersAutForTramitid() {
    return this.mapOfTramitAPersAutForTramitid;
  }

  public void setMapOfTramitAPersAutForTramitid(Map<String, String> mapOfTramitAPersAutForTramitid) {
    this.mapOfTramitAPersAutForTramitid = mapOfTramitAPersAutForTramitid;
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
