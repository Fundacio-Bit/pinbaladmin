package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PinfoDataForm extends PinbalAdminBaseForm {
  
  private PinfoDataJPA pinfoData;
  
  public PinfoDataForm() {
  }
  
  public PinfoDataForm(PinfoDataForm __toClone) {
    super(__toClone);
      this.pinfoData = __toClone.pinfoData;
    this.listOfPINFOForPinfoID = __toClone.listOfPINFOForPinfoID;
    this.listOfValuesForEstat = __toClone.listOfValuesForEstat;
    this.listOfSolicitudForProcedimentID = __toClone.listOfSolicitudForProcedimentID;
    this.listOfServeiForServeiID = __toClone.listOfServeiForServeiID;
    this.listOfValuesForAlta = __toClone.listOfValuesForAlta;
  }
  
  public PinfoDataForm(PinfoDataJPA pinfoData, boolean nou) {
    super(nou);
    this.pinfoData = pinfoData;
  }
  
  public PinfoDataJPA getPinfoData() {
    return pinfoData;
  }
  public void setPinfoData(PinfoDataJPA pinfoData) {
    this.pinfoData = pinfoData;
  }
  
  
  private List<StringKeyValue> listOfPINFOForPinfoID;

  public List<StringKeyValue> getListOfPINFOForPinfoID() {
    return this.listOfPINFOForPinfoID;
  }

  public void setListOfPINFOForPinfoID(List<StringKeyValue> listOfPINFOForPinfoID) {
    this.listOfPINFOForPinfoID = listOfPINFOForPinfoID;
  }



  private List<StringKeyValue> listOfValuesForEstat;

  public List<StringKeyValue> getListOfValuesForEstat() {
    return this.listOfValuesForEstat;
  }

  public void setListOfValuesForEstat(List<StringKeyValue> listOfValuesForEstat) {
    this.listOfValuesForEstat = listOfValuesForEstat;
  }



  private List<StringKeyValue> listOfSolicitudForProcedimentID;

  public List<StringKeyValue> getListOfSolicitudForProcedimentID() {
    return this.listOfSolicitudForProcedimentID;
  }

  public void setListOfSolicitudForProcedimentID(List<StringKeyValue> listOfSolicitudForProcedimentID) {
    this.listOfSolicitudForProcedimentID = listOfSolicitudForProcedimentID;
  }



  private List<StringKeyValue> listOfServeiForServeiID;

  public List<StringKeyValue> getListOfServeiForServeiID() {
    return this.listOfServeiForServeiID;
  }

  public void setListOfServeiForServeiID(List<StringKeyValue> listOfServeiForServeiID) {
    this.listOfServeiForServeiID = listOfServeiForServeiID;
  }



  private List<StringKeyValue> listOfValuesForAlta;

  public List<StringKeyValue> getListOfValuesForAlta() {
    return this.listOfValuesForAlta;
  }

  public void setListOfValuesForAlta(List<StringKeyValue> listOfValuesForAlta) {
    this.listOfValuesForAlta = listOfValuesForAlta;
  }



  
} // Final de Classe 
