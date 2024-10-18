package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.PINFOEJB;
import org.fundaciobit.pinbaladmin.model.entity.PINFO;
import org.fundaciobit.pinbaladmin.persistence.PINFOJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PINFOLogicaEJB")
public class PINFOLogicaEJB extends PINFOEJB implements PINFOLogicaService {

    @Override
    @PermitAll
    public PINFO create(PINFO instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public PINFOJPA findByPrimaryKey(Long _ID_) {
        return (PINFOJPA)super.findByPrimaryKey(_ID_);
    }
    
}