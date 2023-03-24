package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.FitxerEJB;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;

/**
 * 
 * @author anadal
 */
@Stateless(name = "FitxerPublicLogicaEJB")
public class FitxerPublicLogicaEJB extends FitxerEJB implements FitxerPublicLogicaService {

    @Override
    @PermitAll
    public Fitxer create(Fitxer bean) throws I18NException {
        return super.create(bean);
    }

    @Override
    @PermitAll
    public Fitxer update(Fitxer instance) throws I18NException {
        return super.update(instance);
    }

}