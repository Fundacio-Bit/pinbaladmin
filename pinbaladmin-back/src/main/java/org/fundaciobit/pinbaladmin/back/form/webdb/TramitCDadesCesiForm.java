package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TramitCDadesCesiJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TramitCDadesCesiForm extends PinbalAdminBaseForm {
  
  private TramitCDadesCesiJPA tramitCDadesCesi;
  
  public TramitCDadesCesiForm() {
  }
  
  public TramitCDadesCesiForm(TramitCDadesCesiForm __toClone) {
    super(__toClone);
      this.tramitCDadesCesi = __toClone.tramitCDadesCesi;
    this.listOfTramitAPersAutForTramitid = __toClone.listOfTramitAPersAutForTramitid;
    this.listOfOrganForOrganID = __toClone.listOfOrganForOrganID;
    this.listOfOrganForOrganArrelID = __toClone.listOfOrganForOrganArrelID;
    this.listOfValuesForDenominacio = __toClone.listOfValuesForDenominacio;
    this.listOfValuesForMunicipi = __toClone.listOfValuesForMunicipi;
  }
  
  public TramitCDadesCesiForm(TramitCDadesCesiJPA tramitCDadesCesi, boolean nou) {
    super(nou);
    this.tramitCDadesCesi = tramitCDadesCesi;
  }
  
  public TramitCDadesCesiJPA getTramitCDadesCesi() {
    return tramitCDadesCesi;
  }
  public void setTramitCDadesCesi(TramitCDadesCesiJPA tramitCDadesCesi) {
    this.tramitCDadesCesi = tramitCDadesCesi;
  }
  
  
  private List<StringKeyValue> listOfTramitAPersAutForTramitid;

  public List<StringKeyValue> getListOfTramitAPersAutForTramitid() {
    return this.listOfTramitAPersAutForTramitid;
  }

  public void setListOfTramitAPersAutForTramitid(List<StringKeyValue> listOfTramitAPersAutForTramitid) {
    this.listOfTramitAPersAutForTramitid = listOfTramitAPersAutForTramitid;
  }



  private List<StringKeyValue> listOfOrganForOrganID;

  public List<StringKeyValue> getListOfOrganForOrganID() {
    return this.listOfOrganForOrganID;
  }

  public void setListOfOrganForOrganID(List<StringKeyValue> listOfOrganForOrganID) {
    this.listOfOrganForOrganID = listOfOrganForOrganID;
  }



  private List<StringKeyValue> listOfOrganForOrganArrelID;

  public List<StringKeyValue> getListOfOrganForOrganArrelID() {
    return this.listOfOrganForOrganArrelID;
  }

  public void setListOfOrganForOrganArrelID(List<StringKeyValue> listOfOrganForOrganArrelID) {
    this.listOfOrganForOrganArrelID = listOfOrganForOrganArrelID;
  }



  private List<StringKeyValue> listOfValuesForDenominacio;

  public List<StringKeyValue> getListOfValuesForDenominacio() {
    return this.listOfValuesForDenominacio;
  }

  public void setListOfValuesForDenominacio(List<StringKeyValue> listOfValuesForDenominacio) {
    this.listOfValuesForDenominacio = listOfValuesForDenominacio;
  }



  private List<StringKeyValue> listOfValuesForMunicipi;

  public List<StringKeyValue> getListOfValuesForMunicipi() {
    return this.listOfValuesForMunicipi;
  }

  public void setListOfValuesForMunicipi(List<StringKeyValue> listOfValuesForMunicipi) {
    this.listOfValuesForMunicipi = listOfValuesForMunicipi;
  }



  
} // Final de Classe 
