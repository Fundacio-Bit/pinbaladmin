
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class DocumentFilterForm extends PinbalAdminBaseFilterForm implements DocumentFields {

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


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String notes;

  public java.lang.String getNotes() {
    return this.notes;
  }

  public void setNotes(java.lang.String notes) {
    this.notes = notes;
  }


  public DocumentFilterForm() {
  }
  
  public DocumentFilterForm(DocumentFilterForm __toClone) {
    super(__toClone);
    this.documentIDDesde = __toClone.documentIDDesde;
    this.documentIDFins = __toClone.documentIDFins;
    this.nom = __toClone.nom;
    this.notes = __toClone.notes;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM ,NOTES }));
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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
