
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.FitxerJPA;
import org.fundaciobit.pinbaladmin.model.dao.IFitxerManager;

@Local
public interface FitxerLocal extends IFitxerManager {

 public static final String JNDI_NAME = "pinbaladmin/FitxerEJB/local";
  public FitxerJPA findByPrimaryKey(Long _ID_);
}
