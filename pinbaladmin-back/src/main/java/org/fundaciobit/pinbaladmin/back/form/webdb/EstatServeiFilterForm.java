
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.EstatServeiFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EstatServeiFilterForm extends PinbalAdminBaseFilterForm implements EstatServeiFields {

  private java.lang.Long estatServeiIDDesde;

  public java.lang.Long getEstatServeiIDDesde() {
    return this.estatServeiIDDesde;
  }

  public void setEstatServeiIDDesde(java.lang.Long estatServeiIDDesde) {
    this.estatServeiIDDesde = estatServeiIDDesde;
  }


  private java.lang.Long estatServeiIDFins;

  public java.lang.Long getEstatServeiIDFins() {
    return this.estatServeiIDFins;
  }

  public void setEstatServeiIDFins(java.lang.Long estatServeiIDFins) {
    this.estatServeiIDFins = estatServeiIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  public EstatServeiFilterForm() {
  }
  
  public EstatServeiFilterForm(EstatServeiFilterForm __toClone) {
    super(__toClone);
    this.estatServeiIDDesde = __toClone.estatServeiIDDesde;
    this.estatServeiIDFins = __toClone.estatServeiIDFins;
    this.nom = __toClone.nom;
    this.descripcio = __toClone.descripcio;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM ,DESCRIPCIO }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(ESTATSERVEIID )};


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
