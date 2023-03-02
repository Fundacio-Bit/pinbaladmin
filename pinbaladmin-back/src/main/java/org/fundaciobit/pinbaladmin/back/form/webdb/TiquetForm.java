package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TiquetJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TiquetForm extends PinbalAdminBaseForm {
  
  private TiquetJPA tiquet;
  
  
  private CommonsMultipartFile adjunt1ID;
  private boolean adjunt1IDDelete;
  
  
  private CommonsMultipartFile adjunt2ID;
  private boolean adjunt2IDDelete;
  
  public TiquetForm() {
  }
  
  public TiquetForm(TiquetForm __toClone) {
    super(__toClone);
      this.tiquet = __toClone.tiquet;
    this.listOfEstatTiquetForEstatTiquetID = __toClone.listOfEstatTiquetForEstatTiquetID;
    this.listOfTipusTiquetForTipusTiquetID = __toClone.listOfTipusTiquetForTipusTiquetID;
    this.listOfValuesForEntorn = __toClone.listOfValuesForEntorn;
  }
  
  public TiquetForm(TiquetJPA tiquet, boolean nou) {
    super(nou);
    this.tiquet = tiquet;
  }
  
  public TiquetJPA getTiquet() {
    return tiquet;
  }
  public void setTiquet(TiquetJPA tiquet) {
    this.tiquet = tiquet;
  }
  
  
  public CommonsMultipartFile getAdjunt1ID() {
    return adjunt1ID;
  }
  
   public void setAdjunt1ID(CommonsMultipartFile adjunt1ID) {
    this.adjunt1ID = adjunt1ID;
  }
  public boolean isAdjunt1IDDelete() {
    return adjunt1IDDelete;
  }
  
  public void setAdjunt1IDDelete(boolean adjunt1IDDelete) {
    this.adjunt1IDDelete = adjunt1IDDelete;
   }
  public CommonsMultipartFile getAdjunt2ID() {
    return adjunt2ID;
  }
  
   public void setAdjunt2ID(CommonsMultipartFile adjunt2ID) {
    this.adjunt2ID = adjunt2ID;
  }
  public boolean isAdjunt2IDDelete() {
    return adjunt2IDDelete;
  }
  
  public void setAdjunt2IDDelete(boolean adjunt2IDDelete) {
    this.adjunt2IDDelete = adjunt2IDDelete;
   }
  private List<StringKeyValue> listOfEstatTiquetForEstatTiquetID;

  public List<StringKeyValue> getListOfEstatTiquetForEstatTiquetID() {
    return this.listOfEstatTiquetForEstatTiquetID;
  }

  public void setListOfEstatTiquetForEstatTiquetID(List<StringKeyValue> listOfEstatTiquetForEstatTiquetID) {
    this.listOfEstatTiquetForEstatTiquetID = listOfEstatTiquetForEstatTiquetID;
  }



  private List<StringKeyValue> listOfTipusTiquetForTipusTiquetID;

  public List<StringKeyValue> getListOfTipusTiquetForTipusTiquetID() {
    return this.listOfTipusTiquetForTipusTiquetID;
  }

  public void setListOfTipusTiquetForTipusTiquetID(List<StringKeyValue> listOfTipusTiquetForTipusTiquetID) {
    this.listOfTipusTiquetForTipusTiquetID = listOfTipusTiquetForTipusTiquetID;
  }



  private List<StringKeyValue> listOfValuesForEntorn;

  public List<StringKeyValue> getListOfValuesForEntorn() {
    return this.listOfValuesForEntorn;
  }

  public void setListOfValuesForEntorn(List<StringKeyValue> listOfValuesForEntorn) {
    this.listOfValuesForEntorn = listOfValuesForEntorn;
  }



  
} // Final de Classe 
