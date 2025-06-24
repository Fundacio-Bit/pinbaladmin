package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSoliServJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class ModificacioSoliServForm extends PinbalAdminBaseForm {
  
  private ModificacioSoliServJPA modificacioSoliServ;
  
  
  private CommonsMultipartFile fitxerNorma1ID;
  private boolean fitxerNorma1IDDelete;
  
  
  private CommonsMultipartFile fitxerNorma2ID;
  private boolean fitxerNorma2IDDelete;
  
  
  private CommonsMultipartFile fitxerNorma3ID;
  private boolean fitxerNorma3IDDelete;
  
  public ModificacioSoliServForm() {
  }
  
  public ModificacioSoliServForm(ModificacioSoliServForm __toClone) {
    super(__toClone);
      this.modificacioSoliServ = __toClone.modificacioSoliServ;
    this.listOfSolicitudServeiForSoliServID = __toClone.listOfSolicitudServeiForSoliServID;
    this.listOfModificacioSolicitudForModSoliID = __toClone.listOfModificacioSolicitudForModSoliID;
  }
  
  public ModificacioSoliServForm(ModificacioSoliServJPA modificacioSoliServ, boolean nou) {
    super(nou);
    this.modificacioSoliServ = modificacioSoliServ;
  }
  
  public ModificacioSoliServJPA getModificacioSoliServ() {
    return modificacioSoliServ;
  }
  public void setModificacioSoliServ(ModificacioSoliServJPA modificacioSoliServ) {
    this.modificacioSoliServ = modificacioSoliServ;
  }
  
  
  public CommonsMultipartFile getFitxerNorma1ID() {
    return fitxerNorma1ID;
  }
  
   public void setFitxerNorma1ID(CommonsMultipartFile fitxerNorma1ID) {
    this.fitxerNorma1ID = fitxerNorma1ID;
  }
  public boolean isFitxerNorma1IDDelete() {
    return fitxerNorma1IDDelete;
  }
  
  public void setFitxerNorma1IDDelete(boolean fitxerNorma1IDDelete) {
    this.fitxerNorma1IDDelete = fitxerNorma1IDDelete;
   }
  public CommonsMultipartFile getFitxerNorma2ID() {
    return fitxerNorma2ID;
  }
  
   public void setFitxerNorma2ID(CommonsMultipartFile fitxerNorma2ID) {
    this.fitxerNorma2ID = fitxerNorma2ID;
  }
  public boolean isFitxerNorma2IDDelete() {
    return fitxerNorma2IDDelete;
  }
  
  public void setFitxerNorma2IDDelete(boolean fitxerNorma2IDDelete) {
    this.fitxerNorma2IDDelete = fitxerNorma2IDDelete;
   }
  public CommonsMultipartFile getFitxerNorma3ID() {
    return fitxerNorma3ID;
  }
  
   public void setFitxerNorma3ID(CommonsMultipartFile fitxerNorma3ID) {
    this.fitxerNorma3ID = fitxerNorma3ID;
  }
  public boolean isFitxerNorma3IDDelete() {
    return fitxerNorma3IDDelete;
  }
  
  public void setFitxerNorma3IDDelete(boolean fitxerNorma3IDDelete) {
    this.fitxerNorma3IDDelete = fitxerNorma3IDDelete;
   }
  private List<StringKeyValue> listOfSolicitudServeiForSoliServID;

  public List<StringKeyValue> getListOfSolicitudServeiForSoliServID() {
    return this.listOfSolicitudServeiForSoliServID;
  }

  public void setListOfSolicitudServeiForSoliServID(List<StringKeyValue> listOfSolicitudServeiForSoliServID) {
    this.listOfSolicitudServeiForSoliServID = listOfSolicitudServeiForSoliServID;
  }



  private List<StringKeyValue> listOfModificacioSolicitudForModSoliID;

  public List<StringKeyValue> getListOfModificacioSolicitudForModSoliID() {
    return this.listOfModificacioSolicitudForModSoliID;
  }

  public void setListOfModificacioSolicitudForModSoliID(List<StringKeyValue> listOfModificacioSolicitudForModSoliID) {
    this.listOfModificacioSolicitudForModSoliID = listOfModificacioSolicitudForModSoliID;
  }



  
} // Final de Classe 
