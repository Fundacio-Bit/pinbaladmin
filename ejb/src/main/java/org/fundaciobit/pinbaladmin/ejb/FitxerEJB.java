
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.jpa.FitxerJPA;
import org.fundaciobit.pinbaladmin.jpa.FitxerJPAManager;

@Stateless(name = "FitxerEJB")
@SecurityDomain("seycon")
public class FitxerEJB extends FitxerJPAManager implements FitxerLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(Fitxer instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Fitxer create(Fitxer instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Fitxer update(Fitxer instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public FitxerJPA findByPrimaryKey(Long _ID_) {
    return (FitxerJPA)super.findByPrimaryKey(_ID_);
  }

}
