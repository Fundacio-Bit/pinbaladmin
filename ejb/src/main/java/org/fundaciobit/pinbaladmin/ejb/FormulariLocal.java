
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.FormulariJPA;
import org.fundaciobit.pinbaladmin.model.dao.IFormulariManager;

@Local
public interface FormulariLocal extends IFormulariManager {

 public static final String JNDI_NAME = "pinbaladmin/FormulariEJB/local";
  public FormulariJPA findByPrimaryKey(Long _ID_);
}
