package org.fundaciobit.pinbaladmin.logic;

import java.util.Set;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudLocal;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface DocumentSolicitudLogicaLocal extends DocumentSolicitudLocal {

  String JNDI_NAME = "pinbaladmin/DocumentSolicitudLogicaEJB/local";

  Set<Long> deleteFull(Long documentId, Long solicitudId, boolean deleteFiles) throws I18NException;
}

