
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.DocumentCedentFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class DocumentCedentFilterForm extends PinbalAdminBaseFilterForm implements DocumentCedentFields {

  private java.lang.Long documentCedentIDDesde;

  public java.lang.Long getDocumentCedentIDDesde() {
    return this.documentCedentIDDesde;
  }

  public void setDocumentCedentIDDesde(java.lang.Long documentCedentIDDesde) {
    this.documentCedentIDDesde = documentCedentIDDesde;
  }


  private java.lang.Long documentCedentIDFins;

  public java.lang.Long getDocumentCedentIDFins() {
    return this.documentCedentIDFins;
  }

  public void setDocumentCedentIDFins(java.lang.Long documentCedentIDFins) {
    this.documentCedentIDFins = documentCedentIDFins;
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


  private java.lang.Long entitatServeiIDDesde;

  public java.lang.Long getEntitatServeiIDDesde() {
    return this.entitatServeiIDDesde;
  }

  public void setEntitatServeiIDDesde(java.lang.Long entitatServeiIDDesde) {
    this.entitatServeiIDDesde = entitatServeiIDDesde;
  }


  private java.lang.Long entitatServeiIDFins;

  public java.lang.Long getEntitatServeiIDFins() {
    return this.entitatServeiIDFins;
  }

  public void setEntitatServeiIDFins(java.lang.Long entitatServeiIDFins) {
    this.entitatServeiIDFins = entitatServeiIDFins;
  }


  private java.sql.Timestamp dataCreacioDesde;

  public java.sql.Timestamp getDataCreacioDesde() {
    return this.dataCreacioDesde;
  }

  public void setDataCreacioDesde(java.sql.Timestamp dataCreacioDesde) {
    this.dataCreacioDesde = dataCreacioDesde;
  }


  private java.sql.Timestamp dataCreacioFins;

  public java.sql.Timestamp getDataCreacioFins() {
    return this.dataCreacioFins;
  }

  public void setDataCreacioFins(java.sql.Timestamp dataCreacioFins) {
    this.dataCreacioFins = dataCreacioFins;
  }


  public DocumentCedentFilterForm() {
  }
  
  public DocumentCedentFilterForm(DocumentCedentFilterForm __toClone) {
    super(__toClone);
    this.documentCedentIDDesde = __toClone.documentCedentIDDesde;
    this.documentCedentIDFins = __toClone.documentCedentIDFins;
    this.titol = __toClone.titol;
    this.descripcio = __toClone.descripcio;
    this.entitatServeiIDDesde = __toClone.entitatServeiIDDesde;
    this.entitatServeiIDFins = __toClone.entitatServeiIDFins;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.mapOfEntitatServeiForEntitatServeiID = __toClone.mapOfEntitatServeiForEntitatServeiID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TITOL ,DESCRIPCIO }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ENTITATSERVEIID ,DATACREACIO }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(DATACREACIO, org.fundaciobit.genapp.common.query.OrderType.DESC )};


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
  private Map<String, String> mapOfEntitatServeiForEntitatServeiID;

  public Map<String, String> getMapOfEntitatServeiForEntitatServeiID() {
    return this.mapOfEntitatServeiForEntitatServeiID;
  }

  public void setMapOfEntitatServeiForEntitatServeiID(Map<String, String> mapOfEntitatServeiForEntitatServeiID) {
    this.mapOfEntitatServeiForEntitatServeiID = mapOfEntitatServeiForEntitatServeiID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
