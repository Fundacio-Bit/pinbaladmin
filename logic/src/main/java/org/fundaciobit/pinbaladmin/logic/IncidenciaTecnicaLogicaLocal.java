package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaLocal;
import org.fundaciobit.pinbaladmin.jpa.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface IncidenciaTecnicaLogicaLocal extends IncidenciaTecnicaLocal {

  String JNDI_NAME = "pinbaladmin/IncidenciaTecnicaLogicaEJB/local";

  @Override
  @PermitAll
  public IncidenciaTecnicaJPA findByPrimaryKey(Long _ID_);

  public IncidenciaTecnica createFromEmail(EmailMessageInfo emi, String creador)
      throws I18NException;

  public void deleteFull(Long _ID) throws I18NException;

}
