package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.EstatTiquetJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EstatTiquetForm extends PinbalAdminBaseForm {
  
  private EstatTiquetJPA estatTiquet;
  
  public EstatTiquetForm() {
  }
  
  public EstatTiquetForm(EstatTiquetForm __toClone) {
    super(__toClone);
      this.estatTiquet = __toClone.estatTiquet;
  }
  
  public EstatTiquetForm(EstatTiquetJPA estatTiquet, boolean nou) {
    super(nou);
    this.estatTiquet = estatTiquet;
  }
  
  public EstatTiquetJPA getEstatTiquet() {
    return estatTiquet;
  }
  public void setEstatTiquet(EstatTiquetJPA estatTiquet) {
    this.estatTiquet = estatTiquet;
  }
  
  
  
} // Final de Classe 
