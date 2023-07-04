package org.fundaciobit.pinbaladmin.apiclientpeticions;

/**
 * 
 * @author anadal
 *
 */
public class PinbalAdminSolicitudsConfiguration {

    String urlBase;
    String username;
    String password;

    String idSolicitud;

    String codigoCertificado;

    String finalidad;
    String identificadorSolicitante;
    String unidadTramitadora;

    String codProcedimiento;

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getCodigoCertificado() {
        return codigoCertificado;
    }

    public void setCodigoCertificado(String codigoCertificado) {
        this.codigoCertificado = codigoCertificado;
    }

    public String getFinalidad() {
        return finalidad;
    }

    public void setFinalidad(String finalidad) {
        this.finalidad = finalidad;
    }

    public String getIdentificadorSolicitante() {
        return identificadorSolicitante;
    }

    public void setIdentificadorSolicitante(String identificadorSolicitante) {
        this.identificadorSolicitante = identificadorSolicitante;
    }

    public String getUnidadTramitadora() {
        return unidadTramitadora;
    }

    public void setUnidadTramitadora(String unidadTramitadora) {
        this.unidadTramitadora = unidadTramitadora;
    }

    public String getCodProcedimiento() {
        return codProcedimiento;
    }

    public void setCodProcedimiento(String codProcedimiento) {
        this.codProcedimiento = codProcedimiento;
    }

}
