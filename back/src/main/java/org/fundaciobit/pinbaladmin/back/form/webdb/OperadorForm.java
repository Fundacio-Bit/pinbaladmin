package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.OperadorJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class OperadorForm extends PinbalAdminBaseForm {
  
  private OperadorJPA operador;
  
  public OperadorForm() {
  }
  
  public OperadorForm(OperadorForm __toClone) {
    super(__toClone);
      this.operador = __toClone.operador;
  }
  
  public OperadorForm(OperadorJPA operador, boolean nou) {
    super(nou);
    this.operador = operador;
  }
  
  public OperadorJPA getOperador() {
    return operador;
  }
  public void setOperador(OperadorJPA operador) {
    this.operador = operador;
  }
  
  
  
} // Final de Classe 
