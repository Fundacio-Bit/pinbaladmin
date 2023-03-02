package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.EstatSolicitudJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EstatSolicitudForm extends PinbalAdminBaseForm {
  
  private EstatSolicitudJPA estatSolicitud;
  
  public EstatSolicitudForm() {
  }
  
  public EstatSolicitudForm(EstatSolicitudForm __toClone) {
    super(__toClone);
      this.estatSolicitud = __toClone.estatSolicitud;
  }
  
  public EstatSolicitudForm(EstatSolicitudJPA estatSolicitud, boolean nou) {
    super(nou);
    this.estatSolicitud = estatSolicitud;
  }
  
  public EstatSolicitudJPA getEstatSolicitud() {
    return estatSolicitud;
  }
  public void setEstatSolicitud(EstatSolicitudJPA estatSolicitud) {
    this.estatSolicitud = estatSolicitud;
  }
  
  
  
} // Final de Classe 
