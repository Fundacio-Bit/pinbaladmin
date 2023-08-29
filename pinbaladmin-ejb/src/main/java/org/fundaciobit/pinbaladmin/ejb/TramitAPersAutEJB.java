
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPAManager;

import org.fundaciobit.pinbaladmin.commons.utils.Constants;

@Stateless
public class TramitAPersAutEJB extends TramitAPersAutJPAManager implements TramitAPersAutService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(TramitAPersAut instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public TramitAPersAut create(TramitAPersAut instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public TramitAPersAut update(TramitAPersAut instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(TramitAPersAut instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public TramitAPersAutJPA findByPrimaryKey(Long _ID_) {
        return (TramitAPersAutJPA)super.findByPrimaryKey(_ID_);
    }

}
