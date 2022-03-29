package org.fundaciobit.pinbaladmin.back.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author anadal
 *
 */
public class ProcedimentInfo {

  final String codi;
  final String nom;
  final String tipusProcediment;

  List<ServeiInfo> serveis = new ArrayList<ServeiInfo>();

  /**
   * @param codi
   * @param nom
   * @param tipusProcediment
   */
  public ProcedimentInfo(String codi, String nom, String tipusProcediment) {
    super();
    this.codi = codi;
    this.nom = nom;
    this.tipusProcediment = tipusProcediment;
  }

  public String getCodi() {
    return codi;
  }

  public String getNom() {
    return nom;
  }

  public String getTipusProcediment() {
    return tipusProcediment;
  }

  public List<ServeiInfo> getServeis() {
    return serveis;
  }

  public void setServeis(List<ServeiInfo> serveis) {
    this.serveis = serveis;
  }

  public void addServei(ServeiInfo servei) {
    this.serveis.add(servei);
  }

}
