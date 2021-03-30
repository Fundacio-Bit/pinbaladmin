package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.EstatServeiJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EstatServeiForm extends PinbalAdminBaseForm {
  
  private EstatServeiJPA estatServei;
  
  public EstatServeiForm() {
  }
  
  public EstatServeiForm(EstatServeiForm __toClone) {
    super(__toClone);
      this.estatServei = __toClone.estatServei;
  }
  
  public EstatServeiForm(EstatServeiJPA estatServei, boolean nou) {
    super(nou);
    this.estatServei = estatServei;
  }
  
  public EstatServeiJPA getEstatServei() {
    return estatServei;
  }
  public void setEstatServei(EstatServeiJPA estatServei) {
    this.estatServei = estatServei;
  }
  
  
  
} // Final de Classe 
