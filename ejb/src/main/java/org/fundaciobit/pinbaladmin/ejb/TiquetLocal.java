
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.TiquetJPA;
import org.fundaciobit.pinbaladmin.model.dao.ITiquetManager;

@Local
public interface TiquetLocal extends ITiquetManager {

 public static final String JNDI_NAME = "pinbaladmin/TiquetEJB/local";
  public TiquetJPA findByPrimaryKey(Long _ID_);
}
