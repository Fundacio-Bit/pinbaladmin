
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;
import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPAManager;

import org.fundaciobit.pinbaladmin.commons.utils.Constants;

@Stateless
public class TramitBDadesSoliEJB extends TramitBDadesSoliJPAManager implements TramitBDadesSoliService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(TramitBDadesSoli instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public TramitBDadesSoli create(TramitBDadesSoli instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public TramitBDadesSoli update(TramitBDadesSoli instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(TramitBDadesSoli instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public TramitBDadesSoliJPA findByPrimaryKey(Long _ID_) {
        return (TramitBDadesSoliJPA)super.findByPrimaryKey(_ID_);
    }

}
