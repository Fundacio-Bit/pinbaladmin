
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.Departament;
import org.fundaciobit.pinbaladmin.jpa.DepartamentJPA;
import org.fundaciobit.pinbaladmin.jpa.DepartamentJPAManager;

@Stateless(name = "DepartamentEJB")
@SecurityDomain("seycon")
public class DepartamentEJB extends DepartamentJPAManager implements DepartamentLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(Departament instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Departament create(Departament instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Departament update(Departament instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public DepartamentJPA findByPrimaryKey(Long _ID_) {
    return (DepartamentJPA)super.findByPrimaryKey(_ID_);
  }

}
