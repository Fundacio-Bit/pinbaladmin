package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.ServeiJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class ServeiForm extends PinbalAdminBaseForm {
  
  private ServeiJPA servei;
  
  public ServeiForm() {
  }
  
  public ServeiForm(ServeiForm __toClone) {
    super(__toClone);
      this.servei = __toClone.servei;
    this.listOfFormulariForFormulariID = __toClone.listOfFormulariForFormulariID;
    this.listOfEntitatServeiForEntitatServeiID = __toClone.listOfEntitatServeiForEntitatServeiID;
    this.listOfEstatServeiForEstatServeiID = __toClone.listOfEstatServeiForEstatServeiID;
    this.listOfValuesForTipusConsentiment = __toClone.listOfValuesForTipusConsentiment;
  }
  
  public ServeiForm(ServeiJPA servei, boolean nou) {
    super(nou);
    this.servei = servei;
  }
  
  public ServeiJPA getServei() {
    return servei;
  }
  public void setServei(ServeiJPA servei) {
    this.servei = servei;
  }
  
  
  private List<StringKeyValue> listOfFormulariForFormulariID;

  public List<StringKeyValue> getListOfFormulariForFormulariID() {
    return this.listOfFormulariForFormulariID;
  }

  public void setListOfFormulariForFormulariID(List<StringKeyValue> listOfFormulariForFormulariID) {
    this.listOfFormulariForFormulariID = listOfFormulariForFormulariID;
  }



  private List<StringKeyValue> listOfEntitatServeiForEntitatServeiID;

  public List<StringKeyValue> getListOfEntitatServeiForEntitatServeiID() {
    return this.listOfEntitatServeiForEntitatServeiID;
  }

  public void setListOfEntitatServeiForEntitatServeiID(List<StringKeyValue> listOfEntitatServeiForEntitatServeiID) {
    this.listOfEntitatServeiForEntitatServeiID = listOfEntitatServeiForEntitatServeiID;
  }



  private List<StringKeyValue> listOfEstatServeiForEstatServeiID;

  public List<StringKeyValue> getListOfEstatServeiForEstatServeiID() {
    return this.listOfEstatServeiForEstatServeiID;
  }

  public void setListOfEstatServeiForEstatServeiID(List<StringKeyValue> listOfEstatServeiForEstatServeiID) {
    this.listOfEstatServeiForEstatServeiID = listOfEstatServeiForEstatServeiID;
  }



  private List<StringKeyValue> listOfValuesForTipusConsentiment;

  public List<StringKeyValue> getListOfValuesForTipusConsentiment() {
    return this.listOfValuesForTipusConsentiment;
  }

  public void setListOfValuesForTipusConsentiment(List<StringKeyValue> listOfValuesForTipusConsentiment) {
    this.listOfValuesForTipusConsentiment = listOfValuesForTipusConsentiment;
  }



  
} // Final de Classe 
