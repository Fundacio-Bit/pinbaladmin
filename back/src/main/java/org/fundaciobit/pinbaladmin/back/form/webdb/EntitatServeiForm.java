package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.EntitatServeiJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EntitatServeiForm extends PinbalAdminBaseForm {
  
  private EntitatServeiJPA entitatServei;
  
  public EntitatServeiForm() {
  }
  
  public EntitatServeiForm(EntitatServeiForm __toClone) {
    super(__toClone);
      this.entitatServei = __toClone.entitatServei;
  }
  
  public EntitatServeiForm(EntitatServeiJPA entitatServei, boolean nou) {
    super(nou);
    this.entitatServei = entitatServei;
  }
  
  public EntitatServeiJPA getEntitatServei() {
    return entitatServei;
  }
  public void setEntitatServei(EntitatServeiJPA entitatServei) {
    this.entitatServei = entitatServei;
  }
  
  
  
} // Final de Classe 
