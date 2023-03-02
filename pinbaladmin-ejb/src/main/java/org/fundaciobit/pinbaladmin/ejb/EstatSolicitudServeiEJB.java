
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.EstatSolicitudServei;
import org.fundaciobit.pinbaladmin.persistence.EstatSolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.EstatSolicitudServeiJPAManager;

import org.fundaciobit.pinbaladmin.commons.utils.Constants;

@Stateless
public class EstatSolicitudServeiEJB extends EstatSolicitudServeiJPAManager implements EstatSolicitudServeiService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(EstatSolicitudServei instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public EstatSolicitudServei create(EstatSolicitudServei instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public EstatSolicitudServei update(EstatSolicitudServei instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(EstatSolicitudServei instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public EstatSolicitudServeiJPA findByPrimaryKey(Long _ID_) {
        return (EstatSolicitudServeiJPA)super.findByPrimaryKey(_ID_);
    }

}
