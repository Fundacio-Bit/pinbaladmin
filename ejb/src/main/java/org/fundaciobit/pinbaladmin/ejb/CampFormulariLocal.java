
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.CampFormulariJPA;
import org.fundaciobit.pinbaladmin.model.dao.ICampFormulariManager;

@Local
public interface CampFormulariLocal extends ICampFormulariManager {

 public static final String JNDI_NAME = "pinbaladmin/CampFormulariEJB/local";
  public CampFormulariJPA findByPrimaryKey(Long _ID_);
}
