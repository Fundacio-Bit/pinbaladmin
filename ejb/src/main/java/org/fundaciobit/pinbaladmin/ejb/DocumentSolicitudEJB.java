
package org.fundaciobit.pinbaladmin.ejb;

import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.pinbaladmin.jpa.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.jpa.DocumentSolicitudJPAManager;

@Stateless(name = "DocumentSolicitudEJB")
@SecurityDomain("seycon")
public class DocumentSolicitudEJB extends DocumentSolicitudJPAManager implements DocumentSolicitudLocal {

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public void delete(DocumentSolicitud instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public DocumentSolicitud create(DocumentSolicitud instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
	public DocumentSolicitud update(DocumentSolicitud instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({"PAD_ADMIN","PAD_USER"})
  public DocumentSolicitudJPA findByPrimaryKey(Long _ID_) {
    return (DocumentSolicitudJPA)super.findByPrimaryKey(_ID_);
  }

}
