package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.ServeiService;
import org.fundaciobit.pinbaladmin.ejb.TramitIServEJB;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;
import org.fundaciobit.pinbaladmin.persistence.TramitIServJPA;

/**
 * 
 * @author anadal
 */
@Stateless(name = "TramitIServLogicaEJB")
public class TramitIServLogicaEJB extends TramitIServEJB implements TramitIServLogicaService {

    @EJB(mappedName = TramitFCteTecLogicaService.JNDI_NAME)
    protected TramitFCteTecLogicaService tramitFCteTecLogicEjb;

    @EJB(mappedName = ServeiService.JNDI_NAME)
    protected ServeiService serveiEjb;

    @Override
    @PermitAll
    public TramitIServJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public TramitIServ create(TramitIServ instance) throws I18NException {
        log.info("TramitIServ::TramitID: " + instance.getTramitid());
        return super.create(instance);
    }

    @Override
    public void deleteFull(Long tramitid) throws I18NException {
        //Borrar B i llamar a borrarFullB
        List<TramitFCteTec> list = tramitFCteTecLogicEjb.select(TRAMITID.equal(tramitid));
        
        tramitFCteTecLogicEjb.delete(list.get(0));
        log.info("Borramos TramitF");

        tramitFCteTecLogicEjb.deleteFull(tramitid);
    }
    
    
    @Override
    public String getServeiValue(String servei) throws I18NException {

        List<StringKeyValue> list = getReferenceListForServei();
        log.info("servei: " + servei);

        for (StringKeyValue elem : list) {
            if (elem.getKey().equals(servei)) {
                return elem.getValue();
            }
        }
        log.info("Salio de acá vacio");
        return null;
    }

    @Override
    public List<StringKeyValue> getReferenceListForServei() throws I18NException  {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        
        List<Servei> serveis =  serveiEjb.select();
        
        __tmp.add(new StringKeyValue("", "..."));
        for (Servei servei : serveis) {
            __tmp.add(new StringKeyValue(servei.getCodi(), servei.getNom()));
        }
        return __tmp;
    }

}