package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TramitECteAudJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TramitECteAudForm extends PinbalAdminBaseForm {
  
  private TramitECteAudJPA tramitECteAud;
  
  public TramitECteAudForm() {
  }
  
  public TramitECteAudForm(TramitECteAudForm __toClone) {
    super(__toClone);
      this.tramitECteAud = __toClone.tramitECteAud;
    this.listOfTramitAPersAutForTramitid = __toClone.listOfTramitAPersAutForTramitid;
  }
  
  public TramitECteAudForm(TramitECteAudJPA tramitECteAud, boolean nou) {
    super(nou);
    this.tramitECteAud = tramitECteAud;
  }
  
  public TramitECteAudJPA getTramitECteAud() {
    return tramitECteAud;
  }
  public void setTramitECteAud(TramitECteAudJPA tramitECteAud) {
    this.tramitECteAud = tramitECteAud;
  }
  
  
  private List<StringKeyValue> listOfTramitAPersAutForTramitid;

  public List<StringKeyValue> getListOfTramitAPersAutForTramitid() {
    return this.listOfTramitAPersAutForTramitid;
  }

  public void setListOfTramitAPersAutForTramitid(List<StringKeyValue> listOfTramitAPersAutForTramitid) {
    this.listOfTramitAPersAutForTramitid = listOfTramitAPersAutForTramitid;
  }



  
} // Final de Classe 
