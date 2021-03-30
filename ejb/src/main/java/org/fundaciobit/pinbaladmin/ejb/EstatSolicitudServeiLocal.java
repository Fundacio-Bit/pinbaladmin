
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.EstatSolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudServeiManager;

@Local
public interface EstatSolicitudServeiLocal extends IEstatSolicitudServeiManager {

 public static final String JNDI_NAME = "pinbaladmin/EstatSolicitudServeiEJB/local";
  public EstatSolicitudServeiJPA findByPrimaryKey(Long _ID_);
}
