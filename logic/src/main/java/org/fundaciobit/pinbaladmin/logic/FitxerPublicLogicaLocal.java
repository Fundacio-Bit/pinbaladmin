package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.FitxerLocal;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface FitxerPublicLogicaLocal extends FitxerLocal {

  String JNDI_NAME = "pinbaladmin/FitxerPublicLogicaEJB/local";

  @Override
  @PermitAll
  public Fitxer create(Fitxer bean) throws I18NException;

}
