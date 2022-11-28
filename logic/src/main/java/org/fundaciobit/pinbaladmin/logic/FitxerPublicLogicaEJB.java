package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.FitxerEJB;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 */
@Stateless(name = "FitxerPublicLogicaEJB")
@SecurityDomain("seycon")
public class FitxerPublicLogicaEJB extends FitxerEJB implements FitxerPublicLogicaLocal {

  @Override
  @PermitAll
  public Fitxer create(Fitxer bean) throws I18NException {
    return super.create(bean);
  }
  
  
  @Override
  @PermitAll
  public Fitxer update(Fitxer instance) throws I18NException {
     return super.update(instance);
  }

}