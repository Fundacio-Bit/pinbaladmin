
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.jpa.ServeiJPA;
import org.fundaciobit.pinbaladmin.jpa.ServeiJPAManager;

@Stateless(name = "ServeiEJB")
@SecurityDomain("seycon")
public class ServeiEJB extends ServeiJPAManager implements ServeiLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(Servei instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Servei create(Servei instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Servei update(Servei instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public ServeiJPA findByPrimaryKey(Long _ID_) {
    return (ServeiJPA)super.findByPrimaryKey(_ID_);
  }

}
