
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.EstatTiquetFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EstatTiquetFilterForm extends PinbalAdminBaseFilterForm implements EstatTiquetFields {

  private java.lang.Long estatTiquetIDDesde;

  public java.lang.Long getEstatTiquetIDDesde() {
    return this.estatTiquetIDDesde;
  }

  public void setEstatTiquetIDDesde(java.lang.Long estatTiquetIDDesde) {
    this.estatTiquetIDDesde = estatTiquetIDDesde;
  }


  private java.lang.Long estatTiquetIDFins;

  public java.lang.Long getEstatTiquetIDFins() {
    return this.estatTiquetIDFins;
  }

  public void setEstatTiquetIDFins(java.lang.Long estatTiquetIDFins) {
    this.estatTiquetIDFins = estatTiquetIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  public EstatTiquetFilterForm() {
  }
  
  public EstatTiquetFilterForm(EstatTiquetFilterForm __toClone) {
    super(__toClone);
    this.estatTiquetIDDesde = __toClone.estatTiquetIDDesde;
    this.estatTiquetIDFins = __toClone.estatTiquetIDFins;
    this.nom = __toClone.nom;
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


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(NOM )};


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
