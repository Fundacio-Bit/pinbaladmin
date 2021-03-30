
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.pinbaladmin.jpa.EntitatJPA;
import org.fundaciobit.pinbaladmin.jpa.EntitatJPAManager;

@Stateless(name = "EntitatEJB")
@SecurityDomain("seycon")
public class EntitatEJB extends EntitatJPAManager implements EntitatLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(Entitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Entitat create(Entitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Entitat update(Entitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public EntitatJPA findByPrimaryKey(Long _ID_) {
    return (EntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
