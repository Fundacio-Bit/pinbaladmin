package org.fundaciobit.pinbaladmin.logic;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.ServeiEJB;
import org.fundaciobit.pinbaladmin.ejb.ServeiService;
import org.fundaciobit.pinbaladmin.logic.utils.LogicUtils;
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