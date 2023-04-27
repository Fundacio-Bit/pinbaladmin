package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;

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
    this.listOfValuesForEstatServeiID = __toClone.listOfValuesForEstatServeiID;
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



  private List<StringKeyValue> listOfValuesForEstatServeiID;

  public List<StringKeyValue> getListOfValuesForEstatServeiID() {
    return this.listOfValuesForEstatServeiID;
  }

  public void setListOfValuesForEstatServeiID(List<StringKeyValue> listOfValuesForEstatServeiID) {
    this.listOfValuesForEstatServeiID = listOfValuesForEstatServeiID;
  }



  private List<StringKeyValue> listOfValuesForTipusConsentiment;

  public List<StringKeyValue> getListOfValuesForTipusConsentiment() {
    return this.listOfValuesForTipusConsentiment;
  }

  public void setListOfValuesForTipusConsentiment(List<StringKeyValue> listOfValuesForTipusConsentiment) {
    this.listOfValuesForTipusConsentiment = listOfValuesForTipusConsentiment;
  }



  
} // Final de Classe 
