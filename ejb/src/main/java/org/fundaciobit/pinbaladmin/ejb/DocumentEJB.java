
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.jpa.DocumentJPA;
import org.fundaciobit.pinbaladmin.jpa.DocumentJPAManager;

@Stateless(name = "DocumentEJB")
@SecurityDomain("seycon")
public class DocumentEJB extends DocumentJPAManager implements DocumentLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(Document instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Document create(Document instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public Document update(Document instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public DocumentJPA findByPrimaryKey(Long _ID_) {
    return (DocumentJPA)super.findByPrimaryKey(_ID_);
  }

}
