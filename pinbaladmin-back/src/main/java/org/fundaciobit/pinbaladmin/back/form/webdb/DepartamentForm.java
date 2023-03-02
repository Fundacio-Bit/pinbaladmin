package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.DepartamentJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class DepartamentForm extends PinbalAdminBaseForm {
  
  private DepartamentJPA departament;
  
  public DepartamentForm() {
  }
  
  public DepartamentForm(DepartamentForm __toClone) {
    super(__toClone);
      this.departament = __toClone.departament;
    this.listOfAreaForAreaID = __toClone.listOfAreaForAreaID;
  }
  
  public DepartamentForm(DepartamentJPA departament, boolean nou) {
    super(nou);
    this.departament = departament;
  }
  
  public DepartamentJPA getDepartament() {
    return departament;
  }
  public void setDepartament(DepartamentJPA departament) {
    this.departament = departament;
  }
  
  
  private List<StringKeyValue> listOfAreaForAreaID;

  public List<StringKeyValue> getListOfAreaForAreaID() {
    return this.listOfAreaForAreaID;
  }

  public void setListOfAreaForAreaID(List<StringKeyValue> listOfAreaForAreaID) {
    this.listOfAreaForAreaID = listOfAreaForAreaID;
  }



  
} // Final de Classe 
