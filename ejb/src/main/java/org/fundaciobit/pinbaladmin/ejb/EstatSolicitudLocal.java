
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.EstatSolicitudJPA;
import org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudManager;

@Local
public interface EstatSolicitudLocal extends IEstatSolicitudManager {

 public static final String JNDI_NAME = "pinbaladmin/EstatSolicitudEJB/local";
  public EstatSolicitudJPA findByPrimaryKey(Long _ID_);
}
