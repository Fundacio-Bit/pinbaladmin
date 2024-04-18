package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class SolicitudServeiForm extends PinbalAdminBaseForm {
  
  private SolicitudServeiJPA solicitudServei;
  
  
  private CommonsMultipartFile fitxernormaID;
  private boolean fitxernormaIDDelete;
  
  
  private CommonsMultipartFile fitxernorma2ID;
  private boolean fitxernorma2IDDelete;
  
  
  private CommonsMultipartFile fitxernorma3ID;
  private boolean fitxernorma3IDDelete;
  
  public SolicitudServeiForm() {
  }
  
  public SolicitudServeiForm(SolicitudServeiForm __toClone) {
    super(__toClone);
      this.solicitudServei = __toClone.solicitudServei;
    this.listOfSolicitudForSolicitudID = __toClone.listOfSolicitudForSolicitudID;
    this.listOfServeiForServeiID = __toClone.listOfServeiForServeiID;
    this.listOfValuesForEstatSolicitudServeiID = __toClone.listOfValuesForEstatSolicitudServeiID;
    this.listOfValuesForTipusConsentiment = __toClone.listOfValuesForTipusConsentiment;
    this.listOfValuesForConsentiment = __toClone.listOfValuesForConsentiment;
  }
  
  public SolicitudServeiForm(SolicitudServeiJPA solicitudServei, boolean nou) {
    super(nou);
    this.solicitudServei = solicitudServei;
  }
  
  public SolicitudServeiJPA getSolicitudServei() {
    return solicitudServei;
  }
  public void setSolicitudServei(SolicitudServeiJPA solicitudServei) {
    this.solicitudServei = solicitudServei;
  }
  
  
  public CommonsMultipartFile getFitxernormaID() {
    return fitxernormaID;
  }
  
   public void setFitxernormaID(CommonsMultipartFile fitxernormaID) {
    this.fitxernormaID = fitxernormaID;
  }
  public boolean isFitxernormaIDDelete() {
    return fitxernormaIDDelete;
  }
  
  public void setFitxernormaIDDelete(boolean fitxernormaIDDelete) {
    this.fitxernormaIDDelete = fitxernormaIDDelete;
   }
  public CommonsMultipartFile getFitxernorma2ID() {
    return fitxernorma2ID;
  }
  
   public void setFitxernorma2ID(CommonsMultipartFile fitxernorma2ID) {
    this.fitxernorma2ID = fitxernorma2ID;
  }
  public boolean isFitxernorma2IDDelete() {
    return fitxernorma2IDDelete;
  }
  
  public void setFitxernorma2IDDelete(boolean fitxernorma2IDDelete) {
    this.fitxernorma2IDDelete = fitxernorma2IDDelete;
   }
  public CommonsMultipartFile getFitxernorma3ID() {
    return fitxernorma3ID;
  }
  
   public void setFitxernorma3ID(CommonsMultipartFile fitxernorma3ID) {
    this.fitxernorma3ID = fitxernorma3ID;
  }
  public boolean isFitxernorma3IDDelete() {
    return fitxernorma3IDDelete;
  }
  
  public void setFitxernorma3IDDelete(boolean fitxernorma3IDDelete) {
    this.fitxernorma3IDDelete = fitxernorma3IDDelete;
   }
  private List<StringKeyValue> listOfSolicitudForSolicitudID;

  public List<StringKeyValue> getListOfSolicitudForSolicitudID() {
    return this.listOfSolicitudForSolicitudID;
  }

  public void setListOfSolicitudForSolicitudID(List<StringKeyValue> listOfSolicitudForSolicitudID) {
    this.listOfSolicitudForSolicitudID = listOfSolicitudForSolicitudID;
  }



  private List<StringKeyValue> listOfServeiForServeiID;

  public List<StringKeyValue> getListOfServeiForServeiID() {
    return this.listOfServeiForServeiID;
  }

  public void setListOfServeiForServeiID(List<StringKeyValue> listOfServeiForServeiID) {
    this.listOfServeiForServeiID = listOfServeiForServeiID;
  }



  private List<StringKeyValue> listOfValuesForEstatSolicitudServeiID;

  public List<StringKeyValue> getListOfValuesForEstatSolicitudServeiID() {
    return this.listOfValuesForEstatSolicitudServeiID;
  }

  public void setListOfValuesForEstatSolicitudServeiID(List<StringKeyValue> listOfValuesForEstatSolicitudServeiID) {
    this.listOfValuesForEstatSolicitudServeiID = listOfValuesForEstatSolicitudServeiID;
  }



  private List<StringKeyValue> listOfValuesForTipusConsentiment;

  public List<StringKeyValue> getListOfValuesForTipusConsentiment() {
    return this.listOfValuesForTipusConsentiment;
  }

  public void setListOfValuesForTipusConsentiment(List<StringKeyValue> listOfValuesForTipusConsentiment) {
    this.listOfValuesForTipusConsentiment = listOfValuesForTipusConsentiment;
  }



  private List<StringKeyValue> listOfValuesForConsentiment;

  public List<StringKeyValue> getListOfValuesForConsentiment() {
    return this.listOfValuesForConsentiment;
  }

  public void setListOfValuesForConsentiment(List<StringKeyValue> listOfValuesForConsentiment) {
    this.listOfValuesForConsentiment = listOfValuesForConsentiment;
  }



  
} // Final de Classe 
