package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TramitJConsentJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TramitJConsentForm extends PinbalAdminBaseForm {
  
  private TramitJConsentJPA tramitJConsent;
  
  
  private CommonsMultipartFile adjuntID;
  private boolean adjuntIDDelete;
  
  public TramitJConsentForm() {
  }
  
  public TramitJConsentForm(TramitJConsentForm __toClone) {
    super(__toClone);
      this.tramitJConsent = __toClone.tramitJConsent;
    this.listOfTramitAPersAutForTramitid = __toClone.listOfTramitAPersAutForTramitid;
    this.listOfValuesForConsentiment = __toClone.listOfValuesForConsentiment;
    this.listOfValuesForConsentimentadjunt = __toClone.listOfValuesForConsentimentadjunt;
  }
  
  public TramitJConsentForm(TramitJConsentJPA tramitJConsent, boolean nou) {
    super(nou);
    this.tramitJConsent = tramitJConsent;
  }
  
  public TramitJConsentJPA getTramitJConsent() {
    return tramitJConsent;
  }
  public void setTramitJConsent(TramitJConsentJPA tramitJConsent) {
    this.tramitJConsent = tramitJConsent;
  }
  
  
  public CommonsMultipartFile getAdjuntID() {
    return adjuntID;
  }
  
   public void setAdjuntID(CommonsMultipartFile adjuntID) {
    this.adjuntID = adjuntID;
  }
  public boolean isAdjuntIDDelete() {
    return adjuntIDDelete;
  }
  
  public void setAdjuntIDDelete(boolean adjuntIDDelete) {
    this.adjuntIDDelete = adjuntIDDelete;
   }
  private List<StringKeyValue> listOfTramitAPersAutForTramitid;

  public List<StringKeyValue> getListOfTramitAPersAutForTramitid() {
    return this.listOfTramitAPersAutForTramitid;
  }

  public void setListOfTramitAPersAutForTramitid(List<StringKeyValue> listOfTramitAPersAutForTramitid) {
    this.listOfTramitAPersAutForTramitid = listOfTramitAPersAutForTramitid;
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
