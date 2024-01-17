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

  protected String tipusConsentiment;
  protected String consentiment;
  protected String enllazConsentiment;
  protected String notes;
  protected String caduca;
  protected String fechaCaduca;

  
  public String getTipusConsentiment() {
    return tipusConsentiment;
}

public void setTipusConsentiment(String tipusConsentiment) {
    this.tipusConsentiment = tipusConsentiment;
}

public String getConsentiment() {
    return consentiment;
}

public void setConsentiment(String consentiment) {
    this.consentiment = consentiment;
}

public String getEnllazConsentiment() {
    return enllazConsentiment;
}

public void setEnllazConsentiment(String enllazConsentiment) {
    this.enllazConsentiment = enllazConsentiment;
}

public String getNotes() {
    return notes;
}

public void setNotes(String notes) {
    this.notes = notes;
}

public String getCaduca() {
    return caduca;
}

public void setCaduca(String caduca) {
    this.caduca = caduca;
}

public String getFechaCaduca() {
    return fechaCaduca;
}

public void setFechaCaduca(String fechaCaduca) {
    this.fechaCaduca = fechaCaduca;
}

protected List<NormativaInfo> normatives = new ArrayList<NormativaInfo>();

  /**
   * @param nom
   */
  public ServeiInfo(String nom, String cedent) {
    super();
    this.nom = nom;
    this.cedent = cedent;
  }

  public ServeiInfo(String nom, String cedent, String tipusConsentiment, String consentiment, String enllazConsentiment,
        String notes, String caduca, String fechaCaduca) {
    super();
    this.nom = nom;
    this.cedent = cedent;
    this.tipusConsentiment = tipusConsentiment;
    this.consentiment = consentiment;
    this.enllazConsentiment = enllazConsentiment;
    this.notes = notes;
    this.caduca = caduca;
    this.fechaCaduca = fechaCaduca;
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
