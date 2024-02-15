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
import org.fundaciobit.pinbaladmin.ejb.OrganEJB;
import org.fundaciobit.pinbaladmin.ejb.OrganService;
import org.fundaciobit.pinbaladmin.logic.utils.LogicUtils;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.persistence.OrganJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "OrganLogicaEJB")
public class OrganLogicaEJB extends OrganEJB implements OrganLogicaService {

    @Override
    @PermitAll
    public Organ create(Organ instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public OrganJPA findByPrimaryKey(Long _ID_) {
        return (OrganJPA)super.findByPrimaryKey(_ID_);
    }
    
}