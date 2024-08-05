package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitBDadesSoliEJB;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;
import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPA;

/**
 * 
 * @author anadal
 */
@Stateless(name = "TramitBDadesSoliLogicaEJB")
public class TramitBDadesSoliLogicaEJB extends TramitBDadesSoliEJB implements TramitBDadesSoliLogicaService {

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @Override
    @PermitAll
    public TramitBDadesSoliJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public TramitBDadesSoli create(TramitBDadesSoli instance) throws I18NException {
        log.info("TramitBDadesSoli::TramitID: " + instance.getTramitid());
        return super.create(instance);
    }

    @Override
    @PermitAll
    public TramitBDadesSoli update(TramitBDadesSoli instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    public void deleteFull(Long tramitid) throws I18NException {

        List<TramitAPersAut> list = tramitAPersAutLogicEjb.select(TramitAPersAutFields.TRAMITID.equal(tramitid));
        
        tramitAPersAutLogicEjb.delete(list.get(0));
        log.info("Borramos TramitA");
        tramitAPersAutLogicEjb.deleteFull(tramitid);
    }

    @Override
    public String getTipussolicitudValue(long tipussolicitud) {

        String tipus = String.valueOf(tipussolicitud);
        List<StringKeyValue> list = getReferenceListForTipussolicitud();
        log.info("tipus: " + tipus);

        for (StringKeyValue elem : list) {
            log.info("elem: " + elem.getValue());
            if (elem.getKey().equals(tipus)) {
                return elem.getValue();
            }
        }
        log.info("Salio de acá vacio");
        return null;
    }
    
    @Override
    public List<StringKeyValue> getReferenceListForTipussolicitud(){
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("1", "Alta"));
        __tmp.add(new StringKeyValue("2", "Modificació"));
        __tmp.add(new StringKeyValue("3", "Baixa"));
        return __tmp;
    }
}