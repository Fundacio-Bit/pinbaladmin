package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TramitFCteTecJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TramitFCteTecForm extends PinbalAdminBaseForm {
  
  private TramitFCteTecJPA tramitFCteTec;
  
  public TramitFCteTecForm() {
  }
  
  public TramitFCteTecForm(TramitFCteTecForm __toClone) {
    super(__toClone);
      this.tramitFCteTec = __toClone.tramitFCteTec;
    this.listOfTramitAPersAutForTramitid = __toClone.listOfTramitAPersAutForTramitid;
  }
  
  public TramitFCteTecForm(TramitFCteTecJPA tramitFCteTec, boolean nou) {
    super(nou);
    this.tramitFCteTec = tramitFCteTec;
  }
  
  public TramitFCteTecJPA getTramitFCteTec() {
    return tramitFCteTec;
  }
  public void setTramitFCteTec(TramitFCteTecJPA tramitFCteTec) {
    this.tramitFCteTec = tramitFCteTec;
  }
  
  
  private List<StringKeyValue> listOfTramitAPersAutForTramitid;

  public List<StringKeyValue> getListOfTramitAPersAutForTramitid() {
    return this.listOfTramitAPersAutForTramitid;
  }

  public void setListOfTramitAPersAutForTramitid(List<StringKeyValue> listOfTramitAPersAutForTramitid) {
    this.listOfTramitAPersAutForTramitid = listOfTramitAPersAutForTramitid;
  }



  
} // Final de Classe 
