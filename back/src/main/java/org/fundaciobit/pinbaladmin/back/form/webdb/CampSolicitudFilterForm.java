
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.CampSolicitudFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class CampSolicitudFilterForm extends PinbalAdminBaseFilterForm implements CampSolicitudFields {

  private java.lang.Long campSolicitudIDDesde;

  public java.lang.Long getCampSolicitudIDDesde() {
    return this.campSolicitudIDDesde;
  }

  public void setCampSolicitudIDDesde(java.lang.Long campSolicitudIDDesde) {
    this.campSolicitudIDDesde = campSolicitudIDDesde;
  }


  private java.lang.Long campSolicitudIDFins;

  public java.lang.Long getCampSolicitudIDFins() {
    return this.campSolicitudIDFins;
  }

  public void setCampSolicitudIDFins(java.lang.Long campSolicitudIDFins) {
    this.campSolicitudIDFins = campSolicitudIDFins;
  }


  private java.lang.Long campFormulariIDDesde;

  public java.lang.Long getCampFormulariIDDesde() {
    return this.campFormulariIDDesde;
  }

  public void setCampFormulariIDDesde(java.lang.Long campFormulariIDDesde) {
    this.campFormulariIDDesde = campFormulariIDDesde;
  }


  private java.lang.Long campFormulariIDFins;

  public java.lang.Long getCampFormulariIDFins() {
    return this.campFormulariIDFins;
  }

  public void setCampFormulariIDFins(java.lang.Long campFormulariIDFins) {
    this.campFormulariIDFins = campFormulariIDFins;
  }


  private java.lang.Long solicitudServeiIDDesde;

  public java.lang.Long getSolicitudServeiIDDesde() {
    return this.solicitudServeiIDDesde;
  }

  public void setSolicitudServeiIDDesde(java.lang.Long solicitudServeiIDDesde) {
    this.solicitudServeiIDDesde = solicitudServeiIDDesde;
  }


  private java.lang.Long solicitudServeiIDFins;

  public java.lang.Long getSolicitudServeiIDFins() {
    return this.solicitudServeiIDFins;
  }

  public void setSolicitudServeiIDFins(java.lang.Long solicitudServeiIDFins) {
    this.solicitudServeiIDFins = solicitudServeiIDFins;
  }


  private java.lang.String valor;

  public java.lang.String getValor() {
    return this.valor;
  }

  public void setValor(java.lang.String valor) {
    this.valor = valor;
  }


  public CampSolicitudFilterForm() {
  }
  
  public CampSolicitudFilterForm(CampSolicitudFilterForm __toClone) {
    super(__toClone);
    this.campSolicitudIDDesde = __toClone.campSolicitudIDDesde;
    this.campSolicitudIDFins = __toClone.campSolicitudIDFins;
    this.campFormulariIDDesde = __toClone.campFormulariIDDesde;
    this.campFormulariIDFins = __toClone.campFormulariIDFins;
    this.solicitudServeiIDDesde = __toClone.solicitudServeiIDDesde;
    this.solicitudServeiIDFins = __toClone.solicitudServeiIDFins;
    this.valor = __toClone.valor;
    this.mapOfCampFormulariForCampFormulariID = __toClone.mapOfCampFormulariForCampFormulariID;
    this.mapOfSolicitudServeiForSolicitudServeiID = __toClone.mapOfSolicitudServeiForSolicitudServeiID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { VALOR }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { SOLICITUDSERVEIID }));
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
  private Map<String, String> mapOfCampFormulariForCampFormulariID;

  public Map<String, String> getMapOfCampFormulariForCampFormulariID() {
    return this.mapOfCampFormulariForCampFormulariID;
  }

  public void setMapOfCampFormulariForCampFormulariID(Map<String, String> mapOfCampFormulariForCampFormulariID) {
    this.mapOfCampFormulariForCampFormulariID = mapOfCampFormulariForCampFormulariID;
  }



  private Map<String, String> mapOfSolicitudServeiForSolicitudServeiID;

  public Map<String, String> getMapOfSolicitudServeiForSolicitudServeiID() {
    return this.mapOfSolicitudServeiForSolicitudServeiID;
  }

  public void setMapOfSolicitudServeiForSolicitudServeiID(Map<String, String> mapOfSolicitudServeiForSolicitudServeiID) {
    this.mapOfSolicitudServeiForSolicitudServeiID = mapOfSolicitudServeiForSolicitudServeiID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
