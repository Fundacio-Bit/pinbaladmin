
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.EntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EntitatFilterForm extends PinbalAdminBaseFilterForm implements EntitatFields {

  private java.lang.Long entitatIDDesde;

  public java.lang.Long getEntitatIDDesde() {
    return this.entitatIDDesde;
  }

  public void setEntitatIDDesde(java.lang.Long entitatIDDesde) {
    this.entitatIDDesde = entitatIDDesde;
  }


  private java.lang.Long entitatIDFins;

  public java.lang.Long getEntitatIDFins() {
    return this.entitatIDFins;
  }

  public void setEntitatIDFins(java.lang.Long entitatIDFins) {
    this.entitatIDFins = entitatIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String personaContacte;

  public java.lang.String getPersonaContacte() {
    return this.personaContacte;
  }

  public void setPersonaContacte(java.lang.String personaContacte) {
    this.personaContacte = personaContacte;
  }


  private java.lang.String CIF;

  public java.lang.String getCIF() {
    return this.CIF;
  }

  public void setCIF(java.lang.String CIF) {
    this.CIF = CIF;
  }


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


  public EntitatFilterForm() {
  }
  
  public EntitatFilterForm(EntitatFilterForm __toClone) {
    super(__toClone);
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.nom = __toClone.nom;
    this.personaContacte = __toClone.personaContacte;
    this.CIF = __toClone.CIF;
    this.grupEntitatIDDesde = __toClone.grupEntitatIDDesde;
    this.grupEntitatIDFins = __toClone.grupEntitatIDFins;
    this.mapOfGrupEntitatForGrupEntitatID = __toClone.mapOfGrupEntitatForGrupEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { GRUPENTITATID ,CONVENIPMSBAE }));
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
  private Map<String, String> mapOfGrupEntitatForGrupEntitatID;

  public Map<String, String> getMapOfGrupEntitatForGrupEntitatID() {
    return this.mapOfGrupEntitatForGrupEntitatID;
  }

  public void setMapOfGrupEntitatForGrupEntitatID(Map<String, String> mapOfGrupEntitatForGrupEntitatID) {
    this.mapOfGrupEntitatForGrupEntitatID = mapOfGrupEntitatForGrupEntitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
