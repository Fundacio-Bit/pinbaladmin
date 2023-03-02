package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.EstatSolicitudServeiJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EstatSolicitudServeiForm extends PinbalAdminBaseForm {
  
  private EstatSolicitudServeiJPA estatSolicitudServei;
  
  public EstatSolicitudServeiForm() {
  }
  
  public EstatSolicitudServeiForm(EstatSolicitudServeiForm __toClone) {
    super(__toClone);
      this.estatSolicitudServei = __toClone.estatSolicitudServei;
  }
  
  public EstatSolicitudServeiForm(EstatSolicitudServeiJPA estatSolicitudServei, boolean nou) {
    super(nou);
    this.estatSolicitudServei = estatSolicitudServei;
  }
  
  public EstatSolicitudServeiJPA getEstatSolicitudServei() {
    return estatSolicitudServei;
  }
  public void setEstatSolicitudServei(EstatSolicitudServeiJPA estatSolicitudServei) {
    this.estatSolicitudServei = estatSolicitudServei;
  }
  
  
  
} // Final de Classe 
