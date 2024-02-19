package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitJConsentEJB;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;
import org.fundaciobit.pinbaladmin.persistence.TramitJConsentJPA;

/**
 * 
 * @author anadal
 */
@Stateless(name = "TramitJConsentLogicaEJB")
public class TramitJConsentLogicaEJB extends TramitJConsentEJB implements TramitJConsentLogicaService {

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @Override
    @PermitAll
    public TramitJConsentJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public TramitJConsent create(TramitJConsent instance) throws I18NException {
        log.info("TramitJConsent::TramitID: " + instance.getTramitid());
        return super.create(instance);
    }

    @Override
    @PermitAll
    public TramitJConsent update(TramitJConsent instance) throws I18NException {
        return super.update(instance);
    }

    @Override
    public void deleteFull(Long tramitid) throws I18NException {

        List<TramitAPersAut> list = tramitAPersAutLogicEjb.select(TramitAPersAutFields.TRAMITID.equal(tramitid));
        
        tramitAPersAutLogicEjb.delete(list.get(0));
        log.info("Borramos TramitA");
        tramitAPersAutLogicEjb.deleteFull(tramitid);
    }

}