
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.EstatTiquet;
import org.fundaciobit.pinbaladmin.jpa.EstatTiquetJPA;
import org.fundaciobit.pinbaladmin.jpa.EstatTiquetJPAManager;

@Stateless(name = "EstatTiquetEJB")
@SecurityDomain("seycon")
public class EstatTiquetEJB extends EstatTiquetJPAManager implements EstatTiquetLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(EstatTiquet instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public EstatTiquet create(EstatTiquet instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public EstatTiquet update(EstatTiquet instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public EstatTiquetJPA findByPrimaryKey(Long _ID_) {
    return (EstatTiquetJPA)super.findByPrimaryKey(_ID_);
  }

}
