package org.fundaciobit.pinbaladmin.logic.dto;

public class SolicitudDTO {

    private final Long solicitudID;
    private final String procedimentCodi;
    private final String procedimentNom;
    private final Long organGestor;

    public SolicitudDTO(Long solicitudID, String procedimentCodi, String procedimentNom, Long organGestor) {
        this.solicitudID = solicitudID;
        this.procedimentCodi = procedimentCodi;
        this.procedimentNom = procedimentNom;
        this.organGestor = organGestor;
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

	public Long getOrganGestor() {
		return organGestor;
	}
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SolicitudDTO))
            return false;
        SolicitudDTO that = (SolicitudDTO) o;
        return solicitudID.equals(that.solicitudID);
    }

    @Override
    public int hashCode() {
        return solicitudID.hashCode();
    }

    @Override
    public String toString() {
        return "SolicitudDTO{" + "solicitudID=" + solicitudID + ", procedimentCodi='" + procedimentCodi + '\''
                + ", procedimentNom='" + procedimentNom + '\'' + ", organGestor='" + organGestor + '}';
    }
}
