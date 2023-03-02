package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.EmailJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EmailForm extends PinbalAdminBaseForm {
  
  private EmailJPA email;
  
  public EmailForm() {
  }
  
  public EmailForm(EmailForm __toClone) {
    super(__toClone);
      this.email = __toClone.email;
  }
  
  public EmailForm(EmailJPA email, boolean nou) {
    super(nou);
    this.email = email;
  }
  
  public EmailJPA getEmail() {
    return email;
  }
  public void setEmail(EmailJPA email) {
    this.email = email;
  }
  
  
  
} // Final de Classe 
