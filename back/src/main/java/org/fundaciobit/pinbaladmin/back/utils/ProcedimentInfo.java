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

  List<String> servicios = new ArrayList<String>();

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

  public List<String> getServicios() {
    return servicios;
  }

  public void setServicios(List<String> servicios) {
    this.servicios = servicios;
  }

  public void addServicio(String servicio) {
    this.servicios.add(servicio);
  }

}
