package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.GrupEntitatCedentJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class GrupEntitatCedentForm extends PinbalAdminBaseForm {
  
  private GrupEntitatCedentJPA grupEntitatCedent;
  
  public GrupEntitatCedentForm() {
  }
  
  public GrupEntitatCedentForm(GrupEntitatCedentForm __toClone) {
    super(__toClone);
      this.grupEntitatCedent = __toClone.grupEntitatCedent;
    this.listOfGrupEntitatForGrupEntitatID = __toClone.listOfGrupEntitatForGrupEntitatID;
    this.listOfEntitatServeiForCedentID = __toClone.listOfEntitatServeiForCedentID;
  }
  
  public GrupEntitatCedentForm(GrupEntitatCedentJPA grupEntitatCedent, boolean nou) {
    super(nou);
    this.grupEntitatCedent = grupEntitatCedent;
  }
  
  public GrupEntitatCedentJPA getGrupEntitatCedent() {
    return grupEntitatCedent;
  }
  public void setGrupEntitatCedent(GrupEntitatCedentJPA grupEntitatCedent) {
    this.grupEntitatCedent = grupEntitatCedent;
  }
  
  
  private List<StringKeyValue> listOfGrupEntitatForGrupEntitatID;

  public List<StringKeyValue> getListOfGrupEntitatForGrupEntitatID() {
    return this.listOfGrupEntitatForGrupEntitatID;
  }

  public void setListOfGrupEntitatForGrupEntitatID(List<StringKeyValue> listOfGrupEntitatForGrupEntitatID) {
    this.listOfGrupEntitatForGrupEntitatID = listOfGrupEntitatForGrupEntitatID;
  }



  private List<StringKeyValue> listOfEntitatServeiForCedentID;

  public List<StringKeyValue> getListOfEntitatServeiForCedentID() {
    return this.listOfEntitatServeiForCedentID;
  }

  public void setListOfEntitatServeiForCedentID(List<StringKeyValue> listOfEntitatServeiForCedentID) {
    this.listOfEntitatServeiForCedentID = listOfEntitatServeiForCedentID;
  }



  
} // Final de Classe 
