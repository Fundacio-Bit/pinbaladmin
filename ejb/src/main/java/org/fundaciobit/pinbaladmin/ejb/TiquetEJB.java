
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.Tiquet;
import org.fundaciobit.pinbaladmin.jpa.TiquetJPA;
import org.fundaciobit.pinbaladmin.jpa.TiquetJPAManager;

@Stateless(name = "TiquetEJB")
@SecurityDomain("seycon")
public class TiquetEJB extends TiquetJPAManager implements TiquetLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(Tiquet instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Tiquet create(Tiquet instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Tiquet update(Tiquet instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public TiquetJPA findByPrimaryKey(Long _ID_) {
    return (TiquetJPA)super.findByPrimaryKey(_ID_);
  }

}
