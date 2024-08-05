package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.DocumentEJB;
import org.fundaciobit.pinbaladmin.model.entity.Document;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "DocumentLogicaEJB")
public class DocumentLogicaEJB extends DocumentEJB implements DocumentLogicaService {

    @Override
    @PermitAll
    public Document create(Document instance) throws I18NException {
        return super.create(instance);
    }
    
	@PermitAll
	@Override
	public Document update(Document instance) throws I18NException {
		return super.update(instance);
	}
}