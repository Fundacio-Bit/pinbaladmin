package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitFCteTecEJB;
import org.fundaciobit.pinbaladmin.model.entity.TramitECteAud;
import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.pinbaladmin.persistence.TramitFCteTecJPA;

/**
 * 
 * @author anadal
 */
@Stateless(name = "TramitFCteTecLogicaEJB")
public class TramitFCteTecLogicaEJB extends TramitFCteTecEJB implements TramitFCteTecLogicaService {

    @EJB(mappedName = TramitECteAudLogicaService.JNDI_NAME)
    protected TramitECteAudLogicaService tramitECteAudLogicEjb;

    @Override
    @PermitAll
    public TramitFCteTecJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public TramitFCteTec create(TramitFCteTec instance) throws I18NException {
        log.info("TramitFCteTec::TramitID: " + instance.getTramitid());
        return super.create(instance);
    }

    @Override
    public void deleteFull(Long tramitid) throws I18NException {
        //Borrar B i llamar a borrarFullB
        List<TramitECteAud> list = tramitECteAudLogicEjb.select(TRAMITID.equal(tramitid));
        
        tramitECteAudLogicEjb.delete(list.get(0));
        log.info("Borramos TramitE");

        tramitECteAudLogicEjb.deleteFull(tramitid);
    }

}