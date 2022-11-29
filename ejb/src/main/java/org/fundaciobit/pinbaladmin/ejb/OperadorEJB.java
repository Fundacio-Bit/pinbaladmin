
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.Operador;
import org.fundaciobit.pinbaladmin.jpa.OperadorJPA;
import org.fundaciobit.pinbaladmin.jpa.OperadorJPAManager;

@Stateless(name = "OperadorEJB")
@SecurityDomain("seycon")
public class OperadorEJB extends OperadorJPAManager implements OperadorLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(Operador instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Operador create(Operador instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Operador update(Operador instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public OperadorJPA findByPrimaryKey(Long _ID_) {
    return (OperadorJPA)super.findByPrimaryKey(_ID_);
  }

}
