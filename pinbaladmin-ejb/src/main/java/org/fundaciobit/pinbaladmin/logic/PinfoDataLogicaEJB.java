package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.PinfoDataEJB;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PinfoDataLogicaEJB")
public class PinfoDataLogicaEJB extends PinfoDataEJB implements PinfoDataLogicaService {

	@Override
	@PermitAll
	public PinfoData create(PinfoData instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public PinfoDataJPA findByPrimaryKey(Long _ID_) {
		return (PinfoDataJPA) super.findByPrimaryKey(_ID_);
	}

	@Override
	@PermitAll
	public PinfoData update(PinfoData instance) throws I18NException {
		return super.update(instance);
	}

	@Override
	@PermitAll
	public void delete(Long id) {
		super.delete(id);
	}
	
	@Override
	@PermitAll
	public void delete(PinfoData instance) {
		super.delete(instance);
	}

}