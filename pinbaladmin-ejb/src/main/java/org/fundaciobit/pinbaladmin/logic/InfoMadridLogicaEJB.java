package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.InfoMadridEJB;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "InfoMadridLogicaEJB")
public class InfoMadridLogicaEJB extends InfoMadridEJB implements InfoMadridLogicaService {

    @Override
    @PermitAll
    public InfoMadrid create(InfoMadrid instance) throws I18NException {
    	log.info("Estaim a create");
    	
    	
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public InfoMadridJPA findByPrimaryKey(Long _ID_) {
    	
    	log.info("Farem un findByPK -- " + _ID_);
        return (InfoMadridJPA)super.findByPrimaryKey(_ID_);
    }
    
}