
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.EntitatServei;
import org.fundaciobit.pinbaladmin.jpa.EntitatServeiJPA;
import org.fundaciobit.pinbaladmin.jpa.EntitatServeiJPAManager;

@Stateless(name = "EntitatServeiEJB")
@SecurityDomain("seycon")
public class EntitatServeiEJB extends EntitatServeiJPAManager implements EntitatServeiLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(EntitatServei instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public EntitatServei create(EntitatServei instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public EntitatServei update(EntitatServei instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public EntitatServeiJPA findByPrimaryKey(Long _ID_) {
    return (EntitatServeiJPA)super.findByPrimaryKey(_ID_);
  }

}
