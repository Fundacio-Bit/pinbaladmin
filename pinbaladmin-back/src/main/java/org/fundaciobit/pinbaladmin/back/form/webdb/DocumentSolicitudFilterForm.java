
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class DocumentSolicitudFilterForm extends PinbalAdminBaseFilterForm implements DocumentSolicitudFields {

  private java.lang.Long documentSolicitudIDDesde;

  public java.lang.Long getDocumentSolicitudIDDesde() {
    return this.documentSolicitudIDDesde;
  }

  public void setDocumentSolicitudIDDesde(java.lang.Long documentSolicitudIDDesde) {
    this.documentSolicitudIDDesde = documentSolicitudIDDesde;
  }


  private java.lang.Long documentSolicitudIDFins;

  public java.lang.Long getDocumentSolicitudIDFins() {
    return this.documentSolicitudIDFins;
  }

  public void setDocumentSolicitudIDFins(java.lang.Long documentSolicitudIDFins) {
    this.documentSolicitudIDFins = documentSolicitudIDFins;
  }


  private java.lang.Long documentIDDesde;

  public java.lang.Long getDocumentIDDesde() {
    return this.documentIDDesde;
  }

  public void setDocumentIDDesde(java.lang.Long documentIDDesde) {
    this.documentIDDesde = documentIDDesde;
  }


  private java.lang.Long documentIDFins;

  public java.lang.Long getDocumentIDFins() {
    return this.documentIDFins;
  }

  public void setDocumentIDFins(java.lang.Long documentIDFins) {
    this.documentIDFins = documentIDFins;
  }


  private java.lang.Long solicitudIDDesde;

  public java.lang.Long getSolicitudIDDesde() {
    return this.solicitudIDDesde;
  }

  public void setSolicitudIDDesde(java.lang.Long solicitudIDDesde) {
    this.solicitudIDDesde = solicitudIDDesde;
  }


  private java.lang.Long solicitudIDFins;

  public java.lang.Long getSolicitudIDFins() {
    return this.solicitudIDFins;
  }

  public void setSolicitudIDFins(java.lang.Long solicitudIDFins) {
    this.solicitudIDFins = solicitudIDFins;
  }


  public DocumentSolicitudFilterForm() {
  }
  
  public DocumentSolicitudFilterForm(DocumentSolicitudFilterForm __toClone) {
    super(__toClone);
    this.documentSolicitudIDDesde = __toClone.documentSolicitudIDDesde;
    this.documentSolicitudIDFins = __toClone.documentSolicitudIDFins;
    this.documentIDDesde = __toClone.documentIDDesde;
    this.documentIDFins = __toClone.documentIDFins;
    this.solicitudIDDesde = __toClone.solicitudIDDesde;
    this.solicitudIDFins = __toClone.solicitudIDFins;
    this.mapOfDocumentForDocumentID = __toClone.mapOfDocumentForDocumentID;
    this.mapOfSolicitudForSolicitudID = __toClone.mapOfSolicitudForSolicitudID;
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


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(DOCUMENTSOLICITUDID, org.fundaciobit.genapp.common.query.OrderType.DESC )};


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
  private Map<String, String> mapOfDocumentForDocumentID;

  public Map<String, String> getMapOfDocumentForDocumentID() {
    return this.mapOfDocumentForDocumentID;
  }

  public void setMapOfDocumentForDocumentID(Map<String, String> mapOfDocumentForDocumentID) {
    this.mapOfDocumentForDocumentID = mapOfDocumentForDocumentID;
  }



  private Map<String, String> mapOfSolicitudForSolicitudID;

  public Map<String, String> getMapOfSolicitudForSolicitudID() {
    return this.mapOfSolicitudForSolicitudID;
  }

  public void setMapOfSolicitudForSolicitudID(Map<String, String> mapOfSolicitudForSolicitudID) {
    this.mapOfSolicitudForSolicitudID = mapOfSolicitudForSolicitudID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
