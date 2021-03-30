
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.Formulari;
import org.fundaciobit.pinbaladmin.jpa.FormulariJPA;
import org.fundaciobit.pinbaladmin.jpa.FormulariJPAManager;

@Stateless(name = "FormulariEJB")
@SecurityDomain("seycon")
public class FormulariEJB extends FormulariJPAManager implements FormulariLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(Formulari instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Formulari create(Formulari instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Formulari update(Formulari instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public FormulariJPA findByPrimaryKey(Long _ID_) {
    return (FormulariJPA)super.findByPrimaryKey(_ID_);
  }

}
