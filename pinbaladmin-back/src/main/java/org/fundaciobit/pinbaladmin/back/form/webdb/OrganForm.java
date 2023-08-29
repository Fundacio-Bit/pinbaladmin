package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.OrganJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class OrganForm extends PinbalAdminBaseForm {
  
  private OrganJPA organ;
  
  public OrganForm() {
  }
  
  public OrganForm(OrganForm __toClone) {
    super(__toClone);
      this.organ = __toClone.organ;
    this.listOfEntitatForEntitatid = __toClone.listOfEntitatForEntitatid;
  }
  
  public OrganForm(OrganJPA organ, boolean nou) {
    super(nou);
    this.organ = organ;
  }
  
  public OrganJPA getOrgan() {
    return organ;
  }
  public void setOrgan(OrganJPA organ) {
    this.organ = organ;
  }
  
  
  private List<StringKeyValue> listOfEntitatForEntitatid;

  public List<StringKeyValue> getListOfEntitatForEntitatid() {
    return this.listOfEntitatForEntitatid;
  }

  public void setListOfEntitatForEntitatid(List<StringKeyValue> listOfEntitatForEntitatid) {
    this.listOfEntitatForEntitatid = listOfEntitatForEntitatid;
  }



  
} // Final de Classe 
