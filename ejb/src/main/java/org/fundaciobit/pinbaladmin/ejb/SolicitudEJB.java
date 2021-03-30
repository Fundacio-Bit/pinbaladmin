
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.jpa.SolicitudJPA;
import org.fundaciobit.pinbaladmin.jpa.SolicitudJPAManager;

@Stateless(name = "SolicitudEJB")
@SecurityDomain("seycon")
public class SolicitudEJB extends SolicitudJPAManager implements SolicitudLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(Solicitud instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Solicitud create(Solicitud instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Solicitud update(Solicitud instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public SolicitudJPA findByPrimaryKey(Long _ID_) {
    return (SolicitudJPA)super.findByPrimaryKey(_ID_);
  }

}
