package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.EntitatServeiEJB;
import org.fundaciobit.pinbaladmin.model.entity.EntitatServei;
import org.fundaciobit.pinbaladmin.persistence.EntitatServeiJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "EntitatServeiLogicEJB")
public class EntitatServeiLogicEJB extends EntitatServeiEJB implements EntitatServeiLogicService {

    @Override
    @PermitAll
    public EntitatServei create(EntitatServei instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public EntitatServeiJPA findByPrimaryKey(Long _ID_) {
        return (EntitatServeiJPA)super.findByPrimaryKey(_ID_);
    }
    
    
    
}