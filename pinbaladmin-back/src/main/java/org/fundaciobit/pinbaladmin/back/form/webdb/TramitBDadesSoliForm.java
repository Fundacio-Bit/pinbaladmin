package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TramitBDadesSoliForm extends PinbalAdminBaseForm {
  
  private TramitBDadesSoliJPA tramitBDadesSoli;
  
  public TramitBDadesSoliForm() {
  }
  
  public TramitBDadesSoliForm(TramitBDadesSoliForm __toClone) {
    super(__toClone);
      this.tramitBDadesSoli = __toClone.tramitBDadesSoli;
    this.listOfTramitAPersAutForTramitid = __toClone.listOfTramitAPersAutForTramitid;
    this.listOfValuesForTipussolicitud = __toClone.listOfValuesForTipussolicitud;
    this.listOfValuesForEntorn = __toClone.listOfValuesForEntorn;
  }
  
  public TramitBDadesSoliForm(TramitBDadesSoliJPA tramitBDadesSoli, boolean nou) {
    super(nou);
    this.tramitBDadesSoli = tramitBDadesSoli;
  }
  
  public TramitBDadesSoliJPA getTramitBDadesSoli() {
    return tramitBDadesSoli;
  }
  public void setTramitBDadesSoli(TramitBDadesSoliJPA tramitBDadesSoli) {
    this.tramitBDadesSoli = tramitBDadesSoli;
  }
  
  
  private List<StringKeyValue> listOfTramitAPersAutForTramitid;

  public List<StringKeyValue> getListOfTramitAPersAutForTramitid() {
    return this.listOfTramitAPersAutForTramitid;
  }

  public void setListOfTramitAPersAutForTramitid(List<StringKeyValue> listOfTramitAPersAutForTramitid) {
    this.listOfTramitAPersAutForTramitid = listOfTramitAPersAutForTramitid;
  }



  private List<StringKeyValue> listOfValuesForTipussolicitud;

  public List<StringKeyValue> getListOfValuesForTipussolicitud() {
    return this.listOfValuesForTipussolicitud;
  }

  public void setListOfValuesForTipussolicitud(List<StringKeyValue> listOfValuesForTipussolicitud) {
    this.listOfValuesForTipussolicitud = listOfValuesForTipussolicitud;
  }



  private List<StringKeyValue> listOfValuesForEntorn;

  public List<StringKeyValue> getListOfValuesForEntorn() {
    return this.listOfValuesForEntorn;
  }

  public void setListOfValuesForEntorn(List<StringKeyValue> listOfValuesForEntorn) {
    this.listOfValuesForEntorn = listOfValuesForEntorn;
  }



  
} // Final de Classe 
