
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.SolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager;

@Local
public interface SolicitudServeiLocal extends ISolicitudServeiManager {

 public static final String JNDI_NAME = "pinbaladmin/SolicitudServeiEJB/local";
  public SolicitudServeiJPA findByPrimaryKey(Long _ID_);
}
