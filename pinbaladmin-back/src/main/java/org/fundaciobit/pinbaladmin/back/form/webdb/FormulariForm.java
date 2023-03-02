package org.fundaciobit.pinbaladmin.back.form.webdb;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.FormulariJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class FormulariForm extends PinbalAdminBaseForm {
  
  private FormulariJPA formulari;
  
  
  private CommonsMultipartFile fitxerID;
  private boolean fitxerIDDelete;
  
  public FormulariForm() {
  }
  
  public FormulariForm(FormulariForm __toClone) {
    super(__toClone);
      this.formulari = __toClone.formulari;
  }
  
  public FormulariForm(FormulariJPA formulari, boolean nou) {
    super(nou);
    this.formulari = formulari;
  }
  
  public FormulariJPA getFormulari() {
    return formulari;
  }
  public void setFormulari(FormulariJPA formulari) {
    this.formulari = formulari;
  }
  
  
  public CommonsMultipartFile getFitxerID() {
    return fitxerID;
  }
  
   public void setFitxerID(CommonsMultipartFile fitxerID) {
    this.fitxerID = fitxerID;
  }
  public boolean isFitxerIDDelete() {
    return fitxerIDDelete;
  }
  
  public void setFitxerIDDelete(boolean fitxerIDDelete) {
    this.fitxerIDDelete = fitxerIDDelete;
   }
  
} // Final de Classe 
