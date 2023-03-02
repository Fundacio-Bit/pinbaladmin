package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.CampSolicitudJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class CampSolicitudForm extends PinbalAdminBaseForm {
  
  private CampSolicitudJPA campSolicitud;
  
  public CampSolicitudForm() {
  }
  
  public CampSolicitudForm(CampSolicitudForm __toClone) {
    super(__toClone);
      this.campSolicitud = __toClone.campSolicitud;
    this.listOfCampFormulariForCampFormulariID = __toClone.listOfCampFormulariForCampFormulariID;
    this.listOfSolicitudServeiForSolicitudServeiID = __toClone.listOfSolicitudServeiForSolicitudServeiID;
  }
  
  public CampSolicitudForm(CampSolicitudJPA campSolicitud, boolean nou) {
    super(nou);
    this.campSolicitud = campSolicitud;
  }
  
  public CampSolicitudJPA getCampSolicitud() {
    return campSolicitud;
  }
  public void setCampSolicitud(CampSolicitudJPA campSolicitud) {
    this.campSolicitud = campSolicitud;
  }
  
  
  private List<StringKeyValue> listOfCampFormulariForCampFormulariID;

  public List<StringKeyValue> getListOfCampFormulariForCampFormulariID() {
    return this.listOfCampFormulariForCampFormulariID;
  }

  public void setListOfCampFormulariForCampFormulariID(List<StringKeyValue> listOfCampFormulariForCampFormulariID) {
    this.listOfCampFormulariForCampFormulariID = listOfCampFormulariForCampFormulariID;
  }



  private List<StringKeyValue> listOfSolicitudServeiForSolicitudServeiID;

  public List<StringKeyValue> getListOfSolicitudServeiForSolicitudServeiID() {
    return this.listOfSolicitudServeiForSolicitudServeiID;
  }

  public void setListOfSolicitudServeiForSolicitudServeiID(List<StringKeyValue> listOfSolicitudServeiForSolicitudServeiID) {
    this.listOfSolicitudServeiForSolicitudServeiID = listOfSolicitudServeiForSolicitudServeiID;
  }



  
} // Final de Classe 
