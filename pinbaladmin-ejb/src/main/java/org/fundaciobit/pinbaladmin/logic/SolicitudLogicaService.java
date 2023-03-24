package org.fundaciobit.pinbaladmin.logic;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.SolicitudService;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.logic.dto.SolicitudDTO;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailAttachmentInfo;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface SolicitudLogicaService extends SolicitudService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/SolicitudLogicaEJB!org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService";

    Map<Long, List<SolicitudDTO>> getSolicitudsByServei(Collection<Long> serveiIds);

    Set<Long> deleteFull(Long solicitudId, boolean deleteFiles) throws I18NException;

    @Override
    @PermitAll
    public SolicitudJPA findByPrimaryKey(Long _ID_);

    public SolicitudJPA findByPrimaryKeyFull(Long _ID_) throws I18NException;

    @PermitAll
    public void updateCAID(Long soliID, String incidencia, String seguiment) throws I18NException;

    /**
     * 
     * @param solicituds
     * @param xlsx
     * @param attachs
     * @throws I18NException
     */
    public void crearSolicituds(List<SolicitudJPA> solicituds, EmailAttachmentInfo xlsx,
            List<EmailAttachmentInfo> attachs) throws I18NException;

}
