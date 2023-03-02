package org.fundaciobit.pinbaladmin.logic;

import java.util.Set;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface DocumentSolicitudLogicaService extends DocumentSolicitudService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/DocumentSolicitudLogicaEJB!org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService";

    Set<Long> deleteFull(Long documentId, Long solicitudId, boolean deleteFiles) throws I18NException;
}
