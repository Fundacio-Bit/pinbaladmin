package org.fundaciobit.pinbaladmin.logic;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.ejb.ContacteService;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ContacteLogicaService extends ContacteService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/ContacteLogicaEJB!org.fundaciobit.pinbaladmin.logic.ContacteLogicaService";

	Contacte crearContacteTitular(Solicitud soli);

}
