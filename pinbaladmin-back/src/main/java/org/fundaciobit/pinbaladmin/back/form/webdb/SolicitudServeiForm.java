package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class SolicitudServeiForm extends PinbalAdminBaseForm {
  
  private SolicitudServeiJPA solicitudServei;
  
  public SolicitudServeiForm() {
  }
  
  public SolicitudServeiForm(SolicitudServeiForm __toClone) {
    super(__toClone);
      this.solicitudServei = __toClone.solicitudServei;
    this.listOfSolicitudForSolicitudID = __toClone.listOfSolicitudForSolicitudID;
    this.listOfServeiForServeiID = __toClone.listOfServeiForServeiID;
    this.listOfValuesForEstatSolicitudServeiID = __toClone.listOfValuesForEstatSolicitudServeiID;
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



  
} // Final de Classe 
