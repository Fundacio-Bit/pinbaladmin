
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.DocumentCedent;
import org.fundaciobit.pinbaladmin.jpa.DocumentCedentJPA;
import org.fundaciobit.pinbaladmin.jpa.DocumentCedentJPAManager;

@Stateless(name = "DocumentCedentEJB")
@SecurityDomain("seycon")
public class DocumentCedentEJB extends DocumentCedentJPAManager implements DocumentCedentLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(DocumentCedent instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public DocumentCedent create(DocumentCedent instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public DocumentCedent update(DocumentCedent instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public DocumentCedentJPA findByPrimaryKey(Long _ID_) {
    return (DocumentCedentJPA)super.findByPrimaryKey(_ID_);
  }

}
