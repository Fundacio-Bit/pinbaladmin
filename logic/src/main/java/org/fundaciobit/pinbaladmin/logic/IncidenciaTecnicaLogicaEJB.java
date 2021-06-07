package org.fundaciobit.pinbaladmin.logic;


import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaEJB;
import org.fundaciobit.pinbaladmin.jpa.IncidenciaTecnicaJPA;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 */
@Stateless(name = "IncidenciaTecnicaLogicaEJB")
@SecurityDomain("seycon")
public class IncidenciaTecnicaLogicaEJB extends IncidenciaTecnicaEJB implements IncidenciaTecnicaLogicaLocal {

  
  @Override
  @PermitAll
  public IncidenciaTecnicaJPA findByPrimaryKey(Long _ID_) {
     return super.findByPrimaryKey(_ID_);
  }

}