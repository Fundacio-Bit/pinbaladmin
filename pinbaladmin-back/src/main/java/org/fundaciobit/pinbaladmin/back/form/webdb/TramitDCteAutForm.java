package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TramitDCteAutJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TramitDCteAutForm extends PinbalAdminBaseForm {
  
  private TramitDCteAutJPA tramitDCteAut;
  
  public TramitDCteAutForm() {
  }
  
  public TramitDCteAutForm(TramitDCteAutForm __toClone) {
    super(__toClone);
      this.tramitDCteAut = __toClone.tramitDCteAut;
    this.listOfTramitAPersAutForTramitid = __toClone.listOfTramitAPersAutForTramitid;
  }
  
  public TramitDCteAutForm(TramitDCteAutJPA tramitDCteAut, boolean nou) {
    super(nou);
    this.tramitDCteAut = tramitDCteAut;
  }
  
  public TramitDCteAutJPA getTramitDCteAut() {
    return tramitDCteAut;
  }
  public void setTramitDCteAut(TramitDCteAutJPA tramitDCteAut) {
    this.tramitDCteAut = tramitDCteAut;
  }
  
  
  private List<StringKeyValue> listOfTramitAPersAutForTramitid;

  public List<StringKeyValue> getListOfTramitAPersAutForTramitid() {
    return this.listOfTramitAPersAutForTramitid;
  }

  public void setListOfTramitAPersAutForTramitid(List<StringKeyValue> listOfTramitAPersAutForTramitid) {
    this.listOfTramitAPersAutForTramitid = listOfTramitAPersAutForTramitid;
  }



  
} // Final de Classe 
