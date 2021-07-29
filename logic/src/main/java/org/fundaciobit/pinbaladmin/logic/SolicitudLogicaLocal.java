package org.fundaciobit.pinbaladmin.logic;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.SolicitudLocal;
import org.fundaciobit.pinbaladmin.jpa.SolicitudJPA;
import org.fundaciobit.pinbaladmin.logic.dto.SolicitudDTO;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface SolicitudLogicaLocal extends SolicitudLocal {

  String JNDI_NAME = "pinbaladmin/SolicitudLogicaEJB/local";

  Map<Long, List<SolicitudDTO>> getSolicitudsByServei(Collection<Long> serveiIds);

  Set<Long> deleteFull(Long solicitudId, boolean deleteFiles) throws I18NException;
  
  @Override
  @PermitAll
  public SolicitudJPA findByPrimaryKey(Long _ID_);
  

  public SolicitudJPA findByPrimaryKeyFull(Long _ID_) throws I18NException;
  

  @PermitAll
  public void updateCAID(Long soliID, String incidencia, String seguiment) throws I18NException;
  
}
