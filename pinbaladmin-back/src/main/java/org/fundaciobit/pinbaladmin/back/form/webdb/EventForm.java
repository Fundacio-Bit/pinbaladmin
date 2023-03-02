package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EventForm extends PinbalAdminBaseForm {
  
  private EventJPA event;
  
  
  private CommonsMultipartFile fitxerID;
  private boolean fitxerIDDelete;
  
  public EventForm() {
  }
  
  public EventForm(EventForm __toClone) {
    super(__toClone);
      this.event = __toClone.event;
    this.listOfSolicitudForSolicitudID = __toClone.listOfSolicitudForSolicitudID;
    this.listOfIncidenciaTecnicaForIncidenciaTecnicaID = __toClone.listOfIncidenciaTecnicaForIncidenciaTecnicaID;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
  }
  
  public EventForm(EventJPA event, boolean nou) {
    super(nou);
    this.event = event;
  }
  
  public EventJPA getEvent() {
    return event;
  }
  public void setEvent(EventJPA event) {
    this.event = event;
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
  private List<StringKeyValue> listOfSolicitudForSolicitudID;

  public List<StringKeyValue> getListOfSolicitudForSolicitudID() {
    return this.listOfSolicitudForSolicitudID;
  }

  public void setListOfSolicitudForSolicitudID(List<StringKeyValue> listOfSolicitudForSolicitudID) {
    this.listOfSolicitudForSolicitudID = listOfSolicitudForSolicitudID;
  }



  private List<StringKeyValue> listOfIncidenciaTecnicaForIncidenciaTecnicaID;

  public List<StringKeyValue> getListOfIncidenciaTecnicaForIncidenciaTecnicaID() {
    return this.listOfIncidenciaTecnicaForIncidenciaTecnicaID;
  }

  public void setListOfIncidenciaTecnicaForIncidenciaTecnicaID(List<StringKeyValue> listOfIncidenciaTecnicaForIncidenciaTecnicaID) {
    this.listOfIncidenciaTecnicaForIncidenciaTecnicaID = listOfIncidenciaTecnicaForIncidenciaTecnicaID;
  }



  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  
} // Final de Classe 
