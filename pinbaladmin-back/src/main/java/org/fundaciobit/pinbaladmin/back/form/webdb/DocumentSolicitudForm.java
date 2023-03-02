package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class DocumentSolicitudForm extends PinbalAdminBaseForm {
  
  private DocumentSolicitudJPA documentSolicitud;
  
  public DocumentSolicitudForm() {
  }
  
  public DocumentSolicitudForm(DocumentSolicitudForm __toClone) {
    super(__toClone);
      this.documentSolicitud = __toClone.documentSolicitud;
    this.listOfDocumentForDocumentID = __toClone.listOfDocumentForDocumentID;
    this.listOfSolicitudForSolicitudID = __toClone.listOfSolicitudForSolicitudID;
  }
  
  public DocumentSolicitudForm(DocumentSolicitudJPA documentSolicitud, boolean nou) {
    super(nou);
    this.documentSolicitud = documentSolicitud;
  }
  
  public DocumentSolicitudJPA getDocumentSolicitud() {
    return documentSolicitud;
  }
  public void setDocumentSolicitud(DocumentSolicitudJPA documentSolicitud) {
    this.documentSolicitud = documentSolicitud;
  }
  
  
  private List<StringKeyValue> listOfDocumentForDocumentID;

  public List<StringKeyValue> getListOfDocumentForDocumentID() {
    return this.listOfDocumentForDocumentID;
  }

  public void setListOfDocumentForDocumentID(List<StringKeyValue> listOfDocumentForDocumentID) {
    this.listOfDocumentForDocumentID = listOfDocumentForDocumentID;
  }



  private List<StringKeyValue> listOfSolicitudForSolicitudID;

  public List<StringKeyValue> getListOfSolicitudForSolicitudID() {
    return this.listOfSolicitudForSolicitudID;
  }

  public void setListOfSolicitudForSolicitudID(List<StringKeyValue> listOfSolicitudForSolicitudID) {
    this.listOfSolicitudForSolicitudID = listOfSolicitudForSolicitudID;
  }



  
} // Final de Classe 
