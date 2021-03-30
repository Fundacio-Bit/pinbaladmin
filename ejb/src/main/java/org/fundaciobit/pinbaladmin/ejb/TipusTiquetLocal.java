
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.TipusTiquetJPA;
import org.fundaciobit.pinbaladmin.model.dao.ITipusTiquetManager;

@Local
public interface TipusTiquetLocal extends ITipusTiquetManager {

 public static final String JNDI_NAME = "pinbaladmin/TipusTiquetEJB/local";
  public TipusTiquetJPA findByPrimaryKey(Long _ID_);
}
