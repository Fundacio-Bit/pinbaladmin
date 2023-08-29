
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.TramitBDadesSoliFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TramitBDadesSoliFilterForm extends PinbalAdminBaseFilterForm implements TramitBDadesSoliFields {

  private java.lang.Long dadessoliidDesde;

  public java.lang.Long getDadessoliidDesde() {
    return this.dadessoliidDesde;
  }

  public void setDadessoliidDesde(java.lang.Long dadessoliidDesde) {
    this.dadessoliidDesde = dadessoliidDesde;
  }


  private java.lang.Long dadessoliidFins;

  public java.lang.Long getDadessoliidFins() {
    return this.dadessoliidFins;
  }

  public void setDadessoliidFins(java.lang.Long dadessoliidFins) {
    this.dadessoliidFins = dadessoliidFins;
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


  private java.util.List<java.lang.Long> tipussolicitudSelect;

  public java.util.List<java.lang.Long> getTipussolicitudSelect() {
    return this.tipussolicitudSelect;
  }

  public void setTipussolicitudSelect(java.util.List<java.lang.Long> tipussolicitudSelect) {
    this.tipussolicitudSelect = tipussolicitudSelect;
  }


  private java.lang.String entorn;

  public java.lang.String getEntorn() {
    return this.entorn;
  }

  public void setEntorn(java.lang.String entorn) {
    this.entorn = entorn;
  }


  public TramitBDadesSoliFilterForm() {
  }
  
  public TramitBDadesSoliFilterForm(TramitBDadesSoliFilterForm __toClone) {
    super(__toClone);
    this.dadessoliidDesde = __toClone.dadessoliidDesde;
    this.dadessoliidFins = __toClone.dadessoliidFins;
    this.tramitidDesde = __toClone.tramitidDesde;
    this.tramitidFins = __toClone.tramitidFins;
    this.tipussolicitudSelect = __toClone.tipussolicitudSelect;
    this.entorn = __toClone.entorn;
    this.mapOfTramitAPersAutForTramitid = __toClone.mapOfTramitAPersAutForTramitid;
    this.mapOfValuesForTipussolicitud = __toClone.mapOfValuesForTipussolicitud;
    this.mapOfValuesForEntorn = __toClone.mapOfValuesForEntorn;
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



  private Map<String, String> mapOfValuesForTipussolicitud;

  public Map<String, String> getMapOfValuesForTipussolicitud() {
    return this.mapOfValuesForTipussolicitud;
  }

  public void setMapOfValuesForTipussolicitud(Map<String, String> mapOfValuesForTipussolicitud) {
    this.mapOfValuesForTipussolicitud = mapOfValuesForTipussolicitud;
  }



  private Map<String, String> mapOfValuesForEntorn;

  public Map<String, String> getMapOfValuesForEntorn() {
    return this.mapOfValuesForEntorn;
  }

  public void setMapOfValuesForEntorn(Map<String, String> mapOfValuesForEntorn) {
    this.mapOfValuesForEntorn = mapOfValuesForEntorn;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
