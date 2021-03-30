package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.EntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EntitatForm extends PinbalAdminBaseForm {
  
  private EntitatJPA entitat;
  
  public EntitatForm() {
  }
  
  public EntitatForm(EntitatForm __toClone) {
    super(__toClone);
      this.entitat = __toClone.entitat;
    this.listOfGrupEntitatForGrupEntitatID = __toClone.listOfGrupEntitatForGrupEntitatID;
  }
  
  public EntitatForm(EntitatJPA entitat, boolean nou) {
    super(nou);
    this.entitat = entitat;
  }
  
  public EntitatJPA getEntitat() {
    return entitat;
  }
  public void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }
  
  
  private List<StringKeyValue> listOfGrupEntitatForGrupEntitatID;

  public List<StringKeyValue> getListOfGrupEntitatForGrupEntitatID() {
    return this.listOfGrupEntitatForGrupEntitatID;
  }

  public void setListOfGrupEntitatForGrupEntitatID(List<StringKeyValue> listOfGrupEntitatForGrupEntitatID) {
    this.listOfGrupEntitatForGrupEntitatID = listOfGrupEntitatForGrupEntitatID;
  }



  
} // Final de Classe 
