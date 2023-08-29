package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TramitAPersAutForm extends PinbalAdminBaseForm {
  
  private TramitAPersAutJPA tramitAPersAut;
  
  public TramitAPersAutForm() {
  }
  
  public TramitAPersAutForm(TramitAPersAutForm __toClone) {
    super(__toClone);
      this.tramitAPersAut = __toClone.tramitAPersAut;
  }
  
  public TramitAPersAutForm(TramitAPersAutJPA tramitAPersAut, boolean nou) {
    super(nou);
    this.tramitAPersAut = tramitAPersAut;
  }
  
  public TramitAPersAutJPA getTramitAPersAut() {
    return tramitAPersAut;
  }
  public void setTramitAPersAut(TramitAPersAutJPA tramitAPersAut) {
    this.tramitAPersAut = tramitAPersAut;
  }
  
  
  
} // Final de Classe 
