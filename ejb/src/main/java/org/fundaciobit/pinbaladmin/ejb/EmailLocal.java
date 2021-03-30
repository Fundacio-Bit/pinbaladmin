
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.jpa.EmailJPA;
import org.fundaciobit.pinbaladmin.model.dao.IEmailManager;

@Local
public interface EmailLocal extends IEmailManager {

 public static final String JNDI_NAME = "pinbaladmin/EmailEJB/local";
  public EmailJPA findByPrimaryKey(Long _ID_);
}
