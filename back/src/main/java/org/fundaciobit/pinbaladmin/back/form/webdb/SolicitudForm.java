package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.jpa.SolicitudJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class SolicitudForm extends PinbalAdminBaseForm {
  
  private SolicitudJPA solicitud;
  
  
  private CommonsMultipartFile documentSolicitudID;
  private boolean documentSolicitudIDDelete;
  
  
  private CommonsMultipartFile solicitudXmlID;
  private boolean solicitudXmlIDDelete;
  
  public SolicitudForm() {
  }
  
  public SolicitudForm(SolicitudForm __toClone) {
    super(__toClone);
      this.solicitud = __toClone.solicitud;
    this.listOfValuesForProcedimentTipus = __toClone.listOfValuesForProcedimentTipus;
    this.listOfEstatSolicitudForEstatID = __toClone.listOfEstatSolicitudForEstatID;
    this.listOfDepartamentForDepartamentID = __toClone.listOfDepartamentForDepartamentID;
  }
  
  public SolicitudForm(SolicitudJPA solicitud, boolean nou) {
    super(nou);
    this.solicitud = solicitud;
  }
  
  public SolicitudJPA getSolicitud() {
    return solicitud;
  }
  public void setSolicitud(SolicitudJPA solicitud) {
    this.solicitud = solicitud;
  }
  
  
  public CommonsMultipartFile getDocumentSolicitudID() {
    return documentSolicitudID;
  }
  
   public void setDocumentSolicitudID(CommonsMultipartFile documentSolicitudID) {
    this.documentSolicitudID = documentSolicitudID;
  }
  public boolean isDocumentSolicitudIDDelete() {
    return documentSolicitudIDDelete;
  }
  
  public void setDocumentSolicitudIDDelete(boolean documentSolicitudIDDelete) {
    this.documentSolicitudIDDelete = documentSolicitudIDDelete;
   }
  public CommonsMultipartFile getSolicitudXmlID() {
    return solicitudXmlID;
  }
  
   public void setSolicitudXmlID(CommonsMultipartFile solicitudXmlID) {
    this.solicitudXmlID = solicitudXmlID;
  }
  public boolean isSolicitudXmlIDDelete() {
    return solicitudXmlIDDelete;
  }
  
  public void setSolicitudXmlIDDelete(boolean solicitudXmlIDDelete) {
    this.solicitudXmlIDDelete = solicitudXmlIDDelete;
   }
  private List<StringKeyValue> listOfValuesForProcedimentTipus;

  public List<StringKeyValue> getListOfValuesForProcedimentTipus() {
    return this.listOfValuesForProcedimentTipus;
  }

  public void setListOfValuesForProcedimentTipus(List<StringKeyValue> listOfValuesForProcedimentTipus) {
    this.listOfValuesForProcedimentTipus = listOfValuesForProcedimentTipus;
  }



  private List<StringKeyValue> listOfEstatSolicitudForEstatID;

  public List<StringKeyValue> getListOfEstatSolicitudForEstatID() {
    return this.listOfEstatSolicitudForEstatID;
  }

  public void setListOfEstatSolicitudForEstatID(List<StringKeyValue> listOfEstatSolicitudForEstatID) {
    this.listOfEstatSolicitudForEstatID = listOfEstatSolicitudForEstatID;
  }



  private List<StringKeyValue> listOfDepartamentForDepartamentID;

  public List<StringKeyValue> getListOfDepartamentForDepartamentID() {
    return this.listOfDepartamentForDepartamentID;
  }

  public void setListOfDepartamentForDepartamentID(List<StringKeyValue> listOfDepartamentForDepartamentID) {
    this.listOfDepartamentForDepartamentID = listOfDepartamentForDepartamentID;
  }



  
} // Final de Classe 
