package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.TramitIServJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TramitIServForm extends PinbalAdminBaseForm {
  
  private TramitIServJPA tramitIServ;
  
  public TramitIServForm() {
  }
  
  public TramitIServForm(TramitIServForm __toClone) {
    super(__toClone);
      this.tramitIServ = __toClone.tramitIServ;
    this.listOfTramitAPersAutForTramitid = __toClone.listOfTramitAPersAutForTramitid;
    this.listOfValuesForNom = __toClone.listOfValuesForNom;
    this.listOfValuesForConsentiment = __toClone.listOfValuesForConsentiment;
    this.listOfValuesForConsentimentpublicat = __toClone.listOfValuesForConsentimentpublicat;
  }
  
  public TramitIServForm(TramitIServJPA tramitIServ, boolean nou) {
    super(nou);
    this.tramitIServ = tramitIServ;
  }
  
  public TramitIServJPA getTramitIServ() {
    return tramitIServ;
  }
  public void setTramitIServ(TramitIServJPA tramitIServ) {
    this.tramitIServ = tramitIServ;
  }
  
  
  private List<StringKeyValue> listOfTramitAPersAutForTramitid;

  public List<StringKeyValue> getListOfTramitAPersAutForTramitid() {
    return this.listOfTramitAPersAutForTramitid;
  }

  public void setListOfTramitAPersAutForTramitid(List<StringKeyValue> listOfTramitAPersAutForTramitid) {
    this.listOfTramitAPersAutForTramitid = listOfTramitAPersAutForTramitid;
  }



  private List<StringKeyValue> listOfValuesForNom;

  public List<StringKeyValue> getListOfValuesForNom() {
    return this.listOfValuesForNom;
  }

  public void setListOfValuesForNom(List<StringKeyValue> listOfValuesForNom) {
    this.listOfValuesForNom = listOfValuesForNom;
  }



  private List<StringKeyValue> listOfValuesForConsentiment;

  public List<StringKeyValue> getListOfValuesForConsentiment() {
    return this.listOfValuesForConsentiment;
  }

  public void setListOfValuesForConsentiment(List<StringKeyValue> listOfValuesForConsentiment) {
    this.listOfValuesForConsentiment = listOfValuesForConsentiment;
  }



  private List<StringKeyValue> listOfValuesForConsentimentpublicat;

  public List<StringKeyValue> getListOfValuesForConsentimentpublicat() {
    return this.listOfValuesForConsentimentpublicat;
  }

  public void setListOfValuesForConsentimentpublicat(List<StringKeyValue> listOfValuesForConsentimentpublicat) {
    this.listOfValuesForConsentimentpublicat = listOfValuesForConsentimentpublicat;
  }



  
} // Final de Classe 
