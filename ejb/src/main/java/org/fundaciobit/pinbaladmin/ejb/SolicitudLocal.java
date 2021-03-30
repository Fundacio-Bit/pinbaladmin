
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.SolicitudJPA;
import org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager;

@Local
public interface SolicitudLocal extends ISolicitudManager {

 public static final String JNDI_NAME = "pinbaladmin/SolicitudEJB/local";
  public SolicitudJPA findByPrimaryKey(Long _ID_);
}
