
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.GrupEntitat;
import org.fundaciobit.pinbaladmin.jpa.GrupEntitatJPA;
import org.fundaciobit.pinbaladmin.jpa.GrupEntitatJPAManager;

@Stateless(name = "GrupEntitatEJB")
@SecurityDomain("seycon")
public class GrupEntitatEJB extends GrupEntitatJPAManager implements GrupEntitatLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(GrupEntitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public GrupEntitat create(GrupEntitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public GrupEntitat update(GrupEntitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public GrupEntitatJPA findByPrimaryKey(Long _ID_) {
    return (GrupEntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
