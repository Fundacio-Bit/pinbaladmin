
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TramitCDadesCesiFilterForm extends PinbalAdminBaseFilterForm implements TramitCDadesCesiFields {

  private java.lang.Long dadescesiidDesde;

  public java.lang.Long getDadescesiidDesde() {
    return this.dadescesiidDesde;
  }

  public void setDadescesiidDesde(java.lang.Long dadescesiidDesde) {
    this.dadescesiidDesde = dadescesiidDesde;
  }


  private java.lang.Long dadescesiidFins;

  public java.lang.Long getDadescesiidFins() {
    return this.dadescesiidFins;
  }

  public void setDadescesiidFins(java.lang.Long dadescesiidFins) {
    this.dadescesiidFins = dadescesiidFins;
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


  private java.lang.Long organArrelIDDesde;

  public java.lang.Long getOrganArrelIDDesde() {
    return this.organArrelIDDesde;
  }

  public void setOrganArrelIDDesde(java.lang.Long organArrelIDDesde) {
    this.organArrelIDDesde = organArrelIDDesde;
  }


  private java.lang.Long organArrelIDFins;

  public java.lang.Long getOrganArrelIDFins() {
    return this.organArrelIDFins;
  }

  public void setOrganArrelIDFins(java.lang.Long organArrelIDFins) {
    this.organArrelIDFins = organArrelIDFins;
  }


  private java.lang.String denominacio;

  public java.lang.String getDenominacio() {
    return this.denominacio;
  }

  public void setDenominacio(java.lang.String denominacio) {
    this.denominacio = denominacio;
  }


  private java.lang.String nif;

  public java.lang.String getNif() {
    return this.nif;
  }

  public void setNif(java.lang.String nif) {
    this.nif = nif;
  }


  private java.lang.String responsable;

  public java.lang.String getResponsable() {
    return this.responsable;
  }

  public void setResponsable(java.lang.String responsable) {
    this.responsable = responsable;
  }


  private java.lang.String dir3responsable;

  public java.lang.String getDir3responsable() {
    return this.dir3responsable;
  }

  public void setDir3responsable(java.lang.String dir3responsable) {
    this.dir3responsable = dir3responsable;
  }


  private java.lang.String dir3arrel;

  public java.lang.String getDir3arrel() {
    return this.dir3arrel;
  }

  public void setDir3arrel(java.lang.String dir3arrel) {
    this.dir3arrel = dir3arrel;
  }


  private java.lang.String direccio;

  public java.lang.String getDireccio() {
    return this.direccio;
  }

  public void setDireccio(java.lang.String direccio) {
    this.direccio = direccio;
  }


  private java.lang.String codipostal;

  public java.lang.String getCodipostal() {
    return this.codipostal;
  }

  public void setCodipostal(java.lang.String codipostal) {
    this.codipostal = codipostal;
  }


  private java.lang.String municipi;

  public java.lang.String getMunicipi() {
    return this.municipi;
  }

  public void setMunicipi(java.lang.String municipi) {
    this.municipi = municipi;
  }


  public TramitCDadesCesiFilterForm() {
  }
  
  public TramitCDadesCesiFilterForm(TramitCDadesCesiFilterForm __toClone) {
    super(__toClone);
    this.dadescesiidDesde = __toClone.dadescesiidDesde;
    this.dadescesiidFins = __toClone.dadescesiidFins;
    this.tramitidDesde = __toClone.tramitidDesde;
    this.tramitidFins = __toClone.tramitidFins;
    this.organIDDesde = __toClone.organIDDesde;
    this.organIDFins = __toClone.organIDFins;
    this.organArrelIDDesde = __toClone.organArrelIDDesde;
    this.organArrelIDFins = __toClone.organArrelIDFins;
    this.denominacio = __toClone.denominacio;
    this.nif = __toClone.nif;
    this.responsable = __toClone.responsable;
    this.dir3responsable = __toClone.dir3responsable;
    this.dir3arrel = __toClone.dir3arrel;
    this.direccio = __toClone.direccio;
    this.codipostal = __toClone.codipostal;
    this.municipi = __toClone.municipi;
    this.mapOfTramitAPersAutForTramitid = __toClone.mapOfTramitAPersAutForTramitid;
    this.mapOfValuesForDenominacio = __toClone.mapOfValuesForDenominacio;
    this.mapOfValuesForMunicipi = __toClone.mapOfValuesForMunicipi;
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



  private Map<String, String> mapOfValuesForDenominacio;

  public Map<String, String> getMapOfValuesForDenominacio() {
    return this.mapOfValuesForDenominacio;
  }

  public void setMapOfValuesForDenominacio(Map<String, String> mapOfValuesForDenominacio) {
    this.mapOfValuesForDenominacio = mapOfValuesForDenominacio;
  }



  private Map<String, String> mapOfValuesForMunicipi;

  public Map<String, String> getMapOfValuesForMunicipi() {
    return this.mapOfValuesForMunicipi;
  }

  public void setMapOfValuesForMunicipi(Map<String, String> mapOfValuesForMunicipi) {
    this.mapOfValuesForMunicipi = mapOfValuesForMunicipi;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
