
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.IdiomaJPA;
import org.fundaciobit.pinbaladmin.model.dao.IIdiomaManager;

@Local
public interface IdiomaLocal extends IIdiomaManager {

 public static final String JNDI_NAME = "pinbaladmin/IdiomaEJB/local";
  public IdiomaJPA findByPrimaryKey(String _ID_);
}
