
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.EstatSolicitudServei;
import org.fundaciobit.pinbaladmin.jpa.EstatSolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.jpa.EstatSolicitudServeiJPAManager;

@Stateless(name = "EstatSolicitudServeiEJB")
@SecurityDomain("seycon")
public class EstatSolicitudServeiEJB extends EstatSolicitudServeiJPAManager implements EstatSolicitudServeiLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(EstatSolicitudServei instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public EstatSolicitudServei create(EstatSolicitudServei instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public EstatSolicitudServei update(EstatSolicitudServei instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public EstatSolicitudServeiJPA findByPrimaryKey(Long _ID_) {
    return (EstatSolicitudServeiJPA)super.findByPrimaryKey(_ID_);
  }

}
