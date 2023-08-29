
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TramitJConsentFilterForm extends PinbalAdminBaseFilterForm implements TramitJConsentFields {

  private java.lang.Long consentidDesde;

  public java.lang.Long getConsentidDesde() {
    return this.consentidDesde;
  }

  public void setConsentidDesde(java.lang.Long consentidDesde) {
    this.consentidDesde = consentidDesde;
  }


  private java.lang.Long consentidFins;

  public java.lang.Long getConsentidFins() {
    return this.consentidFins;
  }

  public void setConsentidFins(java.lang.Long consentidFins) {
    this.consentidFins = consentidFins;
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


  public TramitJConsentFilterForm() {
  }
  
  public TramitJConsentFilterForm(TramitJConsentFilterForm __toClone) {
    super(__toClone);
    this.consentidDesde = __toClone.consentidDesde;
    this.consentidFins = __toClone.consentidFins;
    this.tramitidDesde = __toClone.tramitidDesde;
    this.tramitidFins = __toClone.tramitidFins;
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
