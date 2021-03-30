
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.EntitatServeiFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EntitatServeiFilterForm extends PinbalAdminBaseFilterForm implements EntitatServeiFields {

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


  public EntitatServeiFilterForm() {
  }
  
  public EntitatServeiFilterForm(EntitatServeiFilterForm __toClone) {
    super(__toClone);
    this.entitatServeiIDDesde = __toClone.entitatServeiIDDesde;
    this.entitatServeiIDFins = __toClone.entitatServeiIDFins;
    this.nom = __toClone.nom;
    this.descripcio = __toClone.descripcio;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { BALEARS }));
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
