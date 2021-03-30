
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.GrupEntitatCedent;
import org.fundaciobit.pinbaladmin.jpa.GrupEntitatCedentJPA;
import org.fundaciobit.pinbaladmin.jpa.GrupEntitatCedentJPAManager;

@Stateless(name = "GrupEntitatCedentEJB")
@SecurityDomain("seycon")
public class GrupEntitatCedentEJB extends GrupEntitatCedentJPAManager implements GrupEntitatCedentLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(GrupEntitatCedent instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public GrupEntitatCedent create(GrupEntitatCedent instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public GrupEntitatCedent update(GrupEntitatCedent instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public GrupEntitatCedentJPA findByPrimaryKey(Long _ID_) {
    return (GrupEntitatCedentJPA)super.findByPrimaryKey(_ID_);
  }

}
