package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.CampFormulariJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class CampFormulariForm extends PinbalAdminBaseForm {
  
  private CampFormulariJPA campFormulari;
  
  public CampFormulariForm() {
  }
  
  public CampFormulariForm(CampFormulariForm __toClone) {
    super(__toClone);
      this.campFormulari = __toClone.campFormulari;
    this.listOfFormulariForFormulariID = __toClone.listOfFormulariForFormulariID;
  }
  
  public CampFormulariForm(CampFormulariJPA campFormulari, boolean nou) {
    super(nou);
    this.campFormulari = campFormulari;
  }
  
  public CampFormulariJPA getCampFormulari() {
    return campFormulari;
  }
  public void setCampFormulari(CampFormulariJPA campFormulari) {
    this.campFormulari = campFormulari;
  }
  
  
  private List<StringKeyValue> listOfFormulariForFormulariID;

  public List<StringKeyValue> getListOfFormulariForFormulariID() {
    return this.listOfFormulariForFormulariID;
  }

  public void setListOfFormulariForFormulariID(List<StringKeyValue> listOfFormulariForFormulariID) {
    this.listOfFormulariForFormulariID = listOfFormulariForFormulariID;
  }



  
} // Final de Classe 
