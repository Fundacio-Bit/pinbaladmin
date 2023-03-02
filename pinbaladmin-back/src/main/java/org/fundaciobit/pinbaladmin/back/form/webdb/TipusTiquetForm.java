package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TipusTiquetJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TipusTiquetForm extends PinbalAdminBaseForm {
  
  private TipusTiquetJPA tipusTiquet;
  
  public TipusTiquetForm() {
  }
  
  public TipusTiquetForm(TipusTiquetForm __toClone) {
    super(__toClone);
      this.tipusTiquet = __toClone.tipusTiquet;
  }
  
  public TipusTiquetForm(TipusTiquetJPA tipusTiquet, boolean nou) {
    super(nou);
    this.tipusTiquet = tipusTiquet;
  }
  
  public TipusTiquetJPA getTipusTiquet() {
    return tipusTiquet;
  }
  public void setTipusTiquet(TipusTiquetJPA tipusTiquet) {
    this.tipusTiquet = tipusTiquet;
  }
  
  
  
} // Final de Classe 
