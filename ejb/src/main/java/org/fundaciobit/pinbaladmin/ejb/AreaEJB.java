
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.Area;
import org.fundaciobit.pinbaladmin.jpa.AreaJPA;
import org.fundaciobit.pinbaladmin.jpa.AreaJPAManager;

@Stateless(name = "AreaEJB")
@SecurityDomain("seycon")
public class AreaEJB extends AreaJPAManager implements AreaLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(Area instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Area create(Area instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Area update(Area instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public AreaJPA findByPrimaryKey(Long _ID_) {
    return (AreaJPA)super.findByPrimaryKey(_ID_);
  }

}
