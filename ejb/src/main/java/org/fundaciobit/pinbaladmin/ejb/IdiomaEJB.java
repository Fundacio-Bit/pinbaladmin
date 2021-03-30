
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.Idioma;
import org.fundaciobit.pinbaladmin.jpa.IdiomaJPA;
import org.fundaciobit.pinbaladmin.jpa.IdiomaJPAManager;

@Stateless(name = "IdiomaEJB")
@SecurityDomain("seycon")
public class IdiomaEJB extends IdiomaJPAManager implements IdiomaLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(Idioma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Idioma create(Idioma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Idioma update(Idioma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public IdiomaJPA findByPrimaryKey(String _ID_) {
    return (IdiomaJPA)super.findByPrimaryKey(_ID_);
  }

}
