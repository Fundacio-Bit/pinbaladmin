
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.CampSolicitudJPA;
import org.fundaciobit.pinbaladmin.model.dao.ICampSolicitudManager;

@Local
public interface CampSolicitudLocal extends ICampSolicitudManager {

 public static final String JNDI_NAME = "pinbaladmin/CampSolicitudEJB/local";
  public CampSolicitudJPA findByPrimaryKey(Long _ID_);
}
