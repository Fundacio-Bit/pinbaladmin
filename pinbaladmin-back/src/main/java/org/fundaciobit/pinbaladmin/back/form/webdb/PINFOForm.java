package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.PINFOJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PINFOForm extends PinbalAdminBaseForm {
  
  private PINFOJPA pINFO;
  
  
  private CommonsMultipartFile fitxerID;
  private boolean fitxerIDDelete;
  
  
  private CommonsMultipartFile fitxerfirmatID;
  private boolean fitxerfirmatIDDelete;
  
  public PINFOForm() {
  }
  
  public PINFOForm(PINFOForm __toClone) {
    super(__toClone);
      this.pINFO = __toClone.pINFO;
    this.listOfIncidenciaTecnicaForIncidenciaID = __toClone.listOfIncidenciaTecnicaForIncidenciaID;
  }
  
  public PINFOForm(PINFOJPA pINFO, boolean nou) {
    super(nou);
    this.pINFO = pINFO;
  }
  
  public PINFOJPA getPINFO() {
    return pINFO;
  }
  public void setPINFO(PINFOJPA pINFO) {
    this.pINFO = pINFO;
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
  public CommonsMultipartFile getFitxerfirmatID() {
    return fitxerfirmatID;
  }
  
   public void setFitxerfirmatID(CommonsMultipartFile fitxerfirmatID) {
    this.fitxerfirmatID = fitxerfirmatID;
  }
  public boolean isFitxerfirmatIDDelete() {
    return fitxerfirmatIDDelete;
  }
  
  public void setFitxerfirmatIDDelete(boolean fitxerfirmatIDDelete) {
    this.fitxerfirmatIDDelete = fitxerfirmatIDDelete;
   }
  private List<StringKeyValue> listOfIncidenciaTecnicaForIncidenciaID;

  public List<StringKeyValue> getListOfIncidenciaTecnicaForIncidenciaID() {
    return this.listOfIncidenciaTecnicaForIncidenciaID;
  }

  public void setListOfIncidenciaTecnicaForIncidenciaID(List<StringKeyValue> listOfIncidenciaTecnicaForIncidenciaID) {
    this.listOfIncidenciaTecnicaForIncidenciaID = listOfIncidenciaTecnicaForIncidenciaID;
  }



  
} // Final de Classe 
