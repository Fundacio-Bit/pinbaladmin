
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.jpa.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.jpa.IncidenciaTecnicaJPAManager;

@Stateless(name = "IncidenciaTecnicaEJB")
@SecurityDomain("seycon")
public class IncidenciaTecnicaEJB extends IncidenciaTecnicaJPAManager implements IncidenciaTecnicaLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(IncidenciaTecnica instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public IncidenciaTecnica create(IncidenciaTecnica instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public IncidenciaTecnica update(IncidenciaTecnica instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public IncidenciaTecnicaJPA findByPrimaryKey(Long _ID_) {
    return (IncidenciaTecnicaJPA)super.findByPrimaryKey(_ID_);
  }

}
