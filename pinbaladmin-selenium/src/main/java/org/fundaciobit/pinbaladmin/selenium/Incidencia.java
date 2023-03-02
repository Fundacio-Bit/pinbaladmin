package org.fundaciobit.pinbaladmin.selenium;

/**
 * 
 * @author anadal
 *
 */
public class Incidencia {

    protected String email;

    protected String incidencia;

    protected String seguiment;

    public Incidencia(String email, String incidencia, String seguiment) {
        super();
        this.email = email;
        this.incidencia = incidencia;
        this.seguiment = seguiment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }

    public String getSeguiment() {
        return seguiment;
    }

    public void setSeguiment(String seguiment) {
        this.seguiment = seguiment;
    }

}
