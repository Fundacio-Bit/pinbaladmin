package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSolicitudJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class ModificacioSolicitudForm extends PinbalAdminBaseForm {
  
  private ModificacioSolicitudJPA modificacioSolicitud;
  
  
  private CommonsMultipartFile doCconsentimentID;
  private boolean doCconsentimentIDDelete;
  
  public ModificacioSolicitudForm() {
  }
  
  public ModificacioSolicitudForm(ModificacioSolicitudForm __toClone) {
    super(__toClone);
      this.modificacioSolicitud = __toClone.modificacioSolicitud;
    this.listOfSolicitudForSolicitudID = __toClone.listOfSolicitudForSolicitudID;
    this.listOfValuesForEstatID = __toClone.listOfValuesForEstatID;
    this.listOfValuesForProcedimentTipus = __toClone.listOfValuesForProcedimentTipus;
    this.listOfOrganForOrganID = __toClone.listOfOrganForOrganID;
    this.listOfValuesForConsentiment = __toClone.listOfValuesForConsentiment;
    this.listOfValuesForEstatModificacio = __toClone.listOfValuesForEstatModificacio;
  }
  
  public ModificacioSolicitudForm(ModificacioSolicitudJPA modificacioSolicitud, boolean nou) {
    super(nou);
    this.modificacioSolicitud = modificacioSolicitud;
  }
  
  public ModificacioSolicitudJPA getModificacioSolicitud() {
    return modificacioSolicitud;
  }
  public void setModificacioSolicitud(ModificacioSolicitudJPA modificacioSolicitud) {
    this.modificacioSolicitud = modificacioSolicitud;
  }
  
  
  public CommonsMultipartFile getDoCconsentimentID() {
    return doCconsentimentID;
  }
  
   public void setDoCconsentimentID(CommonsMultipartFile doCconsentimentID) {
    this.doCconsentimentID = doCconsentimentID;
  }
  public boolean isDoCconsentimentIDDelete() {
    return doCconsentimentIDDelete;
  }
  
  public void setDoCconsentimentIDDelete(boolean doCconsentimentIDDelete) {
    this.doCconsentimentIDDelete = doCconsentimentIDDelete;
   }
  private List<StringKeyValue> listOfSolicitudForSolicitudID;

  public List<StringKeyValue> getListOfSolicitudForSolicitudID() {
    return this.listOfSolicitudForSolicitudID;
  }

  public void setListOfSolicitudForSolicitudID(List<StringKeyValue> listOfSolicitudForSolicitudID) {
    this.listOfSolicitudForSolicitudID = listOfSolicitudForSolicitudID;
  }



  private List<StringKeyValue> listOfValuesForEstatID;

  public List<StringKeyValue> getListOfValuesForEstatID() {
    return this.listOfValuesForEstatID;
  }

  public void setListOfValuesForEstatID(List<StringKeyValue> listOfValuesForEstatID) {
    this.listOfValuesForEstatID = listOfValuesForEstatID;
  }



  private List<StringKeyValue> listOfValuesForProcedimentTipus;

  public List<StringKeyValue> getListOfValuesForProcedimentTipus() {
    return this.listOfValuesForProcedimentTipus;
  }

  public void setListOfValuesForProcedimentTipus(List<StringKeyValue> listOfValuesForProcedimentTipus) {
    this.listOfValuesForProcedimentTipus = listOfValuesForProcedimentTipus;
  }



  private List<StringKeyValue> listOfOrganForOrganID;

  public List<StringKeyValue> getListOfOrganForOrganID() {
    return this.listOfOrganForOrganID;
  }

  public void setListOfOrganForOrganID(List<StringKeyValue> listOfOrganForOrganID) {
    this.listOfOrganForOrganID = listOfOrganForOrganID;
  }



  private List<StringKeyValue> listOfValuesForConsentiment;

  public List<StringKeyValue> getListOfValuesForConsentiment() {
    return this.listOfValuesForConsentiment;
  }

  public void setListOfValuesForConsentiment(List<StringKeyValue> listOfValuesForConsentiment) {
    this.listOfValuesForConsentiment = listOfValuesForConsentiment;
  }



  private List<StringKeyValue> listOfValuesForEstatModificacio;

  public List<StringKeyValue> getListOfValuesForEstatModificacio() {
    return this.listOfValuesForEstatModificacio;
  }

  public void setListOfValuesForEstatModificacio(List<StringKeyValue> listOfValuesForEstatModificacio) {
    this.listOfValuesForEstatModificacio = listOfValuesForEstatModificacio;
  }



  
} // Final de Classe 
