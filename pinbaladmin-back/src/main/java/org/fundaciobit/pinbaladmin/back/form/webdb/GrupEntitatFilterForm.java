
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.GrupEntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class GrupEntitatFilterForm extends PinbalAdminBaseFilterForm implements GrupEntitatFields {

  private java.lang.Long grupEntitatIDDesde;

  public java.lang.Long getGrupEntitatIDDesde() {
    return this.grupEntitatIDDesde;
  }

  public void setGrupEntitatIDDesde(java.lang.Long grupEntitatIDDesde) {
    this.grupEntitatIDDesde = grupEntitatIDDesde;
  }


  private java.lang.Long grupEntitatIDFins;

  public java.lang.Long getGrupEntitatIDFins() {
    return this.grupEntitatIDFins;
  }

  public void setGrupEntitatIDFins(java.lang.Long grupEntitatIDFins) {
    this.grupEntitatIDFins = grupEntitatIDFins;
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


  public GrupEntitatFilterForm() {
  }
  
  public GrupEntitatFilterForm(GrupEntitatFilterForm __toClone) {
    super(__toClone);
    this.grupEntitatIDDesde = __toClone.grupEntitatIDDesde;
    this.grupEntitatIDFins = __toClone.grupEntitatIDFins;
    this.nom = __toClone.nom;
    this.descripcio = __toClone.descripcio;
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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
