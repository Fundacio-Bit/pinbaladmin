
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.TipusTiquetFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusTiquetFilterForm extends PinbalAdminBaseFilterForm implements TipusTiquetFields {

  private java.lang.Long tipusTiquetIDDesde;

  public java.lang.Long getTipusTiquetIDDesde() {
    return this.tipusTiquetIDDesde;
  }

  public void setTipusTiquetIDDesde(java.lang.Long tipusTiquetIDDesde) {
    this.tipusTiquetIDDesde = tipusTiquetIDDesde;
  }


  private java.lang.Long tipusTiquetIDFins;

  public java.lang.Long getTipusTiquetIDFins() {
    return this.tipusTiquetIDFins;
  }

  public void setTipusTiquetIDFins(java.lang.Long tipusTiquetIDFins) {
    this.tipusTiquetIDFins = tipusTiquetIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  public TipusTiquetFilterForm() {
  }
  
  public TipusTiquetFilterForm(TipusTiquetFilterForm __toClone) {
    super(__toClone);
    this.tipusTiquetIDDesde = __toClone.tipusTiquetIDDesde;
    this.tipusTiquetIDFins = __toClone.tipusTiquetIDFins;
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
