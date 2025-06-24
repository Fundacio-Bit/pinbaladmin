
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseFilterForm;

import org.fundaciobit.pinbaladmin.model.fields.ModificacioSoliServFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ModificacioSoliServFilterForm extends PinbalAdminBaseFilterForm implements ModificacioSoliServFields {

  private java.lang.Long modsoliservidDesde;

  public java.lang.Long getModsoliservidDesde() {
    return this.modsoliservidDesde;
  }

  public void setModsoliservidDesde(java.lang.Long modsoliservidDesde) {
    this.modsoliservidDesde = modsoliservidDesde;
  }


  private java.lang.Long modsoliservidFins;

  public java.lang.Long getModsoliservidFins() {
    return this.modsoliservidFins;
  }

  public void setModsoliservidFins(java.lang.Long modsoliservidFins) {
    this.modsoliservidFins = modsoliservidFins;
  }


  private java.lang.Long soliServIDDesde;

  public java.lang.Long getSoliServIDDesde() {
    return this.soliServIDDesde;
  }

  public void setSoliServIDDesde(java.lang.Long soliServIDDesde) {
    this.soliServIDDesde = soliServIDDesde;
  }


  private java.lang.Long soliServIDFins;

  public java.lang.Long getSoliServIDFins() {
    return this.soliServIDFins;
  }

  public void setSoliServIDFins(java.lang.Long soliServIDFins) {
    this.soliServIDFins = soliServIDFins;
  }


  private java.lang.Long modSoliIDDesde;

  public java.lang.Long getModSoliIDDesde() {
    return this.modSoliIDDesde;
  }

  public void setModSoliIDDesde(java.lang.Long modSoliIDDesde) {
    this.modSoliIDDesde = modSoliIDDesde;
  }


  private java.lang.Long modSoliIDFins;

  public java.lang.Long getModSoliIDFins() {
    return this.modSoliIDFins;
  }

  public void setModSoliIDFins(java.lang.Long modSoliIDFins) {
    this.modSoliIDFins = modSoliIDFins;
  }


  private java.lang.String estat;

  public java.lang.String getEstat() {
    return this.estat;
  }

  public void setEstat(java.lang.String estat) {
    this.estat = estat;
  }


  private java.lang.String norma1;

  public java.lang.String getNorma1() {
    return this.norma1;
  }

  public void setNorma1(java.lang.String norma1) {
    this.norma1 = norma1;
  }


  private java.lang.String articles1;

  public java.lang.String getArticles1() {
    return this.articles1;
  }

  public void setArticles1(java.lang.String articles1) {
    this.articles1 = articles1;
  }


  private java.lang.String norma2;

  public java.lang.String getNorma2() {
    return this.norma2;
  }

  public void setNorma2(java.lang.String norma2) {
    this.norma2 = norma2;
  }


  private java.lang.String articles2;

  public java.lang.String getArticles2() {
    return this.articles2;
  }

  public void setArticles2(java.lang.String articles2) {
    this.articles2 = articles2;
  }


  private java.lang.String norma3;

  public java.lang.String getNorma3() {
    return this.norma3;
  }

  public void setNorma3(java.lang.String norma3) {
    this.norma3 = norma3;
  }


  private java.lang.String articles3;

  public java.lang.String getArticles3() {
    return this.articles3;
  }

  public void setArticles3(java.lang.String articles3) {
    this.articles3 = articles3;
  }


  public ModificacioSoliServFilterForm() {
  }
  
  public ModificacioSoliServFilterForm(ModificacioSoliServFilterForm __toClone) {
    super(__toClone);
    this.modsoliservidDesde = __toClone.modsoliservidDesde;
    this.modsoliservidFins = __toClone.modsoliservidFins;
    this.soliServIDDesde = __toClone.soliServIDDesde;
    this.soliServIDFins = __toClone.soliServIDFins;
    this.modSoliIDDesde = __toClone.modSoliIDDesde;
    this.modSoliIDFins = __toClone.modSoliIDFins;
    this.estat = __toClone.estat;
    this.norma1 = __toClone.norma1;
    this.articles1 = __toClone.articles1;
    this.norma2 = __toClone.norma2;
    this.articles2 = __toClone.articles2;
    this.norma3 = __toClone.norma3;
    this.articles3 = __toClone.articles3;
    this.mapOfSolicitudServeiForSoliServID = __toClone.mapOfSolicitudServeiForSoliServID;
    this.mapOfModificacioSolicitudForModSoliID = __toClone.mapOfModificacioSolicitudForModSoliID;
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
  private Map<String, String> mapOfSolicitudServeiForSoliServID;

  public Map<String, String> getMapOfSolicitudServeiForSoliServID() {
    return this.mapOfSolicitudServeiForSoliServID;
  }

  public void setMapOfSolicitudServeiForSoliServID(Map<String, String> mapOfSolicitudServeiForSoliServID) {
    this.mapOfSolicitudServeiForSoliServID = mapOfSolicitudServeiForSoliServID;
  }



  private Map<String, String> mapOfModificacioSolicitudForModSoliID;

  public Map<String, String> getMapOfModificacioSolicitudForModSoliID() {
    return this.mapOfModificacioSolicitudForModSoliID;
  }

  public void setMapOfModificacioSolicitudForModSoliID(Map<String, String> mapOfModificacioSolicitudForModSoliID) {
    this.mapOfModificacioSolicitudForModSoliID = mapOfModificacioSolicitudForModSoliID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
