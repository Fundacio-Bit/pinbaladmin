
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager;

@Local
public interface IncidenciaTecnicaLocal extends IIncidenciaTecnicaManager {

 public static final String JNDI_NAME = "pinbaladmin/IncidenciaTecnicaEJB/local";
  public IncidenciaTecnicaJPA findByPrimaryKey(Long _ID_);
}
