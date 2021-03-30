package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.GrupEntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class GrupEntitatForm extends PinbalAdminBaseForm {
  
  private GrupEntitatJPA grupEntitat;
  
  public GrupEntitatForm() {
  }
  
  public GrupEntitatForm(GrupEntitatForm __toClone) {
    super(__toClone);
      this.grupEntitat = __toClone.grupEntitat;
  }
  
  public GrupEntitatForm(GrupEntitatJPA grupEntitat, boolean nou) {
    super(nou);
    this.grupEntitat = grupEntitat;
  }
  
  public GrupEntitatJPA getGrupEntitat() {
    return grupEntitat;
  }
  public void setGrupEntitat(GrupEntitatJPA grupEntitat) {
    this.grupEntitat = grupEntitat;
  }
  
  
  
} // Final de Classe 
