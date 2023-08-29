package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TramitHProcJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TramitHProcForm extends PinbalAdminBaseForm {
  
  private TramitHProcJPA tramitHProc;
  
  public TramitHProcForm() {
  }
  
  public TramitHProcForm(TramitHProcForm __toClone) {
    super(__toClone);
      this.tramitHProc = __toClone.tramitHProc;
    this.listOfTramitAPersAutForTramitid = __toClone.listOfTramitAPersAutForTramitid;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
  }
  
  public TramitHProcForm(TramitHProcJPA tramitHProc, boolean nou) {
    super(nou);
    this.tramitHProc = tramitHProc;
  }
  
  public TramitHProcJPA getTramitHProc() {
    return tramitHProc;
  }
  public void setTramitHProc(TramitHProcJPA tramitHProc) {
    this.tramitHProc = tramitHProc;
  }
  
  
  private List<StringKeyValue> listOfTramitAPersAutForTramitid;

  public List<StringKeyValue> getListOfTramitAPersAutForTramitid() {
    return this.listOfTramitAPersAutForTramitid;
  }

  public void setListOfTramitAPersAutForTramitid(List<StringKeyValue> listOfTramitAPersAutForTramitid) {
    this.listOfTramitAPersAutForTramitid = listOfTramitAPersAutForTramitid;
  }



  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  
} // Final de Classe 
