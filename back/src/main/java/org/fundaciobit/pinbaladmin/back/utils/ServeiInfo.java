package org.fundaciobit.pinbaladmin.back.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author anadal
 *
 */
public class ServeiInfo {

  protected String nom;
  
  protected String cedent;

  protected List<NormativaInfo> normatives = new ArrayList<NormativaInfo>();

  /**
   * @param nom
   */
  public ServeiInfo(String nom, String cedent) {
    super();
    this.nom = nom;
    this.cedent = cedent;
  }

  public String getNom() {
    return nom;
  }

  public String getCedent() {
    return cedent;
  }

  public void setCedent(String cedent) {
    this.cedent = cedent;
  }

  public List<NormativaInfo> getNormatives() {
    return normatives;
  }

  public void addNormativa(NormativaInfo normativa) {
    if (!normativa.getNormaLegal().isEmpty()) { 
      this.normatives.add(normativa);
    }
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setNormatives(List<NormativaInfo> normatives) {
    this.normatives = normatives;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append(this.nom).append("(Cedent: " + this.cedent  + ")\n");
    for (NormativaInfo n : normatives) {
      str.append("\t\t" + n.getNormaLegal()).append("\n");
      str.append("\t\t" + n.getArticles()).append("\n");
      str.append("\t\t" + n.getEnllaz()).append("\n");
      str.append("\t\t====================\n");
    }
    return str.toString();
  }

}
