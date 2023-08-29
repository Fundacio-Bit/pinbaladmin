package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TramitGDadesTitForm extends PinbalAdminBaseForm {
  
  private TramitGDadesTitJPA tramitGDadesTit;
  
  public TramitGDadesTitForm() {
  }
  
  public TramitGDadesTitForm(TramitGDadesTitForm __toClone) {
    super(__toClone);
      this.tramitGDadesTit = __toClone.tramitGDadesTit;
    this.listOfTramitAPersAutForTramitid = __toClone.listOfTramitAPersAutForTramitid;
  }
  
  public TramitGDadesTitForm(TramitGDadesTitJPA tramitGDadesTit, boolean nou) {
    super(nou);
    this.tramitGDadesTit = tramitGDadesTit;
  }
  
  public TramitGDadesTitJPA getTramitGDadesTit() {
    return tramitGDadesTit;
  }
  public void setTramitGDadesTit(TramitGDadesTitJPA tramitGDadesTit) {
    this.tramitGDadesTit = tramitGDadesTit;
  }
  
  
  private List<StringKeyValue> listOfTramitAPersAutForTramitid;

  public List<StringKeyValue> getListOfTramitAPersAutForTramitid() {
    return this.listOfTramitAPersAutForTramitid;
  }

  public void setListOfTramitAPersAutForTramitid(List<StringKeyValue> listOfTramitAPersAutForTramitid) {
    this.listOfTramitAPersAutForTramitid = listOfTramitAPersAutForTramitid;
  }



  
} // Final de Classe 
