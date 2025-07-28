package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.pinbaladmin.back.form.PinbalAdminBaseForm;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class InfoMadridForm extends PinbalAdminBaseForm {
  
  private InfoMadridJPA infoMadrid;
  
  public InfoMadridForm() {
  }
  
  public InfoMadridForm(InfoMadridForm __toClone) {
    super(__toClone);
      this.infoMadrid = __toClone.infoMadrid;
    this.listOfValuesForEstatProcediment = __toClone.listOfValuesForEstatProcediment;
    this.listOfValuesForEstatAutoritzacio = __toClone.listOfValuesForEstatAutoritzacio;
  }
  
  public InfoMadridForm(InfoMadridJPA infoMadrid, boolean nou) {
    super(nou);
    this.infoMadrid = infoMadrid;
  }
  
  public InfoMadridJPA getInfoMadrid() {
    return infoMadrid;
  }
  public void setInfoMadrid(InfoMadridJPA infoMadrid) {
    this.infoMadrid = infoMadrid;
  }
  
  
  private List<StringKeyValue> listOfValuesForEstatProcediment;

  public List<StringKeyValue> getListOfValuesForEstatProcediment() {
    return this.listOfValuesForEstatProcediment;
  }

  public void setListOfValuesForEstatProcediment(List<StringKeyValue> listOfValuesForEstatProcediment) {
    this.listOfValuesForEstatProcediment = listOfValuesForEstatProcediment;
  }



  private List<StringKeyValue> listOfValuesForEstatAutoritzacio;

  public List<StringKeyValue> getListOfValuesForEstatAutoritzacio() {
    return this.listOfValuesForEstatAutoritzacio;
  }

  public void setListOfValuesForEstatAutoritzacio(List<StringKeyValue> listOfValuesForEstatAutoritzacio) {
    this.listOfValuesForEstatAutoritzacio = listOfValuesForEstatAutoritzacio;
  }



  
} // Final de Classe 
