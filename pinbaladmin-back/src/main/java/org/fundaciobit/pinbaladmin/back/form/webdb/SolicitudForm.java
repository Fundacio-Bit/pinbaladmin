package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

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
    this.listOfValuesForEstatID = __toClone.listOfValuesForEstatID;
    this.listOfOrganForOrganid = __toClone.listOfOrganForOrganid;
    this.listOfValuesForCreador = __toClone.listOfValuesForCreador;
    this.listOfValuesForOperador = __toClone.listOfValuesForOperador;
    this.listOfValuesForEstatpinbal = __toClone.listOfValuesForEstatpinbal;
    this.listOfValuesForConsentiment = __toClone.listOfValuesForConsentiment;
    this.listOfValuesForConsentimentadjunt = __toClone.listOfValuesForConsentimentadjunt;
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



  private List<StringKeyValue> listOfValuesForEstatID;

  public List<StringKeyValue> getListOfValuesForEstatID() {
    return this.listOfValuesForEstatID;
  }

  public void setListOfValuesForEstatID(List<StringKeyValue> listOfValuesForEstatID) {
    this.listOfValuesForEstatID = listOfValuesForEstatID;
  }



  private List<StringKeyValue> listOfOrganForOrganid;

  public List<StringKeyValue> getListOfOrganForOrganid() {
    return this.listOfOrganForOrganid;
  }

  public void setListOfOrganForOrganid(List<StringKeyValue> listOfOrganForOrganid) {
    this.listOfOrganForOrganid = listOfOrganForOrganid;
  }



  private List<StringKeyValue> listOfValuesForCreador;

  public List<StringKeyValue> getListOfValuesForCreador() {
    return this.listOfValuesForCreador;
  }

  public void setListOfValuesForCreador(List<StringKeyValue> listOfValuesForCreador) {
    this.listOfValuesForCreador = listOfValuesForCreador;
  }



  private List<StringKeyValue> listOfValuesForOperador;

  public List<StringKeyValue> getListOfValuesForOperador() {
    return this.listOfValuesForOperador;
  }

  public void setListOfValuesForOperador(List<StringKeyValue> listOfValuesForOperador) {
    this.listOfValuesForOperador = listOfValuesForOperador;
  }



  private List<StringKeyValue> listOfValuesForEstatpinbal;

  public List<StringKeyValue> getListOfValuesForEstatpinbal() {
    return this.listOfValuesForEstatpinbal;
  }

  public void setListOfValuesForEstatpinbal(List<StringKeyValue> listOfValuesForEstatpinbal) {
    this.listOfValuesForEstatpinbal = listOfValuesForEstatpinbal;
  }



  private List<StringKeyValue> listOfValuesForConsentiment;

  public List<StringKeyValue> getListOfValuesForConsentiment() {
    return this.listOfValuesForConsentiment;
  }

  public void setListOfValuesForConsentiment(List<StringKeyValue> listOfValuesForConsentiment) {
    this.listOfValuesForConsentiment = listOfValuesForConsentiment;
  }



  private List<StringKeyValue> listOfValuesForConsentimentadjunt;

  public List<StringKeyValue> getListOfValuesForConsentimentadjunt() {
    return this.listOfValuesForConsentimentadjunt;
  }

  public void setListOfValuesForConsentimentadjunt(List<StringKeyValue> listOfValuesForConsentimentadjunt) {
    this.listOfValuesForConsentimentadjunt = listOfValuesForConsentimentadjunt;
  }



  
} // Final de Classe 
