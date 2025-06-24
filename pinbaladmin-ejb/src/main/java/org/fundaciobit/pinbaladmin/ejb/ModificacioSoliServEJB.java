
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.entity.ModificacioSoliServ;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSoliServJPA;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSoliServJPAManager;

import org.fundaciobit.pinbaladmin.commons.utils.Constants;

@Stateless
public class ModificacioSoliServEJB extends ModificacioSoliServJPAManager implements ModificacioSoliServService {

    @javax.annotation.Resource
    protected javax.transaction.TransactionSynchronizationRegistry __tsRegistry;

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(ModificacioSoliServ instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public ModificacioSoliServ create(ModificacioSoliServ instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public ModificacioSoliServ update(ModificacioSoliServ instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(ModificacioSoliServ instance,  FitxerService fitxerEjb)
            throws I18NException {

        java.util.ArrayList<Long> fitxers = new java.util.ArrayList<Long>();
        fitxers.add(instance.getFitxerNorma1ID());
        fitxers.add(instance.getFitxerNorma2ID());
        fitxers.add(instance.getFitxerNorma3ID());

        this.delete(instance);

        java.util.Set<Long> fitxersEsborrar = new java.util.HashSet<Long>();

        // Borram fitxers a BD
        for (Long f : fitxers) {
            if (f != null) {
                fitxerEjb.delete(f);
                fitxersEsborrar.add(f);
            }
        }

        // Borram fitxers fisic
        __tsRegistry.registerInterposedSynchronization(new org.fundaciobit.pinbaladmin.ejb.utils.CleanFilesSynchronization(fitxersEsborrar));
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public ModificacioSoliServJPA findByPrimaryKey(Long _ID_) {
        return (ModificacioSoliServJPA)super.findByPrimaryKey(_ID_);
    }

}
