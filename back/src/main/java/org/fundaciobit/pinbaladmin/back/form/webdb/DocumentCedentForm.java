package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.DocumentCedentJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class DocumentCedentForm extends PinbalAdminBaseForm {
  
  private DocumentCedentJPA documentCedent;
  
  
  private CommonsMultipartFile fitxerID;
  private boolean fitxerIDDelete;
  
  public DocumentCedentForm() {
  }
  
  public DocumentCedentForm(DocumentCedentForm __toClone) {
    super(__toClone);
      this.documentCedent = __toClone.documentCedent;
    this.listOfEntitatServeiForEntitatServeiID = __toClone.listOfEntitatServeiForEntitatServeiID;
  }
  
  public DocumentCedentForm(DocumentCedentJPA documentCedent, boolean nou) {
    super(nou);
    this.documentCedent = documentCedent;
  }
  
  public DocumentCedentJPA getDocumentCedent() {
    return documentCedent;
  }
  public void setDocumentCedent(DocumentCedentJPA documentCedent) {
    this.documentCedent = documentCedent;
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
  private List<StringKeyValue> listOfEntitatServeiForEntitatServeiID;

  public List<StringKeyValue> getListOfEntitatServeiForEntitatServeiID() {
    return this.listOfEntitatServeiForEntitatServeiID;
  }

  public void setListOfEntitatServeiForEntitatServeiID(List<StringKeyValue> listOfEntitatServeiForEntitatServeiID) {
    this.listOfEntitatServeiForEntitatServeiID = listOfEntitatServeiForEntitatServeiID;
  }



  
} // Final de Classe 
