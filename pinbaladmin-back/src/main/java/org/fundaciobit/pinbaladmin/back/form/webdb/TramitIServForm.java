package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TramitIServJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TramitIServForm extends PinbalAdminBaseForm {
  
  private TramitIServJPA tramitIServ;
  
  
  private CommonsMultipartFile fitxernormaID;
  private boolean fitxernormaIDDelete;
  
  
  private CommonsMultipartFile fitxernorma2ID;
  private boolean fitxernorma2IDDelete;
  
  public TramitIServForm() {
  }
  
  public TramitIServForm(TramitIServForm __toClone) {
    super(__toClone);
      this.tramitIServ = __toClone.tramitIServ;
    this.listOfTramitAPersAutForTramitid = __toClone.listOfTramitAPersAutForTramitid;
  }
  
  public TramitIServForm(TramitIServJPA tramitIServ, boolean nou) {
    super(nou);
    this.tramitIServ = tramitIServ;
  }
  
  public TramitIServJPA getTramitIServ() {
    return tramitIServ;
  }
  public void setTramitIServ(TramitIServJPA tramitIServ) {
    this.tramitIServ = tramitIServ;
  }
  
  
  public CommonsMultipartFile getFitxernormaID() {
    return fitxernormaID;
  }
  
   public void setFitxernormaID(CommonsMultipartFile fitxernormaID) {
    this.fitxernormaID = fitxernormaID;
  }
  public boolean isFitxernormaIDDelete() {
    return fitxernormaIDDelete;
  }
  
  public void setFitxernormaIDDelete(boolean fitxernormaIDDelete) {
    this.fitxernormaIDDelete = fitxernormaIDDelete;
   }
  public CommonsMultipartFile getFitxernorma2ID() {
    return fitxernorma2ID;
  }
  
   public void setFitxernorma2ID(CommonsMultipartFile fitxernorma2ID) {
    this.fitxernorma2ID = fitxernorma2ID;
  }
  public boolean isFitxernorma2IDDelete() {
    return fitxernorma2IDDelete;
  }
  
  public void setFitxernorma2IDDelete(boolean fitxernorma2IDDelete) {
    this.fitxernorma2IDDelete = fitxernorma2IDDelete;
   }
  private List<StringKeyValue> listOfTramitAPersAutForTramitid;

  public List<StringKeyValue> getListOfTramitAPersAutForTramitid() {
    return this.listOfTramitAPersAutForTramitid;
  }

  public void setListOfTramitAPersAutForTramitid(List<StringKeyValue> listOfTramitAPersAutForTramitid) {
    this.listOfTramitAPersAutForTramitid = listOfTramitAPersAutForTramitid;
  }



  
} // Final de Classe 
