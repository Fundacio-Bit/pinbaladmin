package org.fundaciobit.pinbaladmin.logic.dto;

public class SolicitudDTO {

    private final Long solicitudID;
    private final String procedimentCodi;
    private final String procedimentNom;
    private final Long departamentID;

    public SolicitudDTO(Long solicitudID, String procedimentCodi, String procedimentNom, Long departamentID) {
        this.solicitudID = solicitudID;
        this.procedimentCodi = procedimentCodi;
        this.procedimentNom = procedimentNom;
        this.departamentID = departamentID;
    }

    public Long getSolicitudID() {
        return solicitudID;
    }

    public String getProcedimentCodi() {
        return procedimentCodi;
    }

    public String getProcedimentNom() {
        return procedimentNom;
    }

    public Long getDepartamentID() {
        return departamentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SolicitudDTO)) return false;
        SolicitudDTO that = (SolicitudDTO) o;
        return solicitudID.equals(that.solicitudID);
    }

    @Override
    public int hashCode() {
        return solicitudID.hashCode();
    }

    @Override
    public String toString() {
        return "SolicitudDTO{" +
                "solicitudID=" + solicitudID +
                ", procedimentCodi='" + procedimentCodi + '\'' +
                ", procedimentNom='" + procedimentNom + '\'' +
                ", departamentID=" + departamentID +
                '}';
    }
}
