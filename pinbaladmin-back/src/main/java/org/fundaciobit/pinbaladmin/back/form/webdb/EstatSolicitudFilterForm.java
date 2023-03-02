
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EstatSolicitudFilterForm extends PinbalAdminBaseFilterForm implements EstatSolicitudFields {

  private java.lang.Long estatSolicitudIDDesde;

  public java.lang.Long getEstatSolicitudIDDesde() {
    return this.estatSolicitudIDDesde;
  }

  public void setEstatSolicitudIDDesde(java.lang.Long estatSolicitudIDDesde) {
    this.estatSolicitudIDDesde = estatSolicitudIDDesde;
  }


  private java.lang.Long estatSolicitudIDFins;

  public java.lang.Long getEstatSolicitudIDFins() {
    return this.estatSolicitudIDFins;
  }

  public void setEstatSolicitudIDFins(java.lang.Long estatSolicitudIDFins) {
    this.estatSolicitudIDFins = estatSolicitudIDFins;
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


  public EstatSolicitudFilterForm() {
  }
  
  public EstatSolicitudFilterForm(EstatSolicitudFilterForm __toClone) {
    super(__toClone);
    this.estatSolicitudIDDesde = __toClone.estatSolicitudIDDesde;
    this.estatSolicitudIDFins = __toClone.estatSolicitudIDFins;
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


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(ESTATSOLICITUDID )};


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
