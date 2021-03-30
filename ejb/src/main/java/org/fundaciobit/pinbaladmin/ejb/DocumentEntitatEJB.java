
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.DocumentEntitat;
import org.fundaciobit.pinbaladmin.jpa.DocumentEntitatJPA;
import org.fundaciobit.pinbaladmin.jpa.DocumentEntitatJPAManager;

@Stateless(name = "DocumentEntitatEJB")
@SecurityDomain("seycon")
public class DocumentEntitatEJB extends DocumentEntitatJPAManager implements DocumentEntitatLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(DocumentEntitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public DocumentEntitat create(DocumentEntitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public DocumentEntitat update(DocumentEntitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public DocumentEntitatJPA findByPrimaryKey(Long _ID_) {
    return (DocumentEntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
