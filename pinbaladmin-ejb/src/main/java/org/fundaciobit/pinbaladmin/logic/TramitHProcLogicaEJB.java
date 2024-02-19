package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitHProcEJB;
import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.persistence.TramitHProcJPA;

/**
 * 
 * @author anadal
 */
@Stateless(name = "TramitHProcLogicaEJB")
public class TramitHProcLogicaEJB extends TramitHProcEJB implements TramitHProcLogicaService {

    @EJB(mappedName = TramitFCteTecLogicaService.JNDI_NAME)
    protected TramitFCteTecLogicaService tramitFCteTecLogicEjb;

    @Override
    @PermitAll
    public TramitHProcJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public TramitHProc create(TramitHProc instance) throws I18NException {
        log.info("TramitHProc::TramitID: " + instance.getTramitid());
        return super.create(instance);
    }

    @Override
    @PermitAll
    public TramitHProc update(TramitHProc instance) throws I18NException {
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


//    public String getTipusProcediment(String key) {
//        
//    }
    
}