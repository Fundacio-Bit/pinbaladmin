
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.EstatSolicitud;
import org.fundaciobit.pinbaladmin.jpa.EstatSolicitudJPA;
import org.fundaciobit.pinbaladmin.jpa.EstatSolicitudJPAManager;

@Stateless(name = "EstatSolicitudEJB")
@SecurityDomain("seycon")
public class EstatSolicitudEJB extends EstatSolicitudJPAManager implements EstatSolicitudLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(EstatSolicitud instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public EstatSolicitud create(EstatSolicitud instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public EstatSolicitud update(EstatSolicitud instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public EstatSolicitudJPA findByPrimaryKey(Long _ID_) {
    return (EstatSolicitudJPA)super.findByPrimaryKey(_ID_);
  }

}
