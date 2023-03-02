package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.DocumentEntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class DocumentEntitatForm extends PinbalAdminBaseForm {
  
  private DocumentEntitatJPA documentEntitat;
  
  
  private CommonsMultipartFile fitxerID;
  private boolean fitxerIDDelete;
  
  public DocumentEntitatForm() {
  }
  
  public DocumentEntitatForm(DocumentEntitatForm __toClone) {
    super(__toClone);
      this.documentEntitat = __toClone.documentEntitat;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public DocumentEntitatForm(DocumentEntitatJPA documentEntitat, boolean nou) {
    super(nou);
    this.documentEntitat = documentEntitat;
  }
  
  public DocumentEntitatJPA getDocumentEntitat() {
    return documentEntitat;
  }
  public void setDocumentEntitat(DocumentEntitatJPA documentEntitat) {
    this.documentEntitat = documentEntitat;
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
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 
