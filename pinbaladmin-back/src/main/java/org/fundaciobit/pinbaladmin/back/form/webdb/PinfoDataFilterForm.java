
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PinfoDataFilterForm extends PinbalAdminBaseFilterForm implements PinfoDataFields {

  private java.lang.Long pinfodataIDDesde;

  public java.lang.Long getPinfodataIDDesde() {
    return this.pinfodataIDDesde;
  }

  public void setPinfodataIDDesde(java.lang.Long pinfodataIDDesde) {
    this.pinfodataIDDesde = pinfodataIDDesde;
  }


  private java.lang.Long pinfodataIDFins;

  public java.lang.Long getPinfodataIDFins() {
    return this.pinfodataIDFins;
  }

  public void setPinfodataIDFins(java.lang.Long pinfodataIDFins) {
    this.pinfodataIDFins = pinfodataIDFins;
  }


  private java.lang.Long pinfoIDDesde;

  public java.lang.Long getPinfoIDDesde() {
    return this.pinfoIDDesde;
  }

  public void setPinfoIDDesde(java.lang.Long pinfoIDDesde) {
    this.pinfoIDDesde = pinfoIDDesde;
  }


  private java.lang.Long pinfoIDFins;

  public java.lang.Long getPinfoIDFins() {
    return this.pinfoIDFins;
  }

  public void setPinfoIDFins(java.lang.Long pinfoIDFins) {
    this.pinfoIDFins = pinfoIDFins;
  }


  private java.lang.Long estatDesde;

  public java.lang.Long getEstatDesde() {
    return this.estatDesde;
  }

  public void setEstatDesde(java.lang.Long estatDesde) {
    this.estatDesde = estatDesde;
  }


  private java.lang.Long estatFins;

  public java.lang.Long getEstatFins() {
    return this.estatFins;
  }

  public void setEstatFins(java.lang.Long estatFins) {
    this.estatFins = estatFins;
  }


  private java.lang.String usuariid;

  public java.lang.String getUsuariid() {
    return this.usuariid;
  }

  public void setUsuariid(java.lang.String usuariid) {
    this.usuariid = usuariid;
  }


  private java.lang.Long procedimentIDDesde;

  public java.lang.Long getProcedimentIDDesde() {
    return this.procedimentIDDesde;
  }

  public void setProcedimentIDDesde(java.lang.Long procedimentIDDesde) {
    this.procedimentIDDesde = procedimentIDDesde;
  }


  private java.lang.Long procedimentIDFins;

  public java.lang.Long getProcedimentIDFins() {
    return this.procedimentIDFins;
  }

  public void setProcedimentIDFins(java.lang.Long procedimentIDFins) {
    this.procedimentIDFins = procedimentIDFins;
  }


  private java.lang.Long serveiIDDesde;

  public java.lang.Long getServeiIDDesde() {
    return this.serveiIDDesde;
  }

  public void setServeiIDDesde(java.lang.Long serveiIDDesde) {
    this.serveiIDDesde = serveiIDDesde;
  }


  private java.lang.Long serveiIDFins;

  public java.lang.Long getServeiIDFins() {
    return this.serveiIDFins;
  }

  public void setServeiIDFins(java.lang.Long serveiIDFins) {
    this.serveiIDFins = serveiIDFins;
  }


  private java.lang.Long altaDesde;

  public java.lang.Long getAltaDesde() {
    return this.altaDesde;
  }

  public void setAltaDesde(java.lang.Long altaDesde) {
    this.altaDesde = altaDesde;
  }


  private java.lang.Long altaFins;

  public java.lang.Long getAltaFins() {
    return this.altaFins;
  }

  public void setAltaFins(java.lang.Long altaFins) {
    this.altaFins = altaFins;
  }


  public PinfoDataFilterForm() {
  }
  
  public PinfoDataFilterForm(PinfoDataFilterForm __toClone) {
    super(__toClone);
    this.pinfodataIDDesde = __toClone.pinfodataIDDesde;
    this.pinfodataIDFins = __toClone.pinfodataIDFins;
    this.pinfoIDDesde = __toClone.pinfoIDDesde;
    this.pinfoIDFins = __toClone.pinfoIDFins;
    this.estatDesde = __toClone.estatDesde;
    this.estatFins = __toClone.estatFins;
    this.usuariid = __toClone.usuariid;
    this.procedimentIDDesde = __toClone.procedimentIDDesde;
    this.procedimentIDFins = __toClone.procedimentIDFins;
    this.serveiIDDesde = __toClone.serveiIDDesde;
    this.serveiIDFins = __toClone.serveiIDFins;
    this.altaDesde = __toClone.altaDesde;
    this.altaFins = __toClone.altaFins;
    this.mapOfPINFOForPinfoID = __toClone.mapOfPINFOForPinfoID;
    this.mapOfSolicitudForProcedimentID = __toClone.mapOfSolicitudForProcedimentID;
    this.mapOfServeiForServeiID = __toClone.mapOfServeiForServeiID;
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
  private Map<String, String> mapOfPINFOForPinfoID;

  public Map<String, String> getMapOfPINFOForPinfoID() {
    return this.mapOfPINFOForPinfoID;
  }

  public void setMapOfPINFOForPinfoID(Map<String, String> mapOfPINFOForPinfoID) {
    this.mapOfPINFOForPinfoID = mapOfPINFOForPinfoID;
  }



  private Map<String, String> mapOfSolicitudForProcedimentID;

  public Map<String, String> getMapOfSolicitudForProcedimentID() {
    return this.mapOfSolicitudForProcedimentID;
  }

  public void setMapOfSolicitudForProcedimentID(Map<String, String> mapOfSolicitudForProcedimentID) {
    this.mapOfSolicitudForProcedimentID = mapOfSolicitudForProcedimentID;
  }



  private Map<String, String> mapOfServeiForServeiID;

  public Map<String, String> getMapOfServeiForServeiID() {
    return this.mapOfServeiForServeiID;
  }

  public void setMapOfServeiForServeiID(Map<String, String> mapOfServeiForServeiID) {
    this.mapOfServeiForServeiID = mapOfServeiForServeiID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}