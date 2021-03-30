
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.CampFormulari;
import org.fundaciobit.pinbaladmin.jpa.CampFormulariJPA;
import org.fundaciobit.pinbaladmin.jpa.CampFormulariJPAManager;

@Stateless(name = "CampFormulariEJB")
@SecurityDomain("seycon")
public class CampFormulariEJB extends CampFormulariJPAManager implements CampFormulariLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(CampFormulari instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public CampFormulari create(CampFormulari instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public CampFormulari update(CampFormulari instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public CampFormulariJPA findByPrimaryKey(Long _ID_) {
    return (CampFormulariJPA)super.findByPrimaryKey(_ID_);
  }

}
