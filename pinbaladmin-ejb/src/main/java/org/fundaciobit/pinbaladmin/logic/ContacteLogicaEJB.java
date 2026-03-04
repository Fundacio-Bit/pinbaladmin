package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.ContacteEJB;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.persistence.ContacteJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "ContacteLogicaEJB")
public class ContacteLogicaEJB extends ContacteEJB implements ContacteLogicaService {

    @Override
    @PermitAll
    public Contacte create(Contacte instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public ContacteJPA findByPrimaryKey(Long _ID_) {
        return (ContacteJPA)super.findByPrimaryKey(_ID_);
    }
    
    @Override
    @PermitAll
	public Contacte crearContacteTitular(Solicitud soli) {
		ContacteJPA contacte = new ContacteJPA();

		contacte.setNif(soli.getTitularFirmaNif());
		contacte.setNom(soli.getTitularFirmaNom());
		contacte.setLlinatge1(soli.getTitularFirmaLlinatges());
		contacte.setMail(soli.getTitularFirmaEmail());

		return contacte;
	}
}