
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.EstatServei;
import org.fundaciobit.pinbaladmin.jpa.EstatServeiJPA;
import org.fundaciobit.pinbaladmin.jpa.EstatServeiJPAManager;

@Stateless(name = "EstatServeiEJB")
@SecurityDomain("seycon")
public class EstatServeiEJB extends EstatServeiJPAManager implements EstatServeiLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(EstatServei instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public EstatServei create(EstatServei instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public EstatServei update(EstatServei instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public EstatServeiJPA findByPrimaryKey(Long _ID_) {
    return (EstatServeiJPA)super.findByPrimaryKey(_ID_);
  }

}
