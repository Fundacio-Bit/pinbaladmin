package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class DocumentForm extends PinbalAdminBaseForm {
  
  private DocumentJPA document;
  
  
  private CommonsMultipartFile fitxerOriginalID;
  private boolean fitxerOriginalIDDelete;
  
  
  private CommonsMultipartFile fitxerFirmatID;
  private boolean fitxerFirmatIDDelete;
  
  public DocumentForm() {
  }
  
  public DocumentForm(DocumentForm __toClone) {
    super(__toClone);
      this.document = __toClone.document;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
  }
  
  public DocumentForm(DocumentJPA document, boolean nou) {
    super(nou);
    this.document = document;
  }
  
  public DocumentJPA getDocument() {
    return document;
  }
  public void setDocument(DocumentJPA document) {
    this.document = document;
  }
  
  
  public CommonsMultipartFile getFitxerOriginalID() {
    return fitxerOriginalID;
  }
  
   public void setFitxerOriginalID(CommonsMultipartFile fitxerOriginalID) {
    this.fitxerOriginalID = fitxerOriginalID;
  }
  public boolean isFitxerOriginalIDDelete() {
    return fitxerOriginalIDDelete;
  }
  
  public void setFitxerOriginalIDDelete(boolean fitxerOriginalIDDelete) {
    this.fitxerOriginalIDDelete = fitxerOriginalIDDelete;
   }
  public CommonsMultipartFile getFitxerFirmatID() {
    return fitxerFirmatID;
  }
  
   public void setFitxerFirmatID(CommonsMultipartFile fitxerFirmatID) {
    this.fitxerFirmatID = fitxerFirmatID;
  }
  public boolean isFitxerFirmatIDDelete() {
    return fitxerFirmatIDDelete;
  }
  
  public void setFitxerFirmatIDDelete(boolean fitxerFirmatIDDelete) {
    this.fitxerFirmatIDDelete = fitxerFirmatIDDelete;
   }
  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  
} // Final de Classe 
