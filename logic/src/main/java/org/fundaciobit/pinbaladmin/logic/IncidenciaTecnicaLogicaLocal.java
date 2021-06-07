package org.fundaciobit.pinbaladmin.logic;



import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaLocal;
import org.fundaciobit.pinbaladmin.jpa.IncidenciaTecnicaJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface IncidenciaTecnicaLogicaLocal extends IncidenciaTecnicaLocal {

  String JNDI_NAME = "pinbaladmin/IncidenciaTecnicaLogicaEJB/local";

  
  @Override
  @PermitAll
  public IncidenciaTecnicaJPA findByPrimaryKey(Long _ID_);
  
}
