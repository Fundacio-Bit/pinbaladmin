package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.FitxerEJB;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;

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

	@Override
	@PermitAll
	public FitxerJPA findByPrimaryKey(Long _fitxerID_) {
		return super.findByPrimaryKey(_fitxerID_);
	}
	
    @Override
	@PermitAll
    public void delete(Fitxer instance) {
        super.delete(instance);
    }
}