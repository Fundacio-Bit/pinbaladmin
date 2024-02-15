package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitECteAudEJB;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitECteAud;
import org.fundaciobit.pinbaladmin.persistence.TramitECteAudJPA;

/**
 * 
 * @author anadal
 */
@Stateless(name = "TramitECteAudLogicaEJB")
public class TramitECteAudLogicaEJB extends TramitECteAudEJB implements TramitECteAudLogicaService {

    @EJB(mappedName = TramitDCteAutLogicaService.JNDI_NAME)
    protected TramitDCteAutLogicaService tramitDCteAutLogicEjb;

    @Override
    @PermitAll
    public TramitECteAudJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public TramitECteAud create(TramitECteAud instance) throws I18NException {
        log.info("TramitECteAud::TramitID: " + instance.getTramitid());
        return super.create(instance);
    }

    @Override
    @PermitAll
    public TramitECteAud update(TramitECteAud instance) throws I18NException {
        return super.update(instance);
    }

    @Override
    public void deleteFull(Long tramitid) throws I18NException {
        //Borrar B i llamar a borrarFullB
        List<TramitDCteAut> list = tramitDCteAutLogicEjb.select(TRAMITID.equal(tramitid));
        
        tramitDCteAutLogicEjb.delete(list.get(0));
        log.info("Borramos TramitD");

        tramitDCteAutLogicEjb.deleteFull(tramitid);
    }
}