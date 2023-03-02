
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.CampFormulariFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class CampFormulariFilterForm extends PinbalAdminBaseFilterForm implements CampFormulariFields {

  private java.lang.Long campFormulariIDDesde;

  public java.lang.Long getCampFormulariIDDesde() {
    return this.campFormulariIDDesde;
  }

  public void setCampFormulariIDDesde(java.lang.Long campFormulariIDDesde) {
    this.campFormulariIDDesde = campFormulariIDDesde;
  }


  private java.lang.Long campFormulariIDFins;

  public java.lang.Long getCampFormulariIDFins() {
    return this.campFormulariIDFins;
  }

  public void setCampFormulariIDFins(java.lang.Long campFormulariIDFins) {
    this.campFormulariIDFins = campFormulariIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String campPDF;

  public java.lang.String getCampPDF() {
    return this.campPDF;
  }

  public void setCampPDF(java.lang.String campPDF) {
    this.campPDF = campPDF;
  }


  private java.lang.Long formulariIDDesde;

  public java.lang.Long getFormulariIDDesde() {
    return this.formulariIDDesde;
  }

  public void setFormulariIDDesde(java.lang.Long formulariIDDesde) {
    this.formulariIDDesde = formulariIDDesde;
  }


  private java.lang.Long formulariIDFins;

  public java.lang.Long getFormulariIDFins() {
    return this.formulariIDFins;
  }

  public void setFormulariIDFins(java.lang.Long formulariIDFins) {
    this.formulariIDFins = formulariIDFins;
  }


  public CampFormulariFilterForm() {
  }
  
  public CampFormulariFilterForm(CampFormulariFilterForm __toClone) {
    super(__toClone);
    this.campFormulariIDDesde = __toClone.campFormulariIDDesde;
    this.campFormulariIDFins = __toClone.campFormulariIDFins;
    this.nom = __toClone.nom;
    this.campPDF = __toClone.campPDF;
    this.formulariIDDesde = __toClone.formulariIDDesde;
    this.formulariIDFins = __toClone.formulariIDFins;
    this.mapOfFormulariForFormulariID = __toClone.mapOfFormulariForFormulariID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM ,CAMPPDF }));
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
  private Map<String, String> mapOfFormulariForFormulariID;

  public Map<String, String> getMapOfFormulariForFormulariID() {
    return this.mapOfFormulariForFormulariID;
  }

  public void setMapOfFormulariForFormulariID(Map<String, String> mapOfFormulariForFormulariID) {
    this.mapOfFormulariForFormulariID = mapOfFormulariForFormulariID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
