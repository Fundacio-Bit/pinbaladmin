package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.model.entity.Servei;

/**
 * 
 * @author anadal
 *
 */
@RunAs(Constants.PAD_ADMIN)
@PermitAll
@Stateless(name = "WebServicesLogicaEJB")
public class WebServicesLogicaEJB implements WebServicesLogicaService {

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.ServeiService serveiEjb;

    @Override
    @PermitAll
    public List<Servei> serveiEjbSelect(Where w) throws I18NException {
        return serveiEjb.select(w);
    }

}
