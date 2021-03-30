
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.jpa.SolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.jpa.SolicitudServeiJPAManager;

@Stateless(name = "SolicitudServeiEJB")
@SecurityDomain("seycon")
public class SolicitudServeiEJB extends SolicitudServeiJPAManager implements SolicitudServeiLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(SolicitudServei instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public SolicitudServei create(SolicitudServei instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public SolicitudServei update(SolicitudServei instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public SolicitudServeiJPA findByPrimaryKey(Long _ID_) {
    return (SolicitudServeiJPA)super.findByPrimaryKey(_ID_);
  }

}
