
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.TipusTiquet;
import org.fundaciobit.pinbaladmin.jpa.TipusTiquetJPA;
import org.fundaciobit.pinbaladmin.jpa.TipusTiquetJPAManager;

@Stateless(name = "TipusTiquetEJB")
@SecurityDomain("seycon")
public class TipusTiquetEJB extends TipusTiquetJPAManager implements TipusTiquetLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(TipusTiquet instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public TipusTiquet create(TipusTiquet instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public TipusTiquet update(TipusTiquet instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public TipusTiquetJPA findByPrimaryKey(Long _ID_) {
    return (TipusTiquetJPA)super.findByPrimaryKey(_ID_);
  }

}
