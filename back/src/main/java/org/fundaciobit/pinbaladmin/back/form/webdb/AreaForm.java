package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.AreaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AreaForm extends PinbalAdminBaseForm {
  
  private AreaJPA area;
  
  public AreaForm() {
  }
  
  public AreaForm(AreaForm __toClone) {
    super(__toClone);
      this.area = __toClone.area;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public AreaForm(AreaJPA area, boolean nou) {
    super(nou);
    this.area = area;
  }
  
  public AreaJPA getArea() {
    return area;
  }
  public void setArea(AreaJPA area) {
    this.area = area;
  }
  
  
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 
