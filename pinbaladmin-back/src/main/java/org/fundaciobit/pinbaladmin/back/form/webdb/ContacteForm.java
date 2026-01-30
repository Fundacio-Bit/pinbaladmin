package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.ContacteJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class ContacteForm extends PinbalAdminBaseForm {
  
  private ContacteJPA contacte;
  
  public ContacteForm() {
  }
  
  public ContacteForm(ContacteForm __toClone) {
    super(__toClone);
      this.contacte = __toClone.contacte;
  }
  
  public ContacteForm(ContacteJPA contacte, boolean nou) {
    super(nou);
    this.contacte = contacte;
  }
  
  public ContacteJPA getContacte() {
    return contacte;
  }
  public void setContacte(ContacteJPA contacte) {
    this.contacte = contacte;
  }
  
  
  
} // Final de Classe 
