package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.EntitatEJB;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.pinbaladmin.persistence.EntitatJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "EntitatLogicaEJB")
public class EntitatLogicaEJB extends EntitatEJB implements EntitatLogicaService {

    @Override
    @PermitAll
    public Entitat create(Entitat instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public EntitatJPA findByPrimaryKey(Long _ID_) {
        return (EntitatJPA)super.findByPrimaryKey(_ID_);
    }
    
}