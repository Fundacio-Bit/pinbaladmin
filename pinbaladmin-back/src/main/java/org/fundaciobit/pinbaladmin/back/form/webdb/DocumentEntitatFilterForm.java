
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.DocumentEntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class DocumentEntitatFilterForm extends PinbalAdminBaseFilterForm implements DocumentEntitatFields {

  private java.lang.Long documentEntitatIDDesde;

  public java.lang.Long getDocumentEntitatIDDesde() {
    return this.documentEntitatIDDesde;
  }

  public void setDocumentEntitatIDDesde(java.lang.Long documentEntitatIDDesde) {
    this.documentEntitatIDDesde = documentEntitatIDDesde;
  }


  private java.lang.Long documentEntitatIDFins;

  public java.lang.Long getDocumentEntitatIDFins() {
    return this.documentEntitatIDFins;
  }

  public void setDocumentEntitatIDFins(java.lang.Long documentEntitatIDFins) {
    this.documentEntitatIDFins = documentEntitatIDFins;
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


  private java.lang.Long entitatIDDesde;

  public java.lang.Long getEntitatIDDesde() {
    return this.entitatIDDesde;
  }

  public void setEntitatIDDesde(java.lang.Long entitatIDDesde) {
    this.entitatIDDesde = entitatIDDesde;
  }


  private java.lang.Long entitatIDFins;

  public java.lang.Long getEntitatIDFins() {
    return this.entitatIDFins;
  }

  public void setEntitatIDFins(java.lang.Long entitatIDFins) {
    this.entitatIDFins = entitatIDFins;
  }


  private java.sql.Timestamp dataAltaDesde;

  public java.sql.Timestamp getDataAltaDesde() {
    return this.dataAltaDesde;
  }

  public void setDataAltaDesde(java.sql.Timestamp dataAltaDesde) {
    this.dataAltaDesde = dataAltaDesde;
  }


  private java.sql.Timestamp dataAltaFins;

  public java.sql.Timestamp getDataAltaFins() {
    return this.dataAltaFins;
  }

  public void setDataAltaFins(java.sql.Timestamp dataAltaFins) {
    this.dataAltaFins = dataAltaFins;
  }


  public DocumentEntitatFilterForm() {
  }
  
  public DocumentEntitatFilterForm(DocumentEntitatFilterForm __toClone) {
    super(__toClone);
    this.documentEntitatIDDesde = __toClone.documentEntitatIDDesde;
    this.documentEntitatIDFins = __toClone.documentEntitatIDFins;
    this.titol = __toClone.titol;
    this.descripcio = __toClone.descripcio;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.dataAltaDesde = __toClone.dataAltaDesde;
    this.dataAltaFins = __toClone.dataAltaFins;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TITOL ,DESCRIPCIO ,DATAALTA }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ENTITATID }));
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
  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
