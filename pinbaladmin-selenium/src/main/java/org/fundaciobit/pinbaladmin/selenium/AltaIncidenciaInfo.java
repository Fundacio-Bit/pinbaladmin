package org.fundaciobit.pinbaladmin.selenium;

import java.io.File;

/**
 * 
 * @author anadal
 *
 */
public class AltaIncidenciaInfo {

    public String organisme;
    public String nom;
    public String llinatge1;
    public String llinatge2;
    public String telefon;
    public String email;
    public String asunto;
    public String tipus;
    public String entorn;
    public File fitxer;
    public String consulta;

    public AltaIncidenciaInfo(String organisme, String nom, String llinatge1, String llinatge2, String telefon,
            String email, String asunto, String tipus, String entorn, File fitxer, String consulta) {
        super();
        this.organisme = organisme;
        this.nom = nom;
        this.llinatge1 = llinatge1;
        this.llinatge2 = llinatge2;
        this.telefon = telefon;
        this.email = email;
        this.asunto = asunto;
        this.tipus = tipus;
        this.entorn = entorn;
        this.fitxer = fitxer;
        this.consulta = consulta;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLlinatge1() {
        return llinatge1;
    }

    public void setLlinatge1(String llinatge1) {
        this.llinatge1 = llinatge1;
    }

    public String getLlinatge2() {
        return llinatge2;
    }

    public void setLlinatge2(String llinatge2) {
        this.llinatge2 = llinatge2;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getEntorn() {
        return entorn;
    }

    public void setEntorn(String entorn) {
        this.entorn = entorn;
    }

    public File getFitxer() {
        return fitxer;
    }

    public void setFitxer(File fitxer) {
        this.fitxer = fitxer;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

}
