
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.CampSolicitud;
import org.fundaciobit.pinbaladmin.jpa.CampSolicitudJPA;
import org.fundaciobit.pinbaladmin.jpa.CampSolicitudJPAManager;

@Stateless(name = "CampSolicitudEJB")
@SecurityDomain("seycon")
public class CampSolicitudEJB extends CampSolicitudJPAManager implements CampSolicitudLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(CampSolicitud instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public CampSolicitud create(CampSolicitud instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public CampSolicitud update(CampSolicitud instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public CampSolicitudJPA findByPrimaryKey(Long _ID_) {
    return (CampSolicitudJPA)super.findByPrimaryKey(_ID_);
  }

}
