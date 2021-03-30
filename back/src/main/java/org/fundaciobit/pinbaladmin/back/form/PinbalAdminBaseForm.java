package org.fundaciobit.pinbaladmin.back.form;

import org.fundaciobit.genapp.common.web.form.BaseForm;

/**
 * 
 * @author anadal
 *
 */
public abstract class PinbalAdminBaseForm extends BaseForm {

  public PinbalAdminBaseForm() {
  }
  
  public PinbalAdminBaseForm(boolean nou) {
    super(nou);
  }
  
  public PinbalAdminBaseForm(PinbalAdminBaseForm __toClone) {
    super(__toClone);
  }
  
}
