
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.EstatTiquetJPA;
import org.fundaciobit.pinbaladmin.model.dao.IEstatTiquetManager;

@Local
public interface EstatTiquetLocal extends IEstatTiquetManager {

 public static final String JNDI_NAME = "pinbaladmin/EstatTiquetEJB/local";
  public EstatTiquetJPA findByPrimaryKey(Long _ID_);
}
