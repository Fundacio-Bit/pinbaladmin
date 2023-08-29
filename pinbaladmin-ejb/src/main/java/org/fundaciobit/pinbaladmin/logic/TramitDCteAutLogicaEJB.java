package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitDCteAutEJB;
import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
import org.fundaciobit.pinbaladmin.persistence.TramitDCteAutJPA;

/**
 * 
 * @author anadal
 */
@Stateless(name = "TramitDCteAutLogicaEJB")
public class TramitDCteAutLogicaEJB extends TramitDCteAutEJB implements TramitDCteAutLogicaService {
  
    @EJB(mappedName = TramitCDadesCesiLogicaService.JNDI_NAME)
    protected TramitCDadesCesiLogicaService tramitCDadesCesiLogicEjb;

    @Override
    @PermitAll
    public TramitDCteAutJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public TramitDCteAut create(TramitDCteAut instance) throws I18NException {
        log.info("TramitDCteAut::TramitID: " + instance.getTramitid());
        return super.create(instance);
    }
    @Override
    public void deleteFull(Long tramitid) throws I18NException {
        //Borrar B i llamar a borrarFullB
        List<TramitCDadesCesi> list = tramitCDadesCesiLogicEjb.select(TRAMITID.equal(tramitid));
        
        tramitCDadesCesiLogicEjb.delete(list.get(0));
        log.info("Borramos TramitC");

        tramitCDadesCesiLogicEjb.deleteFull(tramitid);
    }
    
}