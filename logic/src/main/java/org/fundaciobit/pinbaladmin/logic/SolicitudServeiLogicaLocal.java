package org.fundaciobit.pinbaladmin.logic;

import java.util.Set;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.SolicitudServeiLocal;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface SolicitudServeiLogicaLocal extends SolicitudServeiLocal {

  String JNDI_NAME = "pinbaladmin/SolicitudServeiLogicaEJB/local";

  Set<Long> deleteFull(Long serveiId, Long solicitudId, boolean deleteFiles) throws I18NException;
}

