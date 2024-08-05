package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitGDadesTitEJB;
import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.pinbaladmin.model.entity.TramitGDadesTit;
import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitJPA;

/**
 * 
 * @author anadal
 */
@Stateless(name = "TramitGDadesTitLogicaEJB")
public class TramitGDadesTitLogicaEJB extends TramitGDadesTitEJB implements TramitGDadesTitLogicaService {

    @EJB(mappedName = TramitFCteTecLogicaService.JNDI_NAME)
    protected TramitFCteTecLogicaService tramitFCteTecLogicEjb;

    @Override
    @PermitAll
    public TramitGDadesTitJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public TramitGDadesTit create(TramitGDadesTit instance) throws I18NException {
        log.info("TramitGDadesTit::TramitID: " + instance.getTramitid());
        return super.create(instance);
    }

    @Override
    @PermitAll
    public TramitGDadesTit update(TramitGDadesTit instance) throws I18NException {
        return super.update(instance);
    }

    @Override
    public void deleteFull(Long tramitid) throws I18NException {
        //Borrar B i llamar a borrarFullB
        List<TramitFCteTec> list = tramitFCteTecLogicEjb.select(TRAMITID.equal(tramitid));
        
        tramitFCteTecLogicEjb.delete(list.get(0));
        log.info("Borramos TramitF");

        tramitFCteTecLogicEjb.deleteFull(tramitid);
    }

}