package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class IncidenciaTecnicaForm extends PinbalAdminBaseForm {
  
  private IncidenciaTecnicaJPA incidenciaTecnica;
  
  public IncidenciaTecnicaForm() {
  }
  
  public IncidenciaTecnicaForm(IncidenciaTecnicaForm __toClone) {
    super(__toClone);
      this.incidenciaTecnica = __toClone.incidenciaTecnica;
    this.listOfValuesForEstat = __toClone.listOfValuesForEstat;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
    this.listOfValuesForCreador = __toClone.listOfValuesForCreador;
    this.listOfValuesForOperador = __toClone.listOfValuesForOperador;
  }
  
  public IncidenciaTecnicaForm(IncidenciaTecnicaJPA incidenciaTecnica, boolean nou) {
    super(nou);
    this.incidenciaTecnica = incidenciaTecnica;
  }
  
  public IncidenciaTecnicaJPA getIncidenciaTecnica() {
    return incidenciaTecnica;
  }
  public void setIncidenciaTecnica(IncidenciaTecnicaJPA incidenciaTecnica) {
    this.incidenciaTecnica = incidenciaTecnica;
  }
  
  
  private List<StringKeyValue> listOfValuesForEstat;

  public List<StringKeyValue> getListOfValuesForEstat() {
    return this.listOfValuesForEstat;
  }

  public void setListOfValuesForEstat(List<StringKeyValue> listOfValuesForEstat) {
    this.listOfValuesForEstat = listOfValuesForEstat;
  }



  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  private List<StringKeyValue> listOfValuesForCreador;

  public List<StringKeyValue> getListOfValuesForCreador() {
    return this.listOfValuesForCreador;
  }

  public void setListOfValuesForCreador(List<StringKeyValue> listOfValuesForCreador) {
    this.listOfValuesForCreador = listOfValuesForCreador;
  }



  private List<StringKeyValue> listOfValuesForOperador;

  public List<StringKeyValue> getListOfValuesForOperador() {
    return this.listOfValuesForOperador;
  }

  public void setListOfValuesForOperador(List<StringKeyValue> listOfValuesForOperador) {
    this.listOfValuesForOperador = listOfValuesForOperador;
  }



  
} // Final de Classe 
