package org.fundaciobit.pinbaladmin.logic;

import java.util.Set;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface SolicitudServeiLogicaService extends SolicitudServeiService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/SolicitudServeiLogicaEJB!org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService";

    Set<Long> deleteFull(Long serveiId, Long solicitudId, boolean deleteFiles) throws I18NException;
}
