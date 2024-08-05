package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.ServeiEJB;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "ServeiLogicaEJB")
public class ServeiLogicaEJB extends ServeiEJB implements ServeiLogicaService {

    @Override
    @PermitAll
    public Servei create(Servei instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public ServeiJPA findByPrimaryKey(Long _ID_) {
        return (ServeiJPA)super.findByPrimaryKey(_ID_);
    }
    
    
    
}