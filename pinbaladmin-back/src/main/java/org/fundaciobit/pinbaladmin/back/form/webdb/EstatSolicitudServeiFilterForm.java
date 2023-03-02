
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudServeiFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EstatSolicitudServeiFilterForm extends PinbalAdminBaseFilterForm implements EstatSolicitudServeiFields {

  private java.lang.Long estatSolicitudServeiIDDesde;

  public java.lang.Long getEstatSolicitudServeiIDDesde() {
    return this.estatSolicitudServeiIDDesde;
  }

  public void setEstatSolicitudServeiIDDesde(java.lang.Long estatSolicitudServeiIDDesde) {
    this.estatSolicitudServeiIDDesde = estatSolicitudServeiIDDesde;
  }


  private java.lang.Long estatSolicitudServeiIDFins;

  public java.lang.Long getEstatSolicitudServeiIDFins() {
    return this.estatSolicitudServeiIDFins;
  }

  public void setEstatSolicitudServeiIDFins(java.lang.Long estatSolicitudServeiIDFins) {
    this.estatSolicitudServeiIDFins = estatSolicitudServeiIDFins;
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


  public EstatSolicitudServeiFilterForm() {
  }
  
  public EstatSolicitudServeiFilterForm(EstatSolicitudServeiFilterForm __toClone) {
    super(__toClone);
    this.estatSolicitudServeiIDDesde = __toClone.estatSolicitudServeiIDDesde;
    this.estatSolicitudServeiIDFins = __toClone.estatSolicitudServeiIDFins;
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
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(ESTATSOLICITUDSERVEIID ) , new OrderBy(NOM )};


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
