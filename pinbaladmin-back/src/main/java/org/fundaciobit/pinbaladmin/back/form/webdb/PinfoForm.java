package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PinfoForm extends PinbalAdminBaseForm {
  
  private PinfoJPA pinfo;
  
  
  private CommonsMultipartFile fitxerID;
  private boolean fitxerIDDelete;
  
  
  private CommonsMultipartFile fitxerfirmatID;
  private boolean fitxerfirmatIDDelete;
  
  public PinfoForm() {
  }
  
  public PinfoForm(PinfoForm __toClone) {
    super(__toClone);
      this.pinfo = __toClone.pinfo;
    this.listOfIncidenciaTecnicaForIncidenciaID = __toClone.listOfIncidenciaTecnicaForIncidenciaID;
  }
  
  public PinfoForm(PinfoJPA pinfo, boolean nou) {
    super(nou);
    this.pinfo = pinfo;
  }
  
  public PinfoJPA getPinfo() {
    return pinfo;
  }
  public void setPinfo(PinfoJPA pinfo) {
    this.pinfo = pinfo;
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
